package com.ucp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class
 * Used for scanning directory for spring annotations
 */
@Configuration
@ComponentScan("com.ucp")
public class SpringConfiguration {
}
