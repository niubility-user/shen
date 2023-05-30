package com.jingdong.app.mall.home.o.a;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Environment;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.widget.HomeDebugItemDecoration;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.app.mall.home.widget.JsonView;
import com.jingdong.app.mall.home.widget.JsonViewLayout;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.JDSharedCommandUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.jdtoast.ToastUtils;
import de.greenrobot.event.EventBus;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class k {

    /* renamed from: e */
    static com.jingdong.app.mall.home.o.a.j f10479e;
    private static final AtomicBoolean a = new AtomicBoolean(true);
    private static final ConcurrentHashMap<String, Long> b = new ConcurrentHashMap<>();

    /* renamed from: c */
    static int f10478c = 0;
    static String d = "";

    /* renamed from: f */
    static boolean f10480f = false;

    /* renamed from: g */
    static boolean f10481g = false;

    /* renamed from: h */
    static boolean f10482h = false;

    /* renamed from: i */
    public static String f10483i = "";

    /* renamed from: j */
    static String f10484j = "welcomeHome.txt";

    /* renamed from: k */
    private static StringBuilder f10485k = new StringBuilder();

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ TextView f10486g;

        a(TextView textView) {
            this.f10486g = textView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDSharedCommandUtils.getInstance().saveShareText(this.f10486g.getText().toString());
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.app.mall.home.state.old.a.c();
        }
    }

    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ TextView f10487g;

        c(TextView textView) {
            this.f10487g = textView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (k.f10479e != null) {
                k.i();
                this.f10487g.setText(k.f10479e.c());
            }
            com.jingdong.app.mall.home.v.d.a.c();
        }
    }

    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.app.mall.home.state.dark.a.c();
            EventBus.getDefault().post(new BaseEvent("babel_get_data", com.jingdong.app.mall.home.state.dark.a.h() ? "1" : "0"));
        }
    }

    /* loaded from: classes4.dex */
    public class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.app.mall.home.v.c.a.f(!com.jingdong.app.mall.home.v.c.a.e());
        }
    }

    /* loaded from: classes4.dex */
    public class f implements MessageQueue.IdleHandler {
        f() {
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            com.jingdong.app.mall.home.s.a.b().o();
            k.g("notifyData");
            return false;
        }
    }

    /* loaded from: classes4.dex */
    public class g implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ TextView f10488g;

        g(TextView textView) {
            this.f10488g = textView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDSharedCommandUtils.getInstance().saveShareText(this.f10488g.getText().toString());
            ToastUtil.showToast("\u590d\u5236\u6210\u529f");
        }
    }

    /* loaded from: classes4.dex */
    public class h implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ EditText f10489g;

        h(EditText editText) {
            this.f10489g = editText;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f10489g.setText("");
        }
    }

    /* loaded from: classes4.dex */
    public class i implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ RelativeLayout f10490g;

        /* renamed from: h */
        final /* synthetic */ ScrollView f10491h;

        /* renamed from: i */
        final /* synthetic */ HorizontalScrollView f10492i;

        i(RelativeLayout relativeLayout, ScrollView scrollView, HorizontalScrollView horizontalScrollView) {
            this.f10490g = relativeLayout;
            this.f10491h = scrollView;
            this.f10492i = horizontalScrollView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f10490g.removeView(this.f10491h);
            this.f10490g.removeView(this.f10492i);
        }
    }

    /* loaded from: classes4.dex */
    public class j implements View.OnClickListener {
        j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            k.y();
        }
    }

    /* renamed from: com.jingdong.app.mall.home.o.a.k$k */
    /* loaded from: classes4.dex */
    public class ViewOnClickListenerC0311k implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ RelativeLayout f10493g;

        /* renamed from: h */
        final /* synthetic */ int f10494h;

        ViewOnClickListenerC0311k(RelativeLayout relativeLayout, int i2) {
            this.f10493g = relativeLayout;
            this.f10494h = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HttpResponse B0;
            JDHomeFragment z0 = JDHomeFragment.z0();
            if (z0 == null || (B0 = z0.B0()) == null) {
                return;
            }
            com.jingdong.app.mall.home.o.a.j jVar = k.f10479e;
            if (jVar == null) {
                k.A(this.f10493g, this.f10494h, B0.getFastJsonObject());
            } else {
                jVar.a(view.getContext(), B0.getFastJsonObject());
            }
        }
    }

    /* loaded from: classes4.dex */
    public class l implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ EditText f10495g;

        /* renamed from: h */
        final /* synthetic */ ScrollView f10496h;

        /* renamed from: i */
        final /* synthetic */ TextView f10497i;

        l(EditText editText, ScrollView scrollView, TextView textView) {
            this.f10495g = editText;
            this.f10496h = scrollView;
            this.f10497i = textView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int g2 = com.jingdong.app.mall.home.n.h.c.g(this.f10495g.getText().toString());
            k.f10478c = g2;
            k.d = String.valueOf(g2);
            boolean z = this.f10496h.getVisibility() == 0;
            this.f10497i.setText(z ? "\u5c55\u5f00\u5185\u5bb9" : "\u6536\u8d77\u5185\u5bb9");
            this.f10496h.setVisibility(z ? 8 : 0);
        }
    }

    /* loaded from: classes4.dex */
    public class m implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ TextView f10498g;

        m(TextView textView) {
            this.f10498g = textView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f10498g.setText(k.f10485k.toString());
        }
    }

    /* loaded from: classes4.dex */
    public class n implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ TextView f10499g;

        n(TextView textView) {
            this.f10499g = textView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f10499g.setText(com.jingdong.app.mall.home.s.a.b().c());
        }
    }

    /* loaded from: classes4.dex */
    public static class o extends AppCompatTextView {
        public o(Context context, String str, int i2) {
            super(context);
            setMinWidth(com.jingdong.app.mall.home.floor.common.d.f9279g / 4);
            setMinHeight(i2);
            setMaxHeight(i2);
            setText(str);
            setTextColor(-1);
            setGravity(1);
            setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(30));
        }
    }

    public static void A(RelativeLayout relativeLayout, int i2, JDJSONObject jDJSONObject) {
        int i3 = R.id.homefloor_extend_bg;
        View findViewById = relativeLayout.findViewById(i3);
        if (findViewById == null) {
            JsonViewLayout jsonViewLayout = new JsonViewLayout(relativeLayout.getContext());
            jsonViewLayout.setId(i3);
            jsonViewLayout.setBackgroundColor(-3342388);
            jsonViewLayout.m(-7829368);
            jsonViewLayout.f();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.bottomMargin = i2;
            relativeLayout.addView(jsonViewLayout, layoutParams);
            jsonViewLayout.b(jDJSONObject.toJSONString());
            n(jsonViewLayout);
            return;
        }
        relativeLayout.removeView(findViewById);
    }

    public static boolean B() {
        return f10478c > 0 && (f10480f || f10482h);
    }

    public static void d() {
        if (a.getAndSet(false)) {
            g("notifyData");
            Looper.myQueue().addIdleHandler(new f());
        }
    }

    public static void e(String str) {
        try {
            StringBuilder sb = f10485k;
            if (sb != null) {
                sb.append(str);
                f10485k.append("\r\n");
            }
            com.jingdong.app.mall.home.o.a.f.r0("addLog", str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void f(Object... objArr) {
        try {
            if (f10485k != null) {
                for (Object obj : objArr) {
                    f10485k.append(obj);
                    f10485k.append("\r\n");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void g(String str) {
        h(str, false);
    }

    public static void h(String str, boolean z) {
        if (f10485k != null) {
            ConcurrentHashMap<String, Long> concurrentHashMap = b;
            Long remove = concurrentHashMap.remove(str);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            String valueOf = String.valueOf(elapsedRealtime - com.jingdong.app.mall.c.b());
            if (remove != null && !z) {
                str.concat(" end: ").concat(valueOf).concat(" used: ").concat(String.valueOf(elapsedRealtime - remove.longValue()));
                return;
            }
            concurrentHashMap.put(str, Long.valueOf(elapsedRealtime));
            str.concat(" start: ").concat(valueOf);
        }
    }

    public static void i() {
        com.jingdong.app.mall.home.o.a.j jVar = f10479e;
        if (jVar != null) {
            jVar.b();
        }
    }

    public static void j(RelativeLayout relativeLayout, HomeRecycleView homeRecycleView) {
        if (relativeLayout == null) {
            return;
        }
        String userPin = LoginUserBase.getUserPin();
        if (!TextUtils.equals(userPin, "zhangdapengvip") && !TextUtils.equals(userPin, "jd_5f47085b808d1") && (!f10480f || !f10482h)) {
            f10485k = null;
            return;
        }
        try {
            f10482h = true;
            homeRecycleView.addItemDecoration(new HomeDebugItemDecoration());
            String str = System.getProperty("os.name") + System.getProperty("java.specification.vendor");
            e("\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014");
            e("OS : " + str);
            e("homeVersion : " + q());
            e("jdKey : " + SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.SWITCH_SHARE_JCOMM_SPOT_CH, ""));
            e(PackageInfoUtil.getVersionName() + "\uff08" + PackageInfoUtil.getVersionCode() + "\uff09");
            e("\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014");
            g("show logInfo");
            z(relativeLayout);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void k(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (Exception unused) {
        }
    }

    public static void l(Reader reader) {
        try {
            reader.close();
        } catch (Exception unused) {
        }
    }

    public static void m(Writer writer) {
        try {
            writer.close();
        } catch (Exception unused) {
        }
    }

    private static void n(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof JsonView) {
                ((JsonView) childAt).expand();
                return;
            }
            if (childAt instanceof ViewGroup) {
                n((ViewGroup) childAt);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.InputStream] */
    public static String o(String str) {
        Throwable th;
        BufferedReader bufferedReader;
        Exception e2;
        ?? applicationContext = JdSdk.getInstance().getApplicationContext();
        try {
            try {
                str = applicationContext.getResources().getAssets().open(str);
            } catch (Exception e3) {
                bufferedReader = null;
                e2 = e3;
                str = 0;
            } catch (Throwable th2) {
                applicationContext = 0;
                th = th2;
                str = 0;
            }
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(str));
            } catch (Exception e4) {
                e2 = e4;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                applicationContext = 0;
                k(str);
                l(applicationContext);
                throw th;
            }
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine.trim());
                    } else {
                        k(str);
                        l(bufferedReader);
                        String sb2 = sb.toString();
                        k(str);
                        l(bufferedReader);
                        return sb2;
                    }
                }
            } catch (Exception e5) {
                e2 = e5;
                com.jingdong.app.mall.home.o.a.f.s0(k.class, e2);
                k(str);
                l(bufferedReader);
                return "";
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static JDJSONObject p(HttpResponse httpResponse) {
        if (!v()) {
            return httpResponse.getFastJsonObject();
        }
        return s(f10484j, httpResponse);
    }

    public static String q() {
        return "12.0.08";
    }

    public static JDJSONObject r() {
        try {
            String o2 = o(com.jingdong.app.mall.home.o.a.f.k0() ? "local/localWelcomeHome" : "local/homeLocalPrivacy");
            if (TextUtils.isEmpty(o2)) {
                return null;
            }
            return JDJSON.parseObject(o2);
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(k.class, e2);
            return null;
        }
    }

    public static JDJSONObject s(String str, HttpResponse httpResponse) {
        JDJSONObject jDJSONObject = httpResponse == null ? new JDJSONObject() : httpResponse.getFastJsonObject();
        if (!v() || TextUtils.isEmpty(str)) {
            return jDJSONObject;
        }
        String t = t(str);
        return TextUtils.isEmpty(t) ? jDJSONObject : JDJSON.parseObject(t);
    }

    public static String t(String str) {
        BufferedReader bufferedReader;
        InputStream open;
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        InputStream inputStream = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory());
            String str2 = File.separator;
            sb.append(str2);
            File file = new File(sb.toString() + "DownloadL" + str2 + str);
            if (file.exists()) {
                open = new FileInputStream(file);
            } else {
                open = applicationContext.getResources().getAssets().open(str);
            }
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(open));
            } catch (Throwable th) {
                inputStream = open;
                th = th;
                bufferedReader = null;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
        try {
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb2.append(readLine.trim());
                } else {
                    k(open);
                    l(bufferedReader);
                    String sb3 = sb2.toString();
                    k(open);
                    l(bufferedReader);
                    return sb3;
                }
            }
        } catch (Throwable th3) {
            inputStream = open;
            th = th3;
            try {
                com.jingdong.app.mall.home.o.a.f.q0("Local", th);
                k(inputStream);
                l(bufferedReader);
                return "";
            } catch (Throwable th4) {
                k(inputStream);
                l(bufferedReader);
                throw th4;
            }
        }
    }

    public static int u() {
        return f10478c;
    }

    public static boolean v() {
        return JDHomeFragment.isDebug() || f10480f || f10482h;
    }

    public static boolean w() {
        return f10480f;
    }

    public static boolean x() {
        return f10481g && f10480f;
    }

    public static void y() {
        HttpResponse B0;
        JsonObject asJsonObject;
        Gson create;
        BufferedWriter bufferedWriter;
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null || (B0 = z0.B0()) == null) {
            return;
        }
        JDJSONObject fastJsonObject = B0.getFastJsonObject();
        String optString = fastJsonObject.optString("aapType");
        String format = new SimpleDateFormat("yyyy_MM_dd-HH:mm  ", Locale.getDefault()).format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        String str = File.separator;
        sb.append(str);
        String sb2 = sb.toString();
        String concat = format.concat("(").concat(fastJsonObject.optString("userCategory")).concat(d).concat(").txt");
        String concat2 = sb2.concat("DownloadL").concat(str).concat("welcomeHome_").concat(optString).concat(CartConstant.KEY_YB_INFO_LINK).concat(concat);
        BufferedWriter bufferedWriter2 = null;
        try {
            asJsonObject = new JsonParser().parse(fastJsonObject.toString()).getAsJsonObject();
            create = new GsonBuilder().setPrettyPrinting().create();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(concat2)));
        } catch (Throwable unused) {
        }
        try {
            bufferedWriter.write(create.toJson((JsonElement) asJsonObject));
            ToastUtils.showToast(JdSdk.getInstance().getApplicationContext(), "Save " + concat);
            m(bufferedWriter);
        } catch (Throwable unused2) {
            bufferedWriter2 = bufferedWriter;
            m(bufferedWriter2);
        }
    }

    private static void z(RelativeLayout relativeLayout) {
        Context context = relativeLayout.getContext();
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(24);
        int d3 = com.jingdong.app.mall.home.floor.common.d.d(80);
        ScrollView scrollView = new ScrollView(context);
        scrollView.setVisibility(8);
        scrollView.setBackgroundColor(-872402125);
        scrollView.setVerticalScrollBarEnabled(false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(12);
        relativeLayout.addView(scrollView, layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setPadding(d2, d3, d2, d3);
        linearLayout.setOrientation(1);
        scrollView.addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        TextView textView = new TextView(context);
        textView.setTextColor(-1);
        textView.setText(f10485k.toString());
        textView.setGravity(GravityCompat.START);
        linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        textView.setOnClickListener(new g(textView));
        EditText editText = new EditText(context);
        editText.setBackgroundColor(0);
        editText.setOnClickListener(new h(editText));
        editText.setGravity(17);
        editText.setTextColor(-1);
        editText.setHintTextColor(-1);
        editText.setHint("\u8bf7\u8f93\u5165\u9884\u89c8\u65f6\u957f\uff1a\u5c0f\u65f6");
        linearLayout.addView(editText, new LinearLayout.LayoutParams(-1, com.jingdong.app.mall.home.floor.common.d.d(80)));
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{-855677440, -865691239});
        float f2 = d2;
        gradientDrawable.setCornerRadii(new float[]{f2, f2, f2, f2, 0.0f, 0.0f, 0.0f, 0.0f});
        horizontalScrollView.setBackgroundDrawable(gradientDrawable);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, d3);
        layoutParams2.addRule(12);
        relativeLayout.addView(horizontalScrollView, layoutParams2);
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setOrientation(0);
        horizontalScrollView.addView(linearLayout2, new FrameLayout.LayoutParams(-2, d3));
        o oVar = new o(context, "\u5173\u95ed\u9875\u9762", d3);
        oVar.setOnClickListener(new i(relativeLayout, scrollView, horizontalScrollView));
        linearLayout2.addView(oVar);
        o oVar2 = new o(context, "\u4fdd\u5b58\u63a5\u53e3", d3);
        oVar2.setOnClickListener(new j());
        linearLayout2.addView(oVar2);
        o oVar3 = new o(context, "\u67e5\u770b\u6570\u636e", d3);
        oVar3.setOnClickListener(new ViewOnClickListenerC0311k(relativeLayout, d3));
        linearLayout2.addView(oVar3);
        o oVar4 = new o(context, "\u5c55\u5f00\u5185\u5bb9", d3);
        linearLayout2.addView(oVar4);
        oVar4.setOnClickListener(new l(editText, scrollView, oVar4));
        o oVar5 = new o(context, "\u5237\u65b0\u6570\u636e", d3);
        oVar5.setOnClickListener(new m(textView));
        linearLayout2.addView(oVar5);
        o oVar6 = new o(context, "\u67e5\u770b\u542f\u52a8", d3);
        oVar6.setOnClickListener(new n(textView));
        linearLayout2.addView(oVar6);
        o oVar7 = new o(context, "\u62f7\u8d1d\u9762\u677f", d3);
        linearLayout2.addView(oVar7);
        oVar7.setOnClickListener(new a(textView));
        o oVar8 = new o(context, "\u8001\u5e74\u7248", d3);
        oVar8.setOnClickListener(new b());
        linearLayout2.addView(oVar8);
        o oVar9 = new o(context, "\u7248\u672c\u5207\u6362", d3);
        oVar9.setOnClickListener(new c(oVar9));
        linearLayout2.addView(oVar9);
        o oVar10 = new o(context, "\u6697\u9ed1\u5207\u6362", d3);
        oVar10.setOnClickListener(new d());
        linearLayout2.addView(oVar10);
        o oVar11 = new o(context, "\u7070\u9636\u5207\u6362", d3);
        oVar11.setOnClickListener(new e());
        linearLayout2.addView(oVar11);
    }
}
