package com.jingdong.app.mall.privacy;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.R;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class JDPrivacyUtil {
    private static final String PAGE_ID = "Privacy_Policy";
    private static final String POLICY_URL = "https://ihelp.jd.com/n/help/tip/getTipsFacade.json?tipId=140";
    private static final String REGISTER_URL = "https://in.m.jd.com/help/app/register_info.html";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JDDialog createCheckDialogSafe(Activity activity) {
        JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(activity, activity.getString(R.string.privacy_check_txt_title), getClickableContent(activity, R.string.privacy_check_txt_content, 2, 10), activity.getString(R.string.privacy_check_txt_disagree), activity.getString(R.string.privacy_check_txt_agree));
        createJdDialogWithStyle6.messageView.setMovementMethod(new LinkMovementMethod());
        createJdDialogWithStyle6.setCancelable(false);
        createJdDialogWithStyle6.show();
        return createJdDialogWithStyle6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JDDialog createOnceDialogSafe(Activity activity) {
        JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(activity, activity.getString(R.string.privacy_once_txt_title), getClickableContent(activity, R.string.privacy_once_txt_content, 33, 41), activity.getString(R.string.privacy_once_txt_disagree), activity.getString(R.string.privacy_once_txt_agree));
        createJdDialogWithStyle6.messageView.setMovementMethod(new LinkMovementMethod());
        createJdDialogWithStyle6.setCancelable(false);
        createJdDialogWithStyle6.show();
        return createJdDialogWithStyle6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void dismissDialog(Dialog dialog) {
        if (dialog != null) {
            try {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static View generateDialogView(Activity activity) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(getDpi750Width(activity, 23));
        gradientDrawable.setColor(-1);
        RelativeLayout relativeLayout = new RelativeLayout(activity);
        relativeLayout.setGravity(1);
        relativeLayout.setBackgroundDrawable(gradientDrawable);
        relativeLayout.setLayoutParams(new FrameLayout.LayoutParams(getDpi750Width(activity, R2.attr.chipIconEnabled), getDpi750Width(activity, 700)));
        TextView textView = new TextView(activity);
        int i2 = R.id.privacy_title;
        textView.setId(i2);
        textView.setTextSize(0, getDpi750Width(activity, 32));
        textView.setTextColor(-13750995);
        textView.setGravity(17);
        textView.getPaint().setFakeBoldText(true);
        relativeLayout.addView(textView, new RelativeLayout.LayoutParams(-1, getDpi750Width(activity, 144)));
        TextView textView2 = new TextView(activity);
        textView2.setTextColor(-15066598);
        int i3 = R.id.privacy_message;
        textView2.setId(i3);
        textView2.setTextSize(0, getDpi750Width(activity, 26));
        textView2.setLineSpacing(10.0f, 1.0f);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getDpi750Width(activity, R2.attr.btnIconAlpha), getDpi750Width(activity, 400));
        layoutParams.topMargin = getDpi750Width(activity, 104);
        layoutParams.addRule(14);
        relativeLayout.addView(textView2, layoutParams);
        TextView textView3 = new TextView(activity);
        textView3.setTextColor(-8355712);
        int i4 = R.id.privacy_bottom_content;
        textView3.setId(i4);
        textView3.setTextSize(0, getDpi750Width(activity, 22));
        textView3.setLineSpacing(8.0f, 1.0f);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(getDpi750Width(activity, R2.attr.btnIconAlpha), getDpi750Width(activity, 140));
        layoutParams2.bottomMargin = getDpi750Width(activity, R2.anim.pop_in);
        layoutParams2.addRule(14);
        layoutParams2.addRule(12);
        relativeLayout.addView(textView3, layoutParams2);
        TextView textView4 = new TextView(activity);
        int i5 = R.id.privacy_bottom_agree;
        textView4.setId(i5);
        textView4.setTextColor(-1);
        textView4.setTextSize(0, getDpi750Width(activity, 24));
        textView4.setGravity(17);
        GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-381927, -377063, -370407, -367591});
        gradientDrawable2.setShape(0);
        gradientDrawable2.setCornerRadius(getDpi750Width(activity, 30));
        textView4.setBackgroundDrawable(gradientDrawable2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(getDpi750Width(activity, R2.attr.btnIconAlpha), getDpi750Width(activity, 60));
        layoutParams3.addRule(14);
        layoutParams3.addRule(12);
        layoutParams3.bottomMargin = getDpi750Width(activity, 96);
        relativeLayout.addView(textView4, layoutParams3);
        TextView textView5 = new TextView(activity);
        int i6 = R.id.privacy_bottom_deny;
        textView5.setId(i6);
        textView5.setTextColor(-8355712);
        textView5.setTextSize(0, getDpi750Width(activity, 24));
        textView5.setGravity(17);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, getDpi750Width(activity, 96));
        layoutParams4.addRule(14);
        layoutParams4.addRule(12);
        relativeLayout.addView(textView5, layoutParams4);
        ((TextView) relativeLayout.findViewById(i2)).setText(R.string.privacy_txt_title);
        ((TextView) relativeLayout.findViewById(i3)).setText(getPrivacyTopMessage(activity));
        ((TextView) relativeLayout.findViewById(i4)).setText(getPrivacyBottomMessage(activity));
        ((TextView) relativeLayout.findViewById(i5)).setText(R.string.privacy_txt_btn_agree);
        ((TextView) relativeLayout.findViewById(i6)).setText(R.string.privacy_txt_btn_disagree);
        return relativeLayout;
    }

    private static SpannableString getClickableContent(Activity activity, int i2, int i3, int i4) {
        SpannableString spannableString = new SpannableString(activity.getString(i2));
        spannableString.setSpan(new ClickableSpan() { // from class: com.jingdong.app.mall.privacy.JDPrivacyUtil.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                JDPrivacyManager.getInstance().startWebActivity(JDPrivacyUtil.POLICY_URL);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(Color.argb(255, 240, 43, 43));
                textPaint.setFakeBoldText(true);
            }
        }, i3, i4, 33);
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getDpi750Width(Activity activity, int i2) {
        int min = Math.min(DpiUtil.getAppWidth(activity), (int) R2.attr.miaoShaPriceTextColor);
        float height = DpiUtil.getHeight(activity);
        if (height > 0.0f && height / min < 1.4f) {
            min = (int) (height / 1.6f);
        }
        return (int) (((min * i2) / 750.0f) + 0.5f);
    }

    private static SpannableString getPrivacyBottomMessage(Activity activity) {
        SpannableString spannableString = new SpannableString(activity.getString(R.string.privacy_txt_bottom));
        spannableString.setSpan(new ClickableSpan() { // from class: com.jingdong.app.mall.privacy.JDPrivacyUtil.3
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                JDPrivacyManager.getInstance().startWebActivity("https://in.m.jd.com/help/app/register_info.html");
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(Color.argb(255, 240, 43, 43));
                textPaint.setFakeBoldText(true);
            }
        }, 42, 52, 33);
        return spannableString;
    }

    private static SpannableString getPrivacyTopMessage(Activity activity) {
        SpannableString spannableString = new SpannableString(activity.getString(R.string.privacy_txt_content));
        spannableString.setSpan(new ClickableSpan() { // from class: com.jingdong.app.mall.privacy.JDPrivacyUtil.2
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                JDPrivacyManager.getInstance().startWebActivity(JDPrivacyUtil.POLICY_URL);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(Color.argb(255, 240, 43, 43));
                textPaint.setFakeBoldText(true);
            }
        }, 102, 110, 33);
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sendMta(String str) {
        try {
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), str, "", "", PAGE_ID, "JDPrivacyManager", "", "", "", null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sendMtaExpo(String str) {
        try {
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str, "", PAGE_ID, "JDPrivacyManager", "", "", null);
        } catch (Throwable unused) {
        }
    }
}
