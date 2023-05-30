package com.jingdong.common.network;

import com.jd.framework.json.anotation.JSONField;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class BaseParam {
    private static final String ENCRYPT_FILED = "jdenc_";
    private int identifier;
    private WeakReference<BaseActivity> mActivity;
    public String plugin_version;

    public BaseParam() {
        setUpInternalParam();
    }

    private void setUpInternalParam() {
        this.plugin_version = String.valueOf(AuraBundleConfig.getInstance().getBundleVersionCode("com.jd.lib.setting"));
    }

    @JSONField(serialize = false)
    public BaseActivity getActivity() {
        if (hasBaseActivity()) {
            return this.mActivity.get();
        }
        return null;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public boolean hasBaseActivity() {
        WeakReference<BaseActivity> weakReference = this.mActivity;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @JSONField(serialize = false)
    public void setActivity(BaseActivity baseActivity) {
        WeakReference<BaseActivity> weakReference = this.mActivity;
        if (weakReference == null || weakReference.get() == null) {
            this.mActivity = new WeakReference<>(baseActivity);
        }
    }

    public void setIdentifier(int i2) {
        this.identifier = i2;
    }

    public String setUpEncFiled(String str) {
        return "jdenc_" + str;
    }
}
