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

    /* JADX WARN: Code restructure failed: missing block: B:157:0x002e, code lost:
        if (r0 != 6) goto L161;
     */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.goRedirect) {
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Throwable unused) {
                return false;
            }
        }
        if (this.mLastX == -1.0f) {
            this.mLastX = motionEvent.getRawX();
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action == 3) {
                        resetMoreView(0);
                    } else if (action == 5) {
                        this.mLastX = motionEvent.getRawX();
                        this.mode = 2;
                    }
                    resetMoreView(0);
                    this.mLastX = -1.0f;
                    this.width = 1.0f;
                } else {
                    if (this.mode == 1) {
                        motionEvent.getRawX();
                    } else if (motionEvent.getPointerCount() == 2) {
                        Math.min(motionEvent.getX(0) - this.mLastX, motionEvent.getX(1) - this.mLastX);
                    }
                    float rawX = motionEvent.getRawX() - this.mLastX;
                    this.mLastX = motionEvent.getRawX();
                    try {
                        if (getLayoutManager() != null && ((LinearLayoutManager) getLayoutManager()).findLastCompletelyVisibleItemPosition() == getAdapter().getItemCount() - 1) {
                            float f2 = this.width + ((-rawX) / OFFSET_RADIO);
                            this.width = f2;
                            int i2 = this.FOOTER_WIDTH;
                            if (f2 > i2) {
                                this.width = i2;
                            }
                            resetMoreView((int) this.width);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            try {
                if (getLayoutManager() != null) {
                    if (((LinearLayoutManager) getLayoutManager()).findLastCompletelyVisibleItemPosition() == getAdapter().getItemCount() - 1 && this.width >= this.FOOTER_WIDTH) {
                        jump();
                    }
                    resetMoreView(0);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.width = 1.0f;
            this.mLastX = -1.0f;
        } else {
            this.mLastX = motionEvent.getRawX();
            this.mode = 1;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Throwable unused2) {
            return false;
        }
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
