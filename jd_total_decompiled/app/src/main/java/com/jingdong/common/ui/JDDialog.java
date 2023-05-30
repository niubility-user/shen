package com.jingdong.common.ui;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.basewidget.widget.watermark.WatermarkHelper;
import com.jd.lib.un.global.GlobalThemeController;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.unification.dialog.UnBaseDialog;
import com.jingdong.common.widget.PasswordInputView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDDialog extends UnBaseDialog {
    public static final int DIALOG_TYPE_TIMER_NEW = 16;
    public static final int DIALOG_TYPE_TIMER_QR = 15;
    private static final String TAG = "JDDialog";
    private static GlobalThemeController controller;
    private ImageView bottomClose;
    private View.OnClickListener cancelClickListener;
    public LinearLayout contentLayout;
    private int countDownTime;
    public int dialogId;
    public int dialogType;
    public CountDownTimer downTimer;
    public EditText editText;
    public ImageView imageView;
    public boolean isFinishTimeCount;
    boolean isOutsideContentView;
    private boolean isPosAccent;
    public boolean isStartTimeCount;
    public boolean isSupportBottomClose;
    public boolean isTop;
    public int maxHeight;
    public boolean maxHeightEnable;
    public TextView messageView;
    public Button negButton;
    public ImageButton negImgButton;
    public JdDialogParam param;
    public Button posButton;
    public LinearLayout progressBarLayout;
    public String requestId;
    private FrameLayout rootContentLayout;
    private FrameLayout.LayoutParams rootLayoutParams;
    private View rootView;
    public TextView sec;
    public TextView secondMessageView;
    public TextView secondTitleView;
    public boolean statusBarFollowActivity;
    private boolean themeChange;
    public ProgressBar timeProgress;
    public TextView timer;
    public TextView timerStartText;
    public ImageView tipImage;
    public LinearLayout tipLayout;
    public TextView tipTextView;
    public TextView titleView;
    private Boolean useBg;

    /* loaded from: classes6.dex */
    public interface DialogTimeEndListener {
        void onFinish(TextView textView, TextView textView2);
    }

    public JDDialog(Context context) {
        super(context, R.style.JD_Dialog_Common);
        this.maxHeightEnable = false;
        this.maxHeight = 0;
        this.requestId = "";
        this.cancelClickListener = new View.OnClickListener() { // from class: com.jingdong.common.ui.JDDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDDialog.this.cancel();
            }
        };
        setCanceledOnTouchOutside(false);
        setAutoElderMode(UnWidgetThemeController.getInstance().isDialogAutoElder());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject createEventParam() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JdDialogParam jdDialogParam = this.param;
        if (jdDialogParam == null) {
            return jSONObject;
        }
        jSONObject.put("pageid", jdDialogParam.pageId);
        jSONObject.put("functionid", this.param.functionId);
        return jSONObject;
    }

    private void initTimeProgress() {
        ProgressBar progressBar = this.timeProgress;
        if (progressBar != null) {
            progressBar.setMax(this.countDownTime);
            this.timeProgress.setProgress(0);
        }
    }

    private boolean isOverTime() {
        return this.timer.getVisibility() != 0;
    }

    public static JDDialog newJDDialog(Context context) {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        controller = newInstance;
        if (newInstance != null && newInstance.isCustomTheme() && controller.getThemeConfig().c() > 0) {
            return new JDDialog(context, controller.getThemeConfig().c());
        }
        return new JDDialog(context);
    }

    private void resetHeight() {
        View view;
        if (this.maxHeightEnable && (view = this.rootView) != null) {
            this.rootLayoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            this.rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.jingdong.common.ui.JDDialog.6
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    int height = view2.getHeight();
                    JDDialog jDDialog = JDDialog.this;
                    if (jDDialog.maxHeight <= 0) {
                        jDDialog.maxHeight = (DpiUtil.getHeight(jDDialog.getContext()) * 2) / 3;
                    }
                    JDDialog jDDialog2 = JDDialog.this;
                    int i10 = jDDialog2.maxHeight;
                    if (i10 <= 0 || height <= i10) {
                        return;
                    }
                    jDDialog2.rootLayoutParams.height = i10;
                    JDDialog.this.rootView.setLayoutParams(JDDialog.this.rootLayoutParams);
                }
            });
        }
    }

    private void setWindowWith() {
        int width;
        View view;
        if (UnAndroidUtils.isMatex(getContext()) || UnAndroidUtils.isFoldScreen()) {
            return;
        }
        int height = DpiUtil.getHeight(getContext());
        if (getContext() instanceof Activity) {
            width = DpiUtil.getAppWidth((Activity) getContext());
        } else {
            width = DpiUtil.getWidth(getContext());
        }
        if (height <= 0 || width <= 0) {
            return;
        }
        int dip2px = DpiUtil.dip2px(getContext(), 80.0f);
        if (this.isTop) {
            dip2px = DpiUtil.dip2px(getContext(), 10.0f);
        }
        int i2 = width - dip2px;
        if (i2 > getContext().getResources().getDimensionPixelSize(R.dimen.jd_dialog_width) && height > width && (view = this.rootView) != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            this.rootLayoutParams = layoutParams;
            layoutParams.width = i2;
            this.rootView.setLayoutParams(layoutParams);
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            Window window = getWindow();
            attributes.width = i2;
            window.setAttributes(attributes);
        }
    }

    private void showTimer() {
        TextView textView = this.sec;
        if (textView != null) {
            textView.setVisibility(0);
        }
        TextView textView2 = this.timer;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        TextView textView3 = this.timerStartText;
        if (textView3 != null) {
            textView3.setText("\u9884\u8ba1\u8fd8\u9700\u7b49\u5f85");
        }
    }

    private void toggleAutoShowSoftKeyBoard(boolean z) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (attributes == null) {
            return;
        }
        if (z) {
            if (Build.VERSION.SDK_INT >= 28) {
                this.editText.setFocusable(true);
                this.editText.setFocusableInTouchMode(true);
                this.editText.requestFocus();
            }
            attributes.softInputMode |= 4;
        } else {
            attributes.softInputMode &= -5;
        }
        getWindow().setSoftInputMode(attributes.softInputMode);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        super.cancel();
        CountDownTimer countDownTimer = this.downTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override // com.jingdong.common.unification.dialog.UnBaseDialog
    public void darkMode() {
        FrameLayout frameLayout;
        this.themeChange = true;
        Boolean bool = this.useBg;
        if (bool == null || bool.booleanValue()) {
            if (this.isSupportBottomClose && (frameLayout = this.rootContentLayout) != null) {
                frameLayout.setBackgroundResource(R.drawable.jd_dialog_common_bg_dark);
            } else {
                Window window = getWindow();
                if (window != null) {
                    window.setBackgroundDrawableResource(R.drawable.jd_dialog_common_bg_dark);
                }
            }
        }
        TextView textView = this.messageView;
        if (textView != null) {
            textView.setTextSize(14.0f);
            this.messageView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1_dark));
        }
        TextView textView2 = this.titleView;
        if (textView2 != null) {
            textView2.setTextSize(16.0f);
            this.titleView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1_dark));
        }
        TextView textView3 = this.secondMessageView;
        if (textView3 != null) {
            textView3.setTextSize(14.0f);
            this.secondMessageView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1_dark));
        }
        Button button = this.posButton;
        if (button != null) {
            button.getLayoutParams().height = DpiUtil.dip2px(getContext(), 31.0f);
            this.posButton.setTextSize(14.0f);
            this.posButton.setTypeface(Typeface.DEFAULT);
            if (this.isPosAccent) {
                this.posButton.setBackgroundResource(R.drawable.button_d_a_dark);
                this.posButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_d_a_font_color_dark));
            } else {
                this.posButton.setBackgroundResource(R.drawable.button_e_dark);
                this.posButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_e_font_color_dark));
            }
        }
        Button button2 = this.negButton;
        if (button2 != null) {
            button2.getLayoutParams().height = DpiUtil.dip2px(getContext(), 31.0f);
            this.negButton.setTextSize(14.0f);
            this.negButton.setTypeface(Typeface.DEFAULT);
            this.negButton.setBackgroundResource(R.drawable.button_d_a_dark);
            this.negButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_d_a_font_color_dark));
        }
        TextView textView4 = this.tipTextView;
        if (textView4 != null) {
            textView4.setTextSize(11.0f);
            this.tipTextView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_2_dark));
        }
        ImageView imageView = this.tipImage;
        if (imageView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.width = DpiUtil.dip2px(getContext(), 11.0f);
            layoutParams.height = DpiUtil.dip2px(getContext(), 11.0f);
            this.tipImage.setLayoutParams(layoutParams);
        }
        EditText editText = this.editText;
        if (editText != null) {
            if (editText instanceof PasswordInputView) {
                Resources resources = getContext().getResources();
                int i2 = R.color.un_bg_level_3_dark;
                ((PasswordInputView) editText).setBorderColor(resources.getColor(i2));
                ((PasswordInputView) this.editText).setContentColor(getContext().getResources().getColor(i2));
                ((PasswordInputView) this.editText).setPasswordColor(getContext().getResources().getColor(R.color.un_content_level_1_dark));
                ((PasswordInputView) this.editText).setDividerColor(getContext().getResources().getColor(i2));
                return;
            }
            editText.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1_dark));
            this.editText.setBackgroundResource(R.drawable.dialog_edit_bg_normal_dark);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        CountDownTimer countDownTimer = this.downTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override // com.jingdong.common.unification.dialog.UnBaseDialog
    public void elderMode() {
        FrameLayout frameLayout;
        this.themeChange = true;
        Boolean bool = this.useBg;
        if (bool == null || bool.booleanValue()) {
            if (this.isSupportBottomClose && (frameLayout = this.rootContentLayout) != null) {
                frameLayout.setBackgroundResource(R.drawable.jd_dialog_common_bg);
            } else {
                Window window = getWindow();
                if (window != null) {
                    window.setBackgroundDrawableResource(R.drawable.jd_dialog_common_bg);
                }
            }
        }
        TextView textView = this.messageView;
        if (textView != null) {
            textView.setTextSize(18.0f);
            this.messageView.setLineSpacing(0.0f, 1.3f);
            this.messageView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1_elder));
        }
        TextView textView2 = this.titleView;
        if (textView2 != null) {
            textView2.setTextSize(18.0f);
            this.titleView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1_elder));
        }
        TextView textView3 = this.secondMessageView;
        if (textView3 != null) {
            textView3.setTextSize(18.0f);
            this.secondMessageView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1_elder));
        }
        Button button = this.posButton;
        if (button != null) {
            button.getLayoutParams().height = DpiUtil.dip2px(getContext(), 44.0f);
            this.posButton.setTextSize(18.0f);
            this.posButton.setTypeface(Typeface.DEFAULT_BOLD);
            if (this.isPosAccent) {
                this.posButton.setBackgroundResource(R.drawable.button_main_red_elder);
                this.posButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_main_red_elder_font_color));
            } else {
                this.posButton.setBackgroundResource(R.drawable.button_a_elder);
                this.posButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_a_elder_font_color));
            }
        }
        Button button2 = this.negButton;
        if (button2 != null) {
            button2.getLayoutParams().height = DpiUtil.dip2px(getContext(), 44.0f);
            this.negButton.setTextSize(18.0f);
            this.negButton.setTypeface(Typeface.DEFAULT_BOLD);
            this.negButton.setBackgroundResource(R.drawable.button_main_red_elder);
            this.negButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_main_red_elder_font_color));
        }
        TextView textView4 = this.tipTextView;
        if (textView4 != null) {
            textView4.setTextSize(14.0f);
            this.tipTextView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_2_elder));
        }
        ImageView imageView = this.tipImage;
        if (imageView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.width = DpiUtil.dip2px(getContext(), 14.0f);
            layoutParams.height = DpiUtil.dip2px(getContext(), 14.0f);
            this.tipImage.setLayoutParams(layoutParams);
        }
        EditText editText = this.editText;
        if (editText != null) {
            if (editText instanceof PasswordInputView) {
                Resources resources = getContext().getResources();
                int i2 = R.color.un_bg_level_3_elder;
                ((PasswordInputView) editText).setBorderColor(resources.getColor(i2));
                ((PasswordInputView) this.editText).setContentColor(getContext().getResources().getColor(i2));
                ((PasswordInputView) this.editText).setPasswordColor(getContext().getResources().getColor(R.color.un_content_level_1_elder));
                ((PasswordInputView) this.editText).setDividerColor(getContext().getResources().getColor(i2));
                return;
            }
            editText.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1_elder));
            this.editText.setBackgroundResource(R.drawable.dialog_edit_bg_normal);
        }
    }

    public int getPaddingBottom() {
        return this.rootView.getPaddingBottom();
    }

    public int getPaddingLeft() {
        return this.rootView.getPaddingLeft();
    }

    public int getPaddingRight() {
        return this.rootView.getPaddingRight();
    }

    public int getPaddingTop() {
        return this.rootView.getPaddingTop();
    }

    public void hideProgressBar() {
        LinearLayout linearLayout = this.progressBarLayout;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        EditText editText = this.editText;
        if (editText != null) {
            editText.setEnabled(true);
        }
    }

    @Override // com.jingdong.common.unification.dialog.UnBaseDialog
    public void normalMode() {
        FrameLayout frameLayout;
        this.themeChange = false;
        Boolean bool = this.useBg;
        if (bool == null || bool.booleanValue()) {
            if (this.isSupportBottomClose && (frameLayout = this.rootContentLayout) != null) {
                frameLayout.setBackgroundResource(R.drawable.jd_dialog_common_bg);
            } else {
                Window window = getWindow();
                if (window != null) {
                    window.setBackgroundDrawableResource(R.drawable.jd_dialog_common_bg);
                }
            }
        }
        TextView textView = this.messageView;
        if (textView != null) {
            textView.setTextSize(14.0f);
            this.messageView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1));
        }
        TextView textView2 = this.titleView;
        if (textView2 != null) {
            textView2.setTextSize(16.0f);
            this.titleView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1));
        }
        TextView textView3 = this.secondMessageView;
        if (textView3 != null) {
            textView3.setTextSize(14.0f);
            this.secondMessageView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1));
        }
        Button button = this.posButton;
        if (button != null) {
            button.getLayoutParams().height = DpiUtil.dip2px(getContext(), 31.0f);
            this.posButton.setTextSize(14.0f);
            this.posButton.setTypeface(Typeface.DEFAULT);
            if (this.isPosAccent) {
                this.posButton.setBackgroundResource(R.drawable.button_b);
                this.posButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_b_font_color));
            } else {
                this.posButton.setBackgroundResource(R.drawable.button_a);
                this.posButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_a_font_color));
            }
        }
        Button button2 = this.negButton;
        if (button2 != null) {
            button2.getLayoutParams().height = DpiUtil.dip2px(getContext(), 31.0f);
            this.negButton.setTextSize(14.0f);
            this.negButton.setTypeface(Typeface.DEFAULT);
            this.negButton.setBackgroundResource(R.drawable.button_b);
            this.negButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_b_font_color));
        }
        TextView textView4 = this.tipTextView;
        if (textView4 != null) {
            textView4.setTextSize(11.0f);
            this.tipTextView.setTextColor(getContext().getResources().getColor(R.color.un_content_level_2));
        }
        ImageView imageView = this.tipImage;
        if (imageView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.width = DpiUtil.dip2px(getContext(), 11.0f);
            layoutParams.height = DpiUtil.dip2px(getContext(), 11.0f);
            this.tipImage.setLayoutParams(layoutParams);
        }
        EditText editText = this.editText;
        if (editText != null) {
            if (editText instanceof PasswordInputView) {
                Resources resources = getContext().getResources();
                int i2 = R.color.un_bg_level_3;
                ((PasswordInputView) editText).setBorderColor(resources.getColor(i2));
                ((PasswordInputView) this.editText).setContentColor(getContext().getResources().getColor(i2));
                ((PasswordInputView) this.editText).setPasswordColor(getContext().getResources().getColor(R.color.un_content_level_1));
                ((PasswordInputView) this.editText).setDividerColor(getContext().getResources().getColor(i2));
                return;
            }
            editText.setTextColor(getContext().getResources().getColor(R.color.un_content_level_1));
            this.editText.setBackgroundResource(R.drawable.dialog_edit_bg_normal);
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void resetContentViewHeight() {
        LinearLayout linearLayout = this.contentLayout;
        if (linearLayout == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        this.contentLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredHeight = this.contentLayout.getMeasuredHeight();
        if (this.maxHeight <= 0) {
            this.maxHeight = (DpiUtil.getHeight() * 2) / 3;
        }
        int dip2px = DpiUtil.dip2px(getContext(), 100.0f);
        int i2 = measuredHeight + dip2px;
        int i3 = this.maxHeight;
        if (i2 > i3) {
            layoutParams.height = i3 - dip2px;
        }
    }

    public JDDialog setBottomCloseListener(final View.OnClickListener onClickListener) {
        ImageView imageView = this.bottomClose;
        if (imageView == null) {
            return this;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str;
                JDDialog jDDialog = JDDialog.this;
                if (jDDialog.dialogType == 16 && jDDialog.param != null) {
                    try {
                        str = jDDialog.createEventParam().toString();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        str = "";
                    }
                    UnWidgetThemeController.getInstance().onClickWithPageId(JDDialog.this.getContext(), "QueueLimit_PopupClose", JDDialog.this.getContext().getClass().getName(), str, JDDialog.this.param.pageId);
                }
                View.OnClickListener onClickListener2 = onClickListener;
                if (onClickListener2 != null) {
                    onClickListener2.onClick(view);
                }
                JDDialog.this.cancel();
            }
        });
        return this;
    }

    @Override // android.app.Dialog
    public void setContentView(int i2) {
        if (!this.isSupportBottomClose) {
            super.setContentView(i2);
        } else {
            Window window = getWindow();
            if (window != null) {
                window.setBackgroundDrawableResource(R.color.transparent);
            }
            super.setContentView(R.layout.un_dialog_close_layout);
            this.rootContentLayout = (FrameLayout) findViewById(R.id.root_content);
            int i3 = R.id.bottomCloseDialog;
            this.bottomClose = (ImageView) findViewById(i3);
            this.rootContentLayout.addView(LayoutInflater.from(getContext()).inflate(i2, (ViewGroup) this.rootContentLayout, false));
            ImageView imageView = (ImageView) findViewById(i3);
            this.bottomClose = imageView;
            useCancelClickEvent(imageView);
            setBottomCloseListener(null);
        }
        try {
            this.rootView = findViewById(R.id.rootView);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setCountDownTime(int i2) {
        this.countDownTime = i2 * 1000;
        initTimeProgress();
    }

    public void setCountdown(int i2) {
        String valueOf;
        if (this.timer == null) {
            return;
        }
        if (i2 < 10 && i2 > 0) {
            valueOf = "0" + i2;
        } else {
            valueOf = String.valueOf(i2);
        }
        this.timer.setText(valueOf);
    }

    public void setEditText(String str) {
        EditText editText = this.editText;
        if (editText == null || str == null) {
            return;
        }
        editText.setText(str);
    }

    public void setEditTextHint(String str) {
        EditText editText = this.editText;
        if (editText == null || str == null) {
            return;
        }
        editText.setHint(str);
    }

    public void setEditTextWrong() {
        EditText editText = this.editText;
        if (editText != null) {
            editText.setBackgroundResource(R.drawable.jd_dialog_edit_bg_redbound);
            this.editText.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.edittext_shake));
        }
    }

    public void setMessage(CharSequence charSequence) {
        setMessage(charSequence, false);
    }

    public void setMessageColor(int i2) {
        TextView textView = this.messageView;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public void setMessagePosition(int i2) {
        TextView textView = this.messageView;
        if (textView != null) {
            textView.setGravity(i2);
        }
    }

    public void setOnLeftButtonClickListener(final View.OnClickListener onClickListener) {
        Button button = this.posButton;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDDialog.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    String str;
                    View.OnClickListener onClickListener2 = onClickListener;
                    if (onClickListener2 != null) {
                        onClickListener2.onClick(view);
                    }
                    JDDialog jDDialog = JDDialog.this;
                    int i2 = jDDialog.dialogType;
                    if (i2 == 15) {
                        UnWidgetThemeController.getInstance().onClickWithPageId(JDDialog.this.getContext(), "QRCodeWinNoWait", JDDialog.this.getContext().getClass().getName(), "", JDDialog.this.getContext().getClass().getSimpleName());
                    } else if (i2 == 16) {
                        if (jDDialog.param != null) {
                            try {
                                JSONObject createEventParam = jDDialog.createEventParam();
                                if (!TextUtils.isEmpty(JDDialog.this.param.getLeftJumpUrl())) {
                                    createEventParam.put("url", JDDialog.this.param.getLeftJumpUrl());
                                }
                                str = createEventParam.toString();
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                                str = "";
                            }
                            String str2 = str;
                            if (TextUtils.isEmpty(JDDialog.this.param.getLeftTitle()) && TextUtils.isEmpty(JDDialog.this.param.getRightTitle())) {
                                UnWidgetThemeController.getInstance().onClickWithPageId(JDDialog.this.getContext(), "QueueLimit_PopupNoWait", JDDialog.this.getContext().getClass().getName(), str2, JDDialog.this.param.pageId);
                            } else {
                                UnWidgetThemeController.getInstance().onClickWithPageId(JDDialog.this.getContext(), "QueueLimit_PopupGoCoupon", JDDialog.this.getContext().getClass().getName(), str2, JDDialog.this.param.pageId);
                                UnWidgetThemeController.getInstance().jdRouter(JDDialog.this.param.getLeftJumpUrl());
                            }
                        }
                        CountDownTimer countDownTimer = JDDialog.this.downTimer;
                        if (countDownTimer != null) {
                            countDownTimer.cancel();
                        }
                        JDDialog.this.dismiss();
                    }
                }
            });
        }
    }

    public void setOnRightButtonClickListener(final View.OnClickListener onClickListener) {
        Button button = this.negButton;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDDialog.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    String str;
                    View.OnClickListener onClickListener2 = onClickListener;
                    if (onClickListener2 != null) {
                        onClickListener2.onClick(view);
                    }
                    JDDialog jDDialog = JDDialog.this;
                    int i2 = jDDialog.dialogType;
                    if (i2 == 15) {
                        UnWidgetThemeController.getInstance().onClickWithPageId(JDDialog.this.getContext(), "QRCodeWinFeedback", JDDialog.this.getContext().getClass().getName(), "", JDDialog.this.getContext().getClass().getSimpleName());
                        UnWidgetThemeController.getInstance().jdRouter("router://JDMyJdModule/showNewFeedback?source=1&from=shenduntanchuang&requestId=" + JDDialog.this.requestId);
                        CountDownTimer countDownTimer = JDDialog.this.downTimer;
                        if (countDownTimer != null) {
                            countDownTimer.cancel();
                        }
                        JDDialog.this.dismiss();
                    } else if (i2 == 16) {
                        if (jDDialog.param != null) {
                            try {
                                JSONObject createEventParam = jDDialog.createEventParam();
                                createEventParam.put("url", JDDialog.this.param.getRightJumpUrl());
                                str = createEventParam.toString();
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                                str = "";
                            }
                            UnWidgetThemeController.getInstance().onClickWithPageId(JDDialog.this.getContext(), "QueueLimit_PopupGoUrl", JDDialog.this.getContext().getClass().getName(), str, JDDialog.this.param.pageId);
                            UnWidgetThemeController.getInstance().jdRouter(JDDialog.this.param.getRightJumpUrl());
                        }
                        CountDownTimer countDownTimer2 = JDDialog.this.downTimer;
                        if (countDownTimer2 != null) {
                            countDownTimer2.cancel();
                        }
                        JDDialog.this.dismiss();
                    }
                }
            });
        }
        ImageButton imageButton = this.negImgButton;
        if (imageButton != null) {
            imageButton.setOnClickListener(onClickListener);
        }
    }

    public JDDialog setPadding(int i2, int i3, int i4, int i5) {
        this.rootView.setPadding(i2, i3, i4, i5);
        return this;
    }

    public void setPosAccent(boolean z) {
        this.isPosAccent = z;
    }

    public void setSecondMessage(String str) {
        if (this.secondMessageView != null) {
            if (!TextUtils.isEmpty(str)) {
                this.secondMessageView.setText(str);
            } else {
                this.secondMessageView.setVisibility(8);
            }
        }
    }

    public void setSecondTitle(String str) {
        if (this.secondTitleView != null) {
            if (!TextUtils.isEmpty(str)) {
                this.secondTitleView.setText(str);
            } else {
                this.secondTitleView.setVisibility(8);
            }
        }
    }

    public void setTipMessage(String str) {
        if (this.tipTextView != null) {
            if (!TextUtils.isEmpty(str)) {
                this.tipTextView.setVisibility(0);
                this.tipTextView.setText(str);
                return;
            }
            this.tipTextView.setVisibility(8);
        }
    }

    public void setTipMessageClickListener(View.OnClickListener onClickListener) {
        LinearLayout linearLayout = this.tipLayout;
        if (linearLayout == null || onClickListener == null) {
            return;
        }
        linearLayout.setOnClickListener(onClickListener);
    }

    public void setTitle(String str) {
        if (this.titleView != null) {
            if (!TextUtils.isEmpty(str)) {
                this.titleView.setVisibility(0);
                this.titleView.setText(str);
                return;
            }
            this.titleView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTotalHeightofListView(ListView listView) {
        int dividerHeight;
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int dimension = (int) getContext().getResources().getDimension(R.dimen.base_ui_jd_dialog_content_maxheight);
        if (adapter.getCount() >= 10) {
            dividerHeight = dimension + 10;
        } else {
            int i2 = 0;
            for (int i3 = 0; i3 < adapter.getCount(); i3++) {
                View view = adapter.getView(i3, null, listView);
                view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                i2 += view.getMeasuredHeight();
            }
            dividerHeight = i2 + (listView.getDividerHeight() * (adapter.getCount() - 1));
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        if (dividerHeight > dimension) {
            layoutParams.height = dimension;
            listView.setLayoutParams(layoutParams);
            listView.requestLayout();
        }
    }

    @Override // android.app.Dialog
    public void show() {
        String str;
        Context context = getContext();
        if (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        Context context2 = context;
        if ((context2 instanceof Activity) && ((Activity) context2).isFinishing()) {
            return;
        }
        if (this.statusBarFollowActivity) {
            try {
                getWindow().clearFlags(2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            if (this.isTop) {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                window.setGravity(48);
                window.setAttributes(attributes);
                setWindowWith();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        showTimer();
        toggleAutoShowSoftKeyBoard(this.editText != null);
        if (isElder()) {
            elderMode();
        } else if (isDarkMode()) {
            darkMode();
        } else if (this.themeChange) {
            normalMode();
        }
        try {
            super.show();
            resetHeight();
        } catch (Exception unused) {
        }
        new WatermarkHelper().showByCanAdd(this);
        int i2 = this.dialogType;
        if (i2 == 15) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("pageId", context2.getClass().getName());
            } catch (JSONException e4) {
                e4.printStackTrace();
            }
            UnWidgetThemeController.getInstance().sendExposureDataWithExt(context2, "QRCodeWinExpo", "", context2.getClass().getSimpleName(), context2.getClass().getName(), "", jSONObject.toString());
        } else if (i2 != 16 || this.param == null) {
        } else {
            try {
                JSONObject createEventParam = createEventParam();
                createEventParam.put("overtime", "1");
                str = createEventParam.toString();
            } catch (JSONException e5) {
                e5.printStackTrace();
                str = "";
            }
            UnWidgetThemeController.getInstance().sendExposureDataWithExt(context2, "QueueLimit_PopupExpo", "", this.param.pageId, "", "", str);
        }
    }

    public void showProgressBar() {
        LinearLayout linearLayout = this.progressBarLayout;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        EditText editText = this.editText;
        if (editText != null) {
            editText.setEnabled(false);
        }
    }

    public void startTimeCount(int i2, final TextView textView, final TextView textView2, final DialogTimeEndListener dialogTimeEndListener) {
        CountDownTimer countDownTimer = new CountDownTimer(i2 * 1000, 1000L) { // from class: com.jingdong.common.ui.JDDialog.7
            @Override // android.os.CountDownTimer
            public void onFinish() {
                DialogTimeEndListener dialogTimeEndListener2 = dialogTimeEndListener;
                if (dialogTimeEndListener2 != null) {
                    dialogTimeEndListener2.onFinish(textView, textView2);
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
                textView.setEnabled(false);
                textView.setText(String.valueOf(j2 / 1000));
            }
        };
        this.downTimer = countDownTimer;
        countDownTimer.start();
    }

    public void startTimeCountDown(int i2, final DialogTimeEndListener dialogTimeEndListener) {
        CountDownTimer countDownTimer = new CountDownTimer(i2 * 1000, 1000L) { // from class: com.jingdong.common.ui.JDDialog.8
            @Override // android.os.CountDownTimer
            public void onFinish() {
                DialogTimeEndListener dialogTimeEndListener2 = dialogTimeEndListener;
                if (dialogTimeEndListener2 != null) {
                    JDDialog jDDialog = JDDialog.this;
                    dialogTimeEndListener2.onFinish(jDDialog.timer, jDDialog.messageView);
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
                JDDialog.this.setCountdown((int) (j2 / 1000));
            }
        };
        this.downTimer = countDownTimer;
        countDownTimer.start();
    }

    public void startTimeCountNew() {
        initTimeProgress();
        CountDownTimer countDownTimer = new CountDownTimer(this.countDownTime, 100L) { // from class: com.jingdong.common.ui.JDDialog.9
            @Override // android.os.CountDownTimer
            public void onFinish() {
                String simpleName;
                JDDialog.this.sec.setVisibility(8);
                JDDialog.this.timer.setVisibility(8);
                JDDialog.this.timerStartText.setText("\u52aa\u529b\u52a0\u8f7d\u4e2d\uff0c\u8fd8\u8bf7\u8010\u5fc3\u7b49\u5f85");
                JDDialog jDDialog = JDDialog.this;
                JdDialogParam jdDialogParam = jDDialog.param;
                if (jdDialogParam != null) {
                    simpleName = jdDialogParam.pageId;
                } else {
                    simpleName = jDDialog.getContext().getClass().getSimpleName();
                }
                String str = simpleName;
                String str2 = "";
                try {
                    JSONObject createEventParam = JDDialog.this.createEventParam();
                    if (createEventParam != null) {
                        createEventParam.put("overtime", "1");
                        str2 = createEventParam.toString();
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                UnWidgetThemeController.getInstance().sendExposureDataWithExt(JDDialog.this.getContext(), "QueueLimit_Popup_overtime_expo", "", str, "", "", str2);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
                JDDialog jDDialog = JDDialog.this;
                ProgressBar progressBar = jDDialog.timeProgress;
                if (progressBar != null && j2 > 1000) {
                    progressBar.setProgress(jDDialog.countDownTime - ((int) j2));
                }
                JDDialog.this.setCountdown((int) (j2 / 1000));
            }
        };
        this.downTimer = countDownTimer;
        countDownTimer.start();
    }

    public void updateTick(long j2) {
        ProgressBar progressBar = this.timeProgress;
        if (progressBar != null) {
            progressBar.setProgress(this.countDownTime - ((int) j2));
        }
        setCountdown((int) (j2 / 1000));
    }

    public JDDialog useBg(boolean z) {
        FrameLayout frameLayout;
        Boolean bool = this.useBg;
        if (!(bool == null && z) && (bool == null || z != bool.booleanValue())) {
            if (!this.isSupportBottomClose || (frameLayout = this.rootContentLayout) == null) {
                Window window = getWindow();
                if (z) {
                    if (isDarkMode()) {
                        window.setBackgroundDrawableResource(R.drawable.jd_dialog_common_bg_dark);
                    } else {
                        window.setBackgroundDrawableResource(R.drawable.jd_dialog_common_bg);
                    }
                } else {
                    window.setBackgroundDrawableResource(R.color.transparent);
                }
            } else if (z) {
                if (isDarkMode()) {
                    this.rootContentLayout.setBackgroundResource(R.drawable.jd_dialog_common_bg_dark);
                } else {
                    this.rootContentLayout.setBackgroundResource(R.drawable.jd_dialog_common_bg);
                }
            } else {
                frameLayout.setBackgroundResource(R.color.transparent);
            }
            this.useBg = Boolean.valueOf(z);
            return this;
        }
        return this;
    }

    public void useCancelClickEvent(View view) {
        if (view != null) {
            view.setOnClickListener(this.cancelClickListener);
        }
    }

    public void setMessage(CharSequence charSequence, boolean z) {
        setMessage(charSequence, z, false);
    }

    public void setMessage(CharSequence charSequence, boolean z, final boolean z2) {
        if (this.messageView != null) {
            if (!TextUtils.isEmpty(charSequence)) {
                this.messageView.setVisibility(0);
                this.messageView.setText(charSequence);
                if (z) {
                    this.messageView.post(new Runnable() { // from class: com.jingdong.common.ui.JDDialog.3
                        @Override // java.lang.Runnable
                        public void run() {
                            if (JDDialog.this.messageView.getLineCount() > 1) {
                                JDDialog.this.messageView.setGravity(3);
                                if (z2) {
                                    JDDialog.this.messageView.getPaint().setFakeBoldText(false);
                                    return;
                                }
                                return;
                            }
                            JDDialog.this.messageView.setGravity(17);
                            if (z2) {
                                JDDialog.this.messageView.getPaint().setFakeBoldText(true);
                            }
                        }
                    });
                    return;
                }
                return;
            }
            this.messageView.setVisibility(8);
        }
    }

    public void startTimeCount(int i2, final TextView textView) {
        textView.getText().toString();
        CountDownTimer countDownTimer = new CountDownTimer
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0012: CONSTRUCTOR (r0v1 'countDownTimer' android.os.CountDownTimer) = 
              (r9v0 'this' com.jingdong.common.ui.JDDialog A[IMMUTABLE_TYPE, THIS])
              (wrap: int : 0x000a: ARITH (r10v0 'i2' int) * (1000 int) A[DONT_GENERATE, REMOVE, WRAPPED])
              (1000 long)
              (r11v0 'textView' android.widget.TextView A[DONT_INLINE])
              (r8 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[DECLARE_VAR, MD:(com.jingdong.common.ui.JDDialog, long, long, android.widget.TextView, java.lang.String):void (m)] (LINE:4) call: com.jingdong.common.ui.JDDialog.10.<init>(com.jingdong.common.ui.JDDialog, long, long, android.widget.TextView, java.lang.String):void type: CONSTRUCTOR in method: com.jingdong.common.ui.JDDialog.startTimeCount(int, android.widget.TextView):void, file: classes6.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            java.lang.CharSequence r0 = r11.getText()
            java.lang.String r8 = r0.toString()
            com.jingdong.common.ui.JDDialog$10 r0 = new com.jingdong.common.ui.JDDialog$10
            int r10 = r10 * 1000
            long r3 = (long) r10
            r5 = 1000(0x3e8, double:4.94E-321)
            r1 = r0
            r2 = r9
            r7 = r11
            r1.<init>(r3, r5)
            r9.downTimer = r0
            r10 = 1
            r9.isStartTimeCount = r10
            r10 = 0
            r9.isFinishTimeCount = r10
            r0.start()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.ui.JDDialog.startTimeCount(int, android.widget.TextView):void");
    }

    public void setOnRightButtonClickListener(View.OnClickListener onClickListener, boolean z) {
        Button button = this.negButton;
        if (button != null) {
            button.setOnClickListener(onClickListener);
            this.negButton.setEnabled(z);
        }
    }

    public JDDialog(Context context, int i2) {
        super(context, i2);
        this.maxHeightEnable = false;
        this.maxHeight = 0;
        this.requestId = "";
        this.cancelClickListener = new View.OnClickListener() { // from class: com.jingdong.common.ui.JDDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDDialog.this.cancel();
            }
        };
        setCanceledOnTouchOutside(false);
        setAutoElderMode(UnWidgetThemeController.getInstance().isDialogAutoElder());
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        if (!this.isSupportBottomClose) {
            super.setContentView(view);
        } else {
            Window window = getWindow();
            if (window != null) {
                window.setBackgroundDrawableResource(R.color.transparent);
            }
            super.setContentView(R.layout.un_dialog_close_layout);
            this.rootContentLayout = (FrameLayout) findViewById(R.id.root_content);
            int i2 = R.id.bottomCloseDialog;
            this.bottomClose = (ImageView) findViewById(i2);
            this.rootContentLayout.addView(view);
            this.bottomClose = (ImageView) findViewById(i2);
            setBottomCloseListener(null);
        }
        try {
            this.rootView = findViewById(R.id.rootView);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
