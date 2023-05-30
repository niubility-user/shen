package com.jingdong.common.XView2.layer.flexcube.view;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.b.f;
import com.jd.lib.flexcube.widgets.c.a;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.entity.XViewCountDownViewEntity;
import com.jingdong.common.XView2.entity.timer.XViewCountDownConfig;
import com.jingdong.common.XView2.entity.timer.XViewCountDownDataPath;
import com.jingdong.common.XView2.layer.BaseLayerDelegate;
import com.jingdong.common.XView2.layer.IBaseLayer;
import com.jingdong.common.XView2.layer.flexcube.view.CountDownTextView;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class XViewCountDownView extends FrameLayout implements IWidget<XViewCountDownViewEntity>, IBaseLayer {
    public static final String EVENT_COUNTDOWN_CLOSE_TYPE = "countdown_close";
    public static final String EVENT_COUNTDOWN_OPENAPP_TYPE = "countdown_openapp";
    public static final String EVENT_COUNTDOWN_REFRESH_TYPE = "countdown_refresh";
    public static final String EVENT_FINISH_TYPE = "countdown_finish";
    private int height;
    private final CountDownTextView mCountDownTextView;
    private XViewCountDownViewEntity mEntity;
    private float multiple;
    private int textSize;
    private int width;

    public XViewCountDownView(@NonNull Context context) {
        super(context);
        CountDownTextView countDownTextView = new CountDownTextView(context);
        this.mCountDownTextView = countDownTextView;
        addView(countDownTextView, new ViewGroup.LayoutParams(-1, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(Map map, IWidgetCommunication iWidgetCommunication) {
        setCountDownEvent(this.mCountDownTextView, b.a(map, this.mEntity.dataPath.countDownEvent), iWidgetCommunication);
    }

    private void setCountDownEvent(View view, ClickEvent clickEvent, IWidgetCommunication iWidgetCommunication) {
        if (clickEvent == null || TextUtils.isEmpty(clickEvent.type) || iWidgetCommunication == null) {
            return;
        }
        BabelScope babelScope = iWidgetCommunication.getBabelScope();
        IClickEvent iClickEvent = babelScope != null ? (IClickEvent) babelScope.getService(IClickEvent.class) : null;
        if (iClickEvent != null) {
            iClickEvent.onClick(view, clickEvent);
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void changeLayoutCallBack(String str, String str2, int i2, String str3) {
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        setVisibility(8);
        setOnClickListener(null);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void closeLayer(String str, int i2) {
        CountDownTextView countDownTextView = this.mCountDownTextView;
        if (countDownTextView != null) {
            countDownTextView.destroy();
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void closeXView2Anim() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void configControlBtn() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void configCurrentLayer(XViewConfigEntity.TargetsEntity targetsEntity, View view) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void destroyLayer() {
        CountDownTextView countDownTextView = this.mCountDownTextView;
        if (countDownTextView != null) {
            countDownTextView.destroy();
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public BaseLayerDelegate getBaseLayerDelegate() {
        return null;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public View getContainer() {
        return null;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public String getLayerId() {
        return null;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        XViewCountDownViewEntity xViewCountDownViewEntity = this.mEntity;
        if (xViewCountDownViewEntity == null || xViewCountDownViewEntity.getBaseConfig().getH(this.multiple) <= 0) {
            return -2;
        }
        return this.mEntity.getBaseConfig().getH(this.multiple);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        XViewCountDownViewEntity xViewCountDownViewEntity = this.mEntity;
        if (xViewCountDownViewEntity == null || xViewCountDownViewEntity.getBaseConfig().getW(this.multiple) <= 0) {
            return -2;
        }
        return this.mEntity.getBaseConfig().getW(this.multiple);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void initParamsBeforeCreateLayer(JSONObject jSONObject) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public XView2Container initXViewContainer() {
        return null;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isFullScreen() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isJumpClose() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isLayerEnterImmediatelyPop() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isLayerEnterPop() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isRenderSuccess() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onLayerDisplayed(String str, String str2) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onLoadingLayer(String str, String str2) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onPause() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onResume() {
    }

    public long parse(String str, long j2) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return j2;
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void releaseLayer() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void setCurrentLayer(View view) {
    }

    protected void setOnClick(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        ClickEvent a = b.a(map, this.mEntity.dataPath.clickEvent);
        if (a == null || iWidgetCommunication == null) {
            return;
        }
        a.b bVar = new a.b(getContext(), a);
        bVar.a(iWidgetCommunication.getBabelScope());
        setOnClickListener(bVar.b());
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void setTargetInfo(XViewConfigEntity.TargetsEntity targetsEntity) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void showPrepared() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void startTimeCountTimer() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void startXView2popUpAnim() {
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull final Map<String, String> map, final IWidgetCommunication iWidgetCommunication) {
        XViewCountDownDataPath xViewCountDownDataPath;
        XViewCountDownViewEntity xViewCountDownViewEntity = this.mEntity;
        if (xViewCountDownViewEntity != null && (xViewCountDownDataPath = xViewCountDownViewEntity.dataPath) != null && xViewCountDownViewEntity.config != null) {
            String d = b.d(map, xViewCountDownDataPath.countDown);
            if (TextUtils.isEmpty(d)) {
                setVisibility(8);
            } else {
                setVisibility(0);
                String d2 = b.d(map, this.mEntity.dataPath.improvementTime);
                this.mCountDownTextView.showCountTime((Long.parseLong(d.trim()) * 1000) - (TextUtils.isEmpty(d2) ? 0L : SystemClock.elapsedRealtime() - Long.parseLong(d2)), this.mEntity.config.countDownType, new CountDownTextView.OnCountDownListener() { // from class: com.jingdong.common.XView2.layer.flexcube.view.a
                    @Override // com.jingdong.common.XView2.layer.flexcube.view.CountDownTextView.OnCountDownListener
                    public final void onCountDownFinish() {
                        XViewCountDownView.this.b(map, iWidgetCommunication);
                    }
                });
            }
            if (TextUtils.isEmpty(this.mEntity.dataPath.clickEvent)) {
                return;
            }
            setOnClick(map, iWidgetCommunication);
            return;
        }
        clear();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateStyle(@NonNull XViewCountDownViewEntity xViewCountDownViewEntity, float f2) {
        this.mEntity = xViewCountDownViewEntity;
        this.multiple = f2;
        if (xViewCountDownViewEntity == null || !(xViewCountDownViewEntity.getBaseConfig() instanceof XViewCountDownConfig)) {
            return;
        }
        XViewCountDownConfig xViewCountDownConfig = (XViewCountDownConfig) this.mEntity.getBaseConfig();
        this.width = com.jd.lib.flexcube.iwidget.b.b.d((int) xViewCountDownConfig.w, f2);
        this.height = com.jd.lib.flexcube.iwidget.b.b.d((int) xViewCountDownConfig.f4225h, f2);
        this.mCountDownTextView.setTextColor(com.jd.lib.flexcube.iwidget.b.a.a(xViewCountDownConfig.color, SupportMenu.CATEGORY_MASK));
        f.f(this.mCountDownTextView, xViewCountDownConfig.fontInfo, f2, true);
        f.e(this.mCountDownTextView, xViewCountDownConfig.fontInfo);
        setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(xViewCountDownConfig.bgColor, 0));
    }
}
