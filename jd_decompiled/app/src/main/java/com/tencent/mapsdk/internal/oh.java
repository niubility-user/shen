package com.tencent.mapsdk.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.map.sdk.comps.vis.VisualLayer;
import com.tencent.map.sdk.comps.vis.VisualLayerOptions;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.Condition;
import com.tencent.map.tools.Util;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.NetResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class oh extends n1 implements y3 {

    /* renamed from: c  reason: collision with root package name */
    private Map<String, jh> f16947c;
    private Map<String, AtomicInteger> d;

    /* renamed from: e  reason: collision with root package name */
    private File f16948e;

    /* renamed from: f  reason: collision with root package name */
    private File f16949f;

    /* renamed from: g  reason: collision with root package name */
    private String f16950g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f16951h;

    /* renamed from: i  reason: collision with root package name */
    private List<d4> f16952i;

    /* renamed from: j  reason: collision with root package name */
    private e4 f16953j;

    /* renamed from: k  reason: collision with root package name */
    private g f16954k;

    /* renamed from: l  reason: collision with root package name */
    private Set<String> f16955l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f16956m;

    /* loaded from: classes9.dex */
    public class a implements Callback<jh> {
        public a() {
        }

        @Override // com.tencent.map.tools.Callback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callback(jh jhVar) {
            if (jhVar != null) {
                jhVar.b(2);
                jhVar.x();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ Callback b;

        public b(String str, Callback callback) {
            this.a = str;
            this.b = callback;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.callback(fa.h(oh.this.l(this.a)));
        }
    }

    /* loaded from: classes9.dex */
    public class c extends Condition<d4> {
        public final /* synthetic */ String a;

        public c(String str) {
            this.a = str;
        }

        @Override // com.tencent.map.tools.Condition
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public boolean condition(d4 d4Var) {
            return d4Var != null && d4Var.c().equals(this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class d extends Condition<jh> {
        public final /* synthetic */ String a;

        public d(String str) {
            this.a = str;
        }

        @Override // com.tencent.map.tools.Condition
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public boolean condition(jh jhVar) {
            return jhVar != null && jhVar.getId().equals(this.a);
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class e {
        public static final /* synthetic */ int[] a;

        static {
            z3.values();
            int[] iArr = new int[7];
            a = iArr;
            try {
                z3 z3Var = z3.Gradient;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                z3 z3Var2 = z3.Aggregation;
                iArr2[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = a;
                z3 z3Var3 = z3.ArcLine;
                iArr3[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = a;
                z3 z3Var4 = z3.GLModel;
                iArr4[4] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public class f extends pb implements ib {

        /* renamed from: c  reason: collision with root package name */
        private final String f16958c;
        private final String d;

        /* renamed from: e  reason: collision with root package name */
        private ByteArrayOutputStream f16959e;

        /* renamed from: f  reason: collision with root package name */
        private Callback<byte[]> f16960f;

        /* renamed from: g  reason: collision with root package name */
        private String f16961g;

        /* renamed from: h  reason: collision with root package name */
        private jb f16962h = new jb();

        public f(String str, String str2) {
            this.f16958c = str;
            this.d = str2;
        }

        public void a(Callback<byte[]> callback) {
            this.f16962h.a(this);
            this.f16962h.a(this.d, this);
            this.f16960f = callback;
        }

        @Override // com.tencent.mapsdk.internal.pb
        public void a(NetRequest.NetRequestBuilder netRequestBuilder) {
            super.a(netRequestBuilder);
            ma.a(la.x, "#onPreConnect gzip {" + this.f16958c + "}");
            netRequestBuilder.gzip();
        }

        @Override // com.tencent.mapsdk.internal.pb
        public void a(NetResponse netResponse) {
            super.a(netResponse);
            this.f16961g = netResponse.contentEncoding;
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void a(String str) {
            ma.a(la.x, "#completed download {" + this.f16958c + "} [" + str + "]");
            this.f16962h.b(this);
            if (this.f16960f != null) {
                byte[] byteArray = this.f16959e.toByteArray();
                byte[] c2 = "gzip".equals(this.f16961g) ? ia.c(byteArray) : byteArray;
                StringBuilder sb = new StringBuilder();
                sb.append("\u6570\u636e\u91cf\u5927\u5c0f {");
                sb.append(this.f16958c);
                sb.append("} [");
                sb.append(byteArray != null ? byteArray.length : 0);
                sb.append(" : ");
                sb.append(c2 != null ? c2.length : 0);
                sb.append("]result:");
                sb.append(new String(c2));
                ma.a(la.x, sb.toString());
                this.f16960f.callback(c2);
            }
            ga.a(this.f16959e);
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void a(String str, kb kbVar) {
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void a(String str, byte[] bArr) {
            if (bArr != null) {
                try {
                    this.f16959e.write(bArr);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void b(String str) {
            ma.a(la.x, "#fail download {" + this.f16958c + "} [" + str + "]");
            ga.a(this.f16959e);
            jh j2 = oh.this.j(this.f16958c);
            if (j2 != null) {
                j2.b(1);
            }
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void c(String str) {
            ma.a(la.x, "#cancel download {" + this.f16958c + "} [" + str + "]");
            ga.a(this.f16959e);
            jh j2 = oh.this.j(this.f16958c);
            if (j2 != null) {
                j2.b(1);
            }
        }

        @Override // com.tencent.mapsdk.internal.ib
        public void d(String str) {
            ma.a(la.x, "#start download {" + this.f16958c + "} [" + str + "]");
            this.f16959e = new ByteArrayOutputStream();
        }
    }

    /* loaded from: classes9.dex */
    public static class g extends Handler {
        public WeakReference<oh> a;

        public g(oh ohVar) {
            super(Looper.myLooper());
            this.a = new WeakReference<>(ohVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            oh ohVar = this.a.get();
            if (ohVar == null || message.what == 0) {
                return;
            }
            String str = (String) message.obj;
            int i2 = message.arg2;
            if (message.arg1 > 0) {
                if (i2 > 0) {
                    ma.a(la.x, "\u5faa\u73af\u5237\u65b0[" + str + "]\u6b21\u6570[" + i2 + "]");
                    ohVar.n(str);
                }
                message.arg2 = i2 + 1;
                sendMessageDelayed(Message.obtain(message), r6.arg1);
            }
        }
    }

    private boolean b(JSONObject jSONObject) {
        ma.a(la.x, "#parseLayerInfoJson:" + jSONObject);
        e4 e4Var = (e4) JsonUtils.parseToModel(jSONObject, e4.class, new Object[0]);
        if (e4Var == null) {
            ma.g(la.x, "\u89e3\u6790LayerInfo\u6570\u636e\u5931\u8d25");
            return false;
        }
        this.f16951h = e4Var.b();
        this.f16952i.clear();
        List<d4> a2 = e4Var.a();
        if (a2 != null && !a2.isEmpty()) {
            this.f16952i.addAll(a2);
        }
        ma.a(la.x, "\u89e3\u6790LayerInfo\u6570\u636e\u6210\u529f");
        this.f16953j = e4Var;
        return true;
    }

    private void g() {
        ma.a(la.x, "#loadLayerJsonFromLocal");
        byte[] h2 = fa.h(this.f16949f);
        if (h2 == null || h2.length <= 0) {
            return;
        }
        try {
            b(new JSONObject(new String(h2)));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void h() {
        JSONObject json;
        ma.a(la.x, "#restoreLayerJsonToLocal");
        e4 e4Var = this.f16953j;
        if (e4Var == null || !this.f16951h || (json = e4Var.toJson()) == null) {
            return;
        }
        h(json.toString());
    }

    private void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        fa.d(this.f16949f);
        fa.b(this.f16949f, str.getBytes());
    }

    private d4 i(String str) {
        return (d4) Util.singleWhere(new ArrayList(this.f16952i), new c(k(str)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public jh j(String str) {
        return (jh) Util.singleWhere(this.f16947c.values(), new d(str));
    }

    private static String k(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.substring(0, str.lastIndexOf(CartConstant.KEY_YB_INFO_LINK) == -1 ? str.length() : str.lastIndexOf(CartConstant.KEY_YB_INFO_LINK));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File l(String str) {
        return new File(new File(this.f16948e, va.a(k(str))), "cache.dat");
    }

    private File m(String str) {
        return new File(this.f16948e, va.a(k(str)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(String str) {
        ma.a(la.x, "#refreshLayerData[" + str + "]");
        jh jhVar = this.f16947c.get(str);
        if (jhVar != null) {
            jhVar.c(this);
        }
    }

    @Override // com.tencent.mapsdk.internal.y3
    public VisualLayer a(VisualLayerOptions visualLayerOptions) {
        if (visualLayerOptions == null) {
            return null;
        }
        String layerId = visualLayerOptions.getLayerId();
        ma.a(la.x, "#createLayer layerId [" + layerId + "]");
        String k2 = k(layerId);
        ma.a(la.x, "#createLayer baseLayerId [" + k2 + "]");
        jh jhVar = this.f16947c.get(layerId);
        if (jhVar != null) {
            ma.a(la.x, "#createLayer layerId [" + layerId + "] already exists");
            jhVar.a(visualLayerOptions);
            return jhVar;
        }
        jh jhVar2 = new jh(visualLayerOptions);
        this.f16947c.put(visualLayerOptions.getLayerId(), jhVar2);
        if (this.d.get(k2) != null) {
            this.d.get(k2).incrementAndGet();
        } else {
            this.d.put(k2, new AtomicInteger(1));
        }
        jhVar2.b(this);
        return jhVar2;
    }

    @Override // com.tencent.mapsdk.internal.n1
    public void a(o1 o1Var) {
        super.a(o1Var);
        this.f16956m = false;
        this.f16954k = new g(this);
        this.f16947c = new ConcurrentHashMap();
        this.d = new ConcurrentHashMap();
        this.f16952i = new CopyOnWriteArrayList();
        this.f16955l = new HashSet();
        this.f16950g = o1Var.q().g();
        if (!TextUtils.isEmpty(o1Var.q().j())) {
            this.f16950g = o1Var.q().j();
        }
        String c2 = o1Var.q().c();
        this.f16948e = new File(o1Var.x().c(), "visual/" + c2);
        this.f16949f = new File(this.f16948e, "layerInfo.json");
        ma.a(la.x, "#datalayer config file [" + this.f16949f + "]");
        g();
        nh.a();
    }

    @Override // com.tencent.mapsdk.internal.y3
    public void a(String str) {
        ma.g(la.x, "\u6dfb\u52a0\u5230\u7b49\u5f85\u961f\u5217[" + str + "]");
        this.f16955l.add(str);
    }

    @Override // com.tencent.mapsdk.internal.y3
    public void a(String str, int i2) {
        ma.a(la.x, "#startTimeInterval[" + str + "], hash = " + str.hashCode() + " timeInterval = " + i2);
        if (i2 <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        this.f16954k.removeMessages(str.hashCode());
        Message.obtain(this.f16954k, str.hashCode(), i2 * 1000, 0, str).sendToTarget();
    }

    @Override // com.tencent.mapsdk.internal.y3
    public void a(String str, int i2, int i3) {
        ma.a(la.x, "#updateLayerVersionInfo[" + str + "], dv=" + i2 + " sv=" + i3);
        d4 i4 = i(str);
        if (i4 != null) {
            i4.b(i3);
            i4.a(i2);
        }
    }

    @Override // com.tencent.mapsdk.internal.y3
    public void a(String str, Callback<byte[]> callback) {
        String str2;
        ma.a(la.x, "#requestNew[" + str + "]");
        d4 i2 = i(str);
        String k2 = k(str);
        boolean z = true;
        if (i2 != null) {
            Map<String, jh> map = this.f16947c;
            if (map == null || map.get(k2) == null || this.f16947c.get(k2).l() <= 0) {
                int i3 = this.d.get(k2).get();
                int i4 = 1;
                while (true) {
                    if (i4 >= i3) {
                        str2 = str;
                        break;
                    }
                    str2 = k2 + CartConstant.KEY_YB_INFO_LINK + i4;
                    Map<String, jh> map2 = this.f16947c;
                    if (map2 != null && map2.get(str2) != null && this.f16947c.get(str2).l() > 0) {
                        break;
                    }
                    i4++;
                }
            } else {
                str2 = k2;
            }
            ma.a(la.x, "\u56fe\u5c42id[" + str + "] \u9009\u4e3e\u4f5c\u4e3a\u5237\u65b0\u6570\u636e\u7684\u56fe\u5c42id[" + str2 + "]");
            if (str.equals(str2)) {
                String a2 = i2.a();
                if (!TextUtils.isEmpty(a2)) {
                    String concat = a2.concat("&key=" + this.f16950g);
                    ma.a(la.x, "\u56fe\u5c42id[" + str + "] \u8bf7\u6c42\u6570\u636e\u7684URL[" + concat + "]");
                    new f(k2, concat).a(callback);
                }
            } else {
                b(str, callback);
                ma.a(la.x, "\u56fe\u5c42id[" + str + "] \u8bfb\u53d6\u672c\u5730\u56fe\u5c42\u7f13\u5b58\u6570\u636e, \u65e0\u9700\u91cd\u590d\u53d1\u9001\u8bf7\u6c42");
            }
            if (!z || callback == null) {
            }
            callback.callback(null);
            return;
        }
        z = false;
        if (z) {
        }
    }

    @Override // com.tencent.mapsdk.internal.y3
    public void a(String str, byte[] bArr) {
        ma.a(la.x, "#saveLayerData[" + str + "]");
        File l2 = l(str);
        File c2 = fa.c(l2);
        fa.b(c2, bArr);
        fa.b(c2, l2);
    }

    @Override // com.tencent.mapsdk.internal.y3
    public void a(JSONObject jSONObject) {
        ma.a(la.x, "#saveLayerInfosToLocal[" + jSONObject + "]");
        this.f16956m = true;
        boolean z = false;
        if (jSONObject != null) {
            z = b(jSONObject);
            if (z) {
                h(jSONObject.toString());
                if (!this.f16955l.isEmpty()) {
                    ma.a(la.x, "\u521d\u59cb\u5316\u7b49\u5f85\u961f\u5217\u56fe\u5c42[" + this.f16955l.size() + "]");
                    Iterator<String> it = this.f16955l.iterator();
                    while (it.hasNext()) {
                        jh jhVar = this.f16947c.get(it.next());
                        if (jhVar != null) {
                            jhVar.b(this);
                        }
                    }
                    this.f16955l.clear();
                }
            }
        } else {
            this.f16951h = false;
            this.f16952i.clear();
            this.f16953j = null;
        }
        if (z && this.f16951h) {
            return;
        }
        Util.foreach(this.f16947c.values(), new a());
    }

    @Override // com.tencent.mapsdk.internal.y3
    public int b(String str) {
        StringBuilder sb;
        if (this.d.get(str) != null) {
            sb = new StringBuilder();
        } else {
            this.d.put(str, new AtomicInteger(1));
            sb = new StringBuilder();
        }
        sb.append("#getRefCount [");
        sb.append(str);
        sb.append("], refCnt=[");
        sb.append(this.d.get(str).get());
        sb.append("]");
        ma.a(la.x, sb.toString());
        return this.d.get(str).get();
    }

    @Override // com.tencent.mapsdk.internal.n1
    public void b(o1 o1Var) {
        super.b(o1Var);
        Map<String, jh> map = this.f16947c;
        if (map != null) {
            for (jh jhVar : map.values()) {
                if (!jhVar.isRemoved()) {
                    jhVar.remove();
                }
            }
            this.f16947c.clear();
        }
        h();
        nh.b();
        ma.a(la.x, "\u9000\u51fa\u6570\u636e\u56fe\u5c42\u6210\u529f");
    }

    @Override // com.tencent.mapsdk.internal.y3
    public void b(String str, Callback<byte[]> callback) {
        ma.a(la.x, "#readLayerDataFromCache[" + str + "]");
        if (callback != null) {
            ba.a(new b(str, callback));
        }
    }

    @Override // com.tencent.mapsdk.internal.y3
    public void c(String str) {
        ma.a(la.x, "#removeLayer[" + str + "]");
        Map<String, jh> map = this.f16947c;
        if (map != null) {
            map.remove(str);
        }
        e(str);
    }

    @Override // com.tencent.mapsdk.internal.y3
    public boolean c() {
        return this.f16956m;
    }

    @Override // com.tencent.mapsdk.internal.y3
    public void d(String str) {
        ma.a(la.x, "#clearCache[" + str + "]");
        if (str == null || TextUtils.isEmpty(str)) {
            return;
        }
        File m2 = m(str);
        ma.a(la.x, "\u6267\u884c\u5220\u9664\u6587\u4ef6[" + m2 + "]");
        fa.e(m2);
        a(str, 0, 0);
    }

    @Override // com.tencent.mapsdk.internal.y3
    public void e(String str) {
        String k2 = k(str);
        ma.a(la.x, "#stopTimeInterval[" + str + "]");
        if (TextUtils.isEmpty(k2)) {
            return;
        }
        this.f16954k.removeMessages(k2.hashCode());
    }

    @Override // com.tencent.mapsdk.internal.y3
    public boolean f(String str) {
        jh j2;
        kh mhVar;
        ma.a(la.x, "#checkLayerStatusById[" + str + "]");
        if (this.f16951h) {
            d4 i2 = i(str);
            if (i2 != null && (j2 = j(str)) != null) {
                int ordinal = i2.d().ordinal();
                if (ordinal == 1) {
                    mhVar = new mh();
                } else if (ordinal == 2) {
                    mhVar = new hh();
                } else if (ordinal == 3) {
                    mhVar = new ih();
                } else if (ordinal == 4) {
                    mhVar = new lh();
                }
                j2.a(mhVar);
            }
            return i2 != null;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.y3
    public String g(String str) {
        return m(k(str)).getAbsolutePath();
    }
}
