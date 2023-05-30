package com.jd.dynamic.apis;

import com.jd.dynamic.R;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.lib.error.DynamicException;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.viewparse.BaseFrameLayout;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"com/jd/dynamic/apis/DynamicContainer$bindViewAsync$2", "Lcom/jd/dynamic/apis/DynamicContainer$OnAddDynamicViewListener;", "", "onAddView", "()V", "lib_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class DynamicContainer$bindViewAsync$2 implements DynamicContainer.OnAddDynamicViewListener {
    final /* synthetic */ DynamicContainer a;
    final /* synthetic */ IViewBindListener b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DynamicContainer$bindViewAsync$2(DynamicContainer dynamicContainer, IViewBindListener iViewBindListener) {
        this.a = dynamicContainer;
        this.b = iViewBindListener;
    }

    @Override // com.jd.dynamic.apis.DynamicContainer.OnAddDynamicViewListener
    public void onAddView() {
        JSONObject jSONObject;
        DynamicTemplateEngine dynamicTemplateEngine = this.a.engine;
        if (dynamicTemplateEngine == null) {
            Intrinsics.throwNpe();
        }
        jSONObject = this.a.containerData;
        dynamicTemplateEngine.bindViewAsync(jSONObject, new IViewBindListener() { // from class: com.jd.dynamic.apis.DynamicContainer$bindViewAsync$2$onAddView$1
            @Override // com.jd.dynamic.apis.IViewBindListener
            public void onDynamicViewBind() {
                BaseFrameLayout baseFrameLayout;
                if (DynamicContainer$bindViewAsync$2.this.a.isAsyncCreateView && (baseFrameLayout = (BaseFrameLayout) DynamicContainer$bindViewAsync$2.this.a.findViewById(R.id.dynamic_root_view)) != null) {
                    baseFrameLayout.setVisibility(0);
                }
                DynamicContainer$bindViewAsync$2.this.a.hideOrShowSkeletonView(false);
                IViewBindListener iViewBindListener = DynamicContainer$bindViewAsync$2.this.b;
                if (iViewBindListener != null) {
                    iViewBindListener.onDynamicViewBind();
                }
            }

            @Override // com.jd.dynamic.apis.IViewBindListener
            public void onError(@NotNull DynamicException exception) {
                DYContainerConfig dYContainerConfig;
                DYContainerConfig dYContainerConfig2;
                DynamicContainer$bindViewAsync$2.this.a.hideOrShowSkeletonView(false);
                String message = exception.getMessage();
                dYContainerConfig = DynamicContainer$bindViewAsync$2.this.a.containerConfig;
                String templateId = dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null;
                dYContainerConfig2 = DynamicContainer$bindViewAsync$2.this.a.containerConfig;
                String module = dYContainerConfig2 != null ? dYContainerConfig2.getModule() : null;
                DynamicTemplateEngine dynamicTemplateEngine2 = DynamicContainer$bindViewAsync$2.this.a.engine;
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND_ASYNC, message, templateId, module, R2.attr.layout_constraintHeight_default, exception, m.q(dynamicTemplateEngine2 != null ? dynamicTemplateEngine2.getZipVersion() : null, null));
                IViewBindListener iViewBindListener = DynamicContainer$bindViewAsync$2.this.b;
                if (iViewBindListener != null) {
                    iViewBindListener.onError(exception);
                }
            }
        });
    }
}
