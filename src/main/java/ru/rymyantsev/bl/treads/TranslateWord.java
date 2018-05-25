package ru.rymyantsev.bl.treads;

import lombok.AllArgsConstructor;
import lombok.ToString;
import ru.rymyantsev.bl.remoteWS.FactoryTranslateService;
import ru.rymyantsev.bl.remoteWS.RemoteServiceTranslate;
import ru.rymyantsev.db.model.DataTranslate;

import java.util.Date;
import java.util.concurrent.Callable;

import static ru.rymyantsev.bl.remoteWS.FactoryTranslateService.Service.YANDEX;


@AllArgsConstructor
@ToString
public class TranslateWord implements Callable {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TranslateWord.class);

    private final String sourceWord;
    private final String fromLang;
    private final String toLang;


    @Override
    public String call() {

        // если у нас совпал исходный язык и язык перевода  возвращаем исходное
        if (fromLang.equalsIgnoreCase(toLang)){return sourceWord;}

        // метод перевода текста
        String translateWord = translateInService(YANDEX, sourceWord, fromLang, toLang );

        // елси слово перевелось пишем данные в бд если нет то сообщаем об этом в лог
        if (!checkTranslate(translateWord)) {
            DataTranslate dataTranslate = new DataTranslate(sourceWord, translateWord, new Date().toString(), "");
            new Thread(new WriteTranslateWordToBase(dataTranslate)).start();
        } else {
            logger.warn("Exeption не выпал но словое не перевелось ");
        }

        return  translateWord;

    }


    // private methods --------------------------------------

    // перевод слова в сервисе
    private String translateInService(FactoryTranslateService.Service serviceName, String sourceWord, String fromLang, String toLang) {

        //создаем обьект сервиса куда отпрами запрос
        RemoteServiceTranslate service = FactoryTranslateService.createService(serviceName);

        //переводим и возращаем результат
        return service.translate(fromLang, toLang, sourceWord);
    }

    // проверка успешного превода
    private boolean checkTranslate( String translateWord){
        return translateWord == null || sourceWord.equalsIgnoreCase(translateWord );
    }

}


