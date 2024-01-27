package ru.sipmine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/*
 * This class contains the main logic of the program. and implementation of the data filtering system
 */
public class Filtering {
    private List<String> lStrings = new ArrayList<>();
    private List<Float> lFloats = new ArrayList<>();
    private List<Long> lLong = new ArrayList<>();


    public List<String> getStringList() {
        return lStrings;
    }

    public List<Float> getFloatList() {
        return lFloats;
    }

    public List<Long> getLongList() {
        return lLong;
    }

    // Filtering input string to float, long or string 

    public void typeValid(String s) {
        try {
            float floatsPart = Float.parseFloat(s);
            long longPart = (long) floatsPart;
            if (floatsPart - longPart == 0.0) {
                lLong.add(longPart);
            } else {
                lFloats.add(floatsPart);
            }
        } catch (NumberFormatException e) {
            lStrings.add(s);
        }
    }
    private void shortStat(List<?> list, boolean isNotEmpty) {
        if (isNotEmpty) {
            System.out.println(list.get(0).getClass().getSimpleName() + " count: " + list.size());
        }
    }

    // print short stat all available type
    public void shortStats(FileProcesing fileProcesing) {
        shortStat(lFloats, fileProcesing.isListNotEmpty(lFloats));
        shortStat(lLong, fileProcesing.isListNotEmpty(lLong));
        shortStat(lStrings, fileProcesing.isListNotEmpty(lStrings));
    }

    private void fullStatFloat(List<Float> listNumerical, boolean isNotEmpty) {
        if (isNotEmpty) {
            System.out.println("----" + listNumerical.get(0).getClass().getSimpleName() + "----");
            System.out.println("Count: " + listNumerical.size());
            System.out.println("Max: " + listNumerical.stream().max(Comparator.naturalOrder()).get());
            System.out.println("Min: " + listNumerical.stream().min(Comparator.naturalOrder()).get());
            System.out.println("Sum: " + listNumerical.stream().mapToDouble(i -> i.floatValue()).sum());
            System.out.println(
                    "Mid: " + listNumerical.stream().mapToDouble(i -> i.floatValue()).sum() / listNumerical.size());

        }
    }

    private void fullStatLong(List<Long> listNumerical, boolean isNotEmpty) {
        if (isNotEmpty) {
            System.out.println("----" + listNumerical.get(0).getClass().getSimpleName() + "----");
            System.out.println("Count: " + listNumerical.size());
            System.out.println("Max: " + listNumerical.stream().max(Comparator.naturalOrder()).get());
            System.out.println("Min: " + listNumerical.stream().min(Comparator.naturalOrder()).get());
            System.out.println("Sum: " + listNumerical.stream().mapToLong(i->i.longValue()).sum());
            System.out.println(
                    "Mid: " + listNumerical.stream().mapToLong(i->i.longValue()).sum()/ listNumerical.size());

        }
    }

    private void fullStatString(List<String> listString, boolean isNotEmpty) {
        if (isNotEmpty) {
            System.out.println("----" + listString.get(0).getClass().getSimpleName() + "----");
            System.out.println("Count: " + listString.size());
            System.out.println(
                    "Max lenght: " + listString.stream().max(Comparator.comparing(String::length)).get().length());
            System.out.println(
                    "Min lenght: " + listString.stream().min(Comparator.comparing(String::length)).get().length());
        }
    }

    public void fullStats(FileProcesing fileProcesing) {
        fullStatFloat(lFloats, fileProcesing.isListNotEmpty(lFloats));
        fullStatLong(lLong, fileProcesing.isListNotEmpty(lLong));
        fullStatString(lStrings, fileProcesing.isListNotEmpty(lStrings));
    }

}
