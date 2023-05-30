package com.jdpay.image.loader.converter;

import android.graphics.BitmapFactory;
import androidx.annotation.Nullable;
import com.jdpay.image.loader.BitmapLoader;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.io.InputStreamFileConverter;
import java.io.File;
import java.io.InputStream;

/* loaded from: classes18.dex */
public class BitmapFileCacheConverter implements Converter<InputStream, Object> {
    protected BitmapFactory.Options bitmapOptions;
    protected File cache;
    protected boolean isFd;
    protected ScaleConfig scaleConfig;

    public BitmapFileCacheConverter(@Nullable File file, @Nullable BitmapFactory.Options options, boolean z, ScaleConfig scaleConfig) {
        this.cache = file;
        this.bitmapOptions = options;
        this.isFd = z;
        this.scaleConfig = scaleConfig;
    }

    @Override // com.jdpay.lib.converter.Converter
    public Object convert(@Nullable InputStream inputStream) throws Throwable {
        if (inputStream == null) {
            return null;
        }
        boolean z = false;
        File file = this.cache;
        if (file != null) {
            if (file.exists()) {
                this.cache.delete();
            }
            File parentFile = this.cache.getParentFile();
            z = !parentFile.exists() ? parentFile.mkdirs() : true;
            if (z) {
                z = this.cache.createNewFile();
            }
        }
        if (z) {
            this.cache = new InputStreamFileConverter(this.cache).convert(inputStream);
        }
        File file2 = this.cache;
        if (file2 != null && file2.exists()) {
            if (this.isFd) {
                ScaleConfig scaleConfig = this.scaleConfig;
                if (scaleConfig != null && scaleConfig.mode == 1) {
                    return BitmapLoader.loadWithScaleWidth(this.cache, this.bitmapOptions, scaleConfig.width, scaleConfig.maxHeight);
                }
                return BitmapLoader.loadOnFileDescriptor(this.cache, this.bitmapOptions);
            }
            return BitmapFactory.decodeFile(this.cache.getAbsolutePath(), this.bitmapOptions);
        }
        return BitmapFactory.decodeStream(inputStream, null, this.bitmapOptions);
    }
}
