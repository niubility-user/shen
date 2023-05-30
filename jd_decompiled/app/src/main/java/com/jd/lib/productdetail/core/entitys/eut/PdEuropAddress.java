package com.jd.lib.productdetail.core.entitys.eut;

import com.jingdong.common.entity.AddressGlobal;

/* loaded from: classes15.dex */
public class PdEuropAddress {
    public String euta;
    public Hold hold;
    public boolean refreshMe0;

    /* loaded from: classes15.dex */
    public static class Hold {
        public int conCityId;
        public int conCountyId;
        public int conProvinceId;
        public int conTownId;
        public String consolidatorAddressName;
        public String consolidatorDetailAddress;
        public int consolidatorId;
        public String consolidatorName;
        public String consolidatorTele;
    }

    public void fill(AddressGlobal addressGlobal) {
        Hold hold;
        if (addressGlobal == null || (hold = this.hold) == null) {
            return;
        }
        addressGlobal.setServiceId(hold.consolidatorId);
        addressGlobal.setIdProvince(this.hold.conProvinceId);
        addressGlobal.setIdCity(this.hold.conCityId);
        addressGlobal.setIdTown(this.hold.conTownId);
        addressGlobal.setIdArea(this.hold.conCountyId);
        addressGlobal.setName(this.hold.consolidatorAddressName);
        addressGlobal.setMobile(this.hold.consolidatorTele);
        addressGlobal.setWhere(this.hold.consolidatorDetailAddress);
    }
}
