package com.wso2.test.api.tierapi.dao;

import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.wso2.test.api.tierapi.bean.ContainerSpec;

@XmlRootElement
public interface ContainerSpecsDao {
    
    /*
     * Get all defined Container Specs
     */
    public List<ContainerSpec> getAllContainerSpecs() throws SQLException;
    
    /*
     * Get Container Spec by ID
     */
    public ContainerSpec getContainerSpecById(int containerSpecId) throws SQLException;
    
    /*
     * Define new Container Spec 
     */
    public ContainerSpec defineContainerSpec(ContainerSpec containerSpec) throws SQLException;
    
    /*
     * Delete Container Spec by ID
     */
    public boolean deleteContainerSpecById (int planId) throws SQLException;
    
    /*
     * Update Container Spec by ID
     */
    public ContainerSpec updateContainerSpecById(int containerSpecId,ContainerSpec containerSpec) throws SQLException;

}
