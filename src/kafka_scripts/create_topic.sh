cd /home/kamila/kafka_2.12-2.5.0/
kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic chat --create --partitions 1 --replication-factor 1