package kz.defaultlocale.vaadin8.samples;

import com.vaadin.server.DefaultErrorHandler;
import static com.vaadin.server.DefaultErrorHandler.doDefault;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * Demo for default error handling mechanism
 *
 */
public class DefaultErrorHandlerSample extends Sample {

    public DefaultErrorHandlerSample() {
        super("Default Error Handler");
    }

    @Override
    protected void init() {
        final Button button = new Button("Throws exception", this::throwException);
        addComponent(button);
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                String cause = "<b>The click failed because:</b><br/>";
                for (Throwable t = event.getThrowable(); t != null;
                        t = t.getCause()) {
                    if (t.getCause() == null) // We're at final cause
                    {
                        cause += t.getClass().getName() + "<br/>";
                    }
                }
                addComponent(new Label(cause, ContentMode.HTML));
                doDefault(event);
            }
        });
    }

    private void throwException(Button.ClickEvent event) {
        Notification.show("Result: " + (1 / 0), Notification.Type.ERROR_MESSAGE);
    }
}
