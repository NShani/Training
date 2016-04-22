package com.wso2.test.api.tierapi.server;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wso2.test.api.tierapi.bean.ContainerSpec;
import com.wso2.test.api.tierapi.dao.impl.ContainerSpecDaoImpl;
import com.wso2.test.api.tierapi.delegate.DAOdelegate;

@Path("/containerSpecs")
public class ContainerSpecSrvice {
    
    private ContainerSpecDaoImpl ContainerSpecInstance= (ContainerSpecDaoImpl) DAOdelegate.getContainerSpecInstance();
    
    /*
     * Get all Plans
     * @return                  Return all plans
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<ContainerSpec> getPlans() throws SQLException {
        
        return ContainerSpecInstance.getAllContainerSpecs();
    }

    /*
     * Get Plan using Plan ID
     * @param planId            Plan ID of the plan
     * @return                  Return the plan refer to the Plan ID
     */
    @GET
    @Path("/{containerSpecId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ContainerSpec getPlan(@PathParam("containerSpecId") int containerSpecId) throws SQLException {
        
        return ContainerSpecInstance.getContainerSpecById(containerSpecId);
    }

}
