package com.jd.dynamic.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pair;
import com.facebook.soloader.SoLoader;
import com.google.gson.Gson;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.apis.IViewBindListener;
import com.jd.dynamic.b.j.a;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.ICustomView;
import com.jd.dynamic.base.interfaces.IDarkChangeListener;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.IFinishAddView;
import com.jd.dynamic.base.timer.TimerManager;
import com.jd.dynamic.engine.jni.JSCException;
import com.jd.dynamic.engine.jni.JavaScriptRuntime;
import com.jd.dynamic.entity.MtaTimePair;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.dynamic.parser.DynamicXParser;
import com.jd.dynamic.lib.error.DynamicException;
import com.jd.dynamic.lib.viewparse.BaseFrameLayout;
import com.jd.dynamic.lib.views.CollectionView;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class DynamicTemplateEngine implements IDarkChangeListener {
    private static long P;
    private static long Q;
    private TimerManager A;
    private Gson F;
    private int G;
    private com.jd.dynamic.a.i H;
    private com.jd.dynamic.a.i I;
    private JSONObject J;
    private ViewNode N;
    public String bizField;
    public int containerHeight;
    public int containerWidth;
    public JSONObject currentData;
    public ResultEntity entity;

    /* renamed from: g */
    private FrameLayout f1885g;

    /* renamed from: h */
    private Activity f1886h;

    /* renamed from: i */
    private IFunctionFactory f1887i;

    /* renamed from: j */
    private Map<ViewNode, HashMap<String, String>> f1888j;

    /* renamed from: l */
    private HashMap<ViewNode, HashMap<String, String>> f1890l;

    /* renamed from: m */
    private IFunctionFactory f1891m;
    public String mPackageName;

    /* renamed from: n */
    private CachePool f1892n;
    private Template o;
    private com.jd.dynamic.a.g q;
    private String r;
    private Map<String, ICustomView> s;
    private Dialog t;
    private double x;
    private String y;
    private BaseFrameLayout z;

    /* renamed from: k */
    private SparseArrayCompat<HashMap<String, String>> f1889k = new SparseArrayCompat<>(16);
    private boolean p = true;
    public boolean isRelease = false;
    private List<Pair<ViewNode, String>> v = new ArrayList();
    private List<Pair<ViewNode, String>> w = new ArrayList();
    @Nullable
    public Boolean useAsyncItem = Boolean.FALSE;
    @Nullable
    public boolean useTagViewOptimize = false;
    private boolean B = false;
    private ConcurrentHashMap<String, Object> C = new ConcurrentHashMap<>();
    private List<com.jd.dynamic.a.e> D = new ArrayList();
    private Map<String, Integer> E = new HashMap();
    public Map<View, HashMap<String, String>> unBindListenerViews = new HashMap();
    public boolean isAttached = true;
    final JSONObject[] K = new JSONObject[1];
    private boolean L = false;
    private long M = 0;
    private boolean O = false;
    private com.jd.dynamic.lib.utils.u u = new com.jd.dynamic.lib.utils.u();

    /* loaded from: classes13.dex */
    public class AsyncBindTask extends AsyncTask<Void, Void, ParseResult> {
        private JSONObject a;
        private JSONObject b;

        /* renamed from: c */
        private BaseFrameLayout f1901c;
        private long d;

        /* renamed from: e */
        private double f1902e;

        /* renamed from: f */
        private IViewBindListener f1903f;

        /* renamed from: g */
        private Map<ViewNode, HashMap<String, String>> f1904g;

        public AsyncBindTask(JSONObject jSONObject, JSONObject jSONObject2, BaseFrameLayout baseFrameLayout, Map<ViewNode, HashMap<String, String>> map, IViewBindListener iViewBindListener) {
            DynamicTemplateEngine.this = r1;
            this.a = jSONObject;
            this.b = jSONObject2;
            this.f1901c = baseFrameLayout;
            this.d = ObjectsCompat.hashCode(jSONObject2);
            this.f1903f = iViewBindListener;
            this.f1904g = map;
        }

        public /* synthetic */ void b() {
            DynamicTemplateEngine.this.h0();
        }

        public /* synthetic */ void d(Map.Entry entry) {
            DynamicTemplateEngine.this.R((HashMap) entry.getValue(), (View) entry.getKey());
            DynamicTemplateEngine.this.A((HashMap) entry.getValue(), (View) entry.getKey());
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public ParseResult doInBackground(Void... voidArr) {
            int i2;
            ParseResult parseResult = new ParseResult();
            HashMap<View, HashMap<String, String>> hashMap = new HashMap<>();
            MtaTimePair newInstance = MtaTimePair.newInstance();
            newInstance.startRecord();
            try {
                for (Map.Entry<ViewNode, HashMap<String, String>> entry : this.f1904g.entrySet()) {
                    HashMap<String, String> value = entry.getValue();
                    ViewNode key = entry.getKey();
                    if (key != null && (i2 = key.viewId) != 0) {
                        View b = DynamicTemplateEngine.this.b(i2);
                        if (b == null) {
                            b = this.f1901c.findViewById(key.viewId);
                        }
                        if (b != null && !key.unNeedInitBind) {
                            for (Map.Entry<String, String> entry2 : value.entrySet()) {
                                if (DynamicUtils.isElOrKnownSymbol(entry2.getValue())) {
                                    DynamicTemplateEngine.this.X(entry2.getValue());
                                    System.nanoTime();
                                    DynamicTemplateEngine.this.newBindDataWithEL(key, this.b, entry2.getValue(), entry2.getKey());
                                    HashMap<String, String> eLAttributes = key.getELAttributes();
                                    if (!TextUtils.isEmpty(entry2.getValue()) && entry2.getValue().contains("$dark(") && DynamicSdk.getEngine().getDynamicDark() != null) {
                                        String str = eLAttributes.get(entry2.getKey());
                                        if (!TextUtils.isEmpty(str)) {
                                            try {
                                                JSONObject jSONObject = new JSONObject();
                                                jSONObject.put(DynamicSdk.getEngine().getDynamicDark().isDarkMode() ? CustomThemeConstance.NAVI_IMAGE_DARK_TAG : "unDark", str);
                                                eLAttributes.put(entry2.getKey() + "_dark", jSONObject.toString());
                                            } catch (Exception unused) {
                                            }
                                        }
                                    }
                                }
                            }
                            hashMap.put(b, key.getELAttributes());
                            DynamicTemplateEngine.this.r(key);
                        }
                    }
                }
                if (this.f1901c.isFirstBind()) {
                    newInstance.endRecord();
                    DynamicMtaUtil.appendBindDataMtaStat(DynamicTemplateEngine.this.y, newInstance);
                }
                parseResult.viewAttrMaps = hashMap;
                parseResult.unId = this.d;
            } catch (Exception e2) {
                parseResult.exception = e2;
            }
            return parseResult;
        }

        public boolean bindViewWithCache() {
            if (DynamicTemplateEngine.this.H(this.b, this.f1902e)) {
                IViewBindListener iViewBindListener = this.f1903f;
                if (iViewBindListener != null) {
                    iViewBindListener.onDynamicViewBind();
                    return true;
                }
                return true;
            }
            return false;
        }

        @Override // android.os.AsyncTask
        /* renamed from: c */
        public void onPostExecute(ParseResult parseResult) {
            if (parseResult.exception != null) {
                IViewBindListener iViewBindListener = this.f1903f;
                if (iViewBindListener != null) {
                    iViewBindListener.onError(new DynamicException("\u5f02\u6b65\u7ed1\u5b9a\u5931\u8d25", parseResult.exception, 503));
                    return;
                }
                return;
            }
            JSONArray jSONArray = new JSONArray();
            com.jd.dynamic.lib.viewparse.c.v vVar = new com.jd.dynamic.lib.viewparse.c.v();
            vVar.b(DynamicTemplateEngine.this);
            if (this.f1901c.unId == parseResult.unId) {
                long j2 = 0;
                DynamicTemplateEngine.this.updateScreenWidth();
                for (final Map.Entry<View, HashMap<String, String>> entry : parseResult.viewAttrMaps.entrySet()) {
                    JSONObject jSONObject = new JSONObject();
                    long nanoTime = System.nanoTime();
                    try {
                        jSONObject.put("layoutId", entry.getKey().getId());
                        jSONObject.put("attrs", new JSONObject(entry.getValue()));
                    } catch (JSONException e2) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND_ASYNC, e2.getMessage(), DynamicTemplateEngine.this.getBizField(), DynamicTemplateEngine.this.getSystemCode(), R2.attr.layout_constraintHeight_min, e2, com.jd.dynamic.lib.utils.m.q(DynamicTemplateEngine.this.getZipVersion(), null));
                    }
                    jSONArray.put(jSONObject);
                    vVar.a(entry.getValue(), entry.getKey());
                    j2 += System.nanoTime() - nanoTime;
                    if (this.f1902e != DynamicTemplateEngine.this.x && (entry.getKey() instanceof CollectionView) && ((CollectionView) entry.getKey()).getRecyclerView() != null && ((CollectionView) entry.getKey()).getRecyclerView().getAdapter() != null) {
                        ((CollectionView) entry.getKey()).getRecyclerView().getAdapter().notifyDataSetChanged();
                    }
                    if (!this.f1901c.isFirstBind()) {
                        if (DynamicTemplateEngine.this.getRootContainer() != null) {
                            DynamicTemplateEngine.this.getRootContainer().post(new Runnable() { // from class: com.jd.dynamic.base.n0
                                {
                                    DynamicTemplateEngine.AsyncBindTask.this = this;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    DynamicTemplateEngine.AsyncBindTask.this.d(entry);
                                }
                            });
                        } else {
                            DynamicTemplateEngine.this.R(entry.getValue(), entry.getKey());
                            DynamicTemplateEngine.this.A(entry.getValue(), entry.getKey());
                        }
                    }
                }
                try {
                    this.a.put("dynamic_private_data", jSONArray);
                } catch (JSONException e3) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND_ASYNC, e3.getMessage(), DynamicTemplateEngine.this.getBizField(), DynamicTemplateEngine.this.getSystemCode(), R2.attr.layout_constraintHeight_percent, e3, com.jd.dynamic.lib.utils.m.q(DynamicTemplateEngine.this.getZipVersion(), null));
                }
                com.jd.dynamic.lib.utils.t.e("BindView", " isFirstBind =" + this.f1901c.isFirstBind(), " bind time = " + DynamicMtaUtil.getCurMicroseconds(j2));
            } else {
                com.jd.dynamic.lib.utils.t.e("BindView", "\u6570\u636eID\u4e0d\u4e00\u81f4");
            }
            if (this.f1901c.isFirstBind()) {
                if (DynamicTemplateEngine.this.getRootContainer() != null) {
                    DynamicTemplateEngine.this.getRootContainer().post(new Runnable() { // from class: com.jd.dynamic.base.m0
                        {
                            DynamicTemplateEngine.AsyncBindTask.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            DynamicTemplateEngine.AsyncBindTask.this.b();
                        }
                    });
                } else {
                    DynamicTemplateEngine.this.h0();
                }
                DynamicMtaUtil.uploadMta(DynamicTemplateEngine.this.f1886h.getApplicationContext(), DynamicTemplateEngine.this.y, DynamicTemplateEngine.this.f1886h.getClass().getCanonicalName());
            }
            this.f1901c.setFirstBind(false);
            IViewBindListener iViewBindListener2 = this.f1903f;
            if (iViewBindListener2 != null) {
                iViewBindListener2.onDynamicViewBind();
            }
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            com.jd.dynamic.lib.utils.t.e("BindView", "task onCancelled");
        }

        public void setOldWidth(double d) {
            this.f1902e = d;
        }
    }

    /* loaded from: classes13.dex */
    public static class ParseResult {
        public Exception exception;
        public long unId;
        public HashMap<View, HashMap<String, String>> viewAttrMaps;

        private ParseResult() {
        }
    }

    public DynamicTemplateEngine(String str, Activity activity, FrameLayout frameLayout, IFunctionFactory iFunctionFactory) {
        this.f1886h = activity;
        this.mPackageName = str;
        this.f1887i = iFunctionFactory;
        this.f1885g = frameLayout;
        i(activity);
        this.G = hashCode();
        this.x = DynamicXParser.getWidth();
    }

    public void A(HashMap<String, String> hashMap, View view) {
        if (this.isAttached && com.jd.dynamic.lib.utils.m.J(hashMap)) {
            String str = hashMap.get("onRefresh");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Iterator<String> it = com.jd.dynamic.lib.utils.s.i(str).iterator();
            while (it.hasNext()) {
                com.jd.dynamic.lib.utils.s.b(it.next(), view, this, view);
            }
        }
    }

    public /* synthetic */ void B(HashMap hashMap, String str) {
        if (TextUtils.isEmpty(getCachePool().getParam(str))) {
            return;
        }
        hashMap.put(str, com.jd.dynamic.lib.utils.s.b(str, this.currentData, this, getRootContainer()));
    }

    public /* synthetic */ void C(Map.Entry entry) {
        com.jd.dynamic.lib.viewparse.c.e.g0 g0Var = new com.jd.dynamic.lib.viewparse.c.e.g0();
        g0Var.e(this);
        g0Var.a((HashMap) entry.getValue(), (View) entry.getKey());
    }

    private void D(final JSONObject jSONObject, final String str, final IFinishAddView iFinishAddView) {
        if (!com.jd.dynamic.lib.utils.m.D()) {
            y(new Runnable() { // from class: com.jd.dynamic.base.r0
                {
                    DynamicTemplateEngine.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    DynamicTemplateEngine.this.S(jSONObject, str, iFinishAddView);
                }
            });
            return;
        }
        MtaTimePair mtaTimePair = new MtaTimePair();
        if (!loadJsSo()) {
            v(this.entity.viewNode, jSONObject, str, iFinishAddView);
            return;
        }
        mtaTimePair.startRecord();
        K();
        Object obj = null;
        if (this.q != null) {
            obj = T();
            DYConstants.DYLog("newInitJSTemplate xpj22 obj is :" + obj);
        }
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            jSONObject = J(jSONObject);
        }
        mtaTimePair.endRecord();
        DynamicMtaUtil.appendLoadJsEnd(str, mtaTimePair);
        v(this.entity.viewNode, jSONObject, str, iFinishAddView);
    }

    private void E(JSONObject jSONObject, boolean z, IViewBindListener iViewBindListener) {
        JSONObject jSONObject2;
        JSONObject J;
        double d = this.x;
        DynamicXParser.updateWAndH(this.f1886h);
        double width = DynamicXParser.getWidth();
        this.x = width;
        if (d != width) {
            JSONObject jSONObject3 = this.currentData;
            if (jSONObject3 != null) {
                jSONObject3.remove("dynamic_private_data");
                this.currentData.remove("dynamic_item_private_data");
                this.currentData.remove("dynamic_private_js_data");
            }
            if (jSONObject != null) {
                jSONObject.remove("dynamic_private_data");
                jSONObject.remove("dynamic_item_private_data");
                jSONObject.remove("dynamic_private_js_data");
            }
        }
        if (getRootContainer() != null && getRootContainer().isFirstBind()) {
            DynamicMtaUtil.reportBusinessData(this.o, jSONObject);
        }
        releaseTimers(false);
        try {
            if (d0()) {
                System.nanoTime();
                if (com.jd.dynamic.b.a.b.o().d()) {
                    initJSEnv();
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("dynamic_private_js_data");
                if (optJSONObject != null) {
                    com.jd.dynamic.lib.utils.t.e("bindViewUseCache", "useCachedJSData", "isAttached = " + this.isAttached);
                    J = optJSONObject;
                } else {
                    J = J(jSONObject);
                }
                if (jSONObject.has("dynamic_private_data") && !J.has("dynamic_private_data")) {
                    J.put("dynamic_private_data", jSONObject.opt("dynamic_private_data"));
                }
                if (jSONObject.has("dynamic_item_private_data") && !J.has("dynamic_item_private_data")) {
                    J.put("dynamic_item_private_data", jSONObject.opt("dynamic_item_private_data"));
                }
                if (optJSONObject == null) {
                    jSONObject.put("dynamic_private_js_data", J);
                }
                this.currentData = J;
                jSONObject2 = J;
            } else {
                this.currentData = jSONObject;
                jSONObject2 = jSONObject;
            }
            BaseFrameLayout rootContainer = getRootContainer();
            if (!z && H(this.currentData, d)) {
                if (rootContainer != 0) {
                    rootContainer.setFirstBind(false);
                }
            } else if (rootContainer != null) {
                HashMap hashMap = new HashMap(this.f1888j);
                if (hashMap.isEmpty()) {
                    if (iViewBindListener != null) {
                        iViewBindListener.onDynamicViewBind();
                        return;
                    }
                    return;
                }
                AsyncBindTask asyncBindTask = new AsyncBindTask(jSONObject, jSONObject2, rootContainer, hashMap, iViewBindListener);
                asyncBindTask.setOldWidth(d);
                if (asyncBindTask.bindViewWithCache()) {
                    rootContainer.setFirstBind(false);
                    return;
                }
                rootContainer.unId = ObjectsCompat.hashCode(jSONObject2);
                if (!z) {
                    asyncBindTask.onPostExecute(asyncBindTask.doInBackground(new Void[0]));
                    return;
                }
                if (rootContainer.getBindTask() != null) {
                    rootContainer.getBindTask().cancel(true);
                }
                rootContainer.setBindTask(asyncBindTask);
                asyncBindTask.execute(new Void[0]);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:298:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x02b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean H(org.json.JSONObject r46, double r47) {
        /*
            Method dump skipped, instructions count: 1134
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.DynamicTemplateEngine.H(org.json.JSONObject, double):boolean");
    }

    private JSONObject J(JSONObject jSONObject) {
        if (this.H != null) {
            try {
                DYConstants.DYLog("XPJ V2: " + this.H);
                return U(jSONObject);
            } catch (Exception e2) {
                e2.printStackTrace();
                return jSONObject;
            }
        }
        com.jd.dynamic.a.g gVar = this.q;
        if (gVar != null) {
            try {
                com.jd.dynamic.a.i d = gVar.d("__dyn");
                this.H = d;
                d.o();
                com.jd.dynamic.a.i b = this.H.b("callJsFunction");
                this.I = b;
                b.o();
                DYConstants.DYLog("XPJ => " + this.H);
                return U(jSONObject);
            } catch (Exception e3) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_JS, "\u8c03\u7528js\u5904\u7406\u6570\u636eapi\u5931\u8d25", this.bizField, this.r, e3);
            }
        }
        return jSONObject;
    }

    private void K() {
        try {
            if (this.q == null) {
                this.q = new com.jd.dynamic.a.g(this, this.bizField, this.r, com.jd.dynamic.b.a.b.o().h());
            }
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_JS, "js\u5f15\u64ce\u521d\u59cb\u5316\u5f02\u5e38", this.bizField, this.r, e2, com.jd.dynamic.lib.utils.m.q(getZipVersion(), null));
        }
    }

    public /* synthetic */ void L(View view) {
        com.jd.dynamic.lib.utils.m.s(view, this);
    }

    public /* synthetic */ void N(ViewNode viewNode, View view) {
        com.jd.dynamic.lib.viewparse.c.v vVar = new com.jd.dynamic.lib.viewparse.c.v();
        vVar.b(this);
        vVar.a(viewNode.getELAttributes(), view);
    }

    public /* synthetic */ void O(com.jd.dynamic.lib.viewparse.c.v vVar, ViewNode viewNode, View view, double d) {
        vVar.a(viewNode.getELAttributes(), view);
        if (d != this.x && (view instanceof CollectionView)) {
            CollectionView collectionView = (CollectionView) view;
            if (collectionView.getRecyclerView() != null && collectionView.getRecyclerView().getAdapter() != null) {
                collectionView.getRecyclerView().getAdapter().notifyDataSetChanged();
            }
        }
        R(viewNode.getAttributes(), view);
        A(viewNode.getAttributes(), view);
    }

    private void P(Runnable runnable) {
        Activity activity = this.f1886h;
        if (activity != null) {
            activity.runOnUiThread(runnable);
        } else {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, "post Runnable but activity is null", this.bizField, this.r, 1015, new RuntimeException(), com.jd.dynamic.lib.utils.m.q(getZipVersion(), null));
        }
    }

    private void Q(String str) {
        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, str, this.bizField, this.r, 1010, new RuntimeException(), com.jd.dynamic.lib.utils.m.q(getZipVersion(), null));
    }

    public void R(HashMap<String, String> hashMap, View view) {
        if (this.isAttached && com.jd.dynamic.lib.utils.m.J(hashMap)) {
            String str = hashMap.get("onBind");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Iterator<String> it = com.jd.dynamic.lib.utils.s.i(str).iterator();
            while (it.hasNext()) {
                com.jd.dynamic.lib.utils.s.b(it.next(), view, this, view);
            }
        }
    }

    public /* synthetic */ void S(final JSONObject jSONObject, final String str, final IFinishAddView iFinishAddView) {
        MtaTimePair mtaTimePair = new MtaTimePair();
        mtaTimePair.startRecord();
        if (!loadJsSo()) {
            v(this.entity.viewNode, jSONObject, str, iFinishAddView);
            return;
        }
        K();
        Object obj = null;
        if (this.q != null) {
            obj = T();
            DYConstants.DYLog("newInitJSTemplate xpj22 obj is :" + obj);
        }
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            jSONObject = J(jSONObject);
        }
        mtaTimePair.endRecord();
        DynamicMtaUtil.appendLoadJsEnd(str, mtaTimePair);
        com.jd.dynamic.b.g.e.a().c().b(new MainJSTask() { // from class: com.jd.dynamic.base.DynamicTemplateEngine.1
            {
                DynamicTemplateEngine.this = this;
            }

            @Override // com.jd.dynamic.b.g.c
            public void execute() {
                DynamicTemplateEngine dynamicTemplateEngine = DynamicTemplateEngine.this;
                dynamicTemplateEngine.v(dynamicTemplateEngine.entity.viewNode, jSONObject, str, iFinishAddView);
            }
        });
    }

    private Object T() {
        long evalJSResult;
        com.jd.dynamic.a.g gVar = this.q;
        if (gVar == null || com.jd.dynamic.lib.utils.m.T(gVar.h())) {
            return null;
        }
        if (!com.jd.dynamic.b.a.b.o().h()) {
            evalJSResult = JavaScriptRuntime.evalJSResult(this.q.h(), this.entity.jsString);
        } else if (TextUtils.isEmpty(this.entity.jsString)) {
            return Boolean.FALSE;
        } else {
            evalJSResult = JavaScriptRuntime.evalJSResultNPT(this.q.h(), this.entity.jsString);
        }
        return com.jd.dynamic.a.h.b(this.q.h(), evalJSResult);
    }

    private JSONObject U(JSONObject jSONObject) {
        if (this.J == null) {
            this.J = new JSONObject();
        }
        this.J.put("originData", jSONObject);
        String a0 = a0(this.J);
        if (TextUtils.isEmpty(a0)) {
            return jSONObject;
        }
        Object c2 = this.H.c(this.I.f1715c, "DYNViewModule", "data", a0);
        return c2 instanceof JSONObject ? (JSONObject) c2 : jSONObject;
    }

    public /* synthetic */ void W(ViewNode viewNode, View view) {
        com.jd.dynamic.lib.viewparse.c.v vVar = new com.jd.dynamic.lib.viewparse.c.v();
        vVar.b(this);
        vVar.a(viewNode.getNoELAttributes(), view);
    }

    public void X(String str) {
        if (!this.p || DynamicSdk.getEngine().hasDarkListener(this) || TextUtils.isEmpty(str) || !str.startsWith("$dark(")) {
            return;
        }
        DynamicSdk.getEngine().addDarkChangeListener(this);
    }

    private Pair<String, String> Z() {
        return new Pair<>(TextUtils.isEmpty(this.r) ? "" : this.r, TextUtils.isEmpty(this.bizField) ? "" : this.bizField);
    }

    private String a0(JSONObject jSONObject) {
        try {
            this.K[0] = jSONObject;
            return com.jd.dynamic.a.c.b + Arrays.toString(this.K);
        } catch (Throwable unused) {
            return null;
        }
    }

    public View b(int i2) {
        return this.f1892n.getViewFromCacheById(i2);
    }

    public /* synthetic */ void b0(ViewNode viewNode, View view) {
        com.jd.dynamic.lib.viewparse.c.v vVar = new com.jd.dynamic.lib.viewparse.c.v();
        vVar.b(this);
        vVar.a(viewNode.getNoELAttributes(), view);
    }

    private JSONObject c0(JSONObject jSONObject) {
        if (jSONObject != null && (!d0() || (jSONObject = J(jSONObject)) != null)) {
            this.currentData = jSONObject;
        }
        return this.currentData;
    }

    private static BaseFrameLayout d(Activity activity, ViewNode viewNode, DynamicTemplateEngine dynamicTemplateEngine, String str) {
        return new BaseFrameLayout(activity, viewNode, dynamicTemplateEngine, false);
    }

    private boolean d0() {
        ResultEntity resultEntity = this.entity;
        return (resultEntity == null || TextUtils.isEmpty(resultEntity.jsString)) ? false : true;
    }

    private Object e(ViewNode viewNode, Object obj, String str, View view) {
        com.jd.dynamic.lib.expv2.d parserEntry = viewNode.getParserEntry();
        if (parserEntry == null) {
            parserEntry = new com.jd.dynamic.lib.expv2.h();
            viewNode.setParserEntry(parserEntry);
        }
        parserEntry.a(this, obj, view);
        return parserEntry.a(str);
    }

    private void e0() {
        if (this.f1888j != null) {
            this.f1890l = new HashMap<>();
            for (Map.Entry<ViewNode, HashMap<String, String>> entry : this.f1888j.entrySet()) {
                HashMap<String, String> hashMap = new HashMap<>();
                for (Map.Entry<String, String> entry2 : entry.getValue().entrySet()) {
                    if (entry2.getValue().startsWith("$dark(")) {
                        hashMap.put(entry2.getKey(), entry2.getValue());
                    }
                }
                if (com.jd.dynamic.lib.utils.m.J(hashMap)) {
                    this.f1890l.put(entry.getKey(), hashMap);
                }
            }
        }
    }

    public static Object expV2Cache(ViewNode viewNode, Object obj, String str, DynamicTemplateEngine dynamicTemplateEngine, View view, boolean z) {
        long nanoTime = System.nanoTime();
        a.e eVar = a.e.f1789l;
        com.jd.dynamic.lib.expv2.c.i h2 = z ? eVar.h(str) : eVar.g(str);
        Q += System.nanoTime() - nanoTime;
        if (h2 != null) {
            Object c2 = h2.c(str, obj, dynamicTemplateEngine, view);
            P += System.nanoTime() - nanoTime;
            return c2;
        } else if (viewNode == null) {
            com.jd.dynamic.lib.expv2.c.i e2 = new com.jd.dynamic.lib.expv2.h().e(str);
            if (e2 == null) {
                return str;
            }
            Object c3 = e2.c(str, obj, dynamicTemplateEngine, view);
            a.e eVar2 = a.e.f1789l;
            if (z) {
                eVar2.d(str, e2);
            } else {
                eVar2.a(str, e2);
            }
            return c3;
        } else {
            com.jd.dynamic.lib.expv2.d parserEntry = viewNode.getParserEntry();
            if (parserEntry == null) {
                parserEntry = new com.jd.dynamic.lib.expv2.h();
                viewNode.setParserEntry(parserEntry);
            }
            com.jd.dynamic.lib.expv2.c.i e3 = ((com.jd.dynamic.lib.expv2.h) parserEntry).e(str);
            if (e3 == null) {
                return str;
            }
            Object c4 = e3.c(str, obj, dynamicTemplateEngine, view);
            a.e eVar3 = a.e.f1789l;
            if (z) {
                eVar3.d(str, e3);
            } else {
                eVar3.a(str, e3);
            }
            return c4;
        }
    }

    private Object f(ViewNode viewNode, Object obj, String str, DynamicTemplateEngine dynamicTemplateEngine, View view, boolean z) {
        if (this.B) {
            return expV2Cache(viewNode, obj, str, dynamicTemplateEngine, view, z);
        }
        long nanoTime = System.nanoTime();
        Object e2 = e(viewNode, obj, str, view);
        this.M += System.nanoTime() - nanoTime;
        return e2;
    }

    private void f0(JSONObject jSONObject) {
        int i2;
        BaseFrameLayout rootContainer = getRootContainer();
        if (rootContainer == null || this.f1888j == null) {
            return;
        }
        final com.jd.dynamic.lib.viewparse.c.v vVar = new com.jd.dynamic.lib.viewparse.c.v();
        vVar.b(this);
        if (this.f1890l == null) {
            e0();
        }
        if (this.f1890l.isEmpty()) {
            return;
        }
        for (Map.Entry<ViewNode, HashMap<String, String>> entry : this.f1890l.entrySet()) {
            HashMap<String, String> value = entry.getValue();
            final ViewNode key = entry.getKey();
            if (key != null && key.getAttributes() != null && (i2 = key.viewId) != 0 && !key.unNeedInitBind) {
                try {
                    final View b = b(i2);
                    if (b == null) {
                        b = rootContainer.findViewById(i2);
                    }
                    key.getELAttributes().clear();
                    for (Map.Entry<String, String> entry2 : value.entrySet()) {
                        newBindDataWithEL(key, jSONObject, entry2.getValue(), entry2.getKey());
                    }
                    if (b != null) {
                        P(new Runnable() { // from class: com.jd.dynamic.base.y0
                            @Override // java.lang.Runnable
                            public final void run() {
                                DynamicTemplateEngine.w(vVar, key, b);
                            }
                        });
                    }
                } catch (Exception e2) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "DynamicTemplateEngine refreshDark error", com.jd.dynamic.lib.utils.m.j(this), com.jd.dynamic.lib.utils.m.O(this), e2);
                }
            }
        }
    }

    private void g0() {
        this.B = com.jd.dynamic.b.a.b.o().q(this.r, this.bizField);
        this.O = com.jd.dynamic.b.a.b.o().t(this.r, this.bizField);
    }

    public void h0() {
        View view;
        View view2;
        if (this.isAttached) {
            try {
                List<Pair<ViewNode, String>> list = this.w;
                if (list != null && !list.isEmpty()) {
                    for (Pair<ViewNode, String> pair : this.w) {
                        ViewNode viewNode = pair.first;
                        if (viewNode != null) {
                            int i2 = viewNode.viewId;
                            view2 = b(i2);
                            if (view2 == null && getRootContainer() != null) {
                                view2 = getRootContainer().findViewById(i2);
                            }
                        } else {
                            view2 = null;
                        }
                        String str = pair.second;
                        if (!TextUtils.isEmpty(str) && view2 != null) {
                            Iterator<String> it = com.jd.dynamic.lib.utils.s.i(str).iterator();
                            while (it.hasNext()) {
                                com.jd.dynamic.lib.utils.s.b(it.next(), view2, this, view2);
                            }
                        }
                    }
                }
                List<Pair<ViewNode, String>> list2 = this.v;
                if (list2 == null || list2.isEmpty()) {
                    return;
                }
                for (Pair<ViewNode, String> pair2 : this.v) {
                    ViewNode viewNode2 = pair2.first;
                    if (viewNode2 == null || viewNode2.isExecOnLoad) {
                        view = null;
                    } else {
                        int i3 = viewNode2.viewId;
                        view = b(i3);
                        if (view == null && getRootContainer() != null) {
                            view = getRootContainer().findViewById(i3);
                        }
                    }
                    String str2 = pair2.second;
                    if (!TextUtils.isEmpty(str2) && view != null) {
                        view.setTag(R.id.dynamic_lifecycle_on_load_exec, com.jd.dynamic.b.c.a.b);
                        viewNode2.isExecOnLoad = true;
                        Iterator<String> it2 = com.jd.dynamic.lib.utils.s.i(str2).iterator();
                        while (it2.hasNext()) {
                            com.jd.dynamic.lib.utils.s.b(it2.next(), view, this, view);
                        }
                    }
                }
            } catch (Exception e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "DynamicTemplateEngine handleLifecycleEvents error", com.jd.dynamic.lib.utils.m.j(this), com.jd.dynamic.lib.utils.m.O(this), e2);
            }
        }
    }

    private void i(Activity activity) {
    }

    public /* synthetic */ void i0() {
        if (loadJsSo()) {
            K();
            if (this.q == null || this.L) {
                return;
            }
            Object T = T();
            DYConstants.DYLog("xpj22 obj is :" + T);
            if ((T instanceof Boolean) && ((Boolean) T).booleanValue()) {
                this.L = true;
            }
        }
    }

    public /* synthetic */ void j(View view) {
        com.jd.dynamic.lib.utils.m.s(view, this);
    }

    public /* synthetic */ void k(Pair pair, long j2, ViewNode viewNode, final IFinishAddView iFinishAddView, String str) {
        DynamicMtaUtil.reportRenderEnterPost((String) pair.first, (String) pair.second, SystemClock.uptimeMillis() - j2);
        MtaTimePair newInstance = MtaTimePair.newInstance();
        newInstance.startRecord();
        BaseFrameLayout d = d(this.f1886h, viewNode, this, "newInitTemplate--");
        this.f1885g.removeAllViews();
        this.f1885g.addView(d, new FrameLayout.LayoutParams(-1, -1));
        this.f1885g.post(new Runnable() { // from class: com.jd.dynamic.base.DynamicTemplateEngine.2
            {
                DynamicTemplateEngine.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                DynamicTemplateEngine.this.h0();
                IFinishAddView iFinishAddView2 = iFinishAddView;
                if (iFinishAddView2 != null) {
                    iFinishAddView2.finishAddViewInPost();
                }
            }
        });
        newInstance.endRecord();
        DynamicMtaUtil.appendRenderMtaStat(str, newInstance);
        DynamicMtaUtil.endLoadTemplate(TextUtils.isEmpty(this.r) ? "" : this.r, TextUtils.isEmpty(this.bizField) ? "" : this.bizField, str, SystemClock.uptimeMillis() - j2);
        DynamicMtaUtil.uploadMta(this.f1886h.getApplicationContext(), str, this.f1886h.getClass().getCanonicalName());
        DYConstants.DYLog("do init end ++++++++++++");
    }

    public /* synthetic */ void l(Pair pair, long j2, HashMap hashMap, ViewNode viewNode, ViewNode viewNode2, final IFinishAddView iFinishAddView, String str) {
        DynamicMtaUtil.reportRenderEnterPost((String) pair.first, (String) pair.second, SystemClock.uptimeMillis() - j2);
        MtaTimePair newInstance = MtaTimePair.newInstance();
        newInstance.startRecord();
        this.f1888j = hashMap;
        BaseFrameLayout parseBFLayout = parseBFLayout(this.f1886h, viewNode, this, viewNode2, "initTemplate333333");
        this.f1885g.removeAllViews();
        this.f1885g.addView(parseBFLayout, new FrameLayout.LayoutParams(-1, -1));
        this.f1885g.post(new Runnable(this) { // from class: com.jd.dynamic.base.DynamicTemplateEngine.3
            @Override // java.lang.Runnable
            public void run() {
                IFinishAddView iFinishAddView2 = iFinishAddView;
                if (iFinishAddView2 != null) {
                    iFinishAddView2.finishAddViewInPost();
                }
            }
        });
        newInstance.endRecord();
        DynamicMtaUtil.appendRenderMtaStat(str, newInstance);
        DynamicMtaUtil.endLoadTemplate(TextUtils.isEmpty(this.r) ? "" : this.r, TextUtils.isEmpty(this.bizField) ? "" : this.bizField, str, SystemClock.uptimeMillis() - j2);
        DynamicMtaUtil.uploadMta(this.f1886h.getApplicationContext(), str, this.f1886h.getClass().getCanonicalName());
    }

    public /* synthetic */ void m(Pair pair, long j2, HashMap hashMap, ViewNode viewNode, ViewNode viewNode2, String str) {
        DynamicMtaUtil.reportRenderEnterPost((String) pair.first, (String) pair.second, SystemClock.uptimeMillis() - j2);
        MtaTimePair newInstance = MtaTimePair.newInstance();
        newInstance.startRecord();
        this.f1888j = hashMap;
        BaseFrameLayout parseBFLayout = parseBFLayout(this.f1886h, viewNode, this, viewNode2, "initTemplate111111");
        this.f1885g.removeAllViews();
        this.f1885g.addView(parseBFLayout, new FrameLayout.LayoutParams(-1, -1));
        newInstance.endRecord();
        DynamicMtaUtil.appendRenderMtaStat(str, newInstance);
        DynamicMtaUtil.endLoadTemplate(TextUtils.isEmpty(this.r) ? "" : this.r, TextUtils.isEmpty(this.bizField) ? "" : this.bizField, str, SystemClock.uptimeMillis() - j2);
        DynamicMtaUtil.uploadMta(this.f1886h.getApplicationContext(), str, this.f1886h.getClass().getCanonicalName());
    }

    public static BaseFrameLayout parseBFLayout(Activity activity, ViewNode viewNode, DynamicTemplateEngine dynamicTemplateEngine, ViewNode viewNode2, String str) {
        if (viewNode2 != null) {
            com.jd.dynamic.lib.utils.s.e(dynamicTemplateEngine, viewNode2);
        }
        BaseFrameLayout baseFrameLayout = new BaseFrameLayout(activity, viewNode, dynamicTemplateEngine, false);
        DYConstants.DYLog(str + " engine end parse base frame layout .....");
        return baseFrameLayout;
    }

    public void r(ViewNode viewNode) {
        if (this.isAttached && viewNode.getAttributes() != null) {
            String str = viewNode.getAttributes().get("onBind");
            if (!TextUtils.isEmpty(str) && !viewNode.isSavedOnBind) {
                viewNode.isSavedOnBind = true;
                this.w.add(new Pair<>(viewNode, str));
            }
            String str2 = viewNode.getAttributes().get("onLoad");
            if (TextUtils.isEmpty(str2) || viewNode.isSavedOnLoad) {
                return;
            }
            viewNode.isSavedOnLoad = true;
            this.v.add(new Pair<>(viewNode, str2));
        }
    }

    public /* synthetic */ void s(ViewNode viewNode, View view) {
        com.jd.dynamic.lib.viewparse.c.v vVar = new com.jd.dynamic.lib.viewparse.c.v();
        vVar.b(this);
        vVar.a(viewNode.getNoELAttributes(), view);
    }

    private void t(ViewNode viewNode, Object obj, String str, String str2, View view) {
        if (str.startsWith("fun{")) {
            viewNode.getELAttributes().put(str2, str);
            return;
        }
        if (this.O) {
            if (8 == viewNode.getVisibilityFlag()) {
                if ("visibility".equals(str2)) {
                    viewNode.getELAttributes().put(str2, "2");
                    return;
                }
                return;
            } else if (4 == viewNode.getVisibilityFlag()) {
                if ("visibility".equals(str2)) {
                    viewNode.getELAttributes().put(str2, "0");
                }
                if (!com.jd.dynamic.lib.utils.m.W(str2)) {
                    return;
                }
            }
        }
        if (!com.jd.dynamic.b.a.b.o().l()) {
            updateScreenWidth();
        }
        Object f2 = f(viewNode, obj, str, this, view, true);
        if (f2 != null) {
            viewNode.getELAttributes().put(str2, DynamicUtils.toString(f2));
        }
    }

    private void u(ViewNode viewNode, Object obj, String str, String str2, String str3, boolean z) {
        HashMap<String, String> eLAttributes;
        String dynamicUtils;
        if (str.startsWith("fun{")) {
            eLAttributes = z ? viewNode.getELAttributes() : viewNode.getAttributes();
            dynamicUtils = DynamicUtils.toString(str);
        } else {
            if (this.O) {
                if (8 == viewNode.getVisibilityFlag()) {
                    if ("visibility".equals(str2)) {
                        (z ? viewNode.getELAttributes() : viewNode.getAttributes()).put(str2, "2");
                        return;
                    }
                    return;
                } else if (4 == viewNode.getVisibilityFlag()) {
                    if ("visibility".equals(str2)) {
                        (z ? viewNode.getELAttributes() : viewNode.getAttributes()).put(str2, "0");
                    }
                    if (!com.jd.dynamic.lib.utils.m.W(str2)) {
                        return;
                    }
                }
            }
            if (!com.jd.dynamic.b.a.b.o().l()) {
                updateScreenWidth();
            }
            Object f2 = f(viewNode, obj, str, this, null, false);
            if (f2 == null) {
                return;
            }
            eLAttributes = z ? viewNode.getELAttributes() : viewNode.getAttributes();
            dynamicUtils = DynamicUtils.toString(f2);
        }
        eLAttributes.put(str2, dynamicUtils);
    }

    public void v(ViewNode viewNode, JSONObject jSONObject, final String str, final IFinishAddView iFinishAddView) {
        ViewNode viewNode2;
        if (viewNode == null || this.f1885g == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("node is null : ");
            sb.append(viewNode == null);
            sb.append(" container: ");
            sb.append(this.f1885g);
            Q(sb.toString());
            return;
        }
        releaseTimers(false);
        P = 0L;
        this.M = 0L;
        Q = 0L;
        DYConstants.DYLog("xpj22 do init start ++++++++++++");
        long nanoTime = System.nanoTime();
        this.N = viewNode;
        ViewNode viewNode3 = null;
        if (this.O) {
            calculateVisibilityFromRoot(viewNode, jSONObject, null, this);
            DYConstants.DYLog("calc do init start openVisibilityFeature " + (System.nanoTime() - nanoTime));
        }
        final long uptimeMillis = SystemClock.uptimeMillis();
        z(str);
        if (jSONObject != null) {
            this.currentData = jSONObject;
        }
        if ("DynamicData".equals(viewNode.getViewName())) {
            if (!this.useAsyncItem.booleanValue()) {
                this.useAsyncItem = Boolean.valueOf(viewNode.getAttributes().containsKey("useAsyncItem"));
            }
            if (!this.useTagViewOptimize) {
                this.useTagViewOptimize = viewNode.getAttributes().containsKey("useTagViewOptimize");
            }
            ViewNode childByName = viewNode.getChildByName(DYConstants.DY_FLEXBOX_LAYOUT);
            viewNode3 = viewNode.getChildByName("Events");
            viewNode2 = childByName;
        } else {
            viewNode2 = null;
        }
        if (viewNode3 != null) {
            com.jd.dynamic.lib.utils.s.e(this, viewNode3);
        }
        updateScreenWidth();
        DYConstants.DYLog("before exp : ++++++++");
        if (viewNode.unBindMaps != null) {
            MtaTimePair newInstance = MtaTimePair.newInstance();
            newInstance.startRecord();
            for (Map.Entry<ViewNode, HashMap<String, String>> entry : viewNode.unBindMaps.entrySet()) {
                HashMap<String, String> value = entry.getValue();
                ViewNode key = entry.getKey();
                if (!key.unNeedInitBind) {
                    for (Map.Entry<String, String> entry2 : value.entrySet()) {
                        X(entry2.getValue());
                        newBindData(key, jSONObject, entry2.getValue(), entry2.getKey());
                    }
                    r(key);
                }
            }
            newInstance.endRecord();
            DynamicMtaUtil.appendBindDataMtaStat(str, newInstance);
            this.f1888j = viewNode.unBindMaps;
            this.f1889k = viewNode.elAttrMapping;
        }
        DYConstants.DYLog("calc use time : " + (System.nanoTime() - nanoTime) + " open:" + this.O + " cache : " + P + " -- and normal:  " + this.M + " get time: " + Q);
        ViewNode viewNode4 = viewNode2 == null ? viewNode : viewNode2;
        DYConstants.DYLog("end exp : -------------");
        final Pair<String, String> Z = Z();
        DynamicMtaUtil.reportRenderActivityStatus(this.f1886h, Z.first, Z.second, SystemClock.uptimeMillis() - uptimeMillis);
        final ViewNode viewNode5 = viewNode4;
        y(new Runnable() { // from class: com.jd.dynamic.base.k0
            {
                DynamicTemplateEngine.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                DynamicTemplateEngine.this.k(Z, uptimeMillis, viewNode5, iFinishAddView, str);
            }
        });
        DYConstants.DYLog("XPJ a test calc time is : " + (System.nanoTime() - nanoTime));
    }

    public static /* synthetic */ void w(com.jd.dynamic.lib.viewparse.c.v vVar, ViewNode viewNode, View view) {
        vVar.a(viewNode.getELAttributes(), view);
    }

    public /* synthetic */ void x(com.jd.dynamic.lib.viewparse.c.v vVar, ViewNode viewNode, View view, double d) {
        vVar.a(viewNode.getELAttributes(), view);
        if (d != this.x && (view instanceof CollectionView)) {
            CollectionView collectionView = (CollectionView) view;
            if (collectionView.getRecyclerView() != null && collectionView.getRecyclerView().getAdapter() != null) {
                collectionView.getRecyclerView().getAdapter().notifyDataSetChanged();
            }
        }
        R(viewNode.getAttributes(), view);
        A(viewNode.getAttributes(), view);
    }

    private void y(Runnable runnable) {
        if (com.jd.dynamic.lib.utils.m.D()) {
            runnable.run();
        } else {
            P(runnable);
        }
    }

    private void z(String str) {
        DynamicMtaUtil.startDynRender(this.r, this.bizField, str);
    }

    @MainThread
    public boolean G(JSONObject jSONObject) {
        if (d0()) {
            JSONObject J = J(jSONObject);
            if (J == jSONObject) {
                this.currentData = J;
                return false;
            }
            try {
                jSONObject.put("dynamic_private_js_data", J);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            jSONObject = J;
        }
        this.currentData = jSONObject;
        return true;
    }

    public void addLogListener(com.jd.dynamic.a.e eVar) {
        if (this.D.contains(eVar)) {
            return;
        }
        this.D.add(eVar);
    }

    public void bindData(ViewNode viewNode, Object obj, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        u(viewNode, obj, str, str2, "bindData", false);
    }

    public void bindView(JSONObject jSONObject) {
        E(jSONObject, false, null);
    }

    public void bindViewAsync(JSONObject jSONObject, IViewBindListener iViewBindListener) {
        E(jSONObject, true, iViewBindListener);
    }

    public void calculateVisibilityFromRoot(ViewNode viewNode, Object obj, View view, DynamicTemplateEngine dynamicTemplateEngine) {
        if (viewNode != null && this.O) {
            if (!TextUtils.isEmpty(viewNode.getVisibilityExp())) {
                Object expV2Cache = expV2Cache(viewNode, obj, viewNode.getVisibilityExp(), dynamicTemplateEngine, view, false);
                if (expV2Cache instanceof String) {
                    String str = (String) expV2Cache;
                    if (!TextUtils.isEmpty(str)) {
                        viewNode.updateVisibility(str);
                    }
                }
            }
            List<ViewNode> childs = viewNode.getChilds();
            if (childs == null) {
                return;
            }
            Iterator<ViewNode> it = childs.iterator();
            while (it.hasNext()) {
                calculateVisibilityFromRoot(it.next(), obj, view, dynamicTemplateEngine);
            }
        }
    }

    public void clearItemLocks() {
        this.C.clear();
    }

    public void execEvent(int i2, String... strArr) {
        if (strArr != null) {
            try {
                if (strArr.length > 0) {
                    View b = b(com.jd.dynamic.lib.dynamic.parser.h.a(this, String.valueOf(i2)));
                    if (b == null) {
                        b = getRootContainer().findViewById(i2);
                    }
                    for (String str : strArr) {
                        if (!TextUtils.isEmpty(getCachePool().getParam(str))) {
                            com.jd.dynamic.lib.utils.s.b(str, b, this, b);
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public Activity getActivity() {
        return this.f1886h;
    }

    public String getBizField() {
        return this.bizField;
    }

    public CachePool getCachePool() {
        if (this.f1892n == null) {
            this.f1892n = new CachePool(this);
        }
        return this.f1892n;
    }

    public IFunctionFactory getCustomFactory() {
        return this.f1887i;
    }

    public Map<String, ICustomView> getCustomViewMap() {
        return this.s;
    }

    public IFunctionFactory getDefaultFactory() {
        if (this.f1891m == null) {
            this.f1891m = new com.jd.dynamic.b.b.a.a();
        }
        return this.f1891m;
    }

    public Dialog getDialog() {
        return this.t;
    }

    public SparseArrayCompat<HashMap<String, String>> getElAttrMapping() {
        return this.f1889k;
    }

    public int getEngineHashCode() {
        return this.G;
    }

    public HashMap<ViewNode, HashMap<String, String>> getHasDarkMap() {
        return this.f1890l;
    }

    public Object getItemLock(String str, boolean z) {
        Object obj = this.C.get(str);
        if (z && obj == null) {
            Object obj2 = new Object();
            this.C.put(str, obj2);
            return obj2;
        }
        return obj;
    }

    public com.jd.dynamic.a.g getJscDynContext() {
        return this.q;
    }

    public List<com.jd.dynamic.a.e> getListLogInterface() {
        return this.D;
    }

    public com.jd.dynamic.lib.utils.u getRecyclerInnerCachePool() {
        return this.u;
    }

    public BaseFrameLayout getRootContainer() {
        BaseFrameLayout baseFrameLayout = this.z;
        if (baseFrameLayout != null) {
            return baseFrameLayout;
        }
        FrameLayout frameLayout = this.f1885g;
        if (frameLayout != null) {
            View findViewById = frameLayout.findViewById(R.id.dynamic_root_view);
            if (findViewById instanceof BaseFrameLayout) {
                return (BaseFrameLayout) findViewById;
            }
            return null;
        }
        return null;
    }

    public String getSystemCode() {
        return this.r;
    }

    public Template getTemplate() {
        return this.o;
    }

    public TimerManager getTimerManager() {
        return this.A;
    }

    public Map<ViewNode, HashMap<String, String>> getUnbindMap() {
        return this.f1888j;
    }

    public Map<String, Integer> getViewIdTable() {
        return this.E;
    }

    public String getZipVersion() {
        ViewNode viewNode = this.N;
        return viewNode != null ? viewNode.zipVersion : "";
    }

    public FrameLayout getmTemplateContainer() {
        return this.f1885g;
    }

    @MainThread
    public void h() {
        ResultEntity resultEntity = this.entity;
        if (resultEntity == null || TextUtils.isEmpty(resultEntity.jsString) || !loadJsSo()) {
            return;
        }
        K();
        if (this.q != null) {
            Object T = T();
            DYConstants.DYLog("xpj22 obj is :" + T);
            if (!((T instanceof Boolean) && ((Boolean) T).booleanValue())) {
            }
        }
    }

    public void initJSEnv() {
        if (!com.jd.dynamic.lib.utils.m.D()) {
            y(new Runnable() { // from class: com.jd.dynamic.base.q0
                {
                    DynamicTemplateEngine.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    DynamicTemplateEngine.this.i0();
                }
            });
        } else if (loadJsSo()) {
            K();
            if (this.q == null || this.L) {
                return;
            }
            Object T = T();
            DYConstants.DYLog("xpj22 obj is :" + T);
            if ((T instanceof Boolean) && ((Boolean) T).booleanValue()) {
                this.L = true;
            }
        }
    }

    public void initTPFromContainer(ViewNode viewNode, JSONObject jSONObject, String str) {
        try {
            Template tempByMtaId = DynamicMtaUtil.getTempByMtaId(str);
            this.o = tempByMtaId;
            DynamicMtaUtil.reportBusinessData(tempByMtaId, jSONObject);
            this.N = viewNode;
            ResultEntity resultEntity = this.entity;
            if (resultEntity == null || TextUtils.isEmpty(resultEntity.jsString)) {
                v(viewNode, jSONObject, str, null);
            } else {
                D(jSONObject, str, null);
            }
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, "newInitTemplate catch Exception", this.bizField, this.r, 1014, e2, com.jd.dynamic.lib.utils.m.q(getZipVersion(), null));
        }
    }

    public void initTemplate(ViewNode viewNode, JSONObject jSONObject, final String str) {
        final ViewNode viewNode2;
        DynamicMtaUtil.reportOldApiInfo(this.f1886h, this.r, this.bizField);
        try {
            Template tempByMtaId = DynamicMtaUtil.getTempByMtaId(str);
            this.o = tempByMtaId;
            DynamicMtaUtil.reportBusinessData(tempByMtaId, jSONObject);
            if (viewNode != null && this.f1885g != null) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                z(str);
                if (jSONObject != null) {
                    this.currentData = jSONObject;
                }
                ViewNode viewNode3 = null;
                if ("DynamicData".equals(viewNode.getViewName())) {
                    viewNode3 = viewNode.getChildByName(DYConstants.DY_FLEXBOX_LAYOUT);
                    viewNode2 = viewNode.getChildByName("Events");
                } else {
                    viewNode2 = null;
                }
                final HashMap hashMap = new HashMap();
                if (viewNode.unBindMaps != null) {
                    MtaTimePair newInstance = MtaTimePair.newInstance();
                    newInstance.startRecord();
                    Iterator<Map.Entry<ViewNode, HashMap<String, String>>> it = viewNode.unBindMaps.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<ViewNode, HashMap<String, String>> next = it.next();
                        ViewNode key = next.getKey();
                        ViewNode m18clone = key.m18clone();
                        HashMap hashMap2 = new HashMap();
                        Iterator<Map.Entry<String, String>> it2 = next.getValue().entrySet().iterator();
                        while (it2.hasNext()) {
                            Map.Entry<String, String> next2 = it2.next();
                            Iterator<Map.Entry<ViewNode, HashMap<String, String>>> it3 = it;
                            X(next2.getValue());
                            Iterator<Map.Entry<String, String>> it4 = it2;
                            hashMap2.put(next2.getKey(), next2.getValue());
                            if (!key.unNeedInitBind) {
                                bindData(key, jSONObject, next2.getValue(), next2.getKey());
                            }
                            it2 = it4;
                            it = it3;
                        }
                        hashMap.put(m18clone, hashMap2);
                        it = it;
                    }
                    newInstance.endRecord();
                    DynamicMtaUtil.appendBindDataMtaStat(str, newInstance);
                }
                final ViewNode viewNode4 = viewNode3 == null ? viewNode : viewNode3;
                final Pair<String, String> Z = Z();
                DynamicMtaUtil.reportRenderActivityStatus(this.f1886h, Z.first, Z.second, SystemClock.uptimeMillis() - uptimeMillis);
                y(new Runnable() { // from class: com.jd.dynamic.base.v0
                    {
                        DynamicTemplateEngine.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        DynamicTemplateEngine.this.m(Z, uptimeMillis, hashMap, viewNode4, viewNode2, str);
                    }
                });
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("node is null : ");
            sb.append(viewNode == null);
            sb.append(" container: ");
            sb.append(this.f1885g);
            Q(sb.toString());
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, "initTemplate catch Exception", this.bizField, this.r, 1014, e2);
        }
    }

    public void initTemplate(ViewNode viewNode, JSONObject jSONObject, final String str, final IFinishAddView iFinishAddView) {
        final ViewNode viewNode2;
        try {
            Template tempByMtaId = DynamicMtaUtil.getTempByMtaId(str);
            this.o = tempByMtaId;
            DynamicMtaUtil.reportBusinessData(tempByMtaId, jSONObject);
            if (viewNode != null && this.f1885g != null) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                z(str);
                if (jSONObject != null) {
                    this.currentData = jSONObject;
                }
                ViewNode viewNode3 = null;
                if ("DynamicData".equals(viewNode.getViewName())) {
                    viewNode3 = viewNode.getChildByName(DYConstants.DY_FLEXBOX_LAYOUT);
                    viewNode2 = viewNode.getChildByName("Events");
                } else {
                    viewNode2 = null;
                }
                final HashMap hashMap = new HashMap();
                if (viewNode.unBindMaps != null) {
                    MtaTimePair newInstance = MtaTimePair.newInstance();
                    newInstance.startRecord();
                    Iterator<Map.Entry<ViewNode, HashMap<String, String>>> it = viewNode.unBindMaps.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<ViewNode, HashMap<String, String>> next = it.next();
                        ViewNode key = next.getKey();
                        ViewNode m18clone = key.m18clone();
                        HashMap hashMap2 = new HashMap();
                        Iterator<Map.Entry<String, String>> it2 = next.getValue().entrySet().iterator();
                        while (it2.hasNext()) {
                            Map.Entry<String, String> next2 = it2.next();
                            Iterator<Map.Entry<ViewNode, HashMap<String, String>>> it3 = it;
                            X(next2.getValue());
                            Iterator<Map.Entry<String, String>> it4 = it2;
                            hashMap2.put(next2.getKey(), next2.getValue());
                            if (!key.unNeedInitBind) {
                                bindData(key, jSONObject, next2.getValue(), next2.getKey());
                            }
                            it2 = it4;
                            it = it3;
                        }
                        hashMap.put(m18clone, hashMap2);
                        it = it;
                    }
                    newInstance.endRecord();
                    DynamicMtaUtil.appendBindDataMtaStat(str, newInstance);
                }
                final ViewNode viewNode4 = viewNode3 == null ? viewNode : viewNode3;
                final Pair<String, String> Z = Z();
                DynamicMtaUtil.reportRenderActivityStatus(this.f1886h, Z.first, Z.second, SystemClock.uptimeMillis() - uptimeMillis);
                y(new Runnable() { // from class: com.jd.dynamic.base.p0
                    {
                        DynamicTemplateEngine.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        DynamicTemplateEngine.this.l(Z, uptimeMillis, hashMap, viewNode4, viewNode2, iFinishAddView, str);
                    }
                });
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("node is null : ");
            sb.append(viewNode == null);
            sb.append(" container: ");
            sb.append(this.f1885g);
            Q(sb.toString());
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, "initTemplate with callBack catch Exception", this.bizField, this.r, 1014, e2);
        }
    }

    public boolean loadJsSo() {
        if (DynamicSdk.hasLoadJsSo) {
            return true;
        }
        try {
            SoLoader.loadLibrary("dynamic-jsc");
            JSCException.init();
            DynamicSdk.hasLoadJsSo = true;
            return true;
        } catch (Throwable th) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, "load libdynamic-js.so failed!!!", this.bizField, this.r, 1014, new Exception(com.jd.dynamic.lib.utils.t.d(th)), com.jd.dynamic.lib.utils.m.q(this.entity.zipVersion, null));
            return false;
        }
    }

    public void newBindData(ViewNode viewNode, Object obj, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        u(viewNode, obj, str, str2, "newBindData", false);
    }

    public void newBindDataWithEL(ViewNode viewNode, Object obj, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        u(viewNode, obj, str, str2, "newBindDataWithEL", true);
    }

    public void newBindDataWithELWithView(ViewNode viewNode, Object obj, String str, String str2, View view) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        t(viewNode, obj, str, str2, view);
    }

    public void newInitTemplate(ViewNode viewNode, JSONObject jSONObject, String str) {
        DynamicMtaUtil.reportOldApiInfo(this.f1886h, this.r, this.bizField);
        initTPFromContainer(viewNode, jSONObject, str);
    }

    public void newInitTemplate(ViewNode viewNode, JSONObject jSONObject, String str, IFinishAddView iFinishAddView) {
        DynamicMtaUtil.reportOldApiInfo(this.f1886h, this.r, this.bizField);
        try {
            Template tempByMtaId = DynamicMtaUtil.getTempByMtaId(str);
            this.o = tempByMtaId;
            DynamicMtaUtil.reportBusinessData(tempByMtaId, jSONObject);
            this.N = viewNode;
            ResultEntity resultEntity = this.entity;
            if (resultEntity == null || TextUtils.isEmpty(resultEntity.jsString)) {
                v(viewNode, jSONObject, str, iFinishAddView);
            } else {
                D(jSONObject, str, iFinishAddView);
            }
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, "newInitTemplate with callBack catch Exception", this.bizField, this.r, 1014, e2, com.jd.dynamic.lib.utils.m.q(getZipVersion(), null));
        }
    }

    public void newRefreshCountDownView(JSONObject jSONObject) {
        Map<ViewNode, HashMap<String, String>> map;
        int i2;
        c0(jSONObject);
        BaseFrameLayout rootContainer = getRootContainer();
        if (rootContainer == null || (map = this.f1888j) == null) {
            return;
        }
        for (Map.Entry<ViewNode, HashMap<String, String>> entry : map.entrySet()) {
            HashMap<String, String> value = entry.getValue();
            final ViewNode key = entry.getKey();
            if (key != null && TextUtils.equals("TextView", key.getViewName()) && key.getAttributes() != null && key.getAttributes().containsKey(DYConstants.DY_COUNTDOWN) && (i2 = key.viewId) != 0 && !key.unNeedInitBind) {
                try {
                    final View b = b(i2);
                    if (b == null) {
                        b = rootContainer.findViewById(i2);
                    }
                    for (Map.Entry<String, String> entry2 : value.entrySet()) {
                        newBindDataWithEL(key, jSONObject, entry2.getValue(), entry2.getKey());
                    }
                    key.getELAttributes().put(DYConstants.DY_COUNTDOWN, DynamicUtils.toString(key.getAttributes().get(DYConstants.DY_COUNTDOWN)));
                    if (b != null) {
                        P(new Runnable() { // from class: com.jd.dynamic.base.u0
                            {
                                DynamicTemplateEngine.this = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                DynamicTemplateEngine.this.N(key, b);
                            }
                        });
                    }
                } catch (Exception e2) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "DynamicTemplateEngine newRefreshCountDownView error", com.jd.dynamic.lib.utils.m.j(this), com.jd.dynamic.lib.utils.m.O(this), e2);
                }
            }
        }
    }

    public void newRefreshItemView(JSONObject jSONObject, int i2, View view) {
        com.jd.dynamic.lib.utils.m.C(jSONObject, i2, view, this, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x015b A[Catch: Exception -> 0x01a0, TryCatch #0 {Exception -> 0x01a0, blocks: (B:108:0x0078, B:114:0x0088, B:117:0x008d, B:120:0x009e, B:122:0x00ac, B:131:0x0107, B:136:0x014d, B:137:0x0155, B:139:0x015b, B:141:0x0167, B:143:0x0173, B:144:0x017b, B:145:0x018b, B:111:0x0080), top: B:151:0x0078 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void newRefreshView(org.json.JSONObject r20) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.DynamicTemplateEngine.newRefreshView(org.json.JSONObject):void");
    }

    public void newRefreshView(JSONObject jSONObject, int i2, View view) {
        com.jd.dynamic.lib.utils.m.C(jSONObject, i2, view, this, false);
    }

    public Map<String, Object> onCustomEvent(@NonNull String str, String str2) {
        ViewNode viewNode;
        if (TextUtils.isEmpty(str2)) {
            ViewNode viewNode2 = this.N;
            if (viewNode2 == null) {
                return null;
            }
            viewNode = viewNode2.getChildByName(DYConstants.DY_FLEXBOX_LAYOUT);
        } else {
            int a = com.jd.dynamic.lib.dynamic.parser.h.a(this, str2);
            if (com.jd.dynamic.lib.utils.m.J(this.f1888j)) {
                for (Map.Entry<ViewNode, HashMap<String, String>> entry : this.f1888j.entrySet()) {
                    if (a == entry.getKey().viewId) {
                        viewNode = entry.getKey();
                        break;
                    }
                }
            }
            viewNode = null;
        }
        if (viewNode != null && com.jd.dynamic.lib.utils.m.J(viewNode.getAttributes())) {
            String str3 = viewNode.getAttributes().get(str);
            if (!TextUtils.isEmpty(str3)) {
                List<String> i2 = com.jd.dynamic.lib.utils.s.i(str3);
                if (com.jd.dynamic.lib.utils.m.I(i2)) {
                    final HashMap hashMap = new HashMap();
                    Observable.from(i2).forEach(new Action1() { // from class: com.jd.dynamic.base.w0
                        {
                            DynamicTemplateEngine.this = this;
                        }

                        @Override // rx.functions.Action1
                        public final void call(Object obj) {
                            DynamicTemplateEngine.this.B(hashMap, (String) obj);
                        }
                    });
                    return hashMap;
                }
            }
        }
        return null;
    }

    @Override // com.jd.dynamic.base.interfaces.IDarkChangeListener
    public void onDarkChange(boolean z) {
        JSONObject jSONObject = this.currentData;
        if (jSONObject != null) {
            f0(jSONObject);
        }
        if (com.jd.dynamic.lib.utils.m.I(getCachePool().getDarkChangeListeners())) {
            Iterator<IDarkChangeListener> it = getCachePool().getDarkChangeListeners().iterator();
            while (it.hasNext()) {
                it.next().onDarkChange(DynamicSdk.getEngine().getDynamicDark().isDarkMode());
            }
        }
    }

    public void parseViewListeners() {
        if (com.jd.dynamic.lib.utils.m.J(this.unBindListenerViews)) {
            Observable.from(this.unBindListenerViews.entrySet()).forEach(new Action1() { // from class: com.jd.dynamic.base.s0
                {
                    DynamicTemplateEngine.this = this;
                }

                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    DynamicTemplateEngine.this.C((Map.Entry) obj);
                }
            });
            this.unBindListenerViews.clear();
        }
    }

    public void preEngine() {
        ViewNode viewNode = this.entity.viewNode;
        if (viewNode == null) {
            return;
        }
        ViewNode viewNode2 = null;
        if ("DynamicData".equals(viewNode.getViewName())) {
            this.entity.viewNode.getChildByName(DYConstants.DY_FLEXBOX_LAYOUT);
            viewNode2 = this.entity.viewNode.getChildByName("Events");
        }
        if (viewNode2 != null) {
            System.nanoTime();
            com.jd.dynamic.lib.utils.s.e(this, viewNode2);
        }
        this.f1888j = this.entity.viewNode.unBindMaps;
        e0();
    }

    public void preParseElValue(JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry<ViewNode, HashMap<String, String>> entry : this.f1888j.entrySet()) {
            HashMap<String, String> value = entry.getValue();
            ViewNode key = entry.getKey();
            if (!key.unNeedInitBind) {
                for (Map.Entry<String, String> entry2 : value.entrySet()) {
                    if (DynamicUtils.isElOrKnownSymbol(entry2.getValue())) {
                        newBindDataWithEL(key, this.currentData, entry2.getValue(), entry2.getKey());
                        if (!TextUtils.isEmpty(entry2.getValue()) && entry2.getValue().contains("$dark(") && DynamicSdk.getEngine().getDynamicDark() != null) {
                            String str = key.getELAttributes().get(entry2.getKey());
                            if (!TextUtils.isEmpty(str)) {
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put(DynamicSdk.getEngine().getDynamicDark().isDarkMode() ? CustomThemeConstance.NAVI_IMAGE_DARK_TAG : "unDark", str);
                                    key.getELAttributes().put(entry2.getKey() + "_dark", jSONObject2.toString());
                                } catch (Exception unused) {
                                }
                            }
                        }
                        if (TextUtils.equals(key.getViewName(), DYConstants.DYN_TAG_VIEW) || (TextUtils.equals(key.getViewName(), DYConstants.DYN_COLLECTION_VIEW) && !TextUtils.isEmpty(key.getELAttributes().get("data")))) {
                            try {
                                com.jd.dynamic.lib.j.c cVar = new com.jd.dynamic.lib.j.c(key, this, new JSONArray(key.getELAttributes().get("data")));
                                for (int i2 = 0; i2 < cVar.a(); i2++) {
                                    cVar.b(i2);
                                }
                            } catch (Exception unused2) {
                            }
                        }
                    }
                }
                if (key.viewId != 0 && com.jd.dynamic.lib.utils.m.J(key.getELAttributes())) {
                    JSONObject jSONObject3 = new JSONObject();
                    try {
                        jSONObject3.put("layoutId", key.viewId);
                        jSONObject3.put("attrs", new JSONObject(key.getELAttributes()));
                    } catch (JSONException e2) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND_ASYNC, e2.getMessage(), getBizField(), getSystemCode(), R2.attr.layout_constraintHeight_min, e2, com.jd.dynamic.lib.utils.m.q(getZipVersion(), null));
                    }
                    jSONArray.put(jSONObject3);
                }
            }
        }
        try {
            JSONObject jSONObject4 = this.currentData;
            if (jSONObject4 != null && jSONObject4.has("dynamic_item_private_data") && !jSONObject.has("dynamic_item_private_data")) {
                jSONObject.put("dynamic_item_private_data", this.currentData.opt("dynamic_item_private_data"));
            }
            jSONObject.put("dynamic_private_data", jSONArray);
        } catch (JSONException e3) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND_ASYNC, e3.getMessage(), getBizField(), getSystemCode(), R2.attr.layout_constraintHeight_percent, e3, com.jd.dynamic.lib.utils.m.q(getZipVersion(), null));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x013d A[Catch: Exception -> 0x0182, TryCatch #1 {Exception -> 0x0182, blocks: (B:129:0x00a5, B:131:0x00ba, B:133:0x00c9, B:134:0x00d2, B:137:0x012f, B:138:0x0137, B:140:0x013d, B:142:0x0149, B:144:0x0155, B:145:0x015d, B:146:0x016d), top: B:157:0x00a5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void rebindDataRefreshView(org.json.JSONObject r19) {
        /*
            Method dump skipped, instructions count: 412
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.DynamicTemplateEngine.rebindDataRefreshView(org.json.JSONObject):void");
    }

    public void refreshCountDownView(JSONObject jSONObject) {
        Map<ViewNode, HashMap<String, String>> map;
        int i2;
        if (jSONObject != null) {
            this.currentData = jSONObject;
        }
        BaseFrameLayout rootContainer = getRootContainer();
        if (rootContainer == null || (map = this.f1888j) == null) {
            return;
        }
        for (Map.Entry<ViewNode, HashMap<String, String>> entry : map.entrySet()) {
            HashMap<String, String> value = entry.getValue();
            ViewNode key = entry.getKey();
            if (key != null && TextUtils.equals("TextView", key.getViewName()) && key.getAttributes() != null && key.getAttributes().containsKey(DYConstants.DY_COUNTDOWN) && (i2 = key.viewId) != 0 && !key.unNeedInitBind) {
                try {
                    final View findViewById = rootContainer.findViewById(i2);
                    final ViewNode m18clone = key.m18clone();
                    for (Map.Entry<String, String> entry2 : value.entrySet()) {
                        bindData(m18clone, jSONObject, entry2.getValue(), entry2.getKey());
                    }
                    if (findViewById != null) {
                        P(new Runnable() { // from class: com.jd.dynamic.base.a1
                            {
                                DynamicTemplateEngine.this = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                DynamicTemplateEngine.this.W(m18clone, findViewById);
                            }
                        });
                    }
                } catch (Exception e2) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "DynamicTemplateEngine refreshCountDownView error", com.jd.dynamic.lib.utils.m.j(this), com.jd.dynamic.lib.utils.m.O(this), e2);
                }
            }
        }
    }

    public void refreshView(JSONObject jSONObject) {
        Map<ViewNode, HashMap<String, String>> map;
        int i2;
        com.jd.dynamic.a.g gVar = this.q;
        if (gVar != null) {
            gVar.j();
        }
        if (jSONObject != null) {
            this.currentData = jSONObject;
        }
        BaseFrameLayout rootContainer = getRootContainer();
        if (rootContainer == null || (map = this.f1888j) == null) {
            return;
        }
        for (Map.Entry<ViewNode, HashMap<String, String>> entry : map.entrySet()) {
            HashMap<String, String> value = entry.getValue();
            ViewNode key = entry.getKey();
            if (key != null && key.getAttributes() != null && (i2 = key.viewId) != 0) {
                try {
                    final View findViewById = rootContainer.findViewById(i2);
                    final ViewNode m18clone = key.m18clone();
                    if (!m18clone.unNeedInitBind) {
                        for (Map.Entry<String, String> entry2 : value.entrySet()) {
                            bindData(m18clone, jSONObject, entry2.getValue(), entry2.getKey());
                        }
                        if (findViewById != null) {
                            P(new Runnable() { // from class: com.jd.dynamic.base.o0
                                {
                                    DynamicTemplateEngine.this = this;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    DynamicTemplateEngine.this.b0(m18clone, findViewById);
                                }
                            });
                        }
                    }
                } catch (Exception e2) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "DynamicTemplateEngine refreshView error", com.jd.dynamic.lib.utils.m.j(this), com.jd.dynamic.lib.utils.m.O(this), e2);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x00a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x00a0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0088 A[Catch: Exception -> 0x00aa, LOOP:1: B:88:0x0082->B:90:0x0088, LOOP_END, TryCatch #0 {Exception -> 0x00aa, blocks: (B:83:0x006a, B:87:0x0076, B:88:0x0082, B:90:0x0088, B:93:0x00a1, B:86:0x0072), top: B:98:0x006a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void refreshView(org.json.JSONObject r8, int r9, android.view.View r10) {
        /*
            r7 = this;
            if (r8 == 0) goto L4
            r7.currentData = r8
        L4:
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            java.lang.String r2 = "refreshView"
            r0[r1] = r2
            r1 = 1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "layoutId = "
            r2.append(r3)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r0[r1] = r2
            com.jd.dynamic.lib.utils.t.e(r0)
            java.lang.String r9 = java.lang.String.valueOf(r9)
            int r9 = com.jd.dynamic.lib.dynamic.parser.h.a(r7, r9)
            com.jd.dynamic.lib.viewparse.BaseFrameLayout r0 = r7.getRootContainer()
            if (r0 == 0) goto Lbb
            java.util.Map<com.jd.dynamic.entity.ViewNode, java.util.HashMap<java.lang.String, java.lang.String>> r1 = r7.f1888j
            if (r1 == 0) goto Lbb
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L3d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto Lbb
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getValue()
            java.util.HashMap r3 = (java.util.HashMap) r3
            java.lang.Object r2 = r2.getKey()
            com.jd.dynamic.entity.ViewNode r2 = (com.jd.dynamic.entity.ViewNode) r2
            if (r2 == 0) goto L3d
            java.util.HashMap r4 = r2.getAttributes()
            if (r4 == 0) goto L3d
            int r4 = r2.viewId
            if (r4 == 0) goto L3d
            if (r4 != r9) goto L3d
            boolean r4 = r2.unNeedInitBind
            if (r4 == 0) goto L68
            goto L3d
        L68:
            if (r10 == 0) goto L72
            int r4 = r10.getId()     // Catch: java.lang.Exception -> Laa
            if (r4 != r9) goto L72
            r4 = r10
            goto L76
        L72:
            android.view.View r4 = r0.findViewById(r9)     // Catch: java.lang.Exception -> Laa
        L76:
            com.jd.dynamic.entity.ViewNode r2 = r2.m18clone()     // Catch: java.lang.Exception -> Laa
            java.util.Set r3 = r3.entrySet()     // Catch: java.lang.Exception -> Laa
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Exception -> Laa
        L82:
            boolean r5 = r3.hasNext()     // Catch: java.lang.Exception -> Laa
            if (r5 == 0) goto L9e
            java.lang.Object r5 = r3.next()     // Catch: java.lang.Exception -> Laa
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch: java.lang.Exception -> Laa
            java.lang.Object r6 = r5.getValue()     // Catch: java.lang.Exception -> Laa
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Exception -> Laa
            java.lang.Object r5 = r5.getKey()     // Catch: java.lang.Exception -> Laa
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> Laa
            r7.bindData(r2, r8, r6, r5)     // Catch: java.lang.Exception -> Laa
            goto L82
        L9e:
            if (r4 != 0) goto La1
            goto L3d
        La1:
            com.jd.dynamic.base.l0 r3 = new com.jd.dynamic.base.l0     // Catch: java.lang.Exception -> Laa
            r3.<init>()     // Catch: java.lang.Exception -> Laa
            r7.P(r3)     // Catch: java.lang.Exception -> Laa
            goto L3d
        Laa:
            r2 = move-exception
            java.lang.String r3 = com.jd.dynamic.lib.utils.m.j(r7)
            java.lang.String r4 = com.jd.dynamic.lib.utils.m.O(r7)
            java.lang.String r5 = "bind"
            java.lang.String r6 = "DynamicTemplateEngine refreshView2 error"
            com.jd.dynamic.base.DynamicSdk.handException(r5, r6, r3, r4, r2)
            goto L3d
        Lbb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.DynamicTemplateEngine.refreshView(org.json.JSONObject, int, android.view.View):void");
    }

    public void release() {
        releaseTimers(true);
        try {
            FrameLayout frameLayout = this.f1885g;
            if (frameLayout != null) {
                frameLayout.removeAllViews();
            }
        } catch (Exception unused) {
        }
        releaseJS();
        this.f1887i = null;
        CachePool cachePool = this.f1892n;
        if (cachePool != null) {
            cachePool.cleanPool();
        }
        Map<ViewNode, HashMap<String, String>> map = this.f1888j;
        if (map != null) {
            map.clear();
        }
        HashMap<ViewNode, HashMap<String, String>> hashMap = this.f1890l;
        if (hashMap != null) {
            hashMap.clear();
        }
        this.currentData = null;
        this.isRelease = true;
        com.jd.dynamic.a.g gVar = this.q;
        if (gVar != null) {
            gVar.i();
            this.q = null;
        }
        List<Pair<ViewNode, String>> list = this.v;
        if (list != null) {
            list.clear();
        }
        List<Pair<ViewNode, String>> list2 = this.w;
        if (list2 != null) {
            list2.clear();
        }
        List<com.jd.dynamic.a.e> list3 = this.D;
        if (list3 != null) {
            list3.clear();
        }
        Map<String, Integer> map2 = this.E;
        if (map2 != null) {
            map2.clear();
        }
        if (com.jd.dynamic.lib.utils.m.J(this.unBindListenerViews)) {
            this.unBindListenerViews.clear();
        }
        try {
            if (this.isAttached) {
                DynamicSdk.getEngine().releaseCacheEngineWithPrefix(getSystemCode());
            }
        } catch (Exception unused2) {
        }
    }

    public void releaseCachePool() {
        com.jd.dynamic.lib.utils.u uVar = this.u;
        if (uVar != null) {
            uVar.c();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void releaseJS() {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "XPJ  release js : "
            r0.append(r1)
            com.jd.dynamic.a.i r1 = r7.H
            r0.append(r1)
            java.lang.String r1 = " context : "
            r0.append(r1)
            com.jd.dynamic.a.g r1 = r7.q
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.jd.dynamic.DYConstants.DYLog(r0)
            com.jd.dynamic.a.g r0 = r7.q
            if (r0 != 0) goto L25
            return
        L25:
            com.jd.dynamic.a.i r0 = r7.H
            r1 = 0
            if (r0 == 0) goto L34
            r0.p()
            com.jd.dynamic.a.i r0 = r7.H
            r0.q()
            r7.H = r1
        L34:
            com.jd.dynamic.a.i r0 = r7.I
            if (r0 == 0) goto L42
            r0.p()
            com.jd.dynamic.a.i r0 = r7.I
            r0.q()
            r7.I = r1
        L42:
            com.jd.dynamic.b.a.b r0 = com.jd.dynamic.b.a.b.o()
            boolean r0 = r0.n()
            r2 = -1
            if (r0 == 0) goto L8b
            android.app.Activity r0 = r7.getActivity()
            if (r0 == 0) goto L8b
            com.jd.dynamic.apis.IDynamicDriver r0 = com.jd.dynamic.base.DynamicSdk.getDriver()
            android.app.Activity r4 = r7.getActivity()
            com.jd.dynamic.a.g r5 = r7.q
            long r5 = r5.h()
            r0.removeContext(r4, r5)
            com.jd.dynamic.apis.IDynamicDriver r0 = com.jd.dynamic.base.DynamicSdk.getDriver()
            android.app.Activity r4 = r7.getActivity()
            int r0 = r0.getContextCacheSize(r4)
            if (r0 != 0) goto L8b
            com.jd.dynamic.apis.IDynamicDriver r0 = com.jd.dynamic.base.DynamicSdk.getDriver()
            android.app.Activity r4 = r7.getActivity()
            long r4 = r0.removeContextGroup(r4)
            com.jd.dynamic.apis.IDynamicDriver r0 = com.jd.dynamic.base.DynamicSdk.getDriver()
            android.app.Activity r6 = r7.getActivity()
            r0.cleanContextCache(r6)
            goto L8c
        L8b:
            r4 = r2
        L8c:
            com.jd.dynamic.a.g r0 = r7.q
            r0.i()
            r7.q = r1
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 == 0) goto La5
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            java.lang.String r2 = "destroyJSContextGroup"
            r0[r1] = r2
            com.jd.dynamic.lib.utils.t.e(r0)
            com.jd.dynamic.engine.jni.JavaScriptRuntime.destroyJSContextGroup(r4)
        La5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.DynamicTemplateEngine.releaseJS():void");
    }

    public void releaseTimers(boolean z) {
        TimerManager timerManager = this.A;
        if (timerManager != null) {
            if (z) {
                timerManager.onDestroy();
            } else {
                timerManager.cancelAllTimerTask();
            }
            getCachePool().removeData("timerData");
        }
    }

    public void removeItemLock(String str) {
        this.C.remove(str);
    }

    public View returnDynamicView(ViewNode viewNode, String str) {
        ViewNode viewNode2;
        this.N = viewNode;
        ResultEntity resultEntity = this.entity;
        if (resultEntity != null && !TextUtils.isEmpty(resultEntity.jsString) && !com.jd.dynamic.b.a.b.o().d()) {
            initJSEnv();
        }
        ViewNode viewNode3 = null;
        if (viewNode == null) {
            Q("node is null ");
            return null;
        }
        this.y = str;
        long uptimeMillis = SystemClock.uptimeMillis();
        z(str);
        this.o = DynamicMtaUtil.getTempByMtaId(str);
        if ("DynamicData".equals(viewNode.getViewName())) {
            if (!this.useAsyncItem.booleanValue()) {
                this.useAsyncItem = Boolean.valueOf(viewNode.getAttributes().containsKey("useAsyncItem"));
            }
            if (!this.useTagViewOptimize) {
                this.useTagViewOptimize = viewNode.getAttributes().containsKey("useTagViewOptimize");
            }
            viewNode2 = viewNode.getChildByName(DYConstants.DY_FLEXBOX_LAYOUT);
            viewNode3 = viewNode.getChildByName("Events");
        } else {
            viewNode2 = null;
        }
        if (viewNode3 != null) {
            com.jd.dynamic.lib.utils.s.e(this, viewNode3);
        }
        this.f1888j = viewNode.unBindMaps;
        this.f1889k = viewNode.elAttrMapping;
        e0();
        if (viewNode2 != null) {
            viewNode = viewNode2;
        }
        MtaTimePair newInstance = MtaTimePair.newInstance();
        newInstance.startRecord();
        System.nanoTime();
        BaseFrameLayout baseFrameLayout = new BaseFrameLayout(this.f1886h, viewNode, this, true);
        this.z = baseFrameLayout;
        baseFrameLayout.setCommAttrBinded(true);
        newInstance.endRecord();
        DynamicMtaUtil.appendRenderMtaStat(str, newInstance);
        DynamicMtaUtil.endLoadTemplate(TextUtils.isEmpty(this.r) ? "" : this.r, TextUtils.isEmpty(this.bizField) ? "" : this.bizField, str, SystemClock.uptimeMillis() - uptimeMillis);
        return baseFrameLayout;
    }

    public void setActivity(Activity activity) {
        this.f1886h = this.f1886h;
    }

    public void setBizField(String str) {
        this.bizField = str;
    }

    public void setCustomFactory(IFunctionFactory iFunctionFactory) {
        this.f1887i = iFunctionFactory;
    }

    public void setCustomViewMap(Map<String, ICustomView> map) {
        this.s = map;
    }

    public void setDialog(Dialog dialog) {
        this.t = dialog;
        if (dialog != null) {
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jd.dynamic.base.DynamicTemplateEngine.4
                {
                    DynamicTemplateEngine.this = this;
                }

                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    DynamicTemplateEngine.this.t = null;
                }
            });
        }
    }

    public void setSystemCode(String str) {
        this.r = str;
        g0();
    }

    public void setTimerManager(TimerManager timerManager) {
        this.A = timerManager;
    }

    public void setViewIdTable(Map<String, Integer> map) {
        this.E = map;
    }

    public void shouldAutoListenDarkStatus(boolean z) {
        this.p = z;
    }

    public void updateScreenWidth() {
        DynamicXParser.updateWAndH(this.f1886h);
        this.x = DynamicXParser.getWidth();
    }
}
