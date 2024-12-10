package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    // Test pour vérifier la validité d'une date
    @Test
    void testIsValidDate() {
        // Cas de dates valides
        assertTrue(Date.isValidDate(1, 1, 2024));   // 1er janvier 2024 est valide
        assertTrue(Date.isValidDate(29, 2, 2024));  // 29 février 2024 est valide (année bissextile)
        assertTrue(Date.isValidDate(31, 12, 2024)); // 31 décembre 2024 est valide

        // Cas de dates invalides
        assertFalse(Date.isValidDate(32, 1, 2024)); // 32 janvier est invalide
        assertFalse(Date.isValidDate(29, 2, 2023)); // 29 février 2023 est invalide (année non bissextile)
        assertFalse(Date.isValidDate(31, 4, 2024)); // 31 avril est invalide (avril a 30 jours)
        assertFalse(Date.isValidDate(0, 1, 2024));  // Jour 0 est invalide
        assertFalse(Date.isValidDate(1, 13, 2024)); // Mois 13 est invalide
    }

    // Test pour vérifier la méthode nextDate()
    @Test
    void testNextDate() {
        Date date = new Date(28, 2, 2024); // Date valide (année bissextile)
        Date nextDate = date.nextDate();
        assertEquals(new Date(29, 2, 2024), nextDate); // 29 février 2024

        date = new Date(31, 12, 2024); // 31 décembre 2024
        nextDate = date.nextDate();
        assertEquals(new Date(1, 1, 2025), nextDate); // 1er janvier 2025
    }

    // Test pour vérifier la méthode previousDate()
    @Test
    void testPreviousDate() {
        Date date = new Date(1, 3, 2024); // 1er mars 2024
        Date previousDate = date.previousDate();
        assertEquals(new Date(29, 2, 2024), previousDate); // 29 février 2024 (année bissextile)

        date = new Date(1, 1, 2025); // 1er janvier 2025
        previousDate = date.previousDate();
        assertEquals(new Date(31, 12, 2024), previousDate); // 31 décembre 2024
    }

    // Test pour vérifier la méthode compareTo()
    @Test
    void testCompareTo() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(1, 1, 2024);
        Date date3 = new Date(2, 1, 2024);

        // Date égale
        assertEquals(0, date1.compareTo(date2)); // Les dates sont égales

        // Date antérieure
        assertTrue(date1.compareTo(date3) < 0); // date1 est avant date3

        // Date postérieure
        assertTrue(date3.compareTo(date1) > 0); // date3 est après date1
    }

    // Test pour vérifier la méthode isLeapYear()
    @Test
    void testIsLeapYear() {
        // Test d'années bissextiles
        assertTrue(Date.isLeapYear(2024)); // 2024 est bissextile
        assertTrue(Date.isLeapYear(2000)); // 2000 est bissextile (années multiples de 400)

        // Test d'années non-bissextiles
        assertFalse(Date.isLeapYear(2023)); // 2023 n'est pas bissextile
        assertFalse(Date.isLeapYear(1900)); // 1900 n'est pas bissextile (années multiples de 100 mais pas de 400)
    }

}
