package com.jd.manto.center;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSON;
import com.jd.manto.center.MantoCenterMineAdapter;
import com.jd.manto.center.model.MantoCenterMineEntity;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes17.dex */
public class MantoCenterMineFragment extends Fragment {

    /* renamed from: g */
    private RecyclerView f6296g;

    /* renamed from: i */
    private MantoCenterMineAdapter f6298i;

    /* renamed from: k */
    private MantoNewCenterActivity f6300k;

    /* renamed from: l */
    private String f6301l;

    /* renamed from: m */
    private LinearLayout f6302m;

    /* renamed from: n */
    private TextView f6303n;

    /* renamed from: h */
    private List<MantoCenterMineEntity.AppInfo> f6297h = new ArrayList(50);

    /* renamed from: j */
    private boolean f6299j = true;

    /* loaded from: classes17.dex */
    class a extends GridLayoutManager.SpanSizeLookup {
        a() {
            MantoCenterMineFragment.this = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i2) {
            int i3 = ((MantoCenterMineEntity.AppInfo) MantoCenterMineFragment.this.f6297h.get(i2)).nativeItemType;
            return (i3 == 0 || i3 == 5) ? 4 : 1;
        }
    }

    /* loaded from: classes17.dex */
    class b implements MantoCenterMineAdapter.g {
        b() {
            MantoCenterMineFragment.this = r1;
        }

        @Override // com.jd.manto.center.MantoCenterMineAdapter.g
        public void a(MantoCenterMineEntity.AppInfo appInfo, int i2) {
            MantoCenterMineFragment.this.n(appInfo, i2);
        }

        @Override // com.jd.manto.center.MantoCenterMineAdapter.g
        public void b(MantoCenterMineEntity.AppInfo appInfo, int i2) {
            MantoCenterMineFragment.this.q(appInfo, i2);
        }
    }

    /* loaded from: classes17.dex */
    class c implements RecyclerView.OnItemTouchListener {
        c() {
            MantoCenterMineFragment.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NotNull RecyclerView recyclerView, @NotNull MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1 && recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY()) == null) {
                if (MantoCenterMineFragment.this.f6298i.t()) {
                    MantoCenterMineFragment.this.f6298i.u();
                }
                return true;
            }
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NotNull RecyclerView recyclerView, @NotNull MotionEvent motionEvent) {
        }
    }

    /* loaded from: classes17.dex */
    public class d implements HttpGroup.OnCommonListener {

        /* loaded from: classes17.dex */
        class a implements Runnable {
            a() {
                d.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MantoCenterMineFragment.this.getContext(), MantoCenterMineFragment.this.getString(R.string.manto_center_delete_success), 0).show();
                MantoCenterMineFragment.this.s();
            }
        }

        /* loaded from: classes17.dex */
        class b implements Runnable {
            b() {
                d.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MantoCenterMineFragment.this.getContext(), MantoCenterMineFragment.this.getString(R.string.manto_center_delete_failed), 0).show();
            }
        }

        /* loaded from: classes17.dex */
        class c implements Runnable {
            c() {
                d.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MantoCenterMineFragment.this.getContext(), MantoCenterMineFragment.this.getString(R.string.manto_center_delete_failed), 0).show();
            }
        }

        d() {
            MantoCenterMineFragment.this = r1;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse.getFastJsonObject().optBoolean("success")) {
                MantoCenterMineFragment.this.r(new a());
            } else {
                MantoCenterMineFragment.this.r(new b());
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MantoCenterMineFragment.this.r(new c());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes17.dex */
    public class e implements HttpGroup.OnCommonListener {

        /* loaded from: classes17.dex */
        class a implements Runnable {
            a() {
                e.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MantoCenterMineFragment.this.getContext(), MantoCenterMineFragment.this.getString(R.string.manto_center_delete_success), 0).show();
                MantoCenterMineFragment.this.s();
            }
        }

        /* loaded from: classes17.dex */
        class b implements Runnable {
            b() {
                e.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MantoCenterMineFragment.this.getContext(), MantoCenterMineFragment.this.getString(R.string.manto_center_delete_failed), 0).show();
            }
        }

        /* loaded from: classes17.dex */
        class c implements Runnable {
            c() {
                e.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MantoCenterMineFragment.this.getContext(), MantoCenterMineFragment.this.getString(R.string.manto_center_delete_failed), 0).show();
            }
        }

        e() {
            MantoCenterMineFragment.this = r1;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse.getFastJsonObject().optBoolean("success")) {
                MantoCenterMineFragment.this.r(new a());
            } else {
                MantoCenterMineFragment.this.r(new b());
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MantoCenterMineFragment.this.r(new c());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes17.dex */
    public class f implements Runnable {
        f() {
            MantoCenterMineFragment.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jd.manto.center.k.h.b(MantoCenterMineFragment.this.f6302m);
            MantoCenterMineFragment.this.f6298i.notifyDataSetChanged();
        }
    }

    /* loaded from: classes17.dex */
    public class g implements HttpGroup.OnCommonListener {

        /* loaded from: classes17.dex */
        class a implements Runnable {
            a() {
                g.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCenterMineFragment.this.i();
            }
        }

        g() {
            MantoCenterMineFragment.this = r1;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            MantoCenterMineFragment.this.m((MantoCenterMineEntity) JDJSON.parseObject(httpResponse.getJSONObject().toString(), MantoCenterMineEntity.class));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MantoCenterMineFragment.this.r(new a());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes17.dex */
    public class h implements Runnable {
        h() {
            MantoCenterMineFragment.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MantoCenterMineFragment.this.f6300k != null) {
                MantoCenterMineFragment.this.f6300k.y(1);
            }
            MantoCenterMineFragment.this.f6299j = false;
        }
    }

    /* loaded from: classes17.dex */
    public class i implements Runnable {
        i() {
            MantoCenterMineFragment.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MantoCenterMineFragment.this.f6300k != null) {
                MantoCenterMineFragment.this.f6300k.y(1);
            }
            MantoCenterMineFragment.this.f6299j = false;
        }
    }

    private void k(MantoCenterMineEntity.AppInfo appInfo) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(com.jd.manto.center.c.a());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("appType", appInfo.appType);
        httpSetting.putJsonParam("appId", appInfo.appId);
        httpSetting.putJsonParam("followType", "2");
        httpSetting.setEffect(1);
        httpSetting.setFunctionId("tinyAppFollow");
        httpSetting.setListener(new d());
        new HttpGroupUtil().getHttpGroupaAsynPool((IMyActivity) getActivity(), null).add(httpSetting);
    }

    private void l(MantoCenterMineEntity.AppInfo appInfo) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(com.jd.manto.center.c.a());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("appType", appInfo.appType);
        httpSetting.putJsonParam("appId", appInfo.appId);
        httpSetting.setEffect(1);
        httpSetting.setFunctionId("tinyAppDelete");
        httpSetting.setListener(new e());
        new HttpGroupUtil().getHttpGroupaAsynPool((IMyActivity) getActivity(), null).add(httpSetting);
    }

    public void m(MantoCenterMineEntity mantoCenterMineEntity) {
        this.f6297h.clear();
        if (mantoCenterMineEntity != null && !com.jd.manto.center.c.b(mantoCenterMineEntity.data)) {
            String str = mantoCenterMineEntity.tinyAppIntroduction;
            this.f6301l = str;
            MantoNewCenterActivity mantoNewCenterActivity = this.f6300k;
            if (mantoNewCenterActivity != null) {
                mantoNewCenterActivity.z(mantoCenterMineEntity.searchWordText, str);
            }
            Iterator<MantoCenterMineEntity.Data> it = mantoCenterMineEntity.data.iterator();
            while (it.hasNext()) {
                o(it.next());
            }
            u();
            if (this.f6299j && com.jd.manto.center.c.b(mantoCenterMineEntity.data.get(0).appList)) {
                r(new i());
                return;
            }
            return;
        }
        MantoCenterMineEntity.Data data = new MantoCenterMineEntity.Data();
        data.type = "follows";
        data.title = "\u6211\u7684\u5173\u6ce8";
        o(data);
        u();
        if (mantoCenterMineEntity != null) {
            String str2 = mantoCenterMineEntity.tinyAppIntroduction;
            this.f6301l = str2;
            MantoNewCenterActivity mantoNewCenterActivity2 = this.f6300k;
            if (mantoNewCenterActivity2 != null) {
                mantoNewCenterActivity2.z(mantoCenterMineEntity.searchWordText, str2);
            }
        }
        if (this.f6299j) {
            r(new h());
        }
    }

    public void n(MantoCenterMineEntity.AppInfo appInfo, int i2) {
        if (appInfo == null) {
            return;
        }
        int i3 = appInfo.nativeItemType;
        if (i3 == 1 || i3 == 2) {
            com.jd.manto.center.c.d(getActivity(), appInfo.appId, appInfo.appType, i3 == 1 ? "1005" : "1006");
            com.jd.manto.center.h.b.f(getContext(), appInfo.appId, appInfo.appName, appInfo.nativeItemType == 1 ? "J_Applets_My_Recently" : "J_Applets_My_Follows");
        } else if (i3 == 3) {
            Intent intent = new Intent(getActivity(), MantoCenterRecentlyUsedActivity.class);
            intent.putExtra("tinyAppIntroduction", this.f6301l);
            startActivity(intent);
            com.jd.manto.center.h.a.c(getContext(), "J_Applets_My_More", "", "", "J_Applets_My", "", "", "", null);
        } else if (i3 == 4) {
            MantoNewCenterActivity mantoNewCenterActivity = this.f6300k;
            if (mantoNewCenterActivity != null) {
                mantoNewCenterActivity.y(1);
            }
            com.jd.manto.center.h.a.c(getContext(), "J_Applets_My_Add", "", "", "J_Applets_My", "", "", "", null);
        }
    }

    private void o(MantoCenterMineEntity.Data data) {
        if (data == null || TextUtils.isEmpty(data.type) || TextUtils.isEmpty(data.title)) {
            return;
        }
        if ("history".equals(data.type)) {
            if (com.jd.manto.center.c.c(data.appList)) {
                MantoCenterMineEntity.AppInfo appInfo = new MantoCenterMineEntity.AppInfo();
                appInfo.title = data.title;
                appInfo.nativeItemType = 0;
                this.f6297h.add(appInfo);
                if (data.appList.size() > 7) {
                    for (int i2 = 0; i2 < 7; i2++) {
                        MantoCenterMineEntity.AppInfo appInfo2 = data.appList.get(i2);
                        appInfo2.nativeItemType = 1;
                        this.f6297h.add(appInfo2);
                    }
                    MantoCenterMineEntity.AppInfo appInfo3 = new MantoCenterMineEntity.AppInfo();
                    appInfo3.appName = "\u66f4\u591a";
                    appInfo3.nativeItemType = 3;
                    this.f6297h.add(appInfo3);
                    return;
                }
                for (MantoCenterMineEntity.AppInfo appInfo4 : data.appList) {
                    appInfo4.nativeItemType = 1;
                    this.f6297h.add(appInfo4);
                }
            }
        } else if ("follows".equals(data.type)) {
            MantoCenterMineEntity.AppInfo appInfo5 = new MantoCenterMineEntity.AppInfo();
            appInfo5.title = data.title;
            appInfo5.nativeItemType = 0;
            this.f6297h.add(appInfo5);
            if (com.jd.manto.center.c.c(data.appList)) {
                for (MantoCenterMineEntity.AppInfo appInfo6 : data.appList) {
                    appInfo6.nativeItemType = 2;
                    this.f6297h.add(appInfo6);
                }
            }
            MantoCenterMineEntity.AppInfo appInfo7 = new MantoCenterMineEntity.AppInfo();
            appInfo7.appName = "\u6dfb\u52a0";
            appInfo7.nativeItemType = 4;
            this.f6297h.add(appInfo7);
            MantoCenterMineEntity.AppInfo appInfo8 = new MantoCenterMineEntity.AppInfo();
            appInfo8.nativeItemType = 5;
            this.f6297h.add(appInfo8);
        }
    }

    public void q(MantoCenterMineEntity.AppInfo appInfo, int i2) {
        if (appInfo == null) {
            return;
        }
        int i3 = appInfo.nativeItemType;
        if (1 == i3) {
            l(appInfo);
            com.jd.manto.center.h.b.f(getContext(), appInfo.appId, appInfo.appName, "J_Applets_My_RecentlyDelete");
        } else if (2 == i3) {
            k(appInfo);
            com.jd.manto.center.h.b.f(getContext(), appInfo.appId, appInfo.appName, "J_Applets_My_FollowsDelete");
        }
    }

    public void r(Runnable runnable) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        getActivity().runOnUiThread(runnable);
    }

    private void u() {
        r(new f());
    }

    public void i() {
        com.jd.manto.center.k.h.l(this.f6302m);
        com.jd.manto.center.k.h.i(this.f6303n, "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc");
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.manto_center_mine_fragment, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        com.jd.manto.center.h.b.p(getContext(), "J_Applets_My");
        s();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        com.jd.manto.center.h.b.p(getContext(), "J_Applets_My");
        s();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f6296g = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.manto_discovery_error_container);
        this.f6302m = linearLayout;
        linearLayout.setVisibility(8);
        this.f6303n = (TextView) view.findViewById(R.id.manto_discovery_error_tip);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        this.f6296g.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new a());
        MantoCenterMineAdapter mantoCenterMineAdapter = new MantoCenterMineAdapter(getActivity(), this.f6297h, new b());
        this.f6298i = mantoCenterMineAdapter;
        this.f6296g.setAdapter(mantoCenterMineAdapter);
        this.f6296g.addOnItemTouchListener(new c());
    }

    public void s() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(com.jd.manto.center.c.a());
        httpSetting.setEffect(1);
        httpSetting.setFunctionId("tinyAppHome");
        httpSetting.setListener(new g());
        new HttpGroupUtil().getHttpGroupaAsynPool((IMyActivity) getActivity(), null).add(httpSetting);
    }

    public void t(MantoNewCenterActivity mantoNewCenterActivity) {
        this.f6300k = mantoNewCenterActivity;
    }
}
