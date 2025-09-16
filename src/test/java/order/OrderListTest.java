package order;


import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Получение всего списка заказов")
public class OrderListTest {
    private final static String BASE_URI = "http://qa-scooter.praktikum-services.ru/";
    OrderClient orderClient = new OrderClient();

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }
    @DisplayName("Получение списка")
    @Test
    public void getOrderList() {
        orderClient.getAllOrders()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("orders", notNullValue());
    }
}