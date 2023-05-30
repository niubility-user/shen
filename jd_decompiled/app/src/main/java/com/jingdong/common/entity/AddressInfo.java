package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.common.entity.settlement.AddressOverSea;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class AddressInfo implements Serializable {
    private ArrayList<AddressAreas> Areas;
    public boolean Flag;
    public String Message;
    public List<AddressAreas> otherAreas;
    public List<AddressAreas> topAreas;
    public int unKnowId;

    /* loaded from: classes5.dex */
    public static class AddressAreas extends AddressOverSea implements Serializable {
        public int Id;
        public boolean IsSupCOD;
        public String Name;
        public int parentId;
        public String parentName;

        public String getName() {
            return TextUtils.isEmpty(this.Name) ? "" : this.Name;
        }
    }

    public ArrayList<AddressAreas> getAreas() {
        return this.Areas;
    }

    public String getMessage() {
        return TextUtils.isEmpty(this.Message) ? "" : this.Message;
    }

    public void setAreas(ArrayList<AddressAreas> arrayList) {
        this.Areas = arrayList;
    }
}
