package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.cart.CartConfigState;

/* loaded from: classes5.dex */
public class SubCartJumpConfig {
    public int sevenFreshType = 0;
    public String sevenFreshUrl = "";
    public int ecardType = 0;
    public String ecardUrl = "";
    public int homeWishListType = 0;
    public String homeWishListPageURL = "";

    public SubCartJumpConfig(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            setSevenFreshType(jDJSONObject.optInt("7freshType"));
            setSevenFreshUrl(jDJSONObject.optString("7freshUrl"));
            setEcardType(jDJSONObject.optInt("ecardType"));
            setEcardUrl(jDJSONObject.optString("ecardUrl"));
            setHomeWishListType(jDJSONObject.optInt("homeWishListPageType"));
            setHomeWishListPageURL(jDJSONObject.optString("homeWishListPageUrl"));
        }
    }

    public static SubCartJumpConfig getConfig() {
        CartConfigDetail cartConfigDetail;
        CartConfigInfo cartConfigInfo = CartConfigState.getInstance().getCartConfigInfo();
        if (cartConfigInfo == null || (cartConfigDetail = cartConfigInfo.cartConfigDetail) == null) {
            return null;
        }
        return cartConfigDetail.cartJumpConfig;
    }

    public int getEcardType() {
        return this.ecardType;
    }

    public String getEcardUrl() {
        return this.ecardUrl;
    }

    public String getHomeWishListPageURL() {
        return this.homeWishListPageURL;
    }

    public int getHomeWishListType() {
        return this.homeWishListType;
    }

    public int getSevenFreshType() {
        return this.sevenFreshType;
    }

    public String getSevenFreshUrl() {
        return this.sevenFreshUrl;
    }

    public void setEcardType(int i2) {
        this.ecardType = i2;
    }

    public void setEcardUrl(String str) {
        this.ecardUrl = str;
    }

    public void setHomeWishListPageURL(String str) {
        this.homeWishListPageURL = str;
    }

    public void setHomeWishListType(int i2) {
        this.homeWishListType = i2;
    }

    public void setSevenFreshType(int i2) {
        this.sevenFreshType = i2;
    }

    public void setSevenFreshUrl(String str) {
        this.sevenFreshUrl = str;
    }
}
