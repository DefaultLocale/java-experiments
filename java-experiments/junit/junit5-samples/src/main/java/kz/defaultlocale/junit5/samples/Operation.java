package kz.defaultlocale.junit5.samples;

/**
 * Sample class to test inheritance demo
 *
 * @author Aidar.Myrzahanov
 */
public interface Operation<T> {

    T run(T first, T second);
}
