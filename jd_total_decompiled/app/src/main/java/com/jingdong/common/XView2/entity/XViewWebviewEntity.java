package com.jingdong.common.XView2.entity;

import com.jd.framework.json.anotation.JSONField;
import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView2.entity.webview.XViewWebviewDataPath;
import com.jingdong.common.XView2.layer.BaseLayerDelegate;

/* loaded from: classes.dex */
public class XViewWebviewEntity extends BaseWidgetEntity {
    public BaseConfig config;
    public XViewWebviewDataPath dataPath;
    @JSONField(deserialize = false, serialize = false)
    public BaseLayerDelegate mBaseLayerDelegate;
    @JSONField(deserialize = false, serialize = false)
    public XViewCallBack mXViewCallBack;
    @JSONField(deserialize = false, serialize = false)
    public XViewEntity mXViewEntity;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        if (this.config == null) {
            this.config = new BaseConfig();
        }
        return this.config;
    }

    public void setXViewEntity(XViewEntity xViewEntity, XViewCallBack xViewCallBack, BaseLayerDelegate baseLayerDelegate) {
        this.mXViewEntity = xViewEntity;
        this.mXViewCallBack = xViewCallBack;
        this.mBaseLayerDelegate = baseLayerDelegate;
    }
}
