package com.uniyaz.ui.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class HeaderComponent extends VerticalLayout {

    private HorizontalLayout mainlayout;
    private Label headerLabel;


    public HeaderComponent()
    {
        setSizeFull();

        buildLayout();
        addComponent(mainlayout);

        setComponentAlignment(mainlayout, Alignment.MIDDLE_CENTER);
    }

    private void buildLayout()
    {
        mainlayout = new HorizontalLayout();
        headerLabel=new Label();
        headerLabel.setValue("Vaadin Anket Uygulaması");
        headerLabel.addStyleName(ValoTheme.LABEL_H1);
        mainlayout.addComponent(headerLabel);

    }
}
