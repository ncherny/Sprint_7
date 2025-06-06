package api.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.page.object.models.CancelOrderRequestBody;
import ru.yandex.praktikum.page.object.models.Order;
import ru.yandex.praktikum.page.object.models.GetOrderListRequestBody;

import static io.restassured.RestAssured.given;

public class OrdersMethods {
    @Step("Create order")
    public static Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Get order list")
    public static Response getOrderList(GetOrderListRequestBody getOrderListRequestBody) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(getOrderListRequestBody)
                .when()
                .get("/api/v1/orders");
    }

    @Step("Cancel previously created order")
    public static Response cancelOrder(CancelOrderRequestBody cancelOrderRequestBody) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(cancelOrderRequestBody)
                .when()
                .put("/api/v1/orders/cancel");
    }

}
