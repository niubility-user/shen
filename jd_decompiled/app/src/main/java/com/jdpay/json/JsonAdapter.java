package com.jdpay.json;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.util.JDPayLog;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public class JsonAdapter {
    private static volatile JsonConverterFactory converterFactory;
    private static volatile NameStrategy defaultNameStrategy;

    public static synchronized <T> T create(String str) {
        T t;
        synchronized (JsonAdapter.class) {
            try {
                t = (T) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable unused) {
                return null;
            }
        }
        return t;
    }

    public static synchronized NameStrategy getDefaultNameStrategy() {
        NameStrategy nameStrategy;
        synchronized (JsonAdapter.class) {
            nameStrategy = defaultNameStrategy;
        }
        return nameStrategy;
    }

    public static void init() {
        if (converterFactory == null) {
            String[] strArr = {"com.jdpay.json.gson.GsonConverterFactory", "com.jdpay.json.fastjson.FastjsonConverterFactory"};
            int i2 = 0;
            while (true) {
                if (i2 >= 2) {
                    break;
                }
                String str = strArr[i2];
                converterFactory = (JsonConverterFactory) create(str);
                if (converterFactory != null) {
                    JDPayLog.i("JsonConverterFactory:" + str);
                    break;
                }
                i2++;
            }
        }
        if (defaultNameStrategy == null) {
            String[] strArr2 = {"com.jdpay.json.gson.GsonNameStrategy", "com.jdpay.json.fastjson.FastjsonNameStrategy"};
            for (int i3 = 0; i3 < 2; i3++) {
                String str2 = strArr2[i3];
                defaultNameStrategy = (NameStrategy) create(str2);
                if (defaultNameStrategy != null) {
                    JDPayLog.i("JsonNameStrategy:" + str2);
                    return;
                }
            }
        }
    }

    public static JsonObjectConverter newObjectConverter(@NonNull Type type, @Nullable NameStrategy nameStrategy) {
        init();
        return converterFactory.convertObject(type, nameStrategy);
    }

    public static JsonStringConverter newStringConverter(@Nullable NameStrategy nameStrategy, @Nullable Class<? extends Annotation>[] clsArr, @Nullable Class<? extends Annotation>[] clsArr2) {
        init();
        return converterFactory.convertString(nameStrategy, clsArr, clsArr2);
    }

    public static <T> T object(@NonNull String str, @NonNull Type type) throws Throwable {
        return (T) object(str, type, defaultNameStrategy);
    }

    public static <T> T objectSafety(@NonNull String str, @NonNull Type type) {
        return (T) objectSafety(str, type, defaultNameStrategy);
    }

    public static synchronized void setDefaultNameStrategy(NameStrategy nameStrategy) {
        synchronized (JsonAdapter.class) {
            defaultNameStrategy = nameStrategy;
        }
    }

    public static synchronized void setJsonConverterFactory(@NonNull JsonConverterFactory jsonConverterFactory) {
        synchronized (JsonAdapter.class) {
            converterFactory = jsonConverterFactory;
        }
    }

    public static String string(@NonNull Object obj) throws Throwable {
        return string(obj, defaultNameStrategy, null, null);
    }

    public static String stringSafety(@NonNull Object obj) {
        return stringSafety(obj, defaultNameStrategy, null, null);
    }

    public static <T> T object(@NonNull String str, @NonNull Type type, @Nullable NameStrategy nameStrategy) throws Throwable {
        return (T) newObjectConverter(type, nameStrategy).convert(str);
    }

    public static <T> T objectSafety(@NonNull String str, @NonNull Type type, @Nullable NameStrategy nameStrategy) {
        try {
            return (T) object(str, type, nameStrategy);
        } catch (Throwable th) {
            JDPayLog.e(th);
            return null;
        }
    }

    public static String string(@NonNull Object obj, @Nullable Class<? extends Annotation>[] clsArr) throws Throwable {
        return string(obj, defaultNameStrategy, clsArr, null);
    }

    public static String stringSafety(@NonNull Object obj, @Nullable Class<? extends Annotation>[] clsArr) {
        return stringSafety(obj, defaultNameStrategy, clsArr, null);
    }

    public static String string(@NonNull Object obj, @Nullable NameStrategy nameStrategy, @Nullable Class<? extends Annotation>[] clsArr, @Nullable Class<? extends Annotation>[] clsArr2) throws Throwable {
        init();
        return converterFactory.convertString(nameStrategy, clsArr, clsArr2).convert(obj);
    }

    public static String stringSafety(@NonNull Object obj, @Nullable NameStrategy nameStrategy, @Nullable Class<? extends Annotation>[] clsArr, @Nullable Class<? extends Annotation>[] clsArr2) {
        try {
            return string(obj, nameStrategy, clsArr, clsArr2);
        } catch (Throwable th) {
            JDPayLog.e(th);
            return null;
        }
    }
}
