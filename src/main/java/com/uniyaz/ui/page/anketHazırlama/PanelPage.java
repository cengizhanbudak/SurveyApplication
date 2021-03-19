package com.uniyaz.ui.page.anketHazırlama;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class PanelPage extends TabSheet {

    private VerticalLayout anketListeLayout;
    private VerticalLayout soruEkleLayout;
    private VerticalLayout secenekEkleLayout;

        public PanelPage() {

            setSizeFull();
            addStyleName(ValoTheme.TABSHEET_FRAMED);
            addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

            final FormLayout formLayout = new FormLayout();
            formLayout.setWidth(100.0f, Unit.PERCENTAGE);


            buildAnketListele();
            buildSoruEkle();
            buildSecenekEkle();


        }

        private void buildSecenekEkle() {
            secenekEkleLayout=new VerticalLayout();
            secenekEkleLayout.setMargin(true);
            addTab(secenekEkleLayout, "Seçenek işlemleri" );
            Label secenekLabel=new Label("Bu aşamaya henüz gelinmedi");
            secenekEkleLayout.addComponent(secenekLabel);

        }

        private void buildSoruEkle() {

            soruEkleLayout=new VerticalLayout();
            soruEkleLayout.setMargin(true);
            addTab(soruEkleLayout, "Soru İşlemleri" );

            SoruListPage soruListPage =new SoruListPage();
            soruEkleLayout.addComponent(soruListPage);
        }

        private void buildAnketListele() {
            anketListeLayout=new VerticalLayout();
            anketListeLayout.setMargin(true);
            addTab(anketListeLayout, "Anket İşlemleri" );
            AnketListPage anketListPage =new AnketListPage();
            AnketPage anketPage=new AnketPage();
            anketListeLayout.addComponent(anketListPage);
            anketListeLayout.addComponent(anketPage);
        }
    }


