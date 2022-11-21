package liga.medical.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.entity.DebugEntity;
import liga.medical.entity.ExceptionEntity;
import liga.medical.repository.DebugRepository;
import liga.medical.repository.ExceptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.LogRestMessage;
import model.RabbitMessageDto;
import model.SystemType;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoggingService {

    private final DebugRepository debugRepository;
    private final ExceptionRepository exceptionRepository;

    private final ObjectMapper objectMapper;

    public void log(LogRestMessage message, SystemType systemType) {
        log.info(message.buildMessage());

        DebugEntity debug = DebugEntity.builder()
                .uuid(UUID.randomUUID())
                .systemTypeId(systemType)
                .methodParams(message.getMessageParams())
                .build();
        debugRepository.save(debug);
    }

    public void logQueueMessageSending(RabbitMessageDto messageDto, String queue, SystemType systemType)
            throws JsonProcessingException {
        String message = String.format(
                "Сообщение [%s] отправлено в очередь [%s]",
                objectMapper.writeValueAsString(messageDto), queue);
        log.info(message);

        DebugEntity debug = DebugEntity.builder()
                .uuid(UUID.randomUUID())
                .systemTypeId(systemType)
                .methodParams(message)
                .build();
        debugRepository.save(debug);
    }

    public void logQueueMessageReceiving(RabbitMessageDto messageDto, String queue, SystemType systemType)
            throws JsonProcessingException {
        String message = String.format(
                "Сообщение [%s] получено из очереди [%s]",
                objectMapper.writeValueAsString(messageDto), queue);
        log.info(message);

        DebugEntity debug = DebugEntity.builder()
                .uuid(UUID.randomUUID())
                .systemTypeId(systemType)
                .methodParams(message)
                .build();
        debugRepository.save(debug);
    }

    public void logQueueSendingException(String exceptionMessage, String queue, SystemType systemType) {
        String message = String.format(
                "Сообщение об ошибке [%s] отправлено в очередь [%s]",
                exceptionMessage, queue);
        log.error(message);

        DebugEntity debug = DebugEntity.builder()
                .uuid(UUID.randomUUID())
                .systemTypeId(systemType)
                .methodParams(message)
                .build();
        debugRepository.save(debug);
    }

    public void logRestException(LogRestMessage message, SystemType systemType) {
        log.error(message.buildMessage());

        ExceptionEntity exception = ExceptionEntity.builder()
                .uuid(UUID.randomUUID())
                .systemTypeId(systemType)
                .methodParams(message.getMessageParams())
                .build();
        exceptionRepository.save(exception);
    }

    public void logException(String message, SystemType systemType) {
        log.error(message);

        ExceptionEntity exception = ExceptionEntity.builder()
                .uuid(UUID.randomUUID())
                .systemTypeId(systemType)
                .methodParams(message)
                .build();
        exceptionRepository.save(exception);
    }
}
