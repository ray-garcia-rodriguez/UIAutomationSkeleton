package org.rodriguez.uiautomation.pageObject.base;

public enum NavBarItemType {
    SHOOTING("3074457345616732421");

    private String dprtmntId;

    NavBarItemType(String dprtmntId) { this.dprtmntId = dprtmntId; }

    public String getDprtmntId() { return dprtmntId; }
}
