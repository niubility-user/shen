package com.tencent.mapsdk.internal;

import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.mapsdk.internal.f4;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngDeserializer;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngListDeserializer;
import java.util.List;

/* loaded from: classes9.dex */
public class g4 extends f4 {
    @Json(name = "detail")
    public a b;

    /* loaded from: classes9.dex */
    public static class a extends f4.a {
        @Json(name = "styleTable")

        /* renamed from: c  reason: collision with root package name */
        public C0795a f16589c;

        /* renamed from: com.tencent.mapsdk.internal.g4$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static class C0795a extends f4.c {
            @Json(name = CustomThemeConstance.TABLE_NAME)

            /* renamed from: c  reason: collision with root package name */
            public b f16590c;
            @Json(name = "control")
            public C0796a d;

            /* renamed from: com.tencent.mapsdk.internal.g4$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes9.dex */
            public static final class C0796a extends f4.c.a {
            }

            /* renamed from: com.tencent.mapsdk.internal.g4$a$a$b */
            /* loaded from: classes9.dex */
            public static class b extends JsonComposer {
                @Json(name = ScaleModeConstants.TEXT_SCALE_MODE_STANDARD)
                public C0798b a;

                /* renamed from: com.tencent.mapsdk.internal.g4$a$a$b$a  reason: collision with other inner class name */
                /* loaded from: classes9.dex */
                public static final class C0797a extends f4.c.AbstractC0794c {
                    @Json(deserializer = LatLngDeserializer.class, name = "position")
                    public LatLng a;
                    @Json(name = "rotate")
                    public List<Double> b;
                    @Json(name = "scale")

                    /* renamed from: c  reason: collision with root package name */
                    public double f16591c;
                    @Json(name = "autoScale")
                    public boolean d;
                    @Json(name = "pixelBound")

                    /* renamed from: e  reason: collision with root package name */
                    public List<Integer> f16592e;
                    @Json(deserializer = LatLngListDeserializer.class, name = "pedestal")

                    /* renamed from: f  reason: collision with root package name */
                    public List<LatLng> f16593f;
                    @Json(name = "animation")

                    /* renamed from: g  reason: collision with root package name */
                    public f4.c.f f16594g;
                    @Json(name = "exposure")

                    /* renamed from: h  reason: collision with root package name */
                    public double f16595h;
                }

                /* renamed from: com.tencent.mapsdk.internal.g4$a$a$b$b  reason: collision with other inner class name */
                /* loaded from: classes9.dex */
                public static class C0798b extends JsonComposer {
                    @Json(name = "defaultStyle")
                    public C0797a a;
                }
            }

            @Override // com.tencent.mapsdk.internal.f4.c
            public boolean a() {
                return (!super.a() || this.f16590c == null || this.d == null) ? false : true;
            }
        }

        @Override // com.tencent.mapsdk.internal.f4.a
        public boolean a() {
            C0795a c0795a;
            return super.a() && z3.GLModel.b(this.a) && (c0795a = this.f16589c) != null && c0795a.a();
        }
    }

    @Override // com.tencent.mapsdk.internal.f4
    public boolean a() {
        a aVar;
        return super.a() && (aVar = this.b) != null && aVar.a();
    }

    @Override // com.tencent.mapsdk.internal.f4
    public int b() {
        if (a()) {
            return this.b.b.a;
        }
        return 0;
    }

    @Override // com.tencent.mapsdk.internal.f4
    public int c() {
        if (a()) {
            return this.b.f16589c.a;
        }
        return 0;
    }
}
