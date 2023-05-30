package com.babel.coupon.view;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.babel.coupon.R;
import com.babel.coupon.bean.BenefitData;
import com.babel.coupon.bean.Body;
import com.babel.coupon.bean.QryParam;
import com.babel.coupon.entity.TextLayoutConfig;
import com.babel.coupon.entity.TextLayoutModel;
import com.jd.framework.json.JDJSON;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jingdong.common.jdmiaosha.preload.DoubleTabDataPreloadUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TextLayout extends FrameLayout implements IFloorView<TextLayoutModel> {
    public static final String COUPON_DEFAULT_IMG = "https://m.360buyimg.com/mobilecal/jfs/t1/142808/31/21349/11309/614c4b27Ef4a51c0c/67dee35e802ba166.png";
    public static final String TAG = TextLayout.class.getSimpleName();
    public BabelScope babelScope;
    public String cacheData;
    public Context context;
    private RelativeLayout couponCover;
    private ImageWraper couponImg;
    private TextView couponStatus;
    private TextView couponTimer;
    private RelativeLayout coupon_child;
    private TextView discountAmount;
    public TextLayoutModel entity;
    private boolean isRequestTrue;
    private CountDownTimer timer;

    public TextLayout(Context context) {
        super(context);
        this.cacheData = "";
        this.context = context;
        this.isRequestTrue = false;
        this.cacheData = "";
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LayoutInflater.from(this.context).inflate(R.layout.coupon_layout, this);
        this.coupon_child = (RelativeLayout) findViewById(R.id.coupon_child);
        this.discountAmount = (TextView) findViewById(R.id.coupon_price_num);
        this.couponTimer = (TextView) findViewById(R.id.coupon_timer);
        this.couponCover = (RelativeLayout) findViewById(R.id.coupon_cover);
        this.couponStatus = (TextView) findViewById(R.id.coupon_status);
        this.couponImg = (ImageWraper) findViewById(R.id.coupon_img);
        this.coupon_child.setVisibility(4);
    }

    public void renderData(String str) {
        BenefitData benefitData = (BenefitData) JDJSON.parseObject(str, BenefitData.class);
        if ("0".equals(benefitData.getCode()) && benefitData.getData() != null && benefitData.getData().getBenefitReceiveResult() != null && benefitData.getData().getBenefitReceiveResult().getCode().intValue() == 0 && benefitData.getData().getBenefitReceiveResult().getBenefitReceiveVO() != null && benefitData.getData().getBenefitReceiveResult().getBenefitReceiveVO().code.intValue() == 200) {
            BenefitData.DataDTO.BenefitReceiveResultDTO.BenefitReceiveVODTO benefitReceiveVO = benefitData.getData().getBenefitReceiveResult().getBenefitReceiveVO();
            final String discountAmount = benefitReceiveVO.getDiscountAmount();
            final int intValue = benefitReceiveVO.getDiscountTtl().intValue() * 1000;
            if (!TextUtils.isEmpty(discountAmount) && intValue > 0) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("aid", this.babelScope.getPageName());
                    jSONObject.put("mid", this.entity.mid);
                    jSONObject.put("fno", this.entity.fno);
                    jSONObject.put("cid", "-100");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                CommonServiceUtil.sendExposureData(this.babelScope, "Babel_InnerCouponExpo", jSONObject.toString());
                this.coupon_child.post(new Runnable() { // from class: com.babel.coupon.view.TextLayout.2
                    @Override // java.lang.Runnable
                    public void run() {
                        TextLayout.this.coupon_child.setVisibility(0);
                        TextLayout.this.discountAmount.setText(discountAmount);
                        if (TextLayout.this.timer != null) {
                            TextLayout.this.timer.cancel();
                        }
                        TextLayout.this.timer = new CountDownTimer(intValue, 1000L) { // from class: com.babel.coupon.view.TextLayout.2.1
                            @Override // android.os.CountDownTimer
                            public void onFinish() {
                                TextLayout.this.couponCover.setVisibility(0);
                                TextLayout.this.couponTimer.setVisibility(8);
                                TextLayout.this.couponStatus.setText("\u5df2\u5931\u6548");
                                TextLayout.this.timer = null;
                            }

                            @Override // android.os.CountDownTimer
                            public void onTick(long j2) {
                                StringBuilder sb;
                                StringBuilder sb2;
                                String str2;
                                long j3 = j2 / 3600000;
                                long j4 = j2 - (((j3 * 1000) * 60) * 60);
                                long j5 = j4 / 60000;
                                long j6 = (j4 - ((j5 * 1000) * 60)) / 1000;
                                if (j3 < 10) {
                                    sb = new StringBuilder();
                                    sb.append("0");
                                    sb.append(j3);
                                } else {
                                    sb = new StringBuilder();
                                    sb.append(j3);
                                    sb.append("");
                                }
                                String sb3 = sb.toString();
                                if (j5 < 10) {
                                    sb2 = new StringBuilder();
                                    sb2.append("0");
                                    sb2.append(j5);
                                } else {
                                    sb2 = new StringBuilder();
                                    sb2.append(j5);
                                    sb2.append("");
                                }
                                String sb4 = sb2.toString();
                                if (j6 < 10) {
                                    str2 = "0" + j6;
                                } else {
                                    str2 = j6 + "";
                                }
                                TextLayout.this.couponTimer.setText(String.format(Locale.getDefault(), "%s:%s:%s\u540e\u5931\u6548", sb3, sb4, str2));
                            }
                        };
                        TextLayout.this.timer.start();
                    }
                });
                return;
            }
            viewDismiss();
            return;
        }
        viewDismiss();
    }

    public void viewDismiss() {
        this.coupon_child.post(new Runnable() { // from class: com.babel.coupon.view.TextLayout.1
            @Override // java.lang.Runnable
            public void run() {
                ViewGroup.LayoutParams layoutParams = TextLayout.this.coupon_child.getLayoutParams();
                layoutParams.height = 1;
                TextLayout.this.coupon_child.setLayoutParams(layoutParams);
                TextLayout.this.coupon_child.setVisibility(4);
            }
        });
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void update(@NonNull BabelScope babelScope, @NonNull TextLayoutModel textLayoutModel, int i2) {
        this.babelScope = babelScope;
        this.entity = textLayoutModel;
        TextLayoutConfig textLayoutConfig = textLayoutModel.boardParams;
        String str = textLayoutConfig.bgColor;
        String str2 = textLayoutConfig.pic;
        if (str != null) {
            try {
                this.coupon_child.setBackgroundColor(Color.parseColor(str));
            } catch (Exception e2) {
                String str3 = "err=====>" + e2.getMessage();
                viewDismiss();
                return;
            }
        }
        this.couponImg.setScaleType(ImageView.ScaleType.FIT_XY);
        if (str2 != null) {
            CommonServiceUtil.displayImage(str2, this.couponImg);
        } else {
            CommonServiceUtil.displayImage(COUPON_DEFAULT_IMG, this.couponImg);
        }
        if (this.isRequestTrue && !TextUtils.isEmpty(this.cacheData)) {
            renderData(this.cacheData);
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(DoubleTabDataPreloadUtil.FUNCTION_ID);
        httpSetting.setHost("api.m.jd.com");
        Body body = new Body();
        body.setApplyKey("people_red_bag");
        ArrayList arrayList = new ArrayList();
        QryParam qryParam = new QryParam();
        qryParam.setType("benefitReceive");
        qryParam.setMapTo("benefitReceiveResult");
        qryParam.setDiscountType("3");
        arrayList.add(qryParam);
        body.setQryParam(JDJSON.toJSONString(arrayList));
        httpSetting.putJsonParam(body);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.babel.coupon.view.TextLayout.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                TextLayout.this.isRequestTrue = true;
                TextLayout.this.cacheData = httpResponse.getString();
                TextLayout textLayout = TextLayout.this;
                textLayout.renderData(textLayout.cacheData);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                TextLayout.this.viewDismiss();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
