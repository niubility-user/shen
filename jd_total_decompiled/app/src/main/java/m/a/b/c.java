package m.a.b;

import com.jd.dynamic.DYConstants;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.utils.LangUtils;
import java.math.BigInteger;
import java.security.AccessController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes11.dex */
public class c {
    private static String b;
    private String a;

    static {
        b = (String) AccessController.doPrivileged(new m.a.a.b("java.security.debug"));
        String str = (String) AccessController.doPrivileged(new m.a.a.b("java.security.auth.debug"));
        if (b == null) {
            b = str;
        } else if (str != null) {
            b += DYConstants.DY_REGEX_COMMA + str;
        }
        String str2 = b;
        if (str2 != null) {
            String e2 = e(str2);
            b = e2;
            if (e2.equals(PersonalConstants.FUNCTION_ID_HELP_DESK)) {
                a();
            }
        }
        "0123456789abcdef".toCharArray();
    }

    public static void a() {
        System.err.println();
        System.err.println("all           turn on all debugging");
        System.err.println("access        print all checkPermission results");
        System.err.println("combiner      SubjectDomainCombiner debugging");
        System.err.println("gssloginconfig");
        System.err.println("configfile    JAAS ConfigFile loading");
        System.err.println("configparser  JAAS ConfigFile parsing");
        System.err.println("              GSS LoginConfigImpl debugging");
        System.err.println("jar           jar verification");
        System.err.println("logincontext  login context results");
        System.err.println("policy        loading and granting");
        System.err.println("provider      security provider debugging");
        System.err.println("scl           permissions SecureClassLoader assigns");
        System.err.println();
        System.err.println("The following can be used with access:");
        System.err.println();
        System.err.println("stack         include stack trace");
        System.err.println("domain        dump all domains in context");
        System.err.println("failure       before throwing exception, dump stack");
        System.err.println("              and domain that didn't have permission");
        System.err.println();
        System.err.println("The following can be used with stack and domain:");
        System.err.println();
        System.err.println("permission=<classname>");
        System.err.println("              only dump output if specified permission");
        System.err.println("              is being checked");
        System.err.println("codebase=<URL>");
        System.err.println("              only dump output if specified codebase");
        System.err.println("              is being checked");
        System.err.println();
        System.err.println("Note: Separate multiple options with a comma");
        System.exit(0);
    }

    public static c b(String str) {
        return c(str, str);
    }

    public static c c(String str, String str2) {
        if (d(str)) {
            c cVar = new c();
            cVar.a = str2;
            return cVar;
        }
        return null;
    }

    public static boolean d(String str) {
        String str2 = b;
        if (str2 == null) {
            return false;
        }
        return (str2.indexOf(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL) == -1 && b.indexOf(str) == -1) ? false : true;
    }

    private static String e(String str) {
        if (str != null) {
            StringBuffer stringBuffer = new StringBuffer();
            Matcher matcher = Pattern.compile("[Pp][Ee][Rr][Mm][Ii][Ss][Ss][Ii][Oo][Nn]=[a-zA-Z_$][a-zA-Z0-9_$]*([.][a-zA-Z_$][a-zA-Z0-9_$]*)*").matcher(new StringBuffer(str));
            StringBuffer stringBuffer2 = new StringBuffer();
            while (matcher.find()) {
                stringBuffer.append(matcher.group().replaceFirst("[Pp][Ee][Rr][Mm][Ii][Ss][Ss][Ii][Oo][Nn]=", "permission="));
                stringBuffer.append("  ");
                matcher.appendReplacement(stringBuffer2, "");
            }
            matcher.appendTail(stringBuffer2);
            Matcher matcher2 = Pattern.compile("[Cc][Oo][Dd][Ee][Bb][Aa][Ss][Ee]=[^, ;]*").matcher(stringBuffer2);
            StringBuffer stringBuffer3 = new StringBuffer();
            while (matcher2.find()) {
                stringBuffer.append(matcher2.group().replaceFirst("[Cc][Oo][Dd][Ee][Bb][Aa][Ss][Ee]=", "codebase="));
                stringBuffer.append("  ");
                matcher2.appendReplacement(stringBuffer3, "");
            }
            matcher2.appendTail(stringBuffer3);
            stringBuffer.append(stringBuffer3.toString().toLowerCase());
            return stringBuffer.toString();
        }
        return null;
    }

    public static String g(BigInteger bigInteger) {
        String bigInteger2 = bigInteger.toString(16);
        StringBuffer stringBuffer = new StringBuffer(bigInteger2.length() * 2);
        if (bigInteger2.startsWith("-")) {
            stringBuffer.append("   -");
            bigInteger2 = bigInteger2.substring(1);
        } else {
            stringBuffer.append("    ");
        }
        if (bigInteger2.length() % 2 != 0) {
            bigInteger2 = "0" + bigInteger2;
        }
        int i2 = 0;
        while (i2 < bigInteger2.length()) {
            int i3 = i2 + 2;
            stringBuffer.append(bigInteger2.substring(i2, i3));
            if (i3 != bigInteger2.length()) {
                if (i3 % 64 == 0) {
                    stringBuffer.append("\n    ");
                } else if (i3 % 8 == 0) {
                    stringBuffer.append(LangUtils.SINGLE_SPACE);
                }
            }
            i2 = i3;
        }
        return stringBuffer.toString();
    }

    public void f(String str) {
        System.err.println(this.a + ": " + str);
    }
}
