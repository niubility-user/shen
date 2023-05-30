package com.jd.manto.center.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jd.manto.center.R;
import com.jd.manto.center.k.b;
import com.jd.manto.center.k.h;
import com.jd.manto.center.model.entity.MantoDiscoveryBean;

/* loaded from: classes17.dex */
public class TitleViewholder extends MantoBaseViewholder {
    private TextView a;

    public TitleViewholder(@NonNull View view) {
        super(view);
        this.a = (TextView) view.findViewById(R.id.manto_center_item_title);
    }

    @Override // com.jd.manto.center.viewholder.MantoBaseViewholder
    public void b(Context context, MantoDiscoveryBean mantoDiscoveryBean) {
        if (b.a(context, mantoDiscoveryBean)) {
            return;
        }
        h.i(this.a, mantoDiscoveryBean.recommendText);
    }
}
