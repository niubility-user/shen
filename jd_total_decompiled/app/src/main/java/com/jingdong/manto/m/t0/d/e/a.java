package com.jingdong.manto.m.t0.d.e;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanRecord;
import android.os.Build;
import android.os.ParcelUuid;
import android.util.ArrayMap;
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
    @TargetApi(19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static a a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int i2 = 0;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        SparseArray sparseArray = new SparseArray();
        ArrayMap arrayMap = new ArrayMap();
        String str = null;
        int i3 = -1;
        byte b = -2147483648;
        while (i2 < bArr.length) {
            try {
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                if (i5 == 0) {
                    return new a(!arrayList.isEmpty() ? null : arrayList, arrayList2, sparseArray, arrayMap, i3, b, str, bArr);
                }
                int i6 = i5 - 1;
                int i7 = i4 + 1;
                int i8 = bArr[i4] & 255;
                if (i8 != 255) {
                    int i9 = 16;
                    switch (i8) {
                        case 1:
                            i3 = bArr[i7] & 255;
                            continue;
                        case 2:
                        case 3:
                            b(bArr, i7, i6, 2, arrayList);
                            continue;
                        case 4:
                        case 5:
                            b(bArr, i7, i6, 4, arrayList);
                            continue;
                        case 6:
                        case 7:
                            b(bArr, i7, i6, 16, arrayList);
                            continue;
                        case 8:
                        case 9:
                            str = new String(a(bArr, i7, i6));
                            continue;
                        case 10:
                            b = bArr[i7];
                            continue;
                        default:
                            switch (i8) {
                                case 20:
                                    a(bArr, i7, i6, 2, arrayList2);
                                    break;
                                case 21:
                                    a(bArr, i7, i6, 16, arrayList2);
                                    break;
                                default:
                                    switch (i8) {
                                        case 31:
                                            a(bArr, i7, i6, 4, arrayList2);
                                            break;
                                        case 32:
                                        case 33:
                                            break;
                                        default:
                                            continue;
                                    }
                                case 22:
                                    if (i8 == 32) {
                                        i9 = 4;
                                    } else if (i8 != 33) {
                                        i9 = 2;
                                    }
                                    arrayMap.put(com.jingdong.manto.m.t0.d.d.f.a(a(bArr, i7, i9)), a(bArr, i7 + i9, i6 - i9));
                                    break;
                            }
                    }
                } else {
                    sparseArray.put(((bArr[i7 + 1] & 255) << 8) + (255 & bArr[i7]), a(bArr, i7 + 2, i6 - 2));
                }
                i2 = i6 + i7;
            } catch (Throwable unused) {
                String str2 = "unable to parse scan record: " + Arrays.toString(bArr);
                return new a(null, null, null, null, -1, Integer.MIN_VALUE, null, bArr);
            }
        }
        return new a(!arrayList.isEmpty() ? null : arrayList, arrayList2, sparseArray, arrayMap, i3, b, str, bArr);
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
