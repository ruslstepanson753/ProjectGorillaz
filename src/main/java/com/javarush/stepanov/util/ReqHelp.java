package com.javarush.stepanov.util;

import com.javarush.stepanov.exception.AppException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

@UtilityClass
public class ReqHelp {

    public static void createErrorToView(HttpServletRequest request, String errorMessage) {
        request.getSession().setAttribute(ERROR_MESSAGE, errorMessage);
    }

    public static <T> T getAttrFromSession(HttpServletRequest request, String attribute) {
        return (T) request.getSession().getAttribute(attribute);
    }

    public static <T> void setAttrSession(HttpServletRequest request, String attribute, T value) {
        if (request == null || attribute == null) {
            throw new AppException(ERROR_REQ_SET_ATR);
        }
        request.getSession().setAttribute(attribute, value);
    }
}
