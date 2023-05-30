package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.d.f.b;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0019B\t\b\u0016\u00a2\u0006\u0004\b\u0018\u0010\bJ\u000f\u0010\u0004\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0005\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0016\u0010\u0005\"\u0004\b\u0017\u0010\u0014\u00a8\u0006\u001a"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/OctopusRateResponse;", "Lcom/jd/lib/cashier/sdk/d/f/b;", "Lcom/jd/lib/cashier/sdk/core/model/ICheckNullObj;", "", "toString", "()Ljava/lang/String;", "", "checkNullObjAndInit", "()V", "Lcom/jd/lib/cashier/sdk/pay/bean/OctopusRateResponse$Result;", "octopusResult", "Lcom/jd/lib/cashier/sdk/pay/bean/OctopusRateResponse$Result;", "getOctopusResult", "()Lcom/jd/lib/cashier/sdk/pay/bean/OctopusRateResponse$Result;", "setOctopusResult", "(Lcom/jd/lib/cashier/sdk/pay/bean/OctopusRateResponse$Result;)V", "exBuyPrice", "Ljava/lang/String;", "getExBuyPrice", "setExBuyPrice", "(Ljava/lang/String;)V", "orderPrice", "getOrderPrice", "setOrderPrice", "<init>", "Result", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class OctopusRateResponse extends b implements ICheckNullObj {
    @NotNull
    private Result octopusResult = Result.SUCCESS;
    @NotNull
    private String orderPrice = "";
    @NotNull
    private String exBuyPrice = "";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/OctopusRateResponse$Result;", "", "<init>", "(Ljava/lang/String;I)V", "SUCCESS", "FAILURE", "cashier_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public enum Result {
        SUCCESS,
        FAILURE
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
    }

    @NotNull
    public final String getExBuyPrice() {
        return this.exBuyPrice;
    }

    @NotNull
    public final Result getOctopusResult() {
        return this.octopusResult;
    }

    @NotNull
    public final String getOrderPrice() {
        return this.orderPrice;
    }

    public final void setExBuyPrice(@NotNull String str) {
        this.exBuyPrice = str;
    }

    public final void setOctopusResult(@NotNull Result result) {
        this.octopusResult = result;
    }

    public final void setOrderPrice(@NotNull String str) {
        this.orderPrice = str;
    }

    @NotNull
    public String toString() {
        return "OctopusRateResponse(orderPrice='" + this.orderPrice + "', exBuyPrice='" + this.exBuyPrice + "')";
    }
}
