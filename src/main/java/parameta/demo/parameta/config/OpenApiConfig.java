package parameta.demo.parameta.config;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
	
	@Value("${spring.application.name}")
	private String appName;
	
	private final String baseUrl = "http://localhost:8080/";
		
	@Bean
    public OpenAPI customOpenApi() {
		
		Server devServer = new Server()
                .url(baseUrl)
                .description("Servidor de desarrollo");
		
        return new OpenAPI()
                .info(new Info()
                        .title(appName + " API")
                        .version("1.0.0")
                        .description("""
                            Esta API está construida con Spring Boot.
                            
                            ### Características:                            
                            - Documentación generada automáticamente con springdoc-openapi
                        """))
                .servers(List.of(devServer));
	}
	
	@Bean
	public GroupedOpenApi group() {
		return GroupedOpenApi.builder()
				.group(appName + " API")
				.pathsToMatch("/api/roles/**",
						"/api/typeDocument/**",
						"/api/person/**", 
						"/api/employee/**")
				.build();
	}

}
