package liga.medical.service;

import liga.medical.entity.DebugEntity;
import liga.medical.entity.ExceptionEntity;
import liga.medical.repository.DebugRepository;
import liga.medical.repository.ExceptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.LogMessage;
import model.SystemType;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoggingService {

    private final DebugRepository debugRepository;
    private final ExceptionRepository exceptionRepository;

    public void log(LogMessage message, SystemType systemType) {
        log.info(message.buildMessage());

        DebugEntity debug = DebugEntity.builder()
                .uuid(UUID.randomUUID())
                .systemTypeId(systemType)
                .methodParams(message.getMessageParams())
                .build();
        debugRepository.save(debug);
    }

    public void logException(LogMessage message, SystemType systemType) {
        log.error(message.buildMessage());

        ExceptionEntity exception = ExceptionEntity.builder()
                .uuid(UUID.randomUUID())
                .systemTypeId(systemType)
                .methodParams(message.getMessageParams())
                .build();
        exceptionRepository.save(exception);
    }
}
