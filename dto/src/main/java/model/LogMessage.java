package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogMessage {

    private String logId;
    private String username;
    private String className;
    private String methodName;
    private String args;

    public String buildMessage() {
        return String.format(
                "[%s] пользователем [%s] из класса %s вызван метод %s() с аргументами %s",
                logId, username, className, methodName, args);
    }

    public String getMessageParams() {
        return "class:[" + className + "], " +
                "method:[" + methodName + "], " +
                "args:[" + args + "]";
    }
}
