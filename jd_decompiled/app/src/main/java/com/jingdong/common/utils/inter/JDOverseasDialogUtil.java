package com.jingdong.common.utils.inter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.d;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.R;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.businessAddress.JDGlobalAddressManager;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.inter.JDOverseasModel;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class JDOverseasDialogUtil {
    private static final String B_MODEL = "2";
    public static final String OVERSEAS_LBS_ID = "e5f3f30ce925e0181d5f07d143df506c";
    private static final String OVERSEAS_SCENE_ID = "basicShoppingProcess";
    private static final String TAG = "JDOverseas_";
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private static final AtomicBoolean sChecked = new AtomicBoolean(false);

    public static boolean checkOverseasDialog(final String str, final Activity activity, final BaseOverseasListener baseOverseasListener) {
        if (activity == null || d.a() || !JDPrivacyHelper.isAcceptPrivacy(activity) || TextUtils.equals(JDBModeUtils.getCurrentMode(), "2")) {
            return false;
        }
        mtaExpo(str, PermissionHelper.hasGrantedLocation(PermissionHelper.generateBundle("OverseasCheck", JDOverseasDialogUtil.class.getSimpleName(), "getAddress")), "Sitechange_Get");
        if (sChecked.getAndSet(true)) {
            log("start only check once");
            return false;
        }
        if (baseOverseasListener == null) {
            baseOverseasListener = new BaseOverseasListener();
        }
        if (!JDOverseasModel.hasShowTimes()) {
            log("not have showTimes");
            return false;
        }
        JDLocationOption jDLocationOption = new JDLocationOption();
        jDLocationOption.setBusinessId(OVERSEAS_LBS_ID);
        jDLocationOption.setSceneId("basicShoppingProcess");
        JDLocationManager.getInstance().getAddress(jDLocationOption, new JDLocationListener() { // from class: com.jingdong.common.utils.inter.JDOverseasDialogUtil.1
            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onFail(JDLocationError jDLocationError) {
                if (!JDOverseasDialogUtil.requestOverseasInfo(str, activity, new JDLocation(), false, baseOverseasListener)) {
                    JDOverseasDialogUtil.unShowChangeDialog(baseOverseasListener);
                }
                JDOverseasDialogUtil.log("LBS onFail");
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onSuccess(JDLocation jDLocation) {
                if (JDOverseasDialogUtil.requestOverseasInfo(str, activity, jDLocation, false, baseOverseasListener)) {
                    return;
                }
                JDOverseasDialogUtil.unShowChangeDialog(baseOverseasListener);
            }
        });
        return true;
    }

    public static void dismissDialog(Dialog dialog) {
        try {
            dialog.dismiss();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static LinearLayout generateTipsTv(ViewGroup viewGroup, int i2, String str, String str2, String str3, int i3) {
        Context context = viewGroup.getContext();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setPadding(DpiUtil.dip2px(context, 5.0f), DpiUtil.dip2px(context, 5.0f), DpiUtil.dip2px(context, 5.0f), DpiUtil.dip2px(context, 5.0f));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(DpiUtil.dip2px(context, 5.0f));
        gradientDrawable.setColor(i3);
        linearLayout.setBackground(gradientDrawable);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        JDImageUtils.displayImage(str2, simpleDraweeView, new JDDisplayImageOptions().showImageForEmptyUri(i2).showImageOnFail(i2).showImageOnLoading(i2));
        linearLayout.addView(simpleDraweeView);
        setViewSize(simpleDraweeView, 26, 26, null);
        TextView textView = new TextView(context);
        textView.setTextSize(0, DpiUtil.dip2px(context, 12.0f));
        textView.setPadding(0, -3, 0, -3);
        textView.setTextColor(-8355712);
        textView.setGravity(16);
        textView.setLineSpacing(0.0f, 0.75f);
        if (!TextUtils.isEmpty(str3)) {
            str = str3;
        }
        textView.setText(str);
        linearLayout.addView(textView);
        setViewSize(textView, 86, 26, null);
        viewGroup.addView(linearLayout);
        return linearLayout;
    }

    private static boolean isValidLocation(JDLocation jDLocation) {
        return (jDLocation == null || jDLocation.getProvinceId() <= 0 || TextUtils.isEmpty(jDLocation.getProvinceName()) || jDLocation.getCityId() <= 0 || TextUtils.isEmpty(jDLocation.getCityName())) ? false : true;
    }

    public static void log(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, str);
        }
    }

    public static void mtaClick(String str, int i2, int i3, String str2) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("module", (Object) str);
        jDJSONObject.put("oldsite", (Object) ("" + i2));
        jDJSONObject.put("newsite", (Object) ("" + i3));
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), str2, "", "", "", "", "", "", jDJSONObject.toJSONString(), null);
    }

    public static void mtaExpo(String str, int i2, int i3, String str2) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("module", (Object) str);
        jDJSONObject.put("oldsite", (Object) ("" + i2));
        jDJSONObject.put("newsite", (Object) ("" + i3));
        mtaExpo(str2, jDJSONObject);
    }

    public static boolean requestOverseasInfo(String str, Activity activity, JDLocation jDLocation, BaseOverseasListener baseOverseasListener) {
        if (d.a() || !JDPrivacyHelper.isAcceptPrivacy(activity) || TextUtils.equals(JDBModeUtils.getCurrentMode(), "2")) {
            return false;
        }
        mtaExpo(str, true, "Sitechange_Get");
        return requestOverseasInfo(str, activity, jDLocation, true, baseOverseasListener);
    }

    private static void setViewSize(View view, int i2, int i3, Rect rect) {
        Context context;
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (context = view.getContext()) == null || (layoutParams = view.getLayoutParams()) == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (i2 > 0) {
            i2 = DpiUtil.dip2px(context, i2);
        }
        marginLayoutParams.width = i2;
        if (i3 > 0) {
            i3 = DpiUtil.dip2px(context, i3);
        }
        marginLayoutParams.height = i3;
        if (rect != null) {
            marginLayoutParams.setMargins(DpiUtil.dip2px(context, rect.left), DpiUtil.dip2px(context, rect.top), DpiUtil.dip2px(context, rect.right), DpiUtil.dip2px(context, rect.bottom));
        }
        view.setLayoutParams(marginLayoutParams);
    }

    private static void showDialog(Dialog dialog) {
        try {
            dialog.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void showDialogView(final String str, Activity activity, final JDLocation jDLocation, final JDOverseasModel jDOverseasModel, final BaseOverseasListener baseOverseasListener) {
        String str2;
        String str3;
        int i2;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        if (!baseOverseasListener.canShowDialog()) {
            baseOverseasListener.unShowDialog();
            log("request dialog can't show...");
            return;
        }
        int i3 = R.drawable.overseas_dialog_tips_icon1;
        int i4 = R.drawable.overseas_dialog_tips_icon2;
        int i5 = R.drawable.overseas_dialog_tips_icon3;
        int i6 = R.drawable.overseas_dialog_tips_icon4;
        int areaCode = jDOverseasModel.getAreaCode();
        if (areaCode == 0) {
            str2 = "\u672c\u5730\u652f\u4ed8\u65b9\u5f0f";
            str3 = "\u914d\u9001\u4e0a\u95e8\u6216\u81ea\u63d0";
            i2 = R.drawable.overseas_dialog_tips_icon5;
            str4 = "\u672c\u5730\u4e13\u5c5e\u4f18\u60e0";
            str5 = "\u66f4\u591a\u5546\u54c1\u79cd\u7c7b";
            i6 = i4;
        } else {
            if (areaCode == 5) {
                str7 = "\u6e2f\u6fb3\u5c08\u5c6c\r\n\u6d3b\u52d5\u512a\u60e0";
                str8 = "\u591a\u91cd\u652f\u4ed8\u597d\u79ae";
            } else if (areaCode != 7) {
                str2 = "\u591a\u91cd\u652f\u4ed8\u597d\u793c";
                str3 = "\u8de8\u5883\u4e13\u5c5e\u5ba2\u670d";
                i2 = i4;
                str4 = "\u6d77\u5916\u4e13\u5c5e\r\n\u6d3b\u52a8\u4f18\u60e0";
                str5 = "\u76f4\u90ae/\u96c6\u8fd0\r\n\u591a\u79cd\u7269\u6d41\u9009\u62e9";
            } else {
                str7 = "\u6708\u6708\u597d\u5eb7\r\n\u591a\u91cd\u56de\u994b";
                str8 = "\u5b98\u65b9\u5e97\r\n\u9650\u6642$0\u904b\u8cbb";
            }
            i2 = i4;
            str4 = str7;
            str6 = "\u8de8\u5883\u5c08\u5c6c\u5ba2\u670d";
            str5 = "\u76f4\u90f5/\u96c6\u904b\r\n\u591a\u7a2e\u7269\u6d41\u9078\u64c7";
            str2 = str8;
            final JDDialog jDDialog = new JDDialog(activity);
            jDDialog.setContentView(R.layout.overseas_confirm_dialog);
            jDDialog.setCanceledOnTouchOutside(false);
            LinearLayout linearLayout = (LinearLayout) jDDialog.findViewById(R.id.rootView);
            setViewSize(linearLayout, 295, -2, null);
            setViewSize((LinearLayout) linearLayout.findViewById(R.id.title_container), -2, -2, new Rect(0, 24, 0, 0));
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) linearLayout.findViewById(R.id.title_img);
            setViewSize(simpleDraweeView, 16, 16, new Rect(0, 0, 2, 0));
            String areaImg = jDOverseasModel.getAreaImg();
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            int i7 = R.drawable.overseas_dialog_location;
            JDImageUtils.displayImage(areaImg, simpleDraweeView, jDDisplayImageOptions.showImageForEmptyUri(i7).showImageOnFail(i7).showImageOnLoading(i7));
            TextView textView = (TextView) linearLayout.findViewById(R.id.jd_dialog_title);
            textView.setPadding(0, -3, 0, -3);
            textView.setTextColor(-15066598);
            textView.setTextSize(0, DpiUtil.dip2px(textView.getContext(), 16.0f));
            textView.setText(jDOverseasModel.getAreaName());
            TextView textView2 = (TextView) linearLayout.findViewById(R.id.jd_dialog_message);
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            textView2.setPadding(-3, 0, -3, 0);
            setViewSize(textView2, 254, 50, null);
            textView2.setTextColor(-8355712);
            textView2.setTextSize(0, DpiUtil.dip2px(textView.getContext(), 12.0f));
            textView2.setText(jDOverseasModel.getAreaText());
            RelativeLayout relativeLayout = (RelativeLayout) linearLayout.findViewById(R.id.tips_container);
            setViewSize(relativeLayout, 251, 77, null);
            setViewSize(generateTipsTv(relativeLayout, i3, str4, jDOverseasModel.getAreaIconImg1(), jDOverseasModel.getAreaIconText1(), jDOverseasModel.getAreaColor()), 123, 36, new Rect(0, 0, 0, 0));
            setViewSize(generateTipsTv(relativeLayout, i2, str5, jDOverseasModel.getAreaIconImg2(), jDOverseasModel.getAreaIconText2(), jDOverseasModel.getAreaColor()), 123, 36, new Rect(128, 0, 0, 0));
            setViewSize(generateTipsTv(relativeLayout, i5, str2, jDOverseasModel.getAreaIconImg3(), jDOverseasModel.getAreaIconText3(), jDOverseasModel.getAreaColor()), 123, 36, new Rect(0, 41, 0, 0));
            setViewSize(generateTipsTv(relativeLayout, i6, str6, jDOverseasModel.getAreaIconImg4(), jDOverseasModel.getAreaIconText4(), jDOverseasModel.getAreaColor()), 123, 36, new Rect(128, 41, 0, 0));
            TextView textView3 = (TextView) linearLayout.findViewById(R.id.confirm_button);
            setViewSize(textView3, 207, 38, new Rect(0, 23, 0, 0));
            textView3.setTextSize(0, DpiUtil.dip2px(textView3.getContext(), 14.0f));
            textView3.setText("\u5207\u6362\u81f3" + jDOverseasModel.getAreaName() + "\u7ad9");
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.inter.JDOverseasDialogUtil.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDOverseasDialogUtil.dismissDialog(jDDialog);
                    JDOverseasDialogUtil.mtaClick(str, JDOverseasUtil.getCurrentOverseasArea(), jDOverseasModel.getAreaCode(), "Sitechange_Click");
                    int areaCode2 = jDOverseasModel.getAreaCode();
                    if (JDOverseasUtil.getCurrentOverseasArea() != areaCode2) {
                        JDOverseasUtil.updateCurrentOverseasArea(areaCode2);
                        JDOverseasDialogUtil.updateGlobalAddress(jDLocation, areaCode2);
                        baseOverseasListener.onOverseasChanged(jDOverseasModel);
                    }
                    JDOverseasDialogUtil.log("click change overseas code...");
                }
            });
            TextView textView4 = (TextView) linearLayout.findViewById(R.id.disagree);
            setViewSize(textView4, 120, 28, new Rect(0, 7, 0, 17));
            textView4.setTextSize(0, (float) DpiUtil.dip2px(textView4.getContext(), 11.0f));
            textView4.setText("\u6682\u4e0d\u5207\u6362");
            textView4.setTextColor(1719697536);
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.inter.JDOverseasDialogUtil.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDOverseasModel.addShowTimes();
                    JDOverseasDialogUtil.dismissDialog(jDDialog);
                    JDOverseasDialogUtil.mtaClick(str, JDOverseasUtil.getCurrentOverseasArea(), jDOverseasModel.getAreaCode(), "Sitechange_Close");
                    baseOverseasListener.onDialogClose();
                    JDOverseasDialogUtil.log("click close overseas dialog...");
                }
            });
            jDDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.common.utils.inter.JDOverseasDialogUtil.6
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    baseOverseasListener.onDialogDismiss();
                }
            });
            showDialog(jDDialog);
            mtaExpo(str, JDOverseasUtil.getCurrentOverseasArea(), jDOverseasModel.getAreaCode(), "Sitechange_Show");
            baseOverseasListener.onDialogShow();
            log("show overseas dialog...");
        }
        str6 = str3;
        final JDDialog jDDialog2 = new JDDialog(activity);
        jDDialog2.setContentView(R.layout.overseas_confirm_dialog);
        jDDialog2.setCanceledOnTouchOutside(false);
        LinearLayout linearLayout2 = (LinearLayout) jDDialog2.findViewById(R.id.rootView);
        setViewSize(linearLayout2, 295, -2, null);
        setViewSize((LinearLayout) linearLayout2.findViewById(R.id.title_container), -2, -2, new Rect(0, 24, 0, 0));
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) linearLayout2.findViewById(R.id.title_img);
        setViewSize(simpleDraweeView2, 16, 16, new Rect(0, 0, 2, 0));
        String areaImg2 = jDOverseasModel.getAreaImg();
        JDDisplayImageOptions jDDisplayImageOptions2 = new JDDisplayImageOptions();
        int i72 = R.drawable.overseas_dialog_location;
        JDImageUtils.displayImage(areaImg2, simpleDraweeView2, jDDisplayImageOptions2.showImageForEmptyUri(i72).showImageOnFail(i72).showImageOnLoading(i72));
        TextView textView5 = (TextView) linearLayout2.findViewById(R.id.jd_dialog_title);
        textView5.setPadding(0, -3, 0, -3);
        textView5.setTextColor(-15066598);
        textView5.setTextSize(0, DpiUtil.dip2px(textView5.getContext(), 16.0f));
        textView5.setText(jDOverseasModel.getAreaName());
        TextView textView22 = (TextView) linearLayout2.findViewById(R.id.jd_dialog_message);
        textView22.setMovementMethod(LinkMovementMethod.getInstance());
        textView22.setPadding(-3, 0, -3, 0);
        setViewSize(textView22, 254, 50, null);
        textView22.setTextColor(-8355712);
        textView22.setTextSize(0, DpiUtil.dip2px(textView5.getContext(), 12.0f));
        textView22.setText(jDOverseasModel.getAreaText());
        RelativeLayout relativeLayout2 = (RelativeLayout) linearLayout2.findViewById(R.id.tips_container);
        setViewSize(relativeLayout2, 251, 77, null);
        setViewSize(generateTipsTv(relativeLayout2, i3, str4, jDOverseasModel.getAreaIconImg1(), jDOverseasModel.getAreaIconText1(), jDOverseasModel.getAreaColor()), 123, 36, new Rect(0, 0, 0, 0));
        setViewSize(generateTipsTv(relativeLayout2, i2, str5, jDOverseasModel.getAreaIconImg2(), jDOverseasModel.getAreaIconText2(), jDOverseasModel.getAreaColor()), 123, 36, new Rect(128, 0, 0, 0));
        setViewSize(generateTipsTv(relativeLayout2, i5, str2, jDOverseasModel.getAreaIconImg3(), jDOverseasModel.getAreaIconText3(), jDOverseasModel.getAreaColor()), 123, 36, new Rect(0, 41, 0, 0));
        setViewSize(generateTipsTv(relativeLayout2, i6, str6, jDOverseasModel.getAreaIconImg4(), jDOverseasModel.getAreaIconText4(), jDOverseasModel.getAreaColor()), 123, 36, new Rect(128, 41, 0, 0));
        TextView textView32 = (TextView) linearLayout2.findViewById(R.id.confirm_button);
        setViewSize(textView32, 207, 38, new Rect(0, 23, 0, 0));
        textView32.setTextSize(0, DpiUtil.dip2px(textView32.getContext(), 14.0f));
        textView32.setText("\u5207\u6362\u81f3" + jDOverseasModel.getAreaName() + "\u7ad9");
        textView32.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.inter.JDOverseasDialogUtil.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDOverseasDialogUtil.dismissDialog(jDDialog2);
                JDOverseasDialogUtil.mtaClick(str, JDOverseasUtil.getCurrentOverseasArea(), jDOverseasModel.getAreaCode(), "Sitechange_Click");
                int areaCode2 = jDOverseasModel.getAreaCode();
                if (JDOverseasUtil.getCurrentOverseasArea() != areaCode2) {
                    JDOverseasUtil.updateCurrentOverseasArea(areaCode2);
                    JDOverseasDialogUtil.updateGlobalAddress(jDLocation, areaCode2);
                    baseOverseasListener.onOverseasChanged(jDOverseasModel);
                }
                JDOverseasDialogUtil.log("click change overseas code...");
            }
        });
        TextView textView42 = (TextView) linearLayout2.findViewById(R.id.disagree);
        setViewSize(textView42, 120, 28, new Rect(0, 7, 0, 17));
        textView42.setTextSize(0, (float) DpiUtil.dip2px(textView42.getContext(), 11.0f));
        textView42.setText("\u6682\u4e0d\u5207\u6362");
        textView42.setTextColor(1719697536);
        textView42.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.inter.JDOverseasDialogUtil.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDOverseasModel.addShowTimes();
                JDOverseasDialogUtil.dismissDialog(jDDialog2);
                JDOverseasDialogUtil.mtaClick(str, JDOverseasUtil.getCurrentOverseasArea(), jDOverseasModel.getAreaCode(), "Sitechange_Close");
                baseOverseasListener.onDialogClose();
                JDOverseasDialogUtil.log("click close overseas dialog...");
            }
        });
        jDDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.common.utils.inter.JDOverseasDialogUtil.6
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                baseOverseasListener.onDialogDismiss();
            }
        });
        showDialog(jDDialog2);
        mtaExpo(str, JDOverseasUtil.getCurrentOverseasArea(), jDOverseasModel.getAreaCode(), "Sitechange_Show");
        baseOverseasListener.onDialogShow();
        log("show overseas dialog...");
    }

    public static void unShowChangeDialog(final BaseOverseasListener baseOverseasListener) {
        sHandler.post(new Runnable() { // from class: com.jingdong.common.utils.inter.JDOverseasDialogUtil.2
            @Override // java.lang.Runnable
            public void run() {
                baseOverseasListener.unShowDialog();
            }
        });
    }

    public static void updateGlobalAddress(JDLocation jDLocation, int i2) {
        if (jDLocation == null) {
            return;
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.UPDATE_ADDRESS_DISABLED, false)) {
            log("update address disabled");
            return;
        }
        AddressGlobal addressGlobal = new AddressGlobal();
        addressGlobal.setIdProvince(jDLocation.getProvinceId());
        addressGlobal.setProvinceName(jDLocation.getProvinceName());
        addressGlobal.setIdCity(jDLocation.getCityId());
        addressGlobal.setCityName(jDLocation.getCityName());
        if (i2 == 0) {
            addressGlobal.setIdArea(jDLocation.getDistrictId());
            addressGlobal.setAreaName(jDLocation.getDistrictName());
            addressGlobal.setIdTown(jDLocation.getTownId());
            addressGlobal.setTownName(jDLocation.getTownName());
        } else {
            addressGlobal.setIdArea(0);
        }
        log(addressGlobal.toString(System.currentTimeMillis()));
        JDLbsHttpOption jDLbsHttpOption = new JDLbsHttpOption(OVERSEAS_LBS_ID);
        jDLbsHttpOption.setSceneId("basicShoppingProcess");
        JDGlobalAddressManager.updateAddressGlobal(jDLbsHttpOption, addressGlobal);
    }

    public static boolean requestOverseasInfo(final String str, final Activity activity, final JDLocation jDLocation, boolean z, final BaseOverseasListener baseOverseasListener) {
        if (activity == null || jDLocation == null) {
            return false;
        }
        if (z && sChecked.getAndSet(true)) {
            log("start only check once");
            return false;
        }
        if (baseOverseasListener == null) {
            baseOverseasListener = new BaseOverseasListener();
        }
        if (!JDOverseasModel.hasShowTimes()) {
            log("not have showTimes");
            return false;
        }
        log("request dialog info...");
        JDOverseasModel.requestDialogInfo(jDLocation, new JDOverseasModel.IOverseasListener() { // from class: com.jingdong.common.utils.inter.JDOverseasDialogUtil.3
            @Override // com.jingdong.common.utils.inter.JDOverseasModel.IOverseasListener
            public void showDialog(final JDOverseasModel jDOverseasModel) {
                JDOverseasDialogUtil.mtaExpo(str, JDOverseasUtil.getCurrentOverseasArea(), jDOverseasModel.getAreaCode(), "Sitechange_Diff");
                JDOverseasDialogUtil.sHandler.post(new Runnable() { // from class: com.jingdong.common.utils.inter.JDOverseasDialogUtil.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                        JDOverseasDialogUtil.showDialogView(str, activity, jDLocation, jDOverseasModel, baseOverseasListener);
                    }
                });
            }

            @Override // com.jingdong.common.utils.inter.JDOverseasModel.IOverseasListener
            public void unShowDialog() {
                JDOverseasDialogUtil.log("request dialog not show...");
                JDOverseasDialogUtil.unShowChangeDialog(baseOverseasListener);
            }
        });
        return true;
    }

    private static void mtaExpo(String str, boolean z, String str2) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("module", (Object) str);
        jDJSONObject.put("locright", (Object) (z ? "1" : "0"));
        mtaExpo(str2, jDJSONObject);
    }

    private static void mtaExpo(String str, JDJSONObject jDJSONObject) {
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str, "", "", "", "", jDJSONObject.toJSONString(), null);
    }
}
