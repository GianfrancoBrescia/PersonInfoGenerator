package org.example.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BirthDateDto {
    private int birthDay;
    private int birthMonth;
    private int birthYear;
}
