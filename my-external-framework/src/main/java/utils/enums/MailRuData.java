package utils.enums;

public enum MailRuData {
    MAIL_RU_URL("https://mail.ru/"),
    MAIL_RU_LOGGED_URL("https://e.mail.ru/inbox/"),
    MAIL_RU_TITLE("Mail.ru: почта, поиск в интернете, новости, игры"),
    LOG_OFF_TITLE("Вход - Почта Mail.ru"),
    LOG_IN_TITLE("Входящие - Почта Mail.ru"),
    SEND("Отправить"),
    SAVE_AS_DRAFT("Сохранить"),
    CANCEL("Отменить"),
    INBOX("Входящие"),
    SENT("Отправленные"),
    DRAFT("Черновики");

    public String value;

    MailRuData(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
