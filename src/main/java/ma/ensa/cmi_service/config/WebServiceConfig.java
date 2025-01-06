package ma.ensa.cmi_service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    
    @Bean(name = "requests_responses")
    public DefaultWsdl11Definition requests_responsesDefinition(@Qualifier("reqResSchema") XsdSchema reqResSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("RequestResponsePort");
        definition.setLocationUri("/ws/requests_responses");
        definition.setTargetNamespace("http://ensa.ma/cmi-service/requests_responses");
        definition.setSchema(reqResSchema);
        return definition;
    }

    @Bean
    public XsdSchema reqResSchema() {
        return new SimpleXsdSchema(new org.springframework.core.io.ClassPathResource("requests_responses.xsd"));
    }

}

