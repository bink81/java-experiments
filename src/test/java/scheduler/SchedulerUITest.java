package scheduler;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SchedulerUITest {

	@Test
	public void testWithTaskBuffer1NoSchedulingRequests() throws Exception {
		TaskScheduler buffer = new TaskScheduler(1);
		List<SchedulingRequest> requests = new ArrayList<>();
		List<ScheduledResponse> results = SchedulerUI.processRequests(requests, buffer);
		Assert.assertEquals(0, results.size());
	}

	@Test
	public void testWithTaskBuffer1And1SchedulingRequest() throws Exception {
		TaskScheduler buffer = new TaskScheduler(1);
		List<SchedulingRequest> requests = new ArrayList<>();
		requests.add(new SchedulingRequest(0, 0));

		List<ScheduledResponse> results = SchedulerUI.processRequests(requests, buffer);

		Assert.assertEquals(requests.size(), results.size());
		Assert.assertEquals(0, results.get(0).getStartTime());
	}

	@Test
	public void testWithTaskBuffer1And2SchedulingRequests() throws Exception {
		TaskScheduler buffer = new TaskScheduler(1);
		List<SchedulingRequest> requests = new ArrayList<>();
		requests.add(new SchedulingRequest(0, 1));
		requests.add(new SchedulingRequest(0, 1)); // they all come at the same time
		requests.add(new SchedulingRequest(0, 1));

		List<ScheduledResponse> results = SchedulerUI.processRequests(requests, buffer);

		Assert.assertEquals(requests.size(), results.size());
		Assert.assertEquals(0, results.get(0).getStartTime());
		Assert.assertEquals(-1, results.get(1).getStartTime());
		Assert.assertEquals(-1, results.get(2).getStartTime());
	}

	@Test
	public void testWithTaskBuffer1And1LateSchedulingRequests() throws Exception {
		TaskScheduler buffer = new TaskScheduler(1);
		List<SchedulingRequest> requests = new ArrayList<>();
		requests.add(new SchedulingRequest(1, 0));

		List<ScheduledResponse> results = SchedulerUI.processRequests(requests, buffer);

		Assert.assertEquals(requests.size(), results.size());
		Assert.assertEquals(1, results.get(0).getStartTime());
	}

	@Test
	public void testWithTaskBuffer1And2ConsequentSchedulingRequests() throws Exception {
		TaskScheduler buffer = new TaskScheduler(1);
		List<SchedulingRequest> requests = new ArrayList<>();
		requests.add(new SchedulingRequest(1, 1));
		requests.add(new SchedulingRequest(1, 0));

		List<ScheduledResponse> results = SchedulerUI.processRequests(requests, buffer);

		Assert.assertEquals(requests.size(), results.size());
		Assert.assertEquals(1, results.get(0).getStartTime());
		Assert.assertEquals(-1, results.get(1).getStartTime());
	}

	@Test
	public void testWithTaskBuffer1AndManyConsequentSchedulingRequests() throws Exception {
		TaskScheduler buffer = new TaskScheduler(1);
		List<SchedulingRequest> requests = new ArrayList<>();
		for (int i = 0; i < 100; i++) { // many consequent requests
			requests.add(new SchedulingRequest(i, 1));
		}

		List<ScheduledResponse> results = SchedulerUI.processRequests(requests, buffer);

		Assert.assertEquals(requests.size(), results.size());
		for (int i = 0; i < requests.size(); i++) {
			Assert.assertEquals(i, results.get(i).getStartTime());
		}
	}

	@Test
	public void testWithTaskBuffer1AndManyNotConsequentSchedulingRequests() throws Exception {
		TaskScheduler buffer = new TaskScheduler(1);
		List<SchedulingRequest> requests = new ArrayList<>();
		for (int i = 0; i < 100; i++) { // many consequent requests
			requests.add(new SchedulingRequest(i * 2, 1));
		}

		List<ScheduledResponse> results = SchedulerUI.processRequests(requests, buffer);

		Assert.assertEquals(requests.size(), results.size());
		for (int i = 0; i < requests.size(); i++) {
			Assert.assertEquals(i + ": ", i * 2, results.get(i).getStartTime());
		}
	}

	@Test
	public void testWithTaskBuffer2And2SchedulingRequests() throws Exception {
		TaskScheduler buffer = new TaskScheduler(2);
		List<SchedulingRequest> requests = new ArrayList<>();
		requests.add(new SchedulingRequest(0, 1));
		requests.add(new SchedulingRequest(0, 1)); // they all come at the same time
		requests.add(new SchedulingRequest(0, 1));

		List<ScheduledResponse> results = SchedulerUI.processRequests(requests, buffer);

		Assert.assertEquals(requests.size(), results.size());
		Assert.assertEquals(0, results.get(0).getStartTime());
		Assert.assertEquals(1, results.get(1).getStartTime());
		Assert.assertEquals(-1, results.get(2).getStartTime()); // no buffer space for the third one
	}

	@Test
	public void testWithTaskBuffer3AndMoreSchedulingRequests() throws Exception {
		TaskScheduler buffer = new TaskScheduler(3);
		List<SchedulingRequest> requests = new ArrayList<>();
		requests.add(new SchedulingRequest(0, 2));
		requests.add(new SchedulingRequest(1, 2));
		requests.add(new SchedulingRequest(2, 2));
		requests.add(new SchedulingRequest(3, 2));
		requests.add(new SchedulingRequest(4, 2));
		requests.add(new SchedulingRequest(5, 2));

		List<ScheduledResponse> results = SchedulerUI.processRequests(requests, buffer);

		Assert.assertEquals(requests.size(), results.size());
		Assert.assertEquals(0, results.get(0).getStartTime());
		Assert.assertEquals(2, results.get(1).getStartTime());
		Assert.assertEquals(4, results.get(2).getStartTime());
		Assert.assertEquals(6, results.get(3).getStartTime());
		Assert.assertEquals(8, results.get(4).getStartTime());
		Assert.assertEquals(-1, results.get(5).getStartTime());
	}
}
