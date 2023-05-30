package com.jingdong.common.recommend.entity;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.apis.DYTemplateStatus;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.widget.custom.livewidget.bean.LiveVideoEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendItem {
    public ArrayList<String> ad_exposal_url;
    public String bizfield;
    public String client_exposal_url;
    public RecommendDna dna;
    public ExpoData expoData;
    public ArrayList<ExpoData> expoDatas;
    public JSONObject jsonObject;
    public JDJSONObject localJsonObject;
    public RecommendProduct product;
    public RecommendPromotion promotion;
    public RecommendFestivalData recommendFestivalData;
    public RecommendHomeCardBean recommendHomeCardBean;
    public RecommendHomeTabTemp recommendHomeTabTemp;
    public LiveVideoEntity recommendLiveBean;
    public RecommendPdProductFor2 recommendPdProductFor2;
    public RecommendPdTitle recommendPdTitle;
    public RecommendTemplate recommendTemplate;
    public RecommendVideo recommendVideo;
    public RecommendShop shop;
    public RecommendAdData type66Data;
    public String videoId;
    public int type = -1;
    public int opmShowTimes = 0;
    public boolean hasRecommendExpo = false;
    public boolean hasAdExpo = false;
    public boolean isShow = true;
    public boolean isHomeData = false;

    public RecommendItem() {
    }

    private void addTouchStone(JDJSONObject jDJSONObject, String str) {
        if (jDJSONObject != null) {
            String optString = jDJSONObject.optString("touchstone_expids");
            if (!TextUtils.isEmpty(optString)) {
                str = optString + DYConstants.DY_REGEX_COMMA + str;
            }
            jDJSONObject.put("touchstone_expids", (Object) str);
        }
    }

    private void createDynamicType(JDJSONObject jDJSONObject) {
        if (hasDynamicFiled(this.bizfield)) {
            RecommendTemplate recommendTemplate = (RecommendTemplate) JDJSON.parseObject(jDJSONObject.toString(), RecommendTemplate.class);
            this.recommendTemplate = recommendTemplate;
            this.type66Data = recommendTemplate;
            if (RecommendType.dynamicTypeMap.get(this.bizfield) == null) {
                RecommendType.dynamicTypeMap.put(this.bizfield, Integer.valueOf(RecommendType.dynamic_baseline));
                RecommendType.typeDynamicMap.put(Integer.valueOf(RecommendType.dynamic_baseline), this.bizfield);
                RecommendType.recom_dynamic_types.add(Integer.valueOf(RecommendType.dynamic_baseline));
                RecommendType.dynamic_baseline++;
            }
            this.type = RecommendType.dynamicTypeMap.get(this.bizfield).intValue();
            return;
        }
        RecommendUtils.requestDynamic();
        this.isShow = false;
    }

    private void generateExpoData(JDJSONObject jDJSONObject) {
        try {
            this.client_exposal_url = jDJSONObject.optString("client_exposal_url");
            this.expoData = productExpoData(jDJSONObject);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    private boolean hasDynamicFiled(String str) {
        if (!HomeRecommendContentLayout.isUseDynamicZip && !HomeRecommendContentLayout.isUseNewDynApi) {
            return DynamicSdk.getEngine().hasCachedTempFile(RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE, str);
        }
        IDynamicDriver driver = DynamicSdk.getDriver();
        DYTemplateStatus templateStatus = driver != null ? driver.getTemplateStatus(RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE, str) : null;
        return templateStatus != null && (templateStatus.getB() || templateStatus.getA());
    }

    public static ExpoData productExpoData(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            ExpoData expoData = new ExpoData(true);
            expoData.exposureSourceValue = jDJSONObject.optString("exposureJsonSourceValue");
            expoData.isBackUp = -1;
            return expoData;
        }
        return null;
    }

    public void handleData(JDJSONObject jDJSONObject) {
        try {
            String buriedStr = UnIconConfigHelper.getBuriedStr();
            if (TextUtils.isEmpty(buriedStr)) {
                return;
            }
            JDJSONObject optJSONObject = jDJSONObject.optJSONObject("exposureJsonSourceValue");
            addTouchStone(optJSONObject, buriedStr);
            jDJSONObject.put("exposureJsonSourceValue", (Object) optJSONObject.toJSONString());
            JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("sourceValue");
            addTouchStone(optJSONObject2, buriedStr);
            jDJSONObject.put("sourceValue", (Object) optJSONObject2.toJSONString());
        } catch (Exception unused) {
        }
    }

    public void setData(JDJSONObject jDJSONObject) {
        setData(jDJSONObject, -1);
    }

    public void setData(JDJSONObject jDJSONObject, int i2) {
        this.videoId = jDJSONObject.optString("videoId");
        this.localJsonObject = jDJSONObject;
        if (jDJSONObject != null) {
            this.jsonObject = new JSONObject(this.localJsonObject.getInnerMap());
        }
        this.bizfield = jDJSONObject.optString("bizfield");
        int optInt = jDJSONObject.optInt(CartConstant.KEY_VENDOR_ITEM_TYPE);
        if (optInt == 0 && i2 == 9 && "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "touchStoneAdd", "touchStoneAdd", "0"))) {
            handleData(jDJSONObject);
        }
        generateExpoData(jDJSONObject);
        if (optInt == 0 || optInt == 16 || optInt == 37) {
            RecommendProduct recommendProduct = (RecommendProduct) jDJSONObject.toJavaObject(RecommendProduct.class);
            this.product = recommendProduct;
            recommendProduct.jdjsonObject = jDJSONObject;
            if (optInt == 16 || optInt == 0) {
                recommendProduct.generateLocalColors();
            }
            this.type = optInt;
            if (TextUtils.isEmpty(this.bizfield)) {
                return;
            }
            this.type = 20000;
        } else if (optInt == 36 || optInt == 18) {
            this.recommendVideo = (RecommendVideo) JDJSON.parseObject(jDJSONObject.toString(), RecommendVideo.class);
            this.type = optInt;
        } else if (optInt == 20 || optInt == 24 || optInt == 31 || optInt == 32 || optInt == 34 || optInt == 33 || optInt == 999 || optInt == 26 || optInt == 1000 || optInt == 1003) {
            RecommendDna recommendDna = (RecommendDna) JDJSON.parseObject(jDJSONObject.toString(), RecommendDna.class);
            this.dna = recommendDna;
            if (TextUtils.isEmpty(recommendDna.wareId)) {
                RecommendDna recommendDna2 = this.dna;
                recommendDna2.wareId = recommendDna2.dnaId;
            }
            if (optInt == 1000) {
                this.dna.dealBannerData();
            }
            this.type = optInt;
            if (optInt == 26) {
                String optString = jDJSONObject.optString("opmShowTimes");
                if (!TextUtils.isEmpty(optString)) {
                    try {
                        this.opmShowTimes = Integer.parseInt(optString);
                    } catch (NumberFormatException e2) {
                        if (OKLog.D) {
                            e2.printStackTrace();
                        }
                    }
                }
                this.dna.opmShowTimes = this.opmShowTimes;
            }
        } else if (optInt != 66) {
            if (optInt == 15 || optInt == 19 || optInt == 23) {
                RecommendHomeTabTemp recommendHomeTabTemp = (RecommendHomeTabTemp) JDJSON.parseObject(jDJSONObject.toString(), RecommendHomeTabTemp.class);
                this.recommendHomeTabTemp = recommendHomeTabTemp;
                recommendHomeTabTemp.generateFirstTitleColor();
                this.type = optInt;
            } else if (optInt != 1001 && optInt != 1002 && optInt != 2003 && optInt != 2004 && optInt != 2005 && optInt != 2006 && optInt != 2007) {
                this.type = 0;
            } else {
                this.isHomeData = true;
                this.type = optInt;
                if (!TextUtils.isEmpty(this.bizfield)) {
                    createDynamicType(jDJSONObject);
                } else if (optInt == 2003) {
                    this.recommendHomeCardBean = (RecommendHomeCardBean) JDJSON.parseObject(jDJSONObject.toString(), RecommendHomeCardBean.class);
                } else if (optInt == 2004) {
                    this.recommendHomeCardBean = (RecommendHomeCardBean) JDJSON.parseObject(jDJSONObject.toString(), RecommendHomeCardBean.class);
                } else if (optInt == 2005) {
                    this.recommendHomeCardBean = (RecommendHomeCardBean) JDJSON.parseObject(jDJSONObject.toString(), RecommendHomeCardBean.class);
                } else if (optInt == 2006) {
                    this.recommendHomeCardBean = (RecommendHomeCardBean) JDJSON.parseObject(jDJSONObject.toString(), RecommendHomeCardBean.class);
                } else if (optInt == 2007) {
                    this.recommendHomeCardBean = (RecommendHomeCardBean) JDJSON.parseObject(jDJSONObject.toString(), RecommendHomeCardBean.class);
                } else {
                    RecommendFestivalData recommendFestivalData = (RecommendFestivalData) JDJSON.parseObject(jDJSONObject.toString(), RecommendFestivalData.class);
                    this.recommendFestivalData = recommendFestivalData;
                    if (optInt == 1001) {
                        recommendFestivalData.generateBgColors();
                        this.type = 2001;
                        return;
                    }
                    this.type = 2002;
                }
            }
        } else if (!TextUtils.isEmpty(this.bizfield)) {
            if (jDJSONObject.containsKey("jdFlvUrl")) {
                this.recommendLiveBean = new LiveVideoEntity("4", jDJSONObject.optString("jdFlvUrl"), jDJSONObject.optString("itemid"), "0", jDJSONObject.optString("img"), 1);
            }
            createDynamicType(jDJSONObject);
        } else {
            String optString2 = jDJSONObject.optString("tpid");
            if ("7".equals(optString2)) {
                RecommendVideo recommendVideo = (RecommendVideo) JDJSON.parseObject(jDJSONObject.toString(), RecommendVideo.class);
                this.recommendVideo = recommendVideo;
                recommendVideo.generateImageUrl();
                this.type = 1007;
                this.type66Data = this.recommendVideo;
            } else if ("8".equals(optString2)) {
                RecommendDna recommendDna3 = (RecommendDna) JDJSON.parseObject(jDJSONObject.toString(), RecommendDna.class);
                this.dna = recommendDna3;
                this.type = 1008;
                if (TextUtils.isEmpty(recommendDna3.wareId)) {
                    RecommendDna recommendDna4 = this.dna;
                    recommendDna4.wareId = recommendDna4.dnaId;
                }
                this.type66Data = this.dna;
            } else if ("5".equals(optString2)) {
                RecommendVideo recommendVideo2 = (RecommendVideo) JDJSON.parseObject(jDJSONObject.toString(), RecommendVideo.class);
                this.recommendVideo = recommendVideo2;
                recommendVideo2.generateImageUrl();
                this.type = 1005;
                this.type66Data = this.recommendVideo;
            } else if ("6".equals(optString2)) {
                RecommendVideo recommendVideo3 = (RecommendVideo) JDJSON.parseObject(jDJSONObject.toString(), RecommendVideo.class);
                this.recommendVideo = recommendVideo3;
                recommendVideo3.generateImageUrl();
                this.type = 1006;
                this.type66Data = this.recommendVideo;
            } else if ("13".equals(optString2)) {
                RecommendVideo recommendVideo4 = (RecommendVideo) JDJSON.parseObject(jDJSONObject.toString(), RecommendVideo.class);
                this.recommendVideo = recommendVideo4;
                recommendVideo4.generateImageUrl();
                this.type = 1013;
                this.type66Data = this.recommendVideo;
            } else if ("14".equals(optString2)) {
                RecommendVideo recommendVideo5 = (RecommendVideo) JDJSON.parseObject(jDJSONObject.toString(), RecommendVideo.class);
                this.recommendVideo = recommendVideo5;
                recommendVideo5.generateImageUrl();
                this.type = 1014;
                this.type66Data = this.recommendVideo;
            } else if ("15".equals(optString2)) {
                RecommendVideo recommendVideo6 = (RecommendVideo) JDJSON.parseObject(jDJSONObject.toString(), RecommendVideo.class);
                this.recommendVideo = recommendVideo6;
                recommendVideo6.generateImageUrl();
                this.type = 1015;
                this.type66Data = this.recommendVideo;
            } else if (Constants.VIA_ACT_TYPE_NINETEEN.equals(optString2)) {
                RecommendVideo recommendVideo7 = (RecommendVideo) JDJSON.parseObject(jDJSONObject.toString(), RecommendVideo.class);
                this.recommendVideo = recommendVideo7;
                recommendVideo7.generateImageUrl();
                this.type = 1019;
                this.type66Data = this.recommendVideo;
            } else if ("22".equals(optString2)) {
                RecommendVideo recommendVideo8 = (RecommendVideo) JDJSON.parseObject(jDJSONObject.toString(), RecommendVideo.class);
                this.recommendVideo = recommendVideo8;
                recommendVideo8.generateImageUrl();
                this.type = 1022;
                this.type66Data = this.recommendVideo;
            } else {
                RecommendTemplate recommendTemplate = (RecommendTemplate) JDJSON.parseObject(jDJSONObject.toString(), RecommendTemplate.class);
                this.recommendTemplate = recommendTemplate;
                recommendTemplate.generateFirstTitleColor();
                this.recommendTemplate.generateImageUrl();
                this.type66Data = this.recommendTemplate;
                if ("2".equals(optString2)) {
                    this.type = 1002;
                } else if ("3".equals(optString2)) {
                    this.type = 10003;
                } else if ("1".equals(optString2)) {
                    this.type = 1001;
                } else if ("4".equals(optString2)) {
                    this.type = 1004;
                } else if ("9".equals(optString2)) {
                    this.type = 1009;
                } else if ("10".equals(optString2)) {
                    this.type = 1010;
                } else if ("11".equals(optString2)) {
                    this.type = 1011;
                } else if ("12".equals(optString2)) {
                    this.type = 1012;
                } else if ("18".equals(optString2)) {
                    this.type = 1018;
                } else {
                    this.type = 1001;
                }
            }
        }
    }

    public RecommendItem(JDJSONObject jDJSONObject) {
        setData(jDJSONObject);
    }
}
