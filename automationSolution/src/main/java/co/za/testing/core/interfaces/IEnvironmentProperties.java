package co.za.testing.core.interfaces;

import org.aeonbits.owner.Config;

//Reads the system properties and looks for the "environment" property
@Config.Sources({
        "classpath:application.properties"
})

//Service Properties
public interface IEnvironmentProperties extends Config {
    @Key("browser")
    String getDefaultBrowser();

    @Key("browser.version")
    String getDefaultBrowserVersion();

    @Key("applicationUrl")
    String getDefaultApplicationUrl();

}
