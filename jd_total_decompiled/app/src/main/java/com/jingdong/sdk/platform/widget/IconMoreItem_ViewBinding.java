package com.jingdong.sdk.platform.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.sdk.platform.business.personal.R;

/* loaded from: classes10.dex */
public class IconMoreItem_ViewBinding implements Unbinder {
    private IconMoreItem target;

    @UiThread
    public IconMoreItem_ViewBinding(IconMoreItem iconMoreItem) {
        this(iconMoreItem, iconMoreItem);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        IconMoreItem iconMoreItem = this.target;
        if (iconMoreItem != null) {
            this.target = null;
            iconMoreItem.subTitle = null;
            iconMoreItem.title = null;
            iconMoreItem.icon = null;
            iconMoreItem.iconRedPoint = null;
            iconMoreItem.content = null;
            iconMoreItem.contentRedPoint = null;
            iconMoreItem.lottieView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }

    @UiThread
    public IconMoreItem_ViewBinding(IconMoreItem iconMoreItem, View view) {
        this.target = iconMoreItem;
        iconMoreItem.subTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.sub_title, "field 'subTitle'", TextView.class);
        iconMoreItem.title = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'title'", TextView.class);
        iconMoreItem.icon = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon, "field 'icon'", SimpleDraweeView.class);
        iconMoreItem.iconRedPoint = (ImageView) Utils.findRequiredViewAsType(view, R.id.icon_red_point, "field 'iconRedPoint'", ImageView.class);
        iconMoreItem.content = (TextView) Utils.findRequiredViewAsType(view, R.id.content, "field 'content'", TextView.class);
        iconMoreItem.contentRedPoint = (ImageView) Utils.findRequiredViewAsType(view, R.id.content_red_point, "field 'contentRedPoint'", ImageView.class);
        iconMoreItem.lottieView = (ImageView) Utils.findRequiredViewAsType(view, R.id.lottie_view, "field 'lottieView'", ImageView.class);
    }
}
