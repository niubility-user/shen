package com.jingdong.common.deeplinkhelper.imhelper;

import android.os.Bundle;
import android.text.TextUtils;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DDParameterBuilder {
    public static final String ACTION_BROADCAST_APP_GET_UNREAD_MSG_COUNT = "com.jd.IM_JIMI_GET_UNREAD_MSG_COUNT";
    public static final String ACTION_BROADCAST_APP_REQUEST_LATEST_MSG = "com.jd.IM_JIMI_REQUEST_LATEST_MSG";
    public static final String ACTION_BROADCAST_ENTRY_ASK = "com.jd.start.dd.entryask";
    public static final String ACTION_BROADCAST_GLOBAL_ASK = "com.jd.start.dd.globalask";
    public static final String ACTION_BROADCAST_M_PRODUCT_ASK = "com.jd.start.dd.m.productask";
    public static final String ACTION_BROADCAST_ORDER_ASK = "com.jd.start.dd.orderask";
    public static final String ACTION_BROADCAST_PRODUCT_ASK = "com.jd.start.dd.productask";
    public static final String ACTION_BROADCAST_SHOP_ASK = "com.jd.start.dd.shopask";
    public static final String ACTION_BROADCAST_START_IM = "com.jd.start.jd.im.dd";
    public static final String ACTION_BROADCAST_START_IM_CUSTOMER_SERVICE_MANAGER = "com.jd.start.dd.customer.service.mgr";
    public static final String ACTION_BROADCAST_START_IM_PRIVATEORDERASK = "com.jd.start.dd.privateorderask";
    public static final String ACTION_BROADCAST_START_LOGOUT = "com.jd.start.jd.im.dd.logout";
    private static final String TAG = "DDParameterBuilder";
    private String bbtf;
    private int buId;
    private int channelId;
    private int channelTag;
    private String class3;
    private String mAskType;
    private String mAvatar;
    private String mContent;
    private int mCustomType;
    private String mDirectWaiter;
    private Integer mDisputeId;
    private String mEntry;
    private String mExtRelativeId;
    private String mFrom;
    private String mGid;
    private String mMsgCenterParam;
    private String mName;
    private String mOrderID;
    private String mOrderTime;
    private String mOrderValue;
    private String mPin;
    private String mProductImageUrl;
    private String mProductName;
    private String mProductPrice;
    private String mProductUrl;
    private String mServiceId;
    private String mShopID;
    private String mSkillID;
    private String mSkuID;
    private String mStoreId;
    private String mTemplate;
    private String mToApp;
    private String mToPin;
    private String mVenderID;
    private String searchKeyword;

    public DDParameterBuilder(String str) {
        this.mCustomType = -1;
        this.mCustomType = -1;
        this.mPin = str;
    }

    public static DDParameterBuilder convertFromWebParameter(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("orderID");
        if (string != null) {
            bundle.remove("orderID");
            bundle.putString("orderId", string);
        }
        String string2 = bundle.getString("skuID");
        if (string2 != null) {
            bundle.remove("skuID");
            bundle.putString("pid", string2);
        }
        String string3 = bundle.getString("skillID");
        if (string3 != null) {
            bundle.remove("skillID");
            bundle.putString("skillId", string3);
        }
        String string4 = bundle.getString("skuId");
        if (string4 != null) {
            bundle.remove("skuId");
            bundle.putString("pid", string4);
        }
        String string5 = bundle.getString("name");
        if (string5 != null) {
            bundle.remove("name");
            bundle.putString("pname", string5);
        }
        String string6 = bundle.getString("imgUrl");
        if (string6 != null) {
            bundle.remove("imgUrl");
            bundle.putString("purl", string6);
        }
        String string7 = bundle.getString("price");
        if (string7 != null) {
            bundle.remove("price");
            bundle.putString("pprice", string7);
        }
        DDParameterBuilder generateWithPin = generateWithPin();
        if (generateWithPin == null) {
            return null;
        }
        generateWithPin.addFrom(bundle.getString("from"));
        generateWithPin.addSkuID(bundle.getString("pid"));
        generateWithPin.addProductName(bundle.getString("pname"));
        generateWithPin.addProductPrice(bundle.getString("pprice"));
        generateWithPin.addProductImageUrl(bundle.getString("purl"));
        generateWithPin.addShopID(bundle.getString("shopId"));
        generateWithPin.addVenderID(bundle.getString("venderId"));
        generateWithPin.addOrderID(bundle.getString("orderId"));
        generateWithPin.addProductUrl(bundle.getString("productUrl"));
        generateWithPin.addOrderValue(bundle.getString("orderValue"));
        generateWithPin.addOrderTime(bundle.getString("orderTime"));
        generateWithPin.addSkillID(bundle.getString("skillId"));
        generateWithPin.addEntry(bundle.getString("entry"));
        generateWithPin.addServiceId(bundle.getString("serviceId"));
        generateWithPin.addextRelativeId(bundle.getString("extRelativeId"));
        generateWithPin.addContent(bundle.getString("content"));
        generateWithPin.addTemplate(bundle.getString(MobileCertConstants.TEMPLATE));
        generateWithPin.addCustomeType(bundle.getInt("customer_type", -1));
        generateWithPin.addMsgCenterParam(bundle.getString("msgCenterParam"));
        generateWithPin.addDisputeId(Integer.valueOf(bundle.getInt("disputeId", -1)));
        return generateWithPin;
    }

    public static DDParameterBuilder generateWithPin() {
        String loginUserName = LoginUserBase.getLoginUserName();
        if (TextUtils.isEmpty(loginUserName)) {
            return null;
        }
        return new DDParameterBuilder(loginUserName);
    }

    public static DDParameterBuilder generateWithoutPin() {
        return new DDParameterBuilder("");
    }

    private String getAction() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("from", this.mFrom);
            jSONObject.put("pin", this.mPin);
            jSONObject.put(MiaoShaPublicConstants.KEY_GID, this.mGid);
            int i2 = this.mCustomType;
            if (i2 != -1) {
                jSONObject.put("customer_type", i2);
            }
            String body = getBody();
            if (body != null) {
                jSONObject.put("body", body);
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
            if (OKLog.D) {
                OKLog.d(TAG, e2.toString());
                return null;
            }
            return null;
        }
    }

    private String getBody() {
        try {
            JSONObject jSONObject = new JSONObject();
            String str = this.mSkuID;
            if (str != null) {
                jSONObject.put("pid", str);
            }
            String str2 = this.mProductName;
            if (str2 != null) {
                jSONObject.put("pname", str2);
            }
            String str3 = this.mProductPrice;
            if (str3 != null) {
                jSONObject.put("pprice", str3);
            }
            String str4 = this.mProductImageUrl;
            if (str4 != null) {
                jSONObject.put("purl", str4);
            }
            String str5 = this.mShopID;
            if (str5 != null) {
                jSONObject.put("shopId", str5);
            }
            String str6 = this.mVenderID;
            if (str6 != null) {
                jSONObject.put("venderId", str6);
            }
            String str7 = this.mStoreId;
            if (str7 != null) {
                jSONObject.put("storeId", str7);
            }
            String str8 = this.mAskType;
            if (str8 != null) {
                jSONObject.put("askType", str8);
            }
            String str9 = this.mDirectWaiter;
            if (str9 != null) {
                jSONObject.put("directWaiter", str9);
            }
            String str10 = this.searchKeyword;
            if (str10 != null) {
                jSONObject.put("searchKeyword", str10);
            }
            String str11 = this.class3;
            if (str11 != null) {
                jSONObject.put("class3", str11);
            }
            int i2 = this.buId;
            if (i2 > 0) {
                jSONObject.put("buId", i2);
            }
            int i3 = this.channelId;
            if (i3 > 0) {
                jSONObject.put("channelId", i3);
            }
            int i4 = this.channelTag;
            if (i4 > 0) {
                jSONObject.put("channelTag", i4);
            }
            if (!TextUtils.isEmpty(this.bbtf)) {
                jSONObject.put("bbtf", this.bbtf);
            }
            String str12 = this.mOrderID;
            if (str12 != null) {
                jSONObject.put("orderId", str12);
            }
            String str13 = this.mProductUrl;
            if (str13 != null) {
                jSONObject.put("productUrl", str13);
            }
            String str14 = this.mOrderValue;
            if (str14 != null) {
                jSONObject.put("orderValue", str14);
            }
            String str15 = this.mOrderTime;
            if (str15 != null) {
                jSONObject.put("orderTime", str15);
            }
            String str16 = this.mSkillID;
            if (str16 != null) {
                jSONObject.put("skillId", str16);
            }
            Integer num = this.mDisputeId;
            if (num != null) {
                jSONObject.put("disputeId", num);
            }
            String str17 = this.mEntry;
            if (str17 != null) {
                jSONObject.put("entry", str17);
            }
            String str18 = this.mServiceId;
            if (str18 != null) {
                jSONObject.put("serviceId", str18);
            }
            String str19 = this.mExtRelativeId;
            if (str19 != null) {
                jSONObject.put("extRelativeId", str19);
            }
            String str20 = this.mContent;
            if (str20 != null) {
                jSONObject.put("content", str20);
            }
            String str21 = this.mTemplate;
            if (str21 != null) {
                jSONObject.put(MobileCertConstants.TEMPLATE, str21);
            }
            String str22 = this.mMsgCenterParam;
            if (str22 != null) {
                jSONObject.put("msgCenterParam", str22);
            }
            String str23 = this.mToPin;
            if (str23 != null) {
                jSONObject.put("toPin", str23);
            }
            String str24 = this.mToApp;
            if (str24 != null) {
                jSONObject.put("toApp", str24);
            }
            String str25 = this.mName;
            if (str25 != null) {
                jSONObject.put("name", str25);
            }
            String str26 = this.mAvatar;
            if (str26 != null) {
                jSONObject.put("avatar", str26);
            }
            if (jSONObject.length() > 0) {
                return jSONObject.toString();
            }
            return null;
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
            if (OKLog.D) {
                OKLog.d(TAG, e2.toString());
            }
            return null;
        }
    }

    public DDParameterBuilder addAskType(String str) {
        this.mAskType = str;
        return this;
    }

    public DDParameterBuilder addAvatar(String str) {
        this.mAvatar = str;
        return this;
    }

    public DDParameterBuilder addBbtf(String str) {
        this.bbtf = str;
        return this;
    }

    public DDParameterBuilder addBuId(int i2) {
        this.buId = i2;
        return this;
    }

    public DDParameterBuilder addChannelId(int i2) {
        this.channelId = i2;
        return this;
    }

    public DDParameterBuilder addChannelTag(int i2) {
        this.channelTag = i2;
        return this;
    }

    public DDParameterBuilder addClass3(String str) {
        this.class3 = str;
        return this;
    }

    public DDParameterBuilder addContent(String str) {
        this.mContent = str;
        return this;
    }

    public DDParameterBuilder addCustomeType(int i2) {
        this.mCustomType = i2;
        return this;
    }

    public DDParameterBuilder addDirectWaiter(String str) {
        this.mDirectWaiter = str;
        return this;
    }

    public DDParameterBuilder addDisputeId(Integer num) {
        this.mDisputeId = num;
        return this;
    }

    public DDParameterBuilder addEntry(String str) {
        this.mEntry = str;
        return this;
    }

    public DDParameterBuilder addFrom(String str) {
        this.mFrom = str;
        return this;
    }

    public DDParameterBuilder addGid(String str) {
        this.mGid = str;
        return this;
    }

    public DDParameterBuilder addMsgCenterParam(String str) {
        this.mMsgCenterParam = str;
        return this;
    }

    public DDParameterBuilder addName(String str) {
        this.mName = str;
        return this;
    }

    public DDParameterBuilder addOrderID(String str) {
        this.mOrderID = str;
        return this;
    }

    public DDParameterBuilder addOrderTime(String str) {
        this.mOrderTime = str;
        return this;
    }

    public DDParameterBuilder addOrderValue(String str) {
        this.mOrderValue = str;
        return this;
    }

    public DDParameterBuilder addProductImageUrl(String str) {
        this.mProductImageUrl = str;
        return this;
    }

    public DDParameterBuilder addProductName(String str) {
        this.mProductName = str;
        return this;
    }

    public DDParameterBuilder addProductPrice(String str) {
        this.mProductPrice = str;
        return this;
    }

    public DDParameterBuilder addProductUrl(String str) {
        this.mProductUrl = str;
        return this;
    }

    public DDParameterBuilder addSearchKeyword(String str) {
        this.searchKeyword = str;
        return this;
    }

    public DDParameterBuilder addServiceId(String str) {
        this.mServiceId = str;
        return this;
    }

    public DDParameterBuilder addShopID(String str) {
        this.mShopID = str;
        return this;
    }

    public DDParameterBuilder addSkillID(String str) {
        this.mSkillID = str;
        return this;
    }

    public DDParameterBuilder addSkuID(String str) {
        this.mSkuID = str;
        return this;
    }

    public DDParameterBuilder addStoreId(String str) {
        this.mStoreId = str;
        return this;
    }

    public DDParameterBuilder addTemplate(String str) {
        this.mTemplate = str;
        return this;
    }

    public DDParameterBuilder addToApp(String str) {
        this.mToApp = str;
        return this;
    }

    public DDParameterBuilder addToPin(String str) {
        this.mToPin = str;
        return this;
    }

    public DDParameterBuilder addVenderID(String str) {
        this.mVenderID = str;
        return this;
    }

    public DDParameterBuilder addextRelativeId(String str) {
        this.mExtRelativeId = str;
        return this;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("action", getAction());
        return bundle;
    }
}
