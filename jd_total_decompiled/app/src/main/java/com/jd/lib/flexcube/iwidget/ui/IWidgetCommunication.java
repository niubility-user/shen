package com.jd.lib.flexcube.iwidget.ui;

import android.os.Bundle;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.servicekit.model.BaseEvent;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;

/* loaded from: classes14.dex */
public interface IWidgetCommunication {
    BabelScope getBabelScope();

    MaterialModel getMaterialModel();

    String getPageName();

    String getPageParam();

    Bundle getStateBundle();

    void sendEvent(BaseEvent baseEvent);
}
