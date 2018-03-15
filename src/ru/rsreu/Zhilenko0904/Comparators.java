package ru.rsreu.Zhilenko0904;

import java.util.Comparator;

import ru.rsreu.Zhilenko0904.model.Medicine;

public class Comparators {
	private Comparators() {
	}

	public static Comparator<Medicine> sortByPrice = new Comparator<Medicine>() {
		public int compare(Medicine m1, Medicine m2) {
			return m1.getPharm().getPacket().getPrice()
					- m2.getPharm().getPacket().getPrice();
		}
	};
}
