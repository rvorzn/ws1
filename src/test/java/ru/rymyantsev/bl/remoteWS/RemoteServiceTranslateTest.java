package ru.rymyantsev.bl.remoteWS;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import ru.rymyantsev.bl.remoteWS.yandexAPI.YandexRemoteService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
import static ru.rymyantsev.bl.remoteWS.yandexAPI.YandexAPI.*;

public class RemoteServiceTranslateTest {
    private final String API_KEY = "trnsl.1.1.20180111T031017Z.410436c2392f754b.f3ad4a29b0e38d2512fb94daea5a1b6a3afa86e5";
    private final String URL_PATH = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    private  RemoteServiceTranslate translateService = new YandexRemoteService();

    private HttpPost httpPost;

    @Before
    public void createHttp(){
        HttpPost httpPost = new HttpPost(URL_PATH);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(PARAM_KEY_API, API_KEY));
        params.add(new BasicNameValuePair(PARAM_LANGUAGE, "ru-en"));
        params.add(new BasicNameValuePair(PARAM_TEXT_FOR_TRANSLATE, "стол"));

        httpPost.setEntity(new UrlEncodedFormEntity(params, ENCODING));

        this.httpPost = httpPost;
    }

    @Test
    public void whenParamsPostValidThenNoExeptiotTrue() {
        boolean resultEx = true;

        try {
            translateService.sendPost(httpPost);
        }catch (RuntimeException e){
            resultEx = false;
        }

        assertTrue(resultEx);
    }


    @Test
    public void whenResponceValidThenNoExeptiotTrue() {
        boolean resultEx = true;
        String responseJson = null;
        try {
            HttpResponse httpResponse = translateService.sendPost(httpPost);
            responseJson = translateService.getResponce(httpResponse);
        }catch (RuntimeException e){
            resultEx = false;
        }

        assertTrue(resultEx &&responseJson != null);
    }

}