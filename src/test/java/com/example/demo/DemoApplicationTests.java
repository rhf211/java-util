package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.config.SpringContext;
import com.example.demo.dao.ConfigDao;
import com.example.demo.dao.ReaderDao;
import com.example.demo.entity.ConfigDO;
import com.example.demo.entity.JdGoods;
import com.example.demo.entity.ReadInfo;
import com.example.demo.entity.juejin.JuejinEntity;
import com.example.demo.entity.test.A;
import com.example.demo.service.LookService;
import com.example.demo.service.Pay;
import com.example.demo.service.ReadService;
import com.example.demo.service.impl.ResChainHandler;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.ProjectInfoUtils;
import io.netty.handler.codec.redis.ArrayRedisMessage;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.junit.jupiter.api.Test;
import org.redisson.RedissonCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private ReadService readService;

    @Autowired
    private LookService lookService;

    @Autowired
    private RestTemplate template;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ConfigDao configDao;

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private BBossESStarter bBossESStarter;


    @Test
    void contextLoads() {
        //readService.getReader();
        lookService.testLook();
    }

    @Test
    void swagger2() {
        IPage<ReadInfo> reader = readService.getReader();
        reader.getRecords().stream().forEach(readInfo -> System.out.println(readInfo.getSms_id()));
    }

    @Test
    void restTemplateTest() {
        String url = "https://api.juejin.cn/recommend_api/v1/short_msg/recommend?aid=2608&uuid=7051397084989244942";
        //GET 请求
        //ReadInfo readInfo = template.getForObject(url, ReadInfo.class);
        URI uri = URI.create(url);
        ResponseEntity<JuejinEntity> responseEntity = template.postForEntity(uri, null, JuejinEntity.class);

        /*//POST请求
        MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
        request.set("userName", "张三1");
        request.set("userId", 1);
        ReadInfo resultData = template.postForObject(url, request, ReadInfo.class);
        System.out.println("*****POST表单提交使用URI查询返回结果={}" + resultData);
        //方法二：使用URI
        URI uri = URI.create(url);
        resultData = template.postForObject(uri, new ReadInfo(), ReadInfo.class);
        System.out.println("******POST使用URI查询返回结果={}" + resultData);*/
        Object body = responseEntity.getBody();
        System.out.println(body);
    }

    @Test
    void rabbitMqTest() {

    }

    @Test
    public void testBuilderControllersApiSimple() {
        RLock ds = redissonClient.getLock("mkt:add_sign_up_coupon");
        //控制顺序
        CountDownLatch countDownLatch = new CountDownLatch(10);
        //控制并发
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                    //等待时间 加锁时间 单位
                    boolean b = ds.tryLock(0L, 10L, TimeUnit.SECONDS);

                    if (b) {
                        System.out.println(Thread.currentThread().getName() + "加锁成功");

                    } else {
                        System.out.println(Thread.currentThread().getName() + "加锁失败");

                    }
                    Thread.sleep(1000);

                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (ds.isHeldByCurrentThread()) {
                        ds.unlock();
                        semaphore.release();
                    }
                }
            }, "Thread" + i);
            thread.start();
        }
        try {
            countDownLatch.await();
            System.out.println("子线程执行完毕");
        } catch (Exception e) {

        }

    }

    /**
     * 多数据源测试
     */
    @Test
    public void doubleDataSource() {
        ReadInfo readInfo = readerDao.selectById(251156);
        ConfigDO configDO = configDao.selectById(1);
        System.out.println(readInfo.toString());
        System.out.println(configDO.toString());
    }

    @Test
    public void bbossClient() {
        ClientInterface restClient = bBossESStarter.getRestClient();
        //String posts = restClient.getDocument("jd_goods", "_doc", "Kptr2H0Bpcz767VWbteD");
        ESDatas<JdGoods> esDatas = restClient.searchAllParallel("jd_goods", JdGoods.class, 2);

        List<JdGoods> datas = esDatas.getDatas();
        for (JdGoods data : datas) {
            System.out.println(data.getName());
            System.out.println(data.getPrice());

        }
    }

    @Test
    public void bacthInsert() {
        ReadInfo readInfo = new ReadInfo();
        readInfo.setId(1);
        readInfo.setValue("kk5kww");
        readInfo.setSms_id("dsd");
        // readerDao.insertOrupdate(null);

        readerDao.batchUpdate(Arrays.asList(readInfo));
    }

    @Test
    public void payAdpater() throws Exception {
        ApplicationContext CTX = SpringContext.getApplicationContext();
        Map<String, Pay> beansOfType = CTX.getBeansOfType(Pay.class);
        String s1 = beansOfType.values().stream().
                filter(s -> s.refund("wx"))
                .findFirst().map(pay -> pay.payInfo()).orElseThrow(() -> new Exception("未定义该订单类型的处理"));
        System.out.println(s1);

    }

    @Test
    public void resChain() {
        ApplicationContext CTX = SpringContext.getApplicationContext();
        Map<String, Pay> map = CTX.getBeansOfType(Pay.class);
        ResChainHandler resChainHandler = new ResChainHandler();
        map.values().forEach(pay -> {
            resChainHandler.add(pay);
        });
        resChainHandler.handler();
    }

    public static void main(String[] args) {
        /*List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);
        int pageSize = 2;
        List<List<Integer>> splitList = IntStream
                //{0,1,2}三页
                .range(0, (ids.size() + pageSize - 1) / pageSize)
                //{0,2,4} 每页跳过元素
                .map(pageNum -> pageNum * pageSize)
                //并行处理每个流
                .parallel()
                //对ids做跳跃操作
                .mapToObj(i -> ids.stream().skip(i).limit(pageSize).collect(Collectors.toList()))
                .collect(Collectors.toList());
        for (List<Integer> list : splitList) {
            System.out.println(Arrays.toString(list.toArray()));
        }
        List<ReadInfo> list = new ArrayList<>();

        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();*/
        List<Integer> ids = Arrays.asList(5, 1, 3, 4, 2);
        A a=new A();
        a.setSex("male");
        A ba=new A();
        ba.setSex("female");
        A ra=new A();
        ra.setSex("male");

        List<A> as = Arrays.asList(a, ba, ra);
        List<Integer> collect1 = ids.stream().sorted((x, y) -> x-y).collect(Collectors.toList());
        List<Integer> collect = ids.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).collect(Collectors.toList());
        List<Integer> collect2 = ids.stream().filter((x) -> x > 2).collect(Collectors.toList());

        Map<String, List<A>> collect3 = as.stream().collect(Collectors.groupingBy(a1 -> a1.getSex()));

        System.out.println(Arrays.toString(collect.toArray()));
        System.out.println(Arrays.toString(collect1.toArray()));
        System.out.println(Arrays.toString(collect2.toArray()));
        System.out.println(collect3);

    }
}
