package com.jingdong.app.mall.home.floor.model.entity;

import android.os.SystemClock;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.x.f;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.Product;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class PanicFloorEntity extends ListItemFloorEntity<Product> {
    private static final int CONTENT_HEIGHT = 227;
    public static final int[] DEFAULT_RIGHT_ARROW_COLORS = {-48001, -375478};
    private int dynamicCount;
    private String expo;
    private b expoJson;
    private int interestPointColor;
    private boolean isVersion100;
    protected String mNameText;
    protected String mNextRoundKey;
    protected String mPanicExpoSourceValue;
    private int mTitleColor;
    private int priceStyle;
    private int[] promotionTagColor;
    private int[] rightArrowColor;
    private String skuTagImg;
    protected boolean isTestA = true;
    protected ArrayMap<String, JDJSONObject> mNextRoundMap = new ArrayMap<>();
    protected int mMiaoshaAdvance = 0;
    protected BuyTimeViewData mBuyTimeViewData = new BuyTimeViewData();
    private int mPlayCount = 0;
    private String mOperateWord = "";
    private boolean mIsAddToAnimationTree = false;
    private int mBuyTimeDateSpaceX = 0;
    private JSONArray mProductJsonArr = b.d();
    protected final float PAGE_WIDTH = 0.29f;
    protected final int nameImgHeight = 70;

    /* loaded from: classes4.dex */
    public static class BuyTimeViewData {
        private static f sTimeSync = new f();
        private long nextStartTime;
        private long timeMillis;
        private long timeRemain;
        private long widthChangedOffset;
        protected int timePointColor = -16777216;
        protected int backgroundColor = -16777216;
        protected int timeTextColor = -1;
        protected int backgroundWidth = 27;
        protected int backgroundHeight = 22;
        protected int timeTextSizePx = 18;

        private long getWidthChangedOffset() {
            long j2 = this.widthChangedOffset;
            if (j2 > 0) {
                return j2;
            }
            return 0L;
        }

        public int getBackgroundColor() {
            return this.backgroundColor;
        }

        public int getBackgroundHeight() {
            return d.d(this.backgroundHeight);
        }

        public int getBackgroundWidth() {
            return d.d(this.backgroundWidth);
        }

        public int getLayoutHeight() {
            return getBackgroundHeight() + (DPIUtil.dip2px(2.0f) * 2);
        }

        public int getLayoutWidth() {
            return (getBackgroundWidth() * 3) + (DPIUtil.dip2px(2.0f) * 10);
        }

        public long getNextStartTime() {
            return this.nextStartTime;
        }

        public long getTimeOffset() {
            return (getWidthChangedOffset() + SystemClock.elapsedRealtime()) - this.timeMillis;
        }

        public int getTimePointColor() {
            return this.timePointColor;
        }

        public long getTimeRemain() {
            return this.timeRemain;
        }

        public int getTimeTextColor() {
            return this.timeTextColor;
        }

        public int getTimeTextSizePx() {
            return d.d(this.timeTextSizePx);
        }

        public void setBackgroundColor(int i2) {
            this.backgroundColor = i2;
        }

        public void setTimeInfo(boolean z, long j2, long j3, long j4) {
            this.timeMillis = SystemClock.elapsedRealtime();
            this.nextStartTime = j3;
            setWidthChangedOffset(z, j4);
            this.timeRemain = j4 > 0 ? sTimeSync.a(j2, j3, j4) : j2;
            if (!z || j4 > 0) {
                return;
            }
            sTimeSync.c(j2, j3);
        }

        public void setTimePointColor(int i2) {
            this.timePointColor = i2;
        }

        public void setTimeTextColor(int i2) {
            this.timeTextColor = i2;
        }

        public void setTimeTextSizePx(int i2) {
            this.timeTextSizePx = i2;
        }

        public void setWidthChangedOffset(boolean z, long j2) {
            if (!z && (j2 <= 0 || !sTimeSync.b(this.nextStartTime))) {
                this.widthChangedOffset = 0L;
            } else {
                this.widthChangedOffset = this.timeMillis - s.f9357c;
            }
        }
    }

    public PanicFloorEntity() {
        this.mTitleText = "\u79d2\u6740";
    }

    public void clearNextRoundMap() {
        this.mNextRoundMap.clear();
    }

    public String collectExpoJsonParam(int i2, int i3) {
        JSONArray jSONArray = this.mProductJsonArr;
        if (jSONArray == null || jSONArray.length() <= i2) {
            return "";
        }
        JSONArray d = b.d();
        while (i2 <= i3 && i2 < this.mProductJsonArr.length()) {
            try {
                d.put(this.mProductJsonArr.get(i2));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            i2++;
        }
        return d.length() > 0 ? d.toString() : "";
    }

    public int getBuyTimeDateSpaceX() {
        float factorWithMargin = getFactorWithMargin();
        if (this.mBuyTimeDateSpaceX == 0) {
            this.mBuyTimeDateSpaceX = (int) (DPIUtil.dip2px(2.0f) - ((factorWithMargin * (DPIUtil.dip2px(2.0f) - d.d(2))) / d.d(22)));
        }
        return this.mBuyTimeDateSpaceX;
    }

    public int getBuyTimeLayoutHeight() {
        return this.mBuyTimeViewData.getLayoutHeight();
    }

    public int getBuyTimeLayoutWidth() {
        return this.mBuyTimeViewData.getLayoutWidth();
    }

    public BuyTimeViewData getBuyTimeViewData() {
        return this.mBuyTimeViewData;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.ListItemFloorEntity
    public int getContentHeight() {
        return d.d(227);
    }

    public String getExpo() {
        return this.expo;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        if (TextUtils.isEmpty(this.expo)) {
            return this.mExposData;
        }
        this.mExposData.clear();
        if (!TextUtils.isEmpty(this.expo)) {
            this.mExposData.add(this.expo);
        }
        return super.getExpoData();
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        if (this.expoJson == null) {
            return super.getExpoJson();
        }
        this.mJsonExposData.clear();
        this.mJsonExposData.add(this.expoJson);
        return this.mJsonExposData;
    }

    public float getFactorWithMargin() {
        return (float) (d.d(21) - 1);
    }

    public int getInterestPointColor() {
        return this.interestPointColor;
    }

    public boolean getIsAddToAnimationTree() {
        return this.mIsAddToAnimationTree;
    }

    public int getMiaoshaAdvance() {
        return this.mMiaoshaAdvance;
    }

    public int getNameImgHeight() {
        return d.d(70);
    }

    public int getNameImgWidth() {
        return d.f9279g;
    }

    public String getNameText() {
        return this.mNameText;
    }

    public JDJSONObject getNextRoundObject() {
        return this.mNextRoundMap.get(this.mNextRoundKey);
    }

    public String getOperateWord() {
        return this.mOperateWord;
    }

    public float getPageWidth() {
        return 0.29f;
    }

    public String getPanicExpoSourceValue() {
        return this.mPanicExpoSourceValue;
    }

    public int getPlayCount() {
        return this.mPlayCount;
    }

    public int getPriceStyle() {
        return this.priceStyle;
    }

    public String getProductJsonByPosition(int i2) {
        try {
            return this.mProductJsonArr.get(i2).toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public int[] getPromotionTagColor() {
        return this.promotionTagColor;
    }

    public int[] getRightArrowColor() {
        return this.rightArrowColor;
    }

    public String getRightCornerAdView() {
        b bVar = new b();
        bVar.a(DeeplinkProductDetailHelper.LAYER_STYLE, this.tpl);
        if (!TextUtils.isEmpty(this.element.l())) {
            bVar.e(b.c(this.element.l()));
        }
        return bVar.toString();
    }

    public String getSkuTagImg() {
        return this.skuTagImg;
    }

    public int getTitleColor() {
        return this.mTitleColor;
    }

    public boolean isTestA() {
        return this.isTestA;
    }

    public boolean isVersion100() {
        return this.isVersion100;
    }

    public void parseJsonParam() {
        List<Product> itemList = getItemList();
        if (itemList == null) {
            return;
        }
        this.mProductJsonArr = b.d();
        for (Product product : itemList) {
            if (product != null) {
                b bVar = new b();
                bVar.e(b.c(this.element.l()));
                JumpEntity jumpEntity = product.jump;
                if (jumpEntity != null) {
                    bVar.e(b.c(jumpEntity.srv));
                }
                bVar.a(DeeplinkProductDetailHelper.LAYER_STYLE, this.tpl);
                bVar.a(PdMtaUtil.PARAM_KEY_SKUID, Long.valueOf(product.getId() == null ? 0L : product.getId().longValue()));
                this.mProductJsonArr.put(bVar);
            }
        }
    }

    public void setExpo(String str) {
        this.expo = str;
    }

    public void setExpoJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.expoJson = b.c(str);
    }

    public void setInterestPointColor(int i2) {
        this.interestPointColor = i2;
    }

    public void setIsAddToAnimationTree(boolean z) {
        this.mIsAddToAnimationTree = z;
    }

    public void setIsTestA(boolean z) {
        this.isTestA = z;
    }

    public void setMiaoshaAdvance(int i2) {
        if (i2 < 0) {
            this.mMiaoshaAdvance = 0;
        } else {
            this.mMiaoshaAdvance = i2;
        }
    }

    public void setNameText(String str) {
        this.mNameText = m.g(str);
    }

    public void setNextRoundKey(String str) {
        this.mNextRoundKey = str;
    }

    public void setNextRoundObject(JDJSONObject jDJSONObject) {
        this.mNextRoundMap.put(this.mNextRoundKey, jDJSONObject);
    }

    public void setOperateWord(String str) {
        this.mOperateWord = str;
    }

    public void setPanicExpoSourceValue(String str) {
        this.mPanicExpoSourceValue = m.g(str);
    }

    public void setPlayCount(int i2) {
        this.mPlayCount = i2;
    }

    public void setPriceStyle(int i2) {
        this.priceStyle = i2;
    }

    public void setPromotionTagColor(int[] iArr) {
        this.promotionTagColor = iArr;
    }

    public void setRightArrowColor(int[] iArr) {
        this.rightArrowColor = iArr;
    }

    public void setSkuTagImg(String str) {
        this.skuTagImg = str;
    }

    public void setTimeInfo(boolean z, Long l2, long j2) {
        this.mBuyTimeViewData.setTimeInfo(z, l2.longValue(), j2, this.mModel.U);
    }

    public void setTitleColor(int i2) {
        this.mTitleColor = i2;
    }

    public void setVersion100(boolean z) {
        this.isVersion100 = z;
    }
}
