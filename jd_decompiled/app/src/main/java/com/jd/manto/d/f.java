package com.jd.manto.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jingdong.manto.sdk.api.IImageLoader;

/* loaded from: classes17.dex */
public class f implements IImageLoader {

    /* loaded from: classes17.dex */
    class a extends BaseBitmapDataSubscriber {
        final /* synthetic */ ImageView a;

        /* renamed from: com.jd.manto.d.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes17.dex */
        class RunnableC0190a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ Bitmap f6652g;

            RunnableC0190a(Bitmap bitmap) {
                this.f6652g = bitmap;
            }

            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmap = this.f6652g;
                if (bitmap != null) {
                    a.this.a.setImageBitmap(bitmap);
                }
            }
        }

        a(f fVar, ImageView imageView) {
            this.a = imageView;
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onCancelImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        }

        @Override // com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
        protected void onNewResultImpl(Bitmap bitmap) {
            com.jingdong.a.f().mainThread().execute(new RunnableC0190a(bitmap));
        }
    }

    /* loaded from: classes17.dex */
    class b extends BaseBitmapDataSubscriber {
        final /* synthetic */ IImageLoader.ImageLoaderCallback a;

        b(f fVar, IImageLoader.ImageLoaderCallback imageLoaderCallback) {
            this.a = imageLoaderCallback;
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onCancelImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            IImageLoader.ImageLoaderCallback imageLoaderCallback = this.a;
            if (imageLoaderCallback != null) {
                imageLoaderCallback.onFail();
            }
        }

        @Override // com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
        protected void onNewResultImpl(Bitmap bitmap) {
            IImageLoader.ImageLoaderCallback imageLoaderCallback = this.a;
            if (imageLoaderCallback != null) {
                imageLoaderCallback.onSuccess(bitmap);
            }
        }
    }

    @Override // com.jingdong.manto.sdk.api.IImageLoader
    public void loadImage(ImageView imageView, String str) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build(), imageView.getContext().getApplicationContext()).subscribe(new a(this, imageView), CallerThreadExecutor.getInstance());
    }

    @Override // com.jingdong.manto.sdk.api.IImageLoader
    public void loadImage(Context context, String str, IImageLoader.ImageLoaderCallback imageLoaderCallback) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build(), context).subscribe(new b(this, imageLoaderCallback), CallerThreadExecutor.getInstance());
    }
}
