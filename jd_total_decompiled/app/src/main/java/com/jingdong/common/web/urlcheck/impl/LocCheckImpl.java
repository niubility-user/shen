package com.jingdong.common.web.urlcheck.impl;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.utils.PDLocalReceiver;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.corelib.utils.Log;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public class LocCheckImpl extends BaseWebComponent implements ICheckUrl {
    private final String TAG;

    public LocCheckImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = LocCheckImpl.class.getSimpleName();
    }

    private boolean checkLocUrl(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        if (Log.D) {
            Log.d(this.TAG, "scheme" + scheme);
        }
        if (scheme != null && !scheme.equals("http") && !scheme.equals("https")) {
            if (Log.D) {
                Log.d(this.TAG, "checkLocUrl() isNotIntentAvailable -->> ");
            }
            if (JumpUtil.isJdScheme(scheme)) {
                if (Log.D) {
                    Log.d(this.TAG, "checkLocUrl() -->> is openapp scheme :" + uri.toString());
                }
                String queryParameter = uri.getQueryParameter("params");
                if (TextUtils.isEmpty(queryParameter)) {
                    return false;
                }
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this.webUiBinder.getBaseActivity());
                boolean z = true;
                if (queryParameter.indexOf("\"des\":\"matchbuy_loc\"") >= 0) {
                    if (Log.D) {
                        Log.d(this.TAG, "checkLocUrl() start matchbuy_loc -->> " + queryParameter);
                    }
                    Intent intent = new Intent("com.jingdong.productActivity.ACTION_MATCHBUY_LOC_INFO");
                    intent.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"loc\"") >= 0) {
                    if (Log.D) {
                        Log.d(this.TAG, "checkLocUrl() start loc -->> " + queryParameter);
                    }
                    Intent intent2 = new Intent("com.jingdong.productActivity.ACTION_LOC_INFO");
                    intent2.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent2);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"productDetail_studentCertification\"") >= 0) {
                    Intent intent3 = new Intent(PDLocalReceiver.INTENT_ACTION_STUDENT_CERT);
                    intent3.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent3);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"productDetail_openWhiteBar\"") >= 0) {
                    Intent intent4 = new Intent(PDLocalReceiver.INTENT_ACTION_WHITEBAR_NEWUSER_CERT);
                    intent4.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent4);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"productdetail_sizehelper\"") >= 0) {
                    Intent intent5 = new Intent("com.jingdong.productActivity.ACTION_SIZE_HELPER_INFO");
                    intent5.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent5);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"BundlingSale\"") >= 0) {
                    Intent intent6 = new Intent(com.jingdong.app.mall.bundle.styleinfoview.utils.PDLocalReceiver.INTENT_ACTION_CAR_BUNDLING_SALE);
                    intent6.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent6);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"pdAuth\"") >= 0) {
                    Intent intent7 = new Intent("com.jingdong.productActivity.ACTION_REAL_NAME_CERTIFICATION");
                    intent7.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent7);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"pdPlusLinkistMember\"") >= 0) {
                    Intent intent8 = new Intent("com.jingdong.productActivity.ACTION_OPEN_PLUS_LINKIST_MEMBER");
                    intent8.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent8);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"mycarforItemList\"") >= 0) {
                    Intent intent9 = new Intent("com.jingdong.productActivity.INTENT_ACTION_UPDATE_CAR");
                    intent9.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent9);
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"mycarforItemAdd\"") >= 0) {
                    Intent intent10 = new Intent("com.jingdong.productActivity.INTENT_ACTION_ADD_CAR");
                    intent10.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent10);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"productdetail_hagglePrice\"") >= 0) {
                    Intent intent11 = new Intent(com.jingdong.app.mall.bundle.styleinfoview.utils.PDLocalReceiver.INTENT_ACTION_HAGGALE_PRICE);
                    intent11.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent11);
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"productDetail_zeroEpson\"") >= 0) {
                    Intent intent12 = new Intent("com.jingdong.productActivity.INTENT_ACTION_ZERO_EPSON");
                    intent12.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent12);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"productDetail_accompanyCoupon\"") >= 0) {
                    Intent intent13 = new Intent(PDLocalReceiver.INTENT_ACTION_BUILD_USER_INFO_FINISH);
                    intent13.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent13);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (queryParameter.indexOf("\"des\":\"productDetail_openVisionArchives\"") >= 0) {
                    Intent intent14 = new Intent(PDLocalReceiver.INTENT_ACTION_BUILD_VISION_ARCHIVES_SAVE);
                    intent14.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent14);
                    this.webUiBinder.finishUi();
                } else if (queryParameter.indexOf("\"des\":\"productDetail_refresh\"") >= 0) {
                    Intent intent15 = new Intent(PDLocalReceiver.INTENT_ACTION_REFRESH_PRODUCTDETAIL);
                    intent15.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent15);
                    try {
                        JDJSONObject parseObject = JDJSON.parseObject(queryParameter);
                        if (parseObject.containsKey("close")) {
                            z = parseObject.getBoolean("close").booleanValue();
                        }
                    } catch (Exception unused) {
                    }
                    if (z) {
                        this.webUiBinder.finishUi();
                    }
                } else if (queryParameter.indexOf("\"des\":\"show3cServicePop\"") >= 0) {
                    Intent intent16 = new Intent(PDLocalReceiver.INTENT_ACTION_SHOW_SERVICE_POP);
                    intent16.putExtra("key", queryParameter);
                    localBroadcastManager.sendBroadcast(intent16);
                    this.webUiBinder.finishUi();
                }
            }
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return true;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        Uri parse = Uri.parse(str);
        if (Log.D) {
            Log.d(this.TAG, "start checklocUrl :" + parse);
        }
        return checkLocUrl(parse);
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return WebUiConstans.UrlCheckKeys.CHECK_LOC;
    }
}
