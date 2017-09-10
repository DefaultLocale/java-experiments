package kz.defaultlocale.vaadin8.samples;

import java.util.Date;

public class FormBean {

    private String name;
    private Date date;
    private int number;

    public FormBean(String name, Date date, int number) {
        this.name = name;
        this.date = date;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    

}
