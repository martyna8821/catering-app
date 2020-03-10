package pl.martyna.catering.app.configuration;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

@Configuration
@EnableAspectJAutoProxy
public class BasicConfiguration {

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
    public BaseFont getBaseArialFontWithPolishLetters() throws IOException, DocumentException {

       return BaseFont.createFont(
               "/home/martyna/git/api-catering/catering-app/catering-api/src/main/resources/arial.ttf",
                      BaseFont.CP1250,
                      BaseFont.EMBEDDED);
    }

    @Bean("arial-bold")
    public BaseFont getBaseArialBoldFontWithPolishLetters() throws IOException, DocumentException {

        return BaseFont.createFont(
                "/home/martyna/git/api-catering/catering-app/catering-api/src/main/resources/arial-bold.ttf",
                       BaseFont.CP1250,
                       BaseFont.EMBEDDED);
    }
}
