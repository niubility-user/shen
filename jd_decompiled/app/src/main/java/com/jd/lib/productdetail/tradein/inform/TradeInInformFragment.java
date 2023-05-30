package com.jd.lib.productdetail.tradein.inform;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInDialogFragment;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.inform.TradeInInformData;
import com.jd.lib.productdetail.tradein.k.b;
import com.jd.lib.productdetail.tradein.widget.TradeInTitle;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes16.dex */
public class TradeInInformFragment extends Fragment {

    /* renamed from: g  reason: collision with root package name */
    public TradeInDialogFragment f5370g;

    /* renamed from: h  reason: collision with root package name */
    public TradeInViewModel f5371h;

    /* renamed from: i  reason: collision with root package name */
    public TradeInTitle f5372i;

    /* renamed from: j  reason: collision with root package name */
    public TextView f5373j;

    /* renamed from: k  reason: collision with root package name */
    public TextView f5374k;

    /* renamed from: l  reason: collision with root package name */
    public Button f5375l;

    /* renamed from: m  reason: collision with root package name */
    public TextView f5376m;

    /* renamed from: n  reason: collision with root package name */
    public TextView f5377n;
    public SimpleDraweeView o;
    public MutableLiveData<TradeInInformData.Data.TradeinInformInfo> p;

    /* loaded from: classes16.dex */
    public class a implements Observer<TradeInInformData.Data.TradeinInformInfo> {
        public a() {
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(TradeInInformData.Data.TradeinInformInfo tradeinInformInfo) {
            TradeInInformData.Data.TradeinInformInfo tradeinInformInfo2 = tradeinInformInfo;
            if (tradeinInformInfo2 == null || !tradeinInformInfo2.isValid()) {
                return;
            }
            TradeInInformFragment tradeInInformFragment = TradeInInformFragment.this;
            tradeInInformFragment.getClass();
            if (tradeinInformInfo2.isValid()) {
                tradeInInformFragment.f5372i.b(tradeinInformInfo2.ruleInfo);
                tradeInInformFragment.f5373j.setText(tradeinInformInfo2.oldProductText);
                tradeInInformFragment.f5374k.setText(tradeinInformInfo2.remindExchangeText);
                tradeInInformFragment.f5375l.setText(tradeinInformInfo2.chooseProductText);
                tradeInInformFragment.f5377n.setText(tradeinInformInfo2.servicePointText);
                JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                createSimple.setRoundingParams(new RoundingParams().setRoundAsCircle(true));
                createSimple.setPlaceholder(17);
                JDImageUtils.displayImage(tradeinInformInfo2.oldProductImageUrl, tradeInInformFragment.o, createSimple);
                TradeInInformData.Data.TradeinInformInfo.NoHaveLocalSubsidyInfo noHaveLocalSubsidyInfo = tradeinInformInfo2.noHaveLocalSubsidyInfo;
                if (noHaveLocalSubsidyInfo != null && !TextUtils.isEmpty(noHaveLocalSubsidyInfo.subsidyInfoText)) {
                    tradeInInformFragment.f5376m.setVisibility(0);
                    tradeInInformFragment.f5376m.setText(tradeinInformInfo2.noHaveLocalSubsidyInfo.subsidyInfoText);
                    return;
                }
                tradeInInformFragment.f5376m.setVisibility(8);
            }
        }
    }

    public TradeInInformFragment(BaseActivity baseActivity, TradeInDialogFragment tradeInDialogFragment) {
        super(R.layout.tradein_inform_fragment_root);
        this.p = new MutableLiveData<>();
        this.f5370g = tradeInDialogFragment;
    }

    public static /* synthetic */ void a(View view) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f5371h = (TradeInViewModel) new ViewModelProvider(this.f5370g).get(TradeInViewModel.class);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.p.setValue((TradeInInformData.Data.TradeinInformInfo) arguments.getSerializable("extra.params.key.inform.data"));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.p.getValue() != null) {
            this.p.observe(getViewLifecycleOwner(), new a());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.inform.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInInformFragment.a(view2);
            }
        });
        this.o = (SimpleDraweeView) view.findViewById(R.id.tradein_inform_content_img);
        this.f5372i = (TradeInTitle) view.findViewById(R.id.tradein_inform_title);
        this.f5373j = (TextView) view.findViewById(R.id.tradein_inform_content_title);
        this.f5374k = (TextView) view.findViewById(R.id.tradein_inform_content_sub_title);
        this.f5375l = (Button) view.findViewById(R.id.tradein_inform_content_btn_ok);
        this.f5376m = (TextView) view.findViewById(R.id.tradein_inform_content_btn_ok_tip);
        this.f5377n = (TextView) view.findViewById(R.id.tradein_inform_servicePointText);
        this.f5375l.setOnClickListener(new com.jd.lib.productdetail.tradein.k.a(this));
        this.f5372i.f5650j = new b(this);
    }
}
