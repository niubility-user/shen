package com.jdpay.membercode.bean;

import com.jdpay.lib.Bean;
import com.jdpay.lib.annotation.Name;
import java.util.List;

/* loaded from: classes18.dex */
public class CodeOpenInfoBean implements Bean {
    @Name("btnText")
    public String actionText;
    @Name("logo")
    public String logoUrl;
    @Name("protocolInfo")
    public ProtocolBean protocolInfo;
    @Name("introductionList")
    public List<String> tips;
    @Name("title")
    public String title;
}
