package com.jd.dynamic.lib.j;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.p;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class f {
    private DYContainerConfig a;
    private JSONObject b;

    /* renamed from: c  reason: collision with root package name */
    private DynamicTemplateEngine f2259c;

    public f(DYContainerConfig dYContainerConfig) {
        this.a = dYContainerConfig;
    }

    private void c(ResultEntity resultEntity, Context context) {
        DynamicTemplateEngine dynamicTemplateEngine = new DynamicTemplateEngine(TextUtils.isEmpty(this.a.getPkgName()) ? "" : this.a.getPkgName(), (Activity) context, null, null);
        this.f2259c = dynamicTemplateEngine;
        dynamicTemplateEngine.containerWidth = this.a.getContainerWidth();
        this.f2259c.containerHeight = this.a.getContainerHeight();
        DynamicTemplateEngine dynamicTemplateEngine2 = this.f2259c;
        dynamicTemplateEngine2.isAttached = false;
        dynamicTemplateEngine2.entity = resultEntity;
        dynamicTemplateEngine2.useAsyncItem = Boolean.TRUE;
        dynamicTemplateEngine2.setBizField(this.a.getTemplateId());
        this.f2259c.setSystemCode(this.a.getModule());
        this.f2259c.shouldAutoListenDarkStatus(this.a.getIsAutoListen());
        this.f2259c.preEngine();
    }

    public DynamicTemplateEngine a() {
        return this.f2259c;
    }

    public void b(Context context) {
        ResultEntity a;
        if (com.jd.dynamic.b.a.b.o().N() && com.jd.dynamic.b.e.a.b.w(this.a.getModule(), this.a.getTemplateId())) {
            a = com.jd.dynamic.b.i.a.a(this.a.getModule(), this.a.getTemplateId(), R2.attr.layout_constraintHeight, "", false);
            if (!m.F(a)) {
                return;
            }
        } else if (com.jd.dynamic.b.e.a.b.t(this.a.getModule(), this.a.getTemplateId())) {
            Template a2 = com.jd.dynamic.b.e.a.b.a(this.a.getModule(), this.a.getTemplateId());
            File file = new File(com.jd.dynamic.b.e.a.b.m(this.a.getModule()) + File.separator + a2.businessCode, p.b(a2.getDownloadUrl(), a2.getDownloadFileName()));
            if (!file.exists()) {
                return;
            }
            a = NewDynamicXParser.parseBinaryToResultEntity(file.getAbsolutePath(), false, this.a.getTemplateId(), this.a.getModule(), "");
            if (!m.F(a)) {
                return;
            }
        } else if (!com.jd.dynamic.b.e.a.b.w(this.a.getModule(), this.a.getTemplateId())) {
            return;
        } else {
            a = com.jd.dynamic.b.i.a.a(this.a.getModule(), this.a.getTemplateId(), R2.attr.layout_constraintHeight, "", false);
            if (!m.F(a)) {
                return;
            }
        }
        c(a, context);
    }

    public void d(JSONObject jSONObject) {
        this.b = jSONObject;
    }

    public JSONObject e() {
        return this.b;
    }

    public void f() {
        DynamicTemplateEngine dynamicTemplateEngine = this.f2259c;
        if (dynamicTemplateEngine != null) {
            dynamicTemplateEngine.preParseElValue(this.b);
        }
    }

    public void g() {
        DynamicTemplateEngine dynamicTemplateEngine = this.f2259c;
        if (dynamicTemplateEngine != null) {
            dynamicTemplateEngine.releaseJS();
            this.f2259c.release();
        }
    }

    public DynamicTemplateEngine h() {
        DynamicTemplateEngine dynamicTemplateEngine = this.f2259c;
        if (dynamicTemplateEngine != null) {
            dynamicTemplateEngine.returnDynamicView(dynamicTemplateEngine.entity.viewNode, "");
            return this.f2259c;
        }
        return null;
    }
}
