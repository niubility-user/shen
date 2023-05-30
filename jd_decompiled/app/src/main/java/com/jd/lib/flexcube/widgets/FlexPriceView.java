package com.jd.lib.flexcube.widgets;

import android.content.Context;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jd.lib.flexcube.iwidget.b.a;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.b.d;
import com.jd.lib.flexcube.widgets.b.f;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.PriceEntity;
import com.jd.lib.flexcube.widgets.entity.common.ImgLabel;
import com.jd.lib.flexcube.widgets.entity.product.PriceDataPath;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes15.dex */
public class FlexPriceView extends LinearLayout implements IWidget<PriceEntity>, IKnowWidget<PriceEntity> {
    private String autoFitType;
    private int height;
    private int labelWidth;
    private ImageView mLabelView;
    private PriceEntity mPriceEntity;
    private TextView mPriceView;
    private float multiple;
    protected Paint paint;
    private String textColorKey;
    private int width;

    public FlexPriceView(Context context) {
        super(context);
        setOrientation(0);
        TextView textView = new TextView(context);
        this.mPriceView = textView;
        textView.setSingleLine();
        this.mPriceView.setEllipsize(TextUtils.TruncateAt.END);
        this.mPriceView.setIncludeFontPadding(false);
        addView(this.mPriceView);
        ImageView newImageView = ImageLoad.newImageView(context);
        this.mLabelView = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.mLabelView);
    }

    private void checkWidth() {
        if (this.mPriceView == null || this.labelWidth <= 0) {
            return;
        }
        if (this.paint == null) {
            this.paint = new Paint();
        }
        if (f.a(this.paint, this.mPriceView.getText(), this.mPriceEntity.getConfig().getTextSize(this.multiple)) + this.labelWidth > this.mPriceEntity.getConfig().getW(this.multiple)) {
            this.mLabelView.setVisibility(8);
        } else {
            this.mLabelView.setVisibility(0);
        }
    }

    private int getTextColorD(@NonNull Map map, String str, int i2) {
        if (!TextUtils.isEmpty(str) && this.mPriceEntity.getConfig() != null) {
            String d = b.d(map, str);
            if (!TextUtils.isEmpty(d)) {
                return a.a(d, -16777216);
            }
        }
        return i2;
    }

    private int getTextMargin() {
        try {
            int i2 = this.mPriceView.getPaint().getFontMetricsInt().descent;
            if (i2 >= 4) {
                return i2;
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    private void setOnClick(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        Serializable serializable = iWidgetCommunication.getStateBundle().getSerializable(this.mPriceEntity.dataPath.clickEvent);
        ClickEvent clickEvent = serializable instanceof ClickEvent ? (ClickEvent) serializable : null;
        if (clickEvent == null) {
            clickEvent = b.a(map, this.mPriceEntity.dataPath.clickEvent);
        }
        if (clickEvent != null) {
            a.b bVar = new a.b(getContext(), clickEvent);
            bVar.a(iWidgetCommunication.getBabelScope());
            setOnClickListener(bVar.b());
            iWidgetCommunication.getStateBundle().putSerializable(this.mPriceEntity.dataPath.clickEvent, clickEvent);
            return;
        }
        setClickable(false);
    }

    private void showLabel(ImgLabel imgLabel) {
        this.labelWidth = (int) (imgLabel.w * this.multiple);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.labelWidth, (int) (imgLabel.f4511h * this.multiple));
        layoutParams.leftMargin = DPIUtil.dip2px(getContext(), 3.0f);
        layoutParams.bottomMargin = getTextMargin();
        this.mLabelView.setLayoutParams(layoutParams);
        this.mLabelView.setVisibility(0);
        ImageLoad.with(this.mLabelView).load(imgLabel.src);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        this.mPriceView.setText("");
        this.mLabelView.setVisibility(8);
        setClickable(false);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        if ("11".equals(this.mPriceEntity.getConfig().autoFitType)) {
            return this.height;
        }
        return -2;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        PriceEntity priceEntity = this.mPriceEntity;
        if (priceEntity != null) {
            if ("1".equals(priceEntity.getConfig().linearLayout_autoFitType)) {
                return -2;
            }
            if ("11".equals(this.mPriceEntity.getConfig().autoFitType)) {
                return this.width;
            }
            return this.width;
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        PriceDataPath priceDataPath;
        int i2;
        PriceEntity priceEntity = this.mPriceEntity;
        if (priceEntity != null && (priceDataPath = priceEntity.dataPath) != null) {
            String d = b.d(map, priceDataPath.value);
            if (!TextUtils.isEmpty(d)) {
                setVisibility(0);
                if (!TextUtils.isEmpty(this.textColorKey)) {
                    this.mPriceView.setTextColor(getTextColorD(map, this.textColorKey, -16777216));
                }
                StringBuilder sb = new StringBuilder(c.a(getContext(), d));
                int indexOf = sb.indexOf(OrderISVUtil.MONEY_DECIMAL);
                int length = sb.length();
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb);
                if (this.mPriceEntity.getConfig().fontInfo != null) {
                    float scale = this.mPriceEntity.config.fontInfo.getScale();
                    if ("1".equals(this.mPriceEntity.getConfig().fontInfo.zoom)) {
                        spannableStringBuilder.setSpan(new RelativeSizeSpan(scale), 0, 1, 33);
                        if (indexOf > 0 && (i2 = indexOf + 1) <= spannableStringBuilder.length()) {
                            spannableStringBuilder.setSpan(new RelativeSizeSpan(scale), i2, length, 33);
                        }
                    }
                    if ("1".equals(this.mPriceEntity.getConfig().fontInfo.italic)) {
                        spannableStringBuilder.append((CharSequence) LangUtils.SINGLE_SPACE);
                    }
                }
                this.mPriceView.setText(spannableStringBuilder);
                if (!"1".equals(this.autoFitType)) {
                    checkWidth();
                }
                if (TextUtils.isEmpty(this.mPriceEntity.dataPath.clickEvent)) {
                    setClickable(false);
                    return;
                } else {
                    setOnClick(map, iWidgetCommunication);
                    return;
                }
            }
            setVisibility(8);
            clear();
            return;
        }
        clear();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IKnowWidget
    public PriceEntity getWidgetEntity() {
        return this.mPriceEntity;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateStyle(@NonNull PriceEntity priceEntity, float f2) {
        this.mPriceEntity = priceEntity;
        if (priceEntity != null && priceEntity.config != null) {
            this.multiple = f2;
            this.width = priceEntity.getConfig().getW(f2);
            this.height = priceEntity.getConfig().getH(f2);
            d.c(this.mPriceView, priceEntity.getConfig().fontInfo);
            d.b(this, priceEntity.getConfig().fontInfo, "11".equals(this.mPriceEntity.getConfig().autoFitType));
            f.e(this.mPriceView, priceEntity.getConfig().fontInfo);
            this.mPriceView.setTextSize(0, this.mPriceEntity.getConfig().getTextSize(f2));
            if (!TextUtils.isEmpty(priceEntity.getConfig().color) && priceEntity.getConfig().color.startsWith("$")) {
                this.textColorKey = priceEntity.getConfig().color;
            } else {
                this.mPriceView.setTextColor(com.jd.lib.flexcube.iwidget.b.a.a(priceEntity.getConfig().color, -16777216));
            }
            this.autoFitType = priceEntity.config.linearLayout_autoFitType;
            if (priceEntity.hasLabel()) {
                showLabel(priceEntity.getConfig().priceLabel);
                return;
            }
            this.labelWidth = 0;
            this.mLabelView.setVisibility(8);
            return;
        }
        clear();
    }
}
