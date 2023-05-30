package com.jingdong.sdk.deeplink;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public final class a {
    private final String a;
    private final String b;

    /* renamed from: c */
    private final Set<String> f14704c;
    private final Pattern d;

    /* renamed from: com.jingdong.sdk.deeplink.a$a */
    /* loaded from: classes7.dex */
    public enum EnumC0712a {
        CLASS,
        METHOD
    }

    public a(String str, String str2, EnumC0712a enumC0712a, String str3, String str4) {
        DeepLinkUri parse = DeepLinkUri.parse(str2);
        if (parse != null) {
            String g2 = g(parse);
            this.a = str;
            this.b = str3;
            this.f14704c = f(parse);
            this.d = Pattern.compile(g2.replaceAll("%7B(([a-zA-Z][a-zA-Z0-9_-]*))%7D", "([a-zA-Z0-9_#!%-~,\\.\\$]*)") + "$");
            return;
        }
        throw new IllegalArgumentException("deeplink cannot parse uri:" + str2);
    }

    private static String e(DeepLinkUri deepLinkUri) {
        return deepLinkUri.encodedPath();
    }

    private static Set<String> f(DeepLinkUri deepLinkUri) {
        Matcher matcher = Pattern.compile("%7B(([a-zA-Z][a-zA-Z0-9_-]*))%7D").matcher(deepLinkUri.encodedHost() + deepLinkUri.encodedPath());
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }

    private String g(DeepLinkUri deepLinkUri) {
        return deepLinkUri.scheme() + "://" + deepLinkUri.encodedHost() + e(deepLinkUri);
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public Map<String, String> c(String str) {
        DeepLinkUri parse;
        HashMap hashMap = new HashMap(this.f14704c.size());
        if (str == null || (parse = DeepLinkUri.parse(str)) == null) {
            return hashMap;
        }
        Matcher matcher = this.d.matcher(g(parse));
        if (matcher.matches()) {
            int i2 = 1;
            for (String str2 : this.f14704c) {
                int i3 = i2 + 1;
                String group = matcher.group(i2);
                if (group != null && !"".equals(group.trim())) {
                    hashMap.put(str2, group);
                }
                i2 = i3;
            }
        }
        return hashMap;
    }

    public boolean d(String str) {
        DeepLinkUri parse = DeepLinkUri.parse(str);
        return parse != null && this.d.matcher(g(parse)).find();
    }
}
