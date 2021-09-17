package ru.job4j.stream;

public class Programmer {

    private String name;
    private String surname;
    private boolean java;
    private boolean spring;
    private boolean hibernate;
    private boolean sql;
    private boolean git;

    static class Builder {

        private String name;
        private String surname;
        private boolean java;
        private boolean spring;
        private boolean hibernate;
        private boolean sql;
        private boolean git;

        Builder buildName(String name) {
            this.name = name;
            return this;
        }

        Builder buildSurname(String surname) {
            this.surname = surname;
            return this;
        }

        Builder buildJava(boolean java) {
            this.java = java;
            return this;
        }

        Builder buildSpring(boolean spring) {
            this.spring = spring;
            return this;
        }

        Builder buildHibernate(boolean hibernate) {
            this.hibernate = hibernate;
            return this;
        }

        Builder buildSql(boolean sql) {
            this.sql = sql;
            return this;
        }

        Builder buildGit(boolean git) {
            this.git = git;
            return this;
        }

        Programmer biuld() {
            Programmer programmer = new Programmer();
            programmer.name = name;
            programmer.surname = surname;
            programmer.java = java;
            programmer.spring = spring;
            programmer.hibernate = hibernate;
            programmer.sql = sql;
            programmer.git = git;
            return programmer;
        }

    }

    @Override
    public String toString() {
        return "Programmer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", java=" + java +
                ", spring=" + spring +
                ", hibernate=" + hibernate +
                ", sql=" + sql +
                ", git=" + git +
                '}';
    }

    public static void main(String[] args) {
        Programmer programmer = new Builder().buildName("alex")
                .buildSurname("alex")
                .buildJava(true)
                .buildSpring(true)
                .buildHibernate(false)
                .buildSql(true)
                .buildGit(true)
                .biuld();
        System.out.println(programmer);
    }
}
