package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class NewAssetSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewAssetSystemApplication.class, args);
	}
//	@Bean
//	public Docket postsApi() {
//			return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(apiInfo()).select().build();
//		}
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("simplifyingtech API")
//	.description("simplifyingtech API for developers").termsOfServiceUrl("https://simplifyingtechcode.wordpress.com/").licenseUrl("simplifyingtech@gmail.com").version("2.0").build();
//		}
//	@Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:3000");
//            }
//        };
//    }

}
