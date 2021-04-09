package com.github.distribute.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUArray<T> {
    private static final int DEFAULT_CAPACITY = 1 << 3;

    private int capacity;
    private int count;

    private T[] value;

    private Map<T, Integer> holder;

    public LRUArray() {
        this(5);
    }

    public LRUArray(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.value = (T[])new Object[capacity];
        this.holder = new HashMap<T, Integer>(capacity);
    }

    public void offer(T object){
        if (object == null) {
            throw new IllegalArgumentException("not support null");
        }
        Integer integer = holder.get(object);
        //如果数据为空
        if (integer == null) {
            if(isFull()) {//先删除，后更新
                removeAndCache(object);
            }else {//添加到第一个
                cache(object, count);
            }
        }else {//如果不为空，更新index索引信息
            updateIndex(integer);
        }
    }

    private void updateIndex(Integer integer) {
        T t = value[integer];
        rightShift(integer);
        value[0] = t;
        holder.put(t, 0);
    }

    private void removeAndCache(T object) {
        T key = value[--count];
        holder.remove(key);
        cache(object, count);
    }

    private void cache(T object, int end) {
        rightShift(end);
        value[0] = object;
        holder.put(object, 0);
        count++;
    }


    private void rightShift(int count) {
        for (int i = count-1; i >= 0; i--) {
            value[i+1] = value[i];
            holder.put(value[i], i+1);
        }
    }

    private boolean isFull() {
        return count == capacity;
    }

    public static void testDefaultConstructor() {
        System.out.println("======无参测试========");
        LRUArray<Integer> lru = new LRUArray<Integer>();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void testSpecifiedConstructor(int capacity) {
        System.out.println("======有参测试========");
        LRUArray<Integer> lru = new LRUArray<Integer>(capacity);
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

    public static void main(String[] args) {
        testSpecifiedConstructor(7);
    }
}
