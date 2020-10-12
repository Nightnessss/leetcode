package day20201012_lru;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 * <p>
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 示例:
 * LRUCache cache = new LRUCache( 2 );
 * <p>
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/10/12 14:35
 */
public class Main {


    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(2 /* 缓存容量 */);

        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);    // 该操作会使得密钥 2 作废
        cache.put(4, 1);
        System.out.println(cache.get(1));        // 返回 -1 (未找到)
        System.out.println(cache.get(2));        // 返回  3
    }

}

class LRUCache {
    private int capacity;
    private LinkedHashMap<Integer, Integer> linkedHashMap;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LinkedHashMap<Integer, Integer> getLinkedHashMap() {
        return linkedHashMap;
    }

    public void setLinkedHashMap(LinkedHashMap<Integer, Integer> linkedHashMap) {
        this.linkedHashMap = linkedHashMap;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.linkedHashMap = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!this.linkedHashMap.containsKey(key)) {
            return -1;
        }
        int value = this.linkedHashMap.get(key);
        this.linkedHashMap.remove(key);
        this.linkedHashMap.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        this.linkedHashMap.remove(key);
        this.linkedHashMap.put(key, value);
        if (this.linkedHashMap.size() > this.capacity) {
            this.linkedHashMap.remove(this.linkedHashMap.entrySet().iterator().next().getKey());
        }
    }
}

class LRUCache2 {
    static class Data {
        int key, value;

        public Data(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final LinkedList<Data> linkedList;
    private final Map<Integer, Data> map;
    private final int capacity;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        this.linkedList = new LinkedList<>();
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!this.map.containsKey(key)) {
            return -1;
        }
        Data data = this.map.get(key);
        this.linkedList.remove(data);
        this.linkedList.add(data);
        return data.value;
    }

    public void put(int key, int value) {
        Data data = new Data(key, value);
        this.linkedList.add(data);
        if (this.map.containsKey(key)) {
            this.linkedList.remove(this.map.get(key));
        }
        this.map.put(key, data);
        if (this.linkedList.size() > this.capacity) {
            Data remove = this.linkedList.removeFirst();
            this.map.remove(remove.key);
        }
    }


}