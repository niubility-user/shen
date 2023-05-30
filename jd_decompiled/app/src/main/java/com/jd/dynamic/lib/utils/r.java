package com.jd.dynamic.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.entity.MtaTimePair;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser;
import com.jd.dynamic.lib.viewparse.BaseFrameLayout;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class r {
    private final String a = "DynamicData";
    private final String b = "Events";

    /* renamed from: c  reason: collision with root package name */
    private FrameLayout f2296c;
    private DynamicTemplateEngine d;

    /* renamed from: e  reason: collision with root package name */
    private DYContainerConfig f2297e;

    private final BaseFrameLayout a(Context context, ViewNode viewNode, DynamicTemplateEngine dynamicTemplateEngine, ViewNode viewNode2) {
        BaseFrameLayout baseFrameLayout = new BaseFrameLayout(context, viewNode, dynamicTemplateEngine, false);
        if (viewNode2 != null) {
            s.e(dynamicTemplateEngine, viewNode2);
        }
        return baseFrameLayout;
    }

    private final void b(Context context, ViewNode viewNode, String str) {
        ViewNode viewNode2;
        if (viewNode == null) {
            DYConstants.DYLog("fast  error rootNode is null !!!!");
            return;
        }
        DYConstants.DYLog("doInitFast new enter v2 !!!!");
        ViewNode viewNode3 = null;
        if (Intrinsics.areEqual(this.a, viewNode.getViewName())) {
            viewNode3 = viewNode.getChildByName(DYConstants.DY_FLEXBOX_LAYOUT);
            viewNode2 = viewNode.getChildByName(this.b);
        } else {
            viewNode2 = null;
        }
        if (viewNode3 != null) {
            viewNode = viewNode3;
        }
        MtaTimePair newInstance = MtaTimePair.newInstance();
        newInstance.startRecord();
        a(context, viewNode, this.d, viewNode2);
        newInstance.endRecord();
        DynamicMtaUtil.appendRenderMtaStat(str, newInstance);
    }

    private final void d(ViewNode viewNode, String str, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        DYConstants.DYLog("fake showViewNode start time :" + currentTimeMillis + " time is " + j2 + " hhh: " + (currentTimeMillis - j2));
        if (this.d == null) {
            DYContainerConfig dYContainerConfig = this.f2297e;
            Context context = dYContainerConfig != null ? dYContainerConfig.getContext() : null;
            if (!(context instanceof Activity)) {
                context = null;
            }
            this.d = new DynamicTemplateEngine("", (Activity) context, this.f2296c, null);
        }
        DynamicTemplateEngine dynamicTemplateEngine = this.d;
        if (dynamicTemplateEngine != null) {
            dynamicTemplateEngine.shouldAutoListenDarkStatus(true);
        }
        try {
            DYContainerConfig dYContainerConfig2 = this.f2297e;
            b(dYContainerConfig2 != null ? dYContainerConfig2.getContext() : null, viewNode, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            DYConstants.DYLog("fast error!!!!");
        }
        DynamicTemplateEngine dynamicTemplateEngine2 = this.d;
        if (dynamicTemplateEngine2 != null) {
            dynamicTemplateEngine2.release();
        }
        DYConstants.DYLog("fake showViewNode end time : " + (System.currentTimeMillis() - j2));
    }

    private final boolean f(ViewNode viewNode) {
        return (viewNode == null || TextUtils.isEmpty(viewNode.getViewName())) ? false : true;
    }

    private final boolean g() {
        return this.f2297e == null;
    }

    public final void c(@NotNull DYContainerConfig dYContainerConfig) {
        this.f2297e = dYContainerConfig;
    }

    public final boolean e() {
        if (g()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("fake enter load.... load time ");
        sb.append(currentTimeMillis);
        sb.append(" module: ");
        DYContainerConfig dYContainerConfig = this.f2297e;
        sb.append(dYContainerConfig != null ? dYContainerConfig.getModule() : null);
        sb.append(" template: ");
        DYContainerConfig dYContainerConfig2 = this.f2297e;
        sb.append(dYContainerConfig2 != null ? dYContainerConfig2.getTemplateId() : null);
        DYConstants.DYLog(sb.toString());
        DYContainerConfig dYContainerConfig3 = this.f2297e;
        String module = dYContainerConfig3 != null ? dYContainerConfig3.getModule() : null;
        DYContainerConfig dYContainerConfig4 = this.f2297e;
        if (!com.jd.dynamic.b.e.a.b.t(module, dYContainerConfig4 != null ? dYContainerConfig4.getTemplateId() : null)) {
            DYContainerConfig dYContainerConfig5 = this.f2297e;
            String module2 = dYContainerConfig5 != null ? dYContainerConfig5.getModule() : null;
            DYContainerConfig dYContainerConfig6 = this.f2297e;
            if (com.jd.dynamic.b.e.a.b.w(module2, dYContainerConfig6 != null ? dYContainerConfig6.getTemplateId() : null)) {
                DynamicSdk.Engine engine = DynamicSdk.getEngine();
                Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
                String appType = engine.getAppType();
                DYContainerConfig dYContainerConfig7 = this.f2297e;
                String module3 = dYContainerConfig7 != null ? dYContainerConfig7.getModule() : null;
                DYContainerConfig dYContainerConfig8 = this.f2297e;
                String mtaId = DynamicMtaUtil.startMtaStat(appType, module3, "", dYContainerConfig8 != null ? dYContainerConfig8.getTemplateId() : null, false);
                DYContainerConfig dYContainerConfig9 = this.f2297e;
                String module4 = dYContainerConfig9 != null ? dYContainerConfig9.getModule() : null;
                DYContainerConfig dYContainerConfig10 = this.f2297e;
                ResultEntity a = com.jd.dynamic.b.i.a.a(module4, dYContainerConfig10 != null ? dYContainerConfig10.getTemplateId() : null, R2.attr.layout_constraintHeight, "", false);
                if (m.F(a)) {
                    if (a == null) {
                        Intrinsics.throwNpe();
                    }
                    ViewNode viewNode = a.viewNode;
                    Intrinsics.checkExpressionValueIsNotNull(viewNode, "entity!!.viewNode");
                    Intrinsics.checkExpressionValueIsNotNull(mtaId, "mtaId");
                    d(viewNode, mtaId, currentTimeMillis);
                    return true;
                }
            }
            return false;
        }
        DYContainerConfig dYContainerConfig11 = this.f2297e;
        String module5 = dYContainerConfig11 != null ? dYContainerConfig11.getModule() : null;
        DYContainerConfig dYContainerConfig12 = this.f2297e;
        Template a2 = com.jd.dynamic.b.e.a.b.a(module5, dYContainerConfig12 != null ? dYContainerConfig12.getTemplateId() : null);
        if (a2 != null) {
            DynamicSdk.Engine engine2 = DynamicSdk.getEngine();
            Intrinsics.checkExpressionValueIsNotNull(engine2, "DynamicSdk.getEngine()");
            String appType2 = engine2.getAppType();
            DYContainerConfig dYContainerConfig13 = this.f2297e;
            String module6 = dYContainerConfig13 != null ? dYContainerConfig13.getModule() : null;
            DYContainerConfig dYContainerConfig14 = this.f2297e;
            String mtaId2 = DynamicMtaUtil.startMtaStat(appType2, module6, "", dYContainerConfig14 != null ? dYContainerConfig14.getTemplateId() : null, false);
            String b = p.b(a2.getDownloadUrl(), a2.getDownloadFileName());
            StringBuilder sb2 = new StringBuilder();
            DYContainerConfig dYContainerConfig15 = this.f2297e;
            sb2.append(com.jd.dynamic.b.e.a.b.m(dYContainerConfig15 != null ? dYContainerConfig15.getModule() : null));
            sb2.append(File.separator);
            sb2.append(a2.businessCode);
            File file = new File(sb2.toString(), b);
            if (file.exists() && file.isFile()) {
                String absolutePath = file.getAbsolutePath();
                DYContainerConfig dYContainerConfig16 = this.f2297e;
                String templateId = dYContainerConfig16 != null ? dYContainerConfig16.getTemplateId() : null;
                DYContainerConfig dYContainerConfig17 = this.f2297e;
                ViewNode viewNode2 = NewDynamicXParser.parseBinaryToViewNode(absolutePath, false, templateId, dYContainerConfig17 != null ? dYContainerConfig17.getModule() : null, mtaId2, false);
                if (f(viewNode2)) {
                    Intrinsics.checkExpressionValueIsNotNull(viewNode2, "viewNode");
                    Intrinsics.checkExpressionValueIsNotNull(mtaId2, "mtaId");
                    d(viewNode2, mtaId2, currentTimeMillis);
                    return true;
                }
            }
        }
        return false;
    }
}
