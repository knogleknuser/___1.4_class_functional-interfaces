package demos.day2;

import java.time.*;

public class ClassDemo2 {
    // For looking into the Date and Time API
    public static void main(String[] args) {
        // LocalDate NOW
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getDayOfWeek());

        // LocalDate OF
        LocalDate past = LocalDate.of(2020, 1, 1);
        System.out.println("Past date: "+past);
        System.out.println("Difference between now and then: "+now.compareTo(past)); // 3 because 3 years

        // Period
        Period periodInBetween = Period.between(past,now);
        System.out.println("Period in between: "+periodInBetween.getYears()+" years "+periodInBetween.getMonths()+" months "+periodInBetween.getDays()+" days");

        // LocalDateTime. Duration vs Period
        LocalDateTime backThen = past.atStartOfDay();
        LocalDateTime nowTime = LocalDateTime.now();

        // Duration
        Duration durationInBetween = Duration.between(backThen, nowTime);
        System.out.println("Total duration in between: "+durationInBetween.getSeconds()/(60*60*24) + " days");

        // ZonedDateTime, OffsetDateTime, OffsetTime
//        ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
        ZoneId zoneIdCopenhagen = ZoneId.of("Europe/Copenhagen");
        ZoneId zoneIdNewYork = ZoneId.of("America/New_York");
//        ZonedDateTime zonedDateTimeCopenhagen = ZonedDateTime.of(nowTime, zoneIdCopenhagen);
        ZonedDateTime zonedDateTimeCopenhagen = ZonedDateTime.of(LocalDateTime.of(2023,8,28,19,20), zoneIdNewYork);
        ZonedDateTime zonedDateTimeNewYork = ZonedDateTime.of(LocalDateTime.of(2023,8,28,22,00), zoneIdNewYork);

        System.out.println("Zoned date time: "+zonedDateTime);
        // Instant
        Instant instant = Instant.now();
        System.out.println("Instant: "+instant);

        // DateTimeFormatter
        System.out.println("Formatted date time: "+zonedDateTime.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

    }
}
