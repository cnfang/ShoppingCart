package idv.cnfang.utility;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class CustomApiError {
    
    private HttpStatus status;
    private LocalDateTime timeStamp;
    private String message;
    private String debugMessage;
    
    
    public CustomApiError(HttpStatus status, String message) {
        this.timeStamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }
    
    public CustomApiError(HttpStatus status, String message, Exception exception) {
        this.timeStamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.debugMessage = exception.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
