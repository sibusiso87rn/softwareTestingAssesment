package co.za.testing.core.components;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    public ScenarioContext(){
        scenarioContext = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        scenarioContext.put(key, value);
    }

    public Object getContext(String key){
        return scenarioContext.get(key);
    }

    public Boolean isContains(String key){
        return scenarioContext.containsKey(key);
    }

    public ArrayList<String> getList(String key){
        return (ArrayList<String>)getContext(key);
    }
}
