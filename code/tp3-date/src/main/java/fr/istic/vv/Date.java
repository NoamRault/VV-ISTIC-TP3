package fr.istic.vv;

import java.time.DateTimeException;
import java.time.LocalDate;

class Date implements Comparable<Date> {
    private int day, month, year;

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Validate if the given date is valid
    public static boolean isValidDate(int day, int month, int year) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return true; // Si aucune exception n'est levée, la date est valide
        } catch (DateTimeException e) {
            return false; // Si une exception est levée, la date est invalide
        }
    }


    // Check if a year is a leap year
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    // Get the next day
    public Date nextDate() {
        LocalDate localDate = LocalDate.of(year, month, day).plusDays(1);
        return new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }

    // Get the previous day
    public Date previousDate() {
        LocalDate localDate = LocalDate.of(year, month, day).minusDays(1);
        return new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }

    // Compare this date to another date
    public int compareTo(Date other) {
        LocalDate localDate1 = LocalDate.of(year, month, day);
        LocalDate localDate2 = LocalDate.of(other.year, other.month, other.day);
        return localDate1.compareTo(localDate2);
    }

    @Override
    public boolean equals(Object obj) {
        // Vérifie si les deux objets sont identiques (référence égale)
        if (this == obj) {
            return true;
        }

        // Vérifie si l'objet passé en paramètre est nul ou d'un type différent
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // On cast l'objet pour le comparer aux attributs de la classe Date
        Date date = (Date) obj;

        // Compare les attributs day, month et year pour vérifier l'égalité
        return day == date.day && month == date.month && year == date.year;
    }


    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}
