package com.jingdong.common.shop;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JshopCoupon implements Parcelable {
    public static final int COUPON_STATUS_INVALID = 2;
    public static final int COUPON_STATUS_RECEIVED = 1;
    public static final int COUPON_STATUS_ROBED = 3;
    public static final Parcelable.Creator<JshopCoupon> CREATOR = new Parcelable.Creator<JshopCoupon>() { // from class: com.jingdong.common.shop.JshopCoupon.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JshopCoupon createFromParcel(Parcel parcel) {
            return new JshopCoupon(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JshopCoupon[] newArray(int i2) {
            return new JshopCoupon[i2];
        }
    };
    public String act;
    public String appid;
    public Boolean applicability;
    public String beginTime;
    public String biinfo;
    public String channel;
    public String channelDetail;
    public String couponId;
    public String couponJson;
    public String couponJumpUrl;
    public String couponPos;
    public String couponSource;
    public String couponSourceDetail;
    public List<CouponStair> couponStairList;
    public int couponStatus;
    public boolean crmCoupon;
    public String crmCouponId;
    public double discount;
    public String endTime;
    public boolean financeCoupon;
    public String getType;
    public String imageUrl;
    public boolean isfansCoupon;
    public long mBatchId;
    public String mJshopName;
    public String mJsonObjecStr;
    public int mTemplateId;
    public double maxDiscount;
    public double minDiscount;
    public String name;
    public int platformType;
    public String platformid;
    public String position;
    public int quota;
    public int remain;
    public String skus;
    public int style;
    public String subChannel;
    public int takeRule;
    public int takeType;
    public String takeUrl;
    public int type;
    public String venderId;

    public JshopCoupon() {
        this.getType = "1";
    }

    public static ArrayList<JshopCoupon> toList(JDJSONArray jDJSONArray) {
        ArrayList<JshopCoupon> arrayList = new ArrayList<>();
        if (jDJSONArray != null && jDJSONArray.size() > 0) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    arrayList.add(new JshopCoupon(optJSONObject));
                }
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String generateCouponTip() {
        StringBuilder sb = new StringBuilder();
        if (this.style == 3) {
            List<CouponStair> list = this.couponStairList;
            if (list != null && list.size() > 0) {
                if (list.size() == 1 && list.get(0) != null) {
                    sb.append(String.format("\u6ee1%s\u4eab%s\u6298", list.get(0).quota + "", list.get(0).discount + ""));
                } else {
                    int size = list.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        if (i2 == 0) {
                            sb.append(String.format("\u6ee1%s\u4eab%s\u6298", list.get(i2).quota + "", list.get(i2).discount + ""));
                        } else {
                            sb.append(String.format("\uff0c\u6ee1%s\u4eab%s\u6298", list.get(i2).quota + "", list.get(i2).discount + ""));
                        }
                    }
                }
            }
        } else if (this.type == 0 || this.quota == 0) {
            sb.append(String.format("%s\u5143\u4eac\u52b5", this.discount + ""));
        } else {
            sb.append(String.format("\u6ee1%s\u51cf%s", this.quota + "", this.discount + ""));
        }
        return String.format("\u9650\u65f6\u4fc3\u9500\uff1a\u4ee5\u4e0b\u5546\u54c1\u53ef\u4f7f\u7528%s\u4f18\u60e0\u5238", sb.toString());
    }

    public JSONObjectProxy getJSObjProxy() {
        try {
            return new JSONObjectProxy(new JSONObject(this.mJsonObjecStr));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.venderId);
        parcel.writeString(this.couponId);
        parcel.writeString(this.name);
        parcel.writeString(this.couponJson);
        parcel.writeInt(this.type);
        parcel.writeInt(this.platformType);
        parcel.writeDouble(this.discount);
        parcel.writeInt(this.quota);
        parcel.writeString(this.beginTime);
        parcel.writeString(this.endTime);
        parcel.writeInt(this.takeRule);
        parcel.writeInt(this.remain);
        parcel.writeValue(this.applicability);
        parcel.writeString(this.act);
        parcel.writeString(this.mJsonObjecStr);
        parcel.writeString(this.mJshopName);
        parcel.writeInt(this.mTemplateId);
        parcel.writeLong(this.mBatchId);
        parcel.writeString(this.imageUrl);
        parcel.writeInt(this.couponStatus);
        parcel.writeValue(Boolean.valueOf(this.crmCoupon));
        parcel.writeString(this.crmCouponId);
        parcel.writeInt(this.style);
        parcel.writeDouble(this.minDiscount);
        parcel.writeDouble(this.maxDiscount);
        parcel.writeTypedList(this.couponStairList);
        parcel.writeString(this.couponJumpUrl);
        parcel.writeValue(Boolean.valueOf(this.isfansCoupon));
        parcel.writeValue(Boolean.valueOf(this.financeCoupon));
        parcel.writeInt(this.takeType);
        parcel.writeString(this.takeUrl);
    }

    public JshopCoupon(JDJSONObject jDJSONObject, String str) {
        this(jDJSONObject);
        this.mJshopName = str;
    }

    public JshopCoupon(JDJSONObject jDJSONObject, int i2) {
        this(jDJSONObject);
        this.mTemplateId = i2;
    }

    public JshopCoupon(JDJSONObject jDJSONObject) {
        int size;
        this.getType = "1";
        if (jDJSONObject == null) {
            return;
        }
        this.mJsonObjecStr = jDJSONObject.toString();
        this.venderId = jDJSONObject.optString("venderId");
        this.couponId = jDJSONObject.optString("couponId");
        this.name = jDJSONObject.optString("name");
        this.type = jDJSONObject.optInt("type");
        this.discount = jDJSONObject.optDouble("discount");
        this.quota = jDJSONObject.optInt("quota");
        this.beginTime = jDJSONObject.optString(JshopConst.JSKEY_COUPON_BEGIN_TIME);
        this.endTime = jDJSONObject.optString("endTime");
        this.takeRule = jDJSONObject.optInt(JshopConst.JSKEY_COUPON_TAKERULE);
        this.remain = jDJSONObject.optInt(JshopConst.JSKEY_COUPON_REAIN);
        this.applicability = Boolean.valueOf(jDJSONObject.optBoolean(JshopConst.JSKEY_APPLI));
        this.act = jDJSONObject.optString("act");
        this.platformType = jDJSONObject.optInt(JshopConst.JSKEY_COUPON_PLATFORM);
        this.mBatchId = jDJSONObject.optInt(JshopConst.JSKEY_BATCH_ID);
        this.imageUrl = jDJSONObject.optString("imageUrl");
        this.couponStatus = jDJSONObject.optInt("couponStatus");
        this.crmCoupon = jDJSONObject.optBoolean("crmCoupon");
        this.crmCouponId = jDJSONObject.optString("crmCouponId");
        this.style = jDJSONObject.optInt(DeeplinkProductDetailHelper.LAYER_STYLE, 1);
        this.minDiscount = jDJSONObject.optDouble("minDiscount", -1.0d);
        this.maxDiscount = jDJSONObject.optDouble("maxDiscount", -1.0d);
        this.couponJumpUrl = jDJSONObject.optString("couponJumpUrl");
        this.isfansCoupon = jDJSONObject.optBoolean("isfansCoupon");
        this.couponSource = jDJSONObject.optString("couponSource");
        this.couponSourceDetail = jDJSONObject.optString("couponSourceDetail");
        this.channel = jDJSONObject.optString("channel");
        this.subChannel = jDJSONObject.optString("subChannel");
        this.channelDetail = jDJSONObject.optString("channelDetail");
        this.biinfo = jDJSONObject.optString("biinfo");
        this.skus = jDJSONObject.optString("skus");
        this.appid = jDJSONObject.optString("appid");
        this.platformid = jDJSONObject.optString("platformid");
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("stairs");
        if (optJSONArray != null && (size = optJSONArray.size()) > 0) {
            this.couponStairList = new ArrayList();
            for (int i2 = 0; i2 < size; i2++) {
                this.couponStairList.add(new CouponStair(optJSONArray.optJSONObject(i2)));
            }
        }
        this.financeCoupon = jDJSONObject.optBoolean("financeCoupon", false);
        this.takeType = jDJSONObject.optInt("takeType");
        this.takeUrl = jDJSONObject.optString("takeUrl");
    }

    protected JshopCoupon(Parcel parcel) {
        this.getType = "1";
        this.venderId = parcel.readString();
        this.couponId = parcel.readString();
        this.name = parcel.readString();
        this.couponJson = parcel.readString();
        this.type = parcel.readInt();
        this.platformType = parcel.readInt();
        this.discount = parcel.readDouble();
        this.quota = parcel.readInt();
        this.beginTime = parcel.readString();
        this.endTime = parcel.readString();
        this.takeRule = parcel.readInt();
        this.remain = parcel.readInt();
        this.applicability = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.act = parcel.readString();
        this.mJsonObjecStr = parcel.readString();
        this.mJshopName = parcel.readString();
        this.mTemplateId = parcel.readInt();
        this.mBatchId = parcel.readLong();
        this.imageUrl = parcel.readString();
        this.couponStatus = parcel.readInt();
        this.crmCoupon = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.crmCouponId = parcel.readString();
        this.style = parcel.readInt();
        this.minDiscount = parcel.readDouble();
        this.maxDiscount = parcel.readDouble();
        this.couponStairList = parcel.createTypedArrayList(CouponStair.CREATOR);
        this.couponJumpUrl = parcel.readString();
        this.isfansCoupon = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.financeCoupon = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.takeType = parcel.readInt();
        this.takeUrl = parcel.readString();
    }
}
