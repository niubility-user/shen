package com.jingdong.common.utils;

import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import com.jingdong.common.R;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.frame.IResumeListener;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.widget.ExceptionDrawable;

/* loaded from: classes6.dex */
public class NoImageUtils {
    public static void initImageView(final IMyActivity iMyActivity, final HttpGroup httpGroup, final ImageView imageView, final String str, boolean z) {
        if (z) {
            iMyActivity.addResumeListener(new IResumeListener() { // from class: com.jingdong.common.utils.NoImageUtils.1
                @Override // com.jingdong.common.frame.IResumeListener
                public void onResume() {
                    NoImageUtils.initImageView(iMyActivity, httpGroup, imageView, str, false);
                }
            });
        }
        if (needNoImage()) {
            imageView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.utils.NoImageUtils.2
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    NoImageUtils.loadImage(true, iMyActivity, httpGroup, imageView, str);
                    return true;
                }
            });
        } else {
            imageView.setOnLongClickListener(null);
            imageView.setLongClickable(false);
        }
        loadImage(false, iMyActivity, httpGroup, imageView, str);
    }

    public static boolean isNeedAlertDialog() {
        return CommonBase.getJdSharedPreferences().getBoolean(com.jingdong.jdsdk.res.StringUtil.no_image_alert_dialog_key, true);
    }

    public static void loadImage(boolean z, final IMyActivity iMyActivity, HttpGroup httpGroup, final ImageView imageView, String str) {
        final boolean z2 = !z && needNoImage();
        HttpGroup.OnCommonListener onCommonListener = new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.NoImageUtils.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.utils.NoImageUtils.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        imageView.setImageDrawable(new BitmapDrawable(ImageUtil.createBitmap(ImageUtil.InputWay.createInputWay(httpResponse), 0, 0)));
                        imageView.invalidate();
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.utils.NoImageUtils.3.2
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (z2) {
                            imageView.setImageDrawable(new ExceptionDrawable(iMyActivity.getThisActivity(), JdSdk.getInstance().getApplication().getApplicationContext().getString(R.string.image_need_long_click)));
                            return;
                        }
                        imageView.setImageDrawable(new ExceptionDrawable(iMyActivity.getThisActivity(), JdSdk.getInstance().getApplication().getApplicationContext().getString(R.string.image_no_image)));
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        };
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setType(5000);
        httpSetting.setUrl(str);
        httpSetting.setListener(onCommonListener);
        if (z2) {
            httpSetting.setCacheMode(1);
        }
        httpGroup.add(httpSetting);
    }

    public static boolean needNoImage() {
        return CommonBase.getJdSharedPreferences().getBoolean("jd_no_image_switch", false) && !NetUtils.isWifiForLoadImage();
    }

    public static void setIfNeedAlertDialog(boolean z) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putBoolean(com.jingdong.jdsdk.res.StringUtil.no_image_alert_dialog_key, z);
        edit.commit();
    }

    public static void setIfNeedNoImage(boolean z) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putBoolean("jd_no_image_switch", z);
        edit.commit();
    }

    public static void initImageView(IMyActivity iMyActivity, ImageView imageView, View.OnLongClickListener onLongClickListener) {
        if (imageView == null || iMyActivity == null || onLongClickListener == null) {
            return;
        }
        imageView.setImageDrawable(new ExceptionDrawable(iMyActivity.getThisActivity(), JdSdk.getInstance().getApplication().getApplicationContext().getString(R.string.image_need_long_click)));
        imageView.setOnLongClickListener(onLongClickListener);
    }
}
