package problems1_10.BestTimetoBuyAndSellStock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BestTimeToBuyAndSellStockTest {
    @Test
    public void baseCaseTest(){
        int[] prices = {7,1,5,3,6,4};       // Arrange
        int result = BestTimeToBuyAndSellStock.maxProfit3(prices); //Act
        Assertions.assertEquals(5, result);  // Assert
    }
    @Test
    public void zeroOutputTest(){
        int[] prices = {7,6,5,3,2,1};
        int result = BestTimeToBuyAndSellStock.maxProfit3(prices);
        Assertions.assertEquals(0, result);
    }
    @Test
    public void emptyInputTest(){
        int[] prices = {};
        int result = BestTimeToBuyAndSellStock.maxProfit3(prices);
        Assertions.assertEquals(0, result);
    }
    @Test
    public void nullInputTest(){
        int[] prices = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BestTimeToBuyAndSellStock.maxProfit3(prices);
        });
    }
    @Test
    public void repeatableInputTest(){
        int[] prices = {1,4,1,4,1,1,4,4,4,1,4,1,4,4,1,4};
        int result = BestTimeToBuyAndSellStock.maxProfit3(prices);
        Assertions.assertEquals(3, result);
    }
    @Test
    public void singleElementTest(){
        int[] prices = {5};
        int result = BestTimeToBuyAndSellStock.maxProfit3(prices);
        Assertions.assertEquals(0, result);
    }
    @Test
    public void profitAtEndTest(){
        int[] prices = {9,8,7,1,10};
        int result = BestTimeToBuyAndSellStock.maxProfit3(prices);
        Assertions.assertEquals(9, result);
    }
}
