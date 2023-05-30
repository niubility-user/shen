package com.jd.lib.flexcube.owidgets.view.close;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.FlexImageView;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.ImageEntity;
import com.jd.lib.un.commonreslib.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import java.util.Map;

/* loaded from: classes15.dex */
public class CloseButton extends FlexImageView {
    public static Drawable t = new ColorDrawable(0);
    private JDDisplayImageOptions s;

    public CloseButton(Context context) {
        super(context);
        JDDisplayImageOptions showImageOnLoading = new JDDisplayImageOptions().showImageOnLoading(t);
        int i2 = R.drawable.xview_close;
        this.s = showImageOnLoading.showImageOnFail(i2).showImageForEmptyUri(i2);
        ImageView imageView = this.f4452g;
        if (imageView != null) {
            imageView.setContentDescription(context.getString(com.jd.lib.flexcube.owidgets.R.string.owidgets_contentDescription_close));
        }
    }

    @Override // com.jd.lib.flexcube.widgets.FlexImageView, com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map map, IWidgetCommunication iWidgetCommunication) {
        ImageEntity imageEntity = this.f4453h;
        if (imageEntity != null && imageEntity.dataPath != null) {
            String d = b.d(map, this.f4455j);
            setVisibility(0);
            JDImageUtils.displayImage(d, this.f4452g, this.s, true);
            setBackgroundColor(this.f4457l);
            if (TextUtils.isEmpty(this.f4453h.dataPath.clickEvent)) {
                a.b bVar = new a.b(getContext(), new ClickEvent());
                bVar.a(iWidgetCommunication.getBabelScope());
                setOnClickListener(bVar.b());
                return;
            }
            g(map, iWidgetCommunication);
            return;
        }
        clear();
    }
}
