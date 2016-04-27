package concurrency.mapreducer.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapReducerMock implements MapReducer {
	private final Map<String, List<Object>> mapperResults = new HashMap<String, List<Object>>();
	private final Map<String, Object> reducerResults = new HashMap<>();

	@Override
	public void emitIntermediate(String key, Object value) {
		List<Object> list = new ArrayList<>();
		list.add(value);
		mapperResults.put(key, list);
	}

	@Override
	public void emit(String key, Object value) {
		reducerResults.put(key, value);
	}

	@Override
	public Map<String, Object> execute(List<String> records, Mapper mapper, Reducer reducer) {
		for (String record : records) {
			mapper.map(record);
		}

		for (Entry<String, List<Object>> entry : mapperResults.entrySet()) {
			reducer.reduce(entry.getKey(), entry.getValue());
		}
		return reducerResults;
	}
}
