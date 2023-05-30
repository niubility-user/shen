package com.jingdong.app.mall.home;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.base.HomeImageView;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.floor.view.view.HomeFooterView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.inter.JDOverseasUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.jdtoast.ToastUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class BaseOverseaSelectActivity extends BaseActivity {
    private static boolean t;

    /* renamed from: g  reason: collision with root package name */
    private RelativeLayout f8454g;

    /* renamed from: h  reason: collision with root package name */
    private LinearLayout f8455h;

    /* renamed from: i  reason: collision with root package name */
    private FrameLayout f8456i;

    /* renamed from: j  reason: collision with root package name */
    private HomeFooterView f8457j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f8458k;

    /* renamed from: l  reason: collision with root package name */
    private ListView f8459l;

    /* renamed from: m  reason: collision with root package name */
    private List<com.jingdong.app.mall.home.u.c> f8460m;

    /* renamed from: n  reason: collision with root package name */
    private int f8461n = -1;
    private int o = -1;
    private g p;
    private long q;
    private ShapeDrawable r;
    private ShapeDrawable s;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HomeFooterView.RetryListener {
        a() {
        }

        @Override // com.jingdong.app.mall.home.floor.view.view.HomeFooterView.RetryListener
        public void emptyRetry() {
            BaseOverseaSelectActivity.this.initData();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BaseOverseaSelectActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements HttpGroup.OnCommonListener {

        /* loaded from: classes4.dex */
        class a extends com.jingdong.app.mall.home.o.a.b {
            a() {
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                BaseOverseaSelectActivity.this.K();
            }
        }

        c() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null) {
                BaseOverseaSelectActivity.this.showError();
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                BaseOverseaSelectActivity.this.showError();
                return;
            }
            JDJSONObject optJSONObject = fastJsonObject.optJSONObject("data");
            if (optJSONObject == null) {
                BaseOverseaSelectActivity.this.showError();
                return;
            }
            JDJSONArray optJSONArray = optJSONObject.optJSONArray("homeAreaList");
            if (optJSONArray == null || optJSONArray.size() <= 0) {
                BaseOverseaSelectActivity.this.showError();
                return;
            }
            BaseOverseaSelectActivity.this.f8460m = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                JDJSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                com.jingdong.app.mall.home.u.c cVar = new com.jingdong.app.mall.home.u.c();
                cVar.a(optJSONObject2);
                BaseOverseaSelectActivity.this.f8460m.add(cVar);
            }
            com.jingdong.app.mall.home.o.a.f.E0(new a());
            BaseOverseaSelectActivity.this.f8457j.setFooterState(4);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            BaseOverseaSelectActivity.this.showError();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (BaseOverseaSelectActivity.this.f8455h != null) {
                BaseOverseaSelectActivity.this.f8455h.setVisibility(8);
            }
            if (BaseOverseaSelectActivity.this.f8457j != null) {
                BaseOverseaSelectActivity.this.f8457j.setFooterState(3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements AdapterView.OnItemClickListener {
        e() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            long currentTimeMillis = System.currentTimeMillis();
            if (i2 >= BaseOverseaSelectActivity.this.f8460m.size() || i2 == BaseOverseaSelectActivity.this.o || currentTimeMillis - BaseOverseaSelectActivity.this.q < 500) {
                return;
            }
            BaseOverseaSelectActivity.this.q = currentTimeMillis;
            BaseOverseaSelectActivity baseOverseaSelectActivity = BaseOverseaSelectActivity.this;
            baseOverseaSelectActivity.L(i2, (com.jingdong.app.mall.home.u.c) baseOverseaSelectActivity.f8460m.get(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.u.c f8467g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f8468h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ JDDialog f8469i;

        f(com.jingdong.app.mall.home.u.c cVar, int i2, JDDialog jDDialog) {
            this.f8467g = cVar;
            this.f8468h = i2;
            this.f8469i = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
            bVar.a("code", String.valueOf(this.f8467g.a));
            com.jingdong.app.mall.home.r.c.a.s("Home_SwichConfirm", "", bVar.toString());
            BaseOverseaSelectActivity.this.f8458k.setText(String.format("\u5f53\u524d\u9009\u62e9\u7684\u56fd\u5bb6/\u5730\u533a: %s", this.f8467g.b));
            BaseOverseaSelectActivity.this.o = this.f8468h;
            BaseOverseaSelectActivity.this.p.c(this.f8468h);
            com.jingdong.app.mall.home.u.a.w().L(this.f8467g.a);
            com.jingdong.app.mall.home.u.a.K(this.f8467g);
            this.f8469i.dismiss();
            BaseOverseaSelectActivity.this.finish();
        }
    }

    /* loaded from: classes4.dex */
    public static class g extends BaseAdapter {

        /* renamed from: g  reason: collision with root package name */
        private Context f8471g;

        /* renamed from: h  reason: collision with root package name */
        private List<com.jingdong.app.mall.home.u.c> f8472h;

        /* renamed from: i  reason: collision with root package name */
        private int f8473i;

        /* loaded from: classes4.dex */
        static class a {
            TextView a;
            ImageView b;

            /* synthetic */ a(TextView textView, ImageView imageView, a aVar) {
                this(textView, imageView);
            }

            private a(TextView textView, ImageView imageView) {
                this.a = textView;
                this.b = imageView;
            }
        }

        /* synthetic */ g(Context context, List list, a aVar) {
            this(context, list);
        }

        @Override // android.widget.Adapter
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public com.jingdong.app.mall.home.u.c getItem(int i2) {
            return this.f8472h.get(i2);
        }

        public void c(int i2) {
            if (this.f8473i != i2) {
                this.f8473i = i2;
                notifyDataSetChanged();
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f8472h.size();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            a aVar;
            RelativeLayout relativeLayout;
            com.jingdong.app.mall.home.u.c item = getItem(i2);
            if (view == null) {
                RelativeLayout relativeLayout2 = new RelativeLayout(this.f8471g);
                relativeLayout2.setLayoutParams(new AbsListView.LayoutParams(-1, com.jingdong.app.mall.home.floor.common.d.d(100)));
                TextView textView = new TextView(this.f8471g);
                textView.setPadding(0, 0, com.jingdong.app.mall.home.floor.common.d.d(80), 0);
                textView.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(28));
                textView.setSingleLine(true);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setGravity(16);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
                layoutParams.leftMargin = DPIUtil.dip2px(18.0f);
                relativeLayout2.addView(textView, layoutParams);
                HomeImageView homeImageView = new HomeImageView(this.f8471g);
                homeImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                homeImageView.setImageResource(R.drawable.home_area_selected);
                homeImageView.setVisibility(4);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(24), com.jingdong.app.mall.home.floor.common.d.d(24));
                layoutParams2.rightMargin = DPIUtil.dip2px(18.0f);
                layoutParams2.addRule(11);
                layoutParams2.addRule(15);
                relativeLayout2.addView(homeImageView, layoutParams2);
                aVar = new a(textView, homeImageView, null);
                relativeLayout2.setTag(aVar);
                relativeLayout = relativeLayout2;
            } else {
                aVar = (a) view.getTag();
                relativeLayout = view;
            }
            aVar.a.setText(item.b);
            aVar.a.setTextColor(BaseOverseaSelectActivity.z());
            if (i2 == this.f8473i) {
                aVar.b.setVisibility(0);
            } else {
                aVar.b.setVisibility(4);
            }
            return relativeLayout;
        }

        private g(Context context, List<com.jingdong.app.mall.home.u.c> list) {
            this.f8471g = context;
            this.f8472h = list;
        }
    }

    private static int J() {
        return t ? -1250068 : -14277082;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K() {
        a aVar;
        com.jingdong.app.mall.home.u.c cVar;
        List<com.jingdong.app.mall.home.u.c> list = this.f8460m;
        if (list != null && list.size() != 0) {
            int i2 = this.f8461n;
            if (i2 < 0) {
                i2 = 0;
            }
            this.f8461n = i2;
            Iterator<com.jingdong.app.mall.home.u.c> it = this.f8460m.iterator();
            while (true) {
                aVar = null;
                if (!it.hasNext()) {
                    cVar = null;
                    break;
                }
                cVar = it.next();
                if (cVar.a == this.f8461n) {
                    break;
                }
            }
            if (cVar != null) {
                this.o = 0;
                this.f8460m.remove(cVar);
                this.f8460m.add(0, cVar);
            } else {
                this.o = -1;
            }
            com.jingdong.app.mall.home.u.c cVar2 = this.f8460m.get(0);
            TextView textView = this.f8458k;
            Object[] objArr = new Object[1];
            objArr[0] = this.o == -1 ? "" : cVar2.b;
            textView.setText(String.format("\u5f53\u524d\u9009\u62e9\u7684\u56fd\u5bb6/\u5730\u533a: %s", objArr));
            this.f8458k.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(28));
            this.f8458k.setGravity(16);
            this.f8458k.setTextColor(J());
            this.f8458k.setPadding(DPIUtil.dip2px(18.0f), 0, 0, 0);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f8458k.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = com.jingdong.app.mall.home.floor.common.d.d(120);
            g gVar = new g(this, this.f8460m, aVar);
            this.p = gVar;
            gVar.f8473i = this.o;
            this.f8459l.setAdapter((ListAdapter) this.p);
            this.f8459l.setOnItemClickListener(new e());
            this.f8455h.setVisibility(0);
            return;
        }
        ToastUtils.shortToast(this, JdSdk.getInstance().getApplication().getString(R.string.overseas_list_exception_tip));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L(int i2, com.jingdong.app.mall.home.u.c cVar) {
        if (cVar == null) {
            return;
        }
        JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(this, "\u5207\u6362\u56fd\u5bb6/\u5730\u533a\u5230" + cVar.b, getString(R.string.home_dialog_my_jd_overseas_cancel), getString(R.string.home_dialog_my_jd_overseas_confirm));
        TextView textView = createJdDialogWithStyle2.messageView;
        if (textView != null) {
            textView.setMaxLines(2);
            createJdDialogWithStyle2.messageView.setEllipsize(TextUtils.TruncateAt.END);
        }
        createJdDialogWithStyle2.setOnRightButtonClickListener(new f(cVar, i2, createJdDialogWithStyle2));
        com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
        bVar.a("code", String.valueOf(cVar.a));
        com.jingdong.app.mall.home.r.c.a.y("Home_SwitchAreaPopupExpo", "", bVar.toString());
        createJdDialogWithStyle2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initData() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("homeArea");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setEffect(0);
        httpSetting.setListener(new c());
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private void initView() {
        this.f8454g = (RelativeLayout) findViewById(R.id.ll_header);
        TextView textView = (TextView) findViewById(R.id.tv_selected_city);
        this.f8458k = textView;
        textView.setBackgroundDrawable(this.r);
        ListView listView = (ListView) findViewById(R.id.lv_city_list);
        this.f8459l = listView;
        listView.setBackgroundDrawable(this.s);
        this.f8455h = (LinearLayout) findViewById(R.id.ll_content);
        this.f8456i = (FrameLayout) findViewById(R.id.error_layout);
        HomeFooterView homeFooterView = new HomeFooterView(this);
        this.f8457j = homeFooterView;
        homeFooterView.setRetryListener(new a());
        this.f8456i.addView(this.f8457j);
        this.f8454g.setBackgroundColor(t ? -14869733 : -1);
        this.f8455h.setBackgroundColor(t ? -16119543 : IconFloorEntity.BGCOLOR_DEFAULT);
        if (t) {
            this.f8459l.getDivider().setColorFilter(-13619666, PorterDuff.Mode.SRC);
        }
        HomeImageView homeImageView = new HomeImageView(this);
        homeImageView.setImageResource(t ? R.drawable.common_title_back_selected : R.drawable.common_title_back_normal);
        homeImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        homeImageView.setPadding(com.jingdong.app.mall.home.floor.common.d.d(10), com.jingdong.app.mall.home.floor.common.d.d(10), com.jingdong.app.mall.home.floor.common.d.d(10), com.jingdong.app.mall.home.floor.common.d.d(10));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(60), com.jingdong.app.mall.home.floor.common.d.d(60));
        layoutParams.leftMargin = com.jingdong.app.mall.home.floor.common.d.d(26);
        layoutParams.addRule(15);
        this.f8454g.addView(homeImageView, layoutParams);
        homeImageView.setOnClickListener(new b());
        TextView textView2 = new TextView(this);
        textView2.setText("\u5207\u6362\u56fd\u5bb6/\u5730\u533a");
        textView2.setTextColor(J());
        textView2.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(36));
        textView2.setGravity(17);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        this.f8454g.addView(textView2, layoutParams2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showError() {
        com.jingdong.app.mall.home.o.a.f.E0(new d());
    }

    static /* synthetic */ int z() {
        return J();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.home_oversea_activity);
        t = isAppDarkMode();
        this.statusBarTintEnable = true;
        this.statusBarDarkModeEnable = true;
        statusBarRefresh();
        findViewById(R.id.main_container).setBackgroundColor(t ? -16119543 : IconFloorEntity.BGCOLOR_DEFAULT);
        this.f8461n = JDOverseasUtil.getCurrentOverseasArea();
        float dip2px = DPIUtil.dip2px(12.0f);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{0.0f, 0.0f, 0.0f, 0.0f, dip2px, dip2px, dip2px, dip2px}, null, null));
        this.r = shapeDrawable;
        shapeDrawable.getPaint().setAntiAlias(true);
        this.r.getPaint().setColor(t ? -14869733 : -1);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(new RoundRectShape(new float[]{dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px}, null, null));
        this.s = shapeDrawable2;
        shapeDrawable2.getPaint().setAntiAlias(true);
        this.s.getPaint().setColor(t ? -14869733 : -1);
        initView();
        initData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        com.jingdong.app.mall.home.r.c.a.y("Home_SwitchAreaExpo", "", "");
    }
}
