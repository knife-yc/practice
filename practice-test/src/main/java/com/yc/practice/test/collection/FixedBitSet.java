package com.yc.practice.test.collection;

import java.util.BitSet;

public class FixedBitSet {

	//BitSet：位图，使用0和1标记数据是否出现，如果该数据出现了，则对应的bit位为1，否则为0，默认的初始大小为64，主要应用于海量数据存储，查找
	
	
	private final BitSet bitSet = new BitSet();;
	
	public void add(int num){
		bitSet.set(num);
	}
	
	public boolean getBoolean(int bitIndex){
		return bitSet.get(bitIndex);
	}
	
	public int getInt(int bitIndex){
		boolean flag = bitSet.get(bitIndex);
		if(flag){
			return bitIndex;
		}else{
			return 0;
		}
	}
	
	public int size(){
		return bitSet.size();
	}

	@Override
	public String toString() {
		return "FixedBitSet [bitSet=" + bitSet + "]";
	}
	
	
}
