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
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.widget.TradeInOldItem;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes16.dex */
public class f extends Dialog {

    /* loaded from: classes16.dex */
    public static class a {
        public Context a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f5390c;
        public List<TradeInResultData.OldContentList> d;

        /* renamed from: e  reason: collision with root package name */
        public DialogInterface.OnClickListener f5391e;

        /* renamed from: com.jd.lib.productdetail.tradein.l.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        public class ViewOnClickListenerC0160a implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            public final /* synthetic */ f f5392g;

            public ViewOnClickListenerC0160a(f fVar) {
                this.f5392g = fVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialogInterface.OnClickListener onClickListener = a.this.f5391e;
                if (onClickListener != null) {
                    onClickListener.onClick(this.f5392g, -1);
                }
            }
        }

        public a(Context context) {
            this.a = context;
        }

        public f a() {
            f fVar = new f(this.a, R.style.tradein_dialog_style);
            View inflate = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.tradein_widget_dialog_old_i, (ViewGroup) null);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.tradein_widget_dialog_old_i_layout);
            Button button = (Button) inflate.findViewById(R.id.tradein_widget_dialog_i_neg_button);
            button.setOnClickListener(new ViewOnClickListenerC0160a(fVar));
            ((TextView) inflate.findViewById(R.id.tradein_widget_dialog_old_title)).setText(this.b);
            button.setText(this.f5390c);
            linearLayout.removeAllViews();
            List<TradeInResultData.OldContentList> list = this.d;
            if (list != null && !list.isEmpty()) {
                Iterator<TradeInResultData.OldContentList> it = this.d.iterator();
                while (it.hasNext()) {
                    TradeInOldItem tradeInOldItem = (TradeInOldItem) LayoutInflater.from(this.a).inflate(R.layout.tradein_old_item, (ViewGroup) linearLayout, false);
                    tradeInOldItem.a(it.next());
                    linearLayout.addView(tradeInOldItem);
                }
            }
            fVar.addContentView(inflate, new RelativeLayout.LayoutParams(-1, -1));
            return fVar;
        }
    }

    public f(Context context, int i2) {
        super(context, i2);
    }
}
