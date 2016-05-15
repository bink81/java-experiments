package scheduler;

class SchedulingRequest {
	private final int arrivalTime;
	private final int processingTime;

	public SchedulingRequest(int arrivalTime, int processingTime) {
		this.arrivalTime = arrivalTime;
		this.processingTime = processingTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getProcessingTime() {
		return processingTime;
	}
}