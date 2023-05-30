package com.jingdong.common.XView2.layer.flexcube.view;

import android.content.Context;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.b;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView2.entity.XViewWebviewEntity;
import com.jingdong.common.XView2.layer.BaseLayerDelegate;
import java.util.Map;

/* loaded from: classes5.dex */
public class XViewWebView extends FrameLayout implements IWidget<XViewWebviewEntity>, IKnowWidget {
    private XViewWebviewEntity mEntity;
    private XViewEntity mXViewEntity;
    private float multiple;
    public XView xView;

    public XViewWebView(Context context) {
        super(context);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        XViewWebviewEntity xViewWebviewEntity = this.mEntity;
        if (xViewWebviewEntity != null) {
            return xViewWebviewEntity.getBaseConfig().getH(this.multiple);
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        XViewWebviewEntity xViewWebviewEntity = this.mEntity;
        if (xViewWebviewEntity != null) {
            return xViewWebviewEntity.getBaseConfig().getW(this.multiple);
        }
        return 0;
    }

    public String getUrl() {
        XViewEntity xViewEntity = this.mXViewEntity;
        return xViewEntity != null ? xViewEntity.url : "";
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IKnowWidget
    public BaseWidgetEntity getWidgetEntity() {
        return this.mEntity;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        XViewWebviewEntity xViewWebviewEntity = this.mEntity;
        if (xViewWebviewEntity != null && xViewWebviewEntity.dataPath != null) {
            XViewEntity xViewEntity = xViewWebviewEntity.mXViewEntity;
            this.mXViewEntity = xViewEntity;
            if (xViewEntity == null) {
                this.mXViewEntity = new XViewEntity();
            }
            String d = b.d(map, this.mEntity.dataPath.url);
            XViewEntity xViewEntity2 = this.mXViewEntity;
            BaseLayerDelegate baseLayerDelegate = this.mEntity.mBaseLayerDelegate;
            if (baseLayerDelegate != null) {
                d = baseLayerDelegate.getAppendParamsUrl(d);
            }
            xViewEntity2.url = d;
            this.xView.configXView(this, this.mXViewEntity, this.mEntity.mXViewCallBack);
            this.xView.setIsNeedConfigCloseButton(false);
            this.xView.startXView();
            return;
        }
        clear();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateStyle(@NonNull XViewWebviewEntity xViewWebviewEntity, float f2) {
        this.mEntity = xViewWebviewEntity;
        this.multiple = f2;
        this.xView = new XView(getContext());
    }
}
