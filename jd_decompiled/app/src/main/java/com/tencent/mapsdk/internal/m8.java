package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class m8 implements x8<Object> {
    @Override // com.tencent.mapsdk.internal.x8
    public Object a(float f2, Object obj, Object obj2) {
        int intValue = ((Integer) obj).intValue();
        int i2 = intValue >> 24;
        int i3 = (intValue >> 16) & 255;
        int i4 = (intValue >> 8) & 255;
        int i5 = intValue & 255;
        int intValue2 = ((Integer) obj2).intValue();
        return Integer.valueOf(((i2 + ((int) (((intValue2 >> 24) - i2) * f2))) << 24) | ((i3 + ((int) ((((intValue2 >> 16) & 255) - i3) * f2))) << 16) | ((i4 + ((int) ((((intValue2 >> 8) & 255) - i4) * f2))) << 8) | (i5 + ((int) (f2 * ((intValue2 & 255) - i5)))));
    }
}
