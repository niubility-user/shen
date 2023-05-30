package com.jingdong.common.entity.settlement;

import com.jd.framework.json.JDJSON;
import com.jingdong.common.entity.UserAddress;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class AddressCheckInfo implements Serializable {
    public String addressDetail;
    public int cityId;
    public String cityName;
    public int countyId;
    public String countyName;
    public String fullAddress;
    public String oldCityId;
    public String oldCountyId;
    public String oldProviceId;
    public String oldTownId;
    public int provinceId;
    public String provinceName;
    public int townId;
    public String townName;
    public String where;

    public void copyToUserAddress(UserAddress userAddress) {
        if (userAddress != null) {
            userAddress.idProvince = Integer.valueOf(this.provinceId);
            userAddress.ProvinceName = this.provinceName;
            userAddress.idCity = Integer.valueOf(this.cityId);
            userAddress.CityName = this.cityName;
            userAddress.idArea = Integer.valueOf(this.countyId);
            userAddress.CountryName = this.countyName;
            userAddress.idTown = Integer.valueOf(this.townId);
            userAddress.TownName = this.townName;
            userAddress.where = this.where;
            userAddress.addressDetail = this.addressDetail;
        }
    }

    public JSONObject toJsonObject() {
        try {
            return new JSONObject(JDJSON.toJSONString(this));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
