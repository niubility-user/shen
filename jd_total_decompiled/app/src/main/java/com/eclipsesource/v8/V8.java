package com.eclipsesource.v8;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.inspector.V8InspectorDelegate;
import com.eclipsesource.v8.utils.V8Executor;
import com.eclipsesource.v8.utils.V8Map;
import com.eclipsesource.v8.utils.V8Runnable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class V8 extends V8Object {
    private static boolean initialized;
    private static boolean nativeLibraryLoaded;
    private static Error nativeLoadError;
    private static Exception nativeLoadException;
    private static volatile int runtimeCounter;
    private static String v8Flags;
    private Map<String, Object> data;
    private V8Map<V8Executor> executors;
    private boolean forceTerminateExecutors;
    private Map<Long, MethodDescriptor> functionRegistry;
    private final V8Locker locker;
    private long objectReferences;
    private LinkedList<ReferenceHandler> referenceHandlers;
    private LinkedList<V8Runnable> releaseHandlers;
    private List<Releasable> resources;
    private SignatureProvider signatureProvider;
    private long v8RuntimePtr;
    protected Map<Long, V8Value> v8WeakReferences;
    private static Object lock = new Object();
    private static V8Value undefined = new V8Object.Undefined();
    private static Object invalid = new Object();

    /* loaded from: classes.dex */
    public class MethodDescriptor {
        JavaCallback callback;
        boolean includeReceiver;
        Method method;
        Object object;
        JavaVoidCallback voidCallback;

        private MethodDescriptor() {
            V8.this = r1;
        }
    }

    protected V8() {
        this(null);
    }

    private native void _acquireLock(long j2);

    private native void _add(long j2, long j3, String str, double d);

    private native void _add(long j2, long j3, String str, int i2);

    private native void _add(long j2, long j3, String str, String str2);

    private native void _add(long j2, long j3, String str, boolean z);

    private native void _addArrayBooleanItem(long j2, long j3, boolean z);

    private native void _addArrayDoubleItem(long j2, long j3, double d);

    private native void _addArrayIntItem(long j2, long j3, int i2);

    private native void _addArrayNullItem(long j2, long j3);

    private native void _addArrayObjectItem(long j2, long j3, long j4);

    private native void _addArrayStringItem(long j2, long j3, String str);

    private native void _addArrayUndefinedItem(long j2, long j3);

    private native void _addNull(long j2, long j3, String str);

    private native void _addObject(long j2, long j3, String str, long j4);

    private native void _addUndefined(long j2, long j3, String str);

    private native Object _arrayGet(long j2, int i2, long j3, int i3);

    private native boolean _arrayGetBoolean(long j2, long j3, int i2);

    private native int _arrayGetBooleans(long j2, long j3, int i2, int i3, boolean[] zArr);

    private native boolean[] _arrayGetBooleans(long j2, long j3, int i2, int i3);

    private native byte _arrayGetByte(long j2, long j3, int i2);

    private native int _arrayGetBytes(long j2, long j3, int i2, int i3, byte[] bArr);

    private native byte[] _arrayGetBytes(long j2, long j3, int i2, int i3);

    private native double _arrayGetDouble(long j2, long j3, int i2);

    private native int _arrayGetDoubles(long j2, long j3, int i2, int i3, double[] dArr);

    private native double[] _arrayGetDoubles(long j2, long j3, int i2, int i3);

    private native int _arrayGetInteger(long j2, long j3, int i2);

    private native int _arrayGetIntegers(long j2, long j3, int i2, int i3, int[] iArr);

    private native int[] _arrayGetIntegers(long j2, long j3, int i2, int i3);

    private native int _arrayGetSize(long j2, long j3);

    private native String _arrayGetString(long j2, long j3, int i2);

    private native int _arrayGetStrings(long j2, long j3, int i2, int i3, String[] strArr);

    private native String[] _arrayGetStrings(long j2, long j3, int i2, int i3);

    private native void _clearWeak(long j2, long j3);

    private native boolean _contains(long j2, long j3, String str);

    private native long _createInspector(long j2, V8InspectorDelegate v8InspectorDelegate, String str);

    private native long _createIsolate(String str);

    private native void _createTwin(long j2, long j3, long j4);

    private native ByteBuffer _createV8ArrayBufferBackingStore(long j2, long j3, int i2);

    private native void _dispatchProtocolMessage(long j2, long j3, String str);

    private native boolean _equals(long j2, long j3, long j4);

    private native boolean _executeBooleanFunction(long j2, long j3, String str, long j4);

    private native boolean _executeBooleanScript(long j2, String str, String str2, int i2);

    private native double _executeDoubleFunction(long j2, long j3, String str, long j4);

    private native double _executeDoubleScript(long j2, String str, String str2, int i2);

    private native Object _executeFunction(long j2, int i2, long j3, String str, long j4);

    private native Object _executeFunction(long j2, long j3, long j4, long j5);

    private native int _executeIntegerFunction(long j2, long j3, String str, long j4);

    private native int _executeIntegerScript(long j2, String str, String str2, int i2);

    private native Object _executeScript(long j2, int i2, String str, String str2, int i3);

    private native String _executeStringFunction(long j2, long j3, String str, long j4);

    private native String _executeStringScript(long j2, String str, String str2, int i2);

    private native void _executeVoidFunction(long j2, long j3, String str, long j4);

    private native void _executeVoidScript(long j2, String str, String str2, int i2);

    private native Object _get(long j2, int i2, long j3, String str);

    private native int _getArrayType(long j2, long j3);

    private native boolean _getBoolean(long j2, long j3, String str);

    private static native long _getBuildID();

    private native String _getConstructorName(long j2, long j3);

    private native double _getDouble(long j2, long j3, String str);

    private native long _getGlobalObject(long j2);

    private native int _getInteger(long j2, long j3, String str);

    private native String[] _getKeys(long j2, long j3);

    private native String _getString(long j2, long j3, String str);

    private native int _getType(long j2, long j3);

    private native int _getType(long j2, long j3, int i2);

    private native int _getType(long j2, long j3, int i2, int i3);

    private native int _getType(long j2, long j3, String str);

    private static native String _getVersion();

    private native int _identityHash(long j2, long j3);

    private native long _initEmptyContainer(long j2);

    private native long _initNewV8Array(long j2);

    private native long _initNewV8ArrayBuffer(long j2, int i2);

    private native long _initNewV8ArrayBuffer(long j2, ByteBuffer byteBuffer, int i2);

    private native long _initNewV8Float32Array(long j2, long j3, int i2, int i3);

    private native long _initNewV8Float64Array(long j2, long j3, int i2, int i3);

    private native long[] _initNewV8Function(long j2);

    private native long _initNewV8Int16Array(long j2, long j3, int i2, int i3);

    private native long _initNewV8Int32Array(long j2, long j3, int i2, int i3);

    private native long _initNewV8Int8Array(long j2, long j3, int i2, int i3);

    private native long _initNewV8Object(long j2);

    private native long _initNewV8UInt16Array(long j2, long j3, int i2, int i3);

    private native long _initNewV8UInt32Array(long j2, long j3, int i2, int i3);

    private native long _initNewV8UInt8Array(long j2, long j3, int i2, int i3);

    private native long _initNewV8UInt8ClampedArray(long j2, long j3, int i2, int i3);

    private static native boolean _isNodeCompatible();

    private static native boolean _isRunning(long j2);

    private native boolean _isWeak(long j2, long j3);

    private native void _lowMemoryNotification(long j2);

    private static native boolean _pumpMessageLoop(long j2);

    private native long _registerJavaMethod(long j2, long j3, String str, boolean z);

    private native void _release(long j2, long j3);

    private native void _releaseLock(long j2);

    private native void _releaseMethodDescriptor(long j2, long j3);

    private native void _releaseRuntime(long j2);

    private native boolean _sameValue(long j2, long j3, long j4);

    private native void _schedulePauseOnNextStatement(long j2, long j3, String str);

    private static native void _setFlags(String str);

    private native void _setPrototype(long j2, long j3, long j4);

    private native void _setWeak(long j2, long j3);

    private static native void _startNodeJS(long j2, String str);

    private native boolean _strictEquals(long j2, long j3, long j4);

    private native void _terminateExecution(long j2);

    private native String _toString(long j2, long j3);

    private void checkArgs(Object[] objArr) {
        for (Object obj : objArr) {
            if (obj == invalid) {
                throw new IllegalArgumentException("argument type mismatch");
            }
        }
    }

    private static void checkNativeLibraryLoaded() {
        if (nativeLibraryLoaded) {
            return;
        }
        String computeLibraryShortName = LibraryLoader.computeLibraryShortName(true);
        String str = "J2V8 native library not loaded (" + LibraryLoader.computeLibraryShortName(false) + "/" + computeLibraryShortName + ")";
        if (nativeLoadError == null) {
            if (nativeLoadException != null) {
                throw new IllegalStateException(str, nativeLoadException);
            }
            throw new IllegalStateException(str);
        }
        throw new IllegalStateException(str, nativeLoadError);
    }

    private Object checkResult(Object obj) {
        if (obj == null) {
            return obj;
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if ((obj instanceof Integer) || (obj instanceof Double) || (obj instanceof Boolean) || (obj instanceof String)) {
            return obj;
        }
        if (obj instanceof V8Value) {
            if (((V8Value) obj).isReleased()) {
                throw new V8RuntimeException("V8Value already released");
            }
            return obj;
        }
        throw new V8RuntimeException("Unknown return type: " + obj.getClass());
    }

    static void checkScript(String str) {
        if (str == null) {
            throw new NullPointerException("Script is null");
        }
    }

    public static V8 createV8Runtime() {
        return createV8Runtime(null, null);
    }

    public static int getActiveRuntimes() {
        return runtimeCounter;
    }

    private Object[] getArgs(V8Object v8Object, MethodDescriptor methodDescriptor, V8Array v8Array, boolean z) {
        int length = methodDescriptor.method.getParameterTypes().length;
        int i2 = z ? length - 1 : length;
        Object[] defaultValues = setDefaultValues(new Object[length], methodDescriptor.method.getParameterTypes(), v8Object, methodDescriptor.includeReceiver);
        ArrayList arrayList = new ArrayList();
        populateParamters(v8Array, i2, defaultValues, arrayList, methodDescriptor.includeReceiver);
        if (z) {
            Object varArgContainer = getVarArgContainer(methodDescriptor.method.getParameterTypes(), arrayList.size());
            System.arraycopy(arrayList.toArray(), 0, varArgContainer, 0, arrayList.size());
            defaultValues[i2] = varArgContainer;
        }
        return defaultValues;
    }

    private Object getArrayItem(V8Array v8Array, int i2) {
        try {
            int type = v8Array.getType(i2);
            if (type != 10) {
                if (type != 99) {
                    switch (type) {
                        case 1:
                            return Integer.valueOf(v8Array.getInteger(i2));
                        case 2:
                            return Double.valueOf(v8Array.getDouble(i2));
                        case 3:
                            return Boolean.valueOf(v8Array.getBoolean(i2));
                        case 4:
                            return v8Array.getString(i2);
                        case 5:
                        case 8:
                            return v8Array.getArray(i2);
                        case 6:
                            return v8Array.getObject(i2);
                        case 7:
                            return v8Array.getObject(i2);
                        default:
                            return null;
                    }
                }
                return getUndefined();
            }
            return v8Array.get(i2);
        } catch (V8ResultUndefined unused) {
            return null;
        }
    }

    public static long getBuildID() {
        return _getBuildID();
    }

    private Object getDefaultValue(Class<?> cls) {
        if (cls.equals(V8Object.class)) {
            return new V8Object.Undefined();
        }
        if (cls.equals(V8Array.class)) {
            return new V8Array.Undefined();
        }
        return invalid;
    }

    public static String getSCMRevision() {
        return "Unknown revision ID";
    }

    public static V8Value getUndefined() {
        return undefined;
    }

    public static String getV8Version() {
        return _getVersion();
    }

    private Object getVarArgContainer(Class<?>[] clsArr, int i2) {
        Class<?> cls = clsArr[clsArr.length - 1];
        if (cls.isArray()) {
            cls = cls.getComponentType();
        }
        return Array.newInstance(cls, i2);
    }

    public static boolean isLoaded() {
        return nativeLibraryLoaded;
    }

    public static boolean isNodeCompatible() {
        if (!nativeLibraryLoaded) {
            synchronized (lock) {
                if (!nativeLibraryLoaded) {
                    load(null);
                }
            }
        }
        return _isNodeCompatible();
    }

    private boolean isVoidMethod(Method method) {
        return method.getReturnType().equals(Void.TYPE);
    }

    private static synchronized void load(String str) {
        synchronized (V8.class) {
            try {
                LibraryLoader.loadLibrary(str);
                nativeLibraryLoaded = true;
            } catch (Error e2) {
                nativeLoadError = e2;
            } catch (Exception e3) {
                nativeLoadException = e3;
            }
        }
    }

    private void notifyReferenceCreated(V8Value v8Value) {
        Iterator<ReferenceHandler> it = this.referenceHandlers.iterator();
        while (it.hasNext()) {
            it.next().v8HandleCreated(v8Value);
        }
    }

    private void notifyReferenceDisposed(V8Value v8Value) {
        Iterator<ReferenceHandler> it = this.referenceHandlers.iterator();
        while (it.hasNext()) {
            it.next().v8HandleDisposed(v8Value);
        }
    }

    private void notifyReleaseHandlers(V8 v8) {
        Iterator<V8Runnable> it = this.releaseHandlers.iterator();
        while (it.hasNext()) {
            it.next().run(v8);
        }
    }

    private void populateParamters(V8Array v8Array, int i2, Object[] objArr, List<Object> list, boolean z) {
        for (int i3 = z ? 1 : 0; i3 < v8Array.length() + (z ? 1 : 0); i3++) {
            if (i3 >= i2) {
                list.add(getArrayItem(v8Array, i3 - (z ? 1 : 0)));
            } else {
                objArr[i3] = getArrayItem(v8Array, i3 - (z ? 1 : 0));
            }
        }
    }

    private void releaseArguments(Object[] objArr, boolean z) {
        if (z && objArr.length > 0 && (objArr[objArr.length - 1] instanceof Object[])) {
            for (Object obj : (Object[]) objArr[objArr.length - 1]) {
                if (obj instanceof V8Value) {
                    ((V8Value) obj).close();
                }
            }
        }
        for (Object obj2 : objArr) {
            if (obj2 instanceof V8Value) {
                ((V8Value) obj2).close();
            }
        }
    }

    private void releaseNativeMethodDescriptors() {
        Iterator<Long> it = this.functionRegistry.keySet().iterator();
        while (it.hasNext()) {
            releaseMethodDescriptor(this.v8RuntimePtr, it.next().longValue());
        }
    }

    private void releaseResources() {
        List<Releasable> list = this.resources;
        if (list != null) {
            Iterator<Releasable> it = list.iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            this.resources.clear();
            this.resources = null;
        }
    }

    private Object[] setDefaultValues(Object[] objArr, Class<?>[] clsArr, V8Object v8Object, boolean z) {
        int i2 = 0;
        if (z) {
            objArr[0] = v8Object;
            i2 = 1;
        }
        while (i2 < objArr.length) {
            objArr[i2] = getDefaultValue(clsArr[i2]);
            i2++;
        }
        return objArr;
    }

    public static void setFlags(String str) {
        v8Flags = str;
        initialized = false;
    }

    public void acquireLock(long j2) {
        _acquireLock(j2);
    }

    public void add(long j2, long j3, String str, int i2) {
        _add(j2, j3, str, i2);
    }

    public void addArrayBooleanItem(long j2, long j3, boolean z) {
        _addArrayBooleanItem(j2, j3, z);
    }

    public void addArrayDoubleItem(long j2, long j3, double d) {
        _addArrayDoubleItem(j2, j3, d);
    }

    public void addArrayIntItem(long j2, long j3, int i2) {
        _addArrayIntItem(j2, j3, i2);
    }

    public void addArrayNullItem(long j2, long j3) {
        _addArrayNullItem(j2, j3);
    }

    public void addArrayObjectItem(long j2, long j3, long j4) {
        _addArrayObjectItem(j2, j3, j4);
    }

    public void addArrayStringItem(long j2, long j3, String str) {
        _addArrayStringItem(j2, j3, str);
    }

    public void addArrayUndefinedItem(long j2, long j3) {
        _addArrayUndefinedItem(j2, j3);
    }

    public void addNull(long j2, long j3, String str) {
        _addNull(j2, j3, str);
    }

    public void addObjRef(V8Value v8Value) {
        this.objectReferences++;
        if (this.referenceHandlers.isEmpty()) {
            return;
        }
        notifyReferenceCreated(v8Value);
    }

    public void addObject(long j2, long j3, String str, long j4) {
        _addObject(j2, j3, str, j4);
    }

    public void addReferenceHandler(ReferenceHandler referenceHandler) {
        this.referenceHandlers.add(0, referenceHandler);
    }

    public void addReleaseHandler(V8Runnable v8Runnable) {
        this.releaseHandlers.add(v8Runnable);
    }

    public void addUndefined(long j2, long j3, String str) {
        _addUndefined(j2, j3, str);
    }

    public Object arrayGet(long j2, int i2, long j3, int i3) {
        return _arrayGet(j2, i2, j3, i3);
    }

    public boolean arrayGetBoolean(long j2, long j3, int i2) {
        return _arrayGetBoolean(j2, j3, i2);
    }

    public boolean[] arrayGetBooleans(long j2, long j3, int i2, int i3) {
        return _arrayGetBooleans(j2, j3, i2, i3);
    }

    public byte arrayGetByte(long j2, long j3, int i2) {
        return _arrayGetByte(j2, j3, i2);
    }

    public byte[] arrayGetBytes(long j2, long j3, int i2, int i3) {
        return _arrayGetBytes(j2, j3, i2, i3);
    }

    public double arrayGetDouble(long j2, long j3, int i2) {
        return _arrayGetDouble(j2, j3, i2);
    }

    public double[] arrayGetDoubles(long j2, long j3, int i2, int i3) {
        return _arrayGetDoubles(j2, j3, i2, i3);
    }

    public int arrayGetInteger(long j2, long j3, int i2) {
        return _arrayGetInteger(j2, j3, i2);
    }

    public int[] arrayGetIntegers(long j2, long j3, int i2, int i3) {
        return _arrayGetIntegers(j2, j3, i2, i3);
    }

    public int arrayGetSize(long j2, long j3) {
        return _arrayGetSize(j2, j3);
    }

    public String arrayGetString(long j2, long j3, int i2) {
        return _arrayGetString(j2, j3, i2);
    }

    public String[] arrayGetStrings(long j2, long j3, int i2, int i3) {
        return _arrayGetStrings(j2, j3, i2, i3);
    }

    protected Object callObjectJavaMethod(long j2, V8Object v8Object, V8Array v8Array) throws Throwable {
        MethodDescriptor methodDescriptor = this.functionRegistry.get(Long.valueOf(j2));
        JavaCallback javaCallback = methodDescriptor.callback;
        if (javaCallback != null) {
            return checkResult(javaCallback.invoke(v8Object, v8Array));
        }
        boolean isVarArgs = methodDescriptor.method.isVarArgs();
        Object[] args = getArgs(v8Object, methodDescriptor, v8Array, isVarArgs);
        checkArgs(args);
        try {
            try {
                try {
                    try {
                        return checkResult(methodDescriptor.method.invoke(methodDescriptor.object, args));
                    } catch (IllegalArgumentException e2) {
                        throw e2;
                    }
                } catch (IllegalAccessException e3) {
                    throw e3;
                }
            } catch (InvocationTargetException e4) {
                throw e4.getTargetException();
            }
        } finally {
            releaseArguments(args, isVarArgs);
        }
    }

    protected void callVoidJavaMethod(long j2, V8Object v8Object, V8Array v8Array) throws Throwable {
        MethodDescriptor methodDescriptor = this.functionRegistry.get(Long.valueOf(j2));
        JavaVoidCallback javaVoidCallback = methodDescriptor.voidCallback;
        if (javaVoidCallback != null) {
            javaVoidCallback.invoke(v8Object, v8Array);
            return;
        }
        boolean isVarArgs = methodDescriptor.method.isVarArgs();
        Object[] args = getArgs(v8Object, methodDescriptor, v8Array, isVarArgs);
        checkArgs(args);
        try {
            try {
                methodDescriptor.method.invoke(methodDescriptor.object, args);
            } catch (IllegalAccessException e2) {
                throw e2;
            } catch (IllegalArgumentException e3) {
                throw e3;
            } catch (InvocationTargetException e4) {
                throw e4.getTargetException();
            }
        } finally {
            releaseArguments(args, isVarArgs);
        }
    }

    public void checkRuntime(V8Value v8Value) {
        if (v8Value == null || v8Value.isUndefined()) {
            return;
        }
        V8 runtime = v8Value.getRuntime();
        if (runtime == null || runtime.isReleased() || runtime != this) {
            throw new Error("Invalid target runtime");
        }
    }

    public void checkThread() {
        this.locker.checkThread();
        if (isReleased()) {
            throw new Error("Runtime disposed error");
        }
    }

    public void clearWeak(long j2, long j3) {
        _clearWeak(j2, j3);
    }

    @Override // com.eclipsesource.v8.V8Value, com.eclipsesource.v8.Releasable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        release(true);
    }

    public boolean contains(long j2, long j3, String str) {
        return _contains(j2, j3, str);
    }

    public void createAndRegisterMethodDescriptor(JavaCallback javaCallback, long j2) {
        MethodDescriptor methodDescriptor = new MethodDescriptor();
        methodDescriptor.callback = javaCallback;
        this.functionRegistry.put(Long.valueOf(j2), methodDescriptor);
    }

    public long createInspector(V8InspectorDelegate v8InspectorDelegate, String str) {
        return _createInspector(this.v8RuntimePtr, v8InspectorDelegate, str);
    }

    public void createNodeRuntime(String str) {
        _startNodeJS(this.v8RuntimePtr, str);
    }

    public void createTwin(V8Value v8Value, V8Value v8Value2) {
        checkThread();
        createTwin(this.v8RuntimePtr, v8Value.getHandle(), v8Value2.getHandle());
    }

    public ByteBuffer createV8ArrayBufferBackingStore(long j2, long j3, int i2) {
        return _createV8ArrayBufferBackingStore(j2, j3, i2);
    }

    public void dispatchProtocolMessage(long j2, String str) {
        checkThread();
        _dispatchProtocolMessage(this.v8RuntimePtr, j2, str);
    }

    protected void disposeMethodID(long j2) {
        this.functionRegistry.remove(Long.valueOf(j2));
    }

    public boolean equals(long j2, long j3, long j4) {
        return _equals(j2, j3, j4);
    }

    public V8Array executeArrayScript(String str) {
        return executeArrayScript(str, null, 0);
    }

    public boolean executeBooleanFunction(long j2, long j3, String str, long j4) {
        return _executeBooleanFunction(j2, j3, str, j4);
    }

    public boolean executeBooleanScript(String str) {
        return executeBooleanScript(str, null, 0);
    }

    public double executeDoubleFunction(long j2, long j3, String str, long j4) {
        return _executeDoubleFunction(j2, j3, str, j4);
    }

    public double executeDoubleScript(String str) {
        return executeDoubleScript(str, null, 0);
    }

    public Object executeFunction(long j2, int i2, long j3, String str, long j4) {
        return _executeFunction(j2, i2, j3, str, j4);
    }

    public int executeIntegerFunction(long j2, long j3, String str, long j4) {
        return _executeIntegerFunction(j2, j3, str, j4);
    }

    public int executeIntegerScript(String str) {
        return executeIntegerScript(str, null, 0);
    }

    public Object executeModule(String str, String str2, String str3, String str4) {
        checkThread();
        checkScript(str);
        return executeScript(getV8RuntimePtr(), 0, str2 + str + str3, str4, 0);
    }

    public V8Object executeObjectScript(String str) {
        return executeObjectScript(str, null, 0);
    }

    public Object executeScript(String str) {
        return executeScript(str, null, 0);
    }

    public String executeStringFunction(long j2, long j3, String str, long j4) {
        return _executeStringFunction(j2, j3, str, j4);
    }

    public String executeStringScript(String str) {
        return executeStringScript(str, null, 0);
    }

    public void executeVoidFunction(long j2, long j3, String str, long j4) {
        _executeVoidFunction(j2, j3, str, j4);
    }

    public void executeVoidScript(String str) {
        executeVoidScript(str, null, 0);
    }

    public Object get(long j2, int i2, long j3, String str) {
        return _get(j2, i2, j3, str);
    }

    public int getArrayType(long j2, long j3) {
        return _getArrayType(j2, j3);
    }

    public boolean getBoolean(long j2, long j3, String str) {
        return _getBoolean(j2, j3, str);
    }

    public String getConstructorName(long j2, long j3) {
        return _getConstructorName(j2, j3);
    }

    public Object getData(String str) {
        Map<String, Object> map = this.data;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public double getDouble(long j2, long j3, String str) {
        return _getDouble(j2, j3, str);
    }

    public V8Executor getExecutor(V8Object v8Object) {
        checkThread();
        V8Map<V8Executor> v8Map = this.executors;
        if (v8Map == null) {
            return null;
        }
        return v8Map.get(v8Object);
    }

    public int getInteger(long j2, long j3, String str) {
        return _getInteger(j2, j3, str);
    }

    public String[] getKeys(long j2, long j3) {
        return _getKeys(j2, j3);
    }

    public V8Locker getLocker() {
        return this.locker;
    }

    public long getObjectReferenceCount() {
        return this.objectReferences - this.v8WeakReferences.size();
    }

    public String getString(long j2, long j3, String str) {
        return _getString(j2, j3, str);
    }

    public int getType(long j2, long j3) {
        return _getType(j2, j3);
    }

    public long getV8RuntimePtr() {
        return this.v8RuntimePtr;
    }

    public int identityHash(long j2, long j3) {
        return _identityHash(j2, j3);
    }

    protected long initEmptyContainer(long j2) {
        return _initEmptyContainer(j2);
    }

    public long initNewV8Array(long j2) {
        return _initNewV8Array(j2);
    }

    public long initNewV8ArrayBuffer(long j2, ByteBuffer byteBuffer, int i2) {
        return _initNewV8ArrayBuffer(j2, byteBuffer, i2);
    }

    public long initNewV8Float32Array(long j2, long j3, int i2, int i3) {
        return _initNewV8Float32Array(j2, j3, i2, i3);
    }

    public long initNewV8Float64Array(long j2, long j3, int i2, int i3) {
        return _initNewV8Float64Array(j2, j3, i2, i3);
    }

    public long[] initNewV8Function(long j2) {
        checkThread();
        return _initNewV8Function(j2);
    }

    public long initNewV8Int16Array(long j2, long j3, int i2, int i3) {
        return _initNewV8Int16Array(j2, j3, i2, i3);
    }

    public long initNewV8Int32Array(long j2, long j3, int i2, int i3) {
        return _initNewV8Int32Array(j2, j3, i2, i3);
    }

    public long initNewV8Int8Array(long j2, long j3, int i2, int i3) {
        return _initNewV8Int8Array(j2, j3, i2, i3);
    }

    public long initNewV8Object(long j2) {
        return _initNewV8Object(j2);
    }

    public long initNewV8UInt16Array(long j2, long j3, int i2, int i3) {
        return _initNewV8UInt16Array(j2, j3, i2, i3);
    }

    public long initNewV8UInt32Array(long j2, long j3, int i2, int i3) {
        return _initNewV8UInt32Array(j2, j3, i2, i3);
    }

    public long initNewV8UInt8Array(long j2, long j3, int i2, int i3) {
        return _initNewV8UInt8Array(j2, j3, i2, i3);
    }

    public long initNewV8UInt8ClampedArray(long j2, long j3, int i2, int i3) {
        return _initNewV8UInt8ClampedArray(j2, j3, i2, i3);
    }

    public boolean isRunning() {
        return _isRunning(this.v8RuntimePtr);
    }

    public boolean isWeak(long j2, long j3) {
        return _isWeak(j2, j3);
    }

    public void lowMemoryNotification() {
        checkThread();
        lowMemoryNotification(getV8RuntimePtr());
    }

    public boolean pumpMessageLoop() {
        return _pumpMessageLoop(this.v8RuntimePtr);
    }

    public void registerCallback(Object obj, Method method, long j2, String str, boolean z) {
        MethodDescriptor methodDescriptor = new MethodDescriptor();
        methodDescriptor.object = obj;
        methodDescriptor.method = method;
        methodDescriptor.includeReceiver = z;
        this.functionRegistry.put(Long.valueOf(registerJavaMethod(getV8RuntimePtr(), j2, str, isVoidMethod(method))), methodDescriptor);
    }

    protected long registerJavaMethod(long j2, long j3, String str, boolean z) {
        return _registerJavaMethod(j2, j3, str, z);
    }

    public void registerResource(Releasable releasable) {
        checkThread();
        if (this.resources == null) {
            this.resources = new ArrayList();
        }
        this.resources.add(releasable);
    }

    public void registerV8Executor(V8Object v8Object, V8Executor v8Executor) {
        checkThread();
        if (this.executors == null) {
            this.executors = new V8Map<>();
        }
        this.executors.put2((V8Value) v8Object, (V8Object) v8Executor);
    }

    public void registerVoidCallback(JavaVoidCallback javaVoidCallback, long j2, String str) {
        MethodDescriptor methodDescriptor = new MethodDescriptor();
        methodDescriptor.voidCallback = javaVoidCallback;
        this.functionRegistry.put(Long.valueOf(registerJavaMethod(getV8RuntimePtr(), j2, str, true)), methodDescriptor);
    }

    @Override // com.eclipsesource.v8.V8Value, com.eclipsesource.v8.Releasable
    @Deprecated
    public void release() {
        release(true);
    }

    public void releaseLock(long j2) {
        _releaseLock(j2);
    }

    protected void releaseMethodDescriptor(long j2, long j3) {
        _releaseMethodDescriptor(j2, j3);
    }

    public void releaseObjRef(V8Value v8Value) {
        if (!this.referenceHandlers.isEmpty()) {
            notifyReferenceDisposed(v8Value);
        }
        this.objectReferences--;
    }

    public V8Executor removeExecutor(V8Object v8Object) {
        checkThread();
        V8Map<V8Executor> v8Map = this.executors;
        if (v8Map == null) {
            return null;
        }
        return v8Map.remove(v8Object);
    }

    public void removeReferenceHandler(ReferenceHandler referenceHandler) {
        this.referenceHandlers.remove(referenceHandler);
    }

    public void removeReleaseHandler(V8Runnable v8Runnable) {
        this.releaseHandlers.remove(v8Runnable);
    }

    protected boolean sameValue(long j2, long j3, long j4) {
        return _sameValue(j2, j3, j4);
    }

    public void schedulePauseOnNextStatement(long j2, String str) {
        checkThread();
        _schedulePauseOnNextStatement(this.v8RuntimePtr, j2, str);
    }

    public synchronized void setData(String str, Object obj) {
        if (this.data == null) {
            this.data = new HashMap();
        }
        this.data.put(str, obj);
    }

    public void setPrototype(long j2, long j3, long j4) {
        _setPrototype(j2, j3, j4);
    }

    public void setSignatureProvider(SignatureProvider signatureProvider) {
        this.signatureProvider = signatureProvider;
    }

    public void setWeak(long j2, long j3) {
        _setWeak(j2, j3);
    }

    public void shutdownExecutors(boolean z) {
        checkThread();
        V8Map<V8Executor> v8Map = this.executors;
        if (v8Map == null) {
            return;
        }
        for (V8Executor v8Executor : v8Map.values()) {
            if (z) {
                v8Executor.forceTermination();
            } else {
                v8Executor.shutdown();
            }
        }
    }

    public boolean strictEquals(long j2, long j3, long j4) {
        return _strictEquals(j2, j3, j4);
    }

    public void terminateExecution() {
        this.forceTerminateExecutors = true;
        terminateExecution(this.v8RuntimePtr);
    }

    public String toString(long j2, long j3) {
        return _toString(j2, j3);
    }

    protected void weakReferenceReleased(long j2) {
        V8Value v8Value = this.v8WeakReferences.get(Long.valueOf(j2));
        if (v8Value != null) {
            this.v8WeakReferences.remove(Long.valueOf(j2));
            try {
                v8Value.close();
            } catch (Exception unused) {
            }
        }
    }

    protected V8(String str) {
        super(null);
        this.v8WeakReferences = new HashMap();
        this.data = null;
        this.signatureProvider = null;
        this.objectReferences = 0L;
        this.v8RuntimePtr = 0L;
        this.resources = null;
        this.executors = null;
        this.forceTerminateExecutors = false;
        this.functionRegistry = new HashMap();
        this.referenceHandlers = new LinkedList<>();
        this.releaseHandlers = new LinkedList<>();
        this.released = false;
        this.v8RuntimePtr = _createIsolate(str);
        this.locker = new V8Locker(this);
        checkThread();
        this.objectHandle = _getGlobalObject(this.v8RuntimePtr);
    }

    public static V8 createV8Runtime(String str) {
        return createV8Runtime(str, null);
    }

    public void add(long j2, long j3, String str, boolean z) {
        _add(j2, j3, str, z);
    }

    public int arrayGetBooleans(long j2, long j3, int i2, int i3, boolean[] zArr) {
        return _arrayGetBooleans(j2, j3, i2, i3, zArr);
    }

    public int arrayGetBytes(long j2, long j3, int i2, int i3, byte[] bArr) {
        return _arrayGetBytes(j2, j3, i2, i3, bArr);
    }

    public int arrayGetDoubles(long j2, long j3, int i2, int i3, double[] dArr) {
        return _arrayGetDoubles(j2, j3, i2, i3, dArr);
    }

    public int arrayGetIntegers(long j2, long j3, int i2, int i3, int[] iArr) {
        return _arrayGetIntegers(j2, j3, i2, i3, iArr);
    }

    public int arrayGetStrings(long j2, long j3, int i2, int i3, String[] strArr) {
        return _arrayGetStrings(j2, j3, i2, i3, strArr);
    }

    public V8Array executeArrayScript(String str, String str2, int i2) {
        checkThread();
        Object executeScript = executeScript(str, str2, i2);
        if (executeScript instanceof V8Array) {
            return (V8Array) executeScript;
        }
        throw new V8ResultUndefined();
    }

    public boolean executeBooleanScript(String str, String str2, int i2) {
        checkThread();
        checkScript(str);
        return executeBooleanScript(this.v8RuntimePtr, str, str2, i2);
    }

    public double executeDoubleScript(String str, String str2, int i2) {
        checkThread();
        checkScript(str);
        return executeDoubleScript(this.v8RuntimePtr, str, str2, i2);
    }

    public Object executeFunction(long j2, long j3, long j4, long j5) {
        return _executeFunction(j2, j3, j4, j5);
    }

    public int executeIntegerScript(String str, String str2, int i2) {
        checkThread();
        checkScript(str);
        return executeIntegerScript(this.v8RuntimePtr, str, str2, i2);
    }

    public V8Object executeObjectScript(String str, String str2, int i2) {
        checkThread();
        Object executeScript = executeScript(str, str2, i2);
        if (executeScript instanceof V8Object) {
            return (V8Object) executeScript;
        }
        throw new V8ResultUndefined();
    }

    public Object executeScript(String str, String str2) {
        checkThread();
        checkScript(str);
        return executeScript(getV8RuntimePtr(), 0, str, str2, 0);
    }

    public String executeStringScript(String str, String str2, int i2) {
        checkThread();
        checkScript(str);
        return executeStringScript(this.v8RuntimePtr, str, str2, i2);
    }

    public void executeVoidScript(String str, String str2, int i2) {
        checkThread();
        checkScript(str);
        executeVoidScript(this.v8RuntimePtr, str, str2, i2);
    }

    public int getType(long j2, long j3, String str) {
        return _getType(j2, j3, str);
    }

    public long initNewV8ArrayBuffer(long j2, int i2) {
        return _initNewV8ArrayBuffer(j2, i2);
    }

    public void release(boolean z) {
        if (isReleased()) {
            return;
        }
        checkThread();
        try {
            notifyReleaseHandlers(this);
            releaseResources();
            shutdownExecutors(this.forceTerminateExecutors);
            V8Map<V8Executor> v8Map = this.executors;
            if (v8Map != null) {
                v8Map.clear();
            }
            releaseNativeMethodDescriptors();
            synchronized (lock) {
                runtimeCounter--;
            }
            _releaseRuntime(this.v8RuntimePtr);
            this.v8RuntimePtr = 0L;
            this.released = true;
            if (!z || getObjectReferenceCount() <= 0) {
                return;
            }
            throw new IllegalStateException(getObjectReferenceCount() + " Object(s) still exist in runtime");
        } catch (Throwable th) {
            releaseResources();
            shutdownExecutors(this.forceTerminateExecutors);
            if (this.executors != null) {
                this.executors.clear();
            }
            releaseNativeMethodDescriptors();
            synchronized (lock) {
                runtimeCounter--;
                _releaseRuntime(this.v8RuntimePtr);
                this.v8RuntimePtr = 0L;
                this.released = true;
                if (!z || getObjectReferenceCount() <= 0) {
                    throw th;
                }
                throw new IllegalStateException(getObjectReferenceCount() + " Object(s) still exist in runtime");
            }
        }
    }

    public static V8 createV8Runtime(String str, String str2) {
        if (!nativeLibraryLoaded) {
            synchronized (lock) {
                if (!nativeLibraryLoaded) {
                    load(str2);
                }
            }
        }
        checkNativeLibraryLoaded();
        if (!initialized) {
            _setFlags(v8Flags);
            initialized = true;
        }
        V8 v8 = new V8(str);
        synchronized (lock) {
            runtimeCounter++;
        }
        return v8;
    }

    public void add(long j2, long j3, String str, double d) {
        _add(j2, j3, str, d);
    }

    protected void createTwin(long j2, long j3, long j4) {
        _createTwin(j2, j3, j4);
    }

    public int getType(long j2, long j3, int i2) {
        return _getType(j2, j3, i2);
    }

    protected void lowMemoryNotification(long j2) {
        _lowMemoryNotification(j2);
    }

    protected void terminateExecution(long j2) {
        _terminateExecution(j2);
    }

    public void add(long j2, long j3, String str, String str2) {
        _add(j2, j3, str, str2);
    }

    public int getType(long j2, long j3, int i2, int i3) {
        return _getType(j2, j3, i2, i3);
    }

    protected boolean executeBooleanScript(long j2, String str, String str2, int i2) {
        return _executeBooleanScript(j2, str, str2, i2);
    }

    protected double executeDoubleScript(long j2, String str, String str2, int i2) {
        return _executeDoubleScript(j2, str, str2, i2);
    }

    protected int executeIntegerScript(long j2, String str, String str2, int i2) {
        return _executeIntegerScript(j2, str, str2, i2);
    }

    public Object executeScript(String str, String str2, int i2) {
        checkThread();
        checkScript(str);
        return executeScript(getV8RuntimePtr(), 0, str, str2, i2);
    }

    protected String executeStringScript(long j2, String str, String str2, int i2) {
        return _executeStringScript(j2, str, str2, i2);
    }

    protected void executeVoidScript(long j2, String str, String str2, int i2) {
        _executeVoidScript(j2, str, str2, i2);
    }

    public void registerCallback(JavaCallback javaCallback, long j2, String str) {
        createAndRegisterMethodDescriptor(javaCallback, registerJavaMethod(getV8RuntimePtr(), j2, str, false));
    }

    protected Object executeScript(long j2, int i2, String str, String str2, int i3) {
        return _executeScript(j2, i2, str, str2, i3);
    }

    public void release(long j2, long j3) {
        _release(j2, j3);
    }
}
