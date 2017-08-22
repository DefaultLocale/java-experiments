package mak.tryouts.vaadin7.samples;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.TextField;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.NumberRenderer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

public class GridSample extends Sample {

    public GridSample() {
        super("Grid");
    }

    @Override
    protected void init() {
        // Have some data
        Collection<Person> people = Arrays.asList(
                new Person("Nicolaus Copernicus", 1543, true),
                new Person("Galileo Galilei", 1564, false),
                new Person("Johannes Kepler", 1571, true));
        // Have a container of some type to contain the data
        BeanItemContainer<Person> container = new BeanItemContainer<>(Person.class, people);
        // Create a grid bound to the container
        Grid grid = new Grid(container);
        grid.setColumnOrder("name", "born", "somethingBoolean");
        grid.setWidth("100%");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        Grid.Column bornColumn = grid.getColumn("born");
        bornColumn.setRenderer(new NumberRenderer("born in %d AD"));
        Grid.Column truth = grid.getColumn("somethingBoolean");
        truth.setRenderer(new HtmlRenderer(),
                new StringToBooleanConverter(
                        FontAwesome.CHECK_CIRCLE_O.getHtml(),
                        FontAwesome.CIRCLE_O.getHtml()));
        Grid.Column nameColumn = grid.getColumn("name");
        nameColumn.setRenderer(new HtmlRenderer(),
                new Converter<String, String>() {
            @Override
            public String convertToModel(String value,
                    Class<? extends String> targetType, Locale locale)
                    throws Converter.ConversionException {
                return "not implemented";
            }

            @Override
            public String convertToPresentation(String value,
                    Class<? extends String> targetType, Locale locale)
                    throws Converter.ConversionException {
                return "<a href='http://en.wikipedia.org/wiki/"
                        + value + "' target='_blank'>" + value + "</a>";
            }

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
        Grid.FooterRow footer = grid.prependFooterRow();
        Grid.FooterCell totalCell = footer.getCell("name");
        totalCell.setText("Total: 3");

        // Create a header row to hold column filters
        HeaderRow filterRow = grid.appendHeaderRow();
        // Set up a filter for all columns
        for (Object pid : grid.getContainerDataSource()
                .getContainerPropertyIds()) {
            HeaderCell cell = filterRow.getCell(pid);
            // Have an input field to use for filter
            TextField filterField = new TextField();
            filterField.setColumns(8);
            // Update filter When the filter input is changed
            filterField.addTextChangeListener(change -> {
                // Can't modify filters so need to replace
                container.removeContainerFilters(pid);
                // (Re)create the filter if necessary
                if (!change.getText().isEmpty()) {
                    container.addContainerFilter(
                            new SimpleStringFilter(pid,
                                    change.getText(), true, false));
                }
            });
            cell.setComponent(filterField);
        }

        addComponent(grid);
    }

}
