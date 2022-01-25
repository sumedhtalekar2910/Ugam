package com.ugams.core.utils;

import com.ugams.core.models.impl.UserListImpl;
import org.apache.commons.httpclient.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class FetchApi {

    public static String readData(String url) {

        final Logger log = LoggerFactory.getLogger(UserListImpl.class);
        try {
            log.info("inside fetch api");
            URL requestURL = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) requestURL.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();

            while ((inputLine = bufferedReader.readLine()) != null){
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();
            return  stringBuffer.toString();

        }catch (Exception e) {
            e.printStackTrace();
        }


        return  null;
    }
}
