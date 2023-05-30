package com.jd.dynamic.a.a.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IDynamicDark;
import com.jd.dynamic.base.interfaces.IUniConfig;
import com.jd.dynamic.base.interfaces.IUniConfigWithAdapter;
import com.jd.dynamic.lib.dynamic.parser.DynamicXParser;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.connect.common.Constants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class a implements h {
    public static final C0066a b = new C0066a(null);
    private final DynamicTemplateEngine a;

    /* renamed from: com.jd.dynamic.a.a.a.a$a */
    /* loaded from: classes13.dex */
    public static final class C0066a {
        private C0066a() {
        }

        public /* synthetic */ C0066a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Object a(@Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable Object obj) {
            double d;
            String fontSizeLevel;
            String str;
            Object obj2 = "";
            if (obj instanceof String) {
                if (Intrinsics.areEqual(obj, "deviceType")) {
                    obj2 = BaseInfo.getDeviceModel();
                } else if (Intrinsics.areEqual(obj, HybridSDK.OS_VERSION)) {
                    obj2 = Build.VERSION.RELEASE;
                } else if (Intrinsics.areEqual(obj, Constants.PARAM_PLATFORM)) {
                    obj2 = "android";
                } else if (!Intrinsics.areEqual(obj, "appVersion")) {
                    if (Intrinsics.areEqual(obj, "currentMode")) {
                        DynamicSdk.Engine engine = DynamicSdk.getEngine();
                        Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
                        IUniConfig uniConfig = engine.getUniConfig();
                        if (!(uniConfig instanceof IUniConfigWithAdapter)) {
                            return "";
                        }
                        fontSizeLevel = ((IUniConfigWithAdapter) uniConfig).getThemeMode();
                        str = "config.themeMode";
                    } else if (Intrinsics.areEqual(obj, "fontSizeLevel")) {
                        DynamicSdk.Engine engine2 = DynamicSdk.getEngine();
                        Intrinsics.checkExpressionValueIsNotNull(engine2, "DynamicSdk.getEngine()");
                        IUniConfig uniConfig2 = engine2.getUniConfig();
                        if (!(uniConfig2 instanceof IUniConfigWithAdapter)) {
                            return "";
                        }
                        fontSizeLevel = ((IUniConfigWithAdapter) uniConfig2).getFontSizeLevel();
                        str = "config.fontSizeLevel";
                    } else if (Intrinsics.areEqual(obj, "darkMode")) {
                        DynamicSdk.Engine engine3 = DynamicSdk.getEngine();
                        Intrinsics.checkExpressionValueIsNotNull(engine3, "DynamicSdk.getEngine()");
                        IDynamicDark dynamicDark = engine3.getDynamicDark();
                        Intrinsics.checkExpressionValueIsNotNull(dynamicDark, "DynamicSdk.getEngine().dynamicDark");
                        obj2 = Boolean.valueOf(dynamicDark.isDarkMode());
                    } else {
                        if (Intrinsics.areEqual(obj, "screenWidth")) {
                            DynamicXParser.updateWAndH(dynamicTemplateEngine != null ? dynamicTemplateEngine.getActivity() : null);
                            d = DynamicXParser.getWidth();
                        } else if (Intrinsics.areEqual(obj, "screenHeight")) {
                            DynamicXParser.updateWAndH(dynamicTemplateEngine != null ? dynamicTemplateEngine.getActivity() : null);
                            d = DynamicXParser.height;
                        } else if (Intrinsics.areEqual(obj, "statusBarHeight")) {
                            DynamicXParser.updateWAndH(dynamicTemplateEngine != null ? dynamicTemplateEngine.getActivity() : null);
                            d = DynamicXParser.statusBarHeight;
                        } else if (Intrinsics.areEqual(obj, "adaptScale")) {
                            obj2 = 1;
                        } else if (Intrinsics.areEqual(obj, "safeAreaBottom")) {
                            obj2 = 0;
                        }
                        obj2 = Double.valueOf(d);
                    }
                    Intrinsics.checkExpressionValueIsNotNull(fontSizeLevel, str);
                    return fontSizeLevel;
                } else {
                    DynamicSdk.Engine engine4 = DynamicSdk.getEngine();
                    Intrinsics.checkExpressionValueIsNotNull(engine4, "DynamicSdk.getEngine()");
                    Context context = engine4.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "DynamicSdk.getEngine().context");
                    PackageManager packageManager = context.getPackageManager();
                    DynamicSdk.Engine engine5 = DynamicSdk.getEngine();
                    Intrinsics.checkExpressionValueIsNotNull(engine5, "DynamicSdk.getEngine()");
                    Context context2 = engine5.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context2, "DynamicSdk.getEngine().context");
                    obj2 = packageManager.getPackageInfo(context2.getPackageName(), 0).versionName;
                }
                Intrinsics.checkExpressionValueIsNotNull(obj2, "when (inputType) {\n     \u2026Y_EMPTY_STR\n            }");
                return obj2;
            }
            return "";
        }
    }

    public a(@Nullable DynamicTemplateEngine dynamicTemplateEngine) {
        this.a = dynamicTemplateEngine;
    }

    @Override // com.jd.dynamic.a.a.a.h
    @NotNull
    public Object a(@Nullable com.jd.dynamic.a.g gVar, @Nullable String str, @NotNull Object... objArr) {
        DYConstants.DYLog("method name is " + str + " params is " + objArr);
        return objArr.length == 0 ? "" : b.a(this.a, objArr[0]);
    }

    @Override // com.jd.dynamic.a.a.a.h
    @NotNull
    public String a() {
        return "appContext";
    }
}
