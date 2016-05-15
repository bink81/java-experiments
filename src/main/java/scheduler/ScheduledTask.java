package scheduler;

class ScheduledTask {
	private final int startTime;
	private final int completionTime;

	public ScheduledTask(int startTime, int completiontime) {
		this.startTime = startTime;
		this.completionTime = completiontime;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getCompletionTime() {
		return completionTime;
	}
}