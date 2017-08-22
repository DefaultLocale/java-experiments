package kz.defaultlocale.junit5.samples;

public class Concatenation implements Operation<String> {

    @Override
    public String run(String first, String second) {
        return first + second;
    }

}
