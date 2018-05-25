package ru.rymyantsev;

import ru.rymyantsev.ws.ServiceTranslate;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class LocalClient {
    public static void main(String[] args)  {

        URL url = null;
        try {
            url = new URL("http://localhost:1981/ws/translate?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        QName qname = new QName("http://ws.rymyantsev.ru/", "ServiceTranslateImplService");

        Service service = Service.create(url, qname);
        ServiceTranslate hello = service.getPort(ServiceTranslate.class);

        System.out.println(hello);

//        String text  = "Let's now consider ";
//        System.out.println(hello.translateText(text, "en", "ru"));
    }
}
