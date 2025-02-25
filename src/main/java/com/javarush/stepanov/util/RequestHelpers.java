package com.javarush.stepanov.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import static com.javarush.stepanov.constants.ConstantsCommon.ERROR_MESSAGE;
import static com.javarush.stepanov.constants.ConstantsCommon.REQUESTHELPERS_ATTRIBUTE_LOGIN;

@UtilityClass
public class RequestHelpers {

    public static void createError(HttpServletRequest request, String errorMessage) {
        request.getSession().setAttribute(ERROR_MESSAGE, errorMessage);
    }

    public static String getNameUserFromReq(HttpServletRequest request){
        return  request.getSession().getAttribute(REQUESTHELPERS_ATTRIBUTE_LOGIN).toString();
    }

}
