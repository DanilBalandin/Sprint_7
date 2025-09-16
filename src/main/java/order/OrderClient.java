package order;
import io.qameta.allure.Step;
import models.Order;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient {
    private final static String ORDERS_URL = "/api/v1/orders";
    private final static String CANCEL_URL = "/api/v1/orders/cancel";
    @Step("Создание заказа")
    public Response create(Order order){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(ORDERS_URL);
    }
    @Step("Отмена заказа")
    public Response cancel(int orderTrack){
        return given()
                .header("Content-type", "application/json")
                .and()
                .param("track", orderTrack)
                .body("{\"track\": " + orderTrack +" }")
                .when()
                .put(CANCEL_URL);

    }
    @Step("Получение всех заказов")
    public Response getAllOrders(){
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(ORDERS_URL);
    }
}