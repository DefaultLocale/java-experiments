package kz.defaultlocale.junit5.samples;

public class Addition implements Operation<Integer> {

    @Override
    public Integer run(Integer first, Integer second) {
        if(first==null) {
            throw new IllegalArgumentException("first");
        }
        if(second==null) {
            throw new IllegalArgumentException("second");
        }
        return first + second;
    }

}
