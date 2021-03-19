package com.uniyaz.ui.component;

import com.uniyaz.core.domain.EnumSecenekTuru;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;

public class SecenekTuruCombobox extends ComboBox {

    public SecenekTuruCombobox() {
        fillCombobox();
    }

    private void fillCombobox() {

        for (EnumSecenekTuru secenekTuru : EnumSecenekTuru.values()) {
            Item item = addItem(secenekTuru);
        }
    }
}
