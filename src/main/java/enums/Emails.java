package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Emails {
    AUTOTEST_EMAIL("autotest_autotest@mail.ru","Test e-mail","Hello! This is a test e-mail.");

    private String addressee;
    private String subject;
    private String text;

    @Override
    public String toString() {
        return "Emails{" +
                "addressee='" + addressee + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
