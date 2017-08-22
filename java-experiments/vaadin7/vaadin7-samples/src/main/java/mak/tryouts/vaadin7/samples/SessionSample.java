package mak.tryouts.vaadin7.samples;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import java.util.Date;

public class SessionSample extends Sample{

    public SessionSample() {
        super("Session");
    }

    @Override
    protected void init() {
        Date initDate = (Date) getUI().getSession().getAttribute("init-date");
        addComponent(new Label("<b>Initialization time (retrieved from session): </b>"+initDate, ContentMode.HTML));
    }

}
