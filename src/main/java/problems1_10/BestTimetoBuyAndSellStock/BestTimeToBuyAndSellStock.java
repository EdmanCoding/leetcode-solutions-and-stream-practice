package problems1_10.BestTimetoBuyAndSellStock;

public class BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        int min = 10000;
        int minInd = 0 ;
        int max = 0;
        int maxInd = 0 ;
        int result = 0;
        for (int i = 0; i<prices.length; i++){
            if (min >= prices[i]){
                min = prices[i];
                minInd = i;
                if (minInd<maxInd && max - min>result)
                    result = max-min;
                max = min;
            }
            else if (max <= prices [i]){
                max = prices [i];
                maxInd = i;
                if (minInd<maxInd && max - min>result)
                    result = max-min;
            }
        }
        return result;
    }
    // resolve second time with hint. O(n) time complexity.
    public static int maxProfit2(int[] prices) {
        int maxProfit = 0;
        int minPriceSeenBefore = Integer.MAX_VALUE;
        for(int i = 0; i<prices.length; i++){
            if(minPriceSeenBefore>prices[i])
                minPriceSeenBefore = prices[i];
            else if (prices[i]-minPriceSeenBefore > maxProfit)
                maxProfit = prices[i]-minPriceSeenBefore;
        }
        return maxProfit;
    }
    // clean solution by ChatGPT
    public static int maxProfit3(int[] prices){
        if (prices == null)
            throw new IllegalArgumentException("prices cannot be null");
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
            for (int price : prices) {
                minPrice = Math.min(minPrice, price);
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {2,4,1};
        int[] prices2 = {2,1,2,1,0,1,2};
        int[] prices3 = {7,1,5,3,6,4};
        int[] prices4 = {7,6,4,3,1};
        int[] prices5 = {3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit(prices2));
        System.out.println(maxProfit(prices3));
        System.out.println(maxProfit(prices4));
        System.out.println(maxProfit(prices5));
    }
}
