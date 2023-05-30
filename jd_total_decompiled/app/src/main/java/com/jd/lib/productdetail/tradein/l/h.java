package com.jd.lib.productdetail.tradein.l;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.bean.TradeInServiceInfoItem;
import com.jd.lib.productdetail.tradein.l.h;
import com.jd.lib.productdetail.tradein.widget.TradeInServiceDialogItem;
import java.util.List;

/* loaded from: classes16.dex */
public class h extends Dialog {

    /* loaded from: classes16.dex */
    public static class a {
        public Context a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f5394c;
        public List<TradeInServiceInfoItem> d;

        /* renamed from: e  reason: collision with root package name */
        public DialogInterface.OnClickListener f5395e;

        public a(Context context) {
            this.a = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(h hVar, View view) {
            DialogInterface.OnClickListener onClickListener = this.f5395e;
            if (onClickListener != null) {
                onClickListener.onClick(hVar, -1);
            }
        }

        public h a() {
            final h hVar = new h(this.a, R.style.tradein_dialog_style);
            View inflate = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.tradein_widget_dialog_service_i, (ViewGroup) null);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.tradein_widget_dialog_old_i_layout);
            Button button = (Button) inflate.findViewById(R.id.tradein_widget_dialog_i_neg_button);
            linearLayout.setBackgroundResource(R.drawable.tradein_result_service_background);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.l.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    h.a.this.b(hVar, view);
                }
            });
            ((TextView) inflate.findViewById(R.id.tradein_widget_dialog_old_title)).setText(this.b);
            button.setText(this.f5394c);
            linearLayout.removeAllViews();
            List<TradeInServiceInfoItem> list = this.d;
            if (list != null && !list.isEmpty()) {
                for (int i2 = 0; i2 < this.d.size(); i2++) {
                    TradeInServiceDialogItem tradeInServiceDialogItem = (TradeInServiceDialogItem) LayoutInflater.from(this.a).inflate(R.layout.tradein_service_dialog_item, (ViewGroup) linearLayout, false);
                    tradeInServiceDialogItem.a(this.d.get(i2));
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                    if (this.d.size() == 1) {
                        layoutParams.topMargin = PDUtils.dip2px(16.0f);
                        layoutParams.bottomMargin = PDUtils.dip2px(16.0f);
                    } else if (i2 == this.d.size() - 1) {
                        layoutParams.bottomMargin = PDUtils.dip2px(16.0f);
                    } else if (i2 == 0) {
                        layoutParams.topMargin = PDUtils.dip2px(16.0f);
                        layoutParams.bottomMargin = PDUtils.dip2px(10.0f);
                    } else {
                        layoutParams.bottomMargin = PDUtils.dip2px(10.0f);
                    }
                    linearLayout.addView(tradeInServiceDialogItem, layoutParams);
                }
            }
            hVar.addContentView(inflate, new RelativeLayout.LayoutParams(-1, -1));
            return hVar;
        }
    }

    public h(Context context, int i2) {
        super(context, i2);
    }
}
