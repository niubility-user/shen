package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import g.b.a.a.a;
import g.b.a.a.b;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
@GwtCompatible
/* loaded from: classes12.dex */
public final class InternetDomainName {
    private static final CharMatcher DASH_MATCHER;
    private static final String DOT_REGEX = "\\.";
    private static final int MAX_DOMAIN_PART_LENGTH = 63;
    private static final int MAX_LENGTH = 253;
    private static final int MAX_PARTS = 127;
    private static final int NO_SUFFIX_FOUND = -1;
    private static final CharMatcher PART_CHAR_MATCHER;
    private final String name;
    private final ImmutableList<String> parts;
    private final int publicSuffixIndex;
    private final int registrySuffixIndex;
    private static final CharMatcher DOTS_MATCHER = CharMatcher.anyOf(".\u3002\uff0e\uff61");
    private static final Splitter DOT_SPLITTER = Splitter.on((char) OrderISVUtil.MONEY_DECIMAL_CHAR);
    private static final Joiner DOT_JOINER = Joiner.on((char) OrderISVUtil.MONEY_DECIMAL_CHAR);

    static {
        CharMatcher anyOf = CharMatcher.anyOf("-_");
        DASH_MATCHER = anyOf;
        PART_CHAR_MATCHER = CharMatcher.javaLetterOrDigit().or(anyOf);
    }

    InternetDomainName(String str) {
        String lowerCase = Ascii.toLowerCase(DOTS_MATCHER.replaceFrom(str, OrderISVUtil.MONEY_DECIMAL_CHAR));
        lowerCase = lowerCase.endsWith(OrderISVUtil.MONEY_DECIMAL) ? lowerCase.substring(0, lowerCase.length() - 1) : lowerCase;
        Preconditions.checkArgument(lowerCase.length() <= 253, "Domain name too long: '%s':", lowerCase);
        this.name = lowerCase;
        ImmutableList<String> copyOf = ImmutableList.copyOf(DOT_SPLITTER.split(lowerCase));
        this.parts = copyOf;
        Preconditions.checkArgument(copyOf.size() <= 127, "Domain has too many parts: '%s'", lowerCase);
        Preconditions.checkArgument(validateSyntax(copyOf), "Not a valid domain name: '%s'", lowerCase);
        this.publicSuffixIndex = findSuffixOfType(Optional.absent());
        this.registrySuffixIndex = findSuffixOfType(Optional.of(b.REGISTRY));
    }

    private InternetDomainName ancestor(int i2) {
        Joiner joiner = DOT_JOINER;
        ImmutableList<String> immutableList = this.parts;
        return from(joiner.join(immutableList.subList(i2, immutableList.size())));
    }

    private int findSuffixOfType(Optional<b> optional) {
        int size = this.parts.size();
        for (int i2 = 0; i2 < size; i2++) {
            String join = DOT_JOINER.join(this.parts.subList(i2, size));
            if (matchesType(optional, Optional.fromNullable(a.a.get(join)))) {
                return i2;
            }
            if (a.f19392c.containsKey(join)) {
                return i2 + 1;
            }
            if (matchesWildcardSuffixType(optional, join)) {
                return i2;
            }
        }
        return -1;
    }

    public static InternetDomainName from(String str) {
        return new InternetDomainName((String) Preconditions.checkNotNull(str));
    }

    public static boolean isValid(String str) {
        try {
            from(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    private static boolean matchesType(Optional<b> optional, Optional<b> optional2) {
        return optional.isPresent() ? optional.equals(optional2) : optional2.isPresent();
    }

    private static boolean matchesWildcardSuffixType(Optional<b> optional, String str) {
        String[] split = str.split(DOT_REGEX, 2);
        return split.length == 2 && matchesType(optional, Optional.fromNullable(a.b.get(split[1])));
    }

    private static boolean validatePart(String str, boolean z) {
        if (str.length() >= 1 && str.length() <= 63) {
            if (!PART_CHAR_MATCHER.matchesAllOf(CharMatcher.ascii().retainFrom(str))) {
                return false;
            }
            CharMatcher charMatcher = DASH_MATCHER;
            if (!charMatcher.matches(str.charAt(0)) && !charMatcher.matches(str.charAt(str.length() - 1))) {
                return (z && CharMatcher.digit().matches(str.charAt(0))) ? false : true;
            }
        }
        return false;
    }

    private static boolean validateSyntax(List<String> list) {
        int size = list.size() - 1;
        if (validatePart(list.get(size), true)) {
            for (int i2 = 0; i2 < size; i2++) {
                if (!validatePart(list.get(i2), false)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public InternetDomainName child(String str) {
        return from(((String) Preconditions.checkNotNull(str)) + OrderISVUtil.MONEY_DECIMAL + this.name);
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InternetDomainName) {
            return this.name.equals(((InternetDomainName) obj).name);
        }
        return false;
    }

    public boolean hasParent() {
        return this.parts.size() > 1;
    }

    public boolean hasPublicSuffix() {
        return this.publicSuffixIndex != -1;
    }

    public boolean hasRegistrySuffix() {
        return this.registrySuffixIndex != -1;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean isPublicSuffix() {
        return this.publicSuffixIndex == 0;
    }

    public boolean isRegistrySuffix() {
        return this.registrySuffixIndex == 0;
    }

    public boolean isTopDomainUnderRegistrySuffix() {
        return this.registrySuffixIndex == 1;
    }

    public boolean isTopPrivateDomain() {
        return this.publicSuffixIndex == 1;
    }

    public boolean isUnderPublicSuffix() {
        return this.publicSuffixIndex > 0;
    }

    public boolean isUnderRegistrySuffix() {
        return this.registrySuffixIndex > 0;
    }

    public InternetDomainName parent() {
        Preconditions.checkState(hasParent(), "Domain '%s' has no parent", this.name);
        return ancestor(1);
    }

    public ImmutableList<String> parts() {
        return this.parts;
    }

    public InternetDomainName publicSuffix() {
        if (hasPublicSuffix()) {
            return ancestor(this.publicSuffixIndex);
        }
        return null;
    }

    public InternetDomainName registrySuffix() {
        if (hasRegistrySuffix()) {
            return ancestor(this.registrySuffixIndex);
        }
        return null;
    }

    public String toString() {
        return this.name;
    }

    public InternetDomainName topDomainUnderRegistrySuffix() {
        if (isTopDomainUnderRegistrySuffix()) {
            return this;
        }
        Preconditions.checkState(isUnderRegistrySuffix(), "Not under a registry suffix: %s", this.name);
        return ancestor(this.registrySuffixIndex - 1);
    }

    public InternetDomainName topPrivateDomain() {
        if (isTopPrivateDomain()) {
            return this;
        }
        Preconditions.checkState(isUnderPublicSuffix(), "Not under a public suffix: %s", this.name);
        return ancestor(this.publicSuffixIndex - 1);
    }
}
