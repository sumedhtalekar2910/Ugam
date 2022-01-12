package com.ugams.core.models;

public interface OSGiConfigModel {
    public String getServiceName();
    public int getServiceID();
    public boolean getIsService();
    public String[] getCountries() ;
    public String getRunModes();
}
