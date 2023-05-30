package com.tencent.mapsdk.engine.jni;

import androidx.annotation.Keep;
import java.util.Arrays;

@Keep
/* loaded from: classes9.dex */
public class JNIEvent {
    public byte[] data;
    public Object extra;
    public int id;
    public String name;

    public String toString() {
        return "JNIEvent{id=" + this.id + ", name='" + this.name + "', data=" + Arrays.toString(this.data) + ", extra=" + this.extra + '}';
    }
}
