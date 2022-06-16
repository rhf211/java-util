package com.example.demo.service.impl;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

@Service
public class ConfirmListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            System.out.println("消息处理完成"+new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }

    }
}
