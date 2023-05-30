package com.jingdong.pdj.libcore.watcher;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import com.jingdong.common.lbs.businessAddress.JDYFAddress;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.lbs.jdlocation.JDLocationSDK;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.pdj.libcore.bubble.HourlyGoBackHomeBubbleView;
import com.jingdong.pdj.libcore.bubble.HourlyGoGuideBubbleView;
import com.jingdong.pdj.libcore.net.HourlyGoTabNameTask;
import com.jingdong.pdj.libcore.net.HourlyGoTabUrlNetTask;
import com.jingdong.pdj.libcore.utils.HourlyGoAddressHelper;
import com.jingdong.pdj.libcore.utils.HourlyGoLibConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes7.dex */
public class HourlyGoObserverManager implements HourlyGoObservableListener {
    public static final String ADDRESS_BUSINESS_ID = "15f39f78ae41d235baf6dfdc573ccd47";
    private static final String HOURLY_GO_SOURCE_VALUE_DEFAULT = "-100";
    private static final String HOURLY_GO_SOURCE_VALUE_ERROR = "-200";
    public static final String HOURLY_GO_SOURCE_VALUE_GUIDE_BUBBLE = "47";
    private static final String HOURLY_GO_SOURCE_VALUE_HOME = "0";
    public static final String HOURLY_GO_SOURCE_VALUE_ORDER_BUBBLE = "48";
    private static final String HOURLY_GO_TAB_NAME_DEFAULT = "\u540c\u57ce";
    private static final String HOURLY_GO_TAB_NAME_KEY = "hourly_go_tab_name_key";
    public static Handler sHandler = new Handler(Looper.getMainLooper());
    private HourlyGoBackHomeBubbleView backHomeBubbleView;
    private HourlyGoGuideBubbleView guideBubbleView;
    public String hourgoInfo;
    private String mFloorId;
    public String sourceValue = "0";
    private List<HourlyGoObserverListener> list = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class SingletonHolder {
        private static final HourlyGoObserverManager INSTANCE = new HourlyGoObserverManager();

        private SingletonHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(String str, int i2, int i3) {
        Iterator<HourlyGoObserverListener> it = this.list.iterator();
        while (it.hasNext()) {
            it.next().updateHeadUrl(str, i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(String str) {
        Iterator<HourlyGoObserverListener> it = this.list.iterator();
        while (it.hasNext()) {
            it.next().updateTabName(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(String str, Map map, JDJSONObject jDJSONObject) {
        Iterator<HourlyGoObserverListener> it = this.list.iterator();
        while (it.hasNext()) {
            it.next().updateTabPicUrl(str, map, jDJSONObject);
        }
    }

    public static HourlyGoObserverManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestTabName(int i2, int i3, int i4, int i5, double d, double d2) {
        HourlyGoTabNameTask.getTabNameRequest(HourlyGoAddressHelper.generateGeo(d, d2), i2 + CartConstant.KEY_YB_INFO_LINK + i3 + CartConstant.KEY_YB_INFO_LINK + i4 + CartConstant.KEY_YB_INFO_LINK + i5, new HourlyGoTabNameTask.HourlyGoOnTabNameListener() { // from class: com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager.4
            @Override // com.jingdong.pdj.libcore.net.HourlyGoTabNameTask.HourlyGoOnTabNameListener
            public void onFailed() {
                HourlyGoObserverManager.this.notifyTabNameObserver(HourlyGoObserverManager.HOURLY_GO_TAB_NAME_DEFAULT);
            }

            @Override // com.jingdong.pdj.libcore.net.HourlyGoTabNameTask.HourlyGoOnTabNameListener
            public void onSuccess(String str) {
                HourlyGoObserverManager.this.setTabName(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTabName(String str) {
        if (!TextUtils.isEmpty(str)) {
            notifyTabNameObserver(str);
            CommonBase.putStringToPreference(HOURLY_GO_TAB_NAME_KEY, str);
            return;
        }
        notifyTabNameObserver(HOURLY_GO_TAB_NAME_DEFAULT);
    }

    private void setTabNameCache() {
        String stringFromPreference = CommonBase.getStringFromPreference(HOURLY_GO_TAB_NAME_KEY, "");
        if (!TextUtils.isEmpty(stringFromPreference)) {
            notifyTabNameObserver(stringFromPreference);
        } else {
            notifyTabNameObserver(HOURLY_GO_TAB_NAME_DEFAULT);
        }
    }

    private void verfityJDLocation(final int i2, final int i3, final int i4, final int i5, final double d, final double d2) {
        if (i2 != 0 && i3 != 0) {
            requestTabName(i2, i3, i4, i5, d, d2);
            return;
        }
        JDLocationOption jDLocationOption = new JDLocationOption();
        jDLocationOption.setBusinessId("15f39f78ae41d235baf6dfdc573ccd47");
        jDLocationOption.setNeedDetail(true);
        JDLocationSDK.getInstance().getAddress(jDLocationOption, new JDLocationListener() { // from class: com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager.3
            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onFail(JDLocationError jDLocationError) {
                HourlyGoObserverManager.this.requestTabName(i2, i3, i4, i5, d, d2);
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onSuccess(JDLocation jDLocation) {
                HourlyGoObserverManager.this.requestTabName(jDLocation.getProvinceId(), jDLocation.getCityId(), jDLocation.getDistrictId(), jDLocation.getTownId(), jDLocation.getLat(), jDLocation.getLng());
            }
        });
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    public void add(HourlyGoObserverListener hourlyGoObserverListener) {
        this.list.add(hourlyGoObserverListener);
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    public void dismissBackHomeBubble() {
        try {
            HourlyGoBackHomeBubbleView hourlyGoBackHomeBubbleView = this.backHomeBubbleView;
            if (hourlyGoBackHomeBubbleView != null) {
                hourlyGoBackHomeBubbleView.a();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
        }
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    public void dismissNearByBubble(boolean z) {
        try {
            HourlyGoGuideBubbleView hourlyGoGuideBubbleView = this.guideBubbleView;
            if (hourlyGoGuideBubbleView != null) {
                hourlyGoGuideBubbleView.a(z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
        }
    }

    public String getmFloorId() {
        return this.mFloorId;
    }

    public boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    public void notifyHeadUrlObserver(final String str, final int i2, final int i3) {
        runOnUiThread(new Runnable() { // from class: com.jingdong.pdj.libcore.watcher.a
            @Override // java.lang.Runnable
            public final void run() {
                HourlyGoObserverManager.this.b(str, i2, i3);
            }
        });
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    public void notifyTabNameObserver(final String str) {
        runOnUiThread(new Runnable() { // from class: com.jingdong.pdj.libcore.watcher.c
            @Override // java.lang.Runnable
            public final void run() {
                HourlyGoObserverManager.this.d(str);
            }
        });
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    public void notifyTabUrlObserver(final String str, final Map<String, Object> map, final JDJSONObject jDJSONObject) {
        runOnUiThread(new Runnable() { // from class: com.jingdong.pdj.libcore.watcher.b
            @Override // java.lang.Runnable
            public final void run() {
                HourlyGoObserverManager.this.f(str, map, jDJSONObject);
            }
        });
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    public void remove(HourlyGoObserverListener hourlyGoObserverListener) {
        if (this.list.contains(hourlyGoObserverListener)) {
            this.list.remove(hourlyGoObserverListener);
        }
    }

    public void runOnUiThread(@NotNull Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            sHandler.post(runnable);
        }
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    public void setFoorId(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mFloorId = str;
        HourlyGoTabUrlNetTask.getAddress();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0069 A[Catch: Exception -> 0x0073, TryCatch #0 {Exception -> 0x0073, blocks: (B:4:0x0003, B:6:0x0009, B:8:0x0010, B:7:0x000c, B:10:0x0013, B:12:0x0019, B:14:0x0027, B:16:0x003d, B:18:0x0045, B:20:0x0053, B:22:0x005b, B:28:0x0069, B:30:0x0070, B:29:0x006e), top: B:35:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006e A[Catch: Exception -> 0x0073, TryCatch #0 {Exception -> 0x0073, blocks: (B:4:0x0003, B:6:0x0009, B:8:0x0010, B:7:0x000c, B:10:0x0013, B:12:0x0019, B:14:0x0027, B:16:0x003d, B:18:0x0045, B:20:0x0053, B:22:0x005b, B:28:0x0069, B:30:0x0070, B:29:0x006e), top: B:35:0x0001 }] */
    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setIntent(android.content.Intent r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L13
            boolean r5 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Exception -> L73
            if (r5 != 0) goto Lc
            r4.sourceValue = r6     // Catch: java.lang.Exception -> L73
            goto L10
        Lc:
            java.lang.String r5 = "0"
            r4.sourceValue = r5     // Catch: java.lang.Exception -> L73
        L10:
            r4.hourgoInfo = r0     // Catch: java.lang.Exception -> L73
            return
        L13:
            android.os.Bundle r5 = r5.getExtras()     // Catch: java.lang.Exception -> L73
            if (r5 == 0) goto L66
            java.lang.String r6 = "params"
            java.lang.String r5 = r5.getString(r6)     // Catch: java.lang.Exception -> L73
            java.lang.Object r5 = com.jd.framework.json.JDJSON.parse(r5)     // Catch: java.lang.Exception -> L73
            boolean r6 = r5 instanceof com.jd.framework.json.JDJSONObject     // Catch: java.lang.Exception -> L73
            if (r6 == 0) goto L66
            com.jd.framework.json.JDJSONObject r5 = (com.jd.framework.json.JDJSONObject) r5     // Catch: java.lang.Exception -> L73
            java.lang.String r6 = "category"
            java.lang.String r6 = r5.optString(r6)     // Catch: java.lang.Exception -> L73
            java.lang.String r1 = "des"
            java.lang.String r1 = r5.optString(r1)     // Catch: java.lang.Exception -> L73
            java.lang.String r2 = "param"
            com.jd.framework.json.JDJSONObject r2 = r5.optJSONObject(r2)     // Catch: java.lang.Exception -> L73
            if (r2 == 0) goto L44
            java.lang.String r3 = "hourgoInfo"
            java.lang.String r2 = r2.optString(r3)     // Catch: java.lang.Exception -> L73
            goto L45
        L44:
            r2 = r0
        L45:
            java.lang.String r3 = "sourceValue"
            java.lang.String r5 = r5.optString(r3)     // Catch: java.lang.Exception -> L73
            java.lang.String r3 = "jump"
            boolean r6 = r3.equals(r6)     // Catch: java.lang.Exception -> L73
            if (r6 == 0) goto L67
            java.lang.String r6 = "HomePageTopTab"
            boolean r6 = r6.equals(r1)     // Catch: java.lang.Exception -> L73
            if (r6 == 0) goto L67
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L73
            if (r6 != 0) goto L63
            r0 = r5
            goto L67
        L63:
            java.lang.String r0 = "-100"
            goto L67
        L66:
            r2 = r0
        L67:
            if (r0 != 0) goto L6e
            java.lang.String r5 = "-200"
            r4.sourceValue = r5     // Catch: java.lang.Exception -> L73
            goto L70
        L6e:
            r4.sourceValue = r0     // Catch: java.lang.Exception -> L73
        L70:
            r4.hourgoInfo = r2     // Catch: java.lang.Exception -> L73
            return
        L73:
            r5 = move-exception
            r5.printStackTrace()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager.setIntent(android.content.Intent, java.lang.String):void");
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    public void setLocationInfo(JDLocation jDLocation, JDBusinessAddress jDBusinessAddress) {
        try {
            setTabNameCache();
            if (jDLocation != null) {
                verfityJDLocation(jDLocation.getProvinceId(), jDLocation.getCityId(), jDLocation.getDistrictId(), jDLocation.getTownId(), jDLocation.getLat(), jDLocation.getLng());
            } else if (jDBusinessAddress != null) {
                verfityJDLocation(jDBusinessAddress.getProvinceCode(), jDBusinessAddress.getCityCode(), jDBusinessAddress.getDistrictCode(), jDBusinessAddress.getTownCode(), jDBusinessAddress.getLat(), jDBusinessAddress.getLng());
            } else {
                requestTabName(0, 0, 0, 0, 0.0d, 0.0d);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0073, code lost:
        if (r1 != false) goto L28;
     */
    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void showBackHomeBubble(android.app.Activity r10, java.util.List<java.lang.String> r11, java.lang.String r12, java.lang.String r13, int r14, int r15) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager.showBackHomeBubble(android.app.Activity, java.util.List, java.lang.String, java.lang.String, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x02e5 A[EDGE_INSN: B:132:0x02e5->B:90:0x02e5 BREAK  A[LOOP:0: B:50:0x012d->B:89:0x02d2], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b2 A[Catch: Exception -> 0x004b, TRY_LEAVE, TryCatch #1 {Exception -> 0x004b, blocks: (B:18:0x0047, B:25:0x005f, B:30:0x00a5, B:37:0x00b2, B:40:0x00ce, B:46:0x00da), top: B:129:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ce A[Catch: Exception -> 0x004b, TRY_ENTER, TryCatch #1 {Exception -> 0x004b, blocks: (B:18:0x0047, B:25:0x005f, B:30:0x00a5, B:37:0x00b2, B:40:0x00ce, B:46:0x00da), top: B:129:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00da A[Catch: Exception -> 0x004b, TRY_LEAVE, TryCatch #1 {Exception -> 0x004b, blocks: (B:18:0x0047, B:25:0x005f, B:30:0x00a5, B:37:0x00b2, B:40:0x00ce, B:46:0x00da), top: B:129:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0239 A[Catch: Exception -> 0x044e, TryCatch #0 {Exception -> 0x044e, blocks: (B:3:0x0008, B:5:0x0019, B:7:0x0020, B:11:0x002a, B:13:0x0032, B:16:0x003a, B:22:0x004f, B:28:0x006c, B:38:0x00c0, B:48:0x00ed, B:54:0x013a, B:56:0x0140, B:58:0x0154, B:60:0x016e, B:64:0x0196, B:66:0x022d, B:72:0x0239, B:74:0x0244, B:80:0x0250, B:83:0x0266, B:87:0x0275, B:89:0x02d2, B:84:0x026a, B:85:0x026e, B:86:0x0272, B:90:0x02e5, B:93:0x030f, B:95:0x0338, B:97:0x0342, B:99:0x0368, B:101:0x0375, B:102:0x0378, B:103:0x0383, B:105:0x0389, B:106:0x038d, B:108:0x03df, B:109:0x03ea, B:111:0x03ee, B:112:0x03f6, B:114:0x03fa, B:115:0x03fd, B:119:0x0446, B:121:0x044a), top: B:128:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0250 A[Catch: Exception -> 0x044e, TryCatch #0 {Exception -> 0x044e, blocks: (B:3:0x0008, B:5:0x0019, B:7:0x0020, B:11:0x002a, B:13:0x0032, B:16:0x003a, B:22:0x004f, B:28:0x006c, B:38:0x00c0, B:48:0x00ed, B:54:0x013a, B:56:0x0140, B:58:0x0154, B:60:0x016e, B:64:0x0196, B:66:0x022d, B:72:0x0239, B:74:0x0244, B:80:0x0250, B:83:0x0266, B:87:0x0275, B:89:0x02d2, B:84:0x026a, B:85:0x026e, B:86:0x0272, B:90:0x02e5, B:93:0x030f, B:95:0x0338, B:97:0x0342, B:99:0x0368, B:101:0x0375, B:102:0x0378, B:103:0x0383, B:105:0x0389, B:106:0x038d, B:108:0x03df, B:109:0x03ea, B:111:0x03ee, B:112:0x03f6, B:114:0x03fa, B:115:0x03fd, B:119:0x0446, B:121:0x044a), top: B:128:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x026e A[Catch: Exception -> 0x044e, TryCatch #0 {Exception -> 0x044e, blocks: (B:3:0x0008, B:5:0x0019, B:7:0x0020, B:11:0x002a, B:13:0x0032, B:16:0x003a, B:22:0x004f, B:28:0x006c, B:38:0x00c0, B:48:0x00ed, B:54:0x013a, B:56:0x0140, B:58:0x0154, B:60:0x016e, B:64:0x0196, B:66:0x022d, B:72:0x0239, B:74:0x0244, B:80:0x0250, B:83:0x0266, B:87:0x0275, B:89:0x02d2, B:84:0x026a, B:85:0x026e, B:86:0x0272, B:90:0x02e5, B:93:0x030f, B:95:0x0338, B:97:0x0342, B:99:0x0368, B:101:0x0375, B:102:0x0378, B:103:0x0383, B:105:0x0389, B:106:0x038d, B:108:0x03df, B:109:0x03ea, B:111:0x03ee, B:112:0x03f6, B:114:0x03fa, B:115:0x03fd, B:119:0x0446, B:121:0x044a), top: B:128:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0338 A[Catch: Exception -> 0x044e, TryCatch #0 {Exception -> 0x044e, blocks: (B:3:0x0008, B:5:0x0019, B:7:0x0020, B:11:0x002a, B:13:0x0032, B:16:0x003a, B:22:0x004f, B:28:0x006c, B:38:0x00c0, B:48:0x00ed, B:54:0x013a, B:56:0x0140, B:58:0x0154, B:60:0x016e, B:64:0x0196, B:66:0x022d, B:72:0x0239, B:74:0x0244, B:80:0x0250, B:83:0x0266, B:87:0x0275, B:89:0x02d2, B:84:0x026a, B:85:0x026e, B:86:0x0272, B:90:0x02e5, B:93:0x030f, B:95:0x0338, B:97:0x0342, B:99:0x0368, B:101:0x0375, B:102:0x0378, B:103:0x0383, B:105:0x0389, B:106:0x038d, B:108:0x03df, B:109:0x03ea, B:111:0x03ee, B:112:0x03f6, B:114:0x03fa, B:115:0x03fd, B:119:0x0446, B:121:0x044a), top: B:128:0x0008 }] */
    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void showNearByBubble(android.app.Activity r33, android.graphics.PointF r34, com.jd.framework.json.JDJSONObject r35) {
        /*
            Method dump skipped, instructions count: 1122
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager.showNearByBubble(android.app.Activity, android.graphics.PointF, com.jd.framework.json.JDJSONObject):void");
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObservableListener
    public void setLocationInfo(JDYFAddress jDYFAddress) {
        try {
            if (jDYFAddress != null) {
                requestTabName((int) jDYFAddress.getProvinceCode(), (int) jDYFAddress.getCityCode(), (int) jDYFAddress.getDistrictCode(), (int) jDYFAddress.getTownCode(), jDYFAddress.getLat(), jDYFAddress.getLng());
            } else {
                requestTabName(0, 0, 0, 0, 0.0d, 0.0d);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
