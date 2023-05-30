package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.List;

/* loaded from: classes15.dex */
public class JpsAgreementEntity {
    public String agreement;
    public String agreementText;
    public String agreementTip;
    public List<Buttons> button;
    public boolean hasSignJPS;
    public String introText;
    public String introUrl;
    public String protocolUrl;
    public String title;

    /* loaded from: classes15.dex */
    public static class Buttons {
        public String biz;
        public boolean select;
        public String text;
    }
}
