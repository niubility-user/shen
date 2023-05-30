package com.jingdong.content.component.base;

import androidx.lifecycle.Lifecycle;
import com.jingdong.content.component.base.IContentWidgetInvoke;
import com.jingdong.content.component.entity.BaseContentEntity;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\bf\u0018\u0000*\n\b\u0000\u0010\u0002 \u0000*\u00020\u0001*\n\b\u0001\u0010\u0004 \u0000*\u00020\u00032\u00020\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&\u00a2\u0006\u0004\b\t\u0010\nJ!\u0010\r\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00028\u00002\b\u0010\f\u001a\u0004\u0018\u00018\u0001H&\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\bH&\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\bH&\u00a2\u0006\u0004\b\u0011\u0010\u0010\u00a8\u0006\u0012"}, d2 = {"Lcom/jingdong/content/component/base/IContentWidget;", "Lcom/jingdong/content/component/entity/BaseContentEntity;", "DATA", "Lcom/jingdong/content/component/base/IContentWidgetInvoke;", "ACTION", "", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "", "registerLifecycle", "(Landroidx/lifecycle/Lifecycle;)V", "contentEntity", "invoke", "updateContent", "(Lcom/jingdong/content/component/entity/BaseContentEntity;Lcom/jingdong/content/component/base/IContentWidgetInvoke;)V", "reset", "()V", "release", "content-component-widget_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public interface IContentWidget<DATA extends BaseContentEntity, ACTION extends IContentWidgetInvoke> {
    void registerLifecycle(@NotNull Lifecycle lifecycle);

    void release();

    void reset();

    void updateContent(@NotNull DATA contentEntity, @Nullable ACTION invoke);
}
