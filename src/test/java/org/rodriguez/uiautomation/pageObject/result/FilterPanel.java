package org.rodriguez.uiautomation.pageObject.result;

import org.rodriguez.uiautomation.DriverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilterPanel {
    @Autowired
    private DriverFactory driverFactory;
    @Autowired
    private FilterPanelSection filterPanelSection;

    public void filterSection(String fltrPnlSctnTypStr, String ... fltrItmTypStrs) {
        filterPanelSection.filterSectionWItms(fltrPnlSctnTypStr, fltrItmTypStrs);
    }
}
