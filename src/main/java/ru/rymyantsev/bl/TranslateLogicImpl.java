package ru.rymyantsev.bl;

import org.apache.log4j.Logger;
import ru.rymyantsev.bl.treads.TranslateWord;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static ru.rymyantsev.ws.ServiceTranslate.CLIENT_MESSAGE_PROBLEMS;


public final class TranslateLogicImpl implements TranslateLogic {
    private final Logger logger = Logger.getLogger(TranslateLogicImpl.class);
    private final String SPLITTER = "[\\s]+";

    // main methods for translate logic
    @Override
    public String translate(final String sourceText, final String fromLang, final String toLang) {

        String[] arrWord = sourceText.trim().split(SPLITTER);

        ExecutorService executor = Executors.newFixedThreadPool(arrWord.length < MAX_TREADS ? arrWord.length : MAX_TREADS);

        //запускаем потоки для перевода каждого слова
        List<Future<String>> translateWordList = new ArrayList<>();
        for (String word : arrWord) {
            Future<String> future = executor.submit(new TranslateWord(word, fromLang, toLang));
            translateWordList.add(future);
        }

        // обработка времени работы потоков
        if (!executorsShutdown(executor)) { return CLIENT_MESSAGE_PROBLEMS; }

        //формитрование правилього вида ответа от сервера
        String answerFromService =  collectAnswer(translateWordList);

        // Возвращаем переведенное слово
        return answerFromService;
    }

    // private methods -------------------------------------------------------------------------------------------------

    // метод обработки ExecutorService в случае простоя
    private boolean executorsShutdown(ExecutorService executor) {

        try {
            executor.shutdown();
            executor.awaitTermination(TIMEOUT_VALUE, TIMEOUT_TYPE);
        }
        catch (InterruptedException e) {
            logger.warn("Истекло время отработки потоков executorsShutdown(...)", e);
            return false;
        }
        finally {
            executor.shutdownNow();
        }

        return executor.isTerminated();
    }

    // метод формирования ответа от сервиса
    private String collectAnswer(List<Future<String>> translateWordList)  {

        StringBuilder translateText = new StringBuilder();

        for(Future<String> fut : translateWordList){
            try {
                translateText = translateText.append(fut.get()).append(" ");
            } catch (InterruptedException | ExecutionException e) {
                logger.warn("Ошибка обьедения строк: " + translateText.toString() + " " + fut.toString(), e);
            }
        }

        return translateText.toString();
    }

}

