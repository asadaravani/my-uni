package pl.beganov.myuni.util;

import pl.beganov.myuni.dto.usos.course.CourseEditionUsosDto;
import pl.beganov.myuni.dto.usos.course.WelcomeDto;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SemesterExtractor {


    public static List<CourseEditionUsosDto> extractSemesters(WelcomeDto dto) {
        String currentSemester = getCurrentSemester();
        String upcomingSemester = getUpcomingSemester(currentSemester);
        return dto.courseEditions().entrySet().stream()
                .filter(entry -> entry.getKey().equals(currentSemester) || entry.getKey().equals(upcomingSemester))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
    }
    private static String getCurrentSemester() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int currentMonth = localDate.getMonthValue();
        return currentMonth < 10 ? year + "L" : year + "Z";
    }
    private static String getUpcomingSemester(String currentSemester) {
        if(currentSemester.endsWith("L")) {
            return currentSemester.substring(0, currentSemester.length() - 1) + 'Z';
        }
        else {
            int year = Integer.parseInt(currentSemester.substring(0, currentSemester.length() - 1));
            return (year + 1) + "L";
        }
    }
}
