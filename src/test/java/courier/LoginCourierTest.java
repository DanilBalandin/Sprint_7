package courier;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Courier;
import models.CourierCreds;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static utils.Utils.randomString;


@DisplayName("Логин курьера")
public class LoginCourierTest {
    private static String BASE_URI = "http://qa-scooter.praktikum-services.ru/";
    private final CourierClient courierClient = new CourierClient();
    Courier courier = CourierGenerator.randomCourier();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        courierClient.create(courier);
    }

    @Test
    @DisplayName("Вход под курьером. Возвращается ID курьера")
    public void loginCourierTest() {
        Response response = courierClient.login(CourierCreds.credsFrom(courier));
        response.then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("id", notNullValue());

    }

    @Test
    @DisplayName("Вход без пароля")
    public void noPassReturnError() {
        CourierCreds creds = CourierCreds.credsFrom(courier);
        creds.setPassword("");
        Response response = courierClient.login(creds);
        response.then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Вход без указания логина")
    public void noLoginReturnError() {
        CourierCreds creds = CourierCreds.credsFrom(courier);
        creds.setLogin("");
        Response response = courierClient.login(creds);
        response.then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Попытка входа с неверным паролем")
    public void wrongPassReturnError() {
        CourierCreds creds = CourierCreds.credsFrom(courier);
        creds.setPassword(randomString(2));
        Response response = courierClient.login(creds);
        response.then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Попытка входа с неверным логином")
    public void wrongLoginReturnError() {
        CourierCreds creds = CourierCreds.credsFrom(courier);
        creds.setLogin(randomString(3));
        Response response = courierClient.login(creds);
        response.then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Попытка входа с неверным паролем и логином")
    public void wrongLoginAndPassReturnError() {
        CourierCreds creds = CourierCreds.credsFrom(courier);
        creds.setLogin(randomString(3));
        creds.setPassword(randomString(3));
        Response response = courierClient.login(creds);
        response.then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @AfterEach
    public void tearDown() {
        courierClient.deleteCourier(courier);
    }
}