package mak.tryouts.vaadin7.samples;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

public class MenuBarSample extends Sample {

    public MenuBarSample() {
        super("Menu Bar");
    }

    @Override
    protected void init() {
        // A feedback component
        final Label selection = new Label("-");
        addComponent(selection);
        // Define a common menu command for all the menu items.
        MenuBar.Command mycommand = (MenuItem selectedItem) -> {
            selection.setValue("Ordered a " + selectedItem.getText() + " from menu.");
        };

        MenuBar barmenu = new MenuBar();
        addComponent(barmenu);

        // A top-level menu item that opens a submenu
        MenuItem drinks = barmenu.addItem("Beverages", null, null);
        // Submenu item with a sub-submenu
        MenuItem hots = drinks.addItem("Hot", null, null);
        hots.addItem("Tea", new ThemeResource("icons/tea.png"), mycommand);
        hots.addItem("Coffee",
                new ThemeResource("icons/coffee.png"), mycommand);
        // Another submenu item with a sub-submenu
        MenuItem colds = drinks.addItem("Cold", null, null);
        colds.addItem("Milk", null, mycommand);
        colds.addItem("Weissbier", null, mycommand);
        // Another top-level item
        MenuItem snacks = barmenu.addItem("Snacks", null, null);
        snacks.addItem("Weisswurst", null, mycommand);
        snacks.addItem("Bratwurst", null, mycommand);
        snacks.addItem("Currywurst", null, mycommand);
        // Yet another top-level item
        MenuItem servs = barmenu.addItem("Services", null, null);
        servs.addItem("Car Service", null, mycommand);

    }

}
