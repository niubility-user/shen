package com.unionpay.tsmservice.request;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.data.NinePatchInfo;
import java.util.ArrayList;

/* loaded from: classes11.dex */
public class SafetyKeyboardRequestParams extends RequestParams {
    public static final Parcelable.Creator<SafetyKeyboardRequestParams> CREATOR = new Parcelable.Creator<SafetyKeyboardRequestParams>() { // from class: com.unionpay.tsmservice.request.SafetyKeyboardRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SafetyKeyboardRequestParams createFromParcel(Parcel parcel) {
            return new SafetyKeyboardRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SafetyKeyboardRequestParams[] newArray(int i2) {
            return new SafetyKeyboardRequestParams[i2];
        }
    };
    private int mConfirmBtnHeight;
    private int mConfirmBtnOutPaddingRight;
    private int mConfirmBtnWidth;
    private Bitmap mDelBgBitmap;
    private int mDelBgColor;
    private Bitmap mDelForeBitmap;
    private Bitmap mDoneBgBitmap;
    private int mDoneBgColor;
    private Bitmap mDoneForeBitmap;
    private int mDoneRight;
    private boolean mEnableLightStatusBar;
    private int mEnableOKBtn;
    private int mInnerPaddingBottom;
    private int mInnerPaddingLeft;
    private int mInnerPaddingRight;
    private int mInnerPaddingTop;
    private int mIsAudio;
    private int mIsDefaultPosition;
    private int mIsVibrate;
    private Bitmap mKeyboardBgBitmap;
    private int mKeyboardBgColor;
    private int mKeyboardHeight;
    private int mKeyboardWidth;
    private int mMarginCol;
    private int mMarginRow;
    private NinePatchInfo mNinePatchBackground;
    private NinePatchInfo mNinePatchDelKeyBg;
    private NinePatchInfo mNinePatchDoneKeyBg;
    private NinePatchInfo mNinePatchNumKeyBg;
    private NinePatchInfo mNinePatchTitleBg;
    private Bitmap mNumBgBitmap;
    private int mNumBgColor;
    private ArrayList<Bitmap> mNumForeBitmaps;
    private int mNumSize;
    private int mNumberKeyColor;
    private int mOutPaddingBottom;
    private int mOutPaddingLeft;
    private int mOutPaddingRight;
    private int mOutPaddingTop;
    private int mSecureHeight;
    private int mSecureWidth;
    private int mStartX;
    private int mStartY;
    private String mTitle;
    private Bitmap mTitleBgBitmap;
    private int mTitleBgColor;
    private int mTitleColor;
    private int mTitleDrawablePadding;
    private Bitmap mTitleDropBitmap;
    private int mTitleFont;
    private int mTitleHeight;
    private Bitmap mTitleIconBitmap;
    private int mTitleSize;

    public SafetyKeyboardRequestParams() {
        this.mKeyboardWidth = -1;
        this.mKeyboardHeight = -1;
        this.mTitleHeight = -1;
        this.mMarginRow = -1;
        this.mMarginCol = -1;
        this.mOutPaddingLeft = -1;
        this.mOutPaddingTop = -1;
        this.mOutPaddingRight = -1;
        this.mOutPaddingBottom = -1;
        this.mInnerPaddingLeft = -1;
        this.mInnerPaddingTop = -1;
        this.mInnerPaddingRight = -1;
        this.mInnerPaddingBottom = -1;
        this.mConfirmBtnOutPaddingRight = -1;
        this.mConfirmBtnWidth = -1;
        this.mConfirmBtnHeight = -1;
        this.mStartX = 0;
        this.mStartY = 0;
        this.mIsDefaultPosition = 1;
        this.mNumSize = -1;
        this.mKeyboardBgColor = -1;
        this.mTitleBgColor = -1;
        this.mDoneBgColor = -1;
        this.mDelBgColor = -1;
        this.mNumBgColor = -1;
        this.mIsAudio = 0;
        this.mEnableOKBtn = 1;
        this.mDoneRight = 0;
        this.mIsVibrate = 0;
        this.mSecureWidth = -1;
        this.mSecureHeight = -1;
        this.mTitleDrawablePadding = -1;
        this.mTitleColor = -1;
        this.mTitleSize = -1;
        this.mNumberKeyColor = -16777216;
        this.mEnableLightStatusBar = false;
    }

    public SafetyKeyboardRequestParams(Parcel parcel) {
        super(parcel);
        this.mKeyboardWidth = -1;
        this.mKeyboardHeight = -1;
        this.mTitleHeight = -1;
        this.mMarginRow = -1;
        this.mMarginCol = -1;
        this.mOutPaddingLeft = -1;
        this.mOutPaddingTop = -1;
        this.mOutPaddingRight = -1;
        this.mOutPaddingBottom = -1;
        this.mInnerPaddingLeft = -1;
        this.mInnerPaddingTop = -1;
        this.mInnerPaddingRight = -1;
        this.mInnerPaddingBottom = -1;
        this.mConfirmBtnOutPaddingRight = -1;
        this.mConfirmBtnWidth = -1;
        this.mConfirmBtnHeight = -1;
        this.mStartX = 0;
        this.mStartY = 0;
        this.mIsDefaultPosition = 1;
        this.mNumSize = -1;
        this.mKeyboardBgColor = -1;
        this.mTitleBgColor = -1;
        this.mDoneBgColor = -1;
        this.mDelBgColor = -1;
        this.mNumBgColor = -1;
        this.mIsAudio = 0;
        this.mEnableOKBtn = 1;
        this.mDoneRight = 0;
        this.mIsVibrate = 0;
        this.mSecureWidth = -1;
        this.mSecureHeight = -1;
        this.mTitleDrawablePadding = -1;
        this.mTitleColor = -1;
        this.mTitleSize = -1;
        this.mNumberKeyColor = -16777216;
        this.mEnableLightStatusBar = false;
        this.mTitle = parcel.readString();
        this.mKeyboardWidth = parcel.readInt();
        this.mKeyboardHeight = parcel.readInt();
        this.mTitleHeight = parcel.readInt();
        this.mMarginRow = parcel.readInt();
        this.mMarginCol = parcel.readInt();
        this.mOutPaddingLeft = parcel.readInt();
        this.mOutPaddingTop = parcel.readInt();
        this.mOutPaddingRight = parcel.readInt();
        this.mOutPaddingBottom = parcel.readInt();
        this.mInnerPaddingLeft = parcel.readInt();
        this.mInnerPaddingTop = parcel.readInt();
        this.mInnerPaddingRight = parcel.readInt();
        this.mInnerPaddingBottom = parcel.readInt();
        this.mConfirmBtnOutPaddingRight = parcel.readInt();
        this.mConfirmBtnWidth = parcel.readInt();
        this.mConfirmBtnHeight = parcel.readInt();
        this.mStartX = parcel.readInt();
        this.mStartY = parcel.readInt();
        this.mIsDefaultPosition = parcel.readInt();
        this.mNumSize = parcel.readInt();
        this.mKeyboardBgBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mTitleBgBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mTitleIconBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mTitleDropBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mDoneForeBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mDoneBgBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mDelForeBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mDelBgBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mNumBgBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mNumForeBitmaps = parcel.readArrayList(ArrayList.class.getClassLoader());
        this.mKeyboardBgColor = parcel.readInt();
        this.mTitleBgColor = parcel.readInt();
        this.mDoneBgColor = parcel.readInt();
        this.mDelBgColor = parcel.readInt();
        this.mNumBgColor = parcel.readInt();
        this.mIsAudio = parcel.readInt();
        this.mEnableOKBtn = parcel.readInt();
        this.mDoneRight = parcel.readInt();
        this.mIsVibrate = parcel.readInt();
        this.mSecureWidth = parcel.readInt();
        this.mSecureHeight = parcel.readInt();
        this.mTitleDrawablePadding = parcel.readInt();
        this.mTitleColor = parcel.readInt();
        this.mTitleSize = parcel.readInt();
        this.mNumberKeyColor = parcel.readInt();
        this.mNinePatchBackground = (NinePatchInfo) parcel.readParcelable(NinePatchInfo.class.getClassLoader());
        this.mNinePatchDelKeyBg = (NinePatchInfo) parcel.readParcelable(NinePatchInfo.class.getClassLoader());
        this.mNinePatchDoneKeyBg = (NinePatchInfo) parcel.readParcelable(NinePatchInfo.class.getClassLoader());
        this.mNinePatchNumKeyBg = (NinePatchInfo) parcel.readParcelable(NinePatchInfo.class.getClassLoader());
        this.mNinePatchTitleBg = (NinePatchInfo) parcel.readParcelable(NinePatchInfo.class.getClassLoader());
        this.mEnableLightStatusBar = parcel.readInt() == 1;
    }

    public int getConfirmBtnHeight() {
        return this.mConfirmBtnHeight;
    }

    public int getConfirmBtnOutPaddingRight() {
        return this.mConfirmBtnOutPaddingRight;
    }

    public int getConfirmBtnWidth() {
        return this.mConfirmBtnWidth;
    }

    public int getDefaultPosition() {
        return this.mIsDefaultPosition;
    }

    public Bitmap getDelBgBitmap() {
        return this.mDelBgBitmap;
    }

    public int getDelBgColor() {
        return this.mDelBgColor;
    }

    public Bitmap getDelForeBitmap() {
        return this.mDelForeBitmap;
    }

    public NinePatchInfo getDelKeyBgNinePatch() {
        return this.mNinePatchDelKeyBg;
    }

    public Bitmap getDoneBgBitmap() {
        return this.mDoneBgBitmap;
    }

    public int getDoneBgColor() {
        return this.mDoneBgColor;
    }

    public Bitmap getDoneForeBitmap() {
        return this.mDoneForeBitmap;
    }

    public NinePatchInfo getDoneKeyBgNinePatch() {
        return this.mNinePatchDoneKeyBg;
    }

    public int getDoneRight() {
        return this.mDoneRight;
    }

    public int getEnableOKBtn() {
        return this.mEnableOKBtn;
    }

    public int getInnerPaddingBottom() {
        return this.mInnerPaddingBottom;
    }

    public int getInnerPaddingLeft() {
        return this.mInnerPaddingLeft;
    }

    public int getInnerPaddingRight() {
        return this.mInnerPaddingRight;
    }

    public int getInnerPaddingTop() {
        return this.mInnerPaddingTop;
    }

    public int getIsAudio() {
        return this.mIsAudio;
    }

    public int getIsVibrate() {
        return this.mIsVibrate;
    }

    public Bitmap getKeyboardBgBitmap() {
        return this.mKeyboardBgBitmap;
    }

    public int getKeyboardBgColor() {
        return this.mKeyboardBgColor;
    }

    public NinePatchInfo getKeyboardBgNinePatch() {
        return this.mNinePatchBackground;
    }

    public int getKeyboardHeight() {
        return this.mKeyboardHeight;
    }

    public int getKeyboardWidth() {
        return this.mKeyboardWidth;
    }

    public int getMarginCol() {
        return this.mMarginCol;
    }

    public int getMarginRow() {
        return this.mMarginRow;
    }

    public Bitmap getNumBgBitmap() {
        return this.mNumBgBitmap;
    }

    public int getNumBgColor() {
        return this.mNumBgColor;
    }

    public ArrayList<Bitmap> getNumForeBitmaps() {
        return this.mNumForeBitmaps;
    }

    public NinePatchInfo getNumKeyBgNinePatch() {
        return this.mNinePatchNumKeyBg;
    }

    public int getNumSize() {
        return this.mNumSize;
    }

    public int getNumberKeyColor() {
        return this.mNumberKeyColor;
    }

    public int getOutPaddingBottom() {
        return this.mOutPaddingBottom;
    }

    public int getOutPaddingLeft() {
        return this.mOutPaddingLeft;
    }

    public int getOutPaddingRight() {
        return this.mOutPaddingRight;
    }

    public int getOutPaddingTop() {
        return this.mOutPaddingTop;
    }

    public int getSecureHeight() {
        return this.mSecureHeight;
    }

    public int getSecureWidth() {
        return this.mSecureWidth;
    }

    public int getStartX() {
        return this.mStartX;
    }

    public int getStartY() {
        return this.mStartY;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Bitmap getTitleBgBitmap() {
        return this.mTitleBgBitmap;
    }

    public int getTitleBgColor() {
        return this.mTitleBgColor;
    }

    public NinePatchInfo getTitleBgNinePatch() {
        return this.mNinePatchTitleBg;
    }

    public int getTitleColor() {
        return this.mTitleColor;
    }

    public int getTitleDrawablePadding() {
        return this.mTitleDrawablePadding;
    }

    public Bitmap getTitleDropBitmap() {
        return this.mTitleDropBitmap;
    }

    public int getTitleFont() {
        return this.mTitleFont;
    }

    public int getTitleHeight() {
        return this.mTitleHeight;
    }

    public Bitmap getTitleIconBitmap() {
        return this.mTitleIconBitmap;
    }

    public int getTitleSize() {
        return this.mTitleSize;
    }

    public boolean isEnableLightStatusBar() {
        return this.mEnableLightStatusBar;
    }

    public void setConfirmBtnHeight(int i2) {
        this.mConfirmBtnHeight = i2;
    }

    public void setConfirmBtnOutPaddingRight(int i2) {
        this.mConfirmBtnOutPaddingRight = i2;
    }

    public void setConfirmBtnWidth(int i2) {
        this.mConfirmBtnWidth = i2;
    }

    public void setDefaultPosition(int i2) {
        this.mIsDefaultPosition = i2;
    }

    public void setDelBgBitmap(Bitmap bitmap) {
        this.mDelBgBitmap = bitmap;
    }

    public void setDelBgColor(int i2) {
        this.mDelBgColor = i2;
    }

    public void setDelForeBitmap(Bitmap bitmap) {
        this.mDelForeBitmap = bitmap;
    }

    public void setDelKeyBgNinePatch(NinePatchInfo ninePatchInfo) {
        this.mNinePatchDelKeyBg = ninePatchInfo;
    }

    public void setDoneBgBitmap(Bitmap bitmap) {
        this.mDoneBgBitmap = bitmap;
    }

    public void setDoneBgColor(int i2) {
        this.mDoneBgColor = i2;
    }

    public void setDoneForeBitmap(Bitmap bitmap) {
        this.mDoneForeBitmap = bitmap;
    }

    public void setDoneKeyBgNinePatch(NinePatchInfo ninePatchInfo) {
        this.mNinePatchDoneKeyBg = ninePatchInfo;
    }

    public void setDoneRight(int i2) {
        this.mDoneRight = i2;
    }

    public void setEnableLightStatusBar(boolean z) {
        this.mEnableLightStatusBar = z;
    }

    public void setEnableOKBtn(int i2) {
        this.mEnableOKBtn = i2;
    }

    public void setInnerPaddingBottom(int i2) {
        this.mInnerPaddingBottom = i2;
    }

    public void setInnerPaddingLeft(int i2) {
        this.mInnerPaddingLeft = i2;
    }

    public void setInnerPaddingRight(int i2) {
        this.mInnerPaddingRight = i2;
    }

    public void setInnerPaddingTop(int i2) {
        this.mInnerPaddingTop = i2;
    }

    public void setIsAudio(int i2) {
        this.mIsAudio = i2;
    }

    public void setIsVibrate(int i2) {
        this.mIsVibrate = i2;
    }

    public void setKeyboardBgBitmap(Bitmap bitmap) {
        this.mKeyboardBgBitmap = bitmap;
    }

    public void setKeyboardBgColor(int i2) {
        this.mKeyboardBgColor = i2;
    }

    public void setKeyboardBgNinePatch(NinePatchInfo ninePatchInfo) {
        this.mNinePatchBackground = ninePatchInfo;
    }

    public void setKeyboardHeight(int i2) {
        this.mKeyboardHeight = i2;
    }

    public void setKeyboardWidth(int i2) {
        this.mKeyboardWidth = i2;
    }

    public void setMarginCol(int i2) {
        this.mMarginCol = i2;
    }

    public void setMarginRow(int i2) {
        this.mMarginRow = i2;
    }

    public void setNumBgBitmap(Bitmap bitmap) {
        this.mNumBgBitmap = bitmap;
    }

    public void setNumBgColor(int i2) {
        this.mNumBgColor = i2;
    }

    public void setNumForeBitmaps(ArrayList<Bitmap> arrayList) {
        this.mNumForeBitmaps = arrayList;
    }

    public void setNumKeyBgNinePatch(NinePatchInfo ninePatchInfo) {
        this.mNinePatchNumKeyBg = ninePatchInfo;
    }

    public void setNumSize(int i2) {
        this.mNumSize = i2;
    }

    public void setNumberKeyColor(int i2) {
        this.mNumberKeyColor = i2;
    }

    public void setOutPaddingBottom(int i2) {
        this.mOutPaddingBottom = i2;
    }

    public void setOutPaddingLeft(int i2) {
        this.mOutPaddingLeft = i2;
    }

    public void setOutPaddingRight(int i2) {
        this.mOutPaddingRight = i2;
    }

    public void setOutPaddingTop(int i2) {
        this.mOutPaddingTop = i2;
    }

    public void setSecureHeight(int i2) {
        this.mSecureHeight = i2;
    }

    public void setSecureWidth(int i2) {
        this.mSecureWidth = i2;
    }

    public void setStartX(int i2) {
        this.mStartX = i2;
    }

    public void setStartY(int i2) {
        this.mStartY = i2;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setTitleBgBitmap(Bitmap bitmap) {
        this.mTitleBgBitmap = bitmap;
    }

    public void setTitleBgColor(int i2) {
        this.mTitleBgColor = i2;
    }

    public void setTitleBgNinePatch(NinePatchInfo ninePatchInfo) {
        this.mNinePatchTitleBg = ninePatchInfo;
    }

    public void setTitleColor(int i2) {
        this.mTitleColor = i2;
    }

    public void setTitleDrawablePadding(int i2) {
        this.mTitleDrawablePadding = i2;
    }

    public void setTitleDropBitmap(Bitmap bitmap) {
        this.mTitleDropBitmap = bitmap;
    }

    public void setTitleFont(int i2) {
        this.mTitleFont = i2;
    }

    public void setTitleHeight(int i2) {
        this.mTitleHeight = i2;
    }

    public void setTitleIconBitmap(Bitmap bitmap) {
        this.mTitleIconBitmap = bitmap;
    }

    public void setTitleSize(int i2) {
        this.mTitleSize = i2;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.mTitle);
        parcel.writeInt(this.mKeyboardWidth);
        parcel.writeInt(this.mKeyboardHeight);
        parcel.writeInt(this.mTitleHeight);
        parcel.writeInt(this.mMarginRow);
        parcel.writeInt(this.mMarginCol);
        parcel.writeInt(this.mOutPaddingLeft);
        parcel.writeInt(this.mOutPaddingTop);
        parcel.writeInt(this.mOutPaddingRight);
        parcel.writeInt(this.mOutPaddingBottom);
        parcel.writeInt(this.mInnerPaddingLeft);
        parcel.writeInt(this.mInnerPaddingTop);
        parcel.writeInt(this.mInnerPaddingRight);
        parcel.writeInt(this.mInnerPaddingBottom);
        parcel.writeInt(this.mConfirmBtnOutPaddingRight);
        parcel.writeInt(this.mConfirmBtnWidth);
        parcel.writeInt(this.mConfirmBtnHeight);
        parcel.writeInt(this.mStartX);
        parcel.writeInt(this.mStartY);
        parcel.writeInt(this.mIsDefaultPosition);
        parcel.writeInt(this.mNumSize);
        parcel.writeParcelable(this.mKeyboardBgBitmap, 0);
        parcel.writeParcelable(this.mTitleBgBitmap, 0);
        parcel.writeParcelable(this.mTitleIconBitmap, 0);
        parcel.writeParcelable(this.mTitleDropBitmap, 0);
        parcel.writeParcelable(this.mDoneForeBitmap, 0);
        parcel.writeParcelable(this.mDoneBgBitmap, 0);
        parcel.writeParcelable(this.mDelForeBitmap, 0);
        parcel.writeParcelable(this.mDelBgBitmap, 0);
        parcel.writeParcelable(this.mNumBgBitmap, 0);
        parcel.writeList(this.mNumForeBitmaps);
        parcel.writeInt(this.mKeyboardBgColor);
        parcel.writeInt(this.mTitleBgColor);
        parcel.writeInt(this.mDoneBgColor);
        parcel.writeInt(this.mDelBgColor);
        parcel.writeInt(this.mNumBgColor);
        parcel.writeInt(this.mIsAudio);
        parcel.writeInt(this.mEnableOKBtn);
        parcel.writeInt(this.mDoneRight);
        parcel.writeInt(this.mIsVibrate);
        parcel.writeInt(this.mSecureWidth);
        parcel.writeInt(this.mSecureHeight);
        parcel.writeInt(this.mTitleDrawablePadding);
        parcel.writeInt(this.mTitleColor);
        parcel.writeInt(this.mTitleSize);
        parcel.writeInt(this.mNumberKeyColor);
        parcel.writeParcelable(this.mNinePatchBackground, i2);
        parcel.writeParcelable(this.mNinePatchDelKeyBg, i2);
        parcel.writeParcelable(this.mNinePatchDoneKeyBg, i2);
        parcel.writeParcelable(this.mNinePatchNumKeyBg, i2);
        parcel.writeParcelable(this.mNinePatchTitleBg, i2);
        parcel.writeInt(this.mEnableLightStatusBar ? 1 : 0);
    }
}
