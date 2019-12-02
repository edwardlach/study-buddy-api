package dtos;

public class RequestContextDTO {
    String routeKey;
    String messageId;
    String eventType;
    String requestId;
    String connectionId;
    String domainName;
    String stage;


    public RequestContextDTO(
            String routeKey,
            String messageId,
            String eventType,
            String requestId,
            String connectionId,
            String domainName,
            String stage) {
        this.routeKey = routeKey;
        this.messageId = messageId;
        this.eventType = eventType;
        this.requestId = requestId;
        this.connectionId = connectionId;
        this.domainName = domainName;
        this.stage = stage;
    }

    public RequestContextDTO() {}

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

}
