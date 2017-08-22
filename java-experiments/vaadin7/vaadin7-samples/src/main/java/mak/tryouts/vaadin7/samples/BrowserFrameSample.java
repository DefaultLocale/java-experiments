package mak.tryouts.vaadin7.samples;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;

public class BrowserFrameSample extends Sample {

    public BrowserFrameSample() {
        super("Browser Frame");
    }

    @Override
    protected void init() {
        BrowserFrame browser = new BrowserFrame("Browser",
                new ExternalResource("http://demo.vaadin.com/sampler/"));
        browser.setWidth("600px");
        browser.setHeight("400px");
        addComponent(browser);
    }

}
