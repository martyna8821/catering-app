package pl.martyna.catering.app.configuration;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import org.apache.coyote.http2.Http2Protocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@Configuration
@EnableAspectJAutoProxy
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class BasicConfiguration {

    @Value( "${blobservice.connection.string}" )
    private String blobConnectionString;

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public ConfigurableServletWebServerFactory tomcatCustomizer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(connector ->
                    connector.addUpgradeProtocol(
                                    new Http2Protocol()));
        return factory;
    }

    @Bean("arial")
    @Scope("prototype")
    public PdfFont getBaseArialFontWithPolishLetters() throws IOException {

       FontProgram arialFont = FontProgramFactory.createFont(
               "/home/martyna/git/api-catering/catering-app/catering-api/src/main/resources/arial.ttf");
        return PdfFontFactory.createFont(arialFont,"CP1250" ,true);
    }

    @Bean("arial-bold")

    @Scope("prototype")
    public PdfFont getBaseArialBoldFontWithPolishLetters() throws IOException {

        FontProgram arialBoldFont = FontProgramFactory.createFont(
                "/home/martyna/git/api-catering/catering-app/catering-api/src/main/resources/arial-bold.ttf");

        return PdfFontFactory.createFont(arialBoldFont,"CP1250" ,true);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    @Bean
    public BlobServiceClient getBlobServiceClient(){
        return new BlobServiceClientBuilder().connectionString(blobConnectionString).buildClient();

    }
}
