package com.jingdong.common.recommend;

import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.recommend.forlist.RecommendViewHolder;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class RecommendExpoHelper {
    private RecyclerView childRecyclerView;
    private RecyclerView.OnScrollListener expoScrollListener;
    public RecyclerView.OnItemTouchListener itemTouchListener;
    public RecyclerView nestedParentView;
    public boolean scrollByTouch = true;
    private boolean isRightExpo = false;
    boolean canExpo = true;
    boolean isAdRealExpo = false;

    public RecommendExpoHelper(RecyclerView recyclerView) {
        this.childRecyclerView = recyclerView;
        if (this.expoScrollListener == null) {
            this.expoScrollListener = new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.recommend.RecommendExpoHelper.1
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView2, int i2) {
                    super.onScrollStateChanged(recyclerView2, i2);
                    RecommendExpoHelper.this.onViewScrollStateChange();
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(@NonNull RecyclerView recyclerView2, int i2, int i3) {
                    super.onScrolled(recyclerView2, i2, i3);
                    RecommendExpoHelper.this.onViewScroll();
                }
            };
        }
        if (this.itemTouchListener == null) {
            this.itemTouchListener = new RecyclerView.OnItemTouchListener() { // from class: com.jingdong.common.recommend.RecommendExpoHelper.2
                @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView2, @NonNull MotionEvent motionEvent) {
                    RecommendExpoHelper.this.onViewTouch(motionEvent);
                    return false;
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                public void onRequestDisallowInterceptTouchEvent(boolean z) {
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                public void onTouchEvent(@NonNull RecyclerView recyclerView2, @NonNull MotionEvent motionEvent) {
                }
            };
        }
        RecyclerView recyclerView2 = this.childRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addOnScrollListener(this.expoScrollListener);
            this.childRecyclerView.addOnItemTouchListener(this.itemTouchListener);
        }
    }

    public void dealExpoView(RecyclerView recyclerView) {
        try {
            if (this.canExpo && recyclerView != null && recyclerView.isAttachedToWindow()) {
                int childCount = recyclerView.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = recyclerView.getChildAt(i2);
                    RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
                    if (childViewHolder instanceof RecommendViewHolder) {
                        RecommendViewHolder recommendViewHolder = (RecommendViewHolder) childViewHolder;
                        double currentExpoPercent = RecommendViewUtil.getCurrentExpoPercent(childAt);
                        if (this.isAdRealExpo && currentExpoPercent > 0.0d) {
                            recommendViewHolder.doAdRealExpo();
                        }
                        if (!recommendViewHolder.needRealExpo()) {
                            continue;
                        } else if (currentExpoPercent < 0.5d) {
                            return;
                        } else {
                            if (this.isRightExpo) {
                                ((RecommendViewHolder) childViewHolder).doRealRightExpo();
                            } else {
                                ((RecommendViewHolder) childViewHolder).doRealExpo();
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public View getNestedView() {
        RecyclerView recyclerView = this.nestedParentView;
        return recyclerView != null ? recyclerView : this.childRecyclerView;
    }

    public void onViewScroll() {
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        if (this.scrollByTouch) {
            if ((this.childRecyclerView.getScrollState() == 0 && ((recyclerView2 = this.nestedParentView) == null || recyclerView2.getScrollState() == 0)) || (recyclerView = this.childRecyclerView) == null || !recyclerView.isAttachedToWindow()) {
                return;
            }
            dealExpoView(this.childRecyclerView);
        }
    }

    public void onViewScrollStateChange() {
        RecyclerView recyclerView = this.nestedParentView;
        if (recyclerView != null) {
            if (recyclerView.getScrollState() == 0 && this.childRecyclerView.getScrollState() == 0) {
                this.scrollByTouch = false;
            }
        } else if (this.childRecyclerView.getScrollState() == 0) {
            this.scrollByTouch = false;
        }
    }

    public void onViewTouch(MotionEvent motionEvent) {
        if (motionEvent == null || motionEvent.getAction() != 0) {
            return;
        }
        this.scrollByTouch = true;
    }

    public void parentViewBindExpo() {
        RecyclerView recyclerView = this.nestedParentView;
        if (recyclerView != null) {
            recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() { // from class: com.jingdong.common.recommend.RecommendExpoHelper.3
                @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView2, @NonNull MotionEvent motionEvent) {
                    if (motionEvent == null || motionEvent.getAction() != 0) {
                        return false;
                    }
                    RecommendExpoHelper.this.onViewTouch(motionEvent);
                    return false;
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                public void onRequestDisallowInterceptTouchEvent(boolean z) {
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                public void onTouchEvent(@NonNull RecyclerView recyclerView2, @NonNull MotionEvent motionEvent) {
                }
            });
            this.nestedParentView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.recommend.RecommendExpoHelper.4
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView2, int i2) {
                    super.onScrollStateChanged(recyclerView2, i2);
                    RecommendExpoHelper.this.onViewScrollStateChange();
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(@NonNull RecyclerView recyclerView2, int i2, int i3) {
                    super.onScrolled(recyclerView2, i2, i3);
                    RecommendExpoHelper.this.onViewScroll();
                }
            });
        }
    }

    public void setAdRealExpo(boolean z) {
        this.isAdRealExpo = z;
    }

    public void setCanExpo(boolean z) {
        this.canExpo = z;
    }

    public void setNestedParentView(RecyclerView recyclerView) {
        this.nestedParentView = recyclerView;
    }

    public void setRightExpo(boolean z) {
        this.isRightExpo = z;
    }

    public void dealExpoView() {
        dealExpoView(this.childRecyclerView);
    }
}
