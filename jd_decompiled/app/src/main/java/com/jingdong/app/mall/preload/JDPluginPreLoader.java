package com.jingdong.app.mall.preload;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.aura.e;
import com.jingdong.app.mall.preload.JDPluginSwitchEntity;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.utils.SwitchQueryFetcher;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
public class JDPluginPreLoader {
    private static final String TAG = "JDPluginPreLoader";
    private ExecutorService executorService;
    private static final JDPluginPreLoader sIntance = new JDPluginPreLoader();
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private void addRunnable(Runnable runnable) {
        if (this.executorService == null) {
            this.executorService = Executors.newSingleThreadExecutor();
        }
        this.executorService.submit(runnable);
    }

    public static JDPluginPreLoader getInstance() {
        return sIntance;
    }

    private long getRemainMemory() {
        try {
            return ((Runtime.getRuntime().maxMemory() - (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())) / 1024) / 1024;
        } catch (Exception e2) {
            e2.printStackTrace();
            e2.getMessage();
            return 0L;
        }
    }

    private void loadBundle(String str) {
        if (TextUtils.isEmpty(str) || AuraConfig.isBundleProvided(str)) {
            return;
        }
        try {
            String str2 = "loadBundle " + str;
            e.n(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            e2.getMessage();
        }
    }

    private void preloadHighPrioriyPlugins(final JDPluginSwitchEntity jDPluginSwitchEntity) {
        int i2;
        final JDPluginSwitchEntity.Priority highPriority = jDPluginSwitchEntity.getHighPriority();
        if (highPriority == null || highPriority.getPlugins() == null || highPriority.getPlugins().size() == 0 || (i2 = highPriority.minMemory) == 0 || getRemainMemory() == 0 || getRemainMemory() < i2) {
            return;
        }
        final long j2 = jDPluginSwitchEntity.idleTimesDelay;
        addRunnable(new Runnable() { // from class: com.jingdong.app.mall.preload.JDPluginPreLoader.1
            @Override // java.lang.Runnable
            public void run() {
                JDPluginPreLoader.this.preloadPlugins(highPriority);
                JDPluginPreLoader.sHandler.postDelayed(new Runnable() { // from class: com.jingdong.app.mall.preload.JDPluginPreLoader.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        JDPluginPreLoader.this.preloadLowPriorityPlugins(jDPluginSwitchEntity);
                    }
                }, j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preloadLowPriorityPlugins(JDPluginSwitchEntity jDPluginSwitchEntity) {
        int i2;
        final JDPluginSwitchEntity.Priority lowPriority = jDPluginSwitchEntity.getLowPriority();
        if (lowPriority == null || lowPriority.getPlugins() == null || lowPriority.getPlugins().size() == 0 || (i2 = lowPriority.minMemory) == 0 || getRemainMemory() == 0 || getRemainMemory() < i2) {
            return;
        }
        addRunnable(new Runnable() { // from class: com.jingdong.app.mall.preload.JDPluginPreLoader.2
            @Override // java.lang.Runnable
            public void run() {
                JDPluginPreLoader.this.preloadPlugins(lowPriority);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01ae, code lost:
        if (r2.equals("preloadNewProduct") == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void preloadPlugins(com.jingdong.app.mall.preload.JDPluginSwitchEntity.Priority r18) {
        /*
            Method dump skipped, instructions count: 884
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.preload.JDPluginPreLoader.preloadPlugins(com.jingdong.app.mall.preload.JDPluginSwitchEntity$Priority):void");
    }

    public void start() {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        JDPluginSwitchEntity jDPluginSwitchEntity = null;
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.PRELOAD_PLUGIN, null);
        if (TextUtils.isEmpty(switchStringValue)) {
            return;
        }
        try {
            jDPluginSwitchEntity = (JDPluginSwitchEntity) JDJSON.parseObject(switchStringValue, JDPluginSwitchEntity.class);
        } catch (Exception e2) {
            e2.printStackTrace();
            e2.getMessage();
        }
        if (jDPluginSwitchEntity == null) {
            return;
        }
        String str = jDPluginSwitchEntity.mainSwitch;
        if (TextUtils.isEmpty(str) || "no".equals(str)) {
            return;
        }
        preloadHighPrioriyPlugins(jDPluginSwitchEntity);
    }
}
