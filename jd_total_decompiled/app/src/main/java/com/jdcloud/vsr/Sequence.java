package com.jdcloud.vsr;

/* loaded from: classes18.dex */
public class Sequence extends JDTObject {
    protected Sequence(long j2) {
        super(j2);
    }

    private native long copy(long j2, int i2, int i3);

    private native void insert(long j2, long j3, int i2);

    private native void remove(long j2, int i2, int i3);

    private native void shrink(long j2, int i2, int i3);

    public Sequence copy(int i2, int i3) {
        return new Sequence(copy(this.handle, i2, i3));
    }

    public void insert(Sequence sequence, int i2) {
        insert(this.handle, sequence.handle, i2);
    }

    public void remove(int i2, int i3) {
        remove(this.handle, i2, i3);
    }
}
