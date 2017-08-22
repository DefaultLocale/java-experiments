package mak.tryouts.vaadin7.samples;

import org.vaadin.openesignforms.ckeditor.CKEditorTextField;

public class CKEditorSample extends Sample{

    public CKEditorSample() {
        super("CKEditor for Vaadin");
    }

    @Override
    protected void init() {
        CKEditorTextField textField = new CKEditorTextField();
        addComponent(textField);
    }

}
