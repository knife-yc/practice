package com.yc.practice.algorithm.leetcode.easy;

import java.util.*;

public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        if(pattern == null || str == null){
            return false;
        }
        char[] array = pattern.toCharArray();
        String[] strArray = str.split(" ");
        if(strArray.length != array.length){
            return false;
        }
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i = 0;i < array.length;i++){
            Character tmp = array[i];
            List<Integer> val = map.get(tmp);
            if(val == null){
                val = new ArrayList<>();
                val.add(i);
                map.put(tmp,val);
            }else{
                val.add(i);
            }
        }

        Map<String, List<Integer>> strmap = new HashMap<>();
        for(int i = 0;i < strArray.length;i++){
            String tmp = strArray[i];
            List<Integer> val = strmap.get(tmp);
            if(val == null){
                val = new ArrayList<>();
                val.add(i);
                strmap.put(tmp,val);
            }else{
                val.add(i);
            }
        }
        Iterator<List<Integer>> iterator = strmap.values().iterator();
        while(iterator.hasNext()){
            List<Integer> list = iterator.next();
            if(!map.containsValue(list)){
                return false;
            }
        }
        return true;
    }

    /*
    char作为key，String作为value，key相同时value不同则不符合算法
     */
    public boolean answer(String pattern, String str) {
        String[] arr= str.split(" ");
        HashMap<Character, String> map = new HashMap<Character, String>();
        if(arr.length!= pattern.length())
            return false;
        for(int i=0; i<arr.length; i++){
            char c = pattern.charAt(i);
            if(map.containsKey(c)){
                if(!map.get(c).equals(arr[i]))
                    return false;
            }else{
                if(map.containsValue(arr[i]))
                    return false;
                map.put(c, arr[i]);
            }
        }
        return true;
    }
}
