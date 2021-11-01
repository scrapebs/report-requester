package com.sinkovdenis.reportrequester.configuration.kafka;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaAdditionalHeaders {
    public static final String MESSAGE_ID = "MessageId";
    public static final String SENDER_ID = "SenderId";
    public static final String SENDER_NAME = "SenderName";
}
