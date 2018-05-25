package ru.rymyantsev.bl.remoteWS.yandexAPI;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import ru.rymyantsev.bl.remoteWS.RemoteServiceTranslate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class YandexRemoteService implements RemoteServiceTranslate, YandexAPI {

    @Override
    public String translate(String fromLang, String toLang, String sourceWord) {

        // формируем наш запрос
        HttpPost httpPost =  createPost(fromLang, toLang, sourceWord);

        // отправляем наш запрос
        HttpResponse httpResponse= sendPost(httpPost);

        //принимает ответ в виде JSON
        String responseJson = getResponce(httpResponse);

        // разбираем ответ сервиса
        YandexAnswerModel answerServiceTranslate; //exception throw up
        try {
            answerServiceTranslate = new ObjectMapper().readValue(responseJson, YandexAnswerModel.class);
        } catch (IOException e) {
            logger.warn("Ошибка при парсинге JSON: " + responseJson, e);
            return sourceWord;
        }

        // обрабатываем обьект ответа сервиса
        String resultTranslateWord;
        if (answerServiceTranslate.checkRangeCode( answerServiceTranslate.getCode() ,OK)){
            resultTranslateWord = answerServiceTranslate.getText()[0];
        } else {
            logger.warn("Ответ от RemoteServiceTranslate не 200");
            return   sourceWord;
        }

        return resultTranslateWord;
    }

    // privet methods --------------------------------------------------------------------------------------------------

    private final String API_KEY = "trnsl.1.1.20180111T031017Z.410436c2392f754b.f3ad4a29b0e38d2512fb94daea5a1b6a3afa86e5";
    private final String URL_PATH = "https://translate.yandex.net/api/v1.5/tr.json/translate";

    private HttpPost createPost(String fromLang, String toLang, String inputWord) {
        HttpPost httpPost = new HttpPost(URL_PATH);

        String fromToLang =  (isAutoDetection(fromLang)) ? toLang : fromLang + "-" + toLang;

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(PARAM_KEY_API, API_KEY));
        params.add(new BasicNameValuePair(PARAM_LANGUAGE, fromToLang));
        params.add(new BasicNameValuePair(PARAM_TEXT_FOR_TRANSLATE, inputWord));

        httpPost.setEntity(new UrlEncodedFormEntity(params, ENCODING));

        return httpPost;
    }

}
