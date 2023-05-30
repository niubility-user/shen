package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class GradualPayInfo implements ICheckNullObj {
    public static final String HAS_GRADUAL_PAID = "1";
    public static final String NEED_POPUP_DIALOG = "1";
    public int alreadyPaidTimes;
    public List<GradualInstructionEntity> graduallyPayReadme;
    public String alreadyPaid = "";
    public String alreadyPaidAmount = "";
    public String graduallyPayTip = "";
    public String minAmount = "";
    public String suggestedAmount = "";
    public String totalAmount = "";
    public String popup = "";

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.graduallyPayReadme == null) {
            this.graduallyPayReadme = new ArrayList();
        }
        g0.f(this.graduallyPayReadme);
        if (this.graduallyPayReadme.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.graduallyPayReadme.size(); i2++) {
            GradualInstructionEntity gradualInstructionEntity = this.graduallyPayReadme.get(i2);
            if (gradualInstructionEntity != null) {
                gradualInstructionEntity.checkNullObjAndInit();
            }
        }
    }
}
