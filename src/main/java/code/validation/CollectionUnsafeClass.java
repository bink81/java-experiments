package code.validation;

// This class in not safe to use in collections and the code analysis should detect it
@CollectionSafe
public final class CollectionUnsafeClass {
	private final Long id;

	public CollectionUnsafeClass(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
