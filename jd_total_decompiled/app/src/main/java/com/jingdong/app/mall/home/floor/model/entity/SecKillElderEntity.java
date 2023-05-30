package com.jingdong.app.mall.home.floor.model.entity;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import androidx.collection.ArrayMap;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.app.mall.home.category.floor.feedssub.a;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.g.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.g;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.cart.CartPromotion;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class SecKillElderEntity extends FloorEntity {
    private boolean isCacheData;
    private boolean isShowOperate;
    private LadySecKillDataChangeCallBack mChangeCallBack;
    private f mElement;
    private h mModel;
    private String mNameText;
    private String mNextRoundKey;
    private JumpEntity mOperateJump;
    private b.a mBuyTimeViewData = new b.a();
    private CopyOnWriteArrayList<Product> mProducts = new CopyOnWriteArrayList<>();
    private ArrayMap<String, JDJSONObject> mNextRoundMap = new ArrayMap<>();
    private String priceColor = "";
    private JSONArray mProductJsonArr = com.jingdong.app.mall.home.r.c.b.d();
    private int mAdvance = 0;
    private int mPlayCount = 0;
    private String mPanicExpoSourceValue = "";
    private String mOperateWord = "";

    /* loaded from: classes4.dex */
    public interface LadySecKillDataChangeCallBack {
        void onChange();
    }

    public static boolean isValid(g gVar) {
        ArrayList<f> arrayList;
        f fVar;
        JDJSONObject h2;
        return (gVar == null || (arrayList = gVar.f10682c) == null || arrayList.size() <= 0 || (fVar = arrayList.get(0)) == null || (h2 = fVar.h()) == null || h2.size() == 0) ? false : true;
    }

    public void clearNextRoundMap() {
        this.mNextRoundMap.clear();
    }

    public b.a getBuyTimeViewData() {
        return this.mBuyTimeViewData;
    }

    public f getElement() {
        return this.mElement;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        if (isValid()) {
            return this.mExposData;
        }
        return super.getExpoData();
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<com.jingdong.app.mall.home.r.c.b> getExpoJson() {
        return this.mJsonExposData;
    }

    public Product getItemProduct(int i2) {
        if (i2 < this.mProducts.size()) {
            return this.mProducts.get(i2);
        }
        return null;
    }

    public int getMiaoshaAdvance() {
        return this.mAdvance;
    }

    public String getNameText() {
        return this.mNameText;
    }

    public JDJSONObject getNextRoundObject() {
        return this.mNextRoundMap.get(this.mNextRoundKey);
    }

    public JumpEntity getOperateJump() {
        return this.mOperateJump;
    }

    public String getOperateWord() {
        return this.mOperateWord;
    }

    public String getPanicExpoSourceValue() {
        return this.mPanicExpoSourceValue;
    }

    public int getPlayCount() {
        return this.mPlayCount;
    }

    public String getPriceColor() {
        return this.priceColor;
    }

    public SpannableString getPriceString(String str) {
        SpannableString spannableString = new SpannableString(a.e(str));
        spannableString.setSpan(new AbsoluteSizeSpan(d.d(36)), 0, 1, 18);
        return spannableString;
    }

    public JSONArray getProductExpoJsonArr() {
        return this.mProductJsonArr;
    }

    public CopyOnWriteArrayList<Product> getProducts() {
        return this.mProducts;
    }

    public void initData(h hVar, f fVar) {
        this.mModel = hVar;
        this.mElement = fVar;
        this.mExposData.add(fVar.j());
        if (!TextUtils.isEmpty(this.mElement.l())) {
            this.mJsonExposData.add(com.jingdong.app.mall.home.r.c.b.c(this.mElement.l()));
        }
        this.priceColor = fVar.getJsonString(CartPromotion.KEY_PRICECOLOR, "#FFEB1400");
        this.isCacheData = hVar.Z || hVar.a0;
    }

    public boolean isCacheData() {
        return this.isCacheData;
    }

    public boolean isShowOperate() {
        return false;
    }

    public void onDataChange() {
        LadySecKillDataChangeCallBack ladySecKillDataChangeCallBack = this.mChangeCallBack;
        if (ladySecKillDataChangeCallBack != null) {
            ladySecKillDataChangeCallBack.onChange();
        }
    }

    public void onItemClick(int i2, Context context) {
        Product itemProduct;
        String str;
        String str2 = "";
        if (i2 >= this.mProducts.size() || (itemProduct = getItemProduct(i2)) == null || itemProduct.jump == null) {
            return;
        }
        try {
            str = this.mProductJsonArr.get(i2).toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            str = "";
        }
        l.c(itemProduct.jump, itemProduct.getImageUrl());
        l.e(context, itemProduct.jump);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(l.a)) {
            str2 = l.a + CartConstant.KEY_YB_INFO_LINK;
        }
        sb.append(str2);
        sb.append(i2);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(itemProduct.getId());
        com.jingdong.app.mall.home.r.c.a.u("Home_HandSeckill", "", str, RecommendMtaUtils.Home_PageId, null, sb.toString());
    }

    public void parseExpo() {
        this.mProductJsonArr = com.jingdong.app.mall.home.r.c.b.d();
        for (int i2 = 0; i2 < 3; i2++) {
            Product itemProduct = getItemProduct(i2);
            if (itemProduct != null) {
                com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
                bVar.e(com.jingdong.app.mall.home.r.c.b.c(this.mElement.l()));
                JumpEntity jumpEntity = itemProduct.jump;
                if (jumpEntity != null) {
                    bVar.e(com.jingdong.app.mall.home.r.c.b.c(jumpEntity.srv));
                }
                bVar.a(DeeplinkProductDetailHelper.LAYER_STYLE, "0000");
                bVar.a(PdMtaUtil.PARAM_KEY_SKUID, Long.valueOf(itemProduct.getId() == null ? 0L : itemProduct.getId().longValue()));
                this.mProductJsonArr.put(bVar);
            }
        }
    }

    public void setDataChangeCallBack(LadySecKillDataChangeCallBack ladySecKillDataChangeCallBack) {
        this.mChangeCallBack = ladySecKillDataChangeCallBack;
    }

    public void setMiaoshaAdvance(int i2) {
        if (i2 < 0) {
            this.mAdvance = 0;
        } else {
            this.mAdvance = i2;
        }
    }

    public void setNameText(String str) {
        String g2 = m.g(str);
        if (g2.length() < 2) {
            this.mNameText = "00";
            return;
        }
        String substring = g2.substring(0, g2.length() - 2);
        if (substring.length() == 1) {
            substring = "0" + substring;
        }
        this.mNameText = substring;
    }

    public void setNextRoundKey(String str) {
        this.mNextRoundKey = str;
    }

    public void setNextRoundObject(JDJSONObject jDJSONObject) {
        this.mNextRoundMap.put(this.mNextRoundKey, jDJSONObject);
    }

    public void setOperateJump(JumpEntity jumpEntity) {
        this.mOperateJump = jumpEntity;
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

    public void setProducts(ArrayList<Product> arrayList) {
        this.mProducts.clear();
        if (arrayList != null) {
            this.mProducts.addAll(arrayList);
        }
    }

    public void setShowOperate(boolean z) {
        this.isShowOperate = z;
    }

    public void setTimeInfo(boolean z, Long l2, long j2) {
        this.mBuyTimeViewData.e(z, l2.longValue(), j2, this.mModel.U);
    }
}
