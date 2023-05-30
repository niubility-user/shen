package com.jingdong.common.sample.jshop.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.sample.jshop.Entity.DynamicShopActivity;
import com.jingdong.common.sample.jshop.Entity.DynamicShopEntity;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.platform.business.personal.R2;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class JShopUtil {
    public static final int DEC_SIZE = 12;
    public static final int PRICE_SIZE = 17;
    public static final int SAMS_SIZE = 13;
    private static final String TAG = "JShopUtil";
    private static Pattern pattern = Pattern.compile("\\d+");

    public static String checkImageUrl(String str) {
        return !TextUtils.isEmpty(str) ? str : "http://";
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0033, code lost:
        r1 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String formatPrice(String str) {
        String substring;
        if (isPrice(str) && str.contains(OrderISVUtil.MONEY_DECIMAL)) {
            int length = str.length() - 1;
            int i2 = length;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                char charAt = str.charAt(i2);
                if (charAt == '0' || charAt == '.') {
                    if (charAt == '.') {
                        break;
                    }
                    i2--;
                } else if (i2 != length) {
                    if (i2 < length) {
                        i2++;
                    }
                }
            }
            String str2 = "";
            if (i2 == length) {
                try {
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (str.charAt(length) != '0') {
                    substring = str.substring(0);
                    str2 = substring;
                    Log.d(TAG, "price before ,  ==> :  " + str + " ,  price substring  ==> : " + str2);
                    return str2;
                }
            }
            if (i2 > 0) {
                substring = str.substring(0, i2);
                str2 = substring;
            }
            Log.d(TAG, "price before ,  ==> :  " + str + " ,  price substring  ==> : " + str2);
            return str2;
        }
        return str;
    }

    public static String formatReduceDiscount(double d) {
        String str;
        try {
            str = new DecimalFormat("0.00").format(d);
            try {
                if (!TextUtils.isEmpty(str)) {
                    char charAt = str.charAt(str.length() - 1);
                    char charAt2 = str.charAt(str.length() - 2);
                    if ('0' == charAt && str.contains(OrderISVUtil.MONEY_DECIMAL)) {
                        str = '0' == charAt2 ? str.substring(0, str.indexOf(OrderISVUtil.MONEY_DECIMAL)) : str.substring(0, str.length() - 1);
                    }
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return str;
            }
        } catch (Exception e3) {
            e = e3;
            str = "";
        }
        return str;
    }

    public static String getActivityType(String str, int i2) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c2 = 0;
                    break;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    c2 = 1;
                    break;
                }
                break;
            case 51:
                if (str.equals("3")) {
                    c2 = 2;
                    break;
                }
                break;
            case 52:
                if (str.equals("4")) {
                    c2 = 3;
                    break;
                }
                break;
            case 53:
                if (str.equals("5")) {
                    c2 = 4;
                    break;
                }
                break;
            case 54:
                if (str.equals("6")) {
                    c2 = 5;
                    break;
                }
                break;
            case 55:
                if (str.equals("7")) {
                    c2 = 6;
                    break;
                }
                break;
            case 56:
                if (str.equals("8")) {
                    c2 = 7;
                    break;
                }
                break;
            case R2.attr.progress_cancel /* 1567 */:
                if (str.equals("10")) {
                    c2 = '\b';
                    break;
                }
                break;
            case R2.attr.prospectNum /* 1568 */:
                if (str.equals("11")) {
                    c2 = '\t';
                    break;
                }
                break;
            case R2.attr.prospectStyle /* 1569 */:
                if (str.equals("12")) {
                    c2 = '\n';
                    break;
                }
                break;
            case R2.attr.pstsDividerColor /* 1570 */:
                if (str.equals("13")) {
                    c2 = 11;
                    break;
                }
                break;
            case R2.attr.pstsDividerLeftRightMargin /* 1571 */:
                if (str.equals("14")) {
                    c2 = '\f';
                    break;
                }
                break;
            case R2.attr.pstsDividerPadding /* 1572 */:
                if (str.equals("15")) {
                    c2 = '\r';
                    break;
                }
                break;
            case R2.attr.pstsDividerWidth /* 1573 */:
                if (str.equals("16")) {
                    c2 = 14;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return "\u4e0a\u65b0";
            case 1:
                return "\u4fc3\u9500";
            case 2:
                return "\u4e13\u9898";
            case 3:
                return "\u79d2\u6740";
            case 4:
                return "\u54c1\u724c\u79d2\u6740";
            case 5:
                return "\u56e2\u8d2d";
            case 6:
                return "\u54c1\u724c\u56e2\u8d2d";
            case 7:
                return "\u6ee1\u51cf";
            case '\b':
                return "\u5e97\u94fa\u79d2\u6740";
            case '\t':
                return i2 == 1 ? "\u5e97\u94fa\u7b7e\u5230" : "\u5e97\u94fa\u62bd\u5956";
            case '\n':
                return "\u9884\u552e";
            case 11:
                return "\u76f4\u64ad";
            case '\f':
                return "\u4f18\u60e0\u5238";
            case '\r':
                return "\u8bd5\u7528";
            case 14:
                return "\u4e70\u5bb6\u79c0";
            default:
                return DYConstants.DY_NULL_STR;
        }
    }

    public static float getMaxSamsPriceWidth(Context context, String str) {
        TextView textView = new TextView(context);
        textView.setTextSize(1, 17.0f);
        float measureText = textView.getPaint().measureText(JshopConst.JSHOP_SAMS_MAX_PRICE);
        Log.d(TAG, "jdMaxWidth = " + measureText);
        textView.setTextSize(1, 12.0f);
        float measureText2 = textView.getPaint().measureText(context.getString(R.string.pg_home_jdpirce, ".00"));
        Log.d(TAG, "decimalWidth = " + measureText2);
        textView.setTextSize(1, 13.0f);
        float measureText3 = textView.getPaint().measureText(context.getString(R.string.pg_home_jdpirce, str));
        Log.d(TAG, "samsMaxWidth = " + measureText3);
        float dip2px = measureText + measureText2 + measureText3 + 56.0f + ((float) DPIUtil.dip2px(10.0f));
        Log.d(TAG, "all price width = " + dip2px);
        return dip2px;
    }

    public static String getMtaString(String str) {
        return TextUtils.isEmpty(str) ? "none" : str;
    }

    public static int getScrollY(AbsListView absListView) {
        View childAt = absListView.getChildAt(0);
        if (childAt == null) {
            return 0;
        }
        int firstVisiblePosition = absListView.getFirstVisiblePosition();
        return (firstVisiblePosition * childAt.getHeight()) - childAt.getTop();
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean isFollowGift(int i2) {
        return i2 == 4;
    }

    public static boolean isNorch(Context context) {
        return UnStatusBarTintUtil.getStatusBarHeight(context) > 80;
    }

    public static boolean isPrice(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return pattern.matcher(str).lookingAt();
    }

    public static boolean isToPublishPrice(String str) {
        return !TextUtils.isEmpty(str) && str.equals("\u5f85\u53d1\u5e03");
    }

    public static void jumpAliveRoom(MyActivity myActivity, DynamicShopEntity dynamicShopEntity, DynamicShopActivity dynamicShopActivity, ImageView imageView, TextView textView, ImageView imageView2) {
        jumpAliveRoom(myActivity, dynamicShopEntity, dynamicShopActivity, imageView, textView, imageView2, null);
    }

    public static void jumpWithOpenApp(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Uri parse = Uri.parse(str);
        if (Log.D) {
            Log.d("jumpWithOpenApp", "uri -->> " + parse.toString());
        }
        if (parse.getScheme() == null) {
            return;
        }
        if (parse.getScheme().contains(OpenAppConstant.SCHEME_OPENAPP_1) || parse.getScheme().contains("openapp.jdmobile")) {
            new OpenAppJumpBuilder.Builder(parse).build().jump(context);
        }
    }

    public static void mtaUploadWithRequestUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setPost(false);
        httpSetting.setUrl(str);
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(0);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void setActivityIcon57(Context context, TextView textView, String str, long j2) {
        Drawable drawable;
        textView.setVisibility(0);
        textView.setText(str);
        if (1 == j2) {
            drawable = context.getResources().getDrawable(R.drawable.jshop_dynamic_new);
        } else if (2 == j2) {
            drawable = context.getResources().getDrawable(R.drawable.jshop_dynamic_promotion);
        } else if (4 == j2 || 5 == j2 || 10 == j2) {
            drawable = context.getResources().getDrawable(R.drawable.jshop_dynamic_seckill);
        } else if (8 == j2) {
            drawable = context.getResources().getDrawable(R.drawable.jshop_dynamic_manjian);
        } else if (12 == j2) {
            drawable = context.getResources().getDrawable(R.drawable.jshop_dynamic_presale);
        } else if (15 == j2) {
            drawable = context.getResources().getDrawable(R.drawable.jshop_dynamic_tryout);
        } else if (6 != j2 && 7 != j2) {
            drawable = 16 == j2 ? context.getResources().getDrawable(R.drawable.jshop_dynamic_buyer_show) : null;
        } else {
            drawable = context.getResources().getDrawable(R.drawable.jshop_dynamic_groupon);
        }
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            textView.setCompoundDrawables(drawable, null, null, null);
            textView.setCompoundDrawablePadding(DPIUtil.dip2px(10.0f));
            return;
        }
        textView.setCompoundDrawables(null, null, null, null);
    }

    public static void setIconAfterPrice(JDJSONObject jDJSONObject, TextView textView) {
        textView.setVisibility(8);
        textView.setText("");
        if (jDJSONObject != null) {
            if (!TextUtils.isEmpty(jDJSONObject.optString("5"))) {
                textView.setBackgroundResource(R.drawable.fz);
                textView.setText(R.string.jshop_zeng);
                textView.setVisibility(0);
            } else if (!TextUtils.isEmpty(jDJSONObject.optString("4"))) {
                textView.setBackgroundResource(R.drawable.fz);
                textView.setText(R.string.jshop_dou);
                textView.setVisibility(0);
            } else if (TextUtils.isEmpty(jDJSONObject.optString("100"))) {
            } else {
                textView.setBackgroundResource(R.drawable.product_price_gird_vip);
                textView.setVisibility(0);
            }
        }
    }

    public static void showProductMask(TextView textView, long j2, int i2) {
        if (j2 != 4 && j2 != 5 && j2 != 6 && j2 != 7 && j2 != 10) {
            textView.setVisibility(8);
        } else if (i2 == 0) {
            textView.setVisibility(0);
            textView.setText(R.string.jshop_pd_status_qiang);
        } else if (i2 == -1) {
            textView.setVisibility(0);
            textView.setText(R.string.jshop_pd_status_end);
        } else {
            textView.setVisibility(8);
        }
    }

    public static void toWeb(BaseActivity baseActivity, String str) {
        toWeb(baseActivity, str, false, false);
    }

    public static void toWebWithLogin(BaseActivity baseActivity, String str) {
        toWebWithLogin(baseActivity, str, false, false);
    }

    public static void updateAliveView(Context context, ImageView imageView, TextView textView, DynamicShopActivity dynamicShopActivity) {
        imageView.setVisibility(8);
        textView.setVisibility(8);
        if (dynamicShopActivity != null) {
            imageView.setVisibility(0);
            int i2 = dynamicShopActivity.liveShowStatus;
            if (i2 == 0) {
                imageView.setBackgroundResource(R.drawable.jshop_alive_foreshow);
                textView.setVisibility(0);
                textView.setText(new SimpleDateFormat("MM\u6708dd\u65e5 HH:mm").format(Long.valueOf(dynamicShopActivity.liveShowPublishTime)));
            } else if (i2 == 1) {
                imageView.setBackgroundResource(R.drawable.jshop_alive_ing);
            } else if (i2 == 2) {
                imageView.setBackgroundResource(R.drawable.jshop_alive_playback_creating);
            } else if (i2 != 3) {
                imageView.setVisibility(8);
                textView.setVisibility(8);
            } else {
                imageView.setBackgroundResource(R.drawable.jshop_alive_playback);
            }
        }
    }

    public static void jumpAliveRoom(MyActivity myActivity, DynamicShopEntity dynamicShopEntity, DynamicShopActivity dynamicShopActivity, ImageView imageView, TextView textView, final ImageView imageView2, final IOnJumpAliveRoomListener iOnJumpAliveRoomListener) {
        if (imageView2 != null) {
            imageView2.setEnabled(false);
        }
        jumpAliveRoom(myActivity, dynamicShopActivity.bizId + "", 0);
        myActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.utils.JShopUtil.1
            @Override // java.lang.Runnable
            public void run() {
                ImageView imageView3 = imageView2;
                if (imageView3 != null) {
                    imageView3.setEnabled(true);
                }
                IOnJumpAliveRoomListener iOnJumpAliveRoomListener2 = iOnJumpAliveRoomListener;
                if (iOnJumpAliveRoomListener2 != null) {
                    iOnJumpAliveRoomListener2.onJumpSuccess();
                }
            }
        }, 1000);
    }

    public static void toWeb(BaseActivity baseActivity, String str, boolean z, boolean z2) {
        if (baseActivity == null || TextUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent(baseActivity, WebActivity.class);
        intent.putExtra("url", str);
        intent.putExtra(MBaseKeyNames.IS_USE_RIGHT_BUTTON, z);
        intent.putExtra(MBaseKeyNames.IS_NEED_SHARE, z2);
        baseActivity.startActivity(intent);
    }

    public static void toWebWithLogin(BaseActivity baseActivity, String str, boolean z, boolean z2) {
        if (baseActivity == null || TextUtils.isEmpty(str)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        Intent intent = new Intent(baseActivity, WebActivity.class);
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        intent.putExtra("urlParamMap", serializableContainer);
        intent.putExtra("urlAction", RemoteMessageConst.TO);
        intent.putExtra(MBaseKeyNames.IS_USE_RIGHT_BUTTON, z);
        intent.putExtra(MBaseKeyNames.IS_NEED_SHARE, z2);
        baseActivity.startActivity(intent);
    }

    public static void jumpAliveRoom(MyActivity myActivity, String str, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("id", str);
        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_FIND_LIVE_PRE, myActivity, bundle);
    }
}
