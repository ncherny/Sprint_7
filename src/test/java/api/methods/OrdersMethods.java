package api.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.page.object.models.CancelOrderRequestBody;
import ru.yandex.praktikum.page.object.models.Order;
import ru.yandex.praktikum.page.object.models.GetOrderListRequestBody;

import static io.restassured.RestAssured.given;

public class OrdersMethods {

    private static final String URL_CREATE_ORDER = "/api/v1/orders";
    private static final String URL_GET_ORDER_LIST = "/api/v1/orders";
    private static final String URL_CANCEL_ORDER = "/api/v1/orders/cancel";

    @Step("Create order")
    public static Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(URL_CREATE_ORDER);
    }

    @Step("Get order list")
    public static Response getOrderList(GetOrderListRequestBody getOrderListRequestBody) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(getOrderListRequestBody)
                .when()
                .get(URL_GET_ORDER_LIST);
    }

    @Step("Cancel previously created order")
    public static Response cancelOrder(CancelOrderRequestBody cancelOrderRequestBody) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(cancelOrderRequestBody)
                .when()
                .put(URL_CANCEL_ORDER);
    }

}
