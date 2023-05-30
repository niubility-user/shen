package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Space;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.network.StringUtil;
import com.jingdong.common.widget.PasswordInputView;
import com.jingdong.common.widget.UnLottieAnimationView;
import com.jingdong.common.widget.image.UnNetImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDDialogFactory {
    public static final InputFilter filter = new InputFilter() { // from class: com.jingdong.common.ui.JDDialogFactory.2
        Pattern pattern = Pattern.compile("[\\s+]", 66);

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
            if (this.pattern.matcher(charSequence).find()) {
                return "";
            }
            return null;
        }
    };
    private static JDDialogFactory instance;

    private JDDialog createJdDialogWithStyleTimerNew(Context context, String str, String str2, int i2) {
        JDDialog newJDDialog = JDDialog.newJDDialog(context);
        newJDDialog.setContentView(R.layout.jd_common_dialog_style_timer_new);
        UnLottieAnimationView unLottieAnimationView = (UnLottieAnimationView) newJDDialog.findViewById(R.id.lottie);
        unLottieAnimationView.setAnimation("timer_dialog.json");
        unLottieAnimationView.setProgress(0.0f);
        unLottieAnimationView.loop(true);
        unLottieAnimationView.playAnimation();
        TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_title);
        newJDDialog.titleView = textView;
        textView.setText(str);
        newJDDialog.secondMessageView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message2);
        newJDDialog.setCountdown(i2);
        Button button = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
        newJDDialog.posButton = button;
        button.setText(str2);
        newJDDialog.setPosAccent(true);
        newJDDialog.useCancelClickEvent(newJDDialog.posButton);
        return newJDDialog;
    }

    public static synchronized JDDialogFactory getInstance() {
        JDDialogFactory jDDialogFactory;
        synchronized (JDDialogFactory.class) {
            if (instance == null) {
                instance = new JDDialogFactory();
            }
            jDDialogFactory = instance;
        }
        return jDDialogFactory;
    }

    public JDDialog createJdDialog601(Context context, JdDialogParam jdDialogParam) {
        if (context == null || jdDialogParam == null) {
            return null;
        }
        JDDialog newJDDialog = JDDialog.newJDDialog(context);
        newJDDialog.dialogType = 16;
        newJDDialog.isSupportBottomClose = true;
        newJDDialog.setContentView(R.layout.un_601_dialog_new);
        TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
        newJDDialog.messageView = textView;
        textView.setText(jdDialogParam.message);
        newJDDialog.timer = (TextView) newJDDialog.findViewById(R.id.time);
        newJDDialog.sec = (TextView) newJDDialog.findViewById(R.id.sec);
        newJDDialog.timerStartText = (TextView) newJDDialog.findViewById(R.id.timer_start_text);
        newJDDialog.negButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_neg_button);
        newJDDialog.posButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
        newJDDialog.timeProgress = (ProgressBar) newJDDialog.findViewById(R.id.timeProgress);
        newJDDialog.param = jdDialogParam;
        LinearLayout linearLayout = (LinearLayout) newJDDialog.findViewById(R.id.ll_btns);
        newJDDialog.setCountdown(jdDialogParam.countDownTime);
        View findViewById = newJDDialog.findViewById(R.id.space);
        if (TextUtils.isEmpty(jdDialogParam.getLeftTitle()) && TextUtils.isEmpty(jdDialogParam.getRightTitle())) {
            newJDDialog.posButton.setText(StringUtil.queue_not_wait);
            newJDDialog.negButton.setVisibility(8);
            findViewById.setVisibility(8);
        } else {
            if (TextUtils.isEmpty(jdDialogParam.getLeftTitle())) {
                newJDDialog.posButton.setVisibility(8);
                findViewById.setVisibility(8);
            } else {
                newJDDialog.posButton.setText(jdDialogParam.getLeftTitle());
            }
            if (TextUtils.isEmpty(jdDialogParam.getRightTitle())) {
                newJDDialog.negButton.setVisibility(8);
                findViewById.setVisibility(8);
            } else {
                newJDDialog.negButton.setText(jdDialogParam.getRightTitle());
            }
        }
        newJDDialog.setCountDownTime(jdDialogParam.countDownTime);
        return newJDDialog;
    }

    public JDDialog createJdDialogWithBottomClose(Context context, View view) {
        JDDialog newJDDialog = JDDialog.newJDDialog(context);
        newJDDialog.isSupportBottomClose = true;
        newJDDialog.setContentView(view);
        return newJDDialog;
    }

    public JdRecyclerViewDialog createJdDialogWithRecyclerView(Context context, String str, String str2, String str3, String str4) {
        if (context != null) {
            JdRecyclerViewDialog jdRecyclerViewDialog = new JdRecyclerViewDialog(context);
            jdRecyclerViewDialog.setContentView(R.layout.jd_common_dialog_style_recycler);
            jdRecyclerViewDialog.titleView = (TextView) jdRecyclerViewDialog.findViewById(R.id.jd_dialog_title);
            jdRecyclerViewDialog.messageView = (TextView) jdRecyclerViewDialog.findViewById(R.id.jd_dialog_message);
            jdRecyclerViewDialog.posButton = (Button) jdRecyclerViewDialog.findViewById(R.id.jd_dialog_pos_button);
            jdRecyclerViewDialog.negButton = (Button) jdRecyclerViewDialog.findViewById(R.id.jd_dialog_neg_button);
            jdRecyclerViewDialog.recyclerView = (RecyclerView) jdRecyclerViewDialog.findViewById(R.id.jd_dialog_list);
            LinearLayout linearLayout = (LinearLayout) jdRecyclerViewDialog.findViewById(R.id.ll_btns);
            jdRecyclerViewDialog.contentLayout = (LinearLayout) jdRecyclerViewDialog.findViewById(R.id.contentView);
            if (TextUtils.isEmpty(str)) {
                jdRecyclerViewDialog.titleView.setVisibility(8);
            } else {
                jdRecyclerViewDialog.titleView.setVisibility(0);
                jdRecyclerViewDialog.titleView.setText(str);
            }
            if (TextUtils.isEmpty(str2)) {
                jdRecyclerViewDialog.messageView.setVisibility(8);
            } else {
                jdRecyclerViewDialog.messageView.setVisibility(0);
                jdRecyclerViewDialog.messageView.setText(str2);
            }
            View findViewById = jdRecyclerViewDialog.findViewById(R.id.space);
            if (TextUtils.isEmpty(str3) && TextUtils.isEmpty(str4)) {
                linearLayout.setVisibility(8);
            } else {
                if (TextUtils.isEmpty(str3)) {
                    jdRecyclerViewDialog.posButton.setVisibility(8);
                    findViewById.setVisibility(8);
                } else {
                    jdRecyclerViewDialog.posButton.setText(str3);
                }
                if (TextUtils.isEmpty(str4)) {
                    jdRecyclerViewDialog.negButton.setVisibility(8);
                    findViewById.setVisibility(8);
                } else {
                    jdRecyclerViewDialog.negButton.setText(str4);
                }
            }
            return jdRecyclerViewDialog;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle1(Context context, CharSequence charSequence, String str) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(charSequence)) {
                if (!TextUtils.isEmpty(str)) {
                    JDDialog newJDDialog = JDDialog.newJDDialog(context);
                    newJDDialog.setContentView(R.layout.jd_common_dialog_style_1);
                    newJDDialog.messageView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
                    newJDDialog.setMessage(charSequence, true, true);
                    newJDDialog.setPosAccent(true);
                    Button button = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
                    newJDDialog.posButton = button;
                    button.setText(str);
                    newJDDialog.useCancelClickEvent(newJDDialog.posButton);
                    return newJDDialog;
                }
                throw new IllegalArgumentException("the param buttonText can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle10(final Context context, String str, CharSequence charSequence, View view, String str2, String str3) throws IllegalArgumentException {
        if (context != null) {
            JDDialog newJDDialog = JDDialog.newJDDialog(context);
            newJDDialog.setContentView(R.layout.jd_common_dialog_style_10_2);
            newJDDialog.titleView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_title);
            newJDDialog.setTitle(str);
            TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
            newJDDialog.messageView = textView;
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            newJDDialog.setMessage(charSequence);
            newJDDialog.posButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
            newJDDialog.negButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_neg_button);
            Space space = (Space) newJDDialog.findViewById(R.id.space);
            LinearLayout linearLayout = (LinearLayout) newJDDialog.findViewById(R.id.ll_btns);
            if (TextUtils.isEmpty(str2)) {
                space.setVisibility(8);
                newJDDialog.posButton.setVisibility(8);
                newJDDialog.negButton.setBackgroundResource(R.drawable.dialog_red_button);
                newJDDialog.negButton.setTextColor(-1);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.width = DpiUtil.dip2px(context, 150.0f);
                linearLayout.setLayoutParams(layoutParams);
            } else {
                newJDDialog.posButton.setText(str2);
                newJDDialog.useCancelClickEvent(newJDDialog.posButton);
            }
            if (TextUtils.isEmpty(str3)) {
                space.setVisibility(8);
                newJDDialog.negButton.setVisibility(8);
                newJDDialog.posButton.setBackgroundResource(R.drawable.dialog_white_button);
                newJDDialog.posButton.setTextColor(Color.parseColor(JDDarkUtil.COLOR_F2270C));
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams2.width = DpiUtil.dip2px(context, 150.0f);
                linearLayout.setLayoutParams(layoutParams2);
            } else {
                newJDDialog.negButton.setText(str3);
                newJDDialog.useCancelClickEvent(newJDDialog.negButton);
            }
            final LinearLayout linearLayout2 = (LinearLayout) newJDDialog.findViewById(R.id.viewLayout);
            if (view == null) {
                linearLayout2.setVisibility(8);
            } else {
                linearLayout2.addView(view);
                linearLayout2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.ui.JDDialogFactory.12
                    {
                        JDDialogFactory.this = this;
                    }

                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        linearLayout2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        int height = linearLayout2.getHeight();
                        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_style10_view_height);
                        if (height <= dimensionPixelSize || !(linearLayout2.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                            return;
                        }
                        ((LinearLayout.LayoutParams) linearLayout2.getLayoutParams()).height = dimensionPixelSize;
                        linearLayout2.requestLayout();
                    }
                });
            }
            return newJDDialog;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle11(Context context, String str, CharSequence charSequence, String str2, PasswordInputView.OnPasswordInputFinishListener onPasswordInputFinishListener) throws IllegalArgumentException {
        if (context != null) {
            final JDDialog newJDDialog = JDDialog.newJDDialog(context);
            newJDDialog.setContentView(R.layout.jd_common_dialog_style_11);
            newJDDialog.setCancelable(false);
            ((ImageView) newJDDialog.findViewById(R.id.jd_dialog_close)).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDDialogFactory.13
                {
                    JDDialogFactory.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    newJDDialog.dismiss();
                }
            });
            newJDDialog.titleView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_title);
            newJDDialog.setTitle(str);
            TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
            newJDDialog.messageView = textView;
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            newJDDialog.setMessage(charSequence);
            PasswordInputView passwordInputView = (PasswordInputView) newJDDialog.findViewById(R.id.jd_dialog_passwordInputView);
            newJDDialog.editText = passwordInputView;
            if (passwordInputView instanceof PasswordInputView) {
                passwordInputView.setFinishListener(onPasswordInputFinishListener);
            }
            newJDDialog.tipLayout = (LinearLayout) newJDDialog.findViewById(R.id.jd_dialog_tip_layout);
            TextView textView2 = (TextView) newJDDialog.findViewById(R.id.jd_dialog_tip_message);
            newJDDialog.tipTextView = textView2;
            textView2.setText(str2);
            return newJDDialog;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle12(Context context, CharSequence charSequence) throws IllegalArgumentException {
        if (context != null) {
            JDDialog newJDDialog = JDDialog.newJDDialog(context);
            newJDDialog.setContentView(R.layout.jd_common_dialog_style_12);
            newJDDialog.setCancelable(false);
            newJDDialog.messageView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
            newJDDialog.setMessage(charSequence);
            return newJDDialog;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle13(Context context, String str, CharSequence charSequence, String str2, String str3) throws IllegalArgumentException {
        if (context != null) {
            if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
                throw new IllegalArgumentException("the param leftButtonText or rightButtonText can not be empty in this dialog style");
            }
            final JDDialog newJDDialog = JDDialog.newJDDialog(context);
            newJDDialog.setContentView(R.layout.jd_common_dialog_style_13);
            newJDDialog.titleView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_title);
            newJDDialog.setTitle(str);
            newJDDialog.messageView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
            newJDDialog.setMessage(charSequence, true);
            if (newJDDialog.titleView.getVisibility() == 8 && newJDDialog.messageView.getVisibility() == 0 && (newJDDialog.messageView.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
                ((FrameLayout.LayoutParams) newJDDialog.messageView.getLayoutParams()).topMargin = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_title_style13_margin_top);
                newJDDialog.messageView.requestLayout();
            }
            newJDDialog.posButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
            newJDDialog.negButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_neg_button);
            Space space = (Space) newJDDialog.findViewById(R.id.space);
            LinearLayout linearLayout = (LinearLayout) newJDDialog.findViewById(R.id.ll_btns);
            if (TextUtils.isEmpty(str2)) {
                space.setVisibility(8);
                newJDDialog.posButton.setVisibility(8);
                newJDDialog.negButton.setBackgroundResource(R.drawable.dialog_red_button);
                newJDDialog.negButton.setTextColor(-1);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.width = DpiUtil.dip2px(context, 150.0f);
                linearLayout.setLayoutParams(layoutParams);
            } else {
                newJDDialog.posButton.setText(str2);
                newJDDialog.useCancelClickEvent(newJDDialog.posButton);
            }
            if (TextUtils.isEmpty(str3)) {
                space.setVisibility(8);
                newJDDialog.negButton.setVisibility(8);
                newJDDialog.posButton.setBackgroundResource(R.drawable.dialog_white_button);
                newJDDialog.posButton.setTextColor(Color.parseColor(JDDarkUtil.COLOR_F2270C));
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams2.width = DpiUtil.dip2px(context, 150.0f);
                linearLayout.setLayoutParams(layoutParams2);
            } else {
                newJDDialog.negButton.setText(str3);
                newJDDialog.useCancelClickEvent(newJDDialog.negButton);
            }
            ((ImageButton) newJDDialog.findViewById(R.id.jd_dialog_close)).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDDialogFactory.16
                {
                    JDDialogFactory.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    newJDDialog.dismiss();
                }
            });
            return newJDDialog;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle14(Context context, String str, CharSequence charSequence, View view, String str2, String str3, boolean z, boolean z2) throws IllegalArgumentException {
        if (context != null) {
            JDDialog newJDDialog = JDDialog.newJDDialog(context);
            newJDDialog.setContentView(R.layout.jd_common_dialog_style_14);
            newJDDialog.titleView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_title);
            newJDDialog.setTitle(str);
            TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
            newJDDialog.messageView = textView;
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            newJDDialog.setMessage(charSequence);
            newJDDialog.posButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
            newJDDialog.negButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_neg_button);
            Space space = (Space) newJDDialog.findViewById(R.id.space);
            LinearLayout linearLayout = (LinearLayout) newJDDialog.findViewById(R.id.ll_btns);
            if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
                linearLayout.setVisibility(8);
            } else {
                linearLayout.setVisibility(0);
            }
            if (TextUtils.isEmpty(str2)) {
                space.setVisibility(8);
                newJDDialog.posButton.setVisibility(8);
                newJDDialog.negButton.setBackgroundResource(R.drawable.dialog_red_button);
                newJDDialog.negButton.setTextColor(-1);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.width = DpiUtil.dip2px(context, 150.0f);
                linearLayout.setLayoutParams(layoutParams);
            } else {
                newJDDialog.posButton.setText(str2);
                newJDDialog.useCancelClickEvent(newJDDialog.posButton);
            }
            if (TextUtils.isEmpty(str3)) {
                space.setVisibility(8);
                newJDDialog.negButton.setVisibility(8);
                newJDDialog.posButton.setBackgroundResource(R.drawable.dialog_white_button);
                newJDDialog.posButton.setTextColor(Color.parseColor(JDDarkUtil.COLOR_F2270C));
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams2.width = DpiUtil.dip2px(context, 150.0f);
                linearLayout.setLayoutParams(layoutParams2);
            } else {
                newJDDialog.negButton.setText(str3);
                newJDDialog.useCancelClickEvent(newJDDialog.negButton);
            }
            LinearLayout linearLayout2 = (LinearLayout) newJDDialog.findViewById(R.id.bottomLayout);
            newJDDialog.contentLayout = linearLayout2;
            if (view == null) {
                linearLayout2.setVisibility(8);
            } else {
                if (z2) {
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) linearLayout2.getLayoutParams();
                    if (linearLayout.getVisibility() == 0) {
                        layoutParams3.setMargins(com.jd.lib.un.basewidget.widget.simple.e.a.a(24.0f), com.jd.lib.un.basewidget.widget.simple.e.a.a(12.0f), com.jd.lib.un.basewidget.widget.simple.e.a.a(24.0f), 0);
                    } else {
                        layoutParams3.setMargins(com.jd.lib.un.basewidget.widget.simple.e.a.a(24.0f), com.jd.lib.un.basewidget.widget.simple.e.a.a(12.0f), com.jd.lib.un.basewidget.widget.simple.e.a.a(24.0f), com.jd.lib.un.basewidget.widget.simple.e.a.a(15.0f));
                    }
                    newJDDialog.contentLayout.setLayoutParams(layoutParams3);
                }
                newJDDialog.contentLayout.addView(view);
            }
            if (!z) {
                newJDDialog.useBg(false);
            }
            return newJDDialog;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle1_redbg(Context context, CharSequence charSequence, String str) {
        return createJdDialogWithStyle1_redbg(context, charSequence, str, -1);
    }

    public JDDialog createJdDialogWithStyle2(Context context, CharSequence charSequence, String str, String str2) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(charSequence)) {
                if (!TextUtils.isEmpty(str)) {
                    if (!TextUtils.isEmpty(str2)) {
                        JDDialog newJDDialog = JDDialog.newJDDialog(context);
                        newJDDialog.setContentView(R.layout.jd_common_dialog_style_2);
                        newJDDialog.messageView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
                        newJDDialog.setMessage(charSequence, true, true);
                        Button button = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
                        newJDDialog.posButton = button;
                        button.setText(str);
                        newJDDialog.useCancelClickEvent(newJDDialog.posButton);
                        Button button2 = (Button) newJDDialog.findViewById(R.id.jd_dialog_neg_button);
                        newJDDialog.negButton = button2;
                        button2.setText(str2);
                        newJDDialog.useCancelClickEvent(newJDDialog.negButton);
                        return newJDDialog;
                    }
                    throw new IllegalArgumentException("the param rightButtonText can not be empty in this dialog style");
                }
                throw new IllegalArgumentException("the param leftButtonText can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle3(Context context, CharSequence charSequence, String str, String str2, String str3, String str4) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(charSequence)) {
                if (!TextUtils.isEmpty(str3)) {
                    if (!TextUtils.isEmpty(str4)) {
                        final JDDialog newJDDialog = JDDialog.newJDDialog(context);
                        newJDDialog.setContentView(R.layout.jd_common_dialog_style_3);
                        TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
                        newJDDialog.messageView = textView;
                        textView.setText(charSequence);
                        Button button = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
                        newJDDialog.posButton = button;
                        button.setText(str3);
                        newJDDialog.useCancelClickEvent(newJDDialog.posButton);
                        Button button2 = (Button) newJDDialog.findViewById(R.id.jd_dialog_neg_button);
                        newJDDialog.negButton = button2;
                        button2.setText(str4);
                        newJDDialog.useCancelClickEvent(newJDDialog.negButton);
                        newJDDialog.editText = (EditText) newJDDialog.findViewById(R.id.jd_dialog_input_edit);
                        UnNetImageView unNetImageView = (UnNetImageView) newJDDialog.findViewById(R.id.jd_dialog_input_image);
                        ImageView imageView = (ImageView) unNetImageView.getOriginView();
                        newJDDialog.imageView = imageView;
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        newJDDialog.editText.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(50)});
                        newJDDialog.editText.addTextChangedListener(new TextWatcher() { // from class: com.jingdong.common.ui.JDDialogFactory.1
                            {
                                JDDialogFactory.this = this;
                            }

                            @Override // android.text.TextWatcher
                            public void afterTextChanged(Editable editable) {
                            }

                            @Override // android.text.TextWatcher
                            public void beforeTextChanged(CharSequence charSequence2, int i2, int i3, int i4) {
                            }

                            @Override // android.text.TextWatcher
                            public void onTextChanged(CharSequence charSequence2, int i2, int i3, int i4) {
                                if (charSequence2.length() > 0) {
                                    newJDDialog.negButton.setEnabled(true);
                                } else {
                                    newJDDialog.negButton.setEnabled(false);
                                }
                            }
                        });
                        newJDDialog.tipTextView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_tip_message);
                        if (!TextUtils.isEmpty(str)) {
                            newJDDialog.editText.setHint(str);
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            unNetImageView.setImage(str2);
                        }
                        return newJDDialog;
                    }
                    throw new IllegalArgumentException("the param rightButtonText can not be empty in this dialog style");
                }
                throw new IllegalArgumentException("the param leftButtonText can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDCheckDialog createJdDialogWithStyle4(final Context context, CharSequence charSequence, ArrayList<String> arrayList, String str, String str2) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(charSequence)) {
                if (!TextUtils.isEmpty(str)) {
                    if (!TextUtils.isEmpty(str2)) {
                        if (arrayList != null && arrayList.size() >= 1) {
                            final JDCheckDialog newJDCheckDialog = JDCheckDialog.newJDCheckDialog(context);
                            newJDCheckDialog.setContentView(R.layout.jd_common_dialog_style_4);
                            newJDCheckDialog.messageView = (TextView) newJDCheckDialog.findViewById(R.id.jd_dialog_message);
                            newJDCheckDialog.setMessage(charSequence, true);
                            Button button = (Button) newJDCheckDialog.findViewById(R.id.jd_dialog_pos_button);
                            newJDCheckDialog.posButton = button;
                            button.setText(str);
                            newJDCheckDialog.useCancelClickEvent(newJDCheckDialog.posButton);
                            Button button2 = (Button) newJDCheckDialog.findViewById(R.id.jd_dialog_neg_button);
                            newJDCheckDialog.negButton = button2;
                            button2.setText(str2);
                            newJDCheckDialog.useCancelClickEvent(newJDCheckDialog.negButton);
                            newJDCheckDialog.messageView.setMovementMethod(ScrollingMovementMethod.getInstance());
                            newJDCheckDialog.initListView(context, null, arrayList, "style4");
                            final LinearLayout linearLayout = (LinearLayout) newJDCheckDialog.findViewById(R.id.contentView);
                            final LinearLayout linearLayout2 = (LinearLayout) newJDCheckDialog.findViewById(R.id.ll_btns);
                            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.ui.JDDialogFactory.3
                                {
                                    JDDialogFactory.this = this;
                                }

                                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                                public void onGlobalLayout() {
                                    linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                    int height = linearLayout.getHeight();
                                    int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_content_maxheight);
                                    if (height > dimensionPixelSize) {
                                        if (linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                                            ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).height = dimensionPixelSize;
                                            linearLayout.requestLayout();
                                        }
                                        height = dimensionPixelSize;
                                    }
                                    newJDCheckDialog.messageView.setMaxHeight(((((DpiUtil.getHeight(context) / 3) * 2) - height) - linearLayout2.getMeasuredHeight()) - DpiUtil.dip2px(context, 105.0f));
                                }
                            });
                            return newJDCheckDialog;
                        }
                        throw new IllegalArgumentException("the param items can not be empty in this dialog style");
                    }
                    throw new IllegalArgumentException("the param rightButtonText can not be empty in this dialog style");
                }
                throw new IllegalArgumentException("the param leftButtonText can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle5(Context context, String str, CharSequence charSequence, String str2) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(charSequence)) {
                    if (!TextUtils.isEmpty(str2)) {
                        JDDialog newJDDialog = JDDialog.newJDDialog(context);
                        newJDDialog.setContentView(R.layout.jd_common_dialog_style_5);
                        TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_title);
                        newJDDialog.titleView = textView;
                        textView.setText(str);
                        newJDDialog.messageView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
                        newJDDialog.setMessage(charSequence, true);
                        newJDDialog.setPosAccent(true);
                        Button button = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
                        newJDDialog.posButton = button;
                        button.setText(str2);
                        newJDDialog.useCancelClickEvent(newJDDialog.posButton);
                        return newJDDialog;
                    }
                    throw new IllegalArgumentException("the param buttonText can not be empty in this dialog style");
                }
                throw new IllegalArgumentException("the param message can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param title can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDCheckDialog createJdDialogWithStyle6(Context context, String str, CharSequence charSequence, String str2, String str3) throws IllegalArgumentException {
        if (context != null) {
            if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
                throw new IllegalArgumentException("the param leftButtonText or rightButtonText can not be empty in this dialog style");
            }
            JDCheckDialog newJDCheckDialog = JDCheckDialog.newJDCheckDialog(context);
            newJDCheckDialog.setContentView(R.layout.jd_common_dialog_style_6);
            newJDCheckDialog.titleView = (TextView) newJDCheckDialog.findViewById(R.id.jd_dialog_title);
            newJDCheckDialog.setTitle(str);
            newJDCheckDialog.messageView = (TextView) newJDCheckDialog.findViewById(R.id.jd_dialog_message);
            newJDCheckDialog.setMessage(charSequence, true);
            if (newJDCheckDialog.titleView.getVisibility() == 8 && newJDCheckDialog.messageView.getVisibility() == 0 && (newJDCheckDialog.messageView.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
                ((FrameLayout.LayoutParams) newJDCheckDialog.messageView.getLayoutParams()).topMargin = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_title_margin_top);
                newJDCheckDialog.messageView.requestLayout();
            }
            newJDCheckDialog.posButton = (Button) newJDCheckDialog.findViewById(R.id.jd_dialog_pos_button);
            newJDCheckDialog.negButton = (Button) newJDCheckDialog.findViewById(R.id.jd_dialog_neg_button);
            Space space = (Space) newJDCheckDialog.findViewById(R.id.space);
            LinearLayout linearLayout = (LinearLayout) newJDCheckDialog.findViewById(R.id.ll_btns);
            if (TextUtils.isEmpty(str2)) {
                space.setVisibility(8);
                newJDCheckDialog.posButton.setVisibility(8);
                newJDCheckDialog.negButton.setBackgroundResource(R.drawable.dialog_red_button);
                newJDCheckDialog.negButton.setTextColor(-1);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.width = DpiUtil.dip2px(context, 150.0f);
                linearLayout.setLayoutParams(layoutParams);
            } else {
                newJDCheckDialog.posButton.setText(str2);
                newJDCheckDialog.useCancelClickEvent(newJDCheckDialog.posButton);
            }
            if (TextUtils.isEmpty(str3)) {
                space.setVisibility(8);
                newJDCheckDialog.negButton.setVisibility(8);
                newJDCheckDialog.posButton.setBackgroundResource(R.drawable.dialog_white_button);
                newJDCheckDialog.posButton.setTextColor(Color.parseColor(JDDarkUtil.COLOR_F2270C));
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams2.width = DpiUtil.dip2px(context, 150.0f);
                linearLayout.setLayoutParams(layoutParams2);
            } else {
                newJDCheckDialog.negButton.setText(str3);
                newJDCheckDialog.useCancelClickEvent(newJDCheckDialog.negButton);
            }
            return newJDCheckDialog;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle7(Context context, String str, CharSequence charSequence, String str2, String str3, String str4, String str5) throws IllegalArgumentException {
        return createJdDialogWithStyle7(context, str, charSequence, str2, str3, str4, str5, false);
    }

    public JDListDialog createJdDialogWithStyle8(final Context context, String str, List<ListDialogEntity> list, String str2) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(str2)) {
                    if (list != null && list.size() >= 1) {
                        final JDListDialog jDListDialog = new JDListDialog(context);
                        jDListDialog.setContentView(R.layout.jd_common_dialog_style_8);
                        TextView textView = (TextView) jDListDialog.findViewById(R.id.jd_dialog_title);
                        jDListDialog.titleView = textView;
                        textView.setText(str);
                        jDListDialog.setPosAccent(true);
                        Button button = (Button) jDListDialog.findViewById(R.id.jd_dialog_pos_button);
                        jDListDialog.posButton = button;
                        button.setText(str2);
                        jDListDialog.useCancelClickEvent(jDListDialog.posButton);
                        jDListDialog.titleView.setMovementMethod(ScrollingMovementMethod.getInstance());
                        final LinearLayout linearLayout = (LinearLayout) jDListDialog.findViewById(R.id.ll_btns);
                        jDListDialog.initListView(context, null, list);
                        final LinearLayout linearLayout2 = (LinearLayout) jDListDialog.findViewById(R.id.contentView);
                        linearLayout2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.ui.JDDialogFactory.10
                            {
                                JDDialogFactory.this = this;
                            }

                            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                            public void onGlobalLayout() {
                                linearLayout2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                int height = linearLayout2.getHeight();
                                int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_content_maxheight);
                                if (height > dimensionPixelSize) {
                                    if (linearLayout2.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                                        ((LinearLayout.LayoutParams) linearLayout2.getLayoutParams()).height = dimensionPixelSize;
                                        linearLayout2.requestLayout();
                                    }
                                    height = dimensionPixelSize;
                                }
                                jDListDialog.titleView.setMaxHeight(((((DpiUtil.getHeight(context) / 3) * 2) - height) - linearLayout.getMeasuredHeight()) - DpiUtil.dip2px(context, 102.0f));
                            }
                        });
                        return jDListDialog;
                    }
                    throw new IllegalArgumentException("the param items can not be empty in this dialog style");
                }
                throw new IllegalArgumentException("the param buttonText can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle9(Context context, String str, CharSequence charSequence, View view, String str2, String str3) throws IllegalArgumentException {
        if (context != null) {
            JDDialog newJDDialog = JDDialog.newJDDialog(context);
            newJDDialog.setContentView(R.layout.jd_common_dialog_style_9);
            newJDDialog.titleView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_title);
            newJDDialog.setTitle(str);
            TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
            newJDDialog.messageView = textView;
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            newJDDialog.setMessage(charSequence);
            newJDDialog.posButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
            newJDDialog.negButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_neg_button);
            Space space = (Space) newJDDialog.findViewById(R.id.space);
            LinearLayout linearLayout = (LinearLayout) newJDDialog.findViewById(R.id.ll_btns);
            if (TextUtils.isEmpty(str2)) {
                space.setVisibility(8);
                newJDDialog.posButton.setVisibility(8);
                newJDDialog.negButton.setBackgroundResource(R.drawable.dialog_red_button);
                newJDDialog.negButton.setTextColor(-1);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.width = DpiUtil.dip2px(context, 150.0f);
                linearLayout.setLayoutParams(layoutParams);
            } else {
                newJDDialog.posButton.setText(str2);
                newJDDialog.useCancelClickEvent(newJDDialog.posButton);
            }
            if (TextUtils.isEmpty(str3)) {
                space.setVisibility(8);
                newJDDialog.negButton.setVisibility(8);
                newJDDialog.posButton.setBackgroundResource(R.drawable.dialog_white_button);
                newJDDialog.posButton.setTextColor(Color.parseColor(JDDarkUtil.COLOR_F2270C));
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams2.width = DpiUtil.dip2px(context, 150.0f);
                linearLayout.setLayoutParams(layoutParams2);
            } else {
                newJDDialog.negButton.setText(str3);
                newJDDialog.useCancelClickEvent(newJDDialog.negButton);
            }
            LinearLayout linearLayout2 = (LinearLayout) newJDDialog.findViewById(R.id.bottomLayout);
            newJDDialog.contentLayout = linearLayout2;
            if (view == null) {
                linearLayout2.setVisibility(8);
            } else {
                linearLayout2.addView(view);
            }
            return newJDDialog;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyleTimer(Context context, String str, String str2, int i2) throws IllegalArgumentException {
        return createJdDialogWithStyleTimer(context, str, str2, "", i2, "");
    }

    public JDDialog createJdDialogWithStyleTop(Context context, CharSequence charSequence, CharSequence charSequence2) throws IllegalArgumentException {
        if (context != null) {
            JDDialog newJDDialog = JDDialog.newJDDialog(context);
            newJDDialog.setContentView(R.layout.jd_common_dialog_style_top);
            newJDDialog.messageView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
            newJDDialog.setMessage(charSequence2, false, false);
            TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_title);
            newJDDialog.titleView = textView;
            textView.setText(charSequence);
            return newJDDialog;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyleX(final Context context, View view, final View.OnClickListener onClickListener) throws IllegalArgumentException {
        if (context != null) {
            final JDDialog newJDDialog = JDDialog.newJDDialog(context);
            newJDDialog.setContentView(R.layout.jd_common_dialog_style_x);
            newJDDialog.setCanceledOnTouchOutside(true);
            final ImageButton imageButton = (ImageButton) newJDDialog.findViewById(R.id.jd_dialog_close);
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDDialogFactory.14
                {
                    JDDialogFactory.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    newJDDialog.dismiss();
                    View.OnClickListener onClickListener2 = onClickListener;
                    if (onClickListener2 != null) {
                        onClickListener2.onClick(imageButton);
                    }
                }
            });
            LinearLayout linearLayout = (LinearLayout) newJDDialog.findViewById(R.id.content_layout);
            newJDDialog.contentLayout = linearLayout;
            if (view != null) {
                linearLayout.addView(view);
                newJDDialog.contentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.ui.JDDialogFactory.15
                    {
                        JDDialogFactory.this = this;
                    }

                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        newJDDialog.contentLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        int height = newJDDialog.contentLayout.getHeight();
                        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_stylex_max_height);
                        if (height <= dimensionPixelSize || !(newJDDialog.contentLayout.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                            return;
                        }
                        ((LinearLayout.LayoutParams) newJDDialog.contentLayout.getLayoutParams()).height = dimensionPixelSize;
                        newJDDialog.contentLayout.requestLayout();
                    }
                });
            }
            return newJDDialog;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle1_redbg(Context context, CharSequence charSequence, String str, int i2) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(charSequence)) {
                if (!TextUtils.isEmpty(str)) {
                    JDDialog newJDDialog = JDDialog.newJDDialog(context);
                    newJDDialog.setContentView(R.layout.jd_common_dialog_style_1_redbg);
                    TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
                    newJDDialog.messageView = textView;
                    if (i2 != -1) {
                        textView.setText(charSequence);
                        newJDDialog.messageView.setGravity(i2);
                    } else {
                        newJDDialog.setMessage(charSequence, true, true);
                    }
                    newJDDialog.setPosAccent(true);
                    Button button = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
                    newJDDialog.posButton = button;
                    button.setText(str);
                    newJDDialog.useCancelClickEvent(newJDDialog.posButton);
                    return newJDDialog;
                }
                throw new IllegalArgumentException("the param buttonText can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle7(Context context, String str, CharSequence charSequence, String str2, String str3, String str4, String str5, boolean z) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(charSequence)) {
                    if (!TextUtils.isEmpty(str3)) {
                        if (!TextUtils.isEmpty(str4)) {
                            if (!TextUtils.isEmpty(str5)) {
                                final JDDialog newJDDialog = JDDialog.newJDDialog(context);
                                newJDDialog.setContentView(R.layout.jd_common_dialog_style_7);
                                newJDDialog.titleView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_title);
                                newJDDialog.messageView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message);
                                newJDDialog.titleView.setText(str);
                                newJDDialog.setMessage(charSequence, true);
                                Button button = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
                                newJDDialog.posButton = button;
                                button.setText(str4);
                                newJDDialog.useCancelClickEvent(newJDDialog.posButton);
                                Button button2 = (Button) newJDDialog.findViewById(R.id.jd_dialog_neg_button);
                                newJDDialog.negButton = button2;
                                button2.setText(str5);
                                newJDDialog.editText = (EditText) newJDDialog.findViewById(R.id.jd_dialog_input_edit);
                                final Drawable drawable = context.getResources().getDrawable(R.drawable.dialog_edit_clear);
                                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                                newJDDialog.editText.addTextChangedListener(new TextWatcher() { // from class: com.jingdong.common.ui.JDDialogFactory.8
                                    {
                                        JDDialogFactory.this = this;
                                    }

                                    @Override // android.text.TextWatcher
                                    public void afterTextChanged(Editable editable) {
                                    }

                                    @Override // android.text.TextWatcher
                                    public void beforeTextChanged(CharSequence charSequence2, int i2, int i3, int i4) {
                                    }

                                    @Override // android.text.TextWatcher
                                    public void onTextChanged(CharSequence charSequence2, int i2, int i3, int i4) {
                                        Drawable drawable2 = charSequence2.length() > 0 ? drawable : null;
                                        EditText editText = newJDDialog.editText;
                                        editText.setCompoundDrawables(editText.getCompoundDrawables()[0], newJDDialog.editText.getCompoundDrawables()[1], drawable2, newJDDialog.editText.getCompoundDrawables()[3]);
                                        if (charSequence2.length() > 0) {
                                            JDDialog jDDialog = newJDDialog;
                                            if (jDDialog.isStartTimeCount && !jDDialog.isFinishTimeCount) {
                                                jDDialog.negButton.setEnabled(false);
                                                return;
                                            } else {
                                                jDDialog.negButton.setEnabled(true);
                                                return;
                                            }
                                        }
                                        newJDDialog.negButton.setEnabled(false);
                                    }
                                });
                                newJDDialog.editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.ui.JDDialogFactory.9
                                    {
                                        JDDialogFactory.this = this;
                                    }

                                    @Override // android.view.View.OnTouchListener
                                    public boolean onTouch(View view, MotionEvent motionEvent) {
                                        if (motionEvent.getAction() == 1 && newJDDialog.editText.getCompoundDrawables()[2] != null) {
                                            if (motionEvent.getX() > ((float) (newJDDialog.editText.getWidth() - newJDDialog.editText.getTotalPaddingRight())) && motionEvent.getX() < ((float) (newJDDialog.editText.getWidth() - newJDDialog.editText.getPaddingRight()))) {
                                                newJDDialog.editText.setText("");
                                            }
                                        }
                                        return false;
                                    }
                                });
                                if (z) {
                                    newJDDialog.editText.setInputType(2);
                                }
                                newJDDialog.tipLayout = (LinearLayout) newJDDialog.findViewById(R.id.jd_dialog_tip_layout);
                                TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_tip_message);
                                newJDDialog.tipTextView = textView;
                                textView.setText(str3);
                                if (!TextUtils.isEmpty(str2)) {
                                    newJDDialog.editText.setHint(str2);
                                }
                                LinearLayout linearLayout = (LinearLayout) newJDDialog.findViewById(R.id.progressbar_layout);
                                newJDDialog.progressBarLayout = linearLayout;
                                linearLayout.setOnClickListener(null);
                                return newJDDialog;
                            }
                            throw new IllegalArgumentException("the param rightButtonText can not be empty in this dialog style");
                        }
                        throw new IllegalArgumentException("the param leftButtonText can not be empty in this dialog style");
                    }
                    throw new IllegalArgumentException("the param tipMessage can not be empty in this dialog style");
                }
                throw new IllegalArgumentException("the param message can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyleTimer(Context context, String str, String str2, String str3, int i2, String str4) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(str2)) {
                    JDDialog newJDDialog = JDDialog.newJDDialog(context);
                    newJDDialog.setContentView(R.layout.jd_common_dialog_style_timer);
                    TextView textView = (TextView) newJDDialog.findViewById(R.id.jd_dialog_title);
                    newJDDialog.titleView = textView;
                    textView.setText(str);
                    newJDDialog.timer = (TextView) newJDDialog.findViewById(R.id.jd_dialog_message2);
                    Typeface typeface = UnWidgetThemeController.getInstance().getTypeface(context, 1);
                    if (typeface != null) {
                        newJDDialog.timer.setTypeface(typeface);
                    }
                    newJDDialog.setCountdown(i2);
                    View findViewById = newJDDialog.findViewById(R.id.qrLayout);
                    if (TextUtils.isEmpty(str4)) {
                        findViewById.setVisibility(8);
                    } else {
                        newJDDialog.dialogType = 15;
                        Bitmap qrCode = UnWidgetThemeController.getInstance().qrCode(str4);
                        if (qrCode != null) {
                            findViewById.setVisibility(0);
                            ((ImageView) newJDDialog.findViewById(R.id.qrCode)).setImageBitmap(qrCode);
                        }
                        try {
                            newJDDialog.requestId = new JSONObject(str4).getString("id");
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    View findViewById2 = newJDDialog.findViewById(R.id.ll_btns);
                    if (TextUtils.isEmpty(str3)) {
                        Button button = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button);
                        newJDDialog.posButton = button;
                        button.setVisibility(0);
                        findViewById2.setVisibility(8);
                    } else {
                        newJDDialog.findViewById(R.id.jd_dialog_pos_button).setVisibility(8);
                        findViewById2.setVisibility(0);
                        newJDDialog.posButton = (Button) newJDDialog.findViewById(R.id.jd_dialog_pos_button_2);
                        Button button2 = (Button) newJDDialog.findViewById(R.id.jd_dialog_neg_button);
                        newJDDialog.negButton = button2;
                        button2.setText(str3);
                    }
                    newJDDialog.posButton.setText(str2);
                    newJDDialog.setPosAccent(false);
                    newJDDialog.useCancelClickEvent(newJDDialog.posButton);
                    return newJDDialog;
                }
                throw new IllegalArgumentException("the param buttonText can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param title can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDCheckDialog createJdDialogWithStyle5(final Context context, String str, CharSequence charSequence, ArrayList<String> arrayList, String str2) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(charSequence)) {
                    if (!TextUtils.isEmpty(str2)) {
                        JDCheckDialog newJDCheckDialog = JDCheckDialog.newJDCheckDialog(context);
                        newJDCheckDialog.setContentView(R.layout.jd_common_dialog_style_5_2);
                        TextView textView = (TextView) newJDCheckDialog.findViewById(R.id.jd_dialog_title);
                        newJDCheckDialog.titleView = textView;
                        textView.setText(str);
                        newJDCheckDialog.messageView = (TextView) newJDCheckDialog.findViewById(R.id.jd_dialog_message);
                        newJDCheckDialog.setMessage(charSequence, true);
                        newJDCheckDialog.setPosAccent(true);
                        Button button = (Button) newJDCheckDialog.findViewById(R.id.jd_dialog_pos_button);
                        newJDCheckDialog.posButton = button;
                        button.setText(str2);
                        newJDCheckDialog.useCancelClickEvent(newJDCheckDialog.posButton);
                        if (arrayList != null && arrayList.size() > 0) {
                            newJDCheckDialog.initListView(context, null, arrayList, "style6");
                            final LinearLayout linearLayout = (LinearLayout) newJDCheckDialog.findViewById(R.id.contentView);
                            linearLayout.setVisibility(0);
                            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.ui.JDDialogFactory.5
                                {
                                    JDDialogFactory.this = this;
                                }

                                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                                public void onGlobalLayout() {
                                    linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                    int height = linearLayout.getHeight();
                                    int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_content_maxheight);
                                    if (height <= dimensionPixelSize || !(linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                                        return;
                                    }
                                    ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).height = dimensionPixelSize;
                                    linearLayout.requestLayout();
                                }
                            });
                        }
                        return newJDCheckDialog;
                    }
                    throw new IllegalArgumentException("the param buttonText can not be empty in this dialog style");
                }
                throw new IllegalArgumentException("the param message can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param title can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDListDialog createJdDialogWithStyle8(final Context context, String str, BaseAdapter baseAdapter, String str2) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    throw new IllegalArgumentException("the param buttonText can not be empty in this dialog style");
                }
                if (baseAdapter != null) {
                    final JDListDialog jDListDialog = new JDListDialog(context);
                    jDListDialog.setContentView(R.layout.jd_common_dialog_style_8);
                    TextView textView = (TextView) jDListDialog.findViewById(R.id.jd_dialog_title);
                    jDListDialog.titleView = textView;
                    textView.setText(str);
                    jDListDialog.setPosAccent(true);
                    Button button = (Button) jDListDialog.findViewById(R.id.jd_dialog_pos_button);
                    jDListDialog.posButton = button;
                    button.setText(str2);
                    jDListDialog.useCancelClickEvent(jDListDialog.posButton);
                    jDListDialog.titleView.setMovementMethod(ScrollingMovementMethod.getInstance());
                    final LinearLayout linearLayout = (LinearLayout) jDListDialog.findViewById(R.id.ll_btns);
                    jDListDialog.initListView(context, baseAdapter, null);
                    final LinearLayout linearLayout2 = (LinearLayout) jDListDialog.findViewById(R.id.contentView);
                    linearLayout2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.ui.JDDialogFactory.11
                        {
                            JDDialogFactory.this = this;
                        }

                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public void onGlobalLayout() {
                            linearLayout2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            int height = linearLayout2.getHeight();
                            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_content_maxheight);
                            if (height > dimensionPixelSize) {
                                if (linearLayout2.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                                    ((LinearLayout.LayoutParams) linearLayout2.getLayoutParams()).height = dimensionPixelSize;
                                    linearLayout2.requestLayout();
                                }
                                height = dimensionPixelSize;
                            }
                            jDListDialog.titleView.setMaxHeight(((((DpiUtil.getHeight(context) / 3) * 2) - height) - linearLayout.getMeasuredHeight()) - DpiUtil.dip2px(context, 102.0f));
                        }
                    });
                    return jDListDialog;
                }
                throw new IllegalArgumentException("the param items can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param title can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDCheckDialog createJdDialogWithStyle4(final Context context, CharSequence charSequence, BaseAdapter baseAdapter, String str, String str2) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(charSequence)) {
                if (!TextUtils.isEmpty(str)) {
                    if (TextUtils.isEmpty(str2)) {
                        throw new IllegalArgumentException("the param rightButtonText can not be empty in this dialog style");
                    }
                    if (baseAdapter != null) {
                        final JDCheckDialog newJDCheckDialog = JDCheckDialog.newJDCheckDialog(context);
                        newJDCheckDialog.setContentView(R.layout.jd_common_dialog_style_4);
                        newJDCheckDialog.messageView = (TextView) newJDCheckDialog.findViewById(R.id.jd_dialog_message);
                        newJDCheckDialog.setMessage(charSequence, true);
                        Button button = (Button) newJDCheckDialog.findViewById(R.id.jd_dialog_pos_button);
                        newJDCheckDialog.posButton = button;
                        button.setText(str);
                        newJDCheckDialog.useCancelClickEvent(newJDCheckDialog.posButton);
                        Button button2 = (Button) newJDCheckDialog.findViewById(R.id.jd_dialog_neg_button);
                        newJDCheckDialog.negButton = button2;
                        button2.setText(str2);
                        newJDCheckDialog.useCancelClickEvent(newJDCheckDialog.negButton);
                        newJDCheckDialog.messageView.setMovementMethod(ScrollingMovementMethod.getInstance());
                        final LinearLayout linearLayout = (LinearLayout) newJDCheckDialog.findViewById(R.id.ll_btns);
                        newJDCheckDialog.initListView(context, baseAdapter, null, "style4");
                        final LinearLayout linearLayout2 = (LinearLayout) newJDCheckDialog.findViewById(R.id.contentView);
                        linearLayout2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.ui.JDDialogFactory.4
                            {
                                JDDialogFactory.this = this;
                            }

                            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                            public void onGlobalLayout() {
                                linearLayout2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                int height = linearLayout2.getHeight();
                                int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_content_maxheight);
                                if (height > dimensionPixelSize) {
                                    if (linearLayout2.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                                        ((LinearLayout.LayoutParams) linearLayout2.getLayoutParams()).height = dimensionPixelSize;
                                        linearLayout2.requestLayout();
                                    }
                                    height = dimensionPixelSize;
                                }
                                newJDCheckDialog.messageView.setMaxHeight(((((DpiUtil.getHeight(context) / 3) * 2) - height) - linearLayout.getMeasuredHeight()) - DpiUtil.dip2px(context, 105.0f));
                            }
                        });
                        return newJDCheckDialog;
                    }
                    throw new IllegalArgumentException("the param items can not be empty in this dialog style");
                }
                throw new IllegalArgumentException("the param leftButtonText can not be empty in this dialog style");
            }
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdDialogWithStyle9(Context context, String str, CharSequence charSequence, View view, int i2, String str2, String str3) throws IllegalArgumentException {
        JDDialog createJdDialogWithStyle9 = createJdDialogWithStyle9(context, str, charSequence, view, str2, str3);
        createJdDialogWithStyle9.contentLayout.setGravity(i2);
        return createJdDialogWithStyle9;
    }

    public JDDialog createJdDialogWithStyle10(Context context, String str, CharSequence charSequence, Drawable drawable, View view, String str2, String str3) throws IllegalArgumentException {
        JDDialog createJdDialogWithStyle10 = createJdDialogWithStyle10(context, str, charSequence, view, str2, str3);
        if (!TextUtils.isEmpty(charSequence) && drawable != null && createJdDialogWithStyle10.messageView != null) {
            String str4 = ((Object) charSequence) + "  ";
            SpannableString spannableString = new SpannableString(str4);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            spannableString.setSpan(new ImageSpan(drawable, 0), str4.length() - 1, str4.length(), 17);
            createJdDialogWithStyle10.messageView.setText(spannableString);
        }
        return createJdDialogWithStyle10;
    }

    public JDCheckDialog createJdDialogWithStyle6(final Context context, String str, CharSequence charSequence, ArrayList<String> arrayList, String str2, String str3) throws IllegalArgumentException {
        JDCheckDialog createJdDialogWithStyle6 = createJdDialogWithStyle6(context, str, charSequence, str2, str3);
        if (arrayList != null && arrayList.size() > 0) {
            createJdDialogWithStyle6.initListView(context, null, arrayList, "style6");
            final LinearLayout linearLayout = (LinearLayout) createJdDialogWithStyle6.findViewById(R.id.contentView);
            linearLayout.setVisibility(0);
            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.ui.JDDialogFactory.6
                {
                    JDDialogFactory.this = this;
                }

                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int height = linearLayout.getHeight();
                    int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_content_maxheight);
                    if (height <= dimensionPixelSize || !(linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                        return;
                    }
                    ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).height = dimensionPixelSize;
                    linearLayout.requestLayout();
                }
            });
        }
        return createJdDialogWithStyle6;
    }

    public JDCheckDialog createJdDialogWithStyle6(final Context context, String str, CharSequence charSequence, BaseAdapter baseAdapter, String str2, String str3) throws IllegalArgumentException {
        JDCheckDialog createJdDialogWithStyle6 = createJdDialogWithStyle6(context, str, charSequence, str2, str3);
        if (baseAdapter != null) {
            createJdDialogWithStyle6.initListView(context, baseAdapter, null, "style6");
            final LinearLayout linearLayout = (LinearLayout) createJdDialogWithStyle6.findViewById(R.id.contentView);
            linearLayout.setVisibility(0);
            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.ui.JDDialogFactory.7
                {
                    JDDialogFactory.this = this;
                }

                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int height = linearLayout.getHeight();
                    int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.base_ui_jd_dialog_content_maxheight);
                    if (height <= dimensionPixelSize || !(linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                        return;
                    }
                    ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).height = dimensionPixelSize;
                    linearLayout.requestLayout();
                }
            });
        }
        return createJdDialogWithStyle6;
    }
}
