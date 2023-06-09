package kotlinx.coroutines.selects;

import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.app.mall.e;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0015\u0012\f\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\r\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0001\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\t\u001a\u0004\u0018\u00010\bH\u0001\u00a2\u0006\u0004\b\t\u0010\nJ5\u0010\u000f\u001a\u00020\u0005*\u00020\u000b2\u001c\u0010\u000e\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0006\u0012\u0004\u0018\u00010\b0\fH\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010JG\u0010\u000f\u001a\u00020\u0005\"\u0004\b\u0001\u0010\u0011*\b\u0012\u0004\u0012\u00028\u00010\u00122\"\u0010\u000e\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0013H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0014J[\u0010\u000f\u001a\u00020\u0005\"\u0004\b\u0001\u0010\u0015\"\u0004\b\u0002\u0010\u0011*\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u00012\"\u0010\u000e\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0013H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0018J8\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00192\u001c\u0010\u000e\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0006\u0012\u0004\u0018\u00010\b0\fH\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001cR5\u0010 \u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001e0\u001dj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001e`\u001f8\u0006@\u0006\u00a2\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000$8\u0006@\u0006\u00a2\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006,"}, d2 = {"Lkotlinx/coroutines/selects/UnbiasedSelectBuilderImpl;", "R", "Lkotlinx/coroutines/selects/SelectBuilder;", "", e.a, "", "handleBuilderException", "(Ljava/lang/Throwable;)V", "", "initSelectResult", "()Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectClause0;", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", JDReactConstant.BLOCK, "invoke", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", IShareAdapter.SHARE_ACTION_PANE, "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "", "timeMillis", "onTimeout", "(JLkotlin/jvm/functions/Function1;)V", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "Lkotlin/collections/ArrayList;", "clauses", "Ljava/util/ArrayList;", "getClauses", "()Ljava/util/ArrayList;", "Lkotlinx/coroutines/selects/SelectBuilderImpl;", "instance", "Lkotlinx/coroutines/selects/SelectBuilderImpl;", "getInstance", "()Lkotlinx/coroutines/selects/SelectBuilderImpl;", "uCont", "<init>", "(Lkotlin/coroutines/Continuation;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
@PublishedApi
/* loaded from: classes11.dex */
public final class UnbiasedSelectBuilderImpl<R> implements SelectBuilder<R> {
    @NotNull
    private final ArrayList<Function0<Unit>> clauses = new ArrayList<>();
    @NotNull
    private final SelectInstance<R> instance;

    public UnbiasedSelectBuilderImpl(@NotNull Continuation<? super R> continuation) {
        this.instance = new SelectInstance<>(continuation);
    }

    @NotNull
    public final ArrayList<Function0<Unit>> getClauses() {
        return this.clauses;
    }

    @NotNull
    public final SelectInstance<R> getInstance() {
        return this.instance;
    }

    @PublishedApi
    public final void handleBuilderException(@NotNull Throwable e2) {
        this.instance.handleBuilderException(e2);
    }

    @PublishedApi
    @Nullable
    public final Object initSelectResult() {
        if (!this.instance.isSelected()) {
            try {
                Collections.shuffle(this.clauses);
                Iterator<T> it = this.clauses.iterator();
                while (it.hasNext()) {
                    ((Function0) it.next()).invoke();
                }
            } catch (Throwable th) {
                this.instance.handleBuilderException(th);
            }
        }
        return this.instance.getResult();
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        SelectBuilder.DefaultImpls.invoke(this, selectClause2, function2);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void onTimeout(final long timeMillis, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> block) {
        this.clauses.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl$onTimeout$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                UnbiasedSelectBuilderImpl.this.getInstance().onTimeout(timeMillis, block);
            }
        });
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(@NotNull final SelectClause0 selectClause0, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.clauses.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl$invoke$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                selectClause0.registerSelectClause0(UnbiasedSelectBuilderImpl.this.getInstance(), function1);
            }
        });
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(@NotNull final SelectClause1<? extends Q> selectClause1, @NotNull final Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        this.clauses.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl$invoke$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                selectClause1.registerSelectClause1(UnbiasedSelectBuilderImpl.this.getInstance(), function2);
            }
        });
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull final SelectClause2<? super P, ? extends Q> selectClause2, final P p, @NotNull final Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        this.clauses.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl$invoke$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                selectClause2.registerSelectClause2(UnbiasedSelectBuilderImpl.this.getInstance(), p, function2);
            }
        });
    }
}
