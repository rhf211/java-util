package com.example.demo;

import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.config.SpringContext;
import com.example.demo.dao.ConfigDao;
import com.example.demo.dao.ReaderDao;
import com.example.demo.entity.*;
import com.example.demo.service.AppletsService;
import com.example.demo.service.Pay;
import com.example.demo.service.ReadService;
import com.example.demo.service.impl.ResChainHandler;
import com.example.demo.util.ConfigCenterWrapper;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private ReadService readService;

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

    @Autowired
    AppletsService appletsService;

    @Resource(name = "redisTemplate")
    RedisTemplate redisTemplate;


    @Test
    void contextLoads() {
        redisTemplate.opsForValue()
                .set("user:session:" + "10086", JSONUtil.toJsonStr(new ReadInfo(1, "sd")), 1L, TimeUnit.DAYS);
    }

    @Test
    void swagger2() {
        IPage<ReadInfo> reader = readService.getReader(new Date());
    }

    @Test
    void restTemplateTest() {
        String url = "https://api.juejin.cn/recommend_api/v1/short_msg/recommend?aid=2608&uuid=7051397084989244942";
        //GET 请求
        //ReadInfo readInfo = template.getForObject(url, ReadInfo.class);
        URI uri = URI.create(url);
        //ResponseEntity<JuejinEntity> responseEntity = template.postForEntity(uri, null, JuejinEntity.class);

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
        //Object body = responseEntity.getBody();
        //System.out.println(body);
    }

    @Test
    void rabbitMqTest() {

    }

    @Test
    void redissonTest() {
        RLock lock = redissonClient.getLock("test:lock");
        try {
            boolean b = lock.tryLock();
            if (b) {
                System.out.println("lock success");
            }
        } catch (Exception e) {

        }
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
        readInfo.set_deleted(true);

        ReadInfo readInfo1 = readerDao.selectById(1L);
        boolean deleted = readInfo1.is_deleted();
        System.out.println(deleted);
    }

    @Test
    public void buildQuery() {
        QueryWrapper<ReadInfo> wrapper = new QueryWrapper<>();
        String s = "2";
        QueryWrapper<ReadInfo> or = wrapper.or(ObjectUtil.isNotEmpty(s), w -> {
            w.like("id", s)
                    .or().like("value", s)
                    .or().like("sms_id", s);
        });
        readerDao.selectList(wrapper);
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
        List<ReadInfo> list = new ArrayList<>();*/
        //将list 转为 map
        //
        List<ReadInfo> list=new ArrayList<>();
        list.add(new ReadInfo());
        List<ReadInfo> list1=null;
        boolean empty = CollUtil.isEmpty(list);
        boolean empty1 = CollUtil.isEmpty(list1);
        System.out.println(empty);
        System.out.println(empty1);
        //list 根据id 相互匹配对象中的数据
    }

    @Test
    public void redisCluster() {
        RBucket<Object> bucket1 = redissonClient.getBucket("5");
        bucket1.set(5);
    }

    @Test
    public void daoTest() {
        ReadInfo readInfo = readerDao.selectById(1);
        List<Attrs> attrs = readInfo.getAttrs();
        for (Attrs attr : attrs) {
            if (attr.getType().equals("String")){
                attr.setAttr("11111");
            }
        }
        //readInfo.setAttrs(attrs);
        System.out.println(readInfo);
    }
}
