package com.jdpay.membercode.network;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jdpay.lib.Bean;
import com.jdpay.lib.annotation.Name;
import java.util.List;

/* loaded from: classes18.dex */
public class JDPayResultCtrlBean implements Bean {
    @Name("controlList")
    public List<JDPayCheckErrorInfoBean> btnActions;
    @Name(RemoteMessageConst.MessageBody.MSG_CONTENT)
    public String content;
    @Name("msgTitle")
    public String title;
}
