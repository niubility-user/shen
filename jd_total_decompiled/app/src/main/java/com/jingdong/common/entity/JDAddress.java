package com.jingdong.common.entity;

import com.jingdong.common.entity.AddressInfo;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class JDAddress implements Serializable {
    private AddressGlobal addressGlobal;
    private AddressInfo area;
    public String changeAddressMsg;
    private AddressInfo city;
    private AddressInfo.AddressAreas currAreaAddressAreas;
    public int currAreaId;
    private AddressInfo.AddressAreas currCityAddressAreas;
    public int currCityId;
    private AddressInfo.AddressAreas currProviceAddressAreas;
    public int currProvinceId;
    private AddressInfo.AddressAreas currTownsAddressAreas;
    public int currTownsId;
    public boolean isShowAreaPromptIcon;
    public boolean isShowTownsPromptIcon;
    public List<AddressInfo.AddressAreas> otherAreas;
    private AddressInfo provices;
    public List<AddressInfo.AddressAreas> topAreas;
    private AddressInfo town;
    public int unKnowId;

    public AddressGlobal getAddressGlobal() {
        return this.addressGlobal;
    }

    public AddressInfo getArea() {
        AddressInfo addressInfo = this.area;
        return addressInfo == null ? new AddressInfo() : addressInfo;
    }

    public AddressInfo getCity() {
        AddressInfo addressInfo = this.city;
        return addressInfo == null ? new AddressInfo() : addressInfo;
    }

    public AddressInfo.AddressAreas getCurrAreaAddressAreas() {
        AddressInfo.AddressAreas addressAreas = this.currAreaAddressAreas;
        return addressAreas == null ? new AddressInfo.AddressAreas() : addressAreas;
    }

    public AddressInfo.AddressAreas getCurrCityAddressAreas() {
        AddressInfo.AddressAreas addressAreas = this.currCityAddressAreas;
        return addressAreas == null ? new AddressInfo.AddressAreas() : addressAreas;
    }

    public AddressInfo.AddressAreas getCurrProviceAddressAreas() {
        AddressInfo.AddressAreas addressAreas = this.currProviceAddressAreas;
        return addressAreas == null ? new AddressInfo.AddressAreas() : addressAreas;
    }

    public AddressInfo.AddressAreas getCurrTownsAddressAreas() {
        AddressInfo.AddressAreas addressAreas = this.currTownsAddressAreas;
        return addressAreas == null ? new AddressInfo.AddressAreas() : addressAreas;
    }

    public AddressInfo getProvices() {
        AddressInfo addressInfo = this.provices;
        return addressInfo == null ? new AddressInfo() : addressInfo;
    }

    public AddressInfo getTown() {
        AddressInfo addressInfo = this.town;
        return addressInfo == null ? new AddressInfo() : addressInfo;
    }

    public void setAddressGlobal(AddressGlobal addressGlobal) {
        this.addressGlobal = addressGlobal;
    }

    public void setArea(AddressInfo addressInfo) {
        this.area = addressInfo;
    }

    public void setCity(AddressInfo addressInfo) {
        this.city = addressInfo;
    }

    public void setCurrAreaAddressAreas(AddressInfo.AddressAreas addressAreas) {
        this.currAreaAddressAreas = addressAreas;
    }

    public void setCurrCityAddressAreas(AddressInfo.AddressAreas addressAreas) {
        this.currCityAddressAreas = addressAreas;
    }

    public void setCurrProviceAddressAreas(AddressInfo.AddressAreas addressAreas) {
        this.currProviceAddressAreas = addressAreas;
    }

    public void setCurrTownsAddressAreas(AddressInfo.AddressAreas addressAreas) {
        this.currTownsAddressAreas = addressAreas;
    }

    public void setProvices(AddressInfo addressInfo) {
        this.provices = addressInfo;
    }

    public void setTown(AddressInfo addressInfo) {
        this.town = addressInfo;
    }
}
