package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.R;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.NetUtils;

/* loaded from: classes4.dex */
public class CarouseFigureImagePagerAdapter extends PagerAdapter {
    protected static final int LAST_URL = R.id.image_last_url;
    private IAccessibilityTextListener accessibilityTextListener;
    private Context context;
    private int cornerRadius;
    protected JDDisplayImageOptions displayOptions;
    private ImageView first;
    private CarouseFigureImageAdapterListener imageAdapterListener;
    private boolean isAllChange;
    private boolean isCarousel;
    private ImageView last;

    /* loaded from: classes4.dex */
    public interface CarouseFigureImageAdapterListener {
        int getCount();

        String getImageUrl(int i2);

        JDDisplayImageOptions getJDDisplayImageOptions();

        void onClick(int i2);
    }

    /* loaded from: classes4.dex */
    public interface IAccessibilityTextListener {
        String getAccessibilityText(int i2);
    }

    public CarouseFigureImagePagerAdapter(Context context, boolean z, CarouseFigureImageAdapterListener carouseFigureImageAdapterListener) {
        this(context, z, 0, carouseFigureImageAdapterListener);
    }

    private ImageView getImageView() {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.context);
        simpleDraweeView.setPadding(0, 0, 0, 0);
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NetUtils.isNetworkAvailable()) {
                    CarouseFigureImagePagerAdapter.this.imageAdapterListener.onClick(CarouseFigureImagePagerAdapter.this.getRealIndex(view.getId()));
                }
            }
        });
        return simpleDraweeView;
    }

    private void initImage() {
        if (isTrueCarousel()) {
            if (this.first == null) {
                this.first = getImageView();
            }
            if (this.last == null) {
                this.last = getImageView();
            }
            displayImage(this.last, this.imageAdapterListener.getImageUrl(r1.getCount() - 1), this.imageAdapterListener.getJDDisplayImageOptions());
            IAccessibilityTextListener iAccessibilityTextListener = this.accessibilityTextListener;
            if (iAccessibilityTextListener == null || this.last == null) {
                return;
            }
            String accessibilityText = iAccessibilityTextListener.getAccessibilityText(this.imageAdapterListener.getCount() - 1);
            if (TextUtils.isEmpty(accessibilityText)) {
                return;
            }
            this.last.setContentDescription(accessibilityText);
        }
    }

    private boolean isTrueCarousel() {
        CarouseFigureImageAdapterListener carouseFigureImageAdapterListener = this.imageAdapterListener;
        return carouseFigureImageAdapterListener != null && this.isCarousel && carouseFigureImageAdapterListener.getCount() >= 2;
    }

    private void requestViewOfAccessibilityFocus(final View view) {
        if (view == null) {
            return;
        }
        view.postDelayed(new Runnable() { // from class: com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                View view2 = view;
                if (view2 != null) {
                    view2.sendAccessibilityEvent(128);
                }
            }
        }, 150L);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public void displayImage(ImageView imageView, String str, JDDisplayImageOptions jDDisplayImageOptions) {
        if (this.displayOptions == null) {
            if (jDDisplayImageOptions == null) {
                this.displayOptions = new JDDisplayImageOptions().resetViewBeforeLoading(false).setPlaceholder(21);
            } else {
                this.displayOptions = jDDisplayImageOptions;
            }
            this.displayOptions.displayer(new JDRoundedBitmapDisplayer(this.cornerRadius));
        }
        this.displayOptions.bitmapConfig(Bitmap.Config.RGB_565);
        int i2 = LAST_URL;
        if (imageView.getTag(i2) != null && str != null && str.equals(imageView.getTag(i2))) {
            int i3 = JDImageUtils.STATUS_TAG;
            if (imageView.getTag(i3) == null || !imageView.getTag(i3).equals(3)) {
                return;
            }
        }
        if (imageView.getTag(i2) != null) {
            JDImageUtils.displayImage(str, imageView, this.displayOptions, false);
        } else {
            JDImageUtils.displayImage(str, imageView, this.displayOptions, true);
        }
        imageView.setTag(i2, str);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        CarouseFigureImageAdapterListener carouseFigureImageAdapterListener = this.imageAdapterListener;
        if (carouseFigureImageAdapterListener != null) {
            return carouseFigureImageAdapterListener.getCount() + (isTrueCarousel() ? 2 : 0);
        }
        return 0;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return this.isAllChange ? -2 : -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRealIndex(int i2) {
        if (!this.isCarousel || this.imageAdapterListener.getCount() <= 1) {
            return i2;
        }
        int count = (i2 - 1) % this.imageAdapterListener.getCount();
        return count < 0 ? count + this.imageAdapterListener.getCount() : count;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        ImageView simpleDraweeView;
        try {
            boolean z = this.isCarousel;
            if (!z || (simpleDraweeView = this.first) == null || i2 != 1) {
                if (z && this.last != null && i2 == getCount() - 2) {
                    simpleDraweeView = this.last;
                } else {
                    simpleDraweeView = getImageView();
                }
            }
            simpleDraweeView.setId(i2);
            viewGroup.addView(simpleDraweeView);
            displayImage(simpleDraweeView, this.imageAdapterListener.getImageUrl(getRealIndex(i2)), this.imageAdapterListener.getJDDisplayImageOptions());
            IAccessibilityTextListener iAccessibilityTextListener = this.accessibilityTextListener;
            if (iAccessibilityTextListener != null && simpleDraweeView != null) {
                CharSequence accessibilityText = iAccessibilityTextListener.getAccessibilityText(getRealIndex(i2));
                if (!TextUtils.isEmpty(accessibilityText)) {
                    simpleDraweeView.setContentDescription(accessibilityText);
                }
            }
        } catch (Exception unused) {
            simpleDraweeView = new SimpleDraweeView(this.context);
        }
        requestViewOfAccessibilityFocus(simpleDraweeView);
        if (this.isAllChange) {
            this.isAllChange = false;
        }
        return simpleDraweeView;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        initImage();
        super.notifyDataSetChanged();
    }

    public void setAccessibilityTextListener(IAccessibilityTextListener iAccessibilityTextListener) {
        this.accessibilityTextListener = iAccessibilityTextListener;
    }

    public void setAllChanged(boolean z) {
        this.isAllChange = z;
    }

    public CarouseFigureImagePagerAdapter(Context context, boolean z, int i2, CarouseFigureImageAdapterListener carouseFigureImageAdapterListener) {
        this.context = context;
        this.isCarousel = z;
        this.imageAdapterListener = carouseFigureImageAdapterListener;
        this.cornerRadius = i2;
        initImage();
    }
}
