import api.methods.OrdersMethods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.page.object.models.GetOrderListRequestBody;
import util.Consts;

public class GetOrderListTests {

    @Before
    public void setup() {
        RestAssured.baseURI = Consts.BASE_URL;
    }

    @Test
    @DisplayName("Positive test: Get list of orders")
    public void getOrderListSuccessTest() {
        OrdersMethods.getOrderList(new GetOrderListRequestBody())
                .then()
                .statusCode(200)
                .assertThat().body("orders", Matchers.notNullValue());
    }

}
