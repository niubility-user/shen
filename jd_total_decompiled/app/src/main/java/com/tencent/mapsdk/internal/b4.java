package com.tencent.mapsdk.internal;

import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.mapsdk.internal.f4;

/* loaded from: classes9.dex */
public final class b4 extends f4 {
    @Json(name = "detail")
    public a b;

    /* loaded from: classes9.dex */
    public static class a extends f4.a {
        @Json(name = "styleTable")

        /* renamed from: c  reason: collision with root package name */
        public C0785a f16283c;

        /* renamed from: com.tencent.mapsdk.internal.b4$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static class C0785a extends f4.c {
            @Json(name = CustomThemeConstance.TABLE_NAME)

            /* renamed from: c  reason: collision with root package name */
            public b f16284c;
            @Json(name = "control")
            public C0786a d;

            /* renamed from: com.tencent.mapsdk.internal.b4$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes9.dex */
            public static final class C0786a extends f4.c.a {
                @Json(name = "enable3D")

                /* renamed from: h  reason: collision with root package name */
                public boolean f16285h;
                @Json(name = "animated")

                /* renamed from: i  reason: collision with root package name */
                public boolean f16286i;
                @Json(name = "animation")

                /* renamed from: j  reason: collision with root package name */
                public C0787a f16287j;

                /* renamed from: com.tencent.mapsdk.internal.b4$a$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes9.dex */
                public static class C0787a extends JsonComposer {
                    @Json(name = "highlightDuration")
                    public double a;
                    @Json(name = "highlightColor")
                    public int b;
                    @Json(name = "duration")

                    /* renamed from: c  reason: collision with root package name */
                    public double f16288c;
                }
            }

            /* renamed from: com.tencent.mapsdk.internal.b4$a$a$b */
            /* loaded from: classes9.dex */
            public static class b extends JsonComposer {
                @Json(name = ScaleModeConstants.TEXT_SCALE_MODE_STANDARD)
                public C0790b a;

                /* renamed from: com.tencent.mapsdk.internal.b4$a$a$b$a  reason: collision with other inner class name */
                /* loaded from: classes9.dex */
                public static final class C0788a extends f4.c.AbstractC0794c {
                    @Json(name = "width")
                    public int a;
                    @Json(name = "radian")
                    public double b;
                    @Json(name = "gradient")

                    /* renamed from: c  reason: collision with root package name */
                    public f4.c.e f16289c;
                    @Json(name = "animation")
                    public C0789a d;

                    /* renamed from: com.tencent.mapsdk.internal.b4$a$a$b$a$a  reason: collision with other inner class name */
                    /* loaded from: classes9.dex */
                    public static final class C0789a extends f4.c.d {
                        @Json(name = "highlightDuration")
                        public double b;
                        @Json(name = "highlightColor")

                        /* renamed from: c  reason: collision with root package name */
                        public int f16290c;
                    }
                }

                /* renamed from: com.tencent.mapsdk.internal.b4$a$a$b$b  reason: collision with other inner class name */
                /* loaded from: classes9.dex */
                public static class C0790b extends JsonComposer {
                    @Json(name = "defaultStyle")
                    public C0788a a;
                }
            }
        }

        @Override // com.tencent.mapsdk.internal.f4.a
        public boolean a() {
            C0785a c0785a;
            return super.a() && z3.ArcLine.b(this.a) && (c0785a = this.f16283c) != null && c0785a.a();
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
            return this.b.f16283c.a;
        }
        return 0;
    }
}
