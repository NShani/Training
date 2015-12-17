package com.nadeeshani.test.jaxws;
import javax.xml.ws.Endpoint;
//Endpoint publisher
public class HelloWorldPublisher{
 
	public static void main(String[] args) {
	   Endpoint.publish("http://localhost:7034/web/helloow", new HelloWorldImplMyOne());
    }
 
}