package com.jingdong.app.mall.preload;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.alibaba.fastjson.parser.JSONLexer;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.aura.e;
import com.jingdong.app.mall.preload.JDPluginSwitchEntity;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import java.util.Iterator;
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
    */
    public void preloadPlugins(JDPluginSwitchEntity.Priority priority) {
        Iterator<String> it = priority.getPlugins().iterator();
        while (it.hasNext()) {
            String next = it.next();
            char c2 = 0;
            boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue(next, false);
            String str = next + " switch is " + switchBooleanValue;
            if (switchBooleanValue) {
                next.hashCode();
                switch (next.hashCode()) {
                    case -2135967208:
                        break;
                    case -2072292463:
                        if (next.equals("preloadNewArrivals")) {
                            c2 = 1;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -2043195019:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_JDPAYSDK)) {
                            c2 = 2;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -1384212053:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_ADDRESS)) {
                            c2 = 3;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -884910214:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_ORDER_CENTER)) {
                            c2 = 4;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -720756259:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_NEW_COUPON)) {
                            c2 = 5;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -651548179:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_JDLIVELIST)) {
                            c2 = 6;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -641978167:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_CART)) {
                            c2 = 7;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -641500122:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_SCAN)) {
                            c2 = '\b';
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -363939177:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_PRODUCT_DETAIL)) {
                            c2 = '\t';
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -86436762:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_BROWER_HISTORY)) {
                            c2 = '\n';
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 70938008:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_SHARE_ORDER)) {
                            c2 = 11;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 216330663:
                        if (next.equals("preloadDiscovery")) {
                            c2 = '\f';
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 248399031:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_EVALUATECENTER)) {
                            c2 = '\r';
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 713893911:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_COMMUNE)) {
                            c2 = 14;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 718405054:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_MESSAGE)) {
                            c2 = 15;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 816196441:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_MIAOSHA)) {
                            c2 = 16;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1301524545:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_WORTHBUY)) {
                            c2 = 17;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1572574049:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_BABEL)) {
                            c2 = 18;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1579551223:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_JSHOP)) {
                            c2 = 19;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1582231264:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_LOGIN)) {
                            c2 = 20;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1700978537:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_PERSONAL)) {
                            c2 = 21;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1707926122:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_ICSSDK)) {
                            c2 = 22;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1817550242:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_SHANGOU)) {
                            c2 = 23;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1841589281:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_MYLIVE)) {
                            c2 = 24;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1947573266:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_SETTLEMENT)) {
                            c2 = 25;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1995527441:
                        if (next.equals(SwitchQueryFetcher.PRELOAD_SEARCH)) {
                            c2 = JSONLexer.EOI;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    default:
                        c2 = '\uffff';
                        break;
                }
                switch (c2) {
                    case 0:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(99));
                        continue;
                    case 1:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(108));
                        continue;
                    case 2:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(10));
                        continue;
                    case 3:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(29));
                        continue;
                    case 4:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(18));
                        continue;
                    case 5:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(11));
                        continue;
                    case 6:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(44));
                        continue;
                    case 7:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(45));
                        continue;
                    case '\b':
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(13));
                        continue;
                    case '\t':
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(9));
                        continue;
                    case '\n':
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(24));
                        continue;
                    case 11:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(83));
                        continue;
                    case '\f':
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(94));
                        continue;
                    case '\r':
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(21));
                        continue;
                    case 14:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(20));
                        continue;
                    case 15:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(79));
                        continue;
                    case 16:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(35));
                        continue;
                    case 17:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(19));
                        continue;
                    case 18:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(90));
                        continue;
                    case 19:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(16));
                        continue;
                    case 20:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(41));
                        continue;
                    case 21:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(58));
                        continue;
                    case 22:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(53));
                        continue;
                    case 23:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(93));
                        continue;
                    case 24:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(33));
                        continue;
                    case 25:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(55));
                        continue;
                    case 26:
                        loadBundle(AuraBundleInfos.getBundleNameFromBundleId(12));
                        continue;
                }
            }
        }
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
