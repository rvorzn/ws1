package ru.rymyantsev.bl.remoteWS;

import ru.rymyantsev.bl.remoteWS.yandexAPI.YandexRemoteService;

public class FactoryTranslateService {

    public static RemoteServiceTranslate createService(Service service){
        switch (service){
            case YANDEX:
                return new YandexRemoteService();
        }

        return new NotRemoteService();
    }

    public enum Service{
        YANDEX,
        GOOGLE,
    }
}
