package com.jingdong.common.unification.jdbottomdialogview;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.common.unification.jdbottomdialogview.ExpandTouchListener;

/* loaded from: classes6.dex */
public class JDBottomDialogView implements OnViewThemeConfig<JDBottomDialogView> {
    private JDBottomDialogViewBuilder builder;
    private ViewGroup contentContainer;
    private int contentHeight;
    private RelativeLayout contentLayoutNoRadius;
    private View contentView;
    private Context context;
    private ViewGroup decorView;
    private int defaultTransY;
    private int expandTransY;
    private boolean isAutoDark;
    private boolean isCancelable;
    private boolean isDarkMode;
    private boolean isDismissing;
    private TextView leftBt;
    private LinearLayout llBottomContainer;
    private OnCancelListener onCancelListener;
    private final View.OnTouchListener onCancelableTouchListener;
    private OnDismissListener onDismissListener;
    private TextView rightBt;
    private ViewGroup rootView;
    private Animation rootViewOutAlphaAnim;
    private Animation rootViewShowAlphaAnim;
    private Space space;
    private ExpandTouchListener touchListener;

    JDBottomDialogView(JDBottomDialogViewBuilder jDBottomDialogViewBuilder, boolean z) {
        this(jDBottomDialogViewBuilder);
        this.isAutoDark = z;
        if (isDarkMode()) {
            darkMode();
        }
    }

    private void initBottomBt() {
        String leftBtText = this.builder.getLeftBtText();
        String rightBtText = this.builder.getRightBtText();
        if (TextUtils.isEmpty(leftBtText) && TextUtils.isEmpty(rightBtText)) {
            this.llBottomContainer.setVisibility(8);
        } else {
            this.llBottomContainer.setVisibility(0);
        }
        if (!TextUtils.isEmpty(leftBtText)) {
            this.leftBt.setVisibility(0);
            this.leftBt.setText(leftBtText);
            View.OnClickListener onLeftBtClickListener = this.builder.getOnLeftBtClickListener();
            if (onLeftBtClickListener != null) {
                this.leftBt.setOnClickListener(onLeftBtClickListener);
            }
        } else {
            this.leftBt.setVisibility(8);
            this.space.setVisibility(8);
        }
        if (!TextUtils.isEmpty(rightBtText)) {
            this.rightBt.setVisibility(0);
            this.rightBt.setText(rightBtText);
            View.OnClickListener onRightBtClickListener = this.builder.getOnRightBtClickListener();
            if (onRightBtClickListener != null) {
                this.rightBt.setOnClickListener(onRightBtClickListener);
                return;
            }
            return;
        }
        this.rightBt.setVisibility(8);
        this.space.setVisibility(8);
    }

    private void initCancelable() {
        if (this.isCancelable) {
            this.rootView.findViewById(R.id.dialogplus_outmost_container).setOnTouchListener(this.onCancelableTouchListener);
        }
    }

    private void initContentView() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, 0, 0, 0);
        this.contentView.setLayoutParams(layoutParams);
        this.contentView.setPadding(0, 0, 0, 0);
    }

    private void initExpand(Activity activity, View view, int i2, int i3) {
        AbsListView contentListView = view instanceof IJDBottomDialogView ? ((IJDBottomDialogView) view).getContentListView() : null;
        ExpandTouchListener newListener = ExpandTouchListener.newListener(activity, contentListView, this.contentContainer, i2, i3, this.contentHeight, new ExpandTouchListener.IDismiss() { // from class: com.jingdong.common.unification.jdbottomdialogview.JDBottomDialogView.2
            @Override // com.jingdong.common.unification.jdbottomdialogview.ExpandTouchListener.IDismiss
            public void accessDismiss() {
                JDBottomDialogView.this.dismiss();
            }
        });
        this.touchListener = newListener;
        if (contentListView != null) {
            contentListView.setOnTouchListener(newListener);
        }
    }

    public static JDBottomDialogViewBuilder newDialog(Context context) {
        return new JDBottomDialogViewBuilder(context);
    }

    private void onAttached(View view) {
        ViewGroup viewGroup = this.decorView;
        if (viewGroup == null) {
            return;
        }
        viewGroup.addView(view);
        this.rootViewShowAlphaAnim.setDuration(JDBottomDialogViewUtil.getDuration(this.contentHeight, this.defaultTransY, this.rootView));
        this.rootView.startAnimation(this.rootViewShowAlphaAnim);
        this.contentContainer.addView(this.contentView);
        JDBottomDialogViewUtil.startTransAnim(this.contentContainer, this.contentHeight, this.defaultTransY, null);
        ExpandTouchListener expandTouchListener = this.touchListener;
        if (expandTouchListener != null) {
            expandTouchListener.reset();
        }
        this.contentContainer.requestFocus();
    }

    public void dismiss() {
        if (this.isDismissing) {
            return;
        }
        this.rootViewOutAlphaAnim.setDuration(JDBottomDialogViewUtil.getDuration(this.contentContainer.getTranslationY(), this.contentHeight, this.rootView));
        this.rootView.startAnimation(this.rootViewOutAlphaAnim);
        ViewGroup viewGroup = this.contentContainer;
        JDBottomDialogViewUtil.startTransAnim(viewGroup, viewGroup.getTranslationY(), this.contentHeight, new Animator.AnimatorListener() { // from class: com.jingdong.common.unification.jdbottomdialogview.JDBottomDialogView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                JDBottomDialogView.this.decorView.removeView(JDBottomDialogView.this.rootView);
                JDBottomDialogView.this.contentContainer.removeView(JDBottomDialogView.this.contentView);
                JDBottomDialogView.this.isDismissing = false;
                if (JDBottomDialogView.this.onDismissListener != null) {
                    JDBottomDialogView.this.onDismissListener.onDismiss(JDBottomDialogView.this);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        this.isDismissing = true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDBottomDialogView elderMode() {
        return null;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoDarkMode() {
        return this.isAutoDark;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoElderMode() {
        return false;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isDarkMode() {
        if (this.isAutoDark) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isElderMode() {
        return false;
    }

    public boolean isShowing() {
        return this.decorView.findViewById(R.id.dialogplus_outmost_container) != null;
    }

    public void onBackPressed(JDBottomDialogView jDBottomDialogView) {
        OnCancelListener onCancelListener = this.onCancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel(this);
        }
        dismiss();
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public void refresh() {
        if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDBottomDialogView setAutoElderMode(boolean z) {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDBottomDialogView setElderMode(boolean z) {
        return null;
    }

    public void show() {
        if (isShowing()) {
            return;
        }
        onAttached(this.rootView);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDBottomDialogView darkMode() {
        this.llBottomContainer.setBackgroundResource(R.color.un_bg_level_2_dark);
        this.leftBt.setBackgroundResource(R.drawable.button_e_dark);
        this.leftBt.setTextColor(this.context.getResources().getColorStateList(R.color.button_e_font_color_dark));
        this.rightBt.setBackgroundResource(R.drawable.button_a_dark);
        this.rightBt.setTextColor(this.context.getResources().getColorStateList(R.color.button_a_font_color_dark));
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDBottomDialogView normalMode() {
        this.llBottomContainer.setBackgroundResource(R.color.un_bg_level_2);
        this.leftBt.setBackgroundResource(R.drawable.button_e);
        this.leftBt.setTextColor(this.context.getResources().getColorStateList(R.color.button_e_font_color));
        this.rightBt.setBackgroundResource(R.drawable.button_b);
        this.rightBt.setTextColor(this.context.getResources().getColorStateList(R.color.button_b_font_color));
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDBottomDialogView setAutoDarkMode(boolean z) {
        this.isAutoDark = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDBottomDialogView setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JDBottomDialogView(JDBottomDialogViewBuilder jDBottomDialogViewBuilder) {
        this.onCancelableTouchListener = new View.OnTouchListener() { // from class: com.jingdong.common.unification.jdbottomdialogview.JDBottomDialogView.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    if (JDBottomDialogView.this.onCancelListener != null) {
                        JDBottomDialogView.this.onCancelListener.onCancel(JDBottomDialogView.this);
                    }
                    JDBottomDialogView.this.dismiss();
                    return false;
                }
                return false;
            }
        };
        this.builder = jDBottomDialogViewBuilder;
        Context context = jDBottomDialogViewBuilder.getContext();
        this.context = context;
        LayoutInflater from = LayoutInflater.from(context);
        Context context2 = this.context;
        if (context2 instanceof Activity) {
            Activity activity = (Activity) context2;
            this.onDismissListener = jDBottomDialogViewBuilder.getOnDismissListener();
            this.onCancelListener = jDBottomDialogViewBuilder.getOnCancelListener();
            this.isCancelable = jDBottomDialogViewBuilder.isCancelable();
            ViewGroup decorView = jDBottomDialogViewBuilder.getDecorView();
            this.decorView = decorView;
            if (decorView == null && (activity.getWindow().getDecorView().findViewById(16908290) instanceof ViewGroup)) {
                this.decorView = (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290);
            }
            ViewGroup viewGroup = this.decorView;
            if (viewGroup == null) {
                return;
            }
            ViewGroup viewGroup2 = (ViewGroup) from.inflate(R.layout.jd_bottom_dialog_view_container, viewGroup, false);
            this.rootView = viewGroup2;
            viewGroup2.setLayoutParams(jDBottomDialogViewBuilder.getOutmostLayoutParams());
            this.rootViewShowAlphaAnim = new AlphaAnimation(0.0f, 1.0f);
            this.rootViewOutAlphaAnim = new AlphaAnimation(1.0f, 0.0f);
            this.contentLayoutNoRadius = (RelativeLayout) this.rootView.findViewById(R.id.content_layout_no_radius);
            this.contentContainer = (ViewGroup) this.rootView.findViewById(R.id.dialogplus_content_container);
            if (jDBottomDialogViewBuilder.isRound()) {
                this.contentLayoutNoRadius.setVisibility(8);
            } else {
                this.contentContainer.setVisibility(8);
                this.contentLayoutNoRadius.setVisibility(0);
                this.contentContainer = this.contentLayoutNoRadius;
            }
            this.contentContainer.setLayoutParams(jDBottomDialogViewBuilder.getContentParams());
            this.llBottomContainer = (LinearLayout) this.rootView.findViewById(R.id.dialogplus_button_container);
            this.leftBt = (TextView) this.rootView.findViewById(R.id.dialogplus_button_left);
            this.rightBt = (TextView) this.rootView.findViewById(R.id.dialogplus_button_right);
            this.space = (Space) this.rootView.findViewById(R.id.space);
            initBottomBt();
            this.contentView = jDBottomDialogViewBuilder.getContentView();
            initContentView();
            initCancelable();
            int contentHeight = jDBottomDialogViewBuilder.getContentHeight();
            this.contentHeight = contentHeight;
            this.contentContainer.setTranslationY(contentHeight);
            if (jDBottomDialogViewBuilder.isExpanded()) {
                this.expandTransY = 0;
                int defaultContentHeight = this.contentHeight - jDBottomDialogViewBuilder.getDefaultContentHeight();
                this.defaultTransY = defaultContentHeight;
                initExpand(activity, this.contentView, this.expandTransY, defaultContentHeight);
                return;
            }
            this.defaultTransY = 0;
        }
    }
}
