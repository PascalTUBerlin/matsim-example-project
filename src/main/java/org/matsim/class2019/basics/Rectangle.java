package org.matsim.class2019.basics;

public class Rectangle {
	private double height;
	private double width;

	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public double calculateArea() {
		return width*height;
	}

}
