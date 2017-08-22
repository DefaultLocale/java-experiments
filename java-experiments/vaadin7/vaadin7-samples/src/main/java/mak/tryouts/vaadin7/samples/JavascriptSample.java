package mak.tryouts.vaadin7.samples;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import elemental.json.JsonArray;

public class JavascriptSample extends Sample {

    public JavascriptSample() {
        super("JavaScript");
    }

    @Override
    protected void init() {
        Button showAlertButton = new Button("Show alert", (Button.ClickEvent event) -> {
            JavaScript.getCurrent().execute("alert('Hello')");
        });
        addComponent(showAlertButton);

        JavaScript.getCurrent().addFunction("com.example.foo.myfunc", (JsonArray arguments) -> {
            Notification.show("Received call");
        });
        Link link = new Link("Send Message", new ExternalResource(
                "javascript:com.example.foo.myfunc()"));
        addComponent(link);
    }

}
