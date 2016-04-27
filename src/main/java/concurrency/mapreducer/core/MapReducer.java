package concurrency.mapreducer.core;

import java.util.List;
import java.util.Map;

public interface MapReducer {

	void emitIntermediate(String key, Object value);

	void emit(String key, Object result);

	Map<String, Object> execute(List<String> records, Mapper mapper, Reducer reducer);
}
