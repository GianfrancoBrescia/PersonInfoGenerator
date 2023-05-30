package org.example.dto.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
public class PersonDto {
    private String name;
    private String surname;
    private String birthPlace;  // comune di nascita
    private String birthCountry;  // sigla comune
    private BirthDateDto birthDate;
    private String sex;

    public PersonDto(String personInfo, String birthDateInfo) {
        String[] personInfoSplit = personInfo.split(";");
        this.name = StringUtils.capitalize(personInfoSplit[0]);
        this.surname = StringUtils.capitalize(personInfoSplit[1]);
        this.birthPlace = StringUtils.capitalize(personInfoSplit[2]);
        this.birthCountry = personInfoSplit[3].toUpperCase(Locale.ROOT);
        this.sex = personInfoSplit[4].toUpperCase(Locale.ROOT);

        String[] birthDateInfoSplit = birthDateInfo.split(";");
        this.birthDate = new BirthDateDto(Integer.parseInt(birthDateInfoSplit[0]), Integer.parseInt(birthDateInfoSplit[1]), Integer.parseInt(birthDateInfoSplit[2]));
    }
}
