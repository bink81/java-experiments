package math;

import java.math.BigDecimal;
import java.util.Scanner;

import math.Triangle.Type;

/**
 * This class asks for a user's input of three sides of a triangle and
 * determines it's geometric properties.
 * 
 * @author bink81@gmail.com
 */
public final class TriangleDemo {
	public static void main(String[] args) {
		printIntroductionMessage();
		try (Scanner scanner = new Scanner(System.in)) {
			BigDecimal sideA = readUserInputForSide(scanner, "A");
			BigDecimal sideB = readUserInputForSide(scanner, "B");
			BigDecimal sideC = readUserInputForSide(scanner, "C");
			printTriangleType(new Triangle(sideA, sideB, sideC).determineType());
		} catch (TriangleUnequalException e) {
			printExceptionMessage(e.getMessage());
		}
		printExitMessage();
	}

	private static BigDecimal readUserInputForSide(final Scanner scanner, final String sideName) {
		BigDecimal value = null;
		while (value == null) {
			try {
				System.out.print("Enter a positive, decimal value for side " + sideName + ": ");
				value = new BigDecimal(scanner.nextLine());
				if (value.compareTo(BigDecimal.ZERO) < 0) {
					System.out.println("The provided value is not positive, try again (or 0 to exit)");
					value = null;
					continue;
				} else if (value.compareTo(BigDecimal.ZERO) == 0) {
					System.out.println("User abort detected0");
					printExitMessage();
					System.exit(0);
				}
			} catch (NumberFormatException e) {
				System.out.println("The provided value is not a number, try again (or 0 to exit)");
			}
		}
		return value;
	}

	private static void printIntroductionMessage() {
		System.out.println(
				"This program reads three sides of a triangle and determines whether it's equilateral, isosceles, scalene or invalid.");
		System.out.println("");
	}

	private static void printTriangleType(final Type type) {
		System.out.println("");
		System.out.println("This triangle is " + type);
	}

	private static void printExceptionMessage(final String message) {
		System.out.println("");
		System.out.println("The provided values do not form a valid triangle (guess: " + message + ").");
	}

	private static void printExitMessage() {
		System.out.println("");
		System.out.print("Exiting...");
	}
}
