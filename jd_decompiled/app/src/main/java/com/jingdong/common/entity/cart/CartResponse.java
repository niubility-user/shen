package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.cart.CartCommonUtil;
import com.jingdong.common.entity.cart.tabMenu.TabMenuEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class CartResponse implements Parcelable {
    public static final Parcelable.Creator<CartResponse> CREATOR = new Parcelable.Creator<CartResponse>() { // from class: com.jingdong.common.entity.cart.CartResponse.1
        @Override // android.os.Parcelable.Creator
        public CartResponse createFromParcel(Parcel parcel) {
            return new CartResponse(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CartResponse[] newArray(int i2) {
            return new CartResponse[i2];
        }
    };
    public CartAddHotListEntity cartAddHotListEntity;
    public CartConfigCards cartConfigCards;
    public CartRecCoupon cartRecCoupon;
    public JDJSONObject cartResponseJSONObject;
    private int code;
    public JDJSONObject config;
    public int configVersion;
    private long dataLoadedTime;
    public int degradation;
    public HashMap<String, String> digitalAbCards;
    public HashMap<String, String> digitalBuriedExpLabel;
    public Map<String, String> djBadInfo;
    private String errorMessage;
    public String homeWishListUserFlag;
    private String imageDomain;
    private CartResponseInfo info;
    private boolean isCache;
    public String jsonObjectStr;
    private String message;
    public Boolean noRefreshCart;
    private int refresh;
    private int resultCode;
    private String resultMsg;
    private String solve;
    private ArrayList<TabMenuEntity> tabMenus;
    private String targetUrl;
    public String userIdentity;
    private String value;

    public CartResponse(String str, JDJSONObject jDJSONObject) {
        if (OKLog.D) {
            OKLog.d("CartResponse", "  -->> start : ");
        }
        setCode(Integer.valueOf(jDJSONObject.optInt("code", -1)));
        setImageDomain(jDJSONObject.optString(CartConstant.KEY_IMAGE_DOMAIN));
        setMessage(jDJSONObject.optString("message"));
        setErrorMessage(jDJSONObject.optString("msg"));
        setResultCode(jDJSONObject.optInt("resultCode"));
        this.solve = jDJSONObject.optString(CartConstant.KEY_CART_SOLVE);
        this.refresh = jDJSONObject.optInt("refresh", 1);
        this.resultMsg = jDJSONObject.optString(CartConstant.KEY_CART_RESULTMSG);
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("button");
        this.userIdentity = jDJSONObject.optString(CartConstant.KEY_CART_USERIDENTITY);
        this.homeWishListUserFlag = jDJSONObject.optString("homeWishListUserFlag");
        if (optJSONObject != null) {
            if (!optJSONObject.isNull("value") && !optJSONObject.isNull("url")) {
                setValue(optJSONObject.optString("value"));
                setTargetUrl(optJSONObject.optString("url"));
            } else {
                setValue("");
                setTargetUrl("");
            }
        }
        JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("digitalAbCards");
        if (optJSONObject2 != null) {
            this.digitalAbCards = new HashMap<>();
            for (String str2 : optJSONObject2.keySet()) {
                this.digitalAbCards.put(str2, optJSONObject2.optString(str2));
            }
            CartCommonUtil.digitalAbCards = this.digitalAbCards;
        } else {
            CartCommonUtil.digitalAbCards = null;
        }
        JDJSONObject optJSONObject3 = jDJSONObject.optJSONObject("digitalBuriedExpLabel");
        if (optJSONObject3 != null) {
            this.digitalBuriedExpLabel = new HashMap<>();
            for (String str3 : optJSONObject3.keySet()) {
                this.digitalBuriedExpLabel.put(str3, optJSONObject3.optString(str3));
            }
            CartCommonUtil.digitalBuriedExpLabel = this.digitalBuriedExpLabel;
        } else {
            CartCommonUtil.digitalBuriedExpLabel = null;
        }
        JDJSONObject optJSONObject4 = jDJSONObject.optJSONObject(CartConstant.KEY_CONFIGCARDS);
        if (optJSONObject4 != null) {
            this.cartConfigCards = CartConfigCards.toCartConfigCards(optJSONObject4);
        }
        JDJSONObject optJSONObject5 = jDJSONObject.optJSONObject("coupon");
        if (optJSONObject5 != null) {
            this.cartRecCoupon = new CartRecCoupon(optJSONObject5);
        }
        JDJSONObject optJSONObject6 = jDJSONObject.optJSONObject("addHotList");
        if (optJSONObject6 != null) {
            this.cartAddHotListEntity = new CartAddHotListEntity(optJSONObject6);
        }
        JDJSONObject optJSONObject7 = jDJSONObject.optJSONObject(CartConstant.KEY_CART_INFO);
        if (optJSONObject7 != null) {
            setInfo(new CartResponseInfo(optJSONObject7));
        }
        this.dataLoadedTime = System.currentTimeMillis();
        if (jDJSONObject.optJSONArray("tabMenus") != null) {
            setTabMenus(TabMenuEntity.toList(jDJSONObject.optJSONArray("tabMenus")));
        }
        JDJSONObject optJSONObject8 = jDJSONObject.optJSONObject("djBadInfo");
        if (optJSONObject8 != null) {
            this.djBadInfo = (Map) JDJSON.parse(optJSONObject8.toJSONString());
        }
        this.configVersion = jDJSONObject.optInt("configVersion", -1);
        this.degradation = jDJSONObject.optInt(CartConstant.KEY_CONFIG_DEGRADATION, 0);
        this.config = jDJSONObject.optJSONObject("config");
        this.jsonObjectStr = str;
        this.cartResponseJSONObject = jDJSONObject;
        this.noRefreshCart = Boolean.valueOf(jDJSONObject.optBoolean("noRefreshCart"));
        if (OKLog.D) {
            OKLog.d("CartResponse", "  -->> end : ");
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAbResult(String str) {
        HashMap<String, String> hashMap;
        if (TextUtils.isEmpty(str)) {
            return "A";
        }
        CartResponseInfo cartResponseInfo = this.info;
        if (cartResponseInfo == null) {
            CartConfigCards cartConfigCards = this.cartConfigCards;
            return (cartConfigCards == null || (hashMap = cartConfigCards.emptyABCards) == null || !hashMap.containsKey(str)) ? "A" : this.cartConfigCards.emptyABCards.get(str);
        }
        HashMap<String, String> hashMap2 = cartResponseInfo.abCards;
        return (hashMap2 == null || !hashMap2.containsKey(str)) ? "A" : this.info.abCards.get(str);
    }

    public int getCode() {
        return this.code;
    }

    public long getDataLoadedTime() {
        return this.dataLoadedTime;
    }

    public String getErrorMessage() {
        String str = this.errorMessage;
        return str == null ? "" : str;
    }

    public String getImageDomain() {
        String str = this.imageDomain;
        return str == null ? "" : str;
    }

    public CartResponseInfo getInfo() {
        return this.info;
    }

    public String getMessage() {
        String str = this.message;
        return str == null ? "" : str;
    }

    public int getRefresh() {
        return this.refresh;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public String getSolve() {
        return this.solve;
    }

    public ArrayList<TabMenuEntity> getTabMenus() {
        return this.tabMenus;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isCache() {
        return this.isCache;
    }

    public void setCache(boolean z) {
        this.isCache = z;
    }

    public void setCode(Integer num) {
        this.code = num.intValue();
    }

    public void setDataLoadedTime(long j2) {
        this.dataLoadedTime = j2;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setImageDomain(String str) {
        this.imageDomain = str;
    }

    public void setInfo(CartResponseInfo cartResponseInfo) {
        this.info = cartResponseInfo;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setRefresh(int i2) {
        this.refresh = i2;
    }

    public void setResultCode(int i2) {
        this.resultCode = i2;
    }

    public void setSolve(String str) {
        this.solve = str;
    }

    public void setTabMenus(ArrayList<TabMenuEntity> arrayList) {
        this.tabMenus = arrayList;
    }

    public void setTargetUrl(String str) {
        this.targetUrl = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.info, i2);
        parcel.writeString(this.imageDomain);
        parcel.writeString(this.message);
        parcel.writeString(this.errorMessage);
        parcel.writeString(this.solve);
        parcel.writeInt(this.refresh);
        parcel.writeInt(this.resultCode);
        parcel.writeString(this.resultMsg);
        parcel.writeString(this.value);
        parcel.writeString(this.targetUrl);
        parcel.writeByte(this.isCache ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.dataLoadedTime);
        parcel.writeString(this.jsonObjectStr);
        parcel.writeParcelable(this.cartRecCoupon, i2);
        parcel.writeParcelable(this.cartAddHotListEntity, i2);
        parcel.writeString(this.userIdentity);
        parcel.writeString(this.homeWishListUserFlag);
        parcel.writeTypedList(this.tabMenus);
    }

    protected CartResponse(Parcel parcel) {
        this.info = (CartResponseInfo) parcel.readParcelable(CartResponseInfo.class.getClassLoader());
        this.imageDomain = parcel.readString();
        this.message = parcel.readString();
        this.errorMessage = parcel.readString();
        this.solve = parcel.readString();
        this.refresh = parcel.readInt();
        this.resultCode = parcel.readInt();
        this.resultMsg = parcel.readString();
        this.value = parcel.readString();
        this.targetUrl = parcel.readString();
        this.isCache = parcel.readByte() != 0;
        this.dataLoadedTime = parcel.readLong();
        this.jsonObjectStr = parcel.readString();
        this.cartRecCoupon = (CartRecCoupon) parcel.readParcelable(CartRecCoupon.class.getClassLoader());
        this.cartAddHotListEntity = (CartAddHotListEntity) parcel.readParcelable(CartAddHotListEntity.class.getClassLoader());
        this.userIdentity = parcel.readString();
        this.homeWishListUserFlag = parcel.readString();
        this.tabMenus = parcel.createTypedArrayList(TabMenuEntity.CREATOR);
    }
}
