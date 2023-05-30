package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

/* loaded from: classes12.dex */
public class UnknownDescriptor extends BaseDescriptor {
    private static Logger log = Logger.getLogger(UnknownDescriptor.class.getName());
    private ByteBuffer data;

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    int getContentSize() {
        throw new RuntimeException("sdjlhfl");
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer byteBuffer) throws IOException {
        this.data = byteBuffer.slice();
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public ByteBuffer serialize() {
        throw new RuntimeException("sdjlhfl");
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public String toString() {
        return "UnknownDescriptor{tag=" + this.tag + ", sizeOfInstance=" + this.sizeOfInstance + ", data=" + this.data + '}';
    }
}
