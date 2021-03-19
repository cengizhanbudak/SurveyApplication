package com.uniyaz.ui.page.anketHazırlama;

import com.uniyaz.core.domain.Anket;

import com.uniyaz.core.service.AnketService;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

public class AnketCevapPage extends VerticalLayout {

    private VerticalLayout mainLayout;
    private Table table;
    private Container container;


    public AnketCevapPage() {

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
        table.setColumnHeaders("ID", "ADI", "Çöz");
    }

    private void buildContainer() {

        container = new IndexedContainer();
        container.addContainerProperty("id", Long.class, null);
        container.addContainerProperty("adi", String.class, null);
        container.addContainerProperty("Çöz", Button.class, null);

    }

    private void fillTable() {

        AnketService anketService = new AnketService();
        List<Anket> anketList = anketService.findAllHql();
        container.removeAllItems();

        for (Anket anket : anketList) {

            Item item = container.addItem(anket);
            item.getItemProperty("id").setValue(anket.getId());
            item.getItemProperty("adi").setValue(anket.getAdi());

            Button guncelle = buildCevaplaButton(anket);
            item.getItemProperty("Çöz").setValue(guncelle);

        }
    }


    private Button buildCevaplaButton(Anket anket) {
        Button cevaplaButton = new Button();
        cevaplaButton.setCaption("Başla");
        cevaplaButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        cevaplaButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {


                }
            });
            return cevaplaButton;
        }
    }



