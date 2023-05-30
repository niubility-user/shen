package com.jingdong.common.recommend.forlist;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.skin.lib.CodingConstants;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendTemplate;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.Stack;

/* loaded from: classes6.dex */
public class BaseRecommendTemplateViewHolder extends BaseRecommendMaterialViewHolder {
    public static String TAG = "com.jingdong.common.recommend.forlist.BaseRecommendTemplateViewHolder";
    private BaseActivity activity;
    private Space elderSpace;
    protected int from;
    private int itemWidth;
    private View.OnClickListener jumpClickListener;
    private int labelContainerWidth;
    private Stack<View> labelViews;
    private LinearLayout recommendLabelContainer;

    public BaseRecommendTemplateViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.labelViews = new Stack<>();
        this.jumpClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.BaseRecommendTemplateViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
                RecommendTemplate recommendTemplate = (RecommendTemplate) view2.getTag();
                if (recommendTemplate == null || (onRecommendClickedListener = BaseRecommendTemplateViewHolder.this.clickedListener) == null) {
                    return;
                }
                onRecommendClickedListener.onRecommendJump(recommendTemplate.jump, recommendTemplate.isOpenApp);
                if (recommendTemplate.isFromCache) {
                    return;
                }
                RecommendMtaUtils.aggregationClickMtaRealize(BaseRecommendTemplateViewHolder.this.itemView.getContext(), recommendTemplate.sourceValue, BaseRecommendTemplateViewHolder.this.from, recommendTemplate.extension_id);
                BaseRecommendTemplateViewHolder.this.onAdClick(recommendTemplate.client_click_url);
            }
        };
        this.activity = baseActivity;
        this.recommendLabelContainer = (LinearLayout) view.findViewById(R.id.recommend_template_label);
        this.elderSpace = (Space) view.findViewById(R.id.recommend_template_elder_bottom_space);
        int i2 = RecommendUtils.windowWidthSize;
        if (i2 == 0) {
            this.itemWidth = ((DPIUtil.getAppWidth(baseActivity) - (RecommendUtils.RECYCLERVIEW_LEFT_RIGHT_PADDING * 2)) / 2) - (RecommendUtils.RECYCLERVIEW_ITEM_MARGIN * 2);
        } else {
            this.itemWidth = ((i2 - (RecommendUtils.RECYCLERVIEW_LEFT_RIGHT_PADDING * 2)) / 2) - (RecommendUtils.RECYCLERVIEW_ITEM_MARGIN * 2);
        }
    }

    private void changeLabelText(ViewGroup viewGroup, String str, String str2, String str3, String str4, JDDisplayImageOptions jDDisplayImageOptions) {
        if (viewGroup.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) viewGroup.getBackground().mutate()).setColor(generateColor(str3, "#0D4965F2"));
        }
        TextView textView = (TextView) viewGroup.getChildAt(1);
        textView.setText(str);
        textView.setTextColor(generateColor(str2, "#4965F2"));
        RecommendViewUtil.setRightTextSize(textView, 10, CodingConstants.T1_Text_regular, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) viewGroup.getChildAt(0);
        if (TextUtils.isEmpty(str4)) {
            simpleDraweeView.setVisibility(8);
            return;
        }
        simpleDraweeView.setVisibility(0);
        JDImageUtils.displayImage(str4, simpleDraweeView, jDDisplayImageOptions);
    }

    private View generateLabelView() {
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, DPIUtil.dip2px(18.0f));
        layoutParams.rightMargin = DPIUtil.dip2px(4.0f);
        linearLayout.setPadding(DPIUtil.dip2px(5.0f), 0, DPIUtil.dip2px(5.0f), 0);
        linearLayout.setLayoutParams(layoutParams);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.activity);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(DPIUtil.dip2px(11.0f), DPIUtil.dip2px(9.0f));
        layoutParams2.rightMargin = DPIUtil.dip2px(4.0f);
        simpleDraweeView.setLayoutParams(layoutParams2);
        simpleDraweeView.setVisibility(8);
        linearLayout.addView(simpleDraweeView);
        TextView textView = new TextView(this.activity);
        textView.setIncludeFontPadding(false);
        RecommendViewUtil.setRightTextSize(textView, 10, CodingConstants.T1_Text_regular, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setLines(1);
        linearLayout.addView(textView);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(9.0f));
        linearLayout.setBackgroundDrawable(gradientDrawable);
        return linearLayout;
    }

    private int getViewWidth(View view) {
        view.measure(0, 0);
        return view.getMeasuredWidth();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int generateColor(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return Color.parseColor(str);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        try {
            return Color.parseColor(str2);
        } catch (Exception e3) {
            if (OKLog.D) {
                e3.printStackTrace();
                return 0;
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void generateGradientDrawable(View view, float f2, int[] iArr) {
        try {
            if (Build.VERSION.SDK_INT >= 16 && (view.getBackground() instanceof GradientDrawable)) {
                ((GradientDrawable) view.getBackground().mutate()).setColors(iArr);
                return;
            }
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, iArr);
            gradientDrawable.setCornerRadius(f2);
            view.setBackgroundDrawable(gradientDrawable);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetContent() {
        if (this.recommendLabelContainer != null) {
            for (int i2 = 0; i2 < this.recommendLabelContainer.getChildCount(); i2++) {
                this.labelViews.push(this.recommendLabelContainer.getChildAt(i2));
            }
            this.recommendLabelContainer.removeAllViews();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTemplateData(RecommendTemplate recommendTemplate, int i2, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i3) {
        ArrayList<String> arrayList;
        setAdData(recommendTemplate);
        RecommendViewUtil.showUnIcon(this.adIcon, recommendTemplate.iconAd);
        this.from = i2;
        setFrom(i2);
        if (this.itemView.getMeasuredWidth() != 0) {
            double measuredWidth = this.itemView.getMeasuredWidth();
            Double.isNaN(measuredWidth);
            double dip2px = DPIUtil.dip2px(8.0f);
            Double.isNaN(dip2px);
            this.labelContainerWidth = (int) ((measuredWidth * 0.76d) - dip2px);
        } else {
            double d = this.itemWidth;
            Double.isNaN(d);
            double dip2px2 = DPIUtil.dip2px(8.0f);
            Double.isNaN(dip2px2);
            this.labelContainerWidth = (int) ((d * 0.76d) - dip2px2);
        }
        this.itemView.setOnClickListener(this.jumpClickListener);
        RecommendViewUtil.visible(this.recommendLabelContainer);
        try {
            if (this.recommendLabelContainer != null && (arrayList = recommendTemplate.lastTitle) != null && !arrayList.isEmpty()) {
                int i4 = this.labelContainerWidth;
                if ("1".equals(recommendTemplate.lastTitleType)) {
                    for (int i5 = 0; i5 < recommendTemplate.lastTitle.size(); i5++) {
                        View pop = !this.labelViews.isEmpty() ? this.labelViews.pop() : null;
                        if (pop == null) {
                            pop = generateLabelView();
                        }
                        View view = pop;
                        changeLabelText((ViewGroup) view, recommendTemplate.lastTitle.get(i5), recommendTemplate.lastTitleFont, recommendTemplate.lastTitleBg, "", jDDisplayImageOptions);
                        int viewWidth = getViewWidth(view) + DPIUtil.dip2px(4.0f);
                        if (i5 != 0 && viewWidth > i4) {
                            break;
                        }
                        this.recommendLabelContainer.addView(view);
                        i4 -= viewWidth;
                    }
                } else if ("2".equals(recommendTemplate.lastTitleType)) {
                    View pop2 = this.labelViews.isEmpty() ? null : this.labelViews.pop();
                    if (pop2 == null) {
                        pop2 = generateLabelView();
                    }
                    changeLabelText((ViewGroup) pop2, recommendTemplate.lastTitle.get(0), recommendTemplate.lastTitleFont, recommendTemplate.lastTitleBg, recommendTemplate.lastTitleIcon, jDDisplayImageOptions);
                    this.recommendLabelContainer.addView(pop2);
                }
                this.recommendLabelContainer.setVisibility(0);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        if (expoDataStore != null && !recommendTemplate.isFromCache) {
            if (!TextUtils.isEmpty(recommendTemplate.exposureJsonSourceValue)) {
                expoDataStore.putExpoJsonDada(recommendTemplate.exposureJsonSourceValue);
            } else if (!TextUtils.isEmpty(recommendTemplate.exposureSourceValue)) {
                expoDataStore.putExpoData(recommendTemplate.exposureSourceValue);
            }
        }
        setMaterialData(recommendTemplate, i3);
        RecommendUtils.setRecommendDebugMark(this.debugMarkTextView, recommendTemplate.showAdDot(), recommendTemplate.showMonetizedDot());
        if (isElder()) {
            RecommendViewUtil.visible(this.elderSpace);
        } else {
            RecommendViewUtil.gone(this.elderSpace);
        }
    }
}
