package com.tencent.mapsdk.internal;

import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.mapsdk.internal.f4;
import java.util.List;

/* loaded from: classes9.dex */
public final class h4 extends f4 {
    @Json(name = "detail")
    public a b;

    /* loaded from: classes9.dex */
    public static class a extends f4.a {
        @Json(name = "styleTable")

        /* renamed from: c  reason: collision with root package name */
        public C0799a f16649c;

        /* renamed from: com.tencent.mapsdk.internal.h4$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static class C0799a extends f4.c {
            @Json(name = CustomThemeConstance.TABLE_NAME)

            /* renamed from: c  reason: collision with root package name */
            public b f16650c;
            @Json(name = "control")
            public C0800a d;

            /* renamed from: com.tencent.mapsdk.internal.h4$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes9.dex */
            public static final class C0800a extends f4.c.a {
                @Json(name = "maxIntensity")

                /* renamed from: h  reason: collision with root package name */
                public double f16651h;
                @Json(name = "minIntensity")

                /* renamed from: i  reason: collision with root package name */
                public double f16652i;
                @Json(name = "enable3D")

                /* renamed from: j  reason: collision with root package name */
                public boolean f16653j;
                @Json(name = "heightRange")

                /* renamed from: k  reason: collision with root package name */
                public List<Double> f16654k;
                @Json(name = "animated")

                /* renamed from: l  reason: collision with root package name */
                public boolean f16655l;

                @Override // com.tencent.mapsdk.internal.f4.c.a
                public boolean a() {
                    List<Double> list;
                    return super.a() && (list = this.f16654k) != null && list.size() > 0;
                }
            }

            /* renamed from: com.tencent.mapsdk.internal.h4$a$a$b */
            /* loaded from: classes9.dex */
            public static class b extends JsonComposer {
                @Json(name = ScaleModeConstants.TEXT_SCALE_MODE_STANDARD)
                public C0802b a;

                /* renamed from: com.tencent.mapsdk.internal.h4$a$a$b$a  reason: collision with other inner class name */
                /* loaded from: classes9.dex */
                public static final class C0801a extends f4.c.AbstractC0794c {
                    @Json(name = JDPureVideoManager.SourceKey.RADIUS)
                    public int a;
                    @Json(name = "gradient")
                    public f4.c.e b;
                    @Json(name = "animation")

                    /* renamed from: c  reason: collision with root package name */
                    public f4.c.d f16656c;

                    public boolean a() {
                        f4.c.e eVar;
                        f4.c.d dVar;
                        return this.a > 0 && (eVar = this.b) != null && eVar.a() && (dVar = this.f16656c) != null && dVar.a();
                    }
                }

                /* renamed from: com.tencent.mapsdk.internal.h4$a$a$b$b  reason: collision with other inner class name */
                /* loaded from: classes9.dex */
                public static class C0802b extends JsonComposer {
                    @Json(name = "defaultStyle")
                    public C0801a a;

                    public boolean a() {
                        C0801a c0801a = this.a;
                        return c0801a != null && c0801a.a();
                    }
                }

                public boolean a() {
                    C0802b c0802b = this.a;
                    return c0802b != null && c0802b.a();
                }
            }

            @Override // com.tencent.mapsdk.internal.f4.c
            public boolean a() {
                b bVar;
                C0800a c0800a;
                return super.a() && (bVar = this.f16650c) != null && bVar.a() && (c0800a = this.d) != null && c0800a.a();
            }
        }

        @Override // com.tencent.mapsdk.internal.f4.a
        public boolean a() {
            C0799a c0799a;
            return super.a() && z3.Gradient.b(this.a) && (c0799a = this.f16649c) != null && c0799a.a();
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
            return this.b.f16649c.a;
        }
        return 0;
    }
}
