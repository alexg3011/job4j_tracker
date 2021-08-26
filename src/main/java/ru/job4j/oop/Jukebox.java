package ru.job4j.oop;

public class Jukebox {
    public static void main(String[] args) {
        Jukebox playMusic = new Jukebox();
        playMusic.music(100);
        playMusic.music(1);
        playMusic.music(2);
    }
    public void music(int position) {
        if (position == 1) {
            System.out.println("Пусть бегут неуклюже");
        }
        else {
            if (position == 2) {
                System.out.println("Спокойной ночи");
            }
            else {
                System.out.println("Песня не найдена");
            }

        }
    }
}
