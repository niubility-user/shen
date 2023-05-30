package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeepLinkVideoAndImageHelper;
import com.jingdong.common.unification.image.editor.ImageConstant;
import com.jingdong.common.unification.image.editor.ImageParam;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.unification.router.builderimpl.ImageEditorBuilder;
import java.util.List;

/* loaded from: classes6.dex */
public class JDImageEditorModule extends AbsJDModule {
    private ImageParam getParamFromJson(JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        ImageParam imageParam = new ImageParam();
        if (jDJSONObject.containsKey(ImageConstant.EDITOR_IMAGE_PATH)) {
            imageParam.editorImagePath = jDJSONObject.getString(ImageConstant.EDITOR_IMAGE_PATH);
        }
        if (routerEntry != null) {
            imageParam.filterTypes = (List) routerEntry.extraBundle.get("picFilterTypes");
        }
        return imageParam;
    }

    @Override // com.jingdong.common.unification.router.module.AbsJDModule
    public void show(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (context != null && jDJSONObject != null) {
            ImageParam imageParam = routerEntry instanceof ImageEditorBuilder.ImageEditorRouterEntry ? ((ImageEditorBuilder.ImageEditorRouterEntry) routerEntry).imageParam : null;
            if (imageParam == null) {
                imageParam = getParamFromJson(jDJSONObject, routerEntry);
            }
            int i2 = routerEntry.requestCode;
            if (i2 != -1 && (context instanceof Activity)) {
                DeepLinkVideoAndImageHelper.startImageEditorActivityForResult((Activity) context, imageParam, i2);
            } else {
                DeepLinkVideoAndImageHelper.startImageEditorActivity(context, imageParam);
            }
            CallBackListener callBackListener = routerEntry.callBackListener;
            if (callBackListener != null) {
                callBackListener.onComplete();
                return;
            }
            return;
        }
        CallBackListener callBackListener2 = routerEntry.callBackListener;
        if (callBackListener2 != null) {
            JDRouterUtil.callBackError(callBackListener2, 703);
        }
    }
}
