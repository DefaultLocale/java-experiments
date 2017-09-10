package kz.defaultlocale.vaadin8.samples;

import com.vaadin.data.Binder;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValidationException;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FormSample extends Sample {

    private Binder<FormBean> binder;
    private final Button submitButton = new Button("Submit", this::save);

    public FormSample() {
        super("Form");
    }

    @Override
    protected void init() {
        FormLayout form = new FormLayout();
        binder = new Binder<>();

        TextField nameField = new TextField("Name");
        binder.forField(nameField).asRequired("Name should be specifid").bind(FormBean::getName, FormBean::setName);

        TextField numberField = new TextField("Number");
        binder.forField(numberField)
                .withValidator(text -> text.length() < 5, "Length should be less than 5 characters")
                .withConverter(new StringToIntegerConverter("Wrong number"))
                .bind(FormBean::getNumber, FormBean::setNumber);

        DateField dateField = new DateField("Date");
        binder.forField(dateField)
                .withConverter(new LocalDateTimeToDateConverter())
                .bind(FormBean::getDate, FormBean::setDate);

        form.addComponents(nameField,
                numberField,
                dateField,
                submitButton);
        addComponent(form);

        FormBean bean = new FormBean("MAK", new Date(), 2);
        binder.readBean(bean);
    }

    private void save(Button.ClickEvent event) {
        try {
            FormBean result = new FormBean("", new Date(), 0);
            binder.writeBean(result);
            System.out.println(result.getName());
        } catch (ValidationException e) {
            Notification.show("Validation error count: " + e.getValidationErrors().size());
        }
    }

    private static class LocalDateTimeToDateConverter implements Converter<LocalDate, Date> {

        private static final long serialVersionUID = 1L;

        @Override
        public Result<Date> convertToModel(LocalDate value, ValueContext context) {
            return Result.ok(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        @Override
        public LocalDate convertToPresentation(Date value, ValueContext context) {
            return Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        }

    }

}
