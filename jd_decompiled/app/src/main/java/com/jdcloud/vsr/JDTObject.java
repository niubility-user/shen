package com.jdcloud.vsr;

/* loaded from: classes18.dex */
public class JDTObject {
    protected long handle;

    /* JADX INFO: Access modifiers changed from: protected */
    public JDTObject(long j2) {
        this.handle = j2;
    }

    private native void disposeNative();

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void dispose() {
        disposeNative();
    }

    public boolean equals(Object obj) {
        return (obj instanceof JDTObject) && this.handle == ((JDTObject) obj).handle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
    }
}
