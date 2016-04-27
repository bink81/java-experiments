package concurrency.mapreducer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import concurrency.mapreducer.core.MapReducer;
import concurrency.mapreducer.core.Mapper;

public class WordCounterMapper implements Mapper {

	private final MapReducer mapReducer;
	private final Map<String, Integer> wordCount = new HashMap<String, Integer>();

	public WordCounterMapper(MapReducer mapReducer) {
		this.mapReducer = mapReducer;
	}

	@Override
	public void map(String record) {
		String[] words = record.split("\\s");
		for (String word : words) {
			if (wordCount.containsKey(word)) {
				wordCount.put(word, wordCount.get(word) + 1);
			} else {
				wordCount.put(word, 1);
			}
		}
		for (Entry<String, Integer> entry : wordCount.entrySet()) {
			mapReducer.emitIntermediate(entry.getKey(), entry.getValue());
		}
	}
}
