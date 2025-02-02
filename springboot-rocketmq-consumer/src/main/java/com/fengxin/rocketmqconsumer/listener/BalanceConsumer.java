package com.fengxin.rocketmqconsumer.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author FENGXIN
 * @date 2024/9/22
 * @project springboot-part
 * @description
 **/
@Component
@RocketMQMessageListener (
        topic = "bootBalanceTopic",
        consumerGroup = "boot-balance-consumer-group",
        // CLUSTERING 集群模式 5.x版本下 pushConsumer simpleConsumer默认使用消息粒度均衡
        // BROADCASTING 广播模式 每个消费组的每一个消费者都消费同一条消息
        messageModel = MessageModel.CLUSTERING
)
public class BalanceConsumer implements RocketMQListener<MessageExt> {
    /**
     * 消息堆积解决：<br>
     *  1.生产者生产太快问题<br>
     *      1）增加消费者数量 但不大于队列数量<br>
     *      2）适当增加线程数量 IO 2n CPU n || n + 1<br>
     *      3) 动态扩容队列数量 从而增加消费者数量<br>
     *  2.消费者问题<br>
     *      1）排查消费者程序问题<br>
     * 消息丢失解决：<br>
     *  1.producer发送消息时将相关信息存入MySQL持久化<br>
     *  2.consumer消费消息后更新相关信息在MySQL<br>
     *  3.定时任务 检索已经发送的消息但是长时间未消费的消息 做相应业务逻辑 如重新发送 并更新数据库（保证幂等性防止重复操作）<br>
     * @param messageExt
     */
    @Override
    public void onMessage (MessageExt messageExt) {
    
    }
}
