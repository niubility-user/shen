package kotlin.text;

import java.util.Set;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u001c\u0010\u0002\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u0006\u001a\"\u0010\u0002\u001a\u00020\u0001*\u00020\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\t\u00a8\u0006\n"}, d2 = {"", "Lkotlin/text/Regex;", "toRegex", "(Ljava/lang/String;)Lkotlin/text/Regex;", "Lkotlin/text/RegexOption;", "option", "(Ljava/lang/String;Lkotlin/text/RegexOption;)Lkotlin/text/Regex;", "", "options", "(Ljava/lang/String;Ljava/util/Set;)Lkotlin/text/Regex;", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
public class StringsKt__RegexExtensionsKt extends StringsKt__RegexExtensionsJVMKt {
    @InlineOnly
    private static final Regex toRegex(@NotNull String str) {
        return new Regex(str);
    }

    @InlineOnly
    private static final Regex toRegex(@NotNull String str, RegexOption regexOption) {
        return new Regex(str, regexOption);
    }

    @InlineOnly
    private static final Regex toRegex(@NotNull String str, Set<? extends RegexOption> set) {
        return new Regex(str, set);
    }
}
