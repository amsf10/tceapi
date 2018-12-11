package br.gov.pi.tce.tceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("API TCE-PI", 
	        "http://www.tce.pi.gov.br", "antonio.moreira@tce.pi.gov.br");

	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	            .apiInfo(this.getApiInfo())
	            .useDefaultResponseMessages(false)
	            .select()
	            .apis(Predicates.and(
	                 RequestHandlerSelectors.basePackage("br.gov.pi.tce.tceapi.resource")))
	            .build();
	    }

			private ApiInfo getApiInfo() {
	        return new ApiInfoBuilder()
	            .title("TCEAPI")
	            .description("Api para teste do TCE-PI")
	            .version("1.0.0")
	            .contact(DEFAULT_CONTACT).build();
	    }

	    @Bean
	    public UiConfiguration uiConfig() {
	        return UiConfigurationBuilder.builder().filter(true)
	            .docExpansion(DocExpansion.LIST).defaultModelRendering(ModelRendering.EXAMPLE)
	            .deepLinking(true)
	            .defaultModelsExpandDepth(1).defaultModelExpandDepth(1)
	            .tagsSorter(TagsSorter.ALPHA).operationsSorter(OperationsSorter.ALPHA)
	            .displayRequestDuration(true)
	            .showExtensions(true).build();
	    }

}
