package kz.defaultlocale.vaadin8.samples;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;

public class StartPageSample extends Sample {

    public StartPageSample() {
        super("Start page");
    }

    @Override
    protected void init() {
        addComponent(new Label("<h2>Start page for vaadin samples</h2>", ContentMode.HTML));
        addComponent(new Label("<h3>Choose a sample from the tree on the left</h3>", ContentMode.HTML));
    }

}
