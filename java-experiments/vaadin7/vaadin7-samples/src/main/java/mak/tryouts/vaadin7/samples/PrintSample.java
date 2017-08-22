package mak.tryouts.vaadin7.samples;

import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class PrintSample extends Sample {

    public PrintSample() {
        super("Printing");
    }

    @Override
    protected void init() {
        Button printButton = new Button("Print This Page");
        printButton.addClickListener((ClickEvent event) -> {
            // Print the current page
            JavaScript.getCurrent().execute("print();");
        });
        addComponent(printButton);

        BrowserWindowOpener opener = new BrowserWindowOpener(PrintUI.class);
        opener.setFeatures("height=200,width=400,resizable");
        // A button to open the printer-friendly page.
        Button printInNewWindowButton = new Button("Version for printing");
        opener.extend(printInNewWindowButton);
        addComponent(printInNewWindowButton);
        
        setSpacing(true);
    }

    public static class PrintUI extends UI {

        @Override
        protected void init(VaadinRequest request) {
            // Have some content to print
            setContent(new Label(
                    "<h1>Here's some dynamic content</h1>\n"
                    + "<p>This is to be printed.</p>",
                    ContentMode.HTML));
            // Print automatically when the window opens
            JavaScript.getCurrent().execute("setTimeout(function() {"
                    + " print(); self.close();}, 0);");
        }
    }

}
