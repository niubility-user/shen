package com.jingdong.app.mall.home.floor.view.view.title.tab;

import android.os.SystemClock;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.HourlyGoBridge;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelImg;
import com.jingdong.app.mall.home.o.a.i;
import com.jingdong.app.mall.home.o.a.n;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.jdsdk.constant.CartConstant;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class TitleTabExpoUtil {
    private static int preShowCount;
    private static long sendExpoTime;

    private static void postClick(b bVar) {
        if (bVar == null) {
            return;
        }
        try {
            bVar.e(HourlyGoBridge.getHourGoExpoJson());
            a.s("Home_Nearby", "", bVar.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void postCustomClick() {
        postClick(TitleTabManager.getInstance().getTitleTabInfo().getTabCustom().getClickJson());
    }

    private static void postHomeClick() {
        postClick(TitleTabManager.getInstance().getTitleTabInfo().getTabHome().getClickJson());
    }

    private static void postLabelClick(TitleLabelImg titleLabelImg) {
        TitleTabItem tabHourlyGo = TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo();
        tabHourlyGo.onPostClick();
        postClick(tabHourlyGo.getClickJson());
        if (titleLabelImg == null || !titleLabelImg.isShowing()) {
            return;
        }
        new com.jingdong.app.mall.home.q.a("\u5934\u90e8Tab\u6807\u7b7e\u70b9\u51fb", tabHourlyGo.getClkLog()).b();
    }

    public static void postLabelExpo() {
        new com.jingdong.app.mall.home.q.a("\u5934\u90e8Tab\u6807\u7b7e\u66dd\u5149", true, TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().getExpoLog()).b();
    }

    public static void postLocationExpo(i iVar) {
        boolean isHourlyDirected = HourlyGoBridge.isHourlyDirected();
        b bVar = new b();
        String str = CartConstant.KEY_YB_INFO_LINK;
        if (iVar != null) {
            str = iVar.c() + CartConstant.KEY_YB_INFO_LINK + iVar.b();
        }
        bVar.put("loc", str);
        bVar.put("rightoption", n.a() ? 1 : 0);
        bVar.put("shown", isHourlyDirected ? 1 : 0);
        a.y("Home_Nearby_LocationTest", "", bVar.toString());
    }

    private static void postPromotionClick() {
        postClick(TitleTabManager.getInstance().getTitleTabInfo().getTabPromotion().getClickJson());
    }

    public static void postRightTagExpo() {
        TitleTabItem tabHourlyGo = TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo();
        if (tabHourlyGo.isCanShow()) {
            b clickJson = tabHourlyGo.getClickJson();
            clickJson.e(HourlyGoBridge.getHourGoExpoJson());
            a.y("Home_NearbyTagExpo", "", clickJson.toString());
        }
    }

    public static void postTabClick(int i2, TitleLabelImg titleLabelImg) {
        if (-1 == i2) {
            postLabelClick(titleLabelImg);
        } else if (i2 == 0) {
            postHomeClick();
        } else if (-2 == i2) {
            postCustomClick();
        } else if (-3 == i2) {
            postPromotionClick();
        }
    }

    public static void postTopTabExpo() {
        TitleTabInfo titleTabInfo = TitleTabManager.getInstance().getTitleTabInfo();
        if (titleTabInfo.isCanShow() && JDHomeFragment.O0()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            JSONArray d = b.d();
            int i2 = 0;
            if (titleTabInfo.getTabHourlyGo().isCanShow()) {
                d.put(titleTabInfo.getTabHourlyGo().getExpoJson());
                i2 = 1;
            }
            if (titleTabInfo.getTabCustom().isCanShow()) {
                d.put(titleTabInfo.getTabCustom().getExpoJson());
                i2++;
            }
            if (titleTabInfo.getTabPromotion().isCanShow()) {
                d.put(titleTabInfo.getTabPromotion().getExpoJson());
                i2++;
            }
            if (i2 > preShowCount || elapsedRealtime - sendExpoTime > 1000) {
                sendExpoTime = elapsedRealtime;
                a.y("Home_Nearby_Expo", "", d.toString());
            }
            preShowCount = i2;
        }
    }

    public static void postTopTabExpoNow() {
        TitleTabInfo titleTabInfo = TitleTabManager.getInstance().getTitleTabInfo();
        JSONArray d = b.d();
        if (titleTabInfo.getTabHourlyGo().isInit()) {
            d.put(titleTabInfo.getTabHourlyGo().getExpoJson());
        }
        if (titleTabInfo.getTabCustom().isCanShow()) {
            d.put(titleTabInfo.getTabCustom().getExpoJson());
        }
        if (titleTabInfo.getTabPromotion().isCanShow()) {
            d.put(titleTabInfo.getTabPromotion().getExpoJson());
        }
        a.y("Home_Nearby_Load", "", d.toString());
    }
}
