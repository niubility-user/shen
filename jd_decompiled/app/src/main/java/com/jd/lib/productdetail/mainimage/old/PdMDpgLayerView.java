package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.handmark.pulltorefresh.library.LoadMoreListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshLoadMoreRecyclerView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.PdDpgListMoreInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.protocol.PdDpgLayerDetailMoreProtocol;
import com.jd.lib.productdetail.core.protocol.PdDpgLayerDetailProtocol;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMDpgLayerAdapter;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PdMDpgLayerView extends RelativeLayout implements LifecycleObserver {
    public static final /* synthetic */ int M = 0;
    public TextView A;
    public TextView B;
    public TextView C;
    public Button D;
    public RecyclerView E;
    public PdMainImagePresenter F;
    public boolean G;
    public List<PdDpgListLayerInfo.DetailBean.ItemsBean> H;
    public List<PdDpgListLayerInfo.DetailBean.ItemsBean> I;
    public List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> J;
    public List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> K;
    public Context L;

    /* renamed from: g  reason: collision with root package name */
    public int f5014g;

    /* renamed from: h  reason: collision with root package name */
    public int f5015h;

    /* renamed from: i  reason: collision with root package name */
    public PullToRefreshLoadMoreRecyclerView f5016i;

    /* renamed from: j  reason: collision with root package name */
    public PdMDpgLayerAdapter f5017j;

    /* renamed from: k  reason: collision with root package name */
    public String f5018k;

    /* renamed from: l  reason: collision with root package name */
    public List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> f5019l;

    /* renamed from: m  reason: collision with root package name */
    public List<PdDpgListLayerInfo.DetailBean.ItemsBean> f5020m;

    /* renamed from: n  reason: collision with root package name */
    public PdDpgListLayerInfo.DetailBean.ItemInfoAndItem f5021n;
    public PdDpgListLayerInfo.DetailBean.ItemsBean o;
    public boolean p;
    public PdMDpgLayerAdapter.PdDpgLayerHolder q;
    public boolean r;
    public int s;
    public int t;
    public int u;
    public String v;
    public WareBusinessUnitMainImageEntity w;
    public List<PdDpgListLayerInfo.DetailBean> x;
    public View y;
    public ImageView z;

    /* loaded from: classes15.dex */
    public class a implements PdMDpgLayerAdapter.b {
        public a() {
        }
    }

    /* loaded from: classes15.dex */
    public class b extends RecyclerView.OnScrollListener {
        public int a = 0;
        public boolean b = false;

        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
            super.onScrollStateChanged(recyclerView, i2);
            if (this.a == 0 && i2 == 1) {
                this.b = true;
            } else if (i2 == 0) {
                try {
                    PdMDpgLayerView.k(PdMDpgLayerView.this);
                    PdMDpgLayerView.c(PdMDpgLayerView.this);
                    PdMDpgLayerView.h(PdMDpgLayerView.this);
                } catch (Exception e2) {
                    ExceptionReporter.reportExceptionToBugly(e2);
                }
            }
            this.a = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
            super.onScrolled(recyclerView, i2, i3);
            if (!this.b || i3 == 0) {
                return;
            }
            this.b = false;
        }
    }

    public PdMDpgLayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5015h = 10;
        this.f5019l = new ArrayList();
        this.f5020m = new ArrayList();
        this.t = 0;
        this.u = 0;
        this.H = new ArrayList();
        this.I = new ArrayList();
        this.J = new ArrayList();
        this.K = new ArrayList();
        this.L = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        e(this.f5018k);
    }

    public static void c(PdMDpgLayerView pdMDpgLayerView) {
        pdMDpgLayerView.getClass();
        JDJSONArray jDJSONArray = new JDJSONArray();
        ArrayList<PdDpgListLayerInfo.DetailBean.ItemsBean> n2 = pdMDpgLayerView.n();
        if (n2 == null || n2.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < n2.size(); i2++) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            if (n2.get(i2) != null) {
                jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) n2.get(i2).skuId);
                if (!TextUtils.isEmpty(n2.get(i2).nativeMatchId)) {
                    jDJSONObject.put("matchid", (Object) n2.get(i2).nativeMatchId);
                } else {
                    jDJSONObject.put("matchid", (Object) "-100");
                }
                if (n2.get(i2).nativeType > 0) {
                    jDJSONObject.put("type", (Object) Integer.valueOf(n2.get(i2).nativeType));
                } else {
                    jDJSONObject.put("type", (Object) "-100");
                }
                pdMDpgLayerView.I.add(n2.get(i2));
            }
            jDJSONArray.add(jDJSONObject);
        }
        pdMDpgLayerView.F.mtaExposure("Productdetail_DapeiBuyProductExpo", jDJSONArray.toJSONString());
    }

    public static void d(PdMDpgLayerView pdMDpgLayerView, PdDpgListLayerInfo.DetailBean detailBean) {
        List<PdDpgListLayerInfo.DetailBean.ItemsBean> list;
        pdMDpgLayerView.getClass();
        if (detailBean != null) {
            PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = new PdDpgListLayerInfo.DetailBean.ItemInfoAndItem();
            PdDpgListLayerInfo.DetailBean.InfoBean infoBean = detailBean.info;
            itemInfoAndItem.info = infoBean;
            List<PdDpgListLayerInfo.DetailBean.ItemsBean> list2 = detailBean.items;
            itemInfoAndItem.items = list2;
            itemInfoAndItem.type = detailBean.type;
            if (infoBean != null && list2 != null && list2.size() > 0) {
                pdMDpgLayerView.q(itemInfoAndItem);
                pdMDpgLayerView.f5019l.add(itemInfoAndItem);
            }
            List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> list3 = detailBean.others;
            if (list3 != null && list3.size() > 0) {
                for (int i2 = 0; i2 < detailBean.others.size(); i2++) {
                    PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem2 = detailBean.others.get(i2);
                    if (itemInfoAndItem2 != null && itemInfoAndItem2.info != null && (list = itemInfoAndItem2.items) != null && list.size() > 0) {
                        pdMDpgLayerView.q(itemInfoAndItem2);
                        pdMDpgLayerView.f5019l.add(itemInfoAndItem2);
                    }
                }
            }
        }
        pdMDpgLayerView.f5020m.clear();
        if (pdMDpgLayerView.f5019l != null) {
            for (int i3 = 0; i3 < pdMDpgLayerView.f5019l.size(); i3++) {
                PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem3 = pdMDpgLayerView.f5019l.get(i3);
                if (itemInfoAndItem3 != null && itemInfoAndItem3.items != null) {
                    for (int i4 = 0; i4 < itemInfoAndItem3.items.size(); i4++) {
                        PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean = itemInfoAndItem3.items.get(i4);
                        PdDpgListLayerInfo.DetailBean.InfoBean infoBean2 = itemInfoAndItem3.info;
                        if (infoBean2 != null) {
                            itemsBean.nativeMatchId = infoBean2.matchId;
                        }
                    }
                    pdMDpgLayerView.f5020m.addAll(itemInfoAndItem3.items);
                }
            }
        }
        PdMDpgLayerAdapter pdMDpgLayerAdapter = pdMDpgLayerView.f5017j;
        List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> list4 = pdMDpgLayerView.f5019l;
        List<PdDpgListLayerInfo.DetailBean.ItemsBean> list5 = pdMDpgLayerView.H;
        List<PdDpgListLayerInfo.DetailBean.ItemsBean> list6 = pdMDpgLayerView.I;
        pdMDpgLayerAdapter.f4993c = list4;
        pdMDpgLayerAdapter.d = list5;
        pdMDpgLayerAdapter.f4994e = list6;
        pdMDpgLayerAdapter.notifyDataSetChanged();
        List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> list7 = pdMDpgLayerView.f5019l;
        if (list7 != null && list7.size() > 0) {
            pdMDpgLayerView.f(false);
        } else {
            pdMDpgLayerView.f(true);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void destyry() {
        this.G = true;
    }

    public static void h(PdMDpgLayerView pdMDpgLayerView) {
        pdMDpgLayerView.getClass();
        JDJSONArray jDJSONArray = new JDJSONArray();
        ArrayList<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> m2 = pdMDpgLayerView.m();
        if (m2 == null || m2.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < m2.size(); i2++) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            if (m2.get(i2) != null) {
                if (m2.get(i2).info != null) {
                    if (!TextUtils.isEmpty(m2.get(i2).info.matchAdWord)) {
                        jDJSONObject.put("clerk", (Object) "1");
                    } else {
                        jDJSONObject.put("clerk", (Object) "0");
                    }
                    jDJSONObject.put("matchid", (Object) m2.get(i2).info.matchId);
                } else {
                    jDJSONObject.put("clerk", (Object) "0");
                    jDJSONObject.put("matchid", (Object) "-100");
                }
                if (m2.get(i2).type > 0) {
                    jDJSONObject.put("type", (Object) Integer.valueOf(m2.get(i2).type));
                } else {
                    jDJSONObject.put("type", (Object) "-100");
                }
                jDJSONArray.add(jDJSONObject);
                pdMDpgLayerView.K.add(m2.get(i2));
            }
        }
        pdMDpgLayerView.F.mtaExposure("Productdetail_DapeiBuyTitleExpo", jDJSONArray.toJSONString());
    }

    public static void k(PdMDpgLayerView pdMDpgLayerView) {
        LinearLayoutManager linearLayoutManager;
        int findLastVisibleItemPosition;
        RecyclerView recyclerView = pdMDpgLayerView.E;
        if (recyclerView == null || recyclerView.getVisibility() != 0 || !(pdMDpgLayerView.E.getLayoutManager() instanceof LinearLayoutManager) || (findLastVisibleItemPosition = (linearLayoutManager = (LinearLayoutManager) pdMDpgLayerView.E.getLayoutManager()).findLastVisibleItemPosition()) == -1) {
            return;
        }
        View childAt = pdMDpgLayerView.E.getChildAt(findLastVisibleItemPosition - linearLayoutManager.findFirstVisibleItemPosition());
        if (childAt == null) {
            return;
        }
        if (pdMDpgLayerView.f5014g == 0) {
            int ceil = (int) Math.ceil((pdMDpgLayerView.E.getWidth() - PDUtils.dip2px(20.0f)) / PDUtils.dip2px(123.0f));
            pdMDpgLayerView.f5014g = ceil;
            if (ceil < 1) {
                pdMDpgLayerView.f5014g = 3;
            }
        }
        int i2 = (childAt.getTop() + (((childAt.getBottom() - childAt.getTop()) * 2) / 5) < pdMDpgLayerView.E.getHeight() || findLastVisibleItemPosition <= 1) ? findLastVisibleItemPosition : findLastVisibleItemPosition - 1;
        List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> list = pdMDpgLayerView.f5019l;
        if (list != null && list.size() > i2) {
            for (int i3 = 0; i3 <= i2; i3++) {
                PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = pdMDpgLayerView.f5019l.get(i3);
                if (itemInfoAndItem != null) {
                    pdMDpgLayerView.J.add(itemInfoAndItem);
                }
            }
        }
        List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> list2 = pdMDpgLayerView.f5019l;
        if (list2 == null || list2.size() <= findLastVisibleItemPosition) {
            return;
        }
        for (int i4 = 0; i4 <= findLastVisibleItemPosition; i4++) {
            PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem2 = pdMDpgLayerView.f5019l.get(i4);
            if (itemInfoAndItem2 != null && itemInfoAndItem2.items.size() > 0) {
                int size = itemInfoAndItem2.items.size();
                int i5 = pdMDpgLayerView.f5014g;
                if (size >= i5) {
                    pdMDpgLayerView.H.addAll(itemInfoAndItem2.items.subList(0, i5));
                } else {
                    pdMDpgLayerView.H.addAll(itemInfoAndItem2.items);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        this.f5016i.resetFooter();
        if (this.u == this.f5015h) {
            i(this.v);
            return;
        }
        this.f5016i.setReachEndInvisible();
        this.f5016i.setMode(PullToRefreshBase.Mode.DISABLED);
    }

    private ArrayList<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> m() {
        if (this.f5019l == null) {
            return null;
        }
        ArrayList<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.f5019l.size(); i2++) {
            PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = this.f5019l.get(i2);
            if (itemInfoAndItem != null && this.J.contains(itemInfoAndItem) && !this.K.contains(itemInfoAndItem)) {
                arrayList.add(itemInfoAndItem);
            }
        }
        return arrayList;
    }

    private ArrayList<PdDpgListLayerInfo.DetailBean.ItemsBean> n() {
        if (this.f5020m == null) {
            return null;
        }
        ArrayList<PdDpgListLayerInfo.DetailBean.ItemsBean> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.f5020m.size(); i2++) {
            PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean = this.f5020m.get(i2);
            if (itemsBean != null && this.H.contains(itemsBean) && !this.I.contains(itemsBean)) {
                arrayList.add(itemsBean);
            }
        }
        return arrayList;
    }

    private void q(PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem) {
        if (itemInfoAndItem.type == 2) {
            int i2 = 0;
            for (int i3 = 0; i3 < itemInfoAndItem.items.size(); i3++) {
                PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean = itemInfoAndItem.items.get(i3);
                if (itemsBean != null) {
                    itemsBean.nativeType = itemInfoAndItem.type;
                    if (itemsBean.isValid) {
                        itemsBean.isSelectedProductCheckBoxNative = true;
                        itemsBean.isSelectedTextNative = true;
                        i2++;
                    } else {
                        itemsBean.isSelectedTextNative = true;
                        itemsBean.isSelectedProductCheckBoxNative = false;
                    }
                }
            }
            PdDpgListLayerInfo.DetailBean.InfoBean infoBean = itemInfoAndItem.info;
            infoBean.nativeTotalPriceStr = infoBean.matchPrice;
            infoBean.nativeTotalNumber = i2;
            return;
        }
        PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean2 = itemInfoAndItem.items.get(0);
        if (itemsBean2 != null) {
            itemsBean2.nativeType = itemInfoAndItem.type;
            if (itemsBean2.isValid) {
                itemsBean2.isSelectedProductCheckBoxNative = true;
                itemsBean2.isSelectedTextNative = true;
                PdDpgListLayerInfo.DetailBean.InfoBean infoBean2 = itemInfoAndItem.info;
                infoBean2.nativeTotalPriceStr = itemsBean2.price;
                infoBean2.nativeTotalNumber = 1;
            } else {
                itemsBean2.isSelectedTextNative = true;
                itemsBean2.isSelectedProductCheckBoxNative = false;
                PdDpgListLayerInfo.DetailBean.InfoBean infoBean3 = itemInfoAndItem.info;
                infoBean3.nativeTotalPriceStr = "";
                infoBean3.nativeTotalNumber = 0;
            }
        }
        for (int i4 = 0; i4 < itemInfoAndItem.items.size(); i4++) {
            PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean3 = itemInfoAndItem.items.get(i4);
            if (itemsBean3 != null) {
                itemsBean3.nativeType = itemInfoAndItem.type;
            }
        }
    }

    public final void a() {
        this.f5019l.clear();
        this.f5020m.clear();
        this.H.clear();
        this.J.clear();
        this.I.clear();
        this.K.clear();
        this.f5021n = null;
        this.o = null;
        this.t = 0;
        this.u = 0;
        this.p = false;
        this.q = null;
        this.r = false;
        this.s = 0;
    }

    public void e(String str) {
        Context context;
        int i2;
        Context context2;
        int i3;
        View view = this.y;
        if (view != null) {
            view.setVisibility(8);
        }
        PullToRefreshLoadMoreRecyclerView pullToRefreshLoadMoreRecyclerView = this.f5016i;
        if (pullToRefreshLoadMoreRecyclerView != null) {
            pullToRefreshLoadMoreRecyclerView.setVisibility(8);
        }
        this.f5018k = str;
        this.f5016i.setAutoDark(this.F.getMainImageParams().isDark);
        if (this.F.getMainImageParams().isDark) {
            context = this.L;
            i2 = R.color.lib_pd_image_color_0A0909;
        } else {
            context = this.L;
            i2 = R.color.lib_pd_image_color_F2F2F2;
        }
        setBackgroundColor(ContextCompat.getColor(context, i2));
        View view2 = this.y;
        if (this.F.getMainImageParams().isDark) {
            context2 = this.L;
            i3 = R.color.lib_pd_image_color_0A0909;
        } else {
            context2 = this.L;
            i3 = R.color.lib_pd_image_color_F2F2F2;
        }
        view2.setBackgroundColor(ContextCompat.getColor(context2, i3));
        List<PdDpgListLayerInfo.DetailBean> list = this.x;
        if (list != null && list.size() > 0) {
            a();
            PdDpgListMoreInfo pdDpgListMoreInfo = new PdDpgListMoreInfo();
            pdDpgListMoreInfo.code = 0;
            pdDpgListMoreInfo.detail = this.x;
            this.F.mPdMDpgLayerContainer.b.postValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.SUCCESS, pdDpgListMoreInfo, ""));
            this.f5016i.setMode(PullToRefreshBase.Mode.DISABLED);
            return;
        }
        y yVar = this.F.mPdMDpgLayerContainer;
        BaseActivity baseActivity = (BaseActivity) this.L;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity = this.w.extMap;
        String str2 = extMapEntity != null ? extMapEntity.skuId : "";
        yVar.getClass();
        PdDpgLayerDetailProtocol pdDpgLayerDetailProtocol = new PdDpgLayerDetailProtocol();
        PdDpgLayerDetailProtocol.PdDpgLayerRequestParams pdDpgLayerRequestParams = new PdDpgLayerDetailProtocol.PdDpgLayerRequestParams();
        pdDpgLayerRequestParams.area = LocManager.getCommonLbsParameter();
        pdDpgLayerRequestParams.matchId = str;
        pdDpgLayerRequestParams.skuId = str2;
        pdDpgLayerDetailProtocol.mRequestParams = pdDpgLayerRequestParams;
        pdDpgLayerDetailProtocol.request(baseActivity);
        pdDpgLayerDetailProtocol.mResult.observe(baseActivity, new d(yVar, pdDpgLayerDetailProtocol));
        this.f5016i.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
    }

    public final void f(boolean z) {
        if (NetUtils.isNetworkAvailable()) {
            if (this.G) {
                return;
            }
            if (z) {
                this.y.setVisibility(0);
                this.f5016i.setVisibility(8);
                this.D.setVisibility(8);
                this.z.setImageResource(R.drawable.lib_pd_mainimage_recommend_joy);
                this.A.setText("");
                this.B.setText(this.L.getString(R.string.lib_pd_image_recommend_empty));
                this.C.setText("");
                return;
            }
            this.y.setVisibility(8);
            this.f5016i.setVisibility(0);
        } else if (z) {
            this.y.setVisibility(0);
            this.f5016i.setVisibility(8);
            this.D.setVisibility(0);
            this.D.setText(R.string.lib_pd_image_reload);
            this.z.setImageResource(com.jingdong.common.R.drawable.y_03);
            this.A.setText(JdSdk.getInstance().getApplicationContext().getString(R.string.lib_pd_image_topimage_network_fail));
            this.B.setText(JdSdk.getInstance().getApplicationContext().getString(R.string.lib_pd_image_topimage_network_chcek));
            this.C.setText("");
            this.D.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.old.q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PdMDpgLayerView.this.b(view);
                }
            });
        } else {
            this.y.setVisibility(8);
            this.f5016i.setVisibility(0);
        }
    }

    public final void g() {
        this.f5017j.f4995f = new a();
        this.f5016i.setOnLoadMoreListener(new LoadMoreListener() { // from class: com.jd.lib.productdetail.mainimage.old.t
            @Override // com.handmark.pulltorefresh.library.LoadMoreListener
            public final void loadMore() {
                PdMDpgLayerView.this.l();
            }
        });
        this.E.addOnScrollListener(new b());
    }

    public void i(String str) {
        y yVar = this.F.mPdMDpgLayerContainer;
        BaseActivity baseActivity = (BaseActivity) this.L;
        int i2 = this.t;
        int i3 = this.f5015h;
        yVar.getClass();
        PdDpgLayerDetailMoreProtocol pdDpgLayerDetailMoreProtocol = new PdDpgLayerDetailMoreProtocol();
        PdDpgLayerDetailMoreProtocol.PdDpgLayerDetailMoreRequestParams pdDpgLayerDetailMoreRequestParams = new PdDpgLayerDetailMoreProtocol.PdDpgLayerDetailMoreRequestParams();
        pdDpgLayerDetailMoreRequestParams.pageNo = i2;
        pdDpgLayerDetailMoreRequestParams.pageSize = i3;
        pdDpgLayerDetailMoreRequestParams.shopId = str;
        pdDpgLayerDetailMoreProtocol.mRequestParams = pdDpgLayerDetailMoreRequestParams;
        pdDpgLayerDetailMoreProtocol.request(baseActivity);
        pdDpgLayerDetailMoreProtocol.mResult.observe(baseActivity, new c(yVar, pdDpgLayerDetailMoreProtocol));
    }

    public void j() {
        this.f5016i = (PullToRefreshLoadMoreRecyclerView) findViewById(R.id.pd_dpg_layer_rv);
        this.y = findViewById(R.id.layout_net_error);
        this.z = (ImageView) findViewById(com.jingdong.common.R.id.jd_tip_image);
        this.A = (TextView) findViewById(com.jingdong.common.R.id.jd_tip_tv1);
        this.B = (TextView) findViewById(com.jingdong.common.R.id.jd_tip_tv2);
        this.C = (TextView) findViewById(com.jingdong.common.R.id.jd_tip_tv3);
        this.D = (Button) findViewById(com.jingdong.common.R.id.jd_tip_button);
        PdMDpgLayerAdapter pdMDpgLayerAdapter = new PdMDpgLayerAdapter(this.L);
        this.f5017j = pdMDpgLayerAdapter;
        PdMainImagePresenter pdMainImagePresenter = this.F;
        if (pdMainImagePresenter != null) {
            pdMDpgLayerAdapter.f4997h = pdMainImagePresenter;
        }
        RecyclerView refreshableView = this.f5016i.getRefreshableView();
        this.E = refreshableView;
        refreshableView.setLayoutManager(new LinearLayoutManager(this.L));
        this.E.setAdapter(this.f5017j);
        this.f5016i.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        g();
        LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(((BaseActivity) getContext()).getWindow().getDecorView());
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(this);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        j();
    }

    public void r(PdMainImagePresenter pdMainImagePresenter) {
        this.F = pdMainImagePresenter;
        PdMDpgLayerAdapter pdMDpgLayerAdapter = this.f5017j;
        if (pdMDpgLayerAdapter != null) {
            pdMDpgLayerAdapter.f4997h = pdMainImagePresenter;
        }
        pdMainImagePresenter.mPdMDpgLayerContainer.a((BaseActivity) this.L);
        this.F.mPdMDpgLayerContainer.a.observe((BaseActivity) this.L, new f(this));
        this.F.mPdMDpgLayerContainer.b.observe((BaseActivity) this.L, new g(this));
        this.F.mPdMDpgLayerContainer.f5208c.observe((BaseActivity) this.L, new h(this));
    }
}
