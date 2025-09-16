package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import models.Courier;
import org.junit.jupiter.api.DisplayName;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Utils.randomString;

@DisplayName("Создание курьера негативные тесты")
public class CreateCourierNegativeTest {
    private static String BASE_URI = "http://qa-scooter.praktikum-services.ru/";
    private final CourierClient courierClient = new CourierClient();
    Courier courier =  CourierGenerator.randomCourier();
    @BeforeEach
    public void setup(){
        RestAssured.baseURI = BASE_URI;
    }
    @Test
    @DisplayName("Проверка, что нельзя создать одинаковых курьеров")
    public void createSameCourierReturnConflictTest(){
        Courier sameCourier = new Courier()
                .withLogin(courier.getLogin())
                .withPassword(randomString(8))
                .withFirstName(randomString(10));
        Response firstResponse = courierClient.create(courier);
        assertEquals(HttpStatus.SC_CREATED, firstResponse.statusCode(), "Неверный статус код при создании курьера");
        Response sameResponse = courierClient.create(sameCourier);
        sameResponse
                .then()
                .statusCode(HttpStatus.SC_CONFLICT)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
    @Test
    @DisplayName("Создание курьера без логина")
    public void noLoginReturnBadRequest(){
        Courier courierNoLogin = new Courier()
                .withPassword(randomString(20))
                .withFirstName(randomString(10));
        Response noLoginResponse = courierClient.create(courierNoLogin);
        noLoginResponse
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Создание курьера без пароля")
    public void noPassReturnBadRequest(){
        Courier courierNoPass = new Courier()
                .withLogin(randomString(20))
                .withFirstName(randomString(10));
        Response noPassResponse = courierClient.create(courierNoPass);
        noPassResponse
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @AfterEach
    public void tearDown(){
        if(courierClient.getCourierId(courier) != null) {
            courierClient.deleteCourier(courier);
        }
    }
}