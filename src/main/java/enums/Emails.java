package enums;


public enum Emails {
    AUTOTEST_EMAIL("autotest_autotest@mail.ru","Test e-mail","Hello! This is a test e-mail.");

    private String addressee;
    private String subject;
    private String text;

    Emails(String addressee, String subject, String text) {
        this.addressee = addressee;
        this.subject = subject;
        this.text = text;
    }

    public String getAddressee() {
        return addressee;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
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
