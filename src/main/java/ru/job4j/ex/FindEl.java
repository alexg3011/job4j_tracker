package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException{
        int rsl = -1;
        for (int i=0; i< value.length; i++) {
            if (value[i].equals(key)) {
                rsl = i;
                break;
            }
        }
        if (rsl==-1) {
            throw new ElementNotFoundException("Такого элемента нет.");
        }
        return rsl;
    }

    public static void main(String[] args) {
        String[] value = new String[]{"first", "second", "third" };
        try {
            System.out.println(indexOf(value, "fourth"));
        }
        catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
