package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Bundle;
import com.tencent.map.tools.net.NetConfig;
import com.tencent.map.tools.net.NetManager;
import com.tencent.mapsdk.core.components.protocol.jce.conf.CSFileUpdateReq;
import com.tencent.mapsdk.core.components.protocol.jce.conf.FileUpdateReq;
import com.tencent.mapsdk.core.components.protocol.jce.conf.FileUpdateRsp;
import com.tencent.mapsdk.core.components.protocol.jce.conf.SCFileUpdateRsp;
import com.tencent.mapsdk.core.components.protocol.jce.rtt.RttRequest;
import com.tencent.mapsdk.core.components.protocol.jce.rtt.RttResponse;
import com.tencent.mapsdk.core.components.protocol.jce.sso.CmdResult;
import com.tencent.mapsdk.core.components.protocol.jce.sso.Header;
import com.tencent.mapsdk.core.components.protocol.jce.sso.Package;
import com.tencent.mapsdk.core.components.protocol.jce.sso.Tag;
import com.tencent.mapsdk.core.components.protocol.jce.trafficevent.Basic;
import com.tencent.mapsdk.core.components.protocol.jce.trafficevent.Detail;
import com.tencent.mapsdk.core.components.protocol.jce.trafficevent.Response;
import com.tencent.mapsdk.core.components.protocol.jce.user.user_login_t;
import com.tencent.mapsdk.internal.i2;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.TencentMapProtocol;
import com.tencent.tencentmap.mapsdk.maps.TencentMapServiceProtocol;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class l2 extends n1 implements h2, TencentMapProtocol {

    /* renamed from: f */
    private static final String f16783f = "cuid";

    /* renamed from: g */
    private static final String f16784g = "duid";

    /* renamed from: h */
    private static final String f16785h = "sessionid";

    /* renamed from: i */
    private static final Stack<d2> f16786i = new Stack<>();

    /* renamed from: j */
    private static final d2 f16787j = new e2();

    /* renamed from: c */
    private d2 f16788c;
    private final Map<String, String> d = new HashMap();

    /* renamed from: e */
    private TencentMapOptions f16789e;

    /* loaded from: classes9.dex */
    public static class a implements TencentMapServiceProtocol.IMapService {
        public i2.a a;

        public a(i2.a aVar) {
            this.a = aVar;
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapServiceProtocol.IMapService
        public void setAllow(boolean z) {
            this.a.setAllow(z);
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapServiceProtocol.IMapService
        public void setUseHttps(boolean z) {
            this.a.setUseHttps(z);
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapServiceProtocol.IMapService
        public void setUseTest(boolean z) {
            this.a.setUseTest(z);
        }
    }

    public l2() {
    }

    public l2(TencentMapOptions tencentMapOptions, d2 d2Var) {
        this.f16789e = tencentMapOptions;
        this.f16788c = d2Var;
    }

    private d2 a(o1 o1Var, String str, int i2) {
        return a(o1Var, str, o1Var.getContext().getResources().openRawResource(i2));
    }

    private d2 a(o1 o1Var, String str, InputStream inputStream) {
        if (inputStream != null) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                try {
                    byte[] bArr = new byte[1024];
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = inputStream.read(bArr);
                            if (-1 == read) {
                                d2 b = b(o1Var, str, new String(byteArrayOutputStream2.toByteArray(), Charset.forName("UTF-8")));
                                ga.a(byteArrayOutputStream2);
                                ga.a((Closeable) inputStream);
                                return b;
                            }
                            byteArrayOutputStream2.write(bArr, 0, read);
                        } catch (IOException e2) {
                            e = e2;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            e.printStackTrace();
                            ga.a(byteArrayOutputStream);
                            ga.a((Closeable) inputStream);
                            return new e2();
                        } catch (Throwable th) {
                            th = th;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            ga.a(byteArrayOutputStream);
                            ga.a((Closeable) inputStream);
                            throw th;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return new e2();
    }

    public static <S extends j2> S a(Class<S> cls) {
        Stack<d2> stack = f16786i;
        return (S) (stack.isEmpty() ? f16787j : stack.peek()).d(cls);
    }

    private void a(Context context, TencentMapOptions tencentMapOptions) {
        NetManager.init(context, NetConfig.create().setAdapterType(tencentMapOptions.getNetAdapterType()).setLogEnable(li.d).setForceHttps(tencentMapOptions.isForceHttps()).setArguments(tencentMapOptions.getNetParams()).setNetFlowRuleList(i()).setProcessor(g6.class).setProxyRuleList(g().a()));
    }

    private void a(o1 o1Var, TencentMapOptions tencentMapOptions) {
        Object protocolDataDesc = tencentMapOptions.getProtocolDataDesc();
        String g2 = o1Var.q().g();
        if (this.f16788c == null) {
            this.f16788c = f16787j;
        }
        if (protocolDataDesc != null) {
            d2 d2Var = null;
            int protocolFrom = tencentMapOptions.getProtocolFrom();
            if (protocolFrom == -1) {
                d2Var = a(o1Var, g2, (String) protocolDataDesc);
            } else if (protocolFrom == 1) {
                d2Var = a(o1Var, g2, ((Integer) protocolDataDesc).intValue());
            } else if (protocolFrom == 3) {
                d2Var = b(o1Var, g2, (String) protocolDataDesc);
            }
            if (d2Var != null) {
                this.f16788c = d2Var;
            }
        }
        d2 d2Var2 = this.f16788c;
        if (d2Var2 != null) {
            d2Var2.d(g2);
            this.f16788c.a(this);
        } else {
            this.f16788c = f16787j;
        }
        f16786i.push(this.f16788c);
    }

    public static n2 g() {
        Stack<d2> stack = f16786i;
        return stack.isEmpty() ? new o2() : stack.peek().a();
    }

    private HashMap<String, String> i() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("apikey.map.qq.com/mkey/index.php/mkey/check", "map_nf_auth");
        hashMap.put("vectorsdk.map.qq.com/mvd_map", "map_nf_mvd");
        hashMap.put("vectorsdk.map.qq.com/fileupdate", "map_nf_mapcfg");
        hashMap.put("p0.map.gtimg.com/fileupdate", "map_nf_mapcfg");
        hashMap.put("vectorsdk.map.qq.com/indoormap2", "map_nf_indoor");
        hashMap.put("vectorsdk.map.qq.com/indoormap2/index", "map_nf_indoor");
        hashMap.put("vectorsdk.map.qq.com/indoormapx", "map_nf_indoor");
        hashMap.put("vectorsdk.map.qq.com/indoormapx/index", "map_nf_indoor");
        hashMap.put("tafrtt.map.qq.com/rttserverex/", "map_nf_trfc");
        hashMap.put("p0.map.gtimg.com/sateTiles/", "map_nf_sat");
        hashMap.put("p1.map.gtimg.com/sateTiles/", "map_nf_sat");
        hashMap.put("p2.map.gtimg.com/sateTiles/", "map_nf_sat");
        hashMap.put("p3.map.gtimg.com/sateTiles/", "map_nf_sat");
        hashMap.put("apikey.map.qq.com/sdkapis/v1/cos_token", "map_nf_fileup");
        hashMap.put("overseactrl.map.qq.com", "map_nf_wdVer");
        hashMap.put("s0.map.gtimg.com/oversea", "map_nf_wdTile");
        hashMap.put("s1.map.gtimg.com/oversea", "map_nf_wdTile");
        hashMap.put("s2.map.gtimg.com/oversea", "map_nf_wdTile");
        hashMap.put("s3.map.gtimg.com/oversea", "map_nf_wdTile");
        hashMap.put("confinfo.map.qq.com/confinfo", "map_nf_hdVer");
        hashMap.put("p0.map.gtimg.com/scenic/", "map_nf_hdTile");
        hashMap.put("p1.map.gtimg.com/scenic/", "map_nf_hdTile");
        hashMap.put("p2.map.gtimg.com/scenic/", "map_nf_hdTile");
        hashMap.put("p3.map.gtimg.com/scenic/", "map_nf_hdTile");
        hashMap.put("sdkgw.map.qq.com/map/traffic/event", "map_nf_pnt");
        hashMap.put("sdkgw.map.qq.com/map/poi/detail", "map_nf_aoi");
        hashMap.put("mapapi.qq.com/sdk/", "map_nf_res");
        hashMap.put("wecar.myapp.com/myapp/mapwecar", "map_nf_res");
        hashMap.put("map.myapp.com/soso_map/", "map_nf_off");
        hashMap.put("pr.map.qq.com/ditusdk/monitor", "map_nf_pr");
        hashMap.put("pr.map.qq.com/pingd", "map_nf_pr");
        hashMap.put("s0.map.gtimg.com/customlayer/tile", "map_nf_cusLyr");
        hashMap.put("s1.map.gtimg.com/customlayer/tile", "map_nf_cusLyr");
        hashMap.put("s2.map.gtimg.com/customlayer/tile", "map_nf_cusLyr");
        hashMap.put("s3.map.gtimg.com/customlayer/tile", "map_nf_cusLyr");
        hashMap.put("datalayer.map.qq.com/console/datalayer/data", "map_nf_visLyr");
        return hashMap;
    }

    public d2 a(o1 o1Var, String str, String str2) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            inputStream = o1Var.getContext().getResources().getAssets().open(str2);
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
        } catch (Throwable th) {
            th = th;
            ga.a((Closeable) inputStream2);
            throw th;
        }
        try {
            try {
                d2 a2 = a(o1Var, str, inputStream);
                ga.a((Closeable) inputStream);
                return a2;
            } catch (Throwable th2) {
                th = th2;
                inputStream2 = inputStream;
                ga.a((Closeable) inputStream2);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            ga.a((Closeable) inputStream);
            return null;
        }
    }

    @Override // com.tencent.mapsdk.internal.h2
    public Map<String, Class<? extends i2.a>> a() {
        HashMap hashMap = new HashMap();
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_AUTHORIZATION, f3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_INDOOR_DATA, h3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_MAP_DATA, j3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_MAP_STYLE, l3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_RTT_DATA, o3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_SATELLITE_DATA, p3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_SKETCH_DATA, q3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_OVERSEA_DATA, n3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_STATISTIC, r3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_TRAFFIC_EVENT, s3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_OFFLINE_MAP_DATA, m3.class);
        hashMap.put(TencentMapServiceProtocol.SERVICE_NAME_MAP_POI_DATA, k3.class);
        return hashMap;
    }

    @Override // com.tencent.mapsdk.internal.n1
    public void a(o1 o1Var) {
        super.a(o1Var);
        if (o1Var == null) {
            return;
        }
        TencentMapOptions r = o1Var.r();
        this.f16789e = r;
        a(o1Var, r);
        a(e(), this.f16789e);
        b7.a(g().b());
    }

    @Override // com.tencent.mapsdk.internal.n1
    public void a(o1 o1Var, Bundle bundle) {
        super.a(o1Var, bundle);
        b7.a(g().b());
    }

    public void a(String str, String str2) {
        if (e7.b(str2)) {
            return;
        }
        this.d.put(str, str2);
    }

    public d2 b(o1 o1Var, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String optString = jSONObject.optString(f2.a, "-1");
            String optString2 = jSONObject.optString(f2.b, "_unknown");
            if (!"-1".equals(optString) && optString2.equals(str)) {
                k2 k2Var = new k2();
                k2Var.d(str);
                k2Var.a(this);
                JSONArray optJSONArray = jSONObject.optJSONArray(i2.d);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        k2Var.b(optJSONArray.getJSONObject(i2));
                    }
                }
                JSONObject optJSONObject = jSONObject.optJSONObject(g2.f16576c);
                if (optJSONObject != null) {
                    k2Var.a(optJSONObject);
                }
                return k2Var;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return f16787j;
    }

    @Override // com.tencent.mapsdk.internal.h2
    public List<Class<? extends p>> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Basic.class);
        arrayList.add(Detail.class);
        arrayList.add(Response.class);
        arrayList.add(RttRequest.class);
        arrayList.add(RttResponse.class);
        arrayList.add(user_login_t.class);
        arrayList.add(CmdResult.class);
        arrayList.add(Header.class);
        arrayList.add(Package.class);
        arrayList.add(Tag.class);
        arrayList.add(CSFileUpdateReq.class);
        arrayList.add(FileUpdateReq.class);
        arrayList.add(FileUpdateRsp.class);
        arrayList.add(SCFileUpdateRsp.class);
        return arrayList;
    }

    @Override // com.tencent.mapsdk.internal.n1
    public void b(o1 o1Var) {
        d2 d2Var;
        super.b(o1Var);
        Stack<d2> stack = f16786i;
        if (stack.isEmpty() || (d2Var = this.f16788c) == null) {
            return;
        }
        stack.remove(d2Var);
        this.f16788c = null;
    }

    @Override // com.tencent.mapsdk.internal.h2
    public Map<String, String> d() {
        TencentMapOptions tencentMapOptions = this.f16789e;
        if (tencentMapOptions != null) {
            a(f16783f, tencentMapOptions.getCustomUserId());
        }
        a(f16784g, b7.z());
        a(f16785h, f7.b());
        return this.d;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapServiceProtocol
    public TencentMapServiceProtocol.IMapService getMapService(String str) {
        i2.a a2;
        d2 h2 = h();
        this.f16788c = h2;
        if (h2 == null || (a2 = h2.a(str)) == null) {
            return null;
        }
        return new a(a2);
    }

    public d2 h() {
        if (this.f16788c == null) {
            Stack<d2> stack = f16786i;
            this.f16788c = !stack.isEmpty() ? stack.peek() : f16787j;
        }
        return this.f16788c;
    }
}
