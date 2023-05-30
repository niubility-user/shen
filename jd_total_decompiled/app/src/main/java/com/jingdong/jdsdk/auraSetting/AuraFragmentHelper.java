package com.jingdong.jdsdk.auraSetting;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

/* loaded from: classes14.dex */
public class AuraFragmentHelper {
    private static final String TAG = "AuraFragmentHelper";
    private static AuraFragmentHelper mInstance;
    private Application mApplication;
    private c mAuraFragmentSetting = new a(this);

    /* loaded from: classes14.dex */
    class a extends b {
        a(AuraFragmentHelper auraFragmentHelper) {
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public void d(Activity activity) {
        }
    }

    /* loaded from: classes14.dex */
    public static abstract class b implements c {
        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public ArrayList<String> a(String str) {
            return null;
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public Fragment b(ArrayList<String> arrayList, String str) {
            return null;
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public String c(String str) {
            return null;
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public void e(String str, String str2, String str3, Throwable th) {
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public boolean isSwitchOpen(String str) {
            return true;
        }
    }

    /* loaded from: classes14.dex */
    public interface c {
        ArrayList<String> a(String str);

        Fragment b(ArrayList<String> arrayList, String str);

        String c(String str);

        void d(Activity activity);

        void e(String str, String str2, String str3, Throwable th);

        boolean isSwitchOpen(String str);
    }

    private AuraFragmentHelper() {
    }

    public static AuraFragmentHelper getInstance() {
        if (mInstance == null) {
            mInstance = new AuraFragmentHelper();
        }
        return mInstance;
    }

    private ArrayList<String> getNotPreparedBundles(String str) {
        String c2 = this.mAuraFragmentSetting.c(str);
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        return this.mAuraFragmentSetting.a(c2);
    }

    public boolean isFragmentAvailable(String str, String str2) {
        if (this.mAuraFragmentSetting.isSwitchOpen(str)) {
            Fragment fragment = null;
            ArrayList<String> notPreparedBundles = getNotPreparedBundles(str2);
            if (notPreparedBundles != null && notPreparedBundles.size() > 0) {
                boolean z = com.jingdong.sdk.auratools.a.a;
                fragment = new Fragment();
            } else {
                try {
                    fragment = (Fragment) this.mApplication.getClassLoader().loadClass(str2).newInstance();
                } catch (Exception e2) {
                    this.mAuraFragmentSetting.e(str2, "", "isFragmentAvailable", e2);
                    if (com.jingdong.sdk.auratools.a.a) {
                        e2.toString();
                    }
                }
            }
            return fragment != null;
        }
        return false;
    }

    public Fragment newFragment(Activity activity, String str) {
        Fragment fragment = null;
        if (activity == null || TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList<String> notPreparedBundles = getNotPreparedBundles(str);
        if (notPreparedBundles != null && notPreparedBundles.size() > 0) {
            boolean z = com.jingdong.sdk.auratools.a.a;
            return this.mAuraFragmentSetting.b(notPreparedBundles, str);
        }
        try {
            Fragment fragment2 = (Fragment) activity.getApplication().getClassLoader().loadClass(str).newInstance();
            try {
                this.mAuraFragmentSetting.d(activity);
                return fragment2;
            } catch (Exception e2) {
                e = e2;
                fragment = fragment2;
                this.mAuraFragmentSetting.e(str, "", "newFragment", e);
                if (com.jingdong.sdk.auratools.a.a) {
                    e.toString();
                }
                return fragment;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public void registIAuraFragmentSetting(c cVar) {
        if (cVar != null) {
            this.mAuraFragmentSetting = cVar;
        }
    }

    public void setContext(Application application) {
        if (application != null) {
            this.mApplication = application;
        }
    }
}
