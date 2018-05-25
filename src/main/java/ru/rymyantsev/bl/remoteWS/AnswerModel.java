package ru.rymyantsev.bl.remoteWS;

public interface AnswerModel {


    default boolean checkCode(int codeModel, int codeValidation ) {
        if (!isvalidateCode(codeModel)) {return false;}
        return codeModel == codeValidation ;
    }

    default boolean checkRangeCode(int codeModel, int codeValidation) {
        if (!isvalidateCode(codeModel)) {return false;}

        while ( (codeModel / 10) > 0){ codeModel /= 10; }
        codeModel = (codeModel % 10) * 100;
        return (codeValidation<= codeModel  && codeModel < (codeValidation + 100));
    }

    default boolean isvalidateCode(int codeValidation){
        return (codeValidation >= 100 && codeValidation < 600);
    }
}
