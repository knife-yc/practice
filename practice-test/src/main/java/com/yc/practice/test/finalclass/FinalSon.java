package com.yc.practice.test.finalclass;

public final class FinalSon extends Base {

	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	protected Object clone() {
		try {
			throw new CloneNotSupportedException();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "FinalSon [age=" + age + "]";
	}

}
