package com.uniyaz.ui.page.anketHazırlama;

import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.domain.Soru;
import com.uniyaz.core.service.AnketService;
import com.uniyaz.ui.AuUI;
import com.uniyaz.ui.component.ContentComponent;
import com.uniyaz.ui.component.MyEditButton;
import com.uniyaz.ui.component.MySaveButton;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;

import java.util.List;


public class AnketListPage extends VerticalLayout {

    private VerticalLayout mainLayout;
    private Table table;
    private Container container;
    private Soru soru;

    public AnketListPage() {

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
        table.setColumnHeaders("ID", "ADI", "Güncelle", "Sil","SoruEkle");
    }

    private void buildContainer() {

        container = new IndexedContainer();
        container.addContainerProperty("id", Long.class, null);
        container.addContainerProperty("adi", String.class, null);
        container.addContainerProperty("guncelle", MyEditButton.class, null);
        container.addContainerProperty("sil", Button.class, null);
        container.addContainerProperty("soruekle", Button.class, null);


    }

    private void fillTable() {

        AnketService anketService = new AnketService();
        List<Anket> anketList = anketService.findAllHql();
        container.removeAllItems();
        for (Anket anket : anketList) {
            Item item = container.addItem(anket);
            item.getItemProperty("id").setValue(anket.getId());
            item.getItemProperty("adi").setValue(anket.getAdi());


            MyEditButton guncelle = buildGuncelleButton(anket);
            item.getItemProperty("guncelle").setValue(guncelle);

            Button silButton = buildSilButton(anket);
            item.getItemProperty("sil").setValue(silButton);

            MySaveButton soruEkle=buildSoruEkle(anket);
            item.getItemProperty("soruekle").setValue(soruEkle);

        }
    }

    private MySaveButton buildSoruEkle(Anket anket) {
        MySaveButton soruEkle=new MySaveButton();
        soruEkle.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                SoruPage soruPage = new SoruPage(anket);
                Window window = new Window();
                window.setCaption("SORU YAZ");
                window.setClosable(true);
                window.setWindowMode(WindowMode.NORMAL);
                window.setWidth(50, Unit.PERCENTAGE);
                window.setHeight(50, Unit.PERCENTAGE);
                window.setResizable(true);
                window.center();
                window.setContent(soruPage);

                AuUI auUI = (AuUI) AuUI.getCurrent();
                auUI.addWindow(window);

            }
        });

        return soruEkle;
    }


    private Button buildSilButton(Anket anket) {

        Button sil = new Button("sil");
        sil.setIcon(FontAwesome.TRASH);
        sil.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                AnketService anketService = new AnketService();
                anketService.delete(anket);
                fillTable();

            }
        });
        return sil;
    }

    private MyEditButton buildGuncelleButton(Anket anket) {
        MyEditButton guncelle = new MyEditButton();
        guncelle.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                AuUI auUI = (AuUI) AuUI.getCurrent();
                ContentComponent contentComponent = auUI.getContentComponent();

                AnketPage anketPage = new AnketPage(anket);
                contentComponent.addComponent(anketPage);


            }
        });
        return guncelle;
    }


}