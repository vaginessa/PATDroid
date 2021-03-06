package patdroid.smali;

import org.junit.Assert;
import org.junit.Test;
import patdroid.core.Scope;

import java.io.File;
import java.util.logging.Logger;

public class SmaliLoaderTest {
    private static final File FRAMEWORK_CLASSES_FOLDER = new File("apilevels");
    private static final int API_LEVEL = 19;
    private final Scope scope = new Scope();
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Test
    public void testLoadFrameworkClasses() {
        SmaliClassDetailLoader ldr;
        try {
            ldr = SmaliClassDetailLoader.fromFramework(FRAMEWORK_CLASSES_FOLDER, API_LEVEL);
        } catch (RuntimeException e) {
            logger.info("framework classes loader test skipped, API19 not available");
            return ;
        }
        ldr.loadAll(scope);
        Assert.assertNotNull(scope.findClass("android.app.Activity"));
        Assert.assertNotNull(scope.findClass("android.view.View"));
        Assert.assertTrue(scope.findClass("android.view.View").isConvertibleTo(scope.findClass("java.lang.Object")));
        Assert.assertFalse(scope.findClass("java.lang.Object").isConvertibleTo(scope.findClass("android.view.View")));
        Assert.assertNull(scope.findClass("android.bluetooth.le.ScanResult")); // api21
    }
}
