package com.nowak.app.service;


import kafka.admin.AdminUtils;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class TopicServiceImpl implements TopicService {

    private final String ZOOKEEPER_HOST = "127.0.0.1:2181";
    private final String BOOTSTRAP_SERVERS = "127.0.0.1:9092";
    private final int PARTITIONS = 3;
    private final int REPLICATION_FACTOR = 1;
    ZkClient zkClient = null;

    @Override
    public void createTopic(String topicName) {
        try {
            String zookeeperHost = "127.0.0.1:2181";
            int sessionTimeOut = 15 * 1000;
            int connectionTimeOut = 10 * 1000;

            zkClient = new ZkClient(zookeeperHost, sessionTimeOut, connectionTimeOut);
            Properties topicConfiguration = new Properties();

            AdminUtils.createTopic(zkClient, topicName, PARTITIONS, REPLICATION_FACTOR, topicConfiguration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
