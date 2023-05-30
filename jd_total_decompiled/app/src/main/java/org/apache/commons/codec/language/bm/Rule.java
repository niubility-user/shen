package org.apache.commons.codec.language.bm;

import com.jingdong.common.utils.LangUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages;

/* loaded from: classes11.dex */
public class Rule {
    public static final String ALL = "ALL";
    private static final String DOUBLE_QUOTE = "\"";
    private static final String HASH_INCLUDE = "#include";
    private final RPattern lContext;
    private final String pattern;
    private final PhonemeExpr phoneme;
    private final RPattern rContext;
    public static final RPattern ALL_STRINGS_RMATCHER = new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.1
        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return true;
        }
    };
    private static final Map<NameType, Map<RuleType, Map<String, Map<String, List<Rule>>>>> RULES = new EnumMap(NameType.class);

    /* loaded from: classes11.dex */
    public interface PhonemeExpr {
        Iterable<Phoneme> getPhonemes();
    }

    /* loaded from: classes11.dex */
    public static final class PhonemeList implements PhonemeExpr {
        private final List<Phoneme> phonemes;

        public PhonemeList(List<Phoneme> list) {
            this.phonemes = list;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.PhonemeExpr
        public List<Phoneme> getPhonemes() {
            return this.phonemes;
        }
    }

    /* loaded from: classes11.dex */
    public interface RPattern {
        boolean isMatch(CharSequence charSequence);
    }

    static {
        for (NameType nameType : NameType.values()) {
            EnumMap enumMap = new EnumMap(RuleType.class);
            for (RuleType ruleType : RuleType.values()) {
                HashMap hashMap = new HashMap();
                for (String str : Languages.getInstance(nameType).getLanguages()) {
                    try {
                        hashMap.put(str, parseRules(createScanner(nameType, ruleType, str), createResourceName(nameType, ruleType, str)));
                    } catch (IllegalStateException e2) {
                        throw new IllegalStateException("Problem processing " + createResourceName(nameType, ruleType, str), e2);
                    }
                }
                if (!ruleType.equals(RuleType.RULES)) {
                    hashMap.put("common", parseRules(createScanner(nameType, ruleType, "common"), createResourceName(nameType, ruleType, "common")));
                }
                enumMap.put((EnumMap) ruleType, (RuleType) Collections.unmodifiableMap(hashMap));
            }
            RULES.put(nameType, Collections.unmodifiableMap(enumMap));
        }
    }

    public Rule(String str, String str2, String str3, PhonemeExpr phonemeExpr) {
        this.pattern = str;
        this.lContext = pattern(str2 + "$");
        this.rContext = pattern("^" + str3);
        this.phoneme = phonemeExpr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean contains(CharSequence charSequence, char c2) {
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (charSequence.charAt(i2) == c2) {
                return true;
            }
        }
        return false;
    }

    private static String createResourceName(NameType nameType, RuleType ruleType, String str) {
        return String.format("org/apache/commons/codec/language/bm/%s_%s_%s.txt", nameType.getName(), ruleType.getName(), str);
    }

    private static Scanner createScanner(NameType nameType, RuleType ruleType, String str) {
        String createResourceName = createResourceName(nameType, ruleType, str);
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(createResourceName);
        if (resourceAsStream != null) {
            return new Scanner(resourceAsStream, "UTF-8");
        }
        throw new IllegalArgumentException("Unable to load resource: " + createResourceName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean endsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        int length = charSequence.length() - 1;
        for (int length2 = charSequence2.length() - 1; length2 >= 0; length2--) {
            if (charSequence.charAt(length) != charSequence2.charAt(length2)) {
                return false;
            }
            length--;
        }
        return true;
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, Languages.LanguageSet languageSet) {
        Map<String, List<Rule>> instanceMap = getInstanceMap(nameType, ruleType, languageSet);
        ArrayList arrayList = new ArrayList();
        Iterator<List<Rule>> it = instanceMap.values().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next());
        }
        return arrayList;
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType ruleType, Languages.LanguageSet languageSet) {
        return languageSet.isSingleton() ? getInstanceMap(nameType, ruleType, languageSet.getAny()) : getInstanceMap(nameType, ruleType, Languages.ANY);
    }

    private static Phoneme parsePhoneme(String str) {
        int indexOf = str.indexOf("[");
        if (indexOf >= 0) {
            if (str.endsWith("]")) {
                return new Phoneme(str.substring(0, indexOf), Languages.LanguageSet.from(new HashSet(Arrays.asList(str.substring(indexOf + 1, str.length() - 1).split("[+]")))));
            }
            throw new IllegalArgumentException("Phoneme expression contains a '[' but does not end in ']'");
        }
        return new Phoneme(str, Languages.ANY_LANGUAGE);
    }

    private static PhonemeExpr parsePhonemeExpr(String str) {
        if (str.startsWith("(")) {
            if (str.endsWith(")")) {
                ArrayList arrayList = new ArrayList();
                String substring = str.substring(1, str.length() - 1);
                for (String str2 : substring.split("[|]")) {
                    arrayList.add(parsePhoneme(str2));
                }
                if (substring.startsWith("|") || substring.endsWith("|")) {
                    arrayList.add(new Phoneme("", Languages.ANY_LANGUAGE));
                }
                return new PhonemeList(arrayList);
            }
            throw new IllegalArgumentException("Phoneme starts with '(' so must end with ')'");
        }
        return parsePhoneme(str);
    }

    private static Map<String, List<Rule>> parseRules(Scanner scanner, String str) {
        String str2;
        String stripQuotes;
        String stripQuotes2;
        String stripQuotes3;
        HashMap hashMap = new HashMap();
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (scanner.hasNextLine()) {
            int i4 = i3 + 1;
            String nextLine = scanner.nextLine();
            if (z) {
                if (nextLine.endsWith("*/")) {
                    z = false;
                }
            } else if (nextLine.startsWith("/*")) {
                z = true;
            } else {
                int indexOf = nextLine.indexOf("//");
                String trim = (indexOf >= 0 ? nextLine.substring(i2, indexOf) : nextLine).trim();
                if (trim.length() == 0) {
                    i3 = i4;
                } else if (trim.startsWith(HASH_INCLUDE)) {
                    String trim2 = trim.substring(8).trim();
                    if (!trim2.contains(LangUtils.SINGLE_SPACE)) {
                        hashMap.putAll(parseRules(createScanner(trim2), str + "->" + trim2));
                    } else {
                        throw new IllegalArgumentException("Malformed import statement '" + nextLine + "' in " + str);
                    }
                } else {
                    String[] split = trim.split("\\s+");
                    if (split.length == 4) {
                        try {
                            stripQuotes = stripQuotes(split[i2]);
                            stripQuotes2 = stripQuotes(split[1]);
                            stripQuotes3 = stripQuotes(split[2]);
                            str2 = "' in ";
                        } catch (IllegalArgumentException e2) {
                            e = e2;
                            str2 = "' in ";
                        }
                        try {
                            Rule rule = new Rule(stripQuotes, stripQuotes2, stripQuotes3, parsePhonemeExpr(stripQuotes(split[3])), i4, str, stripQuotes, stripQuotes2, stripQuotes3) { // from class: org.apache.commons.codec.language.bm.Rule.2
                                private final String loc;
                                private final int myLine;
                                final /* synthetic */ int val$cLine;
                                final /* synthetic */ String val$lCon;
                                final /* synthetic */ String val$location;
                                final /* synthetic */ String val$pat;
                                final /* synthetic */ String val$rCon;

                                {
                                    this.val$cLine = i4;
                                    this.val$location = str;
                                    this.val$pat = stripQuotes;
                                    this.val$lCon = stripQuotes2;
                                    this.val$rCon = stripQuotes3;
                                    this.myLine = i4;
                                    this.loc = str;
                                }

                                public String toString() {
                                    return "Rule{line=" + this.myLine + ", loc='" + this.loc + "', pat='" + this.val$pat + "', lcon='" + this.val$lCon + "', rcon='" + this.val$rCon + "'}";
                                }
                            };
                            String substring = rule.pattern.substring(0, 1);
                            List list = (List) hashMap.get(substring);
                            if (list == null) {
                                list = new ArrayList();
                                hashMap.put(substring, list);
                            }
                            list.add(rule);
                        } catch (IllegalArgumentException e3) {
                            e = e3;
                            throw new IllegalStateException("Problem parsing line '" + i4 + str2 + str, e);
                        }
                    } else {
                        throw new IllegalArgumentException("Malformed rule statement split into " + split.length + " parts: " + nextLine + " in " + str);
                    }
                }
            }
            i3 = i4;
            i2 = 0;
        }
        return hashMap;
    }

    private static RPattern pattern(String str) {
        boolean startsWith = str.startsWith("^");
        boolean endsWith = str.endsWith("$");
        int length = str.length();
        if (endsWith) {
            length--;
        }
        final String substring = str.substring(startsWith ? 1 : 0, length);
        if (substring.contains("[")) {
            boolean startsWith2 = substring.startsWith("[");
            boolean endsWith2 = substring.endsWith("]");
            if (startsWith2 && endsWith2) {
                final String substring2 = substring.substring(1, substring.length() - 1);
                if (!substring2.contains("[")) {
                    boolean startsWith3 = substring2.startsWith("^");
                    if (startsWith3) {
                        substring2 = substring2.substring(1);
                    }
                    final boolean z = !startsWith3;
                    if (startsWith && endsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.7
                            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() == 1 && Rule.contains(substring2, charSequence.charAt(0)) == z;
                            }
                        };
                    }
                    if (startsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.8
                            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() > 0 && Rule.contains(substring2, charSequence.charAt(0)) == z;
                            }
                        };
                    }
                    if (endsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.9
                            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() > 0 && Rule.contains(substring2, charSequence.charAt(charSequence.length() - 1)) == z;
                            }
                        };
                    }
                }
            }
        } else if (startsWith && endsWith) {
            if (substring.length() == 0) {
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.3
                    @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                    public boolean isMatch(CharSequence charSequence) {
                        return charSequence.length() == 0;
                    }
                };
            }
            return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.4
                @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                public boolean isMatch(CharSequence charSequence) {
                    return charSequence.equals(substring);
                }
            };
        } else if ((startsWith || endsWith) && substring.length() == 0) {
            return ALL_STRINGS_RMATCHER;
        } else {
            if (startsWith) {
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.5
                    @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                    public boolean isMatch(CharSequence charSequence) {
                        return Rule.startsWith(charSequence, substring);
                    }
                };
            }
            if (endsWith) {
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.6
                    @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                    public boolean isMatch(CharSequence charSequence) {
                        return Rule.endsWith(charSequence, substring);
                    }
                };
            }
        }
        return new RPattern(str) { // from class: org.apache.commons.codec.language.bm.Rule.10
            Pattern pattern;
            final /* synthetic */ String val$regex;

            {
                this.val$regex = str;
                this.pattern = Pattern.compile(str);
            }

            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
            public boolean isMatch(CharSequence charSequence) {
                return this.pattern.matcher(charSequence).find();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean startsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        for (int i2 = 0; i2 < charSequence2.length(); i2++) {
            if (charSequence.charAt(i2) != charSequence2.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    private static String stripQuotes(String str) {
        if (str.startsWith(DOUBLE_QUOTE)) {
            str = str.substring(1);
        }
        return str.endsWith(DOUBLE_QUOTE) ? str.substring(0, str.length() - 1) : str;
    }

    public RPattern getLContext() {
        return this.lContext;
    }

    public String getPattern() {
        return this.pattern;
    }

    public PhonemeExpr getPhoneme() {
        return this.phoneme;
    }

    public RPattern getRContext() {
        return this.rContext;
    }

    public boolean patternAndContextMatches(CharSequence charSequence, int i2) {
        if (i2 >= 0) {
            int length = this.pattern.length() + i2;
            if (length <= charSequence.length() && charSequence.subSequence(i2, length).equals(this.pattern) && this.rContext.isMatch(charSequence.subSequence(length, charSequence.length()))) {
                return this.lContext.isMatch(charSequence.subSequence(0, i2));
            }
            return false;
        }
        throw new IndexOutOfBoundsException("Can not match pattern at negative indexes");
    }

    /* loaded from: classes11.dex */
    public static final class Phoneme implements PhonemeExpr {
        public static final Comparator<Phoneme> COMPARATOR = new Comparator<Phoneme>() { // from class: org.apache.commons.codec.language.bm.Rule.Phoneme.1
            @Override // java.util.Comparator
            public int compare(Phoneme phoneme, Phoneme phoneme2) {
                for (int i2 = 0; i2 < phoneme.phonemeText.length(); i2++) {
                    if (i2 >= phoneme2.phonemeText.length()) {
                        return 1;
                    }
                    int charAt = phoneme.phonemeText.charAt(i2) - phoneme2.phonemeText.charAt(i2);
                    if (charAt != 0) {
                        return charAt;
                    }
                }
                return phoneme.phonemeText.length() < phoneme2.phonemeText.length() ? -1 : 0;
            }
        };
        private final Languages.LanguageSet languages;
        private final StringBuilder phonemeText;

        public Phoneme(CharSequence charSequence, Languages.LanguageSet languageSet) {
            this.phonemeText = new StringBuilder(charSequence);
            this.languages = languageSet;
        }

        public Phoneme append(CharSequence charSequence) {
            this.phonemeText.append(charSequence);
            return this;
        }

        public Languages.LanguageSet getLanguages() {
            return this.languages;
        }

        public CharSequence getPhonemeText() {
            return this.phonemeText;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.PhonemeExpr
        public Iterable<Phoneme> getPhonemes() {
            return Collections.singleton(this);
        }

        @Deprecated
        public Phoneme join(Phoneme phoneme) {
            return new Phoneme(this.phonemeText.toString() + phoneme.phonemeText.toString(), this.languages.restrictTo(phoneme.languages));
        }

        public Phoneme mergeWithLanguage(Languages.LanguageSet languageSet) {
            return new Phoneme(this.phonemeText.toString(), this.languages.merge(languageSet));
        }

        public String toString() {
            return this.phonemeText.toString() + "[" + this.languages + "]";
        }

        public Phoneme(Phoneme phoneme, Phoneme phoneme2) {
            this(phoneme.phonemeText, phoneme.languages);
            this.phonemeText.append((CharSequence) phoneme2.phonemeText);
        }

        public Phoneme(Phoneme phoneme, Phoneme phoneme2, Languages.LanguageSet languageSet) {
            this(phoneme.phonemeText, languageSet);
            this.phonemeText.append((CharSequence) phoneme2.phonemeText);
        }
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType ruleType, String str) {
        Map<String, List<Rule>> map = RULES.get(nameType).get(ruleType).get(str);
        if (map != null) {
            return map;
        }
        throw new IllegalArgumentException(String.format("No rules found for %s, %s, %s.", nameType.getName(), ruleType.getName(), str));
    }

    private static Scanner createScanner(String str) {
        String format = String.format("org/apache/commons/codec/language/bm/%s.txt", str);
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(format);
        if (resourceAsStream != null) {
            return new Scanner(resourceAsStream, "UTF-8");
        }
        throw new IllegalArgumentException("Unable to load resource: " + format);
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, String str) {
        return getInstance(nameType, ruleType, Languages.LanguageSet.from(new HashSet(Arrays.asList(str))));
    }
}
