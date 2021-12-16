package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Store.class);
        context.register(StartUI.class);
        context.register(ConsoleInput.class);
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ConsoleInput console = context.getBean(ConsoleInput.class);
        ui.add("Petr Arsentev");
        ui.add("Ivan ivanov");
        ui.print();
        console.askStr("Hi!");
    }
}