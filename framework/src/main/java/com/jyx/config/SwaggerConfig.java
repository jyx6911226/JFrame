package com.jyx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import com.google.common.base.Predicates;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类:
 */

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.jyx.controller")
public class SwaggerConfig {
    /**
     *
     * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/
     * resources/webjars/映射
     *
     * 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义（我没有尝试）
     *
     * 重写该方法需要 extends WebMvcConfigurerAdapter
     *
     *
     *
     */

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // registry.addResourceHandler("swagger-ui.html")
    // .addResourceLocations("classpath:/META-INF/resources/");
    //
    // registry.addResourceHandler("/webjars/**")
    // .addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }

    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * <p>
     * （访问页面就可以看到效果了）
     */
    @SuppressWarnings("unchecked")
    @Bean
    public Docket restApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("rest")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                //.paths(Predicates.or(PathSelectors.regex("/api/.*")))// 过滤的接口
                .build();
        //.apiInfo(testApiInfo());
        return docket;
    }
//	@SuppressWarnings("unchecked")
//	@Bean
//	public Docket demoApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("demo")
//				.genericModelSubstitutes(DeferredResult.class)
//				// .genericModelSubstitutes(ResponseEntity.class)
//				.useDefaultResponseMessages(false)
//				.forCodeGeneration(false)
//				.pathMapping("/")
//				.select()
//				.paths(Predicates.or(PathSelectors.regex("/demo/.*")))// 过滤的接口
//				.build();
//				//.apiInfo(demoApiInfo());
//	}

//	@SuppressWarnings("deprecation")
//	private ApiInfo testApiInfo() {
//		ApiInfo apiInfo = new ApiInfo("Test相关接口", // 大标题
//				"Test相关接口，主要用于测试.", // 小标题
//				"1.0", // 版本
//				"http://412887952-qq-com.iteye.com/",
//				"Angel", // 作者
//				"北京知远信息科技有限公司", // 链接显示文字
//				"http://www.kfit.com.cn/"// 网站链接
//		);
//		return apiInfo;
//	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("接口列表", // 大标题
				"REST接口", // 小标题
				"1.0", // 版本
				"https://user.qzone.qq.com/369906430",
				"nono", // 作者
				"wiztek", // 链接显示文字
				"http://www.baidu.com.cn/"// 网站链接
		);
		return apiInfo;
	}
}