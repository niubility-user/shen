package com.jd.lib.productdetail.tradein;

import android.text.TextUtils;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.google.gson.JsonObject;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.d.g;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateData;
import com.jd.lib.productdetail.tradein.estimate.TradeInSaveData;
import com.jd.lib.productdetail.tradein.f.f;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.result.TradeInSaveIdData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes16.dex */
public class TradeInViewModel extends ViewModel {
    public List<String> a;
    public BaseActivity b;

    /* renamed from: c */
    public ProductDetailEntity f5252c;
    public TradeInDialogFragment d;

    /* renamed from: e */
    public String f5253e;

    /* renamed from: g */
    public int f5255g;

    /* renamed from: h */
    public int f5256h;

    /* renamed from: i */
    public int f5257i;

    /* renamed from: j */
    public int f5258j;

    /* renamed from: k */
    public TradeInPriceMode f5259k;

    /* renamed from: n */
    public String f5262n;
    public String o;
    public int p;
    public JDLocation q;
    public TradeInAddressInfo r;
    public JDJSONObject s;
    public String t;

    /* renamed from: f */
    public TradeInOpenLayerFrom f5254f = TradeInOpenLayerFrom.FORM_OTHER;

    /* renamed from: l */
    public boolean f5260l = false;

    /* renamed from: m */
    public TradeInButtonType f5261m = TradeInButtonType.DEFAULT;
    public MutableLiveData<PdBaseProtocolLiveData.Result<TradeInResultData>> u = new MutableLiveData<>();
    public Map<String, Object> v = new HashMap();

    /* loaded from: classes16.dex */
    public class a implements Function<PdBaseProtocolLiveData.Result<TradeInSaveIdData>, PdBaseProtocolLiveData.Result<TradeInSaveIdData.Data>> {
        public final /* synthetic */ TradeInSaveIdData.Data.MtaData a;

        public a(TradeInViewModel tradeInViewModel, TradeInSaveIdData.Data.MtaData mtaData) {
            this.a = mtaData;
        }

        @Override // androidx.arch.core.util.Function
        public PdBaseProtocolLiveData.Result<TradeInSaveIdData.Data> apply(PdBaseProtocolLiveData.Result<TradeInSaveIdData> result) {
            PdBaseProtocolLiveData.Result<TradeInSaveIdData> result2 = result;
            PdBaseProtocolLiveData.Result<TradeInSaveIdData.Data> result3 = new PdBaseProtocolLiveData.Result<>(result2.mStatus, null, result2.mResponseString);
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
            if (dataStatus == dataStatus2) {
                TradeInSaveIdData tradeInSaveIdData = result2.mData;
                if (tradeInSaveIdData != null && tradeInSaveIdData.isValid()) {
                    TradeInSaveIdData.Data data = result2.mData.data;
                    data.mMtaData = this.a;
                    return new PdBaseProtocolLiveData.Result<>(dataStatus2, data, "");
                }
                PdBaseProtocolLiveData.Result.DataStatus dataStatus3 = PdBaseProtocolLiveData.Result.DataStatus.FAIL;
                TradeInSaveIdData tradeInSaveIdData2 = result2.mData;
                return new PdBaseProtocolLiveData.Result<>(dataStatus3, tradeInSaveIdData2 != null ? tradeInSaveIdData2.data : null, "");
            }
            return result3;
        }
    }

    public LiveData<PdBaseProtocolLiveData.Result<TradeInSaveIdData.Data>> a(int i2, boolean z) {
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo;
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo2;
        TradeInSaveIdData.Data.MtaData mtaData = new TradeInSaveIdData.Data.MtaData();
        HashMap hashMap = new HashMap();
        hashMap.put("settleType", Integer.valueOf(this.f5258j));
        hashMap.put("tradeType", Integer.valueOf(this.f5256h));
        hashMap.put("bizCode", Integer.valueOf(this.f5255g));
        if (z) {
            hashMap.put("recycleAgain", "1");
        }
        JDJSONObject jDJSONObject = this.s;
        if (jDJSONObject != null) {
            hashMap.put("extension", jDJSONObject);
        }
        TradeInAddressInfo c2 = c();
        if (c2 != null) {
            hashMap.put("addressInfo", c2);
        }
        hashMap.put("extend", this.t);
        hashMap.put("skuId", this.f5253e);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("cashierType", "1");
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo3 = null;
        if (this.u.getValue() != null && this.u.getValue().mData != null) {
            if (this.u.getValue().mData.oldWare1 != null) {
                tradeInWareCardInfo2 = this.u.getValue().mData.oldWare1;
            } else {
                tradeInWareCardInfo2 = this.u.getValue().mData.oldWare2 != null ? this.u.getValue().mData.oldWare2 : null;
            }
            if (tradeInWareCardInfo2 != null && tradeInWareCardInfo2.getCurrentBank() != null) {
                hashMap2.put("bankCardId", tradeInWareCardInfo2.getCurrentBank().bankCardId);
                hashMap2.put("bankCardName", tradeInWareCardInfo2.getCurrentBank().bankCardName);
                hashMap2.put("cardNoEnd", tradeInWareCardInfo2.getCurrentBank().cardNoEnd);
            }
        }
        hashMap.put("payInfo", hashMap2);
        hashMap.put(BaseEvent.SCENE, Integer.valueOf(i2));
        if (this.u.getValue() != null && this.u.getValue().mData != null) {
            hashMap.put("jdDaoJiaStoreId", Long.valueOf(this.u.getValue().mData.storeId));
            if (this.u.getValue().mData.oldWare1 != null && this.u.getValue().mData.oldWare1.cardType == 4) {
                tradeInWareCardInfo = this.u.getValue().mData.oldWare1;
            } else {
                tradeInWareCardInfo = (this.u.getValue().mData.oldWare2 == null || this.u.getValue().mData.oldWare2.cardType != 4) ? null : this.u.getValue().mData.oldWare2;
            }
            if (tradeInWareCardInfo != null && tradeInWareCardInfo.getCurrentTranMode() != null) {
                HashMap hashMap3 = new HashMap();
                mtaData.PaiPaiTrans_Ways = tradeInWareCardInfo.getCurrentTranMode().tradeModeName;
                hashMap3.put("tradeModeId", Integer.valueOf(tradeInWareCardInfo.getCurrentTranMode().tradeModeId));
                if (tradeInWareCardInfo.getCurrentTranMode().tradeModeId == 3) {
                    hashMap3.put("recycleDate", tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate != null ? tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate.date : "");
                } else if (tradeInWareCardInfo.getCurrentTranMode().tradeModeId == 4) {
                    hashMap3.put("recycleDate", tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate != null ? tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate.date : "");
                    hashMap3.put("startTime", tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate != null ? tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate.startTime : "");
                    hashMap3.put("endTime", tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate != null ? tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate.endTime : "");
                    hashMap3.put("dayOfWeek", tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate != null ? tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate.dayOfWeek : "");
                } else if (tradeInWareCardInfo.getCurrentTranMode().tradeModeId == 5) {
                    hashMap3.put("storeId", tradeInWareCardInfo.getCurrentTranMode().storeInfo != null ? Integer.valueOf(tradeInWareCardInfo.getCurrentTranMode().storeInfo.storeId) : "");
                    hashMap3.put("storeName", tradeInWareCardInfo.getCurrentTranMode().storeInfo != null ? tradeInWareCardInfo.getCurrentTranMode().storeInfo.storeName : "");
                }
                hashMap.put("tradeModeForClap", hashMap3);
            }
            if (this.u.getValue().mData.oldWare1 != null && this.u.getValue().mData.oldWare1.cardType == 3) {
                tradeInWareCardInfo3 = this.u.getValue().mData.oldWare1;
            } else if (this.u.getValue().mData.oldWare2 != null && this.u.getValue().mData.oldWare2.cardType == 3) {
                tradeInWareCardInfo3 = this.u.getValue().mData.oldWare2;
            }
            if (tradeInWareCardInfo3 != null && tradeInWareCardInfo3.getCurrentTranMode() != null) {
                mtaData.Ttransaction_Ways = tradeInWareCardInfo3.getCurrentTranMode().tradeModeName;
            }
        }
        if (this.u.getValue() != null && this.u.getValue().mData != null) {
            ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
            if (this.u.getValue().mData.oldWare1 != null) {
                f(arrayList, this.u.getValue().mData.oldWare1, mtaData);
            }
            if (this.u.getValue().mData.oldWare2 != null) {
                f(arrayList, this.u.getValue().mData.oldWare2, mtaData);
            }
            hashMap.put("oldProductInfoList", arrayList);
            if (this.u.getValue().mData.subsidyInfos != null) {
                ArrayList arrayList2 = new ArrayList();
                Iterator<TradeInResultData.SubsidyInfos> it = this.u.getValue().mData.subsidyInfos.iterator();
                while (it.hasNext()) {
                    TradeInResultData.SubsidyInfos next = it.next();
                    HashMap hashMap4 = new HashMap();
                    hashMap4.put("subsidyType", "");
                    hashMap4.put("subsidyPrice", Integer.valueOf(next.subsidyPrice));
                    hashMap4.put("sendType", Integer.valueOf(next.sendType));
                    hashMap4.put("jdBear", Integer.valueOf(next.jdBear));
                    hashMap4.put("venderBear", Integer.valueOf(next.venderBear));
                    hashMap4.put("subsidyDesc", next.subsidyDesc);
                    arrayList2.add(hashMap4);
                }
                hashMap.put("subsidyInfoListForClap", arrayList2);
            }
        }
        f fVar = new f(hashMap);
        fVar.request(this.b);
        return Transformations.map(fVar.mResult, new a(this, mtaData));
    }

    public MutableLiveData<PdBaseProtocolLiveData.Result<TradeInSaveData>> b(TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem, TradeInSelectDeviceData.Data.CategoriesInfo.CateItem cateItem, TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries, LinkedHashMap<String, TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> linkedHashMap, int i2) {
        TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap;
        String str;
        Set<String> keySet;
        Iterator<String> it;
        String str2;
        TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList;
        LinkedHashMap<String, TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> linkedHashMap2 = linkedHashMap;
        if (oldProductInquiries == null || tagItem == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("bizCode", Integer.valueOf(this.f5255g));
        hashMap.put("skuId", this.f5253e);
        hashMap.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
        hashMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        hashMap.put("extend", this.t);
        hashMap.put("settleType", Integer.valueOf(this.f5258j));
        hashMap.put("tradeType", Integer.valueOf(this.f5256h));
        if (!TextUtils.isEmpty(this.o)) {
            hashMap.put("qualificationId", this.o);
        }
        hashMap.put("queryDetailedType", Integer.valueOf(this.p));
        ArrayList arrayList = new ArrayList();
        String str3 = "attrName";
        if (tagItem.tagType == 1) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("attrId", oldProductInquiries.attrId);
            hashMap2.put("attrName", oldProductInquiries.attrName);
            hashMap2.put("attrValId", oldProductInquiries.oldProductId);
            hashMap2.put(VerifyTracker.KEY_VALUE_NAME, oldProductInquiries.oldProductName);
            arrayList.add(hashMap2);
        }
        if (oldProductInquiries.leaf || linkedHashMap2 == null || linkedHashMap.size() <= 0 || (keySet = linkedHashMap.keySet()) == null || keySet.size() <= 0) {
            inquiryItemPropertiesMap = null;
            str = null;
        } else {
            Iterator<String> it2 = keySet.iterator();
            TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap2 = null;
            String str4 = null;
            while (it2.hasNext()) {
                TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap3 = linkedHashMap2.get(it2.next());
                if (inquiryItemPropertiesMap3 == null || (inquiryItemBasePropValList = inquiryItemPropertiesMap3.mCurrentVal) == null) {
                    it = it2;
                    str2 = str3;
                } else {
                    if (inquiryItemPropertiesMap2 == null) {
                        inquiryItemPropertiesMap2 = inquiryItemPropertiesMap3;
                    }
                    if (!TextUtils.isEmpty(inquiryItemBasePropValList.price)) {
                        str4 = inquiryItemPropertiesMap3.mCurrentVal.price;
                    }
                    HashMap hashMap3 = new HashMap();
                    it = it2;
                    hashMap3.put("attrId", inquiryItemPropertiesMap3.attrId);
                    hashMap3.put(str3, inquiryItemPropertiesMap3.attrName);
                    StringBuilder sb = new StringBuilder();
                    str2 = str3;
                    sb.append(inquiryItemPropertiesMap3.attrType);
                    sb.append("");
                    hashMap3.put("attrType", sb.toString());
                    TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList2 = inquiryItemPropertiesMap3.mCurrentVal;
                    hashMap3.put("attrValId", inquiryItemBasePropValList2 != null ? inquiryItemBasePropValList2.attrValId : "");
                    TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList3 = inquiryItemPropertiesMap3.mCurrentVal;
                    hashMap3.put(VerifyTracker.KEY_VALUE_NAME, inquiryItemBasePropValList3 != null ? inquiryItemBasePropValList3.valueName : "");
                    arrayList.add(hashMap3);
                }
                linkedHashMap2 = linkedHashMap;
                it2 = it;
                str3 = str2;
            }
            inquiryItemPropertiesMap = inquiryItemPropertiesMap2;
            str = str4;
        }
        hashMap.put("choosedInquiryItems", arrayList);
        int i3 = tagItem.tagType;
        if (i3 == 1) {
            if (TextUtils.isEmpty(str)) {
                str = oldProductInquiries.price;
            }
            HashMap hashMap4 = new HashMap();
            hashMap4.put("tagId", tagItem.tagId);
            hashMap4.put("tagType", tagItem.tagType + "");
            hashMap.put("tagInfo", hashMap4);
            HashMap hashMap5 = new HashMap();
            hashMap5.put("oldProductId", oldProductInquiries.oldProductId);
            hashMap5.put("legacyMode", Integer.valueOf(oldProductInquiries.legacyMode));
            hashMap5.put("aclAmount", str);
            hashMap5.put("firstOldItemInfo", oldProductInquiries.inquiryName);
            if (cateItem != null) {
                hashMap5.put("jdOldCatInfo", cateItem.jdCat);
            }
            if (oldProductInquiries.leaf) {
                hashMap5.put("ruleId", oldProductInquiries.inquiryId);
                hashMap5.put("ruleName", oldProductInquiries.inquiryName);
            } else if (inquiryItemPropertiesMap != null) {
                hashMap5.put("ruleId", inquiryItemPropertiesMap.attrId);
                hashMap5.put("ruleName", inquiryItemPropertiesMap.attrName);
            }
            hashMap.put("inquiryItemForHouseElectricInfo", hashMap5);
        } else if (i3 == 2) {
            HashMap hashMap6 = new HashMap();
            hashMap6.put("oldProductId", oldProductInquiries.oldProductId);
            hashMap6.put("venderId", oldProductInquiries.venderId);
            hashMap6.put("inquiryType", Integer.valueOf(i2));
            if (i2 == 2 || i2 == 3) {
                hashMap6.put("inquiryId", oldProductInquiries.inquiryId);
            }
            hashMap6.put("tradeType", Integer.valueOf(this.f5256h));
            hashMap.put("inquiryItemForClapInfo", hashMap6);
        }
        JDJSONObject jDJSONObject = this.s;
        if (jDJSONObject != null) {
            hashMap.put("extension", jDJSONObject);
        }
        TradeInAddressInfo c2 = c();
        if (c2 != null) {
            hashMap.put("addressInfo", c2);
        }
        g gVar = new g(hashMap);
        gVar.request(this.b);
        return gVar.mResult;
    }

    public TradeInAddressInfo c() {
        if (this.r == null) {
            this.r = new TradeInAddressInfo();
            if (this.q == null) {
                this.q = JDLocationCache.getInstance().getLocation(PDUtils.getLocationCacheOption());
            }
            this.r.latitude = String.valueOf(this.q.getLat());
            this.r.longitude = String.valueOf(this.q.getLng());
            AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
            if (addressGlobal != null) {
                if (addressGlobal.isUserAddress()) {
                    this.r.longitude = addressGlobal.getLongitude();
                    this.r.latitude = addressGlobal.getLatitude();
                }
                this.r.addressId = addressGlobal.getId() + "";
            }
        }
        return this.r;
    }

    public final Map<String, Object> d(TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo) {
        TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight;
        TradeInResultData.BarterText barterText;
        if (tradeInWareCardInfo != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("cardType", Integer.valueOf(tradeInWareCardInfo.cardType));
            TradeInResultData.TradeInFloorData tradeInFloorData = tradeInWareCardInfo.exchangeWareWay;
            if (tradeInFloorData != null && (barterFloorRight = tradeInFloorData.rightInfo) != null && (barterText = barterFloorRight.text1) != null) {
                hashMap.put("replacementMode", Integer.valueOf(barterText.replacementMode));
                if (tradeInWareCardInfo.cardType == 3) {
                    hashMap.put("recycleDemand", Integer.valueOf(tradeInWareCardInfo.exchangeWareWay.rightInfo.text1.recycleDemand));
                    hashMap.put("subReplacementMode", Integer.valueOf(tradeInWareCardInfo.exchangeWareWay.rightInfo.text1.subReplacementMode));
                }
            }
            if (tradeInWareCardInfo.getCurrentTranMode() != null) {
                hashMap.put("tradeModeId", Integer.valueOf(tradeInWareCardInfo.getCurrentTranMode().tradeModeId));
                if (tradeInWareCardInfo.getCurrentTranMode().tradeModeId == 3) {
                    if (tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate != null) {
                        hashMap.put("promiseDateDisplayText", tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate.displayText);
                    }
                } else if (tradeInWareCardInfo.getCurrentTranMode().tradeModeId == 4) {
                    if (tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate != null) {
                        hashMap.put("promiseDateDisplayText", tradeInWareCardInfo.getCurrentTranMode().defaultPromiseDate.displayText);
                    }
                } else if (tradeInWareCardInfo.getCurrentTranMode().tradeModeId == 5) {
                    hashMap.put("storeId", tradeInWareCardInfo.getCurrentTranMode().storeInfo != null ? Integer.valueOf(tradeInWareCardInfo.getCurrentTranMode().storeInfo.storeId) : "");
                    hashMap.put("promiseDateDisplayText", tradeInWareCardInfo.getCurrentTranMode().storeInfo != null ? tradeInWareCardInfo.getCurrentTranMode().storeInfo.storeName : "");
                }
            }
            if (tradeInWareCardInfo.getCurrentBank() != null) {
                hashMap.put("bankCardId", tradeInWareCardInfo.getCurrentBank().bankCardId);
                return hashMap;
            }
            return hashMap;
        }
        return null;
    }

    public void e(String str, JsonObject jsonObject) {
        JsonObject jsonObject2;
        String str2;
        String str3 = this.f5253e;
        if (jsonObject == null) {
            try {
                jsonObject2 = new JsonObject();
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
                str2 = "";
            }
        } else {
            jsonObject2 = jsonObject;
        }
        jsonObject2.addProperty("YJHX_source_param", this.f5261m == TradeInButtonType.DOUBLE ? "1" : "2");
        TradeInOpenLayerFrom tradeInOpenLayerFrom = this.f5254f;
        if (tradeInOpenLayerFrom != null) {
            jsonObject2.addProperty("toast_entrance", tradeInOpenLayerFrom.from);
        }
        int i2 = this.f5255g;
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            jsonObject2.addProperty("toast_biz", "3");
        } else if (i2 == 4) {
            int i3 = this.f5256h;
            if (i3 == 10) {
                jsonObject2.addProperty("toast_biz", "2");
            } else if (i3 == 11) {
                jsonObject2.addProperty("toast_biz", "1");
            }
        }
        str2 = jsonObject2.toString();
        JDMtaUtils.sendClickDataWithExt(this.b, str, null, "onClick", RecommendMtaUtils.Productdetail_MainPage, "com.jd.lib.productdetail.ProductDetailActivity", str3, null, str2, null, null, this.f5253e, null, null);
    }

    public final void f(ArrayList<Map<String, Object>> arrayList, TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo, TradeInSaveIdData.Data.MtaData mtaData) {
        TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight;
        TradeInResultData.BarterText barterText;
        TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight2;
        TradeInResultData.BarterText barterText2;
        TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight3;
        TradeInResultData.BarterText barterText3;
        if (tradeInWareCardInfo == null || !tradeInWareCardInfo.isValid() || tradeInWareCardInfo.wareList == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        Iterator<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> it = tradeInWareCardInfo.wareList.iterator();
        while (it.hasNext()) {
            TradeInResultData.TradeInWareCardInfo.TradeInWareInfo next = it.next();
            int i2 = tradeInWareCardInfo.cardType;
            HashMap hashMap = new HashMap();
            hashMap.put("cardType", Integer.valueOf(i2));
            TradeInResultData.TradeInFloorData tradeInFloorData = tradeInWareCardInfo.exchangeWareWay;
            if (tradeInFloorData != null && (barterFloorRight3 = tradeInFloorData.rightInfo) != null && (barterText3 = barterFloorRight3.text1) != null) {
                hashMap.put("modelType", Integer.valueOf(barterText3.replacementMode));
            }
            if (i2 == 3) {
                TradeInResultData.TradeInFloorData tradeInFloorData2 = tradeInWareCardInfo.exchangeWareWay;
                if (tradeInFloorData2 != null && (barterFloorRight2 = tradeInFloorData2.rightInfo) != null && (barterText2 = barterFloorRight2.text1) != null) {
                    hashMap.put("recycleDemand", Integer.valueOf(barterText2.recycleDemand));
                    hashMap.put("subModelType", Integer.valueOf(tradeInWareCardInfo.exchangeWareWay.rightInfo.text1.subReplacementMode));
                }
                TradeInResultData.TradeInWareCardInfo.TradeInWareInfo.o2nProductDetailedForHouseElectricVO o2nproductdetailedforhouseelectricvo = next.o2nProductDetailedForHouseElectricVO;
                if (o2nproductdetailedforhouseelectricvo != null) {
                    hashMap.put("legacyMode", Integer.valueOf(o2nproductdetailedforhouseelectricvo.legacyMode));
                    hashMap.put("aclAmount", Integer.valueOf(next.o2nProductDetailedForHouseElectricVO.aclAmount));
                    hashMap.put("jdOldCatInfo", next.o2nProductDetailedForHouseElectricVO.jdOldCatInfo);
                    hashMap.put("firstOldItemInfo", next.o2nProductDetailedForHouseElectricVO.firstOldItemInfo);
                    hashMap.put("ruleId", next.o2nProductDetailedForHouseElectricVO.ruleId);
                    hashMap.put("ruleName", next.o2nProductDetailedForHouseElectricVO.ruleName);
                    hashMap.put("tagId", next.o2nProductDetailedForHouseElectricVO.tagId);
                    hashMap.put("choosedInquiryItems", next.o2nProductDetailedForHouseElectricVO.choosedInquiryItems);
                    hashMap.put("oldProductId", next.o2nProductDetailedForHouseElectricVO.oldProductId);
                    hashMap.put("pathInfo", next.o2nProductDetailedForHouseElectricVO.pathInfo);
                    hashMap.put("workerId", next.o2nProductDetailedForHouseElectricVO.workerId);
                    hashMap.put("deviceId", next.o2nProductDetailedForHouseElectricVO.deviceId);
                    if (sb.length() != 0) {
                        sb.append("#");
                    }
                    sb.append(next.o2nProductDetailedForHouseElectricVO.oldProductId);
                    mtaData.product_id = sb.toString();
                }
                TradeInResultData.TradeInFloorData tradeInFloorData3 = tradeInWareCardInfo.tradMode;
                if (tradeInFloorData3 != null && (barterFloorRight = tradeInFloorData3.rightInfo) != null && (barterText = barterFloorRight.text2) != null) {
                    hashMap.put("expectedVisitDate", barterText.saveText);
                }
            } else if (i2 == 4) {
                hashMap.put("inquiryId", next.inquiryId);
                TradeInResultData.TradeInWareCardInfo.TradeInWareInfo.OldProductInfoForClap oldProductInfoForClap = next.oldProductInfoForClap;
                if (oldProductInfoForClap != null) {
                    hashMap.put("recyclePrice", oldProductInfoForClap.recyclePrice);
                    hashMap.put("sendType", next.oldProductInfoForClap.sendType);
                    hashMap.put("inquiryStatus", next.oldProductInfoForClap.inquiryStatus);
                    hashMap.put("oldProductId", next.oldProductInfoForClap.oldProductId);
                    hashMap.put("oldProductName", next.oldProductInfoForClap.oldProductName);
                    if (sb2.length() != 0) {
                        sb2.append("#");
                    }
                    sb2.append(next.oldProductInfoForClap.oldProductId);
                    mtaData.PaiPaiProduct_id = sb2.toString();
                }
            }
            arrayList.add(hashMap);
        }
    }

    public void g() {
        Map<String, Object> d;
        Map<String, Object> d2;
        try {
            MutableLiveData<PdBaseProtocolLiveData.Result<TradeInResultData>> mutableLiveData = this.u;
            if (mutableLiveData == null || mutableLiveData.getValue() == null || this.u.getValue().mData == null) {
                return;
            }
            HashMap hashMap = new HashMap();
            if (this.u.getValue().mData.oldWare1 != null && (d2 = d(this.u.getValue().mData.oldWare1)) != null) {
                hashMap.put("oldWare1", d2);
            }
            if (this.u.getValue().mData.oldWare2 != null && (d = d(this.u.getValue().mData.oldWare2)) != null) {
                hashMap.put("oldWare2", d);
            }
            this.v = hashMap;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public void h(String str, JsonObject jsonObject) {
        String str2;
        String str3 = this.f5253e;
        if (jsonObject == null) {
            try {
                jsonObject = new JsonObject();
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
                str2 = "";
            }
        }
        jsonObject.addProperty("YJHX_source_param", this.f5261m == TradeInButtonType.DOUBLE ? "1" : "2");
        TradeInOpenLayerFrom tradeInOpenLayerFrom = this.f5254f;
        if (tradeInOpenLayerFrom != null) {
            jsonObject.addProperty("toast_entrance", tradeInOpenLayerFrom.from);
        }
        int i2 = this.f5255g;
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            jsonObject.addProperty("toast_biz", "3");
        } else if (i2 != 4) {
            jsonObject.addProperty("toast_biz", "-100");
        } else {
            int i3 = this.f5256h;
            if (i3 == 10) {
                jsonObject.addProperty("toast_biz", "2");
            } else if (i3 == 11) {
                jsonObject.addProperty("toast_biz", "1");
            }
        }
        str2 = jsonObject.toString();
        JDMtaUtils.sendExposureDataWithExt(this.b, str, null, RecommendMtaUtils.Productdetail_MainPage, "com.jd.lib.productdetail.ProductDetailActivity", str3, str2, null, null, null, null);
    }
}
