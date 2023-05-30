package org.example.engine;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.example.dto.person.BirthDateDto;
import org.example.dto.person.PersonDto;
import org.example.utils.Utils;

import java.util.Locale;
import java.util.Objects;

@UtilityClass
public class Engine {

    private final StringBuilder consonantiCognome = new StringBuilder(StringUtils.EMPTY);
    private final StringBuilder vocaliCognome = new StringBuilder(StringUtils.EMPTY);
    private final StringBuilder consonantiNome = new StringBuilder(StringUtils.EMPTY);
    private final StringBuilder vocaliNome = new StringBuilder(StringUtils.EMPTY);


    public static String getFiscalCode(PersonDto person) {
        person.setName(person.getName().replace("'", StringUtils.EMPTY));
        person.setSurname(person.getSurname().replace("'", StringUtils.EMPTY));
        populateVocaliConsonanti(person.getName(), vocaliNome, consonantiNome);
        populateVocaliConsonanti(person.getSurname(), vocaliCognome, consonantiCognome);

        String birthPlaceCode = birthPlaceCode(person.getBirthPlace());
        String cf = surnameCode() + nameCode() + bithDateCode(person.getBirthDate(), person.getSex()) + birthPlaceCode;
        cf += controlCode(cf);

        return (StringUtils.isEmpty(birthPlaceCode) || Objects.equals(birthPlaceCode, Utils.ERROR)) ? StringUtils.EMPTY : cf;
    }

    private String controlCode(String s) {
        String c = StringUtils.EMPTY;
        StringBuilder charPosPari = new StringBuilder(StringUtils.EMPTY);
        StringBuilder charPosDispari = new StringBuilder(StringUtils.EMPTY);
        int counter = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0)
                charPosDispari.append(s.charAt(i));  // perchè per l'algoritmo la stringa comincia da 1 e non da 0
            else charPosPari.append(s.charAt(i));
        }

        for (int i = 0; i < charPosDispari.length(); i++) {
            switch (charPosDispari.charAt(i)) {
                case '0':
                case 'A':
                    counter += 1;
                    break;
                case '1':
                case 'B':
                    counter += 0;
                    break;
                case '2':
                case 'C':
                    counter += 5;
                    break;
                case '3':
                case 'D':
                    counter += 7;
                    break;
                case '4':
                case 'E':
                    counter += 9;
                    break;
                case '5':
                case 'F':
                    counter += 13;
                    break;
                case '6':
                case 'G':
                    counter += 15;
                    break;
                case '7':
                case 'H':
                    counter += 17;
                    break;
                case '8':
                case 'I':
                    counter += 19;
                    break;
                case '9':
                case 'J':
                    counter += 21;
                    break;
                case 'K':
                    counter += 2;
                    break;
                case 'L':
                    counter += 4;
                    break;
                case 'M':
                    counter += 18;
                    break;
                case 'N':
                    counter += 20;
                    break;
                case 'O':
                    counter += 11;
                    break;
                case 'P':
                    counter += 3;
                    break;
                case 'Q':
                    counter += 6;
                    break;
                case 'R':
                    counter += 8;
                    break;
                case 'S':
                    counter += 12;
                    break;
                case 'T':
                    counter += 14;
                    break;
                case 'U':
                    counter += 16;
                    break;
                case 'V':
                    counter += 10;
                    break;
                case 'W':
                    counter += 22;
                    break;
                case 'X':
                    counter += 25;
                    break;
                case 'Y':
                    counter += 24;
                    break;
                case 'Z':
                    counter += 23;
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < charPosPari.length(); i++) {
            switch (charPosPari.charAt(i)) {
                case '1':
                case 'B':
                    counter += 1;
                    break;
                case '2':
                case 'C':
                    counter += 2;
                    break;
                case '3':
                case 'D':
                    counter += 3;
                    break;
                case '4':
                case 'E':
                    counter += 4;
                    break;
                case '5':
                case 'F':
                    counter += 5;
                    break;
                case '6':
                case 'G':
                    counter += 6;
                    break;
                case '7':
                case 'H':
                    counter += 7;
                    break;
                case '8':
                case 'I':
                    counter += 8;
                    break;
                case '9':
                case 'J':
                    counter += 9;
                    break;
                case 'K':
                    counter += 10;
                    break;
                case 'L':
                    counter += 11;
                    break;
                case 'M':
                    counter += 12;
                    break;
                case 'N':
                    counter += 13;
                    break;
                case 'O':
                    counter += 14;
                    break;
                case 'P':
                    counter += 15;
                    break;
                case 'Q':
                    counter += 16;
                    break;
                case 'R':
                    counter += 17;
                    break;
                case 'S':
                    counter += 18;
                    break;
                case 'T':
                    counter += 19;
                    break;
                case 'U':
                    counter += 20;
                    break;
                case 'V':
                    counter += 21;
                    break;
                case 'W':
                    counter += 22;
                    break;
                case 'X':
                    counter += 23;
                    break;
                case 'Y':
                    counter += 24;
                    break;
                case 'Z':
                    counter += 25;
                    break;
                default:
                    break;
            }
        }

        switch (counter % 26) {
            case 0:
                c = "A";
                break;
            case 1:
                c = "B";
                break;
            case 2:
                c = "C";
                break;
            case 3:
                c = "D";
                break;
            case 4:
                c = "E";
                break;
            case 5:
                c = "F";
                break;
            case 6:
                c = "G";
                break;
            case 7:
                c = "H";
                break;
            case 8:
                c = "I";
                break;
            case 9:
                c = "J";
                break;
            case 10:
                c = "K";
                break;
            case 11:
                c = "L";
                break;
            case 12:
                c = "M";
                break;
            case 13:
                c = "N";
                break;
            case 14:
                c = "O";
                break;
            case 15:
                c = "P";
                break;
            case 16:
                c = "Q";
                break;
            case 17:
                c = "R";
                break;
            case 18:
                c = "S";
                break;
            case 19:
                c = "T";
                break;
            case 20:
                c = "U";
                break;
            case 21:
                c = "V";
                break;
            case 22:
                c = "W";
                break;
            case 23:
                c = "X";
                break;
            case 24:
                c = "Y";
                break;
            case 25:
                c = "Z";
                break;
            default:
                break;
        }

        return c;
    }

    private String birthPlaceCode(String birthPlace) {
        return Utils.getData().getKey(birthPlace);
    }

    private String bithDateCode(BirthDateDto birthDate, String sex) {
        int giorno = birthDate.getBirthDay();

        String s = StringUtils.EMPTY;
        String annoS = birthDate.getBirthYear() + StringUtils.EMPTY;
        s = s + annoS.charAt(2) + annoS.charAt(3);
        switch (birthDate.getBirthMonth()) {
            case 1:
                s += "A";
                break;
            case 2:
                s += "B";
                break;
            case 3:
                s += "C";
                break;
            case 4:
                s += "D";
                break;
            case 5:
                s += "E";
                break;
            case 6:
                s += "H";
                break;
            case 7:
                s += "L";
                break;
            case 8:
                s += "M";
                break;
            case 9:
                s += "P";
                break;
            case 10:
                s += "R";
                break;
            case 11:
                s += "S";
                break;
            case 12:
                s += "T";
                break;
            default:
                break;
        }

        if (Objects.equals(sex, "M")) {
            if (giorno < 10) s += "0" + giorno;
            else s += giorno;
        } else {
            s += (giorno + 40);
        }

        return s;
    }

    private String nameCode() {
        StringBuilder s = new StringBuilder(StringUtils.EMPTY);

        if (consonantiNome.length() > 3) {
            s.append(consonantiNome.charAt(0)).append(consonantiNome.charAt(2)).append(consonantiNome.charAt(3));
        } else if (consonantiNome.length() == 1) {
            s.append(consonantiNome.charAt(0)).append(vocaliNome.charAt(0)).append(vocaliNome.charAt(1));
        } else if (consonantiNome.length() == 2) {
            s.append(consonantiNome.charAt(0)).append(consonantiNome.charAt(1)).append(vocaliNome.charAt(0));
        } else if (consonantiNome.length() == 3) {
            for (int i = 0; i < 3; i++) s.append(consonantiNome.charAt(i));
        } else {
            for (int i = 0; i < 3; i++) s.append(vocaliNome.charAt(i));
        }

        return s.toString();
    }

    private String surnameCode() {
        StringBuilder s = new StringBuilder(StringUtils.EMPTY);

        if (consonantiCognome.length() >= 3) {
            for (int i = 0; i < 3; i++) s.append(consonantiCognome.charAt(i));
        } else if (consonantiCognome.length() == 1) {
            s.append(consonantiCognome.charAt(0)).append(vocaliCognome.charAt(0)).append(vocaliCognome.charAt(1));
        } else if (consonantiCognome.length() == 2) {
            s.append(consonantiCognome.charAt(0)).append(consonantiCognome.charAt(1)).append(vocaliCognome.charAt(0));
        } else {
            for (int i = 0; i < 3; i++) s.append(vocaliCognome.charAt(i));
        }

        return s.toString();
    }

    private void populateVocaliConsonanti(String str, StringBuilder vocali, StringBuilder consonanti) {
        String strUpperCase = str.toUpperCase(Locale.ROOT);
        for (int i = 0; i < strUpperCase.length(); i++) {
            if (isVocal(strUpperCase.charAt(i))) vocali.append(strUpperCase.charAt(i));
            else if (strUpperCase.charAt(i) != ' ') consonanti.append(strUpperCase.charAt(i));
        }
    }

    private boolean isVocal(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
