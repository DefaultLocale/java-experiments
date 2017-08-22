package mak.tryouts.vaadin7.samples;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

public class PushSample extends Sample {

    public PushSample() {
        super("Server push");
    }

    @Override
    protected void init() {
        Button startButton = new Button("Start thread", this::startThread);
        addComponent(startButton);
    }

    private void startThread(Button.ClickEvent e) {
        new Thread(() -> {
            getUI().access(() -> addComponent(new Label("Thread started")));
            for (int i = 0; i < 10; i++) {
                getUI().access(() -> {
                    addComponent(new Label("Message"));
                });
                sleep(1000);
            }
            getUI().access(() -> {
                addComponent(new Label("Thread stopped"));
            });
        }).start();

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

}
