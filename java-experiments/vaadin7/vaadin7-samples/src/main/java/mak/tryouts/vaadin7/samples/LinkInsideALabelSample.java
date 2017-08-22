package mak.tryouts.vaadin7.samples;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class LinkInsideALabelSample extends Sample {

    public LinkInsideALabelSample() {
        super("Hyperlink inside a label");
    }

    @Override
    protected void init() {
        addComponent(new Label("Some test <a href='http://google.com'>Link</a>", ContentMode.HTML));
    }

}
