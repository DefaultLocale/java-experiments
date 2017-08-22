package mak.tryouts.vaadin7.samples;

import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class PageSample extends Sample {

    public PageSample() {
        super("Page class");
    }

    @Override
    protected void init() {
        Page page = getUI().getPage();
        addParameter("Browser window height", page.getBrowserWindowHeight());
        addParameter("Browser window width", page.getBrowserWindowHeight());
        addParameter("Location", page.getLocation());
        addParameter("User agent", page.getWebBrowser().getBrowserApplication());
        addParameter("Window name", page.getWindowName());
    }

    private void addParameter(String name, Object value) {
        addComponent(new Label("<b>" + name + ":</b> " + value, ContentMode.HTML));
    }

}
