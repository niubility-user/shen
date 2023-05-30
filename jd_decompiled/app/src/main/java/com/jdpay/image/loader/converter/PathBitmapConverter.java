package com.jdpay.image.loader.converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Nullable;
import com.jdpay.image.loader.BitmapLoader;
import com.jdpay.lib.converter.Converter;
import java.io.File;

/* loaded from: classes18.dex */
public class PathBitmapConverter implements Converter<File, Bitmap> {
    protected boolean isFd;
    protected BitmapFactory.Options options;
    private ScaleConfig scaleConfig;

    public PathBitmapConverter() {
    }

    public PathBitmapConverter(@Nullable BitmapFactory.Options options, boolean z, ScaleConfig scaleConfig) {
        this.options = options;
        this.isFd = z;
        this.scaleConfig = scaleConfig;
    }

    @Override // com.jdpay.lib.converter.Converter
    public Bitmap convert(@Nullable File file) throws Throwable {
        if (this.isFd) {
            ScaleConfig scaleConfig = this.scaleConfig;
            if (scaleConfig != null && scaleConfig.mode == 1) {
                return BitmapLoader.loadWithScaleWidth(file, this.options, scaleConfig.width, scaleConfig.maxHeight);
            }
            return BitmapLoader.loadOnFileDescriptor(file, this.options);
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath(), this.options);
    }
}
