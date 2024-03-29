package constants;

public class ApiRequestMappings {

    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String PUT = "PUT";

    public static final String USERS = "/users";
    public static final String SUBJECTS = "/subjects";
    public static final String GROUPS = "/groups";

    public static final String GROUP_MEMBERSHIP = "/groupmembership";
    public static final String USER_GROUP_MEMBERSHIPS = "/users/{userId}/groupmemberships";
    public static final String DELETE_GROUP_MEMBERSHIP = "/groupmembership/{groupMembership}/delete";

    public static final String CHAT_MESSAGE = "/messages";
    public static final String GROUP_CHAT_MESSAGES = "/groups/{groupId}/messages";

    public static final String CONNECT = "$connect";
    public static final String DISCONNECT = "$disconnect";
    public static final String SEND_MESSAGE = "sendmessage";
    public static final String IDENTIFY = "identify";

}
