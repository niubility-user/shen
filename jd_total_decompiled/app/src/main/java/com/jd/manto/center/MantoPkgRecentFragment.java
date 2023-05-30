package com.jd.manto.center;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.manto.center.MantoPkgRecentAdapter;
import com.jd.manto.center.model.AdapterBeanWrapper;
import com.jd.manto.center.widget.MaeFrameView;
import com.jd.manto.center.widget.recycler.PullUpLoadRecyclerView;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.message.MantoAcrossMessage;
import com.jingdong.manto.message.MantoAcrossMessageCenter;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.db.entity.PkgCollectEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.pkg.ipc.MantoPkgUpdate;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.widget.actionbar.MantoPopupWindow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes17.dex */
public class MantoPkgRecentFragment extends Fragment implements View.OnClickListener, MantoAcrossMessage.Listener {

    /* renamed from: g  reason: collision with root package name */
    private PullUpLoadRecyclerView f6388g;

    /* renamed from: h  reason: collision with root package name */
    private MantoPkgRecentAdapter f6389h;

    /* renamed from: i  reason: collision with root package name */
    boolean f6390i;

    /* renamed from: j  reason: collision with root package name */
    int f6391j = 1;

    /* renamed from: k  reason: collision with root package name */
    private boolean f6392k;

    /* renamed from: l  reason: collision with root package name */
    private MaeFrameView f6393l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(MantoPkgRecentFragment.this.getContext(), "\u6570\u636e\u52a0\u8f7d\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", 0).show();
        }
    }

    /* loaded from: classes17.dex */
    class b implements MantoPkgRecentAdapter.c<PkgHistoryEntity> {
        b() {
        }

        @Override // com.jd.manto.center.MantoPkgRecentAdapter.c
        /* renamed from: c  reason: merged with bridge method [inline-methods] */
        public void b(PkgHistoryEntity pkgHistoryEntity, int i2) {
            MantoPkgRecentFragment.this.s(pkgHistoryEntity);
        }

        @Override // com.jd.manto.center.MantoPkgRecentAdapter.c
        /* renamed from: d  reason: merged with bridge method [inline-methods] */
        public void a(PkgHistoryEntity pkgHistoryEntity, View view, int i2) {
            MantoPkgRecentFragment.this.t(pkgHistoryEntity, view, i2);
        }
    }

    /* loaded from: classes17.dex */
    class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ PkgDetailEntity f6395g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ MantoPkgUpdate f6396h;

        c(PkgDetailEntity pkgDetailEntity, MantoPkgUpdate mantoPkgUpdate) {
            this.f6395g = pkgDetailEntity;
            this.f6396h = mantoPkgUpdate;
        }

        @Override // java.lang.Runnable
        public void run() {
            PkgDetailEntity pkgDetailEntity = this.f6395g;
            String str = pkgDetailEntity.appId;
            String str2 = pkgDetailEntity.type;
            PkgHistoryEntity pkgHistoryEntity = new PkgHistoryEntity();
            pkgHistoryEntity.appId = str;
            pkgHistoryEntity.type = str2;
            int indexOf = MantoPkgRecentFragment.this.f6389h.getData().indexOf(new AdapterBeanWrapper(pkgHistoryEntity));
            if (indexOf > -1) {
                try {
                    MantoPkgRecentFragment.this.f6389h.getData().get(indexOf).entity.favorite = this.f6396h.action == MantoPkgUpdate.UpdateAction.FAVO;
                    MantoPkgRecentFragment.this.f6389h.notifyItemChanged(indexOf);
                } catch (Exception unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements PkgManager.PkgHistoryListCallBack {
        d() {
        }

        @Override // com.jingdong.manto.pkg.PkgManager.PkgHistoryListCallBack
        public void onError(Throwable th) {
            MantoPkgRecentFragment.this.u();
        }

        @Override // com.jingdong.manto.pkg.PkgManager.PkgHistoryListCallBack
        public void onSuccess(List<PkgHistoryEntity> list) {
            MantoPkgRecentFragment.this.r(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements Runnable {

        /* loaded from: classes17.dex */
        class a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ List f6399g;

            a(List list) {
                this.f6399g = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoPkgRecentFragment.this.f6393l.b();
                List list = this.f6399g;
                if (list == null || list.size() <= 0) {
                    MantoPkgRecentFragment.this.z();
                } else {
                    MantoPkgRecentFragment.this.f6391j = 1;
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(new AdapterBeanWrapper("\u6700\u8fd1\u4f7f\u7528"));
                    Iterator it = this.f6399g.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new AdapterBeanWrapper((PkgHistoryEntity) it.next()));
                    }
                    MantoPkgRecentFragment.this.f6389h.m(arrayList);
                    if (MantoPkgRecentFragment.this.f6392k) {
                        MantoPkgRecentFragment.this.D("0");
                    }
                    MantoPkgRecentFragment.this.f6392k = false;
                }
                MantoPkgRecentFragment.this.f6388g.e();
            }
        }

        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPkgRecentFragment.this.C(new a(com.jingdong.a.h()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class f implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ List f6401g;

        f(List list) {
            this.f6401g = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPkgRecentFragment.this.f6393l.b();
            MantoPkgRecentFragment mantoPkgRecentFragment = MantoPkgRecentFragment.this;
            if (mantoPkgRecentFragment.f6391j == 1) {
                mantoPkgRecentFragment.f6389h.m(this.f6401g);
            }
            if (MantoPkgRecentFragment.this.f6392k) {
                MantoPkgRecentFragment.this.D("1");
            }
            MantoPkgRecentFragment.this.f6392k = false;
            MantoPkgRecentFragment.this.f6388g.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPkgRecentFragment.this.f6393l.e("\u8fd8\u6ca1\u6709\u6700\u8fd1\u4f7f\u7528\u8bb0\u5f55");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class h implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoPopupWindow f6404g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ PkgHistoryEntity f6405h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f6406i;

        /* loaded from: classes17.dex */
        class a implements PkgManager.PkgFavoCallBack {

            /* renamed from: com.jd.manto.center.MantoPkgRecentFragment$h$a$a  reason: collision with other inner class name */
            /* loaded from: classes17.dex */
            class RunnableC0178a implements Runnable {
                RunnableC0178a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    MantoPkgRecentFragment.this.f6389h.n(h.this.f6406i);
                    if (MantoPkgRecentFragment.this.f6389h.getItemCount() == 0) {
                        MantoPkgRecentFragment.this.z();
                    }
                    PkgDetailEntity pkgDetailEntity = new PkgDetailEntity();
                    PkgHistoryEntity pkgHistoryEntity = h.this.f6405h;
                    pkgDetailEntity.appId = pkgHistoryEntity.appId;
                    pkgDetailEntity.type = pkgHistoryEntity.type;
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(h.this.f6405h.appId);
                    MantoPkgUpdate mantoPkgUpdate = new MantoPkgUpdate();
                    mantoPkgUpdate.detailEntity = pkgDetailEntity;
                    mantoPkgUpdate.action = MantoPkgUpdate.UpdateAction.UNFAVO;
                    MantoAcrossMessageCenter.notifyCommonData(arrayList, mantoPkgUpdate);
                }
            }

            a() {
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
            public void onError(Throwable th) {
                MantoPkgRecentFragment.this.A();
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
            public void onSuccess() {
                MantoPkgRecentFragment.this.C(new RunnableC0178a());
            }
        }

        h(MantoPopupWindow mantoPopupWindow, PkgHistoryEntity pkgHistoryEntity, int i2) {
            this.f6404g = mantoPopupWindow;
            this.f6405h = pkgHistoryEntity;
            this.f6406i = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6404g.dismiss();
            PkgHistoryEntity pkgHistoryEntity = this.f6405h;
            PkgManager.deletePkg(pkgHistoryEntity.appId, pkgHistoryEntity.type, new a());
            HashMap hashMap = new HashMap();
            hashMap.put("appid", this.f6405h.appId);
            MantoListActivity.u("\u5c0f\u7a0b\u5e8f\u5220\u9664", "Applets_Center_Delete", this.f6405h.appId, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class i implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoPopupWindow f6409g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ PkgHistoryEntity f6410h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f6411i;

        /* loaded from: classes17.dex */
        class a implements PkgManager.PkgFavoCallBack {
            final /* synthetic */ PkgCollectEntity a;

            /* renamed from: com.jd.manto.center.MantoPkgRecentFragment$i$a$a  reason: collision with other inner class name */
            /* loaded from: classes17.dex */
            class RunnableC0179a implements Runnable {

                /* renamed from: g  reason: collision with root package name */
                final /* synthetic */ MantoPkgUpdate f6413g;

                RunnableC0179a(MantoPkgUpdate mantoPkgUpdate) {
                    this.f6413g = mantoPkgUpdate;
                }

                @Override // java.lang.Runnable
                public void run() {
                    i iVar = i.this;
                    PkgHistoryEntity pkgHistoryEntity = iVar.f6410h;
                    pkgHistoryEntity.favorite = false;
                    MantoPkgRecentFragment.this.q(pkgHistoryEntity, iVar.f6411i);
                    Intent intent = new Intent("com.jd.manto.center.pkgfavo");
                    intent.putExtra(CartConstant.KEY_VENDOR_ITEM, this.f6413g);
                    LocalBroadcastManager.getInstance(MantoPkgRecentFragment.this.getActivity()).sendBroadcast(intent);
                }
            }

            a(PkgCollectEntity pkgCollectEntity) {
                this.a = pkgCollectEntity;
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
            public void onError(Throwable th) {
                MantoPkgRecentFragment.this.A();
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
            public void onSuccess() {
                MantoPkgUpdate mantoPkgUpdate = new MantoPkgUpdate(new PkgDetailEntity(this.a), MantoPkgUpdate.UpdateAction.UNFAVO);
                com.jingdong.a.v(mantoPkgUpdate, i.this.f6410h.appId);
                MantoPkgRecentFragment.this.C(new RunnableC0179a(mantoPkgUpdate));
            }
        }

        /* loaded from: classes17.dex */
        class b implements PkgManager.PkgFavoCallBack {
            final /* synthetic */ PkgCollectEntity a;

            /* loaded from: classes17.dex */
            class a implements Runnable {

                /* renamed from: g  reason: collision with root package name */
                final /* synthetic */ MantoPkgUpdate f6415g;

                a(MantoPkgUpdate mantoPkgUpdate) {
                    this.f6415g = mantoPkgUpdate;
                }

                @Override // java.lang.Runnable
                public void run() {
                    i iVar = i.this;
                    PkgHistoryEntity pkgHistoryEntity = iVar.f6410h;
                    pkgHistoryEntity.favorite = true;
                    MantoPkgRecentFragment.this.q(pkgHistoryEntity, iVar.f6411i);
                    Intent intent = new Intent("com.jd.manto.center.pkgfavo");
                    intent.putExtra(CartConstant.KEY_VENDOR_ITEM, this.f6415g);
                    LocalBroadcastManager.getInstance(MantoPkgRecentFragment.this.getActivity()).sendBroadcast(intent);
                }
            }

            b(PkgCollectEntity pkgCollectEntity) {
                this.a = pkgCollectEntity;
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
            public void onError(Throwable th) {
                MantoPkgRecentFragment.this.A();
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgFavoCallBack
            public void onSuccess() {
                MantoPkgUpdate mantoPkgUpdate = new MantoPkgUpdate(new PkgDetailEntity(this.a), MantoPkgUpdate.UpdateAction.FAVO);
                com.jingdong.a.v(mantoPkgUpdate, i.this.f6410h.appId);
                MantoPkgRecentFragment.this.C(new a(mantoPkgUpdate));
            }
        }

        i(MantoPopupWindow mantoPopupWindow, PkgHistoryEntity pkgHistoryEntity, int i2) {
            this.f6409g = mantoPopupWindow;
            this.f6410h = pkgHistoryEntity;
            this.f6411i = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str;
            this.f6409g.dismiss();
            ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
            if (!(iLogin == null ? false : iLogin.hasLogin())) {
                Toast.makeText(MantoPkgRecentFragment.this.getContext(), "\u60a8\u5c1a\u672a\u767b\u5f55\uff0c\u64cd\u4f5c\u65e0\u6548", 0).show();
                return;
            }
            PkgCollectEntity pkgCollectEntity = new PkgCollectEntity();
            PkgHistoryEntity pkgHistoryEntity = this.f6410h;
            pkgCollectEntity.appId = pkgHistoryEntity.appId;
            pkgCollectEntity.type = pkgHistoryEntity.type;
            pkgCollectEntity.logo = pkgHistoryEntity.logo;
            pkgCollectEntity.name = pkgHistoryEntity.name;
            if (pkgHistoryEntity.favorite) {
                pkgCollectEntity.favorite = false;
                PkgManager.unFavoPkg(pkgCollectEntity, new a(pkgCollectEntity));
                str = "0";
            } else {
                pkgCollectEntity.favorite = true;
                PkgManager.favoPkg(pkgCollectEntity, new b(pkgCollectEntity));
                str = "1";
            }
            HashMap hashMap = new HashMap();
            hashMap.put("appid", this.f6410h.appId);
            MantoListActivity.u("\u5c0f\u7a0b\u5e8f\u6536\u85cf", "Applets_Center_Collection", this.f6410h.appId + CartConstant.KEY_YB_INFO_LINK + str, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class j extends AnimatorListenerAdapter {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ PkgHistoryEntity f6417g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ View f6418h;

        j(MantoPkgRecentFragment mantoPkgRecentFragment, PkgHistoryEntity pkgHistoryEntity, View view) {
            this.f6417g = pkgHistoryEntity;
            this.f6418h = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (this.f6417g.favorite) {
                return;
            }
            this.f6418h.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        C(new a());
    }

    private final void B(View view, PkgHistoryEntity pkgHistoryEntity, MantoPopupWindow mantoPopupWindow, int i2) {
        TextView textView = (TextView) view.findViewById(R.id.item1);
        if (pkgHistoryEntity.favorite) {
            textView.setText("\u53d6\u6d88\u5173\u6ce8");
        }
        textView.setOnClickListener(new i(mantoPopupWindow, pkgHistoryEntity, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(Runnable runnable) {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void D(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("vapp", "0");
        hashMap.put("vapp_type", "0");
        MantoTrack.sendPagePv(getActivity(), "\u5c0f\u7a0b\u5e8f\u4e2d\u5fc3\u9875", str, "Applets_Center", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void q(PkgHistoryEntity pkgHistoryEntity, int i2) {
        ObjectAnimator ofFloat;
        ObjectAnimator ofFloat2;
        View view;
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.f6388g.findViewHolderForAdapterPosition(i2);
        View findViewById = (findViewHolderForAdapterPosition == null || (view = findViewHolderForAdapterPosition.itemView) == null) ? null : view.findViewById(R.id.favo_icon);
        if (findViewById != null) {
            if (pkgHistoryEntity.favorite) {
                findViewById.setVisibility(0);
                ofFloat = ObjectAnimator.ofFloat(findViewById, "scaleX", 0.0f, 1.0f);
                ofFloat2 = ObjectAnimator.ofFloat(findViewById, "scaleY", 0.0f, 1.0f);
            } else {
                ofFloat = ObjectAnimator.ofFloat(findViewById, "scaleX", 1.0f, 0.0f);
                ofFloat2 = ObjectAnimator.ofFloat(findViewById, "scaleY", 1.0f, 0.0f);
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofFloat, ofFloat2);
            animatorSet.setDuration(200L).start();
            animatorSet.addListener(new j(this, pkgHistoryEntity, findViewById));
            return;
        }
        this.f6389h.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void r(List<PkgHistoryEntity> list) {
        if (list != null && list.size() > 0) {
            if (this.f6391j == 1) {
                this.f6389h.clear();
            }
            ArrayList arrayList = new ArrayList(20);
            arrayList.add(new AdapterBeanWrapper("\u6700\u8fd1\u4f7f\u7528"));
            Iterator<PkgHistoryEntity> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new AdapterBeanWrapper(it.next()));
            }
            C(new f(arrayList));
            return;
        }
        C(new g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void s(PkgHistoryEntity pkgHistoryEntity) {
        if (getActivity() == null || getActivity().isFinishing() || pkgHistoryEntity == null) {
            return;
        }
        LaunchParam launchParam = new LaunchParam();
        launchParam.appId = pkgHistoryEntity.appId;
        launchParam.debugType = pkgHistoryEntity.type;
        com.jingdong.a.p(launchParam, getActivity());
        HashMap hashMap = new HashMap();
        hashMap.put("vapp", "1");
        hashMap.put("vapp_appid", pkgHistoryEntity.appId);
        hashMap.put("source", "myApplets");
        MantoTrack.sendCommonDataWithExt(getContext(), pkgHistoryEntity.name, "Applets_Center_Enter", pkgHistoryEntity.appId, "", "", "", "", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void t(PkgHistoryEntity pkgHistoryEntity, View view, int i2) {
        View findViewById = view.findViewById(R.id.archer);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.manto_center_pkg_recent_popup_favo, (ViewGroup) null, false);
        MantoPopupWindow mantoPopupWindow = new MantoPopupWindow(getContext());
        mantoPopupWindow.setContentView(inflate);
        inflate.findViewById(R.id.item2).setOnClickListener(new h(mantoPopupWindow, pkgHistoryEntity, i2));
        B(inflate, pkgHistoryEntity, mantoPopupWindow, i2);
        mantoPopupWindow.setBackModalColor(0);
        mantoPopupWindow.show(findViewById, 17, 0, -10, MantoDensityUtils.dip2pixel(getContext(), 115), MantoDensityUtils.dip2pixel(getContext(), 90));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void u() {
        com.jingdong.a.f().diskIO().execute(new e());
    }

    private void v() {
        if (this.f6392k) {
            D("0");
        }
        this.f6392k = false;
        if (this.f6390i) {
        }
    }

    private void w() {
        this.f6393l.g();
        PkgManager.getHistoryList(new d());
    }

    public static MantoPkgRecentFragment x() {
        return new MantoPkgRecentFragment();
    }

    private void y(String str) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((MantoListActivity) activity).v(MantoPkgSearchFragment.r(str), "search");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        this.f6388g.setVisibility(8);
        v();
    }

    @Override // com.jingdong.manto.message.MantoAcrossMessage.Listener
    public void onCalled(Object obj) {
        MantoPkgUpdate mantoPkgUpdate;
        PkgDetailEntity pkgDetailEntity;
        MantoPkgRecentAdapter mantoPkgRecentAdapter;
        if (obj == null || !(obj instanceof MantoPkgUpdate) || (pkgDetailEntity = (mantoPkgUpdate = (MantoPkgUpdate) obj).detailEntity) == null || (mantoPkgRecentAdapter = this.f6389h) == null || mantoPkgRecentAdapter.getData().size() <= 0) {
            return;
        }
        C(new c(pkgDetailEntity, mantoPkgUpdate));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        FragmentActivity activity;
        int id = view.getId();
        if (id == R.id.search) {
            y("");
        } else if (id != R.id.back || (activity = getActivity()) == null) {
        } else {
            activity.onBackPressed();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f6392k = true;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.manto_center_pkg_list_fragment, viewGroup, false);
        this.f6393l = (MaeFrameView) inflate.findViewById(R.id.frameView);
        inflate.setClickable(true);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        MantoAcrossMessageCenter.unRegistMainListener(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.f6391j = 1;
        view.findViewById(R.id.search).setOnClickListener(this);
        view.findViewById(R.id.back).setOnClickListener(this);
        PullUpLoadRecyclerView pullUpLoadRecyclerView = (PullUpLoadRecyclerView) view.findViewById(R.id.recyclerview);
        this.f6388g = pullUpLoadRecyclerView;
        pullUpLoadRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.f6388g.setItemAnimator(new DefaultItemAnimator());
        MantoPkgRecentAdapter mantoPkgRecentAdapter = new MantoPkgRecentAdapter(getActivity(), null, new b());
        this.f6389h = mantoPkgRecentAdapter;
        this.f6388g.setAdapter(mantoPkgRecentAdapter);
        w();
        MantoAcrossMessageCenter.registMainListener(this);
    }
}
