package patterns.document;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;

import org.junit.Assert;
import org.junit.Test;

public class CarTest {

	private static final double DELTA = 0.000001;
	private static final double PRICE = 100.0;
	private static final String MODEL = "Audi";
	private static final String COLOR = "red";
	private static final int WHEELS_COUNT = 4;

	@Test
	public void testCreateCar() throws Exception {
		Map<String, Object> entries = new HashMap<>();
		Car car = new Car(entries);

		car.put(ColorTrait.KEY, COLOR);
		car.put(ModelTrait.KEY, MODEL);
		car.put(PriceTrait.KEY, PRICE);
		car.put(WheelsTrait.KEY, WHEELS_COUNT);

		String color = car.getColor();
		Assert.assertEquals(COLOR, color);
		String model = car.getModel();
		Assert.assertEquals(MODEL, model);
		OptionalDouble price = car.getPrice();
		Assert.assertEquals(PRICE, price.getAsDouble(), DELTA);
		OptionalInt wheels = car.getWheels();
		Assert.assertEquals(WHEELS_COUNT, wheels.getAsInt());
	}
}
