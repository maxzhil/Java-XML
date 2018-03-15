package ru.rsreu.Zhilenko0904.model;

public class Package {
	private String type;
	private int count;
	private int price;

	public Package() {

	}

	public Package(String type, int count, int price) {
		this.count = count;
		this.type = type;
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Package [type=" + type + ", count=" + count + ", price="
				+ price + "]";
	}

}
