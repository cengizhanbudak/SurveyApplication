package com.uniyaz.ui.page.anketHazırlama;

import com.uniyaz.core.domain.Secenek;
import com.uniyaz.core.domain.Soru;
import com.uniyaz.core.service.SecenekService;
import com.uniyaz.ui.component.MySaveButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

public class SecenekPage extends VerticalLayout {


    @PropertyId("id")
    private TextField id;

    @PropertyId("cevap")
    private TextField cevap;

    private FormLayout mainLayout;
    private BeanItem<Secenek> secenekBeanItem;
    private FieldGroup binder;
    private MySaveButton mySaveButton;
    private Soru soru;


    public SecenekPage(Soru soru) {
        this.soru = soru;
        Secenek secenek = new Secenek();

        secenek.setSoru(soru);

        secenekBeanItem = new BeanItem<Secenek>(secenek);
        binder = new FieldGroup(secenekBeanItem);

        setSizeFull();
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        binder.bindMemberFields(this);
        id.setEnabled(false);

    }

    public SecenekPage(Secenek secenek){
        secenekBeanItem = new BeanItem<Secenek>(secenek);
        binder = new FieldGroup(secenekBeanItem);

        setSizeFull();
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        binder.bindMemberFields(this);
        id.setEnabled(false);
    }

    private void buildMainLayout() {

        mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();

        id = new TextField();
        id.setCaption("ID");
        id.setNullRepresentation("");
        mainLayout.addComponent(id);



        cevap = new TextField();
        cevap.setCaption("ekaçıklama");
        cevap.setNullRepresentation("");
        mainLayout.addComponent(cevap);

        mySaveButton = new MySaveButton();
        mySaveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();

                    Secenek secenek = secenekBeanItem.getBean();
                    SecenekService secenekService = new SecenekService();
                    secenekService.saveSoru(secenek);



                } catch (FieldGroup.CommitException e) {
                    Notification.show("Alanlar nesne ile uyumlu değil", Notification.Type.ERROR_MESSAGE);
                } catch (Exception e) {
                    Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        mainLayout.addComponent(mySaveButton);
    }
}



