package com.jingdong.app.mall.bundle.order_center_isv_core.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.order_center_isv_core.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDImageLoadingProgressListener;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.tencent.smtt.sdk.WebView;
import java.util.Random;

/* loaded from: classes3.dex */
public class OrderISVUtil {
    public static final String MONEY_DECIMAL = ".";
    public static final char MONEY_DECIMAL_CHAR = '.';
    public static final String MONEY_STRING = ".00";
    public static final int PRICE_THREE = 3;
    private static final String TAG = "OrderUtil";
    public static final String ZERO = "0";
    private static Random random;

    public static void addImageDarkOrLightFilter(ImageView imageView) {
        if (imageView != null) {
            try {
                if (JDDarkUtil.isDarkMode()) {
                    imageView.setColorFilter(Color.parseColor(JDDarkUtil.COLOR_33000000));
                } else {
                    imageView.setColorFilter(Color.parseColor(JDDarkUtil.COLOR_05000000));
                }
            } catch (Exception unused) {
            }
        }
    }

    public static boolean checkActivityIsAct(FragmentActivity fragmentActivity) {
        if (fragmentActivity == null || fragmentActivity.isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return !fragmentActivity.isDestroyed();
        }
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        return (supportFragmentManager == null || supportFragmentManager.isDestroyed()) ? false : true;
    }

    public static int computeHeight(View view) {
        if (view != null) {
            view.measure(0, 0);
            return view.getMeasuredHeight();
        }
        return 0;
    }

    public static int computeWidth(View view) {
        if (view != null) {
            view.measure(0, 0);
            return view.getMeasuredWidth();
        }
        return 0;
    }

    public static final void displayImage(final int i2, final String str, final ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, boolean z, final JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        if (imageView == null) {
            return;
        }
        Object tag = imageView.getTag(i2);
        if ((tag instanceof CharSequence) && imageView.getDrawable() != null && TextUtils.equals((CharSequence) tag, str)) {
            return;
        }
        imageView.setTag(i2, null);
        JDImageUtils.displayImage(str, imageView, jDDisplayImageOptions, z, new JDImageLoadingListener() { // from class: com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil.1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
                JDImageLoadingListener jDImageLoadingListener2 = JDImageLoadingListener.this;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingCancelled(str2, view);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                imageView.setTag(i2, str);
                JDImageLoadingListener jDImageLoadingListener2 = JDImageLoadingListener.this;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingComplete(str2, view, bitmap);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                JDImageLoadingListener jDImageLoadingListener2 = JDImageLoadingListener.this;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingFailed(str2, view, jDFailReason);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
                JDImageLoadingListener jDImageLoadingListener2 = JDImageLoadingListener.this;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingStarted(str2, view);
                }
            }
        }, jDImageLoadingProgressListener);
    }

    public static SpannableString formatPriceText(String str) {
        SpannableString spannableString = new SpannableString(str);
        try {
            int indexOf = str.indexOf(MONEY_DECIMAL);
            if (indexOf != -1) {
                spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, indexOf - 1, 33);
                spannableString.setSpan(new AbsoluteSizeSpan(13, true), indexOf, str.length(), 33);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return spannableString;
    }

    public static String getFormatString(Context context, @NonNull int i2, @NonNull String str) {
        if (str == null) {
            return "";
        }
        long j2 = 0;
        try {
            j2 = Long.parseLong(str);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "parse orderId" + e2.getMessage());
            }
        }
        String string = (context == null || context.getResources() == null) ? "" : context.getResources().getString(i2, Long.valueOf(j2));
        if (Log.D) {
            Log.d(TAG, "formatted str" + string);
        }
        return "".equals(string) ? str : string;
    }

    public static String getJsonNode(JDJSONObject jDJSONObject, String str) {
        return getNode(jDJSONObject, str.split("\\."), 0);
    }

    public static SpannableString getLabelUnionText(String str, String str2) {
        String str3 = "";
        if (!TextUtils.isEmpty(str)) {
            str3 = "" + str;
        }
        if (!TextUtils.isEmpty(str2)) {
            str3 = str3 + str2;
        }
        SpannableString spannableString = new SpannableString(str3);
        spannableString.setSpan(new StyleSpan(1), 0, !TextUtils.isEmpty(str) ? str.length() : 0, 18);
        return spannableString;
    }

    private static String getNode(JDJSONObject jDJSONObject, String[] strArr, int i2) {
        if (jDJSONObject == null || strArr == null || i2 >= strArr.length) {
            return "";
        }
        if (i2 == strArr.length - 1) {
            return jDJSONObject.optString(strArr[i2], "");
        }
        return getNode(jDJSONObject.optJSONObject(strArr[i2]), strArr, i2 + 1);
    }

    public static String getPrice(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= 3) {
            return str;
        }
        if (str.endsWith(".00")) {
            return str.substring(0, str.length() - 3);
        }
        return (str.charAt(str.length() - 3) == '.' && str.endsWith("0")) ? str.substring(0, str.length() - 1) : str;
    }

    public static int getRandomIn(int i2, int i3) {
        if (random == null) {
            random = new Random();
        }
        return random.nextInt(i3) + i2;
    }

    public static SpannableString getRmbString(String str, int i2, int i3) {
        if (!TextUtils.isEmpty(str) && str.length() > 0) {
            SpannableString spannableString = new SpannableString(str);
            if (TextUtils.equals(str.substring(0, 1), "\u00a5")) {
                spannableString.setSpan(new AbsoluteSizeSpan(i2, true), 0, 1, 17);
                int indexOf = str.indexOf(MONEY_DECIMAL);
                if (indexOf != -1) {
                    spannableString.setSpan(new AbsoluteSizeSpan(i3, true), 1, indexOf, 17);
                    spannableString.setSpan(new AbsoluteSizeSpan(i2, true), indexOf, str.length(), 17);
                    return spannableString;
                }
                spannableString.setSpan(new AbsoluteSizeSpan(i3, true), 1, str.length(), 17);
                return spannableString;
            }
            return spannableString;
        }
        return new SpannableString("");
    }

    public static String getString(Context context, @NonNull int i2) {
        String string = (context == null || context.getResources() == null) ? "" : context.getString(i2);
        return string == null ? "" : string;
    }

    public static int getTextWidth(Context context, String str, float f2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        Paint paint = new Paint();
        paint.setTextSize(f2 * context.getResources().getDisplayMetrics().density);
        return (int) paint.measureText(str);
    }

    public static int getUnDisplayWidth(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return view.getMeasuredWidth();
    }

    public static String getUnionText(String str, String str2) {
        String str3 = "";
        if (!TextUtils.isEmpty(str)) {
            str3 = "" + str;
        }
        if (TextUtils.isEmpty(str2)) {
            return str3;
        }
        return str3 + str2;
    }

    public static void hideSoftInput(View view, Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isTrulyLeaveActivity(Activity activity) {
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        return (activity == null || currentMyActivity == null || !ProcessUtil.isForeground() || TextUtils.equals(activity.getComponentName().getClassName(), currentMyActivity.getThisActivity().getComponentName().getClassName())) ? false : true;
    }

    public static int optInt(Intent intent, String str) {
        return intent.getIntExtra(str, -1);
    }

    public static String optIntentString(Intent intent, String str) {
        return optString(intent, str, "");
    }

    public static String optString(Intent intent, String str, String str2) {
        String stringExtra = intent.getStringExtra(str);
        return stringExtra != null ? stringExtra : str2;
    }

    public static int optionsAlphaColor(String str, float f2) {
        String optionsColorString = optionsColorString(str);
        if (TextUtils.isEmpty(optionsColorString)) {
            return 0;
        }
        try {
            int parseColor = Color.parseColor(optionsColorString);
            return Color.argb((int) (f2 * 255 * 0.95f), Color.red(parseColor), Color.green(parseColor), Color.blue(parseColor));
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static String optionsColorString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.startsWith("#")) {
            return str;
        }
        return "#" + str;
    }

    public static long pareStr2Long(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(TAG, "parse string to long in exception\n" + e2.getMessage());
                e2.printStackTrace();
            }
            return -1L;
        }
    }

    public static int parseColor(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Color.parseColor(str);
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public static int parseStr2Int(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            if (Log.E) {
                Log.e(TAG, "parse string to int in exception\n" + e2.getMessage());
                e2.printStackTrace();
            }
            return -1;
        }
    }

    public static String replaceRMB(Context context, String str) {
        String string = getString(context, R.string.order_center_isv_core_lib_order_center_rmb);
        return !TextUtils.isEmpty(str) ? str.contains(string) ? str.replace(string, getString(context, R.string.order_center_isv_core_lib_order_center_rmb_smaller)) : str : "";
    }

    public static void sendEmail(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse(WebView.SCHEME_MAILTO));
        intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        try {
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(Intent.createChooser(intent, context.getString(R.string.order_center_isv_core_email_to)));
            } else {
                JDDialogFactory.getInstance().createJdDialogWithStyle1(context, context.getString(R.string.order_center_isv_core_email_failed, str), context.getString(R.string.order_center_isv_core_lib_oc_i_know)).show();
            }
        } catch (ActivityNotFoundException unused) {
        }
    }

    public static void showSoftInput(View view, Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 0);
    }

    public static boolean softInputIsActive(Context context) {
        return ((InputMethodManager) context.getSystemService("input_method")).isActive();
    }

    public static SpannableString spanPrice(String str, float f2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        int indexOf = str.indexOf(MONEY_DECIMAL);
        if (indexOf != -1) {
            try {
                spannableString.setSpan(new RelativeSizeSpan(f2), 0, indexOf, 33);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return spannableString;
    }

    public static String getString(int i2) {
        return getString(JdSdk.getInstance().getApplicationContext(), i2);
    }

    public static void addImageDarkOrLightFilter(ImageView imageView, String str, String str2, int i2) {
        if (imageView != null) {
            try {
                if (1 == i2) {
                    imageView.setColorFilter(Color.parseColor(str2));
                } else {
                    imageView.setColorFilter(Color.parseColor(str));
                }
            } catch (Exception unused) {
            }
        }
    }

    public static SpannableString getRmbString(String str, int i2, int i3, boolean z) {
        if (TextUtils.isEmpty(str) || !str.contains("\u00a5") || str.length() <= 0) {
            return new SpannableString("");
        }
        if (z) {
            int indexOf = str.indexOf(MONEY_DECIMAL);
            if (indexOf != -1) {
                String substring = str.substring(0, indexOf);
                if (substring.length() > 3) {
                    SpannableString spannableString = new SpannableString(substring);
                    spannableString.setSpan(new AbsoluteSizeSpan(i2, true), 0, 1, 17);
                    spannableString.setSpan(new AbsoluteSizeSpan(i3, true), 1, substring.length(), 17);
                    return spannableString;
                }
                return getRmbString(str, i2, i3);
            }
            SpannableString spannableString2 = new SpannableString(str);
            spannableString2.setSpan(new AbsoluteSizeSpan(i2, true), 0, 1, 17);
            spannableString2.setSpan(new AbsoluteSizeSpan(i3, true), 1, str.length(), 17);
            return spannableString2;
        }
        return getRmbString(str, i2, i3);
    }
}
