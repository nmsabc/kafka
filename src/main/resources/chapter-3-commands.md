This file contains the shell commands for the Kafka Essentials : Getting Started Course

## Logging into the Kafka Container

docker exec -it kafka-broker /bin/bash

## Navigate to the Kafka Scripts directory

cd /opt/bitnami/kafka/bin

## Creating new Topics - old

## this works!

./kafka-topics.sh --create \
--topic test-topic \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 4

./kafka-topics.sh --create \
--topic kafka-learning-tweets \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 4

./kafka-topics.sh --create \
--topic kafka-learning-alerts \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 4

## delete topics

./kafka-topics.sh --delete \
--topic kafka-learning-tweets \
--bootstrap-server localhost:9092

./kafka-topics.sh --delete \
--topic kafka-learning-alerts \
--bootstrap-server localhost:9092

## Listing Topics

./kafka-topics.sh --list --bootstrap-server localhost:9092

## describe topics

./kafka-topics.sh --describe --bootstrap-server localhost:9092

## Publishing Messages to Topics

./kafka-console-producer.sh \
 --bootstrap-server localhost:29092 \
 --topic kafka-learning-tweets

## Consuming Messages from Topics

docker exec -it kafka-broker /bin/bash
cd /opt/bitnami/kafka/bin

./kafka-console-consumer.sh \
 --bootstrap-server localhost:29092 \
 --topic kafka-learning-tweets \
 --from-beginning

## Deleting Topics

./kafka-topics.sh \
 --zookeeper zookeeper:2181 \
 --delete \
 --topic kafka.learning.alerts

## create a first and a second consumer groups

docker exec -it kafka-broker /bin/bash
cd /opt/bitnami/kafka/bin
./kafka-console-consumer.sh --bootstrap-server localhost:29092 --topic kafka-learning-tweets --from-beginning --property print.key=true --property key.separator=" = " --group test-cons-group

## use the java app to produce topics

docker exec -it kafka-broker /bin/bash
cd /opt/bitnami/kafka/bin

### list them

./kafka-topics.sh --list --bootstrap-server localhost:9092

### describe them

./kafka-topics.sh --describe --bootstrap-server localhost:9092

### consume them

./kafka-console-consumer.sh \
 --bootstrap-server localhost:29092 \
 --topic kafka.learning.orders \
 --from-beginning
