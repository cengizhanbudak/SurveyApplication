package com.uniyaz.ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;


@WebServlet(urlPatterns = "/*", name = "AuUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = AuUI.class, productionMode = false)
public class AuUIServlet extends VaadinServlet {

}
