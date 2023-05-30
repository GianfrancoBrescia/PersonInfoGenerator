package org.example.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.example.dto.DataDto;


@UtilityClass
public class Utils {

    public static final String ERROR = "Errore!";

    public static DataDto getData() {
        return new DataDto();
    }

    public static String dateToParse(int day, int month, int year) {
        return StringUtils.leftPad(String.valueOf(day), 2, "0")
                + "/" + StringUtils.leftPad(String.valueOf(month), 2, "0")
                + "/" + year;
    }
}
