package com.jingdong.app.mall.pavilion;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.personel.MyCommonActivity;
import com.jingdong.app.mall.utils.LoginUser;
import com.jingdong.common.entity.Commercial;
import com.jingdong.common.utils.CommonBridge;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class PavilionListActivity extends MyCommonActivity {

    /* renamed from: h  reason: collision with root package name */
    private Context f11463h;

    /* renamed from: i  reason: collision with root package name */
    private ListView f11464i;

    /* renamed from: j  reason: collision with root package name */
    private FrameLayout f11465j;

    /* renamed from: k  reason: collision with root package name */
    private Button f11466k;

    /* renamed from: l  reason: collision with root package name */
    ArrayList<h> f11467l;

    /* renamed from: m  reason: collision with root package name */
    private f f11468m;

    /* renamed from: n  reason: collision with root package name */
    private View f11469n;
    private int o;

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PavilionListActivity.this.D("0");
        }
    }

    /* loaded from: classes4.dex */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDMtaUtils.onClick(PavilionListActivity.this, "ThemeStreet_BacktoTop", PavilionListActivity.class.getName());
            PavilionListActivity.this.f11469n.setVisibility(8);
            PavilionListActivity.this.f11464i.setSelection(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f11472g;

        /* loaded from: classes4.dex */
        class a implements Runnable {

            /* renamed from: com.jingdong.app.mall.pavilion.PavilionListActivity$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes4.dex */
            class RunnableC0368a implements Runnable {

                /* renamed from: com.jingdong.app.mall.pavilion.PavilionListActivity$c$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes4.dex */
                class RunnableC0369a implements Runnable {
                    RunnableC0369a() {
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        c cVar = c.this;
                        PavilionListActivity.this.D(cVar.f11472g);
                    }
                }

                RunnableC0368a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PavilionListActivity.this.post(new RunnableC0369a());
                }
            }

            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                LoginUser.getInstance().executeLoginRunnable(PavilionListActivity.this, new RunnableC0368a());
            }
        }

        /* loaded from: classes4.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PavilionListActivity.this.f11465j.setVisibility(0);
                PavilionListActivity.this.f11464i.setVisibility(4);
            }
        }

        /* renamed from: com.jingdong.app.mall.pavilion.PavilionListActivity$c$c  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class RunnableC0370c implements Runnable {
            RunnableC0370c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PavilionListActivity.this.f11464i.setVisibility(0);
                PavilionListActivity.this.f11464i.setAdapter((ListAdapter) PavilionListActivity.this.f11468m);
                PavilionListActivity.this.f11465j.setVisibility(4);
            }
        }

        c(String str) {
            this.f11472g = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (Log.D) {
                Log.d("PavilionListActivity", " loadPavilionList -->> onEnd . json -> " + jSONObject);
            }
            if ("0".equals(jSONObject.optString("code"))) {
                JSONArrayPoxy jSONArrayOrNull = jSONObject.getJSONArrayOrNull("topicList");
                PavilionListActivity pavilionListActivity = PavilionListActivity.this;
                pavilionListActivity.f11467l = pavilionListActivity.E(jSONArrayOrNull);
                PavilionListActivity.this.f11468m = new f();
                PavilionListActivity.this.post(new RunnableC0370c());
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (Log.D) {
                Log.d("PavilionListActivity", " postPavilionList -->> onError ");
            }
            if (3 - httpError.getJsonCode() == 0) {
                PavilionListActivity.this.post(new a());
            } else {
                PavilionListActivity.this.post(new b());
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            httpSettingParams.putJsonParam("sort", this.f11472g);
        }
    }

    /* loaded from: classes4.dex */
    static class d {
        TextView a;
        TextView b;

        /* renamed from: c  reason: collision with root package name */
        TextView f11479c;
        ImageView d;

        /* renamed from: e  reason: collision with root package name */
        String f11480e;

        /* renamed from: f  reason: collision with root package name */
        RelativeLayout f11481f;

        d() {
        }
    }

    /* loaded from: classes4.dex */
    private class e implements AbsListView.OnScrollListener {

        /* loaded from: classes4.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PavilionListActivity.this.f11469n.setVisibility(0);
            }
        }

        /* loaded from: classes4.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PavilionListActivity.this.f11469n.setVisibility(8);
            }
        }

        private e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            PavilionListActivity.this.o = i2 + i3;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (PavilionListActivity.this.f11468m != null) {
                if (PavilionListActivity.this.o >= PavilionListActivity.this.f11468m.getCount() - 1) {
                    if (PavilionListActivity.this.f11469n.getVisibility() == 8) {
                        PavilionListActivity.this.post(new a(), 100);
                    }
                } else if (PavilionListActivity.this.f11469n.getVisibility() == 0) {
                    PavilionListActivity.this.post(new b(), 100);
                }
            }
        }

        /* synthetic */ e(PavilionListActivity pavilionListActivity, a aVar) {
            this();
        }
    }

    /* loaded from: classes4.dex */
    class f extends BaseAdapter {

        /* loaded from: classes4.dex */
        class a implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ String f11486g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ String f11487h;

            /* renamed from: i  reason: collision with root package name */
            final /* synthetic */ int f11488i;

            /* renamed from: j  reason: collision with root package name */
            final /* synthetic */ String f11489j;

            a(String str, String str2, int i2, String str3) {
                this.f11486g = str;
                this.f11487h = str2;
                this.f11488i = i2;
                this.f11489j = str3;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(this.f11486g)) {
                    return;
                }
                JDMtaUtils.onClick(PavilionListActivity.this, "ThemeStreet_GotoStreet", PavilionListActivity.class.getName(), this.f11487h);
                Commercial commercial = new Commercial();
                commercial.ynShare = Integer.valueOf(this.f11488i);
                commercial.title = this.f11489j;
                String str = this.f11486g;
                commercial.action = str;
                CommonBridge.goToMWithUrl(PavilionListActivity.this, str, commercial);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class b implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ String f11491g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ String f11492h;

            /* renamed from: i  reason: collision with root package name */
            final /* synthetic */ int f11493i;

            b(String str, String str2, int i2) {
                this.f11491g = str;
                this.f11492h = str2;
                this.f11493i = i2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(this.f11491g)) {
                    return;
                }
                JDMtaUtils.onClick(PavilionListActivity.this, "ThemeStreet_GotoStreet", PavilionListActivity.class.getName(), this.f11492h);
                Commercial commercial = new Commercial();
                commercial.ynShare = Integer.valueOf(this.f11493i);
                CommonBridge.goToMWithUrl(PavilionListActivity.this, this.f11491g, commercial);
            }
        }

        public f() {
        }

        public void a(h hVar, i iVar, int i2) {
            g gVar = hVar.a.get(i2);
            iVar.k(i2).setText(gVar.a);
            iVar.h(i2).setText(gVar.f11497f);
            if (gVar.f11500i == 1 && !TextUtils.isEmpty(gVar.f11499h)) {
                iVar.l(i2).setBackgroundColor(-828836);
                iVar.l(i2).setText(gVar.f11499h);
            } else if (gVar.f11500i != 2 || TextUtils.isEmpty(gVar.f11499h)) {
                iVar.l(i2).setText("");
                iVar.l(i2).setBackgroundResource(0);
            } else {
                iVar.l(i2).setText("");
                iVar.l(i2).setBackgroundResource(0);
            }
            if (iVar.i(i2).getDrawable() == null || iVar.j(i2) == null || !iVar.j(i2).equals(gVar.f11495c)) {
                iVar.n(i2, gVar.f11495c);
                JDImageUtils.displayImage(gVar.f11495c, iVar.i(i2));
            }
            iVar.m(i2).setOnClickListener(new b(gVar.d, gVar.f11496e, gVar.f11498g));
            iVar.m(i2).setVisibility(0);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            ArrayList<h> arrayList = PavilionListActivity.this.f11467l;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return PavilionListActivity.this.f11467l.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i2) {
            return ((h) getItem(i2)).b == 1 ? 0 : 1;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            d dVar;
            i iVar;
            String str;
            int itemViewType = getItemViewType(i2);
            d dVar2 = null;
            if (view == null) {
                if (itemViewType != 0) {
                    if (itemViewType == 1) {
                        view = LayoutInflater.from(PavilionListActivity.this.f11463h).inflate(R.layout.pavilion_list_vertical_item, viewGroup, false);
                        iVar = new i();
                        iVar.a = (TextView) view.findViewById(R.id.pavilion_small_title1);
                        iVar.b = (TextView) view.findViewById(R.id.pavilion_pv_copy1);
                        iVar.f11501c = (TextView) view.findViewById(R.id.pavilion_tag1);
                        iVar.d = (ImageView) view.findViewById(R.id.pavilion_small_img1);
                        iVar.p = (RelativeLayout) view.findViewById(R.id.pavilion_vertical_item1);
                        iVar.f11503f = (TextView) view.findViewById(R.id.pavilion_small_title2);
                        iVar.f11504g = (TextView) view.findViewById(R.id.pavilion_pv_copy2);
                        iVar.f11505h = (TextView) view.findViewById(R.id.pavilion_tag2);
                        iVar.f11506i = (ImageView) view.findViewById(R.id.pavilion_small_img2);
                        iVar.q = (RelativeLayout) view.findViewById(R.id.pavilion_vertical_item2);
                        iVar.f11508k = (TextView) view.findViewById(R.id.pavilion_small_title3);
                        iVar.f11509l = (TextView) view.findViewById(R.id.pavilion_pv_copy3);
                        iVar.f11510m = (TextView) view.findViewById(R.id.pavilion_tag3);
                        iVar.f11511n = (ImageView) view.findViewById(R.id.pavilion_small_img3);
                        iVar.r = (RelativeLayout) view.findViewById(R.id.pavilion_vertical_item3);
                        view.setTag(iVar);
                    }
                    iVar = null;
                } else {
                    view = LayoutInflater.from(PavilionListActivity.this.f11463h).inflate(R.layout.pavilion_list_horizontal_item, viewGroup, false);
                    dVar = new d();
                    dVar.f11481f = (RelativeLayout) view.findViewById(R.id.pavilion_horizontal_item);
                    dVar.a = (TextView) view.findViewById(R.id.pavilion_big_title);
                    dVar.b = (TextView) view.findViewById(R.id.pavilion_pv_copy);
                    dVar.f11479c = (TextView) view.findViewById(R.id.pavilion_tag);
                    dVar.d = (ImageView) view.findViewById(R.id.pavilion_big_img);
                    view.setTag(dVar);
                    dVar2 = dVar;
                    iVar = null;
                }
            } else if (itemViewType != 0) {
                if (itemViewType == 1) {
                    iVar = (i) view.getTag();
                }
                iVar = null;
            } else {
                dVar = (d) view.getTag();
                dVar2 = dVar;
                iVar = null;
            }
            h hVar = (h) getItem(i2);
            if (itemViewType != 0) {
                if (itemViewType == 1 && hVar != null) {
                    int size = hVar.a.size();
                    if (size == 1) {
                        a(hVar, iVar, 0);
                        iVar.m(1).setVisibility(4);
                        iVar.m(2).setVisibility(4);
                    } else if (size == 2) {
                        a(hVar, iVar, 0);
                        a(hVar, iVar, 1);
                        iVar.m(2).setVisibility(4);
                    } else if (size == 3) {
                        a(hVar, iVar, 0);
                        a(hVar, iVar, 1);
                        a(hVar, iVar, 2);
                    }
                }
            } else if (hVar != null && hVar.a.size() > 0) {
                g gVar = hVar.a.get(0);
                dVar2.a.setText(gVar.a);
                dVar2.b.setText(gVar.f11497f);
                if (gVar.f11500i == 1 && !TextUtils.isEmpty(gVar.f11499h)) {
                    dVar2.f11479c.setBackgroundColor(-828836);
                    dVar2.f11479c.setText(gVar.f11499h);
                } else if (gVar.f11500i == 2 && !TextUtils.isEmpty(gVar.f11499h)) {
                    dVar2.f11479c.setText("");
                    dVar2.f11479c.setBackgroundResource(0);
                } else {
                    dVar2.f11479c.setText("");
                    dVar2.f11479c.setBackgroundResource(0);
                }
                if (dVar2.d.getDrawable() == null || (str = dVar2.f11480e) == null || !str.equals(gVar.b)) {
                    String str2 = gVar.b;
                    dVar2.f11480e = str2;
                    JDImageUtils.displayImage(str2, dVar2.d);
                }
                dVar2.f11481f.setOnClickListener(new a(gVar.d, gVar.f11496e, gVar.f11498g, gVar.a));
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class g {
        String a;
        String b;

        /* renamed from: c  reason: collision with root package name */
        String f11495c;
        String d;

        /* renamed from: e  reason: collision with root package name */
        String f11496e;

        /* renamed from: f  reason: collision with root package name */
        String f11497f;

        /* renamed from: g  reason: collision with root package name */
        int f11498g;

        /* renamed from: h  reason: collision with root package name */
        String f11499h;

        /* renamed from: i  reason: collision with root package name */
        int f11500i;

        g(PavilionListActivity pavilionListActivity, JSONObjectProxy jSONObjectProxy) {
            this.a = jSONObjectProxy.optString("title");
            this.b = jSONObjectProxy.optString("bigImg");
            this.f11495c = jSONObjectProxy.optString("smallImg");
            this.d = jSONObjectProxy.optString("url");
            this.f11496e = jSONObjectProxy.optString("sourceValue");
            this.f11497f = jSONObjectProxy.optString("pvCopy");
            this.f11499h = jSONObjectProxy.optString("tag");
            this.f11498g = jSONObjectProxy.optInt("isShared");
            this.f11500i = jSONObjectProxy.optInt("tagColor");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class h {
        ArrayList<g> a;
        int b;

        h() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class i {
        TextView a;
        TextView b;

        /* renamed from: c  reason: collision with root package name */
        TextView f11501c;
        ImageView d;

        /* renamed from: e  reason: collision with root package name */
        String f11502e;

        /* renamed from: f  reason: collision with root package name */
        TextView f11503f;

        /* renamed from: g  reason: collision with root package name */
        TextView f11504g;

        /* renamed from: h  reason: collision with root package name */
        TextView f11505h;

        /* renamed from: i  reason: collision with root package name */
        ImageView f11506i;

        /* renamed from: j  reason: collision with root package name */
        String f11507j;

        /* renamed from: k  reason: collision with root package name */
        TextView f11508k;

        /* renamed from: l  reason: collision with root package name */
        TextView f11509l;

        /* renamed from: m  reason: collision with root package name */
        TextView f11510m;

        /* renamed from: n  reason: collision with root package name */
        ImageView f11511n;
        String o;
        RelativeLayout p;
        RelativeLayout q;
        RelativeLayout r;

        i() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public TextView h(int i2) {
            if (i2 == 0) {
                return this.b;
            }
            if (i2 == 1) {
                return this.f11504g;
            }
            return this.f11509l;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ImageView i(int i2) {
            if (i2 == 0) {
                return this.d;
            }
            if (i2 == 1) {
                return this.f11506i;
            }
            return this.f11511n;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String j(int i2) {
            if (i2 == 0) {
                return this.f11502e;
            }
            if (i2 == 1) {
                return this.f11507j;
            }
            return this.o;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public TextView k(int i2) {
            if (i2 == 0) {
                return this.a;
            }
            if (i2 == 1) {
                return this.f11503f;
            }
            return this.f11508k;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public TextView l(int i2) {
            if (i2 == 0) {
                return this.f11501c;
            }
            if (i2 == 1) {
                return this.f11505h;
            }
            return this.f11510m;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public RelativeLayout m(int i2) {
            if (i2 == 0) {
                return this.p;
            }
            if (i2 == 1) {
                return this.q;
            }
            return this.r;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void n(int i2, String str) {
            if (i2 == 0) {
                this.f11502e = str;
            } else if (i2 == 1) {
                this.f11507j = str;
            } else {
                this.o = str;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(String str) {
        if (Log.D) {
            Log.d("PavilionListActivity", " postPavilionList -->> in ");
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("topicList");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setPost(true);
        httpSetting.setEffect(0);
        httpSetting.setListener(new c(str));
        getHttpGroupaAsynPool().add(httpSetting);
    }

    public ArrayList<h> E(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy == null || jSONArrayPoxy.length() == 0) {
            return null;
        }
        ArrayList<h> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
            JSONObjectProxy jSONObjectOrNull = jSONArrayPoxy.getJSONObjectOrNull(i3);
            if (jSONObjectOrNull != null) {
                if ((i3 >= 0 && i3 < 3) || (i3 >= 12 && i3 < 15)) {
                    g gVar = new g(this, jSONObjectOrNull);
                    ArrayList<g> arrayList3 = new ArrayList<>();
                    arrayList3.add(gVar);
                    h hVar = new h();
                    hVar.b = 1;
                    hVar.a = arrayList3;
                    arrayList.add(hVar);
                } else {
                    i2++;
                    arrayList2.add(new g(this, jSONObjectOrNull));
                    if (i2 > 2) {
                        h hVar2 = new h();
                        hVar2.b = 0;
                        ArrayList<g> arrayList4 = new ArrayList<>();
                        arrayList4.addAll(arrayList2);
                        hVar2.a = arrayList4;
                        arrayList.add(hVar2);
                        arrayList2.clear();
                        i2 = 0;
                    }
                }
            }
        }
        if (arrayList2.size() > 0) {
            h hVar3 = new h();
            hVar3.b = 0;
            ArrayList<g> arrayList5 = new ArrayList<>();
            arrayList5.addAll(arrayList2);
            hVar3.a = arrayList5;
            arrayList.add(hVar3);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.personel.MyCommonActivity, com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f11463h = this;
        setContentView((RelativeLayout) LayoutInflater.from(this).inflate(R.layout.pavilion_list, (ViewGroup) null));
        ((TextView) findViewById(R.id.fd)).setText(R.string.pavilion_list_title);
        setTitleBack((ImageView) findViewById(R.id.fe));
        this.f11465j = (FrameLayout) findViewById(R.id.main_loading_error_tips);
        Button button = (Button) findViewById(R.id.loading_error_but);
        this.f11466k = button;
        button.setOnClickListener(new a());
        View findViewById = findViewById(R.id.snap_to_top);
        this.f11469n = findViewById;
        findViewById.setOnClickListener(new b());
        ListView listView = (ListView) findViewById(R.id.pavilion_list);
        this.f11464i = listView;
        listView.setOnScrollListener(new e(this, null));
        this.f11464i.addFooterView(ImageUtil.inflate(R.layout.pavilion_list_footer, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.personel.MyCommonActivity, com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        D("0");
    }
}
