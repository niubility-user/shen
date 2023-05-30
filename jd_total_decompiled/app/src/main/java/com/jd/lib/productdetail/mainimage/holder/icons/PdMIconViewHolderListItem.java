package com.jd.lib.productdetail.mainimage.holder.icons;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicPicIcons;
import com.jd.lib.productdetail.mainimage.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes15.dex */
public class PdMIconViewHolderListItem extends LinearLayout {
    public SimpleDraweeView a;
    public TextView b;

    /* renamed from: c  reason: collision with root package name */
    public TextView f4891c;

    public PdMIconViewHolderListItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.a = (SimpleDraweeView) findViewById(R.id.lib_pd_holder_topimage_item_icon_item_icon);
        this.b = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_icon_item_text);
        this.f4891c = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_icon_item_desc);
    }

    public void setupWithData(WareBusinessMagicPicIcons wareBusinessMagicPicIcons) {
        if (wareBusinessMagicPicIcons != null) {
            this.b.setText(wareBusinessMagicPicIcons.name);
            JDImageUtils.displayImage(wareBusinessMagicPicIcons.mImgUrl, this.a, JDDisplayImageOptions.createSimple());
            if (this.f4891c != null) {
                if (!TextUtils.isEmpty(wareBusinessMagicPicIcons.shortDesc)) {
                    this.f4891c.setText(wareBusinessMagicPicIcons.shortDesc);
                    this.f4891c.setVisibility(0);
                    return;
                }
                this.f4891c.setVisibility(8);
            }
        }
    }
}
