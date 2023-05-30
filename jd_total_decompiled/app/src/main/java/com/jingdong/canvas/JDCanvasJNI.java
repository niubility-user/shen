package com.jingdong.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class JDCanvasJNI {
    public static volatile boolean GCanvaslibEnable;
    private static List<Runnable> callbacks;
    static Map<String, Integer> contextTypeMap;
    static Map<String, Double> devicePixelRatioMap;
    private static volatile boolean isRuntimeEnable;
    static Map<String, Boolean> qualityMap;

    static {
        loadInternal("StaticBlock");
        contextTypeMap = new HashMap();
        devicePixelRatioMap = new HashMap();
        qualityMap = new HashMap();
    }

    public static native void addFallbackFontFamily(String[] strArr);

    public static native void addFontFamily(String[] strArr, String[] strArr2);

    public static synchronized void addGCanvasInitCallback(Runnable runnable) {
        synchronized (JDCanvasJNI.class) {
            if (callbacks == null) {
                callbacks = new ArrayList();
            }
            callbacks.add(runnable);
            tryExecCallbacks();
        }
    }

    public static native void bindTexture(String str, Bitmap bitmap, int i2, int i3, int i4, int i5, int i6, int i7);

    public static native void bindTexture9(String str, Bitmap bitmap, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public static native String callNative(int i2, String str, String str2);

    public static native String getImageData(String str, int i2, int i3, int i4, int i5);

    public static native int getNativeFps(String str);

    public static native void glTexImage2D2B(String str, ByteBuffer byteBuffer, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public static native boolean isFboSupport(String str);

    public static native boolean isNeonSupport();

    public static void load() {
        loadInternal("load()");
    }

    private static void loadInternal(String str) {
        if (GCanvaslibEnable) {
            return;
        }
        com.jingdong.canvas.b.a.f("CANVAS", "GCanvasJNI init from " + str);
        try {
            GCanvaslibEnable = true;
            System.loadLibrary("freetype");
            System.loadLibrary("jdcanvas");
            setFontFamilies();
        } catch (Exception e2) {
            GCanvaslibEnable = false;
            com.jingdong.canvas.b.a.c("CANVAS", "fail to load gcanvas." + e2.getLocalizedMessage());
        } catch (UnsatisfiedLinkError e3) {
            GCanvaslibEnable = false;
            com.jingdong.canvas.b.a.c("CANVAS", "gcanvas is not found." + e3.getLocalizedMessage());
        }
        com.jingdong.canvas.b.a.f("CANVAS", "GCanvasJNI init end---- ");
    }

    public static void loadRuntime() {
        if (isRuntimeEnable) {
            return;
        }
        com.jingdong.canvas.b.a.f("CANVAS", "GCanvasJNI load runtime ");
        try {
            isRuntimeEnable = true;
            System.loadLibrary("c++_shared");
            System.loadLibrary("gcanvas_runtime");
        } catch (Exception e2) {
            isRuntimeEnable = false;
            com.jingdong.canvas.b.a.c("CANVAS", "fail to load gcanvas_runtime" + e2.getLocalizedMessage());
        } catch (UnsatisfiedLinkError e3) {
            isRuntimeEnable = false;
            com.jingdong.canvas.b.a.c("CANVAS", "gcanvas_runtime is not found." + e3.getLocalizedMessage());
        }
    }

    public static void refreshArguments(String str) {
        Integer num = contextTypeMap.get(str);
        if (num != null) {
            setContextType(str, num.intValue());
        }
        Double d = devicePixelRatioMap.get(str);
        if (d != null) {
            setDevicePixelRatio(str, d.doubleValue());
        }
        Boolean bool = qualityMap.get(str);
        if (bool != null) {
            setHiQuality(str, bool.booleanValue());
        }
    }

    public static native void registerCallback(String str, int i2);

    public static void registerWXCallNativeFunc(Context context) {
        String str;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            str = context.getApplicationInfo().nativeLibraryDir + "/libweexcore.so";
            com.jingdong.canvas.b.a.e("start to test load gcanvas library,path=" + str);
        } else {
            str = null;
        }
        registerCallback(str, i2);
    }

    public static native void release();

    public static native void render(String str, String str2);

    public static native boolean sendEvent(String str);

    public static native void setClearColor(String str, String str2);

    public static native void setConfig(String str, int i2);

    public static native void setContextType(String str, int i2);

    public static native void setDevicePixelRatio(String str, double d);

    public static native void setFallbackFont(String str, String str2);

    public static void setFontFamilies() {
        a aVar = new a();
        setFallbackFont(aVar.a(), aVar.d());
        HashMap<List<String>, List<String>> c2 = aVar.c();
        if (c2 != null) {
            com.jingdong.canvas.b.a.b("CANVAS", "setFontFamilies() fontFamilies.size() " + c2.size());
        } else {
            com.jingdong.canvas.b.a.b("CANVAS", "setFontFamilies() error! fontFamilies is empty");
        }
        if (c2 != null) {
            for (List<String> list : c2.keySet()) {
                int size = list.size();
                String[] strArr = new String[size];
                for (int i2 = 0; i2 < size; i2++) {
                    strArr[i2] = list.get(i2);
                }
                List<String> list2 = c2.get(list);
                int size2 = list2.size();
                String[] strArr2 = new String[size2];
                for (int i3 = 0; i3 < size2; i3++) {
                    strArr2[i3] = list2.get(i3);
                }
                addFontFamily(strArr, strArr2);
            }
        }
        List<String> b = aVar.b();
        if (b == null) {
            return;
        }
        int size3 = b.size();
        String[] strArr3 = new String[size3];
        for (int i4 = 0; i4 < size3; i4++) {
            strArr3[i4] = b.get(i4);
        }
        addFallbackFontFamily(strArr3);
    }

    public static native void setHiQuality(String str, boolean z);

    public static native void setLogLevel(String str);

    public static native void setOrtho(String str, int i2, int i3);

    public static native void setPreCompilePath(String str);

    public static native void setTyOffsetFlag(String str, boolean z);

    public static void setWrapperContextType(String str, int i2) {
        setContextType(str, i2);
        contextTypeMap.put(str, Integer.valueOf(i2));
    }

    public static void setWrapperDevicePixelRatio(String str, double d) {
        setDevicePixelRatio(str, d);
        devicePixelRatioMap.put(str, Double.valueOf(d));
    }

    public static void setWrapperHiQuality(String str, boolean z) {
        setHiQuality(str, z);
        qualityMap.put(str, Boolean.valueOf(z));
    }

    public static native void texSubImage2D(String str, Bitmap bitmap, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    private static synchronized void tryExecCallbacks() {
        synchronized (JDCanvasJNI.class) {
            if (callbacks == null) {
                return;
            }
            if (GCanvaslibEnable) {
                Iterator<Runnable> it = callbacks.iterator();
                while (it.hasNext()) {
                    it.next().run();
                }
                callbacks.clear();
            }
        }
    }
}
