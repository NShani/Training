package com.nadeeshani.test.jaxws;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
 
public class HelloWorldClient{
 	public static void main(String[] args) throws Exception {
 	URL url = new URL("http://localhost:7034/web/helloow?wsdl");
 
        //1st argument service URI, refer to wsdl document above
	    //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://jaxws.test.nadeeshani.com/", "HelloWorldImplMyOneService");
 
        Service service = Service.create(url, qname);
        HelloWorldTest hello = service.getPort(HelloWorldTest.class);
 
        System.out.println(hello.getHelloWorldAsString("<-       My web service"));
 
    }
 
}