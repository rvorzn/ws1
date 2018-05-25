package ru.rymyantsev.bl.remoteWS;

import org.junit.Test;
import ru.rymyantsev.bl.remoteWS.yandexAPI.YandexAnswerModel;

import static org.junit.Assert.*;

public class AnswerModelTest {
    YandexAnswerModel testModel = new YandexAnswerModel();

    @Test
    public void whenCodeEqualsThenTrue() {
        testModel.setCode(200);
        boolean result = testModel.checkCode(testModel.getCode(), 200);
        assertTrue(result);
    }

    @Test
    public void whenCodeNoEqualsThenFalse() {
        testModel.setCode(400);
        boolean result = testModel.checkCode(testModel.getCode(), 200);
        assertFalse(result);
    }


    @Test
    public void whenCodeInRangeCodValidateThenTrue() {
        testModel.setCode(215);
        boolean result = testModel.checkRangeCode(testModel.getCode(), 200);
        assertTrue(result);
    }

    @Test
    public void whenCodeOutRangeCodValidateThenTrue() {
        testModel.setCode(303);
        boolean result = testModel.checkRangeCode(testModel.getCode(), 200);
        assertFalse(result);
    }


    @Test
    public void whenCodeinValidateRangeThenTrue() {
        testModel.setCode(599);
        boolean result1 = testModel.isvalidateCode(testModel.getCode());
        testModel.setCode(100);
        boolean result2 = testModel.isvalidateCode(testModel.getCode());
        assertTrue(result1 & result2);
    }



    @Test
    public void whenCodeOutRangeThenFalse() {
        testModel.setCode(600);
        boolean result1 = testModel.isvalidateCode(testModel.getCode());
        testModel.setCode(99);
        boolean result2 = testModel.isvalidateCode(testModel.getCode());
        assertFalse(result1 & result2);
    }

}