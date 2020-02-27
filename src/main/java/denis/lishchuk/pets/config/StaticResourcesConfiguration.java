package denis.lishchuk.pets.config;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import static denis.lishchuk.pets.service.FileService.IMG_DIR;


@Configuration
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
public class StaticResourcesConfiguration implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(IMG_DIR);
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + IMG_DIR);
    }

}
