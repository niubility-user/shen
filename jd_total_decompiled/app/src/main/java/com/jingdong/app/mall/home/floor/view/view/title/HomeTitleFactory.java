package com.jingdong.app.mall.home.floor.view.view.title;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.view.view.SearchWordEntity;
import com.jingdong.app.mall.home.j;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.v.d.a;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes4.dex */
public class HomeTitleFactory {
    public static final int MIN_HEIGHT = 88;
    private static final String NEW_SEARCH = "searchBoxStyle";
    public static final int TYPE_36 = 1;
    public static final int TYPE_40 = 2;
    public static final int TYPE_DEF = 0;
    private static final String TYPE_SEARCH = "riseStyle";
    public static boolean isNewSearch;
    private static IHomeTitle sCommonTitle;
    private static IHomeTitle sElderTitle;
    private static IHomeTitle sPlanBTitle;
    private static int sSearchType;

    static {
        isNewSearch = f.M(NEW_SEARCH, 0) == 1 && f.k0();
        sSearchType = f.M(TYPE_SEARCH, 0);
    }

    public static int getDefaultHeight() {
        return d.d(88);
    }

    public static IHomeTitle getHomeTitle(Context context) {
        if (a.f()) {
            if (sPlanBTitle == null) {
                sPlanBTitle = new HomeTitleNewB(context);
            }
            return sPlanBTitle;
        } else if (com.jingdong.app.mall.home.state.old.a.f()) {
            if (sElderTitle == null) {
                sElderTitle = new HomeTitleElder(context);
            }
            return sElderTitle;
        } else {
            if (sCommonTitle == null) {
                sCommonTitle = new HomeTitleNew(context);
            }
            return sCommonTitle;
        }
    }

    public static int getSearchType() {
        return sSearchType;
    }

    public static IHomeTitle getTitleB(Context context) {
        if (a.f()) {
            if (sPlanBTitle == null) {
                sPlanBTitle = new HomeTitleNewB(context);
            }
            return sPlanBTitle;
        }
        return null;
    }

    public static void gotoProductList(Context context, SearchWordEntity searchWordEntity, String str) {
        if (searchWordEntity == null) {
            gotoProductListDefault(context);
            return;
        }
        String str2 = searchWordEntity.showWord;
        if (TextUtils.isEmpty(str2)) {
            gotoProductListDefault(context);
            return;
        }
        try {
            OpenAppJumpController.dispatchJumpRequestInApp(context, Uri.parse((!TextUtils.isEmpty(g.p) ? g.p : "openapp.jdmobile://virtual?params={\"des\":\"productList\",\"keyWord\":\"keywordHolder\",\"from\":\"search\",\"category\":\"jump\",\"sourcePage\":\"HomePage\",\"sourceDetail\":\"SearchBox\",\"save\":\"1\"}").replace("keywordHolder", str2)));
        } catch (Exception unused) {
        }
        j.g();
        b c2 = b.c(str);
        c2.a("word", searchWordEntity.showWord);
        com.jingdong.app.mall.home.r.c.a.s("Home_SearchBtn", "", c2.toString());
    }

    public static void gotoProductListDefault(Context context) {
        DeepLinkProductListHelper.homeToSearchActivity(context);
        b b = b.b();
        b.a("word", "empty");
        com.jingdong.app.mall.home.r.c.a.s("Home_SearchBtn", "", b.toString());
    }

    public static void sendSearchClickMta(SearchWordEntity searchWordEntity, String str) {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (searchWordEntity != null) {
            str2 = searchWordEntity.sourceValue;
            jDJSONObject.put("word", (Object) searchWordEntity.showWord);
        } else {
            jDJSONObject.put("word", (Object) "");
        }
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), "Home_Search", str2 + CartConstant.KEY_YB_INFO_LINK + str, "", RecommendMtaUtils.Home_PageId, com.jingdong.app.mall.home.r.c.a.f10642k, "", "", jDJSONObject.toString(), null);
    }

    public static void setSearchInfo(JDJSONObject jDJSONObject) {
        int optInt = jDJSONObject.optInt(NEW_SEARCH, 0);
        f.y0(NEW_SEARCH, optInt);
        isNewSearch = optInt == 1 && f.k0();
        sSearchType = jDJSONObject.optInt(TYPE_SEARCH, 0);
    }
}
