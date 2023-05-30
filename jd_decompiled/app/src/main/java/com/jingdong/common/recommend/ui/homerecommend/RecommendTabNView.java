package com.jingdong.common.recommend.ui.homerecommend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendTab;
import com.jingdong.common.recommend.ui.TabItemViewInterface;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;

/* loaded from: classes6.dex */
public class RecommendTabNView extends FrameLayout implements TabItemViewInterface {
    private String contentDescription;
    public boolean isDeepDark;
    private boolean isSelected;
    private RecommendTab mRecommendTab;
    private int mTitleSelectedTextSize;
    private boolean mTitleSelectedUseText;
    private int mTitleUnSelectedTextSize;
    private boolean mTitleUnSelectedUseText;
    private SimpleDraweeView titleSelectedIV;
    private TextView titleTV;
    private SimpleDraweeView titleUnSelectedIV;
    private int[] wh;

    public RecommendTabNView(Context context) {
        this(context, null);
    }

    private void changeTextColor() {
        if (this.isSelected) {
            if (this.isDeepDark) {
                if (this.mTitleSelectedUseText) {
                    TextView textView = this.titleTV;
                    RecommendTab recommendTab = this.mRecommendTab;
                    setTextColor(textView, (recommendTab == null || TextUtils.isEmpty(recommendTab.seletedTitleDarkColor)) ? "#ffFF3826" : this.mRecommendTab.seletedTitleDarkColor);
                }
            } else if (this.mTitleSelectedUseText) {
                TextView textView2 = this.titleTV;
                RecommendTab recommendTab2 = this.mRecommendTab;
                setTextColor(textView2, (recommendTab2 == null || TextUtils.isEmpty(recommendTab2.seletedTitleColor)) ? "#ffE2231A" : this.mRecommendTab.seletedTitleColor);
            }
        } else if (this.isDeepDark) {
            if (this.mTitleSelectedUseText) {
                TextView textView3 = this.titleTV;
                RecommendTab recommendTab3 = this.mRecommendTab;
                setTextColor(textView3, (recommendTab3 == null || TextUtils.isEmpty(recommendTab3.unSelectedTitleDarkColor)) ? "#ffECECEC" : this.mRecommendTab.unSelectedTitleDarkColor);
            }
        } else if (this.mTitleSelectedUseText) {
            TextView textView4 = this.titleTV;
            RecommendTab recommendTab4 = this.mRecommendTab;
            setTextColor(textView4, (recommendTab4 == null || TextUtils.isEmpty(recommendTab4.unSelectedTitleColor)) ? "#ff222222" : this.mRecommendTab.unSelectedTitleColor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getBitmapFromResponse(HttpResponse httpResponse) {
        Bitmap decodeByteArray;
        if (httpResponse == null) {
            return null;
        }
        try {
            File saveFile = httpResponse.getSaveFile();
            if (saveFile != null) {
                decodeByteArray = BitmapFactory.decodeFile(saveFile.getPath());
            } else {
                byte[] inputData = httpResponse.getInputData();
                decodeByteArray = inputData != null ? BitmapFactory.decodeByteArray(inputData, 0, inputData.length) : null;
            }
            if (decodeByteArray != null) {
                if (decodeByteArray.getByteCount() >= 1) {
                    return decodeByteArray;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private int getRightSize(int i2) {
        int i3 = RecommendUtils.windowWidthSize;
        if (i3 == 0) {
            return DPIUtil.getWidthByDesignValue750(i2);
        }
        return RecommendUtils.getWidthByDesignValue(i3, i2, R2.attr.decimalNumber);
    }

    private void loadImageUrl(final ImageView imageView, String str) {
        if (imageView == null || TextUtils.isEmpty(str)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setConnectTimeout(5000);
        httpSetting.setAttempts(1);
        httpSetting.setReferer(RecommendUtils.HTTP_REFER);
        httpSetting.setCacheMode(0);
        httpSetting.setType(5000);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.recommend.ui.homerecommend.RecommendTabNView.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                try {
                    final Bitmap bitmapFromResponse = RecommendTabNView.this.getBitmapFromResponse(httpResponse);
                    if (bitmapFromResponse != null) {
                        RecommendTabNView.this.post(new Runnable() { // from class: com.jingdong.common.recommend.ui.homerecommend.RecommendTabNView.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    imageView.setImageBitmap(bitmapFromResponse);
                                    AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                    RecommendTabNView.this.onImageLoadSuccess(imageView);
                                } catch (Exception unused) {
                                }
                            }
                        });
                    }
                } catch (Exception e2) {
                    if (OKLog.D) {
                        throw e2;
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpSetting.setNeedShareImage(false);
        HttpGroupUtils.getHttpGroupaAsynPool(5000).add(httpSetting);
    }

    private void onImageLoadError(ImageView imageView) {
        SimpleDraweeView simpleDraweeView = this.titleSelectedIV;
        if (imageView == simpleDraweeView) {
            this.mTitleSelectedUseText = true;
        } else if (imageView == this.titleUnSelectedIV) {
            this.mTitleUnSelectedUseText = true;
        }
        if (imageView == simpleDraweeView || imageView == this.titleUnSelectedIV) {
            updateView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onImageLoadSuccess(View view) {
        SimpleDraweeView simpleDraweeView = this.titleSelectedIV;
        if (view == simpleDraweeView) {
            this.mTitleSelectedUseText = false;
        } else if (view == this.titleUnSelectedIV) {
            this.mTitleUnSelectedUseText = false;
        }
        if (view == simpleDraweeView || view == this.titleUnSelectedIV) {
            updateView();
        }
    }

    private void ratioLayout() {
        if (this.titleSelectedIV.getLayoutParams() != null) {
            this.titleSelectedIV.getLayoutParams().width = getRightSize(122);
            this.titleSelectedIV.getLayoutParams().height = getRightSize(46);
        }
        if (this.titleUnSelectedIV.getLayoutParams() != null) {
            this.titleUnSelectedIV.getLayoutParams().width = getRightSize(94);
            this.titleUnSelectedIV.getLayoutParams().height = getRightSize(35);
        }
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
            if (this.mTitleSelectedUseText) {
                RecommendViewUtil.inVisible(this.titleSelectedIV, this.titleUnSelectedIV);
                RecommendViewUtil.visible(this.titleTV);
                this.titleTV.setTextSize(0, getRightSize(this.mTitleSelectedTextSize));
            } else {
                RecommendViewUtil.inVisible(this.titleTV, this.titleUnSelectedIV);
                RecommendViewUtil.visible(this.titleSelectedIV);
            }
        } else if (this.mTitleUnSelectedUseText) {
            this.titleTV.setTextSize(0, getRightSize(this.mTitleUnSelectedTextSize));
            RecommendViewUtil.inVisible(this.titleSelectedIV, this.titleUnSelectedIV);
            RecommendViewUtil.visible(this.titleTV);
        } else {
            RecommendViewUtil.inVisible(this.titleTV, this.titleSelectedIV);
            RecommendViewUtil.visible(this.titleUnSelectedIV);
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
        this.mTitleSelectedTextSize = 30;
        this.mTitleUnSelectedTextSize = 26;
        if (this.isSelected) {
            this.titleTV.setTextSize(0, getRightSize(30));
        } else {
            this.titleTV.setTextSize(0, getRightSize(26));
        }
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void onWidthSizeChange() {
        try {
            if (getLayoutParams() != null) {
                getLayoutParams().width = getRightSize(this.wh[0]);
                getLayoutParams().height = getRightSize(this.wh[1]);
            }
            ratioLayout();
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
            if (!TextUtils.isEmpty(this.mRecommendTab.unselectedTitleImg)) {
                loadImageUrl(this.titleUnSelectedIV, this.mRecommendTab.unselectedTitleImg);
            }
            if (!TextUtils.isEmpty(this.mRecommendTab.selectedTitleImg)) {
                loadImageUrl(this.titleSelectedIV, this.mRecommendTab.selectedTitleImg);
            }
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
        setLayoutParams(new FrameLayout.LayoutParams(getRightSize(i2), getRightSize(i3)));
    }

    @Override // com.jingdong.common.recommend.ui.TabItemViewInterface
    public void startTabAni(boolean z, boolean z2) {
    }

    public RecommendTabNView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecommendTabNView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTitleSelectedUseText = true;
        this.mTitleUnSelectedUseText = true;
        this.mTitleSelectedTextSize = 30;
        this.mTitleUnSelectedTextSize = 26;
        this.contentDescription = "";
        this.wh = new int[]{0, 0};
        this.isDeepDark = false;
        TextView textView = new TextView(context);
        this.titleTV = textView;
        textView.setTypeface(Typeface.defaultFromStyle(1));
        this.titleTV.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.titleTV.setGravity(17);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.titleSelectedIV = simpleDraweeView;
        simpleDraweeView.setLayoutParams(new FrameLayout.LayoutParams(getRightSize(122), getRightSize(46), 17));
        ScalingUtils.ScaleType scaleType = ScalingUtils.ScaleType.FIT_CENTER;
        this.titleSelectedIV.getHierarchy().setActualImageScaleType(scaleType);
        SimpleDraweeView simpleDraweeView2 = new SimpleDraweeView(context);
        this.titleUnSelectedIV = simpleDraweeView2;
        simpleDraweeView2.setLayoutParams(new FrameLayout.LayoutParams(getRightSize(94), getRightSize(35), 17));
        this.titleUnSelectedIV.getHierarchy().setActualImageScaleType(scaleType);
        onTextScaleModeChanged();
        ratioLayout();
        addView(this.titleTV);
        addView(this.titleSelectedIV);
        addView(this.titleUnSelectedIV);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: com.jingdong.common.recommend.ui.homerecommend.RecommendTabNView.1
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
        RecommendViewUtil.inVisible(this.titleUnSelectedIV, this.titleSelectedIV, this.titleTV);
    }
}
