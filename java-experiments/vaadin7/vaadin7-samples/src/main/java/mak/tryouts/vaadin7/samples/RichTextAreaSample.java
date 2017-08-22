package mak.tryouts.vaadin7.samples;

import com.vaadin.ui.RichTextArea;

public class RichTextAreaSample extends Sample{

    public RichTextAreaSample() {
        super("Rich text area");
    }

    @Override
    protected void init() {
        RichTextArea area = new RichTextArea("Rich text area");
        area.setWidth("100%");
        addComponent(area);
    }

}
