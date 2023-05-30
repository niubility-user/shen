package com.jd.lib.flexcube.widgets.b;

import android.graphics.Rect;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.entity.FlexBoxEntity;
import com.jd.lib.flexcube.widgets.entity.ProcessEntity;
import com.jd.lib.flexcube.widgets.entity.common.ProcessInfo;
import com.jd.lib.flexcube.widgets.entity.flexbox.FlexBoxConfig;

/* loaded from: classes15.dex */
public class c {
    public static Rect a(@NonNull BaseWidgetEntity baseWidgetEntity, float f2) {
        FlexBoxEntity flexBoxEntity;
        FlexBoxConfig flexBoxConfig;
        CanvasConfig canvasConfig;
        if (baseWidgetEntity == null || baseWidgetEntity.getBaseConfig() == null) {
            return null;
        }
        if (baseWidgetEntity instanceof ProcessEntity) {
            ProcessEntity processEntity = (ProcessEntity) baseWidgetEntity;
            ProcessInfo processInfo = processEntity.config.progressInfo;
            if (processInfo != null && !TextUtils.isEmpty(processInfo.iconUrl)) {
                ProcessInfo processInfo2 = processEntity.config.progressInfo;
                if (processInfo2.iconWidth > 0 && processInfo2.iconHeight > 0) {
                    Rect rect = new Rect();
                    rect.left = (int) (baseWidgetEntity.getBaseConfig().x * f2);
                    rect.top = ((int) (baseWidgetEntity.getBaseConfig().y * f2)) + ((int) ((baseWidgetEntity.getBaseConfig().getH(f2) - (processEntity.config.progressInfo.iconHeight * f2)) / 2.0f));
                    rect.right = rect.left + ((int) (baseWidgetEntity.getBaseConfig().w * f2));
                    rect.bottom = (int) (rect.top + Math.max(baseWidgetEntity.getBaseConfig().getH(f2), processEntity.config.progressInfo.iconHeight * f2));
                    return rect;
                }
            }
        }
        if ((baseWidgetEntity instanceof FlexBoxEntity) && (flexBoxConfig = (flexBoxEntity = (FlexBoxEntity) baseWidgetEntity).config) != null && flexBoxConfig.linearLayoutStyle != null && (canvasConfig = flexBoxEntity.canvasConfig) != null) {
            flexBoxConfig.w = canvasConfig.w;
            flexBoxConfig.f4225h = canvasConfig.f4223h;
        }
        Rect rect2 = new Rect();
        rect2.left = (int) (baseWidgetEntity.getBaseConfig().x * f2);
        rect2.top = (int) (baseWidgetEntity.getBaseConfig().y * f2);
        rect2.right = rect2.left + ((int) (baseWidgetEntity.getBaseConfig().w * f2));
        rect2.bottom = rect2.top + ((int) (baseWidgetEntity.getBaseConfig().f4225h * f2));
        return rect2;
    }
}
