package com.paipai.library.inspect.dataclass;

import java.util.List;

/* loaded from: classes9.dex */
public class PpSpecificationInfo {
    private List<PpBaseOutlines> baseOutlines;
    private String code;
    private String codeName;
    private String inspectDesc;
    private int qualityId;
    private List<PpReportOutlines> reportOutlines;

    public List<PpBaseOutlines> getBaseOutlines() {
        return this.baseOutlines;
    }

    public String getCode() {
        return this.code;
    }

    public String getCodeName() {
        return this.codeName;
    }

    public String getInspectDesc() {
        return this.inspectDesc;
    }

    public int getQualityId() {
        return this.qualityId;
    }

    public List<PpReportOutlines> getReportOutlines() {
        return this.reportOutlines;
    }

    public void setBaseOutlines(List<PpBaseOutlines> list) {
        this.baseOutlines = list;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setCodeName(String str) {
        this.codeName = str;
    }

    public void setInspectDesc(String str) {
        this.inspectDesc = str;
    }

    public void setQualityId(int i2) {
        this.qualityId = i2;
    }

    public void setReportOutlines(List<PpReportOutlines> list) {
        this.reportOutlines = list;
    }
}
