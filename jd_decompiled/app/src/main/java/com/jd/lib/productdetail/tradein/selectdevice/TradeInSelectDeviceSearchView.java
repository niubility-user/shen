package com.jd.lib.productdetail.tradein.selectdevice;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.core.util.Pair;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInAddressInfo;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.g.i;
import com.jd.lib.productdetail.tradein.g.j;
import com.jd.lib.productdetail.tradein.g.k;
import com.jd.lib.productdetail.tradein.g.l;
import com.jd.lib.productdetail.tradein.g.m;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.widget.TradeinErrorView;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeInSelectDeviceSearchView extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public boolean f5528g;

    /* renamed from: h  reason: collision with root package name */
    public EditText f5529h;

    /* renamed from: i  reason: collision with root package name */
    public View f5530i;

    /* renamed from: j  reason: collision with root package name */
    public TextView f5531j;

    /* renamed from: k  reason: collision with root package name */
    public RecyclerView f5532k;

    /* renamed from: l  reason: collision with root package name */
    public TradeInSelectSearchIndexAdapter f5533l;

    /* renamed from: m  reason: collision with root package name */
    public TradeInViewModel f5534m;

    /* renamed from: n  reason: collision with root package name */
    public TradeInSelectDeviceFragment f5535n;
    public Pair<String, String> o;
    public TradeinErrorView p;
    public View q;
    public String r;
    public TradeInSelectDeviceData.Data.TagsInfo.TagItem s;
    public MutableLiveData<Pair<String, String>> t;
    public MutableLiveData<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data.InquiriesInfo>> u;
    public Action1<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> v;
    public Observer w;
    public int x;
    public int y;

    /* loaded from: classes16.dex */
    public class a implements Observer<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data.InquiriesInfo>> {
        public a() {
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data.InquiriesInfo> result) {
            List<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> list;
            boolean z;
            boolean z2;
            int i2;
            PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data.InquiriesInfo> result2 = result;
            if (result2 != null) {
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FETCHING) {
                    TradeInSelectDeviceSearchView.c(TradeInSelectDeviceSearchView.this, true, false, false);
                    return;
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                    TradeInSelectDeviceData.Data.InquiriesInfo inquiriesInfo = result2.mData;
                    if (inquiriesInfo != null) {
                        TradeInSelectDeviceData.Data.InquiriesInfo inquiriesInfo2 = inquiriesInfo;
                        int i3 = inquiriesInfo2.pageNo;
                        boolean z3 = i3 == 1;
                        int i4 = inquiriesInfo2.pageSize;
                        boolean z4 = (i4 == 0 || i3 == 0 || (i2 = inquiriesInfo2.totalNumber) == 0 || i4 * i3 >= i2) ? false : true;
                        ArrayList<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> arrayList = inquiriesInfo2.oldProductInquiries;
                        if (arrayList != null && arrayList.size() > 0) {
                            TradeInSelectSearchIndexAdapter tradeInSelectSearchIndexAdapter = TradeInSelectDeviceSearchView.this.f5533l;
                            if (tradeInSelectSearchIndexAdapter != null) {
                                ArrayList<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> arrayList2 = result2.mData.oldProductInquiries;
                                if (z3) {
                                    tradeInSelectSearchIndexAdapter.b.clear();
                                }
                                tradeInSelectSearchIndexAdapter.a = z4;
                                tradeInSelectSearchIndexAdapter.b.addAll(arrayList2);
                                tradeInSelectSearchIndexAdapter.notifyDataSetChanged();
                            }
                            z = true;
                        } else {
                            z = false;
                            z2 = true;
                            TradeInSelectDeviceSearchView.c(TradeInSelectDeviceSearchView.this, false, !z, z2);
                            return;
                        }
                    } else {
                        z = false;
                    }
                    z2 = false;
                    TradeInSelectDeviceSearchView.c(TradeInSelectDeviceSearchView.this, false, !z, z2);
                    return;
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                    TradeInSelectDeviceSearchView.c(TradeInSelectDeviceSearchView.this, false, true, false);
                    return;
                } else {
                    return;
                }
            }
            TradeInSelectSearchIndexAdapter tradeInSelectSearchIndexAdapter2 = TradeInSelectDeviceSearchView.this.f5533l;
            if (tradeInSelectSearchIndexAdapter2 == null || (list = tradeInSelectSearchIndexAdapter2.b) == null) {
                return;
            }
            list.clear();
            TradeInSelectDeviceSearchView.this.f5533l.notifyDataSetChanged();
        }
    }

    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInSelectDeviceSearchView.this.a();
        }
    }

    /* loaded from: classes16.dex */
    public class c extends RecyclerView.OnScrollListener {
        public c() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
            if (i2 == 0 && recyclerView.getAdapter() != null) {
                if (TradeInSelectDeviceSearchView.this.u.getValue() == null || TradeInSelectDeviceSearchView.this.u.getValue().mStatus != PdBaseProtocolLiveData.Result.DataStatus.FETCHING) {
                    RecyclerView.Adapter adapter = TradeInSelectDeviceSearchView.this.f5532k.getAdapter();
                    if (adapter instanceof TradeInSelectSearchIndexAdapter) {
                        TradeInSelectSearchIndexAdapter tradeInSelectSearchIndexAdapter = (TradeInSelectSearchIndexAdapter) adapter;
                        if (tradeInSelectSearchIndexAdapter.a) {
                            RecyclerView.LayoutManager layoutManager = TradeInSelectDeviceSearchView.this.f5532k.getLayoutManager();
                            if ((layoutManager instanceof LinearLayoutManager) && ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() == tradeInSelectSearchIndexAdapter.getItemCount() - 1) {
                                TradeInSelectDeviceSearchView.b(TradeInSelectDeviceSearchView.this, true);
                            }
                        }
                    }
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class d implements TextView.OnEditorActionListener {
        public d() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (i2 == 2 || i2 == 3) {
                ((InputMethodManager) TradeInSelectDeviceSearchView.this.f5529h.getContext().getSystemService("input_method")).hideSoftInputFromWindow(TradeInSelectDeviceSearchView.this.f5529h.getWindowToken(), 2);
                TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView = TradeInSelectDeviceSearchView.this;
                tradeInSelectDeviceSearchView.r = tradeInSelectDeviceSearchView.f5529h.getText().toString();
                if (TextUtils.isEmpty(TradeInSelectDeviceSearchView.this.r.trim())) {
                    return false;
                }
                TradeInSelectDeviceSearchView.b(TradeInSelectDeviceSearchView.this, false);
                TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView2 = TradeInSelectDeviceSearchView.this;
                tradeInSelectDeviceSearchView2.u.observe(tradeInSelectDeviceSearchView2.f5535n.getViewLifecycleOwner(), TradeInSelectDeviceSearchView.this.w);
                return true;
            }
            return false;
        }
    }

    public TradeInSelectDeviceSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5528g = false;
        this.u = new MutableLiveData<>();
        this.w = new a();
        this.y = 20;
    }

    public static void b(TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView, boolean z) {
        if (!z) {
            tradeInSelectDeviceSearchView.u.setValue(null);
        }
        int i2 = tradeInSelectDeviceSearchView.y;
        int i3 = z ? 1 + tradeInSelectDeviceSearchView.x : 1;
        TradeInViewModel tradeInViewModel = tradeInSelectDeviceSearchView.f5534m;
        String str = tradeInSelectDeviceSearchView.r;
        TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem = tradeInSelectDeviceSearchView.s;
        tradeInViewModel.getClass();
        HashMap hashMap = new HashMap();
        hashMap.put("operateType", 5);
        hashMap.put("skuId", tradeInViewModel.f5253e);
        hashMap.put("bizCode", Integer.valueOf(tradeInViewModel.f5255g));
        hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
        hashMap.put("settleType", Integer.valueOf(tradeInViewModel.f5258j));
        hashMap.put("pageNo", Integer.valueOf(i3));
        hashMap.put("pageSize", Integer.valueOf(i2));
        hashMap.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
        hashMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        hashMap.put("extend", tradeInViewModel.t);
        if (!TextUtils.isEmpty(tradeInViewModel.o)) {
            hashMap.put("qualificationId", tradeInViewModel.o);
        }
        JDJSONObject jDJSONObject = tradeInViewModel.s;
        if (jDJSONObject != null) {
            hashMap.put("extension", jDJSONObject);
        }
        TradeInAddressInfo c2 = tradeInViewModel.c();
        if (c2 != null) {
            hashMap.put("addressInfo", c2);
        }
        if (tagItem != null) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("tagId", tagItem.tagId);
            hashMap2.put("tagType", tagItem.tagType + "");
            hashMap.put("tagInfo", hashMap2);
        }
        hashMap.put("keyword", str);
        i iVar = new i(hashMap);
        iVar.request(tradeInViewModel.b);
        MutableLiveData<PdBaseProtocolLiveData.Result<T>> mutableLiveData = iVar.mResult;
        mutableLiveData.observe(tradeInSelectDeviceSearchView.f5535n.getViewLifecycleOwner(), new m(tradeInSelectDeviceSearchView, mutableLiveData, z));
    }

    public static void c(TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView, boolean z, boolean z2, boolean z3) {
        View view;
        if (tradeInSelectDeviceSearchView.p == null || (view = tradeInSelectDeviceSearchView.q) == null) {
            return;
        }
        if (z) {
            tradeInSelectDeviceSearchView.f5532k.setVisibility(8);
            tradeInSelectDeviceSearchView.p.setVisibility(8);
            tradeInSelectDeviceSearchView.q.setVisibility(0);
            return;
        }
        view.setVisibility(8);
        if (z2) {
            tradeInSelectDeviceSearchView.f5532k.setVisibility(8);
            tradeInSelectDeviceSearchView.p.setVisibility(0);
            if (!NetUtils.isNetworkAvailable()) {
                tradeInSelectDeviceSearchView.p.a(TradeinErrorView.a.NONET);
                tradeInSelectDeviceSearchView.p.f5654i.setOnClickListener(new l(tradeInSelectDeviceSearchView));
                return;
            } else if (z3) {
                tradeInSelectDeviceSearchView.p.a(TradeinErrorView.a.EMPTY);
                tradeInSelectDeviceSearchView.p.f5654i.setOnClickListener(new j(tradeInSelectDeviceSearchView));
                return;
            } else {
                tradeInSelectDeviceSearchView.p.a(TradeinErrorView.a.UNKNOWN);
                tradeInSelectDeviceSearchView.p.f5654i.setOnClickListener(new k(tradeInSelectDeviceSearchView));
                return;
            }
        }
        tradeInSelectDeviceSearchView.p.setVisibility(8);
        tradeInSelectDeviceSearchView.f5532k.setVisibility(0);
    }

    public void a() {
        if (this.f5528g) {
            this.f5528g = false;
            EditText editText = this.f5529h;
            if (editText != null) {
                try {
                    InputMethodManager inputMethodManager = (InputMethodManager) JdSdk.getInstance().getApplication().getSystemService("input_method");
                    if (inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    }
                } catch (Exception unused) {
                }
            }
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(-1, -2);
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = PDUtils.dip2px(16.0f);
            layoutParams.topToBottom = R.id.tradein_selectdevice_hint_layout;
            setLayoutParams(layoutParams);
            this.t.setValue(this.o);
            this.f5530i.setVisibility(0);
            this.f5532k.setVisibility(8);
            this.f5531j.setVisibility(8);
            this.p.setVisibility(8);
            this.q.setVisibility(8);
            this.f5529h.setText("");
            this.r = "";
            this.u.setValue(null);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.q = findViewById(R.id.tradein_selectdevice_search_loading);
        this.p = (TradeinErrorView) findViewById(R.id.tradein_selectdevice_search_error_view);
        this.f5529h = (EditText) findViewById(R.id.tradein_selectdevice_search_input);
        this.f5530i = findViewById(R.id.tradein_selectdevice_search_device_click_area);
        this.f5531j = (TextView) findViewById(R.id.tradein_selectdevice_search_btn_cancel);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tradein_selectdevice_search_device_list);
        this.f5532k = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.f5532k.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.f5531j.setOnClickListener(new b());
        this.f5532k.addOnScrollListener(new c());
        this.f5529h.setOnEditorActionListener(new d());
    }

    @Override // android.view.View
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.f5530i.setOnClickListener(onClickListener);
    }
}
