package app.authenticate;

import android.oauth2.bean.HeaderClient;
import android.oauth2.bean.HeaderToken;
import android.oauth2.controller.AccessOAuth2;
import android.oauth2.helper.Constants;
import android.oauth2.listener.ITokenListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private String TAG = "::MainActivity::";

    private final String URL_TOKEN     = "http://10.0.2.2:8080/oauth/token";
    private final String URL_ACCESS    = "http://10.0.2.2:8080/greeting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn =(Button)findViewById( R.id.button );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAndroidOAuth2();
            }
        } );

    }

    private void callAndroidOAuth2(){
        HeaderClient client =
                     new HeaderClient.Builder( "roy", "spring", "clientapp", "123456")
                                     .grantType( Constants.GRANT_TYPE_PASSWORD )
                                     .scope( Constants.SCOPE_READ_WRITE )
                                     .build();

        AccessOAuth2.getResourceToken( client, URL_TOKEN, new ITokenListener() {
            @Override
            public void onLoginSuccess(HeaderToken headerToken) {
                Log.i(TAG, "Login Success");
                AccessOAuth2.setAutoRefreshToken( headerToken, URL_TOKEN  );
                AccessOAuth2.getAccessPathGET( URL_ACCESS );
            }
            @Override
            public void onLoginError(String error) {
                Log.i(TAG, "Login ERROR");
            }
        } );

    }














}
