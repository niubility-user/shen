package com.jingdong.manto.jsapi.refact;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.provider.MantoFileProvider;
import com.jingdong.manto.t.c;
import com.jingdong.manto.t.d;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.utils.z;
import java.io.File;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JsApiOpenDocument extends AbstractMantoModule {
    private static final String FILE_PATH = "filePath";
    private static final String FILE_TYPE = "fileType";
    private static final String OPEN_INNER = "openinner";

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "OpenDocuement";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle2;
        String str2;
        super.handleMethod(str, mantoCore, bundle, mantoResultCallBack);
        String string = bundle.getString("filePath", "");
        String string2 = bundle.getString(FILE_TYPE, "");
        if (TextUtils.isEmpty(string)) {
            bundle2 = new Bundle();
            str2 = "invalid data";
        } else {
            d g2 = c.g(bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY), string);
            if (g2 != null) {
                String str3 = g2.b;
                String b = z.b(g2.f14209c);
                if (MantoStringUtils.isEmpty(string2)) {
                    string2 = b;
                }
                boolean openInner = openInner();
                if (!openInner) {
                    openInner = bundle.getBoolean(OPEN_INNER, false);
                }
                Activity activity = mantoCore.getActivity();
                String num = Integer.toString(mantoCore.hashCode());
                if (!openInner) {
                    openWithThirdApp(activity, str3, string2, mantoResultCallBack);
                    return;
                }
                int openDocument = openDocument(activity, str3, string2, num, mantoResultCallBack);
                MantoLog.i("JsApiOpenDocument", String.format("QB openReadFile, ret = %d", Integer.valueOf(openDocument)));
                if (openDocument != 0) {
                    openWithThirdApp(activity, str3, string2, mantoResultCallBack);
                    return;
                } else {
                    mantoResultCallBack.onSuccess(new Bundle());
                    return;
                }
            }
            bundle2 = new Bundle();
            str2 = "file doesn't exist";
        }
        bundle2.putString("message", str2);
        mantoResultCallBack.onFailed(bundle2);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        if (jSONObject == null) {
            return bundle;
        }
        String optString = jSONObject.optString("filePath");
        String optString2 = jSONObject.optString(FILE_TYPE);
        if (!TextUtils.isEmpty(optString)) {
            bundle.putString("filePath", optString);
        }
        if (!TextUtils.isEmpty(optString2)) {
            bundle.putString(FILE_TYPE, optString2);
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("openDocument", 1));
    }

    public int openDocument(Activity activity, String str, String str2, String str3, MantoResultCallBack mantoResultCallBack) {
        return -1;
    }

    public boolean openInner() {
        return false;
    }

    public int openWithThirdApp(Activity activity, String str, String str2, MantoResultCallBack mantoResultCallBack) {
        Uri fromFile;
        String c2 = z.c(str2);
        try {
            Intent intent = new Intent();
            intent.setPackage(null);
            intent.setAction("android.intent.action.VIEW");
            if (Build.VERSION.SDK_INT >= 24) {
                String providerAuthorities = MantoUtils.getProviderAuthorities(MantoFileProvider.class);
                if (TextUtils.isEmpty(providerAuthorities)) {
                    throw new Exception("no available Provider");
                }
                fromFile = FileProvider.getUriForFile(activity, providerAuthorities, new File(str));
                intent.addFlags(1);
            } else {
                fromFile = Uri.fromFile(new File(str));
            }
            intent.setDataAndType(fromFile, c2);
            intent.addFlags(268435456);
            activity.startActivity(intent);
            mantoResultCallBack.onSuccess(new Bundle());
            return 0;
        } catch (Exception unused) {
            Bundle bundle = new Bundle();
            bundle.putString("message", "no third apps supported");
            mantoResultCallBack.onFailed(bundle);
            return 0;
        }
    }
}
