package com.brotao.study.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author luotao
 * @Date 27/8/2022
 * @Description
 */

public class CustomProducer {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(prop);

        for (int i = 0; i < 5; i++) {
            // 异步发送不带回调函数
//            producer.send(new ProducerRecord<>("second", "hello " + i));
            // 带回调的异步发送
/*            producer.send(new ProducerRecord<>("first", "hello " + i)
                    , ((metadata, exception) -> {
                        if (exception == null) {
                            System.out.println("主题:" + metadata.topic() + "->" + "分区：" + metadata.partition());
                        } else {
                            exception.printStackTrace();
                        }
                    }));*/
            // 同步发送
/*            try {
                producer.send(new ProducerRecord<>("second", "message " + i)).get();
                System.out.println("发送完 message" + i);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }*/
            // 指定分区
            producer.send(new ProducerRecord<>("first", 0, null, "hello " + i)
                    , ((metadata, exception) -> {
                        if (exception == null) {
                            System.out.println("主题:" + metadata.topic() + "->" + "分区：" + metadata.partition());
                        } else {
                            exception.printStackTrace();
                        }
                    }));
        }

        LockSupport.parkNanos(5000000);
        producer.close();
    }
}
