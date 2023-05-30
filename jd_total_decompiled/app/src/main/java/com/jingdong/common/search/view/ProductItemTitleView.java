package com.jingdong.common.search.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.lib.un.utils.UnDisplayUtil;
import com.jingdong.common.search.utils.ViewUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ProductItemTitleView extends AppCompatTextView {
    private Context context;

    public ProductItemTitleView(@NonNull Context context) {
        this(context, null);
    }

    private void setSpanString(List<IconTextBean> list, String str, TextView textView) {
        Bitmap bitmap;
        if (TextUtils.isEmpty(str) || textView == null) {
            return;
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            sb.append(LangUtils.SINGLE_SPACE);
            sb.append(LangUtils.SINGLE_SPACE);
        }
        sb.append(str);
        SpannableString spannableString = new SpannableString(sb.toString());
        for (int i3 = 0; i3 < list.size(); i3++) {
            IconTextBean iconTextBean = list.get(i3);
            if (iconTextBean != null) {
                int dip2px = UnDisplayUtil.dip2px(this.context, iconTextBean.heightDp);
                if (dip2px <= 0) {
                    dip2px = UnDisplayUtil.dip2px(this.context, JDElderModeUtils.isElderMode() ? 16.0f : 12.0f);
                }
                CenterImageSpan centerImageSpan = null;
                if (!TextUtils.isEmpty(iconTextBean.url)) {
                    centerImageSpan = new CenterImageSpan(new UrlImageParser(textView, textView.getContext()).getDrawable(iconTextBean.url, dip2px));
                } else if (!TextUtils.isEmpty(iconTextBean.resCode) && !TextUtils.isEmpty(iconTextBean.showName)) {
                    Bitmap textBitmap = UnIconConfigHelper.getTextBitmap(iconTextBean.resCode, iconTextBean.showName, JDElderModeUtils.isElderMode());
                    if (textBitmap != null && textBitmap.getHeight() != 0) {
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(textView.getContext().getResources(), textBitmap);
                        bitmapDrawable.setBounds(0, 0, (int) (dip2px * ((textBitmap.getWidth() * 1.0f) / textBitmap.getHeight())), dip2px);
                        centerImageSpan = new CenterImageSpan(bitmapDrawable);
                    }
                } else if (!TextUtils.isEmpty(iconTextBean.resCode) && (bitmap = UnIconConfigHelper.getBitmap(iconTextBean.resCode, JDElderModeUtils.isElderMode())) != null && bitmap.getHeight() != 0) {
                    BitmapDrawable bitmapDrawable2 = new BitmapDrawable(textView.getContext().getResources(), bitmap);
                    bitmapDrawable2.setBounds(0, 0, (int) (dip2px * ((bitmap.getWidth() * 1.0f) / bitmap.getHeight())), dip2px);
                    centerImageSpan = new CenterImageSpan(bitmapDrawable2);
                }
                if (centerImageSpan != null) {
                    int i4 = i3 * 2;
                    spannableString.setSpan(centerImageSpan, i4, i4 + 1, 33);
                }
            }
        }
        textView.setText(spannableString);
    }

    public void setData(ProductTitleBean productTitleBean) {
        if (productTitleBean == null) {
            return;
        }
        int i2 = productTitleBean.font;
        if (i2 < 0) {
            i2 = 14;
        }
        if (JDElderModeUtils.isElderMode()) {
            setTextSize(JDElderModeUtils.getElderTextSize(i2));
        } else {
            setTextSize(TextScaleModeHelper.INSTANCE.getInstance().getScaleSize(JdSdk.getInstance().getApplicationContext(), i2));
        }
        setTypeface(productTitleBean.isBold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        productTitleBean.foreLabelMai = null;
        List<IconTextBean> list = productTitleBean.iconArray;
        if (list != null && list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (IconTextBean iconTextBean : productTitleBean.iconArray) {
                if (iconTextBean != null) {
                    if (TextUtils.isEmpty(sb)) {
                        sb.append(iconTextBean.trackId);
                    } else {
                        sb.append("#");
                        sb.append(iconTextBean.trackId);
                    }
                }
            }
            productTitleBean.foreLabelMai = sb.toString();
        }
        setSpanString(productTitleBean.iconArray, productTitleBean.text, this);
        setMaxLines(productTitleBean.maxLine);
        setContentDescription(productTitleBean.text);
        productTitleBean.showLineCountForMta = ViewUtil.getShowLineCountForMta(this, productTitleBean.maxWidth);
    }

    public ProductItemTitleView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProductItemTitleView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.context = context;
        setGravity(16);
        setEllipsize(TextUtils.TruncateAt.END);
    }
}
