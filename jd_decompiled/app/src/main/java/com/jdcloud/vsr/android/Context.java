package com.jdcloud.vsr.android;

import com.jdcloud.vsr.JDTContext;

/* loaded from: classes18.dex */
public class Context extends JDTContext {
    public Context(int i2) {
        super(newContext(i2));
    }

    private static native long newContext(int i2);

    @Override // com.jdcloud.vsr.JDTContext, com.jdcloud.vsr.JDTObject
    public synchronized void dispose() {
        super.dispose();
    }
}
