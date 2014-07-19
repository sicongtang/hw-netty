package io.netty4.example.discard;

import io.netty.util.internal.PlatformDependent;

import java.nio.ByteBuffer;

import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

public class FreeDirectBufferTest {
	
	
	static void freeDirectBuffer(ByteBuffer buffer) {
        if (!(buffer instanceof DirectBuffer)) {
            return;
        }
        try {
            Cleaner cleaner = ((DirectBuffer) buffer).cleaner();
            if (cleaner == null) {
                throw new IllegalArgumentException(
                        "attempted to deallocate the buffer which was allocated via JNIEnv->NewDirectByteBuffer()");
            }
            cleaner.clean();
        } catch (Throwable t) {
            // Nothing we can do here.
        }
    }
	
	public static void main(String[] args) {
		System.out.println(PlatformDependent.hasUnsafe());
	}
}
