package owner.config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:${device}.properties", //такойго файла нет
        "classpath:mobile.properties", //зачитает этот файл
})
public interface MobileConfig extends Config {

    @Key("platform.name")
    String getPlatformName();

    @Key("device.name")
    String getDeviceName();

    @Key("platform.version")
    String getPlatformVersion();

}
