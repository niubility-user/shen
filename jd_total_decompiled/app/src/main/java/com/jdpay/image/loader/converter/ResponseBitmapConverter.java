package com.jdpay.image.loader.converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import com.jdpay.lib.converter.Converter;
import com.jdpay.net.Response;

/* loaded from: classes18.dex */
public class ResponseBitmapConverter<RESPONSE extends Response> implements Converter<RESPONSE, Bitmap> {
    protected BitmapFactory.Options options;
    protected Rect outPadding;

    public ResponseBitmapConverter() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jdpay.lib.converter.Converter
    public /* bridge */ /* synthetic */ Bitmap convert(@Nullable Object obj) throws Throwable {
        return convert((ResponseBitmapConverter<RESPONSE>) ((Response) obj));
    }

    public BitmapFactory.Options getOptions() {
        return this.options;
    }

    public Rect getOutPadding() {
        return this.outPadding;
    }

    public void setOptions(BitmapFactory.Options options) {
        this.options = options;
    }

    public void setOutPadding(Rect rect) {
        this.outPadding = rect;
    }

    public ResponseBitmapConverter(BitmapFactory.Options options) {
        this.options = options;
    }

    public Bitmap convert(@Nullable RESPONSE response) throws Throwable {
        if (response != null) {
            return new InputStreamBitmapConverter(this.options, this.outPadding, null).convert(response.getInputStream());
        }
        return null;
    }

    public ResponseBitmapConverter(BitmapFactory.Options options, Rect rect) {
        this.options = options;
        this.outPadding = rect;
    }
}
