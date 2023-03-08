package com.example.demo.mq.provider;

import cn.hutool.core.date.DateUtil;
import com.example.demo.entity.OrderInfo;
import com.example.demo.entity.ReadInfo;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        try {
            ReadInfo readInfo=new ReadInfo();
            readInfo.setId(0);
            readInfo.setValue("5");
            CorrelationData correlationData=new CorrelationData();
            rabbitTemplate.convertAndSend("order.exchange","order.key",readInfo,correlationData);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.formatDate(new Date()));
    }
}
