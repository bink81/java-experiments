package concurrency.mapreducer;

import java.util.Arrays;
import java.util.Map;

import concurrency.mapreducer.core.MapReducer;
import concurrency.mapreducer.core.MapReducerMock;
import concurrency.mapreducer.core.Mapper;
import concurrency.mapreducer.core.Reducer;

public class WordCounter {
	MapReducer mapReducer = new MapReducerMock();

	public Map<String, Object> execute(String[] records) {
		MapReducer mapReducer = new MapReducerMock();
		Mapper mapper = new WordCounterMapper(mapReducer);
		Reducer reducer = new WordCounterReducer(mapReducer);
		return mapReducer.execute(Arrays.asList(records), mapper, reducer);
	}

	public static void main(String[] args) {
		new WordCounter().execute(args);
	}
}
