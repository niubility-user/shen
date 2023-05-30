package com.jingdong.common.entity.cart.tabMenu;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class TabMenuEntity implements Parcelable {
    public static final Parcelable.Creator<TabMenuEntity> CREATOR = new Parcelable.Creator<TabMenuEntity>() { // from class: com.jingdong.common.entity.cart.tabMenu.TabMenuEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TabMenuEntity createFromParcel(Parcel parcel) {
            return new TabMenuEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TabMenuEntity[] newArray(int i2) {
            return new TabMenuEntity[i2];
        }
    };
    private boolean isHaveSubTab;
    private String itemName;
    private int layoutType;
    private String menuValue;
    private int otherNums;
    public int otherSkuNums;
    private TabMenuEntity parentTabMenuEntity;
    private int skuNums;
    private ArrayList<TabMenuEntity> subTab;
    private String tabMsg;
    private String tabMsgNew;
    private String tabName;
    private String tabPicUrl;
    private String tabPicUrlDark;
    private String tabPicUrlSelected;
    private String tabPicUrlSelectedDark;
    private int width;

    public TabMenuEntity(JDJSONObject jDJSONObject, int i2) {
        if (jDJSONObject != null) {
            setHaveSubTab(false);
            setTabName(jDJSONObject.optString(JshopConst.JSHOP_DYNAMIC_TAB_NAME));
            setSkuNums(jDJSONObject.optInt("skuNums"));
            setLayoutType(jDJSONObject.optInt("layoutType"));
            setMenuValue(jDJSONObject.optString("menuValue"));
            setItemName(jDJSONObject.optString("itemName"));
            setTabPicUrl(jDJSONObject.optString("tabPicUrl"));
            setTabPicUrlDark(jDJSONObject.optString("tabPicUrlDark"));
            setTabPicUrlSelected(jDJSONObject.optString("tabPicUrlSelected"));
            setTabPicUrlSelectedDark(jDJSONObject.optString("tabPicUrlSelectedDark"));
            this.otherSkuNums = jDJSONObject.optInt("otherSkuNums");
            int i3 = this.skuNums;
            if (i2 >= i3) {
                setOtherNums(i2 - i3);
            }
            if (jDJSONObject.optJSONArray("subTab") != null) {
                JDJSONArray optJSONArray = jDJSONObject.optJSONArray("subTab");
                setHaveSubTab(true);
                ArrayList<TabMenuEntity> arrayList = new ArrayList<>(optJSONArray.size());
                for (int i4 = 0; i4 < optJSONArray.size(); i4++) {
                    TabMenuEntity tabMenuEntity = new TabMenuEntity(optJSONArray.optJSONObject(i4), i2);
                    tabMenuEntity.setParentTabMenuEntity(this);
                    arrayList.add(tabMenuEntity);
                }
                setSubTab(arrayList);
            }
            if (this.isHaveSubTab) {
                setTabMsg(this.tabName);
                setTabMsgNew(this.tabName);
            } else if (!TextUtils.isEmpty(this.tabPicUrl) && !TextUtils.isEmpty(this.tabPicUrlDark)) {
                setTabMsg("(" + this.skuNums + ")");
                if (this.skuNums != 0) {
                    setTabMsgNew(this.tabName + LangUtils.SINGLE_SPACE + this.skuNums);
                    return;
                }
                setTabMsgNew(this.tabName);
            } else {
                setTabMsg(this.tabName + "(" + this.skuNums + ")");
                StringBuilder sb = new StringBuilder();
                sb.append(this.tabName);
                sb.append(LangUtils.SINGLE_SPACE);
                sb.append(this.skuNums);
                setTabMsgNew(sb.toString());
            }
        }
    }

    public static ArrayList<TabMenuEntity> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() <= 0) {
            return null;
        }
        int optInt = jDJSONArray.optJSONObject(0) != null ? jDJSONArray.optJSONObject(0).optInt("skuNums") : 0;
        ArrayList<TabMenuEntity> arrayList = new ArrayList<>(jDJSONArray.size());
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            arrayList.add(new TabMenuEntity(jDJSONArray.optJSONObject(i2), optInt));
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getLayoutType() {
        return this.layoutType;
    }

    public String getMenuValue() {
        return this.menuValue;
    }

    public int getOtherNums() {
        return this.otherNums;
    }

    public TabMenuEntity getParentTabMenuEntity() {
        return this.parentTabMenuEntity;
    }

    public int getSkuNums() {
        return this.skuNums;
    }

    public ArrayList<TabMenuEntity> getSubTab() {
        return this.subTab;
    }

    public String getTabMsg() {
        return this.tabMsg;
    }

    public String getTabMsgNew() {
        return this.tabMsgNew;
    }

    public String getTabName() {
        return this.tabName;
    }

    public String getTabPicUrl() {
        return this.tabPicUrl;
    }

    public String getTabPicUrlDark() {
        return this.tabPicUrlDark;
    }

    public String getTabPicUrlSelected() {
        return this.tabPicUrlSelected;
    }

    public String getTabPicUrlSelectedDark() {
        return this.tabPicUrlSelectedDark;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isHaveSubTab() {
        return this.isHaveSubTab;
    }

    public void setHaveSubTab(boolean z) {
        this.isHaveSubTab = z;
    }

    public void setItemName(String str) {
        this.itemName = str;
    }

    public void setLayoutType(int i2) {
        this.layoutType = i2;
    }

    public void setMenuValue(String str) {
        this.menuValue = str;
    }

    public void setOtherNums(int i2) {
        this.otherNums = i2;
    }

    public void setParentTabMenuEntity(TabMenuEntity tabMenuEntity) {
        this.parentTabMenuEntity = tabMenuEntity;
    }

    public void setSkuNums(int i2) {
        this.skuNums = i2;
    }

    public void setSubTab(ArrayList<TabMenuEntity> arrayList) {
        this.subTab = arrayList;
    }

    public void setTabMsg(String str) {
        this.tabMsg = str;
    }

    public void setTabMsgNew(String str) {
        this.tabMsgNew = str;
    }

    public void setTabName(String str) {
        this.tabName = str;
    }

    public void setTabPicUrl(String str) {
        this.tabPicUrl = str;
    }

    public void setTabPicUrlDark(String str) {
        this.tabPicUrlDark = str;
    }

    public void setTabPicUrlSelected(String str) {
        this.tabPicUrlSelected = str;
    }

    public void setTabPicUrlSelectedDark(String str) {
        this.tabPicUrlSelectedDark = str;
    }

    public void setWidth(int i2) {
        this.width = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.tabName);
        parcel.writeString(this.tabPicUrl);
        parcel.writeString(this.tabPicUrlDark);
        parcel.writeString(this.tabPicUrlSelected);
        parcel.writeString(this.tabPicUrlSelectedDark);
        parcel.writeInt(this.skuNums);
        parcel.writeString(this.menuValue);
        parcel.writeString(this.itemName);
        parcel.writeByte(this.isHaveSubTab ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.subTab);
        parcel.writeString(this.tabMsg);
        parcel.writeInt(this.otherNums);
        parcel.writeInt(this.otherSkuNums);
        parcel.writeInt(this.layoutType);
    }

    protected TabMenuEntity(Parcel parcel) {
        this.tabName = parcel.readString();
        this.tabPicUrl = parcel.readString();
        this.tabPicUrlDark = parcel.readString();
        this.tabPicUrlSelected = parcel.readString();
        this.tabPicUrlSelectedDark = parcel.readString();
        this.skuNums = parcel.readInt();
        this.menuValue = parcel.readString();
        this.itemName = parcel.readString();
        this.isHaveSubTab = parcel.readByte() != 0;
        this.subTab = parcel.createTypedArrayList(CREATOR);
        this.tabMsg = parcel.readString();
        this.otherNums = parcel.readInt();
        this.otherSkuNums = parcel.readInt();
        this.layoutType = parcel.readInt();
    }
}
