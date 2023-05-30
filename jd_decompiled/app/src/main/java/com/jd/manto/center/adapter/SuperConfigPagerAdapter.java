package com.jd.manto.center.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.manto.center.k.c;
import com.jd.manto.center.k.h;
import com.jd.manto.center.model.entity.Banner;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes17.dex */
public class SuperConfigPagerAdapter extends PagerAdapter {
    private View a;
    private View b;

    /* renamed from: c  reason: collision with root package name */
    private Context f6515c;
    private List<Banner> d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f6516e;

    /* renamed from: f  reason: collision with root package name */
    private b f6517f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Banner f6518g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f6519h;

        a(Banner banner, int i2) {
            this.f6518g = banner;
            this.f6519h = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SuperConfigPagerAdapter.this.f6517f != null) {
                SuperConfigPagerAdapter.this.f6517f.a(this.f6518g, this.f6519h);
            }
        }
    }

    /* loaded from: classes17.dex */
    public interface b {
        void a(Banner banner, int i2);
    }

    public SuperConfigPagerAdapter(Context context, List<Banner> list, boolean z, b bVar) {
        this.f6515c = context;
        this.d = list;
        this.f6516e = z;
        this.f6517f = bVar;
    }

    private View b(Context context) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        simpleDraweeView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        h.g(context, simpleDraweeView, c.a(12.0f));
        return simpleDraweeView;
    }

    private void c(View view, int i2) {
        if (!com.jd.manto.center.k.b.c(this.d) || i2 >= this.d.size() || this.d.get(i2) == null) {
            return;
        }
        Banner banner = this.d.get(i2);
        if (view instanceof SimpleDraweeView) {
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            jDDisplayImageOptions.isScale(false);
            JDImageUtils.displayImage(banner.imgUrl, (ImageView) view, jDDisplayImageOptions);
            view.setOnClickListener(new a(banner, i2));
        }
    }

    private void d() {
        if (this.f6516e) {
            if (this.a == null) {
                this.a = b(this.f6515c);
            }
            if (this.b == null) {
                this.b = b(this.f6515c);
            }
            c(this.b, getRealIndex(com.jd.manto.center.k.b.c(this.d) ? this.d.size() - 1 : 0));
        }
    }

    private int getRealIndex(int i2) {
        int size = com.jd.manto.center.k.b.c(this.d) ? this.d.size() : 0;
        if (!this.f6516e || size <= 1) {
            return i2;
        }
        int i3 = (i2 - 1) % size;
        return i3 < 0 ? i3 + size : i3;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<Banner> list = this.d;
        if (list != null) {
            return (this.f6516e ? 2 : 0) + list.size();
        }
        return 0;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(@NonNull Object obj) {
        return -1;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i2) {
        View b2;
        try {
            boolean z = this.f6516e;
            if (!z || (b2 = this.a) == null || i2 != 1) {
                if (z && this.b != null && i2 == getCount() - 2) {
                    b2 = this.b;
                } else {
                    b2 = b(this.f6515c);
                }
            }
            b2.setId(i2);
            if (b2.getParent() == null) {
                viewGroup.addView(b2);
            }
            c(b2, getRealIndex(i2));
            return b2;
        } catch (Exception unused) {
            return new View(this.f6515c);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        d();
        super.notifyDataSetChanged();
    }
}
