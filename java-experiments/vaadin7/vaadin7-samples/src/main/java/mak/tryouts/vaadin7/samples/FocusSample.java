package mak.tryouts.vaadin7.samples;

import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class FocusSample extends Sample {

    public FocusSample() {
        super("Focus");
    }

    @Override
    protected void init() {
        addTextField("First field");
        addTextField("Second field");
        addComponent(new TextArea("Text area"));
    }

    private void addTextField(String name) {
        TextField textField = new TextField(name);
        textField.addStyleName("focus-highlight");
        addComponent(textField);
    }

}
