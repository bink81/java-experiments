package scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SchedulerUI {
	private static List<SchedulingRequest> readQueries(Scanner scanner) throws IOException {
		int requests_count = scanner.nextInt();
		List<SchedulingRequest> requests = new ArrayList<SchedulingRequest>();
		for (int i = 0; i < requests_count; ++i) {
			int arrival_time = scanner.nextInt();
			int process_time = scanner.nextInt();
			requests.add(new SchedulingRequest(arrival_time, process_time));
		}
		return requests;
	}

	public static List<ScheduledResponse> processRequests(List<SchedulingRequest> requests,
			TaskScheduler taskBuffer) {
		List<ScheduledResponse> scheduledResponses = new ArrayList<ScheduledResponse>();
		for (int i = 0; i < requests.size(); ++i) {
			scheduledResponses.add(taskBuffer.schedule(requests.get(i)));
		}
		return scheduledResponses;
	}

	private static void printResponses(List<ScheduledResponse> scheduledResponses) {
		for (int i = 0; i < scheduledResponses.size(); ++i) {
			ScheduledResponse scheduledResponse = scheduledResponses.get(i);
			System.out.println(scheduledResponse.getStartTime());
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		TaskScheduler taskBuffer = new TaskScheduler(scanner.nextInt());
		List<SchedulingRequest> requests = readQueries(scanner);
		List<ScheduledResponse> scheduledResponses = processRequests(requests, taskBuffer);
		printResponses(scheduledResponses);
	}
}
