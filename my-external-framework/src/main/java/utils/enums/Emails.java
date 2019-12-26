package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Emails {
    AUTOTEST_EMAIL("autotest_autotest@mail.ru","Test e-mail","Hello! This is a test e-mail."),
    BLANK_EMAIL("autotest_blank@mail.ru", "Test", "Hi! This is a blank e-mail.");

    private String addressee;
    private String subject;
    private String text;

    public String trimAddresse(Emails emails) {
        return emails.getAddressee().substring(0, emails.getAddressee().indexOf("@")).replaceAll("_", " ").toLowerCase();
    }

    @Override
    public String toString() {
        return "Emails{" +
                "addressee='" + addressee + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
