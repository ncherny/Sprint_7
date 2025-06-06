import api.methods.OrdersMethods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.page.object.models.CancelOrderRequestBody;
import ru.yandex.praktikum.page.object.models.Order;
import util.Consts;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class OrderCreationTests {

    private final Order order;
    private Integer trackId;

    public OrderCreationTests(String firstName, String lastName, String address, Integer metroStation, String phone, Integer rentTime, String deliveryDate, String comment, String[] color) {
        order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, Arrays.asList(color));
    }

    @Before
    public void setup() {
        RestAssured.baseURI = Consts.BASE_URL;
    }

    @After
    public void cleanup() {
        if (trackId != null) {
            OrdersMethods.cancelOrder(new CancelOrderRequestBody(trackId));
        }
    }

    @Test
    @DisplayName("Positive test: Create an order")
    public void orderCreationSuccessTest() {
        Response response = OrdersMethods.createOrder(order);
        response
                .then()
                .statusCode(201)
                .assertThat().body("track", Matchers.notNullValue());
        this.trackId = response.body().path("track");
    }

    @Parameterized.Parameters(name = "{index}. Test data: First name={0}; Last name={1}, Address={2}, Metro station={3}, Phone={4}, Rent time={5}, Delivery date={6}, Comment={7}, Color={8}")
    public static Object[][] getData() {
        return new Object[][] {
                { "Иван", "Иванов", "Иванова 12", 4, "+7 800 355 35 35", 5, "2026-01-21", "Комментарий", new String[]{"BlACK", "GREY"}},
                { "Иван", "Иванов", "Иванова 12", 4, "+7 800 355 35 35", 5, "2026-01-21", "Комментарий", new String[]{"BlACK"}},
                { "Иван", "Иванов", "Иванова 12", 4, "+7 800 355 35 35", 5, "2026-01-21", "Комментарий", new String[]{"GREY"}},
                { "Иван", "Иванов", "Иванова 12", 4, "+7 800 355 35 35", 5, "2026-01-21", "Комментарий", new String[]{}}
        };
    }

}
