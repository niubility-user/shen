package com.tencent.mapsdk.internal;

import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.mapsdk.internal.f4;
import java.util.List;

/* loaded from: classes9.dex */
public final class a4 extends f4 {
    @Json(name = "detail")
    public a b;

    /* loaded from: classes9.dex */
    public static class a extends f4.a {
        @Json(name = "styleTable")

        /* renamed from: c  reason: collision with root package name */
        public C0780a f16222c;

        /* renamed from: com.tencent.mapsdk.internal.a4$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static class C0780a extends f4.c {
            @Json(name = CustomThemeConstance.TABLE_NAME)

            /* renamed from: c  reason: collision with root package name */
            public b f16223c;
            @Json(name = "control")
            public C0781a d;

            /* renamed from: com.tencent.mapsdk.internal.a4$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes9.dex */
            public static final class C0781a extends f4.c.a {
                @Json(name = "maxIntensity")

                /* renamed from: h  reason: collision with root package name */
                public double f16224h;
                @Json(name = "minIntensity")

                /* renamed from: i  reason: collision with root package name */
                public double f16225i;
                @Json(name = "enable3D")

                /* renamed from: j  reason: collision with root package name */
                public boolean f16226j;
                @Json(name = "heightRange")

                /* renamed from: k  reason: collision with root package name */
                public List<Double> f16227k;
                @Json(name = "animated")

                /* renamed from: l  reason: collision with root package name */
                public boolean f16228l;

                @Override // com.tencent.mapsdk.internal.f4.c.a
                public boolean a() {
                    List<Double> list;
                    return super.a() && (list = this.f16227k) != null && list.size() > 0;
                }
            }

            /* renamed from: com.tencent.mapsdk.internal.a4$a$a$b */
            /* loaded from: classes9.dex */
            public static class b extends JsonComposer {
                @Json(name = ScaleModeConstants.TEXT_SCALE_MODE_STANDARD)
                public C0783b a;

                /* renamed from: com.tencent.mapsdk.internal.a4$a$a$b$a  reason: collision with other inner class name */
                /* loaded from: classes9.dex */
                public static final class C0782a extends f4.c.AbstractC0794c {
                    @Json(name = "gap")
                    public int a;
                    @Json(name = JDPureVideoManager.SourceKey.RADIUS)
                    public int b;
                    @Json(name = "shapeType")

                    /* renamed from: c  reason: collision with root package name */
                    public String f16229c;
                    @Json(name = "gradient")
                    public f4.c.e d;
                    @Json(name = "animation")

                    /* renamed from: e  reason: collision with root package name */
                    public f4.c.d f16230e;
                }

                /* renamed from: com.tencent.mapsdk.internal.a4$a$a$b$b  reason: collision with other inner class name */
                /* loaded from: classes9.dex */
                public static class C0783b extends JsonComposer {
                    @Json(name = "defaultStyle")
                    public C0782a a;
                }
            }

            @Override // com.tencent.mapsdk.internal.f4.c
            public boolean a() {
                return (!super.a() || this.f16223c == null || this.d == null) ? false : true;
            }
        }

        @Override // com.tencent.mapsdk.internal.f4.a
        public boolean a() {
            C0780a c0780a;
            return super.a() && z3.Aggregation.b(this.a) && (c0780a = this.f16222c) != null && c0780a.a();
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
            return this.b.f16222c.a;
        }
        return 0;
    }
}
