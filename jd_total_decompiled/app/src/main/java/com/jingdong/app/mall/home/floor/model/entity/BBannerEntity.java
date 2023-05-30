package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.g;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class BBannerEntity extends FloorEntity {
    private int floorType;
    private boolean isCacheData;
    private List<Product> msProducts = null;
    private JDJSONArray items = null;
    private f mElement = null;
    private int cornerRadius = 0;
    private JSONArray mProductJsonArr = b.d();

    private void genCornerRadius(h hVar) {
        int i2;
        int i3 = 0;
        this.cornerRadius = 0;
        String str = hVar.u;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String[] split = TextUtils.split(str.trim(), DYConstants.DY_REGEX_COMMA);
            int length = split.length;
            i2 = 0;
            while (i3 < length) {
                try {
                    i2 = Math.max(i2, Integer.parseInt(split[i3]));
                    i3++;
                } catch (Exception unused) {
                    i3 = i2;
                    i2 = i3;
                    this.cornerRadius = i2;
                }
            }
        } catch (Exception unused2) {
        }
        this.cornerRadius = i2;
    }

    private void parseMSExpo() {
        this.mProductJsonArr = b.d();
        List<Product> list = this.msProducts;
        if (list == null) {
            return;
        }
        int min = Math.min(4, list.size());
        for (int i2 = 0; i2 < min; i2++) {
            Product product = this.msProducts.get(i2);
            if (product != null) {
                b bVar = new b();
                bVar.e(b.c(this.mElement.l()));
                JumpEntity jumpEntity = product.jump;
                if (jumpEntity != null) {
                    bVar.e(b.c(jumpEntity.srv));
                }
                bVar.a(DeeplinkProductDetailHelper.LAYER_STYLE, "0000");
                bVar.a(PdMtaUtil.PARAM_KEY_SKUID, Long.valueOf(product.getId() == null ? 0L : product.getId().longValue()));
                bVar.a("guideanimation", 0);
                this.mProductJsonArr.put(bVar);
            }
        }
    }

    public String getAllColor() {
        return this.mElement.getJsonString("allowancePriceColor", "");
    }

    public String getAllTagImg() {
        return this.mElement.getJsonString("allowanceTagBgImg", "");
    }

    public String getAtmImg() {
        return this.mElement.getJsonString("atmoImg", "");
    }

    public String getBgImg() {
        return this.mElement.d();
    }

    public JDJSONArray getCommonItems() {
        return this.items;
    }

    public int getCornerRadius() {
        return this.cornerRadius;
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

    public List<Product> getMsProducts() {
        return this.msProducts;
    }

    public String getPriceColor() {
        return this.mElement.K();
    }

    public JSONArray getProductExpoJsonArr() {
        return this.mProductJsonArr;
    }

    public String getShowName() {
        return this.mElement.O();
    }

    public String getSkuTagImg() {
        return this.mElement.getJsonString("skuTagImg", "");
    }

    public void initData(h hVar, f fVar) {
        this.msProducts = null;
        this.items = null;
        this.mElement = fVar;
        this.isCacheData = hVar.Z;
        this.mExposData.clear();
        this.mExposData.add(this.mElement.j());
        this.mJsonExposData.clear();
        if (!TextUtils.isEmpty(this.mElement.l())) {
            this.mJsonExposData.add(b.c(this.mElement.l()));
        }
        if (this.floorType == 1) {
            JDJSONObject h2 = fVar.h();
            if (h2 != null) {
                ArrayList<Product> list = Product.toList(h2.getJSONArray("indexMiaoSha"), 17);
                Iterator<Product> it = list.iterator();
                while (it.hasNext()) {
                    int i2 = it.next().msItemType;
                    if (i2 != 1 && i2 != 8) {
                        it.remove();
                    }
                }
                this.msProducts = list;
                parseMSExpo();
                String jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(h2, "algorithmFrom", "");
                if (!this.isCacheData && !TextUtils.isEmpty(jSONStringWithDefault)) {
                    a.y("Home_SeckillFloorExpo", jSONStringWithDefault, "");
                }
            }
        } else {
            this.items = fVar.getJsonArr(CartConstant.KEY_ITEMS);
        }
        genCornerRadius(hVar);
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        if (this.mElement == null) {
            return false;
        }
        if (this.floorType == 1) {
            List<Product> list = this.msProducts;
            return list != null && list.size() >= 4;
        }
        JDJSONArray jDJSONArray = this.items;
        return jDJSONArray != null && jDJSONArray.size() >= 4;
    }

    public void setFloorType(int i2) {
        this.floorType = i2;
    }

    public static boolean isValid(int i2, g gVar) {
        ArrayList<f> arrayList;
        f fVar;
        JDJSONArray jSONArray;
        if (gVar == null || (arrayList = gVar.f10682c) == null || arrayList.size() <= 0 || (fVar = arrayList.get(0)) == null) {
            return false;
        }
        if (i2 == 1) {
            JDJSONObject h2 = fVar.h();
            return (h2 == null || (jSONArray = h2.getJSONArray("indexMiaoSha")) == null || jSONArray.size() < 4) ? false : true;
        }
        JDJSONArray jsonArr = fVar.getJsonArr(CartConstant.KEY_ITEMS);
        return jsonArr != null && jsonArr.size() >= 4;
    }
}
