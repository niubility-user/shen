package com.jingdong.app.mall.home.floor.view.view.title.tabbridge;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.ctrl.f;
import com.jingdong.app.mall.home.floor.ctrl.t.n;
import com.jingdong.app.mall.home.floor.view.b.f.d;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabLayout;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager;
import com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeInfo;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.o.a.h;
import com.jingdong.app.mall.home.o.a.o;
import com.jingdong.app.mall.home.r.e.b;
import com.jingdong.app.mall.home.t.a;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class TitleTabPop {
    private static final String DEF_BG = "https://m.360buyimg.com/mobilecal/jfs/t1/92430/14/37346/1505/643ca7ecFbbbd7439/92acd21834a8ac2f.png";
    private static final String SHOW_TIME = "home_title_tab_pop_time";
    private static boolean sCloseBubble;
    private static boolean sHasPopInfo;
    private static boolean sShowed;
    private static boolean sUnShowLabel;
    private final AtomicBoolean closePopMessage = new AtomicBoolean(false);
    private String guideId;
    private boolean isInit;
    private JDJSONObject mBubbleJson;
    private String mHomeLabelUrl;
    private String mInitPin;
    private Bitmap mLabelBitmap;
    private String mLabelText;
    private String mLabelUrl;
    private String mMaxKey;
    private int mMaxTimes;

    public static boolean hasPopInfo() {
        return sHasPopInfo;
    }

    public static boolean isCloseBubble() {
        return sCloseBubble;
    }

    public static boolean isShowed() {
        return sShowed;
    }

    private void loadLabelBitmap() {
        if (TextUtils.isEmpty(this.mLabelUrl)) {
            return;
        }
        f.i(this.mLabelUrl, new a() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleTabPop.1
            {
                TitleTabPop.this = this;
            }

            @Override // com.jingdong.app.mall.home.t.a
            public void onBitmapWithUiNull(Bitmap bitmap) {
                TitleTabPop.this.mLabelBitmap = bitmap;
            }
        });
    }

    public boolean canLabelShow() {
        if (this.isInit && !sUnShowLabel) {
            if (TitleTabNoticeInfo.getInstance().isShowed()) {
                HourlyGoBridge.logD("pop: \u663e\u793a\u8fc7\u7075\u52a8\u5c9b");
                return false;
            } else if (i.e() > 0) {
                HourlyGoBridge.logD("pop: \u5df2\u9009\u4e2d\u8fc7\u9644\u8fd1");
                return false;
            } else if (TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().isRightTab()) {
                return true;
            } else {
                HourlyGoBridge.logD("pop: \u9644\u8fd1\u4e0d\u5728\u6700\u53f3\u4fa7");
                return false;
            }
        }
        HourlyGoBridge.logD("pop: \u672a\u5b8c\u6210\u521d\u59cb\u5316 \u6216 \u624b\u52a8\u5173\u95ed");
        return false;
    }

    public boolean canShow() {
        if (!this.isInit) {
            HourlyGoBridge.logD("pop: \u672a\u5b8c\u6210\u521d\u59cb\u5316");
            return false;
        } else if (!TextUtils.equals(LoginUserBase.getUserPin(), this.mInitPin)) {
            HourlyGoBridge.logD("pop: \u7528\u6237pin\u53d1\u751f\u53d8\u5316");
            return false;
        } else if (sShowed) {
            HourlyGoBridge.logD("pop: \u5f53\u6b21\u5df2\u7ecf\u663e\u793a\u8fc7");
            return false;
        } else if (TitleTabNoticeInfo.getInstance().isShowed()) {
            HourlyGoBridge.logD("pop: \u663e\u793a\u8fc7\u7075\u52a8\u5c9b");
            return false;
        } else if (i.j()) {
            HourlyGoBridge.logD("pop: \u6b63\u5728\u89e6\u6478\u9996\u9875");
            return false;
        } else if (i.i()) {
            HourlyGoBridge.logD("pop: \u542f\u52a8\u56fe\u663e\u793a\u4e2d");
            return false;
        } else if (h.b()) {
            HourlyGoBridge.logD("pop: \u5df2\u5f39\u51fa\u4e1a\u52a1\u5f39\u7a97");
            return false;
        } else if (n.s()) {
            HourlyGoBridge.logD("pop: \u5305\u542b\u672a\u91ca\u653e\u7684xview");
            return false;
        } else if (i.e() > 0) {
            HourlyGoBridge.logD("pop: \u5df2\u9009\u4e2d\u8fc7\u9644\u8fd1");
            return false;
        } else if (!JDHomeFragment.O0()) {
            HourlyGoBridge.logD("pop: \u9996\u9875\u4e0d\u53ef\u89c1");
            return false;
        } else if (i.h() != 0) {
            HourlyGoBridge.logD("pop: \u4e0b\u62c9\u4e2d");
            return false;
        } else {
            return true;
        }
    }

    public String getGuideId() {
        return this.guideId;
    }

    public Bitmap getLabelBitmap() {
        return this.mLabelBitmap;
    }

    public String getLabelText() {
        return this.mLabelText;
    }

    public void initHome(b bVar) {
        this.isInit = false;
        if (bVar == null || g.f9303m) {
            return;
        }
        if (TitleTabNoticeInfo.getInstance().isShowed()) {
            HourlyGoBridge.logD("pop: \u663e\u793a\u8fc7\u7075\u52a8\u5c9b");
            return;
        }
        String jsonString = bVar.getJsonString("guideId");
        this.guideId = jsonString;
        if (TextUtils.isEmpty(jsonString)) {
            HourlyGoBridge.logD("pop: \u672a\u4e0b\u53d1 guideId");
            return;
        }
        this.mMaxKey = "Title_Pop_".concat(this.guideId);
        int h2 = c.h(bVar.getJsonString("expoTimes"), 1);
        this.mMaxTimes = h2;
        if (!d.g(this.mMaxKey, h2)) {
            HourlyGoBridge.logD("pop: \u66dd\u5149\u603b\u6b21\u6570\u4e0a\u9650\uff0c\u6700\u5927\uff1a" + this.mMaxTimes);
            return;
        }
        long N = com.jingdong.app.mall.home.o.a.f.N(SHOW_TIME, 0);
        long currentTimeMillis = System.currentTimeMillis();
        int h3 = c.h(bVar.getJsonString("expoInterval"), 10);
        if (currentTimeMillis - N < h3 * 86400000) {
            HourlyGoBridge.logD("pop: \u66dd\u5149\u95f4\u9694\u4e0a\u9650\uff0c\u6700\u5927\uff1a" + h3 + " \u5929");
            return;
        }
        this.mInitPin = LoginUserBase.getUserPin();
        this.mHomeLabelUrl = bVar.getJsonString("guideTagImg");
        com.jingdong.app.mall.home.floor.ctrl.guide.a.j().n();
        loadLabelBitmap();
        this.isInit = true;
        sHasPopInfo = true;
    }

    public boolean initHourlyGo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null || !this.isInit) {
            return false;
        }
        this.mBubbleJson = jDJSONObject;
        this.mLabelText = jDJSONObject.optString("tagText");
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("tagImage");
        if (optJSONObject != null && TextUtils.isEmpty(this.mHomeLabelUrl)) {
            this.mLabelUrl = optJSONObject.optString("imgUrl", DEF_BG);
        } else {
            this.mLabelUrl = this.mHomeLabelUrl;
        }
        loadLabelBitmap();
        return true;
    }

    public boolean isInit() {
        return this.isInit;
    }

    public boolean isValidLabel() {
        return this.isInit && !TextUtils.isEmpty(this.mLabelText) && o.a(this.mLabelBitmap);
    }

    public void onPopClose() {
        sCloseBubble = true;
        com.jingdong.app.mall.home.o.a.f.K0(this.closePopMessage);
    }

    public void onPopShow() {
        if (sShowed) {
            return;
        }
        sShowed = true;
        HourlyGoBridge.logD("pop: in onPopShow");
        com.jingdong.app.mall.home.o.a.f.z0(SHOW_TIME, System.currentTimeMillis());
        d.c(this.mMaxKey, this.mMaxTimes);
        com.jingdong.app.mall.home.o.a.f.m(this.closePopMessage);
    }

    public void showBubble(TitleTabLayout titleTabLayout) {
        PointF hourlyPointF;
        if (sShowed || this.mBubbleJson == null || (hourlyPointF = titleTabLayout.getTabContent().getHourlyPointF(-1)) == null) {
            return;
        }
        HourlyGoBridge.logD("pop: start showNearByBubble" + hourlyPointF);
        HourlyGoObserverManager.getInstance().showNearByBubble((Activity) titleTabLayout.getContext(), hourlyPointF, this.mBubbleJson);
    }

    public void unShowLabel() {
        sUnShowLabel = true;
    }
}
