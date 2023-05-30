package org.example.engine;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.example.utils.Utils;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

@UtilityClass
public class ReverseEngine {

    public static int getDay(String fiscalCode) {
        int day = Integer.parseInt(fiscalCode.substring(9, 11));
        if (day > 40) day -= 40;
        String stringDay = Integer.toString(day);
        if (stringDay.length() == 1) stringDay = "0" + stringDay;
        return Integer.parseInt(stringDay);
    }

    public static int getMonth(String fiscalCode) {
        String month = StringUtils.EMPTY;

        switch (fiscalCode.charAt(8)) {
            case 'A':
                month = "01";
                break;
            case 'B':
                month = "02";
                break;
            case 'C':
                month = "03";
                break;
            case 'D':
                month = "04";
                break;
            case 'E':
                month = "05";
                break;
            case 'H':
                month = "06";
                break;
            case 'L':
                month = "07";
                break;
            case 'M':
                month = "08";
                break;
            case 'P':
                month = "09";
                break;
            case 'R':
                month = "10";
                break;
            case 'S':
                month = "11";
                break;
            case 'T':
                month = "12";
                break;
            default:
                break;
        }

        return Integer.parseInt(month);
    }

    public static int getYear(String fiscalCode) {
        String year = fiscalCode.substring(6, 8);
        if (Integer.parseInt(year) >= 0
                && Integer.parseInt(year) <= Integer.parseInt(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)).substring(2, 4))) {
            return Integer.parseInt("20" + year);
        }
        return Integer.parseInt("19" + year);
    }

    public static String getSex(String fiscalCode) {
        if (Integer.parseInt(fiscalCode.substring(9, 11)) > 40) return "F";
        return "M";
    }

    public static String getCity(String fiscalCode) {
        StringBuilder output = new StringBuilder(StringUtils.EMPTY);
        String citta = Utils.getData().getValueByKey(fiscalCode.substring(11, 15));
        String[] splittedCitta = citta.split(StringUtils.SPACE);

        for (String s : splittedCitta) {
            output.append(s.substring(0, 1).toUpperCase(Locale.ROOT))
                    .append(s.substring(1).toLowerCase(Locale.ROOT))
                    .append(StringUtils.SPACE);
        }

        return output.substring(0, output.length() - 1);
    }

    public static String getCountry(String fiscalCode) {
        String belfioreCode = fiscalCode.substring(11, 15);

        return Utils.getData().entrySet()
                .stream()
                .filter(e -> e.getKey().startsWith(String.valueOf(belfioreCode.charAt(0))))
                .filter(e -> Objects.equals(e.getKey(), belfioreCode))
                .findFirst()
                .map(e -> e.getValue().getSigla())
                .orElse(StringUtils.EMPTY);
    }
}
