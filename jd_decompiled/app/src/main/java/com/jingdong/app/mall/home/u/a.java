package com.jingdong.app.mall.home.u;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.app.mall.home.o.a.g;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.businessAddress.JDGlobalAddressManager;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.inter.BaseOverseasListener;
import com.jingdong.common.utils.inter.JDOverseasDialogUtil;
import com.jingdong.common.utils.inter.JDOverseasModel;
import com.jingdong.common.utils.inter.JDOverseasUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.jdtoast.ToastUtils;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class a {
    private static final String o = com.jingdong.app.mall.home.o.a.f.class.getSimpleName();
    private static boolean p;
    private boolean a;
    private boolean b;

    /* renamed from: c */
    private int f10938c;
    private List<com.jingdong.app.mall.home.u.c> d;

    /* renamed from: e */
    private int f10939e;

    /* renamed from: f */
    private m f10940f;

    /* renamed from: g */
    private JDDialog f10941g;

    /* renamed from: h */
    private JDDialog f10942h;

    /* renamed from: i */
    private boolean f10943i;

    /* renamed from: j */
    private String f10944j;

    /* renamed from: k */
    private String f10945k;

    /* renamed from: l */
    private int f10946l;

    /* renamed from: m */
    private boolean f10947m;

    /* renamed from: n */
    private com.jingdong.app.mall.home.u.b f10948n;

    /* renamed from: com.jingdong.app.mall.home.u.a$a */
    /* loaded from: classes4.dex */
    public class DialogInterfaceOnDismissListenerC0329a implements DialogInterface.OnDismissListener {
        DialogInterfaceOnDismissListenerC0329a() {
            a.this = r1;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            EventBus.getDefault().post(new com.jingdong.app.mall.home.floor.common.e("overseas_dialog_close"));
            a.this.f10942h = null;
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ IHomeTitle f10950g;

        /* renamed from: h */
        final /* synthetic */ String f10951h;

        /* renamed from: i */
        final /* synthetic */ String f10952i;

        /* renamed from: j */
        final /* synthetic */ int f10953j;

        /* renamed from: k */
        final /* synthetic */ boolean f10954k;

        b(IHomeTitle iHomeTitle, String str, String str2, int i2, boolean z) {
            a.this = r1;
            this.f10950g = iHomeTitle;
            this.f10951h = str;
            this.f10952i = str2;
            this.f10953j = i2;
            this.f10954k = z;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (this.f10950g == null) {
                return;
            }
            if (a.this.f10943i) {
                this.f10950g.addOverseasSwitchIcon(this.f10951h, this.f10952i, this.f10953j);
            }
            if (this.f10954k) {
                this.f10950g.showOverseasBubbleTips();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ Activity f10956g;

        /* renamed from: h */
        final /* synthetic */ IHomeTitle f10957h;

        /* renamed from: i */
        final /* synthetic */ com.jingdong.app.mall.home.u.b f10958i;

        c(Activity activity, IHomeTitle iHomeTitle, com.jingdong.app.mall.home.u.b bVar) {
            a.this = r1;
            this.f10956g = activity;
            this.f10957h = iHomeTitle;
            this.f10958i = bVar;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.this.J(this.f10956g, this.f10957h, this.f10958i);
        }
    }

    /* loaded from: classes4.dex */
    public class d extends g.b {
        final /* synthetic */ Activity a;
        final /* synthetic */ IHomeTitle b;

        d(Activity activity, IHomeTitle iHomeTitle) {
            a.this = r1;
            this.a = activity;
            this.b = iHomeTitle;
        }

        @Override // com.jingdong.app.mall.home.o.a.g.b
        protected void onFail(JDLocationError jDLocationError) {
            a.this.B(this.a, this.b, null);
        }

        @Override // com.jingdong.app.mall.home.o.a.g.b
        protected void onLbsChanged(com.jingdong.app.mall.home.o.a.i iVar) {
            a.this.B(this.a, this.b, iVar);
        }
    }

    /* loaded from: classes4.dex */
    public class e extends BaseOverseasListener {
        final /* synthetic */ IHomeTitle a;

        e(IHomeTitle iHomeTitle) {
            a.this = r1;
            this.a = iHomeTitle;
        }

        @Override // com.jingdong.common.utils.inter.BaseOverseasListener
        public boolean canShowDialog() {
            return a.this.b && !com.jingdong.app.mall.home.o.a.h.c();
        }

        @Override // com.jingdong.common.utils.inter.BaseOverseasListener
        public void onDialogClose() {
            a.this.I(this.a, true);
        }

        @Override // com.jingdong.common.utils.inter.BaseOverseasListener
        public void onDialogDismiss() {
            EventBus.getDefault().post(new com.jingdong.app.mall.home.floor.common.e("overseas_dialog_close"));
        }

        @Override // com.jingdong.common.utils.inter.BaseOverseasListener
        public void onDialogShow() {
            a.this.f10943i = true;
            com.jingdong.app.mall.home.o.a.h.m();
            EventBus.getDefault().post(new com.jingdong.app.mall.home.floor.common.e("overseas_dialog_show"));
        }

        @Override // com.jingdong.common.utils.inter.BaseOverseasListener
        public void onOverseasChanged(JDOverseasModel jDOverseasModel) {
            a.this.z(jDOverseasModel.getAreaCode(), jDOverseasModel.getAreaName());
        }

        @Override // com.jingdong.common.utils.inter.BaseOverseasListener
        public void unShowDialog() {
            a.this.s();
            a.this.I(this.a, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f implements m {
        final /* synthetic */ Context a;

        /* renamed from: com.jingdong.app.mall.home.u.a$f$a */
        /* loaded from: classes4.dex */
        class C0330a extends com.jingdong.app.mall.home.o.a.b {
            C0330a() {
                f.this = r1;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                f fVar = f.this;
                a.this.H(fVar.a);
            }
        }

        f(Context context) {
            a.this = r1;
            this.a = context;
        }

        @Override // com.jingdong.app.mall.home.u.a.m
        public void a(String str, List<com.jingdong.app.mall.home.u.c> list) {
            com.jingdong.app.mall.home.o.a.f.E0(new C0330a());
        }

        @Override // com.jingdong.app.mall.home.u.a.m
        public void onError() {
            a.this.u(-1);
        }
    }

    /* loaded from: classes4.dex */
    public class g extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ IHomeTitle f10962g;

        /* renamed from: h */
        final /* synthetic */ int f10963h;

        /* renamed from: i */
        final /* synthetic */ String f10964i;

        /* renamed from: j */
        final /* synthetic */ String f10965j;

        g(IHomeTitle iHomeTitle, int i2, String str, String str2) {
            a.this = r1;
            this.f10962g = iHomeTitle;
            this.f10963h = i2;
            this.f10964i = str;
            this.f10965j = str2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (this.f10962g == null) {
                return;
            }
            if (this.f10963h <= 0 && (!a.p || !a.this.f10943i)) {
                this.f10962g.removeOverseasSwitchIcon();
            } else {
                this.f10962g.addOverseasSwitchIcon(this.f10964i, this.f10965j, this.f10963h);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class h implements HttpGroup.OnCommonListener {
        h() {
            a.this = r1;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null) {
                a.this.x(false, "", null);
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                a.this.x(false, "", null);
            } else if (fastJsonObject.optInt("code", -1) != 0) {
                a.this.x(false, "", null);
            } else {
                JDJSONObject optJSONObject = fastJsonObject.optJSONObject("data");
                if (optJSONObject == null) {
                    a.this.x(false, "", null);
                    return;
                }
                String optString = optJSONObject.optString("iconImg");
                JDJSONArray optJSONArray = optJSONObject.optJSONArray("homeAreaList");
                a.this.d = new ArrayList();
                if (optJSONArray != null) {
                    for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                        JDJSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        com.jingdong.app.mall.home.u.c cVar = new com.jingdong.app.mall.home.u.c();
                        cVar.a(optJSONObject2);
                        a.this.d.add(cVar);
                    }
                }
                a aVar = a.this;
                aVar.x(true, optString, aVar.d);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            a.this.x(false, "", null);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes4.dex */
    public class i implements View.OnClickListener {
        i() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.f10942h.dismiss();
        }
    }

    /* loaded from: classes4.dex */
    public class j implements AdapterView.OnItemClickListener {

        /* renamed from: g */
        final /* synthetic */ n f10969g;

        j(n nVar) {
            a.this = r1;
            this.f10969g = nVar;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            a.this.f10939e = i2;
            this.f10969g.b(i2);
        }
    }

    /* loaded from: classes4.dex */
    public class k implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ Context f10971g;

        k(Context context) {
            a.this = r1;
            this.f10971g = context;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str;
            a.this.f10942h.dismiss();
            if (a.this.f10939e >= 0) {
                int i2 = ((com.jingdong.app.mall.home.u.c) a.this.d.get(a.this.f10939e)).a;
                if (i2 != a.this.f10938c) {
                    a.K((com.jingdong.app.mall.home.u.c) a.this.d.get(a.this.f10939e));
                    a aVar = a.this;
                    aVar.z(i2, ((com.jingdong.app.mall.home.u.c) aVar.d.get(a.this.f10939e)).b);
                }
                str = String.valueOf(i2);
            } else {
                str = "";
            }
            Context context = this.f10971g;
            JDMtaUtils.sendCommonData(context, "Home_SwichClick", str, "", context, "", k.class, "", RecommendMtaUtils.Home_PageId);
        }
    }

    /* loaded from: classes4.dex */
    public static class l {
        private static final a a = new a(null);
    }

    /* loaded from: classes4.dex */
    public interface m {
        void a(String str, List<com.jingdong.app.mall.home.u.c> list);

        void onError();
    }

    /* loaded from: classes4.dex */
    public static class n extends BaseAdapter {

        /* renamed from: g */
        private Context f10973g;

        /* renamed from: h */
        private List<com.jingdong.app.mall.home.u.c> f10974h;

        /* renamed from: i */
        private int f10975i;

        /* renamed from: com.jingdong.app.mall.home.u.a$n$a */
        /* loaded from: classes4.dex */
        static class C0331a {
            TextView a;

            /* synthetic */ C0331a(TextView textView, c cVar) {
                this(textView);
            }

            private C0331a(TextView textView) {
                this.a = textView;
            }
        }

        /* synthetic */ n(Context context, List list, c cVar) {
            this(context, list);
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public com.jingdong.app.mall.home.u.c getItem(int i2) {
            return this.f10974h.get(i2);
        }

        public void b(int i2) {
            if (this.f10975i != i2) {
                this.f10975i = i2;
                notifyDataSetChanged();
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f10974h.size();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            C0331a c0331a;
            RelativeLayout relativeLayout;
            com.jingdong.app.mall.home.u.c item = getItem(i2);
            if (view == null) {
                RelativeLayout relativeLayout2 = new RelativeLayout(this.f10973g);
                relativeLayout2.setLayoutParams(new AbsListView.LayoutParams(-1, com.jingdong.app.mall.home.floor.common.d.d(80)));
                TextView textView = new TextView(this.f10973g);
                textView.setTextSize(2, 14.0f);
                textView.setSingleLine(true);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
                layoutParams.addRule(13);
                relativeLayout2.addView(textView, layoutParams);
                c0331a = new C0331a(textView, null);
                relativeLayout2.setTag(c0331a);
                relativeLayout = relativeLayout2;
            } else {
                Object tag = view.getTag();
                com.jingdong.app.mall.home.o.a.f.n(tag);
                c0331a = (C0331a) tag;
                relativeLayout = view;
            }
            c0331a.a.setText(item.b);
            if (i2 == this.f10975i) {
                c0331a.a.setTextColor(this.f10973g.getResources().getColor(R.color.c_F0250F));
            } else {
                c0331a.a.setTextColor(this.f10973g.getResources().getColor(R.color.c_666666));
            }
            return relativeLayout;
        }

        private n(Context context, List<com.jingdong.app.mall.home.u.c> list) {
            this.f10973g = context;
            this.f10974h = list;
        }
    }

    /* synthetic */ a(c cVar) {
        this();
    }

    public void B(Activity activity, IHomeTitle iHomeTitle, com.jingdong.app.mall.home.o.a.i iVar) {
        if (JDOverseasDialogUtil.requestOverseasInfo("appHome", activity, iVar == null ? new JDLocation() : iVar.d(), new e(iHomeTitle))) {
            return;
        }
        s();
    }

    private void E() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("homeArea");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setEffect(0);
        httpSetting.setListener(new h());
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void H(Context context) {
        com.jingdong.app.mall.home.u.c cVar;
        List<com.jingdong.app.mall.home.u.c> list = this.d;
        if (list != null && list.size() != 0) {
            if (this.b) {
                JDDialog jDDialog = this.f10941g;
                if (jDDialog == null || !jDDialog.isShowing()) {
                    JDDialog jDDialog2 = this.f10942h;
                    if (jDDialog2 == null || !jDDialog2.isShowing()) {
                        this.f10939e = 0;
                        int currentOverseasArea = JDOverseasUtil.getCurrentOverseasArea();
                        this.f10938c = currentOverseasArea;
                        c cVar2 = null;
                        if (currentOverseasArea >= 0 && this.d.get(0).a != this.f10938c) {
                            Iterator<com.jingdong.app.mall.home.u.c> it = this.d.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    cVar = null;
                                    break;
                                }
                                cVar = it.next();
                                if (cVar.a == this.f10938c) {
                                    break;
                                }
                            }
                            if (cVar != null) {
                                this.d.remove(cVar);
                                this.d.add(0, cVar);
                            } else {
                                this.f10939e = -1;
                            }
                        }
                        JDDialog newJDDialog = JDDialog.newJDDialog(context);
                        this.f10942h = newJDDialog;
                        newJDDialog.setContentView(R.layout.home_page_overseas_choose);
                        this.f10942h.setCanceledOnTouchOutside(false);
                        ((ImageButton) this.f10942h.findViewById(R.id.jd_dialog_close)).setOnClickListener(new i());
                        n nVar = new n(context, this.d, cVar2);
                        nVar.b(this.f10939e);
                        ListView listView = (ListView) this.f10942h.findViewById(R.id.lv_overseas);
                        listView.setOverScrollMode(2);
                        listView.setAdapter((ListAdapter) nVar);
                        listView.setOnItemClickListener(new j(nVar));
                        ((Button) this.f10942h.findViewById(R.id.jd_dialog_pos_button)).setOnClickListener(new k(context));
                        this.f10942h.show();
                        com.jingdong.app.mall.home.r.c.a.w(context, "Home_SwitchPopupsExpo", String.valueOf(this.f10938c), "", RecommendMtaUtils.Home_PageId);
                        EventBus.getDefault().post(new com.jingdong.app.mall.home.floor.common.e("overseas_dialog_show"));
                        this.f10942h.setOnDismissListener(new DialogInterfaceOnDismissListenerC0329a());
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        u(0);
        ToastUtils.longToast(context, JdSdk.getInstance().getApplication().getString(R.string.overseas_list_exception_tip));
    }

    public void I(IHomeTitle iHomeTitle, boolean z) {
        String str;
        if (p) {
            String str2 = this.f10944j;
            int i2 = this.f10946l;
            String str3 = this.f10945k;
            if (str3 != null && str3.length() > 3) {
                str = this.f10945k.substring(0, 3);
            } else {
                str = this.f10945k;
            }
            com.jingdong.app.mall.home.o.a.f.E0(new b(iHomeTitle, str2, str, i2, z));
        }
    }

    public static void K(com.jingdong.app.mall.home.u.c cVar) {
        if (com.jingdong.app.mall.home.o.a.n.a()) {
            com.jingdong.app.mall.home.o.a.f.r0(o, "have location permission, no need to update address");
        } else if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.UPDATE_ADDRESS_DISABLED, false)) {
            com.jingdong.app.mall.home.o.a.f.r0(o, "update address disabled");
        } else if (cVar != null && !cVar.b()) {
            AddressGlobal addressGlobal = new AddressGlobal();
            addressGlobal.setIdProvince(cVar.f10976c);
            addressGlobal.setProvinceName(cVar.d);
            addressGlobal.setIdCity(cVar.f10977e);
            addressGlobal.setCityName(cVar.f10978f);
            com.jingdong.app.mall.home.o.a.f.r0(o, addressGlobal.toString(System.currentTimeMillis()));
            JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption(JDOverseasDialogUtil.OVERSEAS_LBS_ID);
            jDLbsHttpOption.setSceneId("basicShoppingProcess");
            JDGlobalAddressManager.updateAddressGlobal(jDLbsHttpOption, addressGlobal);
        } else {
            com.jingdong.app.mall.home.o.a.f.r0(o, "address is invalid");
        }
    }

    public void s() {
        com.jingdong.app.mall.home.u.b bVar = this.f10948n;
        if (bVar != null) {
            bVar.a();
        }
        EventBus.getDefault().post(new com.jingdong.app.mall.home.floor.common.e("overseas_confirm_no_dialog"));
    }

    public void u(int i2) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("type", (Object) String.valueOf(i2));
        ExceptionReporter.reportLive("821", jDJSONObject.toJSONString(), "1");
    }

    public static a w() {
        return l.a;
    }

    public void x(boolean z, String str, List<com.jingdong.app.mall.home.u.c> list) {
        if (!z) {
            m mVar = this.f10940f;
            if (mVar != null) {
                mVar.onError();
            }
        } else {
            m mVar2 = this.f10940f;
            if (mVar2 != null) {
                mVar2.a(str, list);
            }
        }
        this.f10940f = null;
    }

    public void z(int i2, String str) {
        this.f10938c = i2;
        L(i2);
        com.jingdong.app.mall.home.o.a.f.r0(o, "overseas change to areaCode:" + i2 + ", areaName:" + str);
        EventBus.getDefault().post(new com.jingdong.app.mall.home.floor.common.e("overseas_change_area"));
    }

    public void A() {
        JDDialog jDDialog = this.f10941g;
        if (jDDialog != null && jDDialog.isShowing()) {
            this.f10941g.cancel();
            this.f10941g = null;
        }
        JDDialog jDDialog2 = this.f10942h;
        if (jDDialog2 == null || !jDDialog2.isShowing()) {
            return;
        }
        this.f10942h.cancel();
        this.f10942h = null;
    }

    public void C(Context context) {
        this.b = true;
    }

    public void D(boolean z) {
        this.b = z;
    }

    public void F(boolean z) {
        this.f10947m = z;
    }

    public void G(Context context) {
        JDDialog jDDialog = this.f10942h;
        if (jDDialog == null || !jDDialog.isShowing()) {
            if (this.f10940f == null) {
                this.f10940f = new f(context);
            }
            E();
        }
    }

    public void J(Activity activity, IHomeTitle iHomeTitle, com.jingdong.app.mall.home.u.b bVar) {
        this.f10948n = bVar;
        if (JDPrivacyHelper.isAcceptPrivacy(activity) && !com.jingdong.app.mall.home.floor.common.i.g.l()) {
            if (!this.a && !y()) {
                if (com.jingdong.app.mall.home.o.a.f.p0()) {
                    com.jingdong.app.mall.home.o.a.f.E0(new c(activity, iHomeTitle, bVar));
                    return;
                }
                this.a = true;
                JDDialog jDDialog = this.f10942h;
                if (jDDialog == null || !jDDialog.isShowing()) {
                    if (this.b) {
                        com.jingdong.app.mall.home.o.a.g.g(new d(activity, iHomeTitle));
                        return;
                    } else {
                        s();
                        return;
                    }
                }
                return;
            }
            s();
            return;
        }
        this.a = true;
        s();
    }

    public void L(int i2) {
        if (JDOverseasUtil.getCurrentOverseasArea() == i2) {
            return;
        }
        JDOverseasUtil.updateCurrentOverseasArea(i2);
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 != null) {
            z0.r0();
        }
    }

    public void t(JDJSONObject jDJSONObject, @NonNull IHomeTitle iHomeTitle) {
        this.f10946l = -1;
        if (jDJSONObject != null) {
            this.f10944j = jDJSONObject.optString("img");
            this.f10945k = jDJSONObject.optString("text");
            this.f10946l = jDJSONObject.optInt("homeAreaCode");
            com.jingdong.app.mall.home.a.f8563l = jDJSONObject.optInt("popCount");
            String str = this.f10945k;
            if (str != null && str.length() > 3) {
                this.f10945k = this.f10945k.substring(0, 3);
            }
            p = jDJSONObject.optBoolean("areaSwitch", false);
        } else {
            p = false;
        }
        if (com.jingdong.app.mall.home.a.f8563l <= 0) {
            com.jingdong.app.mall.home.a.f8563l = Integer.MAX_VALUE;
        }
        com.jingdong.app.mall.home.o.a.f.E0(new g(iHomeTitle, this.f10946l, this.f10944j, this.f10945k));
    }

    public int v() {
        int currentOverseasArea = JDOverseasUtil.getCurrentOverseasArea();
        this.f10938c = currentOverseasArea;
        return Math.max(currentOverseasArea, 0);
    }

    public boolean y() {
        return this.f10947m || com.jingdong.app.mall.home.v.d.a.f();
    }

    private a() {
        this.b = true;
        this.f10938c = -1;
        this.f10944j = "";
        this.f10945k = "";
        this.f10946l = -1;
        this.f10938c = JDOverseasUtil.getCurrentOverseasArea();
    }
}
