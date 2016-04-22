package utils;

// Implementation of Tomohiko Sakamoto's Algorithm
public class DayOfWeek {
	static int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };

	public static int calculate(int year, int month, int day) {
		if (month < 3) { // Jan or Feb
			year = year - month;
		}
		return (year + year / 4 - year / 100 + year / 400 + t[month - 1] + day) % 7;
	}
}
