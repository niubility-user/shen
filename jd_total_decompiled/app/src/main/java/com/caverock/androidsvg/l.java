package com.caverock.androidsvg;

import android.graphics.Matrix;
import android.util.Xml;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.caverock.androidsvg.b;
import com.caverock.androidsvg.f;
import com.caverock.androidsvg.h;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.widget.custom.liveplayer.util.TemplateFlagKt;
import com.jingdong.sdk.platform.business.personal.R2;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class l {
    private int d;
    private com.caverock.androidsvg.h a = null;
    private h.j0 b = null;

    /* renamed from: c  reason: collision with root package name */
    private boolean f940c = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f941e = false;

    /* renamed from: f  reason: collision with root package name */
    private h f942f = null;

    /* renamed from: g  reason: collision with root package name */
    private StringBuilder f943g = null;

    /* renamed from: h  reason: collision with root package name */
    private boolean f944h = false;

    /* renamed from: i  reason: collision with root package name */
    private StringBuilder f945i = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[g.values().length];
            b = iArr;
            try {
                iArr[g.x.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[g.y.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[g.width.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[g.height.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[g.version.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[g.href.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[g.preserveAspectRatio.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[g.d.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[g.pathLength.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[g.rx.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[g.ry.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[g.cx.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[g.cy.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[g.r.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[g.x1.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                b[g.y1.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                b[g.x2.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                b[g.y2.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                b[g.dx.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                b[g.dy.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                b[g.requiredFeatures.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                b[g.requiredExtensions.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                b[g.systemLanguage.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                b[g.requiredFormats.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                b[g.requiredFonts.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                b[g.refX.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                b[g.refY.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                b[g.markerWidth.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                b[g.markerHeight.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                b[g.markerUnits.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                b[g.orient.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                b[g.gradientUnits.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                b[g.gradientTransform.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                b[g.spreadMethod.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                b[g.fx.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                b[g.fy.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                b[g.offset.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                b[g.clipPathUnits.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                b[g.startOffset.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                b[g.patternUnits.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                b[g.patternContentUnits.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                b[g.patternTransform.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                b[g.maskUnits.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                b[g.maskContentUnits.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                b[g.style.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                b[g.CLASS.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                b[g.fill.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                b[g.fill_rule.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                b[g.fill_opacity.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                b[g.stroke.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                b[g.stroke_opacity.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                b[g.stroke_width.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                b[g.stroke_linecap.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                b[g.stroke_linejoin.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                b[g.stroke_miterlimit.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                b[g.stroke_dasharray.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                b[g.stroke_dashoffset.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                b[g.opacity.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                b[g.color.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                b[g.font.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                b[g.font_family.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                b[g.font_size.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                b[g.font_weight.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                b[g.font_style.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                b[g.text_decoration.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                b[g.direction.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                b[g.text_anchor.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                b[g.overflow.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                b[g.marker.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                b[g.marker_start.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                b[g.marker_mid.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                b[g.marker_end.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                b[g.display.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                b[g.visibility.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                b[g.stop_color.ordinal()] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                b[g.stop_opacity.ordinal()] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                b[g.clip.ordinal()] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                b[g.clip_path.ordinal()] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                b[g.clip_rule.ordinal()] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                b[g.mask.ordinal()] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                b[g.solid_color.ordinal()] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                b[g.solid_opacity.ordinal()] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                b[g.viewport_fill.ordinal()] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                b[g.viewport_fill_opacity.ordinal()] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                b[g.vector_effect.ordinal()] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                b[g.image_rendering.ordinal()] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                b[g.viewBox.ordinal()] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                b[g.type.ordinal()] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                b[g.media.ordinal()] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            int[] iArr2 = new int[h.values().length];
            a = iArr2;
            try {
                iArr2[h.svg.ordinal()] = 1;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                a[h.g.ordinal()] = 2;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                a[h.a.ordinal()] = 3;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                a[h.defs.ordinal()] = 4;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                a[h.use.ordinal()] = 5;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                a[h.path.ordinal()] = 6;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                a[h.rect.ordinal()] = 7;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                a[h.circle.ordinal()] = 8;
            } catch (NoSuchFieldError unused97) {
            }
            try {
                a[h.ellipse.ordinal()] = 9;
            } catch (NoSuchFieldError unused98) {
            }
            try {
                a[h.line.ordinal()] = 10;
            } catch (NoSuchFieldError unused99) {
            }
            try {
                a[h.polyline.ordinal()] = 11;
            } catch (NoSuchFieldError unused100) {
            }
            try {
                a[h.polygon.ordinal()] = 12;
            } catch (NoSuchFieldError unused101) {
            }
            try {
                a[h.text.ordinal()] = 13;
            } catch (NoSuchFieldError unused102) {
            }
            try {
                a[h.tspan.ordinal()] = 14;
            } catch (NoSuchFieldError unused103) {
            }
            try {
                a[h.tref.ordinal()] = 15;
            } catch (NoSuchFieldError unused104) {
            }
            try {
                a[h.SWITCH.ordinal()] = 16;
            } catch (NoSuchFieldError unused105) {
            }
            try {
                a[h.symbol.ordinal()] = 17;
            } catch (NoSuchFieldError unused106) {
            }
            try {
                a[h.marker.ordinal()] = 18;
            } catch (NoSuchFieldError unused107) {
            }
            try {
                a[h.linearGradient.ordinal()] = 19;
            } catch (NoSuchFieldError unused108) {
            }
            try {
                a[h.radialGradient.ordinal()] = 20;
            } catch (NoSuchFieldError unused109) {
            }
            try {
                a[h.stop.ordinal()] = 21;
            } catch (NoSuchFieldError unused110) {
            }
            try {
                a[h.title.ordinal()] = 22;
            } catch (NoSuchFieldError unused111) {
            }
            try {
                a[h.desc.ordinal()] = 23;
            } catch (NoSuchFieldError unused112) {
            }
            try {
                a[h.clipPath.ordinal()] = 24;
            } catch (NoSuchFieldError unused113) {
            }
            try {
                a[h.textPath.ordinal()] = 25;
            } catch (NoSuchFieldError unused114) {
            }
            try {
                a[h.pattern.ordinal()] = 26;
            } catch (NoSuchFieldError unused115) {
            }
            try {
                a[h.image.ordinal()] = 27;
            } catch (NoSuchFieldError unused116) {
            }
            try {
                a[h.view.ordinal()] = 28;
            } catch (NoSuchFieldError unused117) {
            }
            try {
                a[h.mask.ordinal()] = 29;
            } catch (NoSuchFieldError unused118) {
            }
            try {
                a[h.style.ordinal()] = 30;
            } catch (NoSuchFieldError unused119) {
            }
            try {
                a[h.solidColor.ordinal()] = 31;
            } catch (NoSuchFieldError unused120) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        private static final Map<String, f.a> a;

        static {
            HashMap hashMap = new HashMap(10);
            a = hashMap;
            hashMap.put("none", f.a.none);
            hashMap.put("xMinYMin", f.a.xMinYMin);
            hashMap.put("xMidYMin", f.a.xMidYMin);
            hashMap.put("xMaxYMin", f.a.xMaxYMin);
            hashMap.put("xMinYMid", f.a.xMinYMid);
            hashMap.put("xMidYMid", f.a.xMidYMid);
            hashMap.put("xMaxYMid", f.a.xMaxYMid);
            hashMap.put("xMinYMax", f.a.xMinYMax);
            hashMap.put("xMidYMax", f.a.xMidYMax);
            hashMap.put("xMaxYMax", f.a.xMaxYMax);
        }

        static f.a a(String str) {
            return a.get(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {
        private static final Map<String, Integer> a;

        static {
            HashMap hashMap = new HashMap(47);
            a = hashMap;
            hashMap.put("aliceblue", -984833);
            hashMap.put("antiquewhite", -332841);
            hashMap.put("aqua", -16711681);
            hashMap.put("aquamarine", -8388652);
            hashMap.put("azure", -983041);
            hashMap.put("beige", -657956);
            hashMap.put("bisque", -6972);
            hashMap.put("black", -16777216);
            hashMap.put("blanchedalmond", -5171);
            hashMap.put("blue", -16776961);
            hashMap.put("blueviolet", -7722014);
            hashMap.put("brown", -5952982);
            hashMap.put("burlywood", -2180985);
            hashMap.put("cadetblue", -10510688);
            hashMap.put("chartreuse", -8388864);
            hashMap.put("chocolate", -2987746);
            hashMap.put("coral", -32944);
            hashMap.put("cornflowerblue", -10185235);
            hashMap.put("cornsilk", -1828);
            hashMap.put("crimson", -2354116);
            hashMap.put("cyan", -16711681);
            hashMap.put("darkblue", -16777077);
            hashMap.put("darkcyan", -16741493);
            hashMap.put("darkgoldenrod", -4684277);
            hashMap.put("darkgray", -5658199);
            hashMap.put("darkgreen", -16751616);
            hashMap.put("darkgrey", -5658199);
            hashMap.put("darkkhaki", -4343957);
            hashMap.put("darkmagenta", -7667573);
            hashMap.put("darkolivegreen", -11179217);
            hashMap.put("darkorange", -29696);
            hashMap.put("darkorchid", -6737204);
            hashMap.put("darkred", -7667712);
            hashMap.put("darksalmon", -1468806);
            hashMap.put("darkseagreen", -7357297);
            hashMap.put("darkslateblue", -12042869);
            hashMap.put("darkslategray", -13676721);
            hashMap.put("darkslategrey", -13676721);
            hashMap.put("darkturquoise", -16724271);
            hashMap.put("darkviolet", -7077677);
            hashMap.put("deeppink", -60269);
            hashMap.put("deepskyblue", -16728065);
            hashMap.put("dimgray", -9868951);
            hashMap.put("dimgrey", -9868951);
            hashMap.put("dodgerblue", -14774017);
            hashMap.put("firebrick", -5103070);
            hashMap.put("floralwhite", -1296);
            hashMap.put("forestgreen", -14513374);
            hashMap.put("fuchsia", -65281);
            hashMap.put("gainsboro", -2302756);
            hashMap.put("ghostwhite", -460545);
            hashMap.put("gold", -10496);
            hashMap.put("goldenrod", -2448096);
            hashMap.put("gray", -8355712);
            hashMap.put("green", -16744448);
            hashMap.put("greenyellow", -5374161);
            hashMap.put("grey", -8355712);
            hashMap.put("honeydew", -983056);
            hashMap.put("hotpink", -38476);
            hashMap.put("indianred", -3318692);
            hashMap.put("indigo", -11861886);
            hashMap.put("ivory", -16);
            hashMap.put("khaki", -989556);
            hashMap.put("lavender", -1644806);
            hashMap.put("lavenderblush", -3851);
            hashMap.put("lawngreen", -8586240);
            hashMap.put("lemonchiffon", -1331);
            hashMap.put("lightblue", -5383962);
            hashMap.put("lightcoral", -1015680);
            hashMap.put("lightcyan", -2031617);
            hashMap.put("lightgoldenrodyellow", -329006);
            hashMap.put("lightgray", -2894893);
            hashMap.put("lightgreen", -7278960);
            hashMap.put("lightgrey", -2894893);
            hashMap.put("lightpink", -18751);
            hashMap.put("lightsalmon", -24454);
            hashMap.put("lightseagreen", -14634326);
            hashMap.put("lightskyblue", -7876870);
            hashMap.put("lightslategray", -8943463);
            hashMap.put("lightslategrey", -8943463);
            hashMap.put("lightsteelblue", -5192482);
            hashMap.put("lightyellow", -32);
            hashMap.put("lime", -16711936);
            hashMap.put("limegreen", -13447886);
            hashMap.put("linen", -331546);
            hashMap.put("magenta", -65281);
            hashMap.put("maroon", -8388608);
            hashMap.put("mediumaquamarine", -10039894);
            hashMap.put("mediumblue", -16777011);
            hashMap.put("mediumorchid", -4565549);
            hashMap.put("mediumpurple", -7114533);
            hashMap.put("mediumseagreen", -12799119);
            hashMap.put("mediumslateblue", -8689426);
            hashMap.put("mediumspringgreen", -16713062);
            hashMap.put("mediumturquoise", -12004916);
            hashMap.put("mediumvioletred", -3730043);
            hashMap.put("midnightblue", -15132304);
            hashMap.put("mintcream", -655366);
            hashMap.put("mistyrose", -6943);
            hashMap.put("moccasin", -6987);
            hashMap.put("navajowhite", -8531);
            hashMap.put("navy", -16777088);
            hashMap.put("oldlace", -133658);
            hashMap.put("olive", -8355840);
            hashMap.put("olivedrab", -9728477);
            hashMap.put("orange", -23296);
            hashMap.put("orangered", -47872);
            hashMap.put("orchid", -2461482);
            hashMap.put("palegoldenrod", -1120086);
            hashMap.put("palegreen", -6751336);
            hashMap.put("paleturquoise", -5247250);
            hashMap.put("palevioletred", -2396013);
            hashMap.put("papayawhip", -4139);
            hashMap.put("peachpuff", -9543);
            hashMap.put("peru", -3308225);
            hashMap.put("pink", -16181);
            hashMap.put("plum", -2252579);
            hashMap.put("powderblue", -5185306);
            hashMap.put("purple", -8388480);
            hashMap.put("rebeccapurple", -10079335);
            hashMap.put("red", Integer.valueOf((int) SupportMenu.CATEGORY_MASK));
            hashMap.put("rosybrown", -4419697);
            hashMap.put("royalblue", -12490271);
            hashMap.put("saddlebrown", -7650029);
            hashMap.put("salmon", -360334);
            hashMap.put("sandybrown", -744352);
            hashMap.put("seagreen", -13726889);
            hashMap.put("seashell", -2578);
            hashMap.put("sienna", -6270419);
            hashMap.put("silver", -4144960);
            hashMap.put("skyblue", -7876885);
            hashMap.put("slateblue", -9807155);
            hashMap.put("slategray", -9404272);
            hashMap.put("slategrey", -9404272);
            hashMap.put("snow", -1286);
            hashMap.put("springgreen", -16711809);
            hashMap.put("steelblue", -12156236);
            hashMap.put("tan", -2968436);
            hashMap.put("teal", -16744320);
            hashMap.put("thistle", -2572328);
            hashMap.put("tomato", -40121);
            hashMap.put("turquoise", -12525360);
            hashMap.put("violet", -1146130);
            hashMap.put("wheat", -663885);
            hashMap.put("white", -1);
            hashMap.put("whitesmoke", -657931);
            hashMap.put("yellow", Integer.valueOf((int) InputDeviceCompat.SOURCE_ANY));
            hashMap.put("yellowgreen", -6632142);
            hashMap.put("transparent", 0);
        }

        static Integer a(String str) {
            return a.get(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d {
        private static final Map<String, h.p> a;

        static {
            HashMap hashMap = new HashMap(9);
            a = hashMap;
            h.d1 d1Var = h.d1.pt;
            hashMap.put("xx-small", new h.p(0.694f, d1Var));
            hashMap.put("x-small", new h.p(0.833f, d1Var));
            hashMap.put(TemplateFlagKt.KEY_SMALL, new h.p(10.0f, d1Var));
            hashMap.put("medium", new h.p(12.0f, d1Var));
            hashMap.put(ScaleModeConstants.TEXT_SCALE_MODE_LARGE, new h.p(14.4f, d1Var));
            hashMap.put("x-large", new h.p(17.3f, d1Var));
            hashMap.put("xx-large", new h.p(20.7f, d1Var));
            h.d1 d1Var2 = h.d1.percent;
            hashMap.put("smaller", new h.p(83.33f, d1Var2));
            hashMap.put("larger", new h.p(120.0f, d1Var2));
        }

        static h.p a(String str) {
            return a.get(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class e {
        private static final Map<String, Integer> a;

        static {
            HashMap hashMap = new HashMap(13);
            a = hashMap;
            hashMap.put("normal", 400);
            hashMap.put("bold", 700);
            hashMap.put("bolder", 1);
            hashMap.put("lighter", -1);
            hashMap.put("100", 100);
            hashMap.put(BasicPushStatus.SUCCESS_CODE, 200);
            hashMap.put("300", 300);
            hashMap.put("400", 400);
            hashMap.put("500", 500);
            hashMap.put("600", 600);
            hashMap.put("700", 700);
            hashMap.put("800", 800);
            hashMap.put("900", 900);
        }

        static Integer a(String str) {
            return a.get(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class f extends DefaultHandler2 {
        private f() {
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i2, int i3) throws SAXException {
            l.this.c1(new String(cArr, i2, i3));
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endDocument() throws SAXException {
            l.this.o();
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            l.this.p(str, str2, str3);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void processingInstruction(String str, String str2) throws SAXException {
            l.this.r(str, l.this.x0(new i(str2)));
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startDocument() throws SAXException {
            l.this.W0();
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            l.this.X0(str, str2, str3, attributes);
        }

        /* synthetic */ f(l lVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum g {
        CLASS,
        clip,
        clip_path,
        clipPathUnits,
        clip_rule,
        color,
        cx,
        cy,
        direction,
        dx,
        dy,
        fx,
        fy,
        d,
        display,
        fill,
        fill_rule,
        fill_opacity,
        font,
        font_family,
        font_size,
        font_weight,
        font_style,
        gradientTransform,
        gradientUnits,
        height,
        href,
        image_rendering,
        marker,
        marker_start,
        marker_mid,
        marker_end,
        markerHeight,
        markerUnits,
        markerWidth,
        mask,
        maskContentUnits,
        maskUnits,
        media,
        offset,
        opacity,
        orient,
        overflow,
        pathLength,
        patternContentUnits,
        patternTransform,
        patternUnits,
        points,
        preserveAspectRatio,
        r,
        refX,
        refY,
        requiredFeatures,
        requiredExtensions,
        requiredFormats,
        requiredFonts,
        rx,
        ry,
        solid_color,
        solid_opacity,
        spreadMethod,
        startOffset,
        stop_color,
        stop_opacity,
        stroke,
        stroke_dasharray,
        stroke_dashoffset,
        stroke_linecap,
        stroke_linejoin,
        stroke_miterlimit,
        stroke_opacity,
        stroke_width,
        style,
        systemLanguage,
        text_anchor,
        text_decoration,
        transform,
        type,
        vector_effect,
        version,
        viewBox,
        width,
        x,
        y,
        x1,
        y1,
        x2,
        y2,
        viewport_fill,
        viewport_fill_opacity,
        visibility,
        UNSUPPORTED;
        

        /* renamed from: g  reason: collision with root package name */
        private static final Map<String, g> f946g = new HashMap();

        static {
            for (g gVar : values()) {
                if (gVar == CLASS) {
                    f946g.put("class", gVar);
                } else if (gVar != UNSUPPORTED) {
                    f946g.put(gVar.name().replace('_', '-'), gVar);
                }
            }
        }

        public static g fromString(String str) {
            g gVar = f946g.get(str);
            return gVar != null ? gVar : UNSUPPORTED;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum h {
        svg,
        a,
        circle,
        clipPath,
        defs,
        desc,
        ellipse,
        g,
        image,
        line,
        linearGradient,
        marker,
        mask,
        path,
        pattern,
        polygon,
        polyline,
        radialGradient,
        rect,
        solidColor,
        stop,
        style,
        SWITCH,
        symbol,
        text,
        textPath,
        title,
        tref,
        tspan,
        use,
        view,
        UNSUPPORTED;
        

        /* renamed from: h  reason: collision with root package name */
        private static final Map<String, h> f950h = new HashMap();

        static {
            for (h hVar : values()) {
                if (hVar == SWITCH) {
                    f950h.put("switch", hVar);
                } else if (hVar != UNSUPPORTED) {
                    f950h.put(hVar.name(), hVar);
                }
            }
        }

        public static h fromString(String str) {
            h hVar = f950h.get(str);
            return hVar != null ? hVar : UNSUPPORTED;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class i {
        String a;

        /* renamed from: c  reason: collision with root package name */
        int f952c;
        int b = 0;
        private com.caverock.androidsvg.e d = new com.caverock.androidsvg.e();

        /* JADX INFO: Access modifiers changed from: package-private */
        public i(String str) {
            this.f952c = 0;
            String trim = str.trim();
            this.a = trim;
            this.f952c = trim.length();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void A() {
            while (true) {
                int i2 = this.b;
                if (i2 >= this.f952c || !k(this.a.charAt(i2))) {
                    return;
                }
                this.b++;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int a() {
            int i2 = this.b;
            int i3 = this.f952c;
            if (i2 == i3) {
                return -1;
            }
            int i4 = i2 + 1;
            this.b = i4;
            if (i4 < i3) {
                return this.a.charAt(i4);
            }
            return -1;
        }

        String b() {
            int i2 = this.b;
            while (!h() && !k(this.a.charAt(this.b))) {
                this.b++;
            }
            String substring = this.a.substring(i2, this.b);
            this.b = i2;
            return substring;
        }

        Boolean c(Object obj) {
            if (obj == null) {
                return null;
            }
            z();
            return m();
        }

        float d(float f2) {
            if (Float.isNaN(f2)) {
                return Float.NaN;
            }
            z();
            return n();
        }

        float e(Boolean bool) {
            if (bool == null) {
                return Float.NaN;
            }
            z();
            return n();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean f(char c2) {
            int i2 = this.b;
            boolean z = i2 < this.f952c && this.a.charAt(i2) == c2;
            if (z) {
                this.b++;
            }
            return z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean g(String str) {
            int length = str.length();
            int i2 = this.b;
            boolean z = i2 <= this.f952c - length && this.a.substring(i2, i2 + length).equals(str);
            if (z) {
                this.b += length;
            }
            return z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean h() {
            return this.b == this.f952c;
        }

        boolean i() {
            int i2 = this.b;
            if (i2 == this.f952c) {
                return false;
            }
            char charAt = this.a.charAt(i2);
            return (charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z');
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean j(int i2) {
            return i2 == 10 || i2 == 13;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean k(int i2) {
            return i2 == 32 || i2 == 10 || i2 == 13 || i2 == 9;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Integer l() {
            int i2 = this.b;
            if (i2 == this.f952c) {
                return null;
            }
            String str = this.a;
            this.b = i2 + 1;
            return Integer.valueOf(str.charAt(i2));
        }

        Boolean m() {
            int i2 = this.b;
            if (i2 == this.f952c) {
                return null;
            }
            char charAt = this.a.charAt(i2);
            if (charAt == '0' || charAt == '1') {
                this.b++;
                return Boolean.valueOf(charAt == '1');
            }
            return null;
        }

        float n() {
            float b = this.d.b(this.a, this.b, this.f952c);
            if (!Float.isNaN(b)) {
                this.b = this.d.a();
            }
            return b;
        }

        String o() {
            if (h()) {
                return null;
            }
            int i2 = this.b;
            int charAt = this.a.charAt(i2);
            while (true) {
                if ((charAt < 97 || charAt > 122) && (charAt < 65 || charAt > 90)) {
                    break;
                }
                charAt = a();
            }
            int i3 = this.b;
            while (k(charAt)) {
                charAt = a();
            }
            if (charAt == 40) {
                this.b++;
                return this.a.substring(i2, i3);
            }
            this.b = i2;
            return null;
        }

        h.p p() {
            float n2 = n();
            if (Float.isNaN(n2)) {
                return null;
            }
            h.d1 v = v();
            if (v == null) {
                return new h.p(n2, h.d1.px);
            }
            return new h.p(n2, v);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String q() {
            if (h()) {
                return null;
            }
            int i2 = this.b;
            char charAt = this.a.charAt(i2);
            if (charAt == '\'' || charAt == '\"') {
                int a = a();
                while (a != -1 && a != charAt) {
                    a = a();
                }
                if (a == -1) {
                    this.b = i2;
                    return null;
                }
                int i3 = this.b + 1;
                this.b = i3;
                return this.a.substring(i2 + 1, i3 - 1);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String r() {
            return t(' ', false);
        }

        String s(char c2) {
            return t(c2, false);
        }

        String t(char c2, boolean z) {
            if (h()) {
                return null;
            }
            char charAt = this.a.charAt(this.b);
            if ((z || !k(charAt)) && charAt != c2) {
                int i2 = this.b;
                int a = a();
                while (a != -1 && a != c2 && (z || !k(a))) {
                    a = a();
                }
                return this.a.substring(i2, this.b);
            }
            return null;
        }

        String u(char c2) {
            return t(c2, true);
        }

        h.d1 v() {
            if (h()) {
                return null;
            }
            if (this.a.charAt(this.b) == '%') {
                this.b++;
                return h.d1.percent;
            }
            int i2 = this.b;
            if (i2 > this.f952c - 2) {
                return null;
            }
            try {
                h.d1 valueOf = h.d1.valueOf(this.a.substring(i2, i2 + 2).toLowerCase(Locale.US));
                this.b += 2;
                return valueOf;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String w() {
            if (h()) {
                return null;
            }
            int i2 = this.b;
            char charAt = this.a.charAt(i2);
            if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
                int a = a();
                while (true) {
                    if ((a < 65 || a > 90) && (a < 97 || a > 122)) {
                        break;
                    }
                    a = a();
                }
                return this.a.substring(i2, this.b);
            }
            this.b = i2;
            return null;
        }

        float x() {
            z();
            float b = this.d.b(this.a, this.b, this.f952c);
            if (!Float.isNaN(b)) {
                this.b = this.d.a();
            }
            return b;
        }

        String y() {
            if (h()) {
                return null;
            }
            int i2 = this.b;
            this.b = this.f952c;
            return this.a.substring(i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean z() {
            A();
            int i2 = this.b;
            if (i2 != this.f952c && this.a.charAt(i2) == ',') {
                this.b++;
                A();
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class j implements Attributes {
        private XmlPullParser a;

        public j(l lVar, XmlPullParser xmlPullParser) {
            this.a = xmlPullParser;
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String str) {
            return -1;
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String str, String str2) {
            return -1;
        }

        @Override // org.xml.sax.Attributes
        public int getLength() {
            return this.a.getAttributeCount();
        }

        @Override // org.xml.sax.Attributes
        public String getLocalName(int i2) {
            return this.a.getAttributeName(i2);
        }

        @Override // org.xml.sax.Attributes
        public String getQName(int i2) {
            String attributeName = this.a.getAttributeName(i2);
            if (this.a.getAttributePrefix(i2) != null) {
                return this.a.getAttributePrefix(i2) + ':' + attributeName;
            }
            return attributeName;
        }

        @Override // org.xml.sax.Attributes
        public String getType(int i2) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getType(String str) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getType(String str, String str2) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getURI(int i2) {
            return this.a.getAttributeNamespace(i2);
        }

        @Override // org.xml.sax.Attributes
        public String getValue(int i2) {
            return this.a.getAttributeValue(i2);
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String str) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String str, String str2) {
            return null;
        }
    }

    private void A(h.d dVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            switch (a.b[g.fromString(attributes.getLocalName(i2)).ordinal()]) {
                case 12:
                    dVar.o = o0(trim);
                    break;
                case 13:
                    dVar.p = o0(trim);
                    break;
                case 14:
                    h.p o0 = o0(trim);
                    dVar.q = o0;
                    if (o0.g()) {
                        throw new k("Invalid <circle> element. r cannot be negative");
                    }
                    break;
            }
        }
    }

    private static Set<String> A0(String str) {
        i iVar = new i(str);
        HashSet hashSet = new HashSet();
        while (!iVar.h()) {
            hashSet.add(iVar.r());
            iVar.A();
        }
        return hashSet;
    }

    private void B(h.e eVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            if (a.b[g.fromString(attributes.getLocalName(i2)).ordinal()] == 38) {
                if ("objectBoundingBox".equals(trim)) {
                    eVar.o = Boolean.FALSE;
                } else if ("userSpaceOnUse".equals(trim)) {
                    eVar.o = Boolean.TRUE;
                } else {
                    throw new k("Invalid value for attribute clipPathUnits");
                }
            }
        }
    }

    private static h.p[] B0(String str) {
        h.p p;
        i iVar = new i(str);
        iVar.A();
        if (iVar.h() || (p = iVar.p()) == null || p.g()) {
            return null;
        }
        float a2 = p.a();
        ArrayList arrayList = new ArrayList();
        arrayList.add(p);
        while (!iVar.h()) {
            iVar.z();
            h.p p2 = iVar.p();
            if (p2 == null || p2.g()) {
                return null;
            }
            arrayList.add(p2);
            a2 += p2.a();
        }
        if (a2 == 0.0f) {
            return null;
        }
        return (h.p[]) arrayList.toArray(new h.p[arrayList.size()]);
    }

    private void C(h.g0 g0Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            switch (a.b[g.fromString(attributes.getLocalName(i2)).ordinal()]) {
                case 21:
                    g0Var.e(z0(trim));
                    break;
                case 22:
                    g0Var.j(trim);
                    break;
                case 23:
                    g0Var.g(F0(trim));
                    break;
                case 24:
                    g0Var.i(A0(trim));
                    break;
                case 25:
                    List<String> i0 = i0(trim);
                    g0Var.c(i0 != null ? new HashSet(i0) : new HashSet(0));
                    break;
            }
        }
    }

    private static h.e0.c C0(String str) {
        if ("butt".equals(str)) {
            return h.e0.c.Butt;
        }
        if ("round".equals(str)) {
            return h.e0.c.Round;
        }
        if ("square".equals(str)) {
            return h.e0.c.Square;
        }
        return null;
    }

    private void D(h.l0 l0Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String qName = attributes.getQName(i2);
            if (!qName.equals("id") && !qName.equals("xml:id")) {
                if (qName.equals("xml:space")) {
                    String trim = attributes.getValue(i2).trim();
                    if ("default".equals(trim)) {
                        l0Var.d = Boolean.FALSE;
                        return;
                    } else if ("preserve".equals(trim)) {
                        l0Var.d = Boolean.TRUE;
                        return;
                    } else {
                        throw new k("Invalid value for \"xml:space\" attribute: " + trim);
                    }
                }
            } else {
                l0Var.f897c = attributes.getValue(i2).trim();
                return;
            }
        }
    }

    private static h.e0.d D0(String str) {
        if ("miter".equals(str)) {
            return h.e0.d.Miter;
        }
        if ("round".equals(str)) {
            return h.e0.d.Round;
        }
        if ("bevel".equals(str)) {
            return h.e0.d.Bevel;
        }
        return null;
    }

    private void E(h.i iVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            switch (a.b[g.fromString(attributes.getLocalName(i2)).ordinal()]) {
                case 10:
                    h.p o0 = o0(trim);
                    iVar.q = o0;
                    if (o0.g()) {
                        throw new k("Invalid <ellipse> element. rx cannot be negative");
                    }
                    break;
                case 11:
                    h.p o02 = o0(trim);
                    iVar.r = o02;
                    if (o02.g()) {
                        throw new k("Invalid <ellipse> element. ry cannot be negative");
                    }
                    break;
                case 12:
                    iVar.o = o0(trim);
                    break;
                case 13:
                    iVar.p = o0(trim);
                    break;
            }
        }
    }

    private static void E0(h.l0 l0Var, String str) {
        i iVar = new i(str.replaceAll("/\\*.*?\\*/", ""));
        while (true) {
            String s = iVar.s(':');
            iVar.A();
            if (!iVar.f(':')) {
                return;
            }
            iVar.A();
            String u = iVar.u(';');
            if (u == null) {
                return;
            }
            iVar.A();
            if (iVar.h() || iVar.f(';')) {
                if (l0Var.f899f == null) {
                    l0Var.f899f = new h.e0();
                }
                S0(l0Var.f899f, s, u);
                iVar.A();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x008d, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void F(h.j jVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 != 6) {
                switch (i3) {
                    case 32:
                        if ("objectBoundingBox".equals(trim)) {
                            jVar.f890i = Boolean.FALSE;
                            continue;
                        } else if ("userSpaceOnUse".equals(trim)) {
                            jVar.f890i = Boolean.TRUE;
                            break;
                        } else {
                            throw new k("Invalid value for attribute gradientUnits");
                        }
                    case 33:
                        jVar.f891j = J0(trim);
                        continue;
                    case 34:
                        try {
                            jVar.f892k = h.k.valueOf(trim);
                            continue;
                        } catch (IllegalArgumentException unused) {
                            throw new k("Invalid spreadMethod attribute. \"" + trim + "\" is not a valid value.");
                        }
                }
            } else if ("".equals(attributes.getURI(i2)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i2))) {
                jVar.f893l = trim;
            }
        }
    }

    private static Set<String> F0(String str) {
        i iVar = new i(str);
        HashSet hashSet = new HashSet();
        while (!iVar.h()) {
            String r = iVar.r();
            int indexOf = r.indexOf(45);
            if (indexOf != -1) {
                r = r.substring(0, indexOf);
            }
            hashSet.add(new Locale(r, "", "").getLanguage());
            iVar.A();
        }
        return hashSet;
    }

    private void G(h.o oVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 == 1) {
                oVar.p = o0(trim);
            } else if (i3 == 2) {
                oVar.q = o0(trim);
            } else if (i3 == 3) {
                h.p o0 = o0(trim);
                oVar.r = o0;
                if (o0.g()) {
                    throw new k("Invalid <use> element. width cannot be negative");
                }
            } else if (i3 == 4) {
                h.p o02 = o0(trim);
                oVar.s = o02;
                if (o02.g()) {
                    throw new k("Invalid <use> element. height cannot be negative");
                }
            } else if (i3 != 6) {
                if (i3 == 7) {
                    w0(oVar, trim);
                }
            } else if ("".equals(attributes.getURI(i2)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i2))) {
                oVar.o = trim;
            }
        }
    }

    private static h.e0.f G0(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1074341483:
                if (str.equals(DYConstants.DY_MIDDLE)) {
                    c2 = 0;
                    break;
                }
                break;
            case 100571:
                if (str.equals("end")) {
                    c2 = 1;
                    break;
                }
                break;
            case 109757538:
                if (str.equals("start")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return h.e0.f.Middle;
            case 1:
                return h.e0.f.End;
            case 2:
                return h.e0.f.Start;
            default:
                return null;
        }
    }

    private void H(h.q qVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            switch (a.b[g.fromString(attributes.getLocalName(i2)).ordinal()]) {
                case 15:
                    qVar.o = o0(trim);
                    break;
                case 16:
                    qVar.p = o0(trim);
                    break;
                case 17:
                    qVar.q = o0(trim);
                    break;
                case 18:
                    qVar.r = o0(trim);
                    break;
            }
        }
    }

    private static h.e0.g H0(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1171789332:
                if (str.equals("line-through")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1026963764:
                if (str.equals("underline")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3387192:
                if (str.equals("none")) {
                    c2 = 2;
                    break;
                }
                break;
            case 93826908:
                if (str.equals("blink")) {
                    c2 = 3;
                    break;
                }
                break;
            case 529818312:
                if (str.equals("overline")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return h.e0.g.LineThrough;
            case 1:
                return h.e0.g.Underline;
            case 2:
                return h.e0.g.None;
            case 3:
                return h.e0.g.Blink;
            case 4:
                return h.e0.g.Overline;
            default:
                return null;
        }
    }

    private void I(h.m0 m0Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            switch (a.b[g.fromString(attributes.getLocalName(i2)).ordinal()]) {
                case 15:
                    m0Var.f902m = o0(trim);
                    break;
                case 16:
                    m0Var.f903n = o0(trim);
                    break;
                case 17:
                    m0Var.o = o0(trim);
                    break;
                case 18:
                    m0Var.p = o0(trim);
                    break;
            }
        }
    }

    private static h.e0.EnumC0013h I0(String str) {
        str.hashCode();
        if (str.equals("ltr")) {
            return h.e0.EnumC0013h.LTR;
        }
        if (str.equals("rtl")) {
            return h.e0.EnumC0013h.RTL;
        }
        return null;
    }

    private void J(h.r rVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            switch (a.b[g.fromString(attributes.getLocalName(i2)).ordinal()]) {
                case 26:
                    rVar.q = o0(trim);
                    break;
                case 27:
                    rVar.r = o0(trim);
                    break;
                case 28:
                    h.p o0 = o0(trim);
                    rVar.s = o0;
                    if (o0.g()) {
                        throw new k("Invalid <marker> element. markerWidth cannot be negative");
                    }
                    break;
                case 29:
                    h.p o02 = o0(trim);
                    rVar.t = o02;
                    if (o02.g()) {
                        throw new k("Invalid <marker> element. markerHeight cannot be negative");
                    }
                    break;
                case 30:
                    if ("strokeWidth".equals(trim)) {
                        rVar.p = false;
                        break;
                    } else if ("userSpaceOnUse".equals(trim)) {
                        rVar.p = true;
                        break;
                    } else {
                        throw new k("Invalid value for attribute markerUnits");
                    }
                case 31:
                    if ("auto".equals(trim)) {
                        rVar.u = Float.valueOf(Float.NaN);
                        break;
                    } else {
                        rVar.u = Float.valueOf(f0(trim));
                        break;
                    }
            }
        }
    }

    private Matrix J0(String str) throws k {
        Matrix matrix = new Matrix();
        i iVar = new i(str);
        iVar.A();
        while (!iVar.h()) {
            String o = iVar.o();
            if (o != null) {
                o.hashCode();
                char c2 = '\uffff';
                switch (o.hashCode()) {
                    case -1081239615:
                        if (o.equals("matrix")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -925180581:
                        if (o.equals("rotate")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case 109250890:
                        if (o.equals("scale")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case 109493390:
                        if (o.equals("skewX")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 109493391:
                        if (o.equals("skewY")) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case 1052832078:
                        if (o.equals("translate")) {
                            c2 = 5;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        iVar.A();
                        float n2 = iVar.n();
                        iVar.z();
                        float n3 = iVar.n();
                        iVar.z();
                        float n4 = iVar.n();
                        iVar.z();
                        float n5 = iVar.n();
                        iVar.z();
                        float n6 = iVar.n();
                        iVar.z();
                        float n7 = iVar.n();
                        iVar.A();
                        if (!Float.isNaN(n7) && iVar.f(')')) {
                            Matrix matrix2 = new Matrix();
                            matrix2.setValues(new float[]{n2, n4, n6, n3, n5, n7, 0.0f, 0.0f, 1.0f});
                            matrix.preConcat(matrix2);
                            break;
                        } else {
                            throw new k("Invalid transform list: " + str);
                        }
                    case 1:
                        iVar.A();
                        float n8 = iVar.n();
                        float x = iVar.x();
                        float x2 = iVar.x();
                        iVar.A();
                        if (!Float.isNaN(n8) && iVar.f(')')) {
                            if (Float.isNaN(x)) {
                                matrix.preRotate(n8);
                                break;
                            } else if (!Float.isNaN(x2)) {
                                matrix.preRotate(n8, x, x2);
                                break;
                            } else {
                                throw new k("Invalid transform list: " + str);
                            }
                        } else {
                            throw new k("Invalid transform list: " + str);
                        }
                    case 2:
                        iVar.A();
                        float n9 = iVar.n();
                        float x3 = iVar.x();
                        iVar.A();
                        if (!Float.isNaN(n9) && iVar.f(')')) {
                            if (Float.isNaN(x3)) {
                                matrix.preScale(n9, n9);
                                break;
                            } else {
                                matrix.preScale(n9, x3);
                                break;
                            }
                        } else {
                            throw new k("Invalid transform list: " + str);
                        }
                        break;
                    case 3:
                        iVar.A();
                        float n10 = iVar.n();
                        iVar.A();
                        if (!Float.isNaN(n10) && iVar.f(')')) {
                            matrix.preSkew((float) Math.tan(Math.toRadians(n10)), 0.0f);
                            break;
                        } else {
                            throw new k("Invalid transform list: " + str);
                        }
                    case 4:
                        iVar.A();
                        float n11 = iVar.n();
                        iVar.A();
                        if (!Float.isNaN(n11) && iVar.f(')')) {
                            matrix.preSkew(0.0f, (float) Math.tan(Math.toRadians(n11)));
                            break;
                        } else {
                            throw new k("Invalid transform list: " + str);
                        }
                        break;
                    case 5:
                        iVar.A();
                        float n12 = iVar.n();
                        float x4 = iVar.x();
                        iVar.A();
                        if (!Float.isNaN(n12) && iVar.f(')')) {
                            if (Float.isNaN(x4)) {
                                matrix.preTranslate(n12, 0.0f);
                                break;
                            } else {
                                matrix.preTranslate(n12, x4);
                                break;
                            }
                        } else {
                            throw new k("Invalid transform list: " + str);
                        }
                        break;
                    default:
                        throw new k("Invalid transform list fn: " + o + ")");
                }
                if (iVar.h()) {
                    return matrix;
                }
                iVar.z();
            } else {
                throw new k("Bad transform function encountered in transform list: " + str);
            }
        }
        return matrix;
    }

    private void K(h.s sVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 == 1) {
                sVar.p = o0(trim);
            } else if (i3 == 2) {
                sVar.q = o0(trim);
            } else if (i3 == 3) {
                h.p o0 = o0(trim);
                sVar.r = o0;
                if (o0.g()) {
                    throw new k("Invalid <mask> element. width cannot be negative");
                }
            } else if (i3 == 4) {
                h.p o02 = o0(trim);
                sVar.s = o02;
                if (o02.g()) {
                    throw new k("Invalid <mask> element. height cannot be negative");
                }
            } else if (i3 != 43) {
                if (i3 != 44) {
                    continue;
                } else if ("objectBoundingBox".equals(trim)) {
                    sVar.o = Boolean.FALSE;
                } else if ("userSpaceOnUse".equals(trim)) {
                    sVar.o = Boolean.TRUE;
                } else {
                    throw new k("Invalid value for attribute maskContentUnits");
                }
            } else if ("objectBoundingBox".equals(trim)) {
                sVar.f909n = Boolean.FALSE;
            } else if ("userSpaceOnUse".equals(trim)) {
                sVar.f909n = Boolean.TRUE;
            } else {
                throw new k("Invalid value for attribute maskUnits");
            }
        }
    }

    private void K0(InputStream inputStream) throws k {
        try {
            SAXParserFactory newInstance = SAXParserFactory.newInstance();
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            XMLReader xMLReader = newInstance.newSAXParser().getXMLReader();
            f fVar = new f(this, null);
            xMLReader.setContentHandler(fVar);
            xMLReader.setProperty("http://xml.org/sax/properties/lexical-handler", fVar);
            xMLReader.parse(new InputSource(inputStream));
        } catch (IOException e2) {
            throw new k("Stream error", e2);
        } catch (ParserConfigurationException e3) {
            throw new k("XML parser problem", e3);
        } catch (SAXException e4) {
            throw new k("SVG parse error", e4);
        }
    }

    private void L(h.v vVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 == 8) {
                vVar.o = u0(trim);
            } else if (i3 != 9) {
                continue;
            } else {
                Float valueOf = Float.valueOf(f0(trim));
                vVar.p = valueOf;
                if (valueOf.floatValue() < 0.0f) {
                    throw new k("Invalid <path> element. pathLength cannot be negative");
                }
            }
        }
    }

    private void L0(InputStream inputStream, boolean z) throws k {
        try {
            try {
                XmlPullParser newPullParser = Xml.newPullParser();
                j jVar = new j(this, newPullParser);
                newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", false);
                newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                newPullParser.setInput(inputStream, null);
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.nextToken()) {
                    if (eventType == 0) {
                        W0();
                    } else if (eventType == 8) {
                        String str = "PROC INSTR: " + newPullParser.getText();
                        i iVar = new i(newPullParser.getText());
                        r(iVar.r(), x0(iVar));
                    } else if (eventType != 10) {
                        if (eventType == 2) {
                            String name = newPullParser.getName();
                            if (newPullParser.getPrefix() != null) {
                                name = newPullParser.getPrefix() + ':' + name;
                            }
                            X0(newPullParser.getNamespace(), newPullParser.getName(), name, jVar);
                        } else if (eventType == 3) {
                            String name2 = newPullParser.getName();
                            if (newPullParser.getPrefix() != null) {
                                name2 = newPullParser.getPrefix() + ':' + name2;
                            }
                            p(newPullParser.getNamespace(), newPullParser.getName(), name2);
                        } else if (eventType == 4) {
                            int[] iArr = new int[2];
                            e1(newPullParser.getTextCharacters(iArr), iArr[0], iArr[1]);
                        } else if (eventType == 5) {
                            c1(newPullParser.getText());
                        }
                    } else if (z && this.a.l() == null && newPullParser.getText().contains("<!ENTITY ")) {
                        try {
                            inputStream.reset();
                            K0(inputStream);
                            return;
                        } catch (IOException unused) {
                            return;
                        }
                    }
                }
                o();
            } catch (IOException e2) {
                throw new k("Stream error", e2);
            }
        } catch (XmlPullParserException e3) {
            throw new k("XML parser problem", e3);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x00d0, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void M(h.y yVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 == 1) {
                yVar.s = o0(trim);
            } else if (i3 == 2) {
                yVar.t = o0(trim);
            } else if (i3 == 3) {
                h.p o0 = o0(trim);
                yVar.u = o0;
                if (o0.g()) {
                    throw new k("Invalid <pattern> element. width cannot be negative");
                }
            } else if (i3 == 4) {
                h.p o02 = o0(trim);
                yVar.v = o02;
                if (o02.g()) {
                    throw new k("Invalid <pattern> element. height cannot be negative");
                }
            } else if (i3 != 6) {
                switch (i3) {
                    case 40:
                        if ("objectBoundingBox".equals(trim)) {
                            yVar.p = Boolean.FALSE;
                            continue;
                        } else if ("userSpaceOnUse".equals(trim)) {
                            yVar.p = Boolean.TRUE;
                            break;
                        } else {
                            throw new k("Invalid value for attribute patternUnits");
                        }
                    case 41:
                        if ("objectBoundingBox".equals(trim)) {
                            yVar.q = Boolean.FALSE;
                            continue;
                        } else if ("userSpaceOnUse".equals(trim)) {
                            yVar.q = Boolean.TRUE;
                            break;
                        } else {
                            throw new k("Invalid value for attribute patternContentUnits");
                        }
                    case 42:
                        yVar.r = J0(trim);
                        continue;
                }
            } else if ("".equals(attributes.getURI(i2)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i2))) {
                yVar.w = trim;
            }
        }
    }

    private static h.e0.i M0(String str) {
        str.hashCode();
        if (str.equals("none")) {
            return h.e0.i.None;
        }
        if (str.equals("non-scaling-stroke")) {
            return h.e0.i.NonScalingStroke;
        }
        return null;
    }

    private void N(h.z zVar, Attributes attributes, String str) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            if (g.fromString(attributes.getLocalName(i2)) == g.points) {
                i iVar = new i(attributes.getValue(i2));
                ArrayList arrayList = new ArrayList();
                iVar.A();
                while (!iVar.h()) {
                    float n2 = iVar.n();
                    if (!Float.isNaN(n2)) {
                        iVar.z();
                        float n3 = iVar.n();
                        if (!Float.isNaN(n3)) {
                            iVar.z();
                            arrayList.add(Float.valueOf(n2));
                            arrayList.add(Float.valueOf(n3));
                        } else {
                            throw new k("Invalid <" + str + "> points attribute. There should be an even number of coordinates.");
                        }
                    } else {
                        throw new k("Invalid <" + str + "> points attribute. Non-coordinate content found in list.");
                    }
                }
                zVar.o = new float[arrayList.size()];
                Iterator it = arrayList.iterator();
                int i3 = 0;
                while (it.hasNext()) {
                    zVar.o[i3] = ((Float) it.next()).floatValue();
                    i3++;
                }
            }
        }
    }

    private static h.b N0(String str) throws k {
        i iVar = new i(str);
        iVar.A();
        float n2 = iVar.n();
        iVar.z();
        float n3 = iVar.n();
        iVar.z();
        float n4 = iVar.n();
        iVar.z();
        float n5 = iVar.n();
        if (Float.isNaN(n2) || Float.isNaN(n3) || Float.isNaN(n4) || Float.isNaN(n5)) {
            throw new k("Invalid viewBox definition - should have four numbers");
        }
        if (n4 >= 0.0f) {
            if (n5 >= 0.0f) {
                return new h.b(n2, n3, n4, n5);
            }
            throw new k("Invalid viewBox. height cannot be negative");
        }
        throw new k("Invalid viewBox. width cannot be negative");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x005b, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void O(h.q0 q0Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 == 35) {
                q0Var.p = o0(trim);
            } else if (i3 != 36) {
                switch (i3) {
                    case 12:
                        q0Var.f907m = o0(trim);
                        continue;
                    case 13:
                        q0Var.f908n = o0(trim);
                        continue;
                    case 14:
                        h.p o0 = o0(trim);
                        q0Var.o = o0;
                        if (o0.g()) {
                            throw new k("Invalid <radialGradient> element. r cannot be negative");
                        }
                        continue;
                }
            } else {
                q0Var.q = o0(trim);
            }
        }
    }

    private void O0(Attributes attributes) throws k {
        l("<path>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            h.v vVar = new h.v();
            vVar.a = this.a;
            vVar.b = j0Var;
            D(vVar, attributes);
            S(vVar, attributes);
            W(vVar, attributes);
            C(vVar, attributes);
            L(vVar, attributes);
            this.b.h(vVar);
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void P(h.b0 b0Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 == 1) {
                b0Var.o = o0(trim);
            } else if (i3 == 2) {
                b0Var.p = o0(trim);
            } else if (i3 == 3) {
                h.p o0 = o0(trim);
                b0Var.q = o0;
                if (o0.g()) {
                    throw new k("Invalid <rect> element. width cannot be negative");
                }
            } else if (i3 == 4) {
                h.p o02 = o0(trim);
                b0Var.r = o02;
                if (o02.g()) {
                    throw new k("Invalid <rect> element. height cannot be negative");
                }
            } else if (i3 == 10) {
                h.p o03 = o0(trim);
                b0Var.s = o03;
                if (o03.g()) {
                    throw new k("Invalid <rect> element. rx cannot be negative");
                }
            } else if (i3 != 11) {
                continue;
            } else {
                h.p o04 = o0(trim);
                b0Var.t = o04;
                if (o04.g()) {
                    throw new k("Invalid <rect> element. ry cannot be negative");
                }
            }
        }
    }

    private void P0(Attributes attributes) throws k {
        l("<pattern>", new Object[0]);
        if (this.b != null) {
            h.y yVar = new h.y();
            yVar.a = this.a;
            yVar.b = this.b;
            D(yVar, attributes);
            S(yVar, attributes);
            C(yVar, attributes);
            Y(yVar, attributes);
            M(yVar, attributes);
            this.b.h(yVar);
            this.b = yVar;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void Q(h.f0 f0Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 == 1) {
                f0Var.p = o0(trim);
            } else if (i3 == 2) {
                f0Var.q = o0(trim);
            } else if (i3 == 3) {
                h.p o0 = o0(trim);
                f0Var.r = o0;
                if (o0.g()) {
                    throw new k("Invalid <svg> element. width cannot be negative");
                }
            } else if (i3 == 4) {
                h.p o02 = o0(trim);
                f0Var.s = o02;
                if (o02.g()) {
                    throw new k("Invalid <svg> element. height cannot be negative");
                }
            } else if (i3 == 5) {
                f0Var.t = trim;
            }
        }
    }

    private void Q0(Attributes attributes) throws k {
        l("<polygon>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            h.z a0Var = new h.a0();
            a0Var.a = this.a;
            a0Var.b = j0Var;
            D(a0Var, attributes);
            S(a0Var, attributes);
            W(a0Var, attributes);
            C(a0Var, attributes);
            N(a0Var, attributes, "polygon");
            this.b.h(a0Var);
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void R(h.d0 d0Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            if (a.b[g.fromString(attributes.getLocalName(i2)).ordinal()] == 37) {
                d0Var.f856h = n0(trim);
            }
        }
    }

    private void R0(Attributes attributes) throws k {
        l("<polyline>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            h.z zVar = new h.z();
            zVar.a = this.a;
            zVar.b = j0Var;
            D(zVar, attributes);
            S(zVar, attributes);
            W(zVar, attributes);
            C(zVar, attributes);
            N(zVar, attributes, "polyline");
            this.b.h(zVar);
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void S(h.l0 l0Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            if (trim.length() != 0) {
                int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
                if (i3 == 45) {
                    E0(l0Var, trim);
                } else if (i3 != 46) {
                    if (l0Var.f898e == null) {
                        l0Var.f898e = new h.e0();
                    }
                    S0(l0Var.f898e, attributes.getLocalName(i2), attributes.getValue(i2).trim());
                } else {
                    l0Var.f900g = com.caverock.androidsvg.b.f(trim);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void S0(h.e0 e0Var, String str, String str2) {
        if (str2.length() == 0 || str2.equals("inherit")) {
            return;
        }
        try {
            switch (a.b[g.fromString(str).ordinal()]) {
                case 47:
                    h.o0 t0 = t0(str2);
                    e0Var.f859h = t0;
                    if (t0 != null) {
                        e0Var.f858g |= 1;
                        return;
                    }
                    return;
                case 48:
                    h.e0.a e0 = e0(str2);
                    e0Var.f860i = e0;
                    if (e0 != null) {
                        e0Var.f858g |= 2;
                        return;
                    }
                    return;
                case 49:
                    Float r0 = r0(str2);
                    e0Var.f861j = r0;
                    if (r0 != null) {
                        e0Var.f858g |= 4;
                        return;
                    }
                    return;
                case 50:
                    h.o0 t02 = t0(str2);
                    e0Var.f862k = t02;
                    if (t02 != null) {
                        e0Var.f858g |= 8;
                        return;
                    }
                    return;
                case 51:
                    Float r02 = r0(str2);
                    e0Var.f863l = r02;
                    if (r02 != null) {
                        e0Var.f858g |= 16;
                        return;
                    }
                    return;
                case 52:
                    e0Var.f864m = o0(str2);
                    e0Var.f858g |= 32;
                    break;
                case 53:
                    h.e0.c C0 = C0(str2);
                    e0Var.f865n = C0;
                    if (C0 != null) {
                        e0Var.f858g |= 64;
                        return;
                    }
                    return;
                case 54:
                    h.e0.d D0 = D0(str2);
                    e0Var.o = D0;
                    if (D0 != null) {
                        e0Var.f858g |= 128;
                        return;
                    }
                    return;
                case 55:
                    e0Var.p = Float.valueOf(f0(str2));
                    e0Var.f858g |= 256;
                    break;
                case 56:
                    if ("none".equals(str2)) {
                        e0Var.q = null;
                        e0Var.f858g |= 512;
                        return;
                    }
                    h.p[] B0 = B0(str2);
                    e0Var.q = B0;
                    if (B0 != null) {
                        e0Var.f858g |= 512;
                        return;
                    }
                    return;
                case 57:
                    e0Var.r = o0(str2);
                    e0Var.f858g |= 1024;
                    break;
                case 58:
                    e0Var.s = r0(str2);
                    e0Var.f858g |= 2048;
                    return;
                case 59:
                    e0Var.t = b0(str2);
                    e0Var.f858g |= 4096;
                    break;
                case 60:
                    h0(e0Var, str2);
                    return;
                case 61:
                    List<String> i0 = i0(str2);
                    e0Var.u = i0;
                    if (i0 != null) {
                        e0Var.f858g |= IjkMediaMeta.AV_CH_TOP_FRONT_CENTER;
                        return;
                    }
                    return;
                case 62:
                    h.p j0 = j0(str2);
                    e0Var.v = j0;
                    if (j0 != null) {
                        e0Var.f858g |= IjkMediaMeta.AV_CH_TOP_FRONT_RIGHT;
                        return;
                    }
                    return;
                case 63:
                    Integer l0 = l0(str2);
                    e0Var.w = l0;
                    if (l0 != null) {
                        e0Var.f858g |= IjkMediaMeta.AV_CH_TOP_BACK_LEFT;
                        return;
                    }
                    return;
                case 64:
                    h.e0.b k0 = k0(str2);
                    e0Var.x = k0;
                    if (k0 != null) {
                        e0Var.f858g |= IjkMediaMeta.AV_CH_TOP_BACK_CENTER;
                        return;
                    }
                    return;
                case 65:
                    h.e0.g H0 = H0(str2);
                    e0Var.y = H0;
                    if (H0 != null) {
                        e0Var.f858g |= IjkMediaMeta.AV_CH_TOP_BACK_RIGHT;
                        return;
                    }
                    return;
                case 66:
                    h.e0.EnumC0013h I0 = I0(str2);
                    e0Var.z = I0;
                    if (I0 != null) {
                        e0Var.f858g |= 68719476736L;
                        return;
                    }
                    return;
                case 67:
                    h.e0.f G0 = G0(str2);
                    e0Var.A = G0;
                    if (G0 != null) {
                        e0Var.f858g |= 262144;
                        return;
                    }
                    return;
                case 68:
                    Boolean s0 = s0(str2);
                    e0Var.B = s0;
                    if (s0 != null) {
                        e0Var.f858g |= 524288;
                        return;
                    }
                    return;
                case 69:
                    String m0 = m0(str2, str);
                    e0Var.D = m0;
                    e0Var.E = m0;
                    e0Var.F = m0;
                    e0Var.f858g |= 14680064;
                    return;
                case 70:
                    e0Var.D = m0(str2, str);
                    e0Var.f858g |= 2097152;
                    return;
                case 71:
                    e0Var.E = m0(str2, str);
                    e0Var.f858g |= 4194304;
                    return;
                case 72:
                    e0Var.F = m0(str2, str);
                    e0Var.f858g |= 8388608;
                    return;
                case 73:
                    if (str2.indexOf(124) < 0) {
                        if ("|inline|block|list-item|run-in|compact|marker|table|inline-table|table-row-group|table-header-group|table-footer-group|table-row|table-column-group|table-column|table-cell|table-caption|none|".contains('|' + str2 + '|')) {
                            e0Var.G = Boolean.valueOf(!str2.equals("none"));
                            e0Var.f858g |= 16777216;
                            return;
                        }
                        return;
                    }
                    return;
                case 74:
                    if (str2.indexOf(124) < 0) {
                        if ("|visible|hidden|collapse|".contains('|' + str2 + '|')) {
                            e0Var.H = Boolean.valueOf(str2.equals(ViewProps.VISIBLE));
                            e0Var.f858g |= 33554432;
                            return;
                        }
                        return;
                    }
                    return;
                case 75:
                    if (str2.equals("currentColor")) {
                        e0Var.I = h.g.a();
                    } else {
                        try {
                            e0Var.I = b0(str2);
                        } catch (k e2) {
                            e2.getMessage();
                            return;
                        }
                    }
                    e0Var.f858g |= 67108864;
                    return;
                case 76:
                    e0Var.J = r0(str2);
                    e0Var.f858g |= 134217728;
                    return;
                case 77:
                    h.c a0 = a0(str2);
                    e0Var.C = a0;
                    if (a0 != null) {
                        e0Var.f858g |= 1048576;
                        return;
                    }
                    return;
                case 78:
                    e0Var.K = m0(str2, str);
                    e0Var.f858g |= 268435456;
                    return;
                case 79:
                    e0Var.L = e0(str2);
                    e0Var.f858g |= IjkMediaMeta.AV_CH_STEREO_LEFT;
                    return;
                case 80:
                    e0Var.M = m0(str2, str);
                    e0Var.f858g |= IjkMediaMeta.AV_CH_STEREO_RIGHT;
                    return;
                case 81:
                    if (str2.equals("currentColor")) {
                        e0Var.N = h.g.a();
                    } else {
                        try {
                            e0Var.N = b0(str2);
                        } catch (k e3) {
                            e3.getMessage();
                            return;
                        }
                    }
                    e0Var.f858g |= IjkMediaMeta.AV_CH_WIDE_LEFT;
                    return;
                case 82:
                    e0Var.O = r0(str2);
                    e0Var.f858g |= IjkMediaMeta.AV_CH_WIDE_RIGHT;
                    return;
                case 83:
                    if (str2.equals("currentColor")) {
                        e0Var.P = h.g.a();
                    } else {
                        try {
                            e0Var.P = b0(str2);
                        } catch (k e4) {
                            e4.getMessage();
                            return;
                        }
                    }
                    e0Var.f858g |= IjkMediaMeta.AV_CH_SURROUND_DIRECT_LEFT;
                    return;
                case 84:
                    e0Var.Q = r0(str2);
                    e0Var.f858g |= IjkMediaMeta.AV_CH_SURROUND_DIRECT_RIGHT;
                    return;
                case 85:
                    h.e0.i M0 = M0(str2);
                    e0Var.R = M0;
                    if (M0 != null) {
                        e0Var.f858g |= IjkMediaMeta.AV_CH_LOW_FREQUENCY_2;
                        return;
                    }
                    return;
                case 86:
                    h.e0.e y0 = y0(str2);
                    e0Var.S = y0;
                    if (y0 != null) {
                        e0Var.f858g |= 137438953472L;
                        return;
                    }
                    return;
                default:
            }
        } catch (k unused) {
        }
    }

    private void T(h.u0 u0Var, Attributes attributes) {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            if (a.b[g.fromString(attributes.getLocalName(i2)).ordinal()] == 6 && ("".equals(attributes.getURI(i2)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i2)))) {
                u0Var.f912n = trim;
            }
        }
    }

    private void T0(Attributes attributes) throws k {
        l("<radialGradient>", new Object[0]);
        if (this.b != null) {
            h.q0 q0Var = new h.q0();
            q0Var.a = this.a;
            q0Var.b = this.b;
            D(q0Var, attributes);
            S(q0Var, attributes);
            F(q0Var, attributes);
            O(q0Var, attributes);
            this.b.h(q0Var);
            this.b = q0Var;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void U(h.z0 z0Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 != 6) {
                if (i3 == 39) {
                    z0Var.o = o0(trim);
                }
            } else if ("".equals(attributes.getURI(i2)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i2))) {
                z0Var.f914n = trim;
            }
        }
    }

    private void U0(Attributes attributes) throws k {
        l("<rect>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            h.b0 b0Var = new h.b0();
            b0Var.a = this.a;
            b0Var.b = j0Var;
            D(b0Var, attributes);
            S(b0Var, attributes);
            W(b0Var, attributes);
            C(b0Var, attributes);
            P(b0Var, attributes);
            this.b.h(b0Var);
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void V(h.a1 a1Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 == 1) {
                a1Var.f852n = p0(trim);
            } else if (i3 == 2) {
                a1Var.o = p0(trim);
            } else if (i3 == 19) {
                a1Var.p = p0(trim);
            } else if (i3 == 20) {
                a1Var.q = p0(trim);
            }
        }
    }

    private void V0(Attributes attributes) throws k {
        l("<solidColor>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            h.c0 c0Var = new h.c0();
            c0Var.a = this.a;
            c0Var.b = j0Var;
            D(c0Var, attributes);
            S(c0Var, attributes);
            this.b.h(c0Var);
            this.b = c0Var;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void W(h.n nVar, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            if (g.fromString(attributes.getLocalName(i2)) == g.transform) {
                nVar.k(J0(attributes.getValue(i2)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W0() {
        this.a = new com.caverock.androidsvg.h();
    }

    private void X(h.e1 e1Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 == 1) {
                e1Var.p = o0(trim);
            } else if (i3 == 2) {
                e1Var.q = o0(trim);
            } else if (i3 == 3) {
                h.p o0 = o0(trim);
                e1Var.r = o0;
                if (o0.g()) {
                    throw new k("Invalid <use> element. width cannot be negative");
                }
            } else if (i3 != 4) {
                if (i3 == 6 && ("".equals(attributes.getURI(i2)) || "http://www.w3.org/1999/xlink".equals(attributes.getURI(i2)))) {
                    e1Var.o = trim;
                }
            } else {
                h.p o02 = o0(trim);
                e1Var.s = o02;
                if (o02.g()) {
                    throw new k("Invalid <use> element. height cannot be negative");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X0(String str, String str2, String str3, Attributes attributes) throws k {
        if (this.f940c) {
            this.d++;
        } else if ("http://www.w3.org/2000/svg".equals(str) || "".equals(str)) {
            if (str2.length() <= 0) {
                str2 = str3;
            }
            h fromString = h.fromString(str2);
            switch (a.a[fromString.ordinal()]) {
                case 1:
                    a1(attributes);
                    return;
                case 2:
                case 3:
                    q(attributes);
                    return;
                case 4:
                    m(attributes);
                    return;
                case 5:
                    i1(attributes);
                    return;
                case 6:
                    O0(attributes);
                    return;
                case 7:
                    U0(attributes);
                    return;
                case 8:
                    i(attributes);
                    return;
                case 9:
                    n(attributes);
                    return;
                case 10:
                    v(attributes);
                    return;
                case 11:
                    R0(attributes);
                    return;
                case 12:
                    Q0(attributes);
                    return;
                case 13:
                    d1(attributes);
                    return;
                case 14:
                    h1(attributes);
                    return;
                case 15:
                    g1(attributes);
                    return;
                case 16:
                    k1(attributes);
                    return;
                case 17:
                    b1(attributes);
                    return;
                case 18:
                    x(attributes);
                    return;
                case 19:
                    w(attributes);
                    return;
                case 20:
                    T0(attributes);
                    return;
                case 21:
                    Y0(attributes);
                    return;
                case 22:
                case 23:
                    this.f941e = true;
                    this.f942f = fromString;
                    return;
                case 24:
                    k(attributes);
                    return;
                case 25:
                    f1(attributes);
                    return;
                case 26:
                    P0(attributes);
                    return;
                case 27:
                    u(attributes);
                    return;
                case 28:
                    j1(attributes);
                    return;
                case 29:
                    y(attributes);
                    return;
                case 30:
                    Z0(attributes);
                    return;
                case 31:
                    V0(attributes);
                    return;
                default:
                    this.f940c = true;
                    this.d = 1;
                    return;
            }
        }
    }

    private void Y(h.r0 r0Var, Attributes attributes) throws k {
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            String trim = attributes.getValue(i2).trim();
            int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
            if (i3 == 7) {
                w0(r0Var, trim);
            } else if (i3 == 87) {
                r0Var.o = N0(trim);
            }
        }
    }

    private void Y0(Attributes attributes) throws k {
        l("<stop>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            if (j0Var instanceof h.j) {
                h.d0 d0Var = new h.d0();
                d0Var.a = this.a;
                d0Var.b = j0Var;
                D(d0Var, attributes);
                S(d0Var, attributes);
                R(d0Var, attributes);
                this.b.h(d0Var);
                this.b = d0Var;
                return;
            }
            throw new k("Invalid document. <stop> elements are only valid inside <linearGradient> or <radialGradient> elements.");
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void Z(String str) {
        this.a.a(new com.caverock.androidsvg.b(b.f.screen, b.u.Document).d(str));
    }

    private void Z0(Attributes attributes) throws k {
        l("<style>", new Object[0]);
        if (this.b != null) {
            String str = NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL;
            boolean z = true;
            for (int i2 = 0; i2 < attributes.getLength(); i2++) {
                String trim = attributes.getValue(i2).trim();
                int i3 = a.b[g.fromString(attributes.getLocalName(i2)).ordinal()];
                if (i3 == 88) {
                    z = trim.equals("text/css");
                } else if (i3 == 89) {
                    str = trim;
                }
            }
            if (z && com.caverock.androidsvg.b.b(str, b.f.screen)) {
                this.f944h = true;
                return;
            }
            this.f940c = true;
            this.d = 1;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static h.c a0(String str) {
        if (!"auto".equals(str) && str.startsWith("rect(")) {
            i iVar = new i(str.substring(5));
            iVar.A();
            h.p q0 = q0(iVar);
            iVar.z();
            h.p q02 = q0(iVar);
            iVar.z();
            h.p q03 = q0(iVar);
            iVar.z();
            h.p q04 = q0(iVar);
            iVar.A();
            if (iVar.f(')') || iVar.h()) {
                return new h.c(q0, q02, q03, q04);
            }
            return null;
        }
        return null;
    }

    private void a1(Attributes attributes) throws k {
        l("<svg>", new Object[0]);
        h.f0 f0Var = new h.f0();
        f0Var.a = this.a;
        f0Var.b = this.b;
        D(f0Var, attributes);
        S(f0Var, attributes);
        C(f0Var, attributes);
        Y(f0Var, attributes);
        Q(f0Var, attributes);
        h.j0 j0Var = this.b;
        if (j0Var == null) {
            this.a.s(f0Var);
        } else {
            j0Var.h(f0Var);
        }
        this.b = f0Var;
    }

    private static h.f b0(String str) throws k {
        if (str.charAt(0) == '#') {
            com.caverock.androidsvg.d b2 = com.caverock.androidsvg.d.b(str, 1, str.length());
            if (b2 != null) {
                int a2 = b2.a();
                if (a2 == 4) {
                    int d2 = b2.d();
                    int i2 = d2 & R2.color.error_color_material_dark;
                    int i3 = d2 & 240;
                    int i4 = d2 & 15;
                    return new h.f(i4 | (i2 << 8) | (-16777216) | (i2 << 12) | (i3 << 8) | (i3 << 4) | (i4 << 4));
                } else if (a2 == 5) {
                    int d3 = b2.d();
                    int i5 = 61440 & d3;
                    int i6 = d3 & R2.color.error_color_material_dark;
                    int i7 = d3 & 240;
                    int i8 = d3 & 15;
                    return new h.f((i8 << 24) | (i8 << 28) | (i5 << 8) | (i5 << 4) | (i6 << 4) | i6 | i7 | (i7 >> 4));
                } else if (a2 != 7) {
                    if (a2 == 9) {
                        return new h.f((b2.d() >>> 8) | (b2.d() << 24));
                    }
                    throw new k("Bad hex colour value: " + str);
                } else {
                    return new h.f(b2.d() | (-16777216));
                }
            }
            throw new k("Bad hex colour value: " + str);
        }
        String lowerCase = str.toLowerCase(Locale.US);
        boolean startsWith = lowerCase.startsWith("rgba(");
        if (!startsWith && !lowerCase.startsWith("rgb(")) {
            boolean startsWith2 = lowerCase.startsWith("hsla(");
            if (!startsWith2 && !lowerCase.startsWith("hsl(")) {
                return c0(lowerCase);
            }
            i iVar = new i(str.substring(startsWith2 ? 5 : 4));
            iVar.A();
            float n2 = iVar.n();
            float d4 = iVar.d(n2);
            if (!Float.isNaN(d4)) {
                iVar.f('%');
            }
            float d5 = iVar.d(d4);
            if (!Float.isNaN(d5)) {
                iVar.f('%');
            }
            if (startsWith2) {
                float d6 = iVar.d(d5);
                iVar.A();
                if (!Float.isNaN(d6) && iVar.f(')')) {
                    return new h.f((j(d6 * 256.0f) << 24) | s(n2, d4, d5));
                }
                throw new k("Bad hsla() colour value: " + str);
            }
            iVar.A();
            if (!Float.isNaN(d5) && iVar.f(')')) {
                return new h.f(s(n2, d4, d5) | (-16777216));
            }
            throw new k("Bad hsl() colour value: " + str);
        }
        i iVar2 = new i(str.substring(startsWith ? 5 : 4));
        iVar2.A();
        float n3 = iVar2.n();
        if (!Float.isNaN(n3) && iVar2.f('%')) {
            n3 = (n3 * 256.0f) / 100.0f;
        }
        float d7 = iVar2.d(n3);
        if (!Float.isNaN(d7) && iVar2.f('%')) {
            d7 = (d7 * 256.0f) / 100.0f;
        }
        float d8 = iVar2.d(d7);
        if (!Float.isNaN(d8) && iVar2.f('%')) {
            d8 = (d8 * 256.0f) / 100.0f;
        }
        if (startsWith) {
            float d9 = iVar2.d(d8);
            iVar2.A();
            if (!Float.isNaN(d9) && iVar2.f(')')) {
                return new h.f((j(d9 * 256.0f) << 24) | (j(n3) << 16) | (j(d7) << 8) | j(d8));
            }
            throw new k("Bad rgba() colour value: " + str);
        }
        iVar2.A();
        if (!Float.isNaN(d8) && iVar2.f(')')) {
            return new h.f((j(n3) << 16) | (-16777216) | (j(d7) << 8) | j(d8));
        }
        throw new k("Bad rgb() colour value: " + str);
    }

    private void b1(Attributes attributes) throws k {
        l("<symbol>", new Object[0]);
        if (this.b != null) {
            h.r0 t0Var = new h.t0();
            t0Var.a = this.a;
            t0Var.b = this.b;
            D(t0Var, attributes);
            S(t0Var, attributes);
            C(t0Var, attributes);
            Y(t0Var, attributes);
            this.b.h(t0Var);
            this.b = t0Var;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static h.f c0(String str) throws k {
        Integer a2 = c.a(str);
        if (a2 != null) {
            return new h.f(a2.intValue());
        }
        throw new k("Invalid colour keyword: " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c1(String str) throws k {
        if (this.f940c) {
            return;
        }
        if (this.f941e) {
            if (this.f943g == null) {
                this.f943g = new StringBuilder(str.length());
            }
            this.f943g.append(str);
        } else if (this.f944h) {
            if (this.f945i == null) {
                this.f945i = new StringBuilder(str.length());
            }
            this.f945i.append(str);
        } else if (this.b instanceof h.y0) {
            h(str);
        }
    }

    private static h.o0 d0(String str) {
        str.hashCode();
        if (str.equals("none")) {
            return h.f.f876i;
        }
        if (!str.equals("currentColor")) {
            try {
                return b0(str);
            } catch (k unused) {
                return null;
            }
        }
        return h.g.a();
    }

    private void d1(Attributes attributes) throws k {
        l("<text>", new Object[0]);
        if (this.b != null) {
            h.w0 w0Var = new h.w0();
            w0Var.a = this.a;
            w0Var.b = this.b;
            D(w0Var, attributes);
            S(w0Var, attributes);
            W(w0Var, attributes);
            C(w0Var, attributes);
            V(w0Var, attributes);
            this.b.h(w0Var);
            this.b = w0Var;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static h.e0.a e0(String str) {
        if ("nonzero".equals(str)) {
            return h.e0.a.NonZero;
        }
        if ("evenodd".equals(str)) {
            return h.e0.a.EvenOdd;
        }
        return null;
    }

    private void e1(char[] cArr, int i2, int i3) throws k {
        if (this.f940c) {
            return;
        }
        if (this.f941e) {
            if (this.f943g == null) {
                this.f943g = new StringBuilder(i3);
            }
            this.f943g.append(cArr, i2, i3);
        } else if (this.f944h) {
            if (this.f945i == null) {
                this.f945i = new StringBuilder(i3);
            }
            this.f945i.append(cArr, i2, i3);
        } else if (this.b instanceof h.y0) {
            h(new String(cArr, i2, i3));
        }
    }

    private static float f0(String str) throws k {
        int length = str.length();
        if (length != 0) {
            return g0(str, 0, length);
        }
        throw new k("Invalid float value (empty string)");
    }

    private void f1(Attributes attributes) throws k {
        l("<textPath>", new Object[0]);
        if (this.b != null) {
            h.z0 z0Var = new h.z0();
            z0Var.a = this.a;
            z0Var.b = this.b;
            D(z0Var, attributes);
            S(z0Var, attributes);
            C(z0Var, attributes);
            U(z0Var, attributes);
            this.b.h(z0Var);
            this.b = z0Var;
            h.j0 j0Var = z0Var.b;
            if (j0Var instanceof h.b1) {
                z0Var.o((h.b1) j0Var);
                return;
            } else {
                z0Var.o(((h.x0) j0Var).d());
                return;
            }
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static float g0(String str, int i2, int i3) throws k {
        float b2 = new com.caverock.androidsvg.e().b(str, i2, i3);
        if (Float.isNaN(b2)) {
            throw new k("Invalid float value: " + str);
        }
        return b2;
    }

    private void g1(Attributes attributes) throws k {
        l("<tref>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            if (j0Var instanceof h.y0) {
                h.u0 u0Var = new h.u0();
                u0Var.a = this.a;
                u0Var.b = this.b;
                D(u0Var, attributes);
                S(u0Var, attributes);
                C(u0Var, attributes);
                T(u0Var, attributes);
                this.b.h(u0Var);
                h.j0 j0Var2 = u0Var.b;
                if (j0Var2 instanceof h.b1) {
                    u0Var.o((h.b1) j0Var2);
                    return;
                } else {
                    u0Var.o(((h.x0) j0Var2).d());
                    return;
                }
            }
            throw new k("Invalid document. <tref> elements are only valid inside <text> or <tspan> elements.");
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void h(String str) throws k {
        h.h0 h0Var = (h.h0) this.b;
        int size = h0Var.f879i.size();
        h.n0 n0Var = size == 0 ? null : h0Var.f879i.get(size - 1);
        if (n0Var instanceof h.c1) {
            StringBuilder sb = new StringBuilder();
            h.c1 c1Var = (h.c1) n0Var;
            sb.append(c1Var.f855c);
            sb.append(str);
            c1Var.f855c = sb.toString();
            return;
        }
        this.b.h(new h.c1(str));
    }

    private static void h0(h.e0 e0Var, String str) {
        String s;
        if ("|caption|icon|menu|message-box|small-caption|status-bar|".contains('|' + str + '|')) {
            i iVar = new i(str);
            Integer num = null;
            h.e0.b bVar = null;
            String str2 = null;
            while (true) {
                s = iVar.s('/');
                iVar.A();
                if (s != null) {
                    if (num != null && bVar != null) {
                        break;
                    } else if (!s.equals("normal") && (num != null || (num = e.a(s)) == null)) {
                        if (bVar != null || (bVar = k0(s)) == null) {
                            if (str2 != null || !s.equals("small-caps")) {
                                break;
                            }
                            str2 = s;
                        }
                    }
                } else {
                    return;
                }
            }
            h.p j0 = j0(s);
            if (iVar.f('/')) {
                iVar.A();
                String r = iVar.r();
                if (r != null) {
                    try {
                        o0(r);
                    } catch (k unused) {
                        return;
                    }
                }
                iVar.A();
            }
            e0Var.u = i0(iVar.y());
            e0Var.v = j0;
            e0Var.w = Integer.valueOf(num == null ? 400 : num.intValue());
            if (bVar == null) {
                bVar = h.e0.b.Normal;
            }
            e0Var.x = bVar;
            e0Var.f858g |= 122880;
        }
    }

    private void h1(Attributes attributes) throws k {
        l("<tspan>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            if (j0Var instanceof h.y0) {
                h.v0 v0Var = new h.v0();
                v0Var.a = this.a;
                v0Var.b = this.b;
                D(v0Var, attributes);
                S(v0Var, attributes);
                C(v0Var, attributes);
                V(v0Var, attributes);
                this.b.h(v0Var);
                this.b = v0Var;
                h.j0 j0Var2 = v0Var.b;
                if (j0Var2 instanceof h.b1) {
                    v0Var.o((h.b1) j0Var2);
                    return;
                } else {
                    v0Var.o(((h.x0) j0Var2).d());
                    return;
                }
            }
            throw new k("Invalid document. <tspan> elements are only valid inside <text> or other <tspan> elements.");
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void i(Attributes attributes) throws k {
        l("<circle>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            h.d dVar = new h.d();
            dVar.a = this.a;
            dVar.b = j0Var;
            D(dVar, attributes);
            S(dVar, attributes);
            W(dVar, attributes);
            C(dVar, attributes);
            A(dVar, attributes);
            this.b.h(dVar);
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static List<String> i0(String str) {
        i iVar = new i(str);
        ArrayList arrayList = null;
        do {
            String q = iVar.q();
            if (q == null) {
                q = iVar.u(',');
            }
            if (q == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(q);
            iVar.z();
        } while (!iVar.h());
        return arrayList;
    }

    private void i1(Attributes attributes) throws k {
        l("<use>", new Object[0]);
        if (this.b != null) {
            h.e1 e1Var = new h.e1();
            e1Var.a = this.a;
            e1Var.b = this.b;
            D(e1Var, attributes);
            S(e1Var, attributes);
            W(e1Var, attributes);
            C(e1Var, attributes);
            X(e1Var, attributes);
            this.b.h(e1Var);
            this.b = e1Var;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static int j(float f2) {
        if (f2 < 0.0f) {
            return 0;
        }
        if (f2 > 255.0f) {
            return 255;
        }
        return Math.round(f2);
    }

    private static h.p j0(String str) {
        try {
            h.p a2 = d.a(str);
            return a2 == null ? o0(str) : a2;
        } catch (k unused) {
            return null;
        }
    }

    private void j1(Attributes attributes) throws k {
        l("<view>", new Object[0]);
        if (this.b != null) {
            h.r0 f1Var = new h.f1();
            f1Var.a = this.a;
            f1Var.b = this.b;
            D(f1Var, attributes);
            C(f1Var, attributes);
            Y(f1Var, attributes);
            this.b.h(f1Var);
            this.b = f1Var;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void k(Attributes attributes) throws k {
        l("<clipPath>", new Object[0]);
        if (this.b != null) {
            h.e eVar = new h.e();
            eVar.a = this.a;
            eVar.b = this.b;
            D(eVar, attributes);
            S(eVar, attributes);
            W(eVar, attributes);
            C(eVar, attributes);
            B(eVar, attributes);
            this.b.h(eVar);
            this.b = eVar;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static h.e0.b k0(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1657669071:
                if (str.equals("oblique")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1178781136:
                if (str.equals("italic")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1039745817:
                if (str.equals("normal")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return h.e0.b.Oblique;
            case 1:
                return h.e0.b.Italic;
            case 2:
                return h.e0.b.Normal;
            default:
                return null;
        }
    }

    private void k1(Attributes attributes) throws k {
        l("<switch>", new Object[0]);
        if (this.b != null) {
            h.s0 s0Var = new h.s0();
            s0Var.a = this.a;
            s0Var.b = this.b;
            D(s0Var, attributes);
            S(s0Var, attributes);
            W(s0Var, attributes);
            C(s0Var, attributes);
            this.b.h(s0Var);
            this.b = s0Var;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private void l(String str, Object... objArr) {
    }

    private static Integer l0(String str) {
        return e.a(str);
    }

    private void m(Attributes attributes) throws k {
        l("<defs>", new Object[0]);
        if (this.b != null) {
            h.C0014h c0014h = new h.C0014h();
            c0014h.a = this.a;
            c0014h.b = this.b;
            D(c0014h, attributes);
            S(c0014h, attributes);
            W(c0014h, attributes);
            this.b.h(c0014h);
            this.b = c0014h;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static String m0(String str, String str2) {
        if (!str.equals("none") && str.startsWith("url(")) {
            if (str.endsWith(")")) {
                return str.substring(4, str.length() - 1).trim();
            }
            return str.substring(4).trim();
        }
        return null;
    }

    private void n(Attributes attributes) throws k {
        l("<ellipse>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            h.i iVar = new h.i();
            iVar.a = this.a;
            iVar.b = j0Var;
            D(iVar, attributes);
            S(iVar, attributes);
            W(iVar, attributes);
            C(iVar, attributes);
            E(iVar, attributes);
            this.b.h(iVar);
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private Float n0(String str) throws k {
        if (str.length() != 0) {
            int length = str.length();
            boolean z = true;
            if (str.charAt(str.length() - 1) == '%') {
                length--;
            } else {
                z = false;
            }
            try {
                float g0 = g0(str, 0, length);
                float f2 = 100.0f;
                if (z) {
                    g0 /= 100.0f;
                }
                if (g0 < 0.0f) {
                    f2 = 0.0f;
                } else if (g0 <= 100.0f) {
                    f2 = g0;
                }
                return Float.valueOf(f2);
            } catch (NumberFormatException e2) {
                throw new k("Invalid offset value in <stop>: " + str, e2);
            }
        }
        throw new k("Invalid offset value in <stop> (empty string)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
    }

    static h.p o0(String str) throws k {
        if (str.length() != 0) {
            int length = str.length();
            h.d1 d1Var = h.d1.px;
            char charAt = str.charAt(length - 1);
            if (charAt == '%') {
                length--;
                d1Var = h.d1.percent;
            } else if (length > 2 && Character.isLetter(charAt) && Character.isLetter(str.charAt(length - 2))) {
                length -= 2;
                try {
                    d1Var = h.d1.valueOf(str.substring(length).toLowerCase(Locale.US));
                } catch (IllegalArgumentException unused) {
                    throw new k("Invalid length unit specifier: " + str);
                }
            }
            try {
                return new h.p(g0(str, 0, length), d1Var);
            } catch (NumberFormatException e2) {
                throw new k("Invalid length value: " + str, e2);
            }
        }
        throw new k("Invalid length value (empty string)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(String str, String str2, String str3) throws k {
        if (this.f940c) {
            int i2 = this.d - 1;
            this.d = i2;
            if (i2 == 0) {
                this.f940c = false;
                return;
            }
        }
        if ("http://www.w3.org/2000/svg".equals(str) || "".equals(str)) {
            if (str2.length() <= 0) {
                str2 = str3;
            }
            int i3 = a.a[h.fromString(str2).ordinal()];
            if (i3 != 1 && i3 != 2 && i3 != 4 && i3 != 5 && i3 != 13 && i3 != 14) {
                switch (i3) {
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 31:
                        break;
                    case 22:
                    case 23:
                        this.f941e = false;
                        StringBuilder sb = this.f943g;
                        if (sb != null) {
                            h hVar = this.f942f;
                            if (hVar == h.title) {
                                this.a.t(sb.toString());
                            } else if (hVar == h.desc) {
                                this.a.r(sb.toString());
                            }
                            this.f943g.setLength(0);
                            return;
                        }
                        return;
                    case 30:
                        StringBuilder sb2 = this.f945i;
                        if (sb2 != null) {
                            this.f944h = false;
                            Z(sb2.toString());
                            this.f945i.setLength(0);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
            this.b = ((h.n0) this.b).b;
        }
    }

    private static List<h.p> p0(String str) throws k {
        if (str.length() != 0) {
            ArrayList arrayList = new ArrayList(1);
            i iVar = new i(str);
            iVar.A();
            while (!iVar.h()) {
                float n2 = iVar.n();
                if (!Float.isNaN(n2)) {
                    h.d1 v = iVar.v();
                    if (v == null) {
                        v = h.d1.px;
                    }
                    arrayList.add(new h.p(n2, v));
                    iVar.z();
                } else {
                    throw new k("Invalid length list value: " + iVar.b());
                }
            }
            return arrayList;
        }
        throw new k("Invalid length list (empty string)");
    }

    private void q(Attributes attributes) throws k {
        l("<g>", new Object[0]);
        if (this.b != null) {
            h.m mVar = new h.m();
            mVar.a = this.a;
            mVar.b = this.b;
            D(mVar, attributes);
            S(mVar, attributes);
            W(mVar, attributes);
            C(mVar, attributes);
            this.b.h(mVar);
            this.b = mVar;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static h.p q0(i iVar) {
        if (iVar.g("auto")) {
            return new h.p(0.0f);
        }
        return iVar.p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(String str, Map<String, String> map) {
        String str2;
        if (!str.equals("xml-stylesheet") || com.caverock.androidsvg.h.g() == null) {
            return;
        }
        if (map.get("type") == null || "text/css".equals(map.get("type"))) {
            if ((map.get("alternate") == null || "no".equals(map.get("alternate"))) && (str2 = map.get("href")) != null) {
                com.caverock.androidsvg.h.g().b(str2);
                throw null;
            }
        }
    }

    private static Float r0(String str) {
        try {
            float f0 = f0(str);
            if (f0 < 0.0f) {
                f0 = 0.0f;
            } else if (f0 > 1.0f) {
                f0 = 1.0f;
            }
            return Float.valueOf(f0);
        } catch (k unused) {
            return null;
        }
    }

    private static int s(float f2, float f3, float f4) {
        int i2 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
        float f5 = f2 % 360.0f;
        if (i2 < 0) {
            f5 += 360.0f;
        }
        float f6 = f5 / 60.0f;
        float f7 = f3 / 100.0f;
        float f8 = f4 / 100.0f;
        if (f7 < 0.0f) {
            f7 = 0.0f;
        } else if (f7 > 1.0f) {
            f7 = 1.0f;
        }
        float f9 = f8 >= 0.0f ? f8 > 1.0f ? 1.0f : f8 : 0.0f;
        float f10 = f9 <= 0.5f ? (f7 + 1.0f) * f9 : (f9 + f7) - (f7 * f9);
        float f11 = (f9 * 2.0f) - f10;
        return j(t(f11, f10, f6 - 2.0f) * 256.0f) | (j(t(f11, f10, f6 + 2.0f) * 256.0f) << 16) | (j(t(f11, f10, f6) * 256.0f) << 8);
    }

    private static Boolean s0(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1217487446:
                if (str.equals(ViewProps.HIDDEN)) {
                    c2 = 0;
                    break;
                }
                break;
            case -907680051:
                if (str.equals("scroll")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    c2 = 2;
                    break;
                }
                break;
            case 466743410:
                if (str.equals(ViewProps.VISIBLE)) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
                return Boolean.FALSE;
            case 2:
            case 3:
                return Boolean.TRUE;
            default:
                return null;
        }
    }

    private static float t(float f2, float f3, float f4) {
        float f5;
        if (f4 < 0.0f) {
            f4 += 6.0f;
        }
        if (f4 >= 6.0f) {
            f4 -= 6.0f;
        }
        if (f4 < 1.0f) {
            f5 = (f3 - f2) * f4;
        } else if (f4 < 3.0f) {
            return f3;
        } else {
            if (f4 >= 4.0f) {
                return f2;
            }
            f5 = (f3 - f2) * (4.0f - f4);
        }
        return f5 + f2;
    }

    private static h.o0 t0(String str) {
        if (str.startsWith("url(")) {
            int indexOf = str.indexOf(")");
            if (indexOf != -1) {
                String trim = str.substring(4, indexOf).trim();
                String trim2 = str.substring(indexOf + 1).trim();
                return new h.u(trim, trim2.length() > 0 ? d0(trim2) : null);
            }
            return new h.u(str.substring(4).trim(), null);
        }
        return d0(str);
    }

    private void u(Attributes attributes) throws k {
        l("<image>", new Object[0]);
        if (this.b != null) {
            h.o oVar = new h.o();
            oVar.a = this.a;
            oVar.b = this.b;
            D(oVar, attributes);
            S(oVar, attributes);
            W(oVar, attributes);
            C(oVar, attributes);
            G(oVar, attributes);
            this.b.h(oVar);
            this.b = oVar;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x0283, code lost:
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static h.w u0(String str) {
        float d2;
        float d3;
        float f2;
        float f3;
        i iVar = new i(str);
        h.w wVar = new h.w();
        if (iVar.h()) {
            return wVar;
        }
        int intValue = iVar.l().intValue();
        int i2 = 109;
        if (intValue != 77 && intValue != 109) {
            return wVar;
        }
        int i3 = intValue;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        while (true) {
            iVar.A();
            switch (i3) {
                case 65:
                case 97:
                    float n2 = iVar.n();
                    float d4 = iVar.d(n2);
                    float d5 = iVar.d(d4);
                    Boolean c2 = iVar.c(Float.valueOf(d5));
                    Boolean c3 = iVar.c(c2);
                    float e2 = iVar.e(c3);
                    float d6 = iVar.d(e2);
                    if (!Float.isNaN(d6) && n2 >= 0.0f && d4 >= 0.0f) {
                        if (i3 == 97) {
                            e2 += f4;
                            d6 += f6;
                        }
                        wVar.d(n2, d4, d5, c2.booleanValue(), c3.booleanValue(), e2, d6);
                        f4 = e2;
                        f5 = f4;
                        f6 = d6;
                        f7 = f6;
                        break;
                    }
                    break;
                case 67:
                case 99:
                    float n3 = iVar.n();
                    float d7 = iVar.d(n3);
                    float d8 = iVar.d(d7);
                    float d9 = iVar.d(d8);
                    d2 = iVar.d(d9);
                    d3 = iVar.d(d2);
                    if (Float.isNaN(d3)) {
                        String str2 = "Bad path coords for " + ((char) i3) + " path segment";
                        return wVar;
                    }
                    if (i3 == 99) {
                        d2 += f4;
                        d3 += f6;
                        n3 += f4;
                        d7 += f6;
                        d8 += f4;
                        d9 += f6;
                    }
                    f2 = d8;
                    f3 = d9;
                    wVar.c(n3, d7, f2, f3, d2, d3);
                    f5 = f2;
                    f4 = d2;
                    f6 = d3;
                    f7 = f3;
                    break;
                case 72:
                case 104:
                    float n4 = iVar.n();
                    if (Float.isNaN(n4)) {
                        String str3 = "Bad path coords for " + ((char) i3) + " path segment";
                        return wVar;
                    }
                    if (i3 == 104) {
                        n4 += f4;
                    }
                    f4 = n4;
                    wVar.e(f4, f6);
                    f5 = f4;
                    break;
                case 76:
                case 108:
                    float n5 = iVar.n();
                    float d10 = iVar.d(n5);
                    if (Float.isNaN(d10)) {
                        String str4 = "Bad path coords for " + ((char) i3) + " path segment";
                        return wVar;
                    }
                    if (i3 == 108) {
                        n5 += f4;
                        d10 += f6;
                    }
                    f4 = n5;
                    f6 = d10;
                    wVar.e(f4, f6);
                    f5 = f4;
                    f7 = f6;
                    break;
                case 77:
                case 109:
                    float n6 = iVar.n();
                    float d11 = iVar.d(n6);
                    if (Float.isNaN(d11)) {
                        String str5 = "Bad path coords for " + ((char) i3) + " path segment";
                        return wVar;
                    }
                    if (i3 == i2 && !wVar.i()) {
                        n6 += f4;
                        d11 += f6;
                    }
                    f4 = n6;
                    f6 = d11;
                    wVar.b(f4, f6);
                    f5 = f4;
                    f8 = f5;
                    f7 = f6;
                    f9 = f7;
                    i3 = i3 != i2 ? 76 : 108;
                    break;
                    break;
                case 81:
                case 113:
                    f5 = iVar.n();
                    f7 = iVar.d(f5);
                    float d12 = iVar.d(f7);
                    float d13 = iVar.d(d12);
                    if (Float.isNaN(d13)) {
                        String str6 = "Bad path coords for " + ((char) i3) + " path segment";
                        return wVar;
                    }
                    if (i3 == 113) {
                        d12 += f4;
                        d13 += f6;
                        f5 += f4;
                        f7 += f6;
                    }
                    f4 = d12;
                    f6 = d13;
                    wVar.a(f5, f7, f4, f6);
                    break;
                case 83:
                case 115:
                    float f10 = (f4 * 2.0f) - f5;
                    float f11 = (2.0f * f6) - f7;
                    float n7 = iVar.n();
                    float d14 = iVar.d(n7);
                    d2 = iVar.d(d14);
                    d3 = iVar.d(d2);
                    if (Float.isNaN(d3)) {
                        String str7 = "Bad path coords for " + ((char) i3) + " path segment";
                        return wVar;
                    }
                    if (i3 == 115) {
                        d2 += f4;
                        d3 += f6;
                        n7 += f4;
                        d14 += f6;
                    }
                    f2 = n7;
                    f3 = d14;
                    wVar.c(f10, f11, f2, f3, d2, d3);
                    f5 = f2;
                    f4 = d2;
                    f6 = d3;
                    f7 = f3;
                    break;
                case 84:
                case 116:
                    f5 = (f4 * 2.0f) - f5;
                    f7 = (2.0f * f6) - f7;
                    float n8 = iVar.n();
                    float d15 = iVar.d(n8);
                    if (Float.isNaN(d15)) {
                        String str8 = "Bad path coords for " + ((char) i3) + " path segment";
                        return wVar;
                    }
                    if (i3 == 116) {
                        n8 += f4;
                        d15 += f6;
                    }
                    f4 = n8;
                    f6 = d15;
                    wVar.a(f5, f7, f4, f6);
                    break;
                case 86:
                case 118:
                    float n9 = iVar.n();
                    if (Float.isNaN(n9)) {
                        String str9 = "Bad path coords for " + ((char) i3) + " path segment";
                        return wVar;
                    }
                    if (i3 == 118) {
                        n9 += f6;
                    }
                    f6 = n9;
                    wVar.e(f4, f6);
                    f7 = f6;
                    break;
                case 90:
                case 122:
                    wVar.close();
                    f4 = f8;
                    f5 = f4;
                    f6 = f9;
                    f7 = f6;
                    break;
                default:
                    return wVar;
            }
            iVar.z();
            if (iVar.h()) {
                return wVar;
            }
            if (iVar.i()) {
                i3 = iVar.l().intValue();
            }
            i2 = 109;
        }
    }

    private void v(Attributes attributes) throws k {
        l("<line>", new Object[0]);
        h.j0 j0Var = this.b;
        if (j0Var != null) {
            h.q qVar = new h.q();
            qVar.a = this.a;
            qVar.b = j0Var;
            D(qVar, attributes);
            S(qVar, attributes);
            W(qVar, attributes);
            C(qVar, attributes);
            H(qVar, attributes);
            this.b.h(qVar);
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    static com.caverock.androidsvg.f v0(String str) throws k {
        i iVar = new i(str);
        iVar.A();
        String r = iVar.r();
        if ("defer".equals(r)) {
            iVar.A();
            r = iVar.r();
        }
        f.a a2 = b.a(r);
        f.b bVar = null;
        iVar.A();
        if (!iVar.h()) {
            String r2 = iVar.r();
            r2.hashCode();
            if (r2.equals("meet")) {
                bVar = f.b.meet;
            } else if (!r2.equals("slice")) {
                throw new k("Invalid preserveAspectRatio definition: " + str);
            } else {
                bVar = f.b.slice;
            }
        }
        return new com.caverock.androidsvg.f(a2, bVar);
    }

    private void w(Attributes attributes) throws k {
        l("<linearGradient>", new Object[0]);
        if (this.b != null) {
            h.m0 m0Var = new h.m0();
            m0Var.a = this.a;
            m0Var.b = this.b;
            D(m0Var, attributes);
            S(m0Var, attributes);
            F(m0Var, attributes);
            I(m0Var, attributes);
            this.b.h(m0Var);
            this.b = m0Var;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static void w0(h.p0 p0Var, String str) throws k {
        p0Var.f906n = v0(str);
    }

    private void x(Attributes attributes) throws k {
        l("<marker>", new Object[0]);
        if (this.b != null) {
            h.r rVar = new h.r();
            rVar.a = this.a;
            rVar.b = this.b;
            D(rVar, attributes);
            S(rVar, attributes);
            C(rVar, attributes);
            Y(rVar, attributes);
            J(rVar, attributes);
            this.b.h(rVar);
            this.b = rVar;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> x0(i iVar) {
        HashMap hashMap = new HashMap();
        iVar.A();
        String s = iVar.s('=');
        while (s != null) {
            iVar.f('=');
            hashMap.put(s, iVar.q());
            iVar.A();
            s = iVar.s('=');
        }
        return hashMap;
    }

    private void y(Attributes attributes) throws k {
        l("<mask>", new Object[0]);
        if (this.b != null) {
            h.s sVar = new h.s();
            sVar.a = this.a;
            sVar.b = this.b;
            D(sVar, attributes);
            S(sVar, attributes);
            C(sVar, attributes);
            K(sVar, attributes);
            this.b.h(sVar);
            this.b = sVar;
            return;
        }
        throw new k("Invalid document. Root element must be <svg>");
    }

    private static h.e0.e y0(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -933002398:
                if (str.equals("optimizeQuality")) {
                    c2 = 0;
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    c2 = 1;
                    break;
                }
                break;
            case 362741610:
                if (str.equals("optimizeSpeed")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return h.e0.e.optimizeQuality;
            case 1:
                return h.e0.e.auto;
            case 2:
                return h.e0.e.optimizeSpeed;
            default:
                return null;
        }
    }

    private static Set<String> z0(String str) {
        i iVar = new i(str);
        HashSet hashSet = new HashSet();
        while (!iVar.h()) {
            String r = iVar.r();
            if (r.startsWith("http://www.w3.org/TR/SVG11/feature#")) {
                hashSet.add(r.substring(35));
            } else {
                hashSet.add("UNSUPPORTED");
            }
            iVar.A();
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.caverock.androidsvg.h z(InputStream inputStream, boolean z) throws k {
        if (!inputStream.markSupported()) {
            inputStream = new BufferedInputStream(inputStream);
        }
        try {
            inputStream.mark(3);
            int read = inputStream.read() + (inputStream.read() << 8);
            inputStream.reset();
            if (read == 35615) {
                inputStream = new BufferedInputStream(new GZIPInputStream(inputStream));
            }
        } catch (IOException unused) {
        }
        try {
            inputStream.mark(4096);
            L0(inputStream, z);
            return this.a;
        } finally {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
        }
    }
}
