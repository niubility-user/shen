package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.NativeModule;
import com.facebook.systrace.SystraceMessage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@DoNotStrip
/* loaded from: classes.dex */
public class JavaModuleWrapper {
    private final JSInstance mJSInstance;
    private final ModuleHolder mModuleHolder;
    private final ArrayList<NativeModule.NativeMethod> mMethods = new ArrayList<>();
    private final ArrayList<MethodDescriptor> mDescs = new ArrayList<>();

    @DoNotStrip
    /* loaded from: classes.dex */
    public class MethodDescriptor {
        @DoNotStrip
        Method method;
        @DoNotStrip
        String name;
        @DoNotStrip
        String signature;
        @DoNotStrip
        String type;

        public MethodDescriptor() {
        }
    }

    public JavaModuleWrapper(JSInstance jSInstance, ModuleHolder moduleHolder) {
        this.mJSInstance = jSInstance;
        this.mModuleHolder = moduleHolder;
    }

    @DoNotStrip
    private void findMethods() {
        com.facebook.systrace.Systrace.beginSection(0L, "findMethods");
        HashSet hashSet = new HashSet();
        Class<?> cls = this.mModuleHolder.getModule().getClass();
        Class<? super Object> superclass = cls.getSuperclass();
        if (ReactModuleWithSpec.class.isAssignableFrom(superclass)) {
            cls = superclass;
        }
        for (Method method : cls.getDeclaredMethods()) {
            ReactMethod reactMethod = (ReactMethod) method.getAnnotation(ReactMethod.class);
            if (reactMethod != null) {
                String name = method.getName();
                if (!hashSet.contains(name)) {
                    MethodDescriptor methodDescriptor = new MethodDescriptor();
                    JavaMethodWrapper javaMethodWrapper = new JavaMethodWrapper(this, method, reactMethod.isBlockingSynchronousMethod());
                    methodDescriptor.name = name;
                    String type = javaMethodWrapper.getType();
                    methodDescriptor.type = type;
                    if (type == "sync") {
                        methodDescriptor.signature = javaMethodWrapper.getSignature();
                        methodDescriptor.method = method;
                    }
                    this.mMethods.add(javaMethodWrapper);
                    this.mDescs.add(methodDescriptor);
                } else {
                    throw new IllegalArgumentException("Java Module " + getName() + " method name already registered: " + name);
                }
            }
        }
        com.facebook.systrace.Systrace.endSection(0L);
    }

    @DoNotStrip
    @Nullable
    public NativeMap getConstants() {
        if (this.mModuleHolder.getHasConstants()) {
            String name = getName();
            SystraceMessage.beginSection(0L, "JavaModuleWrapper.getConstants").arg("moduleName", name).flush();
            ReactMarker.logMarker(ReactMarkerConstants.GET_CONSTANTS_START, name);
            BaseJavaModule module = getModule();
            com.facebook.systrace.Systrace.beginSection(0L, "module.getConstants");
            Map<String, Object> constants = module.getConstants();
            com.facebook.systrace.Systrace.endSection(0L);
            com.facebook.systrace.Systrace.beginSection(0L, "create WritableNativeMap");
            ReactMarker.logMarker(ReactMarkerConstants.CONVERT_CONSTANTS_START, name);
            try {
                return Arguments.makeNativeMap(constants);
            } finally {
                ReactMarker.logMarker(ReactMarkerConstants.CONVERT_CONSTANTS_END, name);
                com.facebook.systrace.Systrace.endSection(0L);
                ReactMarker.logMarker(ReactMarkerConstants.GET_CONSTANTS_END, name);
                SystraceMessage.endSection(0L).flush();
            }
        }
        return null;
    }

    @DoNotStrip
    public List<MethodDescriptor> getMethodDescriptors() {
        if (this.mDescs.isEmpty()) {
            findMethods();
        }
        return this.mDescs;
    }

    @DoNotStrip
    public BaseJavaModule getModule() {
        return (BaseJavaModule) this.mModuleHolder.getModule();
    }

    @DoNotStrip
    public String getName() {
        return this.mModuleHolder.getName();
    }

    @DoNotStrip
    public void invoke(int i2, ReadableNativeArray readableNativeArray) {
        ArrayList<NativeModule.NativeMethod> arrayList = this.mMethods;
        if (arrayList == null || i2 >= arrayList.size()) {
            return;
        }
        this.mMethods.get(i2).invoke(this.mJSInstance, readableNativeArray);
    }
}
