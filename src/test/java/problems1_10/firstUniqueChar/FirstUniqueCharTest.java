package problems1_10.firstUniqueChar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstUniqueCharTest {
    @Test
    public void firstCharUniqueTest(){
        String s = "leetcode"; // arrange
        int result = FirstUniqueChar.firstUniqChar2(s); // act
        Assertions.assertEquals(0, result); // assert
    }
    @Test
    public void middleCharUniqueTest(){
        String s = "loveleetcode";
        int result = FirstUniqueChar.firstUniqChar2(s);
        Assertions.assertEquals(2, result);
    }
    @Test
    public void lastCharUniqueTest(){
        String s = "llttccood";
        int result = FirstUniqueChar.firstUniqChar2(s);
        Assertions.assertEquals(8, result);
    }
    @Test
    public void nullStringTest(){
        String s = null;
        int result = FirstUniqueChar.firstUniqChar2(s);
        Assertions.assertEquals(-1, result);
    }
    @Test
    public void emptyStringTest(){
        String s = "";
        int result = FirstUniqueChar.firstUniqChar2(s);
        Assertions.assertEquals(-1, result);
    }
    @Test
    public void noUniqueTest(){
        String s = "aabbcc";
        int result = FirstUniqueChar.firstUniqChar2(s);
        Assertions.assertEquals(-1, result);
    }
    @Test
    public void alluniqueTest(){
        String s = "abcdefhjkl";
        int result = FirstUniqueChar.firstUniqChar2(s);
        Assertions.assertEquals(0, result);
    }
    @Test
    public void onlyOneUniqueTest(){
        String s = "abcdedcba";
        int result = FirstUniqueChar.firstUniqChar2(s);
        Assertions.assertEquals(4, result);
    }
    @Test
    public void oneLetterStringTest(){
        String s = "a";
        int result = FirstUniqueChar.firstUniqChar2(s);
        Assertions.assertEquals(0, result);
    }
    @Test
    public void oneNonUniqueLetterTest(){
        String s = "aaaaaaaaaaaaaaa";
        int result = FirstUniqueChar.firstUniqChar2(s);
        Assertions.assertEquals(-1, result);
    }
}
