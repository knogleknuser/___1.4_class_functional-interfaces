package demos.day2;

import java.time.*;

public class ClassDemoTimeAPI {
    // For looking into the Date and Time API
    public static void main(String[] args) {
        // LocalDate NOW
        LocalDate now = LocalDate.now(); // Based on the system clock
        System.out.println(now);
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getDayOfWeek());

        // LocalDate OF
        LocalDate past = LocalDate.of(2020, 1, 1);
        System.out.println("Past date: "+past);
        System.out.println("Difference between now and then: "+now.compareTo(past)); // 3 because 3 years

        // Period is meant for dates
        Period periodInBetween = Period.between(past,now);
        System.out.println("Period in between: "+periodInBetween.getYears()+" years "+periodInBetween.getMonths()+" months "+periodInBetween.getDays()+" days");

        // LocalDateTime. Duration vs Period
        LocalDateTime backThen = past.atStartOfDay();
        LocalDateTime nowTime = LocalDateTime.now();

        // Duration is measured in seconds, nanoseconds for time.
        Duration durationInBetween = Duration.between(backThen, nowTime);
        System.out.println("Total duration in between: "+durationInBetween.getSeconds()/(60*60*24) + " days");

        // ZonedDateTime, OffsetDateTime, OffsetTime
//        ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
        ZoneId zoneIdCopenhagen2 = ZoneId.of("Europe/Copenhagen");
        ZoneId zoneIdNewYork2 = ZoneId.of("America/New_York");

        ZonedDateTime departureDateTime = ZonedDateTime.of(LocalDateTime.of(2023, 8, 28, 19, 20), zoneIdCopenhagen2);
        ZonedDateTime arrivalDateTime = ZonedDateTime.of(LocalDateTime.of(2023, 8, 28, 22, 0), zoneIdNewYork2);

        Duration flightDuration = Duration.between(departureDateTime, arrivalDateTime);

        long totalMinutes = flightDuration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        System.out.println("Flight time: " + hours + " hours " + minutes + " minutes");

        // Instant is a point in time, a timestamp (like a UNIX timestamp based on the number of seconds since 1/1/1970 00:00:00 UTC)
        Instant instant = Instant.now();
        System.out.println("Instant: "+instant);

        // DateTimeFormatter
        System.out.println("Formatted date time: "+departureDateTime.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }
}
