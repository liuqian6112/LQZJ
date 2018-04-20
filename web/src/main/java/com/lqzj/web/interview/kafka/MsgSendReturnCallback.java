package com.lqzj.web.interview.kafka;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author liuqian1
 */
public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String msgJson = new String(message.getBody());
        System.out.println("回馈消息：" + msgJson);
    }
}