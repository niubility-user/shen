package com.jd.lib.productdetail.tradein.result;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPaiPaiReplacement;
import com.jd.lib.productdetail.core.iconfont.PDIconFontUtil;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.views.CustomTypefaceSpan;
import com.jd.lib.productdetail.core.views.VerticalImageSpan;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInAddressInfo;
import com.jd.lib.productdetail.tradein.TradeInButtonType;
import com.jd.lib.productdetail.tradein.TradeInDialogFragment;
import com.jd.lib.productdetail.tradein.TradeInOpenLayerFrom;
import com.jd.lib.productdetail.tradein.TradeInStep;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.inform.TradeInInformData;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.result.TradeInResultFragment;
import com.jd.lib.productdetail.tradein.result.TradeInResultOldDeviceCard;
import com.jd.lib.productdetail.tradein.result.TradeInSaveIdData;
import com.jd.lib.productdetail.tradein.utils.TradeInConstants;
import com.jd.lib.productdetail.tradein.widget.TradeInScrollView;
import com.jd.lib.productdetail.tradein.widget.TradeInTitle;
import com.jd.lib.productdetail.tradein.widget.TradeinErrorView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.wireless.iconfont.IconDrawable;
import com.jingdong.wireless.iconfont.widget.IconImpl;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public class TradeInResultFragment extends Fragment {
    public LinearLayout A;
    public TextView B;
    public TextView C;
    public TextView D;
    public Drawable E;
    public Drawable F;
    public boolean G;
    public TradeInResultData H;
    public TradeInResultData.BatterFreeFa I;
    public TradeInResultData.TradInBarterCardE J;
    public View K;
    public TradeInResultOldDeviceCard.f L;
    public boolean M;

    /* renamed from: g  reason: collision with root package name */
    public TradeInDialogFragment f5398g;

    /* renamed from: h  reason: collision with root package name */
    public TradeInViewModel f5399h;

    /* renamed from: i  reason: collision with root package name */
    public View f5400i;

    /* renamed from: j  reason: collision with root package name */
    public TradeinErrorView f5401j;

    /* renamed from: k  reason: collision with root package name */
    public TradeInScrollView f5402k;

    /* renamed from: l  reason: collision with root package name */
    public ConstraintLayout f5403l;

    /* renamed from: m  reason: collision with root package name */
    public TradeInTitle f5404m;

    /* renamed from: n  reason: collision with root package name */
    public TradeInResultNewDeviceCard f5405n;
    public ConstraintLayout o;
    public TradeInResultOldDeviceCard p;
    public TradeInResultOldDeviceCard q;
    public TradeInResultSubsidyView r;
    public TextView s;
    public TextView t;
    public View u;
    public TextView v;
    public ImageView w;
    public TextView x;
    public TextView y;
    public TextView z;

    /* loaded from: classes16.dex */
    public class a implements Observer<PdBaseProtocolLiveData.Result<TradeInSaveIdData.Data>> {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ TradeInResultData.BarterButtonInfo f5406g;

        public a(TradeInResultData.BarterButtonInfo barterButtonInfo) {
            this.f5406g = barterButtonInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(JDDialog jDDialog, View view) {
            try {
                jDDialog.dismiss();
                TradeInResultFragment tradeInResultFragment = TradeInResultFragment.this;
                TradeInResultData tradeInResultData = tradeInResultFragment.H;
                if (tradeInResultData != null) {
                    tradeInResultData.mHasCheckedOldDevice = true;
                }
                tradeInResultFragment.C.performClick();
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(JDDialog jDDialog, View view) {
            try {
                jDDialog.dismiss();
                TradeInResultData tradeInResultData = TradeInResultFragment.this.H;
                if (tradeInResultData != null) {
                    tradeInResultData.mHasCheckedOldDevice = true;
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInSaveIdData.Data> result) {
            TradeInSaveIdData.Data data;
            TradeInSaveIdData.Data data2;
            TradeInResultData.BarterButtonInfo.BarterButton barterButton;
            if (result != null) {
                try {
                    PdBaseProtocolLiveData.Result.DataStatus dataStatus = result.mStatus;
                    if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS && TradeInResultFragment.this.f5399h != null && (data2 = result.mData) != null) {
                        if (data2.isValid()) {
                            TradeInResultData.BarterButtonInfo barterButtonInfo = this.f5406g;
                            if (barterButtonInfo != null && (barterButton = barterButtonInfo.second) != null && !TextUtils.isEmpty(barterButton.url)) {
                                PDBaseDeepLinkHelper.gotoMWithUrl(TradeInResultFragment.this.getContext(), this.f5406g.second.url.replace("%s", result.mData.qfId));
                                JsonObject jsonObject = new JsonObject();
                                jsonObject.addProperty("qualification_id", result.mData.qfId);
                                TradeInSaveIdData.Data data3 = result.mData;
                                if (data3.mMtaData != null) {
                                    jsonObject.addProperty("Ttransaction_Ways", data3.mMtaData.Ttransaction_Ways);
                                    jsonObject.addProperty("product_id", result.mData.mMtaData.product_id);
                                    jsonObject.addProperty("PaiPaiTrans_Ways", result.mData.mMtaData.PaiPaiTrans_Ways);
                                    jsonObject.addProperty("PaiPaiProduct_id", result.mData.mMtaData.PaiPaiProduct_id);
                                    try {
                                        if (TradeInResultFragment.this.f5399h.a != null) {
                                            JsonArray jsonArray = new JsonArray();
                                            for (int i2 = 0; i2 < TradeInResultFragment.this.f5399h.a.size(); i2++) {
                                                jsonArray.add(TradeInResultFragment.this.f5399h.a.get(i2));
                                            }
                                            jsonObject.add("touchstone_expids", jsonArray);
                                        } else {
                                            jsonObject.add("touchstone_expids", null);
                                        }
                                    } catch (Exception e2) {
                                        ExceptionReporter.reportExceptionToBugly(e2);
                                    }
                                }
                                TradeInResultFragment.this.f5399h.e("Productdetail_yjhxChangeOld", jsonObject);
                                TradeInResultFragment.this.f5398g.dismiss();
                                return;
                            }
                            TradeInViewModel tradeInViewModel = TradeInResultFragment.this.f5399h;
                            result.mData.toastBiz = WareBusinessPaiPaiReplacement.getToastBiz(tradeInViewModel.f5255g, tradeInViewModel.f5256h);
                            TradeInSaveIdData.Data data4 = result.mData;
                            TradeInSaveIdData.Data data5 = data4;
                            TradeInResultFragment tradeInResultFragment = TradeInResultFragment.this;
                            TradeInViewModel tradeInViewModel2 = tradeInResultFragment.f5399h;
                            TradeInOpenLayerFrom tradeInOpenLayerFrom = tradeInViewModel2.f5254f;
                            data5.toastEntrance = tradeInOpenLayerFrom != null ? tradeInOpenLayerFrom.from : "";
                            data4.toastYJHXSource = tradeInViewModel2.f5261m == TradeInButtonType.DOUBLE ? "1" : "2";
                            tradeInResultFragment.v(data4);
                            return;
                        }
                        return;
                    } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                        try {
                            if (TradeInResultFragment.this.getContext() != null) {
                                if (TradeInResultFragment.this.f5399h != null && (data = result.mData) != null && !TextUtils.isEmpty(data.popupText)) {
                                    TradeInSaveIdData.Data data6 = result.mData;
                                    if (data6.code == 700001 || data6.code == 700002) {
                                        final JDDialog b = TradeInResultFragment.this.b();
                                        b.titleView.setText(result.mData.popupText);
                                        b.negButton.setText(TradeInResultFragment.this.getResources().getString(R.string.tradein_result_confirm_ok));
                                        b.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.e
                                            @Override // android.view.View.OnClickListener
                                            public final void onClick(View view) {
                                                TradeInResultFragment.a.this.b(b, view);
                                            }
                                        });
                                        b.posButton.setText(TradeInResultFragment.this.getResources().getString(R.string.tradein_result_confirm_cancel));
                                        b.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.d
                                            @Override // android.view.View.OnClickListener
                                            public final void onClick(View view) {
                                                TradeInResultFragment.a.this.c(b, view);
                                            }
                                        });
                                        b.show();
                                        return;
                                    }
                                }
                                String string = TradeInResultFragment.this.getContext().getString(R.string.tradein_save_id_fail);
                                TradeInSaveIdData.Data data7 = result.mData;
                                if (data7 != null && !TextUtils.isEmpty(data7.msg)) {
                                    string = result.mData.msg;
                                }
                                PDUtils.showToastCenterIcon(TradeInResultFragment.this.getContext(), (byte) 3, string);
                                return;
                            }
                            return;
                        } catch (Exception unused) {
                            PDUtils.showToastCenterNormal(TradeInResultFragment.this.getContext(), TradeInResultFragment.this.getString(R.string.tradein_save_id_fail));
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Exception e3) {
                    ExceptionReporter.reportExceptionToBugly(e3);
                }
                ExceptionReporter.reportExceptionToBugly(e3);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class b implements Observer<PdBaseProtocolLiveData.Result<TradeInSaveIdData.Data>> {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ TradeInResultData.BarterButtonInfo f5408g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ boolean f5409h;

        public b(TradeInResultData.BarterButtonInfo barterButtonInfo, boolean z) {
            this.f5408g = barterButtonInfo;
            this.f5409h = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(JDDialog jDDialog, View view) {
            try {
                jDDialog.dismiss();
                TradeInResultFragment tradeInResultFragment = TradeInResultFragment.this;
                TradeInResultData tradeInResultData = tradeInResultFragment.H;
                if (tradeInResultData != null) {
                    tradeInResultData.mHasCheckedOldDevice = true;
                }
                tradeInResultFragment.B.performClick();
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(JDDialog jDDialog, View view) {
            try {
                jDDialog.dismiss();
                TradeInResultData tradeInResultData = TradeInResultFragment.this.H;
                if (tradeInResultData != null) {
                    tradeInResultData.mHasCheckedOldDevice = true;
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInSaveIdData.Data> result) {
            TradeInSaveIdData.Data data;
            TradeInSaveIdData.Data data2;
            TradeInResultData.BarterButtonInfo.BarterButton barterButton;
            if (result != null) {
                try {
                    PdBaseProtocolLiveData.Result.DataStatus dataStatus = result.mStatus;
                    if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS && TradeInResultFragment.this.f5399h != null && (data2 = result.mData) != null) {
                        if (data2.isValid()) {
                            TradeInViewModel tradeInViewModel = TradeInResultFragment.this.f5399h;
                            result.mData.toastBiz = WareBusinessPaiPaiReplacement.getToastBiz(tradeInViewModel.f5255g, tradeInViewModel.f5256h);
                            TradeInSaveIdData.Data data3 = result.mData;
                            TradeInSaveIdData.Data data4 = data3;
                            TradeInResultFragment tradeInResultFragment = TradeInResultFragment.this;
                            TradeInViewModel tradeInViewModel2 = tradeInResultFragment.f5399h;
                            TradeInOpenLayerFrom tradeInOpenLayerFrom = tradeInViewModel2.f5254f;
                            data4.toastEntrance = tradeInOpenLayerFrom != null ? tradeInOpenLayerFrom.from : "";
                            TradeInSaveIdData.Data data5 = data3;
                            TradeInButtonType tradeInButtonType = tradeInViewModel2.f5261m;
                            data5.toastYJHXSource = tradeInButtonType == TradeInButtonType.DOUBLE ? "1" : "2";
                            if (tradeInButtonType == TradeInButtonType.DEFAULT) {
                                tradeInResultFragment.v(data3);
                                return;
                            }
                            TradeInResultData.BarterButtonInfo barterButtonInfo = this.f5408g;
                            if (barterButtonInfo != null && (barterButton = barterButtonInfo.main) != null && !TextUtils.isEmpty(barterButton.url)) {
                                PDBaseDeepLinkHelper.gotoMWithUrl(TradeInResultFragment.this.getContext(), this.f5408g.main.url.replace("%s", result.mData.qfId));
                                TradeInResultFragment.this.f5398g.dismiss();
                                JsonObject jsonObject = new JsonObject();
                                jsonObject.addProperty("qualification_id", result.mData.qfId);
                                TradeInSaveIdData.Data data6 = result.mData;
                                if (data6.mMtaData != null) {
                                    jsonObject.addProperty("Ttransaction_Ways", data6.mMtaData.Ttransaction_Ways);
                                    jsonObject.addProperty("product_id", result.mData.mMtaData.product_id);
                                    jsonObject.addProperty("PaiPaiTrans_Ways", result.mData.mMtaData.PaiPaiTrans_Ways);
                                    jsonObject.addProperty("PaiPaiProduct_id", result.mData.mMtaData.PaiPaiProduct_id);
                                    try {
                                        TradeInViewModel tradeInViewModel3 = TradeInResultFragment.this.f5399h;
                                        if (tradeInViewModel3 != null && tradeInViewModel3.a != null) {
                                            JsonArray jsonArray = new JsonArray();
                                            for (int i2 = 0; i2 < TradeInResultFragment.this.f5399h.a.size(); i2++) {
                                                jsonArray.add(TradeInResultFragment.this.f5399h.a.get(i2));
                                            }
                                            jsonObject.add("touchstone_expids", jsonArray);
                                        } else {
                                            jsonObject.add("touchstone_expids", null);
                                        }
                                    } catch (Exception e2) {
                                        ExceptionReporter.reportExceptionToBugly(e2);
                                    }
                                }
                                TradeInResultFragment.this.f5399h.e("Productdetail_yjhxChangeOld", jsonObject);
                                return;
                            } else if (this.f5409h) {
                                TradeInResultFragment.this.v(result.mData);
                                return;
                            } else {
                                TradeInResultFragment.this.j(result.mData);
                                return;
                            }
                        }
                        return;
                    } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                        try {
                            if (TradeInResultFragment.this.getContext() != null) {
                                if (TradeInResultFragment.this.f5399h != null && (data = result.mData) != null && !TextUtils.isEmpty(data.popupText)) {
                                    TradeInSaveIdData.Data data7 = result.mData;
                                    if (data7.code == 700001 || data7.code == 700002) {
                                        final JDDialog b = TradeInResultFragment.this.b();
                                        b.titleView.setText(result.mData.popupText);
                                        b.negButton.setText(TradeInResultFragment.this.getResources().getString(R.string.tradein_result_confirm_ok));
                                        b.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.g
                                            @Override // android.view.View.OnClickListener
                                            public final void onClick(View view) {
                                                TradeInResultFragment.b.this.b(b, view);
                                            }
                                        });
                                        b.posButton.setText(TradeInResultFragment.this.getResources().getString(R.string.tradein_result_confirm_cancel));
                                        b.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.f
                                            @Override // android.view.View.OnClickListener
                                            public final void onClick(View view) {
                                                TradeInResultFragment.b.this.c(b, view);
                                            }
                                        });
                                        b.show();
                                        return;
                                    }
                                }
                                String string = TradeInResultFragment.this.getContext().getString(R.string.tradein_save_id_fail);
                                TradeInSaveIdData.Data data8 = result.mData;
                                if (data8 != null && !TextUtils.isEmpty(data8.msg)) {
                                    string = result.mData.msg;
                                }
                                PDUtils.showToastCenterIcon(TradeInResultFragment.this.getContext(), (byte) 3, string);
                                return;
                            }
                            return;
                        } catch (Exception unused) {
                            PDUtils.showToastCenterNormal(TradeInResultFragment.this.getContext(), TradeInResultFragment.this.getString(R.string.tradein_save_id_fail));
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Exception e3) {
                    ExceptionReporter.reportExceptionToBugly(e3);
                }
                ExceptionReporter.reportExceptionToBugly(e3);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInResultFragment.this.m(false);
        }
    }

    /* loaded from: classes16.dex */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInViewModel tradeInViewModel = TradeInResultFragment.this.f5399h;
            if (tradeInViewModel != null) {
                tradeInViewModel.e("Productdetail_yjhxChangeRules", null);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("extra.key.from.tradein.page", true);
            TradeInResultFragment.this.f5398g.moveToStep(TradeInStep.SELECT_OLD_DEVICE, bundle);
            TradeInViewModel tradeInViewModel = TradeInResultFragment.this.f5399h;
            if (tradeInViewModel != null) {
                tradeInViewModel.e("Productdetail_yjhxAddtoOld", null);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TradeInResultFragment.this.J != null) {
                com.jd.lib.productdetail.tradein.l.e eVar = new com.jd.lib.productdetail.tradein.l.e(TradeInResultFragment.this.getContext());
                eVar.a(TradeInResultFragment.this.J);
                eVar.show();
            }
        }
    }

    /* loaded from: classes16.dex */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInResultFragment.this.G = !r3.G;
            TradeInResultFragment.this.s();
            TradeInViewModel tradeInViewModel = TradeInResultFragment.this.f5399h;
            if (tradeInViewModel != null) {
                tradeInViewModel.e("Productdetail_yjhxToastAgree", null);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class h implements TradeInScrollView.a {
        public h() {
        }
    }

    /* loaded from: classes16.dex */
    public class i implements Observer<PdBaseProtocolLiveData.Result<TradeInResultData>> {
        public i() {
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInResultData> result) {
            PdBaseProtocolLiveData.Result.DataStatus dataStatus;
            TradeInViewModel tradeInViewModel;
            int i2;
            int length;
            PdBaseProtocolLiveData.Result<TradeInResultData> result2 = result;
            StringBuilder sb = new StringBuilder();
            sb.append("mResultInfoLiveData onChanged result = ");
            sb.append(result2 != null ? result2.mStatus : " null");
            sb.toString();
            if (result2 == null || (dataStatus = result2.mStatus) == PdBaseProtocolLiveData.Result.DataStatus.FETCHING) {
                return;
            }
            if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                TradeInResultFragment.this.K.setVisibility(8);
                TradeInResultData tradeInResultData = result2.mData;
                if (tradeInResultData != null && tradeInResultData.isValid()) {
                    TradeInResultFragment.this.o(false, false);
                    TradeInViewModel tradeInViewModel2 = TradeInResultFragment.this.f5399h;
                    if (tradeInViewModel2 != null) {
                        tradeInViewModel2.g();
                    }
                    TradeInResultFragment tradeInResultFragment = TradeInResultFragment.this;
                    TradeInResultData tradeInResultData2 = result2.mData;
                    tradeInResultFragment.H = tradeInResultData2;
                    tradeInResultFragment.I = tradeInResultData2.freeFa;
                    tradeInResultFragment.J = tradeInResultData2.eCard;
                    tradeInResultFragment.f5405n.f(tradeInResultData2.abTouchStones);
                    tradeInResultFragment.f5404m.b(tradeInResultData2.ruleInfo);
                    tradeInResultFragment.f5405n.g(tradeInResultData2.wareNew);
                    tradeInResultFragment.n(false, null);
                    if (!TextUtils.isEmpty(tradeInResultData2.tradeTypeRefreshToast)) {
                        PDUtils.showToastCenterNormal(tradeInResultFragment.getContext(), tradeInResultData2.tradeTypeRefreshToast);
                        tradeInResultData2.tradeTypeRefreshToast = null;
                    }
                    TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = tradeInResultData2.oldWare1;
                    if (tradeInWareCardInfo != null && tradeInWareCardInfo.isValid()) {
                        tradeInResultFragment.p.setVisibility(0);
                        tradeInResultFragment.p.a(tradeInResultData2.oldWare1);
                        tradeInResultFragment.p.x = tradeInResultFragment.L;
                    } else {
                        tradeInResultFragment.p.setVisibility(8);
                        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo2 = tradeInResultData2.oldWare2;
                        if (tradeInWareCardInfo2 == null || !tradeInWareCardInfo2.isValid()) {
                            tradeInResultFragment.n(true, tradeInResultData2);
                            return;
                        }
                    }
                    TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo3 = tradeInResultData2.oldWare2;
                    if (tradeInWareCardInfo3 != null && tradeInWareCardInfo3.isValid()) {
                        tradeInResultFragment.q.setVisibility(0);
                        tradeInResultFragment.q.a(tradeInResultData2.oldWare2);
                        tradeInResultFragment.q.x = tradeInResultFragment.L;
                    } else {
                        tradeInResultFragment.q.setVisibility(8);
                    }
                    if (tradeInResultData2.oldWareLimit > 0) {
                        tradeInResultFragment.A.setVisibility(0);
                        if (tradeInResultData2.getOldDeviceCount() < tradeInResultData2.oldWareLimit) {
                            tradeInResultFragment.y.setEnabled(true);
                        } else {
                            tradeInResultFragment.y.setEnabled(false);
                        }
                        tradeInResultFragment.y.setText(tradeInResultFragment.getString(R.string.tradein_result_btn_add_your_device, Integer.valueOf(tradeInResultData2.getOldDeviceCount()), Integer.valueOf(tradeInResultData2.oldWareLimit)));
                    } else {
                        tradeInResultFragment.A.setVisibility(8);
                    }
                    TradeInResultData.TradInBarterCardE tradInBarterCardE = tradeInResultFragment.J;
                    if (tradInBarterCardE != null && !TextUtils.isEmpty(tradInBarterCardE.floorContent)) {
                        tradeInResultFragment.z.setText(tradeInResultFragment.c(tradeInResultFragment.J.floorContent));
                        tradeInResultFragment.z.setVisibility(0);
                    } else {
                        tradeInResultFragment.z.setVisibility(8);
                    }
                    tradeInResultFragment.r.g(tradeInResultData2);
                    TradeInResultData.TradeInFloorData tradeInFloorData = tradeInResultData2.accountStatement;
                    if (tradeInFloorData != null && tradeInFloorData.rightInfo != null) {
                        tradeInResultFragment.s.setVisibility(8);
                        tradeInResultFragment.t.setVisibility(8);
                        if (!TextUtils.isEmpty(tradeInResultData2.accountStatement.rightInfo.getText1())) {
                            tradeInResultFragment.s.setVisibility(0);
                            TradeInResultData.BarterText barterText = tradeInResultData2.accountStatement.rightInfo.text1;
                            SpannableString spannableString = new SpannableString(barterText.text);
                            int i3 = barterText.colorLocation;
                            if (i3 >= 0 && barterText.colorLength > 0 && i3 <= barterText.text.length() && barterText.colorLocation + barterText.colorLength <= barterText.text.length()) {
                                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
                                CustomTypefaceSpan customTypefaceSpan = new CustomTypefaceSpan("", FontsUtil.getTypeFace(tradeInResultFragment.getContext(), 4099));
                                int i4 = barterText.colorLocation;
                                spannableString.setSpan(foregroundColorSpan, i4, barterText.colorLength + i4, 17);
                                int i5 = barterText.colorLocation;
                                spannableString.setSpan(customTypefaceSpan, i5, barterText.colorLength + i5, 17);
                            }
                            int i6 = barterText.sizeLocation;
                            if (i6 >= 0 && barterText.sizeLength > 0 && i6 <= barterText.text.length() && barterText.sizeLocation + barterText.sizeLength <= barterText.text.length()) {
                                AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(PDUtils.sp2px(tradeInResultFragment.getContext(), 30.0f), false);
                                int i7 = barterText.sizeLocation;
                                spannableString.setSpan(absoluteSizeSpan, i7, barterText.sizeLength + i7, 17);
                            }
                            tradeInResultFragment.s.setText(spannableString);
                        }
                        if (!TextUtils.isEmpty(tradeInResultData2.accountStatement.rightInfo.getText2())) {
                            tradeInResultFragment.t.setVisibility(0);
                            tradeInResultFragment.t.setText(tradeInResultData2.accountStatement.rightInfo.getText2());
                        }
                    }
                    tradeInResultFragment.u.setVisibility(8);
                    TradeInResultData.BarterProtocol barterProtocol = tradeInResultData2.protocol;
                    if (barterProtocol != null && barterProtocol.isValid()) {
                        tradeInResultFragment.u.setVisibility(0);
                        tradeInResultFragment.v.setText(tradeInResultData2.protocol.text1);
                        tradeInResultFragment.x.setText(tradeInResultData2.protocol.text2);
                        tradeInResultFragment.s();
                        tradeInResultFragment.x.setOnClickListener(new com.jd.lib.productdetail.tradein.f.b(tradeInResultFragment, tradeInResultData2));
                    }
                    TradeInResultData.BatterFreeFa batterFreeFa = tradeInResultData2.freeFa;
                    if (batterFreeFa != null && batterFreeFa.isValid()) {
                        tradeInResultFragment.D.setVisibility(0);
                        StringBuilder sb2 = new StringBuilder();
                        if (TextUtils.isEmpty(tradeInResultData2.freeFa.text1)) {
                            i2 = 0;
                        } else {
                            sb2.append(tradeInResultData2.freeFa.text1);
                            i2 = tradeInResultData2.freeFa.text1.length() + 0;
                        }
                        if (!TextUtils.isEmpty(tradeInResultData2.freeFa.text2)) {
                            sb2.append(tradeInResultData2.freeFa.text2);
                            i2 += tradeInResultData2.freeFa.text2.length();
                        }
                        if (!TextUtils.isEmpty(tradeInResultData2.freeFa.text3)) {
                            sb2.append(tradeInResultData2.freeFa.text3);
                        }
                        SpannableString spannableString2 = new SpannableString(sb2.toString());
                        if (!TextUtils.isEmpty(tradeInResultData2.freeFa.text1)) {
                            ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
                            CustomTypefaceSpan customTypefaceSpan2 = new CustomTypefaceSpan("", FontsUtil.getTypeFace(tradeInResultFragment.getContext(), 4099));
                            spannableString2.setSpan(foregroundColorSpan2, 0, tradeInResultData2.freeFa.text1.length(), 17);
                            spannableString2.setSpan(customTypefaceSpan2, 0, tradeInResultData2.freeFa.text1.length(), 17);
                        }
                        if (!TextUtils.isEmpty(tradeInResultData2.freeFa.text3) && (length = tradeInResultData2.freeFa.text3.length() + i2) >= i2 && i2 <= sb2.length() && length <= sb2.length()) {
                            ForegroundColorSpan foregroundColorSpan3 = new ForegroundColorSpan(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
                            CustomTypefaceSpan customTypefaceSpan3 = new CustomTypefaceSpan("", FontsUtil.getTypeFace(tradeInResultFragment.getContext(), 4099));
                            spannableString2.setSpan(foregroundColorSpan3, i2, length, 17);
                            spannableString2.setSpan(customTypefaceSpan3, i2, length, 17);
                        }
                        tradeInResultFragment.D.setText(spannableString2);
                    } else {
                        tradeInResultFragment.D.setVisibility(8);
                    }
                    TradeInResultData.BarterButtonInfo barterButtonInfo = tradeInResultData2.buttonInfo;
                    String str = tradeInResultData2.minPriceLimitButtonToast;
                    if (barterButtonInfo != null) {
                        tradeInResultFragment.B.setVisibility(8);
                        tradeInResultFragment.C.setVisibility(8);
                        if (barterButtonInfo.main != null) {
                            tradeInResultFragment.B.setVisibility(0);
                            TextView textView = tradeInResultFragment.B;
                            TradeInResultData.BarterButtonInfo.BarterButton barterButton = barterButtonInfo.main;
                            textView.setText(tradeInResultFragment.a(barterButton.name, barterButton.subName));
                            try {
                                tradeInResultFragment.B.setTextColor(Color.parseColor(barterButtonInfo.main.textColor));
                            } catch (Exception unused) {
                                tradeInResultFragment.B.setTextColor(-1);
                            }
                            float dip2px = PDUtils.dip2px(20.0f);
                            tradeInResultFragment.B.setBackground(PDUtils.getGiftGradientDrawable(barterButtonInfo.main.bgColor, new float[]{dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px}));
                            tradeInResultFragment.B.setOnClickListener(new com.jd.lib.productdetail.tradein.f.d(tradeInResultFragment, str, barterButtonInfo));
                        }
                        if (barterButtonInfo.second != null) {
                            tradeInResultFragment.C.setVisibility(0);
                            TextView textView2 = tradeInResultFragment.C;
                            TradeInResultData.BarterButtonInfo.BarterButton barterButton2 = barterButtonInfo.second;
                            textView2.setText(tradeInResultFragment.a(barterButton2.name, barterButton2.subName));
                            try {
                                tradeInResultFragment.C.setTextColor(Color.parseColor(barterButtonInfo.second.textColor));
                            } catch (Exception unused2) {
                                tradeInResultFragment.C.setTextColor(-1);
                            }
                            float dip2px2 = PDUtils.dip2px(20.0f);
                            tradeInResultFragment.C.setBackground(PDUtils.getGiftGradientDrawable(barterButtonInfo.second.bgColor, new float[]{dip2px2, dip2px2, dip2px2, dip2px2, dip2px2, dip2px2, dip2px2, dip2px2}));
                            tradeInResultFragment.C.setOnClickListener(new com.jd.lib.productdetail.tradein.f.a(tradeInResultFragment, str, barterButtonInfo));
                            return;
                        }
                        return;
                    }
                    return;
                }
                TradeInResultFragment.this.o(false, true);
            } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                TradeInResultFragment.this.K.setVisibility(8);
                TradeInResultFragment.this.o(false, true);
            } else if (dataStatus != PdBaseProtocolLiveData.Result.DataStatus.DIRTY || (tradeInViewModel = TradeInResultFragment.this.f5399h) == null) {
            } else {
                tradeInViewModel.g();
                View view = TradeInResultFragment.this.K;
                if (view != null) {
                    view.setVisibility(0);
                }
                TradeInResultFragment.this.m(true);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class j implements TradeInResultOldDeviceCard.f {
        public j() {
        }
    }

    public TradeInResultFragment(BaseActivity baseActivity, TradeInDialogFragment tradeInDialogFragment) {
        super(R.layout.tradein_result_fragment_root);
        this.G = false;
        this.L = new j();
        this.M = false;
        this.f5398g = tradeInDialogFragment;
    }

    public static /* synthetic */ void e(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(JDDialog jDDialog, int i2, TradeInResultData.BarterButtonInfo barterButtonInfo, View view) {
        try {
            jDDialog.dismiss();
            if (i2 == 1) {
                g(barterButtonInfo);
            } else if (i2 == 2) {
                u(barterButtonInfo);
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static /* synthetic */ void l(JDDialog jDDialog, View view) {
        try {
            jDDialog.dismiss();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static /* synthetic */ void t(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(View view) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("extra.key.from.tradein.page", true);
        this.f5398g.moveToStep(TradeInStep.SELECT_OLD_DEVICE, bundle);
        TradeInViewModel tradeInViewModel = this.f5399h;
        if (tradeInViewModel != null) {
            tradeInViewModel.e("Productdetail_yjhxChooseMode", null);
        }
    }

    public final SpannableString a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append(str2);
        }
        SpannableString spannableString = new SpannableString(sb);
        try {
            int indexOf = sb.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (indexOf > 0) {
                spannableString.setSpan(new AbsoluteSizeSpan(10, true), indexOf, sb.length(), 18);
                spannableString.setSpan(new CustomTypefaceSpan("", Typeface.DEFAULT), indexOf, sb.length(), 18);
            }
        } catch (Exception unused) {
        }
        return spannableString;
    }

    public final JDDialog b() {
        JDDialog jDDialog = new JDDialog(getContext());
        jDDialog.setContentView(R.layout.tradein_widget_dialog_delete);
        TextView textView = (TextView) jDDialog.findViewById(R.id.tradein_widget_dialog_delete_title);
        jDDialog.titleView = textView;
        textView.setGravity(3);
        jDDialog.negButton = (Button) jDDialog.findViewById(R.id.tradein_widget_dialog_delete_btn_neg);
        jDDialog.posButton = (Button) jDDialog.findViewById(R.id.tradein_widget_dialog_delete_btn_pos);
        jDDialog.setCanceledOnTouchOutside(true);
        return jDDialog;
    }

    public final CharSequence c(String str) {
        if (TextUtils.isEmpty(str) || getContext() == null || getContext().getResources() == null) {
            return str;
        }
        SpannableString spannableString = new SpannableString(str + LangUtils.SINGLE_SPACE);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) spannableString);
        IconDrawable color = new IconDrawable(getContext(), new IconImpl("", getContext().getResources().getString(com.jd.lib.productdetail.core.R.string.lib_pd_icon_30_info)), PDIconFontUtil.PD_ICON_PATH).color(getContext().getResources().getColor(R.color.tradein_FA2C19));
        int dip2px = PDUtils.dip2px(12.0f);
        color.setBounds(0, 0, dip2px, dip2px);
        VerticalImageSpan verticalImageSpan = new VerticalImageSpan(color);
        SpannableString spannableString2 = new SpannableString(LangUtils.SINGLE_SPACE);
        spannableString2.setSpan(verticalImageSpan, 0, 1, 33);
        spannableStringBuilder.append((CharSequence) spannableString2);
        return spannableStringBuilder;
    }

    public final void d(int i2) {
        TradeInScrollView tradeInScrollView = this.f5402k;
        if (tradeInScrollView != null) {
            tradeInScrollView.setVisibility(i2);
        }
        ConstraintLayout constraintLayout = this.f5403l;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(i2);
        }
        TradeInTitle tradeInTitle = this.f5404m;
        if (tradeInTitle != null) {
            tradeInTitle.setVisibility(i2);
        }
    }

    public final void f(TradeInInformData.Data.TradeinInformInfo tradeinInformInfo) {
        if (tradeinInformInfo == null || !tradeinInformInfo.isValid()) {
            return;
        }
        TextView textView = (TextView) this.o.findViewById(R.id.tradein_inform_content_btn_ok);
        TextView textView2 = (TextView) this.o.findViewById(R.id.tradein_inform_content_btn_ok_tip);
        ((TextView) this.o.findViewById(R.id.tradein_inform_content_title)).setText(tradeinInformInfo.oldProductText);
        ((TextView) this.o.findViewById(R.id.tradein_inform_content_sub_title)).setText(tradeinInformInfo.remindExchangeText);
        textView.setText(tradeinInformInfo.chooseProductText);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInResultFragment.this.y(view);
            }
        });
        JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
        createSimple.setRoundingParams(new RoundingParams().setRoundAsCircle(true));
        createSimple.setPlaceholder(17);
        JDImageUtils.displayImage(tradeinInformInfo.oldProductImageUrl, (SimpleDraweeView) this.o.findViewById(R.id.tradein_inform_content_img), createSimple);
        TradeInInformData.Data.TradeinInformInfo.NoHaveLocalSubsidyInfo noHaveLocalSubsidyInfo = tradeinInformInfo.noHaveLocalSubsidyInfo;
        if (noHaveLocalSubsidyInfo != null && !TextUtils.isEmpty(noHaveLocalSubsidyInfo.subsidyInfoText)) {
            textView2.setVisibility(0);
            textView2.setText(tradeinInformInfo.noHaveLocalSubsidyInfo.subsidyInfoText);
            return;
        }
        textView2.setVisibility(8);
    }

    public final void g(TradeInResultData.BarterButtonInfo barterButtonInfo) {
        TradeInResultData tradeInResultData;
        TradeInResultData.BarterButtonInfo.BarterButton barterButton = barterButtonInfo.main;
        boolean z = false;
        boolean z2 = barterButton.type == 1;
        TradeInViewModel tradeInViewModel = this.f5399h;
        if (tradeInViewModel.f5261m == TradeInButtonType.DEFAULT || ((tradeInResultData = this.H) != null && tradeInResultData.mHasCheckedOldDevice)) {
            z = true;
        }
        tradeInViewModel.a(barterButton.scene, z).observe(getViewLifecycleOwner(), new b(barterButtonInfo, z2));
    }

    public void h(final TradeInResultData.BarterButtonInfo barterButtonInfo, final int i2) {
        final JDDialog b2 = b();
        b2.titleView.setText(getResources().getString(R.string.tradein_result_confirm_change));
        b2.negButton.setText(getResources().getString(R.string.tradein_result_confirm));
        b2.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInResultFragment.this.k(b2, i2, barterButtonInfo, view);
            }
        });
        b2.posButton.setText(getResources().getString(R.string.tradein_result_cancel));
        b2.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInResultFragment.l(JDDialog.this, view);
            }
        });
        b2.show();
    }

    public void j(TradeInSaveIdData.Data data) {
        TradeInDialogFragment tradeInDialogFragment = this.f5398g;
        if (tradeInDialogFragment == null || tradeInDialogFragment.getParentFragmentManager() == null || data == null) {
            return;
        }
        if (this.f5399h != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("qualification_id", data.qfId);
            TradeInSaveIdData.Data.MtaData mtaData = data.mMtaData;
            if (mtaData != null) {
                jsonObject.addProperty("Ttransaction_Ways", mtaData.Ttransaction_Ways);
                jsonObject.addProperty("product_id", data.mMtaData.product_id);
                jsonObject.addProperty("PaiPaiTrans_Ways", data.mMtaData.PaiPaiTrans_Ways);
                jsonObject.addProperty("PaiPaiProduct_id", data.mMtaData.PaiPaiProduct_id);
                try {
                    TradeInViewModel tradeInViewModel = this.f5399h;
                    if (tradeInViewModel != null && tradeInViewModel.a != null) {
                        JsonArray jsonArray = new JsonArray();
                        for (int i2 = 0; i2 < this.f5399h.a.size(); i2++) {
                            jsonArray.add(this.f5399h.a.get(i2));
                        }
                        jsonObject.add("touchstone_expids", jsonArray);
                    } else {
                        jsonObject.add("touchstone_expids", null);
                    }
                } catch (Exception unused) {
                }
            }
            this.f5399h.e("Productdetail_yjhxAddCart", jsonObject);
        }
        Bundle bundle = new Bundle();
        bundle.putString(TradeInConstants.PD_TRADE_IN_QF_ID, data.qfId);
        if (this.f5399h != null) {
            bundle.putString("result.key.tradetype", this.f5399h.f5256h + "");
            bundle.putString(TradeInConstants.PD_TRADE_IN_SKU_ID, this.f5399h.f5253e);
        }
        bundle.putString("pd.tradein.request.from", TradeInConstants.PD_TRADE_IN_ADD_CAR_FROM);
        this.f5398g.getParentFragmentManager().setFragmentResult("pd.tradein.request.code", bundle);
        this.f5398g.dismiss();
    }

    public final void m(boolean z) {
        if (this.f5399h != null) {
            if (!z) {
                o(true, false);
            }
            TradeInViewModel tradeInViewModel = this.f5399h;
            tradeInViewModel.getClass();
            HashMap hashMap = new HashMap();
            hashMap.put("bizCode", Integer.valueOf(tradeInViewModel.f5255g));
            hashMap.put("skuId", tradeInViewModel.f5253e);
            hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
            int i2 = tradeInViewModel.f5257i;
            if (i2 != 0) {
                hashMap.put("lastTradeType", Integer.valueOf(i2));
            }
            hashMap.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
            hashMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
            hashMap.put("buttonType", tradeInViewModel.f5261m.toStringValue());
            hashMap.put("settleType", Integer.valueOf(tradeInViewModel.f5258j));
            hashMap.put("esId", tradeInViewModel.f5262n);
            hashMap.put("extend", tradeInViewModel.t);
            JDJSONObject jDJSONObject = tradeInViewModel.s;
            if (jDJSONObject != null) {
                hashMap.put("extension", jDJSONObject);
            }
            if (!TextUtils.isEmpty(tradeInViewModel.o)) {
                hashMap.put("qualificationId", tradeInViewModel.o);
            }
            hashMap.put("queryDetailedType", Integer.valueOf(tradeInViewModel.p));
            TradeInAddressInfo c2 = tradeInViewModel.c();
            if (c2 != null) {
                hashMap.put("addressInfo", c2);
            }
            Map<String, Object> map = tradeInViewModel.v;
            if (map != null) {
                hashMap.put("refreshMeHoldData", map);
            }
            tradeInViewModel.u.setValue(null);
            com.jd.lib.productdetail.tradein.f.e eVar = new com.jd.lib.productdetail.tradein.f.e(hashMap);
            eVar.request(tradeInViewModel.b);
            eVar.mResult.observe(tradeInViewModel.d, new com.jd.lib.productdetail.tradein.a(tradeInViewModel, eVar));
        }
    }

    public void n(boolean z, TradeInResultData tradeInResultData) {
        this.M = z;
        if (z) {
            this.p.setVisibility(8);
            this.q.setVisibility(8);
            LinearLayout linearLayout = this.A;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            this.r.setVisibility(8);
            this.s.setVisibility(8);
            this.t.setVisibility(8);
            this.u.setVisibility(8);
            this.f5403l.setVisibility(8);
            this.D.setVisibility(4);
            this.o.setVisibility(0);
            f(tradeInResultData.noHaveLocalMachineInfo);
            return;
        }
        this.f5403l.setVisibility(0);
        this.o.setVisibility(8);
    }

    public final void o(boolean z, boolean z2) {
        if (z) {
            d(8);
            this.f5401j.setVisibility(8);
            this.f5400i.setVisibility(0);
            return;
        }
        this.f5400i.setVisibility(8);
        if (z2) {
            d(8);
            this.f5401j.setVisibility(0);
            if (NetUtils.isNetworkAvailable()) {
                this.f5401j.a(TradeinErrorView.a.UNKNOWN);
                return;
            } else {
                this.f5401j.a(TradeinErrorView.a.NONET);
                return;
            }
        }
        this.f5401j.setVisibility(8);
        d(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f5399h = (TradeInViewModel) new ViewModelProvider(this.f5398g).get(TradeInViewModel.class);
        this.E = getResources().getDrawable(R.drawable.tradein_result_btn_rule_indicator_checked);
        this.F = getResources().getDrawable(R.drawable.tradein_result_btn_rule_indicator_unchecked);
        Drawable drawable = this.E;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.E.getIntrinsicHeight());
        Drawable drawable2 = this.F;
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.F.getIntrinsicHeight());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInResultFragment.e(view2);
            }
        });
        View findViewById = view.findViewById(R.id.tradein_result_function_loading);
        this.K = findViewById;
        findViewById.setVisibility(8);
        this.K.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInResultFragment.t(view2);
            }
        });
        this.f5400i = view.findViewById(R.id.tradein_result_loading);
        TradeinErrorView tradeinErrorView = (TradeinErrorView) view.findViewById(R.id.tradein_result_error_view);
        this.f5401j = tradeinErrorView;
        tradeinErrorView.f5654i.setOnClickListener(new c());
        this.f5402k = (TradeInScrollView) view.findViewById(R.id.tradein_result_content);
        this.f5403l = (ConstraintLayout) view.findViewById(R.id.tradein_result_bottom_view);
        TradeInTitle tradeInTitle = (TradeInTitle) view.findViewById(R.id.tradein_result_title);
        this.f5404m = tradeInTitle;
        tradeInTitle.f5650j = new d();
        this.o = (ConstraintLayout) view.findViewById(R.id.tradein_result_old_device_emptyview);
        TradeInResultNewDeviceCard tradeInResultNewDeviceCard = (TradeInResultNewDeviceCard) view.findViewById(R.id.tradein_result_new_device);
        this.f5405n = tradeInResultNewDeviceCard;
        tradeInResultNewDeviceCard.f5418h = this.f5399h;
        tradeInResultNewDeviceCard.f5419i = this.f5398g;
        this.p = (TradeInResultOldDeviceCard) view.findViewById(R.id.tradein_result_old_device_container_1);
        this.q = (TradeInResultOldDeviceCard) view.findViewById(R.id.tradein_result_old_device_container_2);
        this.r = (TradeInResultSubsidyView) view.findViewById(R.id.tradein_result_subsidy);
        this.s = (TextView) view.findViewById(R.id.tradein_result_price);
        TextView textView = (TextView) view.findViewById(R.id.tradein_result_promotion);
        this.t = textView;
        FontsUtil.changeTextFont(textView);
        this.u = view.findViewById(R.id.tradein_result_btn_protocol);
        this.v = (TextView) view.findViewById(R.id.tradein_result_btn_protocol_hint);
        this.x = (TextView) view.findViewById(R.id.tradein_result_btn_protocol_detail);
        this.w = (ImageView) view.findViewById(R.id.tradein_result_btn_protocol_hint_image);
        this.y = (TextView) view.findViewById(R.id.tradein_result_btn_add_your_device_text);
        this.z = (TextView) view.findViewById(R.id.tradein_result_btn_add_your_device_text_bottom_view);
        this.A = (LinearLayout) view.findViewById(R.id.tradein_result_btn_add_your_device);
        this.B = (TextView) view.findViewById(R.id.tradein_result_bottom_btn_left);
        this.C = (TextView) view.findViewById(R.id.tradein_result_bottom_btn_right);
        this.D = (TextView) view.findViewById(R.id.tradein_result_free);
        this.y.setOnClickListener(new e());
        this.z.setOnClickListener(new f());
        this.u.setOnClickListener(new g());
        this.f5402k.a(new h());
        if (this.f5399h.u.getValue() == null) {
            m(false);
        }
        this.f5399h.u.observe(getViewLifecycleOwner(), new i());
    }

    public final boolean q(TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo) {
        String string;
        String string2;
        if (tradeInWareCardInfo != null) {
            TradeInResultData.TradeInFloorData tradeInFloorData = tradeInWareCardInfo.exchangeWareWay;
            if (tradeInFloorData != null && tradeInFloorData.checkRequired && !tradeInFloorData.isValid()) {
                if (!TextUtils.isEmpty(tradeInWareCardInfo.title)) {
                    string2 = getString(R.string.tradein_result_toast_tradein_mode_with_type, tradeInWareCardInfo.title);
                } else {
                    string2 = getString(R.string.tradein_result_toast_tradein_mode);
                }
                PDUtils.showToastCenterNormal(getContext(), string2);
                return false;
            }
            TradeInResultData.TradeInFloorData tradeInFloorData2 = tradeInWareCardInfo.tradMode;
            if (tradeInFloorData2 != null && tradeInFloorData2.checkRequired && !tradeInFloorData2.isValid()) {
                if (!TextUtils.isEmpty(tradeInWareCardInfo.title)) {
                    string = getString(R.string.tradein_result_toast_tran_mode_with_type, tradeInWareCardInfo.title);
                } else {
                    string = getString(R.string.tradein_result_toast_tran_mode);
                }
                PDUtils.showToastCenterNormal(getContext(), string);
                return false;
            }
            TradeInResultData.TradeInFloorData tradeInFloorData3 = tradeInWareCardInfo.bankCard;
            if (tradeInFloorData3 != null && tradeInFloorData3.checkRequired && !tradeInFloorData3.isValid()) {
                PDUtils.showToastCenterNormal(getContext(), getString(R.string.tradein_result_toast_payment_mode));
                return false;
            }
        }
        return true;
    }

    public final void s() {
        if (this.G) {
            this.w.setBackground(this.E);
        } else {
            this.w.setBackground(this.F);
        }
        String string = getString(this.G ? R.string.tradein_common_select : R.string.tradein_common_unselect);
        if (this.u != null) {
            StringBuilder sb = new StringBuilder();
            CharSequence text = this.v.getText();
            CharSequence text2 = this.x.getText();
            sb.append(string);
            if (!TextUtils.isEmpty(text)) {
                sb.append(text);
            }
            if (!TextUtils.isEmpty(text2)) {
                sb.append(text2);
            }
            this.u.setContentDescription(sb.toString());
        }
    }

    public final void u(TradeInResultData.BarterButtonInfo barterButtonInfo) {
        TradeInResultData tradeInResultData;
        TradeInViewModel tradeInViewModel = this.f5399h;
        tradeInViewModel.a(barterButtonInfo.second.scene, tradeInViewModel.f5261m == TradeInButtonType.DEFAULT || ((tradeInResultData = this.H) != null && tradeInResultData.mHasCheckedOldDevice)).observe(getViewLifecycleOwner(), new a(barterButtonInfo));
    }

    public void v(TradeInSaveIdData.Data data) {
        TradeInDialogFragment tradeInDialogFragment;
        if (this.f5399h == null || (tradeInDialogFragment = this.f5398g) == null || tradeInDialogFragment.getParentFragmentManager() == null || data == null) {
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("qualification_id", data.qfId);
        TradeInSaveIdData.Data.MtaData mtaData = data.mMtaData;
        if (mtaData != null) {
            jsonObject.addProperty("Ttransaction_Ways", mtaData.Ttransaction_Ways);
            jsonObject.addProperty("product_id", data.mMtaData.product_id);
            jsonObject.addProperty("PaiPaiTrans_Ways", data.mMtaData.PaiPaiTrans_Ways);
            jsonObject.addProperty("PaiPaiProduct_id", data.mMtaData.PaiPaiProduct_id);
            try {
                TradeInViewModel tradeInViewModel = this.f5399h;
                if (tradeInViewModel != null && tradeInViewModel.a != null) {
                    JsonArray jsonArray = new JsonArray();
                    for (int i2 = 0; i2 < this.f5399h.a.size(); i2++) {
                        jsonArray.add(this.f5399h.a.get(i2));
                    }
                    jsonObject.add("touchstone_expids", jsonArray);
                } else {
                    jsonObject.add("touchstone_expids", null);
                }
            } catch (Exception unused) {
            }
        }
        Bundle bundle = new Bundle();
        if (this.f5399h.f5261m != TradeInButtonType.DOUBLE) {
            bundle.putString("result.key.qfid", data.qfId);
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("hxTradeType", (Object) (this.f5399h.f5256h + ""));
            bundle.putString("result.key.tradetype", jDJSONObject.toJSONString());
            this.f5399h.e("Productdetail_yjhxToastConfirm", jsonObject);
        } else {
            bundle.putString(TradeInConstants.PD_TRADE_IN_QF_ID, data.qfId);
            bundle.putString("pd.tradein.request.from", TradeInConstants.PD_TRADE_IN_JUMP_ORDER_FROM);
            bundle.putString(TradeInConstants.RESULT_KEY_SOURCE_YJHX, data.toastYJHXSource);
            bundle.putString(TradeInConstants.RESULT_KEY_TOAST_BIZ, data.toastBiz);
            bundle.putString(TradeInConstants.RESULT_KEY_TOAST_ENTRANCE, data.toastEntrance);
            bundle.putString("result.key.tradetype", this.f5399h.f5256h + "");
            this.f5399h.e("Productdetail_yjhxChangeOld", jsonObject);
        }
        bundle.putString(TradeInConstants.PD_TRADE_IN_SKU_ID, this.f5399h.f5253e);
        this.f5398g.getParentFragmentManager().setFragmentResult("pd.tradein.request.code", bundle);
        this.f5398g.dismiss();
    }

    public final boolean w(String str) {
        TradeInResultData tradeInResultData;
        View view;
        PdBaseProtocolLiveData.Result<TradeInResultData> value = this.f5399h.u.getValue();
        if (value == null || value.mStatus != PdBaseProtocolLiveData.Result.DataStatus.SUCCESS || (tradeInResultData = value.mData) == null) {
            return false;
        }
        TradeInResultData tradeInResultData2 = tradeInResultData;
        if (q(tradeInResultData2.oldWare1) && q(tradeInResultData2.oldWare2)) {
            if (!TextUtils.isEmpty(str)) {
                PDUtils.showToastCenterNormal(getContext(), str);
                return false;
            } else if (this.G || (view = this.u) == null || view.getVisibility() != 0) {
                return true;
            } else {
                PDUtils.showToastCenterNormal(getContext(), getString(R.string.tradein_result_toast_protocol));
                TradeInScrollView tradeInScrollView = this.f5402k;
                if (tradeInScrollView != null) {
                    tradeInScrollView.fullScroll(130);
                }
                return false;
            }
        }
        return false;
    }

    public void x() {
        m(false);
    }
}
