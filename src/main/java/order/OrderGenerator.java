package order;

import models.Order;

import java.util.Random;

import static utils.Utils.*;

public class OrderGenerator {


    public static Order createRanndomOrder(){
        Random random = new Random();
        return new Order()
                .withFirstName(randomString(12))
                .withLastName(randomString(12))
                .withAddress(randomString(20))
                .withMetroStation(randomString(5))
                .withPhone(randomString(11))
                .withRentTime(getRandomNumber(1, 120))
                .withDeliveryDate(getRandomDeliveryDate())
                .withComment(randomString(40))
                .withColor(new String[]{randomString(3), randomString(3)});

    }
}