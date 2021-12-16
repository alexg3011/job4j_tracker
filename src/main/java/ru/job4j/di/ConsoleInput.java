package ru.job4j.di;

import org.springframework.stereotype.Component;
import ru.job4j.tracker.Input;

import java.util.Scanner;

@Component
public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }
}
