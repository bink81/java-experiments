package concurrency.mapreducer;

import java.util.List;

import concurrency.mapreducer.core.MapReducer;
import concurrency.mapreducer.core.Reducer;

public class WordCounterReducer implements Reducer {

	private final MapReducer mapReducer;

	public WordCounterReducer(MapReducer mapReducer) {
		this.mapReducer = mapReducer;
	}

	@Override
	public void reduce(String key, List<Object> values) {
		int total = 0;
		for (Object value : values) {
			total = total + (Integer) value;
		}
		mapReducer.emit(key, total);
	}
}
