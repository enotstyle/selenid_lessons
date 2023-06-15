package owner.config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:owner/${device}.properties", //такойго файла нет
        "classpath:owner/mobile.properties", //зачитает этот файл
})
public interface MobileConfig extends Config {

    @Key("platform.name")
    String getPlatformName();

    @Key("device.name")
    String getDeviceName();

    @Key("platform.version")
    String getPlatformVersion();

}
