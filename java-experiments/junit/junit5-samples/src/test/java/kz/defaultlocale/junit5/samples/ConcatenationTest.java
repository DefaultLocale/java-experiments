package kz.defaultlocale.junit5.samples;

public class ConcatenationTest implements OperationContract<String> {

    @Override
    public Operation<String> createOperation() {
        return new Concatenation();
    }

    @Override
    public String createValue() {
        return "";
    }

}
