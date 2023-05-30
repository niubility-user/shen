package com.jd.lib.productdetail.tradein.ways;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInDialogFragment;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jingdong.common.BaseActivity;
import java.util.ArrayList;

/* loaded from: classes16.dex */
public class TradeInRenewsWaysFragment extends Fragment {

    /* renamed from: g  reason: collision with root package name */
    public TradeInDialogFragment f5605g;

    /* renamed from: h  reason: collision with root package name */
    public TradeInResultData.TradeInWareCardInfo f5606h;

    /* renamed from: i  reason: collision with root package name */
    public TradeInViewModel f5607i;

    /* loaded from: classes16.dex */
    public static final class a extends RecyclerView.ViewHolder {
        public final TextView a;
        public final TextView b;

        /* renamed from: c  reason: collision with root package name */
        public final View f5608c;

        public a(@NonNull View view) {
            super(view);
            this.f5608c = view.findViewById(R.id.tradein_renews_item_container);
            this.a = (TextView) view.findViewById(R.id.tradein_renew_ways_title);
            this.b = (TextView) view.findViewById(R.id.tradein_renew_ways_des);
        }
    }

    public TradeInRenewsWaysFragment(BaseActivity baseActivity, TradeInDialogFragment tradeInDialogFragment) {
        super(R.layout.tradein_renew_ways_fragment_root);
        this.f5605g = tradeInDialogFragment;
    }

    public static /* synthetic */ void a(View view) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f5607i = (TradeInViewModel) new ViewModelProvider(this.f5605g).get(TradeInViewModel.class);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        TradeInResultData.TradeInFloorData tradeInFloorData;
        TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight;
        TradeInResultData.BarterText barterText;
        TradeInResultData.BarterText.BarterOperateEvent barterOperateEvent;
        TradeInResultData.BarterText.BarterOperateEvent.Data data;
        ArrayList<TradeInResultData.BarterText.BarterOperateEvent.Data.ReplacementModeList> arrayList;
        super.onViewCreated(view, bundle);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.ways.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInRenewsWaysFragment.a(view2);
            }
        });
        if (getArguments() == null) {
            return;
        }
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = (TradeInResultData.TradeInWareCardInfo) getArguments().getSerializable("key.oldWare");
        this.f5606h = tradeInWareCardInfo;
        if (tradeInWareCardInfo == null || (tradeInFloorData = tradeInWareCardInfo.exchangeWareWay) == null || (barterFloorRight = tradeInFloorData.rightInfo) == null || (barterText = barterFloorRight.text1) == null || (barterOperateEvent = barterText.event) == null || (data = barterOperateEvent.data) == null || (arrayList = data.replacementModeList) == null) {
            return;
        }
        int i2 = barterText.replacementMode;
        int i3 = barterText.subReplacementMode;
        if (arrayList.size() == 0) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.tradein_renew_ways_tab);
        recyclerView.addItemDecoration(new com.jd.lib.productdetail.tradein.j.a(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new com.jd.lib.productdetail.tradein.j.b(this, arrayList, i2, i3));
    }
}
