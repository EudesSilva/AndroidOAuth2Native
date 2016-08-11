package android.oauth2.controller;

import android.oauth2.bean.HeaderClient;
import android.oauth2.bean.HeaderToken;
import android.oauth2.helper.Constants;
import android.oauth2.helper.UtilMillis;
import android.util.Log;

/**
 * Created by EudesSilva on 18/07/2016.
 */
public class TokenOAuth2 {

    private static String TAG = "::TokenOAuth2::";



   private static HeaderClient configureClientRefresh(String clientId, String clientSecret, String refreshToken){

        HeaderClient clientRefresh = new HeaderClient.BuilderRefresh()
                                        .clientId( clientId )
                                        .clientSecret( clientSecret )
                                        .grantType( Constants.GRANT_TYPE_REFRESH_TOKEN )
                                        .refreshToken( refreshToken )
                                        .build();
      return clientRefresh;
    }




    public static HeaderToken isExpiredTokenOAuth2(HeaderToken token, HeaderClient client){
        HeaderToken tokenRefresh;
        tokenRefresh           = token;
        long expiresEnd        = token.getExpiresEnd();
        boolean expired        = verifyExpires( expiresEnd );
        String clientID        = client.getClientId();
        String clientSecret    = client.getClientSecret();
        String refreshToken    = token.getRefreshToken();
        String urlRefreshToken = token.getUrlRefreshToken();
        boolean autoRefresh    = token.getAutoRefreshToken();

        if( expired ){
            HeaderClient clientRefresh;
            clientRefresh = configureClientRefresh( clientID, clientSecret, refreshToken );
            tokenRefresh  = AccessOAuth2.getResourceToken( clientRefresh, urlRefreshToken, null );
            tokenRefresh.setAutoRefreshToken( autoRefresh );
            tokenRefresh.setUrlRefreshToken( urlRefreshToken );

            tokenRefresh.toString();
            Log.i(TAG, "::REFRESHED:: AUTO REFRESH EXECUTED");
        }
        return tokenRefresh;
    }

    private static boolean verifyExpires( Long end ){
        boolean expired = false;
        Long millisNow  = UtilMillis.currentMillis();
        if( millisNow >= end ){
            expired = true;
        }
        return expired;
    }

}
