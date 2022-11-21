package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogRestMessage {

    private String logId;
    private String username;
    private String className;
    private String methodName;
    private String args;

    public String buildMessage() {
        StringBuilder result = new StringBuilder("[" + logId + "] ");
        if (username != null) {
            result.append("пользователем [").append(username).append("] ");
        }
        result.append(
                String.format("из класса %s вызван метод %s() с аргументами %s",
                        className, methodName, args)
        );
        return result.toString();
    }

    public String getMessageParams() {
        return "class:[" + className + "], " +
                "method:[" + methodName + "], " +
                "args:[" + args + "]";
    }
}
