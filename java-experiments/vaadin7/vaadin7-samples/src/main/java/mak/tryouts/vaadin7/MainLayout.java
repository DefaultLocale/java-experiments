package mak.tryouts.vaadin7;

import mak.tryouts.vaadin7.samples.ComboBoxSample;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import mak.tryouts.vaadin7.samples.BrowserFrameSample;
import mak.tryouts.vaadin7.samples.CKEditorSample;
import mak.tryouts.vaadin7.samples.CalendarSample;
import mak.tryouts.vaadin7.samples.DragAndDropSample;
import mak.tryouts.vaadin7.samples.FocusSample;
import mak.tryouts.vaadin7.samples.FormSample;
import mak.tryouts.vaadin7.samples.GridLayoutSample;
import mak.tryouts.vaadin7.samples.GridSample;
import mak.tryouts.vaadin7.samples.LinkInsideALabelSample;
import mak.tryouts.vaadin7.samples.JavascriptSample;
import mak.tryouts.vaadin7.samples.MenuBarSample;
import mak.tryouts.vaadin7.samples.PageSample;
import mak.tryouts.vaadin7.samples.PopupViewSample;
import mak.tryouts.vaadin7.samples.PrintSample;
import mak.tryouts.vaadin7.samples.PushSample;
import mak.tryouts.vaadin7.samples.RichTextAreaSample;
import mak.tryouts.vaadin7.samples.Sample;
import mak.tryouts.vaadin7.samples.SessionSample;
import mak.tryouts.vaadin7.samples.StartPageSample;
import mak.tryouts.vaadin7.samples.TreeTableSample;

class MainLayout extends VerticalLayout {

    private final List<Sample> samples = Arrays.asList(new StartPageSample(),
            new FormSample(),
            new FocusSample(),
            new TreeTableSample(),
            new GridSample(),
            new MenuBarSample(),
            new CalendarSample(),
            new BrowserFrameSample(),
            new GridLayoutSample(),
            new PrintSample(),
            new DragAndDropSample(),
            new JavascriptSample(),
            new RichTextAreaSample(),
            new CKEditorSample(),
            new PageSample(),
            new PopupViewSample(),
            new SessionSample(),
            new PushSample(),
            new LinkInsideALabelSample(),
            new ComboBoxSample()
    );

    private final VerticalLayout sampleLayout = new VerticalLayout();
    private final Navigator navigator;

    MainLayout(MainUI ui) {
        navigator = new Navigator(ui, sampleLayout);
        VaadinSession session = ui.getSession();
        session.setAttribute("init-date", new Date());
        init();
    }

    private void init() {
        initNavigations();
        addComponent(new Label("<h1>Vaadin 7 Sample</h1>", ContentMode.HTML));
        addComponent(buildSplitPanel());
    }

    private void initNavigations() {
        navigator.addView("", new StartPageSample());
        for (Sample sample : samples) {
            navigator.addView(sample.getTitle(), sample);
        }
    }

    private HorizontalSplitPanel buildSplitPanel() {
        HorizontalSplitPanel panel = new HorizontalSplitPanel();
        panel.setFirstComponent(buildSamplesTree());
        panel.setSecondComponent(sampleLayout);
        panel.setSplitPosition(20);
        return panel;
    }

    private Tree buildSamplesTree() {
        Tree samplesTree = new Tree();
        BeanItemContainer<Sample> samplesContainer = new BeanItemContainer<>(Sample.class, samples);
        samplesTree.setContainerDataSource(samplesContainer);
        samplesTree.setItemCaptionPropertyId("title");
        samplesTree.addValueChangeListener(this::showSample);
        samplesTree.setValue(samples.get(0));
        return samplesTree;
    }

    private void showSample(Property.ValueChangeEvent event) {
        Sample sample = (Sample) event.getProperty().getValue();
        navigator.navigateTo(sample.getTitle());
    }

}
