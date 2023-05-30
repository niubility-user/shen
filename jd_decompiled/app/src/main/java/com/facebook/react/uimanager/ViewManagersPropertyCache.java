package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class ViewManagersPropertyCache {
    private static final Map<Class, Map<String, PropSetter>> CLASS_PROPS_CACHE = new HashMap();
    private static final Map<String, PropSetter> EMPTY_PROPS_MAP = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class ArrayPropSetter extends PropSetter {
        public ArrayPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "Array", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object extractProperty(ReactStylesDiffMap reactStylesDiffMap) {
            return reactStylesDiffMap.getArray(this.mPropName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class BooleanPropSetter extends PropSetter {
        private final boolean mDefaultValue;

        public BooleanPropSetter(ReactProp reactProp, Method method, boolean z) {
            super(reactProp, "boolean", method);
            this.mDefaultValue = z;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object extractProperty(ReactStylesDiffMap reactStylesDiffMap) {
            return reactStylesDiffMap.getBoolean(this.mPropName, this.mDefaultValue) ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class BoxedBooleanPropSetter extends PropSetter {
        public BoxedBooleanPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "boolean", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object extractProperty(ReactStylesDiffMap reactStylesDiffMap) {
            if (reactStylesDiffMap.isNull(this.mPropName)) {
                return null;
            }
            return reactStylesDiffMap.getBoolean(this.mPropName, false) ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class BoxedIntPropSetter extends PropSetter {
        public BoxedIntPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "number", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object extractProperty(ReactStylesDiffMap reactStylesDiffMap) {
            if (reactStylesDiffMap.isNull(this.mPropName)) {
                return null;
            }
            return Integer.valueOf(reactStylesDiffMap.getInt(this.mPropName, 0));
        }

        public BoxedIntPropSetter(ReactPropGroup reactPropGroup, Method method, int i2) {
            super(reactPropGroup, "number", method, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class DynamicPropSetter extends PropSetter {
        public DynamicPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "mixed", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object extractProperty(ReactStylesDiffMap reactStylesDiffMap) {
            return reactStylesDiffMap.getDynamic(this.mPropName);
        }

        public DynamicPropSetter(ReactPropGroup reactPropGroup, Method method, int i2) {
            super(reactPropGroup, "mixed", method, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class MapPropSetter extends PropSetter {
        public MapPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "Map", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object extractProperty(ReactStylesDiffMap reactStylesDiffMap) {
            return reactStylesDiffMap.getMap(this.mPropName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class StringPropSetter extends PropSetter {
        public StringPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "String", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object extractProperty(ReactStylesDiffMap reactStylesDiffMap) {
            return reactStylesDiffMap.getString(this.mPropName);
        }
    }

    ViewManagersPropertyCache() {
    }

    public static void clear() {
        CLASS_PROPS_CACHE.clear();
        EMPTY_PROPS_MAP.clear();
    }

    private static PropSetter createPropSetter(ReactProp reactProp, Method method, Class<?> cls) {
        if (cls == Dynamic.class) {
            return new DynamicPropSetter(reactProp, method);
        }
        if (cls == Boolean.TYPE) {
            return new BooleanPropSetter(reactProp, method, reactProp.defaultBoolean());
        }
        if (cls == Integer.TYPE) {
            return new IntPropSetter(reactProp, method, reactProp.defaultInt());
        }
        if (cls == Float.TYPE) {
            return new FloatPropSetter(reactProp, method, reactProp.defaultFloat());
        }
        if (cls == Double.TYPE) {
            return new DoublePropSetter(reactProp, method, reactProp.defaultDouble());
        }
        if (cls == String.class) {
            return new StringPropSetter(reactProp, method);
        }
        if (cls == Boolean.class) {
            return new BoxedBooleanPropSetter(reactProp, method);
        }
        if (cls == Integer.class) {
            return new BoxedIntPropSetter(reactProp, method);
        }
        if (cls == ReadableArray.class) {
            return new ArrayPropSetter(reactProp, method);
        }
        if (cls == ReadableMap.class) {
            return new MapPropSetter(reactProp, method);
        }
        throw new RuntimeException("Unrecognized type: " + cls + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
    }

    private static void createPropSetters(ReactPropGroup reactPropGroup, Method method, Class<?> cls, Map<String, PropSetter> map) {
        String[] names = reactPropGroup.names();
        int i2 = 0;
        if (cls == Dynamic.class) {
            while (i2 < names.length) {
                map.put(names[i2], new DynamicPropSetter(reactPropGroup, method, i2));
                i2++;
            }
        } else if (cls == Integer.TYPE) {
            while (i2 < names.length) {
                map.put(names[i2], new IntPropSetter(reactPropGroup, method, i2, reactPropGroup.defaultInt()));
                i2++;
            }
        } else if (cls == Float.TYPE) {
            while (i2 < names.length) {
                map.put(names[i2], new FloatPropSetter(reactPropGroup, method, i2, reactPropGroup.defaultFloat()));
                i2++;
            }
        } else if (cls == Double.TYPE) {
            while (i2 < names.length) {
                map.put(names[i2], new DoublePropSetter(reactPropGroup, method, i2, reactPropGroup.defaultDouble()));
                i2++;
            }
        } else if (cls == Integer.class) {
            while (i2 < names.length) {
                map.put(names[i2], new BoxedIntPropSetter(reactPropGroup, method, i2));
                i2++;
            }
        } else {
            throw new RuntimeException("Unrecognized type: " + cls + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
        }
    }

    private static void extractPropSettersFromShadowNodeClassDefinition(Class<? extends ReactShadowNode> cls, Map<String, PropSetter> map) {
        for (Method method : cls.getDeclaredMethods()) {
            ReactProp reactProp = (ReactProp) method.getAnnotation(ReactProp.class);
            if (reactProp != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    map.put(reactProp.name(), createPropSetter(reactProp, method, parameterTypes[0]));
                } else {
                    throw new RuntimeException("Wrong number of args for prop setter: " + cls.getName() + "#" + method.getName());
                }
            }
            ReactPropGroup reactPropGroup = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (reactPropGroup != null) {
                Class<?>[] parameterTypes2 = method.getParameterTypes();
                if (parameterTypes2.length == 2) {
                    if (parameterTypes2[0] == Integer.TYPE) {
                        createPropSetters(reactPropGroup, method, parameterTypes2[1], map);
                    } else {
                        throw new RuntimeException("Second argument should be property index: " + cls.getName() + "#" + method.getName());
                    }
                } else {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + cls.getName() + "#" + method.getName());
                }
            }
        }
    }

    private static void extractPropSettersFromViewManagerClassDefinition(Class<? extends ViewManager> cls, Map<String, PropSetter> map) {
        for (Method method : cls.getDeclaredMethods()) {
            ReactProp reactProp = (ReactProp) method.getAnnotation(ReactProp.class);
            if (reactProp != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 2) {
                    if (View.class.isAssignableFrom(parameterTypes[0])) {
                        map.put(reactProp.name(), createPropSetter(reactProp, method, parameterTypes[1]));
                    } else {
                        throw new RuntimeException("First param should be a view subclass to be updated: " + cls.getName() + "#" + method.getName());
                    }
                } else {
                    throw new RuntimeException("Wrong number of args for prop setter: " + cls.getName() + "#" + method.getName());
                }
            }
            ReactPropGroup reactPropGroup = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (reactPropGroup != null) {
                Class<?>[] parameterTypes2 = method.getParameterTypes();
                if (parameterTypes2.length == 3) {
                    if (View.class.isAssignableFrom(parameterTypes2[0])) {
                        if (parameterTypes2[1] == Integer.TYPE) {
                            createPropSetters(reactPropGroup, method, parameterTypes2[2], map);
                        } else {
                            throw new RuntimeException("Second argument should be property index: " + cls.getName() + "#" + method.getName());
                        }
                    } else {
                        throw new RuntimeException("First param should be a view subclass to be updated: " + cls.getName() + "#" + method.getName());
                    }
                } else {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + cls.getName() + "#" + method.getName());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, PropSetter> getNativePropSettersForShadowNodeClass(Class<? extends ReactShadowNode> cls) {
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (cls2 == ReactShadowNode.class) {
                return EMPTY_PROPS_MAP;
            }
        }
        Map<Class, Map<String, PropSetter>> map = CLASS_PROPS_CACHE;
        Map<String, PropSetter> map2 = map.get(cls);
        if (map2 != null) {
            return map2;
        }
        HashMap hashMap = new HashMap(getNativePropSettersForShadowNodeClass(cls.getSuperclass()));
        extractPropSettersFromShadowNodeClassDefinition(cls, hashMap);
        map.put(cls, hashMap);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, PropSetter> getNativePropSettersForViewManagerClass(Class<? extends ViewManager> cls) {
        if (cls == ViewManager.class) {
            return EMPTY_PROPS_MAP;
        }
        Map<Class, Map<String, PropSetter>> map = CLASS_PROPS_CACHE;
        Map<String, PropSetter> map2 = map.get(cls);
        if (map2 != null) {
            return map2;
        }
        HashMap hashMap = new HashMap(getNativePropSettersForViewManagerClass(cls.getSuperclass()));
        extractPropSettersFromViewManagerClassDefinition(cls, hashMap);
        map.put(cls, hashMap);
        return hashMap;
    }

    static Map<String, String> getNativePropsForView(Class<? extends ViewManager> cls, Class<? extends ReactShadowNode> cls2) {
        HashMap hashMap = new HashMap();
        for (PropSetter propSetter : getNativePropSettersForViewManagerClass(cls).values()) {
            hashMap.put(propSetter.getPropName(), propSetter.getPropType());
        }
        for (PropSetter propSetter2 : getNativePropSettersForShadowNodeClass(cls2).values()) {
            hashMap.put(propSetter2.getPropName(), propSetter2.getPropType());
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class DoublePropSetter extends PropSetter {
        private final double mDefaultValue;

        public DoublePropSetter(ReactProp reactProp, Method method, double d) {
            super(reactProp, "number", method);
            this.mDefaultValue = d;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object extractProperty(ReactStylesDiffMap reactStylesDiffMap) {
            return Double.valueOf(reactStylesDiffMap.getDouble(this.mPropName, this.mDefaultValue));
        }

        public DoublePropSetter(ReactPropGroup reactPropGroup, Method method, int i2, double d) {
            super(reactPropGroup, "number", method, i2);
            this.mDefaultValue = d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class FloatPropSetter extends PropSetter {
        private final float mDefaultValue;

        public FloatPropSetter(ReactProp reactProp, Method method, float f2) {
            super(reactProp, "number", method);
            this.mDefaultValue = f2;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object extractProperty(ReactStylesDiffMap reactStylesDiffMap) {
            return Float.valueOf(reactStylesDiffMap.getFloat(this.mPropName, this.mDefaultValue));
        }

        public FloatPropSetter(ReactPropGroup reactPropGroup, Method method, int i2, float f2) {
            super(reactPropGroup, "number", method, i2);
            this.mDefaultValue = f2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class IntPropSetter extends PropSetter {
        private final int mDefaultValue;

        public IntPropSetter(ReactProp reactProp, Method method, int i2) {
            super(reactProp, "number", method);
            this.mDefaultValue = i2;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object extractProperty(ReactStylesDiffMap reactStylesDiffMap) {
            return Integer.valueOf(reactStylesDiffMap.getInt(this.mPropName, this.mDefaultValue));
        }

        public IntPropSetter(ReactPropGroup reactPropGroup, Method method, int i2, int i3) {
            super(reactPropGroup, "number", method, i2);
            this.mDefaultValue = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static abstract class PropSetter {
        @Nullable
        protected final Integer mIndex;
        protected final String mPropName;
        protected final String mPropType;
        protected final Method mSetter;
        private static final Object[] VIEW_MGR_ARGS = new Object[2];
        private static final Object[] VIEW_MGR_GROUP_ARGS = new Object[3];
        private static final Object[] SHADOW_ARGS = new Object[1];
        private static final Object[] SHADOW_GROUP_ARGS = new Object[2];

        @Nullable
        protected abstract Object extractProperty(ReactStylesDiffMap reactStylesDiffMap);

        public String getPropName() {
            return this.mPropName;
        }

        public String getPropType() {
            return this.mPropType;
        }

        public void updateShadowNodeProp(ReactShadowNode reactShadowNode, ReactStylesDiffMap reactStylesDiffMap) {
            try {
                Integer num = this.mIndex;
                if (num == null) {
                    Object[] objArr = SHADOW_ARGS;
                    objArr[0] = extractProperty(reactStylesDiffMap);
                    this.mSetter.invoke(reactShadowNode, objArr);
                    Arrays.fill(objArr, (Object) null);
                } else {
                    Object[] objArr2 = SHADOW_GROUP_ARGS;
                    objArr2[0] = num;
                    objArr2[1] = extractProperty(reactStylesDiffMap);
                    this.mSetter.invoke(reactShadowNode, objArr2);
                    Arrays.fill(objArr2, (Object) null);
                }
            } catch (Throwable th) {
                FLog.e(ViewManager.class, "Error while updating prop " + this.mPropName, th);
            }
        }

        public void updateViewProp(ViewManager viewManager, View view, ReactStylesDiffMap reactStylesDiffMap) {
            try {
                Integer num = this.mIndex;
                if (num == null) {
                    Object[] objArr = VIEW_MGR_ARGS;
                    objArr[0] = view;
                    objArr[1] = extractProperty(reactStylesDiffMap);
                    this.mSetter.invoke(viewManager, objArr);
                    Arrays.fill(objArr, (Object) null);
                } else {
                    Object[] objArr2 = VIEW_MGR_GROUP_ARGS;
                    objArr2[0] = view;
                    objArr2[1] = num;
                    objArr2[2] = extractProperty(reactStylesDiffMap);
                    this.mSetter.invoke(viewManager, objArr2);
                    Arrays.fill(objArr2, (Object) null);
                }
            } catch (Throwable th) {
                FLog.e(ViewManager.class, "Error while updating prop " + this.mPropName, th);
            }
        }

        private PropSetter(ReactProp reactProp, String str, Method method) {
            this.mPropName = reactProp.name();
            this.mPropType = "__default_type__".equals(reactProp.customType()) ? str : reactProp.customType();
            this.mSetter = method;
            this.mIndex = null;
        }

        private PropSetter(ReactPropGroup reactPropGroup, String str, Method method, int i2) {
            this.mPropName = reactPropGroup.names()[i2];
            this.mPropType = "__default_type__".equals(reactPropGroup.customType()) ? str : reactPropGroup.customType();
            this.mSetter = method;
            this.mIndex = Integer.valueOf(i2);
        }
    }
}
