package com.jingdong.jdsdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.StringRes;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.EncodedImage;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.RemoteImageUtil;
import com.jingdong.remoteimage.RemoteImageManager;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.wireless.iconfont.IconDrawable;
import com.jingdong.wireless.iconfont.IconGradientDrawable;
import com.jingdong.wireless.iconfont.widget.IconImpl;
import java.io.File;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\b"}, d2 = {"Lcom/jingdong/jdsdk/utils/RemoteImageUtil;", "", "<init>", "()V", "Companion", "ImageRequestCallBack", "ImageRequestResultBack", "TextViewType", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class RemoteImageUtil {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @NotNull
    public static final String TAG = "RemoteImageUtil";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\bB\u0010CJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ!\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ-\u0010\u000b\u001a\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\t\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u000b\u0010\u0010JO\u0010\u0018\u001a\u00020\u00172\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u00022\b\b\u0001\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\b\b\u0001\u0010\u0014\u001a\u00020\u000f2\b\b\u0001\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0019J+\u0010\u001b\u001a\u00020\u001a2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u00022\b\b\u0001\u0010\u0012\u001a\u00020\u000fH\u0007\u00a2\u0006\u0004\b\u001b\u0010\u001cJ-\u0010\"\u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0007\u00a2\u0006\u0004\b\"\u0010#J7\u0010(\u001a\u00020!2\b\u0010%\u001a\u0004\u0018\u00010$2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010&\u001a\u0004\u0018\u00010\u001f2\b\b\u0001\u0010'\u001a\u00020\u000fH\u0007\u00a2\u0006\u0004\b(\u0010)JA\u0010*\u001a\u00020!2\b\u0010%\u001a\u0004\u0018\u00010$2\b\u0010\n\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u00022\b\u0010&\u001a\u0004\u0018\u00010\u001f2\b\b\u0001\u0010'\u001a\u00020\u000fH\u0007\u00a2\u0006\u0004\b*\u0010+J-\u0010,\u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0007\u00a2\u0006\u0004\b,\u0010#J7\u0010,\u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010 \u001a\u0004\u0018\u00010\u001f2\b\u0010.\u001a\u0004\u0018\u00010-H\u0007\u00a2\u0006\u0004\b,\u0010/JW\u00107\u001a\u00020!2\b\u00101\u001a\u0004\u0018\u0001002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u00102\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u000f2\u0006\u00104\u001a\u00020\u000f2\b\b\u0001\u0010'\u001a\u00020\u000f2\u0006\u00106\u001a\u000205H\u0007\u00a2\u0006\u0004\b7\u00108J=\u0010;\u001a\u00020!2\b\u0010%\u001a\u0004\u0018\u00010$2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010&\u001a\u0004\u0018\u00010\u001f2\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000fH\u0007\u00a2\u0006\u0004\b;\u0010<J\u0019\u0010>\u001a\u0004\u0018\u00010=2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b@\u0010A\u00a8\u0006D"}, d2 = {"Lcom/jingdong/jdsdk/utils/RemoteImageUtil$Companion;", "", "", "url", "", "isUrl", "(Ljava/lang/String;)Z", "getUseDefaultSwitch", "()Z", "ivId", "moduleId", "getRemoteImageUrl", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "(Landroid/content/Context;ILjava/lang/String;)Ljava/lang/String;", "ttfPath", "sourceId", "strokeWidth", "strokeColor", "solidColor", "dpSize", "Lcom/jingdong/wireless/iconfont/IconGradientDrawable;", "getDefaultDrawableHasBg", "(Landroid/content/Context;Ljava/lang/String;IIIII)Lcom/jingdong/wireless/iconfont/IconGradientDrawable;", "Lcom/jingdong/wireless/iconfont/IconDrawable;", "getDefaultDrawable", "(Landroid/content/Context;Ljava/lang/String;I)Lcom/jingdong/wireless/iconfont/IconDrawable;", "Landroid/view/View;", "v", "Landroid/graphics/drawable/Drawable;", "defaultDrawable", "", "setViewBg", "(Landroid/view/View;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)V", "Lcom/facebook/drawee/view/SimpleDraweeView;", "imageView", "defaultImage", "color", "setImageUrl", "(Lcom/facebook/drawee/view/SimpleDraweeView;Ljava/lang/String;Landroid/graphics/drawable/Drawable;I)V", "setImageUrlFromXjs", "(Lcom/facebook/drawee/view/SimpleDraweeView;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;I)V", "setViewNinePathDrawBg", "Lcom/jingdong/jdsdk/utils/RemoteImageUtil$ImageRequestResultBack;", "callBack", "(Landroid/view/View;Ljava/lang/String;Landroid/graphics/drawable/Drawable;Lcom/jingdong/jdsdk/utils/RemoteImageUtil$ImageRequestResultBack;)V", "Landroid/widget/TextView;", "textView", "width", "height", "padding", "Lcom/jingdong/jdsdk/utils/RemoteImageUtil$TextViewType;", "type", "setTextViewImage", "(Landroid/widget/TextView;Ljava/lang/String;Landroid/graphics/drawable/Drawable;IIIILcom/jingdong/jdsdk/utils/RemoteImageUtil$TextViewType;)V", "defaultW", "defaultH", "setImageWidthWrap", "(Lcom/facebook/drawee/view/SimpleDraweeView;Ljava/lang/String;Landroid/graphics/drawable/Drawable;II)V", "Landroid/graphics/Bitmap;", "getCacheData", "(Ljava/lang/String;)Landroid/graphics/Bitmap;", "TAG", "Ljava/lang/String;", "<init>", "()V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class Companion {

        @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
        /* loaded from: classes14.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TextViewType.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[TextViewType.LEFT.ordinal()] = 1;
                iArr[TextViewType.TOP.ordinal()] = 2;
                iArr[TextViewType.RIGHT.ordinal()] = 3;
                iArr[TextViewType.BOTTOM.ordinal()] = 4;
            }
        }

        private Companion() {
        }

        private final boolean getUseDefaultSwitch() {
            return TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDSearch", "iconFontUseDefaultSwitch", "useDefault", "0"), "1");
        }

        /* JADX WARN: Code restructure failed: missing block: B:6:0x0011, code lost:
            if (r5 != false) goto L7;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final boolean isUrl(java.lang.String r5) {
            /*
                r4 = this;
                boolean r0 = android.text.TextUtils.isEmpty(r5)
                r1 = 0
                if (r0 != 0) goto L1c
                if (r5 == 0) goto L13
                r0 = 2
                r2 = 0
                java.lang.String r3 = "http"
                boolean r5 = kotlin.text.StringsKt.contains$default(r5, r3, r1, r0, r2)
                if (r5 == 0) goto L1c
            L13:
                boolean r5 = r4.getUseDefaultSwitch()
                if (r5 == 0) goto L1a
                goto L1c
            L1a:
                r5 = 1
                return r5
            L1c:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.utils.RemoteImageUtil.Companion.isUrl(java.lang.String):boolean");
        }

        @Nullable
        public final Bitmap getCacheData(@Nullable String url) {
            File imageDiskCacheFile;
            if (TextUtils.isEmpty(url) || (imageDiskCacheFile = JDImageUtils.getImageDiskCacheFile(url)) == null || TextUtils.isEmpty(imageDiskCacheFile.getPath())) {
                return null;
            }
            return BitmapFactory.decodeFile(imageDiskCacheFile.getPath());
        }

        @JvmStatic
        @NotNull
        public final IconDrawable getDefaultDrawable(@Nullable Context context, @NotNull String ttfPath, @StringRes int sourceId) {
            return new IconDrawable(context, new IconImpl("", context != null ? context.getString(sourceId) : null), ttfPath);
        }

        @JvmStatic
        @NotNull
        public final IconGradientDrawable getDefaultDrawableHasBg(@Nullable Context context, @NotNull String ttfPath, @StringRes int sourceId, int strokeWidth, @ColorInt int strokeColor, @ColorInt int solidColor, int dpSize) {
            IconGradientDrawable iconGradientDrawable = new IconGradientDrawable(context, new IconImpl("", context != null ? context.getString(sourceId) : null), ttfPath);
            if (strokeWidth > 0) {
                iconGradientDrawable.setStroke(strokeWidth, strokeColor);
            }
            iconGradientDrawable.setColor(solidColor);
            iconGradientDrawable.sizeDp(dpSize);
            return iconGradientDrawable;
        }

        @JvmStatic
        @Nullable
        public final String getRemoteImageUrl(@NotNull String ivId, @NotNull String moduleId) {
            return RemoteImageManager.getImageUrlById(moduleId + '_' + ivId);
        }

        @JvmStatic
        public final void setImageUrl(@Nullable SimpleDraweeView imageView, @Nullable String url, @Nullable Drawable defaultImage, @ColorInt int color) {
            if (imageView == null) {
                return;
            }
            if (color != -1) {
                imageView.setColorFilter(color);
            } else {
                imageView.clearColorFilter();
            }
            if (!isUrl(url)) {
                imageView.setImageDrawable(defaultImage);
                return;
            }
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            jDDisplayImageOptions.showImageOnLoading(defaultImage);
            jDDisplayImageOptions.showImageOnFail(defaultImage);
            JDImageUtils.displayImage(url, imageView, jDDisplayImageOptions);
        }

        @JvmStatic
        public final void setImageUrlFromXjs(@Nullable SimpleDraweeView imageView, @Nullable String moduleId, @Nullable String ivId, @Nullable Drawable defaultImage, @ColorInt int color) {
            String str = "";
            if (imageView == null) {
                return;
            }
            try {
                if (!TextUtils.isEmpty(moduleId) && !TextUtils.isEmpty(ivId)) {
                    str = RemoteImageManager.getImageUrlById(moduleId + '_' + ivId);
                }
            } catch (Exception unused) {
            }
            if (color != -1) {
                imageView.setColorFilter(color);
            } else {
                imageView.clearColorFilter();
            }
            if (!isUrl(str)) {
                imageView.setImageDrawable(defaultImage);
                return;
            }
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            jDDisplayImageOptions.showImageOnLoading(defaultImage);
            jDDisplayImageOptions.showImageOnFail(defaultImage);
            JDImageUtils.displayImage(str, imageView, jDDisplayImageOptions);
        }

        @JvmStatic
        public final void setImageWidthWrap(@Nullable final SimpleDraweeView imageView, @Nullable String url, @Nullable final Drawable defaultImage, final int defaultW, final int defaultH) {
            if (imageView == null) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = defaultW;
            }
            ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams2.height = defaultH;
            }
            if (!isUrl(url)) {
                imageView.setImageDrawable(defaultImage);
            } else {
                JDImageLoader.getBitmap(url, null, new ImageRequestListener<Bitmap>() { // from class: com.jingdong.jdsdk.utils.RemoteImageUtil$Companion$setImageWidthWrap$1
                    @Override // com.jd.mobile.image.ImageRequestListener
                    public void onCancel() {
                    }

                    @Override // com.jd.mobile.image.ImageRequestListener
                    public void onFailure(@Nullable Throwable p0) {
                        SimpleDraweeView simpleDraweeView = imageView;
                        if (simpleDraweeView == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
                        }
                        simpleDraweeView.setImageDrawable(defaultImage);
                    }

                    @Override // com.jd.mobile.image.ImageRequestListener
                    public void onSuccess(@Nullable Bitmap bitmap) {
                        int i2;
                        if (bitmap != null) {
                            try {
                                i2 = (defaultH * bitmap.getWidth()) / bitmap.getHeight();
                            } catch (Exception unused) {
                                i2 = defaultW;
                            }
                            ViewGroup.LayoutParams layoutParams3 = imageView.getLayoutParams();
                            if (layoutParams3 != null) {
                                layoutParams3.width = i2;
                            }
                            SimpleDraweeView simpleDraweeView = imageView;
                            if (simpleDraweeView == null) {
                                throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
                            }
                            Context context = imageView.getContext();
                            simpleDraweeView.setImageDrawable(new BitmapDrawable(context != null ? context.getResources() : null, bitmap));
                            return;
                        }
                        SimpleDraweeView simpleDraweeView2 = imageView;
                        if (simpleDraweeView2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
                        }
                        simpleDraweeView2.setImageDrawable(defaultImage);
                    }
                }, UiThreadImmediateExecutorService.getInstance());
            }
        }

        @JvmStatic
        public final void setTextViewImage(@Nullable final TextView textView, @Nullable String url, @Nullable final Drawable defaultDrawable, int width, int height, int padding, @ColorInt int color, @NotNull TextViewType type) {
            if (textView == null) {
                return;
            }
            textView.setIncludeFontPadding(false);
            final RemoteImageUtil$Companion$setTextViewImage$callBack$1 remoteImageUtil$Companion$setTextViewImage$callBack$1 = new RemoteImageUtil$Companion$setTextViewImage$callBack$1(width, height, color, textView, padding, type);
            if (!isUrl(url)) {
                remoteImageUtil$Companion$setTextViewImage$callBack$1.onCallDrawable(defaultDrawable);
                return;
            }
            remoteImageUtil$Companion$setTextViewImage$callBack$1.onCallDrawable(defaultDrawable);
            JDImageLoader.getBitmap(url, null, new ImageRequestListener<Bitmap>() { // from class: com.jingdong.jdsdk.utils.RemoteImageUtil$Companion$setTextViewImage$1
                @Override // com.jd.mobile.image.ImageRequestListener
                public void onCancel() {
                }

                @Override // com.jd.mobile.image.ImageRequestListener
                public void onFailure(@Nullable Throwable p0) {
                }

                @Override // com.jd.mobile.image.ImageRequestListener
                public void onSuccess(@Nullable Bitmap bitmap) {
                    Drawable bitmapDrawable;
                    if (bitmap == null) {
                        bitmapDrawable = defaultDrawable;
                    } else {
                        Context context = textView.getContext();
                        bitmapDrawable = new BitmapDrawable(context != null ? context.getResources() : null, bitmap);
                    }
                    remoteImageUtil$Companion$setTextViewImage$callBack$1.onCallDrawable(bitmapDrawable);
                }
            }, UiThreadImmediateExecutorService.getInstance());
        }

        @JvmStatic
        public final void setViewBg(@Nullable final View v, @Nullable String url, @Nullable final Drawable defaultDrawable) {
            if (v == null) {
                return;
            }
            if (!isUrl(url)) {
                v.setBackground(defaultDrawable);
                return;
            }
            Bitmap cacheData = getCacheData(url);
            if (cacheData != null) {
                Context context = v.getContext();
                v.setBackground(new BitmapDrawable(context != null ? context.getResources() : null, cacheData));
                return;
            }
            v.setBackground(defaultDrawable);
            JDImageLoader.getBitmap(url, new JDDisplayImageOptions(), new ImageRequestListener<Bitmap>() { // from class: com.jingdong.jdsdk.utils.RemoteImageUtil$Companion$setViewBg$1
                @Override // com.jd.mobile.image.ImageRequestListener
                public void onCancel() {
                }

                @Override // com.jd.mobile.image.ImageRequestListener
                public void onFailure(@Nullable Throwable p0) {
                }

                @Override // com.jd.mobile.image.ImageRequestListener
                public void onSuccess(@Nullable Bitmap bitmap) {
                    Drawable bitmapDrawable;
                    View view = v;
                    if (bitmap == null) {
                        bitmapDrawable = defaultDrawable;
                    } else {
                        Context context2 = v.getContext();
                        bitmapDrawable = new BitmapDrawable(context2 != null ? context2.getResources() : null, bitmap);
                    }
                    view.setBackground(bitmapDrawable);
                }
            }, UiThreadImmediateExecutorService.getInstance());
        }

        @JvmStatic
        public final void setViewNinePathDrawBg(@Nullable View v, @Nullable String url, @Nullable Drawable defaultDrawable) {
            setViewNinePathDrawBg(v, url, defaultDrawable, null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @Nullable
        public final String getRemoteImageUrl(@Nullable Context context, @StringRes int ivId, @NotNull String moduleId) {
            try {
                return RemoteImageManager.getImageUrlById(moduleId + '_' + (context != null ? context.getString(ivId) : null));
            } catch (Exception unused) {
                return "";
            }
        }

        @JvmStatic
        public final void setViewNinePathDrawBg(@Nullable final View v, @Nullable final String url, @Nullable final Drawable defaultDrawable, @Nullable final ImageRequestResultBack callBack) {
            if (v == null) {
                return;
            }
            if (!isUrl(url)) {
                v.setBackground(defaultDrawable);
                if (callBack != null) {
                    callBack.onFailure(new Throwable("\u56fe\u7247\u94fe\u63a5\u5f02\u5e38\u4e3a\u7a7a\u3001\u4e0d\u662f\u4ee5HTTP\u5f00\u5934\uff0c\u6216\u8005\u5f00\u542f\u4e86\u672c\u5730\u515c\u5e95\u5f00\u5173"));
                    return;
                }
                return;
            }
            JDImageLoader.getEncodedImage(url, null, new ImageRequestListener<EncodedImage>() { // from class: com.jingdong.jdsdk.utils.RemoteImageUtil$Companion$setViewNinePathDrawBg$1
                @Override // com.jd.mobile.image.ImageRequestListener
                public void onCancel() {
                }

                @Override // com.jd.mobile.image.ImageRequestListener
                public void onFailure(@Nullable Throwable p0) {
                    v.setBackground(defaultDrawable);
                    if (OKLog.D) {
                        OKLog.e(RemoteImageUtil.TAG, url + " onLoadFailed");
                    }
                    RemoteImageUtil.ImageRequestResultBack imageRequestResultBack = callBack;
                    if (imageRequestResultBack != null) {
                        imageRequestResultBack.onFailure(p0);
                    }
                }

                @Override // com.jd.mobile.image.ImageRequestListener
                public void onSuccess(@Nullable EncodedImage encodedImage) {
                    if (encodedImage != null) {
                        Bitmap decodeStream = BitmapFactory.decodeStream(encodedImage.getInputStream());
                        if (decodeStream != null && decodeStream.getNinePatchChunk() != null && NinePatch.isNinePatchChunk(decodeStream.getNinePatchChunk())) {
                            NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(v.getResources(), decodeStream, decodeStream.getNinePatchChunk(), new Rect(), null);
                            v.setBackground(ninePatchDrawable);
                            RemoteImageUtil.ImageRequestResultBack imageRequestResultBack = callBack;
                            if (imageRequestResultBack != null) {
                                imageRequestResultBack.onCallBitmapSuccess(decodeStream);
                            }
                            RemoteImageUtil.ImageRequestResultBack imageRequestResultBack2 = callBack;
                            if (imageRequestResultBack2 != null) {
                                imageRequestResultBack2.onCallDrawableSuccess(ninePatchDrawable);
                                return;
                            }
                            return;
                        }
                        if (OKLog.D) {
                            OKLog.e(RemoteImageUtil.TAG, url + " .9\u56fe\u7279\u6027\u4e22\u5931");
                        }
                        v.setBackground(defaultDrawable);
                        RemoteImageUtil.ImageRequestResultBack imageRequestResultBack3 = callBack;
                        if (imageRequestResultBack3 != null) {
                            imageRequestResultBack3.onFailure(new Throwable(url + " .9\u56fe\u7279\u6027\u4e22\u5931"));
                        }
                    }
                }
            }, UiThreadImmediateExecutorService.getInstance());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/jdsdk/utils/RemoteImageUtil$ImageRequestCallBack;", "", "Landroid/graphics/drawable/Drawable;", "drawable", "", "onCallDrawable", "(Landroid/graphics/drawable/Drawable;)V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public interface ImageRequestCallBack {
        void onCallDrawable(@Nullable Drawable drawable);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\t\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&\u00a2\u0006\u0004\b\t\u0010\nJ\u0019\u0010\r\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bH&\u00a2\u0006\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/jingdong/jdsdk/utils/RemoteImageUtil$ImageRequestResultBack;", "", "Landroid/graphics/drawable/Drawable;", "drawable", "", "onCallDrawableSuccess", "(Landroid/graphics/drawable/Drawable;)V", "", "throwable", "onFailure", "(Ljava/lang/Throwable;)V", "Landroid/graphics/Bitmap;", "bitmap", "onCallBitmapSuccess", "(Landroid/graphics/Bitmap;)V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public interface ImageRequestResultBack {
        void onCallBitmapSuccess(@Nullable Bitmap bitmap);

        void onCallDrawableSuccess(@Nullable Drawable drawable);

        void onFailure(@Nullable Throwable throwable);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/jingdong/jdsdk/utils/RemoteImageUtil$TextViewType;", "", "<init>", "(Ljava/lang/String;I)V", "LEFT", "TOP", "RIGHT", "BOTTOM", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public enum TextViewType {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    @JvmStatic
    @NotNull
    public static final IconDrawable getDefaultDrawable(@Nullable Context context, @NotNull String str, @StringRes int i2) {
        return INSTANCE.getDefaultDrawable(context, str, i2);
    }

    @JvmStatic
    @NotNull
    public static final IconGradientDrawable getDefaultDrawableHasBg(@Nullable Context context, @NotNull String str, @StringRes int i2, int i3, @ColorInt int i4, @ColorInt int i5, int i6) {
        return INSTANCE.getDefaultDrawableHasBg(context, str, i2, i3, i4, i5, i6);
    }

    @JvmStatic
    @Nullable
    public static final String getRemoteImageUrl(@Nullable Context context, @StringRes int i2, @NotNull String str) {
        return INSTANCE.getRemoteImageUrl(context, i2, str);
    }

    @JvmStatic
    @Nullable
    public static final String getRemoteImageUrl(@NotNull String str, @NotNull String str2) {
        return INSTANCE.getRemoteImageUrl(str, str2);
    }

    @JvmStatic
    public static final void setImageUrl(@Nullable SimpleDraweeView simpleDraweeView, @Nullable String str, @Nullable Drawable drawable, @ColorInt int i2) {
        INSTANCE.setImageUrl(simpleDraweeView, str, drawable, i2);
    }

    @JvmStatic
    public static final void setImageUrlFromXjs(@Nullable SimpleDraweeView simpleDraweeView, @Nullable String str, @Nullable String str2, @Nullable Drawable drawable, @ColorInt int i2) {
        INSTANCE.setImageUrlFromXjs(simpleDraweeView, str, str2, drawable, i2);
    }

    @JvmStatic
    public static final void setImageWidthWrap(@Nullable SimpleDraweeView simpleDraweeView, @Nullable String str, @Nullable Drawable drawable, int i2, int i3) {
        INSTANCE.setImageWidthWrap(simpleDraweeView, str, drawable, i2, i3);
    }

    @JvmStatic
    public static final void setTextViewImage(@Nullable TextView textView, @Nullable String str, @Nullable Drawable drawable, int i2, int i3, int i4, @ColorInt int i5, @NotNull TextViewType textViewType) {
        INSTANCE.setTextViewImage(textView, str, drawable, i2, i3, i4, i5, textViewType);
    }

    @JvmStatic
    public static final void setViewBg(@Nullable View view, @Nullable String str, @Nullable Drawable drawable) {
        INSTANCE.setViewBg(view, str, drawable);
    }

    @JvmStatic
    public static final void setViewNinePathDrawBg(@Nullable View view, @Nullable String str, @Nullable Drawable drawable) {
        INSTANCE.setViewNinePathDrawBg(view, str, drawable);
    }

    @JvmStatic
    public static final void setViewNinePathDrawBg(@Nullable View view, @Nullable String str, @Nullable Drawable drawable, @Nullable ImageRequestResultBack imageRequestResultBack) {
        INSTANCE.setViewNinePathDrawBg(view, str, drawable, imageRequestResultBack);
    }
}
