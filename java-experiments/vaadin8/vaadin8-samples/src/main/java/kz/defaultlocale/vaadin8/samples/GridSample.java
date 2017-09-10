package kz.defaultlocale.vaadin8.samples;

import com.vaadin.ui.Grid;

/**
 * Grid sample
 */
public class GridSample extends Sample{

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
        addComponent(grid);
    }

}
