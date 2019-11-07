package enums;

public enum MailRuData {
    MAIL_RU_URL("https://mail.ru/"),
    MAIL_RU_TITLE("@mail.ru"),
    LOG_OFF_TITLE("Вход - Почта Mail.ru"),
    NEW_EMAIL_SEND("Отправить"),
    NEW_EMAIL_SAVE_AS_DRAFT("Сохранить"),
    NEW_EMAIL_CANCEL("Отменить"),
    INBOX("Входящие"),
    SENT("Отправленные"),
    DRAFT("Черновики");


    public String value;

    MailRuData(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MailRuData{" +
                "value='" + value + '\'' +
                '}';
    }
}
