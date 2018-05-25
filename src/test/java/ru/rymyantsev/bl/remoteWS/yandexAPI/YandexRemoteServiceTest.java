package ru.rymyantsev.bl.remoteWS.yandexAPI;

import org.junit.Test;
import ru.rymyantsev.bl.remoteWS.RemoteServiceTranslate;

import static org.junit.Assert.*;

public class YandexRemoteServiceTest {

    RemoteServiceTranslate serviceTranslate = new YandexRemoteService();

    @Test
    public void whenParamsSendValidateThenTrue() {
        boolean resultEx;
        String translate = null;

        resultEx = true;
        try {
            translate = serviceTranslate.translate("ru", "en", "стол");
        } catch (Exception e){
            resultEx = false;
        }
        boolean result1 = resultEx && "table".equalsIgnoreCase(translate);

        resultEx = true;
        try {
            translate = serviceTranslate.translate("auto", "ru", "cat");
        } catch (Exception e){
            resultEx = false;
        }
        boolean result2 = resultEx && "кошка".equalsIgnoreCase(translate);

        assertTrue(result1 && result2);
    }

//    @Test
//    public void whenParamsSendNoValidateThenTranslateEqualsSourceText() {
//
//        String translate = serviceTranslate.translate("ru", "en1", "стол");
//        boolean result = "стол".equals(translate);
//
//        assertTrue(result);
//    }


}