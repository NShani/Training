package com.wso2.appcloud.testValve;

 
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.wso2.carbon.databridge.agent.AgentHolder;
import org.wso2.carbon.databridge.agent.DataPublisher;
import org.wso2.carbon.databridge.agent.exception.DataEndpointAgentConfigurationException;
import org.wso2.carbon.databridge.agent.exception.DataEndpointAuthenticationException;
import org.wso2.carbon.databridge.agent.exception.DataEndpointConfigurationException;
import org.wso2.carbon.databridge.agent.exception.DataEndpointException;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.exception.TransportException;
/**
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0
 */
public class ProcessingValve extends ValveBase {
    private DataPublisher dataPublisher = null;
    private static final Log logger = LogFactory.getLog(ProcessingValve.class.getName());
    private String streamId = "org.wso2.sample.http.stats:1.0.0";
    private String tomcatHome;
    /**
     * {@inheritDoc}
     */
    
    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = request.getRequest();
 
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        String type = "Thrift";
        String receiverURL = "tcp://172.16.4.1:7611";
        String authURL="ssl://172.16.4.1:7711";
        String username = "admin";
        String password = "admin";
        //AgentHolder. setConfigPath(“/path/to/data/agent/conf.xml”)
        
        File userDir = new File(System.getProperty("catalina.base"));
        String appName=System.getProperty("catalina.webAppName").toString();
        System.out.println("++++++++++++++++++++"+appName);
        tomcatHome = userDir.getAbsolutePath();
       
        AgentHolder.setConfigPath(getDataAgentConfigPath());
        //AgentHolder. setConfigPath("/home/nadeeshani/Documents/Software/apache-tomcat-7.0.65/");
        try {
            dataPublisher = new DataPublisher(type, receiverURL, authURL, username, password);
           // dataPublisher = new DataPublisher( receiverURL, username, password);
        } catch (DataEndpointAgentConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DataEndpointException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DataEndpointConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DataEndpointAuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransportException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            getNext().invoke(request, response);
        } catch (ServletException e) {
            logger.error("Invoke failed:" + e);
        }
//        Object[] payload = new Object[2];
//        payload[0] = "localhost";
//        payload[1] = "shani_app";
        
        List<Object> payload = new ArrayList<Object>();
        payload.add("localhost");
        payload.add(appName);
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            logger.info(new Object[]{header, httpServletRequest.getHeader(header)});
            String server=httpServletRequest.getServerName();
            System.out.println("..................."+server);
        }
        Event event = new Event(streamId, System.currentTimeMillis(), null, null, payload.toArray());
        
        dataPublisher.publish(event);
        logger.info(event.toString());
        /*
         *  Object[] payload = new Object[11];
        payload[0] = "localhost";
        payload[1] = "shani_app";
        payload[2] = "service";
        payload[3] = "localhost";
        payload[4] = "shani_app";
        payload[5] = "service";
        payload[6] = "type";
        payload[7] = 2L;
        payload[8] = "code";
        payload[9] = 8;
        payload[10] = 2L;
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            logger.info(new Object[]{header, httpServletRequest.getHeader(header)});
        }
         */
//        logg("ASDF===== asadsfdwefwjenfewj");
        
     //   getNext().invoke(request, response);
        
        
        /*
        
        Long startTime = System.currentTimeMillis();
        try {
            getNext().invoke(request, response);
        } catch (IOException | ServletException e) {
            LOG.error("Invoke failed:" + e);
        }
        long responseTime = System.currentTimeMillis() - startTime;

        EventBuilder eventbuilder = new EventBuilder();
        WebappMonitoringEvent webappMonitoringEvent = eventbuilder.setStatData(request, response,
                startTime, responseTime);

        if (LOG.isDebugEnabled()) {
            LOG.debug("publishing the HTTP Stat : " + webappMonitoringEvent);
        }
        try {
            Event event = eventbuilder.prepareEvent(getStreamId(), webappMonitoringEvent);
            dataPublisher.publish(event);
        } catch (MonitoringPublisherException e) {
            e.printStackTrace();
        }
        
        
        */
    }
    private String getDataAgentConfigPath() {
        Path path = Paths.get(tomcatHome, "conf/Security", "data-agent-conf.xml");
        return path.toString();
        }
    
    
    
    
}