Java.perform(function () {
    hookAntiFridaByStringContains();
    var BitmapkitUtils = Java.use("com.jingdong.common.utils.BitmapkitUtils")
    BitmapkitUtils.getSignFromJni.implementation = function (context, functionId, body, deviceUUID, platform, versionName) {
        var originalResult = this.getSignFromJni(context, functionId, body, deviceUUID, platform, versionName); // 先调用原来的方法

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