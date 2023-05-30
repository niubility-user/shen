package com.jd.manto.center;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSON;
import com.jd.manto.center.MantoCenterRecentlyUsedAdapter;
import com.jd.manto.center.model.MantoCenterMineEntity;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes17.dex */
public class MantoCenterRecentlyUsedActivity extends BaseMantoCenterActivity {

    /* renamed from: h  reason: collision with root package name */
    private String f6317h;

    /* renamed from: i  reason: collision with root package name */
    private View f6318i;

    /* renamed from: j  reason: collision with root package name */
    private RecyclerView f6319j;

    /* renamed from: k  reason: collision with root package name */
    private List<MantoCenterMineEntity.AppInfo> f6320k = new ArrayList(50);

    /* renamed from: l  reason: collision with root package name */
    private MantoCenterRecentlyUsedAdapter f6321l;

    /* renamed from: m  reason: collision with root package name */
    private LinearLayout f6322m;

    /* renamed from: n  reason: collision with root package name */
    private TextView f6323n;
    private LinearLayout o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: com.jd.manto.center.MantoCenterRecentlyUsedActivity$a$a  reason: collision with other inner class name */
        /* loaded from: classes17.dex */
        class RunnableC0177a implements Runnable {
            RunnableC0177a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCenterRecentlyUsedActivity.this.i();
            }
        }

        a() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            MantoCenterRecentlyUsedActivity.this.C((MantoCenterMineEntity.Data) JDJSON.parseObject(httpResponse.getFastJsonObject().toString(), MantoCenterMineEntity.Data.class));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MantoCenterRecentlyUsedActivity.this.post(new RunnableC0177a());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoCenterRecentlyUsedActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jd.manto.center.h.b.q(MantoCenterRecentlyUsedActivity.this, "J_Applets_Top_More", "J_Applets_RecentlyUsed");
            Intent intent = new Intent(MantoCenterRecentlyUsedActivity.this, MantoCenterAboutActivity.class);
            intent.putExtra("tinyAppIntroduction", MantoCenterRecentlyUsedActivity.this.f6317h);
            MantoCenterRecentlyUsedActivity.this.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoCenterRecentlyUsedActivity.this.showEmptyView();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jd.manto.center.k.h.b(MantoCenterRecentlyUsedActivity.this.f6322m);
            MantoCenterRecentlyUsedActivity.this.f6321l.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class f implements MantoCenterRecentlyUsedAdapter.e {
        f() {
        }

        @Override // com.jd.manto.center.MantoCenterRecentlyUsedAdapter.e
        public void a(MantoCenterMineEntity.AppInfo appInfo, int i2) {
            com.jd.manto.center.c.d(MantoCenterRecentlyUsedActivity.this, appInfo.appId, appInfo.appType, "1005");
            com.jd.manto.center.h.b.h(MantoCenterRecentlyUsedActivity.this, appInfo.appId, appInfo.appName, i2 + "", "J_Applets_RecentlyUsed_List");
        }

        @Override // com.jd.manto.center.MantoCenterRecentlyUsedAdapter.e
        public void b(MantoCenterMineEntity.AppInfo appInfo, int i2) {
            MantoCenterRecentlyUsedActivity.this.H(appInfo, i2);
        }

        @Override // com.jd.manto.center.MantoCenterRecentlyUsedAdapter.e
        public void c(MantoCenterMineEntity.AppInfo appInfo, int i2) {
            MantoCenterRecentlyUsedActivity.this.E(appInfo, i2);
            com.jd.manto.center.h.b.g(MantoCenterRecentlyUsedActivity.this, appInfo.appId, appInfo.appName, "1".equals(appInfo.isFollow) ? "0" : "1");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class g implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f6330g;

        g(MantoCenterRecentlyUsedActivity mantoCenterRecentlyUsedActivity, JDDialog jDDialog) {
            this.f6330g = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6330g.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class h implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f6331g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ MantoCenterMineEntity.AppInfo f6332h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f6333i;

        h(JDDialog jDDialog, MantoCenterMineEntity.AppInfo appInfo, int i2) {
            this.f6331g = jDDialog;
            this.f6332h = appInfo;
            this.f6333i = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6331g.dismiss();
            MantoCenterRecentlyUsedActivity.this.D(this.f6332h, this.f6333i);
            MantoCenterRecentlyUsedActivity mantoCenterRecentlyUsedActivity = MantoCenterRecentlyUsedActivity.this;
            MantoCenterMineEntity.AppInfo appInfo = this.f6332h;
            com.jd.manto.center.h.b.h(mantoCenterRecentlyUsedActivity, appInfo.appId, appInfo.appName, this.f6333i + "", "J_Applets_RecentlyUsed_ListDelete");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class i implements HttpGroup.OnCommonListener {

        /* loaded from: classes17.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCenterRecentlyUsedActivity mantoCenterRecentlyUsedActivity = MantoCenterRecentlyUsedActivity.this;
                Toast.makeText(mantoCenterRecentlyUsedActivity, mantoCenterRecentlyUsedActivity.getString(R.string.manto_center_delete_success), 0).show();
            }
        }

        /* loaded from: classes17.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCenterRecentlyUsedActivity mantoCenterRecentlyUsedActivity = MantoCenterRecentlyUsedActivity.this;
                Toast.makeText(mantoCenterRecentlyUsedActivity, mantoCenterRecentlyUsedActivity.getString(R.string.manto_center_delete_failed), 0).show();
            }
        }

        /* loaded from: classes17.dex */
        class c implements Runnable {
            c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCenterRecentlyUsedActivity mantoCenterRecentlyUsedActivity = MantoCenterRecentlyUsedActivity.this;
                Toast.makeText(mantoCenterRecentlyUsedActivity, mantoCenterRecentlyUsedActivity.getString(R.string.manto_center_delete_failed), 0).show();
            }
        }

        i() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse.getFastJsonObject().optBoolean("success")) {
                MantoCenterRecentlyUsedActivity.this.post(new a());
                MantoCenterRecentlyUsedActivity.this.G();
                return;
            }
            MantoCenterRecentlyUsedActivity.this.post(new b());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MantoCenterRecentlyUsedActivity.this.post(new c());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class j implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoCenterMineEntity.AppInfo f6339g;

        /* loaded from: classes17.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if ("1".equals(j.this.f6339g.isFollow)) {
                    MantoCenterRecentlyUsedActivity mantoCenterRecentlyUsedActivity = MantoCenterRecentlyUsedActivity.this;
                    Toast.makeText(mantoCenterRecentlyUsedActivity, mantoCenterRecentlyUsedActivity.getString(R.string.manto_center_unfollow_success), 0).show();
                    return;
                }
                MantoCenterRecentlyUsedActivity mantoCenterRecentlyUsedActivity2 = MantoCenterRecentlyUsedActivity.this;
                Toast.makeText(mantoCenterRecentlyUsedActivity2, mantoCenterRecentlyUsedActivity2.getString(R.string.manto_center_follow_success), 0).show();
            }
        }

        /* loaded from: classes17.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCenterRecentlyUsedActivity mantoCenterRecentlyUsedActivity = MantoCenterRecentlyUsedActivity.this;
                Toast.makeText(mantoCenterRecentlyUsedActivity, mantoCenterRecentlyUsedActivity.getString(R.string.manto_center_delete_failed), 0).show();
            }
        }

        /* loaded from: classes17.dex */
        class c implements Runnable {
            c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoCenterRecentlyUsedActivity mantoCenterRecentlyUsedActivity = MantoCenterRecentlyUsedActivity.this;
                Toast.makeText(mantoCenterRecentlyUsedActivity, mantoCenterRecentlyUsedActivity.getString(R.string.manto_center_delete_failed), 0).show();
            }
        }

        j(MantoCenterMineEntity.AppInfo appInfo) {
            this.f6339g = appInfo;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse.getFastJsonObject().optBoolean("success")) {
                MantoCenterRecentlyUsedActivity.this.post(new a());
                MantoCenterRecentlyUsedActivity.this.G();
                return;
            }
            MantoCenterRecentlyUsedActivity.this.post(new b());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MantoCenterRecentlyUsedActivity.this.post(new c());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class k implements View.OnClickListener {
        k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jd.manto.center.h.b.q(MantoCenterRecentlyUsedActivity.this, "J_Applets_Top_Close", "J_Applets_RecentlyUsed");
            MantoCenterRecentlyUsedActivity.this.sendBroadcast(new Intent("action.exit"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(MantoCenterMineEntity.Data data) {
        if (data != null && !com.jd.manto.center.c.b(data.appList)) {
            this.f6320k.addAll(data.appList);
            if (data.appList.size() > 8) {
                MantoCenterMineEntity.AppInfo appInfo = new MantoCenterMineEntity.AppInfo();
                appInfo.nativeItemType = 1;
                this.f6320k.add(appInfo);
            }
            post(new e());
            return;
        }
        post(new d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(MantoCenterMineEntity.AppInfo appInfo, int i2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(com.jd.manto.center.c.a());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("appType", appInfo.appType);
        httpSetting.putJsonParam("appId", appInfo.appId);
        httpSetting.setEffect(1);
        httpSetting.setFunctionId("tinyAppDelete");
        httpSetting.setListener(new i());
        new HttpGroupUtil().getHttpGroupaAsynPool(this, null).add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(MantoCenterMineEntity.AppInfo appInfo, int i2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(com.jd.manto.center.c.a());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("appType", appInfo.appType);
        httpSetting.putJsonParam("appId", appInfo.appId);
        if ("1".equals(appInfo.isFollow)) {
            httpSetting.putJsonParam("followType", "2");
        } else {
            httpSetting.putJsonParam("followType", "1");
        }
        httpSetting.setEffect(1);
        httpSetting.setFunctionId("tinyAppFollow");
        httpSetting.setListener(new j(appInfo));
        new HttpGroupUtil().getHttpGroupaAsynPool(this, null).add(httpSetting);
    }

    private void F() {
        findViewById(R.id.common_title_home).setOnClickListener(new k());
        this.f6318i.setOnClickListener(new b());
        findViewById(R.id.common_title_option).setOnClickListener(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        this.f6320k.clear();
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(com.jd.manto.center.c.a());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(1);
        httpSetting.setFunctionId("tinyAppHistory");
        httpSetting.setListener(new a());
        new HttpGroupUtil().getHttpGroupaAsynPool(this, null).add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(MantoCenterMineEntity.AppInfo appInfo, int i2) {
        JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(this, "\u786e\u5b9a\u8981\u5220\u9664\u5417\uff1f", null, "\u53d6\u6d88", "\u786e\u5b9a");
        createJdDialogWithStyle6.setMessagePosition(17);
        createJdDialogWithStyle6.setOnLeftButtonClickListener(new g(this, createJdDialogWithStyle6));
        createJdDialogWithStyle6.setOnRightButtonClickListener(new h(createJdDialogWithStyle6, appInfo, i2));
        createJdDialogWithStyle6.show();
    }

    private void initView() {
        setContentView(R.layout.manto_center_recent_activity);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.new_center_title);
        this.o = linearLayout;
        com.jd.manto.center.k.d.e(linearLayout, "2833");
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.manto_discovery_error_container);
        this.f6322m = linearLayout2;
        linearLayout2.setVisibility(8);
        this.f6323n = (TextView) findViewById(R.id.manto_discovery_error_tip);
        View findViewById = findViewById(R.id.common_title_back_text);
        this.f6318i = findViewById;
        findViewById.setVisibility(0);
        findViewById(R.id.common_title_search_container).setVisibility(8);
        this.f6319j = (RecyclerView) findViewById(R.id.recycler);
        this.f6319j.setLayoutManager(new LinearLayoutManager(this, 1, false));
        MantoCenterRecentlyUsedAdapter mantoCenterRecentlyUsedAdapter = new MantoCenterRecentlyUsedAdapter(this, this.f6320k, new f());
        this.f6321l = mantoCenterRecentlyUsedAdapter;
        this.f6319j.setAdapter(mantoCenterRecentlyUsedAdapter);
    }

    public void i() {
        com.jd.manto.center.k.h.l(this.f6322m);
        com.jd.manto.center.k.h.i(this.f6323n, "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.manto.center.BaseMantoCenterActivity, com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        com.jd.manto.center.h.b.p(this, "J_Applets_RecentlyUsed");
        this.f6317h = getIntent().getStringExtra("tinyAppIntroduction");
        initView();
        F();
        G();
    }

    public void showEmptyView() {
        com.jd.manto.center.k.h.l(this.f6322m);
        com.jd.manto.center.k.h.i(this.f6323n, "\u5185\u5bb9\u8d70\u4e22\u5566~");
    }
}
