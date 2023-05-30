package com.jingdong.app.mall.home.floor.view.view.title.tabnotice;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.b;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.ctrl.guide.a;
import com.jingdong.app.mall.home.floor.ctrl.t.n;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.HourlyGoBridge;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleTabPop;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.h;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.o.a.o;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class TitleTabNoticeInfo {
    private static final String DEF_BG = "https://m.360buyimg.com/mobilecal/jfs/t1/92430/14/37346/1505/643ca7ecFbbbd7439/92acd21834a8ac2f.png";
    private static long sRequestTime;
    private String clkLog;
    private final AtomicBoolean closePopMessage = new AtomicBoolean(false);
    private int colorLeft;
    private int colorRight;
    private long dataInitTime;
    private String deliveryState;
    private long dismissTime;
    private String expoJson;
    private String expoLog;
    private long freshInterval;
    private int initWidth;
    private boolean isDataInit;
    private boolean isInit;
    private boolean isRelease;
    private boolean isRequesting;
    private boolean isShowed;
    private String mInitPin;
    private Bitmap mLabelBitmap;
    private String mLabelText;
    private String mLastShowText;
    private String mLeftText;
    private String mRightText;
    private String orderId;
    private long showTime;
    private String skuImg;
    private String srvJson;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class TitleTabNoticeInfoHolder {
        private static final TitleTabNoticeInfo INSTANCE = new TitleTabNoticeInfo();

        private TitleTabNoticeInfoHolder() {
        }
    }

    public static TitleTabNoticeInfo getInstance() {
        return TitleTabNoticeInfoHolder.INSTANCE;
    }

    public boolean canLabelShow() {
        if (isInit() && !widthChanged()) {
            if (!f.n0()) {
                HourlyGoBridge.logD("notice: \u7528\u6237\u672a\u767b\u5f55");
                return false;
            } else if (i.e() > 0) {
                HourlyGoBridge.logD("notice: \u5df2\u9009\u4e2d\u8fc7\u9644\u8fd1");
                return false;
            } else if (TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().isRightTab()) {
                return true;
            } else {
                HourlyGoBridge.logD("notice: \u9644\u8fd1\u4e0d\u5728\u6700\u53f3\u4fa7");
                return false;
            }
        }
        HourlyGoBridge.logD("notice: \u672a\u5b8c\u6210\u521d\u59cb\u5316 \u6216 \u5bbd\u5ea6\u53d8\u5316");
        return false;
    }

    public boolean canShow() {
        if (isInit() && isDataInit() && !widthChanged()) {
            if (!f.n0()) {
                HourlyGoBridge.logD("notice: \u7528\u6237\u672a\u767b\u5f55");
                return false;
            } else if (!TextUtils.equals(LoginUserBase.getUserPin(), this.mInitPin)) {
                HourlyGoBridge.logD("pop: \u7528\u6237pin\u53d1\u751f\u53d8\u5316");
                return false;
            } else if (i.i()) {
                HourlyGoBridge.logD("notice: \u542f\u52a8\u56fe\u663e\u793a\u4e2d");
                return false;
            } else if (TitleTabPop.isShowed()) {
                HourlyGoBridge.logD("notice: \u663e\u793a\u8fc7\u4e00\u5206\u8d2d");
                return false;
            } else if (g.f9303m) {
                HourlyGoBridge.logD("notice: \u4e0b\u53d1\u81ea\u52a8\u8df3\u8f6cTab");
                return false;
            } else if (h.b()) {
                HourlyGoBridge.logD("notice: \u5df2\u5f39\u51fa\u4e1a\u52a1\u5f39\u7a97");
                this.isRelease = true;
                return false;
            } else if (n.s()) {
                HourlyGoBridge.logD("notice: \u5305\u542b\u672a\u91ca\u653e\u7684xview");
                return false;
            } else if (!i.k()) {
                HourlyGoBridge.logD("notice: \u4e0d\u6ee1\u8db3\u5934\u90e8\u56fa\u5b9a");
                return false;
            } else if (i.e() > 0) {
                HourlyGoBridge.logD("notice: \u5df2\u9009\u4e2d\u8fc7\u9644\u8fd1");
                this.isRelease = true;
                return false;
            } else if (JDHomeFragment.O0()) {
                return true;
            } else {
                HourlyGoBridge.logD("notice: \u9996\u9875\u4e0d\u53ef\u89c1");
                return false;
            }
        }
        HourlyGoBridge.logD("notice: \u672a\u5b8c\u6210\u521d\u59cb\u5316 \u6216 \u5bbd\u5ea6\u53d8\u5316");
        return false;
    }

    public int getColorLeft() {
        return this.colorLeft;
    }

    public int getColorRight() {
        return this.colorRight;
    }

    public long getDismissTime() {
        return this.dismissTime;
    }

    public Bitmap getLabelBitmap() {
        return this.mLabelBitmap;
    }

    public String getLabelText() {
        return this.mLabelText;
    }

    public String getLeftText() {
        return this.mLeftText;
    }

    public String getRightText() {
        return this.mRightText;
    }

    public long getShowTime() {
        return this.showTime;
    }

    public String getSkuImg() {
        return this.skuImg;
    }

    public void initHome(d dVar) {
        JDJSONObject d;
        if (Build.VERSION.SDK_INT >= 21 && dVar != null) {
            try {
                if (dVar.isCacheData || !f.n0() || (d = dVar.mParentModel.d(0)) == null) {
                    return;
                }
                if (TitleTabPop.isShowed()) {
                    HourlyGoBridge.logD("notice: \u663e\u793a\u8fc7\u4e00\u5206\u8d2d");
                    return;
                }
                a.j().n();
                long h2 = c.h(d.optString("orderShowTime"), 3);
                this.showTime = h2;
                this.showTime = Math.max(h2, 3L) * 1000;
                long h3 = c.h(d.optString("cornerDisapperTime"), 5);
                this.dismissTime = h3;
                this.dismissTime = Math.max(h3, 3L) * 1000;
                long h4 = c.h(d.optString("freshInterval"), 10);
                this.freshInterval = h4;
                this.freshInterval = Math.max(h4, 5L) * 1000;
                this.colorLeft = com.jingdong.app.mall.home.floor.view.b.h.a.d(d.optString("color1"), -15066598);
                this.colorRight = com.jingdong.app.mall.home.floor.view.b.h.a.d(d.optString("color2"), -16734163);
                this.expoJson = d.optString("expoJson");
                this.srvJson = d.optString("srvJson");
                this.expoLog = d.optString("expoLog");
                this.clkLog = d.optString("clkLog");
                com.jingdong.app.mall.home.floor.ctrl.f.i(d.optString("islandLabelImg", DEF_BG), new com.jingdong.app.mall.home.t.a() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeInfo.1
                    @Override // com.jingdong.app.mall.home.t.a
                    public void onBitmapWithUiNull(Bitmap bitmap) {
                        TitleTabNoticeInfo.this.mLabelBitmap = bitmap;
                    }
                });
                this.isInit = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void initHourlyGo(JDJSONObject jDJSONObject) {
        JDJSONObject optJSONObject;
        JDJSONObject optJSONObject2;
        this.isDataInit = false;
        if (jDJSONObject == null || (optJSONObject = jDJSONObject.optJSONObject("result")) == null || (optJSONObject2 = optJSONObject.optJSONObject("logo")) == null) {
            return;
        }
        this.initWidth = com.jingdong.app.mall.home.floor.common.d.f9279g;
        String optString = optJSONObject.optString("deliveryStateText");
        this.mLeftText = optString;
        this.mLeftText = f.j(5, optString);
        String optString2 = optJSONObject.optString("waybillPromiseDateUccText");
        this.mRightText = optString2;
        this.mRightText = f.j(7, optString2);
        this.mLabelText = optJSONObject.optString("deliveryStateShortText");
        this.orderId = optJSONObject.optString("orderId");
        this.deliveryState = optJSONObject.optString("deliveryState");
        if (!TextUtils.isEmpty(this.mLeftText) && !TextUtils.isEmpty(this.mRightText)) {
            if (TextUtils.equals(this.mLastShowText, this.mLeftText) && !k.w()) {
                HourlyGoBridge.logD("notice: \u663e\u914d\u9001\u72b6\u6001\u5185\u5bb9\u672a\u53d8\u5316");
                return;
            }
            String optString3 = optJSONObject2.optString("imgUrl");
            this.skuImg = optString3;
            e.z(optString3, null);
            this.isDataInit = true;
            this.dataInitTime = SystemClock.elapsedRealtime();
            return;
        }
        HourlyGoBridge.logD("notice: \u914d\u9001\u6587\u6848\u4e3a\u7a7a");
    }

    public boolean isDataInit() {
        return this.isDataInit && SystemClock.elapsedRealtime() - this.dataInitTime < Math.min(this.freshInterval, 60000L);
    }

    public boolean isInit() {
        return this.isInit;
    }

    public boolean isShowed() {
        return this.isShowed;
    }

    public boolean isValidLabel() {
        return isInit() && !TextUtils.isEmpty(this.mLabelText) && o.a(this.mLabelBitmap);
    }

    public boolean loadOrderInfo(final HourlyGoBridge hourlyGoBridge) {
        try {
            if (f.n0() && isInit() && !this.isRelease && !TitleTabPop.isShowed()) {
                if (k.w()) {
                    this.freshInterval = 1000L;
                }
                if (SystemClock.elapsedRealtime() - sRequestTime < this.freshInterval) {
                    HourlyGoBridge.logD("notice: \u5237\u65b0\u65f6\u95f4\u5c0f\u4e8e\u95f4\u9694\uff1a" + this.freshInterval);
                    return this.isRequesting;
                }
                this.isRequesting = true;
                this.mInitPin = LoginUserBase.getUserPin();
                HourlyGoBridge.logD("notice: request hours_entrance_order");
                sRequestTime = SystemClock.elapsedRealtime();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("fQueryStamp", b.f8602m + "");
                f.D0("hours_entrance_order", jSONObject, new f.d() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeInfo.2
                    @Override // com.jingdong.app.mall.home.o.a.f.d
                    public void onEnd(JDJSONObject jDJSONObject) {
                        TitleTabNoticeInfo.this.isRequesting = false;
                        TitleTabNoticeInfo.this.initHourlyGo(jDJSONObject);
                        f.E0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeInfo.2.1
                            @Override // com.jingdong.app.mall.home.o.a.b
                            protected void safeRun() {
                                hourlyGoBridge.checkHourlyShow(true);
                            }
                        });
                    }

                    @Override // com.jingdong.app.mall.home.o.a.f.d
                    public void onError(HttpError httpError) {
                        TitleTabNoticeInfo.this.isRequesting = false;
                    }

                    @Override // com.jingdong.app.mall.home.o.a.f.d
                    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                    }
                });
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void onPopClick() {
        com.jingdong.app.mall.home.r.c.d dVar = new com.jingdong.app.mall.home.r.c.d("Home_TabOrder");
        dVar.f(this.srvJson);
        dVar.a("orderId", this.orderId);
        dVar.a("deliveryState", this.deliveryState);
        dVar.c();
        new com.jingdong.app.mall.home.q.a("\u7075\u52a8\u5c9b\u66dd\u5149", this.clkLog).b();
    }

    public void onPopClose() {
        f.K0(this.closePopMessage);
    }

    public void onPopShow() {
        this.isDataInit = false;
        this.mLastShowText = this.mLeftText;
        com.jingdong.app.mall.home.r.c.d dVar = new com.jingdong.app.mall.home.r.c.d("Home_TabOrderExpo");
        dVar.f(this.expoJson);
        dVar.a("orderId", this.orderId);
        dVar.a("deliveryState", this.deliveryState);
        dVar.d();
        new com.jingdong.app.mall.home.q.a("\u7075\u52a8\u5c9b\u66dd\u5149", true, this.expoLog).b();
        f.m(this.closePopMessage);
    }

    public void setShowed(boolean z) {
        this.isShowed = z;
    }

    public boolean widthChanged() {
        return this.initWidth != com.jingdong.app.mall.home.floor.common.d.f9279g;
    }
}
