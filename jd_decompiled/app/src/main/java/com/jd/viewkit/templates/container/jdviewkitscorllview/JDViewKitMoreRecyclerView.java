package com.jd.viewkit.templates.container.jdviewkitscorllview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitScorllView;

/* loaded from: classes18.dex */
public class JDViewKitMoreRecyclerView extends RecyclerView {
    private static final int DRAG = 1;
    private static final int NONE = 0;
    private static final float OFFSET_RADIO = 1.5f;
    private static final String TAG = "JDViewKitMoreRecyclerView";
    private static final int ZOOM = 2;
    protected int FOOTER_WIDTH;
    private boolean checkForReset;
    private boolean goRedirect;
    private boolean isSwiping;
    protected int mItemWidth;
    private float mLastMotionX;
    private float mLastMotionY;
    float mLastX;
    private OnItemClickLitener mOnItemClickLitener;
    private int mode;
    private RecyclerView.OnScrollListener onScrollListener;
    float width;

    /* loaded from: classes18.dex */
    public interface OnItemClickLitener {
        void onItemClick();
    }

    public JDViewKitMoreRecyclerView(Context context) {
        super(context);
        this.goRedirect = true;
        this.checkForReset = false;
        this.mode = 0;
        this.onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitMoreRecyclerView.1
            {
                JDViewKitMoreRecyclerView.this = this;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (i2 == 0 && JDViewKitMoreRecyclerView.this.checkForReset) {
                    JDViewKitMoreRecyclerView.this.checkForReset();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                super.onScrolled(recyclerView, i2, i3);
            }
        };
        initView();
    }

    public void checkForReset() {
        int left = getChildAt(0).getLeft();
        if (left == 0) {
            return;
        }
        if (Math.abs(left) > (this.mItemWidth >> 1)) {
            if (((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition() < getAdapter().getItemCount() - 1) {
                smoothScrollBy(this.mItemWidth - Math.abs(left), 0);
                return;
            }
            return;
        }
        smoothScrollBy(-Math.abs(left), 0);
    }

    private void initView() {
        setFocusable(false);
        setOnScrollListener(this.onScrollListener);
        this.mItemWidth = 100;
        this.FOOTER_WIDTH = 20;
    }

    private void jump() {
        OnItemClickLitener onItemClickLitener = this.mOnItemClickLitener;
        if (onItemClickLitener != null) {
            onItemClickLitener.onItemClick();
        }
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void resetMoreView(final int i2) {
        post(new Runnable() { // from class: com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitMoreRecyclerView.2
            {
                JDViewKitMoreRecyclerView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((JDViewKitScorllView.MyRecyclerAdapter) JDViewKitMoreRecyclerView.this.getAdapter()).getView().setPadding(0, 0, i2, 0);
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            requestParentDisallowInterceptTouchEvent(true);
        } else if (action == 1) {
            this.isSwiping = false;
        } else if (action == 2) {
            float abs = Math.abs(x - this.mLastMotionX);
            float abs2 = Math.abs(y - this.mLastMotionY);
            if (!this.isSwiping && abs <= abs2) {
                requestParentDisallowInterceptTouchEvent(false);
            }
            this.isSwiping = true;
        } else if (action == 3) {
            this.isSwiping = false;
        }
        this.mLastMotionX = x;
        this.mLastMotionY = y;
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:89:0x002e, code lost:
        if (r0 != 6) goto L93;
     */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instructions count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitMoreRecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setCheckForReset(boolean z) {
        this.checkForReset = z;
    }

    public void setGoRedirect(boolean z) {
        this.goRedirect = z;
    }

    public void setOnLastItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i2) {
        if (i2 == 1) {
            return false;
        }
        return super.startNestedScroll(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, androidx.core.view.NestedScrollingChild2
    public boolean startNestedScroll(int i2, int i3) {
        if (i2 == 1) {
            return false;
        }
        return super.startNestedScroll(i2, i3);
    }

    public JDViewKitMoreRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.goRedirect = true;
        this.checkForReset = false;
        this.mode = 0;
        this.onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitMoreRecyclerView.1
            {
                JDViewKitMoreRecyclerView.this = this;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (i2 == 0 && JDViewKitMoreRecyclerView.this.checkForReset) {
                    JDViewKitMoreRecyclerView.this.checkForReset();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                super.onScrolled(recyclerView, i2, i3);
            }
        };
        initView();
    }
}
