package com.jingdong.common.sample.jshopmember.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.entity.MultiSuppliers;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.WareRankInfo;
import com.jingdong.common.sample.jshop.utils.ButtonStatus;
import com.jingdong.common.sample.jshop.utils.SpannableStringUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes6.dex */
public class JshopProduct extends Product implements Parcelable {
    public static final Parcelable.Creator<JshopProduct> CREATOR = new Parcelable.Creator<JshopProduct>() { // from class: com.jingdong.common.sample.jshopmember.entity.JshopProduct.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JshopProduct createFromParcel(Parcel parcel) {
            return new JshopProduct(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JshopProduct[] newArray(int i2) {
            return new JshopProduct[i2];
        }
    };
    private String customerPrice;
    public boolean isOver;
    public boolean isValidPrice;
    private String jdPrice;
    public ButtonStatus mBtnStatus;
    public boolean mHasStart;
    public int position;
    public String promotionId;
    public long serverTime;
    public int status;
    public int subStatus;

    public JshopProduct() {
        this.jdPrice = "";
        this.mHasStart = false;
        this.promotionId = "";
        this.position = 0;
        this.isOver = false;
        this.mBtnStatus = new ButtonStatus();
        this.isValidPrice = true;
    }

    public static ArrayList<JshopProduct> toShopProductList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<JshopProduct> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<JshopProduct> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    JshopProduct jshopProduct = new JshopProduct(jSONArrayPoxy.getJSONObject(i3), i2, null);
                    if (!TextUtils.isEmpty("" + jshopProduct.getId())) {
                        arrayList2.add(jshopProduct);
                    }
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    e.printStackTrace();
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public void createStauts() {
        if (this.mHasStart) {
            if (this.status == 0) {
                this.mBtnStatus.setStatus(11004);
            } else {
                this.mBtnStatus.setStatus(11005);
            }
        } else if (this.isOver) {
            this.mBtnStatus.setStatus(11005);
        } else if (this.subStatus == 1) {
            this.mBtnStatus.setStatus(11003);
        } else {
            this.mBtnStatus.setStatus(11002);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCustomerPrice() {
        String str;
        Double valueOf;
        try {
            str = "";
            if (!TextUtils.isEmpty(this.customerPrice) && (valueOf = Double.valueOf(this.customerPrice)) != null && valueOf.doubleValue() > 0.0d) {
                str = new DecimalFormat("0.00").format(valueOf);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            str = this.customerPrice;
        }
        return TextUtils.isEmpty(str) ? StringUtil.no_price : str;
    }

    public Spannable getFormatJdPrice() {
        Spannable spannable;
        try {
            if (this.isValidPrice) {
                spannable = SpannableStringUtils.getJDPriceSpan("\u00a5" + getJdPrice(), -1, DPIUtil.dip2px(17.0f));
            } else {
                String jdPrice = getJdPrice();
                if ("\u5f85\u53d1\u5e03".equals(jdPrice)) {
                    jdPrice = "\u00a5" + getJdPrice();
                }
                spannable = SpannableStringUtils.getJDPriceSpan(jdPrice, -1, DPIUtil.dip2px(15.0f));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            spannable = null;
        }
        if (spannable == null) {
            SpannableString spannableString = new SpannableString(StringUtil.no_price);
            spannableString.setSpan(new AbsoluteSizeSpan(DPIUtil.dip2px(15.0f)), 0, 4, 33);
            return spannableString;
        }
        return spannable;
    }

    @Override // com.jingdong.common.entity.Product
    public String getJdPrice() {
        String str;
        Double valueOf;
        try {
            str = "";
            if (!TextUtils.isEmpty(this.jdPrice) && (valueOf = Double.valueOf(this.jdPrice)) != null && valueOf.doubleValue() > 0.0d) {
                str = new DecimalFormat("0.00").format(valueOf);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            str = this.jdPrice;
        }
        return TextUtils.isEmpty(str) ? StringUtil.no_price : str;
    }

    public void setCustomerPrice(String str) {
        this.customerPrice = str;
    }

    @Override // com.jingdong.common.entity.Product
    public void setJdPrice(String str) {
        this.jdPrice = str;
        try {
            Double.valueOf(str);
            this.isValidPrice = true;
        } catch (Exception e2) {
            this.isValidPrice = false;
            e2.printStackTrace();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.jdPrice);
        parcel.writeInt(this.status);
        parcel.writeInt(this.subStatus);
        parcel.writeLong(this.serverTime);
        parcel.writeByte(this.mHasStart ? (byte) 1 : (byte) 0);
        parcel.writeString(this.promotionId);
        parcel.writeInt(this.position);
        parcel.writeByte(this.isOver ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.mBtnStatus, i2);
        parcel.writeByte(this.isValidPrice ? (byte) 1 : (byte) 0);
    }

    public JshopProduct(JSONObjectProxy jSONObjectProxy, int i2, Object[] objArr) {
        this.jdPrice = "";
        this.mHasStart = false;
        this.promotionId = "";
        this.position = 0;
        this.isOver = false;
        this.mBtnStatus = new ButtonStatus();
        this.isValidPrice = true;
        this.samsPrice = jSONObjectProxy.optString("spPrice");
        this.stock = jSONObjectProxy.optInt("stock", -1);
        this.isUnderCarriage = jSONObjectProxy.optInt("isUnderCarriage", 0);
        setId(jSONObjectProxy.getLongOrNull("wareId"));
        setImage(jSONObjectProxy.getStringOrNull("imageurl"), jSONObjectProxy.getStringOrNull("longImgUrl"));
        setStockQuantity(jSONObjectProxy.optInt("stockQuantity", 0));
        setName(jSONObjectProxy.getStringOrNull("wname"));
        setAdWord(jSONObjectProxy.getStringOrNull("adword"));
        setMarketPrice(jSONObjectProxy.getStringOrNull("martPrice"));
        setAuthor(jSONObjectProxy.optString("author"));
        setBook(Boolean.valueOf(jSONObjectProxy.optBoolean("isbook")));
        if (objArr != null && objArr.length > 0) {
            setIsBookDisc((Boolean) objArr[0]);
            setIsFood((Boolean) objArr[1]);
            setIsShowNetcontent((Boolean) objArr[2]);
            setLogid((String) objArr[3]);
            setAdEventId((String) objArr[4]);
        }
        setJdPrice(jSONObjectProxy.getStringOrNull(JshopConst.JSKEY_PRODUCT_JDPRICE));
        addJshopIcon(jSONObjectProxy);
        addNewProductListScore(jSONObjectProxy);
        setMultiSuppliers(new MultiSuppliers(jSONObjectProxy));
        setShopName(jSONObjectProxy.getStringOrNull("shopName"));
        setAvailableInStore(jSONObjectProxy.getBooleanOrNull("availableInStore"));
        setPriority(jSONObjectProxy.getStringOrNull(RemoteMessageConst.Notification.PRIORITY));
        setStockStateStr(jSONObjectProxy.getStringOrNull(CartConstant.KEY_SKU_STOCKSTATE));
        JSONObjectProxy jSONObjectOrNull = jSONObjectProxy.getJSONObjectOrNull("wareRank");
        if (jSONObjectOrNull != null) {
            WareRankInfo wareRankInfo = new WareRankInfo(jSONObjectOrNull);
            this.wareRank = wareRankInfo;
            setWareRank(wareRankInfo);
        }
        setIsHaveProductLogTag(jSONObjectProxy.optBoolean("loc", false));
        setSelf(jSONObjectProxy.optBoolean("self", false));
        setIsInternational(jSONObjectProxy.getBooleanOrNull("international"));
        setIsPromotionShan(jSONObjectProxy.getBooleanOrNull("flashBuy"));
        setIsPromotionTuan(Boolean.valueOf(jSONObjectProxy.optBoolean("groupBuyFlag", false)));
        setPromotionIconUrl(jSONObjectProxy.getStringOrNull("promotionIconUrl"));
        setInterlImgUrl(jSONObjectProxy.getStringOrNull("interlImgUrl"));
        setIsEche(jSONObjectProxy.getBooleanOrNull("eche"));
        setToMURL(jSONObjectProxy.getStringOrNull("toMURL"));
        setTargetUrl(jSONObjectProxy.getStringOrNull("targetUrl"));
        setFlowOrder(jSONObjectProxy.getIntOrNull("flow_order"));
        setCustomAttr(jSONObjectProxy.getStringOrNull("customAttr"));
        setCustomAttrList(jSONObjectProxy.getJSONArrayOrNull("customAttrList"));
        setFullCut(jSONObjectProxy.optString("upToSaving"));
        setIsBooking(jSONObjectProxy.optBoolean("isBooking"));
        setDiffMobilePrice(jSONObjectProxy.optString("diffMobilePrice"));
        setFlags(jSONObjectProxy.optString("flags"));
        setP13nFlags(jSONObjectProxy.optString("p13nFlags"));
        setCatId(Long.valueOf(jSONObjectProxy.optLong("catid")));
        setPriceKeep(jSONObjectProxy.optBoolean("priceKeep"));
        JSONArrayPoxy jSONArrayOrNull = jSONObjectProxy.getJSONArrayOrNull("mySearchWareTags");
        if (jSONArrayOrNull != null) {
            try {
                if (TextUtils.isEmpty(jSONArrayOrNull.toString())) {
                    return;
                }
                setMySearchProductTagInfos(JDJSON.parseArray(jSONArrayOrNull.toString(), String.class));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    protected JshopProduct(Parcel parcel) {
        this.jdPrice = "";
        this.mHasStart = false;
        this.promotionId = "";
        this.position = 0;
        this.isOver = false;
        this.mBtnStatus = new ButtonStatus();
        this.isValidPrice = true;
        this.jdPrice = parcel.readString();
        this.status = parcel.readInt();
        this.subStatus = parcel.readInt();
        this.serverTime = parcel.readLong();
        this.mHasStart = parcel.readByte() != 0;
        this.promotionId = parcel.readString();
        this.position = parcel.readInt();
        this.isOver = parcel.readByte() != 0;
        this.mBtnStatus = (ButtonStatus) parcel.readParcelable(ButtonStatus.class.getClassLoader());
        this.isValidPrice = parcel.readByte() != 0;
    }
}
