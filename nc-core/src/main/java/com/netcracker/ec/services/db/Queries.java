package com.netcracker.ec.services.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Queries {
    private static final String QUERIES_PROPERTIES = "queries.properties";
    private static Properties properties;

    private static Properties getQueries() {
        InputStream stream = Queries.class.getResourceAsStream("/" + QUERIES_PROPERTIES);
        if (stream == null) {
            throw new RuntimeException("Unable to load property file: " + QUERIES_PROPERTIES);
        }
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(stream);
            } catch (IOException e) {
                throw new RuntimeException("Unable to load property file: " + QUERIES_PROPERTIES + "\n" + e.getMessage());
            }
        }
        return properties;
    }

    public static String getQuery(String query) {
        return getQueries().getProperty(query);
    }
}
