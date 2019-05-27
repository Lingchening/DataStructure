package java.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于数组实现LRU缓存
 * 空间复杂度O(n)
 * 时间复杂度O(n)
 * 不支持null的缓存
 *
 * @param <T>
 */
public class LRUBasedArray<T> {
    private static final int DEFAULT_CAPACITY = 1 << 3;

    private int capacity;

    private int count;

    private T[] value;

    private Map<T, Integer> holder;

    public LRUBasedArray() {
        this(DEFAULT_CAPACITY);
    }

    //初始化
    public LRUBasedArray(int capacity) {
        this.value = (T[]) new Object[capacity];
        this.count = 0;
        this.capacity = capacity;
        holder = new HashMap<T, Integer>(capacity);
    }

    //查询

    public void offer(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("该缓存容器不支持null!");
        }
        Integer index = holder.get(obj);

        if (index == null) {
            if (isFull()) {
                remove(obj);
            } else {
                cache(obj, count);
            }
        } else {
            update(index);
        }
    }

    public boolean isFull() {
        return count == capacity;
    }

    public void remove(T obj) {
        T key = value[--count];
        holder.remove(key);
        cache(obj,count);
    }

    public void cache(T obj, int end) {
        rightShit(end);
        value[0] = obj;
        holder.put(obj,0);
        count++;
    }

    public void update(int end) {
        T target = value[end];
        rightShit(end);
        value[0] = target;
        holder.put(target,0);
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void rightShit(int end) {
        for (int i = end - 1; i >= 0; i--) {
            value[i + 1] = value[i];
            holder.put(value[i],i+1);
        }
    }
    public boolean isContain(T object) {
        return holder.containsKey(object);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    static class TestLRUBasedArray {

        public static void main(String[] args) {
            testDefaultConstructor();
            testSpecifiedConstructor(4);
//            testWithException();
        }

        private static void testWithException() {
            LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
            lru.offer(null);
        }

        public static void testDefaultConstructor() {
            System.out.println("======无参测试========");
            LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
            lru.offer(1);
            lru.offer(2);
            lru.offer(3);
            lru.offer(4);
            lru.offer(5);
            System.out.println(lru);
            lru.offer(6);
            lru.offer(7);
            lru.offer(8);
            lru.offer(9);
            System.out.println(lru);
        }

        public static void testSpecifiedConstructor(int capacity) {
            System.out.println("======有参测试========");
            LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>(capacity);
            lru.offer(1);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
            lru.offer(3);
            System.out.println(lru);
            lru.offer(4);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
            lru.offer(4);
            System.out.println(lru);
            lru.offer(7);
            System.out.println(lru);
            lru.offer(1);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
        }
    }
}
