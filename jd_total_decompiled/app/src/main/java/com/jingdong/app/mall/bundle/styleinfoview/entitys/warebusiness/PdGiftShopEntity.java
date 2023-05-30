package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.giftshopping.PdTrickContentEntity;
import java.util.List;

/* loaded from: classes3.dex */
public class PdGiftShopEntity implements Parcelable {
    public static final Parcelable.Creator<PdGiftShopEntity> CREATOR = new Parcelable.Creator<PdGiftShopEntity>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdGiftShopEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdGiftShopEntity createFromParcel(Parcel parcel) {
            return new PdGiftShopEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdGiftShopEntity[] newArray(int i2) {
            return new PdGiftShopEntity[i2];
        }
    };
    public String backgroudImg;
    public String button1;
    public String button2;
    public ButtonEntrance buttonEntrance;
    public List<Buttons> buttons;
    public boolean decodeSwithForAndroid;
    public String from;
    public String giftIcon;
    public String giftJiantou;
    public GiftTextPackage giftPackageText;
    public String giftText;
    public String giftTextColor;
    public String mManageKey;
    public String productIcon;
    public String productName;
    public String productSecelt;
    public String secretGiftText;
    public String secretGiftTitle;
    public SelectEntrance selectEntrance;
    public List<GiftThemeItem> shareBgList;
    public String sharePic;
    public String shareText;
    public String shoppingPic;
    public String shoppingText;
    public String skuId;
    public String tips1;
    public String tips2;
    public TopEntrance topEntrance;
    public PdTrickContentEntity trickContent;
    public String xcxId;
    public String xcxType;
    public String xiaoI;

    /* loaded from: classes3.dex */
    public static class ButtonEntrance {
        public String giftBubbleIcon;
        public String giftBubblePic;
        public String giftBubbleText;
        public String giftButtonColor;
        public String giftButtonPic;
        public String giftButtonText;
        public String giftButtonTextColor;
        public String giftEntranceType;
    }

    /* loaded from: classes3.dex */
    public static class Buttons implements Parcelable {
        public static final Parcelable.Creator<Buttons> CREATOR = new Parcelable.Creator<Buttons>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdGiftShopEntity.Buttons.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Buttons createFromParcel(Parcel parcel) {
                return new Buttons(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Buttons[] newArray(int i2) {
                return new Buttons[i2];
            }
        };
        public String buttonChannelType;
        public String buttonColor;
        public String buttonJumpType;
        public String buttonJumpUrl;
        public String buttonText;
        public String xcxId;
        public String xcxType;
        public String xiaoI;
        public String xiaoITips;

        public Buttons() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.buttonText);
            parcel.writeString(this.buttonJumpType);
            parcel.writeString(this.buttonJumpUrl);
            parcel.writeString(this.buttonColor);
            parcel.writeString(this.xiaoI);
            parcel.writeString(this.xiaoITips);
            parcel.writeString(this.xcxType);
            parcel.writeString(this.xcxId);
        }

        protected Buttons(Parcel parcel) {
            this.buttonText = parcel.readString();
            this.buttonJumpType = parcel.readString();
            this.buttonJumpUrl = parcel.readString();
            this.buttonColor = parcel.readString();
            this.xiaoI = parcel.readString();
            this.xiaoITips = parcel.readString();
            this.xcxType = parcel.readString();
            this.xcxId = parcel.readString();
        }
    }

    /* loaded from: classes3.dex */
    public static class GiftTextPackage {
        public String packageTextLeft;
        public String packageTextRight;
    }

    /* loaded from: classes3.dex */
    public static class GiftThemeItem {
        public String bgColor;
        public String bigPic;
        public String name;
        public String smallPic;
    }

    /* loaded from: classes3.dex */
    public static class SelectEntrance {
        public String giftEntranceType;
        public String giftSelectArrow;
        public String giftSelectBgColor;
        public String giftSelectIcon;
        public String giftSelectText;
        public String giftSelectTextColor;
    }

    /* loaded from: classes3.dex */
    public static class TopEntrance {
        public String giftEntranceType;
        public String giftTopAlpha;
        public String giftTopArrow;
        public String giftTopBgColor;
        public String giftTopIconPic;
        public String giftTopText;
        public String giftTopTextColor;
    }

    public PdGiftShopEntity() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mManageKey);
        parcel.writeString(this.skuId);
        parcel.writeString(this.backgroudImg);
        parcel.writeString(this.productIcon);
        parcel.writeString(this.productName);
        parcel.writeString(this.productSecelt);
        parcel.writeString(this.xiaoI);
        parcel.writeString(this.xcxId);
        parcel.writeString(this.xcxType);
        parcel.writeByte(this.decodeSwithForAndroid ? (byte) 1 : (byte) 0);
        parcel.writeString(this.giftJiantou);
        parcel.writeString(this.giftIcon);
        parcel.writeString(this.giftText);
        parcel.writeString(this.button2);
        parcel.writeString(this.button1);
        parcel.writeString(this.tips1);
        parcel.writeString(this.tips2);
        parcel.writeString(this.shoppingText);
        parcel.writeString(this.sharePic);
        parcel.writeString(this.shareText);
        parcel.writeString(this.shoppingPic);
        parcel.writeTypedList(this.buttons);
        parcel.writeString(this.giftTextColor);
        parcel.writeString(this.from);
    }

    protected PdGiftShopEntity(Parcel parcel) {
        this.mManageKey = parcel.readString();
        this.skuId = parcel.readString();
        this.backgroudImg = parcel.readString();
        this.productIcon = parcel.readString();
        this.productName = parcel.readString();
        this.productSecelt = parcel.readString();
        this.xiaoI = parcel.readString();
        this.xcxId = parcel.readString();
        this.xcxType = parcel.readString();
        this.decodeSwithForAndroid = parcel.readByte() != 0;
        this.giftJiantou = parcel.readString();
        this.giftIcon = parcel.readString();
        this.giftText = parcel.readString();
        this.button2 = parcel.readString();
        this.button1 = parcel.readString();
        this.tips1 = parcel.readString();
        this.tips2 = parcel.readString();
        this.shoppingText = parcel.readString();
        this.sharePic = parcel.readString();
        this.shareText = parcel.readString();
        this.shoppingPic = parcel.readString();
        this.buttons = parcel.createTypedArrayList(Buttons.CREATOR);
        this.giftTextColor = parcel.readString();
        this.from = parcel.readString();
    }
}
