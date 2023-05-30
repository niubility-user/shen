package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdDrugInfo implements Serializable {
    public ArrayList<DrugDetails> drugDetails;
    public String imageInCell;
    public String jumpButtonText;
    public boolean showDetail;
    public ArrayList<String> tips;
    public String title;

    /* loaded from: classes15.dex */
    public static class DrugDetails implements Serializable {
        public String desc;
        public int sortNum;
        public String title;
    }
}
