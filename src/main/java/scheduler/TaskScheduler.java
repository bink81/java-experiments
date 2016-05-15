package scheduler;

import java.util.LinkedList;

/**
 * This class simulates scheduling of tasks. They can arrive in some order but their arrivalTime
 * MUST not be decreasing. There is only one processor and it schedules the tasks based on their
 * arrival and time it takes to execute them. Once a tasks is scheduled, the processor cannot be
 * interrupted or change the scheduled plan. Tasks that need to wait, until the previous tasks
 * finish, are persisted in a buffer of a predefined size. If there is not enough space in the
 * buffer, a task will be skipped with executionTime = -1 in the response.
 *
 * If there is not enough work, the processor will wait.
 */
class TaskScheduler {
	private final int maximumBufferSize;
	private final LinkedList<ScheduledTask> queue = new LinkedList<>();

	public TaskScheduler(int maximumBufferSize) {
		this.maximumBufferSize = maximumBufferSize;
	}

	public ScheduledResponse schedule(final SchedulingRequest request) {
		while (queue.peekFirst() != null
				&& request.getArrivalTime() >= queue.peekFirst().getCompletionTime()) {
			queue.pollFirst();
		}
		ScheduledResponse scheduledResponse;
		if (queue.size() < maximumBufferSize) {
			int taskStartTime = request.getArrivalTime();
			if (queue.peekLast() != null
					&& queue.peekLast().getCompletionTime() > request.getArrivalTime()) {
				taskStartTime = queue.peekLast().getCompletionTime();
			}
			scheduledResponse = new ScheduledResponse(taskStartTime);
			queue.add(new ScheduledTask(taskStartTime,
					taskStartTime + request.getProcessingTime()));
		} else {
			scheduledResponse = new ScheduledResponse(-1);
		}
		return scheduledResponse;
	}
}