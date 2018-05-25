package ru.rymyantsev.bl.remoteWS.yandexAPI;


import lombok.*;
import ru.rymyantsev.bl.remoteWS.AnswerModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class YandexAnswerModel implements AnswerModel {

    private int code;
    private String lang;
    private String[] text;


}

