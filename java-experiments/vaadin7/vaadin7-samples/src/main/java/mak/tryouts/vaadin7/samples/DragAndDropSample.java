package mak.tryouts.vaadin7.samples;

import com.vaadin.data.Container;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.WrapperTransferable;
import com.vaadin.ui.Html5File;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TreeDragMode;
import com.vaadin.ui.Tree.TreeTargetDetails;
import java.util.Iterator;

public class DragAndDropSample extends Sample {

    public DragAndDropSample() {
        super("Drag & Drop");
    }

    @Override
    protected void init() {
        initTreeSample();
        initVariousSample();
    }

    private void initTreeSample() {
        Label headerLabel = new Label("<h3>Tree nodes</h3>", ContentMode.HTML);
        addComponent(headerLabel);
        final Tree tree = new Tree("Inventory");
        tree.setContainerDataSource(createTreeContent());
        addComponent(tree);
        // Expand all items
        for (Iterator<?> it = tree.rootItemIds().iterator(); it.hasNext();) {
            tree.expandItemsRecursively(it.next());
        }
        // Set the tree in drag source mode
        tree.setDragMode(TreeDragMode.NODE);
        // Allow the tree to receive drag drops and handle them
        tree.setDropHandler(new DropHandler() {
            @Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }

            @Override
            public void drop(DragAndDropEvent event) {
                // Wrapper for the object that is dragged
                Transferable t = event.getTransferable();
                // Make sure the drag source is the same tree
                if (t.getSourceComponent() != tree) {
                    return;
                }
                TreeTargetDetails target = (TreeTargetDetails) event.getTargetDetails();
                // Get ids of the dragged item and the target item
                Object sourceItemId = t.getData("itemId");
                Object targetItemId = target.getItemIdOver();
                // On which side of the target the item was dropped
                VerticalDropLocation location = target.getDropLocation();
                HierarchicalContainer container = (HierarchicalContainer) tree.getContainerDataSource();
                if (null == location) { // Drop right on an item -> make it a child
                    return;
                }
                switch (location) {
                    // Drop at the top of a subtree -> make it previous
                    case MIDDLE:
                        tree.setParent(sourceItemId, targetItemId);
                        break;
                    case TOP: {
                        Object parentId = container.getParent(targetItemId);
                        container.setParent(sourceItemId, parentId);
                        container.moveAfterSibling(sourceItemId, targetItemId);
                        container.moveAfterSibling(targetItemId, sourceItemId);
                        break;
                    } // Drop below another item -> make it next
                    case BOTTOM: {
                        Object parentId = container.getParent(targetItemId);
                        container.setParent(sourceItemId, parentId);
                        container.moveAfterSibling(sourceItemId, targetItemId);
                        break;
                    }
                    default:
                        break;
                }
            }
        });
    }

    private Container createTreeContent() {
        HierarchicalContainer container = new HierarchicalContainer();
        String[] items = new String[]{"first", "second", "third"};
        for (String item : items) {
            container.addItem(item);
        }
        return container;
    }

    private void initVariousSample() {
        Label headerLabel = new Label("<h3>Various drag & drop</h3>", ContentMode.HTML);
        addComponent(headerLabel);
        // A layout that allows moving its contained components 
        // by dragging and dropping them
        final AbsoluteLayout absLayout = new AbsoluteLayout();
        absLayout.setWidth("100%");
        absLayout.setHeight("400px");

        final Label dropLabel = new Label("Drop something here", ContentMode.HTML);
        absLayout.addComponent(dropLabel);
        // Wrap the layout to allow handling drops
        final DragAndDropWrapper dropWrapper = new DragAndDropWrapper(absLayout);
        // Handle moving components within the AbsoluteLayout
        dropWrapper.setDropHandler(new DropHandler() {
            @Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }

            @Override
            public void drop(DragAndDropEvent event) {
                StringBuilder dataInfo = new StringBuilder();
                dataInfo.append("<b>Flavors:</b>");
                Transferable transferable = event.getTransferable();
                transferable.getDataFlavors().stream().forEach((flavor) -> {
                    dataInfo.append(flavor).append("<br />");
                });

                if (transferable instanceof WrapperTransferable) {
                    dataInfo.append("<b>Files:</b>");
                    WrapperTransferable wrapperTransferable = (WrapperTransferable) transferable;
                    for (Html5File file : wrapperTransferable.getFiles()) {
                        dataInfo.append(file.getFileName()).append(": ").append(file.getFileSize());
                    }
                }
                dropLabel.setValue(dataInfo.toString());
            }
        });
        addComponent(dropWrapper);
    }

}
