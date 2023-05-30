package com.sina.weibo.sdk.api;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.sina.weibo.sdk.b.c;
import java.io.ByteArrayOutputStream;
import java.io.File;

/* loaded from: classes9.dex */
public class ImageObject extends MediaObject {
    private static final int CONTENT_LENGTH_LIMIT = 1048576;
    public static final Parcelable.Creator<ImageObject> CREATOR = new a();
    private static final int PATH_LENGTH_LIMIT = 512;
    private static final String TAG = "ImageObject";
    private static final long serialVersionUID = 8760548583231081050L;
    public byte[] imageData;
    public String imagePath;

    /* loaded from: classes9.dex */
    static class a implements Parcelable.Creator<ImageObject> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final ImageObject createFromParcel(Parcel parcel) {
            return new ImageObject(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public final ImageObject[] newArray(int i2) {
            return new ImageObject[i2];
        }
    }

    public ImageObject() {
    }

    private int getFileSize(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        File file = new File(str);
        if (file.exists()) {
            return (int) file.length();
        }
        return 0;
    }

    public boolean checkArgs() {
        String str;
        byte[] bArr = this.imageData;
        if ((bArr != null && bArr.length != 0) || ((str = this.imagePath) != null && str.length() != 0)) {
            byte[] bArr2 = this.imageData;
            if (bArr2 != null && bArr2.length > 1048576) {
                c.b(TAG, "checkArgs fail, content is too large");
                return false;
            }
            String str2 = this.imagePath;
            if (str2 != null && str2.length() > 512) {
                c.b(TAG, "checkArgs fail, path is invalid");
                return false;
            }
            String str3 = this.imagePath;
            if (str3 == null || getFileSize(str3) <= 1048576) {
                return true;
            }
            c.b(TAG, "checkArgs fail, image content is too large");
            return false;
        }
        c.b(TAG, "checkArgs fail, all arguments are null");
        return false;
    }

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setImageData(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            this.imageData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception e2) {
            c.b(TAG, "ImageObject :" + e2.getMessage());
        }
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    @Override // com.sina.weibo.sdk.api.MediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByteArray(this.imageData);
        parcel.writeString(this.imagePath);
    }

    protected ImageObject(Parcel parcel) {
        this.imageData = parcel.createByteArray();
        this.imagePath = parcel.readString();
    }
}
