package patterns.producerconsumer;

public class ExampleProduct {
	private final String createdBy;

	public ExampleProduct(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}
}
