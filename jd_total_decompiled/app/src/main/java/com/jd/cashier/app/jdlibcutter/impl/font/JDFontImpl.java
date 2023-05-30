package com.jd.cashier.app.jdlibcutter.impl.font;

import android.graphics.Typeface;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.font.IFont;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes13.dex */
public class JDFontImpl implements IFont {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.font.IFont
    public void changeTextFont(TextView textView, byte b) {
        if (textView != null) {
            FontsUtil.changeTextFont(textView, b);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.font.IFont
    public Typeface getTypeFace(byte b) {
        if (b != 1) {
            if (b != 2) {
                if (b != 3) {
                    return null;
                }
                return FontsUtil.getTypeFace(JdSdk.getInstance().getApplication(), 4099);
            }
            return FontsUtil.getTypeFace(JdSdk.getInstance().getApplication(), 4098);
        }
        return FontsUtil.getTypeFace(JdSdk.getInstance().getApplication(), 4097);
    }
}
