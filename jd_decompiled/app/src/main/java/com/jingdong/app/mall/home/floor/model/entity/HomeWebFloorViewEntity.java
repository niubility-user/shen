package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.r.e.b;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class HomeWebFloorViewEntity extends b {
    public static final int LAUNCH_LOGIN_COUPON = 1;
    public static final int NEW_PRODUCT_POP_GUIDE = 4;
    public static final int NEW_PRODUCT_X_VIEW_GUIDE = 2;
    public static final int TOP_CATEGORY_GUIDE = 3;
    public boolean backXViewFirst;
    public String baseColor;
    public int bindModule;
    public AtomicBoolean canBlock;
    public boolean checkUnLogin;
    public String clickEventId;
    public String clkLog;
    public String closeLog;
    public boolean conflictWithStay;
    public String expoLog;
    public String img;
    public boolean isConflict;
    private JumpEntity jump;
    private String launchType;
    public boolean mLaunchIntercept;
    private boolean mLeaveClose;
    public int passthrough;
    public int showTimes;
    public int showTimesDaily;
    public String sourceValue;
    public String srvJson;
    public int totalShowTimesDaily;
    public int transparency;
    public String wordsColor;
    public String xViewId;
    public int xViewType;

    public HomeWebFloorViewEntity() {
        super(null);
        this.canBlock = new AtomicBoolean(true);
        this.launchType = "0";
    }

    public JumpEntity getJump() {
        JumpEntity jumpEntity = this.jump;
        return jumpEntity == null ? new JumpEntity() : jumpEntity;
    }

    public String getLaunchType() {
        return this.launchType;
    }

    public String getUrl() {
        try {
            return JDJSON.parseObject(this.jump.params).getString("url");
        } catch (Exception unused) {
            return null;
        }
    }

    public void initLaunchType(boolean z) {
        if (!z) {
            this.launchType = "3";
            return;
        }
        JDHomeFragment z0 = JDHomeFragment.z0();
        if ((z0 == null ? 0L : z0.H0()) > 0) {
            this.launchType = "1";
        }
    }

    public boolean isErrorGuid(HomeWebFloorEntity homeWebFloorEntity) {
        if (TextUtils.isEmpty(homeWebFloorEntity.getJsonString("xviewGuideFloor"))) {
            return false;
        }
        k.e(NavigationBase.getInstance().getNavigationOrder());
        return !TextUtils.equals(r2, r0);
    }

    public boolean isLaunchIntercept() {
        JDHomeFragment z0 = JDHomeFragment.z0();
        return (z0 == null ? 0L : z0.H0()) > 100 && this.mLaunchIntercept;
    }

    public boolean isLeaveClose() {
        return this.mLeaveClose && f.P() > 0;
    }

    public boolean isLoginXView() {
        return this.xViewType == 1;
    }

    public HomeWebFloorViewEntity(JDJSONObject jDJSONObject, JDJSONObject jDJSONObject2) {
        super(jDJSONObject);
        this.canBlock = new AtomicBoolean(true);
        this.launchType = "0";
        this.img = getJsonString("img");
        this.wordsColor = getJsonString("wordsColor");
        this.sourceValue = getJsonString("sourceValue");
        this.closeLog = getJsonString("closeLog");
        this.expoLog = getJsonString("expoLog");
        this.clkLog = getJsonString("clkLog");
        this.isConflict = TextUtils.equals("1", getJsonString("conflict"));
        int g2 = c.g(getJsonString("showTimes"));
        this.showTimes = g2;
        this.showTimes = g2 <= 0 ? 99 : g2;
        int g3 = c.g(getJsonString("showTimesDaily"));
        this.showTimesDaily = g3;
        this.showTimesDaily = g3 > 0 ? g3 : 99;
        this.passthrough = c.g(getJsonString("passthrough"));
        this.bindModule = c.g(getJsonString("bindModule"));
        this.xViewId = getJsonString("xViewId");
        this.baseColor = getJsonString("baseColor");
        this.transparency = Math.min(Math.max(0, c.g(getJsonString("transparency"))), 100);
        this.xViewType = c.h(getJsonString("xViewType"), 0);
        this.conflictWithStay = 1 == c.h(getJsonString("conflictWithStay"), 0);
        this.backXViewFirst = TextUtils.equals("1", getJsonString("backXViewFirst"));
        this.mLeaveClose = TextUtils.equals("0", b.getJsonString(jDJSONObject2, "reShowJumpInterrupt", "1"));
        this.mLaunchIntercept = TextUtils.equals("0", b.getJsonString(jDJSONObject2, "reShowOffSiteEvoked", "1"));
        JumpEntity jumpEntity = (JumpEntity) getObject("jump", JumpEntity.class);
        this.jump = jumpEntity;
        this.srvJson = jumpEntity == null ? "" : jumpEntity.getSrvJson();
    }
}
