package problems1_10.ValidParentheses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import problems1_10.ValidParentheses_Easy.ValidParentheses;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidParenthesesTest {
    private final ValidParentheses solution = new ValidParentheses();

    @Test
    @DisplayName("Valid sequence returns true")
    public void testBasicTrueCase(){
        String input = "()[]{}"; //Arrange
        boolean result = solution.isValid3(input); // Act
        assertTrue(result); //Assert
    }
    @Test
    public void testBasicFalseCase(){
        String input = "(])[";
        boolean result = solution.isValid3(input);
        assertFalse(result);
    }
    @Test
    public void testEmptyString(){
        String input = "";
        boolean result = solution.isValid3(input);
        assertTrue(result);
    }
    @Test
    public void testOnlyOpenParentheses(){
        String input = "[{(";
        boolean result = solution.isValid3(input);
        assertFalse(result);
    }
    @Test
    public void testOnlyCloseParentheses(){
        String input = "]})";
        boolean result = solution.isValid3(input);
        assertFalse(result);
    }
    @Test
    public void testMoreCloseThanOpenParentheses(){
        String input = "[{()}])";
        boolean result = solution.isValid3(input);
        assertFalse(result);
    }
    @Test
    public void testMoreOpenThanCloseParentheses(){
        String input = "[{(()}]";
        boolean result = solution.isValid3(input);
        assertFalse(result);
    }
    @Test
    public void testCrossingParentheses(){
        String input = "([)]";
        boolean result = solution.isValid3(input);
        assertFalse(result);
    }
}
