package mak.tryouts.vaadin7.samples;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import java.util.Date;

public class FormSample extends Sample {

    private FieldGroup binder;
    private final Button submitButton = new Button("Submit", this::save);

    public FormSample() {
        super("Form");
    }

    @Override
    protected void init() {
        FormLayout form = new FormLayout();
        binder = new BeanFieldGroup<>(FormBean.class);
        FormBean bean = new FormBean("MAK", new Date(), 2);
        BeanItem<FormBean> item = new BeanItem<>(bean);
        binder.setItemDataSource(item);
        binder.setBuffered(true);
        for (Object propertyId : binder.getUnboundPropertyIds()) {
            Field<?> field = binder.buildAndBind(propertyId);
            if ("number".equals(propertyId)) {
                field.addValidator(new IntegerRangeValidator("Integer is not withing the range", 0, 10000));
            }
            form.addComponent(field);
        }
        form.addComponent(submitButton);
        addComponent(form);
    }

    private void save(Button.ClickEvent event) {
        try {
            binder.commit();
            Notification.show("Saved");
        } catch (FieldGroup.CommitException ce) {
            StringBuilder description = new StringBuilder();
            ce.getInvalidFields().values().stream().forEach((exception) -> {
                description.append(exception.getMessage()).append("\r\n");
            });
            Notification.show("Commit failed: " + description, Notification.Type.ERROR_MESSAGE);
            submitButton.setComponentError(new UserError(description.toString()));
        }
    }

}
