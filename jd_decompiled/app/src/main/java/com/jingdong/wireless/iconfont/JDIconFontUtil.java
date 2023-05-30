package com.jingdong.wireless.iconfont;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.wireless.iconfont.widget.IconImpl;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import jpbury.t;

/* loaded from: classes12.dex */
public class JDIconFontUtil {
    public static final String COMMON_PATH = "fonts".concat(File.separator).concat("common.ttf");
    private static final String NULL_CODE = "null_code";

    public static String decode(String str) {
        return (TextUtils.isEmpty(str) || !str.contains("&#")) ? str : Html.fromHtml(str).toString();
    }

    public static Typeface getTypeface(Context context, String str) {
        return getTypefaceWithMta(context, str, NULL_CODE);
    }

    public static Typeface getTypefaceWithMta(Context context, String str, String str2) {
        try {
            return Typeface.createFromAsset(context.getAssets(), str);
        } catch (Exception unused) {
            if (NULL_CODE.equals(str2)) {
                return null;
            }
            sendErrMta(context, str, str2);
            return null;
        }
    }

    public static void sendErrMta(Context context, String str, String str2) {
        try {
            Method method = Class.forName("com.jingdong.jdsdk.network.toolbox.ExceptionReporter").getMethod("sendExceptionData", Context.class, HashMap.class);
            HashMap hashMap = new HashMap();
            Locale locale = Locale.CHINA;
            Object[] objArr = new Object[1];
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            objArr[0] = Double.valueOf(currentTimeMillis / 1000.0d);
            hashMap.put("occurTime", String.format(locale, "%.6f", objArr));
            hashMap.put("errCode", "932");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("errMsg", unDecode(str2));
            hashMap.put("url", str);
            hashMap.put(t.f20145j, "Load ttf file error.");
            method.invoke(null, context, hashMap);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        } catch (Exception unused) {
        }
    }

    public static IconDrawable setCommonIcon(ImageView imageView, int i2) {
        if (imageView == null) {
            return null;
        }
        Context context = imageView.getContext();
        IconDrawable iconDrawable = new IconDrawable(context, new IconImpl("", context.getString(i2)), COMMON_PATH);
        imageView.setImageDrawable(iconDrawable);
        return iconDrawable;
    }

    public static IconDrawable setImageIcon(ImageView imageView, String str, String str2) {
        return setImageIcon(imageView, str, str2, -16777216);
    }

    @Deprecated
    public static IconDrawable setImageIcon1(ImageView imageView, String str, String str2) {
        if (imageView == null) {
            return null;
        }
        IconDrawable iconDrawable = new IconDrawable(imageView.getContext(), new IconImpl("", str2), str);
        imageView.setImageDrawable(iconDrawable);
        return iconDrawable;
    }

    public static void setTypeface(Context context, String str, TextView textView) {
        if (textView == null) {
            return;
        }
        textView.setTypeface(getTypeface(context, str));
    }

    static String unDecode(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (char c2 : str.toCharArray()) {
            sb.append(Integer.toHexString(c2));
            sb.append(',');
        }
        if (sb.length() >= 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static IconDrawable setImageIcon(ImageView imageView, String str, String str2, int i2) {
        if (imageView == null) {
            return null;
        }
        IconDrawable color = new IconDrawable(imageView.getContext(), new IconImpl("", str2), str).color(i2);
        imageView.setImageDrawable(color);
        return color;
    }

    @Deprecated
    public static IconDrawable setImageIcon1(ImageView imageView, String str, String str2, IConfigDrawable iConfigDrawable) {
        if (imageView == null) {
            return null;
        }
        IconDrawable iconDrawable = new IconDrawable(imageView.getContext(), new IconImpl("", str2), str);
        if (iConfigDrawable != null) {
            iConfigDrawable.configDrawable(iconDrawable);
        }
        imageView.setImageDrawable(iconDrawable);
        return iconDrawable;
    }

    public static IconDrawable setImageIcon(ImageView imageView, Typeface typeface, String str) {
        return setImageIcon(imageView, typeface, str, -16777216);
    }

    public static IconDrawable setImageIcon(ImageView imageView, Typeface typeface, String str, int i2) {
        if (imageView == null) {
            return null;
        }
        IconDrawable color = new IconDrawable(imageView.getContext(), new IconImpl("", str), typeface).color(i2);
        imageView.setImageDrawable(color);
        return color;
    }

    public static IconDrawable setImageIcon(ImageView imageView, String str, String str2, IConfigDrawable iConfigDrawable) {
        if (imageView == null) {
            return null;
        }
        IconDrawable iconDrawable = new IconDrawable(imageView.getContext(), new IconImpl("", str2), str);
        if (iConfigDrawable != null) {
            iConfigDrawable.configDrawable(iconDrawable);
        }
        imageView.setImageDrawable(iconDrawable);
        return iconDrawable;
    }
}
