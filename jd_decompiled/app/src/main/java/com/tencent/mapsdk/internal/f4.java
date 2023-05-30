package com.tencent.mapsdk.internal;

import com.facebook.react.uimanager.ViewProps;
import com.jingdong.jdreact.plugin.gradient.JDReactLinearGradientManager;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.map.tools.json.annotation.JsonType;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngDeserializer;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngListDeserializer;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class f4 extends JsonComposer {
    @Json(name = "info")
    public b a;

    /* loaded from: classes9.dex */
    public static class a extends JsonComposer {
        @Json(name = "layerType")
        public String a;
        @Json(name = "data")
        public C0792a b;

        /* renamed from: com.tencent.mapsdk.internal.f4$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static class C0792a extends JsonComposer {
            @Json(name = "version")
            public int a;
            @Json(name = "default")
            public List<AbstractC0793a> b;

            @JsonType(deserializer = c4.class)
            /* renamed from: com.tencent.mapsdk.internal.f4$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public static abstract class AbstractC0793a extends JsonComposer {
                @Json(name = "type")
                public String a;
                public String b;
            }

            /* renamed from: com.tencent.mapsdk.internal.f4$a$a$b */
            /* loaded from: classes9.dex */
            public static class b extends AbstractC0793a {
                @Json(deserializer = LatLngListDeserializer.class, name = "coordinates")

                /* renamed from: c  reason: collision with root package name */
                public List<LatLng> f16490c;
                @Json(name = CartConstant.KEY_SKU_WEIGHT)
                public int d;
            }

            /* renamed from: com.tencent.mapsdk.internal.f4$a$a$c */
            /* loaded from: classes9.dex */
            public static class c extends AbstractC0793a {
                @Json(name = "url")

                /* renamed from: c  reason: collision with root package name */
                public String f16491c;
                @Json(name = "astcUrl")
                public String d;
                @Json(name = "format")

                /* renamed from: e  reason: collision with root package name */
                public String f16492e;
                @Json(name = "name")

                /* renamed from: f  reason: collision with root package name */
                public String f16493f;
                @Json(name = "targetName")

                /* renamed from: g  reason: collision with root package name */
                public String f16494g;
                @Json(name = "id")

                /* renamed from: h  reason: collision with root package name */
                public String f16495h;
            }

            /* renamed from: com.tencent.mapsdk.internal.f4$a$a$d */
            /* loaded from: classes9.dex */
            public static class d extends AbstractC0793a {
                @Json(deserializer = LatLngDeserializer.class, name = "coordinates")

                /* renamed from: c  reason: collision with root package name */
                public LatLng f16496c;
                @Json(name = CartConstant.KEY_SKU_WEIGHT)
                public int d;
            }

            /* renamed from: com.tencent.mapsdk.internal.f4$a$a$e */
            /* loaded from: classes9.dex */
            public static class e extends AbstractC0793a {
                @Json(deserializer = i4.class, name = "coordinates")

                /* renamed from: c  reason: collision with root package name */
                public List<WeightedLatLng> f16497c;
            }

            public boolean a() {
                List<AbstractC0793a> list = this.b;
                return list != null && list.size() > 0;
            }
        }

        public boolean a() {
            C0792a c0792a = this.b;
            return c0792a != null && c0792a.a();
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends JsonComposer {
        @Json(name = "error")
        public int a;
        @Json(name = "msg")
        public String b;
    }

    /* loaded from: classes9.dex */
    public static abstract class c extends JsonComposer {
        @Json(name = "version")
        public int a;
        @Json(name = "mapping")
        public b b;

        /* loaded from: classes9.dex */
        public static abstract class a extends JsonComposer {
            @Json(name = "displayLevel")
            public int a;
            @Json(name = "zIndex")
            public int b;
            @Json(name = ViewProps.HIDDEN)

            /* renamed from: c  reason: collision with root package name */
            public boolean f16498c;
            @Json(name = ViewProps.OPACITY)
            public double d;
            @Json(name = "maxZoom")

            /* renamed from: e  reason: collision with root package name */
            public int f16499e;
            @Json(name = "minZoom")

            /* renamed from: f  reason: collision with root package name */
            public int f16500f;
            @Json(name = "themeName")

            /* renamed from: g  reason: collision with root package name */
            public String f16501g;

            public boolean a() {
                return true;
            }
        }

        /* loaded from: classes9.dex */
        public static class b extends JsonComposer {
            @Json(name = "rule")
            public a a;

            /* loaded from: classes9.dex */
            public static class a extends JsonComposer {
                @Json(name = "default")
                public String a;
            }
        }

        /* renamed from: com.tencent.mapsdk.internal.f4$c$c  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static abstract class AbstractC0794c extends JsonComposer {
        }

        /* loaded from: classes9.dex */
        public static class d extends JsonComposer {
            @Json(name = "duration")
            public double a;

            public boolean a() {
                return this.a >= 0.0d;
            }
        }

        /* loaded from: classes9.dex */
        public static class e extends JsonComposer {
            @Json(name = "points")
            public List<Double> a;
            @Json(name = JDReactLinearGradientManager.PROP_COLORS)
            public List<Integer> b;

            public boolean a() {
                List<Integer> list;
                List<Double> list2 = this.a;
                return list2 != null && list2.size() > 0 && (list = this.b) != null && list.size() > 0;
            }
        }

        /* loaded from: classes9.dex */
        public static class f extends JsonComposer {
            @Json(name = "type")
            public int a;

            public boolean a() {
                return true;
            }
        }

        public boolean a() {
            return true;
        }
    }

    public boolean a() {
        b bVar = this.a;
        return bVar != null && bVar.a == 0;
    }

    public abstract int b();

    public abstract int c();
}
