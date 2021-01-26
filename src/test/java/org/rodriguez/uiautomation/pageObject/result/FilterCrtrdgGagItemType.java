package org.rodriguez.uiautomation.pageObject.result;

public enum FilterCrtrdgGagItemType {
    LUGER_9MM("facetButton_5_-2004_3074457345618277137_38_9mm_Luger"),
    LUGGER_9MM_P("facetButton_5_-2004_3074457345618277137_1_9mm_Luger_+P");

    private String itmId;

    FilterCrtrdgGagItemType(String itmId) { this.itmId = itmId; }

    public String getItmId() { return itmId; }
}
