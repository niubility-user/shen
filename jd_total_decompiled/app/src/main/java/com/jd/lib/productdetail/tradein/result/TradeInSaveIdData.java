package com.jd.lib.productdetail.tradein.result;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes16.dex */
public class TradeInSaveIdData implements Serializable {
    public int code;
    public Data data;
    public boolean success;

    /* loaded from: classes16.dex */
    public static class Data implements Serializable {
        public static final int CODE_NEED_CONFIRM = 700001;
        public static final int CODE_NEED_CONFIRM_2 = 700002;
        public int code;
        public MtaData mMtaData = new MtaData();
        public String msg;
        public String popupText;
        public String qfId;
        public String toastBiz;
        public String toastEntrance;
        public String toastYJHXSource;

        /* loaded from: classes16.dex */
        public static class MtaData implements Serializable {
            public String Ttransaction_Ways = "";
            public String product_id = "";
            public String PaiPaiTrans_Ways = "";
            public String PaiPaiProduct_id = "";
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(this.qfId);
        }
    }

    public boolean isValid() {
        Data data = this.data;
        return data != null && data.isValid();
    }
}
