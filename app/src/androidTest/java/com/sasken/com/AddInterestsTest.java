package com.sasken.com;

import androidx.test.uiautomator.UiObjectNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddInterestsTest {

    IGmailCUJ gmailCUJObj = new GmailCUJImpl();

    @Before
    public void beforeTest (){
        gmailCUJObj.openGmailApp();
    }
    @Test
    public void emailSendTest () throws UiObjectNotFoundException, IllegalAccessException {

//        Click on "Compose"
        gmailCUJObj.clickComposeIcon();
//        Add Email address
        gmailCUJObj.addEmailAddress("enlighterk@gmail.com");
//        Set Email Subject
        gmailCUJObj.setEmailSubject("Email CUJ Test");
//        add Email Body
        gmailCUJObj.setEmailBody("This is email CUJ test");
//        click Send Button
        gmailCUJObj.clickSendButton();
    }

    @After
    public void afterTest(){
        gmailCUJObj.comeBack(3);
    }
}
