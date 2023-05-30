package com.jingdong.app.mall.im.business;

import android.app.Activity;
import android.view.View;
import com.facebook.react.ReactPackage;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jdreactFramework.JDReactExtendPackage;
import com.jingdong.common.jdreactFramework.helper.ReactPackageFactory;
import com.jingdong.common.jdreactFramework.preload.EngineHelper;
import com.jingdong.common.jdreactFramework.preload.JDReactEngineReuseManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.entity.RnViewBean;
import com.jingdong.service.impl.IMRN;

/* loaded from: classes4.dex */
public class n extends IMRN {
    private static final String a = "n";
    private static EngineHelper b;

    /* loaded from: classes4.dex */
    class a implements ReactPackageFactory {
        a(n nVar) {
        }

        @Override // com.jingdong.common.jdreactFramework.helper.ReactPackageFactory
        public ReactPackage newReactPackage() {
            try {
                return (ReactPackage) JdSdk.getInstance().getApplicationContext().getClassLoader().loadClass("mix.JDReactExtendPackage").newInstance();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
                return new JDReactExtendPackage();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
                return new JDReactExtendPackage();
            } catch (InstantiationException e4) {
                e4.printStackTrace();
                return new JDReactExtendPackage();
            }
        }
    }

    @Override // com.jingdong.service.impl.IMRN, com.jingdong.service.service.RNService
    public void createEngineHelper() {
        OKLog.d("bundleicssdkservice", a + "---createEngineHelper");
        b = JDReactEngineReuseManager.getInstance().createEngine(SourceEntity.SOURCE_TYPE_FROM_DONGDONG, new a(this));
    }

    @Override // com.jingdong.service.impl.IMRN, com.jingdong.service.service.RNService
    public void destroy() {
        OKLog.d("bundleicssdkservice", a + "---destroy");
        if (b != null) {
            b.destroy();
        }
    }

    @Override // com.jingdong.service.impl.IMRN, com.jingdong.service.service.RNService
    public Object getEngineHelper() {
        OKLog.d("bundleicssdkservice", a + "---getEngineHelper");
        EngineHelper engineHelper = b;
        return b;
    }

    @Override // com.jingdong.service.impl.IMRN, com.jingdong.service.service.RNService
    public View getRNView(Activity activity, RnViewBean rnViewBean) {
        OKLog.d("bundleicssdkservice", a + "---getRNView");
        try {
            return (View) JdSdk.getInstance().getApplicationContext().getClassLoader().loadClass("mix.RnUtils").getMethod("getRNView", Activity.class, RnViewBean.class).invoke(null, activity, rnViewBean);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.jingdong.service.impl.IMRN, com.jingdong.service.service.RNService
    public boolean msgRNTemplateEanble() {
        OKLog.d("bundleicssdkservice", a + "---msgRNTemplateEanble");
        return true;
    }

    @Override // com.jingdong.service.impl.IMRN, com.jingdong.service.service.RNService
    public void resume(Activity activity) {
        OKLog.d("bundleicssdkservice", a + "---resume");
        if (b != null) {
            b.resume(activity);
        }
    }
}
