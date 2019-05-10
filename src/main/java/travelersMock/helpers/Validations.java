package travelersMock.helpers;

import static travelersMock.controller.emailServiceController.emailMap;

public class Validations {
    public static boolean isTheSameToken(String email, String token) {
        if (emailMap.containsKey(email)) {
            return emailMap.containsValue(token);
        } else {
            return false;
        }
    }
}
