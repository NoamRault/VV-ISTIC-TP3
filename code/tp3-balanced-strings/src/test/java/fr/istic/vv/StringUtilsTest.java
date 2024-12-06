package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testBalanced(){
        assertTrue(isBalanced("{}"));
        assertTrue(isBalanced("{[]}"));
        assertTrue(isBalanced("{[][]}({})"));
        assertTrue(isBalanced(""));
        assertTrue(isBalanced("([]{})"));
    }

    @Test
    void testUnbalanced(){
        assertFalse(isBalanced("]["));
        assertFalse(isBalanced("([)]"));
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced("{(}{}"));
    }

    @Test
    void testNullAndEdgeCases() {
        assertFalse(isBalanced(null));  // Null should return false
        assertTrue(isBalanced(" "));   // Whitespaces are balanced
        assertTrue(isBalanced("{[()]}"));  // Nested correctly
        assertFalse(isBalanced("{[(()]}")); // Nested incorrectly
    }

    @Test
    void testStringsWithInvalidCharacters() {
        assertFalse(isBalanced("{[a]}"));  // Contient un caractère non-valide
        assertFalse(isBalanced("{[1]}"));  // Contient un caractère non-valide
        assertFalse(isBalanced("{[!]}"));  // Contient un caractère non-valide

    }
}