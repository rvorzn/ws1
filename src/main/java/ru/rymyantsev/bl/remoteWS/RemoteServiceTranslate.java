package ru.rymyantsev.bl.remoteWS;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface RemoteServiceTranslate {
    Logger logger = org.apache.log4j.Logger.getLogger(RemoteServiceTranslate.class);

    int OK = 200;

    String translate(String fromLang, String toLang, String sourceText);

    default HttpResponse sendPost(HttpPost httpPost){
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(httpPost);
        } catch (IOException e) {
            logger.warn("Ошибка при отправке POST запроса", e);
            throw new RuntimeException("Ошибка при отправке POST запроса", e);
        }

        return httpResponse;
    }

    default String getResponce(HttpResponse httpResponse) {
        String responseJson;

        try (InputStreamReader inputStreamReader = new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8")) {
            responseJson = new BufferedReader(inputStreamReader).readLine();
        }
        catch (IOException e) {
            logger.warn("Ошибка при получения ответа от сервиса", e);
            throw new RuntimeException("Ошибка при оджидание ответа от сервера", e);
        }

        return responseJson;
    }

}
