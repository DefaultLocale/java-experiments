package mak.tryouts.vaadin7.samples;

import java.util.Objects;

public class Person {

    private final String name;
    private final int born;
    private final boolean somethingBoolean;

    Person(String name, int year, boolean somethingBoolean) {
        this.name = name;
        this.born = year;
        this.somethingBoolean = somethingBoolean;
    }

    public String getName() {
        return name;
    }

    public int getBorn() {
        return born;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + this.born;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.born != other.born) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    /**
     * @return the somethingBoolean
     */
    public boolean isSomethingBoolean() {
        return somethingBoolean;
    }

    
}
