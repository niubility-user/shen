package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class VersionedParcel {
    private static final int EX_BAD_PARCELABLE = -2;
    private static final int EX_ILLEGAL_ARGUMENT = -3;
    private static final int EX_ILLEGAL_STATE = -5;
    private static final int EX_NETWORK_MAIN_THREAD = -6;
    private static final int EX_NULL_POINTER = -4;
    private static final int EX_PARCELABLE = -9;
    private static final int EX_SECURITY = -1;
    private static final int EX_UNSUPPORTED_OPERATION = -7;
    private static final String TAG = "VersionedParcel";
    private static final int TYPE_BINDER = 5;
    private static final int TYPE_FLOAT = 8;
    private static final int TYPE_INTEGER = 7;
    private static final int TYPE_PARCELABLE = 2;
    private static final int TYPE_SERIALIZABLE = 3;
    private static final int TYPE_STRING = 4;
    private static final int TYPE_VERSIONED_PARCELABLE = 1;
    protected final ArrayMap<String, Class> mParcelizerCache;
    protected final ArrayMap<String, Method> mReadCache;
    protected final ArrayMap<String, Method> mWriteCache;

    /* loaded from: classes.dex */
    public static class ParcelException extends RuntimeException {
        public ParcelException(Throwable th) {
            super(th);
        }
    }

    public VersionedParcel(ArrayMap<String, Method> arrayMap, ArrayMap<String, Method> arrayMap2, ArrayMap<String, Class> arrayMap3) {
        this.mReadCache = arrayMap;
        this.mWriteCache = arrayMap2;
        this.mParcelizerCache = arrayMap3;
    }

    private Exception createException(int i2, String str) {
        switch (i2) {
            case -9:
                return (Exception) readParcelable();
            case -8:
            default:
                return new RuntimeException("Unknown exception code: " + i2 + " msg " + str);
            case -7:
                return new UnsupportedOperationException(str);
            case -6:
                return new NetworkOnMainThreadException();
            case -5:
                return new IllegalStateException(str);
            case -4:
                return new NullPointerException(str);
            case -3:
                return new IllegalArgumentException(str);
            case -2:
                return new BadParcelableException(str);
            case -1:
                return new SecurityException(str);
        }
    }

    private Class findParcelClass(Class<? extends VersionedParcelable> cls) throws ClassNotFoundException {
        Class cls2 = this.mParcelizerCache.get(cls.getName());
        if (cls2 == null) {
            Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
            this.mParcelizerCache.put(cls.getName(), cls3);
            return cls3;
        }
        return cls2;
    }

    private Method getReadMethod(String str) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method = this.mReadCache.get(str);
        if (method == null) {
            System.currentTimeMillis();
            Method declaredMethod = Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
            this.mReadCache.put(str, declaredMethod);
            return declaredMethod;
        }
        return method;
    }

    @NonNull
    protected static Throwable getRootCause(@NonNull Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    private <T> int getType(T t) {
        if (t instanceof String) {
            return 4;
        }
        if (t instanceof Parcelable) {
            return 2;
        }
        if (t instanceof VersionedParcelable) {
            return 1;
        }
        if (t instanceof Serializable) {
            return 3;
        }
        if (t instanceof IBinder) {
            return 5;
        }
        if (t instanceof Integer) {
            return 7;
        }
        if (t instanceof Float) {
            return 8;
        }
        throw new IllegalArgumentException(t.getClass().getName() + " cannot be VersionedParcelled");
    }

    private Method getWriteMethod(Class cls) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method = this.mWriteCache.get(cls.getName());
        if (method == null) {
            Class findParcelClass = findParcelClass(cls);
            System.currentTimeMillis();
            Method declaredMethod = findParcelClass.getDeclaredMethod("write", cls, VersionedParcel.class);
            this.mWriteCache.put(cls.getName(), declaredMethod);
            return declaredMethod;
        }
        return method;
    }

    private <T, S extends Collection<T>> S readCollection(S s) {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        if (readInt != 0) {
            int readInt2 = readInt();
            if (readInt < 0) {
                return null;
            }
            if (readInt2 == 1) {
                while (readInt > 0) {
                    s.add(readVersionedParcelable());
                    readInt--;
                }
            } else if (readInt2 == 2) {
                while (readInt > 0) {
                    s.add(readParcelable());
                    readInt--;
                }
            } else if (readInt2 == 3) {
                while (readInt > 0) {
                    s.add(readSerializable());
                    readInt--;
                }
            } else if (readInt2 == 4) {
                while (readInt > 0) {
                    s.add(readString());
                    readInt--;
                }
            } else if (readInt2 == 5) {
                while (readInt > 0) {
                    s.add(readStrongBinder());
                    readInt--;
                }
            }
        }
        return s;
    }

    private int readExceptionCode() {
        return readInt();
    }

    private <T> void writeCollection(Collection<T> collection, int i2) {
        setOutputField(i2);
        writeCollection(collection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void writeVersionedParcelableCreator(VersionedParcelable versionedParcelable) {
        try {
            writeString(findParcelClass(versionedParcelable.getClass()).getName());
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(versionedParcelable.getClass().getSimpleName() + " does not have a Parcelizer", e2);
        }
    }

    protected abstract void closeField();

    protected abstract VersionedParcel createSubParcel();

    public boolean isStream() {
        return false;
    }

    public <T> T[] readArray(T[] tArr, int i2) {
        return !readField(i2) ? tArr : (T[]) readArray(tArr);
    }

    protected abstract boolean readBoolean();

    public boolean readBoolean(boolean z, int i2) {
        return !readField(i2) ? z : readBoolean();
    }

    public boolean[] readBooleanArray(boolean[] zArr, int i2) {
        return !readField(i2) ? zArr : readBooleanArray();
    }

    protected abstract Bundle readBundle();

    public Bundle readBundle(Bundle bundle, int i2) {
        return !readField(i2) ? bundle : readBundle();
    }

    public byte readByte(byte b, int i2) {
        return !readField(i2) ? b : (byte) (readInt() & 255);
    }

    protected abstract byte[] readByteArray();

    public byte[] readByteArray(byte[] bArr, int i2) {
        return !readField(i2) ? bArr : readByteArray();
    }

    public char[] readCharArray(char[] cArr, int i2) {
        if (readField(i2)) {
            int readInt = readInt();
            if (readInt < 0) {
                return null;
            }
            char[] cArr2 = new char[readInt];
            for (int i3 = 0; i3 < readInt; i3++) {
                cArr2[i3] = (char) readInt();
            }
            return cArr2;
        }
        return cArr;
    }

    protected abstract CharSequence readCharSequence();

    public CharSequence readCharSequence(CharSequence charSequence, int i2) {
        return !readField(i2) ? charSequence : readCharSequence();
    }

    protected abstract double readDouble();

    public double readDouble(double d, int i2) {
        return !readField(i2) ? d : readDouble();
    }

    public double[] readDoubleArray(double[] dArr, int i2) {
        return !readField(i2) ? dArr : readDoubleArray();
    }

    public Exception readException(Exception exc, int i2) {
        int readExceptionCode;
        return (readField(i2) && (readExceptionCode = readExceptionCode()) != 0) ? readException(readExceptionCode, readString()) : exc;
    }

    protected abstract boolean readField(int i2);

    protected abstract float readFloat();

    public float readFloat(float f2, int i2) {
        return !readField(i2) ? f2 : readFloat();
    }

    public float[] readFloatArray(float[] fArr, int i2) {
        return !readField(i2) ? fArr : readFloatArray();
    }

    protected <T extends VersionedParcelable> T readFromParcel(String str, VersionedParcel versionedParcel) {
        try {
            return (T) getReadMethod(str).invoke(null, versionedParcel);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    protected abstract int readInt();

    public int readInt(int i2, int i3) {
        return !readField(i3) ? i2 : readInt();
    }

    public int[] readIntArray(int[] iArr, int i2) {
        return !readField(i2) ? iArr : readIntArray();
    }

    public <T> List<T> readList(List<T> list, int i2) {
        return !readField(i2) ? list : (List) readCollection(new ArrayList());
    }

    protected abstract long readLong();

    public long readLong(long j2, int i2) {
        return !readField(i2) ? j2 : readLong();
    }

    public long[] readLongArray(long[] jArr, int i2) {
        return !readField(i2) ? jArr : readLongArray();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <K, V> Map<K, V> readMap(Map<K, V> map, int i2) {
        if (readField(i2)) {
            int readInt = readInt();
            if (readInt < 0) {
                return null;
            }
            ArrayMap arrayMap = new ArrayMap();
            if (readInt == 0) {
                return arrayMap;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            readCollection(arrayList);
            readCollection(arrayList2);
            for (int i3 = 0; i3 < readInt; i3++) {
                arrayMap.put(arrayList.get(i3), arrayList2.get(i3));
            }
            return arrayMap;
        }
        return map;
    }

    protected abstract <T extends Parcelable> T readParcelable();

    public <T extends Parcelable> T readParcelable(T t, int i2) {
        return !readField(i2) ? t : (T) readParcelable();
    }

    protected Serializable readSerializable() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        try {
            return (Serializable) new ObjectInputStream(new ByteArrayInputStream(readByteArray())) { // from class: androidx.versionedparcelable.VersionedParcel.1
                @Override // java.io.ObjectInputStream
                protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                    Class<?> cls = Class.forName(objectStreamClass.getName(), false, getClass().getClassLoader());
                    return cls != null ? cls : super.resolveClass(objectStreamClass);
                }
            }.readObject();
        } catch (IOException e2) {
            throw new RuntimeException("VersionedParcelable encountered IOException reading a Serializable object (name = " + readString + ")", e2);
        } catch (ClassNotFoundException e3) {
            throw new RuntimeException("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = " + readString + ")", e3);
        }
    }

    public <T> Set<T> readSet(Set<T> set, int i2) {
        return !readField(i2) ? set : (Set) readCollection(new ArraySet());
    }

    @RequiresApi(api = 21)
    public Size readSize(Size size, int i2) {
        if (readField(i2)) {
            if (readBoolean()) {
                return new Size(readInt(), readInt());
            }
            return null;
        }
        return size;
    }

    @RequiresApi(api = 21)
    public SizeF readSizeF(SizeF sizeF, int i2) {
        if (readField(i2)) {
            if (readBoolean()) {
                return new SizeF(readFloat(), readFloat());
            }
            return null;
        }
        return sizeF;
    }

    public SparseBooleanArray readSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int i2) {
        if (readField(i2)) {
            int readInt = readInt();
            if (readInt < 0) {
                return null;
            }
            SparseBooleanArray sparseBooleanArray2 = new SparseBooleanArray(readInt);
            for (int i3 = 0; i3 < readInt; i3++) {
                sparseBooleanArray2.put(readInt(), readBoolean());
            }
            return sparseBooleanArray2;
        }
        return sparseBooleanArray;
    }

    protected abstract String readString();

    public String readString(String str, int i2) {
        return !readField(i2) ? str : readString();
    }

    protected abstract IBinder readStrongBinder();

    public IBinder readStrongBinder(IBinder iBinder, int i2) {
        return !readField(i2) ? iBinder : readStrongBinder();
    }

    public <T extends VersionedParcelable> T readVersionedParcelable(T t, int i2) {
        return !readField(i2) ? t : (T) readVersionedParcelable();
    }

    protected abstract void setOutputField(int i2);

    public void setSerializationFlags(boolean z, boolean z2) {
    }

    public <T> void writeArray(T[] tArr, int i2) {
        setOutputField(i2);
        writeArray(tArr);
    }

    protected abstract void writeBoolean(boolean z);

    public void writeBoolean(boolean z, int i2) {
        setOutputField(i2);
        writeBoolean(z);
    }

    public void writeBooleanArray(boolean[] zArr, int i2) {
        setOutputField(i2);
        writeBooleanArray(zArr);
    }

    protected abstract void writeBundle(Bundle bundle);

    public void writeBundle(Bundle bundle, int i2) {
        setOutputField(i2);
        writeBundle(bundle);
    }

    public void writeByte(byte b, int i2) {
        setOutputField(i2);
        writeInt(b);
    }

    protected abstract void writeByteArray(byte[] bArr);

    public void writeByteArray(byte[] bArr, int i2) {
        setOutputField(i2);
        writeByteArray(bArr);
    }

    protected abstract void writeByteArray(byte[] bArr, int i2, int i3);

    public void writeCharArray(char[] cArr, int i2) {
        setOutputField(i2);
        if (cArr != null) {
            writeInt(cArr.length);
            for (char c2 : cArr) {
                writeInt(c2);
            }
            return;
        }
        writeInt(-1);
    }

    protected abstract void writeCharSequence(CharSequence charSequence);

    public void writeCharSequence(CharSequence charSequence, int i2) {
        setOutputField(i2);
        writeCharSequence(charSequence);
    }

    protected abstract void writeDouble(double d);

    public void writeDouble(double d, int i2) {
        setOutputField(i2);
        writeDouble(d);
    }

    public void writeDoubleArray(double[] dArr, int i2) {
        setOutputField(i2);
        writeDoubleArray(dArr);
    }

    public void writeException(Exception exc, int i2) {
        setOutputField(i2);
        if (exc == null) {
            writeNoException();
            return;
        }
        int i3 = 0;
        if ((exc instanceof Parcelable) && exc.getClass().getClassLoader() == Parcelable.class.getClassLoader()) {
            i3 = -9;
        } else if (exc instanceof SecurityException) {
            i3 = -1;
        } else if (exc instanceof BadParcelableException) {
            i3 = -2;
        } else if (exc instanceof IllegalArgumentException) {
            i3 = -3;
        } else if (exc instanceof NullPointerException) {
            i3 = -4;
        } else if (exc instanceof IllegalStateException) {
            i3 = -5;
        } else if (exc instanceof NetworkOnMainThreadException) {
            i3 = -6;
        } else if (exc instanceof UnsupportedOperationException) {
            i3 = -7;
        }
        writeInt(i3);
        if (i3 == 0) {
            if (exc instanceof RuntimeException) {
                throw ((RuntimeException) exc);
            }
            throw new RuntimeException(exc);
        }
        writeString(exc.getMessage());
        if (i3 != -9) {
            return;
        }
        writeParcelable((Parcelable) exc);
    }

    protected abstract void writeFloat(float f2);

    public void writeFloat(float f2, int i2) {
        setOutputField(i2);
        writeFloat(f2);
    }

    public void writeFloatArray(float[] fArr, int i2) {
        setOutputField(i2);
        writeFloatArray(fArr);
    }

    protected abstract void writeInt(int i2);

    public void writeInt(int i2, int i3) {
        setOutputField(i3);
        writeInt(i2);
    }

    public void writeIntArray(int[] iArr, int i2) {
        setOutputField(i2);
        writeIntArray(iArr);
    }

    public <T> void writeList(List<T> list, int i2) {
        writeCollection(list, i2);
    }

    protected abstract void writeLong(long j2);

    public void writeLong(long j2, int i2) {
        setOutputField(i2);
        writeLong(j2);
    }

    public void writeLongArray(long[] jArr, int i2) {
        setOutputField(i2);
        writeLongArray(jArr);
    }

    public <K, V> void writeMap(Map<K, V> map, int i2) {
        setOutputField(i2);
        if (map == null) {
            writeInt(-1);
            return;
        }
        int size = map.size();
        writeInt(size);
        if (size == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        writeCollection(arrayList);
        writeCollection(arrayList2);
    }

    protected void writeNoException() {
        writeInt(0);
    }

    protected abstract void writeParcelable(Parcelable parcelable);

    public void writeParcelable(Parcelable parcelable, int i2) {
        setOutputField(i2);
        writeParcelable(parcelable);
    }

    public void writeSerializable(Serializable serializable, int i2) {
        setOutputField(i2);
        writeSerializable(serializable);
    }

    public <T> void writeSet(Set<T> set, int i2) {
        writeCollection(set, i2);
    }

    @RequiresApi(api = 21)
    public void writeSize(Size size, int i2) {
        setOutputField(i2);
        writeBoolean(size != null);
        if (size != null) {
            writeInt(size.getWidth());
            writeInt(size.getHeight());
        }
    }

    @RequiresApi(api = 21)
    public void writeSizeF(SizeF sizeF, int i2) {
        setOutputField(i2);
        writeBoolean(sizeF != null);
        if (sizeF != null) {
            writeFloat(sizeF.getWidth());
            writeFloat(sizeF.getHeight());
        }
    }

    public void writeSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int i2) {
        setOutputField(i2);
        if (sparseBooleanArray == null) {
            writeInt(-1);
            return;
        }
        int size = sparseBooleanArray.size();
        writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            writeInt(sparseBooleanArray.keyAt(i3));
            writeBoolean(sparseBooleanArray.valueAt(i3));
        }
    }

    protected abstract void writeString(String str);

    public void writeString(String str, int i2) {
        setOutputField(i2);
        writeString(str);
    }

    protected abstract void writeStrongBinder(IBinder iBinder);

    public void writeStrongBinder(IBinder iBinder, int i2) {
        setOutputField(i2);
        writeStrongBinder(iBinder);
    }

    protected abstract void writeStrongInterface(IInterface iInterface);

    public void writeStrongInterface(IInterface iInterface, int i2) {
        setOutputField(i2);
        writeStrongInterface(iInterface);
    }

    protected <T extends VersionedParcelable> void writeToParcel(T t, VersionedParcel versionedParcel) {
        try {
            getWriteMethod(t.getClass()).invoke(null, t, versionedParcel);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    public void writeVersionedParcelable(VersionedParcelable versionedParcelable, int i2) {
        setOutputField(i2);
        writeVersionedParcelable(versionedParcelable);
    }

    private <T> void writeCollection(Collection<T> collection) {
        if (collection == null) {
            writeInt(-1);
            return;
        }
        int size = collection.size();
        writeInt(size);
        if (size > 0) {
            int type = getType(collection.iterator().next());
            writeInt(type);
            switch (type) {
                case 1:
                    Iterator<T> it = collection.iterator();
                    while (it.hasNext()) {
                        writeVersionedParcelable((VersionedParcelable) it.next());
                    }
                    return;
                case 2:
                    Iterator<T> it2 = collection.iterator();
                    while (it2.hasNext()) {
                        writeParcelable((Parcelable) it2.next());
                    }
                    return;
                case 3:
                    Iterator<T> it3 = collection.iterator();
                    while (it3.hasNext()) {
                        writeSerializable((Serializable) it3.next());
                    }
                    return;
                case 4:
                    Iterator<T> it4 = collection.iterator();
                    while (it4.hasNext()) {
                        writeString((String) it4.next());
                    }
                    return;
                case 5:
                    Iterator<T> it5 = collection.iterator();
                    while (it5.hasNext()) {
                        writeStrongBinder((IBinder) it5.next());
                    }
                    return;
                case 6:
                default:
                    return;
                case 7:
                    Iterator<T> it6 = collection.iterator();
                    while (it6.hasNext()) {
                        writeInt(((Integer) it6.next()).intValue());
                    }
                    return;
                case 8:
                    Iterator<T> it7 = collection.iterator();
                    while (it7.hasNext()) {
                        writeFloat(((Float) it7.next()).floatValue());
                    }
                    return;
            }
        }
    }

    private void writeSerializable(Serializable serializable) {
        if (serializable == null) {
            writeString(null);
            return;
        }
        String name = serializable.getClass().getName();
        writeString(name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            writeByteArray(byteArrayOutputStream.toByteArray());
        } catch (IOException e2) {
            throw new RuntimeException("VersionedParcelable encountered IOException writing serializable object (name = " + name + ")", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T[] readArray(T[] tArr) {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(readInt);
        if (readInt != 0) {
            int readInt2 = readInt();
            if (readInt < 0) {
                return null;
            }
            if (readInt2 == 1) {
                while (readInt > 0) {
                    arrayList.add(readVersionedParcelable());
                    readInt--;
                }
            } else if (readInt2 == 2) {
                while (readInt > 0) {
                    arrayList.add(readParcelable());
                    readInt--;
                }
            } else if (readInt2 == 3) {
                while (readInt > 0) {
                    arrayList.add(readSerializable());
                    readInt--;
                }
            } else if (readInt2 == 4) {
                while (readInt > 0) {
                    arrayList.add(readString());
                    readInt--;
                }
            } else if (readInt2 == 5) {
                while (readInt > 0) {
                    arrayList.add(readStrongBinder());
                    readInt--;
                }
            }
        }
        return (T[]) arrayList.toArray(tArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean[] readBooleanArray() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        boolean[] zArr = new boolean[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            zArr[i2] = readInt() != 0;
        }
        return zArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double[] readDoubleArray() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        double[] dArr = new double[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            dArr[i2] = readDouble();
        }
        return dArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float[] readFloatArray() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        float[] fArr = new float[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            fArr[i2] = readFloat();
        }
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] readIntArray() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        int[] iArr = new int[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            iArr[i2] = readInt();
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long[] readLongArray() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        long[] jArr = new long[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            jArr[i2] = readLong();
        }
        return jArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends VersionedParcelable> T readVersionedParcelable() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        return (T) readFromParcel(readString, createSubParcel());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> void writeArray(T[] tArr) {
        if (tArr == null) {
            writeInt(-1);
            return;
        }
        int length = tArr.length;
        writeInt(length);
        if (length > 0) {
            int i2 = 0;
            int type = getType(tArr[0]);
            writeInt(type);
            if (type == 1) {
                while (i2 < length) {
                    writeVersionedParcelable((VersionedParcelable) tArr[i2]);
                    i2++;
                }
            } else if (type == 2) {
                while (i2 < length) {
                    writeParcelable((Parcelable) tArr[i2]);
                    i2++;
                }
            } else if (type == 3) {
                while (i2 < length) {
                    writeSerializable((Serializable) tArr[i2]);
                    i2++;
                }
            } else if (type == 4) {
                while (i2 < length) {
                    writeString((String) tArr[i2]);
                    i2++;
                }
            } else if (type != 5) {
            } else {
                while (i2 < length) {
                    writeStrongBinder((IBinder) tArr[i2]);
                    i2++;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeBooleanArray(boolean[] zArr) {
        if (zArr != null) {
            writeInt(zArr.length);
            for (boolean z : zArr) {
                writeInt(z ? 1 : 0);
            }
            return;
        }
        writeInt(-1);
    }

    public void writeByteArray(byte[] bArr, int i2, int i3, int i4) {
        setOutputField(i4);
        writeByteArray(bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeDoubleArray(double[] dArr) {
        if (dArr != null) {
            writeInt(dArr.length);
            for (double d : dArr) {
                writeDouble(d);
            }
            return;
        }
        writeInt(-1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeFloatArray(float[] fArr) {
        if (fArr != null) {
            writeInt(fArr.length);
            for (float f2 : fArr) {
                writeFloat(f2);
            }
            return;
        }
        writeInt(-1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeIntArray(int[] iArr) {
        if (iArr != null) {
            writeInt(iArr.length);
            for (int i2 : iArr) {
                writeInt(i2);
            }
            return;
        }
        writeInt(-1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeLongArray(long[] jArr) {
        if (jArr != null) {
            writeInt(jArr.length);
            for (long j2 : jArr) {
                writeLong(j2);
            }
            return;
        }
        writeInt(-1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeVersionedParcelable(VersionedParcelable versionedParcelable) {
        if (versionedParcelable == null) {
            writeString(null);
            return;
        }
        writeVersionedParcelableCreator(versionedParcelable);
        VersionedParcel createSubParcel = createSubParcel();
        writeToParcel(versionedParcelable, createSubParcel);
        createSubParcel.closeField();
    }

    private Exception readException(int i2, String str) {
        return createException(i2, str);
    }
}
