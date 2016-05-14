package code.validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BracketValidator {
	public static final int SUCCESS = -1;

	public static int findUnbalanancedBracketPosition(String input) {
		Stack<Bracket> openedBrackets = new Stack<Bracket>();
		for (int position = 0; position < input.length(); ++position) {
			char next = input.charAt(position);
			if (isOpeningBracket(next)) {
				openedBrackets.push(new Bracket(next, position));
			} else if (isClosingBracket(next)) {
				if (openedBrackets.isEmpty()) {
					return position;
				}
				Bracket top = openedBrackets.pop();
				if (!top.typeMatches(next)) {
					return position;
				}
			}
		}
		if (!openedBrackets.isEmpty()) {
			return openedBrackets.pop().getPosition();
		}
		return SUCCESS;
	}

	private static boolean isOpeningBracket(char next) {
		return next == '(' || next == '[' || next == '{';
	}

	private static boolean isClosingBracket(char next) {
		return next == ')' || next == ']' || next == '}';
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader inputStream = new InputStreamReader(System.in);
		String text = new BufferedReader(inputStream).readLine();
		System.out.println(findUnbalanancedBracketPosition(text));
	}
}
