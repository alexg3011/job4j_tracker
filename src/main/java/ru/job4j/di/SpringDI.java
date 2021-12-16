package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ConsoleInput console = context.getBean(ConsoleInput.class);
        Store store = context.getBean(Store.class);
        store.add("name1");
        store.add("name2");
        Store getStore = context.getBean(Store.class);
        ui.add("Petr Arsentev");
        ui.add("Ivan ivanov");
        ui.print();
        console.askStr("Hi!");
        getStore.getAll().forEach(System.out::println);
    }
}