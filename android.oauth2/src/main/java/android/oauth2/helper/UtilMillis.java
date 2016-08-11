package android.oauth2.helper;

/**
 * Created by EudesSilva on 22/07/2016.
 */
public class UtilMillis {

    public static Long sumInCurrentMillis( Long valueSum ){
        Long sum;
        Long millis  = System.currentTimeMillis();
        sum = ( valueSum * 1000 ) + millis;
        return sum;
    }

    public static Long currentMillis(){
        Long millisCurrent;
        millisCurrent = System.currentTimeMillis();
        return millisCurrent;
    }
}
