package android.oauth2.parse;

import android.oauth2.bean.HeaderToken;
import android.oauth2.helper.Constants;
import android.oauth2.helper.UtilMillis;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by EudesSilva on 21/07/2016.
 */
public class ParseJSONObject {

    private static String TAG = "::ParseJSONObject::";


     public static HeaderToken parseJsonToToken(String jsonData){
         if( jsonData == null && jsonData.length() <= 0 )
           return null;
         HeaderToken token = new HeaderToken();
         try {
             JSONObject json = new JSONObject( jsonData );
             long   expireIn     = json.getLong( Constants.EXPIRES_IN );
             String accessToken  = json.getString( Constants.ACCESS_TOKEN );
             String refreshToken = json.getString( Constants.REFRESH_TOKEN );
             long   expireEnd    = UtilMillis.sumInCurrentMillis( expireIn );
             token.setExpiresIn( expireIn );
             token.setExpiresEnd( expireEnd );
             token.setAccessToken( accessToken );
             token.setRefreshToken( refreshToken );
         } catch (JSONException ex) {
             Log.e(TAG, ex.toString());
             ex.printStackTrace();
         }
      return token;
     }
}
