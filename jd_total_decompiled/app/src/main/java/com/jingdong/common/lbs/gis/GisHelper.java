package com.jingdong.common.lbs.gis;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.CachePool;
import com.jd.framework.json.JDJSON;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class GisHelper {
    private static GisHelper helper;
    private GisAddressInfo addressInfo;

    private GisHelper() {
    }

    public static GisHelper getInstance() {
        GisHelper gisHelper;
        GisHelper gisHelper2 = helper;
        if (gisHelper2 != null) {
            return gisHelper2;
        }
        synchronized (GisHelper.class) {
            if (helper == null) {
                helper = new GisHelper();
            }
            gisHelper = helper;
        }
        return gisHelper;
    }

    public static GisAddressListInfo parseAddressList(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return null;
        }
        try {
            String string = jSONObjectProxy.getString("result");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return (GisAddressListInfo) JDJSON.parseObject(string, GisAddressListInfo.class);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void requestAddressByLocation(final double d, final double d2, final GisAddressListener gisAddressListener) {
        if (gisAddressListener == null) {
            return;
        }
        if (d <= 0.0d || d2 <= 0.0d) {
            if (gisAddressListener != null) {
                gisAddressListener.onError();
                return;
            }
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(HostConfig.getInstance().isUseBeta(HostConstants.COMMENT_HOST) ? "getJDDistrictFromLatlngByMapParams" : "getJDDistrictFromLatlng");
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.COMMENT_HOST));
        httpSetting.putJsonParam("lat", Double.valueOf(d));
        httpSetting.putJsonParam(HybridSDK.LNG, Double.valueOf(d2));
        httpSetting.setEncryptBody(true);
        httpSetting.putJsonParam(PairKey.APP_KEY, "platform_base_jddist_lnglat");
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        HttpGroup.getHttpGroup(createNewSettings).add(httpSetting);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.lbs.gis.GisHelper.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Log.d("gisHelper", httpResponse.getCode() + "  " + httpResponse.getString());
                Log.d("gisHelper", httpResponse.getString());
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                if (jSONObject == null) {
                    GisAddressListener gisAddressListener2 = gisAddressListener;
                    if (gisAddressListener2 != null) {
                        gisAddressListener2.onError();
                        return;
                    }
                    return;
                }
                try {
                    if (jSONObject.optInt("status") == 406) {
                        GisAddressListener gisAddressListener3 = gisAddressListener;
                        if (gisAddressListener3 != null) {
                            gisAddressListener3.onError();
                            return;
                        }
                        return;
                    }
                    String optString = jSONObject.optString("result");
                    if (TextUtils.isEmpty(optString)) {
                        GisAddressListener gisAddressListener4 = gisAddressListener;
                        if (gisAddressListener4 != null) {
                            gisAddressListener4.onError();
                            return;
                        }
                        return;
                    }
                    GisHelper.this.addressInfo = (GisAddressInfo) JDJSON.parseObject(optString, GisAddressInfo.class);
                    if (GisHelper.this.addressInfo != null) {
                        GisHelper.this.addressInfo.lat = d;
                        GisHelper.this.addressInfo.lng = d2;
                        GisAddressListener gisAddressListener5 = gisAddressListener;
                        if (gisAddressListener5 != null) {
                            gisAddressListener5.onSuccess(GisHelper.this.addressInfo);
                            return;
                        }
                        return;
                    }
                    GisAddressListener gisAddressListener6 = gisAddressListener;
                    if (gisAddressListener6 != null) {
                        gisAddressListener6.onError();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    GisAddressListener gisAddressListener7 = gisAddressListener;
                    if (gisAddressListener7 != null) {
                        gisAddressListener7.onError();
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.d("gisHelper", httpError.getException() + LangUtils.SINGLE_SPACE + httpError.getMessage());
                httpError.printStackTrace();
                GisAddressListener gisAddressListener2 = gisAddressListener;
                if (gisAddressListener2 != null) {
                    gisAddressListener2.onError();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
    }

    public void requestAddressListByLocation(double d, double d2, int i2, int i3, int i4, final GisAddressListListener gisAddressListListener) {
        if (gisAddressListListener == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.COMMENT_HOST));
        httpSetting.setEncryptBody(true);
        httpSetting.setFunctionId("placeSearch");
        httpSetting.putJsonParam(PairKey.APP_KEY, "platform_base_search");
        httpSetting.putJsonParam("pageSize", Integer.valueOf(i2));
        httpSetting.putJsonParam(CachePool.K_TAG_PAGE_INDEX, Integer.valueOf(i3));
        httpSetting.putJsonParam("boundary", "nearby(" + d + DYConstants.DY_REGEX_COMMA + d2 + DYConstants.DY_REGEX_COMMA + i4 + ")");
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        HttpGroup.getHttpGroup(createNewSettings).add(httpSetting);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.lbs.gis.GisHelper.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse.getJSONObject() == null) {
                    GisAddressListListener gisAddressListListener2 = gisAddressListListener;
                    if (gisAddressListListener2 != null) {
                        gisAddressListListener2.onError();
                        return;
                    }
                    return;
                }
                GisAddressListInfo parseAddressList = GisHelper.parseAddressList(httpResponse.getJSONObject());
                if (parseAddressList == null) {
                    GisAddressListListener gisAddressListListener3 = gisAddressListListener;
                    if (gisAddressListListener3 != null) {
                        gisAddressListListener3.onError();
                        return;
                    }
                    return;
                }
                gisAddressListListener.onSuccess(parseAddressList);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                GisAddressListListener gisAddressListListener2 = gisAddressListListener;
                if (gisAddressListListener2 != null) {
                    gisAddressListListener2.onError();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i5, int i6) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
    }

    public void requestAddressListSearch(String str, String str2, int i2, int i3, final GisAddressListListener gisAddressListListener) {
        if (gisAddressListListener == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.COMMENT_HOST));
        httpSetting.setFunctionId("placeSuggestion");
        httpSetting.putJsonParam(PairKey.APP_KEY, "platform_base_sugg");
        httpSetting.putJsonParam("keyword", str);
        httpSetting.putJsonParam("region", str2);
        if (i3 > 0 && i2 > 0) {
            httpSetting.putJsonParam("pageSize", Integer.valueOf(i2));
            httpSetting.putJsonParam(CachePool.K_TAG_PAGE_INDEX, Integer.valueOf(i3));
        }
        httpSetting.setEncryptBody(true);
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        HttpGroup.getHttpGroup(createNewSettings).add(httpSetting);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.lbs.gis.GisHelper.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse.getJSONObject() == null) {
                    GisAddressListListener gisAddressListListener2 = gisAddressListListener;
                    if (gisAddressListListener2 != null) {
                        gisAddressListListener2.onError();
                        return;
                    }
                    return;
                }
                GisAddressListInfo parseAddressList = GisHelper.parseAddressList(httpResponse.getJSONObject());
                if (parseAddressList == null) {
                    GisAddressListListener gisAddressListListener3 = gisAddressListListener;
                    if (gisAddressListListener3 != null) {
                        gisAddressListListener3.onError();
                        return;
                    }
                    return;
                }
                gisAddressListListener.onSuccess(parseAddressList);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                GisAddressListListener gisAddressListListener2 = gisAddressListListener;
                if (gisAddressListListener2 != null) {
                    gisAddressListListener2.onError();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i4, int i5) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
    }
}
