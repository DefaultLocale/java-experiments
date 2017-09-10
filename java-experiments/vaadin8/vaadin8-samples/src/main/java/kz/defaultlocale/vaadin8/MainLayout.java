package kz.defaultlocale.vaadin8;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import kz.defaultlocale.vaadin8.samples.ImageResourceSample;
import kz.defaultlocale.vaadin8.samples.Sample;
import kz.defaultlocale.vaadin8.samples.StartPageSample;

class MainLayout extends VerticalLayout {

    private final List<Sample> samples = Arrays.asList(
            new StartPageSample(),
            new ImageResourceSample()
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
        samples.forEach((sample) -> {
            navigator.addView(sample.getTitle(), sample);
        });
    }

    private HorizontalSplitPanel buildSplitPanel() {
        HorizontalSplitPanel panel = new HorizontalSplitPanel();
        panel.setFirstComponent(buildSamplesTree());
        panel.setSecondComponent(sampleLayout);
        panel.setSplitPosition(20);
        return panel;
    }

    private Tree buildSamplesTree() {
        Tree<Sample> samplesTree = new Tree<>();
        samplesTree.setSelectionMode(Grid.SelectionMode.SINGLE);
        samplesTree.setItems(samples);
        samplesTree.setItemCaptionGenerator(s -> s.getTitle());
        samplesTree.addSelectionListener(this::showSample);
        samplesTree.select(samples.get(0));
        return samplesTree;
    }

    private void showSample(SelectionEvent<Sample> event) {
        Sample sample = event.getFirstSelectedItem().get();
        navigator.navigateTo(sample.getTitle());
    }

}
