package com.uniyaz.ui;

import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.domain.Soru;
import com.uniyaz.ui.component.ContentComponent;
import com.uniyaz.ui.component.HeaderComponent;
import com.uniyaz.ui.component.MyMenuBar;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Theme("mytheme")
@Widgetset("com.uniyaz.MyAppWidgetset")
public class AuUI extends UI {

    private VerticalLayout mainLayout;
    private ContentComponent contentComponent;
    private Anket anket;
    private Soru soru;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        buildMainLayout();
        setContent(mainLayout);
    }

    private void buildMainLayout() {

        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        contentComponent = new ContentComponent();

        HeaderComponent headerComponent = new HeaderComponent();
        mainLayout.addComponent(headerComponent);

        MyMenuBar myMenuBar = new MyMenuBar();

        mainLayout.addComponent(myMenuBar);
        mainLayout.addComponent(contentComponent);

        mainLayout.setExpandRatio(headerComponent, 1f);
        mainLayout.setExpandRatio(myMenuBar, 0.9f);
        mainLayout.setExpandRatio(contentComponent, 8.1f);
    }

    public ContentComponent getContentComponent() {
        return contentComponent;
    }

    public void setContentComponent(ContentComponent contentComponent) {
        this.contentComponent = contentComponent;
    }

    public Soru getSoru() {
        return soru;
    }

    public void setSoru(Soru soru) {
        this.soru = soru;
    }

    public Anket getAnket() {
        return anket;
    }

    public void setAnket(Anket anket) {
        this.anket = anket;
    }


}