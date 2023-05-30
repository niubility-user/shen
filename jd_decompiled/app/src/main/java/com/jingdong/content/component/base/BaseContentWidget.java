package com.jingdong.content.component.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.content.component.a;
import com.jingdong.content.component.base.IContentWidgetInvoke;
import com.jingdong.content.component.entity.BaseContentEntity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\b\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\u00020\u00052\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006B'\b\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\bA\u0010\u0011J\u000f\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ)\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0012\u0010\tJ!\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00028\u00002\b\u0010\u0014\u001a\u0004\u0018\u00018\u0001H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001b\u0010\tJ\u000f\u0010\u001c\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001c\u0010\tJ\u0019\u0010\u001f\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016\u00a2\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b!\u0010\tJ\u000f\u0010\"\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\"\u0010\tJ\u000f\u0010#\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b#\u0010\tJ\u000f\u0010$\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00028\u0001H&\u00a2\u0006\u0004\b&\u0010'R$\u0010(\u001a\u0004\u0018\u00018\u00008\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010/\u001a\u0004\u0018\u00010.8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R$\u00105\u001a\u0004\u0018\u00018\u00018\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u0010'\"\u0004\b8\u00109R\"\u0010;\u001a\u00020:8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@\u00a8\u0006B"}, d2 = {"Lcom/jingdong/content/component/base/BaseContentWidget;", "Lcom/jingdong/content/component/entity/BaseContentEntity;", "DATA", "Lcom/jingdong/content/component/base/IContentWidgetInvoke;", "ACTION", "Lcom/jingdong/content/component/base/LifecycleContainer;", "Lcom/jingdong/content/component/base/IContentWidget;", "", XView2Constants.XVIEW2_ACTION_INIT, "()V", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "handleAttrs", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "initView", "contentEntity", "invoke", "updateContent", "(Lcom/jingdong/content/component/entity/BaseContentEntity;Lcom/jingdong/content/component/base/IContentWidgetInvoke;)V", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "registerLifecycle", "(Landroidx/lifecycle/Lifecycle;)V", "reset", "release", "Landroid/os/Bundle;", "bundle", "initData", "(Landroid/os/Bundle;)V", "initListener", "dealDarkModeUI", "dealEldestModeUI", "getLayoutId", "()I", "createWidgetInvoke", "()Lcom/jingdong/content/component/base/IContentWidgetInvoke;", "mContentEntity", "Lcom/jingdong/content/component/entity/BaseContentEntity;", "getMContentEntity", "()Lcom/jingdong/content/component/entity/BaseContentEntity;", "setMContentEntity", "(Lcom/jingdong/content/component/entity/BaseContentEntity;)V", "Landroid/view/View;", "mRootView", "Landroid/view/View;", "getMRootView", "()Landroid/view/View;", "setMRootView", "(Landroid/view/View;)V", "mInvokeAction", "Lcom/jingdong/content/component/base/IContentWidgetInvoke;", "getMInvokeAction", "setMInvokeAction", "(Lcom/jingdong/content/component/base/IContentWidgetInvoke;)V", "", "finishInitView", "Z", "getFinishInitView", "()Z", "setFinishInitView", "(Z)V", "<init>", "content-component-widget_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public abstract class BaseContentWidget<DATA extends BaseContentEntity, ACTION extends IContentWidgetInvoke> extends LifecycleContainer implements IContentWidget<DATA, ACTION> {
    private HashMap _$_findViewCache;
    private boolean finishInitView;
    @Nullable
    private DATA mContentEntity;
    @Nullable
    private ACTION mInvokeAction;
    @Nullable
    private View mRootView;

    @JvmOverloads
    public BaseContentWidget(@NotNull Context context) {
        this(context, null, 0, 6, null);
    }

    @JvmOverloads
    public BaseContentWidget(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ BaseContentWidget(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    private final void init() {
        Intent intent;
        initView();
        Context context = getContext();
        Bundle bundle = null;
        if (!(context instanceof Activity)) {
            context = null;
        }
        Activity activity = (Activity) context;
        if (activity != null && (intent = activity.getIntent()) != null) {
            bundle = intent.getExtras();
        }
        initData(bundle);
        initListener();
    }

    @Override // com.jingdong.content.component.base.LifecycleContainer
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.jingdong.content.component.base.LifecycleContainer
    public View _$_findCachedViewById(int i2) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            this._$_findViewCache.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @NotNull
    public abstract ACTION createWidgetInvoke();

    public void dealDarkModeUI() {
    }

    public void dealEldestModeUI() {
    }

    protected final boolean getFinishInitView() {
        return this.finishInitView;
    }

    public int getLayoutId() {
        return -1;
    }

    @Nullable
    protected final DATA getMContentEntity() {
        return this.mContentEntity;
    }

    @Nullable
    protected final ACTION getMInvokeAction() {
        return this.mInvokeAction;
    }

    @Nullable
    protected final View getMRootView() {
        return this.mRootView;
    }

    public void handleAttrs(@NotNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    }

    public void initData(@Nullable Bundle bundle) {
    }

    public void initListener() {
    }

    public void initView() {
        int layoutId = getLayoutId();
        if (layoutId > 0) {
            this.mRootView = LayoutInflater.from(getContext()).inflate(layoutId, (ViewGroup) this, true);
        }
        if (this.mRootView == null) {
            this.mRootView = getRootView();
        }
        a aVar = a.f12570c;
        if (aVar.a()) {
            dealDarkModeUI();
        }
        if (aVar.b()) {
            dealEldestModeUI();
        }
    }

    @Override // com.jingdong.content.component.base.IContentWidget
    public void registerLifecycle(@NotNull Lifecycle lifecycle) {
        registerObserver(lifecycle);
    }

    @Override // com.jingdong.content.component.base.IContentWidget
    public void release() {
    }

    @Override // com.jingdong.content.component.base.IContentWidget
    public void reset() {
    }

    protected final void setFinishInitView(boolean z) {
        this.finishInitView = z;
    }

    protected final void setMContentEntity(@Nullable DATA data) {
        this.mContentEntity = data;
    }

    protected final void setMInvokeAction(@Nullable ACTION action) {
        this.mInvokeAction = action;
    }

    protected final void setMRootView(@Nullable View view) {
        this.mRootView = view;
    }

    @Override // com.jingdong.content.component.base.IContentWidget
    public void updateContent(@NotNull DATA contentEntity, @Nullable ACTION invoke) {
        this.mContentEntity = contentEntity;
        if (invoke == null) {
            invoke = createWidgetInvoke();
        }
        this.mInvokeAction = invoke;
    }

    @JvmOverloads
    public BaseContentWidget(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        handleAttrs(context, attributeSet, i2);
        init();
    }
}
