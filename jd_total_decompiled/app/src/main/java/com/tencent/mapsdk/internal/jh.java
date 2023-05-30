package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.map.lib.models.CommandFunctionModelClass;
import com.tencent.map.lib.models.EventParamsModelClass;
import com.tencent.map.lib.models.ReturnInfoModelClass;
import com.tencent.map.sdk.comps.vis.VisualLayer;
import com.tencent.map.sdk.comps.vis.VisualLayerOptions;
import com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlayProvider;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.mapsdk.internal.f4;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.IAnimatorModel;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class jh implements VisualLayer {
    private static final int w = 15;

    /* renamed from: g  reason: collision with root package name */
    private y3 f16744g;

    /* renamed from: h  reason: collision with root package name */
    private List<VisualLayer.OnLayerStatusChangedListener> f16745h;

    /* renamed from: i  reason: collision with root package name */
    private int f16746i;

    /* renamed from: j  reason: collision with root package name */
    private int f16747j;

    /* renamed from: k  reason: collision with root package name */
    private int f16748k;

    /* renamed from: l  reason: collision with root package name */
    private float f16749l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f16750m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f16751n;
    private boolean o;
    private final String p;
    private final String q;
    private VectorOverlay r;
    private f4 s;
    private boolean t = true;
    private volatile int u = -1;
    private kh v;

    /* loaded from: classes9.dex */
    public class a implements Callback<byte[]> {
        public final /* synthetic */ y3 a;

        public a(y3 y3Var) {
            this.a = y3Var;
        }

        @Override // com.tencent.map.tools.Callback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callback(byte[] bArr) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u56fe\u5c42id[");
            sb.append(jh.this.p);
            sb.append("] \u8bfb\u53d6\u672c\u5730\u56fe\u5c42\u6570\u636e[");
            sb.append(bArr != null ? bArr.length : 0);
            sb.append("]");
            ma.a(la.x, sb.toString());
            if (bArr != null && bArr.length > 0 && jh.this.a(bArr, false)) {
                jh.this.a(this.a);
            }
            jh.this.c(this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Callback<byte[]> {
        public final /* synthetic */ y3 a;

        public b(y3 y3Var) {
            this.a = y3Var;
        }

        @Override // com.tencent.map.tools.Callback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callback(byte[] bArr) {
            if (bArr != null && bArr.length > 0) {
                ma.a(la.x, "\u56fe\u5c42id[" + jh.this.p + "] \u5237\u65b0\u56fe\u5c42\u6570\u636e[" + bArr.length + "]");
                if (jh.this.a(bArr, true)) {
                    jh.this.a(this.a);
                    this.a.a(jh.this.p, bArr);
                }
            }
            jh.this.d(this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class c implements VectorOverlay.OnVectorOverlayLoadListener {
        public c() {
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay.OnVectorOverlayLoadListener
        public void onVectorOverlayLoaded(boolean z) {
            jh jhVar;
            int i2;
            if (z) {
                jhVar = jh.this;
                i2 = 0;
            } else {
                jhVar = jh.this;
                i2 = 20;
            }
            jhVar.b(i2);
        }
    }

    /* loaded from: classes9.dex */
    public class d implements VectorOverlay.OnVectorOverlayClickListener {
        public d() {
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay.OnVectorOverlayClickListener
        public void onClicked(LatLng latLng, String str, String str2) {
            jh.this.a(VisualLayer.OnLayerStatusChangedListener.EventType.ON_CLICK, JsonUtils.modelToJsonString(new EventParamsModelClass.ClickEventParams(jh.this.p, latLng, str, str2)));
        }
    }

    /* loaded from: classes9.dex */
    public class e implements IAnimatorModel.IAnimatorEndListener {
        public e() {
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.model.IAnimatorModel.IAnimatorEndListener
        public void onAnimatorEnd() {
            jh.this.a(VisualLayer.OnLayerStatusChangedListener.EventType.ON_TRANSLATEANIMATION_COMPLETE, JsonUtils.modelToJsonString(new EventParamsModelClass.TranslateAnimationCompleteEventParams(jh.this.p)));
        }
    }

    /* loaded from: classes9.dex */
    public class f implements Runnable {
        public final /* synthetic */ int a;

        public f(int i2) {
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (jh.this.a(this.a) && jh.this.f16745h != null) {
                ma.a(la.x, "\u56fe\u5c42id[" + jh.this.p + "] notifyStatusChange do success");
                ArrayList arrayList = new ArrayList(jh.this.f16745h);
                jh.this.a(VisualLayer.OnLayerStatusChangedListener.EventType.ON_LAYER_LOAD_FINISH, JsonUtils.modelToJsonString(new EventParamsModelClass.LoadFinishEventParams(jh.this.p, EventParamsModelClass.LoadFinishEventParams.LoadFinishInfo.getById(this.a))));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    VisualLayer.OnLayerStatusChangedListener onLayerStatusChangedListener = (VisualLayer.OnLayerStatusChangedListener) it.next();
                    if (onLayerStatusChangedListener != null) {
                        onLayerStatusChangedListener.onLayerLoadFinish(this.a);
                    }
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public class g implements Runnable {
        public final /* synthetic */ VisualLayer a;
        public final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ String f16752c;

        public g(VisualLayer visualLayer, String str, String str2) {
            this.a = visualLayer;
            this.b = str;
            this.f16752c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (jh.this.f16745h == null) {
                return;
            }
            Iterator it = new ArrayList(jh.this.f16745h).iterator();
            while (it.hasNext()) {
                VisualLayer.OnLayerStatusChangedListener onLayerStatusChangedListener = (VisualLayer.OnLayerStatusChangedListener) it.next();
                if (onLayerStatusChangedListener != null) {
                    onLayerStatusChangedListener.onEvent(this.a, this.b, this.f16752c);
                }
            }
        }
    }

    public jh(VisualLayerOptions visualLayerOptions) {
        String substring = visualLayerOptions.getLayerId().substring(0, visualLayerOptions.getLayerId().lastIndexOf(CartConstant.KEY_YB_INFO_LINK) == -1 ? visualLayerOptions.getLayerId().length() : visualLayerOptions.getLayerId().lastIndexOf(CartConstant.KEY_YB_INFO_LINK));
        this.q = substring;
        int intValue = visualLayerOptions.getLayerId().lastIndexOf(CartConstant.KEY_YB_INFO_LINK) != -1 ? Integer.valueOf(visualLayerOptions.getLayerId().substring(visualLayerOptions.getLayerId().lastIndexOf(CartConstant.KEY_YB_INFO_LINK) + 1)).intValue() : 0;
        if (intValue == 0) {
            this.p = substring;
        } else {
            this.p = substring + CartConstant.KEY_YB_INFO_LINK + intValue;
        }
        a(visualLayerOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(y3 y3Var) {
        StringBuilder sb;
        String str;
        ma.a(la.x, "\u56fe\u5c42id[" + this.p + "] #drawLayer");
        f4 f4Var = this.s;
        if (f4Var == null || !f4Var.a() || y3Var == null) {
            return;
        }
        BaseOverlayProvider a2 = a(this.s);
        if (a2 == null) {
            ma.g(la.x, "\u56fe\u5c42id[" + this.p + "] \u521b\u5efaOverlayProvider\u5931\u8d25");
            b(4);
            return;
        }
        ma.a(la.x, "\u56fe\u5c42id[" + this.p + "] \u521b\u5efaOverlayProvider:" + a2);
        a2.setVectorOverlayLoadedListener((VectorOverlay.OnVectorOverlayLoadListener) new c());
        a2.setVectorOverlayClickListener(new d());
        if (a2 instanceof GLModelOverlayProvider) {
            ((GLModelOverlayProvider) a2).setTransAnimatorEndListener(new e());
        }
        a2.enableClick(this.o);
        VectorOverlay vectorOverlay = this.r;
        TencentMap map = y3Var.getMapContext().j().getMap();
        if (vectorOverlay == null) {
            this.r = map.addVectorOverlay(a2);
            sb = new StringBuilder();
            sb.append("\u56fe\u5c42id[");
            sb.append(this.p);
            str = "] \u521b\u5efaOverlay:";
        } else {
            map.updateVectorOverlay(this.r, a2);
            sb = new StringBuilder();
            sb.append("\u56fe\u5c42id[");
            sb.append(this.p);
            str = "] \u66f4\u65b0Overlay:";
        }
        sb.append(str);
        sb.append(this.r);
        ma.a(la.x, sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i2) {
        if (this.u == i2) {
            return false;
        }
        int i3 = this.u;
        if (i3 == 20 || (i3 == 0 ? i2 > this.u : !(i3 != 1 && i3 != 2 && i3 != 3 && i3 != 4))) {
            i2 = this.u;
        }
        if (this.u == i2) {
            return false;
        }
        this.u = i2;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(y3 y3Var) {
        if (y3Var == null || !this.t) {
            return;
        }
        this.t = false;
        int i2 = this.f16748k;
        if (i2 <= 0) {
            y3Var.e(this.p);
            return;
        }
        if (i2 < 15) {
            this.f16748k = 15;
        }
        y3Var.a(this.p, this.f16748k);
    }

    public BaseOverlayProvider a(f4 f4Var) {
        kh khVar = this.v;
        if (khVar != null) {
            return khVar.a(f4Var);
        }
        return null;
    }

    public f4 a(f4 f4Var, String str) {
        kh khVar = this.v;
        if (khVar != null) {
            return khVar.a(f4Var, str);
        }
        return null;
    }

    public f4 a(byte[] bArr) {
        kh khVar = this.v;
        if (khVar != null) {
            return khVar.a(bArr);
        }
        return null;
    }

    public final void a(VisualLayerOptions visualLayerOptions) {
        if (visualLayerOptions != null) {
            setAlpha(visualLayerOptions.getAlpha());
            setLevel(visualLayerOptions.getLevel());
            setTimeInterval(visualLayerOptions.getTimeInterval());
            setVisible(visualLayerOptions.isVisible());
            setZIndex(visualLayerOptions.getZIndex());
            enableClick(visualLayerOptions.isClickEnabled());
        }
    }

    public void a(kh khVar) {
        this.v = khVar;
    }

    public void a(String str, String str2) {
        ba.a(new g(this, str, str2), 10L);
    }

    public final boolean a(byte[] bArr, boolean z) {
        f4 f4Var;
        f4.b bVar;
        StringBuilder sb = new StringBuilder();
        sb.append("\u56fe\u5c42id[");
        sb.append(this.p);
        sb.append("] #parseLayerData[");
        sb.append(bArr != null ? bArr.length : 0);
        sb.append("]");
        ma.a(la.x, sb.toString());
        f4 a2 = a(bArr);
        this.s = a2;
        if (a2 != null && this.f16744g != null && a2.a()) {
            this.s = a(this.s, this.f16744g.g(this.p));
            this.f16744g.a(getId(), this.s.b(), this.s.c());
            ma.a(la.x, "\u56fe\u5c42id[" + this.p + "] \u521b\u5efaProtocol\u5bf9\u8c61\uff1a\u6210\u529f");
            return true;
        } else if (z && (f4Var = this.s) != null && (bVar = f4Var.a) != null && bVar.a == 0) {
            ma.a(la.x, "\u56fe\u5c42id[" + this.p + "] \u521b\u5efaProtocol\u5bf9\u8c61\uff1a\u7f51\u7edc\u8fd4\u56de\u6570\u636e\u7248\u672c\u65e0\u53d8\u5316, \u65e0\u9700\u66f4\u65b0\u672c\u5730\u6570\u636e");
            return false;
        } else {
            b(3);
            ma.g(la.x, "\u56fe\u5c42id[" + this.p + "] \u521b\u5efaProtocol\u5bf9\u8c61\uff1a\u5931\u8d25");
            return false;
        }
    }

    @Override // com.tencent.map.sdk.comps.vis.VisualLayer
    public void addLayerStatusChangedListener(VisualLayer.OnLayerStatusChangedListener onLayerStatusChangedListener) {
        if (this.f16745h == null) {
            this.f16745h = new ArrayList();
        }
        this.f16745h.remove(onLayerStatusChangedListener);
        this.f16745h.add(onLayerStatusChangedListener);
    }

    public void b(int i2) {
        ma.a(la.x, "\u56fe\u5c42id[" + this.p + "] notifyStatusChange want from[" + this.u + "]to[" + i2 + "]");
        ba.a(new f(i2), 10L);
    }

    public void b(y3 y3Var) {
        if (y3Var == null) {
            return;
        }
        this.f16744g = y3Var;
        if (y3Var.f(this.p)) {
            y3Var.b(this.p, new a(y3Var));
        } else if (y3Var.c()) {
            b(2);
        } else {
            y3Var.a(this.p);
        }
    }

    public final void c(y3 y3Var) {
        if (y3Var == null) {
            return;
        }
        y3Var.a(this.p, new b(y3Var));
    }

    @Override // com.tencent.map.sdk.comps.vis.VisualLayer
    public void clearCache() {
        y3 y3Var;
        if (isRemoved() || TextUtils.isEmpty(this.p) || (y3Var = this.f16744g) == null) {
            return;
        }
        y3Var.d(this.p);
    }

    @Override // com.tencent.map.sdk.comps.vis.VisualLayer
    public VisualLayer copy() {
        y3 y3Var = this.f16744g;
        if (y3Var != null) {
            return this.f16744g.a(new VisualLayerOptions(this.q + CartConstant.KEY_YB_INFO_LINK + y3Var.b(this.q)).newBuilder().setAlpha(this.f16749l).setZIndex(this.f16747j).setTimeInterval(this.f16748k).setClickEnable(this.o).build());
        }
        return null;
    }

    public String d() {
        return this.q;
    }

    @Override // com.tencent.map.sdk.comps.vis.VisualLayer, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void enableClick(boolean z) {
        if (this.o != z) {
            this.o = z;
            VectorOverlay vectorOverlay = this.r;
            if (vectorOverlay != null) {
                vectorOverlay.enableClick(z);
            }
        }
    }

    @Override // com.tencent.map.sdk.comps.vis.VisualLayer
    public String executeCommand(TencentMap tencentMap, String str) {
        ReturnInfoModelClass.ReturnStatus errorReturnInfo;
        CommandFunctionModelClass.BaseCommandFunction a2 = nh.a(str);
        if (a2 == null) {
            errorReturnInfo = new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
        } else {
            String str2 = a2.commandFunction;
            if (TextUtils.isEmpty(str2)) {
                errorReturnInfo = new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.unsupported);
            } else {
                ma.a(la.x, "executeCommand functionType: [" + str2 + "]");
                CommandFunctionModelClass.BaseCommandFunction a3 = nh.a(str, str2);
                VectorOverlay vectorOverlay = this.r;
                if (vectorOverlay != null) {
                    errorReturnInfo = vectorOverlay.executeCommandFunction(a3);
                    ma.a(la.x, "executeCommand returnJson:" + nh.a(errorReturnInfo));
                } else {
                    errorReturnInfo = new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.internal);
                }
            }
        }
        return nh.a(errorReturnInfo);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public ReturnInfoModelClass.ReturnStatus executeCommandFunction(CommandFunctionModelClass.BaseCommandFunction baseCommandFunction) {
        VectorOverlay vectorOverlay = this.r;
        if (vectorOverlay != null) {
            return vectorOverlay.executeCommandFunction(baseCommandFunction);
        }
        return null;
    }

    public <T extends f4> T f() {
        return (T) this.s;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Alphable
    public float getAlpha() {
        return this.f16749l;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IOverlay
    public String getId() {
        return this.p;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public int getLevel() {
        return this.f16746i;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public String getType() {
        VectorOverlay vectorOverlay = this.r;
        return vectorOverlay == null ? "" : vectorOverlay.getType();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public int getZIndex() {
        return this.f16747j;
    }

    @Override // com.tencent.map.sdk.comps.vis.VisualLayer, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public boolean isClickEnabled() {
        VectorOverlay vectorOverlay = this.r;
        if (vectorOverlay != null) {
            return vectorOverlay.isClickEnabled();
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public boolean isRemoved() {
        return this.f16750m;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Visible
    public boolean isVisible() {
        return this.f16751n;
    }

    public int l() {
        return this.f16748k;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public void releaseData() {
        if (isRemoved() || TextUtils.isEmpty(this.p)) {
            return;
        }
        x();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public final void remove() {
        List<VisualLayer.OnLayerStatusChangedListener> list = this.f16745h;
        if (list != null) {
            list.clear();
            this.f16745h = null;
        }
        x();
        y3 y3Var = this.f16744g;
        if (y3Var != null) {
            y3Var.c(this.p);
            this.f16744g = null;
        }
        this.f16750m = true;
    }

    @Override // com.tencent.map.sdk.comps.vis.VisualLayer
    public void removeLayerStatusChangedListener(VisualLayer.OnLayerStatusChangedListener onLayerStatusChangedListener) {
        List<VisualLayer.OnLayerStatusChangedListener> list = this.f16745h;
        if (list != null) {
            list.remove(onLayerStatusChangedListener);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Alphable
    public void setAlpha(float f2) {
        if (this.f16749l != f2) {
            this.f16749l = f2;
            VectorOverlay vectorOverlay = this.r;
            if (vectorOverlay != null) {
                vectorOverlay.setOpacity(f2);
            }
        }
    }

    @Override // com.tencent.map.sdk.comps.vis.VisualLayer, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void setLevel(int i2) {
        if (this.f16746i == i2 || i2 == 0) {
            return;
        }
        this.f16746i = i2;
        VectorOverlay vectorOverlay = this.r;
        if (vectorOverlay != null) {
            vectorOverlay.setLevel(i2);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void setOpacity(float f2) {
        setAlpha(f2);
    }

    @Override // com.tencent.map.sdk.comps.vis.VisualLayer
    public void setTimeInterval(int i2) {
        if (this.f16748k != i2) {
            this.t = true;
            this.f16748k = i2;
            if (i2 > 0 && i2 < 15) {
                this.f16748k = 15;
            }
            d(this.f16744g);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void setVisibility(boolean z) {
        setVisible(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Visible
    public void setVisible(boolean z) {
        if (this.f16751n != z) {
            this.f16751n = z;
            VectorOverlay vectorOverlay = this.r;
            if (vectorOverlay != null) {
                vectorOverlay.setVisibility(z);
            }
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public void setZIndex(float f2) {
        setZIndex((int) f2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public void setZIndex(int i2) {
        if (this.f16747j != i2) {
            this.f16747j = i2;
            VectorOverlay vectorOverlay = this.r;
            if (vectorOverlay != null) {
                vectorOverlay.setZIndex(i2);
            }
        }
    }

    public void x() {
        VectorOverlay vectorOverlay = this.r;
        if (vectorOverlay != null) {
            vectorOverlay.remove();
            this.r = null;
        }
    }
}
