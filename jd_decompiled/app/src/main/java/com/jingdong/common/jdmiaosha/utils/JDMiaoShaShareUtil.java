package com.jingdong.common.jdmiaosha.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.R;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.jdmiaosha.view.JDMiaoShaPriceView;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.widget.a.c;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.FileOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDMiaoShaShareUtil {
    private static final String CHECK_TYPE_FAIL = "-1";
    private static final String DEFAULT_CHANNEL = "Wxfriends";
    private static final int DEFAULT_MPTYPE = 0;
    private static final String DEFAULT_MP_ID = "gh_aef8a2901bf5";
    private static final String DEFAULT_SHARE_TITLE = "\u7231\u4f1a\u6d88\u5931\uff0c\u8865\u8d34\u4e0d\u4f1a\uff0c\u5feb\u6765\u4e00\u8d77\u6361\u4fbf\u5b9c\u5440\uff01";
    private static final String DEFAULT_SHARE_URL = "https://pro.m.jd.com/mall/active/4SV4KgmDfoKpmhpWcCdSNW4wx2U/index.html";
    private static final String FAIL_CODE = "1";
    private static final String NO_PRICE = "\u6682\u65e0\u62a5\u4ef7";
    private static final String SHARE_IMAGE_NAME = "image_share.png";
    private static final String SUB_PRICE = "\u8865\u8d34\u4ef7";
    private static final String SUCCESS_CODE = "0";
    private static final int shareWidth = DPIUtil.dip2px(200.0f);
    private static final int shareHeight = DPIUtil.dip2px(160.0f);
    private static final int MIAO_SHA_PRICE_COLOR = Color.parseColor("#F2270C");
    private static final int JD_PRICE_COLOR = Color.parseColor(JDDarkUtil.COLOR_999999);

    private static String checkOptString(JSONObject jSONObject, String str) {
        Object opt = jSONObject.opt(str);
        return opt == null ? "" : opt instanceof String ? jSONObject.optString(str) : "-1";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T convert(Object obj) {
        return obj;
    }

    public static void deleteShareImage(Context context) {
        File file = new File(context.getFilesDir().toString() + File.separator + SHARE_IMAGE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void getShareImgPathAndToshare(final Context context, String str, boolean z, final IRouterParams iRouterParams, Bitmap bitmap) {
        String str2;
        String str3;
        final String str4;
        final String str5;
        String str6;
        if (z) {
            goToShare(context, "", saveDefaultResources(context, R.drawable.icon_goods_pintuan_share), "", iRouterParams);
            return;
        }
        try {
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                if (!checkOptString(jSONObject, "miaoShaPrice").equals("-1") && !checkOptString(jSONObject, JshopConst.JSKEY_PRODUCT_JDPRICE).equals("-1") && !checkOptString(jSONObject, "imageUrl").equals("-1") && !checkOptString(jSONObject, "activityId").equals("-1") && !checkOptString(jSONObject, "shareCode").equals("-1") && !checkOptString(jSONObject, "groupCode").equals("-1") && !checkOptString(jSONObject, MBaseKeyNames.SHARE_TITLE).equals("-1")) {
                    str2 = jSONObject.optString("miaoShaPrice");
                    str6 = jSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE);
                    String optString = jSONObject.optString("imageUrl");
                    String optString2 = jSONObject.optString(MBaseKeyNames.SHARE_TITLE);
                    String optString3 = jSONObject.optString("activityId");
                    String optString4 = jSONObject.optString("shareCode");
                    String optString5 = jSONObject.optString("groupCode");
                    if (!TextUtils.isEmpty(optString3) && !TextUtils.isEmpty(optString4) && !TextUtils.isEmpty(optString5)) {
                        str4 = "pages/pintuanNew/pintuanDetail/pintuanDetail?activityId=" + optString3 + "&shareCode=" + optString4 + "&groupCode=" + optString5;
                        str5 = optString2;
                        str3 = optString;
                    }
                    onErroCallBack(iRouterParams);
                    return;
                }
                OKLog.d("JDMiaoShaUtils", "\u5165\u53c2\u7c7b\u578b\u4e0d\u5339\u914d");
                onErroCallBack(iRouterParams);
                return;
            }
            onErroCallBack(iRouterParams);
            str2 = "";
            str3 = str2;
            str4 = str3;
            str5 = str4;
            str6 = str5;
            final View layoutShareView = layoutShareView(str2, str6);
            if (layoutShareView == null) {
                goToShare(context, str4, saveDefaultResources(context, R.drawable.icon_goods_pintuan_share), str5, iRouterParams);
                return;
            }
            final SimpleDraweeView simpleDraweeView = (SimpleDraweeView) layoutShareView.findViewById(R.id.skuImg);
            if (bitmap != null) {
                simpleDraweeView.setImageBitmap(bitmap);
                String saveShareBmpFromView = saveShareBmpFromView(context, layoutShareView);
                goToShare(context, str4, saveShareBmpFromView != null ? saveShareBmpFromView : "", str5, iRouterParams);
                return;
            }
            JDDisplayImageOptions forceStaticImage = JDDisplayImageOptions.createSimple().setForceStaticImage(true);
            if (TextUtils.isEmpty(str3)) {
                goToShare(context, str4, saveDefaultResources(context, R.drawable.icon_goods_pintuan_share), str5, iRouterParams);
            } else {
                JDImageUtils.loadImage(str3, forceStaticImage, new JDImageLoadingListener() { // from class: com.jingdong.common.jdmiaosha.utils.JDMiaoShaShareUtil.1
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str7, View view) {
                        OKLog.d("JDMiaoShaUtils", "Image-onLoadingCancelled");
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str7, View view, Bitmap bitmap2) {
                        String saveDefaultResources;
                        SimpleDraweeView simpleDraweeView2;
                        OKLog.d("JDMiaoShaUtils", "Image-onLoadingComplete");
                        if (bitmap2 == null || (simpleDraweeView2 = simpleDraweeView) == null) {
                            saveDefaultResources = JDMiaoShaShareUtil.saveDefaultResources(context, R.drawable.icon_goods_pintuan_share);
                        } else {
                            simpleDraweeView2.setImageBitmap(bitmap2);
                            saveDefaultResources = JDMiaoShaShareUtil.saveShareBmpFromView(context, layoutShareView);
                            if (saveDefaultResources == null) {
                                saveDefaultResources = "";
                            }
                        }
                        JDMiaoShaShareUtil.goToShare(context, str4, saveDefaultResources, str5, iRouterParams);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str7, View view, JDFailReason jDFailReason) {
                        OKLog.d("JDMiaoShaUtils", "Image-onLoadingFailed");
                        JDMiaoShaShareUtil.goToShare(context, str4, JDMiaoShaShareUtil.saveDefaultResources(context, R.drawable.icon_goods_pintuan_share), str5, iRouterParams);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str7, View view) {
                        OKLog.d("JDMiaoShaUtils", "Image-onLoadingStarted");
                    }
                });
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            OKLog.d("JDMiaoShaUtils", "\u6570\u636e\u89e3\u6790\u5f02\u5e38");
            onErroCallBack(iRouterParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void goToShare(Context context, String str, String str2, String str3, IRouterParams iRouterParams) {
        if (TextUtils.isEmpty(str3)) {
            str3 = DEFAULT_SHARE_TITLE;
        }
        ShareInfo shareInfo = new ShareInfo(DEFAULT_SHARE_URL, str3, "", "", "");
        shareInfo.setMpId(DEFAULT_MP_ID);
        shareInfo.setMpPath(str);
        shareInfo.setMpType(0);
        shareInfo.setChannels("Wxfriends");
        shareInfo.setMpLocalIconPath(str2);
        if (context != null) {
            if (iRouterParams != null) {
                iRouterParams.onCallBack("0");
            }
            OKLog.d("JDMiaoShaUtils", "nativeToShare start ShareUtil.open ");
            ShareUtil.open((Activity) context, shareInfo);
            return;
        }
        OKLog.d("JDMiaoShaUtils", "nativeToShare Fail ShareUtil.open ");
        onErroCallBack(iRouterParams);
    }

    public static View layoutShareView(String str, String str2) {
        JDMiaoShaPriceView jDMiaoShaPriceView;
        View inflate = ImageUtil.inflate(R.layout.pintuan_share_layout, null);
        if (inflate == null || (jDMiaoShaPriceView = (JDMiaoShaPriceView) inflate.findViewById(R.id.sharePriceView)) == null) {
            return null;
        }
        jDMiaoShaPriceView.setOrientation(1);
        jDMiaoShaPriceView.miaoShaPrice(str, 26.0f, 0.57f, 0.71f, 0.71f).miaoShaPriceColor(MIAO_SHA_PRICE_COLOR).jdPrice(str2, 12.0f).jdPriceColor(JD_PRICE_COLOR);
        TextView textView = (TextView) jDMiaoShaPriceView.findViewById(R.id.tvMiaoShaPrice);
        TextView textView2 = (TextView) inflate.findViewById(R.id.miaoshaTitle);
        if (textView == null || textView2 == null) {
            return null;
        }
        if (textView.getText().toString().equals("\u6682\u65e0\u62a5\u4ef7")) {
            textView2.setText("");
        } else {
            textView2.setText(SUB_PRICE);
        }
        inflate.measure(View.MeasureSpec.makeMeasureSpec(shareWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(shareHeight, 1073741824));
        inflate.layout(0, 0, inflate.getMeasuredWidth(), inflate.getMeasuredHeight());
        return inflate;
    }

    public static void nativeToShare(IRouterParams iRouterParams) {
        OKLog.d("JDMiaoShaUtils", "nativeToShare start");
        Context context = iRouterParams != null ? iRouterParams.getContext() : null;
        if (iRouterParams != null) {
            String routerParam = iRouterParams.getRouterParam();
            OKLog.d("JDMiaoShaUtils", "nativeToShare start PathAndToshare ");
            getShareImgPathAndToshare(context, routerParam, false, iRouterParams, null);
            return;
        }
        OKLog.d("JDMiaoShaUtils", "nativeToShare Fail");
        iRouterParams.onCallBack("1");
    }

    private static void onErroCallBack(IRouterParams iRouterParams) {
        if (iRouterParams != null) {
            iRouterParams.onCallBack("1");
        } else {
            c.l("\u5206\u4eab\u5931\u8d25\uff0c\u8bf7\u7a0d\u5fae\u91cd\u8bd5~");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String saveDefaultResources(Context context, int i2) {
        if (context == null) {
            return "";
        }
        try {
            Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(i2)).getBitmap();
            String str = context.getFilesDir().toString() + File.separator + SHARE_IMAGE_NAME;
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
            return str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String saveShareBmpFromView(Context context, View view) {
        if (view == null) {
            return "";
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        if (createBitmap == null || view == null) {
            return "";
        }
        String str = context.getFilesDir().toString() + File.separator + SHARE_IMAGE_NAME;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            createBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
            return str;
        } catch (Exception unused) {
            return "";
        } finally {
            createBitmap.recycle();
        }
    }
}
