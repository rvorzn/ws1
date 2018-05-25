package ru.rymyantsev.bl.remoteWS.yandexAPI;

import org.apache.http.Consts;

import java.nio.charset.Charset;

public interface YandexAPI {
    String PARAM_KEY_API = "key";
    String PARAM_LANGUAGE = "lang";
    String PARAM_TEXT_FOR_TRANSLATE = "text";

    String PARAM_AUTO_DETECTION = "auto";

    Charset ENCODING = Consts.UTF_8;

    default boolean isAutoDetection(String fromLang){
        return PARAM_AUTO_DETECTION.equals(fromLang);
    }


}
