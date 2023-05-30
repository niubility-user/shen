package com.jingdong.manto.m.t0.d.e;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanRecord;
import android.os.Build;
import android.os.ParcelUuid;
import android.util.SparseArray;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public final class a {
    private final int a;
    public final List<ParcelUuid> b;

    /* renamed from: c  reason: collision with root package name */
    public final SparseArray<byte[]> f13691c;
    public final Map<ParcelUuid, byte[]> d;

    /* renamed from: e  reason: collision with root package name */
    private final int f13692e;

    /* renamed from: f  reason: collision with root package name */
    final byte[] f13693f;

    /* renamed from: g  reason: collision with root package name */
    public final String f13694g;

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(21)
    public a(ScanRecord scanRecord) {
        this.b = scanRecord.getServiceUuids();
        if (Build.VERSION.SDK_INT >= 29) {
            scanRecord.getServiceSolicitationUuids();
        } else {
            new ArrayList();
        }
        this.f13691c = scanRecord.getManufacturerSpecificData();
        this.d = scanRecord.getServiceData();
        this.f13694g = scanRecord.getDeviceName();
        this.a = scanRecord.getAdvertiseFlags();
        this.f13692e = scanRecord.getTxPowerLevel();
        this.f13693f = scanRecord.getBytes();
    }

    private a(List<ParcelUuid> list, List<ParcelUuid> list2, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i2, int i3, String str, byte[] bArr) {
        this.b = list;
        this.f13691c = sparseArray;
        this.d = map;
        this.f13694g = str;
        this.a = i2;
        this.f13692e = i3;
        this.f13693f = bArr;
    }

    private static int a(byte[] bArr, int i2, int i3, int i4, List<ParcelUuid> list) {
        while (i3 > 0) {
            list.add(com.jingdong.manto.m.t0.d.d.f.a(a(bArr, i2, i4)));
            i3 -= i4;
            i2 += i4;
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b0  */
    @android.annotation.TargetApi(19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.manto.m.t0.d.e.a a(byte[] r15) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.t0.d.e.a.a(byte[]):com.jingdong.manto.m.t0.d.e.a");
    }

    private static String a(SparseArray<byte[]> sparseArray) {
        if (sparseArray == null) {
            return DYConstants.DY_NULL_STR;
        }
        if (sparseArray.size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            sb.append(sparseArray.keyAt(i2));
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(Arrays.toString(sparseArray.valueAt(i2)));
        }
        sb.append('}');
        return sb.toString();
    }

    private static <T> String a(Map<T, byte[]> map) {
        if (map == null) {
            return DYConstants.DY_NULL_STR;
        }
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        Iterator<Map.Entry<T, byte[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            T key = it.next().getKey();
            sb.append(key);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(Arrays.toString(map.get(key)));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    private static byte[] a(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return bArr2;
    }

    private static int b(byte[] bArr, int i2, int i3, int i4, List<ParcelUuid> list) {
        while (i3 > 0) {
            list.add(com.jingdong.manto.m.t0.d.d.f.a(a(bArr, i2, i4)));
            i3 -= i4;
            i2 += i4;
        }
        return i2;
    }

    public final String toString() {
        return "ScanRecord [mAdvertiseFlags=" + this.a + ", mServiceUuids=" + this.b + ", mManufacturerSpecificData=" + a(this.f13691c) + ", mServiceData=" + a(this.d) + ", mTxPowerLevel=" + this.f13692e + ", mDeviceName=" + this.f13694g + "]";
    }
}
