package com.jingdong.common.ui.address.eu;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jd.lib.un.address.R;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.dialog.UnBottomDialog;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.widget.button.UnButton;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.lib.utils.HostUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class EuShippingUtils {
    private static final String ADDRESS_GLOBAL_EU_SHIPPING = "AddressGlobalEuShipping";
    private static final String EU_COUNTRY_LIST = "eu_country_list";
    private static List<Integer> euCountry;

    static /* synthetic */ String access$000() {
        return getShippingExplainPagePath();
    }

    private static UnBottomDialog createShippingExplainDialog(final Context context, final OnEuDialogClickListener onEuDialogClickListener) {
        final UnBottomDialog unBottomDialog = new UnBottomDialog(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.eu_explain_dialog, (ViewGroup) null, false);
        unBottomDialog.addContentWithHeight(inflate, null, 0.8f, false);
        TextView textView = (TextView) inflate.findViewById(R.id.serviceName);
        TextView textView2 = (TextView) inflate.findViewById(R.id.phoneNum);
        TextView textView3 = (TextView) inflate.findViewById(R.id.netAddress);
        TextView textView4 = (TextView) inflate.findViewById(R.id.copyBtn);
        TextView textView5 = (TextView) inflate.findViewById(R.id.official_account);
        TextView textView6 = (TextView) inflate.findViewById(R.id.address);
        TextView textView7 = (TextView) inflate.findViewById(R.id.user_phone);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.close);
        UnButton unButton = (UnButton) inflate.findViewById(R.id.left);
        UnButton unButton2 = (UnButton) inflate.findViewById(R.id.right);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.serviceInfoLayout);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.serviceAddressLayout);
        final EuShippingServiceInfo euShippingServiceInfo = getEuShippingServiceInfo();
        View findViewById = inflate.findViewById(R.id.netAddressLayout);
        AddressGlobal eUShippingAddress = getEUShippingAddress();
        if (euShippingServiceInfo != null && eUShippingAddress != null) {
            if (euShippingServiceInfo.id == eUShippingAddress.getServiceId()) {
                if (!TextUtils.isEmpty(euShippingServiceInfo.serviceName)) {
                    textView.setText("\u670d\u52a1\u5546\uff1a" + euShippingServiceInfo.serviceName);
                } else {
                    textView.setVisibility(8);
                }
                if (!TextUtils.isEmpty(euShippingServiceInfo.servicePhone)) {
                    textView2.setText(Html.fromHtml("\u7535\u8bdd\uff1a<font color='#2692ff'>" + euShippingServiceInfo.servicePhone + "</font>"));
                } else {
                    textView2.setVisibility(8);
                }
                if (!TextUtils.isEmpty(euShippingServiceInfo.netAddress)) {
                    textView3.setText("\u7f51\u5740\uff1a" + euShippingServiceInfo.netAddress);
                } else {
                    findViewById.setVisibility(8);
                }
                if (!TextUtils.isEmpty(euShippingServiceInfo.officialAccount)) {
                    textView5.setText("\u516c\u4f17\u53f7\uff1a" + euShippingServiceInfo.officialAccount);
                } else {
                    textView5.setVisibility(8);
                }
                String where = eUShippingAddress.getWhere();
                if (TextUtils.isEmpty(where)) {
                    where = eUShippingAddress.getProvinceName() + eUShippingAddress.getCityName() + eUShippingAddress.getAreaName() + eUShippingAddress.getTownName() + eUShippingAddress.getAddressDetail();
                }
                if (TextUtils.isEmpty(where)) {
                    linearLayout2.setVisibility(8);
                } else {
                    textView6.setText(where);
                    String mobileReal = eUShippingAddress.getMobileReal();
                    if (TextUtils.isEmpty(mobileReal)) {
                        mobileReal = eUShippingAddress.getMobile();
                    }
                    if (TextUtils.isEmpty(mobileReal)) {
                        mobileReal = eUShippingAddress.phone;
                    }
                    if (TextUtils.isEmpty(mobileReal) || TextUtils.equals(DYConstants.DY_NULL_STR, mobileReal)) {
                        mobileReal = "";
                    }
                    textView7.setText(eUShippingAddress.getName() + LangUtils.SINGLE_SPACE + mobileReal);
                }
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        unBottomDialog.dismiss();
                        OnEuDialogClickListener onEuDialogClickListener2 = onEuDialogClickListener;
                        if (onEuDialogClickListener2 != null) {
                            onEuDialogClickListener2.onCloseClick();
                        }
                    }
                });
                unButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        UnWidgetThemeController.getInstance().jdRouter(EuShippingUtils.access$000());
                        OnEuDialogClickListener onEuDialogClickListener2 = onEuDialogClickListener;
                        if (onEuDialogClickListener2 != null) {
                            onEuDialogClickListener2.onBottomLeftClick();
                        }
                    }
                });
                unButton2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        unBottomDialog.dismiss();
                        OnEuDialogClickListener onEuDialogClickListener2 = onEuDialogClickListener;
                        if (onEuDialogClickListener2 != null) {
                            onEuDialogClickListener2.onBottomRightClick();
                        }
                    }
                });
                textView4.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        try {
                            ((ClipboardManager) context.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD)).setPrimaryClip(ClipData.newPlainText(null, euShippingServiceInfo.netAddress));
                            ToastUtils.showToastInCenter(context, (byte) 2, "\u590d\u5236\u6210\u529f", 1);
                        } catch (Throwable th) {
                            if (OKLog.D) {
                                th.printStackTrace();
                            }
                        }
                        OnEuDialogClickListener onEuDialogClickListener2 = onEuDialogClickListener;
                        if (onEuDialogClickListener2 != null) {
                            onEuDialogClickListener2.onCopyClick();
                        }
                    }
                });
                return unBottomDialog;
            }
            linearLayout = linearLayout;
        }
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(8);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                unBottomDialog.dismiss();
                OnEuDialogClickListener onEuDialogClickListener2 = onEuDialogClickListener;
                if (onEuDialogClickListener2 != null) {
                    onEuDialogClickListener2.onCloseClick();
                }
            }
        });
        unButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UnWidgetThemeController.getInstance().jdRouter(EuShippingUtils.access$000());
                OnEuDialogClickListener onEuDialogClickListener2 = onEuDialogClickListener;
                if (onEuDialogClickListener2 != null) {
                    onEuDialogClickListener2.onBottomLeftClick();
                }
            }
        });
        unButton2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                unBottomDialog.dismiss();
                OnEuDialogClickListener onEuDialogClickListener2 = onEuDialogClickListener;
                if (onEuDialogClickListener2 != null) {
                    onEuDialogClickListener2.onBottomRightClick();
                }
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    ((ClipboardManager) context.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD)).setPrimaryClip(ClipData.newPlainText(null, euShippingServiceInfo.netAddress));
                    ToastUtils.showToastInCenter(context, (byte) 2, "\u590d\u5236\u6210\u529f", 1);
                } catch (Throwable th) {
                    if (OKLog.D) {
                        th.printStackTrace();
                    }
                }
                OnEuDialogClickListener onEuDialogClickListener2 = onEuDialogClickListener;
                if (onEuDialogClickListener2 != null) {
                    onEuDialogClickListener2.onCopyClick();
                }
            }
        });
        return unBottomDialog;
    }

    public static AddressGlobal getEUShippingAddress() {
        String string = SharedPreferencesUtil.getString(ADDRESS_GLOBAL_EU_SHIPPING, "");
        if (OKLog.D) {
            OKLog.d("EuShippingUtils", "getEUShippingAddress:" + string);
        }
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return (AddressGlobal) JDJSON.parseObject(string, AddressGlobal.class);
    }

    public static EuShippingServiceInfo getEuShippingServiceInfo() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "euAddressConfig", "euShippingService");
        if (OKLog.D) {
            OKLog.d("EuShippingUtils", "getEuShippingServiceInfo:" + config);
        }
        if (TextUtils.isEmpty(config)) {
            return null;
        }
        return (EuShippingServiceInfo) JDJSON.parseObject(config, EuShippingServiceInfo.class);
    }

    public static EuShippingServiceInfo getEuShippingServiceInfoById(long j2) {
        EuShippingServiceInfo euShippingServiceInfo;
        String config = JDMobileConfig.getInstance().getConfig("unification", "euAddressConfig", "euShippingService");
        if (OKLog.D) {
            OKLog.d("EuShippingUtils", "getEuShippingServiceInfo:" + config);
        }
        if (TextUtils.isEmpty(config) || (euShippingServiceInfo = (EuShippingServiceInfo) JDJSON.parseObject(config, EuShippingServiceInfo.class)) == null || euShippingServiceInfo.id != j2) {
            return null;
        }
        return euShippingServiceInfo;
    }

    private static String getShippingExplainPagePath() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "euAddressConfig", "euShippingPage");
        return TextUtils.isEmpty(config) ? "https://ihelp.jd.com/l/help/scene/getSceneDetail?id=332314" : config;
    }

    public static boolean isEUCountry(int i2) {
        List<Integer> list = euCountry;
        if (list == null || list.isEmpty()) {
            String string = SharedPreferencesUtil.getString(EU_COUNTRY_LIST, "");
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            List<Integer> list2 = (List) JDJSON.parseObject(string, new TypeReference<ArrayList<Integer>>() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.5
            }.type, new Feature[0]);
            euCountry = list2;
            if (list2 == null) {
                return false;
            }
        }
        return euCountry.contains(Integer.valueOf(i2));
    }

    public static void requestAddress() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("publicThirdAddress");
        httpSetting.putJsonParam("channel", "1");
        httpSetting.putJsonParam("action", "euAddressId");
        httpSetting.setHost(HostUtils.getWareHost());
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.7
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                if (jSONObject != null) {
                    try {
                        String string = jSONObject.getString("euAddressId");
                        if (TextUtils.isEmpty(string)) {
                            return;
                        }
                        EuShippingUtils.updateEUCountryList(string);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static UnBottomDialog showShippingExplainDialog(Context context, OnEuDialogClickListener onEuDialogClickListener) {
        UnBottomDialog createShippingExplainDialog = createShippingExplainDialog(context, onEuDialogClickListener);
        createShippingExplainDialog.show();
        return createShippingExplainDialog;
    }

    public static void updateEUCountryList(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferencesUtil.putString(EU_COUNTRY_LIST, str);
        List<Integer> list = (List) JDJSON.parseObject(str, new TypeReference<ArrayList<Integer>>() { // from class: com.jingdong.common.ui.address.eu.EuShippingUtils.6
        }.type, new Feature[0]);
        if (OKLog.D) {
            OKLog.d("EuShippingUtils", "updateEUCountryList-String:" + str);
            StringBuilder sb = new StringBuilder();
            sb.append("updateEUCountryList-list:");
            sb.append(list == null ? DYConstants.DY_NULL_STR : Integer.valueOf(list.size()));
            OKLog.d("EuShippingUtils", sb.toString());
        }
        if (list == null || list.isEmpty()) {
            return;
        }
        euCountry = list;
    }

    public static void updateEUShippingAddress(AddressGlobal addressGlobal) {
        if (OKLog.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("updateEUShippingAddress:");
            sb.append(addressGlobal == null ? DYConstants.DY_NULL_STR : addressGlobal.toOrderStr());
            OKLog.d("EuShippingUtils", sb.toString());
        }
        if (addressGlobal == null) {
            SharedPreferencesUtil.remove(ADDRESS_GLOBAL_EU_SHIPPING);
        } else {
            SharedPreferencesUtil.putString(ADDRESS_GLOBAL_EU_SHIPPING, addressGlobal.toString());
        }
    }
}
