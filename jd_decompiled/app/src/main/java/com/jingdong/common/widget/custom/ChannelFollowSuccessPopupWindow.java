package com.jingdong.common.widget.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.react.uimanager.ThemedReactContext;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.R;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class ChannelFollowSuccessPopupWindow {
    private Context mContext;
    private PopupWindow mPopupWindow;

    public ChannelFollowSuccessPopupWindow(Context context, String str, String str2) {
        this.mContext = context;
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(getScreenWidth(), -1));
        relativeLayout.setBackgroundColor(Color.parseColor("#cd000000"));
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.ChannelFollowSuccessPopupWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChannelFollowSuccessPopupWindow.this.mPopupWindow == null || !ChannelFollowSuccessPopupWindow.this.mPopupWindow.isShowing()) {
                    return;
                }
                ChannelFollowSuccessPopupWindow.this.mPopupWindow.dismiss();
            }
        });
        RelativeLayout relativeLayout2 = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13);
        relativeLayout2.setLayoutParams(layoutParams);
        relativeLayout2.setId(R.id.channel_follow_success_coremsg);
        relativeLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.ChannelFollowSuccessPopupWindow.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        relativeLayout.addView(relativeLayout2);
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.follow_success_icon);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(get750(100), get750(100));
        layoutParams2.addRule(14);
        imageView.setId(R.id.channel_follow_success);
        imageView.setLayoutParams(layoutParams2);
        relativeLayout2.addView(imageView);
        TextView textView = new TextView(context);
        textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        textView.setTextSize(0, get750(34));
        textView.setTypeface(Typeface.defaultFromStyle(1));
        textView.setText(TextUtils.isEmpty(str) ? "\u5173\u6ce8\u6210\u529f" : str);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.topMargin = get750(50);
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, imageView.getId());
        textView.setLayoutParams(layoutParams3);
        textView.setId(R.id.channel_follow_success_message);
        relativeLayout2.addView(textView);
        TextView textView2 = new TextView(context);
        textView2.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        textView2.setTextSize(0, get750(28));
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.topMargin = get750(25);
        layoutParams4.addRule(14);
        layoutParams4.addRule(3, textView.getId());
        textView2.setLayoutParams(layoutParams4);
        textView2.setGravity(1);
        textView2.setText(str2);
        textView2.setSingleLine(true);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setTypeface(Typeface.defaultFromStyle(1));
        textView2.setId(R.id.channel_follow_success_submessage);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        relativeLayout2.addView(textView2);
        ImageView imageView2 = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(get750(620), get750(R2.attr.barLength));
        layoutParams5.topMargin = get750(32);
        imageView2.setBackgroundResource(R.drawable.channel_follow_success_guide);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        layoutParams5.addRule(14);
        layoutParams5.addRule(3, textView2.getId());
        imageView2.setLayoutParams(layoutParams5);
        imageView2.setId(R.id.channel_follow_success_guide);
        relativeLayout2.addView(imageView2);
        TextView textView3 = new TextView(context);
        textView3.setBackgroundResource(R.drawable.shape_close_follow_tips_bg);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(get750(240), get750(72));
        layoutParams6.topMargin = get750(100);
        layoutParams6.addRule(3, imageView2.getId());
        layoutParams6.addRule(14);
        textView3.setLayoutParams(layoutParams6);
        textView3.setGravity(17);
        textView3.setText("\u6211\u77e5\u9053\u4e86");
        textView3.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        textView3.setTextSize(0, get750(26));
        relativeLayout2.addView(textView3);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.ChannelFollowSuccessPopupWindow.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChannelFollowSuccessPopupWindow.this.mPopupWindow == null || !ChannelFollowSuccessPopupWindow.this.mPopupWindow.isShowing()) {
                    return;
                }
                ChannelFollowSuccessPopupWindow.this.mPopupWindow.dismiss();
            }
        });
        PopupWindow popupWindow = new PopupWindow(relativeLayout, getScreenWidth(), -1);
        this.mPopupWindow = popupWindow;
        popupWindow.setClippingEnabled(false);
        this.mPopupWindow.setSoftInputMode(16);
        this.mPopupWindow.setFocusable(true);
    }

    private int get750(int i2) {
        Context context = this.mContext;
        if (context != null) {
            Activity activity = null;
            if (context instanceof ThemedReactContext) {
                activity = ((ThemedReactContext) context).getCurrentActivity();
            } else if (context instanceof IMyActivity) {
                activity = (Activity) context;
            }
            if (activity != null) {
                return DPIUtil.getWidthByDesignValue750(activity, i2);
            }
            return 0;
        }
        return 0;
    }

    private int getScreenWidth() {
        Context context = this.mContext;
        if (context != null) {
            if (context instanceof ThemedReactContext) {
                return DPIUtil.getAppWidth(((ThemedReactContext) context).getCurrentActivity());
            }
            if (context instanceof IMyActivity) {
                return DPIUtil.getAppWidth((Activity) context);
            }
            return 0;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSafe(Context context) {
        if (context != null) {
            Activity activity = null;
            if (context instanceof ThemedReactContext) {
                activity = ((ThemedReactContext) context).getCurrentActivity();
            } else if (context instanceof Activity) {
                activity = (Activity) context;
            }
            return (activity == null || activity.isFinishing()) ? false : true;
        }
        return false;
    }

    public PopupWindow getPopupWindow() {
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow != null) {
            return popupWindow;
        }
        return null;
    }

    public void show(View view, final Context context) {
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow == null || popupWindow.isShowing()) {
            return;
        }
        if (isSafe(context)) {
            this.mPopupWindow.showAtLocation(view, 0, 0, 0);
        }
        view.postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.ChannelFollowSuccessPopupWindow.4
            @Override // java.lang.Runnable
            public void run() {
                if (ChannelFollowSuccessPopupWindow.this.isSafe(context) && ChannelFollowSuccessPopupWindow.this.mPopupWindow != null && ChannelFollowSuccessPopupWindow.this.mPopupWindow.isShowing()) {
                    ChannelFollowSuccessPopupWindow.this.mPopupWindow.dismiss();
                }
            }
        }, 8000L);
    }
}
