package utils;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class EventAnalyzerTest {

	private EventAnalyzer eventAnalyzer;

	@Before
	public void setUp() throws Exception {
		eventAnalyzer = new EventAnalyzer();
	}

	@Test
	public void testInitilizedSize() throws Exception {
		Assert.assertEquals(0, eventAnalyzer.getEventCount());
	}

	@Test
	public void testInitilizedDifference() throws Exception {
		Assert.assertEquals(0, eventAnalyzer.getTotalDifference());
	}

	@Test
	public void testBefore() throws Exception {
		eventAnalyzer.before();

		Assert.assertEquals(1, eventAnalyzer.getEventCount());
	}

	@Test
	public void testAfter() throws Exception {
		eventAnalyzer.before();
		eventAnalyzer.after();

		Assert.assertEquals(2, eventAnalyzer.getEventCount());
	}

	@Test
	public void testAdd() throws Exception {
		eventAnalyzer.add(null);
		eventAnalyzer.add("");
		eventAnalyzer.add("1");

		Assert.assertEquals(3, eventAnalyzer.getEventCount());
	}
}
