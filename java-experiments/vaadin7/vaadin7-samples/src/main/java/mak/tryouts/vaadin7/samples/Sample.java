package mak.tryouts.vaadin7.samples;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class Sample extends VerticalLayout implements View {

    private final String title;

    Sample(String title) {
        this.title = title;
    }

    @Override
    public void attach() {
        addComponent(new Label("<h2>" + title + "</h2>", ContentMode.HTML));
        init();
    }

    protected abstract void init();

    public String getTitle() {
        return title;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
