package com.jd.lib.flexcube.owidgets.view.exchange;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import com.jd.lib.babel.servicekit.iservice.IFonts;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jd.lib.babel.servicekit.model.BaseEvent;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.babel.servicekit.util.BabelServiceUtils;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.owidgets.R;
import com.jd.lib.flexcube.owidgets.entity.ExchangeButtonEntity;
import com.jd.lib.flexcube.owidgets.entity.exchange.ExchangeData;
import com.jd.lib.flexcube.owidgets.entity.exchange.ExchangeEntity;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.utils.DPIUtil;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes15.dex */
public class ExchangeButton extends LinearLayout implements IWidget<ExchangeButtonEntity> {
    private final int STATUS_EXCHANGED;
    private final int STATUS_LACK;
    private final int STATUS_TO_EXCHANGE;
    private final int STATUS_TO_LOGIN;
    private final int STATUS_USED;
    private View.OnClickListener clickListener;
    private IWidgetCommunication communication;
    private ExchangeEntity entity;
    private ImageView icon;
    private com.jd.lib.flexcube.owidgets.view.exchange.b listener;
    private ExchangeButtonEntity mEntity;
    private float multiple;
    private int status;
    private TextView textView;

    /* loaded from: classes15.dex */
    class a implements com.jd.lib.flexcube.owidgets.view.exchange.b {
        a() {
        }

        private void b(String str) {
            BabelJumpEntity babelJumpEntity;
            if (ExchangeButton.this.communication == null || ExchangeButton.this.communication.getMaterialModel() == null || ExchangeButton.this.communication.getMaterialModel().clickEvent == null || (babelJumpEntity = ExchangeButton.this.communication.getMaterialModel().clickEvent.jump) == null) {
                return;
            }
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("exState", str);
            BabelServiceUtils.onClickMta(ExchangeButton.this.getContext(), MtaData.Builder.from(babelJumpEntity.eventId, babelJumpEntity.getSrv()).page(ExchangeButton.this.communication.getPageName(), ExchangeButton.this.communication.getPageParam()).jsonParam(babelJumpEntity.srvData).ext(hashMap).build());
        }

        @Override // com.jd.lib.flexcube.owidgets.view.exchange.b
        public void a(ExchangeData exchangeData) {
            String str = exchangeData.code;
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case 48626:
                    if (str.equals("101")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 49586:
                    if (str.equals(BasicPushStatus.SUCCESS_CODE)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 49617:
                    if (str.equals("210")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 52469:
                    if (str.equals("500")) {
                        c2 = 3;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    ExchangeButton.this.status = 5;
                    if (ExchangeButton.this.communication != null && ExchangeButton.this.communication.getStateBundle() != null) {
                        ExchangeButton.this.communication.getStateBundle().putInt("exchangeButtonStatus", ExchangeButton.this.status);
                    }
                    ToastUtils.showToastInCenter(ExchangeButton.this.getContext(), (byte) 3, exchangeData.desc, 1);
                    ExchangeButton exchangeButton = ExchangeButton.this;
                    exchangeButton.updateStatus(exchangeButton.status);
                    if (exchangeData.jump != null) {
                        BabelServiceUtils.execJump(ExchangeButton.this.getContext(), exchangeData.jump);
                    }
                    b("0");
                    return;
                case 1:
                    ToastUtils.showToastInCenter(ExchangeButton.this.getContext(), (byte) 2, exchangeData.desc, 1);
                    ExchangeButton.this.status = 3;
                    if (ExchangeButton.this.communication != null && ExchangeButton.this.communication.getStateBundle() != null) {
                        ExchangeButton.this.communication.getStateBundle().putInt("exchangeButtonStatus", ExchangeButton.this.status);
                        ExchangeButton.this.communication.sendEvent(new BaseEvent("jumpToNext"));
                    }
                    ExchangeButton exchangeButton2 = ExchangeButton.this;
                    exchangeButton2.updateStatus(exchangeButton2.status);
                    b("1");
                    return;
                case 2:
                    if (exchangeData.jump != null) {
                        BabelServiceUtils.execJump(ExchangeButton.this.getContext(), exchangeData.jump);
                    }
                    b("0");
                    return;
                case 3:
                    ToastUtils.showToastInCenter(ExchangeButton.this.getContext(), (byte) 3, exchangeData.desc, 1);
                    b("0");
                    return;
                default:
                    if (TextUtils.isEmpty(exchangeData.desc)) {
                        return;
                    }
                    ToastUtils.showToastInCenter(ExchangeButton.this.getContext(), exchangeData.desc, 1);
                    return;
            }
        }

        @Override // com.jd.lib.flexcube.owidgets.view.exchange.b
        public void onError() {
            ToastUtils.showToastInCenter(ExchangeButton.this.getContext(), ExchangeButton.this.getContext().getText(R.string.owidgets_net_error).toString(), 1);
            b("0");
        }
    }

    /* loaded from: classes15.dex */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int i2 = ExchangeButton.this.status;
            if (i2 == 1) {
                BabelServiceUtils.executeLogin(view.getContext(), null);
            } else if (i2 != 2) {
            } else {
                new com.jd.lib.flexcube.owidgets.view.exchange.a(ExchangeButton.this.listener).d(view.getContext(), ExchangeButton.this.entity);
            }
        }
    }

    public ExchangeButton(Context context) {
        super(context);
        this.STATUS_TO_LOGIN = 1;
        this.STATUS_TO_EXCHANGE = 2;
        this.STATUS_EXCHANGED = 3;
        this.STATUS_USED = 4;
        this.STATUS_LACK = 5;
        this.listener = new a();
        this.clickListener = new b();
        setOrientation(0);
        setGravity(17);
        init();
    }

    private void getStatus(com.jd.lib.flexcube.owidgets.b.a aVar, IWidgetCommunication iWidgetCommunication) {
        if (iWidgetCommunication != null && iWidgetCommunication.getStateBundle() != null) {
            int i2 = iWidgetCommunication.getStateBundle().getInt("exchangeButtonStatus", -1);
            this.status = i2;
            if (i2 > 0) {
                return;
            }
            boolean equals = "1".equals(aVar.a(this.mEntity.dataPath.isLogin));
            boolean equals2 = "1".equals(aVar.a(this.mEntity.dataPath.pointsHasExchange));
            boolean equals3 = "1".equals(aVar.a(this.mEntity.dataPath.pointsHasConsume));
            if (!equals) {
                this.status = 1;
            } else if (equals2) {
                this.status = equals3 ? 4 : 3;
            } else {
                this.status = 2;
            }
            iWidgetCommunication.getStateBundle().putInt("exchangeButtonStatus", this.status);
            return;
        }
        this.status = 0;
    }

    private void init() {
        TextView textView = new TextView(getContext());
        this.textView = textView;
        textView.setTextColor(-1);
        this.textView.setGravity(17);
        this.textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        addView(this.textView);
        this.icon = new ImageView(getContext());
        IFonts iFonts = (IFonts) BabelServiceUtils.getService(IFonts.class);
        if (iFonts != null) {
            iFonts.setImageIcon(this.icon, "Babel.ttf", "&#xe006;", -1);
        }
        addView(this.icon);
    }

    private void setBackground(int i2, int i3) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{i2, i3});
        float dip2px = DPIUtil.dip2px(getContext(), 3.0f);
        gradientDrawable.setCornerRadii(new float[]{dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px});
        setBackgroundDrawable(gradientDrawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatus(int i2) {
        setVisibility(0);
        this.icon.setVisibility(8);
        if (i2 == 1 || i2 == 2) {
            setBackground(SupportMenu.CATEGORY_MASK, -31384);
            this.textView.setText(getContext().getText(R.string.owidgets_exchange));
            this.icon.setVisibility(0);
            setOnClickListener(this.clickListener);
        } else if (i2 == 3) {
            setBackground(SupportMenu.CATEGORY_MASK, -31384);
            this.textView.setText(getContext().getText(R.string.owidgets_exchange_use));
            setClickable(false);
        } else if (i2 == 4) {
            setBackground(SupportMenu.CATEGORY_MASK, -31384);
            this.textView.setText(getContext().getText(R.string.owidgets_has_exchange));
            setClickable(false);
        } else if (i2 != 5) {
            setVisibility(8);
        } else {
            setBackground(-4210753, -2039584);
            this.textView.setText(getContext().getText(R.string.owidgets_point_lack));
            setOnClickListener(this.clickListener);
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        ExchangeButtonEntity exchangeButtonEntity = this.mEntity;
        if (exchangeButtonEntity != null) {
            return exchangeButtonEntity.getBaseConfig().getH(this.multiple);
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        ExchangeButtonEntity exchangeButtonEntity = this.mEntity;
        if (exchangeButtonEntity != null) {
            return exchangeButtonEntity.getBaseConfig().getW(this.multiple);
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        this.communication = iWidgetCommunication;
        com.jd.lib.flexcube.owidgets.b.a aVar = new com.jd.lib.flexcube.owidgets.b.a(map);
        getStatus(aVar, iWidgetCommunication);
        updateStatus(this.status);
        ExchangeEntity exchangeEntity = new ExchangeEntity();
        this.entity = exchangeEntity;
        exchangeEntity.exchangeId = aVar.a(this.mEntity.dataPath.exchangeId);
        this.entity.venderId = aVar.a(this.mEntity.dataPath.venderId);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateStyle(@NonNull ExchangeButtonEntity exchangeButtonEntity, float f2) {
        this.mEntity = exchangeButtonEntity;
        this.multiple = f2;
        this.textView.setTextSize(0, (int) (exchangeButtonEntity.getBaseConfig().getH(f2) * 0.5f));
        int h2 = (int) (exchangeButtonEntity.getBaseConfig().getH(f2) * 0.45f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(h2, h2);
        layoutParams.leftMargin = DPIUtil.dip2px(getContext(), 6.0f);
        this.icon.setLayoutParams(layoutParams);
    }
}
