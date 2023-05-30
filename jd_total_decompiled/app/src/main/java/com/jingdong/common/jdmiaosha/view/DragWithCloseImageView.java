package com.jingdong.common.jdmiaosha.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.R;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.jdmiaosha.view.DragWithCloseImageView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002FGB\u0011\b\u0016\u0012\u0006\u0010>\u001a\u00020=\u00a2\u0006\u0004\b?\u0010@B\u001b\b\u0016\u0012\u0006\u0010>\u001a\u00020=\u0012\b\u0010B\u001a\u0004\u0018\u00010A\u00a2\u0006\u0004\b?\u0010CB#\b\u0016\u0012\u0006\u0010>\u001a\u00020=\u0012\b\u0010B\u001a\u0004\u0018\u00010A\u0012\u0006\u0010D\u001a\u00020\u0005\u00a2\u0006\u0004\b?\u0010EJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J5\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0019\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001d\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0016\u0010!\u001a\u00020 8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010)\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010\t\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010,R\u0016\u0010\u0007\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010,R\u0016\u0010.\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020\u00058\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u0010,R\u0016\u0010\b\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010,R\u0016\u0010\n\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010,R\u0016\u00101\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u0010,R\u0018\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0018\u00106\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0018\u0010:\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u00020\u00058\u0002@\u0002X\u0082D\u00a2\u0006\u0006\n\u0004\b<\u0010,\u00a8\u0006H"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/DragWithCloseImageView;", "Landroid/widget/RelativeLayout;", "", "onDragItem", "()V", "", "initTop", "dragViewWidth", "dragViewHeight", "closeViewWidth", "closeViewHeight", "initSize", "(IIIII)V", "topMargin", "setTopMargin", "(I)V", "Landroid/view/MotionEvent;", "event", "", "onTouchEvent", "(Landroid/view/MotionEvent;)Z", "", "url", "setDragViewUrl", "(Ljava/lang/String;)V", "Lcom/jingdong/common/jdmiaosha/view/DragWithCloseImageView$DragViewWithCloseListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "setDragViewWithCloseListener", "(Lcom/jingdong/common/jdmiaosha/view/DragWithCloseImageView$DragViewWithCloseListener;)V", "Lcom/jingdong/common/jdmiaosha/view/DragWithCloseImageView$DragViewLoadingListener;", "setDragViewLoadingListener", "(Lcom/jingdong/common/jdmiaosha/view/DragWithCloseImageView$DragViewLoadingListener;)V", "Lcom/facebook/drawee/view/SimpleDraweeView;", "dragView", "Lcom/facebook/drawee/view/SimpleDraweeView;", "Landroid/widget/LinearLayout;", "closeViewLayout", "Landroid/widget/LinearLayout;", "Landroid/widget/ImageView;", "closeView", "Landroid/widget/ImageView;", "dragViewLoadingListener", "Lcom/jingdong/common/jdmiaosha/view/DragWithCloseImageView$DragViewLoadingListener;", "dragViewWithCloseHeight", "I", "", "downY", "F", "CLOSE_VIEW_TOP_MARGIN", "moveY", "Landroid/widget/RelativeLayout$LayoutParams;", "dragViewPar", "Landroid/widget/RelativeLayout$LayoutParams;", "Landroid/view/ViewGroup;", "parentLayout", "Landroid/view/ViewGroup;", "startMove", "Z", "clickListener", "Lcom/jingdong/common/jdmiaosha/view/DragWithCloseImageView$DragViewWithCloseListener;", "MOVE_MIN_DISTANCE", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "DragViewLoadingListener", "DragViewWithCloseListener", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class DragWithCloseImageView extends RelativeLayout {
    private final int CLOSE_VIEW_TOP_MARGIN;
    private final int MOVE_MIN_DISTANCE;
    private HashMap _$_findViewCache;
    private DragViewWithCloseListener clickListener;
    private final ImageView closeView;
    private int closeViewHeight;
    private final LinearLayout closeViewLayout;
    private int closeViewWidth;
    private float downY;
    private final SimpleDraweeView dragView;
    private int dragViewHeight;
    private DragViewLoadingListener dragViewLoadingListener;
    private RelativeLayout.LayoutParams dragViewPar;
    private int dragViewWidth;
    private int dragViewWithCloseHeight;
    private int moveY;
    private ViewGroup parentLayout;
    private boolean startMove;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0007\u0010\u0004\u00a8\u0006\b"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/DragWithCloseImageView$DragViewLoadingListener;", "", "", "onLoadingComplete", "()V", "onLoadingStarted", "onLoadingFailed", "onLoadingCancelled", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes5.dex */
    public interface DragViewLoadingListener {
        void onLoadingCancelled();

        void onLoadingComplete();

        void onLoadingFailed();

        void onLoadingStarted();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0007\u0010\u0006\u00a8\u0006\b"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/DragWithCloseImageView$DragViewWithCloseListener;", "", "Landroid/view/View;", "view", "", "onClickDragView", "(Landroid/view/View;)V", "onClickCloseView", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes5.dex */
    public interface DragViewWithCloseListener {
        void onClickCloseView(@NotNull View view);

        void onClickDragView(@NotNull View view);
    }

    public DragWithCloseImageView(@NotNull Context context) {
        this(context, null);
    }

    private final void onDragItem() {
        int i2 = this.dragViewWithCloseHeight / 2;
        int i3 = this.moveY;
        ViewGroup viewGroup = this.parentLayout;
        if (viewGroup == null) {
            Intrinsics.throwNpe();
        }
        if (i3 > viewGroup.getHeight() - i2) {
            ViewGroup viewGroup2 = this.parentLayout;
            if (viewGroup2 == null) {
                Intrinsics.throwNpe();
            }
            this.moveY = viewGroup2.getHeight() - i2;
        } else if (this.moveY < i2) {
            this.moveY = i2;
        }
        RelativeLayout.LayoutParams layoutParams = this.dragViewPar;
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        layoutParams.topMargin = this.moveY - i2;
        this.dragView.setLayoutParams(this.dragViewPar);
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

    public final void initSize(int initTop, int dragViewWidth, int dragViewHeight, int closeViewWidth, int closeViewHeight) {
        this.dragViewWidth = dragViewWidth;
        this.dragViewHeight = dragViewHeight;
        this.closeViewWidth = closeViewWidth;
        this.closeViewHeight = closeViewHeight;
        this.dragViewWithCloseHeight = this.CLOSE_VIEW_TOP_MARGIN + dragViewHeight + closeViewHeight;
        this.parentLayout = (ViewGroup) getParent();
        this.dragView.setId(R.id.miaosha_floating_img);
        this.dragView.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dragViewWidth, dragViewHeight);
        this.dragViewPar = layoutParams;
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        layoutParams.topMargin = initTop;
        this.dragView.setLayoutParams(this.dragViewPar);
        this.closeViewLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dragViewWidth, this.CLOSE_VIEW_TOP_MARGIN + closeViewHeight);
        layoutParams2.addRule(3, this.dragView.getId());
        this.closeViewLayout.setLayoutParams(layoutParams2);
        this.closeView.setLayoutParams(new RelativeLayout.LayoutParams(closeViewWidth, closeViewHeight));
        this.closeView.setBackgroundResource(R.drawable.miaosha_floating_close);
        this.closeViewLayout.setPadding((dragViewWidth - closeViewWidth) / 2, this.CLOSE_VIEW_TOP_MARGIN, 0, 0);
        this.closeViewLayout.addView(this.closeView);
        this.closeViewLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdmiaosha.view.DragWithCloseImageView$initSize$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DragWithCloseImageView.DragViewWithCloseListener dragViewWithCloseListener;
                LinearLayout linearLayout;
                dragViewWithCloseListener = DragWithCloseImageView.this.clickListener;
                if (dragViewWithCloseListener != null) {
                    linearLayout = DragWithCloseImageView.this.closeViewLayout;
                    dragViewWithCloseListener.onClickCloseView(linearLayout);
                }
            }
        });
        addView(this.dragView);
        addView(this.closeViewLayout);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (r0 != 3) goto L28;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(@NotNull MotionEvent event) {
        DragViewWithCloseListener dragViewWithCloseListener;
        int action = event.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    this.moveY = (int) event.getY();
                    if (this.startMove) {
                        onDragItem();
                    } else {
                        this.startMove = Math.abs(event.getY() - this.downY) > ((float) this.MOVE_MIN_DISTANCE);
                    }
                }
            }
            if (!this.startMove && (dragViewWithCloseListener = this.clickListener) != null) {
                dragViewWithCloseListener.onClickDragView(this.dragView);
            }
        } else {
            this.startMove = false;
            this.downY = event.getY();
            if (event.getY() < this.dragView.getY() || event.getY() > this.dragView.getY() + this.dragViewWithCloseHeight) {
                return false;
            }
        }
        return super.onTouchEvent(event);
    }

    public final void setDragViewLoadingListener(@NotNull DragViewLoadingListener listener) {
        this.dragViewLoadingListener = listener;
    }

    public final void setDragViewUrl(@NotNull String url) {
        if (this.dragViewWidth == 0 || this.dragViewHeight == 0) {
            return;
        }
        JDImageUtils.displayImage(url, this.dragView, new JDDisplayImageOptions(), new JDImageLoadingListener() { // from class: com.jingdong.common.jdmiaosha.view.DragWithCloseImageView$setDragViewUrl$1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(@Nullable String p0, @Nullable View p1) {
                DragWithCloseImageView.DragViewLoadingListener dragViewLoadingListener;
                dragViewLoadingListener = DragWithCloseImageView.this.dragViewLoadingListener;
                if (dragViewLoadingListener != null) {
                    dragViewLoadingListener.onLoadingCancelled();
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(@Nullable String p0, @Nullable View p1, @Nullable Bitmap p2) {
                DragWithCloseImageView.DragViewLoadingListener dragViewLoadingListener;
                dragViewLoadingListener = DragWithCloseImageView.this.dragViewLoadingListener;
                if (dragViewLoadingListener != null) {
                    dragViewLoadingListener.onLoadingComplete();
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(@Nullable String p0, @Nullable View p1, @Nullable JDFailReason p2) {
                DragWithCloseImageView.DragViewLoadingListener dragViewLoadingListener;
                dragViewLoadingListener = DragWithCloseImageView.this.dragViewLoadingListener;
                if (dragViewLoadingListener != null) {
                    dragViewLoadingListener.onLoadingFailed();
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(@Nullable String p0, @Nullable View p1) {
                DragWithCloseImageView.DragViewLoadingListener dragViewLoadingListener;
                dragViewLoadingListener = DragWithCloseImageView.this.dragViewLoadingListener;
                if (dragViewLoadingListener != null) {
                    dragViewLoadingListener.onLoadingStarted();
                }
            }
        });
    }

    public final void setDragViewWithCloseListener(@NotNull DragViewWithCloseListener listener) {
        this.clickListener = listener;
    }

    public final void setTopMargin(int topMargin) {
        ViewGroup.LayoutParams layoutParams = this.dragView.getLayoutParams();
        if (layoutParams != null) {
            ((RelativeLayout.LayoutParams) layoutParams).topMargin = topMargin;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
    }

    public DragWithCloseImageView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DragWithCloseImageView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.CLOSE_VIEW_TOP_MARGIN = DPIUtil.dip2px(3.0f);
        this.MOVE_MIN_DISTANCE = 8;
        this.dragView = new SimpleDraweeView(getContext());
        this.closeViewLayout = new LinearLayout(getContext());
        this.closeView = new ImageView(getContext());
    }
}
