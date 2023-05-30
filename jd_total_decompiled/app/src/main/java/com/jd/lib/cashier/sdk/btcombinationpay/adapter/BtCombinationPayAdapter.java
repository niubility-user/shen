package com.jd.lib.cashier.sdk.btcombinationpay.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.btcombinationpay.aac.viewmodel.BtCombinationPayViewModel;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.RequestParamSkuCouponInfo;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.RequestParamSkuPlanInfo;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.SplitSkuInfo;
import com.jd.lib.cashier.sdk.btcombinationpay.ui.BtCombinationPayItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class BtCombinationPayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private FragmentActivity a;
    private List<SplitSkuInfo> b;

    /* loaded from: classes14.dex */
    public class BtCombinationPayViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout a;
        private BtCombinationPayItemView b;

        /* renamed from: c  reason: collision with root package name */
        private AbsPayPlanView f2884c;
        private TextView d;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes14.dex */
        public class a implements OnPlanViewClickListener {
            final /* synthetic */ OnPlanViewClickListener a;

            a(BtCombinationPayViewHolder btCombinationPayViewHolder, OnPlanViewClickListener onPlanViewClickListener) {
                this.a = onPlanViewClickListener;
            }

            @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
            public void onClick(IPlanItemViewEntity iPlanItemViewEntity, IPlanItemViewEntity iPlanItemViewEntity2) {
                OnPlanViewClickListener onPlanViewClickListener = this.a;
                if (onPlanViewClickListener != null) {
                    onPlanViewClickListener.onClick(iPlanItemViewEntity, iPlanItemViewEntity2);
                }
            }
        }

        public BtCombinationPayViewHolder(@NonNull BtCombinationPayAdapter btCombinationPayAdapter, View view) {
            super(view);
            this.a = (LinearLayout) view.findViewById(R.id.lib_cashier_bt_combination_pay_list_layout_root);
            this.b = (BtCombinationPayItemView) view.findViewById(R.id.lib_cashier_bt_combination_pay_channel_info_item);
            this.f2884c = (AbsPayPlanView) view.findViewById(R.id.bt_combination_stub_pay_plan_view);
            this.d = (TextView) view.findViewById(R.id.lib_cashier_bt_combination_pay_channel_tip);
        }

        public void d() {
            TextView textView = this.d;
            if (textView != null) {
                textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_848383));
            }
            if (this.a != null) {
                if (JDDarkUtil.isDarkMode()) {
                    this.a.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_bt_combination_top_bottom_corner_dark_bg);
                } else {
                    this.a.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_bt_combination_top_bottom_corner_bg);
                }
            }
        }

        public void e(SplitSkuInfo splitSkuInfo) {
            BtCombinationPayItemView btCombinationPayItemView = this.b;
            if (btCombinationPayItemView == null || splitSkuInfo == null) {
                return;
            }
            btCombinationPayItemView.i(splitSkuInfo);
        }

        public void f(String str) {
            if (this.d == null) {
                return;
            }
            if (!TextUtils.isEmpty(str)) {
                this.d.setVisibility(0);
                this.d.setText(str);
                return;
            }
            this.d.setVisibility(8);
        }

        public void g(List<PlanFeeEntity> list, OnPlanViewClickListener onPlanViewClickListener) {
            if (list != null && list.size() > 0) {
                this.f2884c.setVisibility(0);
                this.f2884c.x(list, "");
                this.f2884c.G(new a(this, onPlanViewClickListener));
                return;
            }
            this.f2884c.setVisibility(8);
        }
    }

    /* loaded from: classes14.dex */
    class a implements OnPlanViewClickListener {
        final /* synthetic */ int a;

        a(int i2) {
            this.a = i2;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public void onClick(IPlanItemViewEntity iPlanItemViewEntity, IPlanItemViewEntity iPlanItemViewEntity2) {
            BtCombinationPayAdapter.this.h(iPlanItemViewEntity, iPlanItemViewEntity2, this.a);
        }
    }

    public BtCombinationPayAdapter(FragmentActivity fragmentActivity, List<SplitSkuInfo> list) {
        this.a = fragmentActivity;
        this.b = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(IPlanItemViewEntity iPlanItemViewEntity, IPlanItemViewEntity iPlanItemViewEntity2, int i2) {
        List<PlanFeeEntity> list;
        List<SplitSkuInfo> list2 = this.b;
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            RequestParamSkuPlanInfo requestParamSkuPlanInfo = new RequestParamSkuPlanInfo();
            SplitSkuInfo splitSkuInfo = this.b.get(i3);
            if (splitSkuInfo != null && (list = splitSkuInfo.baitiaoSkuPlanFeeList) != null && !list.isEmpty()) {
                ArrayList arrayList2 = new ArrayList();
                int i4 = 0;
                while (true) {
                    if (i4 >= splitSkuInfo.baitiaoSkuPlanFeeList.size()) {
                        break;
                    }
                    PlanFeeEntity planFeeEntity = splitSkuInfo.baitiaoSkuPlanFeeList.get(i4);
                    if (i2 == i3) {
                        requestParamSkuPlanInfo.currentPlan = iPlanItemViewEntity2 == null ? "" : iPlanItemViewEntity2.getPlanNum();
                        requestParamSkuPlanInfo.operationType = "2";
                        if (planFeeEntity != null && TextUtils.equals(iPlanItemViewEntity.getPlanNum(), planFeeEntity.getPlanNum())) {
                            requestParamSkuPlanInfo.targetPlan = planFeeEntity.getPlanNum();
                            if (planFeeEntity.getRecommendCoupon() != null) {
                                RequestParamSkuCouponInfo requestParamSkuCouponInfo = new RequestParamSkuCouponInfo();
                                requestParamSkuCouponInfo.actUuId = planFeeEntity.getRecommendCoupon() != null ? planFeeEntity.getRecommendCoupon().getActUuId() : "";
                                requestParamSkuCouponInfo.couponClass = planFeeEntity.getRecommendCoupon() != null ? planFeeEntity.getRecommendCoupon().getCouponClass() : "";
                                arrayList2.add(requestParamSkuCouponInfo);
                                requestParamSkuPlanInfo.targetCouponList = arrayList2;
                            }
                        }
                        i4++;
                    } else {
                        requestParamSkuPlanInfo.operationType = "0";
                        if (planFeeEntity != null && planFeeEntity.isChecked()) {
                            requestParamSkuPlanInfo.currentPlan = planFeeEntity.getPlanNum();
                            if (planFeeEntity.getRecommendCoupon() != null) {
                                RequestParamSkuCouponInfo requestParamSkuCouponInfo2 = new RequestParamSkuCouponInfo();
                                requestParamSkuCouponInfo2.actUuId = planFeeEntity.getRecommendCoupon() != null ? planFeeEntity.getRecommendCoupon().getActUuId() : "";
                                requestParamSkuCouponInfo2.couponClass = planFeeEntity.getRecommendCoupon() != null ? planFeeEntity.getRecommendCoupon().getCouponClass() : "";
                                arrayList2.add(requestParamSkuCouponInfo2);
                                requestParamSkuPlanInfo.targetCouponList = arrayList2;
                            }
                        }
                        i4++;
                    }
                }
            }
            if (splitSkuInfo != null) {
                requestParamSkuPlanInfo.btReqNo = splitSkuInfo.btReqNo;
            }
            arrayList.add(requestParamSkuPlanInfo);
        }
        ((BtCombinationPayViewModel) ViewModelProviders.of(this.a).get(BtCombinationPayViewModel.class)).f().c();
        ((BtCombinationPayViewModel) ViewModelProviders.of(this.a).get(BtCombinationPayViewModel.class)).g().c(this.a, arrayList, "1");
    }

    public List<SplitSkuInfo> getDataList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SplitSkuInfo> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        SplitSkuInfo splitSkuInfo;
        if (this.b == null || i2 > r0.size() - 1 || (splitSkuInfo = this.b.get(i2)) == null) {
            return;
        }
        BtCombinationPayViewHolder btCombinationPayViewHolder = (BtCombinationPayViewHolder) viewHolder;
        btCombinationPayViewHolder.e(splitSkuInfo);
        btCombinationPayViewHolder.g(splitSkuInfo.baitiaoSkuPlanFeeList, new a(i2));
        PlanServiceMap planServiceMap = splitSkuInfo.serviceMap;
        if (planServiceMap != null) {
            btCombinationPayViewHolder.f(planServiceMap.planServiceFeeStr);
        }
        btCombinationPayViewHolder.d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new BtCombinationPayViewHolder(this, LayoutInflater.from(this.a).inflate(R.layout.lib_cashier_sdk_bt_combination_pay_list_layout, viewGroup, false));
    }
}
