package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.config.ReactFeatureFlags;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@DoNotStrip
/* loaded from: classes.dex */
public class ReadableNativeMap extends NativeMap implements ReadableMap {
    private static int mJniCallCounter;
    @Nullable
    private String[] mKeys;
    @Nullable
    private HashMap<String, Object> mLocalMap;
    @Nullable
    private HashMap<String, ReadableType> mLocalTypeMap;

    /* renamed from: com.facebook.react.bridge.ReadableNativeMap$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
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

    @DoNotStrip
    /* loaded from: classes.dex */
    public static class ReadableNativeMapKeySetIterator implements ReadableMapKeySetIterator {
        @DoNotStrip
        private final HybridData mHybridData;
        @DoNotStrip
        private final ReadableNativeMap mMap;

        public ReadableNativeMapKeySetIterator(ReadableNativeMap readableNativeMap) {
            this.mMap = readableNativeMap;
            this.mHybridData = initHybrid(readableNativeMap);
        }

        private static native HybridData initHybrid(ReadableNativeMap readableNativeMap);

        @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
        public native boolean hasNextKey();

        @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
        public native String nextKey();
    }

    static {
        ReactBridge.staticInit();
    }

    public ReadableNativeMap(HybridData hybridData) {
        super(hybridData);
    }

    private void checkInstance(String str, Object obj, Class cls) {
        if (obj == null || cls.isInstance(obj)) {
            return;
        }
        throw new ClassCastException("Value for " + str + " cannot be cast from " + obj.getClass().getSimpleName() + " to " + cls.getSimpleName());
    }

    private native ReadableNativeArray getArrayNative(String str);

    private native boolean getBooleanNative(String str);

    private native double getDoubleNative(String str);

    private native int getIntNative(String str);

    public static int getJNIPassCounter() {
        return mJniCallCounter;
    }

    private HashMap<String, Object> getLocalMap() {
        HashMap<String, Object> hashMap = this.mLocalMap;
        if (hashMap != null) {
            return hashMap;
        }
        synchronized (this) {
            if (this.mKeys == null) {
                this.mKeys = (String[]) Assertions.assertNotNull(importKeys());
                mJniCallCounter++;
            }
            if (this.mLocalMap == null) {
                Object[] objArr = (Object[]) Assertions.assertNotNull(importValues());
                mJniCallCounter++;
                int length = this.mKeys.length;
                this.mLocalMap = new HashMap<>(length);
                for (int i2 = 0; i2 < length; i2++) {
                    this.mLocalMap.put(this.mKeys[i2], objArr[i2]);
                }
            }
        }
        return this.mLocalMap;
    }

    @Nonnull
    private HashMap<String, ReadableType> getLocalTypeMap() {
        HashMap<String, ReadableType> hashMap = this.mLocalTypeMap;
        if (hashMap != null) {
            return hashMap;
        }
        synchronized (this) {
            if (this.mKeys == null) {
                this.mKeys = (String[]) Assertions.assertNotNull(importKeys());
                mJniCallCounter++;
            }
            if (this.mLocalTypeMap == null) {
                Object[] objArr = (Object[]) Assertions.assertNotNull(importTypes());
                mJniCallCounter++;
                int length = this.mKeys.length;
                this.mLocalTypeMap = new HashMap<>(length);
                for (int i2 = 0; i2 < length; i2++) {
                    this.mLocalTypeMap.put(this.mKeys[i2], (ReadableType) objArr[i2]);
                }
            }
        }
        return this.mLocalTypeMap;
    }

    private native ReadableNativeMap getMapNative(String str);

    @Nullable
    private Object getNullableValue(String str) {
        if (hasKey(str)) {
            return getLocalMap().get(str);
        }
        throw new NoSuchKeyException(str);
    }

    private native String getStringNative(String str);

    private native ReadableType getTypeNative(String str);

    @Nonnull
    private Object getValue(@Nonnull String str) {
        if (hasKey(str) && !isNull(str)) {
            return Assertions.assertNotNull(getLocalMap().get(str));
        }
        throw new NoSuchKeyException(str);
    }

    private native boolean hasKeyNative(String str);

    private native String[] importKeys();

    private native Object[] importTypes();

    private native Object[] importValues();

    private native boolean isNullNative(@Nonnull String str);

    public static void setUseNativeAccessor(boolean z) {
        ReactFeatureFlags.useMapNativeAccessor = z;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @Nullable
    public ReadableArray getArray(@Nonnull String str) {
        if (ReactFeatureFlags.useMapNativeAccessor) {
            mJniCallCounter++;
            return getArrayNative(str);
        }
        return (ReadableArray) getNullableValue(str, ReadableArray.class);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean getBoolean(@Nonnull String str) {
        if (ReactFeatureFlags.useMapNativeAccessor) {
            mJniCallCounter++;
            return getBooleanNative(str);
        }
        return ((Boolean) getValue(str, Boolean.class)).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public double getDouble(@Nonnull String str) {
        if (ReactFeatureFlags.useMapNativeAccessor) {
            mJniCallCounter++;
            return getDoubleNative(str);
        }
        return ((Double) getValue(str, Double.class)).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @Nonnull
    public Dynamic getDynamic(@Nonnull String str) {
        return DynamicFromMap.create(this, str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public int getInt(@Nonnull String str) {
        if (ReactFeatureFlags.useMapNativeAccessor) {
            mJniCallCounter++;
            return getIntNative(str);
        }
        return ((Double) getValue(str, Double.class)).intValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @Nullable
    public String getString(@Nonnull String str) {
        if (ReactFeatureFlags.useMapNativeAccessor) {
            mJniCallCounter++;
            return getStringNative(str);
        }
        return (String) getNullableValue(str, String.class);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @Nonnull
    public ReadableType getType(@Nonnull String str) {
        if (ReactFeatureFlags.useMapNativeAccessor) {
            mJniCallCounter++;
            return getTypeNative(str);
        } else if (getLocalTypeMap().containsKey(str)) {
            return (ReadableType) Assertions.assertNotNull(getLocalTypeMap().get(str));
        } else {
            throw new NoSuchKeyException(str);
        }
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean hasKey(@Nonnull String str) {
        if (ReactFeatureFlags.useMapNativeAccessor) {
            mJniCallCounter++;
            return hasKeyNative(str);
        }
        return getLocalMap().containsKey(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean isNull(@Nonnull String str) {
        if (ReactFeatureFlags.useMapNativeAccessor) {
            mJniCallCounter++;
            return isNullNative(str);
        } else if (getLocalMap().containsKey(str)) {
            return getLocalMap().get(str) == null;
        } else {
            throw new NoSuchKeyException(str);
        }
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @Nonnull
    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableNativeMapKeySetIterator(this);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @Nonnull
    public HashMap<String, Object> toHashMap() {
        if (ReactFeatureFlags.useMapNativeAccessor) {
            ReadableMapKeySetIterator keySetIterator = keySetIterator();
            HashMap<String, Object> hashMap = new HashMap<>();
            while (keySetIterator.hasNextKey()) {
                mJniCallCounter++;
                String nextKey = keySetIterator.nextKey();
                mJniCallCounter++;
                switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[getType(nextKey).ordinal()]) {
                    case 1:
                        hashMap.put(nextKey, null);
                        break;
                    case 2:
                        hashMap.put(nextKey, Boolean.valueOf(getBoolean(nextKey)));
                        break;
                    case 3:
                        hashMap.put(nextKey, Double.valueOf(getDouble(nextKey)));
                        break;
                    case 4:
                        hashMap.put(nextKey, getString(nextKey));
                        break;
                    case 5:
                        hashMap.put(nextKey, ((ReadableNativeMap) Assertions.assertNotNull(getMap(nextKey))).toHashMap());
                        break;
                    case 6:
                        hashMap.put(nextKey, ((ReadableArray) Assertions.assertNotNull(getArray(nextKey))).toArrayList());
                        break;
                    default:
                        throw new IllegalArgumentException("Could not convert object with key: " + nextKey + OrderISVUtil.MONEY_DECIMAL);
                }
            }
            return hashMap;
        }
        HashMap<String, Object> hashMap2 = new HashMap<>(getLocalMap());
        for (String str : hashMap2.keySet()) {
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[getType(str).ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    break;
                case 5:
                    hashMap2.put(str, ((ReadableNativeMap) Assertions.assertNotNull(getMap(str))).toHashMap());
                    break;
                case 6:
                    hashMap2.put(str, ((ReadableArray) Assertions.assertNotNull(getArray(str))).toArrayList());
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + str + OrderISVUtil.MONEY_DECIMAL);
            }
        }
        return hashMap2;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @Nullable
    public ReadableNativeMap getMap(@Nonnull String str) {
        if (ReactFeatureFlags.useMapNativeAccessor) {
            mJniCallCounter++;
            return getMapNative(str);
        }
        return (ReadableNativeMap) getNullableValue(str, ReadableNativeMap.class);
    }

    @Nullable
    private <T> T getNullableValue(String str, Class<T> cls) {
        T t = (T) getNullableValue(str);
        checkInstance(str, t, cls);
        return t;
    }

    private <T> T getValue(String str, Class<T> cls) {
        T t = (T) getValue(str);
        checkInstance(str, t, cls);
        return t;
    }
}
