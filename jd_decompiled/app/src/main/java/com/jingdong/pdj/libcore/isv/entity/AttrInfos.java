package com.jingdong.pdj.libcore.isv.entity;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes7.dex */
public class AttrInfos implements Serializable {
    public String attId;
    public String attName;
    public List<ServeAttributeValues> attValueList;

    public boolean havaSelectService() {
        List<ServeAttributeValues> list = this.attValueList;
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (int i2 = 0; i2 < this.attValueList.size(); i2++) {
            if (this.attValueList.get(i2).isSelect) {
                return true;
            }
        }
        return false;
    }
}
