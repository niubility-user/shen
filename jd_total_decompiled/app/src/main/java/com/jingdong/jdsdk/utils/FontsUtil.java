package com.jingdong.jdsdk.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class FontsUtil {
    public static final String KEY_MULTI_BOLD = "bold";
    public static final String KEY_MULTI_LIGHT = "light";
    public static final String KEY_MULTI_REGULAR = "regular";
    public static final int MULTI_BOLD = 4097;
    public static final int MULTI_LIGHT = 4098;
    public static final int MULTI_REGULAR = 4099;
    private static final String TAG = "FontsUtil";
    private static HashMap<String, Typeface> sTypefaceContainer = new HashMap<>();

    public static void changeTextFont(@NonNull TextView textView, int i2) {
        if (textView == null || textView.getContext() == null) {
            return;
        }
        Typeface typeFace = getTypeFace(textView.getContext(), i2);
        if (typeFace != null) {
            textView.setTypeface(typeFace);
        }
        switch (i2) {
            case 4097:
                OKLog.d(TAG, "changed font by JDZhengHT-Bold.ttf");
                return;
            case 4098:
                OKLog.d(TAG, "changed font by JDZhengHT-Light.ttf");
                return;
            case 4099:
                OKLog.d(TAG, "changed font by JDZhengHT-Regular.ttf");
                return;
            default:
                OKLog.d(TAG, "changed font by JDZhengHT-Regular.ttf");
                return;
        }
    }

    public static Typeface getTypeFace(@NonNull Context context, int i2) {
        Typeface typeface;
        if (context == null) {
            return null;
        }
        switch (i2) {
            case 4097:
                typeface = sTypefaceContainer.get("bold");
                if (typeface == null) {
                    Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Bold.ttf");
                    sTypefaceContainer.put("bold", createFromAsset);
                    typeface = createFromAsset;
                }
                OKLog.d(TAG, "get bold typeface");
                break;
            case 4098:
                typeface = sTypefaceContainer.get(KEY_MULTI_LIGHT);
                if (typeface == null) {
                    Typeface createFromAsset2 = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Light.ttf");
                    sTypefaceContainer.put(KEY_MULTI_LIGHT, createFromAsset2);
                    typeface = createFromAsset2;
                }
                OKLog.d(TAG, "get light typeface");
                break;
            case 4099:
                typeface = sTypefaceContainer.get(KEY_MULTI_REGULAR);
                if (typeface == null) {
                    typeface = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Regular.ttf");
                    sTypefaceContainer.put(KEY_MULTI_REGULAR, typeface);
                }
                OKLog.d(TAG, "get ragular typeface");
                break;
            default:
                typeface = sTypefaceContainer.get(KEY_MULTI_REGULAR);
                if (typeface == null) {
                    Typeface createFromAsset3 = Typeface.createFromAsset(context.getAssets(), "fonts/JDZhengHT-Regular.ttf");
                    sTypefaceContainer.put(KEY_MULTI_REGULAR, createFromAsset3);
                    typeface = createFromAsset3;
                }
                OKLog.d(TAG, "get ragular typeface");
                break;
        }
        return typeface;
    }

    public static void changeTextFont(@NonNull TextView textView) {
        changeTextFont(textView, 4099);
    }

    public static Typeface getTypeFace(@NonNull Context context) {
        return getTypeFace(context, 4099);
    }
}
