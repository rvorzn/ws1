package ru.rymyantsev.bl;

import java.util.concurrent.*;


public interface TranslateLogic {
    int MAX_TREADS = 10;
    int TIMEOUT_VALUE = 5;
    TimeUnit TIMEOUT_TYPE = TimeUnit.SECONDS;

    String translate(final String sourceText, final String fromLang,final  String toLang);


}

