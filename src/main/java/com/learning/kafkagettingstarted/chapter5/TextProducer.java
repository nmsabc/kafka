package com.learning.kafkagettingstarted.chapter5;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;

public class TextProducer {
    // private static final String TOPIC_NAME = "text_topic";
    private static final String TOPIC_NAME = "kafka.usecase.students";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final int MAX_MESSAGE_LENGTH = 100;
    private static final int MIN_MESSAGE_LENGTH = 10;

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("key.separator", " : ");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        Random rand = new Random();

        int counter = 1000;

        while (true) {
            String message = getRandomString(
                    rand.nextInt(MAX_MESSAGE_LENGTH - MIN_MESSAGE_LENGTH) + MIN_MESSAGE_LENGTH);
            producer.send(new ProducerRecord<>(TOPIC_NAME, String.valueOf(counter), message));
            System.out.println("# " + counter + " of " + message.length() + " length >> Sent: " + message);
            counter += 1;
            Thread.sleep(1000);
        }
    }

    private static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            char c = (char) (rand.nextInt(26) + 'a');
            sb.append(c);
        }

        return sb.toString();
    }

    private static ProducerRecord<String, String> generateMessage(Random random) {
        int length = random.nextInt(90) + 10;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        String key = UUID.randomUUID().toString();
        String value = sb.toString();
        return new ProducerRecord<>("my_topic", key, value);
    }
}
