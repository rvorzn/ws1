package ru.rymyantsev.ws;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ServiceTranslate {

    String CLIENT_MESSAGE_PROBLEMS = "К сожалени мы не смогли обработать ваш запрос попробуйте езе раз";

    @WebMethod
    String translateText(String textToTranslate, String fromLang, String toLange) ;
}