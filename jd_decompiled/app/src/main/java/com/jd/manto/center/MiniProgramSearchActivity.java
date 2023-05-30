package com.jd.manto.center;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.manto.center.widget.BetterSearchShowView;
import com.jd.manto.center.widget.MantoSearchEditText;
import com.jingdong.common.database.table.MiniProgramSearchHistoryTable;
import com.jingdong.common.entity.MiniProgramSearchHistory;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes17.dex */
public class MiniProgramSearchActivity extends BaseMantoCenterActivity implements com.jd.manto.center.widget.e {

    /* renamed from: h */
    private MantoSearchEditText f6463h;

    /* renamed from: i */
    private LinearLayout f6464i;

    /* renamed from: j */
    private BetterSearchShowView f6465j;

    /* renamed from: l */
    private ImageView f6467l;

    /* renamed from: m */
    private ImageView f6468m;

    /* renamed from: n */
    private ImageView f6469n;
    private String o;
    private String p;
    private FrameLayout r;

    /* renamed from: k */
    protected List<String> f6466k = new ArrayList();
    private int q = 1;

    /* loaded from: classes17.dex */
    class a extends TimerTask {
        a() {
            MiniProgramSearchActivity.this = r1;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            com.jd.manto.center.k.f.b(MiniProgramSearchActivity.this.f6463h);
        }
    }

    /* loaded from: classes17.dex */
    public class b implements View.OnClickListener {
        b() {
            MiniProgramSearchActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MiniProgramSearchActivity.this.finish();
        }
    }

    /* loaded from: classes17.dex */
    public class c implements View.OnClickListener {
        c() {
            MiniProgramSearchActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jd.manto.center.h.b.q(MiniProgramSearchActivity.this, "J_Applets_Top_Close", "J_Applets_Search");
            MiniProgramSearchActivity.this.sendBroadcast(new Intent("action.exit"));
        }
    }

    /* loaded from: classes17.dex */
    public class d implements View.OnClickListener {
        d() {
            MiniProgramSearchActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jd.manto.center.h.b.q(MiniProgramSearchActivity.this, "J_Applets_Top_More", "J_Applets_Search");
            Intent intent = new Intent(MiniProgramSearchActivity.this, MantoCenterAboutActivity.class);
            intent.putExtra("tinyAppIntroduction", MiniProgramSearchActivity.this.p);
            MiniProgramSearchActivity.this.startActivity(intent);
        }
    }

    /* loaded from: classes17.dex */
    public class e implements TextView.OnEditorActionListener {
        e() {
            MiniProgramSearchActivity.this = r1;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (i2 == 3) {
                com.jd.manto.center.k.f.a(MiniProgramSearchActivity.this.f6463h);
                String trim = MiniProgramSearchActivity.this.f6463h.getText() == null ? "" : MiniProgramSearchActivity.this.f6463h.getText().toString().trim();
                com.jd.manto.center.h.b.n(MiniProgramSearchActivity.this, trim);
                MiniProgramSearchActivity.this.E(trim);
                return true;
            }
            return false;
        }
    }

    /* loaded from: classes17.dex */
    public class f implements View.OnClickListener {
        f() {
            MiniProgramSearchActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jd.manto.center.h.b.q(MiniProgramSearchActivity.this, "J_Applets_Top_Search", "J_Applets_Search");
        }
    }

    /* loaded from: classes17.dex */
    public class g implements TextWatcher {
        g() {
            MiniProgramSearchActivity.this = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (TextUtils.isEmpty(charSequence)) {
                com.jd.manto.center.k.h.b(MiniProgramSearchActivity.this.f6469n);
            } else {
                com.jd.manto.center.k.h.l(MiniProgramSearchActivity.this.f6469n);
            }
        }
    }

    /* loaded from: classes17.dex */
    public class h implements View.OnClickListener {
        h() {
            MiniProgramSearchActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MiniProgramSearchActivity.this.f6463h.setText("");
        }
    }

    /* loaded from: classes17.dex */
    public class i implements HttpGroup.OnCommonListener {
        i() {
            MiniProgramSearchActivity.this = r1;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null || httpResponse.getFastJsonObject() == null) {
                MiniProgramSearchActivity.this.A(null);
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                MiniProgramSearchActivity.this.A(null);
            } else if (!TextUtils.equals(fastJsonObject.optString("code"), "0")) {
                MiniProgramSearchActivity.this.A(null);
            } else {
                JDJSONArray optJSONArray = fastJsonObject.optJSONArray("hotWords");
                if (optJSONArray == null) {
                    MiniProgramSearchActivity.this.A(null);
                    return;
                }
                Object[] array = optJSONArray.toArray();
                if (array == null || array.length == 0) {
                    MiniProgramSearchActivity.this.A(null);
                    return;
                }
                MiniProgramSearchActivity.this.f6466k = Arrays.asList((String[]) Arrays.copyOf(array, array.length, String[].class));
                MiniProgramSearchActivity miniProgramSearchActivity = MiniProgramSearchActivity.this;
                miniProgramSearchActivity.A(miniProgramSearchActivity.f6466k);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MiniProgramSearchActivity.this.A(null);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes17.dex */
    public class j implements Runnable {

        /* renamed from: g */
        final /* synthetic */ List f6478g;

        j(List list) {
            MiniProgramSearchActivity.this = r1;
            this.f6478g = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MiniProgramSearchActivity.this.f6465j != null) {
                MiniProgramSearchActivity.this.f6465j.c(this.f6478g);
            }
        }
    }

    public void A(List<String> list) {
        runOnUiThread(new j(list));
    }

    private void B() {
        ArrayList<MiniProgramSearchHistory> allSearchHistory = MiniProgramSearchHistoryTable.getAllSearchHistory();
        BetterSearchShowView betterSearchShowView = this.f6465j;
        if (betterSearchShowView != null) {
            betterSearchShowView.b(allSearchHistory);
        }
    }

    private void C() {
        HttpSetting D = D();
        D.setUseFastJsonParser(true);
        D.setListener(new i());
        HttpGroupUtils.getHttpGroupaAsynPool().add(D);
    }

    private static HttpSetting D() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("tinyAppHotWords");
        httpSetting.setHost(com.jd.manto.center.c.a());
        httpSetting.setEffect(0);
        return httpSetting;
    }

    public void E(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        MiniProgramSearchHistoryTable.saveSearchHistory(str, 14);
        Intent intent = new Intent();
        intent.putExtra("searchKeyword", str);
        intent.setClass(this, MiniProgramListActivity.class);
        startActivityForResult(intent, this.q);
    }

    private void F() {
        this.f6464i.setOnClickListener(new b());
        this.f6467l.setOnClickListener(new c());
        this.f6468m.setOnClickListener(new d());
        this.f6465j.e(this);
        this.f6463h.setOnEditorActionListener(new e());
        this.f6463h.setOnClickListener(new f());
        this.f6463h.addTextChangedListener(new g());
        this.f6469n.setOnClickListener(new h());
    }

    private void initView() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.common_title_background);
        this.r = frameLayout;
        com.jd.manto.center.k.d.e(frameLayout, "2833");
        MantoSearchEditText mantoSearchEditText = (MantoSearchEditText) findViewById(R.id.et_search);
        this.f6463h = mantoSearchEditText;
        mantoSearchEditText.setHint(this.o);
        Selection.setSelection(this.f6463h.getText(), this.f6463h.length());
        this.f6463h.requestFocus();
        this.f6469n = (ImageView) findViewById(R.id.iv_clearall);
        this.f6464i = (LinearLayout) findViewById(R.id.back);
        this.f6465j = (BetterSearchShowView) findViewById(R.id.searchhistory_hotword);
        this.f6467l = (ImageView) findViewById(R.id.manto_actionbar_home);
        this.f6468m = (ImageView) findViewById(R.id.manto_actionbar_option);
    }

    @Override // com.jd.manto.center.widget.e
    public void c(String str, String str2) {
        if (StringUtil.isEmpty(str)) {
            return;
        }
        if (com.jd.manto.center.k.f.c()) {
            com.jd.manto.center.k.f.a(getWindow().getDecorView());
        }
        com.jd.manto.center.k.h.i(this.f6463h, str);
        Selection.setSelection(this.f6463h.getText(), this.f6463h.length());
        E(str);
    }

    @Override // com.jd.manto.center.widget.e
    public void clearHistory() {
        MiniProgramSearchHistoryTable.deleteAllHistory();
        BetterSearchShowView betterSearchShowView = this.f6465j;
        if (betterSearchShowView != null) {
            betterSearchShowView.d();
            this.f6465j.b(null);
        }
    }

    @Override // com.jd.manto.center.widget.e
    public void e(MiniProgramSearchHistory miniProgramSearchHistory, int i2) {
        if (miniProgramSearchHistory == null || TextUtils.isEmpty(miniProgramSearchHistory.getWord())) {
            return;
        }
        if (com.jd.manto.center.k.f.c()) {
            com.jd.manto.center.k.f.a(getWindow().getDecorView());
        }
        com.jd.manto.center.k.h.i(this.f6463h, miniProgramSearchHistory.getWord());
        Selection.setSelection(this.f6463h.getText(), this.f6463h.length());
        E(miniProgramSearchHistory.getWord());
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == this.q) {
            if (i3 == com.jd.manto.center.k.a.a) {
                this.f6463h.setText(intent.getStringExtra("keyword"));
                Selection.setSelection(this.f6463h.getEditableText(), this.f6463h.length());
                B();
            } else if (i3 == com.jd.manto.center.k.a.b) {
                finish();
            }
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.jd.manto.center.BaseMantoCenterActivity, com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        com.jd.manto.center.h.b.p(this, "J_Applets_Search");
        setContentView(R.layout.manto_center_search_activity);
        if (getIntent() != null) {
            this.o = getIntent().getStringExtra("searchKeyword");
            this.p = getIntent().getStringExtra("tinyAppIntroduction");
        }
        initView();
        F();
        B();
        C();
    }

    @Override // com.jd.manto.center.BaseMantoCenterActivity, com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (com.jd.manto.center.k.f.c()) {
            com.jd.manto.center.k.f.a(getWindow().getDecorView());
        }
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        new Timer().schedule(new a(), 500L);
    }
}
