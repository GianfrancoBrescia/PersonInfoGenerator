package org.example;

import org.example.dto.person.PersonDto;
import org.example.engine.Engine;
import org.example.engine.ReverseEngine;
import org.example.utils.Utils;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("From person info to physcal code");
        System.out.print("Insert general information (name;surname;birthPlace;birthCountry;sex): ");
        String personInfo = scanner.next();
        System.out.print("Insert birth date information (birthDay;birthMonth;birthYear): ");
        String birthDateInfo = scanner.next();
        PersonDto person = new PersonDto(personInfo, birthDateInfo);
        String cf = Engine.getFiscalCode(person);
        System.out.println("Mario Rossi fiscal code is: " + cf);

        System.out.println();

        System.out.println("From physcal code to person");
        System.out.print("Insert a person physcal code: ");
        String cfToProcess = scanner.next().toUpperCase(Locale.ROOT);
        System.out.println("Info generated from fiscal code " + cfToProcess + ":");
        System.out.println(" City: " + ReverseEngine.getCity(cfToProcess));
        System.out.println(" Country: " + ReverseEngine.getCountry(cfToProcess));
        System.out.println(" BirthDate: " + Utils.dateToParse(ReverseEngine.getDay(cfToProcess), ReverseEngine.getMonth(cfToProcess), ReverseEngine.getYear(cfToProcess)));
        System.out.println(" Sex: " + ReverseEngine.getSex(cfToProcess));
    }
}