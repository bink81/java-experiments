package math;

/**
 * An exception thrown when a triangle inequality rule is broken, which states:
 * the length of a side of a triangle is less than the sum of the lengths of the
 * other two sides and greater than the difference of the lengths of the other
 * two sides.
 * 
 * @author Robert
 */
public class TriangleUnequalException extends Exception {
	private static final long serialVersionUID = 1L;

	public TriangleUnequalException(String message) {
		super(message);
	}
}
