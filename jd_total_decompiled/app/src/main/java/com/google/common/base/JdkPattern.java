package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@GwtIncompatible
/* loaded from: classes12.dex */
final class JdkPattern extends CommonPattern implements Serializable {
    private static final long serialVersionUID = 0;
    private final Pattern pattern;

    /* loaded from: classes12.dex */
    private static final class JdkMatcher extends CommonMatcher {
        final Matcher matcher;

        JdkMatcher(Matcher matcher) {
            this.matcher = (Matcher) Preconditions.checkNotNull(matcher);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.base.CommonMatcher
        public int end() {
            return this.matcher.end();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.base.CommonMatcher
        public boolean find() {
            return this.matcher.find();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.base.CommonMatcher
        public boolean matches() {
            return this.matcher.matches();
        }

        @Override // com.google.common.base.CommonMatcher
        String replaceAll(String str) {
            return this.matcher.replaceAll(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.base.CommonMatcher
        public int start() {
            return this.matcher.start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.base.CommonMatcher
        public boolean find(int i2) {
            return this.matcher.find(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JdkPattern(Pattern pattern) {
        this.pattern = (Pattern) Preconditions.checkNotNull(pattern);
    }

    @Override // com.google.common.base.CommonPattern
    public boolean equals(Object obj) {
        if (obj instanceof JdkPattern) {
            return this.pattern.equals(((JdkPattern) obj).pattern);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.base.CommonPattern
    public int flags() {
        return this.pattern.flags();
    }

    @Override // com.google.common.base.CommonPattern
    public int hashCode() {
        return this.pattern.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.base.CommonPattern
    public CommonMatcher matcher(CharSequence charSequence) {
        return new JdkMatcher(this.pattern.matcher(charSequence));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.base.CommonPattern
    public String pattern() {
        return this.pattern.pattern();
    }

    @Override // com.google.common.base.CommonPattern
    public String toString() {
        return this.pattern.toString();
    }
}
