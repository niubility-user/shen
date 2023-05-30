package com.tencent.smtt.export.external;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import dalvik.system.DexClassLoader;
import dalvik.system.VMStack;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes9.dex */
public class DexLoader {
    private static final String TAG = "DexLoader";
    private static final String TBS_FUSION_DEX = "tbs_jars_fusion_dex";
    private static final String TBS_WEBVIEW_DEX = "webview_dex";
    private static final String TENCENT_PACKAGE_PREFIX = "com.tencent";
    static boolean mCanUseDexLoaderProviderService = true;
    private static boolean mUseSpeedyClassLoader;
    private static boolean mUseTbsCorePrivateClassLoader;
    private DexClassLoader mClassLoader;
    private static final String CHROMIUM_PREFIX = "org.chromium";
    private static final String ANDROIDX_PREFIX = "androidx";
    private static final String TAF_PREFIX = "com.taf";
    private static final String CHROMIUM_J_N = "J.N";
    private static String[] mPrivatePrefix = {CHROMIUM_PREFIX, ANDROIDX_PREFIX, TAF_PREFIX, CHROMIUM_J_N};

    /* loaded from: classes9.dex */
    public static class TbsCorePrivateClassLoader extends DexClassLoader {
        public TbsCorePrivateClassLoader(String str, String str2, String str3, ClassLoader classLoader) {
            super(str, str2, str3, classLoader);
        }

        @Override // java.lang.ClassLoader
        protected Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            ClassLoader parent;
            if (str != null) {
                String[] strArr = DexLoader.mPrivatePrefix;
                int length = strArr.length;
                boolean z2 = false;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (str.startsWith(strArr[i2])) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z2) {
                    Class<?> findLoadedClass = findLoadedClass(str);
                    if (findLoadedClass == null) {
                        try {
                            findLoadedClass = findClass(str);
                        } catch (ClassNotFoundException unused) {
                        }
                        return (findLoadedClass != null || (parent = getParent()) == null) ? findLoadedClass : parent.loadClass(str);
                    }
                    return findLoadedClass;
                }
                return super.loadClass(str, z);
            }
            return super.loadClass(str, z);
        }
    }

    public DexLoader(Context context, String str, String str2) {
        this(context, new String[]{str}, str2);
    }

    public DexLoader(Context context, String[] strArr, String str) {
        this((String) null, context, strArr, str);
    }

    public DexLoader(Context context, String[] strArr, String str, DexLoader dexLoader) {
        DexClassLoader classLoader = dexLoader.getClassLoader();
        for (String str2 : strArr) {
            classLoader = createDexClassLoader(str2, str, context.getApplicationInfo().nativeLibraryDir, classLoader, context);
            this.mClassLoader = classLoader;
        }
    }

    public DexLoader(Context context, String[] strArr, String str, String str2) {
        ClassLoader classLoader = context.getClassLoader();
        String str3 = context.getApplicationInfo().nativeLibraryDir;
        str3 = TextUtils.isEmpty(str2) ? str3 : str3 + File.pathSeparator + str2;
        DexClassLoader dexClassLoader = classLoader;
        for (String str4 : strArr) {
            dexClassLoader = createDexClassLoader(str4, str, str3, dexClassLoader, context);
            this.mClassLoader = dexClassLoader;
        }
    }

    public DexLoader(String str, Context context, String[] strArr, String str2) {
        this(str, context, strArr, str2, null);
    }

    public DexLoader(String str, Context context, String[] strArr, String str2, Map<String, Object> map) {
        initTbsSettings(map);
        ClassLoader callingClassLoader = VMStack.getCallingClassLoader();
        callingClassLoader = callingClassLoader == null ? context.getClassLoader() : callingClassLoader;
        String str3 = "Set base classLoader for DexClassLoader: " + callingClassLoader;
        DexClassLoader dexClassLoader = callingClassLoader;
        for (String str4 : strArr) {
            dexClassLoader = createDexClassLoader(str4, str2, str, dexClassLoader, context);
            this.mClassLoader = dexClassLoader;
        }
    }

    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x01c3, code lost:
        if (r13 == null) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01c5, code lost:
        r13.e();
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01b2, code lost:
        if (r13 == null) goto L107;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private dalvik.system.DexClassLoader createDexClassLoader(java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.ClassLoader r20, android.content.Context r21) {
        /*
            Method dump skipped, instructions count: 590
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.export.external.DexLoader.createDexClassLoader(java.lang.String, java.lang.String, java.lang.String, java.lang.ClassLoader, android.content.Context):dalvik.system.DexClassLoader");
    }

    public static void delete(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            delete(file2);
        }
        file.delete();
    }

    public static String getFileNameNoEx(String str) {
        int lastIndexOf;
        return (str == null || str.length() <= 0 || (lastIndexOf = str.lastIndexOf(46)) <= -1 || lastIndexOf >= str.length()) ? str : str.substring(0, lastIndexOf);
    }

    private String getPrivateDexFilePath(String str, int i2) {
        Closeable closeable;
        if (Build.VERSION.SDK_INT < 29 || i2 < 29 || str == null) {
            return str;
        }
        File file = new File(str);
        File file2 = new File(file.getParent(), file.getName().replace(OrderISVUtil.MONEY_DECIMAL, ""));
        if (!file2.exists()) {
            Closeable closeable2 = null;
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec("ln -s " + str + LangUtils.SINGLE_SPACE + file2.getAbsolutePath()).getInputStream());
                try {
                    closeable = new BufferedReader(inputStreamReader);
                    do {
                        try {
                        } catch (Throwable th) {
                            th = th;
                            closeable2 = inputStreamReader;
                            try {
                                String str2 = "create PrivateDex failed : " + th;
                                return str;
                            } finally {
                                closeStream(closeable);
                                closeStream(closeable2);
                            }
                        }
                    } while (closeable.readLine() != null);
                    closeStream(closeable);
                    closeStream(inputStreamReader);
                    if (!file2.exists()) {
                        String str3 = "PrivateDex not exist, after ln -s " + str;
                        return str;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable = null;
                }
            } catch (Throwable th3) {
                th = th3;
                closeable = null;
            }
        }
        return file2.getAbsolutePath();
    }

    public static void initTbsSettings(Map<String, Object> map) {
        String str = "initTbsSettings - " + map;
        if (map != null) {
            try {
                Object obj = map.get(TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER);
                if (obj instanceof Boolean) {
                    mUseTbsCorePrivateClassLoader = ((Boolean) obj).booleanValue();
                }
                Object obj2 = map.get(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER);
                if (obj2 instanceof Boolean) {
                    mUseSpeedyClassLoader = ((Boolean) obj2).booleanValue();
                }
                Object obj3 = map.get(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE);
                if (obj3 instanceof Boolean) {
                    mCanUseDexLoaderProviderService = ((Boolean) obj3).booleanValue();
                }
                Object obj4 = map.get(TbsCoreSettings.TBS_SETTINGS_PRIVATE_CLASS_LIST);
                if (obj4 instanceof String) {
                    mPrivatePrefix = ((String) obj4).split(";");
                    String str2 = "PrivateClassPrefix: " + ((String) obj4);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private boolean shouldUseTbsCorePrivateClassLoader(String str) {
        if (mUseTbsCorePrivateClassLoader) {
            return str.contains(TBS_FUSION_DEX) || str.contains(TBS_WEBVIEW_DEX);
        }
        return false;
    }

    public DexClassLoader getClassLoader() {
        return this.mClassLoader;
    }

    public Object getStaticField(String str, String str2) {
        try {
            Field field = this.mClassLoader.loadClass(str).getField(str2);
            field.setAccessible(true);
            return field.get(null);
        } catch (Throwable unused) {
            getClass().getSimpleName();
            String str3 = "'" + str + "' get field '" + str2 + "' failed";
            return null;
        }
    }

    public Object invokeMethod(Object obj, String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = this.mClassLoader.loadClass(str).getMethod(str2, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (Throwable unused) {
            getClass().getSimpleName();
            String str3 = "'" + str + "' invoke method '" + str2 + "' failed";
            return null;
        }
    }

    public Object invokeStaticMethod(String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = this.mClassLoader.loadClass(str).getMethod(str2, clsArr);
            method.setAccessible(true);
            return method.invoke(null, objArr);
        } catch (Throwable th) {
            if (str2 == null || !str2.equalsIgnoreCase("initTesRuntimeEnvironment")) {
                getClass().getSimpleName();
                String str3 = "'" + str + "' invoke static method '" + str2 + "' failed";
                return null;
            }
            getClass().getSimpleName();
            String str4 = "'" + str + "' invoke static method '" + str2 + "' failed";
            return th;
        }
    }

    public Class<?> loadClass(String str) {
        try {
            return this.mClassLoader.loadClass(str);
        } catch (Throwable unused) {
            getClass().getSimpleName();
            String str2 = "loadClass '" + str + "' failed";
            return null;
        }
    }

    public Object newInstance(String str) {
        try {
            return this.mClassLoader.loadClass(str).newInstance();
        } catch (Throwable unused) {
            getClass().getSimpleName();
            String str2 = "create " + str + " instance failed";
            return null;
        }
    }

    public Object newInstance(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            return this.mClassLoader.loadClass(str).getConstructor(clsArr).newInstance(objArr);
        } catch (Throwable th) {
            if ("com.tencent.smtt.webkit.adapter.X5WebViewAdapter".equalsIgnoreCase(str)) {
                getClass().getSimpleName();
                String str2 = "'newInstance " + str + " failed";
                return th;
            }
            getClass().getSimpleName();
            String str3 = "create '" + str + "' instance failed";
            return null;
        }
    }

    public void setStaticField(String str, String str2, Object obj) {
        try {
            Field field = this.mClassLoader.loadClass(str).getField(str2);
            field.setAccessible(true);
            field.set(null, obj);
        } catch (Throwable unused) {
            getClass().getSimpleName();
            String str3 = "'" + str + "' set field '" + str2 + "' failed";
        }
    }
}
