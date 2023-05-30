package org.apache.commons.codec.language;

import com.jd.dynamic.DYConstants;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

/* loaded from: classes11.dex */
public class DaitchMokotoffSoundex implements StringEncoder {
    private static final String COMMENT = "//";
    private static final String DOUBLE_QUOTE = "\"";
    private static final Map<Character, Character> FOLDINGS;
    private static final int MAX_LENGTH = 6;
    private static final String MULTILINE_COMMENT_END = "*/";
    private static final String MULTILINE_COMMENT_START = "/*";
    private static final String RESOURCE_FILE = "org/apache/commons/codec/language/dmrules.txt";
    private static final Map<Character, List<Rule>> RULES;
    private final boolean folding;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class Branch {
        private final StringBuilder builder;
        private String cachedString;
        private String lastReplacement;

        public Branch createBranch() {
            Branch branch = new Branch();
            branch.builder.append(toString());
            branch.lastReplacement = this.lastReplacement;
            return branch;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Branch) {
                return toString().equals(((Branch) obj).toString());
            }
            return false;
        }

        public void finish() {
            while (this.builder.length() < 6) {
                this.builder.append('0');
                this.cachedString = null;
            }
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public void processNextReplacement(String str, boolean z) {
            String str2 = this.lastReplacement;
            if ((str2 == null || !str2.endsWith(str) || z) && this.builder.length() < 6) {
                this.builder.append(str);
                if (this.builder.length() > 6) {
                    StringBuilder sb = this.builder;
                    sb.delete(6, sb.length());
                }
                this.cachedString = null;
            }
            this.lastReplacement = str;
        }

        public String toString() {
            if (this.cachedString == null) {
                this.cachedString = this.builder.toString();
            }
            return this.cachedString;
        }

        private Branch() {
            this.builder = new StringBuilder();
            this.lastReplacement = null;
            this.cachedString = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class Rule {
        private final String pattern;
        private final String[] replacementAtStart;
        private final String[] replacementBeforeVowel;
        private final String[] replacementDefault;

        protected Rule(String str, String str2, String str3, String str4) {
            this.pattern = str;
            this.replacementAtStart = str2.split(DYConstants.DY_REGEX_VERTICAL_LINE);
            this.replacementBeforeVowel = str3.split(DYConstants.DY_REGEX_VERTICAL_LINE);
            this.replacementDefault = str4.split(DYConstants.DY_REGEX_VERTICAL_LINE);
        }

        private boolean isVowel(char c2) {
            return c2 == 'a' || c2 == 'e' || c2 == 'i' || c2 == 'o' || c2 == 'u';
        }

        public int getPatternLength() {
            return this.pattern.length();
        }

        public String[] getReplacements(String str, boolean z) {
            if (z) {
                return this.replacementAtStart;
            }
            int patternLength = getPatternLength();
            if (patternLength < str.length() ? isVowel(str.charAt(patternLength)) : false) {
                return this.replacementBeforeVowel;
            }
            return this.replacementDefault;
        }

        public boolean matches(String str) {
            return str.startsWith(this.pattern);
        }

        public String toString() {
            return String.format("%s=(%s,%s,%s)", this.pattern, Arrays.asList(this.replacementAtStart), Arrays.asList(this.replacementBeforeVowel), Arrays.asList(this.replacementDefault));
        }
    }

    static {
        HashMap hashMap = new HashMap();
        RULES = hashMap;
        HashMap hashMap2 = new HashMap();
        FOLDINGS = hashMap2;
        InputStream resourceAsStream = DaitchMokotoffSoundex.class.getClassLoader().getResourceAsStream(RESOURCE_FILE);
        if (resourceAsStream != null) {
            Scanner scanner = new Scanner(resourceAsStream, "UTF-8");
            parseRules(scanner, RESOURCE_FILE, hashMap, hashMap2);
            scanner.close();
            Iterator it = hashMap.entrySet().iterator();
            while (it.hasNext()) {
                Collections.sort((List) ((Map.Entry) it.next()).getValue(), new Comparator<Rule>() { // from class: org.apache.commons.codec.language.DaitchMokotoffSoundex.1
                    @Override // java.util.Comparator
                    public int compare(Rule rule, Rule rule2) {
                        return rule2.getPatternLength() - rule.getPatternLength();
                    }
                });
            }
            return;
        }
        throw new IllegalArgumentException("Unable to load resource: org/apache/commons/codec/language/dmrules.txt");
    }

    public DaitchMokotoffSoundex() {
        this(true);
    }

    private String cleanup(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c2 : str.toCharArray()) {
            if (!Character.isWhitespace(c2)) {
                char lowerCase = Character.toLowerCase(c2);
                if (this.folding) {
                    Map<Character, Character> map = FOLDINGS;
                    if (map.containsKey(Character.valueOf(lowerCase))) {
                        lowerCase = map.get(Character.valueOf(lowerCase)).charValue();
                    }
                }
                sb.append(lowerCase);
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0091, code lost:
        throw new java.lang.IllegalArgumentException("Malformed folding statement - patterns are not single characters: " + r4 + " in " + r12);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void parseRules(java.util.Scanner r11, java.lang.String r12, java.util.Map<java.lang.Character, java.util.List<org.apache.commons.codec.language.DaitchMokotoffSoundex.Rule>> r13, java.util.Map<java.lang.Character, java.lang.Character> r14) {
        /*
            Method dump skipped, instructions count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.DaitchMokotoffSoundex.parseRules(java.util.Scanner, java.lang.String, java.util.Map, java.util.Map):void");
    }

    private static String stripQuotes(String str) {
        if (str.startsWith(DOUBLE_QUOTE)) {
            str = str.substring(1);
        }
        return str.endsWith(DOUBLE_QUOTE) ? str.substring(0, str.length() - 1) : str;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("Parameter supplied to DaitchMokotoffSoundex encode is not of type java.lang.String");
    }

    public String soundex(String str) {
        String[] soundex = soundex(str, true);
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (String str2 : soundex) {
            sb.append(str2);
            i2++;
            if (i2 < soundex.length) {
                sb.append('|');
            }
        }
        return sb.toString();
    }

    public DaitchMokotoffSoundex(boolean z) {
        this.folding = z;
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        if (str == null) {
            return null;
        }
        return soundex(str, false)[0];
    }

    private String[] soundex(String str, boolean z) {
        String str2;
        int i2;
        String str3;
        if (str == null) {
            return null;
        }
        String cleanup = cleanup(str);
        LinkedHashSet<Branch> linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new Branch());
        int i3 = 0;
        char c2 = 0;
        while (i3 < cleanup.length()) {
            char charAt = cleanup.charAt(i3);
            if (!Character.isWhitespace(charAt)) {
                String substring = cleanup.substring(i3);
                List<Rule> list = RULES.get(Character.valueOf(charAt));
                if (list != null) {
                    List arrayList = z ? new ArrayList() : Collections.EMPTY_LIST;
                    Iterator<Rule> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            str2 = cleanup;
                            i2 = 1;
                            break;
                        }
                        Rule next = it.next();
                        if (next.matches(substring)) {
                            if (z) {
                                arrayList.clear();
                            }
                            String[] replacements = next.getReplacements(substring, c2 == 0);
                            boolean z2 = replacements.length > 1 && z;
                            for (Branch branch : linkedHashSet) {
                                int length = replacements.length;
                                int i4 = 0;
                                while (true) {
                                    if (i4 >= length) {
                                        str3 = cleanup;
                                        break;
                                    }
                                    String str4 = replacements[i4];
                                    Branch createBranch = z2 ? branch.createBranch() : branch;
                                    str3 = cleanup;
                                    createBranch.processNextReplacement(str4, (c2 == 'm' && charAt == 'n') || (c2 == 'n' && charAt == 'm'));
                                    if (z) {
                                        arrayList.add(createBranch);
                                        i4++;
                                        cleanup = str3;
                                    }
                                }
                                cleanup = str3;
                            }
                            str2 = cleanup;
                            if (z) {
                                linkedHashSet.clear();
                                linkedHashSet.addAll(arrayList);
                            }
                            i2 = 1;
                            i3 += next.getPatternLength() - 1;
                        }
                    }
                    c2 = charAt;
                    i3 += i2;
                    cleanup = str2;
                }
            }
            str2 = cleanup;
            i2 = 1;
            i3 += i2;
            cleanup = str2;
        }
        String[] strArr = new String[linkedHashSet.size()];
        int i5 = 0;
        for (Branch branch2 : linkedHashSet) {
            branch2.finish();
            strArr[i5] = branch2.toString();
            i5++;
        }
        return strArr;
    }
}
