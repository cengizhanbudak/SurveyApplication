package com.uniyaz.ui.page.anketHazırlama;

import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.domain.EnumSecenekTuru;
import com.uniyaz.core.domain.Soru;
import com.uniyaz.core.service.SoruService;
import com.uniyaz.ui.component.SecenekTuruCombobox;
import com.uniyaz.ui.component.MySaveButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

public class SoruPage extends VerticalLayout {

    @PropertyId("id")
    private TextField id;

    @PropertyId("soruyaz")
    private TextField soruyaz;

    @PropertyId("secenek")
    private SecenekTuruCombobox secenek;

    @PropertyId("cevap")
    private TextField cevap;

    private FormLayout mainLayout;
    private BeanItem<Soru> soruBeanItem;
    private FieldGroup binder;
    private MySaveButton mySaveButton;
    private Anket anket;


    public SoruPage(Anket anket) {
        this.anket = anket;
        Soru soru = new Soru();
        soru.setAnket(anket);

        soruBeanItem = new BeanItem<Soru>(soru);
        binder = new FieldGroup(soruBeanItem);

        setSizeFull();
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        binder.bindMemberFields(this);
        id.setEnabled(false);

    }

    public SoruPage(Soru soru){

        soruBeanItem = new BeanItem<Soru>(soru);
        binder = new FieldGroup(soruBeanItem);

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

        soruyaz = new TextField();
        soruyaz.setCaption("Soruyaz");
        soruyaz.setNullRepresentation("");
        mainLayout.addComponent(soruyaz);

        secenek = new SecenekTuruCombobox();
        secenek.setCaption("SecenekTürü");
        mainLayout.addComponent(secenek);

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

                    Soru soru = soruBeanItem.getBean();
                    SoruService soruService = new SoruService();
                    soruService.saveSoru(soru);



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
