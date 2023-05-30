package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.jingdong.common.database.table.NavigationBarTable;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.unification.uniconfig.UnNewIconTable;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.JsonParser;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngDeserializer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;

/* renamed from: com.tencent.mapsdk.internal.if  reason: invalid class name */
/* loaded from: classes9.dex */
public final class Cif extends JsonComposer {
    @Json(name = "detail")
    public d a;

    /* renamed from: com.tencent.mapsdk.internal.if$a */
    /* loaded from: classes9.dex */
    public static final class a extends JsonComposer {
        @Json(name = "aoi_latitude")
        public String a;
        @Json(name = "aoi_longitude")
        public String b;
        @Json(name = "area")

        /* renamed from: c  reason: collision with root package name */
        public b f16684c;

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("PoiArea{");
            stringBuffer.append("latitude=");
            stringBuffer.append(this.a);
            stringBuffer.append(", longitude=");
            stringBuffer.append(this.b);
            stringBuffer.append(", area=");
            stringBuffer.append(this.f16684c);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    /* renamed from: com.tencent.mapsdk.internal.if$b */
    /* loaded from: classes9.dex */
    public static final class b extends JsonComposer implements JsonParser.Deserializer<List<List<LatLng>>> {
        @Json(name = "type")
        public String a;
        @Json(deserializer = b.class, name = "coordinates")
        public List<List<LatLng>> b;

        @Override // com.tencent.map.tools.json.JsonParser.Deserializer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public List<List<LatLng>> deserialize(Object obj, String str, Object obj2) {
            ArrayList arrayList = null;
            if (obj2 == null) {
                return null;
            }
            if (obj2 instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj2;
                arrayList = new ArrayList();
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    Object obj3 = jSONArray.get(i2);
                    ArrayList arrayList2 = new ArrayList();
                    if (obj3 instanceof JSONArray) {
                        JSONArray jSONArray2 = (JSONArray) obj3;
                        int length2 = jSONArray2.length();
                        for (int i3 = 0; i3 < length2; i3++) {
                            Object obj4 = jSONArray2.get(i3);
                            if (obj4 instanceof JSONArray) {
                                JSONArray jSONArray3 = (JSONArray) obj4;
                                if (jSONArray3.length() == 2) {
                                    arrayList2.add(new LatLng(jSONArray3.optDouble(1), jSONArray3.optDouble(0)));
                                }
                            }
                        }
                        if (arrayList2.size() != length2) {
                            ma.b("coordinates's data deserialize error!!");
                        }
                    }
                    arrayList.add(arrayList2);
                }
                if (arrayList.size() != length) {
                    ma.b("coordinates's area deserialize error!!");
                }
            }
            return arrayList;
        }

        public String toString() {
            int[] iArr;
            List<List<LatLng>> list = this.b;
            int i2 = 0;
            if (list != null) {
                int size = list.size();
                iArr = new int[size];
                while (i2 < size) {
                    iArr[i2] = this.b.get(i2).size();
                    i2++;
                }
                i2 = size;
            } else {
                iArr = null;
            }
            StringBuffer stringBuffer = new StringBuffer("AreaData{");
            stringBuffer.append("type='");
            stringBuffer.append(this.a);
            stringBuffer.append('\'');
            stringBuffer.append(", coordinates=");
            stringBuffer.append(i2);
            stringBuffer.append("#");
            stringBuffer.append(Arrays.toString(iArr));
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    /* renamed from: com.tencent.mapsdk.internal.if$c */
    /* loaded from: classes9.dex */
    public static class c extends JsonComposer {
        @Json(name = "fill_color")
        public String a;
        @Json(name = "stroke_color")
        public String b;
        @Json(name = "stroke_width")

        /* renamed from: c  reason: collision with root package name */
        public int f16685c;

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("AoiStyle{");
            stringBuffer.append("fillColor='");
            stringBuffer.append(this.a);
            stringBuffer.append('\'');
            stringBuffer.append(", strokeColor='");
            stringBuffer.append(this.b);
            stringBuffer.append('\'');
            stringBuffer.append(", strokeWidth=");
            stringBuffer.append(this.f16685c);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    /* renamed from: com.tencent.mapsdk.internal.if$d */
    /* loaded from: classes9.dex */
    public static class d extends JsonComposer {
        @Json(ignore = true)
        public int a = -1;
        @Json(name = "uid")
        public String b;
        @Json(name = "name")

        /* renamed from: c  reason: collision with root package name */
        public String f16686c;
        @Json(name = PushConstants.SUB_ALIAS_STATUS_NAME)
        public String d;
        @Json(name = "type")

        /* renamed from: e  reason: collision with root package name */
        public String f16687e;
        @Json(name = "styles")

        /* renamed from: f  reason: collision with root package name */
        public List<e> f16688f;
        @Json(name = "shinei_id")

        /* renamed from: g  reason: collision with root package name */
        public String f16689g;
        @Json(deserializer = LatLngDeserializer.class, name = ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID)

        /* renamed from: h  reason: collision with root package name */
        public LatLng f16690h;
        @Json(name = "aoi_info")

        /* renamed from: i  reason: collision with root package name */
        public a f16691i;
        @Json(name = "sub_pois")

        /* renamed from: j  reason: collision with root package name */
        public List<d> f16692j;

        public String a() {
            return !TextUtils.isEmpty(this.d) ? this.d : this.f16686c;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("PoiDetail{");
            stringBuffer.append("displayId=");
            stringBuffer.append(this.a);
            stringBuffer.append(", poiId='");
            stringBuffer.append(this.b);
            stringBuffer.append('\'');
            stringBuffer.append(", name='");
            stringBuffer.append(this.f16686c);
            stringBuffer.append('\'');
            stringBuffer.append(", alias='");
            stringBuffer.append(this.d);
            stringBuffer.append('\'');
            stringBuffer.append(", type='");
            stringBuffer.append(this.f16687e);
            stringBuffer.append('\'');
            stringBuffer.append(", poiStyles=");
            stringBuffer.append(this.f16688f);
            stringBuffer.append(", indoorId='");
            stringBuffer.append(this.f16689g);
            stringBuffer.append('\'');
            stringBuffer.append(", point=");
            stringBuffer.append(this.f16690h);
            stringBuffer.append(", poiArea=");
            stringBuffer.append(this.f16691i);
            stringBuffer.append(", subPois=");
            stringBuffer.append(this.f16692j);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    /* renamed from: com.tencent.mapsdk.internal.if$e */
    /* loaded from: classes9.dex */
    public static class e extends JsonComposer {
        @Json(ignore = true)
        public BitmapDescriptor a;
        @Json(ignore = true)
        public BitmapDescriptor b;
        @Json(name = "style_class")

        /* renamed from: c  reason: collision with root package name */
        public int f16693c;
        @Json(name = "icon_url")
        public String d;
        @Json(name = NavigationBarTable.FIELD_ICON_TYPE)

        /* renamed from: e  reason: collision with root package name */
        public int f16694e;
        @Json(name = "font_color")

        /* renamed from: f  reason: collision with root package name */
        public String f16695f;
        @Json(name = UnNewIconTable.FIELD_TEXT_FONT_SIZE)

        /* renamed from: g  reason: collision with root package name */
        public int f16696g;
        @Json(name = "font_stroke_color")

        /* renamed from: h  reason: collision with root package name */
        public String f16697h;
        @Json(name = "font_stroke_width")

        /* renamed from: i  reason: collision with root package name */
        public int f16698i;
        @Json(name = "level")

        /* renamed from: j  reason: collision with root package name */
        public int f16699j;
        @Json(name = "zindex")

        /* renamed from: k  reason: collision with root package name */
        public int f16700k;
        @Json(name = "aoi")

        /* renamed from: l  reason: collision with root package name */
        public c f16701l;

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("PoiStyle{");
            stringBuffer.append("icon=");
            stringBuffer.append(this.a);
            stringBuffer.append(", type=");
            stringBuffer.append(this.f16693c);
            stringBuffer.append(", iconUrl='");
            stringBuffer.append(this.d);
            stringBuffer.append('\'');
            stringBuffer.append(", iconDisplayType=");
            stringBuffer.append(this.f16694e);
            stringBuffer.append(", fontColor='");
            stringBuffer.append(this.f16695f);
            stringBuffer.append('\'');
            stringBuffer.append(", fontSize=");
            stringBuffer.append(this.f16696g);
            stringBuffer.append(", fontStrokeColor='");
            stringBuffer.append(this.f16697h);
            stringBuffer.append('\'');
            stringBuffer.append(", fontStrokeWidth=");
            stringBuffer.append(this.f16698i);
            stringBuffer.append(", level=");
            stringBuffer.append(this.f16699j);
            stringBuffer.append(", zindex=");
            stringBuffer.append(this.f16700k);
            stringBuffer.append(", aoiStyle=");
            stringBuffer.append(this.f16701l);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AoiInfo{");
        stringBuffer.append("poiDetail=");
        stringBuffer.append(this.a);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
