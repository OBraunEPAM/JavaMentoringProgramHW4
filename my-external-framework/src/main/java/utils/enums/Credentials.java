package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Credentials {
    AUTOTEST_USER("autotest_autotest@mail.ru", "THISis!@#");

    private String username;
    private String password;

    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
