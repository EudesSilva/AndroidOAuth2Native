package android.oauth2.helper;

/**
 * Created by EudesSilva on 21/07/2016.
 */
public class Constants {

    // Client Config
    public static String PASSWORD   = "password";
    public static String USERNAME   = "username";
    public static String GRANT_TYPE = "grant_type";

    public static String SCOPE            = "scope";
    public static String CLIENT_ID        = "client_id";
    public static String CLIENT_SECRET    = "client_secret";
    public static String SCOPE_READ_WRITE = "read write";

    // Type Grant
    public static String GRANT_TYPE_PASSWORD      = "password";
    public static String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    // Header http
    public static String EXPIRES_IN     = "expires_in";
    public static String ACCESS_TOKEN   = "access_token";
    public static String REFRESH_TOKEN  = "refresh_token";
    public static String INVALID_GRANT  = "invalid_grant";

    //  Authorization
    public static String AUTHORIZATION_BASIC  = "Basic ";
    public static String AUTHORIZATION_BEARER = "Bearer ";



    public static String UNAUTHORIZED_JSON = "{\"error\": \"unauthorized\",\"error_description\": \"Authentication failed.\"}";

}
