package com.sasken.com;

public interface IGmailCUJ {
    void openGmailApp();
    void comeBack(int times);
    void clickComposeIcon();
    void addEmailAddress(String address);
    void setEmailSubject(String subject);
    void setEmailBody(String body);
    void clickSendButton();


}
