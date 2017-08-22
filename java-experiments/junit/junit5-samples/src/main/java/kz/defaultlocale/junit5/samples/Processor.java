package kz.defaultlocale.junit5.samples;

/**
 * Sample class to demonstrate nested unit tests
 * @author Aidar.Myrzahanov
 */
public class Processor {

    private Integer result = null;

    public void process(int n) {
        if (result != null) {
            throw new IllegalStateException("Already processed. Use another processor");
        }
        result = 0;
        for (int i = 0; i <= n; i++) {
            result += i;
        }
    }

    public Integer getResult() {
        return result;
    }
}
