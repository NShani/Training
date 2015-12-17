package com.nadeeshani.test.jaxws;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "com.nadeeshani.test.jaxws.HelloWorldTest")
public class HelloWorldImplMyOne implements HelloWorldTest{

	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}

}