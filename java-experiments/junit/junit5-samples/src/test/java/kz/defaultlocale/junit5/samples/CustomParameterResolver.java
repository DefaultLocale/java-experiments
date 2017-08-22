package kz.defaultlocale.junit5.samples;

import java.lang.reflect.Parameter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

class CustomParameterResolver implements ParameterResolver {

    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return Sample.class.equals(parameter.getType());
    }

    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new Sample("injection");
    }

}
