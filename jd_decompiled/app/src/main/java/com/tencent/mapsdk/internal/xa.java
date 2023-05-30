package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public class xa {

    /* renamed from: e  reason: collision with root package name */
    public static final int f17455e = 1;

    /* renamed from: f  reason: collision with root package name */
    public static final int f17456f = 0;
    public final Rect a = new Rect();
    public int[] b;

    /* renamed from: c  reason: collision with root package name */
    public int[] f17457c;
    public int[] d;

    public static xa a(byte[] bArr) {
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.nativeOrder());
        if (order.get() == 0) {
            return null;
        }
        xa xaVar = new xa();
        xaVar.b = new int[order.get()];
        xaVar.f17457c = new int[order.get()];
        xaVar.d = new int[order.get()];
        a(xaVar.b.length);
        a(xaVar.f17457c.length);
        order.getInt();
        order.getInt();
        xaVar.a.left = order.getInt();
        xaVar.a.right = order.getInt();
        xaVar.a.top = order.getInt();
        xaVar.a.bottom = order.getInt();
        order.getInt();
        a(xaVar.b, order);
        a(xaVar.f17457c, order);
        a(xaVar.d, order);
        return xaVar;
    }

    private static void a(int i2) {
        if (i2 == 0 || (i2 & 1) != 0) {
            throw new RuntimeException("invalid nine-patch: " + i2);
        }
    }

    private static void a(int[] iArr, ByteBuffer byteBuffer) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = byteBuffer.getInt();
        }
    }
}
