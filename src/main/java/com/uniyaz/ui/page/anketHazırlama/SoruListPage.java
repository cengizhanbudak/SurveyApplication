package com.uniyaz.ui.page.anketHazırlama;

import com.uniyaz.core.domain.Soru;
import com.uniyaz.core.service.SoruService;
import com.uniyaz.ui.AuUI;
import com.uniyaz.ui.component.ContentComponent;
import com.uniyaz.ui.component.MyEditButton;
import com.uniyaz.ui.component.MySaveButton;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;


public class SoruListPage extends VerticalLayout {

    private VerticalLayout mainLayout;
    private Table table;
    private Container container;


    public SoruListPage() {
        setSizeFull();
        buildMainLayout();
        addComponent(mainLayout);

        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        fillTable();
    }

    private void buildMainLayout() {

        mainLayout = new VerticalLayout();
        mainLayout.setSizeUndefined();

        buildTable();
        mainLayout.addComponent(table);
    }

    private void buildTable() {

        table = new Table();
        buildContainer();

        table.setContainerDataSource(container);
        table.setColumnHeaders("ID", "SORU", "ANKETID", "GÜNCELLE", "SİL","SEÇENEK EKLE");
    }


    private void buildContainer() {

        container = new IndexedContainer();
        container.addContainerProperty("ID", Long.class, null);
        container.addContainerProperty("SORU", String.class, null);
        container.addContainerProperty("ID_ANKET", Long.class, null);
        container.addContainerProperty("guncelle", MyEditButton.class, null);
        container.addContainerProperty("sil", Button.class, null);
        container.addContainerProperty("seçenek ekle", Button.class, null);
    }

    private void fillTable() {

        SoruService soruService = new SoruService();
        List<Soru> soruList = soruService.findAllHql();

        container.removeAllItems();

        for (Soru soru : soruList) {

            Item item = container.addItem(soru);

            item.getItemProperty("ID").setValue(soru.getId());
            item.getItemProperty("SORU").setValue(soru.getSoruyaz());
            item.getItemProperty("ID_ANKET").setValue(soru.getAnket().getId());

            MyEditButton guncelle = buildUpdateButton(soru);
            item.getItemProperty("guncelle").setValue(guncelle);

            Button sil = buildDeleteButton(soru);
            item.getItemProperty("sil").setValue(sil);

            MySaveButton soruEkle=buildSoruEkle(soru);
            item.getItemProperty("seçenek ekle").setValue(soruEkle);
        }
    }

    private MySaveButton buildSoruEkle(Soru soru) {
        MySaveButton secenekEkleButton=new MySaveButton();
        secenekEkleButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AuUI auUI = (AuUI) AuUI.getCurrent();
                SecenekPage secenekPage = new SecenekPage(soru);
                Window window = new Window();

                window.setCaption("SEÇENEK EKLE");
                window.setClosable(true);
                window.setWindowMode(WindowMode.NORMAL);
                window.setWidth(50, Unit.PERCENTAGE);
                window.setHeight(50, Unit.PERCENTAGE);
                window.setResizable(true);
                window.center();
                window.setContent(secenekPage);

                auUI.addWindow(window);

            }
        });

        return secenekEkleButton;
    }

    private MyEditButton buildUpdateButton(Soru soru)
    {
        MyEditButton guncelle = new MyEditButton();
        guncelle.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                AuUI auUI = (AuUI) AuUI.getCurrent();
                ContentComponent contentComponent = auUI.getContentComponent();

                SoruPage soruPage = new SoruPage(soru);
                contentComponent.addComponent(soruPage);
            }
        });
        return guncelle;
    }

    private Button buildDeleteButton( Soru soru)
    {
        Button silButton = new Button();
        silButton.setCaption("Sil");
        silButton.addStyleName(ValoTheme.BUTTON_DANGER);

        silButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SoruService soruService = new SoruService();
                soruService.deleteSoru(soru);
                fillTable();
            }
        });
        return silButton;
    }

}