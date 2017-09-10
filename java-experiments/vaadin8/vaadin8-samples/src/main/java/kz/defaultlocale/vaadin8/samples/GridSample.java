package kz.defaultlocale.vaadin8.samples;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ProgressBarRenderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Grid sample
 */
public class GridSample extends Sample {

    public GridSample() {
        super("Grid");
    }

    @Override
    protected void init() {
        Grid<Person> grid = new Grid<>("Grid");
        grid.setItems(Person.createPersons());
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addColumn(Person::getName).setId("name").setCaption("Name");
        grid.addColumn(Person::getBorn).setId("born").setCaption("Born");
        Random random = new Random();
        grid.addColumn(p-> random.nextDouble(), new ProgressBarRenderer()).setCaption("Random value");
        grid.addColumn(p -> "Delete", new ButtonRenderer<>(e -> {
            DataProvider<Person, ?> dataProvider = grid.getDataProvider();
            if (!(dataProvider instanceof ListDataProvider)) return;
            ListDataProvider<Person> listProvider = (ListDataProvider<Person>) dataProvider;
            Collection<Person> items = new ArrayList<>(listProvider.getItems());
            items.remove(e.getItem());
            grid.setItems(items);
        })).setCaption("Delete row");
        grid.setWidth("100%");
        addComponent(grid);
    }

}
