package com.eclipsesource.v8.debug.mirror;

import com.eclipsesource.v8.V8Object;
import com.jd.dynamic.DYConstants;

/* loaded from: classes.dex */
public class NullMirror extends ValueMirror {
    /* JADX INFO: Access modifiers changed from: package-private */
    public NullMirror(V8Object v8Object) {
        super(v8Object);
    }

    @Override // com.eclipsesource.v8.debug.mirror.Mirror
    public boolean isNull() {
        return true;
    }

    @Override // com.eclipsesource.v8.debug.mirror.Mirror
    public String toString() {
        return DYConstants.DY_NULL_STR;
    }
}
