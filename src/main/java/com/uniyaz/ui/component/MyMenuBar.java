package com.uniyaz.ui.component;

import com.uniyaz.ui.AuUI;
import com.uniyaz.ui.page.anketHazırlama.KullaniciPage;
import com.uniyaz.ui.page.anketHazırlama.PanelPage;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;


public class MyMenuBar extends MenuBar {

    private ContentComponent contentComponent;

    public MyMenuBar() {
        setSizeFull();
        setSizeUndefined();
        addStyleName("syMenuBar");

        AuUI auUI = (AuUI) UI.getCurrent();
        contentComponent = auUI.getContentComponent();

        buildAnketIslemleriMenuItem();
        buildKullaniciIslemleriMenuItem();
    }

    private void buildAnketIslemleriMenuItem() {
        MenuItem anketIslemleri = addItem("Anket İşlemleri", null);
        anketIslemleri.addItem("Anket Ekle", FontAwesome.PLUS, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {

                PanelPage panelPage=new PanelPage();
                contentComponent.addComponent(panelPage);
            }
        });

    }

    private void buildKullaniciIslemleriMenuItem() {
        MenuItem kullaniciIslemleriMenuItem = addItem("Kullanıcı İşlemleri", null);
        kullaniciIslemleriMenuItem.addItem("Anket Çöz", FontAwesome.MAGIC, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {

                KullaniciPage kullaniciPage=new KullaniciPage();
                contentComponent.addComponent(kullaniciPage);
            }
        });

        kullaniciIslemleriMenuItem.addItem("Çözdüklerimi Listele", FontAwesome.LIST, new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {

            }
        });
    }


}
