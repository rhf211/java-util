package com.example.demo.mq.listener;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ReadInfo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Map;

@Component
public class TestListener {

    @RabbitListener(queues = "info")
    public void receiveInfo(Message msg, Channel channel) throws IOException {
        System.out.println("队列info:" + new String(msg.getBody()));
        ReadInfo readInfo = JSONObject.parseObject(msg.getBody(), ReadInfo.class);

        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), true);
        System.out.println(readInfo.toString());
    }

    @RabbitListener(queues = "error")
    public void receiveError(Message msg, Channel channel) throws IOException {
        System.out.println("队列error1:" + new String(msg.getBody()));
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("order.queue"),
            exchange = @Exchange("order.exchange"),
            key = "order.key"
    ))
    public void receiveOrder(@Payload ReadInfo readInfo, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        System.out.println("队列order:" + readInfo.toString());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //channel.basicAck(msg.getMessageProperties().getDeliveryTag(),true);
        channel.basicAck(deliveryTag, true);
    }


    @RabbitListener(queues = "warning")
    public void receiveWarning(Message msg, Channel channel) throws Exception {
        System.out.println("队列warning:" + new String(msg.getBody()));

    }
}
