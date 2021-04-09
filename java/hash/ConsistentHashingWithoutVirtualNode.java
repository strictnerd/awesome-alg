package com.github.distribute.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * ��������ڵ��һ����Hash�㷨
 * �ص㣺1.�����һ��hash����2.����ڹ�ϣ����ӳ��������ڵ㣬3.����ҵ���Ӧ�Ľڵ�
 */
public class ConsistentHashingWithoutVirtualNode {

    //�������Hash���ķ������б�  
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111",
            "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};

    //key��ʾ��������hashֵ��value��ʾ������  
    private static SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

    //�����ʼ���������еķ���������sortedMap��  
    static {
        for (int i = 0; i < servers.length; i++) {
            int hash = getHash(servers[i]);
            System.out.println("[" + servers[i] + "]���뼯����, ��HashֵΪ" + hash);
            sortedMap.put(hash, servers[i]);
        }
        System.out.println();
    }


    //�õ�Ӧ��·�ɵ��Ľ��
    private static String getServer(String key) {
        //�õ���key��hashֵ  
        int hash = getHash(key);
        //�õ����ڸ�Hashֵ������Map  
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if (subMap.isEmpty()) {
            //���û�бȸ�key��hashֵ��ģ���ӵ�һ��node��ʼ  
            Integer i = sortedMap.firstKey();
            //���ض�Ӧ�ķ�����  
            return sortedMap.get(i);
        } else {
            //��һ��Key����˳ʱ���ȥ��node������Ǹ����  
            Integer i = subMap.firstKey();
            //���ض�Ӧ�ķ�����  
            return subMap.get(i);
        }
    }

    //ʹ��FNV1_32_HASH�㷨�����������Hashֵ,���ﲻʹ����дhashCode�ķ���������Ч��û����  
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // ����������ֵΪ������ȡ�����ֵ  
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    public static void main(String[] args) {
        String[] keys = {"̫��", "����", "����"};
        for (int i = 0; i < keys.length; i++)
            System.out.println("[" + keys[i] + "]��hashֵΪ" + getHash(keys[i])
                    + ", ��·�ɵ����[" + getServer(keys[i]) + "]");
    }
} 