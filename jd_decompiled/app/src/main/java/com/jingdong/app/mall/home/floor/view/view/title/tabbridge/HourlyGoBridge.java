package com.jingdong.app.mall.home.floor.view.view.title.tabbridge;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.animation.OvershootInterpolator;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItem;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabLayout;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabSkin;
import com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeInfo;
import com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeLayout;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.i;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.v.b;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.pdj.libcore.home.HourlyGoFragment;
import com.jingdong.pdj.libcore.watcher.HourlyGoObserverListener;
import com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class HourlyGoBridge implements HourlyGoObserverListener {
    private static final String TAB_DIRECT_KEY = "home_tab_direct_key";
    private static final int UN_INTERCEPT = 100;
    private static String sCurrentId;
    private static String sHourGoExpoJson;
    private static String sHourGoInfo;
    private static String sHourlyGoHeadUrl;
    private static String sHourlyGoTabUrl;
    private static int sInitHomeType;
    private static i sLbsInfo;
    private static i sLbsNearby;
    private long checkTime;
    private int interceptPosition = 100;
    private boolean isInit;
    private boolean isRegistered;
    private final TitleTabLayout mTitleTabLayout;
    private final TitleTabSkin mTitleTabSkin;
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private static final AtomicBoolean sAlreadySetLocation = new AtomicBoolean(false);

    public HourlyGoBridge(TitleTabLayout titleTabLayout, TitleTabSkin titleTabSkin) {
        this.mTitleTabLayout = titleTabLayout;
        this.mTitleTabSkin = titleTabSkin;
        sInitHomeType = b.a();
    }

    public static ValueAnimator buildLabelAnimator() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setInterpolator(new OvershootInterpolator(1.1f));
        ofFloat.setDuration(380L);
        return ofFloat;
    }

    private void checkHourlyShowSafe(boolean z) {
        TitleLabelImg labelImg = this.mTitleTabLayout.getLabelImg();
        TitleLabelText labelText = this.mTitleTabLayout.getLabelText();
        TitleTabNoticeLayout tabNotice = this.mTitleTabLayout.getTabNotice();
        TitleTabItem tabHourlyGo = TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo();
        if (!tabHourlyGo.isRightTab()) {
            labelImg.dismiss();
            labelText.dismiss();
            tabNotice.release(true);
            dismissBubble();
        } else if (!this.mTitleTabLayout.isShowing() || checkNotice(labelImg, labelText) || checkPop(z, labelImg, labelText, tabHourlyGo)) {
        } else {
            labelImg.loadIcon(getHourlyGoTabUrl());
        }
    }

    private boolean checkNotice(TitleLabelImg titleLabelImg, TitleLabelText titleLabelText) {
        if (TitleTabNoticeInfo.getInstance().isInit()) {
            TitleTabNoticeLayout tabNotice = this.mTitleTabLayout.getTabNotice();
            if (TitleTabNoticeInfo.getInstance().widthChanged()) {
                dismissBubble();
                tabNotice.release();
                titleLabelText.dismiss();
                titleLabelImg.dismiss();
                return false;
            } else if (TitleTabNoticeInfo.getInstance().canShow()) {
                dismissBubble();
                titleLabelImg.release();
                tabNotice.bindModel(this.mTitleTabLayout);
            } else if (TitleTabNoticeInfo.getInstance().isShowed() && !tabNotice.isRelease()) {
                titleLabelText.dismiss();
            }
        }
        return TitleTabNoticeInfo.getInstance().isShowed();
    }

    private boolean checkPop(boolean z, TitleLabelImg titleLabelImg, TitleLabelText titleLabelText, TitleTabItem titleTabItem) {
        TitleTabPop popInfo = titleTabItem.getPopInfo();
        if (popInfo == null || !popInfo.isInit()) {
            return false;
        }
        titleLabelImg.release();
        if (z) {
            return true;
        }
        if (b.a() != sInitHomeType) {
            titleLabelText.dismiss();
            dismissBubble();
            return true;
        }
        if (popInfo.canShow() && !TitleTabPop.isShowed() && !TitleTabPop.isCloseBubble()) {
            popInfo.showBubble(this.mTitleTabLayout);
        } else if (popInfo.canLabelShow() && TitleTabPop.isCloseBubble()) {
            titleLabelText.bindPopLabel(titleTabItem.getPopInfo());
        }
        return true;
    }

    public static void dismissBubble() {
        try {
            HourlyGoObserverManager.getInstance().dismissNearByBubble(false);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static com.jingdong.app.mall.home.r.c.b getHourGoExpoJson() {
        if (TextUtils.isEmpty(sHourGoExpoJson)) {
            com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c("");
            c2.put("tagmoduleid", "-100");
            c2.put("tagvenderid", "-100");
            c2.put("tagadvertid", "-100");
            return c2;
        }
        return com.jingdong.app.mall.home.r.c.b.c(sHourGoExpoJson);
    }

    public static boolean isHourlyDirected() {
        return f.M(TAB_DIRECT_KEY, 0) > 0;
    }

    public static void logD(String str) {
        f.r0("HourlyGoBridge", str);
    }

    public static void refreshHourGoInfo() {
        if (TextUtils.isEmpty(sHourGoInfo)) {
            return;
        }
        HourlyGoFragment.setOuterLinkParams(com.jingdong.app.mall.home.r.c.b.c(sHourGoInfo));
    }

    public static void saveHourlyDirect() {
        f.y0(TAB_DIRECT_KEY, 1);
    }

    public static void setHourGoExpoJson(String str) {
        sHourGoExpoJson = str;
    }

    public static void setHourGoInfo(@NotNull Intent intent) {
        sHourGoInfo = com.jingdong.app.mall.home.r.c.b.c(intent.getStringExtra("param")).optString("hourgoInfo");
    }

    public static void setLbsInfo(i iVar, i iVar2) {
        sLbsInfo = iVar;
        sLbsNearby = iVar2;
    }

    public void afterRefresh() {
        if (!TitleTabPop.isShowed() || TitleTabPop.isCloseBubble()) {
            return;
        }
        dismissBubble();
    }

    public void checkHourlyShow(boolean z) {
        checkHourlyShow(z, false);
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoGuideBubbleListener
    public PointF getHomeTopTabPoint() {
        PointF hourlyPointF = this.mTitleTabLayout.getTabContent().getHourlyPointF(0);
        if (hourlyPointF == null) {
            return null;
        }
        logD("pop: getHomeTopTabPoint" + hourlyPointF);
        return hourlyPointF;
    }

    public String getHourlyGoHeadUrl() {
        return k.w() ? "http://m.360buyimg.com/mobilecms/jfs/t1/148054/23/25125/116527/624fc812E1cd212c8/78616ea1aa459dfa.png" : sHourlyGoHeadUrl;
    }

    public String getHourlyGoTabUrl() {
        if (TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().isRightTab()) {
            String labelImg = TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().getLabelImg();
            return TextUtils.isEmpty(labelImg) ? sHourlyGoTabUrl : labelImg;
        }
        return "";
    }

    public void initEnd() {
        this.isInit = true;
    }

    public boolean interceptTabSelect(int i2) {
        if (i2 == -1) {
            HourlyGoObserverManager.getInstance().setIntent(null, null);
            return false;
        }
        return false;
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        String type = baseEvent.getType();
        type.hashCode();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case 815832937:
                if (type.equals("homePageXViewClose")) {
                    c2 = 0;
                    break;
                }
                break;
            case 818672077:
                if (type.equals("home_on_scroll")) {
                    c2 = 1;
                    break;
                }
                break;
            case 881725140:
                if (type.equals("home_scroll_stop")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1069086460:
                if (type.equals("home_pull_down")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1236015766:
                if (type.equals("home_pause")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                if (TitleTabNoticeInfo.getInstance().isInit()) {
                    checkHourlyShow(true, true);
                    return;
                }
                return;
            case 1:
            case 2:
            case 3:
            case 4:
                dismissBubble();
                return;
            default:
                return;
        }
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoGuideBubbleListener
    public void onNearByBubbleCallBack(int i2) {
        TitleTabPop popInfo = TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().getPopInfo();
        logD("pop: onNearByBubbleCallBack type:" + i2);
        if (i2 == 1) {
            popInfo.onPopShow();
            return;
        }
        popInfo.onPopClose();
        if (i2 == 4) {
            popInfo.unShowLabel();
            this.mTitleTabLayout.onTabClick(-1, HourlyGoObserverManager.HOURLY_GO_SOURCE_VALUE_GUIDE_BUBBLE);
            return;
        }
        if (i2 == 5) {
            popInfo.unShowLabel();
        }
        checkHourlyShow(true);
    }

    public void registerHourlyGoObserver() {
        if (this.isRegistered) {
            return;
        }
        this.isRegistered = true;
        HourlyGoObserverManager.getInstance().add(this);
        logD("\u6ce8\u518c\u5230\u5bb6\u76d1\u542c");
        try {
            if (TitleTabManager.getInstance().useCityName() && !sAlreadySetLocation.getAndSet(true)) {
                HourlyGoObserverManager hourlyGoObserverManager = HourlyGoObserverManager.getInstance();
                i iVar = sLbsInfo;
                JDBusinessAddress jDBusinessAddress = null;
                JDLocation d = iVar == null ? null : iVar.d();
                i iVar2 = sLbsNearby;
                if (iVar2 != null) {
                    jDBusinessAddress = iVar2.a();
                }
                hourlyGoObserverManager.setLocationInfo(d, jDBusinessAddress);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setHourlyGoFloorId() {
        if (TitleTabNoticeInfo.getInstance().isShowed()) {
            return;
        }
        TitleTabItem tabHourlyGo = TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo();
        TitleTabPop popInfo = tabHourlyGo.getPopInfo();
        String guideId = (popInfo == null || !popInfo.isInit()) ? "" : popInfo.getGuideId();
        boolean z = !TextUtils.isEmpty(guideId);
        if (!z) {
            guideId = tabHourlyGo.getLabelFloorId();
        }
        if (TextUtils.equals(sCurrentId, guideId)) {
            return;
        }
        sCurrentId = guideId;
        HourlyGoObserverManager.getInstance().setFoorId(sCurrentId);
        logD("\u8bbe\u7f6e\u5230\u5bb6\u6807\u7b7eid\uff1a " + sCurrentId + " \u662f\u5426\u5f15\u5bfc\uff1a" + z);
        if (k.w()) {
            updateTabPicUrl("https://m.360buyimg.com/img/jfs/t1/74442/6/19027/1364/629614e6Ea87442c3/9859eacb1226ba1c.png", null, null);
        }
    }

    public void setInterceptPosition(boolean z, int i2) {
        if (!z) {
            i2 = 100;
        }
        this.interceptPosition = i2;
    }

    public void unregisterHourlyGoObserver() {
        if (this.isRegistered) {
            this.isRegistered = false;
            HourlyGoObserverManager.getInstance().remove(this);
            logD("\u89e3\u9664\u5230\u5bb6\u76d1\u542c");
        }
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObserverListener
    public void updateHeadUrl(String str, int i2, int i3) {
        sHourlyGoHeadUrl = str;
        this.mTitleTabSkin.loadHourlyGoHeadSkin(str);
        logD("\u5230\u5bb6\u5934\u56fe\u56de\u8c03 => url: " + str + "  width: " + i2 + "  height: " + i3);
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObserverListener
    public void updateTabName(String str) {
        if (TitleTabItem.setHourlyGoName(str)) {
            this.mTitleTabLayout.notifyHourlyGoName();
        }
    }

    @Override // com.jingdong.pdj.libcore.watcher.HourlyGoObserverListener
    public void updateTabPicUrl(String str, Map<String, Object> map, JDJSONObject jDJSONObject) {
        if (k.w() && TextUtils.isEmpty(str)) {
            return;
        }
        String obj = map != null ? map.toString() : "";
        setHourGoExpoJson(obj);
        TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().getPopInfo().initHourlyGo(jDJSONObject);
        sHourlyGoTabUrl = str;
        checkHourlyShow(true);
        logD("\u5230\u5bb6\u4fe1\u606f\u56de\u8c03 => url: " + str + "  map: " + obj + "bubbleObject\uff1a" + jDJSONObject);
    }

    public void checkHourlyShow(final boolean z, final boolean z2) {
        try {
            if (this.isInit) {
                boolean z3 = com.jingdong.app.mall.home.i.h() != 0;
                Handler handler = sHandler;
                handler.removeCallbacksAndMessages(null);
                if (z3) {
                    handler.postDelayed(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabbridge.HourlyGoBridge.1
                        {
                            HourlyGoBridge.this = this;
                        }

                        @Override // com.jingdong.app.mall.home.o.a.b
                        protected void safeRun() {
                            HourlyGoBridge.this.checkHourlyShow(z, z2);
                        }
                    }, 600L);
                } else if ((z || SystemClock.elapsedRealtime() - this.checkTime >= 200) && !TitleTabNoticeInfo.getInstance().loadOrderInfo(this)) {
                    this.checkTime = SystemClock.elapsedRealtime();
                    checkHourlyShowSafe(z2);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            f.o(th.getMessage());
        }
    }
}
