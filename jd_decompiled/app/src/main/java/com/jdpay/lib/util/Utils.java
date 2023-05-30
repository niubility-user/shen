package com.jdpay.lib.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.exception.JPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Random;

/* loaded from: classes18.dex */
public class Utils {
    public static String createRandom(int i2) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt(36)));
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.lang.Process] */
    public static String exeCommand(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2;
        try {
            try {
                str = Runtime.getRuntime().exec(str);
                try {
                    bufferedReader2 = new BufferedReader(new InputStreamReader(str.getInputStream()), 1048576);
                } catch (IOException e2) {
                    e = e2;
                    bufferedReader2 = null;
                } catch (Throwable th2) {
                    bufferedReader = null;
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (str != 0) {
                        str.destroy();
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                str = 0;
                bufferedReader2 = null;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
                str = 0;
            }
            try {
                String readLine = bufferedReader2.readLine();
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                if (str != 0) {
                    str.destroy();
                }
                return readLine;
            } catch (IOException e6) {
                e = e6;
                e.printStackTrace();
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                if (str != 0) {
                    str.destroy();
                }
                return null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static String getJPThrowableMessage(@Nullable Throwable th) {
        if (th instanceof JPException) {
            return th.getLocalizedMessage();
        }
        return null;
    }

    public static Type getParameterUpperBound(int i2, ParameterizedType parameterizedType) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (i2 >= 0 && i2 < actualTypeArguments.length) {
            Type type = actualTypeArguments[i2];
            return type instanceof WildcardType ? ((WildcardType) type).getUpperBounds()[0] : type;
        }
        throw new IllegalArgumentException("Index " + i2 + " not in range [0," + actualTypeArguments.length + ") for " + parameterizedType);
    }

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (rawType instanceof Class) {
                return (Class) rawType;
            }
            throw new IllegalArgumentException();
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return getRawType(((WildcardType) type).getUpperBounds()[0]);
            }
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + type.getClass().getName());
        }
    }

    public static String getThrowableMessage(@Nullable Throwable th) {
        if (th == null) {
            return null;
        }
        return th.getLocalizedMessage();
    }

    public static boolean isSameClass(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == obj2) {
            return true;
        }
        return (obj == null || obj2 == null || obj.getClass() != obj2.getClass()) ? false : true;
    }

    public static Object newInstance(@Nullable Class cls) {
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (Throwable th) {
                JDPayLog.e(th);
                return null;
            }
        }
        return null;
    }

    public static String toHttps(@NonNull String str) {
        return str.replace("http:", "https:");
    }
}
