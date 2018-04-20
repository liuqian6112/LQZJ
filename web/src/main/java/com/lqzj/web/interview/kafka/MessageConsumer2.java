package com.lqzj.web.interview.kafka;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * @author liuqian1
 */
public class MessageConsumer2 implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
        byte[] body = message.getBody();
        System.out.println("收到消息2 : " + new String(body));
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

    }
}