package com.jingdong.common.recommend.ui.personal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendTab;
import com.jingdong.common.recommend.ui.TabItemViewInterface;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes6.dex */
public class PersonalTabNewView extends RelativeLayout implements TabItemViewInterface {
    private String contentDescription;
    public boolean isDeepDark;
    private boolean isSelected;
    private RecommendTab mRecommendTab;
    private int mTitleSelectedTextSize;
    private ImageView selectImg;
    private TextView titleTV;
    private int[] wh;

    public PersonalTabNewView(@NonNull Context context, boolean z) {
        this(context, null, z);
    }

    private void changeTextColor() {
        if (this.isSelected) {
            if (this.isDeepDark) {
                TextView textView = this.titleTV;
                RecommendTab recommendTab = this.mRecommendTab;
                setTextColor(textView, (recommendTab == null || TextUtils.isEmpty(recommendTab.seletedTitleDarkColor)) ? "#ffFF3826" : this.mRecommendTab.seletedTitleDarkColor);
                return;
            }
            TextView textView2 = this.titleTV;
            RecommendTab recommendTab2 = this.mRecommendTab;
            setTextColor(textView2, (recommendTab2 == null || TextUtils.isEmpty(recommendTab2.seletedTitleColor)) ? "#ffE2231A" : this.mRecommendTab.seletedTitleColor);
        } else if (this.isDeepDark) {
            TextView textView3 = this.titleTV;
            RecommendTab recommendTab3 = this.mRecommendTab;
            setTextColor(textView3, (recommendTab3 == null || TextUtils.isEmpty(recommendTab3.unSelectedTitleDarkColor)) ? "#ffECECEC" : this.mRecommendTab.unSelectedTitleDarkColor);
        } else {
            TextView textView4 = this.titleTV;
            RecommendTab recommendTab4 = this.mRecommendTab;
            setTextColor(textView4, (recommendTab4 == null || TextUtils.isEmpty(recommendTab4.unSelectedTitleColor)) ? "#ff222222" : this.mRecommendTab.unSelectedTitleColor);
        }
    }

    private int getRightSize(int i2) {
        int i3 = RecommendUtils.windowWidthSize;
        if (i3 == 0) {
            return DPIUtil.getWidthByDesignValue750(i2);
        }
        return RecommendUtils.getWidthByDesignValue(i3, i2, R2.attr.decimalNumber);
    }

    private void setTextColor(TextView textView, String str) {
        if (textView != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                textView.setTextColor(Color.parseColor(str));
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void updateView() {
        if (this.isSelected) {
            RecommendViewUtil.visible(this.selectImg);
            this.titleTV.setTypeface(Typeface.defaultFromStyle(1));
        } else {
            RecommendViewUtil.inVisible(this.selectImg);
            this.titleTV.setTypeface(Typeface.defaultFromStyle(0));
        }
        changeTextColor();
        setSelected(this.isSelected);
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void onDeepDarkChanged(boolean z) {
        this.isDeepDark = z;
        changeTextColor();
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void onTextScaleModeChanged() {
        this.mTitleSelectedTextSize = 32;
        this.titleTV.setTextSize(0, getRightSize(32));
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void onWidthSizeChange() {
        try {
            if (getLayoutParams() != null) {
                getLayoutParams().width = getRightSize(this.wh[0]);
                getLayoutParams().height = getRightSize(this.wh[1]);
            }
            updateView();
        } catch (Exception e2) {
            if (OKLog.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setChangeProgress(float f2) {
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setHasSplitLine(boolean z) {
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setHasSubTitle(boolean z) {
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setRecommendTab(RecommendTab recommendTab) {
        this.mRecommendTab = recommendTab;
        if (recommendTab != null) {
            this.titleTV.setText(recommendTab.title);
            if (!TextUtils.isEmpty(recommendTab.title)) {
                this.contentDescription += recommendTab.title;
            }
        }
        setContentDescription(this.contentDescription);
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setTabSelect(boolean z) {
        this.isSelected = z;
        updateView();
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void setWH(int i2, int i3) {
        int[] iArr = this.wh;
        iArr[0] = i2;
        iArr[1] = i3;
        setLayoutParams(new RelativeLayout.LayoutParams(getRightSize(i2), getRightSize(i3)));
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void startTabAni(boolean z, boolean z2) {
    }

    public PersonalTabNewView(@NonNull Context context, @Nullable AttributeSet attributeSet, boolean z) {
        this(context, attributeSet, 0, z);
    }

    public PersonalTabNewView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, boolean z) {
        super(context, attributeSet, i2);
        this.mTitleSelectedTextSize = 32;
        this.isDeepDark = false;
        this.contentDescription = "";
        this.wh = new int[]{0, 0};
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        layoutParams.topMargin = getRightSize(8);
        if (z) {
            layoutParams.rightMargin = getRightSize(38);
            layoutParams.addRule(11);
        } else {
            layoutParams.leftMargin = getRightSize(38);
            layoutParams.addRule(9);
        }
        addView(relativeLayout, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        TextView textView = new TextView(context);
        this.titleTV = textView;
        textView.setLayoutParams(layoutParams2);
        this.titleTV.setGravity(15);
        this.titleTV.setTextSize(0, getRightSize(this.mTitleSelectedTextSize));
        relativeLayout.addView(this.titleTV, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.topMargin = getRightSize(30);
        layoutParams3.addRule(14);
        ImageView imageView = new ImageView(context);
        this.selectImg = imageView;
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.recommend_personal_tab_bg));
        this.selectImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        relativeLayout.addView(this.selectImg, layoutParams3);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: com.jingdong.common.recommend.ui.personal.PersonalTabNewView.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                if (view != null) {
                    if (accessibilityEvent.getEventType() == 32768 || accessibilityEvent.getEventType() == 1) {
                        accessibilityEvent.setChecked(view.isSelected());
                    }
                }
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                if (accessibilityNodeInfoCompat == null || view == null) {
                    return;
                }
                accessibilityNodeInfoCompat.setSelected(view.isSelected());
            }
        });
        RecommendViewUtil.inVisible(this.selectImg);
    }
}
