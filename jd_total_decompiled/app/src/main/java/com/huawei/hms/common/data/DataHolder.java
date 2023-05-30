package com.huawei.hms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.common.internal.safeparcel.AbstractSafeParcelable;
import com.huawei.hms.common.internal.safeparcel.SafeParcelWriter;
import com.huawei.hms.common.sqlite.HMSCursorWrapper;
import com.huawei.hms.support.log.HMSLog;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    private static final String TAG = "DataHolder";
    public static final String TYPE_BOOLEAN = "type_boolean";
    public static final String TYPE_BYTE_ARRAY = "type_byte_array";
    public static final String TYPE_DOUBLE = "type_double";
    public static final String TYPE_FLOAT = "type_float";
    public static final String TYPE_INT = "type_int";
    public static final String TYPE_LONG = "type_long";
    public static final String TYPE_STRING = "type_string";
    private String[] columns;
    private Bundle columnsBundle;
    private CursorWindow[] cursorWindows;
    private int dataCount;
    private boolean isInstance;
    private boolean mClosed;
    private Bundle metadata;
    private int[] perCursorCounts;
    private int statusCode;
    private int version;
    public static final Parcelable.Creator<DataHolder> CREATOR = new DataHolderCreator();
    private static final Builder BUILDER = new DataHolderBuilderCreator(new String[0], null);

    /* loaded from: classes12.dex */
    public static class Builder {
        private String[] a;
        private final ArrayList<HashMap<String, Object>> b;

        /* renamed from: c  reason: collision with root package name */
        private final String f1252c;
        private final HashMap<Object, Integer> d;

        /* JADX WARN: Type inference failed for: r1v0, types: [android.os.Bundle, com.huawei.hms.common.data.DataHolder$1] */
        public DataHolder build(int i2) {
            ?? r1 = 0;
            return new DataHolder(this, i2, (Bundle) r1);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:13:0x003f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder setDataForContentValuesHashMap(HashMap<String, Object> hashMap) {
            int i2;
            Object obj;
            Preconditions.checkNotNull(hashMap, "contentValuesHashMap cannot be null");
            String str = this.f1252c;
            boolean z = false;
            if (str != null && (obj = hashMap.get(str)) != null) {
                Integer num = this.d.get(obj);
                if (num != null) {
                    i2 = num.intValue();
                    z = true;
                    if (!z) {
                        this.b.remove(i2);
                        this.b.add(i2, hashMap);
                    } else {
                        this.b.add(hashMap);
                    }
                    return this;
                }
                this.d.put(obj, Integer.valueOf(this.b.size()));
            }
            i2 = 0;
            if (!z) {
            }
            return this;
        }

        public Builder withRow(ContentValues contentValues) {
            Preconditions.checkNotNull(contentValues, "contentValues cannot be null");
            HashMap<String, Object> hashMap = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            return setDataForContentValuesHashMap(hashMap);
        }

        private Builder(String[] strArr, String str) {
            Preconditions.checkNotNull(strArr, "builderColumnsP cannot be null");
            this.a = strArr;
            this.b = new ArrayList<>();
            this.f1252c = str;
            this.d = new HashMap<>();
        }

        public DataHolder build(int i2, Bundle bundle) {
            return new DataHolder(this, i2, bundle, -1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(String[] strArr, String str, DataHolderBuilderCreator dataHolderBuilderCreator) {
            this(strArr, null);
        }
    }

    /* loaded from: classes12.dex */
    public static class DataHolderException extends RuntimeException {
        public DataHolderException(String str) {
            super(str);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.huawei.hms.common.data.DataHolder$1, java.lang.String] */
    public static Builder builder(String[] strArr) {
        ?? r1 = 0;
        return new Builder(strArr, (String) r1);
    }

    private void checkAvailable(String str, int i2) {
        String str2;
        Bundle bundle = this.columnsBundle;
        if (bundle != null && bundle.containsKey(str)) {
            if (isClosed()) {
                str2 = "buffer has been closed";
            } else if (i2 < 0 || i2 >= this.dataCount) {
                str2 = "row is out of index:" + i2;
            } else {
                str2 = "";
            }
        } else {
            str2 = "cannot find column: " + str;
        }
        Preconditions.checkArgument(str2.isEmpty(), str2);
    }

    public static DataHolder empty(int i2) {
        return new DataHolder(BUILDER, i2, (Bundle) null);
    }

    private static CursorWindow[] getCursorWindows(HMSCursorWrapper hMSCursorWrapper) {
        int i2;
        ArrayList arrayList = new ArrayList();
        try {
            int count = hMSCursorWrapper.getCount();
            CursorWindow window = hMSCursorWrapper.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                i2 = 0;
            } else {
                window.acquireReference();
                hMSCursorWrapper.setWindow(null);
                arrayList.add(window);
                i2 = window.getNumRows();
            }
            arrayList.addAll(iterCursorWrapper(hMSCursorWrapper, i2, count));
            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
        } catch (Throwable th) {
            try {
                HMSLog.e(TAG, "fail to getCursorWindows: " + th.getMessage());
                return new CursorWindow[0];
            } finally {
                hMSCursorWrapper.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b0, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static ArrayList<CursorWindow> iterCursorWindow(Builder builder, int i2, List list) {
        ArrayList<CursorWindow> arrayList = new ArrayList<>();
        CursorWindow cursorWindow = new CursorWindow((String) null);
        cursorWindow.setNumColumns(builder.a.length);
        arrayList.add(cursorWindow);
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            try {
                if (!cursorWindow.allocRow()) {
                    HMSLog.d(TAG, "Failed to allocate a row");
                    cursorWindow = new CursorWindow((String) null);
                    cursorWindow.setStartPosition(i3);
                    cursorWindow.setNumColumns(builder.a.length);
                    if (!cursorWindow.allocRow()) {
                        HMSLog.e(TAG, "Failed to retry to allocate a row");
                        return arrayList;
                    }
                    arrayList.add(cursorWindow);
                }
                HashMap hashMap = (HashMap) list.get(i3);
                boolean z = true;
                for (int i4 = 0; i4 < builder.a.length && (z = putValue(cursorWindow, hashMap.get(builder.a[i4]), i3, i4)); i4++) {
                }
                if (!z) {
                    HMSLog.d(TAG, "fail to put data for row " + i3);
                    cursorWindow.freeLastRow();
                    CursorWindow cursorWindow2 = new CursorWindow((String) null);
                    cursorWindow2.setStartPosition(i3);
                    cursorWindow2.setNumColumns(builder.a.length);
                    arrayList.add(cursorWindow2);
                    break;
                }
                i3++;
            } catch (RuntimeException e2) {
                Iterator<CursorWindow> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().close();
                }
                throw e2;
            }
        }
    }

    private static ArrayList<CursorWindow> iterCursorWrapper(HMSCursorWrapper hMSCursorWrapper, int i2, int i3) {
        ArrayList<CursorWindow> arrayList = new ArrayList<>();
        while (i2 < i3 && hMSCursorWrapper.moveToPosition(i2)) {
            CursorWindow window = hMSCursorWrapper.getWindow();
            if (window == null) {
                window = new CursorWindow((String) null);
                window.setStartPosition(i2);
                hMSCursorWrapper.fillWindow(i2, window);
            } else {
                window.acquireReference();
                hMSCursorWrapper.setWindow(null);
            }
            if (window.getNumRows() == 0) {
                break;
            }
            arrayList.add(window);
            i2 = window.getNumRows() + window.getStartPosition();
        }
        return arrayList;
    }

    private static boolean putValue(CursorWindow cursorWindow, Object obj, int i2, int i3) throws IllegalArgumentException {
        if (obj == null) {
            return cursorWindow.putNull(i2, i3);
        }
        if (obj instanceof Boolean) {
            return cursorWindow.putLong(((Boolean) obj).booleanValue() ? 1L : 0L, i2, i3);
        } else if (obj instanceof Integer) {
            return cursorWindow.putLong(((Integer) obj).intValue(), i2, i3);
        } else {
            if (obj instanceof Long) {
                return cursorWindow.putLong(((Long) obj).longValue(), i2, i3);
            }
            if (obj instanceof Float) {
                return cursorWindow.putDouble(((Float) obj).floatValue(), i2, i3);
            }
            if (obj instanceof Double) {
                return cursorWindow.putDouble(((Double) obj).doubleValue(), i2, i3);
            }
            if (obj instanceof String) {
                return cursorWindow.putString((String) obj, i2, i3);
            }
            if (obj instanceof byte[]) {
                return cursorWindow.putBlob((byte[]) obj, i2, i3);
            }
            throw new IllegalArgumentException("unsupported type for column: " + obj);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (!this.mClosed) {
            for (CursorWindow cursorWindow : this.cursorWindows) {
                cursorWindow.close();
            }
            this.mClosed = true;
        }
    }

    public final void collectColumsAndCount() {
        this.columnsBundle = new Bundle();
        String[] strArr = this.columns;
        int i2 = 0;
        if (strArr != null && strArr.length != 0) {
            int i3 = 0;
            while (true) {
                String[] strArr2 = this.columns;
                if (i3 >= strArr2.length) {
                    break;
                }
                this.columnsBundle.putInt(strArr2[i3], i3);
                i3++;
            }
            CursorWindow[] cursorWindowArr = this.cursorWindows;
            if (cursorWindowArr != null && cursorWindowArr.length != 0) {
                this.perCursorCounts = new int[cursorWindowArr.length];
                int i4 = 0;
                while (true) {
                    CursorWindow[] cursorWindowArr2 = this.cursorWindows;
                    if (i2 < cursorWindowArr2.length) {
                        this.perCursorCounts[i2] = i4;
                        i4 = cursorWindowArr2[i2].getStartPosition() + this.cursorWindows[i2].getNumRows();
                        i2++;
                    } else {
                        this.dataCount = i4;
                        return;
                    }
                }
            } else {
                this.dataCount = 0;
            }
        } else {
            this.dataCount = 0;
        }
    }

    public final void copyToBuffer(String str, int i2, int i3, CharArrayBuffer charArrayBuffer) {
        checkAvailable(str, i2);
        this.cursorWindows[i3].copyStringToBuffer(i2, this.columnsBundle.getInt(str), charArrayBuffer);
    }

    protected final void finalize() throws Throwable {
        if (this.isInstance && this.cursorWindows.length > 0 && !isClosed()) {
            close();
        }
        super.finalize();
    }

    public final int getCount() {
        return this.dataCount;
    }

    public final Bundle getMetadata() {
        return this.metadata;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final Object getValue(String str, int i2, int i3, String str2) {
        str2.hashCode();
        str2.hashCode();
        char c2 = '\uffff';
        switch (str2.hashCode()) {
            case -1092271849:
                if (str2.equals(TYPE_FLOAT)) {
                    c2 = 0;
                    break;
                }
                break;
            case -870070237:
                if (str2.equals(TYPE_BOOLEAN)) {
                    c2 = 1;
                    break;
                }
                break;
            case -675993238:
                if (str2.equals(TYPE_INT)) {
                    c2 = 2;
                    break;
                }
                break;
            case 445002870:
                if (str2.equals(TYPE_DOUBLE)) {
                    c2 = 3;
                    break;
                }
                break;
            case 519136353:
                if (str2.equals(TYPE_LONG)) {
                    c2 = 4;
                    break;
                }
                break;
            case 878975158:
                if (str2.equals(TYPE_STRING)) {
                    c2 = 5;
                    break;
                }
                break;
            case 1300508295:
                if (str2.equals(TYPE_BYTE_ARRAY)) {
                    c2 = 6;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                checkAvailable(str, i2);
                return Float.valueOf(this.cursorWindows[i3].getFloat(i2, this.columnsBundle.getInt(str)));
            case 1:
                checkAvailable(str, i2);
                return Boolean.valueOf(this.cursorWindows[i3].getLong(i2, this.columnsBundle.getInt(str)) == 1);
            case 2:
                checkAvailable(str, i2);
                return Integer.valueOf(this.cursorWindows[i3].getInt(i2, this.columnsBundle.getInt(str)));
            case 3:
                checkAvailable(str, i2);
                return Double.valueOf(this.cursorWindows[i3].getDouble(i2, this.columnsBundle.getInt(str)));
            case 4:
                checkAvailable(str, i2);
                return Long.valueOf(this.cursorWindows[i3].getLong(i2, this.columnsBundle.getInt(str)));
            case 5:
                checkAvailable(str, i2);
                return this.cursorWindows[i3].getString(i2, this.columnsBundle.getInt(str));
            case 6:
                checkAvailable(str, i2);
                return this.cursorWindows[i3].getBlob(i2, this.columnsBundle.getInt(str));
            default:
                return null;
        }
    }

    public final int getWindowIndex(int i2) {
        int[] iArr;
        int i3 = 0;
        Preconditions.checkArgument(i2 >= 0 || i2 < this.dataCount, "rowIndex is out of index:" + i2);
        while (true) {
            iArr = this.perCursorCounts;
            if (i3 >= iArr.length) {
                break;
            } else if (i2 < iArr[i3]) {
                i3--;
                break;
            } else {
                i3++;
            }
        }
        return i3 == iArr.length ? i3 - 1 : i3;
    }

    public final boolean hasColumn(String str) {
        return this.columnsBundle.containsKey(str);
    }

    public final boolean hasNull(String str, int i2, int i3) {
        checkAvailable(str, i2);
        return this.cursorWindows[i3].getType(i2, this.columnsBundle.getInt(str)) == 0;
    }

    public final synchronized boolean isClosed() {
        return this.mClosed;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.columns, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.cursorWindows, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.version);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        if ((i2 & 1) != 0) {
            close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataHolder(int i2, String[] strArr, CursorWindow[] cursorWindowArr, int i3, Bundle bundle) {
        this.mClosed = false;
        this.isInstance = true;
        this.version = i2;
        this.columns = strArr;
        this.cursorWindows = cursorWindowArr;
        this.statusCode = i3;
        this.metadata = bundle;
        collectColumsAndCount();
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        Preconditions.checkNotNull(strArr, "columnsP cannot be null");
        Preconditions.checkNotNull(cursorWindowArr, "cursorWindowP cannot be null");
        this.mClosed = false;
        this.isInstance = true;
        this.version = 1;
        this.columns = strArr;
        this.cursorWindows = cursorWindowArr;
        this.statusCode = i2;
        this.metadata = bundle;
        collectColumsAndCount();
    }

    private static CursorWindow[] getCursorWindows(Builder builder, int i2) {
        if (builder.a.length == 0) {
            return new CursorWindow[0];
        }
        if (i2 < 0 || i2 >= builder.b.size()) {
            i2 = builder.b.size();
        }
        ArrayList<CursorWindow> iterCursorWindow = iterCursorWindow(builder, i2, builder.b.subList(0, i2));
        return (CursorWindow[]) iterCursorWindow.toArray(new CursorWindow[iterCursorWindow.size()]);
    }

    private DataHolder(HMSCursorWrapper hMSCursorWrapper, int i2, Bundle bundle) {
        this(hMSCursorWrapper.getColumnNames(), getCursorWindows(hMSCursorWrapper), i2, bundle);
    }

    public DataHolder(Cursor cursor, int i2, Bundle bundle) {
        this(new HMSCursorWrapper(cursor), i2, bundle);
    }

    private DataHolder(Builder builder, int i2, Bundle bundle) {
        this(builder.a, getCursorWindows(builder, -1), i2, (Bundle) null);
    }

    private DataHolder(Builder builder, int i2, Bundle bundle, int i3) {
        this(builder.a, getCursorWindows(builder, -1), i2, bundle);
    }
}
