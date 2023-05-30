package tv.danmaku.ijk.media.ext.tarns;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.example.widget.media.MeasureHelper;
import tv.danmaku.ijk.media.example.widget.media.TextureRenderView;

/* loaded from: classes11.dex */
public class VideoCoverLayer extends FrameLayout {
    private ImageView imageView;
    private MeasureHelper measureHelper;

    public VideoCoverLayer(Context context) {
        super(context);
        init();
    }

    private void init() {
        ImageView imageView = new ImageView(getContext());
        this.imageView = imageView;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    public void setImageBitmap(Bitmap bitmap) {
        ImageView imageView = this.imageView;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public Bitmap setVideoView(JDVideoView jDVideoView, Bitmap bitmap) {
        Bitmap bitmap2 = null;
        if (jDVideoView == null) {
            return null;
        }
        if (bitmap != null) {
            this.imageView.setImageBitmap(bitmap);
        } else if ((jDVideoView.mRenderView instanceof TextureRenderView) && jDVideoView.getHeight() > 0 && jDVideoView.getWidth() > 0 && Build.VERSION.SDK_INT >= 17) {
            try {
                bitmap2 = ((TextureRenderView) jDVideoView.mRenderView).getBitmap(Bitmap.createBitmap(jDVideoView.getResources().getDisplayMetrics(), jDVideoView.getWidth(), jDVideoView.getHeight(), Bitmap.Config.RGB_565));
                this.imageView.setImageBitmap(bitmap2);
                this.measureHelper = ((TextureRenderView) jDVideoView.mRenderView).getMeasureHelper();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (this.imageView.getParent() != null && (this.imageView.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.imageView.getParent()).removeView(this.imageView);
        }
        if (this.measureHelper != null) {
            addView(this.imageView, new FrameLayout.LayoutParams(this.measureHelper.getMeasuredWidth(), this.measureHelper.getMeasuredHeight(), 17));
        }
        jDVideoView.addView(this, 0, new FrameLayout.LayoutParams(-1, -1, 17));
        return bitmap2;
    }

    public VideoCoverLayer(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public VideoCoverLayer(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }

    @TargetApi(21)
    public VideoCoverLayer(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        init();
    }
}
