package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 {

	private int[] registers = new int[4];

	private List<Instruction> instructions = new ArrayList<>();

	private int position = 0;

	private final int initializationKey;

	public Day12() {
		this.initializationKey = 0;
	}

	public Day12(int initializationKey) {
		this.initializationKey = initializationKey;
	}

	public long task1(final File file) throws IOException {
		Files.lines(file.toPath()).forEach(line -> parse(line));
		registers[2] = initializationKey;
		return process();
	}

	private Object parse(String line) {
		Pattern p = Pattern.compile("(\\w+) (.+)");
		Matcher m = p.matcher(line.trim());
		if (m.find()) {
			switch (m.group(1)) {
			case "cpy":
				String[] split = m.group(2).split(" ");
				instructions.add(new Cpy(split[0], split[1].charAt(0) - 97));
				break;
			case "inc":
				instructions.add(new Inc(m.group(2).charAt(0) - 97));
				break;
			case "dec":
				instructions.add(new Dec(m.group(2).charAt(0) - 97));
				break;
			case "jnz":
				split = m.group(2).split(" ");
				instructions.add(new Jnz(split[0], Integer.valueOf(split[1])));
				break;
			}
		}
		return null;
	}

	private long process() {
		for (position = 0; position < instructions.size(); position++) {
			Instruction instruction = instructions.get(position);
			instruction.execute();
		}
		return registers[0];
	}

	interface Instruction {
		public void execute();
	}

	final class Cpy implements Instruction {
		private String x;

		private int y;

		public Cpy(String x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void execute() {
			if (x.charAt(0) >= 'a' && x.charAt(0) <= 'd') {
				int index = x.charAt(0) - 97;
				registers[y] = registers[index];
			}
			else {
				registers[y] = Integer.valueOf(x);
			}
		}

		@Override
		public String toString() {
			return "Cpy [x=" + x + ", y=" + y + "]";
		}
	}

	final class Inc implements Instruction {
		private final int register;

		public Inc(int register) {
			this.register = register;
		}

		public int getRegister() {
			return register;
		}

		@Override
		public void execute() {
			registers[register]++;
		}

		@Override
		public String toString() {
			return "Inc [register=" + register + "]";
		}
	}

	final class Dec implements Instruction {
		private final int register;

		public Dec(int register) {
			this.register = register;
		}

		public int getRegister() {
			return register;
		}

		@Override
		public void execute() {
			registers[register]--;
		}

		@Override
		public String toString() {
			return "Dec [register=" + register + "]";
		}
	}

	final class Jnz implements Instruction {
		private String x;

		private final int value;

		public Jnz(String x, int value) {
			this.x = x;
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		@Override
		public void execute() {
			int ref;
			if (x.charAt(0) >= 'a' && x.charAt(0) <= 'd') {
				ref = registers[x.charAt(0) - 97];
			}
			else {
				ref = Integer.valueOf(x);
			}
			if (ref != 0) {
				position += value > 0 ? value - 1 : value - 1;
			}
		}

		@Override
		public String toString() {
			return "Jnz [x=" + x + ", value=" + value + "]";
		}
	}
}
