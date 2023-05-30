package com.jd.lib.productdetail.tradein.result;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.l.f;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.Iterator;

/* loaded from: classes16.dex */
public class TradeInResultSubsidyView extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public TextView f5453g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f5454h;

    /* renamed from: i  reason: collision with root package name */
    public LinearLayout f5455i;

    /* renamed from: j  reason: collision with root package name */
    public TextView f5456j;

    /* renamed from: k  reason: collision with root package name */
    public TextView f5457k;

    /* renamed from: l  reason: collision with root package name */
    public LinearLayout f5458l;

    /* renamed from: m  reason: collision with root package name */
    public Drawable f5459m;

    /* renamed from: n  reason: collision with root package name */
    public Drawable f5460n;
    public Drawable o;

    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ TradeInResultData f5461g;

        /* renamed from: com.jd.lib.productdetail.tradein.result.TradeInResultSubsidyView$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        public class DialogInterfaceOnClickListenerC0162a implements DialogInterface.OnClickListener {
            public DialogInterfaceOnClickListenerC0162a(a aVar) {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        }

        public a(TradeInResultData tradeInResultData) {
            this.f5461g = tradeInResultData;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.a aVar = new f.a(TradeInResultSubsidyView.this.getContext());
            TradeInResultData.OldWareSubsidy oldWareSubsidy = this.f5461g.wareNewSubsidy.iContent4OldWareSubsidy;
            aVar.b = oldWareSubsidy.title;
            aVar.d = oldWareSubsidy.contentList;
            aVar.f5390c = oldWareSubsidy.buttonText;
            aVar.f5391e = new DialogInterfaceOnClickListenerC0162a(this);
            aVar.a().show();
        }
    }

    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ TradeInResultData f5463g;

        /* loaded from: classes16.dex */
        public class a implements DialogInterface.OnClickListener {
            public a(b bVar) {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        }

        public b(TradeInResultData tradeInResultData) {
            this.f5463g = tradeInResultData;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.a aVar = new f.a(TradeInResultSubsidyView.this.getContext());
            TradeInResultData.OldWareSubsidy oldWareSubsidy = this.f5463g.oldWareSubsidy.iContent4OldWareSubsidy;
            aVar.b = oldWareSubsidy.title;
            aVar.d = oldWareSubsidy.contentList;
            aVar.f5390c = oldWareSubsidy.buttonText;
            aVar.f5391e = new a(this);
            aVar.a().show();
        }
    }

    public TradeInResultSubsidyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Drawable drawable = getResources().getDrawable(R.drawable.tradein_icon_i);
        this.f5459m = drawable;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.f5459m.getIntrinsicHeight());
        Drawable drawable2 = getResources().getDrawable(R.drawable.tradein_icon_arrow_down_black);
        this.f5460n = drawable2;
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.f5460n.getIntrinsicHeight());
        Drawable drawable3 = getResources().getDrawable(R.drawable.tradein_icon_arrow_up_black);
        this.o = drawable3;
        drawable3.setBounds(0, 0, drawable3.getIntrinsicWidth(), this.o.getIntrinsicHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        if (this.f5458l.getVisibility() == 0) {
            this.f5458l.setVisibility(8);
            this.f5457k.setCompoundDrawables(null, null, this.f5460n, null);
            return;
        }
        this.f5458l.setVisibility(0);
        this.f5457k.setCompoundDrawables(null, null, this.o, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (this.f5455i.getVisibility() == 0) {
            this.f5455i.setVisibility(8);
            this.f5454h.setCompoundDrawables(null, null, this.f5460n, null);
            return;
        }
        this.f5455i.setVisibility(0);
        this.f5454h.setCompoundDrawables(null, null, this.o, null);
    }

    private void e(TradeInResultData tradeInResultData) {
        TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight;
        TradeInResultData.BarterText.BarterOperateEvent.Data data;
        TradeInResultData.TradeInFloorData tradeInFloorData = tradeInResultData.wareNewSubsidy;
        if (tradeInFloorData != null && !TextUtils.isEmpty(tradeInFloorData.text1) && (barterFloorRight = tradeInResultData.wareNewSubsidy.rightInfo) != null && !TextUtils.isEmpty(barterFloorRight.getText1())) {
            this.f5456j.setVisibility(0);
            this.f5457k.setVisibility(0);
            this.f5456j.setText(tradeInResultData.wareNewSubsidy.text1);
            this.f5457k.setText(tradeInResultData.wareNewSubsidy.rightInfo.getText1());
            if (tradeInResultData.wareNewSubsidy.iContent4OldWareSubsidy != null) {
                this.f5456j.setCompoundDrawables(null, null, this.f5459m, null);
                this.f5456j.setOnClickListener(new a(tradeInResultData));
            } else {
                this.f5456j.setCompoundDrawables(null, null, null, null);
                this.f5456j.setOnClickListener(null);
            }
            TradeInResultData.BarterText.BarterOperateEvent barterOperateEvent = tradeInResultData.wareNewSubsidy.rightInfo.text1.event;
            if (barterOperateEvent != null && (data = barterOperateEvent.data) != null && data.isOldWareSubsidyValid()) {
                this.f5458l.setVisibility(8);
                this.f5457k.setCompoundDrawables(null, null, this.f5460n, null);
                this.f5458l.removeAllViews();
                Iterator<TradeInResultData.TradeInFloorData> it = tradeInResultData.wareNewSubsidy.rightInfo.text1.event.data.oldWareSubsidyEventDataList.iterator();
                while (it.hasNext()) {
                    TradeInResultSubsidyDetailItem tradeInResultSubsidyDetailItem = (TradeInResultSubsidyDetailItem) LayoutInflater.from(getContext()).inflate(R.layout.tradein_result_subsidy_detail_item, (ViewGroup) this.f5458l, false);
                    tradeInResultSubsidyDetailItem.a(it.next());
                    this.f5458l.addView(tradeInResultSubsidyDetailItem);
                }
                this.f5457k.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.m
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TradeInResultSubsidyView.this.a(view);
                    }
                });
                return;
            }
            this.f5458l.setVisibility(8);
            this.f5457k.setCompoundDrawables(null, null, null, null);
            this.f5457k.setOnClickListener(null);
            return;
        }
        this.f5456j.setVisibility(8);
        this.f5457k.setVisibility(8);
        this.f5458l.setVisibility(8);
    }

    private void f(TradeInResultData tradeInResultData) {
        TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight;
        TradeInResultData.BarterText.BarterOperateEvent.Data data;
        TradeInResultData.TradeInFloorData tradeInFloorData = tradeInResultData.oldWareSubsidy;
        if (tradeInFloorData != null && !TextUtils.isEmpty(tradeInFloorData.text1) && (barterFloorRight = tradeInResultData.oldWareSubsidy.rightInfo) != null && !TextUtils.isEmpty(barterFloorRight.getText1())) {
            this.f5453g.setVisibility(0);
            this.f5454h.setVisibility(0);
            this.f5453g.setText(tradeInResultData.oldWareSubsidy.text1);
            this.f5454h.setText(tradeInResultData.oldWareSubsidy.rightInfo.getText1());
            if (tradeInResultData.oldWareSubsidy.iContent4OldWareSubsidy != null) {
                this.f5453g.setCompoundDrawables(null, null, this.f5459m, null);
                this.f5453g.setOnClickListener(new b(tradeInResultData));
            } else {
                this.f5453g.setCompoundDrawables(null, null, null, null);
                this.f5453g.setOnClickListener(null);
            }
            TradeInResultData.BarterText.BarterOperateEvent barterOperateEvent = tradeInResultData.oldWareSubsidy.rightInfo.text1.event;
            if (barterOperateEvent != null && (data = barterOperateEvent.data) != null && data.isOldWareSubsidyValid()) {
                this.f5455i.setVisibility(8);
                this.f5454h.setCompoundDrawables(null, null, this.f5460n, null);
                this.f5455i.removeAllViews();
                Iterator<TradeInResultData.TradeInFloorData> it = tradeInResultData.oldWareSubsidy.rightInfo.text1.event.data.oldWareSubsidyEventDataList.iterator();
                while (it.hasNext()) {
                    TradeInResultSubsidyDetailItem tradeInResultSubsidyDetailItem = (TradeInResultSubsidyDetailItem) LayoutInflater.from(getContext()).inflate(R.layout.tradein_result_subsidy_detail_item, (ViewGroup) this.f5455i, false);
                    tradeInResultSubsidyDetailItem.a(it.next());
                    this.f5455i.addView(tradeInResultSubsidyDetailItem);
                }
                this.f5454h.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.n
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TradeInResultSubsidyView.this.b(view);
                    }
                });
                return;
            }
            this.f5455i.setVisibility(8);
            this.f5454h.setCompoundDrawables(null, null, null, null);
            this.f5454h.setOnClickListener(null);
            return;
        }
        this.f5453g.setVisibility(8);
        this.f5454h.setVisibility(8);
        this.f5455i.setVisibility(8);
    }

    public void g(TradeInResultData tradeInResultData) {
        if (tradeInResultData == null) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        f(tradeInResultData);
        e(tradeInResultData);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5453g = (TextView) findViewById(R.id.tradein_result_subsidy_old_device_title);
        TextView textView = (TextView) findViewById(R.id.tradein_result_subsidy_old_device_value);
        this.f5454h = textView;
        FontsUtil.changeTextFont(textView);
        this.f5455i = (LinearLayout) findViewById(R.id.tradein_result_subsidy_old_device_value_detail);
        this.f5456j = (TextView) findViewById(R.id.tradein_result_subsidy_new_device_title);
        TextView textView2 = (TextView) findViewById(R.id.tradein_result_subsidy_new_device_value);
        this.f5457k = textView2;
        FontsUtil.changeTextFont(textView2);
        this.f5458l = (LinearLayout) findViewById(R.id.tradein_result_subsidy_new_device_value_detail);
    }
}
