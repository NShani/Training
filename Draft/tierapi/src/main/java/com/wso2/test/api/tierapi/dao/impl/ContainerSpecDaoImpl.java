package com.wso2.test.api.tierapi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.wso2.test.api.tierapi.bean.ContainerSpec;
import com.wso2.test.api.tierapi.dao.ContainerSpecsDao;
import com.wso2.test.api.tierapi.util.DBConfiguration;

@XmlRootElement
public class ContainerSpecDaoImpl implements ContainerSpecsDao{
    
    public static void main( String[] args ) throws SQLException
    {
      //  ContainerSpecDaoImpl container=new ContainerSpecDaoImpl();
        //ContainerSpec containerSpec=new ContainerSpec("m4.extra",2,3,4,3);
        //container.getContainerSpecById(4);
        //Plan plan3=container.updatePlanById(18,containerSpec);
        //System.out.println("ID"+plan3.getId()+"\n Name :"+plan3.getPlanName()+"\nMAX ins:"+plan3.getMaxInstances()+"\nMax LC"+plan3.getMaxLC()+"\nTeam :"+plan3.getTeam());
        
    }
    
    @Override
    public List<ContainerSpec> getAllContainerSpecs() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        List<ContainerSpec> containerSpecsList = new ArrayList<ContainerSpec>();
        String sql="select * from ContainerSpecs";
        
        try {
        DBConfiguration dbCon=new DBConfiguration();
        dbConnection= dbCon.getConnection();
        
        preparedStatement= dbConnection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            ContainerSpec containerSpec = new ContainerSpec();
            containerSpec.setId(rs.getInt("CON_SPEC_ID"));
            containerSpec.setConSpecName(rs.getString("CON_SPEC_NAME"));
            containerSpec.setCpu(rs.getInt("CPU"));
            containerSpec.setMemory(rs.getInt("MEMORY"));
            containerSpec.setStorage(rs.getInt("STORAGE"));
            containerSpec.setCostPerHour(rs.getInt("COST_PER_HOUR"));
            System.out.println("ID:"+containerSpec.getId()+"\nName:"+containerSpec.getConSpecName()+"\nCPU:"+containerSpec.getCpu()+"\n Memory :"+containerSpec.getMemory()+"\n Storage :"+containerSpec.getStorage()+"\n Cost :"+containerSpec.getCostPerHour());
            containerSpecsList.add(containerSpec);
        }
        } catch (SQLException e) {
            throw e;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return containerSpecsList;
    }

    @Override
    public ContainerSpec getContainerSpecById(int containerSpecId) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        ContainerSpec containerSpec = new ContainerSpec();
        String sql="select * from ContainerSpecs WHERE CON_SPEC_ID ="+containerSpecId;
    
        try {
        DBConfiguration dbCon=new DBConfiguration();
        dbConnection= dbCon.getConnection();
        
        preparedStatement= dbConnection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            containerSpec.setId(rs.getInt("CON_SPEC_ID"));
            containerSpec.setConSpecName(rs.getString("CON_SPEC_NAME"));
            containerSpec.setCpu(rs.getInt("CPU"));
            containerSpec.setMemory(rs.getInt("MEMORY"));
            containerSpec.setStorage(rs.getInt("STORAGE"));
            containerSpec.setCostPerHour(rs.getInt("COST_PER_HOUR"));
            System.out.println("ID:"+containerSpec.getId()+"\nName:"+containerSpec.getConSpecName()+"\nCPU:"+containerSpec.getCpu()+"\n Memory :"+containerSpec.getMemory()+"\n Storage :"+containerSpec.getStorage()+"\n Cost :"+containerSpec.getCostPerHour());
        }
        rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
            
        }
        return containerSpec;
    }

    @Override
    public ContainerSpec defineContainerSpec(ContainerSpec containerSpec) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String sql="INSERT INTO ContainerSpecs (CON_SPEC_NAME, CPU, MEMORY, STORAGE,COST_PER_HOUR) VALUES (?, ?, ?, ?,?)";

        try {
            DBConfiguration dbCon=new DBConfiguration();
            dbConnection= dbCon.getConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            
            
            preparedStatement.setString(1, containerSpec.getConSpecName());
            preparedStatement.setInt(2, containerSpec.getCpu());
            preparedStatement.setInt(3, containerSpec.getMemory());
            preparedStatement.setInt(4, containerSpec.getStorage());
            preparedStatement.setInt(5, containerSpec.getCostPerHour());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            String sql2="select * from ContainerSpecs WHERE CON_SPEC_NAME= ?";
            preparedStatement= dbConnection.prepareStatement(sql2);
            preparedStatement.setString(1, containerSpec.getConSpecName());
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                containerSpec.setId(rs.getInt("CON_SPEC_ID"));
                containerSpec.setConSpecName(rs.getString("CON_SPEC_NAME"));
                containerSpec.setCpu(rs.getInt("CPU"));
                containerSpec.setMemory(rs.getInt("MEMORY"));
                containerSpec.setStorage(rs.getInt("STORAGE"));
                containerSpec.setCostPerHour(rs.getInt("COST_PER_HOUR"));                
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return containerSpec;
    }

    @Override
    public boolean deleteContainerSpecById(int containerSpecId) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        boolean isDeleted;
        
        String sql="DELETE FROM ContainerSpecs WHERE CON_SPEC_ID="+containerSpecId;
    
        try {
        DBConfiguration dbCon=new DBConfiguration();
        dbConnection= dbCon.getConnection();
        
        preparedStatement= dbConnection.prepareStatement(sql);
        
        isDeleted = preparedStatement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return isDeleted;
    }

    @Override
    public ContainerSpec updateContainerSpecById(int containerSpecId,ContainerSpec containerSpec) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql="Update ContainerSpecs SET CON_SPEC_NAME=?, CPU= ?, MEMORY=?, STORAGE=?,COST_PER_HOUR=? WHERE CON_SPEC_ID = ?";
        try {
            DBConfiguration dbCon=new DBConfiguration();
            dbConnection= dbCon.getConnection();
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setString(1, containerSpec.getConSpecName());
            preparedStatement.setInt(2, containerSpec.getCpu());
            preparedStatement.setInt(3, containerSpec.getMemory());
            preparedStatement.setInt(4, containerSpec.getStorage());
            preparedStatement.setInt(5, containerSpec.getCostPerHour());
            preparedStatement.setInt(6, containerSpecId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            String sql2="select * from ContainerSpecs WHERE CON_SPEC_ID= ?";
            preparedStatement= dbConnection.prepareStatement(sql2);
            preparedStatement.setInt(1, containerSpecId);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {          
                containerSpec.setId(rs.getInt("CON_SPEC_ID"));
                containerSpec.setConSpecName(rs.getString("CON_SPEC_NAME"));
                containerSpec.setCpu(rs.getInt("CPU"));
                containerSpec.setMemory(rs.getInt("MEMORY"));
                containerSpec.setStorage(rs.getInt("STORAGE"));
                containerSpec.setCostPerHour(rs.getInt("COST_PER_HOUR"));
            } 

        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return containerSpec;
    }
}
