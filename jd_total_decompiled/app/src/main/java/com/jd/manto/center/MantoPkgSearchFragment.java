package com.jd.manto.center;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jd.manto.center.MantoPkgRecentAdapter;
import com.jd.manto.center.model.AdapterBeanWrapper;
import com.jd.manto.center.widget.MaeFrameView;
import com.jd.manto.center.widget.recycler.BaseRecyclerAdapter;
import com.jd.manto.center.widget.recycler.PullUpLoadRecyclerView;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.MantoRequestHotSearch;
import com.jingdong.manto.network.mantorequests.MantoRequestSearch;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class MantoPkgSearchFragment extends Fragment implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    private EditText f6419g;

    /* renamed from: h  reason: collision with root package name */
    private PullUpLoadRecyclerView f6420h;

    /* renamed from: i  reason: collision with root package name */
    private MantoSearchAdapter f6421i;

    /* renamed from: j  reason: collision with root package name */
    private RecyclerView f6422j;

    /* renamed from: k  reason: collision with root package name */
    private MaeFrameView f6423k;

    /* renamed from: l  reason: collision with root package name */
    int f6424l = 1;

    /* renamed from: m  reason: collision with root package name */
    int f6425m = 1;

    /* renamed from: n  reason: collision with root package name */
    String f6426n = "";

    /* loaded from: classes17.dex */
    class a implements TextView.OnEditorActionListener {
        a() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (i2 == 3) {
                String trim = MantoPkgSearchFragment.this.f6419g.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    MantoTrack.sendCommonDataWithExt(MantoPkgSearchFragment.this.getContext(), "\u70b9\u51fb\u641c\u7d22", "Applets_Center_Search_SearchKeyWords", trim, "", "", "", "", null);
                }
                MantoPkgSearchFragment.this.m();
                MantoPkgSearchFragment.this.u();
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f6427g;

        b(String str) {
            this.f6427g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPkgSearchFragment.this.f6423k.d(R.drawable.manto_center_pkg_search_empty, String.format(MantoPkgSearchFragment.this.getString(R.string.manto_center_pkg_search_empty_tip), "\"" + this.f6427g + "\""), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements MantoPkgRecentAdapter.c<PkgDetailEntity> {
        c() {
        }

        @Override // com.jd.manto.center.MantoPkgRecentAdapter.c
        /* renamed from: c  reason: merged with bridge method [inline-methods] */
        public void b(PkgDetailEntity pkgDetailEntity, int i2) {
            LaunchParam launchParam = new LaunchParam();
            launchParam.appId = pkgDetailEntity.appId;
            launchParam.debugType = pkgDetailEntity.type;
            com.jingdong.a.p(launchParam, MantoPkgSearchFragment.this.getActivity());
            MantoPkgSearchFragment.this.m();
            HashMap hashMap = new HashMap();
            hashMap.put("vapp", "1");
            hashMap.put("vapp_appid", pkgDetailEntity.appId);
            hashMap.put("source", "search");
            MantoTrack.sendCommonDataWithExt(MantoPkgSearchFragment.this.getContext(), pkgDetailEntity.name, "Applets_Center_Enter", pkgDetailEntity.appId, "", "", "", "", hashMap);
        }

        @Override // com.jd.manto.center.MantoPkgRecentAdapter.c
        /* renamed from: d  reason: merged with bridge method [inline-methods] */
        public void a(PkgDetailEntity pkgDetailEntity, View view, int i2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements com.jd.manto.center.widget.recycler.a {
        d() {
        }

        @Override // com.jd.manto.center.widget.recycler.a
        public void onLoad() {
            MantoPkgSearchFragment.this.u();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e extends IMantoHttpListener {
        final /* synthetic */ String a;

        /* loaded from: classes17.dex */
        class a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ List f6429g;

            a(List list) {
                this.f6429g = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoPkgSearchFragment.this.f6423k.b();
                MantoPkgSearchFragment.this.f6421i.p(e.this.a);
                MantoPkgSearchFragment mantoPkgSearchFragment = MantoPkgSearchFragment.this;
                if (mantoPkgSearchFragment.f6424l != 1) {
                    mantoPkgSearchFragment.f6421i.h(this.f6429g);
                } else {
                    this.f6429g.add(0, new AdapterBeanWrapper("\u5c0f\u7a0b\u5e8f"));
                    MantoPkgSearchFragment.this.f6421i.m(this.f6429g);
                }
                MantoPkgSearchFragment mantoPkgSearchFragment2 = MantoPkgSearchFragment.this;
                int i2 = mantoPkgSearchFragment2.f6424l;
                if (i2 < mantoPkgSearchFragment2.f6425m) {
                    mantoPkgSearchFragment2.f6424l = i2 + 1;
                }
            }
        }

        /* loaded from: classes17.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoPkgSearchFragment mantoPkgSearchFragment = MantoPkgSearchFragment.this;
                if (mantoPkgSearchFragment.f6424l >= mantoPkgSearchFragment.f6425m) {
                    mantoPkgSearchFragment.f6420h.e();
                } else {
                    mantoPkgSearchFragment.f6420h.h();
                }
            }
        }

        /* loaded from: classes17.dex */
        class c implements Runnable {
            c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoPkgSearchFragment.this.f6420h.f();
            }
        }

        e(String str) {
            this.a = str;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            MantoLog.d(DYConstants.DY_CENTER, "error: " + th);
            MantoPkgSearchFragment.this.t(new c());
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            MantoLog.d(DYConstants.DY_CENTER, "onSuccess: " + jSONObject);
            ArrayList arrayList = new ArrayList();
            try {
                if (jSONObject.getString("code").equalsIgnoreCase("0")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    MantoPkgSearchFragment.this.f6425m = jSONObject2.optInt("sum_page", 1);
                    JSONArray jSONArray = jSONObject2.getJSONArray(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID);
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                        PkgDetailEntity pkgDetailEntity = new PkgDetailEntity();
                        pkgDetailEntity.appId = jSONObject3.getString("app_id");
                        pkgDetailEntity.name = jSONObject3.getString("name");
                        pkgDetailEntity.logo = jSONObject3.getString("logo");
                        pkgDetailEntity.description = jSONObject3.getString("description");
                        pkgDetailEntity.venderType = jSONObject3.getString("vendor_type");
                        pkgDetailEntity.venderName = jSONObject3.getString("vendor_name");
                        pkgDetailEntity.type = jSONObject3.optString("type", "1");
                        arrayList.add(new AdapterBeanWrapper(pkgDetailEntity));
                    }
                }
            } catch (Throwable unused) {
            }
            if (arrayList.size() > 0) {
                MantoPkgSearchFragment.this.t(new a(arrayList));
            } else {
                MantoPkgSearchFragment.this.s(this.a);
            }
            MantoPkgSearchFragment.this.t(new b());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class f extends IMantoHttpListener {

        /* loaded from: classes17.dex */
        class a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ List f6433g;

            /* renamed from: com.jd.manto.center.MantoPkgSearchFragment$f$a$a  reason: collision with other inner class name */
            /* loaded from: classes17.dex */
            class C0180a implements BaseRecyclerAdapter.a {
                C0180a() {
                }

                @Override // com.jd.manto.center.widget.recycler.BaseRecyclerAdapter.a
                public void a(BaseRecyclerAdapter baseRecyclerAdapter, View view, int i2) {
                    String obj = baseRecyclerAdapter.getItem(i2).toString();
                    MantoPkgSearchFragment.this.w(obj);
                    MantoPkgSearchFragment.this.m();
                    MantoTrack.sendCommonDataWithExt(MantoPkgSearchFragment.this.getContext(), "\u70b9\u51fb\u5173\u952e\u5b57", "Applets_Center_Search_DefaultKeyWords", obj, "", "", "", "", null);
                }
            }

            a(List list) {
                this.f6433g = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.jd.manto.center.d dVar = new com.jd.manto.center.d(MantoPkgSearchFragment.this.getActivity(), this.f6433g, new C0180a());
                MantoPkgSearchFragment.this.f6423k.c();
                try {
                    MantoPkgSearchFragment.this.f6423k.findViewById(R.id.tv_message).setVisibility(0);
                } catch (Exception unused) {
                }
                MantoPkgSearchFragment.this.f6422j.setAdapter(dVar);
            }
        }

        /* loaded from: classes17.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoPkgSearchFragment.this.f6423k.c();
                try {
                    MantoPkgSearchFragment.this.f6423k.findViewById(R.id.tv_message).setVisibility(4);
                } catch (Exception unused) {
                }
            }
        }

        /* loaded from: classes17.dex */
        class c implements Runnable {
            c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoPkgSearchFragment.this.f6423k.d(R.drawable.manto_center_pkg_search_empty, "\u83b7\u53d6\u6570\u636e\u51fa\u9519\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", null);
            }
        }

        f() {
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            MantoPkgSearchFragment.this.t(new c());
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            MantoLog.d(DYConstants.DY_CENTER, "loadHotSearch onSuccess: " + jSONObject);
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                MantoPkgSearchFragment.this.t(new b());
                return;
            }
            ArrayList arrayList = new ArrayList(6);
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                try {
                    arrayList.add(optJSONArray.get(i2).toString());
                } catch (Exception unused) {
                }
            }
            MantoPkgSearchFragment.this.t(new a(arrayList));
        }
    }

    private void l() {
        HashMap hashMap = new HashMap();
        hashMap.put("vapp", "0");
        hashMap.put("vapp_type", "0");
        MantoTrack.sendPagePv(getContext(), "\u5c0f\u7a0b\u5e8f\u4e2d\u5fc3\u9875", "", "Applets_Center_FirstEnter", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        View currentFocus;
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            FragmentActivity activity = getActivity();
            if (activity == null || (currentFocus = activity.getCurrentFocus()) == null) {
                return;
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        } catch (Throwable unused) {
        }
    }

    private final void o() {
        this.f6423k.c();
        this.f6423k.g();
        RecyclerView recyclerView = (RecyclerView) this.f6423k.findViewById(R.id.recommend_recyclerView);
        this.f6422j = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        q();
        l();
    }

    private final void q() {
        MantoJDHttpHandler.commit(new MantoRequestHotSearch(), new f());
    }

    public static MantoPkgSearchFragment r(String str) {
        MantoPkgSearchFragment mantoPkgSearchFragment = new MantoPkgSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", str);
        mantoPkgSearchFragment.setArguments(bundle);
        return mantoPkgSearchFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(String str) {
        t(new b(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(Runnable runnable) {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void u() {
        String trim = this.f6419g.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            Toast.makeText(getContext(), "\u641c\u7d22\u5185\u5bb9\u4e0d\u80fd\u4e3a\u7a7a", 0).show();
            return;
        }
        if (!TextUtils.equals(trim, this.f6426n)) {
            this.f6424l = 1;
            this.f6426n = trim;
        }
        if (this.f6424l == 1) {
            this.f6423k.g();
        }
        MantoJDHttpHandler.commit(new MantoRequestSearch(trim, this.f6424l), new e(trim));
        HashMap hashMap = new HashMap();
        hashMap.put("vapp", "0");
        hashMap.put("vapp_type", "0");
        MantoTrack.sendPagePv(getContext(), "\u641c\u7d22\u7ed3\u679c\u5217\u8868\u9875", "", "Applets_Center_SearchEnd", hashMap);
    }

    private void v() {
        HashMap hashMap = new HashMap();
        hashMap.put("vapp", "0");
        hashMap.put("vapp_type", "0");
        MantoTrack.sendPagePv(getContext(), "\u5c0f\u7a0b\u5e8f\u4e2d\u5fc3\u9875", null, "Applets_Center_Search", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void w(String str) {
        this.f6419g.setText(str);
        u();
    }

    final void n() {
        MantoSearchAdapter mantoSearchAdapter = new MantoSearchAdapter(getActivity(), null, new c());
        this.f6421i = mantoSearchAdapter;
        this.f6420h.setAdapter(mantoSearchAdapter);
        this.f6420h.j(new d());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.search) {
            m();
            this.f6424l = 1;
            u();
            String trim = this.f6419g.getText().toString().trim();
            if (TextUtils.isEmpty(trim)) {
                return;
            }
            MantoTrack.sendCommonDataWithExt(getContext(), "\u70b9\u51fb\u641c\u7d22", "Applets_Center_Search_SearchKeyWords", trim, "", "", "", "", null);
        } else if (id == R.id.back) {
            m();
            MantoTrack.sendCommonDataWithExt(getContext(), "\u56de\u9000\u7bad\u5934", "Applets_Center_Search_Back", "", "", "", "", "", null);
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.onBackPressed();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.manto_center_pkg_search_fragment, viewGroup, false);
        inflate.setClickable(true);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.f6426n = "";
        this.f6424l = 1;
        this.f6423k = (MaeFrameView) view.findViewById(R.id.frameView);
        view.findViewById(R.id.search).setOnClickListener(this);
        view.findViewById(R.id.back).setOnClickListener(this);
        EditText editText = (EditText) view.findViewById(R.id.et_search);
        this.f6419g = editText;
        editText.setImeOptions(3);
        this.f6419g.setOnEditorActionListener(new a());
        PullUpLoadRecyclerView pullUpLoadRecyclerView = (PullUpLoadRecyclerView) view.findViewById(R.id.recyclerview);
        this.f6420h = pullUpLoadRecyclerView;
        pullUpLoadRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.f6420h.setItemAnimator(new DefaultItemAnimator());
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("keyword");
            if (!TextUtils.isEmpty(string)) {
                this.f6419g.setText(string);
                u();
                return;
            }
        }
        this.f6419g.requestFocus();
        try {
            ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this.f6419g, 0);
        } catch (Exception unused) {
        }
        n();
        o();
        v();
    }
}
