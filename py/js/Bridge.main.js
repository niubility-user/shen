Java.perform(function () {
    // 获取目标类
    var TargetClass = Java.use('com.jd.security.jdguard.core.Bridge');

    // 辅助函数，用于将Java byte[]转换为字符串
    function byteArrayToString(javaByteArray) {
        var StringClass = Java.use("java.lang.String");
        var CharsetClass = Java.use('java.nio.charset.Charset');
        var utf8Charset = CharsetClass.forName('UTF-8');

        return StringClass.$new(uint8ArrayFromArray(javaByteArray), utf8Charset).toString();
    }

    function uint8ArrayFromArray(arr) {
        var uint8Array = new Uint8Array(arr.length);
        for (var i = 0; i < arr.length; ++i) {
            uint8Array[i] = arr[i];
        }
        return uint8Array;
    }

    // 打印 objArr 参数
    function printObjectArray(objArr) {
        console.log('objArr:');
        console.log('Element 0:', byteArrayToString(objArr[0]));
        console.log('Element 1:', objArr[1].toString());
        console.log('Element 2:', objArr[2].toString());
        console.log('Element 3:', objArr[3].toString());
        console.log('Element 4:', objArr[4].toString());
    }

    // 重写 main 方法
    TargetClass.main.implementation = function (i2, objArr) {
        console.log('\nCalled main method with:');
        console.log('i2: ' + i2);
        printObjectArray(objArr);

        // 调用原始方法
        var originalResult = this.main(i2, objArr);

        // 确保结果为 Java Object[]，然后打印
        if (originalResult && typeof originalResult === 'object') {
            console.log('Original result:');
            printObjectArray(originalResult);
        } else {
            console.log('Original result:', originalResult);
        }

        // 返回原始结果给调用者
        return originalResult;
    };
});