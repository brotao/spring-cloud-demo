package demo.msa.framework.config;

import demo.msa.framework.registry.ServiceRegistry;
import demo.msa.framework.registry.ServiceRegistryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix = "registry")
public class RegistryConfig {

    @Value("${registry.servers}")
    private String servers;

    @Bean
    public ServiceRegistry serviceRegistry() {
        return new ServiceRegistryImpl(servers);
    }

}
