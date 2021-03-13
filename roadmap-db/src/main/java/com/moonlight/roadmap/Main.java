package com.moonlight.roadmap;

import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;

public class Main {
    // "/url:{url}", "/user:{user}", "/password:{password}"
    public static void main(String[] args) {
        Map<String, String> params = getParams(args);
        String url = params.get("url");
        String user = params.get("user");
        String password = params.get("password");
        migrate(url, user, password);
    }

    private static Map<String, String> getParams(String[] args) {
        Map<String, Map<String, String>> config = new Yaml().load(Main.class.getClassLoader().getResourceAsStream("application.yaml"));
        Map<String, String> databaseConfig = config.get("database");

        Map<String, String> params = new HashMap<>();
        for (String arg : args) {
            int colonIndex = arg.indexOf(":");
            if (colonIndex != -1) {
                String key = arg.substring(1, colonIndex);
                String value = arg.substring(colonIndex + 1);
                params.put(key, value);
            }
        }

        databaseConfig.forEach((key, value) -> {
            if (StringUtils.isBlank(params.get(key))) {
                params.put(key, value);
            }
        });
        return params;
    }

    private static void migrate(String url, String user, String password) {
        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();
    }
}
