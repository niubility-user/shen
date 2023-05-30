package com.jingdong.app.mall.dynamicImpl;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.jd.dynamic.base.DynamicBaseActivity;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class JDCommonDynamicActivity extends DynamicBaseActivity {

    /* renamed from: g  reason: collision with root package name */
    private FrameLayout f8360g;

    @Override // com.jd.dynamic.base.DynamicBaseActivity
    protected View getContentView() {
        FrameLayout frameLayout = new FrameLayout(this);
        this.f8360g = frameLayout;
        return frameLayout;
    }

    @Override // com.jd.dynamic.base.DynamicBaseActivity
    protected FrameLayout getDynamicContainer() {
        return this.f8360g;
    }

    @Override // com.jd.dynamic.base.DynamicBaseActivity
    protected JSONObject getInitJSON() {
        return null;
    }

    @Override // com.jd.dynamic.base.DynamicBaseActivity
    protected InputStream getLocalXmlStream() {
        try {
            return getAssets().open("dynamic_error.xml");
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.base.DynamicBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.base.DynamicBaseActivity
    public void onDynamicLoadError(Exception exc) {
        super.onDynamicLoadError(exc);
        HashMap hashMap = new HashMap();
        hashMap.put("errCode", "941");
        hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
        hashMap.put("function", "loadpage");
        hashMap.put("errMsg", getAppType() + "-" + getSystemCode());
        hashMap.put("url", getBizField());
        hashMap.put("postData", exc.toString());
        ExceptionReporter.sendExceptionData(this, hashMap);
    }
}
