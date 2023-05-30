package com.jingdong.jdsdk.auraSetting;

import android.app.Activity;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/* loaded from: classes14.dex */
public class AuraBundleConfig {
    private static AuraBundleConfig mInstance;
    private a configListener;
    private Vector<b> obs = new Vector<>();

    /* loaded from: classes14.dex */
    public interface a {
        ArrayList<String> a(String str);

        Fragment b(ArrayList<String> arrayList, String str);

        String c(String str);

        void d(Activity activity);

        void e(String str, File file);

        long f(String str);

        void g();

        SharedPreferences getAuraSharedPreferences();

        long getBundleVersionCode(String str);

        boolean h(String str);

        String i();

        boolean isBundlePrepared(String str);

        List<Map<String, String>> j();

        boolean k(String str);
    }

    /* loaded from: classes14.dex */
    public interface b {
        void onChanged();
    }

    private AuraBundleConfig() {
    }

    public static AuraBundleConfig getInstance() {
        if (mInstance == null) {
            mInstance = new AuraBundleConfig();
        }
        return mInstance;
    }

    private void notifyObserver() {
        Iterator<b> it = this.obs.iterator();
        while (it.hasNext()) {
            it.next().onChanged();
        }
    }

    public void addObserver(b bVar) {
        bVar.getClass();
        if (this.obs.contains(bVar)) {
            return;
        }
        this.obs.addElement(bVar);
    }

    public void cleanAuraCache() {
        a aVar = this.configListener;
        if (aVar == null) {
            return;
        }
        aVar.g();
    }

    void ensureActivityResources(Activity activity) {
        a aVar = this.configListener;
        if (aVar == null) {
            return;
        }
        aVar.d(activity);
    }

    public String getBundleNameForComponet(String str) {
        a aVar = this.configListener;
        if (aVar == null) {
            return null;
        }
        return aVar.c(str);
    }

    public long getBundleVersionCode(String str) {
        a aVar = this.configListener;
        if (aVar == null) {
            return -1L;
        }
        return aVar.getBundleVersionCode(str);
    }

    public String getInstalledBundlesVersion() {
        a aVar = this.configListener;
        return aVar == null ? "" : aVar.i();
    }

    public boolean getIsAutoDownloadBundleInWifi() {
        SharedPreferences auraSharedPreferences;
        a aVar = this.configListener;
        if (aVar == null || (auraSharedPreferences = aVar.getAuraSharedPreferences()) == null) {
            return false;
        }
        return auraSharedPreferences.getBoolean("bundle_provided_tip_button", false);
    }

    public ArrayList<String> getNotPreparedProvidedBundles(String str) {
        a aVar = this.configListener;
        if (aVar == null) {
            return null;
        }
        return aVar.a(str);
    }

    public long getOriBundleVersionCode(String str) {
        a aVar = this.configListener;
        if (aVar == null) {
            return -1L;
        }
        return aVar.f(str);
    }

    public List<Map<String, String>> getProvidedBundleInfos() {
        a aVar = this.configListener;
        if (aVar == null) {
            return null;
        }
        return aVar.j();
    }

    public Fragment getProvidedBundleNotFoundFragment(ArrayList<String> arrayList, String str) {
        a aVar = this.configListener;
        if (aVar == null) {
            return null;
        }
        return aVar.b(arrayList, str);
    }

    public SharedPreferences getSharedPreferences() {
        a aVar = this.configListener;
        if (aVar == null) {
            return null;
        }
        return aVar.getAuraSharedPreferences();
    }

    public void installBundleNative(String str, File file) throws Exception {
        this.configListener.e(str, file);
    }

    public boolean isBundleLoaded(String str) {
        a aVar = this.configListener;
        if (aVar == null) {
            return false;
        }
        return aVar.k(str);
    }

    public boolean isBundlePrepared(String str) {
        a aVar = this.configListener;
        if (aVar == null) {
            return false;
        }
        return aVar.isBundlePrepared(str);
    }

    public void loadBundle(String str) {
        a aVar = this.configListener;
        if (aVar == null) {
            return;
        }
        aVar.h(str);
    }

    public void setConfigListener(a aVar) {
        this.configListener = aVar;
        notifyObserver();
    }

    public void setIsAutoDownloadBundleInWifi(boolean z) {
        SharedPreferences auraSharedPreferences;
        a aVar = this.configListener;
        if (aVar == null || (auraSharedPreferences = aVar.getAuraSharedPreferences()) == null) {
            return;
        }
        auraSharedPreferences.edit().putBoolean("bundle_provided_tip_button", z).apply();
    }
}
