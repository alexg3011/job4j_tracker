package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .flatMap(o -> o.getSubjects().stream())
                .mapToInt(Subject::getScore)
                .average()
                .orElse(0.0);
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .map(o -> new Tuple(o.getName(),
                                o.getSubjects().stream()
                                        .mapToInt(Subject::getScore)
                                        .average()
                                        .orElse(0.0)
                        )
                )
                .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream
                .flatMap(o -> o.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        Collectors.averagingDouble(Subject::getScore)))
                .entrySet().stream()
                .map(tuple -> new Tuple(tuple.getKey(), tuple.getValue()))
                .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
                .map(o -> new Tuple(o.getName(), o.getSubjects().stream()
                        .mapToInt(Subject::getScore)
                        .sum()))
                .max(Comparator.comparing(Tuple::getScore))
                .orElse(null);

    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream
                .flatMap(o -> o.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        Collectors.summingDouble(Subject::getScore)))
                .entrySet().stream()
                .map(tuple -> new Tuple(tuple.getKey(), tuple.getValue()))
                .max(Comparator.comparing(Tuple::getScore))
                .orElse(null);
    }
}
