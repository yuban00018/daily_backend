package com.daily.controller;


import com.daily.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import javax.annotation.Resource;


/**
 * Au_Miner
 * 2022-3-26
 */
@Slf4j
@Configuration
public class KafkaController {
    @Resource
    KafkaService kafkaService;

    @KafkaListener(topics = "groupExpChangeKafka")
    public void consumerTopic(String msg) {
        // log.info("kafka:" + msg);
        if (!msg.isEmpty())
            kafkaService.getGroupRank(msg);
    }
}