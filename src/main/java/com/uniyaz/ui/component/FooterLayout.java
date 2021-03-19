package com.uniyaz.ui.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class FooterLayout extends HorizontalLayout {


    public FooterLayout() {
        setSizeFull();

        Label footerLabel=new Label();
        footerLabel.setCaption("Copy right 2021");
        footerLabel.addStyleName(ValoTheme.LABEL_LARGE);

        addComponent(footerLabel);
        setComponentAlignment(footerLabel, Alignment.MIDDLE_CENTER);

    }
}
