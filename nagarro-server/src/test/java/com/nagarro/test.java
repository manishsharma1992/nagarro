package com.nagarro;

public class test {
	
	public static void main(String[] args) {
		int x = 5, y;
		y = ++x + x++ + --x;
		System.out.println(x + ", " + y);
	}

}
