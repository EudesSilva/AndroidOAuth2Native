package android.oauth2.controller;

import android.oauth2.bean.HeaderClient;
import android.oauth2.bean.HeaderToken;
import android.oauth2.config.Authorization;
import android.oauth2.config.HeaderParam;
import android.oauth2.helper.CallHttp;
import android.oauth2.helper.Constants;
import android.oauth2.listener.ITokenListener;
import android.oauth2.parse.ParseJSONObject;
import android.oauth2.parse.ParseStream;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Created by EudesSilva on 18/07/2016.
 */
public class AccessOAuth2 {

    private static String TAG = "::AccessOAuth2::";

    // Storage in static
    private static HeaderClient clientActive;
    private static HeaderToken tokenActive;

   public static HeaderToken getResourceToken( HeaderClient clientConfig, String url, ITokenListener tokenListener ){
        String requestBody;
        String authorization;
        HeaderToken token = null;
        HttpURLConnection urlConnection;
        clientActive = clientConfig;
        String clientID     = clientConfig.getClientId();
        String clientSecret = clientConfig.getClientSecret();
        requestBody   = HeaderParam.getHeadersParams( clientConfig );
        authorization = Authorization.getAuthorizationBasic( clientID, clientSecret );

        Log.i(TAG, "::requestBody_1::"+ requestBody );
        Log.i(TAG, "::authorization_1::"+ authorization );
        try {
            urlConnection = CallHttp.methodPost( url, authorization );
            OutputStream outputStream = urlConnection.getOutputStream();
            ParseStream.writeBytesInStream(outputStream, requestBody );
            boolean responseOK = (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK );

            Log.i(TAG, ":DEBUG_1 URL::" + urlConnection.getURL().toString() );

            if ( responseOK ) {
                InputStream is = urlConnection.getInputStream();
                String data = ParseStream.parseStreamToString(is);
                token = ParseJSONObject.parseJsonToToken(data);

                Log.i(TAG, "::getResourceToken::" + data );
                if( tokenListener != null ) {
                    if (token == null) {
                        tokenListener.onLoginError( "Token is Null" );
                    } else {
                        tokenListener.onLoginSuccess( token );
                    }
                }
            }else{
                String error = urlConnection.getResponseMessage();
                if( tokenListener != null ) {
                    tokenListener.onLoginError( error );
                }
                Log.i(TAG, ":ERROR::FAIL:: getResourceToken");
                Log.i(TAG, ":ERROR::MESSAGE::" +  error );
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
          Log.i(TAG, ":TOKEN Reg::"+ token.toString() );

        return token;
    }




   public static void setAutoRefreshToken(HeaderToken token, String urlRefreshToken){
       token.setAutoRefreshToken( true );
       token.setUrlRefreshToken( urlRefreshToken );
       tokenActive = token;

       Log.i(TAG, ":TOKEN Config::"+ token.toString() );
       Log.i(TAG, ":TOKEN Ative::"+ tokenActive.toString() );
   }



    private static void verifyAutoRefresh(){
        boolean isAutoRefresh;
        if( tokenActive != null){
            isAutoRefresh = tokenActive.getAutoRefreshToken();
            if(isAutoRefresh){
                HeaderToken refreshToken;
                refreshToken = TokenOAuth2.isExpiredTokenOAuth2( tokenActive, clientActive );
                tokenActive  = refreshToken;

                Log.i(TAG,":TOKEN AtiveVery::"+ tokenActive.toString() );
                Log.i(TAG,"::REFRESHED::AUTO REFRESH ACTIVE");
            }
        } Log.i(TAG, "::Verify:: verifyAutoRefresh()");
    }


    public static JSONObject getAccessPathGET( String url ){

        if( (tokenActive  == null) || (clientActive == null) ){
            return null;
        }
        verifyAutoRefresh();
        String accessToken;
        String authorization;
        JSONObject json = null;
        HttpURLConnection urlConnection;
        accessToken   = tokenActive.getAccessToken();
        authorization = Authorization.getAuthorizationBearer( accessToken );
        try {
            urlConnection = CallHttp.methodGet( url, authorization );
            boolean authorized = ( urlConnection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED );
            boolean grantValid = ( urlConnection.getResponseMessage().contains( Constants.INVALID_GRANT ) );
            boolean responseOK = ( authorized || grantValid );

            Log.i(TAG, ":DEBUG RESPONSE::"+  urlConnection.getResponseMessage() );
            Log.i(TAG, ":DEBUG URL::"+  urlConnection.getURL().toString() );
            Log.i(TAG, ":DEBUG Authorization::"+  authorization );

            if ( !responseOK ) {
                InputStream is  = urlConnection.getInputStream();
                String data     = ParseStream.parseStreamToString(is);
                json = new JSONObject(data);
                Log.i(TAG, "::getAccessPathGET::" +json.toString() );
            }else{
                json = new JSONObject( Constants.UNAUTHORIZED_JSON );
                Log.i(TAG, ":ERROR::FAIL:: getAccessPathGET");
                Log.i(TAG, ":ERROR MESSAGE::"+ urlConnection.getResponseMessage() );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


}
