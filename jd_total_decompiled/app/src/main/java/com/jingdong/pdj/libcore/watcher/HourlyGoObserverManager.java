package com.jingdong.pdj.libcore.watcher;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.entity.cart.CartPromotion;
import com.jingdong.common.jump.JumpUtil;
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
import com.jingdong.pdj.libcore.bubble.HourlyGoBaseBubbleView;
import com.jingdong.pdj.libcore.bubble.HourlyGoGuideBubbleView;
import com.jingdong.pdj.libcore.net.HourlyGoTabNameTask;
import com.jingdong.pdj.libcore.net.HourlyGoTabUrlNetTask;
import com.jingdong.pdj.libcore.point.HourlyFloorMaiDianJson;
import com.jingdong.pdj.libcore.point.HourlyGoHomeMaiDianUpload;
import com.jingdong.pdj.libcore.point.PointData;
import com.jingdong.pdj.libcore.screen.HourlyGoDpi750;
import com.jingdong.pdj.libcore.screen.HourlyLayoutSize;
import com.jingdong.pdj.libcore.utils.HourlyGoAddressHelper;
import com.jingdong.pdj.libcore.utils.HourlyGoColorUtil;
import com.jingdong.pdj.libcore.utils.HourlyGoFontUtils;
import com.jingdong.pdj.libcore.utils.HourlyGoImageLoadUtil;
import com.jingdong.pdj.libcore.utils.HourlyGoLibConstant;
import com.jingdong.remoteimage.RemoteImageManager;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
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
    */
    public void setIntent(Intent intent, String str) {
        String str2;
        String str3 = null;
        try {
            if (intent == null) {
                if (!TextUtils.isEmpty(str)) {
                    this.sourceValue = str;
                } else {
                    this.sourceValue = "0";
                }
                this.hourgoInfo = null;
                return;
            }
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Object parse = JDJSON.parse(extras.getString("params"));
                if (parse instanceof JDJSONObject) {
                    JDJSONObject jDJSONObject = (JDJSONObject) parse;
                    String optString = jDJSONObject.optString("category");
                    String optString2 = jDJSONObject.optString("des");
                    JDJSONObject optJSONObject = jDJSONObject.optJSONObject("param");
                    str2 = optJSONObject != null ? optJSONObject.optString("hourgoInfo") : null;
                    String optString3 = jDJSONObject.optString("sourceValue");
                    if ("jump".equals(optString) && JumpUtil.VALUE_DES_APPHOMETOPTAB.equals(optString2)) {
                        str3 = !TextUtils.isEmpty(optString3) ? optString3 : "-100";
                    }
                    if (str3 != null) {
                        this.sourceValue = HOURLY_GO_SOURCE_VALUE_ERROR;
                    } else {
                        this.sourceValue = str3;
                    }
                    this.hourgoInfo = str2;
                }
            }
            str2 = null;
            if (str3 != null) {
            }
            this.hourgoInfo = str2;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
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
    */
    public void showBackHomeBubble(Activity activity, List<String> list, String str, String str2, int i2, int i3) {
        boolean isBlank;
        if (list == null || TextUtils.isEmpty(str) || !list.contains(str)) {
            return;
        }
        try {
            Iterator<HourlyGoObserverListener> it = this.list.iterator();
            if (it.hasNext()) {
                PointF homeTopTabPoint = it.next().getHomeTopTabPoint();
                HourlyGoBackHomeBubbleView hourlyGoBackHomeBubbleView = new HourlyGoBackHomeBubbleView(activity);
                this.backHomeBubbleView = hourlyGoBackHomeBubbleView;
                Integer valueOf = Integer.valueOf(i2);
                Integer valueOf2 = Integer.valueOf(i3);
                HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener = new HourlyGoGuideBubbleListener() { // from class: com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager.2
                    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoGuideBubbleListener
                    public PointF getHomeTopTabPoint() {
                        return null;
                    }

                    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoGuideBubbleListener
                    public void onNearByBubbleCallBack(int i4) {
                        if (2 == i4) {
                            HourlyGoObserverManager.this.backHomeBubbleView = null;
                        }
                    }
                };
                long currentTimeMillis = System.currentTimeMillis() - CommonBase.getLongFromPreference("hourly_go_back_home_show_time", 0L);
                boolean z = false;
                if (currentTimeMillis > 0 && currentTimeMillis < 86400000) {
                    return;
                }
                hourlyGoBackHomeBubbleView.setBubbleListener(hourlyGoGuideBubbleListener);
                if (homeTopTabPoint != null && homeTopTabPoint.x != 0.0f && homeTopTabPoint.y != 0.0f) {
                    if (str2 != null) {
                        isBlank = StringsKt__StringsJVMKt.isBlank(str2);
                    }
                    z = true;
                    if (z) {
                        return;
                    }
                    hourlyGoBackHomeBubbleView.setRootView(HourlyGoBaseBubbleView.a(activity));
                    if (hourlyGoBackHomeBubbleView.getRootView() != null) {
                        HourlyGoImageLoadUtil.displayImageUseSuper(str2, hourlyGoBackHomeBubbleView.a);
                        hourlyGoBackHomeBubbleView.b.setWidth((valueOf == null || valueOf.intValue() <= 0 || valueOf2 == null || valueOf2.intValue() <= 0) ? 272 : (int) ((valueOf.intValue() * 64.0f) / valueOf2.intValue()));
                        HourlyLayoutSize.checkSizeChanged((View) hourlyGoBackHomeBubbleView.a, hourlyGoBackHomeBubbleView.b, true);
                        if (hourlyGoBackHomeBubbleView.d) {
                            return;
                        }
                        hourlyGoBackHomeBubbleView.setShowing(true);
                        CommonBase.putLongToPreference("hourly_go_back_home_show_time", System.currentTimeMillis());
                        if (hourlyGoBackHomeBubbleView.getRootView() != null) {
                            int sizeBy750 = HourlyGoDpi750.getSizeBy750((int) (((r13 * 64) * 1.0f) / 64.0f));
                            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(sizeBy750, HourlyGoDpi750.getSizeBy750(64));
                            double d = homeTopTabPoint.x;
                            double d2 = sizeBy750;
                            Double.isNaN(d2);
                            Double.isNaN(d);
                            marginLayoutParams.leftMargin = (int) (d - ((d2 * 1.0d) / 2.0d));
                            marginLayoutParams.topMargin = (int) homeTopTabPoint.y;
                            ViewGroup rootView = hourlyGoBackHomeBubbleView.getRootView();
                            if (rootView == null) {
                                Intrinsics.throwNpe();
                            }
                            rootView.addView(hourlyGoBackHomeBubbleView, marginLayoutParams);
                        }
                        hourlyGoBackHomeBubbleView.postDelayed(new HourlyGoBackHomeBubbleView.c(), 3000L);
                    }
                }
            }
        } catch (Exception e2) {
            dismissBackHomeBubble();
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
        }
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
    */
    public void showNearByBubble(Activity activity, PointF pointF, JDJSONObject jDJSONObject) {
        boolean z;
        String str;
        boolean z2;
        String optString;
        boolean z3;
        JDJSONArray jDJSONArray;
        int size;
        int i2;
        String str2;
        int i3;
        JDJSONArray jDJSONArray2;
        JDJSONArray jDJSONArray3;
        HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        int i4;
        boolean z4;
        boolean z5;
        boolean isBlank;
        boolean isBlank2;
        boolean isBlank3;
        boolean isBlank4;
        HourlyGoObserverManager hourlyGoObserverManager = this;
        try {
            HourlyGoGuideBubbleView hourlyGoGuideBubbleView = new HourlyGoGuideBubbleView(activity);
            hourlyGoObserverManager.guideBubbleView = hourlyGoGuideBubbleView;
            HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener2 = new HourlyGoGuideBubbleListener() { // from class: com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager.1
                @Override // com.jingdong.pdj.libcore.watcher.HourlyGoGuideBubbleListener
                public PointF getHomeTopTabPoint() {
                    return null;
                }

                @Override // com.jingdong.pdj.libcore.watcher.HourlyGoGuideBubbleListener
                public void onNearByBubbleCallBack(int i5) {
                    Iterator it = HourlyGoObserverManager.this.list.iterator();
                    while (it.hasNext()) {
                        ((HourlyGoObserverListener) it.next()).onNearByBubbleCallBack(i5);
                    }
                    if (2 == i5) {
                        HourlyGoObserverManager.this.guideBubbleView = null;
                    }
                }
            };
            hourlyGoGuideBubbleView.setBubbleListener(hourlyGoGuideBubbleListener2);
            if (pointF != null && pointF.x != 0.0f && pointF.y != 0.0f && jDJSONObject != null) {
                JDJSONArray optJSONArray = jDJSONObject.optJSONArray("productVOList");
                if (optJSONArray != null && optJSONArray.size() > 0) {
                    hourlyGoGuideBubbleView.setRootView(HourlyGoBaseBubbleView.a(activity));
                    try {
                        if (hourlyGoGuideBubbleView.getRootView() == null) {
                            hourlyGoGuideBubbleView.b();
                            return;
                        }
                        String optString2 = jDJSONObject.optString("index");
                        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("bgImage");
                        String str8 = "";
                        if (optJSONObject != null) {
                            str = optJSONObject.optString("imgUrl");
                            Intrinsics.checkExpressionValueIsNotNull(str, "bgImageObject.optString(\"imgUrl\")");
                        } else {
                            str = "";
                        }
                        GradientDrawable gradientDrawable = new GradientDrawable();
                        gradientDrawable.setColor(-6825393);
                        gradientDrawable.setCornerRadius(HourlyGoDpi750.getSizeBy750(24));
                        HourlyGoImageLoadUtil.displayImageUseSuper(str, hourlyGoGuideBubbleView.a, new JDDisplayImageOptions().showImageOnFail(gradientDrawable).showImageOnLoading(gradientDrawable).showImageForEmptyUri(gradientDrawable));
                        String optString3 = jDJSONObject.optString("mainTitle");
                        String optString4 = jDJSONObject.optString("mainTitleColor");
                        try {
                            if (optString3 != null) {
                                isBlank4 = StringsKt__StringsJVMKt.isBlank(optString3);
                                if (!isBlank4) {
                                    z2 = false;
                                    if (!z2) {
                                        hourlyGoGuideBubbleView.f14654g.setText(optString3);
                                        hourlyGoGuideBubbleView.f14654g.setTextColor(HourlyGoColorUtil.getColorValueByNetString(optString4, -1));
                                    }
                                    optString = jDJSONObject.optString("secondTitle");
                                    String optString5 = jDJSONObject.optString("secondTitleColor");
                                    if (optString != null) {
                                        isBlank3 = StringsKt__StringsJVMKt.isBlank(optString);
                                        if (!isBlank3) {
                                            z3 = false;
                                            if (!z3) {
                                                hourlyGoGuideBubbleView.f14656i.setText(optString);
                                                hourlyGoGuideBubbleView.f14656i.setTextColor(HourlyGoColorUtil.getColorValueByNetString(optString5, -592138));
                                            }
                                            int sizeBy750 = HourlyGoDpi750.getSizeBy750(9);
                                            hourlyGoGuideBubbleView.f14658k.setPadding(sizeBy750, sizeBy750, sizeBy750, sizeBy750);
                                            HourlyGoImageLoadUtil.displayRemoteImage("115_3368", RemoteImageManager.XXHDPI, new HourlyGoGuideBubbleView.a(activity, jDJSONObject.optString("closeButtonColor")));
                                            jDJSONArray = new JDJSONArray();
                                            TextPaint textPaint = new TextPaint();
                                            textPaint.setTextSize(HourlyGoDpi750.getSizeBy750(22));
                                            int sizeBy7502 = HourlyGoDpi750.getSizeBy750(64);
                                            int sizeBy7503 = HourlyGoDpi750.getSizeBy750(88);
                                            size = optJSONArray.size();
                                            i2 = 0;
                                            while (true) {
                                                str2 = str8;
                                                if (i2 >= size) {
                                                    break;
                                                }
                                                i3 = size;
                                                if (i2 >= 3) {
                                                    break;
                                                }
                                                JDJSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                                                if (optJSONObject2 != null) {
                                                    jDJSONArray2 = optJSONArray;
                                                    String optString6 = optJSONObject2.optString("picUrl");
                                                    String optString7 = optJSONObject2.optString("srvJson");
                                                    JDJSONObject optJSONObject3 = optJSONObject2.optJSONObject("majorPrice");
                                                    if (optJSONObject3 != null) {
                                                        str5 = optJSONObject3.optString("price");
                                                        Intrinsics.checkExpressionValueIsNotNull(str5, "majorPriceObject.optString(\"price\")");
                                                        str4 = optJSONObject3.optString(CartPromotion.KEY_PRICECOLOR);
                                                        Intrinsics.checkExpressionValueIsNotNull(str4, "majorPriceObject.optString(\"priceColor\")");
                                                    } else {
                                                        str4 = str2;
                                                        str5 = str4;
                                                    }
                                                    RelativeLayout relativeLayout = new RelativeLayout(activity);
                                                    HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener3 = hourlyGoGuideBubbleListener2;
                                                    HourlyLayoutSize hourlyLayoutSize = new HourlyLayoutSize(122, 122);
                                                    ViewGroup.LayoutParams lLParams = hourlyLayoutSize.getLLParams(relativeLayout);
                                                    JDJSONArray jDJSONArray4 = jDJSONArray;
                                                    if (i2 == 0) {
                                                        str6 = "position";
                                                        str7 = optString2;
                                                        i4 = 8;
                                                    } else {
                                                        str6 = "position";
                                                        str7 = optString2;
                                                        i4 = 4;
                                                    }
                                                    hourlyLayoutSize.setMargin(new Rect(i4, 0, 0, 0), lLParams);
                                                    hourlyGoGuideBubbleView.f14660m.addView(relativeLayout, lLParams);
                                                    SimpleDraweeView simpleDraweeView = new SimpleDraweeView(activity);
                                                    simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                                                    Unit unit = Unit.INSTANCE;
                                                    relativeLayout.addView(simpleDraweeView, new HourlyLayoutSize(122, 122).getRLParams(simpleDraweeView));
                                                    HourlyGoImageLoadUtil.displayImageUseSuper(optString6, simpleDraweeView, HourlyGoImageLoadUtil.sHourGoOptions);
                                                    TextView textView = new TextView(activity);
                                                    textView.setEllipsize(TextUtils.TruncateAt.END);
                                                    textView.setMaxLines(1);
                                                    textView.setSingleLine(true);
                                                    textView.setIncludeFontPadding(false);
                                                    textView.setTextSize(0, HourlyGoDpi750.getSizeBy750(22));
                                                    textView.setGravity(17);
                                                    GradientDrawable gradientDrawable2 = new GradientDrawable();
                                                    gradientDrawable2.setColor(-3600);
                                                    gradientDrawable2.setStroke(HourlyGoDpi750.getSizeBy750(1), -1);
                                                    gradientDrawable2.setCornerRadius(HourlyGoDpi750.getSizeBy750(16));
                                                    textView.setBackground(gradientDrawable2);
                                                    textView.setTextColor(-381927);
                                                    HourlyLayoutSize hourlyLayoutSize2 = new HourlyLayoutSize(0, 28);
                                                    RelativeLayout.LayoutParams rLParams = hourlyLayoutSize2.getRLParams(textView);
                                                    rLParams.addRule(12);
                                                    rLParams.addRule(14);
                                                    relativeLayout.addView(textView, rLParams);
                                                    textView.setTextColor(HourlyGoColorUtil.getColorValueByNetString(str4, -381927));
                                                    if (str5 != null) {
                                                        isBlank2 = StringsKt__StringsJVMKt.isBlank(str5);
                                                        if (!isBlank2) {
                                                            z4 = false;
                                                            if (!z4) {
                                                                HourlyGoFontUtils.setPrice(textView, str5, 11, 11, 11);
                                                                CharSequence text = textView.getText();
                                                                if (text != null) {
                                                                    isBlank = StringsKt__StringsJVMKt.isBlank(text);
                                                                    if (!isBlank) {
                                                                        z5 = false;
                                                                        if (z5) {
                                                                            int measureText = ((int) textPaint.measureText(textView.getText().toString())) + HourlyGoDpi750.getSizeBy750(16);
                                                                            if (measureText >= sizeBy7502) {
                                                                                if (measureText > sizeBy7503) {
                                                                                    hourlyLayoutSize2.setOffsetWidth(sizeBy7503);
                                                                                } else {
                                                                                    hourlyLayoutSize2.setOffsetWidth(measureText);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            hourlyLayoutSize2.setOffsetWidth(sizeBy7502);
                                                                        }
                                                                        HourlyLayoutSize.checkSizeChanged((View) textView, hourlyLayoutSize2, true);
                                                                        HourlyFloorMaiDianJson build = HourlyFloorMaiDianJson.build(optString7);
                                                                        StringBuilder sb = new StringBuilder();
                                                                        str3 = str7;
                                                                        sb.append(str3);
                                                                        sb.append("#2#");
                                                                        sb.append(i2 + 1);
                                                                        build.addInfo(str6, sb.toString());
                                                                        String jSONObject = build.toString();
                                                                        Intrinsics.checkExpressionValueIsNotNull(jSONObject, "build.toString()");
                                                                        jDJSONArray3 = jDJSONArray4;
                                                                        jDJSONArray3.add(jSONObject);
                                                                        hourlyGoGuideBubbleListener = hourlyGoGuideBubbleListener3;
                                                                        relativeLayout.setOnClickListener(new HourlyGoGuideBubbleView.b(hourlyGoGuideBubbleListener, new PointData("Home_Nearby_Main", HourlyGoHomeMaiDianUpload.getPageName(false), "Home_Nearby_DiverGoods", "", jSONObject, false, false, false)));
                                                                    }
                                                                }
                                                                z5 = true;
                                                                if (z5) {
                                                                }
                                                                HourlyLayoutSize.checkSizeChanged((View) textView, hourlyLayoutSize2, true);
                                                                HourlyFloorMaiDianJson build2 = HourlyFloorMaiDianJson.build(optString7);
                                                                StringBuilder sb2 = new StringBuilder();
                                                                str3 = str7;
                                                                sb2.append(str3);
                                                                sb2.append("#2#");
                                                                sb2.append(i2 + 1);
                                                                build2.addInfo(str6, sb2.toString());
                                                                String jSONObject2 = build2.toString();
                                                                Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "build.toString()");
                                                                jDJSONArray3 = jDJSONArray4;
                                                                jDJSONArray3.add(jSONObject2);
                                                                hourlyGoGuideBubbleListener = hourlyGoGuideBubbleListener3;
                                                                relativeLayout.setOnClickListener(new HourlyGoGuideBubbleView.b(hourlyGoGuideBubbleListener, new PointData("Home_Nearby_Main", HourlyGoHomeMaiDianUpload.getPageName(false), "Home_Nearby_DiverGoods", "", jSONObject2, false, false, false)));
                                                            }
                                                            hourlyLayoutSize2.setOffsetWidth(sizeBy7502);
                                                            HourlyLayoutSize.checkSizeChanged((View) textView, hourlyLayoutSize2, true);
                                                            HourlyFloorMaiDianJson build22 = HourlyFloorMaiDianJson.build(optString7);
                                                            StringBuilder sb22 = new StringBuilder();
                                                            str3 = str7;
                                                            sb22.append(str3);
                                                            sb22.append("#2#");
                                                            sb22.append(i2 + 1);
                                                            build22.addInfo(str6, sb22.toString());
                                                            String jSONObject22 = build22.toString();
                                                            Intrinsics.checkExpressionValueIsNotNull(jSONObject22, "build.toString()");
                                                            jDJSONArray3 = jDJSONArray4;
                                                            jDJSONArray3.add(jSONObject22);
                                                            hourlyGoGuideBubbleListener = hourlyGoGuideBubbleListener3;
                                                            relativeLayout.setOnClickListener(new HourlyGoGuideBubbleView.b(hourlyGoGuideBubbleListener, new PointData("Home_Nearby_Main", HourlyGoHomeMaiDianUpload.getPageName(false), "Home_Nearby_DiverGoods", "", jSONObject22, false, false, false)));
                                                        }
                                                    }
                                                    z4 = true;
                                                    if (!z4) {
                                                    }
                                                    hourlyLayoutSize2.setOffsetWidth(sizeBy7502);
                                                    HourlyLayoutSize.checkSizeChanged((View) textView, hourlyLayoutSize2, true);
                                                    HourlyFloorMaiDianJson build222 = HourlyFloorMaiDianJson.build(optString7);
                                                    StringBuilder sb222 = new StringBuilder();
                                                    str3 = str7;
                                                    sb222.append(str3);
                                                    sb222.append("#2#");
                                                    sb222.append(i2 + 1);
                                                    build222.addInfo(str6, sb222.toString());
                                                    String jSONObject222 = build222.toString();
                                                    Intrinsics.checkExpressionValueIsNotNull(jSONObject222, "build.toString()");
                                                    jDJSONArray3 = jDJSONArray4;
                                                    jDJSONArray3.add(jSONObject222);
                                                    hourlyGoGuideBubbleListener = hourlyGoGuideBubbleListener3;
                                                    relativeLayout.setOnClickListener(new HourlyGoGuideBubbleView.b(hourlyGoGuideBubbleListener, new PointData("Home_Nearby_Main", HourlyGoHomeMaiDianUpload.getPageName(false), "Home_Nearby_DiverGoods", "", jSONObject222, false, false, false)));
                                                } else {
                                                    jDJSONArray2 = optJSONArray;
                                                    jDJSONArray3 = jDJSONArray;
                                                    hourlyGoGuideBubbleListener = hourlyGoGuideBubbleListener2;
                                                    str3 = optString2;
                                                }
                                                i2++;
                                                jDJSONArray = jDJSONArray3;
                                                optString2 = str3;
                                                hourlyGoGuideBubbleListener2 = hourlyGoGuideBubbleListener;
                                                str8 = str2;
                                                size = i3;
                                                optJSONArray = jDJSONArray2;
                                            }
                                            HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener4 = hourlyGoGuideBubbleListener2;
                                            JDJSONArray jDJSONArray5 = jDJSONArray;
                                            HourlyFloorMaiDianJson build3 = HourlyFloorMaiDianJson.build(jDJSONObject.optString("srvJson"));
                                            build3.addInfo("position", optString2 + "#1#1");
                                            z = false;
                                            PointData pointData = new PointData("Home_Nearby_Main", HourlyGoHomeMaiDianUpload.getPageName(false), "Home_Nearby_DiverGoods", "", build3.toString(), false, false, false);
                                            hourlyGoGuideBubbleView.f14658k.setOnClickListener(new HourlyGoGuideBubbleView.c(hourlyGoGuideBubbleListener4));
                                            hourlyGoGuideBubbleView.setOnClickListener(new HourlyGoGuideBubbleView.d(hourlyGoGuideBubbleListener4, pointData));
                                            if (!hourlyGoGuideBubbleView.d) {
                                                hourlyGoGuideBubbleView.setShowing(true);
                                                if (hourlyGoGuideBubbleView.getRootView() != null) {
                                                    int sizeBy7504 = HourlyGoDpi750.getSizeBy750(406);
                                                    ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(sizeBy7504, HourlyGoDpi750.getSizeBy750(214));
                                                    double d = pointF.x;
                                                    double d2 = sizeBy7504;
                                                    Double.isNaN(d2);
                                                    double d3 = (d2 * 1.0d) / 2.0d;
                                                    Double.isNaN(d);
                                                    marginLayoutParams.leftMargin = (int) (d - d3);
                                                    marginLayoutParams.topMargin = (int) pointF.y;
                                                    ViewGroup rootView = hourlyGoGuideBubbleView.getRootView();
                                                    if (rootView == null) {
                                                        Intrinsics.throwNpe();
                                                    }
                                                    rootView.addView(hourlyGoGuideBubbleView, marginLayoutParams);
                                                    hourlyGoGuideBubbleView.setPivotX((float) d3);
                                                    hourlyGoGuideBubbleView.setPivotY(0.0f);
                                                }
                                                HourlyGoGuideBubbleListener b = hourlyGoGuideBubbleView.getB();
                                                if (b != null) {
                                                    b.onNearByBubbleCallBack(1);
                                                }
                                                ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(hourlyGoGuideBubbleView, PropertyValuesHolder.ofFloat(RelativeLayout.SCALE_X, 0.0f, 1.0f), PropertyValuesHolder.ofFloat(RelativeLayout.SCALE_Y, 0.0f, 1.0f));
                                                Intrinsics.checkExpressionValueIsNotNull(ofPropertyValuesHolder, "ObjectAnimator.ofPropert\u2026der(this, scaleX, scaleY)");
                                                ofPropertyValuesHolder.setDuration(450L);
                                                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(hourlyGoGuideBubbleView, RelativeLayout.ALPHA, 0.0f, 1.0f);
                                                Intrinsics.checkExpressionValueIsNotNull(ofFloat, "ObjectAnimator.ofFloat(this, ALPHA, 0f, 1f)");
                                                ofFloat.setDuration(450L);
                                                AnimatorSet animatorSet = new AnimatorSet();
                                                hourlyGoGuideBubbleView.o = animatorSet;
                                                if (animatorSet != null) {
                                                    animatorSet.playTogether(ofPropertyValuesHolder, ofFloat);
                                                }
                                                AnimatorSet animatorSet2 = hourlyGoGuideBubbleView.o;
                                                if (animatorSet2 != null) {
                                                    animatorSet2.addListener(new HourlyGoGuideBubbleView.g());
                                                }
                                                AnimatorSet animatorSet3 = hourlyGoGuideBubbleView.o;
                                                if (animatorSet3 != null) {
                                                    animatorSet3.start();
                                                }
                                            }
                                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.a, hourlyGoGuideBubbleView.b, true);
                                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14658k, hourlyGoGuideBubbleView.f14659l, true);
                                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14652c, hourlyGoGuideBubbleView.f14653f, true);
                                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14654g, hourlyGoGuideBubbleView.f14655h, true);
                                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14656i, hourlyGoGuideBubbleView.f14657j, true);
                                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14660m, hourlyGoGuideBubbleView.f14661n, true);
                                            HourlyGoHomeMaiDianUpload.sendExpoMtaData(new PointData("Home_Nearby_Main", HourlyGoHomeMaiDianUpload.getPageName(false), "", "Home_Nearby_DiverGoodsExpo", jDJSONArray5.toString(), false, false, false));
                                            return;
                                        }
                                    }
                                    z3 = true;
                                    if (!z3) {
                                    }
                                    int sizeBy7505 = HourlyGoDpi750.getSizeBy750(9);
                                    hourlyGoGuideBubbleView.f14658k.setPadding(sizeBy7505, sizeBy7505, sizeBy7505, sizeBy7505);
                                    HourlyGoImageLoadUtil.displayRemoteImage("115_3368", RemoteImageManager.XXHDPI, new HourlyGoGuideBubbleView.a(activity, jDJSONObject.optString("closeButtonColor")));
                                    jDJSONArray = new JDJSONArray();
                                    TextPaint textPaint2 = new TextPaint();
                                    textPaint2.setTextSize(HourlyGoDpi750.getSizeBy750(22));
                                    int sizeBy75022 = HourlyGoDpi750.getSizeBy750(64);
                                    int sizeBy75032 = HourlyGoDpi750.getSizeBy750(88);
                                    size = optJSONArray.size();
                                    i2 = 0;
                                    while (true) {
                                        str2 = str8;
                                        if (i2 >= size) {
                                        }
                                        i2++;
                                        jDJSONArray = jDJSONArray3;
                                        optString2 = str3;
                                        hourlyGoGuideBubbleListener2 = hourlyGoGuideBubbleListener;
                                        str8 = str2;
                                        size = i3;
                                        optJSONArray = jDJSONArray2;
                                    }
                                    HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener42 = hourlyGoGuideBubbleListener2;
                                    JDJSONArray jDJSONArray52 = jDJSONArray;
                                    HourlyFloorMaiDianJson build32 = HourlyFloorMaiDianJson.build(jDJSONObject.optString("srvJson"));
                                    build32.addInfo("position", optString2 + "#1#1");
                                    z = false;
                                    PointData pointData2 = new PointData("Home_Nearby_Main", HourlyGoHomeMaiDianUpload.getPageName(false), "Home_Nearby_DiverGoods", "", build32.toString(), false, false, false);
                                    hourlyGoGuideBubbleView.f14658k.setOnClickListener(new HourlyGoGuideBubbleView.c(hourlyGoGuideBubbleListener42));
                                    hourlyGoGuideBubbleView.setOnClickListener(new HourlyGoGuideBubbleView.d(hourlyGoGuideBubbleListener42, pointData2));
                                    if (!hourlyGoGuideBubbleView.d) {
                                    }
                                    HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.a, hourlyGoGuideBubbleView.b, true);
                                    HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14658k, hourlyGoGuideBubbleView.f14659l, true);
                                    HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14652c, hourlyGoGuideBubbleView.f14653f, true);
                                    HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14654g, hourlyGoGuideBubbleView.f14655h, true);
                                    HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14656i, hourlyGoGuideBubbleView.f14657j, true);
                                    HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14660m, hourlyGoGuideBubbleView.f14661n, true);
                                    HourlyGoHomeMaiDianUpload.sendExpoMtaData(new PointData("Home_Nearby_Main", HourlyGoHomeMaiDianUpload.getPageName(false), "", "Home_Nearby_DiverGoodsExpo", jDJSONArray52.toString(), false, false, false));
                                    return;
                                }
                            }
                            PointData pointData22 = new PointData("Home_Nearby_Main", HourlyGoHomeMaiDianUpload.getPageName(false), "Home_Nearby_DiverGoods", "", build32.toString(), false, false, false);
                            hourlyGoGuideBubbleView.f14658k.setOnClickListener(new HourlyGoGuideBubbleView.c(hourlyGoGuideBubbleListener42));
                            hourlyGoGuideBubbleView.setOnClickListener(new HourlyGoGuideBubbleView.d(hourlyGoGuideBubbleListener42, pointData22));
                            if (!hourlyGoGuideBubbleView.d) {
                            }
                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.a, hourlyGoGuideBubbleView.b, true);
                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14658k, hourlyGoGuideBubbleView.f14659l, true);
                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14652c, hourlyGoGuideBubbleView.f14653f, true);
                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14654g, hourlyGoGuideBubbleView.f14655h, true);
                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14656i, hourlyGoGuideBubbleView.f14657j, true);
                            HourlyLayoutSize.checkSizeChanged((View) hourlyGoGuideBubbleView.f14660m, hourlyGoGuideBubbleView.f14661n, true);
                            HourlyGoHomeMaiDianUpload.sendExpoMtaData(new PointData("Home_Nearby_Main", HourlyGoHomeMaiDianUpload.getPageName(false), "", "Home_Nearby_DiverGoodsExpo", jDJSONArray52.toString(), false, false, false));
                            return;
                        } catch (Exception e2) {
                            e = e2;
                            hourlyGoObserverManager = this;
                            hourlyGoObserverManager.dismissNearByBubble(z);
                            e.printStackTrace();
                            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e));
                            return;
                        }
                        z2 = true;
                        if (!z2) {
                        }
                        optString = jDJSONObject.optString("secondTitle");
                        String optString52 = jDJSONObject.optString("secondTitleColor");
                        if (optString != null) {
                        }
                        z3 = true;
                        if (!z3) {
                        }
                        int sizeBy75052 = HourlyGoDpi750.getSizeBy750(9);
                        hourlyGoGuideBubbleView.f14658k.setPadding(sizeBy75052, sizeBy75052, sizeBy75052, sizeBy75052);
                        HourlyGoImageLoadUtil.displayRemoteImage("115_3368", RemoteImageManager.XXHDPI, new HourlyGoGuideBubbleView.a(activity, jDJSONObject.optString("closeButtonColor")));
                        jDJSONArray = new JDJSONArray();
                        TextPaint textPaint22 = new TextPaint();
                        textPaint22.setTextSize(HourlyGoDpi750.getSizeBy750(22));
                        int sizeBy750222 = HourlyGoDpi750.getSizeBy750(64);
                        int sizeBy750322 = HourlyGoDpi750.getSizeBy750(88);
                        size = optJSONArray.size();
                        i2 = 0;
                        while (true) {
                            str2 = str8;
                            if (i2 >= size) {
                            }
                            i2++;
                            jDJSONArray = jDJSONArray3;
                            optString2 = str3;
                            hourlyGoGuideBubbleListener2 = hourlyGoGuideBubbleListener;
                            str8 = str2;
                            size = i3;
                            optJSONArray = jDJSONArray2;
                        }
                        HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener422 = hourlyGoGuideBubbleListener2;
                        JDJSONArray jDJSONArray522 = jDJSONArray;
                        HourlyFloorMaiDianJson build322 = HourlyFloorMaiDianJson.build(jDJSONObject.optString("srvJson"));
                        build322.addInfo("position", optString2 + "#1#1");
                        z = false;
                    } catch (Exception e3) {
                        e = e3;
                        z = false;
                        hourlyGoObserverManager.dismissNearByBubble(z);
                        e.printStackTrace();
                        OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e));
                        return;
                    }
                }
                hourlyGoGuideBubbleView.b();
                return;
            }
            hourlyGoGuideBubbleView.b();
        } catch (Exception e4) {
            e = e4;
            z = false;
        }
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
