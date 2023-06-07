Java.perform(function () {
    var String = Java.use("java.lang.String")
    String.contains.implementation = function (target) {
        if(target === "frida")
        var originalResult = this.contains(target); // 先调用原来的方法

        if (functionId === "wareBusiness") {
            console.log('\ncall getSignFromJni');
            console.log('context:', context);
            console.log('functionId:', functionId);
            console.log('body:', body);
            console.log('deviceUUID:', deviceUUID);
            console.log('platform:', platform);
            console.log('versionName:', versionName);
            console.log('result:', originalResult);
        }
        return originalResult;
    }
});