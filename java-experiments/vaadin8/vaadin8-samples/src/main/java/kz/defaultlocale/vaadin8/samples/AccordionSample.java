package kz.defaultlocale.vaadin8.samples;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Sample for accordion UI component
 */
public class AccordionSample extends Sample {

    public AccordionSample() {
        super("Accordion");
    }

    @Override
    protected void init() {
        Accordion accordion = new Accordion();

        for (int i = 0; i < 10; i++) {
            String caption = "Accordion tab " + (i + 1);
            VerticalLayout tab = new VerticalLayout();
            tab.addComponent(new Label(caption));
            accordion.addTab(tab, caption);
        }
        
        addComponent(accordion);
    }

}
