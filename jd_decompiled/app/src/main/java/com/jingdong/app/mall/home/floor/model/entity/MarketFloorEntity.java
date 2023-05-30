package com.jingdong.app.mall.home.floor.model.entity;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.a;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class MarketFloorEntity extends FloorEntity {
    private f mData;
    private List<MarketSkuItem> mSkuList = new ArrayList();

    /* loaded from: classes4.dex */
    public static class MarketSkuItem extends a {
        public String img;
        public String jdPrice;
        public String skuid;

        public MarketSkuItem(JDJSONObject jDJSONObject) {
            super(jDJSONObject);
            this.img = getJsonString("img");
            this.jdPrice = getJsonString(JshopConst.JSKEY_PRODUCT_JDPRICE);
            this.skuid = getJsonString(PdMtaUtil.PARAM_KEY_SKUID);
        }
    }

    private String getAllSkuId() {
        if (this.mSkuList == null) {
            return "";
        }
        String str = "";
        for (int i2 = 0; i2 < this.mSkuList.size(); i2++) {
            MarketSkuItem marketSkuItem = this.mSkuList.get(i2);
            if (i2 == 0) {
                str = marketSkuItem == null ? "" : marketSkuItem.skuid;
            } else {
                str = str.concat(CartConstant.KEY_YB_INFO_LINK).concat(marketSkuItem == null ? "" : marketSkuItem.skuid);
            }
        }
        return str;
    }

    private String getSkuId(int i2) {
        MarketSkuItem marketSkuItem;
        List<MarketSkuItem> list = this.mSkuList;
        return (list == null || i2 < 0 || list.size() <= i2 || (marketSkuItem = this.mSkuList.get(i2)) == null) ? "" : marketSkuItem.skuid;
    }

    public static boolean isValid(d dVar) {
        JDJSONArray V;
        ArrayList<f> arrayList = dVar.f10682c;
        return (arrayList == null || arrayList.size() <= 0 || arrayList.get(0) == null || (V = arrayList.get(0).V()) == null || V.size() <= 0) ? false : true;
    }

    public f getData() {
        return this.mData;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        if (isValid()) {
            return this.mExposData;
        }
        return super.getExpoData();
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        return this.mJsonExposData;
    }

    public List<MarketSkuItem> getSkuList() {
        return this.mSkuList;
    }

    public void initData(f fVar) {
        this.mData = fVar;
        this.mExposData.add(fVar.j());
        if (TextUtils.isEmpty(this.mData.l())) {
            return;
        }
        this.mJsonExposData.add(b.c(this.mData.l()));
    }

    public void onSkuClick(Context context, int i2) {
        JumpEntity jump;
        if (this.mData == null || l.k() || (jump = this.mData.getJump()) == null) {
            return;
        }
        b c2 = b.c(jump.srvJson);
        String allSkuId = i2 == 0 ? getAllSkuId() : getSkuId(i2 - 1);
        c2.a(PdMtaUtil.PARAM_KEY_SKUID, allSkuId);
        c2.a("skuposition", i2 + "");
        l.onClickJsonEvent(context, jump, allSkuId, jump.getSrv(), c2.toString(), i2);
    }

    public void parseSku(JDJSONObject jDJSONObject) {
        this.mSkuList.add(new MarketSkuItem(jDJSONObject));
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        return this.mSkuList.size() > 0 && this.mSkuList.size() <= 3;
    }
}
