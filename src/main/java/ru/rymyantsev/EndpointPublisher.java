package ru.rymyantsev;

import ru.rymyantsev.ws.ServiceTranslateImpl;

import javax.xml.ws.Endpoint;

public class EndpointPublisher {
    public static void main(String... args) {
        Endpoint.publish("http://localhost:1981/ws/translate", new ServiceTranslateImpl());
    }
}