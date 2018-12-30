package com.yc.practice.test.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		FixedBitSet fixedBitSet = new FixedBitSet();
		fixedBitSet.add(2);
		fixedBitSet.add(4);
		fixedBitSet.add(3);
		fixedBitSet.add(5);
		fixedBitSet.add(6);
		fixedBitSet.add(7);
		System.out.println(fixedBitSet.size());
		System.out.println(fixedBitSet);
		int num = fixedBitSet.getInt(3);
		System.out.println(num);
		num = fixedBitSet.getInt(23);
		System.out.println(num);
		boolean flag = fixedBitSet.getBoolean(23);
		System.out.println(flag);
		testArrayCopy();
	}

	public static void testArrayCopy() {
		int size = 1000000;
		List<Integer> arrayList = new ArrayList<Integer>();
		List<Integer> linkedList = new LinkedList<Integer>();
		long startTimeMS = System.currentTimeMillis();
		for(int i = 0;i < size;i++){
			arrayList.add(i);
		}
		long arrayEndTimeMS = System.currentTimeMillis();
		for(int i = 0;i < size;i++){
			linkedList.add(i);
		}
		long linkedEndTime = System.currentTimeMillis();
		System.out.println("arrayUsingTime:"+(arrayEndTimeMS - startTimeMS)+",linkedUsingTimeMS:"+(linkedEndTime - arrayEndTimeMS));
		long removeTimeBeginTimeMS = System.currentTimeMillis();
		for(int i = 0;i < arrayList.size();i++){
			arrayList.get(i);
		}
		long arrayRemoveEndTimeMS = System.currentTimeMillis();
		for(int i = 0;i < linkedList.size();i++){
			linkedList.get(i);
		}
		long linkedListRemoveEndTimeMS = System.currentTimeMillis();
		System.out.println("arrayUsingTime:"+(arrayRemoveEndTimeMS - removeTimeBeginTimeMS)+",linkedUsingTimeMS:"+(linkedListRemoveEndTimeMS - arrayRemoveEndTimeMS));
		
		long getStartTimeMS = System.currentTimeMillis();
		arrayList.get(10000);
		long getEndTimeMS = System.currentTimeMillis();
		linkedList.get(10000);
		long linkedListGetTimeMS = System.currentTimeMillis();
		System.out.println("arrayUsingTime:"+(getEndTimeMS - getStartTimeMS)+",linkedUsingTimeMS:"+(linkedListGetTimeMS - getEndTimeMS));
		Iterator<Integer> linkIterator = linkedList.iterator();
		Iterator<Integer> iterator = arrayList.iterator();
		long iteStartTimeMS = System.currentTimeMillis();
		while(iterator.hasNext()){
			iterator.next();
		}
		long iteEndTimeMS = System.currentTimeMillis();
		while(linkIterator.hasNext()){
			linkIterator.next();
		}
		long iteLinkEndTimeMS = System.currentTimeMillis();
		System.out.println("arrayUsingTime:"+(iteEndTimeMS - iteStartTimeMS)+",linkedUsingTimeMS:"+(iteLinkEndTimeMS - iteEndTimeMS));
		
	}
	
	public static void testArrayLinked() {
		int size = 1000000;
		List<Integer> arrayList = new ArrayList<Integer>();
		List<Integer> linkedList = new LinkedList<Integer>();
		long startTimeMS = System.currentTimeMillis();
		for(int i = 0;i < size;i++){
			arrayList.add(i);
		}
		long arrayEndTimeMS = System.currentTimeMillis();
		for(int i = 0;i < size;i++){
			linkedList.add(i);
		}
		long linkedEndTime = System.currentTimeMillis();
		System.out.println("arrayUsingTime:"+(arrayEndTimeMS - startTimeMS)+",linkedUsingTimeMS:"+(linkedEndTime - arrayEndTimeMS));
		long removeTimeBeginTimeMS = System.currentTimeMillis();
		for(int i = 0;i < arrayList.size();i++){
			arrayList.get(i);
		}
		long arrayRemoveEndTimeMS = System.currentTimeMillis();
		for(int i = 0;i < linkedList.size();i++){
			linkedList.get(i);
		}
		long linkedListRemoveEndTimeMS = System.currentTimeMillis();
		System.out.println("arrayUsingTime:"+(arrayRemoveEndTimeMS - removeTimeBeginTimeMS)+",linkedUsingTimeMS:"+(linkedListRemoveEndTimeMS - arrayRemoveEndTimeMS));
		
		long getStartTimeMS = System.currentTimeMillis();
		arrayList.get(10000);
		long getEndTimeMS = System.currentTimeMillis();
		linkedList.get(10000);
		long linkedListGetTimeMS = System.currentTimeMillis();
		System.out.println("arrayUsingTime:"+(getEndTimeMS - getStartTimeMS)+",linkedUsingTimeMS:"+(linkedListGetTimeMS - getEndTimeMS));
		Iterator<Integer> linkIterator = linkedList.iterator();
		Iterator<Integer> iterator = arrayList.iterator();
		long iteStartTimeMS = System.currentTimeMillis();
		while(iterator.hasNext()){
			iterator.next();
		}
		long iteEndTimeMS = System.currentTimeMillis();
		while(linkIterator.hasNext()){
			linkIterator.next();
		}
		long iteLinkEndTimeMS = System.currentTimeMillis();
		System.out.println("arrayUsingTime:"+(iteEndTimeMS - iteStartTimeMS)+",linkedUsingTimeMS:"+(iteLinkEndTimeMS - iteEndTimeMS));
		
	}
}
