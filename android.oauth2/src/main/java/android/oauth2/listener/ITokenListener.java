package android.oauth2.listener;


import android.oauth2.bean.HeaderToken;

/**
 * Created Eudes on 09/08/2016.
 */
public interface ITokenListener {

    void onLoginSuccess(HeaderToken headerToken);
    void onLoginError(String error);

}
