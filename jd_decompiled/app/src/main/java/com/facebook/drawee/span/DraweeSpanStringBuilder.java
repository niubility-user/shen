package com.facebook.drawee.span;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.view.View;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.lifecycle.AttachDetachListener;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.image.ImageInfo;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class DraweeSpanStringBuilder extends SpannableStringBuilder implements AttachDetachListener {
    public static final int UNSET_SIZE = -1;
    private Drawable mBoundDrawable;
    private View mBoundView;
    private final DrawableCallback mDrawableCallback;
    private DraweeSpanChangedListener mDraweeSpanChangedListener;
    private final Set<DraweeSpan> mDraweeSpans;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class DrawableCallback implements Drawable.Callback {
        private DrawableCallback() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            if (DraweeSpanStringBuilder.this.mBoundView != null) {
                DraweeSpanStringBuilder.this.mBoundView.invalidate();
            } else if (DraweeSpanStringBuilder.this.mBoundDrawable != null) {
                DraweeSpanStringBuilder.this.mBoundDrawable.invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
            if (DraweeSpanStringBuilder.this.mBoundView != null) {
                DraweeSpanStringBuilder.this.mBoundView.postDelayed(runnable, j2 - SystemClock.uptimeMillis());
            } else if (DraweeSpanStringBuilder.this.mBoundDrawable != null) {
                DraweeSpanStringBuilder.this.mBoundDrawable.scheduleSelf(runnable, j2);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            if (DraweeSpanStringBuilder.this.mBoundView != null) {
                DraweeSpanStringBuilder.this.mBoundView.removeCallbacks(runnable);
            } else if (DraweeSpanStringBuilder.this.mBoundDrawable != null) {
                DraweeSpanStringBuilder.this.mBoundDrawable.unscheduleSelf(runnable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class DrawableChangedListener extends BaseControllerListener<ImageInfo> {
        private final DraweeSpan mDraweeSpan;
        private final boolean mEnableResizing;
        private final int mFixedHeight;

        public DrawableChangedListener(DraweeSpanStringBuilder draweeSpanStringBuilder, DraweeSpan draweeSpan) {
            this(draweeSpanStringBuilder, draweeSpan, false);
        }

        public DrawableChangedListener(DraweeSpanStringBuilder draweeSpanStringBuilder, DraweeSpan draweeSpan, boolean z) {
            this(draweeSpan, z, -1);
        }

        public DrawableChangedListener(DraweeSpan draweeSpan, boolean z, int i2) {
            Preconditions.checkNotNull(draweeSpan);
            this.mDraweeSpan = draweeSpan;
            this.mEnableResizing = z;
            this.mFixedHeight = i2;
        }

        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable, Drawable drawable) {
            if (!this.mEnableResizing || imageInfo == null || this.mDraweeSpan.getDraweeHolder().getTopLevelDrawable() == null) {
                return;
            }
            Drawable topLevelDrawable = this.mDraweeSpan.getDraweeHolder().getTopLevelDrawable();
            Rect bounds = topLevelDrawable.getBounds();
            int i2 = this.mFixedHeight;
            if (i2 != -1) {
                int height = (int) ((i2 / imageInfo.getHeight()) * imageInfo.getWidth());
                if (bounds.width() == height && bounds.height() == this.mFixedHeight) {
                    return;
                }
                topLevelDrawable.setBounds(0, 0, height, this.mFixedHeight);
                if (DraweeSpanStringBuilder.this.mDraweeSpanChangedListener == null) {
                    return;
                }
            } else if (bounds.width() == imageInfo.getWidth() && bounds.height() == imageInfo.getHeight()) {
                return;
            } else {
                topLevelDrawable.setBounds(0, 0, imageInfo.getWidth(), imageInfo.getHeight());
                if (DraweeSpanStringBuilder.this.mDraweeSpanChangedListener == null) {
                    return;
                }
            }
            DraweeSpanStringBuilder.this.mDraweeSpanChangedListener.onDraweeSpanChanged(DraweeSpanStringBuilder.this);
        }
    }

    /* loaded from: classes.dex */
    public interface DraweeSpanChangedListener {
        void onDraweeSpanChanged(DraweeSpanStringBuilder draweeSpanStringBuilder);
    }

    public DraweeSpanStringBuilder() {
        this.mDraweeSpans = new HashSet();
        this.mDrawableCallback = new DrawableCallback();
    }

    public DraweeSpanStringBuilder(CharSequence charSequence) {
        super(charSequence);
        this.mDraweeSpans = new HashSet();
        this.mDrawableCallback = new DrawableCallback();
    }

    public DraweeSpanStringBuilder(CharSequence charSequence, int i2, int i3) {
        super(charSequence, i2, i3);
        this.mDraweeSpans = new HashSet();
        this.mDrawableCallback = new DrawableCallback();
    }

    protected void bindToDrawable(Drawable drawable) {
        unbindFromPreviousComponent();
        this.mBoundDrawable = drawable;
    }

    protected void bindToView(View view) {
        unbindFromPreviousComponent();
        this.mBoundView = view;
    }

    @VisibleForTesting
    public Set<DraweeSpan> getDraweeSpans() {
        return this.mDraweeSpans;
    }

    public boolean hasDraweeSpans() {
        return !this.mDraweeSpans.isEmpty();
    }

    @VisibleForTesting
    void onAttach() {
        Iterator<DraweeSpan> it = this.mDraweeSpans.iterator();
        while (it.hasNext()) {
            it.next().onAttach();
        }
    }

    @Override // com.facebook.common.lifecycle.AttachDetachListener
    public void onAttachToView(View view) {
        bindToView(view);
        onAttach();
    }

    @VisibleForTesting
    void onDetach() {
        Iterator<DraweeSpan> it = this.mDraweeSpans.iterator();
        while (it.hasNext()) {
            it.next().onDetach();
        }
    }

    @Override // com.facebook.common.lifecycle.AttachDetachListener
    public void onDetachFromView(View view) {
        unbindFromView(view);
        onDetach();
    }

    public void setDraweeSpanChangedListener(DraweeSpanChangedListener draweeSpanChangedListener) {
        this.mDraweeSpanChangedListener = draweeSpanChangedListener;
    }

    public void setImageSpan(Context context, DraweeHierarchy draweeHierarchy, DraweeController draweeController, int i2, int i3, int i4, int i5, boolean z, int i6) {
        DraweeHolder create = DraweeHolder.create(draweeHierarchy, context);
        create.setController(draweeController);
        setImageSpan(create, i2, i3, i4, i5, z, i6);
    }

    public void setImageSpan(Context context, DraweeHierarchy draweeHierarchy, DraweeController draweeController, int i2, int i3, int i4, boolean z, int i5) {
        setImageSpan(context, draweeHierarchy, draweeController, i2, i2, i3, i4, z, i5);
    }

    public void setImageSpan(DraweeHolder draweeHolder, int i2, int i3, int i4, int i5, boolean z, int i6) {
        if (i3 >= length()) {
            return;
        }
        Drawable topLevelDrawable = draweeHolder.getTopLevelDrawable();
        if (topLevelDrawable != null) {
            if (topLevelDrawable.getBounds().isEmpty()) {
                topLevelDrawable.setBounds(0, 0, i4, i5);
            }
            topLevelDrawable.setCallback(this.mDrawableCallback);
        }
        DraweeSpan draweeSpan = new DraweeSpan(draweeHolder, i6);
        DraweeController controller = draweeHolder.getController();
        if (controller instanceof AbstractDraweeController) {
            ((AbstractDraweeController) controller).addControllerListener(new DrawableChangedListener(draweeSpan, z, i5));
        }
        this.mDraweeSpans.add(draweeSpan);
        setSpan(draweeSpan, i2, i3 + 1, 33);
    }

    public void setImageSpan(DraweeHolder draweeHolder, int i2, int i3, int i4, boolean z, int i5) {
        setImageSpan(draweeHolder, i2, i2, i3, i4, z, i5);
    }

    protected void unbindFromDrawable(Drawable drawable) {
        if (drawable != this.mBoundDrawable) {
            return;
        }
        this.mBoundDrawable = null;
    }

    protected void unbindFromPreviousComponent() {
        View view = this.mBoundView;
        if (view != null) {
            unbindFromView(view);
        }
        Drawable drawable = this.mBoundDrawable;
        if (drawable != null) {
            unbindFromDrawable(drawable);
        }
    }

    protected void unbindFromView(View view) {
        if (view != this.mBoundView) {
            return;
        }
        this.mBoundView = null;
    }
}
