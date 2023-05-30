package com.jdcloud.media.common.bean;

import java.io.Serializable;

/* loaded from: classes18.dex */
public class DescribeAuthenticateResult implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer blacklist;
    private String license;
    private String pId;
    private Integer status;
    private Integer ver;

    public DescribeAuthenticateResult blacklist(Integer num) {
        this.blacklist = num;
        return this;
    }

    public Integer getBlacklist() {
        return this.blacklist;
    }

    public String getLicense() {
        return this.license;
    }

    public String getPId() {
        return this.pId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getVer() {
        return this.ver;
    }

    public DescribeAuthenticateResult license(String str) {
        this.license = str;
        return this;
    }

    public DescribeAuthenticateResult pId(String str) {
        this.pId = str;
        return this;
    }

    public void setBlacklist(Integer num) {
        this.blacklist = num;
    }

    public void setLicense(String str) {
        this.license = str;
    }

    public void setPId(String str) {
        this.pId = str;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public void setVer(Integer num) {
        this.ver = num;
    }

    public DescribeAuthenticateResult status(Integer num) {
        this.status = num;
        return this;
    }

    public DescribeAuthenticateResult ver(Integer num) {
        this.ver = num;
        return this;
    }
}
