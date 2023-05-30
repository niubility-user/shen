package com.jingdong.common.unification.router.builderimpl;

import com.jingdong.common.unification.filter.FilterTools;
import com.jingdong.common.unification.image.editor.ImageConstant;
import com.jingdong.common.unification.image.editor.ImageParam;
import com.jingdong.common.unification.router.builder.RouterBuilder;
import com.jingdong.common.unification.router.builder.RouterEntry;
import java.util.List;

/* loaded from: classes6.dex */
public class ImageEditorBuilder extends RouterBuilder<ImageEditorBuilder, ImageEditorRouterEntry> {

    /* loaded from: classes6.dex */
    public class ImageEditorRouterEntry extends RouterEntry<ImageEditorRouterEntry> {
        public ImageParam imageParam;

        public ImageEditorRouterEntry() {
        }
    }

    public ImageEditorBuilder() {
        super("JDImageEditorModule", "show");
    }

    public ImageEditorBuilder editorImagePath(String str) {
        putString(ImageConstant.EDITOR_IMAGE_PATH, str);
        return this;
    }

    public ImageEditorBuilder imageParam(ImageParam imageParam) {
        ((ImageEditorRouterEntry) this.mRouterEntry).imageParam = imageParam;
        return this;
    }

    public ImageEditorBuilder picFilterTypes(List<FilterTools.FilterType> list) {
        extraObject("picFilterTypes", list);
        return this;
    }

    public ImageEditorBuilder setRequestCode(int i2) {
        requestCode(i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.unification.router.builder.RouterBuilder
    public ImageEditorRouterEntry initRouterEntry() {
        return new ImageEditorRouterEntry();
    }
}
