package com.jingdong.common.search;

import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.utils.AddressUtil;

/* loaded from: classes6.dex */
public class SingletonState {
    private String addressGlobal_where;
    public long timesTamp;
    public boolean useAddressGlobal;
    private boolean isHomeDetailOperation = false;
    private boolean userClickRedDot = false;

    /* loaded from: classes6.dex */
    private static class StateHolder {
        private static final SingletonState INSTANCE = new SingletonState();

        private StateHolder() {
        }
    }

    public static SingletonState getInstance() {
        return StateHolder.INSTANCE;
    }

    public String getAddressGlobal_where() {
        return this.addressGlobal_where;
    }

    public boolean getHomeDetailOperation() {
        return this.isHomeDetailOperation;
    }

    public boolean isUserClickRedDot() {
        return this.userClickRedDot;
    }

    public void saveAddressGlobal_where() {
        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
        if (addressGlobal != null) {
            this.addressGlobal_where = addressGlobal.getWhere();
        }
    }

    public void setHomeDetailOperation() {
        this.isHomeDetailOperation = true;
    }

    public void setUseAddressGlobal(boolean z) {
        this.useAddressGlobal = z;
    }

    public void setUserClickRedDot(boolean z) {
        this.userClickRedDot = z;
    }
}
