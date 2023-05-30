package com.jd.lib.productdetail.mainimage.old;

import androidx.lifecycle.Observer;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductExtInfo;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductListInfo;
import com.jd.lib.productdetail.core.entitys.PreferentialRecommendTabItemEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PreferentialRecommendItemEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes15.dex */
public class s implements Observer<PdBaseProtocolLiveData.Result<Object>> {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ String f5199g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f5200h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ boolean f5201i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ PdBaseProtocolLiveData f5202j;

    /* renamed from: k  reason: collision with root package name */
    public final /* synthetic */ g0 f5203k;

    public s(g0 g0Var, String str, String str2, boolean z, PdBaseProtocolLiveData pdBaseProtocolLiveData) {
        this.f5203k = g0Var;
        this.f5199g = str;
        this.f5200h = str2;
        this.f5201i = z;
        this.f5202j = pdBaseProtocolLiveData;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<Object> result) {
        Iterator<String> it;
        PdBaseProtocolLiveData.Result<Object> result2 = result;
        PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo = new PdPreferentialRecommendProductListInfo();
        pdPreferentialRecommendProductListInfo.tabItemEntities = new ArrayList();
        pdPreferentialRecommendProductListInfo.itemEntitiesMap = new LinkedHashMap();
        pdPreferentialRecommendProductListInfo.from = this.f5199g;
        pdPreferentialRecommendProductListInfo.currentTabId = this.f5200h;
        if (result2 != null) {
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                Object obj = result2.mData;
                if (obj instanceof JDJSONObject) {
                    JDJSONObject jDJSONObject = (JDJSONObject) obj;
                    if (this.f5201i) {
                        jDJSONObject = jDJSONObject.getJSONObject("result");
                    }
                    if (jDJSONObject != null && (jDJSONObject.get("data") instanceof JDJSONObject)) {
                        JDJSONObject jDJSONObject2 = (JDJSONObject) jDJSONObject.get("data");
                        JDJSONObject jDJSONObject3 = (JDJSONObject) jDJSONObject2.get("cateGoodsInfo");
                        JDJSONObject jDJSONObject4 = (JDJSONObject) jDJSONObject2.get("extInfo");
                        JDJSONArray jSONArray = jDJSONObject3.getJSONArray("cateName");
                        JDJSONObject jSONObject = jDJSONObject3.getJSONObject("productInfo");
                        if (jSONArray != null && jSONArray.size() > 0) {
                            for (int i2 = 0; i2 < jSONArray.size(); i2++) {
                                JDJSONObject jDJSONObject5 = (JDJSONObject) jSONArray.get(i2);
                                if (jDJSONObject5 != null && jDJSONObject5.keySet() != null && (it = jDJSONObject5.keySet().iterator()) != null && it.hasNext()) {
                                    String next = it.next();
                                    PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity = new PreferentialRecommendTabItemEntity();
                                    preferentialRecommendTabItemEntity.tabId = next;
                                    preferentialRecommendTabItemEntity.title = jDJSONObject5.optString(next);
                                    pdPreferentialRecommendProductListInfo.tabItemEntities.add(preferentialRecommendTabItemEntity);
                                }
                            }
                        }
                        if (jSONObject != null && jSONObject.keySet() != null) {
                            Iterator<String> it2 = jSONObject.keySet().iterator();
                            while (it2 != null && it2.hasNext()) {
                                ArrayList arrayList = new ArrayList();
                                String next2 = it2.next();
                                JDJSONArray parseArray = JDJSON.parseArray(jSONObject.optString(next2));
                                if (parseArray != null && parseArray.size() > 0) {
                                    for (int i3 = 0; i3 < parseArray.size(); i3++) {
                                        PreferentialRecommendItemEntity preferentialRecommendItemEntity = (PreferentialRecommendItemEntity) JDJSON.parseObject(((JDJSONObject) parseArray.get(i3)).toJSONString(), PreferentialRecommendItemEntity.class);
                                        preferentialRecommendItemEntity.LocaShowType = this.f5201i ? 1 : 0;
                                        preferentialRecommendItemEntity.tabId = next2;
                                        arrayList.add(preferentialRecommendItemEntity);
                                    }
                                }
                                pdPreferentialRecommendProductListInfo.itemEntitiesMap.put(next2, arrayList);
                            }
                        }
                        if (jDJSONObject4 != null) {
                            pdPreferentialRecommendProductListInfo.extInfo = (PdPreferentialRecommendProductExtInfo) JDJSON.parseObject(jDJSONObject4.toJSONString(), PdPreferentialRecommendProductExtInfo.class);
                        }
                    }
                }
                this.f5203k.a.postValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.SUCCESS, pdPreferentialRecommendProductListInfo, ""));
                this.f5202j.mResult.removeObserver(this);
                return;
            }
            PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.FAIL;
            if (dataStatus == dataStatus2) {
                this.f5203k.a.postValue(new PdBaseProtocolLiveData.Result<>(dataStatus2, pdPreferentialRecommendProductListInfo, ""));
                this.f5202j.mResult.removeObserver(this);
                return;
            }
            return;
        }
        this.f5203k.a.postValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, pdPreferentialRecommendProductListInfo, ""));
        this.f5202j.mResult.removeObserver(this);
    }
}
