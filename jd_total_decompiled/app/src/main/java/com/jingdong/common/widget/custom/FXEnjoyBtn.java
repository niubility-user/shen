package com.jingdong.common.widget.custom;

import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jingdong.common.R;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public final class FXEnjoyBtn extends LinearLayout implements View.OnClickListener {
    private IExtend iExtend;
    private EnjoyEntity mEnjoyEntity;
    private final ImageView mImg;
    private ToastEntity mToastEntity;

    /* renamed from: tv  reason: collision with root package name */
    private final TextView f12564tv;

    /* loaded from: classes12.dex */
    public static final class EnjoyEntity {
        private String contentId;
        private int hasEnjoy;
        private boolean hasEnjoyNum;
        private int initHasEnjoy;
        public String storeCountStr;
        private String subChannelId;

        public EnjoyEntity(int i2, String str, String str2) {
            this(i2, str, str2, false, null);
        }

        public EnjoyEntity(int i2, String str, String str2, boolean z, String str3) {
            this.hasEnjoy = i2;
            this.contentId = str;
            this.subChannelId = str2;
            this.hasEnjoyNum = z;
            this.storeCountStr = str3;
            this.initHasEnjoy = i2;
        }
    }

    /* loaded from: classes12.dex */
    public static final class EnjoyResultEntity {
        public int code;
        public String storeCountStr;
        public int subCode;
    }

    /* loaded from: classes12.dex */
    public interface IExtend {
        void onClick(int i2);
    }

    /* loaded from: classes12.dex */
    private static class LoginRunnable implements Runnable {
        private final FXEnjoyBtn fxEnjoyBtn;
        private final IExtend iExtend;
        private final EnjoyEntity mEnjoyEntity;

        public LoginRunnable(IExtend iExtend, EnjoyEntity enjoyEntity, FXEnjoyBtn fXEnjoyBtn) {
            this.iExtend = iExtend;
            this.mEnjoyEntity = enjoyEntity;
            this.fxEnjoyBtn = fXEnjoyBtn;
        }

        @Override // java.lang.Runnable
        public void run() {
            IExtend iExtend = this.iExtend;
            if (iExtend != null) {
                EnjoyEntity enjoyEntity = this.mEnjoyEntity;
                iExtend.onClick((enjoyEntity == null || enjoyEntity.hasEnjoy == 1) ? 0 : 1);
            }
            FXEnjoyBtn fXEnjoyBtn = this.fxEnjoyBtn;
            if (fXEnjoyBtn != null) {
                fXEnjoyBtn.storeContent(new ResultCallBack());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class ResultCallBack {
        private final FXEnjoyBtn fxEnjoyBtn;

        /* JADX INFO: Access modifiers changed from: private */
        public void onRepeat(final EnjoyResultEntity enjoyResultEntity) {
            FXEnjoyBtn fXEnjoyBtn = this.fxEnjoyBtn;
            if (fXEnjoyBtn == null) {
                return;
            }
            fXEnjoyBtn.post(new Runnable() { // from class: com.jingdong.common.widget.custom.FXEnjoyBtn.ResultCallBack.2
                @Override // java.lang.Runnable
                public void run() {
                    if (ResultCallBack.this.fxEnjoyBtn == null || ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity == null) {
                        return;
                    }
                    ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.hasEnjoy = ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.hasEnjoy == 0 ? 1 : 0;
                    ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.storeCountStr = enjoyResultEntity.storeCountStr;
                    ResultCallBack.this.fxEnjoyBtn.setData(ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity, ResultCallBack.this.fxEnjoyBtn.iExtend);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onSuccess(final EnjoyResultEntity enjoyResultEntity) {
            FXEnjoyBtn fXEnjoyBtn = this.fxEnjoyBtn;
            if (fXEnjoyBtn == null) {
                return;
            }
            fXEnjoyBtn.post(new Runnable() { // from class: com.jingdong.common.widget.custom.FXEnjoyBtn.ResultCallBack.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ResultCallBack.this.fxEnjoyBtn == null || ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity == null) {
                        return;
                    }
                    if (ResultCallBack.this.fxEnjoyBtn.mToastEntity != null) {
                        if (ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.hasEnjoy != 0 || !ResultCallBack.this.fxEnjoyBtn.mToastEntity.isShowAddSucceed) {
                            if (ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.hasEnjoy != 0 && ResultCallBack.this.fxEnjoyBtn.mToastEntity.isShowCancelSucceed) {
                                ToastUtils.shortToast(ResultCallBack.this.fxEnjoyBtn.getContext(), TextUtils.isEmpty(ResultCallBack.this.fxEnjoyBtn.mToastEntity.cancelSucceedText) ? "\u53d6\u6d88\u6210\u529f\uff01" : ResultCallBack.this.fxEnjoyBtn.mToastEntity.cancelSucceedText);
                            }
                        } else {
                            ToastUtils.shortToast(ResultCallBack.this.fxEnjoyBtn.getContext(), TextUtils.isEmpty(ResultCallBack.this.fxEnjoyBtn.mToastEntity.addSucceedText) ? "\u6210\u529f\u52a0\u5165\u559c\u6b22\u5217\u8868\uff01" : ResultCallBack.this.fxEnjoyBtn.mToastEntity.addSucceedText);
                        }
                    } else {
                        ToastUtils.shortToast(ResultCallBack.this.fxEnjoyBtn.getContext(), ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.hasEnjoy != 0 ? "\u53d6\u6d88\u6210\u529f\uff01" : "\u6210\u529f\u52a0\u5165\u559c\u6b22\u5217\u8868\uff01");
                    }
                    ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.hasEnjoy = ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.hasEnjoy == 0 ? 1 : 0;
                    ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.storeCountStr = enjoyResultEntity.storeCountStr;
                    ResultCallBack.this.fxEnjoyBtn.setData(ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity, ResultCallBack.this.fxEnjoyBtn.iExtend);
                }
            });
        }

        public void onFail() {
            FXEnjoyBtn fXEnjoyBtn = this.fxEnjoyBtn;
            if (fXEnjoyBtn == null) {
                return;
            }
            fXEnjoyBtn.post(new Runnable() { // from class: com.jingdong.common.widget.custom.FXEnjoyBtn.ResultCallBack.3
                @Override // java.lang.Runnable
                public void run() {
                    if (ResultCallBack.this.fxEnjoyBtn == null || ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity == null) {
                        return;
                    }
                    if (ResultCallBack.this.fxEnjoyBtn.mToastEntity != null) {
                        if (ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.hasEnjoy != 0 || !ResultCallBack.this.fxEnjoyBtn.mToastEntity.isShowAddFail) {
                            if (ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.hasEnjoy == 0 || !ResultCallBack.this.fxEnjoyBtn.mToastEntity.isShowCancelFail) {
                                return;
                            }
                            ToastUtils.shortToast(ResultCallBack.this.fxEnjoyBtn.getContext(), TextUtils.isEmpty(ResultCallBack.this.fxEnjoyBtn.mToastEntity.cancelFailText) ? "\u53d6\u6d88\u5931\u8d25~" : ResultCallBack.this.fxEnjoyBtn.mToastEntity.cancelFailText);
                            return;
                        }
                        ToastUtils.shortToast(ResultCallBack.this.fxEnjoyBtn.getContext(), TextUtils.isEmpty(ResultCallBack.this.fxEnjoyBtn.mToastEntity.addFailText) ? "\u52a0\u5165\u559c\u6b22\u5217\u8868\u5931\u8d25~" : ResultCallBack.this.fxEnjoyBtn.mToastEntity.addFailText);
                        return;
                    }
                    ToastUtils.shortToast(ResultCallBack.this.fxEnjoyBtn.getContext(), ResultCallBack.this.fxEnjoyBtn.mEnjoyEntity.hasEnjoy != 0 ? "\u53d6\u6d88\u5931\u8d25~" : "\u52a0\u5165\u559c\u6b22\u5217\u8868\u5931\u8d25~");
                }
            });
        }

        private ResultCallBack(FXEnjoyBtn fXEnjoyBtn) {
            this.fxEnjoyBtn = fXEnjoyBtn;
        }
    }

    /* loaded from: classes12.dex */
    public static class ToastEntity {
        public String addFailText;
        public String addSucceedText;
        public String cancelFailText;
        public String cancelSucceedText;
        public boolean isShowAddFail;
        public boolean isShowAddSucceed;
        public boolean isShowCancelFail;
        public boolean isShowCancelSucceed;

        public ToastEntity(boolean z, boolean z2, String str, String str2, boolean z3, boolean z4, String str3, String str4) {
            this.isShowAddSucceed = z;
            this.isShowAddFail = z2;
            this.addSucceedText = str;
            this.addFailText = str2;
            this.isShowCancelSucceed = z3;
            this.isShowCancelFail = z4;
            this.cancelSucceedText = str3;
            this.cancelFailText = str4;
        }
    }

    public FXEnjoyBtn(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void storeContent(final ResultCallBack resultCallBack) {
        if (this.mEnjoyEntity == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("storeContent");
        httpSetting.putJsonParam("subChannelId", this.mEnjoyEntity.subChannelId);
        httpSetting.putJsonParam("contentId", this.mEnjoyEntity.contentId);
        httpSetting.putJsonParam("operate", Integer.valueOf(this.mEnjoyEntity.hasEnjoy == 0 ? 1 : 0));
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setAttempts(1);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        httpSetting.setListener(new HttpGroup.OnCommonNewListener<EnjoyResultEntity>() { // from class: com.jingdong.common.widget.custom.FXEnjoyBtn.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                ResultCallBack resultCallBack2 = resultCallBack;
                if (resultCallBack2 == null) {
                    return;
                }
                resultCallBack2.onFail();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnJsonResponseEndLisener
            public void onEnd(HttpResponse httpResponse, EnjoyResultEntity enjoyResultEntity) {
                ResultCallBack resultCallBack2 = resultCallBack;
                if (resultCallBack2 == null) {
                    return;
                }
                if (enjoyResultEntity == null) {
                    onError(null);
                } else if (enjoyResultEntity.code == 0) {
                    int i2 = enjoyResultEntity.subCode;
                    if (i2 == 1) {
                        resultCallBack2.onSuccess(enjoyResultEntity);
                    } else if (i2 == 2) {
                        resultCallBack2.onRepeat(enjoyResultEntity);
                    } else {
                        onError(null);
                    }
                } else {
                    onError(null);
                }
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public boolean getInitHasEnjoy() {
        EnjoyEntity enjoyEntity = this.mEnjoyEntity;
        return enjoyEntity != null && enjoyEntity.initHasEnjoy == 1;
    }

    public boolean hasChanged() {
        EnjoyEntity enjoyEntity = this.mEnjoyEntity;
        return (enjoyEntity == null || enjoyEntity.initHasEnjoy == this.mEnjoyEntity.hasEnjoy) ? false : true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getContext() instanceof IMyActivity) {
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) view.getContext(), new LoginRunnable(this.iExtend, this.mEnjoyEntity, this));
        }
    }

    public void setData(EnjoyEntity enjoyEntity, ToastEntity toastEntity, IExtend iExtend) {
        setData(enjoyEntity, iExtend);
        this.mToastEntity = toastEntity;
    }

    public FXEnjoyBtn(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        ImageView imageView = new ImageView(context);
        this.mImg = imageView;
        imageView.setLayoutParams(new LinearLayout.LayoutParams(DPIUtil.dip2px(19.0f), DPIUtil.dip2px(17.0f)));
        TextView textView = new TextView(context);
        this.f12564tv = textView;
        textView.setVisibility(8);
        textView.setTextSize(1, 10.0f);
        textView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = DPIUtil.dip2px(4.5f);
        textView.setLayoutParams(layoutParams);
        setGravity(17);
        addView(imageView);
        addView(textView);
        setOnClickListener(this);
    }

    public void setData(EnjoyEntity enjoyEntity, IExtend iExtend) {
        if (enjoyEntity == null) {
            return;
        }
        this.iExtend = iExtend;
        this.mEnjoyEntity = enjoyEntity;
        ImageView imageView = this.mImg;
        if (imageView == null) {
            return;
        }
        imageView.setImageResource(enjoyEntity.hasEnjoy == 1 ? R.drawable.fx_enjoyed : R.drawable.fx_enjoy);
        if (this.mEnjoyEntity.hasEnjoyNum) {
            this.f12564tv.setVisibility(0);
            this.f12564tv.setTextColor(Color.parseColor(enjoyEntity.hasEnjoy == 1 ? "#fb2020" : "#828282"));
            this.f12564tv.setText(this.mEnjoyEntity.storeCountStr);
        }
    }
}
