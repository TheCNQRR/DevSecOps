package by.java.enterprise.employeeservice.scheduler;

import by.java.enterprise.employeeservice.entity.OutboxEvent;
import by.java.enterprise.employeeservice.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxEventProcessor {

    private final OutboxEventRepository outboxEventRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processSingleEvent(OutboxEvent event, String topic) {
        try {
            kafkaTemplate.send(topic, event.getPayload()).get();

            event.setPublished(true);
            outboxEventRepository.save(event);

            log.info("employee data published successfully for event id: {}", event.getId());
        } catch (Exception e) {
            log.error("Failed to send outbox event id {}: {}", event.getId(), e.getMessage());
        }
    }
}