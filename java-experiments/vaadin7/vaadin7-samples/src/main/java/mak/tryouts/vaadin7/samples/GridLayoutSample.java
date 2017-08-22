package mak.tryouts.vaadin7.samples;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;

public class GridLayoutSample extends Sample {

    public GridLayoutSample() {
        super("Grid Layout");
    }

    @Override
    protected void init() {
        // Create a 4 by 4 grid layout.
        GridLayout grid = new GridLayout(4, 4);
        grid.addStyleName("example-gridlayout");
        // Fill out the first row using the cursor.
        grid.addComponent(new Button("R/C 1"));
        for (int i = 0; i < 3; i++) {
            grid.addComponent(new Button("Col " + (grid.getCursorX() + 1)));
        }
        // Fill out the first column using coordinates.
        for (int i = 1; i < 4; i++) {
            grid.addComponent(new Button("Row " + i), 0, i);
        }
        // Add some components of various shapes.
        grid.addComponent(new Button("3x1 button"), 1, 1, 3, 1);
        grid.addComponent(new Label("1x2 cell"), 1, 2, 1, 3);
        InlineDateField date = new InlineDateField("A 2x2 date field");
        date.setResolution(Resolution.DAY);
        grid.addComponent(date, 2, 2, 3, 3);
        addComponent(grid);
    }

}
