package com.uniyaz.ui.page.anketHazırlama;


import com.uniyaz.core.domain.Kullanici;
import com.uniyaz.core.service.KullaniciService;
import com.uniyaz.ui.AuUI;
import com.uniyaz.ui.component.ContentComponent;
import com.uniyaz.ui.component.MySaveButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;


public class KullaniciPage extends VerticalLayout
{
    @PropertyId("id")
    private TextField id;

    @PropertyId("mail")
    private TextField mail;


    private FormLayout mainLayout;

    private BeanItem<Kullanici> kullaniciBeanItem;
    private FieldGroup binder;
    private MySaveButton mySaveButton;


    public KullaniciPage()
    {
        this(new Kullanici());
    }

    public KullaniciPage(Kullanici kullanici) {
        setSizeFull();
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        kullaniciBeanItem = new BeanItem<Kullanici>(kullanici);
        binder = new FieldGroup(kullaniciBeanItem);
        binder.bindMemberFields(this);
    }

    public void buildMainLayout() {
        mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();

        id = new TextField();
        id.setCaption("ID");
        id.setEnabled(false);
        mainLayout.addComponent(id);

        mail = new TextField();
        mail.setCaption("Mail hesabınızla anket çözebilirsiniz");
        mainLayout.addComponent(mail);

        mySaveButton = new MySaveButton();
        mySaveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();

                    Kullanici kullanici = kullaniciBeanItem.getBean();
                    KullaniciService kullaniciService = new KullaniciService();
                    kullaniciService.saveKullanici(kullanici);


                    AnketCevapPage anketCevapPage = new AnketCevapPage();
                    Window window = new Window();
                    window.setCaption("Cevapla");
                    window.setClosable(true);
                    window.setWindowMode(WindowMode.NORMAL);
                    window.setWidth(50, Unit.PERCENTAGE);
                    window.setHeight(50, Unit.PERCENTAGE);
                    window.setResizable(true);
                    window.center();
                    window.setContent(anketCevapPage);


                    AuUI auUI = (AuUI) AuUI.getCurrent();
                    auUI.addWindow(window);



                } catch (FieldGroup.CommitException e) {
                    Notification.show("Geçersiz, lütfen geçerli değerler giriniz", Notification.Type.ERROR_MESSAGE);
                } catch (Exception e) {
                    Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        mainLayout.addComponent(mySaveButton);
    }
}