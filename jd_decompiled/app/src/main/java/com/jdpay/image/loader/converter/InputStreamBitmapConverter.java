package com.jdpay.image.loader.converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.io.InputStreamFileConverter;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes18.dex */
public class InputStreamBitmapConverter implements Converter<InputStream, Bitmap> {
    protected InputStreamFileConverter fileConverter;
    protected BitmapFactory.Options options;
    protected Rect outPadding;

    public InputStreamBitmapConverter() {
    }

    public InputStreamBitmapConverter(BitmapFactory.Options options, InputStreamFileConverter inputStreamFileConverter) {
        this(options, null, inputStreamFileConverter);
    }

    @Override // com.jdpay.lib.converter.Converter
    public Bitmap convert(@Nullable InputStream inputStream) throws Throwable {
        Bitmap decodeFile;
        if (inputStream == null) {
            return null;
        }
        try {
            InputStreamFileConverter inputStreamFileConverter = this.fileConverter;
            if (inputStreamFileConverter == null) {
                decodeFile = BitmapFactory.decodeStream(inputStream, this.outPadding, this.options);
            } else {
                decodeFile = BitmapFactory.decodeFile(inputStreamFileConverter.convert(inputStream).getAbsolutePath(), this.options);
            }
            return decodeFile;
        } finally {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public InputStreamBitmapConverter(BitmapFactory.Options options, Rect rect, InputStreamFileConverter inputStreamFileConverter) {
        this.options = options;
        this.outPadding = rect;
        this.fileConverter = inputStreamFileConverter;
    }
}
