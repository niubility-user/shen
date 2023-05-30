package com.laser.open.nfc.c;

/* loaded from: classes12.dex */
public enum d {
    SUCCESS(0, "SUCCESS"),
    BASE_STATUS_WALLET_BIND_SUCCESS(20000, "\u94b1\u5305\u670d\u52a1\u7ed1\u5b9a\u6210\u529f"),
    BASE_STATUS_WALLET_SERVICE_BIND_FAIL(20001, "\u94b1\u5305\u670d\u52a1\u7ed1\u5b9a\u5931\u8d25"),
    BASE_STATUS_WALLET_IS_NOT_EXIST(20002, "\u94b1\u5305\u670d\u52a1\u4e0d\u5b58\u5728"),
    BASE_STATUS_WALLET_INTERFACE_IS_NOT_EXIST(20003, "\u94b1\u5305\u670d\u52a1\u63a5\u53e3\u529f\u80fd\u4e0d\u652f\u6301"),
    BASE_STATUS_LOCAL_ERROR(20004, "\u672c\u5730\u9519\u8bef"),
    BASE_STATUS_CARD_DOWNLOADED(20005, "\u5361\u7247\u5df2\u6dfb\u52a0"),
    BASE_STATUS_CARD_NOT_DOWNLOAD(20006, "\u5361\u7247\u672a\u6dfb\u52a0"),
    BASE_STATUS_REQUEST_SERVER_FAIL(20007, "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25"),
    BASE_STATUS_SE_ISSUER_UNSUPPORT(20008, "\u4e0d\u652f\u6301\u7684SE\u53d1\u884c\u65b9");
    
    private String msg;
    private int status;

    d(int i2, String str) {
        this.status = i2;
        this.msg = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setStatus(int i2) {
        this.status = i2;
    }
}
