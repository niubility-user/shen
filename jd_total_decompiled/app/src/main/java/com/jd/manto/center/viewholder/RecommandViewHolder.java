package com.jd.manto.center.viewholder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.manto.center.R;
import com.jd.manto.center.c;
import com.jd.manto.center.h.b;
import com.jd.manto.center.k.h;
import com.jd.manto.center.model.entity.MantoDiscoveryBean;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes17.dex */
public class RecommandViewHolder extends MantoBaseViewholder {
    private SimpleDraweeView a;
    private TextView b;

    /* renamed from: c  reason: collision with root package name */
    private TextView f6548c;
    private ImageView d;

    /* loaded from: classes17.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f6549g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ MantoDiscoveryBean f6550h;

        a(Context context, MantoDiscoveryBean mantoDiscoveryBean) {
            this.f6549g = context;
            this.f6550h = mantoDiscoveryBean;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Context context = this.f6549g;
            MantoDiscoveryBean mantoDiscoveryBean = this.f6550h;
            c.d(context, mantoDiscoveryBean.appId, mantoDiscoveryBean.appType, "1012");
            Context context2 = this.f6549g;
            MantoDiscoveryBean mantoDiscoveryBean2 = this.f6550h;
            b.o(context2, mantoDiscoveryBean2.appId, mantoDiscoveryBean2.appName, RecommandViewHolder.this.getAdapterPosition() - 2);
        }
    }

    public RecommandViewHolder(View view) {
        super(view);
        this.a = (SimpleDraweeView) view.findViewById(R.id.iv_logo);
        this.b = (TextView) view.findViewById(R.id.tv_app_name);
        this.f6548c = (TextView) view.findViewById(R.id.manto_app_desc);
        this.d = (ImageView) view.findViewById(R.id.iv_trial_icon);
    }

    @Override // com.jd.manto.center.viewholder.MantoBaseViewholder
    public void b(Context context, MantoDiscoveryBean mantoDiscoveryBean) {
        if (mantoDiscoveryBean == null || context == null) {
            return;
        }
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.isScale(false);
        JDImageUtils.displayImage(mantoDiscoveryBean.logoUrl, this.a, jDDisplayImageOptions);
        h.i(this.b, mantoDiscoveryBean.appName);
        h.i(this.f6548c, mantoDiscoveryBean.desc);
        if (TextUtils.equals(mantoDiscoveryBean.appType, "2")) {
            h.l(this.d);
        } else {
            h.b(this.d);
        }
        this.itemView.setOnClickListener(new a(context, mantoDiscoveryBean));
    }
}
