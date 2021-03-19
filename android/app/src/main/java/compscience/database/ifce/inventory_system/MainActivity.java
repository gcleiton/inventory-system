package compscience.database.ifce.inventory_system;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;


public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "compscience.database.ifce.inventory_system";

    @Override
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);

        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                            if (call.method.equals("testingMethod")) {
                                result.success(testingMethod());
                            } else {
                                result.notImplemented();
                            }
                        }
                );
    }

    String testingMethod() {
        return "Result from Java";
    }
}


