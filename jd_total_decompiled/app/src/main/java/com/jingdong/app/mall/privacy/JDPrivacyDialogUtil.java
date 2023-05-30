package com.jingdong.app.mall.privacy;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.privacy.JDPrivacyDialogInfo;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.R;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class JDPrivacyDialogUtil {

    /* loaded from: classes4.dex */
    public static abstract class IDialogListener {
        public abstract void onAgree();

        public abstract void onDisagree();

        public void onNotice(String str) {
        }
    }

    public static Dialog checkPrivacyDialog(Activity activity, JDPrivacyDialogInfo jDPrivacyDialogInfo, IDialogListener iDialogListener) {
        String str;
        boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue("privacy_dialog_close_pop", false);
        boolean z = SwitchQueryFetcher.getSwitchBooleanValue("privacy_dialog_only_privacy", false) && JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication());
        if (jDPrivacyDialogInfo == null || !jDPrivacyDialogInfo.isValid() || !jDPrivacyDialogInfo.hasTimes() || switchBooleanValue || z) {
            if (iDialogListener != null) {
                if (jDPrivacyDialogInfo == null || jDPrivacyDialogInfo.isValid()) {
                    str = (jDPrivacyDialogInfo == null || jDPrivacyDialogInfo.hasTimes()) ? switchBooleanValue ? "\u5df2\u5173\u95ed\u5168\u90e8\u5f39\u7a97" : "\u53c2\u6570 JDPrivacyDialogInfo \u4e0d\u53ef\u4ee5\u4e3anul" : "\u5df2\u7ecf\u8fbe\u5230\u6700\u5927\u5f39\u51fa\u6b21\u6570";
                } else {
                    str = "\u53c2\u6570 JDPrivacyDialogInfo \u4e0d\u7b26\u5408\u89c4\u8303";
                }
                iDialogListener.onNotice(str);
            }
            return null;
        }
        try {
            Dialog dialog = new Dialog(activity, R.style.privacy_dialog);
            dialog.setContentView(generateDialogView(activity, dialog, jDPrivacyDialogInfo, iDialogListener));
            dialog.setCancelable(false);
            dialog.show();
            jDPrivacyDialogInfo.onShow();
            return dialog;
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iDialogListener != null) {
                iDialogListener.onNotice(e2.getMessage());
                return null;
            }
            return null;
        }
    }

    private static View generateDialogView(Activity activity, final Dialog dialog, final JDPrivacyDialogInfo jDPrivacyDialogInfo, final IDialogListener iDialogListener) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(getSize(activity, 32));
        gradientDrawable.setColor(-1);
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        linearLayout.setBackgroundDrawable(gradientDrawable);
        int size = getSize(activity, R2.attr.chipIconEnabled);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(size, -2));
        TextView textView = new TextView(activity);
        textView.setTextSize(0, getSize(activity, 32));
        textView.setGravity(17);
        textView.setSingleLine(true);
        textView.getPaint().setFakeBoldText(true);
        textView.setText(jDPrivacyDialogInfo.title.text);
        textView.setTextColor(jDPrivacyDialogInfo.title.getTextColor(-15066598));
        textView.setPadding(getSize(activity, 48), getSize(activity, 24), getSize(activity, 48), 0);
        linearLayout.addView(textView, new LinearLayout.LayoutParams(size, getSize(activity, 104)));
        TextView textView2 = new TextView(activity);
        textView2.setTextSize(0, getSize(activity, 28));
        textView2.setIncludeFontPadding(false);
        textView2.setId(R.id.privacy_message);
        textView2.setText(getClickableContent(activity, jDPrivacyDialogInfo));
        textView2.setLineSpacing(0.0f, 1.2f);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        textView2.setMaxHeight(getSize(activity, R2.attr.additionBottom));
        textView2.setPadding(getSize(activity, 10), 0, getSize(activity, 10), 0);
        linearLayout.addView(textView2, new LinearLayout.LayoutParams(getSize(activity, 506), -2));
        TextView textView3 = new TextView(activity);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDPrivacyDialogInfo.this.onAgree();
                dialog.dismiss();
                IDialogListener iDialogListener2 = iDialogListener;
                if (iDialogListener2 != null) {
                    iDialogListener2.onAgree();
                }
            }
        });
        textView3.setText(jDPrivacyDialogInfo.agreeText.text);
        textView3.setTextColor(jDPrivacyDialogInfo.agreeText.getTextColor(-1));
        textView3.setTextSize(0, getSize(activity, 24));
        textView3.setGravity(17);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(jDPrivacyDialogInfo.agreeText.getBgColor(-381927));
        gradientDrawable2.setShape(0);
        gradientDrawable2.setCornerRadius(getSize(activity, 30));
        textView3.setBackgroundDrawable(gradientDrawable2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(activity, R2.attr.btnIconAlpha), getSize(activity, 64));
        layoutParams.topMargin = getSize(activity, 48);
        linearLayout.addView(textView3, layoutParams);
        TextView textView4 = new TextView(activity);
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
                jDPrivacyDialogInfo.onDisagree();
                IDialogListener iDialogListener2 = iDialogListener;
                if (iDialogListener2 != null) {
                    iDialogListener2.onDisagree();
                }
            }
        });
        textView4.setText(jDPrivacyDialogInfo.disagreeText.text);
        textView4.setTextColor(jDPrivacyDialogInfo.disagreeText.getTextColor(-8355712));
        textView4.setTextSize(0, getSize(activity, 24));
        textView4.setGravity(17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, getSize(activity, 30));
        layoutParams2.topMargin = getSize(activity, 29);
        layoutParams2.bottomMargin = getSize(activity, 45);
        linearLayout.addView(textView4, layoutParams2);
        return linearLayout;
    }

    private static SpannableString getClickableContent(final Activity activity, final JDPrivacyDialogInfo jDPrivacyDialogInfo) {
        List<JDPrivacyDialogInfo.TextInfo> textList = jDPrivacyDialogInfo.getTextList();
        ArrayList<JDPrivacyDialogInfo.TextInfo> arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (JDPrivacyDialogInfo.TextInfo textInfo : textList) {
            if (textInfo != null && textInfo.isValid()) {
                if (textInfo.needSpan()) {
                    arrayList.add(textInfo);
                }
                textInfo.setStartIndex(i2);
                sb.append(textInfo.text);
                i2 += textInfo.text.length();
            }
        }
        SpannableString spannableString = new SpannableString(sb.toString());
        for (final JDPrivacyDialogInfo.TextInfo textInfo2 : arrayList) {
            if (textInfo2.isStopSign()) {
                spannableString.setSpan(new AbsoluteSizeSpan(getSize(activity, 28)), textInfo2.getStartIndex(), textInfo2.getEndIndex(), 33);
            } else {
                spannableString.setSpan(new ClickableSpan() { // from class: com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.3
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        JDPrivacyDialogInfo.TextInfo.this.onClickSpan(activity, jDPrivacyDialogInfo);
                    }

                    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                    public void updateDrawState(TextPaint textPaint) {
                        textPaint.setColor(JDPrivacyDialogInfo.TextInfo.this.getTextColor(-381927));
                    }
                }, textInfo2.getStartIndex(), textInfo2.getEndIndex(), 33);
            }
        }
        return spannableString;
    }

    public static int getShowTimes(String str) {
        return CommonBase.getJdSharedPreferences().getInt(str, 0);
    }

    static int getSize(Activity activity, int i2) {
        int min = Math.min(DpiUtil.getAppWidth(activity), (int) R2.attr.motionEffect_translationX);
        float height = DpiUtil.getHeight(activity);
        if (height > 0.0f && height / min < 1.4f) {
            min = (int) (height / 1.6f);
        }
        return (int) (((min * i2) / 750.0f) + 0.5f);
    }
}
