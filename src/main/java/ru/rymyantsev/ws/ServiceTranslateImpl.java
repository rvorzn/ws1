package ru.rymyantsev.ws;

import org.apache.log4j.Logger;
import ru.rymyantsev.bl.*;

import javax.jws.WebMethod;
import javax.jws.WebService;


// указывающим полное имя класса интерфейса нашего веб-сервиса
@WebService(endpointInterface = "ru.rymyantsev.ws.ServiceTranslate")
public class ServiceTranslateImpl implements ServiceTranslate {
    private final Logger logger = Logger.getLogger(ServiceTranslateImpl.class);


    @WebMethod(operationName = "translateText")
    @Override
    public String translateText(String textToTranslate, String fromLang, String toLang) {
        logger.info("Подключение с параметрами: " + textToTranslate +" | " + fromLang + "-" + toLang  );

        String answer;
        try {
            TranslateLogic logic = new TranslateLogicImpl();
            answer = logic.translate(textToTranslate, fromLang.toLowerCase(), toLang.toLowerCase());
        } catch (Exception e){ // да мы тут будем отлавливать любой Exception в основном (Runtime)
            logger.error("Получли Exeption в каком то месте",  e);
            logger.debug(e.getStackTrace(), e);

            answer =  CLIENT_MESSAGE_PROBLEMS;
        }

        logger.info("Ответ клиенту: " + answer + " | " + fromLang + "-" + toLang  );
        return answer;
    }
}

