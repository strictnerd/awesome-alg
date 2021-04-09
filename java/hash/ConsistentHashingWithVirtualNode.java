package com.github.distribute.hash;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;  
import java.util.SortedMap;  
import java.util.TreeMap;  


/** 
  * ������ڵ��һ����Hash�㷨 
  */  
 public class ConsistentHashingWithVirtualNode {

     //�������Hash���ķ������б�  
     private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",  
             "192.168.0.3:111", "192.168.0.4:111"};  

     //��ʵ����б�,���ǵ����������ߡ����ߵĳ���������ӡ�ɾ���ĳ�����Ƚ�Ƶ��������ʹ��LinkedList�����  
     private static List<String> realNodes = new LinkedList<String>();  

     //����ڵ㣬key��ʾ����ڵ��hashֵ��value��ʾ����ڵ������  
     private static SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();  

     //����ڵ����Ŀ������д����Ϊ����ʾ��Ҫ��һ����ʵ����Ӧ5������ڵ�  
     private static final int VIRTUAL_NODES = 5;  

     static{  
         //�Ȱ�ԭʼ�ķ�������ӵ���ʵ����б���  
         for(int i=0; i<servers.length; i++)  
             realNodes.add(servers[i]);  

         //���������ڵ㣬����LinkedListʹ��foreachѭ��Ч�ʻ�Ƚϸ�  
         for (String str : realNodes){  
             for(int i=0; i<VIRTUAL_NODES; i++){  
                 String virtualNodeName = str + "&&VN" + String.valueOf(i);  
                 int hash = getHash(virtualNodeName);  
                 System.out.println("����ڵ�[" + virtualNodeName + "]�����, hashֵΪ" + hash);  
                 virtualNodes.put(hash, virtualNodeName);  
             }  
         }  
         System.out.println();  
     }  

     //ʹ��FNV1_32_HASH�㷨�����������Hashֵ,���ﲻʹ����дhashCode�ķ���������Ч��û����  
     private static int getHash(String str){  
         final int p = 16777619;  
         int hash = (int)2166136261L;  
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

     //�õ�Ӧ��·�ɵ��Ľ��  
     private static String getServer(String key){  
        //�õ���key��hashֵ  
         int hash = getHash(key);  
         // �õ����ڸ�Hashֵ������Map  
         SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);  
         String virtualNode;  
         if(subMap.isEmpty()){  
            //���û�бȸ�key��hashֵ��ģ���ӵ�һ��node��ʼ  
            Integer i = virtualNodes.firstKey();  
            //���ض�Ӧ�ķ�����  
            virtualNode = virtualNodes.get(i);  
         }else{  
            //��һ��Key����˳ʱ���ȥ��node������Ǹ����  
            Integer i = subMap.firstKey();  
            //���ض�Ӧ�ķ�����  
            virtualNode = subMap.get(i);  
         }  
         //virtualNode����ڵ�����Ҫ��ȡһ��  
         if(StringUtils.isNotBlank(virtualNode)){
             return virtualNode.substring(0, virtualNode.indexOf("&&"));  
         }  
         return null;  
     }  

     public static void main(String[] args){  
         String[] keys = {"̫��", "����", "����"};  
         for(int i=0; i<keys.length; i++)  
             System.out.println("[" + keys[i] + "]��hashֵΪ" +  
                     getHash(keys[i]) + ", ��·�ɵ����[" + getServer(keys[i]) + "]");  
     }  
 }