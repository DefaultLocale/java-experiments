package kz.defaultlocale.junit5.samples;

import static kz.defaultlocale.junit5.samples.Writing.write;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestExecutionCondition;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class CustomExtension implements TestExecutionCondition,
        TestInstancePostProcessor, ParameterResolver, BeforeAllCallback,
        BeforeEachCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback,
        AfterEachCallback, AfterAllCallback, TestExecutionExceptionHandler {

    @Override
    public ConditionEvaluationResult evaluate(TestExtensionContext context) {
        if (context.getTestMethod().get().getName().contains("extra")) {
            return ConditionEvaluationResult.disabled("no");
        }
        return ConditionEvaluationResult.enabled("yes");
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        write("Post processing");
        write("Display name: " + context.getDisplayName());
        write("Instance type: " + testInstance.getClass());
    }

    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return true;
    }

    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return "hello";
    }

    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        context.getStore().put("Start", System.currentTimeMillis());
        write("Before all callback");
    }

    @Override
    public void beforeEach(TestExtensionContext context) throws Exception {
        write("Before each callback");
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        write("Before test callback");
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        write("After test callback");
    }

    @Override
    public void afterEach(TestExtensionContext context) throws Exception {
        write("After each callback");
    }

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        write("After all callback");
        long start = (long) context.getStore().get("Start");
        write("Duration: " + (System.currentTimeMillis() - start));
    }

    @Override
    public void handleTestExecutionException(TestExtensionContext context, Throwable throwable) throws Throwable {
        write("Handled exception: " + throwable.getMessage());
    }

}
