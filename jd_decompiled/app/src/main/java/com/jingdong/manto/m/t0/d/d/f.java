package com.jingdong.manto.m.t0.d.d;

import android.os.ParcelUuid;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

/* loaded from: classes15.dex */
public class f {
    public static final ParcelUuid a = ParcelUuid.fromString("0000110B-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid b = ParcelUuid.fromString("0000110A-0000-1000-8000-00805F9B34FB");

    /* renamed from: c  reason: collision with root package name */
    public static final ParcelUuid f13675c = ParcelUuid.fromString("0000110D-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid d = ParcelUuid.fromString("00001108-0000-1000-8000-00805F9B34FB");

    /* renamed from: e  reason: collision with root package name */
    public static final ParcelUuid f13676e = ParcelUuid.fromString("0000111E-0000-1000-8000-00805F9B34FB");

    /* renamed from: f  reason: collision with root package name */
    public static final ParcelUuid f13677f;

    /* renamed from: g  reason: collision with root package name */
    public static final ParcelUuid f13678g;

    /* renamed from: h  reason: collision with root package name */
    public static final ParcelUuid f13679h;

    /* renamed from: i  reason: collision with root package name */
    public static final ParcelUuid f13680i;

    /* renamed from: j  reason: collision with root package name */
    public static final ParcelUuid f13681j;

    /* renamed from: k  reason: collision with root package name */
    public static final ParcelUuid f13682k;

    /* renamed from: l  reason: collision with root package name */
    public static final ParcelUuid f13683l;

    /* renamed from: m  reason: collision with root package name */
    public static final ParcelUuid f13684m;

    /* renamed from: n  reason: collision with root package name */
    public static final ParcelUuid f13685n;

    static {
        ParcelUuid.fromString("0000111F-0000-1000-8000-00805F9B34FB");
        f13677f = ParcelUuid.fromString("0000110E-0000-1000-8000-00805F9B34FB");
        f13678g = ParcelUuid.fromString("0000110C-0000-1000-8000-00805F9B34FB");
        f13679h = ParcelUuid.fromString("00001105-0000-1000-8000-00805f9b34fb");
        ParcelUuid.fromString("00001124-0000-1000-8000-00805f9b34fb");
        ParcelUuid.fromString("00001812-0000-1000-8000-00805f9b34fb");
        f13680i = ParcelUuid.fromString("00001115-0000-1000-8000-00805F9B34FB");
        f13681j = ParcelUuid.fromString("00001116-0000-1000-8000-00805F9B34FB");
        ParcelUuid.fromString("0000000f-0000-1000-8000-00805F9B34FB");
        ParcelUuid.fromString("0000112e-0000-1000-8000-00805F9B34FB");
        ParcelUuid.fromString("0000112f-0000-1000-8000-00805F9B34FB");
        f13682k = ParcelUuid.fromString("00001134-0000-1000-8000-00805F9B34FB");
        f13683l = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");
        f13684m = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");
        ParcelUuid.fromString("0000112D-0000-1000-8000-00805F9B34FB");
        ParcelUuid.fromString("0000FDF0-0000-1000-8000-00805f9b34fb");
        f13685n = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    }

    public static ParcelUuid a(byte[] bArr) {
        long j2;
        if (bArr != null) {
            int length = bArr.length;
            if (length != 2 && length != 4 && length != 16) {
                throw new IllegalArgumentException("uuidBytes length invalid - " + length);
            } else if (length == 16) {
                ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
                return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
            } else {
                if (length == 2) {
                    j2 = (bArr[0] & 255) + ((bArr[1] & 255) << 8);
                } else {
                    j2 = ((bArr[3] & 255) << 24) + (bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16);
                }
                ParcelUuid parcelUuid = f13685n;
                return new ParcelUuid(new UUID(parcelUuid.getUuid().getMostSignificantBits() + (j2 << 32), parcelUuid.getUuid().getLeastSignificantBits()));
            }
        }
        throw new IllegalArgumentException("uuidBytes cannot be null");
    }
}
