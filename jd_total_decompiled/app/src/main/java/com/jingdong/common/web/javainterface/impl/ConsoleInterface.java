package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jd.libs.hybrid.base.util.Log;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.util.WebLogHelper;

/* loaded from: classes12.dex */
public class ConsoleInterface implements IJavaInterface {
    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return "xconsole";
    }

    @JavascriptInterface
    public void launchConsole(String str) {
        WebLogHelper.showXLog = true;
        Log.showXLog = true;
        com.jd.libs.xconsole.a.a(str);
    }
}
