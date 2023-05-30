package com.jingdong.common.recommend;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.login.AccessibilityHelper;
import com.jingdong.common.recommend.entity.FeedBackReason;
import com.jingdong.common.recommend.entity.RecommendMaterialData;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.ui.JDDrawableCheckBox;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendFeedBackManger {
    private static final String TAG = "RecommendFeedBackManger";
    private static RecommendFeedBackManger recommendFeedBackManger;
    private PopupWindow.OnDismissListener onDismissListener;
    private String reasonList;
    private PopupWindow singlePopupWindow;

    /* loaded from: classes6.dex */
    public interface OnFeedBackItemClick {
        void feedBackReason(FeedBackReason feedBackReason);
    }

    public void backgroundAlpha(float f2, BaseActivity baseActivity) {
        WindowManager.LayoutParams attributes = baseActivity.getWindow().getAttributes();
        attributes.alpha = f2;
        baseActivity.getWindow().setAttributes(attributes);
    }

    private PopupWindow generateFeedBackWindow(final BaseActivity baseActivity, List<FeedBackReason> list, View view, final OnFeedBackItemClick onFeedBackItemClick) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        final FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(view.getContext()).inflate(R.layout.recommend_feedback_layout, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) frameLayout.findViewById(R.id.recommend_feedback_b_root);
        final PopupWindow popupWindow = new PopupWindow((View) frameLayout, DpiUtil.dip2px(baseActivity, 200.0f), -2, false);
        if ("1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "accessibility", "enable", "1")) && AccessibilityHelper.isAccessibilityEnabled(baseActivity)) {
            OKLog.d("RecommendAB", "\u65e0\u969c\u788d\u5f00\u542f");
            frameLayout.findViewById(R.id.recommend_talkback_close).setVisibility(0);
        }
        frameLayout.findViewById(R.id.recommend_talkback_close).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.RecommendFeedBackManger.3
            {
                RecommendFeedBackManger.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                popupWindow.dismiss();
            }
        });
        for (int i2 = 0; i2 < list.size(); i2++) {
            final FeedBackReason feedBackReason = list.get(i2);
            LinearLayout item = getItem(baseActivity, feedBackReason, feedBackReason.icon);
            item.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.RecommendFeedBackManger.4
                {
                    RecommendFeedBackManger.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    OnFeedBackItemClick onFeedBackItemClick2 = onFeedBackItemClick;
                    if (onFeedBackItemClick2 != null) {
                        onFeedBackItemClick2.feedBackReason(feedBackReason);
                    }
                    popupWindow.dismiss();
                }
            });
            linearLayout.addView(item);
            if (i2 != list.size() - 1) {
                linearLayout.addView(getLine(baseActivity));
            }
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.jingdong.common.recommend.RecommendFeedBackManger.5
            {
                RecommendFeedBackManger.this = this;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                baseActivity.getWindow().clearFlags(2);
                RecommendFeedBackManger.this.backgroundAlpha(1.0f, baseActivity);
                RecommendUtil.dotClick = false;
                if (RecommendFeedBackManger.this.onDismissListener != null) {
                    RecommendFeedBackManger.this.onDismissListener.onDismiss();
                }
                RecommendFeedBackManger.this.singlePopupWindow = null;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_alpha_style);
        int[] calculatePopWindowPosB = calculatePopWindowPosB(view, frameLayout);
        popupWindow.showAtLocation(view, 8388659, calculatePopWindowPosB[0], calculatePopWindowPosB[1]);
        baseActivity.getWindow().addFlags(2);
        backgroundAlpha(0.8f, baseActivity);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() { // from class: com.jingdong.common.recommend.RecommendFeedBackManger.6
            {
                RecommendFeedBackManger.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == 0 && (x < 0 || x >= frameLayout.getWidth() || y < 0 || y >= frameLayout.getHeight())) {
                    popupWindow.dismiss();
                    return true;
                } else if (motionEvent.getAction() == 4) {
                    popupWindow.dismiss();
                    return true;
                } else {
                    return false;
                }
            }
        });
        return popupWindow;
    }

    public static RecommendFeedBackManger getInstance() {
        RecommendFeedBackManger recommendFeedBackManger2 = recommendFeedBackManger;
        if (recommendFeedBackManger2 == null) {
            RecommendFeedBackManger recommendFeedBackManger3 = new RecommendFeedBackManger();
            recommendFeedBackManger = recommendFeedBackManger3;
            return recommendFeedBackManger3;
        }
        return recommendFeedBackManger2;
    }

    private LinearLayout getItem(Context context, FeedBackReason feedBackReason, String str) {
        if (context == null || feedBackReason == null || TextUtils.isEmpty(feedBackReason.name)) {
            return null;
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        ImageView imageView = new ImageView(context);
        int dip2px = DpiUtil.dip2px(context, 19.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px, dip2px);
        layoutParams.gravity = 16;
        layoutParams.setMargins(DpiUtil.dip2px(context, 17.5f), 0, 0, 0);
        imageView.setLayoutParams(layoutParams);
        if (!TextUtils.isEmpty(str)) {
            imageView.setBackgroundDrawable(UnIconConfigHelper.getDrawable(str));
        } else {
            imageView.setBackgroundResource(R.drawable.feedback_secret);
        }
        linearLayout.addView(imageView);
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 16;
        layoutParams2.setMargins(DpiUtil.dip2px(context, 15.0f), 0, 0, 0);
        textView.setLayoutParams(layoutParams2);
        textView.setText(feedBackReason.name);
        textView.setTextColor(context.getResources().getColor(R.color.c_2E2D2D));
        textView.setTextSize(13.0f);
        linearLayout.addView(textView);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, DpiUtil.dip2px(context, 49.5f)));
        return linearLayout;
    }

    private View getLine(Context context) {
        View view = new View(context);
        view.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_F5F5F5));
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, DpiUtil.dip2px(context, 0.5f)));
        return view;
    }

    public static boolean isfeedManagerValid() {
        return recommendFeedBackManger != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:156:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onclick(RecommendProduct recommendProduct, RecommendMaterialData recommendMaterialData, int i2, RecommendUtil.OnRecommendClickedListener onRecommendClickedListener, List<JDDrawableCheckBox> list, FeedBackReason feedBackReason, int i3) {
        String str;
        String str2;
        String str3;
        Object tag;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (list != null) {
            for (int i4 = 0; i4 < list.size(); i4++) {
                JDDrawableCheckBox jDDrawableCheckBox = list.get(i4);
                if (jDDrawableCheckBox.isChecked() && (tag = jDDrawableCheckBox.getTag()) != null) {
                    FeedBackReason feedBackReason2 = (FeedBackReason) tag;
                    arrayList.add(Integer.valueOf(feedBackReason2.getId()));
                    this.reasonList += DYConstants.DY_REGEX_COMMA + feedBackReason2.getId();
                    sb.append(feedBackReason2.getId());
                    sb.append("#");
                    sb2.append(i4 + 1);
                    sb2.append("#");
                }
            }
        } else if (feedBackReason != null) {
            arrayList.add(Integer.valueOf(feedBackReason.id));
            this.reasonList = feedBackReason.id + "";
            sb.append(feedBackReason.id);
            sb.append("#");
            str = feedBackReason.closeUrl;
            str2 = feedBackReason.closeLog;
            if (recommendProduct == null) {
                str3 = recommendProduct.feedbackSourceValue;
            } else {
                str3 = recommendMaterialData != null ? recommendMaterialData.sourceValueFeedback : "";
            }
            if (TextUtils.isEmpty(str3)) {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                } else {
                    sb.append(-100);
                }
                if (sb2.length() > 0) {
                    sb2.deleteCharAt(sb2.length() - 1);
                } else {
                    sb2.append(-100);
                }
                try {
                    JSONObject jSONObject = new JSONObject(str3);
                    jSONObject.put("trigger", String.valueOf(i3));
                    jSONObject.put("feedback", sb.toString());
                    jSONObject.put(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID, sb2.toString());
                    if (recommendProduct != null) {
                        recommendProduct.feedbackSourceValue = jSONObject.toString();
                        onRecommendClickedListener.onNoRecommendClick(recommendProduct, i2, this.reasonList, arrayList);
                    } else if (recommendMaterialData != null) {
                        recommendMaterialData.sourceValueFeedback = jSONObject.toString();
                        onRecommendClickedListener.onNoRecommendMaterialClick(recommendMaterialData, i2, this.reasonList, arrayList, str, str2);
                    }
                    this.reasonList = "";
                    if (onRecommendClickedListener != null) {
                        onRecommendClickedListener.onRecommendReasonMta(jSONObject.toString());
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                    return;
                }
            }
            return;
        }
        str = "";
        str2 = str;
        if (recommendProduct == null) {
        }
        if (TextUtils.isEmpty(str3)) {
        }
    }

    public int[] calculatePopWindowPos(View view, View view2) {
        View findViewById = view2.findViewById(R.id.up_arrow);
        View findViewById2 = view2.findViewById(R.id.down_arrow);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int height = view.getHeight();
        int height2 = DPIUtil.getHeight();
        int width = DPIUtil.getWidth();
        view2.measure(0, 0);
        int measuredHeight = view2.getMeasuredHeight();
        int measuredWidth = view2.getMeasuredWidth();
        int dip2px = DPIUtil.dip2px(32.0f);
        int left = (((width - view.getLeft()) - dip2px) - DPIUtil.dip2px(1.0f)) - view.getWidth();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) findViewById2.getLayoutParams();
        if (iArr2[0] > width / 2) {
            layoutParams.rightMargin = DPIUtil.dip2px(14.0f);
            layoutParams2.rightMargin = DPIUtil.dip2px(14.0f);
            dip2px = DPIUtil.dip2px(12.0f);
        } else {
            layoutParams.rightMargin = left;
            layoutParams2.rightMargin = left;
        }
        if ((height2 - iArr2[1]) - height < measuredHeight) {
            findViewById.setVisibility(4);
            findViewById2.setVisibility(0);
            iArr[0] = (width - measuredWidth) - dip2px;
            iArr[1] = iArr2[1] - measuredHeight;
        } else {
            findViewById.setVisibility(0);
            findViewById2.setVisibility(4);
            iArr[0] = (width - measuredWidth) - dip2px;
            iArr[1] = (iArr2[1] + height) - DPIUtil.dip2px(14.0f);
        }
        return iArr;
    }

    public int[] calculatePopWindowPosB(View view, View view2) {
        View findViewById = view2.findViewById(R.id.up_arrow);
        View findViewById2 = view2.findViewById(R.id.down_arrow);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int height = view.getHeight();
        int height2 = DPIUtil.getHeight();
        int i2 = RecommendUtils.windowWidthSize;
        if (i2 <= 0) {
            i2 = DPIUtil.getWidth();
        }
        view2.measure(0, 0);
        int measuredHeight = view2.getMeasuredHeight();
        int measuredWidth = view2.getMeasuredWidth();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) findViewById2.getLayoutParams();
        int dip2px = DPIUtil.dip2px(14.0f);
        if (iArr2[0] > i2 / 2) {
            layoutParams.rightMargin = dip2px;
            layoutParams2.rightMargin = dip2px;
            layoutParams2.addRule(11);
            layoutParams.addRule(11);
            iArr[0] = (((i2 + DPIUtil.dip2px(2.0f)) - (view.getWidth() / 2)) + (DPIUtil.dip2px(13.0f) / 2)) - measuredWidth;
        } else {
            iArr[0] = (view.getLeft() + (view.getWidth() / 2)) - DPIUtil.dip2px(2.0f);
            layoutParams.leftMargin = dip2px;
            layoutParams2.leftMargin = dip2px;
        }
        if ((height2 - iArr2[1]) - height < measuredHeight) {
            findViewById.setVisibility(4);
            findViewById2.setVisibility(0);
            iArr[1] = iArr2[1] - measuredHeight;
        } else {
            findViewById.setVisibility(0);
            findViewById2.setVisibility(4);
            iArr[1] = (iArr2[1] + height) - dip2px;
        }
        return iArr;
    }

    public void dismissPopupWindow() {
        PopupWindow popupWindow = this.singlePopupWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.singlePopupWindow.dismiss();
    }

    public PopupWindow getFeedBackPlanBPopupWindow(BaseActivity baseActivity, View view, final RecommendProduct recommendProduct, final int i2, final RecommendUtil.OnRecommendClickedListener onRecommendClickedListener, final int i3) {
        if (recommendProduct == null) {
            return null;
        }
        return generateFeedBackWindow(baseActivity, recommendProduct.feedBackReason, view, new OnFeedBackItemClick() { // from class: com.jingdong.common.recommend.RecommendFeedBackManger.1
            {
                RecommendFeedBackManger.this = this;
            }

            @Override // com.jingdong.common.recommend.RecommendFeedBackManger.OnFeedBackItemClick
            public void feedBackReason(FeedBackReason feedBackReason) {
                RecommendFeedBackManger.this.onclick(recommendProduct, null, i2, onRecommendClickedListener, null, feedBackReason, i3);
            }
        });
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public PopupWindow showTipPopupWindow(BaseActivity baseActivity, View view, RecommendProduct recommendProduct, int i2, RecommendUtil.OnRecommendClickedListener onRecommendClickedListener, int i3) {
        if (recommendProduct == null) {
            return null;
        }
        PopupWindow feedBackPlanBPopupWindow = getFeedBackPlanBPopupWindow(baseActivity, view, recommendProduct, i2, onRecommendClickedListener, i3);
        this.singlePopupWindow = feedBackPlanBPopupWindow;
        return feedBackPlanBPopupWindow;
    }

    public PopupWindow getFeedBackPlanBPopupWindow(BaseActivity baseActivity, View view, final RecommendMaterialData recommendMaterialData, final int i2, final RecommendUtil.OnRecommendClickedListener onRecommendClickedListener, final int i3) {
        if (recommendMaterialData == null) {
            return null;
        }
        return generateFeedBackWindow(baseActivity, recommendMaterialData.feedBackReason, view, new OnFeedBackItemClick() { // from class: com.jingdong.common.recommend.RecommendFeedBackManger.2
            {
                RecommendFeedBackManger.this = this;
            }

            @Override // com.jingdong.common.recommend.RecommendFeedBackManger.OnFeedBackItemClick
            public void feedBackReason(FeedBackReason feedBackReason) {
                RecommendFeedBackManger.this.onclick(null, recommendMaterialData, i2, onRecommendClickedListener, null, feedBackReason, i3);
            }
        });
    }

    public PopupWindow showTipPopupWindow(BaseActivity baseActivity, View view, RecommendMaterialData recommendMaterialData, int i2, RecommendUtil.OnRecommendClickedListener onRecommendClickedListener, int i3) {
        if (recommendMaterialData == null) {
            return null;
        }
        PopupWindow feedBackPlanBPopupWindow = getFeedBackPlanBPopupWindow(baseActivity, view, recommendMaterialData, i2, onRecommendClickedListener, i3);
        this.singlePopupWindow = feedBackPlanBPopupWindow;
        return feedBackPlanBPopupWindow;
    }
}
