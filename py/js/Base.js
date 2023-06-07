function hookAntiFridaByStringContains() {
    var String = Java.use("java.lang.String")
    var CharSequence = Java.use("java.lang.CharSequence")
    var hookAntiFrida = false
    String.contains.implementation = function (target) {
        var targetString = Java.cast(target, CharSequence).toString()
        if (targetString === "frida") {
            if (!hookAntiFrida) {
                console.log('\nHook Anti-Frida Success\n');
                hookAntiFrida = true
            }
            return false;
        }
        return this.contains(target);
    }
}