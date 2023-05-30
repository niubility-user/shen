package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\b\u0010\u0006J\r\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\nR\u0016\u0010\u000e\u001a\u00020\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\rR\u0016\u0010\u0010\u001a\u00020\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\rR\u0016\u0010\u0011\u001a\u00020\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\r\u00a8\u0006\u0016"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/InstructionViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "", "questionText", "", "c", "(Ljava/lang/String;)V", "answerText", "b", "d", "()V", "Landroid/widget/TextView;", com.jingdong.jdsdk.a.a.a, "Landroid/widget/TextView;", "tvQuestion", "tvQuestionIndicator", "tvAnswerIndicator", "tvAnswer", "Landroid/view/View;", "itemView", "<init>", "(Landroid/view/View;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class InstructionViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a  reason: from kotlin metadata */
    private final TextView tvQuestion;

    /* renamed from: b  reason: from kotlin metadata */
    private final TextView tvQuestionIndicator;

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private final TextView tvAnswer;

    /* renamed from: d  reason: from kotlin metadata */
    private final TextView tvAnswerIndicator;

    public InstructionViewHolder(@NotNull View view) {
        super(view);
        View findViewById = view.findViewById(R.id.tv_item_large_payment_instruction_question);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "itemView.findViewById(R.\u2026ent_instruction_question)");
        this.tvQuestion = (TextView) findViewById;
        View findViewById2 = view.findViewById(R.id.tv_item_large_payment_instruction_question_indicator);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "itemView.findViewById(R.\u2026ction_question_indicator)");
        this.tvQuestionIndicator = (TextView) findViewById2;
        View findViewById3 = view.findViewById(R.id.tv_item_large_payment_instruction_answer);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "itemView.findViewById(R.\u2026yment_instruction_answer)");
        this.tvAnswer = (TextView) findViewById3;
        View findViewById4 = view.findViewById(R.id.tv_item_large_payment_instruction_answer_indicator);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "itemView.findViewById(R.\u2026ruction_answer_indicator)");
        this.tvAnswerIndicator = (TextView) findViewById4;
    }

    public final void b(@Nullable String answerText) {
        TextView textView = this.tvAnswer;
        if (answerText == null) {
            answerText = "";
        }
        textView.setText(answerText);
    }

    public final void c(@Nullable String questionText) {
        TextView textView = this.tvQuestion;
        if (questionText == null) {
            questionText = "";
        }
        textView.setText(questionText);
    }

    public final void d() {
        this.tvQuestion.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626, JDDarkUtil.COLOR_ECECEC));
        this.tvQuestionIndicator.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626, JDDarkUtil.COLOR_ECECEC));
        this.tvAnswer.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C, JDDarkUtil.COLOR_848383));
        this.tvAnswerIndicator.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C, JDDarkUtil.COLOR_848383));
    }
}
