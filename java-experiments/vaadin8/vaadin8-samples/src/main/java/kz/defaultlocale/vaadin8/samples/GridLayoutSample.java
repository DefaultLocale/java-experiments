package kz.defaultlocale.vaadin8.samples;

import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;

/**
 * Grid layout sample
 */
public class GridLayoutSample extends Sample {

    public GridLayoutSample() {
        super("Grid layout");
    }

    @Override
    protected void init() {
        GridLayout grid = new GridLayout(4, 4);
        grid.addComponent(createButton("R/C 1"));
        for (int i = 0; i < 3; i++) {
            grid.addComponent(createButton("Col " + (grid.getCursorX() + 1)));
        }
        for (int i = 1; i < 4; i++) {
            grid.addComponent(createButton("Row " + i), 0, i);
        }
        grid.addComponent(createButton("3x1 button"), 1, 1, 3, 1);
        grid.addComponent(new Label("1x2 cell"), 1, 2, 1, 3);
        InlineDateField date
                = new InlineDateField("A 2x2 date field");
        date.setResolution(DateResolution.DAY);
        grid.addComponent(date, 2, 2, 3, 3);
        addComponent(grid);
    }



    private Button createButton(String buttonCaption) {
        Button button = new Button(buttonCaption);
        button.setSizeFull();
        return button;
    }

}
