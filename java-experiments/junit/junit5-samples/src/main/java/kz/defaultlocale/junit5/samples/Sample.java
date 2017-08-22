package kz.defaultlocale.junit5.samples;

public class Sample {

    private final String name;

    public Sample() {
        this(null);
    }

    public Sample(String name) {
        this.name = name;
    }

    public int add(int first, int second) {
        return first + second;
    }

    public String getName() {
        return name;
    }

}
