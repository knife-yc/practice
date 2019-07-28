package com.yc.practice.test.nio;

import java.nio.ByteBuffer;

public class Buffers {
    ByteBuffer readBuffer;
    ByteBuffer writeBuffer;

    public Buffers(int readCapacity, int writeCapacity) {
        readBuffer = ByteBuffer.allocate(readCapacity);
        writeBuffer = ByteBuffer.allocate(writeCapacity);
    }

    public ByteBuffer getReadBuffer() {
        return readBuffer;
    }

    public ByteBuffer gerWriteBuffer() {
        return writeBuffer;
    }
}
