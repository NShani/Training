package com.wso2.test.api.tierapi.bean;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * Class to implement the Description Plan details
 */

@XmlRootElement
public class Plan {
    
    private int id;
    private String planName;
    private int team;
    private int maxInstances;
    private int maxLC;
    
    public Plan() {
        
    }
    
    public Plan(String planName,int team , int maxInstances, int maxLC){
        this.planName=planName;
        this.team = team;
        this.maxInstances= maxInstances;
        this.maxLC = maxLC;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlanName(String planName) {
        this.planName=planName;
    }
    
    public String getPlanName() {
        return planName;
    }
    
    public void setTeam(int team) {
        this.team=team;
    }
    
    public int getTeam() {
        return team;
    }
    
    public void setMaxInstances(int maxInstances) {
        this.maxInstances=maxInstances;
    }
    
    public int getMaxInstances() {
        return maxInstances;
    }
    
    public void setMaxLC(int maxLC) {
        this.maxLC=maxLC;
    }
    
    public int getMaxLC() {
        return maxLC;
    }
    
}
