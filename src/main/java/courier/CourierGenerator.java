package courier;

import models.Courier;
import utils.*;

public class CourierGenerator {
    public static Courier randomCourier(){
        return new Courier()
                .withLogin(Utils.randomString(8))
                .withPassword(Utils.randomString(10))
                .withFirstName(Utils.randomString(16));
    }
}