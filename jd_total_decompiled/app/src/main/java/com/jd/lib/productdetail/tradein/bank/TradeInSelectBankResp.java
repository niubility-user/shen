package com.jd.lib.productdetail.tradein.bank;

import java.util.List;

/* loaded from: classes16.dex */
public class TradeInSelectBankResp {
    public String code;
    public Data data;

    /* loaded from: classes16.dex */
    public static final class Data {
        public List<BankCardItem> cardList;

        /* loaded from: classes16.dex */
        public static class BankCardItem {
            public String bankLogo;
            public String bankName;
            public String cardNoEnd;
            public String h5Url;
            public String id;
            public int selected;
            public int type;
        }
    }
}
