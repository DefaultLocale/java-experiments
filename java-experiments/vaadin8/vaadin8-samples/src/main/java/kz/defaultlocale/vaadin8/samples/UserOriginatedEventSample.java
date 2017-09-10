package kz.defaultlocale.vaadin8.samples;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

/**
 * This sample shows how to differentiate between user originated and
 * programmatical value changes
 */
public class UserOriginatedEventSample extends Sample {

    public UserOriginatedEventSample() {
        super("User originated event");
    }

    @Override
    protected void init() {
        final Label displayLabel = new Label();
        addComponent(displayLabel);
        final TextField textField = new TextField("Test", "Initial value", e -> {
            String origin = e.isUserOriginated() ? "User" : "Application";
            displayLabel.setValue(origin + " changed value from \"" + e.getOldValue() + "\" to \"" + e.getValue() + "\"");
        });
        addComponent(textField);
        final Button button = new Button("Reset value", e-> {
            textField.setValue("Initial value");
        });
        addComponent(button);
    }

}
