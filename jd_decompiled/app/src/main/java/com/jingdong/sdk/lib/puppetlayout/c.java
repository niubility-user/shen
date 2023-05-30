package com.jingdong.sdk.lib.puppetlayout;

import android.content.Context;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetManager;
import com.jingdong.sdk.lib.puppetlayout.ylayout.TemplateParser;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateModel;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class c implements com.jingdong.sdk.lib.puppetlayout.a {
    private b a = new b();
    private Map<String, com.jingdong.sdk.lib.puppetlayout.f.a> b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private TemplateParser f15196c;
    private LruCache<String, com.jingdong.sdk.lib.puppetlayout.f.a> d;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static class a {
        private static final c a = new c();
    }

    public c() {
        new HashMap();
        this.d = new LruCache<>(PuppetManager.getInstance().cacheTemplatesCount);
    }

    private void b(com.jingdong.sdk.lib.puppetlayout.f.a aVar, TemplateModel templateModel) {
        if (!PuppetManager.getInstance().cacheTemplates || aVar == null || templateModel == null || TextUtils.isEmpty(templateModel.templateId) || TextUtils.isEmpty(templateModel.version)) {
            return;
        }
        this.d.put(templateModel.templateId + CartConstant.KEY_YB_INFO_LINK + templateModel.version, aVar);
    }

    public static c d() {
        return a.a;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.a
    public com.jingdong.sdk.lib.puppetlayout.f.a a(String str) {
        return this.b.get(str);
    }

    public com.jingdong.sdk.lib.puppetlayout.f.a c(String str, String str2) {
        if (!PuppetManager.getInstance().cacheTemplates || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String str3 = str + CartConstant.KEY_YB_INFO_LINK + str2;
        com.jingdong.sdk.lib.puppetlayout.f.a aVar = this.d.get(str3);
        if (aVar == null || aVar.f15202f == -1 || System.currentTimeMillis() - aVar.f15202f <= PuppetManager.getInstance().cacheTemplatesTime) {
            return aVar;
        }
        this.d.remove(str3);
        return null;
    }

    public View e(Context context, ViewGroup viewGroup, com.jingdong.sdk.lib.puppetlayout.f.a aVar, TemplateModel templateModel, boolean z) {
        if (aVar == null) {
            if (this.f15196c == null) {
                this.f15196c = new TemplateParser(this, this.a);
            }
            aVar = this.f15196c.parseModel2ViewTree(templateModel, true);
            aVar.a = templateModel.flexibleWidth;
            aVar.b = templateModel.flexibleHeight;
            aVar.f15200c = templateModel.templateId;
            aVar.d = templateModel.version;
            aVar.f15202f = System.currentTimeMillis();
        }
        PuppetContext puppetContext = new PuppetContext(aVar.f15200c, aVar.d);
        puppetContext.context = context;
        try {
            View inflate = puppetContext.inflate(context, aVar, viewGroup, z);
            if (inflate != null) {
                b(aVar, templateModel);
                inflate.setTag(R.id.com_jd_sdk_lib_puppetlayout_tree, puppetContext);
                return inflate;
            }
            return null;
        } catch (Exception e2) {
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a && com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    public View f(Context context, ViewGroup viewGroup, com.jingdong.sdk.lib.puppetlayout.f.a aVar, boolean z) {
        if (aVar == null) {
            return null;
        }
        try {
            return e(context, viewGroup, aVar, null, z);
        } catch (Exception e2) {
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                e2.printStackTrace();
            }
            return null;
        }
    }
}
