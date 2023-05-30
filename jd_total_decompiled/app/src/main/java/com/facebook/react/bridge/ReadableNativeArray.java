package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.config.ReactFeatureFlags;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@DoNotStrip
/* loaded from: classes.dex */
public class ReadableNativeArray extends NativeArray implements ReadableArray {
    private static int jniPassCounter;
    @Nullable
    private Object[] mLocalArray;
    @Nullable
    private ReadableType[] mLocalTypeArray;

    /* renamed from: com.facebook.react.bridge.ReadableNativeArray$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    static {
        ReactBridge.staticInit();
        jniPassCounter = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReadableNativeArray(HybridData hybridData) {
        super(hybridData);
    }

    private native ReadableNativeArray getArrayNative(int i2);

    private native boolean getBooleanNative(int i2);

    private native double getDoubleNative(int i2);

    private native int getIntNative(int i2);

    public static int getJNIPassCounter() {
        return jniPassCounter;
    }

    private Object[] getLocalArray() {
        Object[] objArr = this.mLocalArray;
        if (objArr != null) {
            return objArr;
        }
        synchronized (this) {
            if (this.mLocalArray == null) {
                jniPassCounter++;
                this.mLocalArray = (Object[]) Assertions.assertNotNull(importArray());
            }
        }
        return this.mLocalArray;
    }

    private ReadableType[] getLocalTypeArray() {
        ReadableType[] readableTypeArr = this.mLocalTypeArray;
        if (readableTypeArr != null) {
            return readableTypeArr;
        }
        synchronized (this) {
            if (this.mLocalTypeArray == null) {
                jniPassCounter++;
                Object[] objArr = (Object[]) Assertions.assertNotNull(importTypeArray());
                this.mLocalTypeArray = (ReadableType[]) Arrays.copyOf(objArr, objArr.length, ReadableType[].class);
            }
        }
        return this.mLocalTypeArray;
    }

    private native ReadableNativeMap getMapNative(int i2);

    private native String getStringNative(int i2);

    private native ReadableType getTypeNative(int i2);

    private native Object[] importArray();

    private native Object[] importTypeArray();

    private native boolean isNullNative(int i2);

    public static void setUseNativeAccessor(boolean z) {
        ReactFeatureFlags.useArrayNativeAccessor = z;
    }

    private native int sizeNative();

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean getBoolean(int i2) {
        if (ReactFeatureFlags.useArrayNativeAccessor) {
            jniPassCounter++;
            return getBooleanNative(i2);
        }
        return ((Boolean) getLocalArray()[i2]).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public double getDouble(int i2) {
        if (ReactFeatureFlags.useArrayNativeAccessor) {
            jniPassCounter++;
            return getDoubleNative(i2);
        }
        return ((Double) getLocalArray()[i2]).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    @Nonnull
    public Dynamic getDynamic(int i2) {
        return DynamicFromArray.create(this, i2);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int getInt(int i2) {
        if (ReactFeatureFlags.useArrayNativeAccessor) {
            jniPassCounter++;
            return getIntNative(i2);
        }
        return ((Double) getLocalArray()[i2]).intValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    @Nullable
    public String getString(int i2) {
        if (ReactFeatureFlags.useArrayNativeAccessor) {
            jniPassCounter++;
            return getStringNative(i2);
        }
        return (String) getLocalArray()[i2];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    @Nonnull
    public ReadableType getType(int i2) {
        if (ReactFeatureFlags.useArrayNativeAccessor) {
            jniPassCounter++;
            return getTypeNative(i2);
        }
        return getLocalTypeArray()[i2];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean isNull(int i2) {
        if (!ReactFeatureFlags.useArrayNativeAccessor) {
            return getLocalArray()[i2] == null;
        }
        jniPassCounter++;
        return isNullNative(i2);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int size() {
        if (ReactFeatureFlags.useArrayNativeAccessor) {
            jniPassCounter++;
            return sizeNative();
        }
        return getLocalArray().length;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    @Nonnull
    public ArrayList<Object> toArrayList() {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < size(); i2++) {
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[getType(i2).ordinal()]) {
                case 1:
                    arrayList.add(null);
                    break;
                case 2:
                    arrayList.add(Boolean.valueOf(getBoolean(i2)));
                    break;
                case 3:
                    arrayList.add(Double.valueOf(getDouble(i2)));
                    break;
                case 4:
                    arrayList.add(getString(i2));
                    break;
                case 5:
                    arrayList.add(getMap(i2).toHashMap());
                    break;
                case 6:
                    arrayList.add(getArray(i2).toArrayList());
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object at index: " + i2 + OrderISVUtil.MONEY_DECIMAL);
            }
        }
        return arrayList;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    @Nullable
    public ReadableNativeArray getArray(int i2) {
        if (ReactFeatureFlags.useArrayNativeAccessor) {
            jniPassCounter++;
            return getArrayNative(i2);
        }
        return (ReadableNativeArray) getLocalArray()[i2];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    @Nullable
    public ReadableNativeMap getMap(int i2) {
        if (ReactFeatureFlags.useArrayNativeAccessor) {
            jniPassCounter++;
            return getMapNative(i2);
        }
        return (ReadableNativeMap) getLocalArray()[i2];
    }
}
