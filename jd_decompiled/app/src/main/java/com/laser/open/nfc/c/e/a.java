package com.laser.open.nfc.c.e;

import com.laser.open.nfc.model.entity.BaseResp;
import com.laser.open.nfc.model.entity.GetDeleteSignInfoResp;
import com.laser.open.nfc.model.entity.QueryIssueCardInfoResp;
import com.laser.open.nfc.model.entity.QueryTrafficCardInfoResp;
import com.laser.open.nfc.model.entity.SEInfoResp;

/* loaded from: classes12.dex */
public interface a {
    BaseResp a(QueryIssueCardInfoResp queryIssueCardInfoResp);

    BaseResp a(QueryIssueCardInfoResp queryIssueCardInfoResp, GetDeleteSignInfoResp getDeleteSignInfoResp);

    BaseResp a(QueryIssueCardInfoResp queryIssueCardInfoResp, String str);

    BaseResp a(QueryIssueCardInfoResp queryIssueCardInfoResp, String str, int i2);

    BaseResp a(QueryIssueCardInfoResp queryIssueCardInfoResp, String str, int i2, String str2);

    QueryTrafficCardInfoResp a(QueryIssueCardInfoResp queryIssueCardInfoResp, int i2);

    SEInfoResp a();

    BaseResp b(QueryIssueCardInfoResp queryIssueCardInfoResp, int i2);
}
