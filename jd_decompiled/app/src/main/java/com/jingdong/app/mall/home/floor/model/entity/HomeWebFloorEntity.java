package com.jingdong.app.mall.home.floor.model.entity;

import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.activity.MainRightWebActivity;
import com.jingdong.app.mall.home.floor.ctrl.k;
import com.jingdong.app.mall.home.floor.ctrl.t.i;
import com.jingdong.app.mall.home.floor.ctrl.t.m;
import com.jingdong.app.mall.home.floor.ctrl.t.n;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.r.e.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class HomeWebFloorEntity extends b {
    public static final int MODULEFUNCTION_DIRECTDISPLAY = 2;
    public static final int PASSTHROUGH_STATE_INTECEPT = 0;
    public int animationTime;
    public int bindModule;
    private int initAppType;
    public String mDirectJump;
    private HomeWebFloorViewEntity mLaunchEntity;
    public long mParseTime;
    public int moduleFunction;
    public int passthrough;
    public int refreshHeight;
    public int resultHeight;
    public float resultHeightRatio;
    public int showTimes;
    public int showTimesDaily;
    public String sourceValue;
    public int totalShowTimesDaily;
    private List<HomeWebFloorViewEntity> webViewList;
    public String xViewId;
    public int xViewType;

    public HomeWebFloorEntity(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        JDJSONArray jsonArr;
        this.showTimes = 0;
        this.showTimesDaily = 0;
        this.passthrough = 0;
        this.moduleFunction = 2;
        this.resultHeightRatio = 0.0f;
        this.animationTime = 0;
        this.bindModule = 0;
        this.totalShowTimesDaily = 1;
        if (jDJSONObject == null || (jsonArr = getJsonArr("webViewList")) == null || jsonArr.size() <= 0) {
            return;
        }
        this.initAppType = com.jingdong.app.mall.home.v.b.a();
        this.mParseTime = SystemClock.elapsedRealtime();
        this.sourceValue = getJsonString("sourceValue");
        this.mDirectJump = getJsonString("directJump");
        int g2 = c.g(getJsonString("showTimes"));
        this.showTimes = g2;
        this.showTimes = g2 <= 0 ? 99 : g2;
        int g3 = c.g(getJsonString("showTimesDaily"));
        this.showTimesDaily = g3;
        this.showTimesDaily = g3 <= 0 ? 99 : g3;
        this.passthrough = c.g(getJsonString("passthrough"));
        this.moduleFunction = c.g(getJsonString("moduleFunction"));
        this.refreshHeight = c.g(getJsonString("refreshHeight", "130"));
        this.resultHeight = c.g(getJsonString("resultHeight", "320"));
        this.resultHeightRatio = c.e(getJsonString("resultHeightRatio"), 0.0f);
        this.animationTime = c.g(getJsonString("animationTime"));
        int g4 = c.g(getJsonString("totalShowTimesDaily"));
        this.totalShowTimesDaily = g4;
        this.totalShowTimesDaily = g4 > 0 ? g4 : 99;
        if (this.moduleFunction != 3 || i.p().u()) {
            parseXViewList(jsonArr);
        }
        if (this.mLaunchEntity != null) {
            n.D(true);
            HomeWebFloorViewEntity homeWebFloorViewEntity = this.mLaunchEntity;
            this.xViewId = homeWebFloorViewEntity.xViewId;
            this.xViewType = homeWebFloorViewEntity.xViewType;
            this.bindModule = homeWebFloorViewEntity.bindModule;
            this.passthrough = homeWebFloorViewEntity.passthrough;
            this.sourceValue = homeWebFloorViewEntity.sourceValue;
        }
    }

    private void parseXViewList(JDJSONArray jDJSONArray) {
        this.webViewList = new ArrayList();
        int size = jDJSONArray.size();
        JSONArray d = com.jingdong.app.mall.home.r.c.b.d();
        for (int i2 = 0; i2 < size; i2++) {
            HomeWebFloorViewEntity homeWebFloorViewEntity = new HomeWebFloorViewEntity(jDJSONArray.getJSONObject(i2), this.srcJson);
            homeWebFloorViewEntity.totalShowTimesDaily = this.totalShowTimesDaily;
            if (this.moduleFunction == 3) {
                d.put(com.jingdong.app.mall.home.r.c.b.c(homeWebFloorViewEntity.srvJson));
                boolean a = k.a(homeWebFloorViewEntity, this);
                if (!a) {
                    m.e(homeWebFloorViewEntity, "6");
                }
                if (homeWebFloorViewEntity.isLaunchIntercept()) {
                    m.e(homeWebFloorViewEntity, "6");
                } else if (homeWebFloorViewEntity.isLoginXView() && MainRightWebActivity.S()) {
                    m.e(homeWebFloorViewEntity, "6");
                } else if (this.mLaunchEntity == null && a) {
                    this.mLaunchEntity = homeWebFloorViewEntity;
                }
            } else {
                this.webViewList.add(homeWebFloorViewEntity);
            }
        }
        m.b(d);
    }

    public HomeWebFloorViewEntity getFirstEntity() {
        List<HomeWebFloorViewEntity> list = this.webViewList;
        if (list == null || list.size() <= 0) {
            return null;
        }
        return this.webViewList.get(0);
    }

    public HomeWebFloorViewEntity getLaunchEntity() {
        return this.mLaunchEntity;
    }

    public List<HomeWebFloorViewEntity> getWebViewList() {
        return this.webViewList;
    }

    public boolean isPassthrough() {
        return this.passthrough != 0;
    }

    public boolean isPullJump() {
        return TextUtils.equals("8", this.mDirectJump);
    }

    public boolean isSameType() {
        return this.initAppType == com.jingdong.app.mall.home.v.b.a();
    }
}
