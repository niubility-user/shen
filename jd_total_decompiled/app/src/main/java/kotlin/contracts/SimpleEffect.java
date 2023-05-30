package kotlin.contracts;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.ContractsDsl;
import org.jetbrains.annotations.NotNull;

@SinceKotlin(version = "1.3")
@ContractsDsl
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u00a7\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lkotlin/contracts/SimpleEffect;", "Lkotlin/contracts/Effect;", "", "booleanExpression", "Lkotlin/contracts/ConditionalEffect;", "implies", "(Z)Lkotlin/contracts/ConditionalEffect;", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalContracts
/* loaded from: classes11.dex */
public interface SimpleEffect extends Effect {
    @ContractsDsl
    @ExperimentalContracts
    @NotNull
    ConditionalEffect implies(boolean booleanExpression);
}
