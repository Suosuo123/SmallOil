package com.hh.oil.entity;

@SuppressWarnings("serial")
public class Person extends BaseEntity {
	private String name;
	private int age;
	private float score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
