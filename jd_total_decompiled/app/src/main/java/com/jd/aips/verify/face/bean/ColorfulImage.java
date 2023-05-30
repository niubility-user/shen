package com.jd.aips.verify.face.bean;

import androidx.annotation.NonNull;
import com.jd.aips.common.utils.Base64Utils;
import java.io.Serializable;

/* loaded from: classes12.dex */
public class ColorfulImage implements Serializable {
    static final long serialVersionUID = 1667964171609811702L;
    public final String base64Image;
    public final String color;

    public ColorfulImage(@NonNull String str, @NonNull byte[] bArr) {
        this.color = str;
        this.base64Image = Base64Utils.encodeBytes(bArr);
    }
}
