package android.oauth2.config;

import android.oauth2.helper.Constants;
import android.util.Base64;
import java.nio.charset.StandardCharsets;


/**
 * Created by EudesSilva on 18/07/2016.
 */
public class Authorization {

    public static String getAuthorizationBearer(String hashToken) {
        String tokenHeader =  Constants.AUTHORIZATION_BEARER + hashToken;
        return tokenHeader;
    }


    public static String getAuthorizationBasic(String clientID, String secretID ){
        byte[] credentials = ( clientID + ":" + secretID ).getBytes( StandardCharsets.UTF_8 );
        String authorization = Constants.AUTHORIZATION_BASIC + Base64.encodeToString(credentials, Base64.NO_WRAP);
        return  authorization;
    }

}
