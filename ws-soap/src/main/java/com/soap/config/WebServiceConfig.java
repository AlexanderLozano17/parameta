package com.soap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import demo.soap.util.Constants;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

    // Configuración del servlet para el servicio SOAP
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

     //Configuración del WSDL
    @Bean(name = "employee")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema employeeSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("EmployeePort"); // Nombre lógico del servicio
        wsdl.setLocationUri("/ws");           // Ruta donde se expone el servicio
        wsdl.setTargetNamespace("http://localhost:8081/soap/employee"); // Namespace del XML (ajustado)
        wsdl.setSchema(employeeSchema);       // Esquema XSD para generar el WSDL
        return wsdl;
    }

    // Carga el esquema XSD desde el recurso en el classpath
    @Bean
    public XsdSchema employeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/employee.xsd"));
    }
}
