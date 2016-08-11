package android.oauth2.parse;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * Created by EudesSilva on 18/07/2016.
 */
public class ParseStream {

    private static String TAG = "::ParseStream::";



    public static String parseStreamToString(InputStream is) {
        String line, data="";
        try{
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = buffer.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
            buffer.close();
            data = stringBuffer.toString();
        }catch (IOException ex) {
            Log.e(TAG, ex.toString());
            ex.printStackTrace();
        }
        return data;
    }



    public static void writeBytesInStream(OutputStream output, String requestBody){
        try {
            OutputStream outputStream = new BufferedOutputStream(output);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            writer.write(requestBody.getBytes( StandardCharsets.UTF_8).length);
           // writer.write(requestBody);
            writer.flush();
            writer.close();
            outputStream.close();
        }catch(Exception ex){
            Log.e(TAG, ex.toString());
            ex.printStackTrace();
        }
    }


}
