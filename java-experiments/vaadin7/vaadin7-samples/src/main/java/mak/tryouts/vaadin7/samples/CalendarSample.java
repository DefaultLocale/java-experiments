package mak.tryouts.vaadin7.samples;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventMoveHandler;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventResizeHandler;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarSample extends Sample {

    public CalendarSample() {
        super("Calendar");
    }

    @Override
    protected void init() {
        // Create the calendar
        Calendar calendar = new Calendar("Bound Calendar");
        // Use a container of built-in BasicEvents
        final BeanItemContainer<BasicEvent> container = new BeanItemContainer<>(BasicEvent.class);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(GregorianCalendar.WEEK_OF_MONTH, -1);
        for (int i = 0; i < 30; i++) {
            Date startTime = gregorianCalendar.getTime();
            gregorianCalendar.add(GregorianCalendar.HOUR, 1);
            Date finishTime = gregorianCalendar.getTime();
            BasicEvent event = new BasicEvent("The Event", "Regular Event", startTime, finishTime);
            container.addBean(event);
            gregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }

        // The container must be ordered by the start time. You
        // have to sort the BIC every time after you have added
        // or modified events.
        container.sort(new Object[]{"start"}, new boolean[]{true});
        calendar.setContainerDataSource(container, "caption",
                "description", "start", "end", "styleName");
        calendar.setWidth("100%");
        calendar.setHeight("1500px");

        calendar.setHandler((EventMoveHandler) null);
        calendar.setHandler((EventResizeHandler) null);
        addComponent(calendar);
    }

}
