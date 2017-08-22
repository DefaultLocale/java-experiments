package mak.tryouts.vaadin7.samples;

import com.vaadin.ui.TreeTable;

public class TreeTableSample extends Sample {

    public TreeTableSample() {
        super("Tree table");
    }

    @Override
    protected void init() {
        TreeTable treeTable = new TreeTable("My TreeTable");
        treeTable.addContainerProperty("Name", String.class, null);
        treeTable.addContainerProperty("Number", Integer.class, null);
        // Create the tree nodes and set the hierarchy
        treeTable.addItem(new Object[]{"Menu", null}, 0);
        treeTable.addItem(new Object[]{"Beverages", null}, 1);
        treeTable.setParent(1, 0);
        treeTable.addItem(new Object[]{"Foods", null}, 2);
        treeTable.setParent(2, 0);
        treeTable.addItem(new Object[]{"Coffee", 23}, 3);
        treeTable.addItem(new Object[]{"Tea", 42}, 4);
        treeTable.setParent(3, 1);
        treeTable.setParent(4, 1);
        treeTable.addItem(new Object[]{"Bread", 13}, 5);
        treeTable.addItem(new Object[]{"Cake", 11}, 6);
        treeTable.setParent(5, 2);
        treeTable.setParent(6, 2);
        treeTable.setWidth(100, Unit.PERCENTAGE);
        addComponent(treeTable);
    }

}
