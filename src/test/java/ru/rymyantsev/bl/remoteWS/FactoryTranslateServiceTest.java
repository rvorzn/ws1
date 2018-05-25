package ru.rymyantsev.bl.remoteWS;

import org.junit.Test;
import ru.rymyantsev.bl.remoteWS.yandexAPI.YandexRemoteService;

import static org.junit.Assert.*;

public class FactoryTranslateServiceTest {

    @Test
    public void whenParamsInFactoryExistThenTrue() {
        RemoteServiceTranslate service = FactoryTranslateService.createService(FactoryTranslateService.Service.YANDEX);
        boolean resul = service instanceof YandexRemoteService;
        assertTrue(resul);
    }

    @Test
    public void whenParamsNpFactoryExistThenTrue() {
        RemoteServiceTranslate service = FactoryTranslateService.createService(FactoryTranslateService.Service.GOOGLE);
        boolean resul = service instanceof NotRemoteService;
        assertTrue(resul);
    }
}