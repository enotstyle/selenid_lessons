package demoqa.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:demoqa_config/remote.properties"
})
public interface WebConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();
}
