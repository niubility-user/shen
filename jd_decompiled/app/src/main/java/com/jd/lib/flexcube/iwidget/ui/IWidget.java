package com.jd.lib.flexcube.iwidget.ui;

import androidx.annotation.NonNull;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import java.util.Map;

/* loaded from: classes14.dex */
public interface IWidget<T extends BaseWidgetEntity> {
    void clear();

    int getLayoutParamsHeight();

    int getLayoutParamsWidth();

    void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication);

    void updateStyle(@NonNull T t, float f2);
}
