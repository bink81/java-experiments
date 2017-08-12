package collections.heaps;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class HeapByteBufferDemo {

	private static final String STR = "AAAA";

	public static void main(String[] args) {
		final ByteBuffer buffer = ByteBuffer.allocate(100);
		buffer.put(STR.getBytes());
		System.out.println(buffer.toString());
		
		ByteBuffer duplicatedBuffer = buffer.duplicate();
		duplicatedBuffer.put(STR.getBytes());
		System.out.println(duplicatedBuffer.toString());
		
        ByteBuffer slicedBuffer = buffer.slice();
		slicedBuffer.put(STR.getBytes());
		System.out.println(slicedBuffer.toString());
		
        
        IntBuffer intBuffer = buffer.asIntBuffer();
		intBuffer.put(1);
		intBuffer.put(2);
		System.out.println(intBuffer.toString());
		
        CharBuffer charBuffer = buffer.asCharBuffer();
		charBuffer.put('c');
		System.out.println(charBuffer.toString());
		
        FloatBuffer floatBuffer = buffer.asFloatBuffer();
		floatBuffer.put(1f);
		floatBuffer.put(2f);
		System.out.println(floatBuffer.toString());
        
        System.out.println(buffer.toString());
	}
}
