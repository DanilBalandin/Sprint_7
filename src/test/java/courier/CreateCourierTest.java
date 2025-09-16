package courier;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Courier;
import models.CourierCreds;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Создание курьера")
public class CreateCourierTest {
    private static String BASE_URI = "http://qa-scooter.praktikum-services.ru/";
    private final CourierClient courierClient = new CourierClient();
    Courier courier = CourierGenerator.randomCourier();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    @DisplayName("Создание нового курьера и вход под ним")
    public void createCourierTest() {
        Response response = courierClient.create(courier);
        assertEquals(HttpStatus.SC_CREATED,response.statusCode(), "Неверный статус код при создании курьера");
        response.then().assertThat().body("ok", equalTo(true));
        Response loginResponse = courierClient.login(CourierCreds.credsFrom(courier));
        assertEquals(HttpStatus.SC_OK, loginResponse.statusCode(), "Не удалось залогиниться");
    }

    @AfterEach
    public void tearDown() {
        Response response = courierClient.deleteCourier(courier);
        assertEquals(HttpStatus.SC_OK, response.getStatusCode(),"Ошибка при удалении курьера");
    }

}