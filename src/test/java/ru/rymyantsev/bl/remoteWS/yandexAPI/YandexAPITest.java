package ru.rymyantsev.bl.remoteWS.yandexAPI;

import org.junit.Test;

import static org.junit.Assert.*;

public class YandexAPITest {

    YandexRemoteService yandexRemoteService = new YandexRemoteService();

    @Test
    public void whenToLangAutoThenTrue() {
        boolean result = yandexRemoteService.isAutoDetection("auto");
        assertTrue(result);
    }

    @Test
    public void whenToNoAutoThenTrue() {
        boolean result = yandexRemoteService.isAutoDetection("ru");
        assertFalse(result);
    }
}