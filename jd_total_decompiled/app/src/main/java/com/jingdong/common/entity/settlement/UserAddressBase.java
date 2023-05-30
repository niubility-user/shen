package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class UserAddressBase implements Serializable {
    public String CityName;
    public String CountryName;
    public String ProvinceName;
    public String TownName;
    public Integer idArea;
    public Integer idCity;
    public Integer idProvince;
    public Integer idTown;

    public String getCityName() {
        return TextUtils.isEmpty(this.CityName) ? "" : this.CityName;
    }

    public String getCountryName() {
        return TextUtils.isEmpty(this.CountryName) ? "" : this.CountryName;
    }

    public Integer getIdArea() {
        Integer num = this.idArea;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public Integer getIdCity() {
        Integer num = this.idCity;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public Integer getIdProvince() {
        Integer num = this.idProvince;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public Integer getIdTown() {
        Integer num = this.idTown;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getProvinceName() {
        return TextUtils.isEmpty(this.ProvinceName) ? "" : this.ProvinceName;
    }

    public String getTownName() {
        return TextUtils.isEmpty(this.TownName) ? "" : this.TownName;
    }
}
