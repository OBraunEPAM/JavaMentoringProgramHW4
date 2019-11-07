package Exceptions;

import enums.Emails;
import enums.MailRuData;

public class NoSuchEMailException extends Exception {

    public NoSuchEMailException(Emails email, MailRuData folderName) {
        super(String.format("There is no e-mail with \"%s\" title in %s folder", email.getSubject(), folderName.value));
    }
}
