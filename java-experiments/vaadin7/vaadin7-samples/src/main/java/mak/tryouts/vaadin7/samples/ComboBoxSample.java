/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mak.tryouts.vaadin7.samples;

import com.vaadin.ui.ComboBox;

/**
 *
 * @author MAK
 */
public class ComboBoxSample extends Sample {

    public ComboBoxSample() {
        super("ComboBox search button");
    }

    @Override
    protected void init() {
        ComboBox comboBox = new ComboBox("Combobox");
        comboBox.setInputPrompt("Enter search text");
        comboBox.addItem("Item1");
        comboBox.addItem("Item2");
        comboBox.addItem("Item3");
        comboBox.addStyleName("searchbox");
        comboBox.setNullSelectionAllowed(true);
        comboBox.setWidth("400px");
        addComponent(comboBox);
    }

}
