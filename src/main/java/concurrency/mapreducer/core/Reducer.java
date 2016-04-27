package concurrency.mapreducer.core;

import java.util.List;

public interface Reducer {
	void reduce(String key, List<Object> value);
}
