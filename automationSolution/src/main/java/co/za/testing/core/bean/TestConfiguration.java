package co.za.testing.core.bean;

import co.za.testing.common.CommonFunctions;
import co.za.testing.common.ScenarioContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean(name="commonFunctions")
    public CommonFunctions commonFunctions() {
        return new CommonFunctions();
    }

    @Bean(name="scenarioContext")
    public ScenarioContext scenarioContext() {
        return new ScenarioContext();
    }
}
