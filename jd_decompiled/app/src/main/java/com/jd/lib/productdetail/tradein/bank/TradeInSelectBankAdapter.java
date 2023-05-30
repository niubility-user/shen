package com.jd.lib.productdetail.tradein.bank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.bank.TradeInSelectBankResp;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeInSelectBankAdapter extends RecyclerView.Adapter<a> {
    public final List<TradeInSelectBankResp.Data.BankCardItem> a;
    public Action1<TradeInSelectBankResp.Data.BankCardItem> b;

    /* loaded from: classes16.dex */
    public static class a extends RecyclerView.ViewHolder {
        public final SimpleDraweeView a;
        public final TextView b;

        /* renamed from: c  reason: collision with root package name */
        public final ImageView f5267c;

        public a(@NonNull View view) {
            super(view);
            this.a = (SimpleDraweeView) view.findViewById(R.id.trandin_select_bank_photo);
            this.b = (TextView) view.findViewById(R.id.tradein_select_bank_name);
            this.f5267c = (ImageView) view.findViewById(R.id.trandin_select_bank_icon);
        }
    }

    public TradeInSelectBankAdapter(List<TradeInSelectBankResp.Data.BankCardItem> list) {
        this.a = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(TradeInSelectBankResp.Data.BankCardItem bankCardItem, View view) {
        Action1<TradeInSelectBankResp.Data.BankCardItem> action1 = this.b;
        if (action1 != null) {
            action1.call(bankCardItem);
        }
    }

    @NonNull
    public a a(@NonNull ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_bank_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<TradeInSelectBankResp.Data.BankCardItem> list = this.a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        final TradeInSelectBankResp.Data.BankCardItem bankCardItem = this.a.get(i2);
        JDImageUtils.displayImage(bankCardItem.bankLogo, aVar.a, JDDisplayImageOptions.createSimple());
        if (bankCardItem.type == 2) {
            aVar.b.setText(bankCardItem.bankName);
            aVar.f5267c.setImageResource(R.drawable.tradein_arrow_right);
        } else {
            aVar.b.setText(String.format("%s(%s)", bankCardItem.bankName, bankCardItem.cardNoEnd));
            if (bankCardItem.selected == 1) {
                aVar.f5267c.setImageResource(R.drawable.tradein_select_bank_icon);
            } else {
                aVar.f5267c.setImageBitmap(null);
            }
        }
        aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.bank.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInSelectBankAdapter.this.l(bankCardItem, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
