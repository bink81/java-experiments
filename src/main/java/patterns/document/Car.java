package patterns.document;

import java.util.Map;

public final class Car extends BaseDocument
		implements ColorTrait, ModelTrait, PriceTrait, WheelsTrait {

	public Car(Map<String, Object> entries) {
		super(entries);
	}
}