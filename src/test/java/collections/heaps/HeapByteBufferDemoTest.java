package collections.heaps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;
import java.util.function.Predicate;

import org.junit.Test;

public class HeapByteBufferDemoTest {

    private static final int HEAP_SIZE = 5 * 10000;

	@Test
    public void testDirect() {
        assertTrue(ByteBuffer.allocateDirect(HEAP_SIZE).isDirect());
        assertFalse(ByteBuffer.allocate(HEAP_SIZE).isDirect());
    }

    @Test
    public void testProperties() {
        final ByteBuffer buffer = ByteBuffer.allocate(HEAP_SIZE);

        assertBufferWithPredicate(buffer, (p) -> !p.asCharBuffer().isDirect());
        assertBufferWithPredicate(buffer, (p) -> !p.asIntBuffer().isDirect());
        assertBufferWithPredicate(buffer, (p) -> !p.asFloatBuffer().isDirect());
        assertBufferWithPredicate(buffer, (p) -> !p.duplicate().isDirect());
        assertBufferWithPredicate(buffer, (p) -> !p.slice().isDirect());
        assertBufferWithPredicate(buffer, (p) -> !p.put("I am going to trick this buffer".getBytes()).isDirect());
    }

    private void assertBufferWithPredicate(final ByteBuffer buffer, final Predicate<? super ByteBuffer> predicate) {
        assertTrue(predicate.test(buffer));
    }
}
