package com.jd.lib.productdetail.tradein.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.widget.TradeInRecycleview;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes16.dex */
public class TradeInPropIDialog extends Dialog {

    /* loaded from: classes16.dex */
    public static class TradeInPropIAdapter extends RecyclerView.Adapter<a> {
        public Context a;
        public String[] b;

        /* loaded from: classes16.dex */
        public class a extends RecyclerView.ViewHolder {
            public SimpleDraweeView a;

            public a(@NonNull TradeInPropIAdapter tradeInPropIAdapter, View view) {
                super(view);
                this.a = (SimpleDraweeView) view;
            }
        }

        public TradeInPropIAdapter(Context context, String[] strArr) {
            this.a = context;
            this.b = strArr;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            String[] strArr = this.b;
            if (strArr == null || strArr.length <= 0) {
                return 0;
            }
            return strArr.length;
        }

        @NonNull
        public a h() {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.a);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            simpleDraweeView.setAdjustViewBounds(true);
            simpleDraweeView.setLayoutParams(layoutParams);
            return new a(this, simpleDraweeView);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull a aVar, int i2) {
            JDImageUtils.displayImage(this.b[i2], aVar.a);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public /* bridge */ /* synthetic */ a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            return h();
        }
    }

    /* loaded from: classes16.dex */
    public static class a {
        public Context a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f5638c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public DialogInterface.OnClickListener f5639e;

        /* renamed from: com.jd.lib.productdetail.tradein.widget.TradeInPropIDialog$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        public class C0163a implements TradeInRecycleview.b {
            public final /* synthetic */ LinearLayout a;

            public C0163a(a aVar, LinearLayout linearLayout) {
                this.a = linearLayout;
            }
        }

        /* loaded from: classes16.dex */
        public class b implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            public final /* synthetic */ TradeInPropIDialog f5640g;

            public b(TradeInPropIDialog tradeInPropIDialog) {
                this.f5640g = tradeInPropIDialog;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialogInterface.OnClickListener onClickListener = a.this.f5639e;
                if (onClickListener != null) {
                    onClickListener.onClick(this.f5640g, -1);
                }
            }
        }

        public a(Context context) {
            this.a = context;
        }

        public TradeInPropIDialog a() {
            TradeInPropIDialog tradeInPropIDialog = new TradeInPropIDialog(this.a, R.style.tradein_dialog_style);
            View inflate = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.tradein_widget_dialog_prop_i, (ViewGroup) null);
            TradeInRecycleview tradeInRecycleview = (TradeInRecycleview) inflate.findViewById(R.id.tradein_widget_dialog_prop_i_imgs);
            TextView textView = (TextView) inflate.findViewById(R.id.tradein_widget_dialog_prop_i_title);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.tradein_widget_dialog_prop_i_ind);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tradein_widget_dialog_prop_i_tip);
            tradeInRecycleview.b(new C0163a(this, linearLayout));
            ((ImageView) inflate.findViewById(R.id.tradein_widget_dialog_prop_i_btn_close)).setOnClickListener(new b(tradeInPropIDialog));
            if (!TextUtils.isEmpty(this.b)) {
                textView.setVisibility(0);
                textView.setText(this.b);
            } else {
                textView.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.d)) {
                String[] split = this.d.split(";");
                tradeInRecycleview.setAdapter(new TradeInPropIAdapter(this.a, split));
                for (int i2 = 0; i2 < split.length; i2++) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(PDUtils.dip2px(15.0f), PDUtils.dip2px(3.0f));
                    View view = new View(this.a);
                    if (i2 == 0) {
                        view.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
                    } else {
                        view.setBackgroundColor(Color.parseColor("#F2F2F2"));
                    }
                    linearLayout.addView(view, layoutParams);
                }
                tradeInRecycleview.setVisibility(0);
                linearLayout.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
                tradeInRecycleview.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.f5638c)) {
                textView2.setVisibility(0);
                textView2.setText(this.f5638c);
            } else {
                textView2.setVisibility(8);
            }
            tradeInPropIDialog.addContentView(inflate, new RelativeLayout.LayoutParams(-1, -1));
            return tradeInPropIDialog;
        }
    }

    public TradeInPropIDialog(Context context, int i2) {
        super(context, i2);
    }
}
