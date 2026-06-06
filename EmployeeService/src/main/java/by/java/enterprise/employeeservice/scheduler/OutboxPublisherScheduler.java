package by.java.enterprise.employeeservice.scheduler;

import by.java.enterprise.employeeservice.entity.OutboxEvent;
import by.java.enterprise.employeeservice.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxPublisherScheduler {

    private final OutboxEventRepository outboxEventRepository;
    private final OutboxEventProcessor eventProcessor;

    @Value("${kafka.topic.employee-data}")
    private String topic;

    @Scheduled(fixedDelayString = "${scheduler.outbox-fixed-delay}")
    public void publishOutboxEvents() {
        List<OutboxEvent> events = outboxEventRepository.findByPublishedFalseOrderByCreatedAtAsc();

        for (OutboxEvent event : events) {
            try {
                eventProcessor.processSingleEvent(event, topic);
            } catch (Exception e) {
                log.warn("Skipping event {} due to error, moving to next", event.getId());
            }
        }
    }
}
