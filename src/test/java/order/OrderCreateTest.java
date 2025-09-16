package order;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Order;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Создание заказов с разными предпочитаемыми цветами")
public class OrderCreateTest {
    private final static String BASE_URI = "http://qa-scooter.praktikum-services.ru/";
    OrderClient orderClient = new OrderClient();
    Order order = OrderGenerator.createRanndomOrder();

    @BeforeEach
    @DisplayName("Создание заказа с определенным цветом")
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    static Stream<Arguments> getColor() {
        return Stream.of(
                Arguments.of(Arrays.asList("BLACK")),
                Arguments.of(Arrays.asList("BLACK", "GREY")),
                Arguments.of(Arrays.asList("GREY")),
                Arguments.of(Arrays.asList())
        );
    }

    @ParameterizedTest
    @MethodSource("getColor")
    public void createOrder(List<String> color) {
        order.setColor(color.toArray(new String[0]));
        Response response = orderClient.create(order);
        int orderTrack = response.path("track");
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .assertThat().body("track", notNullValue());
        Response cancelResponse = orderClient.cancel(orderTrack);
        cancelResponse.then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("ok", equalTo(true));
    }
}