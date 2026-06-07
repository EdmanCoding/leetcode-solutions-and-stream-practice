package problems21_30.LRUcache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LRUCacheTest {
    @Test
    public void capacityOneTest() {
        LRUCache4 cache = new LRUCache4(1);

        cache.put(1, 1);
        cache.put(2, 2);

        Assertions.assertEquals(-1, cache.get(1));
        Assertions.assertEquals(2, cache.get(2));
    }
    @Test
    public void basicPutGetTest() {
        LRUCache4 cache = new LRUCache4(2);

        cache.put(1, 1);
        cache.put(2, 2);

        Assertions.assertEquals(1, cache.get(1));
    }
    @Test
    public void evictionTest() {
        LRUCache4 cache = new LRUCache4(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3); // evicts key 1

        Assertions.assertEquals(-1, cache.get(1));
    }
    @Test
    public void usageUpdateTest() {
        LRUCache4 cache = new LRUCache4(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);    // now 2 is LRU
        cache.put(3, 3); // should evict 2

        Assertions.assertEquals(-1, cache.get(2));
        Assertions.assertEquals(1, cache.get(1));
    }
    @Test
    public void updateExistingKeyTest() {
        LRUCache4 cache = new LRUCache4(2);

        cache.put(1, 1);
        cache.put(1, 10);

        Assertions.assertEquals(10, cache.get(1));
    }
    @Test
    public void longCapacityAndDifferentKeysAndValuesTest(){
        LRUCache4 cache = new LRUCache4(10);
        cache.put(1, 4);
        cache.put(2, 5);
        cache.put(3, 6);
        cache.put(4, 7);
        cache.put(5, 8);
        cache.put(6, 9);
        cache.put(7, 10);
        cache.put(8, 11);
        cache.put(9, 12);
        cache.put(10, 13);
        cache.put(11, 14); // should evict 1
        Assertions.assertEquals(-1, cache.get(1));
        Assertions.assertEquals(10, cache.get(7)); // different key and value values
    }
    @Test
    public void complexTest(){
        LRUCache4 cache = new LRUCache4(3);
        cache.put(14, 7);
        cache.put(3, 3);
        cache.put(5, 96);
        Assertions.assertEquals(7, cache.get(14));
        Assertions.assertEquals(-1, cache.get(4));
        cache.put(3,22);
        cache.put(4, 4);
        Assertions.assertEquals(-1, cache.get(5));
        Assertions.assertEquals(7, cache.get(14));
        Assertions.assertEquals(22, cache.get(3));
    }
    @Test
    public void repeatedAccessTest(){
        LRUCache4 cache = new LRUCache4(2);

        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.get(1);
        cache.get(1);
        cache.put(3,3);

        Assertions.assertEquals(-1, cache.get(2));
        Assertions.assertEquals(1, cache.get(1));
    }
    @Test
    public void updateOfTheElementTest(){
        LRUCache4 cache = new LRUCache4(2);
        cache.get(2);
        cache.put(2,6);
        cache.get(1);
        cache.put(1,5);
        cache.put(1,2);
        cache.get(1);

        Assertions.assertEquals(6, cache.get(2));
    }
}
