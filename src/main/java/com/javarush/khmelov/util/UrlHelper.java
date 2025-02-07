package com.javarush.khmelov.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import static com.javarush.khmelov.storage.ConstantsCommon.ERROR_MESSAGE;
import static com.javarush.khmelov.storage.ConstantsCommon.IMG_PATH;

@UtilityClass
public class UrlHelper {

    public static String createUrlFromFileName(String fileName) {
        return IMG_PATH+fileName;
    }
}
