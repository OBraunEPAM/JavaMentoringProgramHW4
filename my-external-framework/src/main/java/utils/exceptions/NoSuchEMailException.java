package utils.exceptions;

import utils.enums.Emails;
import utils.enums.MailRuData;

public class NoSuchEMailException extends Exception {

    public NoSuchEMailException(Emails email, MailRuData folderName) {
        super(String.format("There is no e-mail with \"%s\" title and \"%s\" addressee in %s folder", email.getSubject(), email.getAddressee(), folderName.value));
    }

    public NoSuchEMailException(Emails email) {
        super(String.format("There is no e-mail with \"%s\" title and \"%s\" addressee in the folder", email.getSubject(), email.getAddressee()));
    }
}
