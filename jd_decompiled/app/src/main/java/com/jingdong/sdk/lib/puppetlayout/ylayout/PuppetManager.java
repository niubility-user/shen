package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.getkeepsafe.relinker.c;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.sdk.lib.puppetlayout.R;
import com.jingdong.sdk.lib.puppetlayout.f.a;
import com.jingdong.sdk.lib.puppetlayout.g.b;
import com.jingdong.sdk.lib.puppetlayout.storage.NetManager;
import com.jingdong.sdk.lib.puppetlayout.storage.TemplateUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.android.YogaLayout;
import com.jingdong.sdk.lib.puppetlayout.ylayout.callback.ActionCallback;
import com.jingdong.sdk.lib.puppetlayout.ylayout.callback.Callback;
import com.jingdong.sdk.lib.puppetlayout.ylayout.callback.CallbackManager;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateModel;
import java.io.StringReader;

/* loaded from: classes8.dex */
public class PuppetManager {
    private static final PuppetManager ourInstance = new PuppetManager();
    private int loadYLayoutState = -1;
    public String apolloId = "";
    public String apolloSecret = "";
    public String appName = "";
    public boolean debug = false;
    public String appId = "";
    public int cacheTemplatesCount = 20;
    public long cacheTemplatesTime = 86400000;
    public boolean cacheTemplates = false;
    private DefaultStyleListener defaultStyleListener = null;
    public String sdkClient = "plugin_android";
    public String sdkVersion = "71327c8";
    public String sdkName = DeeplinkProductDetailHelper.LAYER_STYLE;

    private boolean checkYlState(Context context) {
        if (this.loadYLayoutState != 1) {
            try {
                c.a(context, "c++_shared");
                c.a(context, "ylayout");
                this.loadYLayoutState = 1;
            } catch (NoClassDefFoundError e2) {
                if (b.a) {
                    e2.printStackTrace();
                }
                try {
                    System.loadLibrary("c++_shared");
                    System.loadLibrary("ylayout");
                    this.loadYLayoutState = 1;
                } catch (Throwable th) {
                    if (b.a) {
                        th.printStackTrace();
                    }
                }
            } catch (Throwable th2) {
                if (b.a) {
                    th2.printStackTrace();
                }
            }
        }
        return this.loadYLayoutState == 1;
    }

    private View createTemplateView(Context context, TemplateModel templateModel, ViewGroup viewGroup) {
        if (templateModel == null || TextUtils.isEmpty(templateModel.templateId)) {
            return null;
        }
        return com.jingdong.sdk.lib.puppetlayout.c.d().e(context, viewGroup, null, templateModel, false);
    }

    public static PuppetManager getInstance() {
        return ourInstance;
    }

    private PuppetContext getPuppetContext(View view) {
        Object tag;
        if (view == null || (tag = view.getTag(R.id.com_jd_sdk_lib_puppetlayout_tree)) == null || !(tag instanceof PuppetContext)) {
            return null;
        }
        return (PuppetContext) tag;
    }

    public void bindViewAction(ViewGroup viewGroup, Callback callback) {
        if (viewGroup == null || callback == null) {
            return;
        }
        try {
            Object tag = viewGroup.getTag(R.id.com_jd_sdk_lib_puppetlayout_tree);
            if (tag == null || !(tag instanceof PuppetContext)) {
                return;
            }
            PuppetContext puppetContext = (PuppetContext) tag;
            if (callback instanceof ActionCallback) {
                puppetContext.manager.register("action", callback);
            }
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
        }
    }

    public void bindViewData(View view, JDJSONObject jDJSONObject) {
        try {
            PuppetContext.bindView(view, jDJSONObject);
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
        }
    }

    public void cancelCountdown(ViewGroup viewGroup) {
        PuppetContext puppetContext;
        if (viewGroup == null || (puppetContext = getPuppetContext(viewGroup)) == null) {
            return;
        }
        puppetContext.cancelAllCountdown();
    }

    public ViewGroup createDdTemplateView(Context context, String str, ViewGroup viewGroup) {
        PuppetManager puppetManager;
        TemplateModel obtainDdTemplate;
        try {
            if (TextUtils.isEmpty(str) || !checkYlState(context) || (obtainDdTemplate = (puppetManager = getInstance()).obtainDdTemplate(context, str)) == null) {
                return null;
            }
            ViewGroup viewGroup2 = (ViewGroup) puppetManager.createTemplateView(context, obtainDdTemplate, viewGroup);
            String str2 = obtainDdTemplate.flexibleWidth;
            String str3 = obtainDdTemplate.flexibleHeight;
            if (viewGroup2 != null) {
                viewGroup2.setTag(R.id.com_jd_sdk_lib_puppetlayout_flexible_width, Boolean.valueOf("1".equals(str2)));
                viewGroup2.setTag(R.id.com_jd_sdk_lib_puppetlayout_flexible_height, Boolean.valueOf("1".equals(str3)));
                viewGroup2.setTag(R.id.com_jd_sdk_lib_puppetlayout_actionmgr, new CallbackManager());
                int i2 = -2;
                int i3 = "1".equals(str2) ? -2 : -1;
                if (!"1".equals(str3)) {
                    i2 = -1;
                }
                viewGroup2.setLayoutParams(new ViewGroup.LayoutParams(i3, i2));
            }
            return viewGroup2;
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public boolean equals(ViewGroup viewGroup, String str, String str2) {
        PuppetContext puppetContext;
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (puppetContext = getPuppetContext(viewGroup)) == null || !str.equals(puppetContext.styleId)) {
                return false;
            }
            return str2.equals(puppetContext.styleVersion);
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public void expo(ViewGroup viewGroup) {
        try {
            PuppetContext puppetContext = getPuppetContext(viewGroup);
            if (puppetContext != null) {
                puppetContext.expo(viewGroup);
            }
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
        }
    }

    public void forceSyncTemplates(Context context, Runnable runnable, Runnable runnable2) {
        NetManager.getInstance().requestUpdateStyles(context, null, null, null, null, null, runnable, runnable2, true, -1);
    }

    public DefaultStyleListener getDefaultStyleListener() {
        return this.defaultStyleListener;
    }

    public int getMeasuredHeight(int i2, ViewGroup viewGroup, JDJSONObject jDJSONObject) {
        if (viewGroup == null) {
            return -1;
        }
        try {
            getInstance().bindViewData(viewGroup, jDJSONObject);
            viewGroup.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            return viewGroup.getMeasuredHeight();
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public float getRootHeight(View view) {
        if (view == null || !(view instanceof YogaLayout)) {
            return -1.0f;
        }
        try {
            Object tag = view.getTag(R.id.com_jd_sdk_lib_puppetlayout_root_height);
            if (tag == null || !(tag instanceof Float)) {
                return -1.0f;
            }
            return ((Float) tag).floatValue();
        } catch (Exception unused) {
            return -1.0f;
        }
    }

    public float getRootWidth(View view) {
        if (view == null || !(view instanceof YogaLayout)) {
            return -1.0f;
        }
        try {
            Object tag = view.getTag(R.id.com_jd_sdk_lib_puppetlayout_root_width);
            if (tag == null || !(tag instanceof Float)) {
                return -1.0f;
            }
            return ((Float) tag).floatValue();
        } catch (Exception unused) {
            return -1.0f;
        }
    }

    public boolean hasTemplate(Context context, String str, String str2) {
        DefaultStyleListener defaultStyleListener;
        try {
            if (checkYlState(context) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                String templateXml = TemplateUtils.getTemplateXml(context, str, str2);
                if (TextUtils.isEmpty(templateXml) && (defaultStyleListener = this.defaultStyleListener) != null) {
                    templateXml = defaultStyleListener.getStyle(str, str2);
                }
                return !TextUtils.isEmpty(templateXml);
            }
            return false;
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public void init(Context context, String str, String str2) {
        syncTemplates(context);
    }

    public boolean isEditing(ViewGroup viewGroup) {
        try {
            PuppetContext puppetContext = getPuppetContext(viewGroup);
            if (puppetContext != null) {
                return puppetContext.isEditing;
            }
            return false;
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public boolean isFlexibleHeight(ViewGroup viewGroup) {
        return viewGroup == null || ((Boolean) viewGroup.getTag(R.id.com_jd_sdk_lib_puppetlayout_flexible_height)).booleanValue();
    }

    public boolean isFlexibleWidth(ViewGroup viewGroup) {
        return viewGroup == null || ((Boolean) viewGroup.getTag(R.id.com_jd_sdk_lib_puppetlayout_flexible_width)).booleanValue();
    }

    public boolean isNotDemo() {
        return !"demodewdweqkwqjd".equalsIgnoreCase(this.appId);
    }

    public void notifyRequestLayout(View view) {
        PuppetContext puppetContext = getPuppetContext(view);
        if (puppetContext == null || !puppetContext.isRequestLayoutOnBind) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (view.isAttachedToWindow()) {
                view.requestLayout();
            }
        } else {
            view.requestLayout();
        }
        puppetContext.isRequestLayoutOnBind = false;
    }

    public TemplateModel obtainDdTemplate(Context context, String str) {
        String ddTemplateXml = TemplateUtils.getDdTemplateXml(context, str);
        if (TextUtils.isEmpty(ddTemplateXml)) {
            if (getInstance().getDefaultStyleListener() != null) {
                ddTemplateXml = getInstance().getDefaultStyleListener().getDdStyle(str);
            }
            if (TextUtils.isEmpty(ddTemplateXml)) {
                return null;
            }
        }
        try {
            TemplateModel parse = new TemplateXmlParser().parse(new StringReader(ddTemplateXml));
            parse.contentLength = ddTemplateXml.length();
            return parse;
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public TemplateModel obtainTemplate(Context context, String str, String str2) {
        String templateXml = TemplateUtils.getTemplateXml(context, str, str2);
        if (TextUtils.isEmpty(templateXml)) {
            if (getInstance().getDefaultStyleListener() != null) {
                templateXml = getInstance().getDefaultStyleListener().getStyle(str, str2);
            }
            if (TextUtils.isEmpty(templateXml)) {
                return null;
            }
        }
        try {
            TemplateModel parse = new TemplateXmlParser().parse(new StringReader(templateXml));
            parse.contentLength = templateXml.length();
            return parse;
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
            return null;
        }
    }

    @Deprecated
    public void register(String str, Callback callback) {
    }

    public void saveStyles(Context context, String str) {
        try {
            syncTemplates(context);
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
        }
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    public void setDefaultStyleListener(DefaultStyleListener defaultStyleListener) {
        this.defaultStyleListener = defaultStyleListener;
    }

    public void setIsEditing(ViewGroup viewGroup, boolean z) {
        try {
            PuppetContext puppetContext = getPuppetContext(viewGroup);
            if (puppetContext != null) {
                puppetContext.isEditing = z;
                puppetContext.notifyEditingChanged(viewGroup);
            }
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
        }
    }

    public void setParentHandler(ViewGroup viewGroup, ParentHandler parentHandler) {
        if (viewGroup == null || parentHandler == null) {
            return;
        }
        try {
            Object tag = viewGroup.getTag(R.id.com_jd_sdk_lib_puppetlayout_tree);
            if (tag == null || !(tag instanceof PuppetContext)) {
                return;
            }
            ((PuppetContext) tag).parentHandler = parentHandler;
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
        }
    }

    public void setRootHeight(View view, float f2) {
    }

    public void setRootWidth(View view, float f2) {
    }

    public void setViewDataTimestamp(ViewGroup viewGroup, long j2) {
        PuppetContext puppetContext = getPuppetContext(viewGroup);
        if (puppetContext != null) {
            puppetContext.dataTimestamp = j2;
        }
    }

    public void sync(Context context) {
        try {
            syncTemplates(context);
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
        }
    }

    public void syncTemplates(Context context) {
        syncTemplates(context, null, null, new Runnable() { // from class: com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetManager.1
            {
                PuppetManager.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
            }
        }, null);
    }

    public void unregister(Callback callback) {
    }

    public void unregister(String str) {
    }

    public void syncTemplates(Context context, String str, String str2) {
        syncTemplates(context, str, str2, null, null);
    }

    public void syncTemplates(Context context, Runnable runnable, Runnable runnable2) {
        syncTemplates(context, null, null, runnable, runnable2);
    }

    public ViewGroup createTemplateView(Context context, String str, String str2) {
        try {
            return createTemplateView(context, str, str2, null);
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public void sync(Context context, String str, String str2, String str3) {
        try {
            NetManager.getInstance().requestUpdateStyles(context, str, str2, str3, null, null, null, null, false, -1);
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
        }
    }

    public void syncTemplates(Context context, String str, String str2, Runnable runnable, Runnable runnable2) {
        NetManager.getInstance().requestUpdateStyles(context, null, null, null, str, str2, runnable, runnable2, false, -1);
    }

    public ViewGroup createTemplateView(Context context, String str, String str2, ViewGroup viewGroup) {
        ViewGroup viewGroup2;
        String str3;
        String str4;
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !checkYlState(context)) {
                return null;
            }
            PuppetManager puppetManager = getInstance();
            a c2 = com.jingdong.sdk.lib.puppetlayout.c.d().c(str, str2);
            if (c2 != null) {
                viewGroup2 = (ViewGroup) com.jingdong.sdk.lib.puppetlayout.c.d().f(context, viewGroup, c2, false);
                str4 = c2.a;
                str3 = c2.b;
            } else {
                TemplateModel obtainTemplate = puppetManager.obtainTemplate(context, str, str2);
                if (obtainTemplate == null) {
                    return null;
                }
                viewGroup2 = (ViewGroup) puppetManager.createTemplateView(context, obtainTemplate, viewGroup);
                String str5 = obtainTemplate.flexibleWidth;
                str3 = obtainTemplate.flexibleHeight;
                str4 = str5;
            }
            if (viewGroup2 != null) {
                viewGroup2.setTag(R.id.com_jd_sdk_lib_puppetlayout_flexible_width, Boolean.valueOf("1".equals(str4)));
                viewGroup2.setTag(R.id.com_jd_sdk_lib_puppetlayout_flexible_height, Boolean.valueOf("1".equals(str3)));
                viewGroup2.setTag(R.id.com_jd_sdk_lib_puppetlayout_actionmgr, new CallbackManager());
                int i2 = -2;
                int i3 = "1".equals(str4) ? -2 : -1;
                if (!"1".equals(str3)) {
                    i2 = -1;
                }
                viewGroup2.setLayoutParams(new ViewGroup.LayoutParams(i3, i2));
            }
            return viewGroup2;
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public void sync(Context context, String str, String str2, String str3, int i2) {
        try {
            NetManager.getInstance().requestUpdateStyles(context, str, str2, str3, null, null, null, null, false, 2);
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
        }
    }

    public void sync(Context context, String str, String str2, String str3, String str4, String str5) {
        try {
            NetManager.getInstance().requestUpdateStyles(context, str, str2, str3, str4, str5, null, null, false, -1);
        } catch (Throwable th) {
            if (b.a) {
                th.printStackTrace();
            }
        }
    }
}
