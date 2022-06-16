package com.example.demo.config;

import com.example.demo.util.ProjectInfoUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"dev", "test","local"})
public class SwaggerConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).
                directModelSubstitute(Byte.class, Integer.class).select()
                .apis(RequestHandlerSelectors.basePackage(ProjectInfoUtils.BASE_PACKAGE)).paths
                        (PathSelectors.any()).build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        String basePackage = ProjectInfoUtils.BASE_PACKAGE;
        String service = basePackage.substring(basePackage.indexOf(".", 3) + 1) + "-service-api";
        return new ApiInfoBuilder().title(service).description(service).contact(new Contact(basePackage, "", ""))
                .version("1.0").build();
    }
}
