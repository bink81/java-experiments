package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {
	private static final int FINAL_LOW = 17;
	private static final int FINAL_HIGH = 61;

	private Bot[] bots;
	private List<Integer>[] outputs;
	private LinkedList<Operation> initOperations = new LinkedList<>();
	private LinkedList<Operation> operations = new LinkedList<>();
	private int maxBotId = 0;
	private int maxOutputId = 0;
	private int solutionBot;

	public long task1(final File file) throws IOException {
		commonSolution(file);
		return outputs[0].get(0) * outputs[1].get(0) * outputs[2].get(0);
	}

	public long task2(final File file) throws IOException {
		commonSolution(file);
		return solutionBot;
	}

	private void commonSolution(final File file) throws IOException {
		Files.lines(file.toPath()).forEach(line -> parse(line));
		bots = new Bot[maxBotId + 1];
		for (int i = 0; i < maxBotId + 1; i++) {
			bots[i] = new Bot(i);
		}
		outputs = new List[maxOutputId + 1];
		for (int i = 0; i < maxOutputId + 1; i++) {
			outputs[i] = new ArrayList<>();
		}

		for (Operation operation : initOperations) {
			operation.execute();
		}

		boolean somethingGotExecuted = true;
		while (somethingGotExecuted) {
			somethingGotExecuted = false;
			for (Operation operation : operations) {
				if (operation.canExecute()) {
					operation.execute();
					somethingGotExecuted = true;
				}
			}
		}
	}

	private Object parse(String line) {
		Pattern givesPattern = Pattern
				.compile("bot (\\d+) gives low to (\\w+) (\\d+) and high to (\\w+) (\\d+)");
		Matcher givesMatch = givesPattern.matcher(line.trim());
		if (givesMatch.find()) {
			int lowTarget = -1;
			int highTarget = -1;
			boolean lowBot = true;
			boolean highBot = true;
			if (givesMatch.group(2).equals("output")) {
				lowBot = false;
			}
			lowTarget = Integer.valueOf(givesMatch.group(3));

			// gives high
			if (givesMatch.group(4).equals("output")) {
				highBot = false;
			}
			highTarget = Integer.valueOf(givesMatch.group(5));

			int botId = Integer.valueOf(givesMatch.group(1));
			if (botId > maxBotId) {
				maxBotId = botId;
			}
			if (lowBot && lowTarget > maxBotId) {
				maxBotId = lowTarget;
			}
			if (!lowBot && lowTarget > maxOutputId) {
				maxOutputId = lowTarget;
			}
			if (highBot && highTarget > maxBotId) {
				maxBotId = highTarget;
			}
			if (!highBot && highTarget > maxOutputId) {
				maxOutputId = highTarget;
			}
			OperationGive operationGive = new OperationGive(botId, lowTarget, lowBot, highTarget,
					highBot);
			operations.add(operationGive);
		} else {
			Pattern initPattern = Pattern.compile("value (\\d+) goes to bot (\\d+)");
			Matcher initMatch = initPattern.matcher(line.trim());
			if (initMatch.find()) {
				int botId = Integer.valueOf(initMatch.group(1));
				if (botId > maxBotId) {
					maxBotId = botId;
				}
				int value = Integer.valueOf(initMatch.group(2));
				initOperations.add(new OperationInit(botId, value));
			}
		}
		return null;
	}

	public interface Operation {
		public boolean canExecute();

		public void execute();
	}

	final class OperationInit implements Operation {
		private final int value;
		private final int bot;

		public OperationInit(int value, int bot) {
			super();
			this.value = value;
			this.bot = bot;
		}

		public int getValue() {
			return value;
		}

		public int getBot() {
			return bot;
		}

		@Override
		public boolean canExecute() {
			return bots[bot].canReceive();
		}

		@Override
		public void execute() {
			bots[bot].receive(value);
		}

		@Override
		public String toString() {
			return "OperationInit [value=" + value + ", bot=" + bot + "]";
		}
	}

	final class OperationGive implements Operation {
		final int giverBotId;
		final int lowTargetId; // id of a bot or output
		final boolean lowIsBot; // otherwise it's an output
		final int highTargetId; // id of a bot or output
		final boolean highIsBot; // otherwise it's an output
		boolean executed;

		public OperationGive(int giverBotId, int lowTargetId, boolean lowIsBot, int highTargetId,
				boolean highIsBot) {
			this.giverBotId = giverBotId;
			this.lowTargetId = lowTargetId;
			this.lowIsBot = lowIsBot;
			this.highTargetId = highTargetId;
			this.highIsBot = highIsBot;
		}

		@Override
		public boolean canExecute() {
			return bots[giverBotId].canGive() && (!lowIsBot || bots[lowTargetId].canReceive())
					&& (!highIsBot || bots[highTargetId].canReceive());
		}

		@Override
		public void execute() {
			Integer[] result = bots[giverBotId].give();
			if (lowIsBot) {
				bots[lowTargetId].receive(result[0]);
			} else {
				outputs[lowTargetId].add(result[0]);
			}
			if (highIsBot) {
				bots[highTargetId].receive(result[1]);
			} else {
				outputs[highTargetId].add(result[1]);
			}
		}

		@Override
		public String toString() {
			return "OperationGive [bot=" + giverBotId + ", lowTarget=" + lowTargetId + ", lowBot="
					+ lowIsBot + ", highTarget=" + highTargetId + ", highBot=" + highIsBot + ", executed="
					+ executed + "]";
		}
	}

	final class Bot {
		private final int id;
		private final List<Integer> values = new ArrayList<>();

		public Bot(int id) {
			this.id = id;
		}

		public boolean canReceive() {
			return values.size() < 2;
		}

		public void receive(Integer value) {
			values.add(value);
			if (values.size() > 2) {
				throw new RuntimeException(id + " has too many tokens: " + values);
			}
		}

		public boolean canGive() {
			return values.size() > 1;
		}

		public Integer[] give() {
			int val1 = values.get(0);
			int val2 = values.get(1);
			Integer[] result = new Integer[2];
			if (val1 < val2) {
				result[0] = val1;
				result[1] = val2;
			} else {
				result[0] = val2;
				result[1] = val1;
			}
			if (result[0] == FINAL_LOW && result[1] == FINAL_HIGH) {
				solutionBot = id;
			}
			values.clear();
			return result;
		}

		@Override
		public String toString() {
			return "Bot [id=" + id + ", values=" + values + "]";
		}
	}
}
