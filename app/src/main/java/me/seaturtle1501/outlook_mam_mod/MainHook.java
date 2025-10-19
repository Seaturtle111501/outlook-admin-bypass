package me.seaturtle1501.outlook_mam_mod;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // Filtering unnecessary applications
        if (!lpparam.packageName.equals("com.microsoft.office.outlook")) return;
        // Execute Hook
        hook(lpparam);
    }

    private void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        // Specific hook code
        XposedBridge.log("Loaded app: " + lpparam.packageName);

        findAndHookMethod("com.microsoft.office.outlook.olmcore.managers.mdm.OlmDeviceEnrollmentManager", lpparam.classLoader, "isDeviceManagementRequired", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                return false;
            }
        });
    }
}
