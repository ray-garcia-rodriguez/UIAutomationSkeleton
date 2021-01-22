package org.rodriguez.uiautomation.pageObject.base;

public enum NavBarShtngItmType {
    HANDGUN_AMMO("3074457345616732421_3074457345616732423_3074457345616735006");

    private String subCtgryId;

    NavBarShtngItmType(String subCtgryId) { this.subCtgryId = subCtgryId; }

    public String getSubCtgryId() { return subCtgryId; }
}
