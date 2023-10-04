package com.mycompany.studentmanagementapp.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

public class CustomPathResourceResolver extends PathResourceResolver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected Resource getResource(String resourcePath, Resource location) {
        try {
            Resource requestedResource = location.createRelative(resourcePath);
            if (requestedResource.exists() && requestedResource.isReadable()) {
                return requestedResource;
            } else if (resourcePath.endsWith(".html")) {
                String htmlResourcePath = resourcePath.substring(0, resourcePath.lastIndexOf(".")) + ".html";
                Resource htmlResource = location.createRelative(htmlResourcePath);
                if (htmlResource.exists() && htmlResource.isReadable()) {
                    return htmlResource;
                }
            }
            // If neither the requested resource nor the .html file is found, fall back to index.html
            return new ClassPathResource("/static/" + resourcePath + ".html");

        } catch (IOException e) {
            // Handle the exception here or log it if needed
            // You can choose to take some specific action or simply ignore it
            // Example: log.error("An error occurred while resolving the resource.", e);
            logger.error("An error occurred while resolving the resource.", e);
            return null; // Return null or another default resource as needed
        }
    }
}