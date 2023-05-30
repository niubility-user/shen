package com.huawei.hms.common.internal.safeparcel;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import androidx.core.internal.view.SupportMenu;
import com.jingdong.sdk.platform.business.personal.R2;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/* loaded from: classes12.dex */
public class SafeParcelWriter {
    private SafeParcelWriter() {
    }

    private static void a(Parcel parcel, int i2, int i3) {
        if (i3 >= 65535) {
            parcel.writeInt(i2 | SupportMenu.CATEGORY_MASK);
            parcel.writeInt(i3);
            return;
        }
        parcel.writeInt(i2 | (i3 << 16));
    }

    private static void b(Parcel parcel, int i2) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i2 - 4);
        parcel.writeInt(dataPosition - i2);
        parcel.setDataPosition(dataPosition);
    }

    public static int beginObjectHeader(Parcel parcel) {
        return a(parcel, R2.string.permission_sms_default);
    }

    public static void finishObjectHeader(Parcel parcel, int i2) {
        b(parcel, i2);
    }

    public static void writeBigDecimal(Parcel parcel, int i2, BigDecimal bigDecimal, boolean z) {
        if (bigDecimal == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        parcel.writeByteArray(bigDecimal.unscaledValue().toByteArray());
        parcel.writeInt(bigDecimal.scale());
        b(parcel, a);
    }

    public static void writeBigDecimalArray(Parcel parcel, int i2, BigDecimal[] bigDecimalArr, boolean z) {
        if (bigDecimalArr == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int length = bigDecimalArr.length;
        parcel.writeInt(length);
        for (int i3 = 0; i3 < length; i3++) {
            parcel.writeByteArray(bigDecimalArr[i3].unscaledValue().toByteArray());
            parcel.writeInt(bigDecimalArr[i3].scale());
        }
        b(parcel, a);
    }

    public static void writeBigInteger(Parcel parcel, int i2, BigInteger bigInteger, boolean z) {
        if (bigInteger != null) {
            int a = a(parcel, i2);
            parcel.writeByteArray(bigInteger.toByteArray());
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeBigIntegerArray(Parcel parcel, int i2, BigInteger[] bigIntegerArr, boolean z) {
        if (bigIntegerArr == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        parcel.writeInt(bigIntegerArr.length);
        for (BigInteger bigInteger : bigIntegerArr) {
            parcel.writeByteArray(bigInteger.toByteArray());
        }
        b(parcel, a);
    }

    public static void writeBoolean(Parcel parcel, int i2, boolean z) {
        a(parcel, i2, 4);
        if (z) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
    }

    public static void writeBooleanArray(Parcel parcel, int i2, boolean[] zArr, boolean z) {
        if (zArr != null) {
            int a = a(parcel, i2);
            parcel.writeBooleanArray(zArr);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeBooleanList(Parcel parcel, int i2, List<Boolean> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = list.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeInt(list.get(i3).booleanValue() ? 1 : 0);
        }
        b(parcel, a);
    }

    public static void writeBooleanObject(Parcel parcel, int i2, Boolean bool, boolean z) {
        if (bool != null) {
            a(parcel, i2, 4);
            parcel.writeInt(bool.booleanValue() ? 1 : 0);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeBundle(Parcel parcel, int i2, Bundle bundle, boolean z) {
        if (bundle != null) {
            int a = a(parcel, i2);
            parcel.writeBundle(bundle);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeByte(Parcel parcel, int i2, byte b) {
        a(parcel, i2, 4);
        parcel.writeInt(b);
    }

    public static void writeByteArray(Parcel parcel, int i2, byte[] bArr, boolean z) {
        if (bArr != null) {
            int a = a(parcel, i2);
            parcel.writeByteArray(bArr);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeByteArrayArray(Parcel parcel, int i2, byte[][] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        parcel.writeInt(bArr.length);
        for (byte[] bArr2 : bArr) {
            parcel.writeByteArray(bArr2);
        }
        b(parcel, a);
    }

    public static void writeByteArraySparseArray(Parcel parcel, int i2, SparseArray<byte[]> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeInt(sparseArray.keyAt(i3));
            parcel.writeByteArray(sparseArray.valueAt(i3));
        }
        b(parcel, a);
    }

    public static void writeChar(Parcel parcel, int i2, char c2) {
        a(parcel, i2, 4);
        parcel.writeInt(c2);
    }

    public static void writeCharArray(Parcel parcel, int i2, char[] cArr, boolean z) {
        if (cArr != null) {
            int a = a(parcel, i2);
            parcel.writeCharArray(cArr);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeDouble(Parcel parcel, int i2, double d) {
        a(parcel, i2, 8);
        parcel.writeDouble(d);
    }

    public static void writeDoubleArray(Parcel parcel, int i2, double[] dArr, boolean z) {
        if (dArr != null) {
            int a = a(parcel, i2);
            parcel.writeDoubleArray(dArr);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeDoubleList(Parcel parcel, int i2, List<Double> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = list.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeDouble(list.get(i3).doubleValue());
        }
        b(parcel, a);
    }

    public static void writeDoubleObject(Parcel parcel, int i2, Double d, boolean z) {
        if (d != null) {
            a(parcel, i2, 8);
            parcel.writeDouble(d.doubleValue());
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeDoubleSparseArray(Parcel parcel, int i2, SparseArray<Double> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeInt(sparseArray.keyAt(i3));
            parcel.writeDouble(sparseArray.valueAt(i3).doubleValue());
        }
        b(parcel, a);
    }

    public static void writeFloat(Parcel parcel, int i2, float f2) {
        a(parcel, i2, 4);
        parcel.writeFloat(f2);
    }

    public static void writeFloatArray(Parcel parcel, int i2, float[] fArr, boolean z) {
        if (fArr != null) {
            int a = a(parcel, i2);
            parcel.writeFloatArray(fArr);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeFloatList(Parcel parcel, int i2, List<Float> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = list.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeFloat(list.get(i3).floatValue());
        }
        b(parcel, a);
    }

    public static void writeFloatObject(Parcel parcel, int i2, Float f2, boolean z) {
        if (f2 != null) {
            a(parcel, i2, 4);
            parcel.writeFloat(f2.floatValue());
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeFloatSparseArray(Parcel parcel, int i2, SparseArray<Float> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeInt(sparseArray.keyAt(i3));
            parcel.writeFloat(sparseArray.valueAt(i3).floatValue());
        }
        b(parcel, a);
    }

    public static void writeIBinder(Parcel parcel, int i2, IBinder iBinder, boolean z) {
        if (iBinder != null) {
            int a = a(parcel, i2);
            parcel.writeStrongBinder(iBinder);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeIBinderArray(Parcel parcel, int i2, IBinder[] iBinderArr, boolean z) {
        if (iBinderArr != null) {
            int a = a(parcel, i2);
            parcel.writeBinderArray(iBinderArr);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeIBinderList(Parcel parcel, int i2, List<IBinder> list, boolean z) {
        if (list != null) {
            int a = a(parcel, i2);
            parcel.writeBinderList(list);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeIBinderSparseArray(Parcel parcel, int i2, SparseArray<IBinder> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeInt(sparseArray.keyAt(i3));
            parcel.writeStrongBinder(sparseArray.valueAt(i3));
        }
        b(parcel, a);
    }

    public static void writeInt(Parcel parcel, int i2, int i3) {
        a(parcel, i2, 4);
        parcel.writeInt(i3);
    }

    public static void writeIntArray(Parcel parcel, int i2, int[] iArr, boolean z) {
        if (iArr != null) {
            int a = a(parcel, i2);
            parcel.writeIntArray(iArr);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeIntegerList(Parcel parcel, int i2, List<Integer> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = list.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeInt(list.get(i3).intValue());
        }
        b(parcel, a);
    }

    public static void writeIntegerObject(Parcel parcel, int i2, Integer num, boolean z) {
        if (num != null) {
            a(parcel, i2, 4);
            parcel.writeInt(num.intValue());
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeList(Parcel parcel, int i2, List list, boolean z) {
        if (list != null) {
            int a = a(parcel, i2);
            parcel.writeList(list);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeLong(Parcel parcel, int i2, long j2) {
        a(parcel, i2, 8);
        parcel.writeLong(j2);
    }

    public static void writeLongArray(Parcel parcel, int i2, long[] jArr, boolean z) {
        if (jArr != null) {
            int a = a(parcel, i2);
            parcel.writeLongArray(jArr);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeLongList(Parcel parcel, int i2, List<Long> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = list.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeLong(list.get(i3).longValue());
        }
        b(parcel, a);
    }

    public static void writeLongObject(Parcel parcel, int i2, Long l2, boolean z) {
        if (l2 != null) {
            a(parcel, i2, 8);
            parcel.writeLong(l2.longValue());
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeParcel(Parcel parcel, int i2, Parcel parcel2, boolean z) {
        if (parcel2 != null) {
            int a = a(parcel, i2);
            parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeParcelArray(Parcel parcel, int i2, Parcel[] parcelArr, boolean z) {
        if (parcelArr == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int length = parcelArr.length;
        parcel.writeInt(length);
        for (int i3 = 0; i3 < length; i3++) {
            if (parcelArr[i3] == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(parcelArr[i3].dataSize());
                parcel.appendFrom(parcelArr[i3], 0, parcelArr[i3].dataSize());
            }
        }
        b(parcel, a);
    }

    public static void writeParcelList(Parcel parcel, int i2, List<Parcel> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = list.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            Parcel parcel2 = list.get(i3);
            if (parcel2 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(parcel2.dataSize());
                parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            }
        }
        b(parcel, a);
    }

    public static void writeParcelSparseArray(Parcel parcel, int i2, SparseArray<Parcel> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeInt(sparseArray.keyAt(i3));
            Parcel valueAt = sparseArray.valueAt(i3);
            if (valueAt == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(valueAt.dataSize());
                parcel.appendFrom(valueAt, 0, valueAt.dataSize());
            }
        }
        b(parcel, a);
    }

    public static void writeParcelable(Parcel parcel, int i2, Parcelable parcelable, int i3, boolean z) {
        if (parcelable != null) {
            int a = a(parcel, i2);
            parcelable.writeToParcel(parcel, i3);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeShort(Parcel parcel, int i2, short s) {
        a(parcel, i2, 4);
        parcel.writeInt(s);
    }

    public static void writeSparseBooleanArray(Parcel parcel, int i2, SparseBooleanArray sparseBooleanArray, boolean z) {
        if (sparseBooleanArray != null) {
            int a = a(parcel, i2);
            parcel.writeSparseBooleanArray(sparseBooleanArray);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeSparseIntArray(Parcel parcel, int i2, SparseIntArray sparseIntArray, boolean z) {
        if (sparseIntArray == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = sparseIntArray.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeInt(sparseIntArray.keyAt(i3));
            parcel.writeInt(sparseIntArray.valueAt(i3));
        }
        b(parcel, a);
    }

    public static void writeSparseLongArray(Parcel parcel, int i2, SparseLongArray sparseLongArray, boolean z) {
        if (sparseLongArray == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = Build.VERSION.SDK_INT >= 18 ? sparseLongArray.size() : 0;
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = Build.VERSION.SDK_INT;
            if (i4 >= 18) {
                parcel.writeInt(sparseLongArray.keyAt(i3));
            }
            if (i4 >= 18) {
                parcel.writeLong(sparseLongArray.valueAt(i3));
            }
        }
        b(parcel, a);
    }

    public static void writeString(Parcel parcel, int i2, String str, boolean z) {
        if (str != null) {
            int a = a(parcel, i2);
            parcel.writeString(str);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeStringArray(Parcel parcel, int i2, String[] strArr, boolean z) {
        if (strArr != null) {
            int a = a(parcel, i2);
            parcel.writeStringArray(strArr);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeStringList(Parcel parcel, int i2, List<String> list, boolean z) {
        if (list != null) {
            int a = a(parcel, i2);
            parcel.writeStringList(list);
            b(parcel, a);
        } else if (z) {
            a(parcel, i2, 0);
        }
    }

    public static void writeStringSparseArray(Parcel parcel, int i2, SparseArray<String> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeInt(sparseArray.keyAt(i3));
            parcel.writeString(sparseArray.valueAt(i3));
        }
        b(parcel, a);
    }

    public static <P extends Parcelable> void writeTypedArray(Parcel parcel, int i2, P[] pArr, int i3, boolean z) {
        if (pArr == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        parcel.writeInt(a);
        int length = pArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            if (pArr[i4] != null) {
                a(parcel, pArr[i4], i3);
            } else {
                parcel.writeInt(0);
            }
        }
        b(parcel, a);
    }

    public static <T extends Parcelable> void writeTypedList(Parcel parcel, int i2, List<T> list, boolean z) {
        if (list == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = list.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            T t = list.get(i3);
            if (t != null) {
                a(parcel, t, 0);
            } else {
                parcel.writeInt(0);
            }
        }
        b(parcel, a);
    }

    public static <T extends Parcelable> void writeTypedSparseArray(Parcel parcel, int i2, SparseArray<T> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                a(parcel, i2, 0);
                return;
            }
            return;
        }
        int a = a(parcel, i2);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            parcel.writeInt(sparseArray.keyAt(i3));
            T valueAt = sparseArray.valueAt(i3);
            if (valueAt != null) {
                a(parcel, valueAt, 0);
            } else {
                parcel.writeInt(0);
            }
        }
        b(parcel, a);
    }

    private static int a(Parcel parcel, int i2) {
        parcel.writeInt(i2 | SupportMenu.CATEGORY_MASK);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static <P extends Parcelable> void a(Parcel parcel, P p, int i2) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        p.writeToParcel(parcel, i2);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }
}
