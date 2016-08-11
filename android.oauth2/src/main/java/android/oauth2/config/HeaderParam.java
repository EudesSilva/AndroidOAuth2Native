package android.oauth2.config;

import android.net.Uri;
import android.oauth2.bean.HeaderClient;
import android.oauth2.helper.Constants;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class HeaderParam {

    private static String TAG = "::HeaderParam::";

    public static String getHeadersParams( HeaderClient clientConfig){
        String requestBody;
        Uri.Builder builder = new Uri.Builder();
        HashMap<String, String> stringMap = new HashMap<>();

        String refreshToken = clientConfig.getRefreshToken();

        if( refreshToken.length() > 0){
            Log.i(TAG,  "REFRESH_TOKEN PARAMS");
            stringMap.put( Constants.REFRESH_TOKEN, clientConfig.getRefreshToken() );
        }else{
            stringMap.put( Constants.PASSWORD, clientConfig.getPassword() );
            stringMap.put( Constants.USERNAME, clientConfig.getUsername() );
            stringMap.put( Constants.SCOPE, clientConfig.getScope() );
        }
        stringMap.put( Constants.GRANT_TYPE, clientConfig.getGrantType() );
        stringMap.put( Constants.CLIENT_ID,     clientConfig.getClientId() );
        stringMap.put( Constants.CLIENT_SECRET, clientConfig.getClientSecret() );

        Iterator entries = stringMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                builder.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                entries.remove(); // avoids a ConcurrentModificationException
            }
        requestBody = builder.build().getEncodedQuery();
        return requestBody;
    }


}
