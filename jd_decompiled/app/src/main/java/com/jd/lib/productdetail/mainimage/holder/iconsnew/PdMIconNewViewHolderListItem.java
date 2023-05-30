package com.jd.lib.productdetail.mainimage.holder.iconsnew;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.mainimage.R;

/* loaded from: classes15.dex */
public class PdMIconNewViewHolderListItem extends LinearLayout {
    public SimpleDraweeView a;
    public TextView b;

    /* renamed from: c  reason: collision with root package name */
    public TextView f4894c;
    public TextView d;

    public PdMIconNewViewHolderListItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.a = (SimpleDraweeView) findViewById(R.id.lib_pd_holder_topimage_item_icon_new_item_icon);
        this.b = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_icon_new_item_name);
        this.f4894c = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_icon_new_item_short_desc);
        this.d = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_icon_new_item_desc);
    }
}
