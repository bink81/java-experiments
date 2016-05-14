package code.validation;

class Bracket {
	private final char type;
	private final int position;

	Bracket(char type, int position) {
		this.type = type;
		this.position = position;
	}

	boolean typeMatches(char c) {
		if (this.getType() == '[' && c == ']') {
			return true;
		}
		if (this.getType() == '{' && c == '}') {
			return true;
		}
		if (this.getType() == '(' && c == ')') {
			return true;
		}
		return false;
	}

	public char getType() {
		return type;
	}

	public int getPosition() {
		return position;
	}
}
