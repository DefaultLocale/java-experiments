package mak.tryouts.vaadin7.samples;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.Arrays;
import java.util.Collection;

public class PopupViewSample extends Sample {

    public PopupViewSample() {
        super("Popup view");
    }

    @Override
    protected void init() {
        // Content for the PopupView
        VerticalLayout popupContent = new VerticalLayout();
        popupContent.addComponent(new TextField("Textfield"));
        popupContent.addComponent(new Button("Button"));
        addComponent(new PopupView("Pop it up", popupContent));

        // A pop-up view without minimalized representation
        final PopupView popup = new PopupView(null, new Table(null, generateContent()));
        // A component to open the view
        Button button = new Button("Show table", click -> popup.setPopupVisible(true));
        addComponents(button, popup);
    }

    private Container generateContent() {
        Collection<Person> people = Arrays.asList(
                new Person("Nicolaus Copernicus", 1543, true),
                new Person("Galileo Galilei", 1564, false),
                new Person("Johannes Kepler", 1571, true));
        return new BeanItemContainer<>(Person.class, people);
    }

}
