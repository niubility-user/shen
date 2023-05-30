package com.tencent.mapsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.tencent.map.tools.json.JsonEncoder;
import com.tencent.map.tools.json.JsonParser;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.mapsdk.internal.o1;
import com.tencent.tencentmap.mapsdk.maps.model.CustomLayer;
import com.tencent.tencentmap.mapsdk.maps.model.CustomLayerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.UrlTileProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class sf {

    /* renamed from: f */
    private static final int f17246f = 256;

    /* renamed from: g */
    private static final String f17247g = "custom-layer";

    /* renamed from: h */
    private static final String f17248h = "layer-infos";
    private Context a;
    private og b;
    private SharedPreferences d;

    /* renamed from: c */
    private List<tf> f17249c = new ArrayList();

    /* renamed from: e */
    private Set<b> f17250e = new HashSet();

    /* loaded from: classes9.dex */
    public class a extends UrlTileProvider {
        public final /* synthetic */ tf a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(int i2, int i3, tf tfVar) {
            super(i2, i3);
            sf.this = r1;
            this.a = tfVar;
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.model.UrlTileProvider
        public URL getTileUrl(int i2, int i3, int i4) {
            tf tfVar = this.a;
            if (i4 <= tfVar.f17279c && i4 >= tfVar.d) {
                try {
                    return new URL(this.a.a(i2, i3, i4));
                } catch (MalformedURLException e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public class b implements JsonEncoder, JsonParser {
        private static final String d = "id";

        /* renamed from: e */
        private static final String f17251e = "version";
        private String a;
        private String b;

        private b() {
            sf.this = r1;
        }

        public /* synthetic */ b(sf sfVar, a aVar) {
            this();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof b) {
                b bVar = (b) obj;
                String str = this.a;
                if (str == null ? bVar.a == null : str.equals(bVar.a)) {
                    String str2 = this.b;
                    String str3 = bVar.b;
                    return str2 != null ? str2.equals(str3) : str3 == null;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            String str = this.a;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.b;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }

        @Override // com.tencent.map.tools.json.JsonParser
        public void parse(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optString("id");
                this.b = jSONObject.optString("version");
            }
        }

        @Override // com.tencent.map.tools.json.JsonEncoder
        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", this.a);
                jSONObject.put("version", this.b);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }
    }

    public sf(Context context, og ogVar, o1.b bVar) {
        this.a = context;
        this.b = ogVar;
        this.d = ha.a(context, "custom-layer." + bVar.c());
        a();
    }

    private tf a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (tf tfVar : this.f17249c) {
            if (tfVar != null && str.equals(tfVar.a)) {
                return tfVar;
            }
        }
        return null;
    }

    private void a() {
        SharedPreferences sharedPreferences = this.d;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(f17248h, null);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    try {
                        this.f17250e.add((b) JsonUtils.parseToModel(jSONArray.getJSONObject(i2), b.class, this));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    private TileOverlayOptions b(CustomLayerOptions customLayerOptions) {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        String str = "custom_layer_" + wa.a(customLayerOptions.getLayerId());
        tileOverlayOptions.diskCacheDir(str);
        tf a2 = a(customLayerOptions.getLayerId());
        qa.b(la.a, "cache_dir", (Object) str);
        if (a2 != null) {
            qa.b(la.a, "version", (Object) a2.b);
            qa.b(la.a, "minZoom", Integer.valueOf(a2.d));
            qa.b(la.a, "maxZoom", Integer.valueOf(a2.f17279c));
            qa.b(la.a, XView2Constants.LAYER_ID, (Object) a2.a);
            tileOverlayOptions.tileProvider(new a(256, 256, a2));
            tileOverlayOptions.versionInfo(a2.b);
        }
        return tileOverlayOptions;
    }

    private void b() {
        boolean z;
        a aVar = null;
        boolean z2 = false;
        if (!this.f17250e.isEmpty() || this.f17249c.isEmpty()) {
            boolean z3 = false;
            for (tf tfVar : this.f17249c) {
                Iterator<b> it = this.f17250e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    b next = it.next();
                    if (next.a.equals(tfVar.a)) {
                        if (!next.b.equalsIgnoreCase(tfVar.b)) {
                            tfVar.f17283h = true;
                            next.b = tfVar.b;
                        }
                        z = true;
                    }
                }
                if (!z) {
                    b bVar = new b(this, aVar);
                    bVar.a = tfVar.a;
                    bVar.b = tfVar.b;
                    this.f17250e.add(bVar);
                    z3 = true;
                }
            }
            z2 = z3;
        } else {
            for (tf tfVar2 : this.f17249c) {
                b bVar2 = new b(this, aVar);
                bVar2.a = tfVar2.a;
                bVar2.b = tfVar2.b;
                this.f17250e.add(bVar2);
                z2 = true;
            }
        }
        if (z2) {
            ha.a(this.d).a(f17248h, JsonUtils.collectionToJson(this.f17250e));
        }
    }

    public CustomLayer a(CustomLayerOptions customLayerOptions) {
        if (this.b != null) {
            qa.d(la.a, "\u6dfb\u52a0\u4e2a\u6027\u5316\u56fe\u5c42[" + customLayerOptions.getLayerId() + "]");
            jg b2 = this.b.b(b(customLayerOptions));
            tf a2 = a(customLayerOptions.getLayerId());
            if (b2 != null && a2 != null) {
                if (a2.f17283h) {
                    b2.reload();
                    a2.f17283h = false;
                }
                b2.b(a2.d, a2.f17279c);
            }
            this.b.b().w().q().b();
            qa.j(la.a);
            return new u0(b2);
        }
        return null;
    }

    public void a(rf rfVar) {
        if (rfVar == null || !rfVar.b()) {
            return;
        }
        this.f17249c.clear();
        this.f17249c.addAll(rfVar.a());
        b();
    }
}
