package com.jd.manto.center;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jd.manto.center.a;
import com.jd.manto.center.model.AppItemWrapper;
import com.jd.manto.center.widget.recycler.HeaderFooterRecyclerAdapterWrapper;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.message.MantoAcrossMessage;
import com.jingdong.manto.message.MantoAcrossMessageCenter;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.db.entity.PkgCollectEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.pkg.db.entity.PkgRecommend;
import com.jingdong.manto.pkg.ipc.MantoPkgUpdate;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class MantoCenterFragment extends Fragment implements MantoAcrossMessage.Listener {

    /* renamed from: g  reason: collision with root package name */
    private RecyclerView f6254g;

    /* renamed from: j  reason: collision with root package name */
    private com.jd.manto.center.a f6257j;

    /* renamed from: k  reason: collision with root package name */
    private ProgressBar f6258k;

    /* renamed from: l  reason: collision with root package name */
    private RecommendView f6259l;

    /* renamed from: m  reason: collision with root package name */
    private HeaderFooterRecyclerAdapterWrapper f6260m;

    /* renamed from: h  reason: collision with root package name */
    private LinkedList<AppItemWrapper> f6255h = new LinkedList<>();

    /* renamed from: i  reason: collision with root package name */
    private List<AppItemWrapper> f6256i = new ArrayList(50);

    /* renamed from: n  reason: collision with root package name */
    private boolean f6261n = false;
    private BroadcastReceiver o = new i();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoPkgUpdate f6262g;

        a(MantoPkgUpdate mantoPkgUpdate) {
            this.f6262g = mantoPkgUpdate;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPkgUpdate mantoPkgUpdate = this.f6262g;
            if (mantoPkgUpdate.detailEntity == null) {
                return;
            }
            PkgHistoryEntity pkgHistoryEntity = new PkgHistoryEntity();
            PkgDetailEntity pkgDetailEntity = mantoPkgUpdate.detailEntity;
            pkgHistoryEntity.appId = pkgDetailEntity.appId;
            pkgHistoryEntity.type = pkgDetailEntity.type;
            pkgHistoryEntity.name = pkgDetailEntity.name;
            pkgHistoryEntity.logo = pkgDetailEntity.logo;
            MantoPkgUpdate.UpdateAction updateAction = MantoPkgUpdate.UpdateAction.FAVO;
            MantoPkgUpdate.UpdateAction updateAction2 = mantoPkgUpdate.action;
            pkgHistoryEntity.favorite = updateAction == updateAction2;
            if (updateAction == updateAction2) {
                MantoCenterFragment.this.f6257j.r(pkgHistoryEntity);
            } else if (MantoPkgUpdate.UpdateAction.UNFAVO == updateAction2) {
                MantoCenterFragment.this.f6257j.z(pkgHistoryEntity);
            }
        }
    }

    /* loaded from: classes17.dex */
    class b extends GridLayoutManager.SpanSizeLookup {
        b() {
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i2) {
            return ((AppItemWrapper) MantoCenterFragment.this.f6256i.get(i2)).type == 0 ? 5 : 1;
        }
    }

    /* loaded from: classes17.dex */
    class c implements a.f {

        /* loaded from: classes17.dex */
        class a implements PkgManager.PkgFavoCallBack {
            final /* synthetic */ int a;

            /* renamed from: com.jd.manto.center.MantoCenterFragment$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes17.dex */
            class RunnableC0175a implements Runnable {
                RunnableC0175a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    List list = MantoCenterFragment.this.f6256i;
                    a aVar = a.this;
                    list.remove(MantoCenterFragment.this.t(aVar.a));
                    a aVar2 = a.this;
                    MantoCenterFragment mantoCenterFragment = MantoCenterFragment.this;
                    mantoCenterFragment.o(mantoCenterFragment.t(aVar2.a));
                }
            }

            /* loaded from: classes17.dex */
            class b implements Runnable {
                b() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(MantoCenterFragment.this.getContext(), "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5", 0).show();
                }
            }

            a(int i2) {
                this.a = i2;
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
            public void onError(Throwable th) {
                MantoCenterFragment.this.x(new b());
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
            public void onSuccess() {
                MantoCenterFragment.this.x(new RunnableC0175a());
            }
        }

        /* loaded from: classes17.dex */
        class b implements PkgManager.PkgFavoCallBack {
            final /* synthetic */ int a;
            final /* synthetic */ PkgCollectEntity b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ AppItemWrapper f6266c;

            /* loaded from: classes17.dex */
            class a implements Runnable {
                a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    com.jd.manto.center.a aVar = MantoCenterFragment.this.f6257j;
                    b bVar = b.this;
                    aVar.y(MantoCenterFragment.this.t(bVar.a));
                    com.jingdong.a.v(new MantoPkgUpdate(new PkgDetailEntity(b.this.b), MantoPkgUpdate.UpdateAction.UNFAVO), b.this.f6266c.entity.appId);
                }
            }

            /* renamed from: com.jd.manto.center.MantoCenterFragment$c$b$b  reason: collision with other inner class name */
            /* loaded from: classes17.dex */
            class RunnableC0176b implements Runnable {
                RunnableC0176b() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(MantoCenterFragment.this.getContext(), "\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5", 0).show();
                }
            }

            b(int i2, PkgCollectEntity pkgCollectEntity, AppItemWrapper appItemWrapper) {
                this.a = i2;
                this.b = pkgCollectEntity;
                this.f6266c = appItemWrapper;
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
            public void onError(Throwable th) {
                MantoCenterFragment.this.x(new RunnableC0176b());
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
            public void onSuccess() {
                MantoCenterFragment.this.x(new a());
            }
        }

        c() {
        }

        @Override // com.jd.manto.center.a.f
        public void a(AppItemWrapper appItemWrapper, int i2) {
            if (appItemWrapper.entity == null) {
                MantoCenterFragment.this.v(appItemWrapper);
                return;
            }
            LaunchParam launchParam = new LaunchParam();
            PkgHistoryEntity pkgHistoryEntity = appItemWrapper.entity;
            launchParam.appId = pkgHistoryEntity.appId;
            launchParam.debugType = pkgHistoryEntity.type;
            com.jingdong.a.p(launchParam, MantoCenterFragment.this.getActivity());
            HashMap hashMap = new HashMap();
            hashMap.put("vapp", "1");
            hashMap.put("vapp_appid", appItemWrapper.entity.appId);
            hashMap.put("source", "myApplets");
            Context context = MantoCenterFragment.this.getContext();
            PkgHistoryEntity pkgHistoryEntity2 = appItemWrapper.entity;
            MantoTrack.sendCommonDataWithExt(context, pkgHistoryEntity2.name, "Applets_Center_Enter", pkgHistoryEntity2.appId, "", "", "", "", hashMap);
        }

        @Override // com.jd.manto.center.a.f
        public void b(AppItemWrapper appItemWrapper, int i2) {
            PkgHistoryEntity pkgHistoryEntity = appItemWrapper.entity;
            if (pkgHistoryEntity != null) {
                int i3 = appItemWrapper.type;
                if (1 == i3) {
                    PkgManager.deletePkg(pkgHistoryEntity.appId, pkgHistoryEntity.type, new a(i2));
                } else if (2 == i3) {
                    PkgCollectEntity pkgCollectEntity = new PkgCollectEntity();
                    PkgHistoryEntity pkgHistoryEntity2 = appItemWrapper.entity;
                    pkgCollectEntity.appId = pkgHistoryEntity2.appId;
                    pkgCollectEntity.type = pkgHistoryEntity2.type;
                    pkgCollectEntity.logo = pkgHistoryEntity2.logo;
                    pkgCollectEntity.name = pkgHistoryEntity2.name;
                    pkgCollectEntity.favorite = false;
                    PkgManager.unFavoPkg(pkgCollectEntity, new b(i2, pkgCollectEntity, appItemWrapper));
                }
            }
        }
    }

    /* loaded from: classes17.dex */
    class d implements RecyclerView.OnItemTouchListener {
        d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1 && recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY()) == null) {
                if (MantoCenterFragment.this.f6257j.v()) {
                    MantoCenterFragment.this.f6257j.A();
                }
                return true;
            }
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements PkgManager.RecommendListCallback {

        /* loaded from: classes17.dex */
        class a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ List f6269g;

            a(List list) {
                this.f6269g = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCenterFragment.this.f6259l = new RecommendView(MantoCenterFragment.this.getActivity());
                MantoCenterFragment.this.f6259l.b(this.f6269g);
                if (MantoCenterFragment.this.f6260m.h(MantoCenterFragment.this.f6259l)) {
                    MantoCenterFragment.this.f6260m.removeHeaderView(MantoCenterFragment.this.f6259l);
                }
                MantoCenterFragment.this.f6260m.addHeaderView(MantoCenterFragment.this.f6259l);
            }
        }

        e() {
        }

        @Override // com.jingdong.manto.pkg.PkgManager.RecommendListCallback
        public void onError(Throwable th, JSONObject jSONObject) {
        }

        @Override // com.jingdong.manto.pkg.PkgManager.RecommendListCallback
        public void onSuccess(List<PkgRecommend> list) {
            if (list == null || list.size() <= 0) {
                return;
            }
            MantoCenterFragment.this.x(new a(list));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoCenterFragment.this.y();
            MantoCenterFragment.this.f6257j.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class g implements PkgManager.PkgHistoryListCallBack {
        final /* synthetic */ List a;

        /* loaded from: classes17.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCenterFragment.this.y();
                MantoCenterFragment.this.f6257j.x(g.this.a);
            }
        }

        g(List list) {
            this.a = list;
        }

        @Override // com.jingdong.manto.pkg.PkgManager.PkgHistoryListCallBack
        public void onError(Throwable th) {
            MantoCenterFragment.this.f6255h.clear();
            MantoCenterFragment.this.z();
        }

        @Override // com.jingdong.manto.pkg.PkgManager.PkgHistoryListCallBack
        public void onSuccess(List<PkgHistoryEntity> list) {
            MantoCenterFragment.this.f6255h.clear();
            if (list != null && list.size() > 0) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    MantoCenterFragment.this.f6255h.add(new AppItemWrapper(1, list.get(i2)));
                }
                for (int i3 = 0; !MantoCenterFragment.this.f6255h.isEmpty() && i3 < 4; i3++) {
                    this.a.add(MantoCenterFragment.this.f6255h.removeFirst());
                }
            }
            MantoCenterFragment.this.x(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class h implements PkgManager.PkgCollectionListCallBack {
        final /* synthetic */ List a;

        /* loaded from: classes17.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCenterFragment.this.y();
                MantoCenterFragment.this.f6257j.w(h.this.a);
            }
        }

        h(List list) {
            this.a = list;
        }

        @Override // com.jingdong.manto.pkg.PkgManager.PkgCollectionListCallBack
        public void onError(Throwable th) {
            MantoCenterFragment.this.z();
        }

        @Override // com.jingdong.manto.pkg.PkgManager.PkgCollectionListCallBack
        public void onSuccess(List<PkgCollectEntity> list, int i2) {
            if (list != null && list.size() > 0) {
                for (int i3 = 0; i3 < list.size(); i3++) {
                    PkgCollectEntity pkgCollectEntity = list.get(i3);
                    PkgHistoryEntity pkgHistoryEntity = new PkgHistoryEntity();
                    pkgHistoryEntity.type = pkgCollectEntity.type;
                    pkgHistoryEntity.appId = pkgCollectEntity.appId;
                    pkgHistoryEntity.logo = pkgCollectEntity.logo;
                    pkgHistoryEntity.name = pkgCollectEntity.name;
                    this.a.add(new AppItemWrapper(2, pkgHistoryEntity));
                }
            }
            MantoCenterFragment.this.x(new a());
        }
    }

    /* loaded from: classes17.dex */
    class i extends BroadcastReceiver {
        i() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"com.jd.manto.center.pkgfavo".equals(intent.getAction())) {
                return;
            }
            try {
                MantoCenterFragment.this.q((MantoPkgUpdate) intent.getParcelableExtra(CartConstant.KEY_VENDOR_ITEM));
            } catch (Exception unused) {
            }
        }
    }

    /* loaded from: classes17.dex */
    class j implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Object f6274g;

        j(Object obj) {
            this.f6274g = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            PkgDetailEntity pkgDetailEntity = (PkgDetailEntity) this.f6274g;
            PkgHistoryEntity pkgHistoryEntity = new PkgHistoryEntity();
            pkgHistoryEntity.type = pkgDetailEntity.type;
            String str = pkgDetailEntity.appId;
            pkgHistoryEntity.appId = str;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            int indexOf = MantoCenterFragment.this.f6256i.indexOf(new AppItemWrapper(-1, pkgHistoryEntity));
            if (indexOf <= -1 || indexOf >= 15 || MantoCenterFragment.this.f6257j == null) {
                return;
            }
            MantoLog.d(DYConstants.DY_CENTER, "delete used: " + indexOf);
            MantoCenterFragment.this.f6256i.remove(indexOf);
            MantoCenterFragment.this.o(indexOf);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(int i2) {
        this.f6257j.q(i2, !this.f6255h.isEmpty() ? this.f6255h.removeFirst() : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(MantoPkgUpdate mantoPkgUpdate) {
        x(new a(mantoPkgUpdate));
    }

    private final void r() {
        ArrayList arrayList = new ArrayList(50);
        showLoading();
        PkgManager.getCollectionList(0, new h(arrayList));
    }

    public static MantoCenterFragment s() {
        return new MantoCenterFragment();
    }

    private final void showLoading() {
        this.f6258k.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int t(int i2) {
        HeaderFooterRecyclerAdapterWrapper headerFooterRecyclerAdapterWrapper = this.f6260m;
        return headerFooterRecyclerAdapterWrapper != null ? i2 - headerFooterRecyclerAdapterWrapper.m() : i2;
    }

    private final void u() {
        showLoading();
        PkgManager.getHistoryList(new g(new ArrayList(5)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void v(AppItemWrapper appItemWrapper) {
        if (appItemWrapper != null) {
            int i2 = appItemWrapper.type;
            if (3 == i2) {
                MantoTrack.sendCommonDataWithExt(getContext(), "\u70b9\u51fb\u66f4\u591a", "Applets_Center_LookMore", "", "", "", "", "", null);
                startActivity(new Intent(getActivity(), MantoListActivity.class));
            } else if (4 == i2) {
                MantoTrack.sendCommonDataWithExt(getContext(), "\u70b9\u51fb\u6dfb\u52a0", "Applets_Center_Add", "", "", "", "", "", null);
                Intent intent = new Intent(getActivity(), MantoListActivity.class);
                intent.putExtra("which", "search");
                startActivity(intent);
            }
        }
    }

    private final void w() {
        PkgManager.getRecommedList(new e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(Runnable runnable) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        getActivity().runOnUiThread(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void y() {
        this.f6258k.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void z() {
        x(new f());
    }

    @Override // com.jingdong.manto.message.MantoAcrossMessage.Listener
    public void onCalled(Object obj) {
        if (obj instanceof PkgDetailEntity) {
            x(new j(obj));
        } else if (obj instanceof MantoPkgUpdate) {
            q((MantoPkgUpdate) obj);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        try {
            LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.o, new IntentFilter("com.jd.manto.center.pkgfavo"));
        } catch (Exception unused) {
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.manto_center_fragment, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        try {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.o);
        } catch (Exception unused) {
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        MantoAcrossMessageCenter.unRegistMainListener(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (!this.f6261n) {
            w();
            u();
            r();
        } else {
            u();
        }
        this.f6261n = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f6254g = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.f6258k = (ProgressBar) view.findViewById(R.id.progress);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
        this.f6254g.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new b());
        this.f6256i.clear();
        com.jd.manto.center.a aVar = new com.jd.manto.center.a(getActivity(), this.f6256i, new c());
        this.f6257j = aVar;
        HeaderFooterRecyclerAdapterWrapper headerFooterRecyclerAdapterWrapper = new HeaderFooterRecyclerAdapterWrapper(aVar);
        this.f6260m = headerFooterRecyclerAdapterWrapper;
        this.f6254g.setAdapter(headerFooterRecyclerAdapterWrapper);
        this.f6254g.addOnItemTouchListener(new d());
        MantoAcrossMessageCenter.registMainListener(this);
    }
}
