package com.sinkovdenis.reportrequester.publisher;

import com.sinkovdenis.reportrequester.GenericTest;
import com.sinkovdenis.reportrequester.TestSinglePartitionTopicHelper;
import com.sinkovdenis.reportrequester.model.ReportType;
import com.sinkovdenis.reportrequester.model.request.ByDateReportRequest;
import com.sinkovdenis.reportrequester.model.request.ByIdsReportRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static com.sinkovdenis.reportrequester.TestData.createByDateReportRequest;
import static com.sinkovdenis.reportrequester.TestData.createByIdsReportRequest;
import static com.sinkovdenis.reportrequester.publisher.ReportRequestPublisherTestParams.ANY_GROUP;
import static com.sinkovdenis.reportrequester.publisher.ReportRequestPublisherTestParams.REPORT_REQUEST_TOPIC;

@EmbeddedKafka(
        partitions = 1,
        topics = {REPORT_REQUEST_TOPIC},
        brokerProperties = {
            "transaction.state.log.replication.factor=1",
            "transaction.state.log.min.isr=1"
        }
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReportRequestPublisherIntegrationTest extends GenericTest {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @SpyBean
    private ReportRequestPublisher publisher;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private TestSinglePartitionTopicHelper helper;

    private static ByDateReportRequest BY_DATE_REPORT_REQUEST;
    private static ByIdsReportRequest BY_IDS_REPORT_REQUEST;

    @Before
    public void setUp() {
        helper = new TestSinglePartitionTopicHelper(
                embeddedKafkaBroker,
                TimeUnit.SECONDS.toMillis(1)
        );
        helper.waitListeners(kafkaListenerEndpointRegistry);

        BY_DATE_REPORT_REQUEST = createByDateReportRequest(ReportType.ORDERS_REPORT);
        BY_IDS_REPORT_REQUEST = createByIdsReportRequest(ReportType.ORDERS_REPORT);
    }

    @Test
    public void testPublish_byDateReportRequest() throws Exception {
        helper.assertEmpty(REPORT_REQUEST_TOPIC, ANY_GROUP);

        publisher.publish(BY_DATE_REPORT_REQUEST);

        TimeUnit.SECONDS.sleep(5);
        helper.assertOne(REPORT_REQUEST_TOPIC, ANY_GROUP);
    }

    @Test
    public void testPublish_byIdsReportRequest() throws Exception {
        helper.assertEmpty(REPORT_REQUEST_TOPIC, ANY_GROUP);

        publisher.publish(BY_IDS_REPORT_REQUEST);

        TimeUnit.SECONDS.sleep(5);
        helper.assertOne(REPORT_REQUEST_TOPIC, ANY_GROUP);
    }
}
