package com.jingdong.common.imagegifexpand;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.common.internal.ImmutableList;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.jingdong.amon.router.annotation.AnnoConst;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B)\b\u0007\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\f\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0014\u00a2\u0006\u0004\b\u000e\u0010\rR*\u0010\u0011\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fj\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001b"}, d2 = {"Lcom/jingdong/common/imagegifexpand/AnimateImageView;", "Lcom/facebook/drawee/view/SimpleDraweeView;", "Lcom/facebook/common/internal/ImmutableList;", "Lcom/facebook/imagepipeline/drawable/DrawableFactory;", "createCustomDrawableFactories", "()Lcom/facebook/common/internal/ImmutableList;", "factory", "", "addDrawableFactory", "(Lcom/facebook/imagepipeline/drawable/DrawableFactory;)V", "Lcom/facebook/drawee/interfaces/DraweeController;", "draweeController", "setController", "(Lcom/facebook/drawee/interfaces/DraweeController;)V", "setCustomDrawableFactories", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "customDrawableFactories", "Ljava/util/ArrayList;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "", "defStyle", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public class AnimateImageView extends SimpleDraweeView {
    private HashMap _$_findViewCache;
    private ArrayList<DrawableFactory> customDrawableFactories;

    @JvmOverloads
    public AnimateImageView(@Nullable Context context) {
        this(context, null, 0, 6, null);
    }

    @JvmOverloads
    public AnimateImageView(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ AnimateImageView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    private final ImmutableList<DrawableFactory> createCustomDrawableFactories() {
        ArrayList<DrawableFactory> arrayList = this.customDrawableFactories;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        try {
            return ImmutableList.copyOf((List) this.customDrawableFactories);
        } catch (Throwable unused) {
            return null;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

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

    public final void addDrawableFactory(@Nullable DrawableFactory factory) {
        if (factory != null) {
            if (this.customDrawableFactories == null) {
                this.customDrawableFactories = new ArrayList<>();
            }
            ArrayList<DrawableFactory> arrayList = this.customDrawableFactories;
            if (arrayList != null) {
                arrayList.add(factory);
            }
        }
    }

    @Override // com.facebook.drawee.view.DraweeView
    public void setController(@Nullable DraweeController draweeController) {
        setCustomDrawableFactories(draweeController);
        super.setController(draweeController);
    }

    protected void setCustomDrawableFactories(@Nullable DraweeController draweeController) {
        ImmutableList<DrawableFactory> createCustomDrawableFactories;
        if (!(draweeController instanceof PipelineDraweeController) || (createCustomDrawableFactories = createCustomDrawableFactories()) == null) {
            return;
        }
        ((PipelineDraweeController) draweeController).setCustomDrawableFactories(createCustomDrawableFactories);
    }

    @JvmOverloads
    public AnimateImageView(@Nullable Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
