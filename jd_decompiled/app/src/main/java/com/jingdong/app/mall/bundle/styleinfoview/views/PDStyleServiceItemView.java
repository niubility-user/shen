package com.jingdong.app.mall.bundle.styleinfoview.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jingdong.app.mall.bundle.styleinfoview.R;

/* loaded from: classes3.dex */
public class PDStyleServiceItemView extends FrameLayout {
    private Context mContext;
    private PdAutoChangeTextSize mServiceItemContent;
    private PdAutoChangeTextSize mServiceItemDiscount;

    public PDStyleServiceItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void changeStyle(boolean z, boolean z2) {
        int i2 = z2 ? R.color.libpdstyleinfoview_style_text_dark_selector : R.color.libpdstyleinfoview_style_text_selector;
        Resources resources = getResources();
        if (z) {
            i2 = com.jingdong.common.R.color.lib_style_jx_text_selector;
        }
        ColorStateList colorStateList = resources.getColorStateList(i2);
        int i3 = z2 ? R.drawable.libpdstyleinfoview_style_button_dark : com.jingdong.common.R.drawable.lib_pd_style_button_g;
        if (z) {
            i3 = com.jingdong.common.R.drawable.lib_pd_style_button_x;
        }
        int i4 = z ? R.drawable.libpdstyleinfoview_style_jx_service_discount_selector : R.drawable.libpdstyleinfoview_style_service_discount_selector;
        ColorStateList colorStateList2 = getResources().getColorStateList(z2 ? R.color.libpdstyleinfoview_style_text_dark_selector_discount : R.color.libpdstyleinfoview_style_text_selector_discount);
        this.mServiceItemContent.setTextColor(colorStateList);
        this.mServiceItemContent.setBackgroundResource(i3);
        this.mServiceItemDiscount.setBackgroundResource(i4);
        this.mServiceItemDiscount.setTextColor(colorStateList2);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mServiceItemContent = (PdAutoChangeTextSize) findViewById(R.id.libpdstyleinfoview_detail_style_service_item_content);
        this.mServiceItemDiscount = (PdAutoChangeTextSize) findViewById(R.id.libpdstyleinfoview_detail_style_service_item_discount);
    }

    public void setContentTextColor(int i2) {
        PdAutoChangeTextSize pdAutoChangeTextSize = this.mServiceItemContent;
        if (pdAutoChangeTextSize != null) {
            pdAutoChangeTextSize.setTextColor(this.mContext.getResources().getColor(i2));
        }
    }

    public void setItemSelected(boolean z) {
        this.mServiceItemContent.setSelected(z);
        this.mServiceItemDiscount.setSelected(z);
        setSelected(z);
    }

    public void setServiceContent(Spannable spannable) {
        PdAutoChangeTextSize pdAutoChangeTextSize = this.mServiceItemContent;
        if (pdAutoChangeTextSize != null) {
            pdAutoChangeTextSize.setText(spannable);
        }
    }

    public void setServiceDiscount(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mServiceItemDiscount.setText(str);
        } else {
            this.mServiceItemDiscount.setVisibility(8);
        }
    }

    public void setServiceContent(String str) {
        PdAutoChangeTextSize pdAutoChangeTextSize = this.mServiceItemContent;
        if (pdAutoChangeTextSize != null) {
            pdAutoChangeTextSize.setText(str);
        }
    }
}
