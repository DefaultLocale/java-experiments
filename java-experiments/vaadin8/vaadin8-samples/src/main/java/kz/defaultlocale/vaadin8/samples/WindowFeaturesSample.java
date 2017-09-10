package kz.defaultlocale.vaadin8.samples;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Browser windows feature configuration
 */
public class WindowFeaturesSample extends Sample {

    private final BrowserWindowOpener opener = new BrowserWindowOpener(CustomPopupWindow.class);

    public WindowFeaturesSample() {
        super("Browser window features");
    }

    @Override
    protected void init() {
        final Binder<WindowFeatures> binder = new Binder<>();
        binder.setBean(new WindowFeatures());

        CheckBox showStatusBarCheckBox = new CheckBox("Show status bar");
        binder.forField(showStatusBarCheckBox)
                .bind(WindowFeatures::isShowLocationBar, WindowFeatures::setShowLocationBar);
        CheckBox resizableCheckBox = new CheckBox("Resizable");
        binder.forField(resizableCheckBox)
                .bind(WindowFeatures::isResizable, WindowFeatures::setResizable);
        CheckBox showMenuBarCheckBox = new CheckBox("Show menu bar");
        binder.forField(showMenuBarCheckBox)
                .bind(WindowFeatures::isShowMenuBar, WindowFeatures::setShowMenuBar);
        CheckBox showLocationBarCheckBox = new CheckBox("Show location bar");
        binder.forField(showLocationBarCheckBox)
                .bind(WindowFeatures::isShowLocationBar, WindowFeatures::setShowLocationBar);
        CheckBox showToolBarCheckBox = new CheckBox("Show toolbar");
        binder.forField(showToolBarCheckBox)
                .bind(WindowFeatures::isShowToolBar, WindowFeatures::setShowToolBar);
        TextField widthField = new TextField("Width");
        binder.forField(widthField)
                .withConverter(new StringToIntegerConverter("Wrong number"))
                .bind(WindowFeatures::getWidth, WindowFeatures::setWidth);
        TextField heightField = new TextField("Height");
        binder.forField(heightField)
                .withConverter(new StringToIntegerConverter("Wrong number"))
                .bind(WindowFeatures::getHeight, WindowFeatures::setHeight);

        Button openWindowButton = new Button("Open window");
        opener.extend(openWindowButton);
        binder.addValueChangeListener(e -> {
            String featuresString = binder.getBean().toString();
            opener.setFeatures(featuresString);
            System.out.println("Set features: "+featuresString);
        });

        FormLayout layout = new FormLayout(
                resizableCheckBox,
                showToolBarCheckBox,
                showStatusBarCheckBox,
                showMenuBarCheckBox,
                showLocationBarCheckBox,
                widthField,
                heightField,
                openWindowButton
        );
        addComponent(layout);
    }

    private static class WindowFeatures {

        private boolean showStatusBar = true;
        private boolean resizable = true;
        private boolean showMenuBar = true;
        private boolean showLocationBar = true;
        private boolean showToolBar = true;
        private int height = 200;
        private int width = 200;

        public boolean isShowStatusBar() {
            return showStatusBar;
        }

        public void setShowStatusBar(boolean showStatusBar) {
            this.showStatusBar = showStatusBar;
        }

        public boolean isResizable() {
            return resizable;
        }

        public void setResizable(boolean resizable) {
            this.resizable = resizable;
        }

        public boolean isShowMenuBar() {
            return showMenuBar;
        }

        public void setShowMenuBar(boolean showMenuBar) {
            this.showMenuBar = showMenuBar;
        }

        public boolean isShowLocationBar() {
            return showLocationBar;
        }

        public void setShowLocationBar(boolean showLocationBar) {
            this.showLocationBar = showLocationBar;
        }

        public boolean isShowToolBar() {
            return showToolBar;
        }

        public void setShowToolBar(boolean showToolBar) {
            this.showToolBar = showToolBar;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public String toString() {
            String result = "height=" + height + ","
                    + "width=" + width + ","
                    + (resizable ? "resizable," : "")
                    + (showStatusBar ? "status," : "")
                    + (showMenuBar ? "menubar," : "")
                    + (showLocationBar ? "location," : "")
                    + (showToolBar ? "toolbar," : "");
            if(result.endsWith(",")) {
                result = result.substring(0, result.length()-1);
            }
            return result;
        }

    }

    @Theme("mytheme")
    public static class CustomPopupWindow extends UI {

        public CustomPopupWindow() {
        }

        @Override
        protected void init(VaadinRequest request) {
            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.addComponent(new Label("Popup window"));
            setContent(mainLayout);
        }
    }
}
