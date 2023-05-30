package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.view.adapter.BaseHeaderFooterRecyclerAdapter;
import com.jingdong.app.mall.home.o.a.e;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class ElasticHorizontalRecyclerView extends RecyclerView {

    /* renamed from: k  reason: collision with root package name */
    public static final int f10032k = DPIUtil.dip2px(8.0f);

    /* renamed from: g  reason: collision with root package name */
    private b f10033g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f10034h;

    /* renamed from: i  reason: collision with root package name */
    private float f10035i;

    /* renamed from: j  reason: collision with root package name */
    private float f10036j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f10037g;

        a(int i2) {
            this.f10037g = i2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            View h2;
            View findViewById;
            RecyclerView.Adapter adapter = ElasticHorizontalRecyclerView.this.getAdapter();
            if (!(adapter instanceof BaseHeaderFooterRecyclerAdapter) || (h2 = ((BaseHeaderFooterRecyclerAdapter) adapter).h()) == null || (findViewById = h2.findViewById(R.id.home_shop_more_blank)) == null) {
                return;
            }
            findViewById.setPadding(0, 0, this.f10037g, 0);
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        void onFooterClick();
    }

    public ElasticHorizontalRecyclerView(Context context) {
        super(context);
        this.f10034h = true;
        setNestedScrollingEnabled(false);
        e.a(this);
    }

    private void jump() {
        b bVar = this.f10033g;
        if (bVar != null) {
            bVar.onFooterClick();
        }
    }

    public boolean a(float f2) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        return (layoutManager instanceof LinearLayoutManager) && ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() == getAdapter().getItemCount() - 1 && f2 >= ((float) f10032k);
    }

    public void b(b bVar) {
        this.f10033g = bVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f10034h) {
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Throwable unused) {
                return false;
            }
        }
        if (this.f10035i == -1.0f) {
            this.f10035i = motionEvent.getRawX();
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    float rawX = motionEvent.getRawX() - this.f10035i;
                    this.f10035i = motionEvent.getRawX();
                    try {
                        RecyclerView.LayoutManager layoutManager = getLayoutManager();
                        if ((layoutManager instanceof LinearLayoutManager) && ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() == getAdapter().getItemCount() - 1) {
                            float f2 = this.f10036j + ((-rawX) / 1.5f);
                            this.f10036j = f2;
                            int i2 = f10032k;
                            if (f2 > i2) {
                                this.f10036j = i2;
                            }
                            resetMoreView((int) this.f10036j);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else if (action == 5) {
                    this.f10035i = motionEvent.getRawX();
                } else if (action != 6) {
                    resetMoreView(0);
                    this.f10035i = -1.0f;
                    this.f10036j = 1.0f;
                }
            }
            try {
                if (getLayoutManager() != null) {
                    if (a(this.f10036j)) {
                        jump();
                    }
                    resetMoreView(0);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.f10036j = 1.0f;
            this.f10035i = -1.0f;
        } else {
            this.f10035i = motionEvent.getRawX();
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Throwable unused2) {
            return false;
        }
    }

    public void resetMoreView(int i2) {
        f.E0(new a(i2));
    }

    public void setGoRedirect(boolean z) {
        this.f10034h = z;
    }
}
