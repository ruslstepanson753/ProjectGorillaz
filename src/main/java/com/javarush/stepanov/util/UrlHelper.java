package com.javarush.stepanov.util;

import lombok.experimental.UtilityClass;

import static com.javarush.stepanov.constants.ConstantsCommon.IMG_PATH;

@UtilityClass
public class UrlHelper {
    public static String createUrlFromFileName(String fileName) {
        return IMG_PATH+fileName;
    }
}
