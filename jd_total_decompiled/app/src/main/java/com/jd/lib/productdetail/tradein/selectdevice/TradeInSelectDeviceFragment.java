package com.jd.lib.productdetail.tradein.selectdevice;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Constraints;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInAddressInfo;
import com.jd.lib.productdetail.tradein.TradeInDialogFragment;
import com.jd.lib.productdetail.tradein.TradeInStep;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.widget.TradeinErrorView;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeInSelectDeviceFragment extends Fragment {
    public boolean A;
    public int B;
    public int C;
    public View D;
    public TradeInSelectDeviceData.Data.CategoryIdListForClap E;
    public MutableLiveData<Pair<String, String>> F;
    public MutableLiveData<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data.TagsInfo>> G;
    public MutableLiveData<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> H;
    public MutableLiveData<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data>> I;
    public MutableLiveData<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data>> J;
    public TradeInSelectDeviceDeviceAdapter K;
    public final Observer<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> L;
    public Observer M;
    public Observer N;
    public Observer O;

    /* renamed from: g */
    public TradeInViewModel f5503g;

    /* renamed from: h */
    public TradeInDialogFragment f5504h;

    /* renamed from: i */
    public View f5505i;

    /* renamed from: j */
    public TradeinErrorView f5506j;

    /* renamed from: k */
    public TradeInSelectDeviceSearchView f5507k;

    /* renamed from: l */
    public RecyclerView f5508l;

    /* renamed from: m */
    public View f5509m;

    /* renamed from: n */
    public TextView f5510n;
    public SimpleDraweeView o;
    public View p;
    public TextView q;
    public RecyclerView r;
    public View s;
    public View t;
    public View u;
    public RecyclerView v;
    public View w;
    public View x;
    public RecyclerView y;
    public boolean z;

    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        public a() {
            TradeInSelectDeviceFragment.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PDUtils.repeatClick() && TradeInSelectDeviceFragment.this.t() != null) {
                TradeInSelectDeviceFragment.this.f5509m.setVisibility(8);
                TradeInSelectDeviceFragment tradeInSelectDeviceFragment = TradeInSelectDeviceFragment.this;
                tradeInSelectDeviceFragment.f5507k.s = tradeInSelectDeviceFragment.t();
                TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView = TradeInSelectDeviceFragment.this.f5507k;
                if (!tradeInSelectDeviceSearchView.f5528g) {
                    Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(-1, 0);
                    ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = PDUtils.dip2px(16.0f);
                    layoutParams.topToBottom = R.id.tradein_selectdevice_hint_layout;
                    layoutParams.bottomToBottom = 0;
                    tradeInSelectDeviceSearchView.setLayoutParams(layoutParams);
                    tradeInSelectDeviceSearchView.f5528g = true;
                    tradeInSelectDeviceSearchView.f5530i.setVisibility(8);
                    tradeInSelectDeviceSearchView.f5532k.setVisibility(0);
                    tradeInSelectDeviceSearchView.f5531j.setVisibility(0);
                    tradeInSelectDeviceSearchView.o = tradeInSelectDeviceSearchView.t.getValue();
                    tradeInSelectDeviceSearchView.t.setValue(null);
                    TradeInSelectSearchIndexAdapter tradeInSelectSearchIndexAdapter = new TradeInSelectSearchIndexAdapter();
                    tradeInSelectDeviceSearchView.f5533l = tradeInSelectSearchIndexAdapter;
                    tradeInSelectSearchIndexAdapter.f5538c = tradeInSelectDeviceSearchView.v;
                    tradeInSelectDeviceSearchView.f5532k.setAdapter(tradeInSelectSearchIndexAdapter);
                    EditText editText = tradeInSelectDeviceSearchView.f5529h;
                    if (editText != null) {
                        try {
                            editText.requestFocus();
                            InputMethodManager inputMethodManager = (InputMethodManager) JdSdk.getInstance().getApplication().getSystemService("input_method");
                            if (inputMethodManager != null) {
                                inputMethodManager.showSoftInput(editText, 0);
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
                TradeInViewModel tradeInViewModel = TradeInSelectDeviceFragment.this.f5503g;
                if (tradeInViewModel != null) {
                    tradeInViewModel.e("Productdetail_yjhxChooseToastSearch", null);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {
        public b() {
            TradeInSelectDeviceFragment.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInSelectDeviceFragment.this.k();
        }
    }

    /* loaded from: classes16.dex */
    public class c implements View.OnClickListener {
        public c() {
            TradeInSelectDeviceFragment.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TradeInSelectDeviceFragment.this.t() != null) {
                TradeInSelectDeviceFragment tradeInSelectDeviceFragment = TradeInSelectDeviceFragment.this;
                TradeInSelectDeviceData.Data.TagsInfo.TagItem t = tradeInSelectDeviceFragment.t();
                MutableLiveData<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> mutableLiveData = TradeInSelectDeviceFragment.this.H;
                tradeInSelectDeviceFragment.e(t, mutableLiveData != null ? mutableLiveData.getValue() : null);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class d extends RecyclerView.OnScrollListener {
        public boolean a = false;
        public TradeInSelectDeviceData.Data.CategoriesInfo.CateItem b = null;

        public d() {
            TradeInSelectDeviceFragment.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
            TradeInSelectDeviceFragment tradeInSelectDeviceFragment;
            TradeInSelectDeviceDeviceAdapter tradeInSelectDeviceDeviceAdapter;
            String str = "onScrollStateChanged newState = " + (i2 == 1 ? "SCROLL_STATE_DRAGGING" : i2 == 2 ? "SCROLL_STATE_SETTLING" : "SCROLL_STATE_IDLE");
            if (i2 == 1) {
                this.a = true;
                this.b = TradeInSelectDeviceFragment.this.q();
            } else if (i2 == 0) {
                if (this.b != null && TradeInSelectDeviceFragment.this.q() != null && this.b == TradeInSelectDeviceFragment.this.q()) {
                    if (recyclerView.getAdapter() == null || (tradeInSelectDeviceDeviceAdapter = (tradeInSelectDeviceFragment = TradeInSelectDeviceFragment.this).K) == null || !tradeInSelectDeviceDeviceAdapter.f5500e) {
                        return;
                    }
                    RecyclerView.LayoutManager layoutManager = tradeInSelectDeviceFragment.y.getLayoutManager();
                    if (layoutManager instanceof LinearLayoutManager) {
                        if (((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() == TradeInSelectDeviceFragment.this.K.getItemCount() - 1) {
                            TradeInSelectDeviceFragment tradeInSelectDeviceFragment2 = TradeInSelectDeviceFragment.this;
                            tradeInSelectDeviceFragment2.d(tradeInSelectDeviceFragment2.q(), true);
                        }
                        this.a = false;
                        return;
                    }
                    return;
                }
                String str2 = "onScrollStateChanged mCurrentBrand = " + this.b + " getCurrentBrand() = " + TradeInSelectDeviceFragment.this.q() + " return...";
            }
        }
    }

    /* loaded from: classes16.dex */
    public class e implements Observer<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData>> {

        /* renamed from: g */
        public final /* synthetic */ MutableLiveData f5515g;

        public e(MutableLiveData mutableLiveData) {
            TradeInSelectDeviceFragment.this = r1;
            this.f5515g = mutableLiveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInSelectDeviceData> result) {
            TradeInSelectDeviceData.Data data;
            PdBaseProtocolLiveData.Result<TradeInSelectDeviceData> result2 = result;
            if (result2 != null) {
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
                if (dataStatus == dataStatus2) {
                    TradeInSelectDeviceData tradeInSelectDeviceData = result2.mData;
                    if (tradeInSelectDeviceData != null && (data = tradeInSelectDeviceData.data) != null) {
                        TradeInSelectDeviceData.Data.TagsInfo tagsInfo = data.tagsInfo;
                        if (tagsInfo != null && tagsInfo.isValid()) {
                            TradeInSelectDeviceFragment.this.G.setValue(new PdBaseProtocolLiveData.Result<>(dataStatus2, result2.mData.data.tagsInfo, ""));
                            MutableLiveData<Pair<String, String>> mutableLiveData = TradeInSelectDeviceFragment.this.F;
                            TradeInSelectDeviceData.Data data2 = result2.mData.data;
                            mutableLiveData.setValue(new Pair<>(data2.oldProductLimitCountText, data2.limitCountImg));
                            Iterator<TradeInSelectDeviceData.Data.TagsInfo.TagItem> it = result2.mData.data.tagsInfo.tagInfoList.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                TradeInSelectDeviceData.Data.TagsInfo.TagItem next = it.next();
                                if (next.selected) {
                                    TradeInSelectDeviceFragment.this.f(next, false);
                                    break;
                                }
                            }
                        } else {
                            TradeInSelectDeviceFragment.this.G.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, ""));
                        }
                        TradeInSelectDeviceData.Data.CategoryIdListForClap categoryIdListForClap = result2.mData.data.categoryIdListForClap;
                        if (categoryIdListForClap != null && categoryIdListForClap.isValid()) {
                            TradeInSelectDeviceFragment.this.I.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.SUCCESS, result2.mData.data, ""));
                        } else {
                            TradeInSelectDeviceData.Data.CategoriesInfo categoriesInfo = result2.mData.data.categoriesInfo;
                            if (categoriesInfo != null && categoriesInfo.isValid()) {
                                TradeInSelectDeviceFragment.this.I.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.SUCCESS, result2.mData.data, ""));
                                Iterator<TradeInSelectDeviceData.Data.CategoriesInfo.CateItem> it2 = result2.mData.data.categoriesInfo.categories.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    TradeInSelectDeviceData.Data.CategoriesInfo.CateItem next2 = it2.next();
                                    if (next2.selected) {
                                        TradeInSelectDeviceFragment.this.n(next2, false);
                                        break;
                                    }
                                }
                            } else {
                                TradeInSelectDeviceFragment.this.I.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, ""));
                            }
                            TradeInSelectDeviceData.Data.InquiriesInfo inquiriesInfo = result2.mData.data.inquiriesInfo;
                            if (inquiriesInfo != null && inquiriesInfo.isValid()) {
                                TradeInSelectDeviceFragment.this.J.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.SUCCESS, result2.mData.data, ""));
                            } else {
                                TradeInSelectDeviceFragment.this.J.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, ""));
                            }
                        }
                    } else {
                        TradeInSelectDeviceFragment.this.G.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, ""));
                    }
                    this.f5515g.removeObserver(this);
                    return;
                }
                PdBaseProtocolLiveData.Result.DataStatus dataStatus3 = PdBaseProtocolLiveData.Result.DataStatus.FAIL;
                if (dataStatus == dataStatus3) {
                    TradeInSelectDeviceFragment.this.G.setValue(new PdBaseProtocolLiveData.Result<>(dataStatus3, null, ""));
                    this.f5515g.removeObserver(this);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class f implements Observer<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData>> {

        /* renamed from: g */
        public final /* synthetic */ MutableLiveData f5517g;

        public f(MutableLiveData mutableLiveData) {
            TradeInSelectDeviceFragment.this = r1;
            this.f5517g = mutableLiveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInSelectDeviceData> result) {
            TradeInSelectDeviceData.Data data;
            PdBaseProtocolLiveData.Result<TradeInSelectDeviceData> result2 = result;
            if (result2 != null) {
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
                if (dataStatus == dataStatus2) {
                    TradeInSelectDeviceData tradeInSelectDeviceData = result2.mData;
                    if (tradeInSelectDeviceData != null && (data = tradeInSelectDeviceData.data) != null) {
                        TradeInSelectDeviceData.Data.CategoryIdListForClap categoryIdListForClap = data.categoryIdListForClap;
                        if (categoryIdListForClap != null && categoryIdListForClap.isValid()) {
                            TradeInSelectDeviceFragment.this.I.setValue(new PdBaseProtocolLiveData.Result<>(dataStatus2, result2.mData.data, ""));
                        } else {
                            TradeInSelectDeviceData.Data.CategoriesInfo categoriesInfo = result2.mData.data.categoriesInfo;
                            if (categoriesInfo != null && categoriesInfo.isValid()) {
                                TradeInSelectDeviceFragment.this.I.setValue(new PdBaseProtocolLiveData.Result<>(dataStatus2, result2.mData.data, ""));
                                Iterator<TradeInSelectDeviceData.Data.CategoriesInfo.CateItem> it = result2.mData.data.categoriesInfo.categories.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    TradeInSelectDeviceData.Data.CategoriesInfo.CateItem next = it.next();
                                    if (next.selected) {
                                        TradeInSelectDeviceFragment.this.n(next, false);
                                        break;
                                    }
                                }
                                TradeInSelectDeviceData.Data.InquiriesInfo inquiriesInfo = result2.mData.data.inquiriesInfo;
                                if (inquiriesInfo != null && inquiriesInfo.isValid()) {
                                    TradeInSelectDeviceFragment.this.J.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.SUCCESS, result2.mData.data, ""));
                                } else {
                                    TradeInSelectDeviceFragment.this.J.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, ""));
                                }
                            } else {
                                TradeInSelectDeviceFragment.this.I.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, ""));
                            }
                        }
                    } else {
                        TradeInSelectDeviceFragment.this.I.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, ""));
                    }
                    this.f5517g.removeObserver(this);
                    return;
                }
                PdBaseProtocolLiveData.Result.DataStatus dataStatus3 = PdBaseProtocolLiveData.Result.DataStatus.FAIL;
                if (dataStatus == dataStatus3) {
                    TradeInSelectDeviceFragment.this.I.setValue(new PdBaseProtocolLiveData.Result<>(dataStatus3, null, ""));
                    this.f5517g.removeObserver(this);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class g implements Observer<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> {
        public g() {
            TradeInSelectDeviceFragment.this = r1;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem cateItem) {
            TextView textView;
            TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem cateItem2 = cateItem;
            String str = "mCurrentCateObserver onChanged cateItem = " + cateItem2;
            if (cateItem2 != null && (textView = TradeInSelectDeviceFragment.this.q) != null) {
                if (cateItem2.categoryName == null) {
                    cateItem2.categoryName = "";
                }
                textView.setVisibility(0);
                SpannableString spannableString = new SpannableString(TradeInSelectDeviceFragment.this.getString(R.string.tradein_selectdevice_current_cate, cateItem2.categoryName));
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#Fa2c19")), 4, cateItem2.categoryName.length() + 4, 33);
                TradeInSelectDeviceFragment.this.q.setText(spannableString);
                TradeInSelectDeviceFragment.this.q.setOnClickListener(new com.jd.lib.productdetail.tradein.g.c(this, cateItem2));
                if (TradeInSelectDeviceFragment.this.f5503g != null) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("cid3", cateItem2.categoryId + "");
                    TradeInSelectDeviceFragment.this.f5503g.h("Productdetail_yjhxChooseoldCidExpo", jsonObject);
                    return;
                }
                return;
            }
            TextView textView2 = TradeInSelectDeviceFragment.this.q;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class h implements Observer<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData>> {

        /* renamed from: g */
        public final /* synthetic */ MutableLiveData f5520g;

        /* renamed from: h */
        public final /* synthetic */ boolean f5521h;

        public h(MutableLiveData mutableLiveData, boolean z) {
            TradeInSelectDeviceFragment.this = r1;
            this.f5520g = mutableLiveData;
            this.f5521h = z;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInSelectDeviceData> result) {
            TradeInSelectDeviceData.Data data;
            TradeInSelectDeviceData.Data.InquiriesInfo inquiriesInfo;
            PdBaseProtocolLiveData.Result<TradeInSelectDeviceData> result2 = result;
            if (result2 != null) {
                PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data> result3 = new PdBaseProtocolLiveData.Result<>(result2.mStatus, null, "");
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
                if (dataStatus == dataStatus2) {
                    TradeInSelectDeviceData tradeInSelectDeviceData = result2.mData;
                    if (tradeInSelectDeviceData != null && (data = tradeInSelectDeviceData.data) != null && (inquiriesInfo = data.inquiriesInfo) != null && inquiriesInfo.isValid()) {
                        result3 = new PdBaseProtocolLiveData.Result<>(dataStatus2, result2.mData.data, "");
                    } else {
                        result3 = new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, "");
                    }
                    this.f5520g.removeObserver(this);
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                    this.f5520g.removeObserver(this);
                }
                if (!this.f5521h || result3.mStatus == dataStatus2) {
                    TradeInSelectDeviceFragment.this.J.setValue(result3);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class i implements Observer<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data.TagsInfo>> {
        public i() {
            TradeInSelectDeviceFragment.this = r1;
        }

        /* JADX WARN: Removed duplicated region for block: B:120:0x008d  */
        /* JADX WARN: Removed duplicated region for block: B:121:0x009d  */
        @Override // androidx.lifecycle.Observer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data.TagsInfo> result) {
            List<TradeInSelectDeviceData.Data.TagsInfo.TagItem> list;
            PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data.TagsInfo> result2 = result;
            StringBuilder sb = new StringBuilder();
            sb.append("mSelectDeviceTags onChanged result = ");
            sb.append(result2 != null ? result2.mStatus : " null");
            sb.toString();
            boolean z = true;
            if (result2 != null) {
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FETCHING) {
                    TradeInSelectDeviceFragment.o(TradeInSelectDeviceFragment.this, true, false);
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                    TradeInSelectDeviceData.Data.TagsInfo tagsInfo = result2.mData;
                    if (tagsInfo != null && tagsInfo.isValid()) {
                        TradeInSelectDeviceFragment.o(TradeInSelectDeviceFragment.this, false, false);
                        TradeInSelectDeviceFragment tradeInSelectDeviceFragment = TradeInSelectDeviceFragment.this;
                        TradeInSelectDeviceData.Data.TagsInfo tagsInfo2 = result2.mData;
                        tradeInSelectDeviceFragment.getClass();
                        if (tagsInfo2 != null && (list = tagsInfo2.tagInfoList) != null && list.size() != 0) {
                            tradeInSelectDeviceFragment.q.setVisibility(8);
                            tradeInSelectDeviceFragment.f5508l.setAdapter(new TradeInSelectDeviceCateTagAdapter(tagsInfo2.tagInfoList, new com.jd.lib.productdetail.tradein.g.h(tradeInSelectDeviceFragment)));
                            try {
                                int currentIndex = tagsInfo2.getCurrentIndex();
                                if (currentIndex != -1) {
                                    tradeInSelectDeviceFragment.f5508l.scrollToPosition(currentIndex);
                                }
                            } catch (Exception unused) {
                            }
                        }
                        if (!z) {
                            TradeInSelectDeviceFragment tradeInSelectDeviceFragment2 = TradeInSelectDeviceFragment.this;
                            tradeInSelectDeviceFragment2.I.observe(tradeInSelectDeviceFragment2.getViewLifecycleOwner(), TradeInSelectDeviceFragment.this.N);
                            return;
                        }
                        TradeInSelectDeviceFragment tradeInSelectDeviceFragment3 = TradeInSelectDeviceFragment.this;
                        tradeInSelectDeviceFragment3.I.removeObserver(tradeInSelectDeviceFragment3.N);
                        return;
                    }
                    TradeInSelectDeviceFragment.o(TradeInSelectDeviceFragment.this, false, true);
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                    TradeInSelectDeviceFragment.o(TradeInSelectDeviceFragment.this, false, true);
                }
            }
            z = false;
            if (!z) {
            }
        }
    }

    /* loaded from: classes16.dex */
    public class j implements Observer<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data>> {
        public j() {
            TradeInSelectDeviceFragment.this = r1;
        }

        /* JADX WARN: Removed duplicated region for block: B:155:0x00c8  */
        /* JADX WARN: Removed duplicated region for block: B:156:0x00d8  */
        @Override // androidx.lifecycle.Observer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data> result) {
            TradeInSelectDeviceData.Data.CategoriesInfo categoriesInfo;
            List<TradeInSelectDeviceData.Data.CategoriesInfo.CateItem> list;
            TradeInSelectDeviceData.Data.CategoryIdListForClap categoryIdListForClap;
            ArrayList<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> arrayList;
            RecyclerView recyclerView;
            PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data> result2 = result;
            StringBuilder sb = new StringBuilder();
            sb.append("mBrandObserver onChanged result = ");
            sb.append(result2 != null ? result2.mStatus : " null");
            sb.toString();
            boolean z = true;
            if (result2 != null) {
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FETCHING) {
                    TradeInSelectDeviceFragment.j(TradeInSelectDeviceFragment.this, true, false, false);
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                    TradeInSelectDeviceData.Data data = result2.mData;
                    if (data != null && (categoryIdListForClap = data.categoryIdListForClap) != null && categoryIdListForClap.isValid()) {
                        TradeInSelectDeviceFragment tradeInSelectDeviceFragment = TradeInSelectDeviceFragment.this;
                        tradeInSelectDeviceFragment.E = result2.mData.categoryIdListForClap;
                        TradeInSelectDeviceFragment.j(tradeInSelectDeviceFragment, false, true, false);
                        TradeInSelectDeviceFragment tradeInSelectDeviceFragment2 = TradeInSelectDeviceFragment.this;
                        TradeInSelectDeviceData.Data.CategoryIdListForClap categoryIdListForClap2 = result2.mData.categoryIdListForClap;
                        tradeInSelectDeviceFragment2.getClass();
                        if (categoryIdListForClap2 != null && (arrayList = categoryIdListForClap2.categories) != null && arrayList.size() != 0 && (recyclerView = tradeInSelectDeviceFragment2.r) != null) {
                            recyclerView.setAdapter(new TradeInSelectDeviceCateAdapter(categoryIdListForClap2.categories, new com.jd.lib.productdetail.tradein.g.d(tradeInSelectDeviceFragment2)));
                        }
                    } else {
                        TradeInSelectDeviceData.Data data2 = result2.mData;
                        if (data2 != null && (categoriesInfo = data2.categoriesInfo) != null && categoriesInfo.isValid()) {
                            TradeInSelectDeviceFragment.j(TradeInSelectDeviceFragment.this, false, false, false);
                            TradeInSelectDeviceFragment tradeInSelectDeviceFragment3 = TradeInSelectDeviceFragment.this;
                            TradeInSelectDeviceData.Data.CategoriesInfo categoriesInfo2 = result2.mData.categoriesInfo;
                            tradeInSelectDeviceFragment3.getClass();
                            if (categoriesInfo2 != null && (list = categoriesInfo2.categories) != null && list.size() != 0) {
                                tradeInSelectDeviceFragment3.v.setAdapter(new TradeInSelectDeviceBrandAdapter(categoriesInfo2.categories, new com.jd.lib.productdetail.tradein.g.e(tradeInSelectDeviceFragment3)));
                            }
                            if (!z) {
                                TradeInSelectDeviceFragment tradeInSelectDeviceFragment4 = TradeInSelectDeviceFragment.this;
                                tradeInSelectDeviceFragment4.J.observe(tradeInSelectDeviceFragment4.getViewLifecycleOwner(), TradeInSelectDeviceFragment.this.O);
                                return;
                            }
                            TradeInSelectDeviceFragment tradeInSelectDeviceFragment5 = TradeInSelectDeviceFragment.this;
                            tradeInSelectDeviceFragment5.J.removeObserver(tradeInSelectDeviceFragment5.O);
                            return;
                        }
                        TradeInSelectDeviceFragment.j(TradeInSelectDeviceFragment.this, false, false, true);
                    }
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                    TradeInSelectDeviceFragment.j(TradeInSelectDeviceFragment.this, false, false, true);
                }
            }
            z = false;
            if (!z) {
            }
        }
    }

    /* loaded from: classes16.dex */
    public class k implements Observer<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data>> {
        public k() {
            TradeInSelectDeviceFragment.this = r1;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data> result) {
            TradeInSelectDeviceData.Data.devicesInfo devicesinfo;
            ArrayList<TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice> arrayList;
            PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data> result2 = result;
            StringBuilder sb = new StringBuilder();
            sb.append("mDeviceObserver onChanged result = ");
            sb.append(result2 != null ? result2.mStatus : " null");
            sb.toString();
            if (result2 != null) {
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FETCHING) {
                    TradeInSelectDeviceFragment.h(TradeInSelectDeviceFragment.this, true, false);
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                    TradeInSelectDeviceData.Data data = result2.mData;
                    if (data != null && data.inquiriesInfo.isValid()) {
                        TradeInSelectDeviceFragment.h(TradeInSelectDeviceFragment.this, false, false);
                        TradeInSelectDeviceFragment tradeInSelectDeviceFragment = TradeInSelectDeviceFragment.this;
                        TradeInSelectDeviceData.Data data2 = result2.mData;
                        tradeInSelectDeviceFragment.getClass();
                        if (data2 != null) {
                            TradeInSelectDeviceData.Data.InquiriesInfo inquiriesInfo = data2.inquiriesInfo;
                            if ((inquiriesInfo == null || !inquiriesInfo.isValid()) && ((devicesinfo = data2.devicesInfo) == null || (arrayList = devicesinfo.deviceInfoList) == null || arrayList.size() <= 0)) {
                                return;
                            }
                            TradeInSelectDeviceData.Data.InquiriesInfo inquiriesInfo2 = data2.inquiriesInfo;
                            int i2 = inquiriesInfo2.pageNo;
                            if (i2 != 1 && i2 != 0) {
                                boolean z = i2 == 1;
                                boolean z2 = inquiriesInfo2.pageSize * i2 < inquiriesInfo2.totalNumber;
                                TradeInSelectDeviceDeviceAdapter tradeInSelectDeviceDeviceAdapter = tradeInSelectDeviceFragment.K;
                                ArrayList<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> arrayList2 = inquiriesInfo2.oldProductInquiries;
                                if (z) {
                                    tradeInSelectDeviceDeviceAdapter.d.clear();
                                }
                                tradeInSelectDeviceDeviceAdapter.f5500e = z2;
                                tradeInSelectDeviceDeviceAdapter.d.addAll(arrayList2);
                                tradeInSelectDeviceDeviceAdapter.notifyDataSetChanged();
                                return;
                            }
                            TradeInSelectDeviceDeviceAdapter tradeInSelectDeviceDeviceAdapter2 = new TradeInSelectDeviceDeviceAdapter(inquiriesInfo2.oldProductInquiries, data2.devicesInfo);
                            tradeInSelectDeviceFragment.K = tradeInSelectDeviceDeviceAdapter2;
                            TradeInSelectDeviceData.Data.InquiriesInfo inquiriesInfo3 = data2.inquiriesInfo;
                            int i3 = inquiriesInfo3.pageSize;
                            int i4 = inquiriesInfo3.pageNo;
                            int i5 = i3 * i4;
                            int i6 = inquiriesInfo3.totalNumber;
                            tradeInSelectDeviceDeviceAdapter2.f5500e = i5 < i6;
                            if (i4 == 0 || i3 == 0 || i6 == 0) {
                                tradeInSelectDeviceDeviceAdapter2.f5500e = false;
                            }
                            tradeInSelectDeviceDeviceAdapter2.a = new com.jd.lib.productdetail.tradein.g.f(tradeInSelectDeviceFragment);
                            tradeInSelectDeviceFragment.y.setAdapter(tradeInSelectDeviceDeviceAdapter2);
                            return;
                        }
                        return;
                    }
                    TradeInSelectDeviceFragment.h(TradeInSelectDeviceFragment.this, false, true);
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                    TradeInSelectDeviceFragment.h(TradeInSelectDeviceFragment.this, false, true);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class l implements Observer<Pair<String, String>> {
        public l() {
            TradeInSelectDeviceFragment.this = r1;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(Pair<String, String> pair) {
            Pair<String, String> pair2 = pair;
            if (pair2 != null && !TextUtils.isEmpty(pair2.first)) {
                TradeInSelectDeviceFragment.this.f5509m.setVisibility(0);
                TradeInSelectDeviceFragment.this.f5510n.setText(pair2.first);
                JDImageLoader.display(pair2.second, TradeInSelectDeviceFragment.this.o);
                return;
            }
            TradeInSelectDeviceFragment.this.f5509m.setVisibility(8);
        }
    }

    /* loaded from: classes16.dex */
    public class m implements Action1<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> {
        public m() {
            TradeInSelectDeviceFragment.this = r1;
        }

        @Override // rx.functions.Action1
        public void call(TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries) {
            TradeInSelectDeviceFragment.g(TradeInSelectDeviceFragment.this, oldProductInquiries);
        }
    }

    /* loaded from: classes16.dex */
    public class n extends RecyclerView.ItemDecoration {
        public n(TradeInSelectDeviceFragment tradeInSelectDeviceFragment) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            rect.left = PDUtils.dip2px(10.0f);
            if (recyclerView.getAdapter() == null || recyclerView.getChildAdapterPosition(view) != recyclerView.getAdapter().getItemCount() - 1) {
                return;
            }
            rect.right = PDUtils.dip2px(10.0f);
        }
    }

    /* loaded from: classes16.dex */
    public class o extends RecyclerView.ItemDecoration {
        public o(TradeInSelectDeviceFragment tradeInSelectDeviceFragment, int i2) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            super.getItemOffsets(rect, view, recyclerView, state);
            if (recyclerView.getChildAdapterPosition(view) / 3 > 0) {
                rect.top = PDUtils.dip2px(8.0f);
            }
            rect.left = PDUtils.dip2px(4.0f);
            rect.right = PDUtils.dip2px(4.0f);
        }
    }

    public TradeInSelectDeviceFragment(BaseActivity baseActivity, TradeInDialogFragment tradeInDialogFragment) {
        super(R.layout.tradein_select_device_fragment_root);
        this.z = false;
        this.A = false;
        this.F = new MutableLiveData<>();
        this.G = new MutableLiveData<>();
        this.H = new MutableLiveData<>();
        this.I = new MutableLiveData<>();
        this.J = new MutableLiveData<>();
        this.L = new g();
        this.M = new i();
        this.N = new j();
        this.O = new k();
        this.f5504h = tradeInDialogFragment;
    }

    public /* synthetic */ void c(View view) {
        if (PDUtils.repeatClick()) {
            this.F.setValue(null);
        }
    }

    public static void g(TradeInSelectDeviceFragment tradeInSelectDeviceFragment, TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries) {
        if (tradeInSelectDeviceFragment.t() == null || tradeInSelectDeviceFragment.q() == null || oldProductInquiries == null) {
            return;
        }
        Bundle bundle = new Bundle();
        if (oldProductInquiries.leaf) {
            tradeInSelectDeviceFragment.D.setVisibility(0);
            tradeInSelectDeviceFragment.f5503g.b(tradeInSelectDeviceFragment.t(), tradeInSelectDeviceFragment.q(), oldProductInquiries, null, tradeInSelectDeviceFragment.B).observe(tradeInSelectDeviceFragment.getViewLifecycleOwner(), new com.jd.lib.productdetail.tradein.g.g(tradeInSelectDeviceFragment));
            return;
        }
        bundle.putSerializable("extra.key.old.device", oldProductInquiries);
        bundle.putSerializable("extra.key.old.device.tag", tradeInSelectDeviceFragment.t());
        bundle.putSerializable("extra.key.old.device.cate", tradeInSelectDeviceFragment.q());
        if (tradeInSelectDeviceFragment.A) {
            tradeInSelectDeviceFragment.A = false;
            tradeInSelectDeviceFragment.f5504h.moveToEstimatePage(bundle);
            return;
        }
        bundle.putInt("extra.key.estimate.type", tradeInSelectDeviceFragment.B);
        bundle.putBoolean("extra.key.from.tradein.page", tradeInSelectDeviceFragment.z);
        tradeInSelectDeviceFragment.f5504h.moveToStep(TradeInStep.ESTIMATE, bundle);
    }

    public static void h(TradeInSelectDeviceFragment tradeInSelectDeviceFragment, boolean z, boolean z2) {
        tradeInSelectDeviceFragment.getClass();
        String str = "showDeviceLoading show = " + z + " error = " + z2;
        View view = tradeInSelectDeviceFragment.w;
        if (view == null || tradeInSelectDeviceFragment.x == null) {
            return;
        }
        if (z) {
            tradeInSelectDeviceFragment.u(8);
            tradeInSelectDeviceFragment.x.setVisibility(8);
            tradeInSelectDeviceFragment.w.setVisibility(0);
            return;
        }
        view.setVisibility(8);
        if (z2) {
            tradeInSelectDeviceFragment.u(8);
            tradeInSelectDeviceFragment.x.setVisibility(0);
            return;
        }
        tradeInSelectDeviceFragment.x.setVisibility(8);
        tradeInSelectDeviceFragment.u(0);
    }

    public static void j(TradeInSelectDeviceFragment tradeInSelectDeviceFragment, boolean z, boolean z2, boolean z3) {
        tradeInSelectDeviceFragment.getClass();
        String str = "showBrandLoading show = " + z + " showCate = " + z2 + " error = " + z3;
        if (tradeInSelectDeviceFragment.s == null || tradeInSelectDeviceFragment.t == null) {
            return;
        }
        tradeInSelectDeviceFragment.w.setVisibility(8);
        tradeInSelectDeviceFragment.x.setVisibility(8);
        tradeInSelectDeviceFragment.y.setVisibility(8);
        if (z) {
            tradeInSelectDeviceFragment.l(8);
            tradeInSelectDeviceFragment.b(8);
            tradeInSelectDeviceFragment.t.setVisibility(8);
            tradeInSelectDeviceFragment.s.setVisibility(0);
            return;
        }
        tradeInSelectDeviceFragment.s.setVisibility(8);
        if (z3) {
            tradeInSelectDeviceFragment.b(8);
            tradeInSelectDeviceFragment.l(8);
            tradeInSelectDeviceFragment.t.setVisibility(0);
            return;
        }
        tradeInSelectDeviceFragment.t.setVisibility(8);
        if (z2) {
            try {
                if (tradeInSelectDeviceFragment.f5503g != null) {
                    JsonObject jsonObject = new JsonObject();
                    TradeInSelectDeviceData.Data.CategoryIdListForClap categoryIdListForClap = tradeInSelectDeviceFragment.E;
                    if (categoryIdListForClap != null && categoryIdListForClap.categories != null) {
                        JsonArray jsonArray = new JsonArray();
                        Iterator<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> it = tradeInSelectDeviceFragment.E.categories.iterator();
                        while (it.hasNext()) {
                            TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem next = it.next();
                            if (next != null) {
                                jsonArray.add(next.categoryId + "");
                            }
                        }
                        jsonObject.add("cid3", jsonArray);
                    }
                    tradeInSelectDeviceFragment.f5503g.h("Productdetail_yjhxAllCategoryExpo", jsonObject);
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
            tradeInSelectDeviceFragment.l(0);
            return;
        }
        tradeInSelectDeviceFragment.b(0);
    }

    public static /* synthetic */ void m(View view) {
    }

    public static void o(TradeInSelectDeviceFragment tradeInSelectDeviceFragment, boolean z, boolean z2) {
        tradeInSelectDeviceFragment.getClass();
        String str = "showContentLoading show = " + z + " error = " + z2;
        if (tradeInSelectDeviceFragment.f5506j == null || tradeInSelectDeviceFragment.f5505i == null) {
            return;
        }
        tradeInSelectDeviceFragment.s.setVisibility(8);
        tradeInSelectDeviceFragment.t.setVisibility(8);
        tradeInSelectDeviceFragment.v.setVisibility(8);
        tradeInSelectDeviceFragment.w.setVisibility(8);
        tradeInSelectDeviceFragment.x.setVisibility(8);
        tradeInSelectDeviceFragment.y.setVisibility(8);
        tradeInSelectDeviceFragment.q.setVisibility(8);
        tradeInSelectDeviceFragment.r.setVisibility(8);
        if (z) {
            tradeInSelectDeviceFragment.r(8);
            tradeInSelectDeviceFragment.f5506j.setVisibility(8);
            tradeInSelectDeviceFragment.f5505i.setVisibility(0);
            return;
        }
        tradeInSelectDeviceFragment.f5505i.setVisibility(8);
        if (z2) {
            tradeInSelectDeviceFragment.r(8);
            tradeInSelectDeviceFragment.f5506j.setVisibility(0);
            if (NetUtils.isNetworkAvailable()) {
                tradeInSelectDeviceFragment.f5506j.a(TradeinErrorView.a.UNKNOWN);
                return;
            } else {
                tradeInSelectDeviceFragment.f5506j.a(TradeinErrorView.a.NONET);
                return;
            }
        }
        tradeInSelectDeviceFragment.f5506j.setVisibility(8);
        tradeInSelectDeviceFragment.r(0);
    }

    public static /* synthetic */ void s(View view) {
    }

    public final void a() {
        this.f5507k.setOnClickListener(new a());
        this.p.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.selectdevice.f
            {
                TradeInSelectDeviceFragment.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInSelectDeviceFragment.this.c(view);
            }
        });
        TextView textView = this.f5506j.f5654i;
        if (textView != null) {
            textView.setOnClickListener(new b());
        }
        this.u.setOnClickListener(new c());
        this.y.addOnScrollListener(new d());
    }

    public final void b(int i2) {
        RecyclerView recyclerView = this.v;
        if (recyclerView != null) {
            recyclerView.setVisibility(i2);
        }
    }

    public final void d(TradeInSelectDeviceData.Data.CategoriesInfo.CateItem cateItem, boolean z) {
        if (t() != null) {
            int i2 = 1;
            if (z && this.J.getValue() != null && this.J.getValue().mData != null && this.J.getValue().mData.inquiriesInfo != null && this.J.getValue().mData.inquiriesInfo.pageNo > 0) {
                i2 = 1 + this.J.getValue().mData.inquiriesInfo.pageNo;
            }
            TradeInViewModel tradeInViewModel = this.f5503g;
            TradeInSelectDeviceData.Data.TagsInfo.TagItem t = t();
            tradeInViewModel.getClass();
            HashMap hashMap = new HashMap();
            hashMap.put("operateType", 3);
            hashMap.put("skuId", tradeInViewModel.f5253e);
            hashMap.put("bizCode", Integer.valueOf(tradeInViewModel.f5255g));
            hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
            hashMap.put("settleType", Integer.valueOf(tradeInViewModel.f5258j));
            hashMap.put("pageNo", Integer.valueOf(i2));
            hashMap.put("pageSize", 20);
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
            if (t != null) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("tagId", t.tagId);
                hashMap2.put("tagType", t.tagType + "");
                hashMap.put("tagInfo", hashMap2);
            }
            if (cateItem != null) {
                hashMap.put("categoryId", cateItem.categoryId);
                hashMap.put("categoryName", cateItem.categoryName);
                hashMap.put("ruleId", cateItem.ruleId);
            }
            com.jd.lib.productdetail.tradein.g.i iVar = new com.jd.lib.productdetail.tradein.g.i(hashMap);
            iVar.request(tradeInViewModel.b);
            MutableLiveData<PdBaseProtocolLiveData.Result<T>> mutableLiveData = iVar.mResult;
            mutableLiveData.observe(getViewLifecycleOwner(), new h(mutableLiveData, z));
        }
    }

    public final void e(TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem, TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem cateItem) {
        this.J.setValue(null);
        n(null, false);
        this.I.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FETCHING, null, ""));
        TradeInViewModel tradeInViewModel = this.f5503g;
        tradeInViewModel.getClass();
        HashMap hashMap = new HashMap();
        hashMap.put("operateType", 2);
        hashMap.put("skuId", tradeInViewModel.f5253e);
        hashMap.put("bizCode", Integer.valueOf(tradeInViewModel.f5255g));
        hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
        hashMap.put("settleType", Integer.valueOf(tradeInViewModel.f5258j));
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
        if (cateItem != null) {
            hashMap.put("operateType", 6);
            hashMap.put("categoryIdForClap", Long.valueOf(cateItem.categoryId));
        }
        com.jd.lib.productdetail.tradein.g.i iVar = new com.jd.lib.productdetail.tradein.g.i(hashMap);
        iVar.request(tradeInViewModel.b);
        MutableLiveData<PdBaseProtocolLiveData.Result<T>> mutableLiveData = iVar.mResult;
        mutableLiveData.observe(getViewLifecycleOwner(), new f(mutableLiveData));
    }

    public final void f(TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem, boolean z) {
        if (this.G.getValue() != null && this.G.getValue().mData != null) {
            t();
            this.G.getValue().mData.mCurrentTag = tagItem;
        }
        this.H.setValue(null);
        if (!z || tagItem == null) {
            return;
        }
        e(tagItem, null);
        if (this.f5503g != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("labelid", tagItem.tagId);
            this.f5503g.e("Productdetail_yjhxChooseToastTab", jsonObject);
        }
    }

    public final void k() {
        this.G.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FETCHING, null, ""));
        this.F.setValue(null);
        this.I.setValue(null);
        this.J.setValue(null);
        f(null, false);
        n(null, false);
        TradeInViewModel tradeInViewModel = this.f5503g;
        int i2 = this.C;
        tradeInViewModel.getClass();
        HashMap hashMap = new HashMap();
        hashMap.put("operateType", 1);
        hashMap.put("skuId", tradeInViewModel.f5253e);
        hashMap.put("bizCode", Integer.valueOf(tradeInViewModel.f5255g));
        hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
        hashMap.put("settleType", Integer.valueOf(tradeInViewModel.f5258j));
        hashMap.put("extend", tradeInViewModel.t);
        if (!TextUtils.isEmpty(tradeInViewModel.o)) {
            hashMap.put("qualificationId", tradeInViewModel.o);
        }
        if (i2 != 0) {
            hashMap.put("filterTagType", Integer.valueOf(i2));
        }
        JDJSONObject jDJSONObject = tradeInViewModel.s;
        if (jDJSONObject != null) {
            hashMap.put("extension", jDJSONObject);
        }
        TradeInAddressInfo c2 = tradeInViewModel.c();
        if (c2 != null) {
            hashMap.put("addressInfo", c2);
        }
        com.jd.lib.productdetail.tradein.g.i iVar = new com.jd.lib.productdetail.tradein.g.i(hashMap);
        iVar.request(tradeInViewModel.b);
        MutableLiveData<PdBaseProtocolLiveData.Result<T>> mutableLiveData = iVar.mResult;
        mutableLiveData.observe(getViewLifecycleOwner(), new e(mutableLiveData));
    }

    public final void l(int i2) {
        RecyclerView recyclerView = this.r;
        if (recyclerView != null) {
            recyclerView.setVisibility(i2);
        }
    }

    public final void n(TradeInSelectDeviceData.Data.CategoriesInfo.CateItem cateItem, boolean z) {
        TradeInSelectDeviceData.Data.CategoriesInfo.CateItem cateItem2;
        if (this.I.getValue() == null || this.I.getValue().mData == null || this.I.getValue().mData.categoriesInfo == null) {
            cateItem2 = null;
        } else {
            cateItem2 = q();
            this.I.getValue().mData.categoriesInfo.mCurrentCate = cateItem;
        }
        if (!z || cateItem == null || cateItem2 == cateItem) {
            return;
        }
        d(cateItem, false);
        if (this.f5503g != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("brand_name", cateItem.categoryName);
            this.f5503g.e("Productdetail_yjhxChooseToastLeftBanner", jsonObject);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f5503g = (TradeInViewModel) new ViewModelProvider(this.f5504h).get(TradeInViewModel.class);
        if (getArguments() != null) {
            this.z = getArguments().getBoolean("extra.key.from.tradein.page", false);
            this.A = getArguments().getBoolean("extra.key.from.estimate.page", false);
            this.B = getArguments().getInt("extra.key.estimate.type", 1);
            this.C = getArguments().getInt("extra.key.tag.type", 0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.selectdevice.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInSelectDeviceFragment.m(view2);
            }
        });
        v(view);
        a();
        k();
        this.F.observe(getViewLifecycleOwner(), new l());
        this.G.observe(getViewLifecycleOwner(), this.M);
        this.H.observe(getViewLifecycleOwner(), this.L);
    }

    public TradeInSelectDeviceData.Data.CategoriesInfo.CateItem q() {
        if (this.I.getValue() == null || this.I.getValue().mData == null || this.I.getValue().mData.categoriesInfo == null || this.I.getValue().mData.categoriesInfo.mCurrentCate == null) {
            return null;
        }
        return this.I.getValue().mData.categoriesInfo.mCurrentCate;
    }

    public final void r(int i2) {
        View view = this.f5509m;
        if (view != null) {
            view.setVisibility(i2);
        }
        TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView = this.f5507k;
        if (tradeInSelectDeviceSearchView != null) {
            tradeInSelectDeviceSearchView.setVisibility(i2);
        }
        RecyclerView recyclerView = this.f5508l;
        if (recyclerView != null) {
            recyclerView.setVisibility(i2);
        }
    }

    public TradeInSelectDeviceData.Data.TagsInfo.TagItem t() {
        if (this.G.getValue() == null || this.G.getValue().mData == null || this.G.getValue().mData.mCurrentTag == null) {
            return null;
        }
        return this.G.getValue().mData.mCurrentTag;
    }

    public final void u(int i2) {
        RecyclerView recyclerView = this.y;
        if (recyclerView != null) {
            recyclerView.setVisibility(i2);
        }
    }

    public final void v(View view) {
        this.f5505i = view.findViewById(R.id.tradein_selectdevice_loading);
        TradeinErrorView tradeinErrorView = (TradeinErrorView) view.findViewById(R.id.tradein_selectdevice_error_view);
        this.f5506j = tradeinErrorView;
        tradeinErrorView.a(TradeinErrorView.a.UNKNOWN);
        TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView = (TradeInSelectDeviceSearchView) view.findViewById(R.id.tradein_selectdevice_search_view);
        this.f5507k = tradeInSelectDeviceSearchView;
        tradeInSelectDeviceSearchView.f5534m = this.f5503g;
        tradeInSelectDeviceSearchView.f5535n = this;
        tradeInSelectDeviceSearchView.t = this.F;
        tradeInSelectDeviceSearchView.v = new m();
        this.q = (TextView) view.findViewById(R.id.tradein_selectdevice_current_cate);
        this.s = view.findViewById(R.id.tradein_selectdevice_brand_loading);
        View findViewById = view.findViewById(R.id.tradein_selectdevice_brand_error);
        this.t = findViewById;
        this.u = findViewById.findViewById(R.id.tradein_selectdevice_brand_error_btn_retry);
        this.w = view.findViewById(R.id.tradein_selectdevice_device_loading);
        this.x = view.findViewById(R.id.tradein_selectdevice_device_error);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.tradein_selectdevice_cate_tag);
        this.f5508l = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.f5508l.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.f5508l.addItemDecoration(new n(this));
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.tradein_selectdevice_cate_list);
        this.r = recyclerView2;
        recyclerView2.setHasFixedSize(true);
        this.r.setLayoutManager(new GridLayoutManager(getContext(), 3));
        this.r.addItemDecoration(new o(this, 3));
        RecyclerView recyclerView3 = (RecyclerView) view.findViewById(R.id.tradein_selectdevice_brand_list);
        this.v = recyclerView3;
        recyclerView3.setHasFixedSize(true);
        this.v.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        RecyclerView recyclerView4 = (RecyclerView) view.findViewById(R.id.tradein_selectdevice_device_list);
        this.y = recyclerView4;
        recyclerView4.setHasFixedSize(true);
        this.y.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        View findViewById2 = view.findViewById(R.id.tradein_selectdevice_hint_layout);
        this.f5509m = findViewById2;
        this.f5510n = (TextView) findViewById2.findViewById(R.id.tradein_selectdevice_hint_text);
        this.o = (SimpleDraweeView) this.f5509m.findViewById(R.id.tradein_selectdevice_hint_img);
        this.p = this.f5509m.findViewById(R.id.tradein_selectdevice_hint_btn_close);
        View findViewById3 = view.findViewById(R.id.tradein_select_device_function_loading);
        this.D = findViewById3;
        findViewById3.setVisibility(8);
        this.D.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.selectdevice.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInSelectDeviceFragment.s(view2);
            }
        });
    }
}
