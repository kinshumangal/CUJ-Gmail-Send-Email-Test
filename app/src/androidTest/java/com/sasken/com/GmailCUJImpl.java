package com.sasken.com;

import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import java.util.List;

public class GmailCUJImpl implements IGmailCUJ {

    private static final String TAG = "GmailCUJImpl";
    private UiDevice mUiDevice;
    public static final String UI_PACKAGE = "com.google.android.gm";
    private static final long SHORT_TIMEOUT = 3000;

    /**
     * for launch the Gmail application
     */
    @Override
    public void openGmailApp() {
        mUiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mUiDevice.waitForIdle();
        mUiDevice.pressHome();
        final String launcherPackage = mUiDevice.getLauncherPackageName();
        mUiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                SHORT_TIMEOUT);
        safeClick(By.desc("Gmail"), SHORT_TIMEOUT);
    }

    @Override
    public void clickComposeIcon(){
        safeClick(By.text("Compose"), SHORT_TIMEOUT);
    }

    @Override
    public void addEmailAddress(String address) {
        UiObject2 toAddress = mUiDevice.wait(Until.findObject(By.res(UI_PACKAGE, "peoplekit_autocomplete_chip_group")), SHORT_TIMEOUT);
        List<UiObject2> child = toAddress.getChildren();
        UiObject2 toEditText = child.get(0);
        toEditText.setText(address);
        SystemClock.sleep(SHORT_TIMEOUT);
        mUiDevice.pressEnter();
    }

    @Override
    public void setEmailSubject(String subject) {
        UiObject2 toEditText = mUiDevice.wait(Until.findObject(By.res(UI_PACKAGE, "subject")), SHORT_TIMEOUT);
        toEditText.setText(subject);
        SystemClock.sleep(SHORT_TIMEOUT);
        mUiDevice.pressEnter();
    }

    @Override
    public void setEmailBody(String body) {
        UiObject2 toEditText = mUiDevice.wait(Until.findObject(By.text("Compose email")), SHORT_TIMEOUT);
        toEditText.setText(body);
        SystemClock.sleep(SHORT_TIMEOUT);
        mUiDevice.pressEnter();
    }

    @Override
    public void clickSendButton() {
        safeClick(By.res(UI_PACKAGE, "send"), SHORT_TIMEOUT);
        Log.d(TAG, "clickSendButton: Email sent successfully");
        SystemClock.sleep(SHORT_TIMEOUT);

    }

    @Override
    public void comeBack(int times) {
        for(int i=0;i<times;i++){
            mUiDevice.pressBack();
        }
    }


    private boolean safeClick(BySelector selector, long timeout){
        UiObject2 dev = mUiDevice.wait(Until.findObject(selector), timeout);
        if (dev != null){
            dev.click();
            SystemClock.sleep(2000);
            Log.d(TAG, "safeClick: "+"openThreeDotsTab: true"+selector.toString());
            return true;
        }
        Log.d(TAG, "safeClick: "+"openThreeDotsTab: false"+selector.toString());
        return false;
    }

}
