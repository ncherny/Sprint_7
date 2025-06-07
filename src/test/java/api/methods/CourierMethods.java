package api.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.page.object.models.Courier;
import ru.yandex.praktikum.page.object.models.CourierLoginRequestBody;

import static io.restassured.RestAssured.given;

public class CourierMethods {

    private static final String URL_COURIER_CREATE = "/api/v1/courier";
    private static final String URL_COURIER_DELETE = "/api/v1/courier/%d";
    private static final String URL_COURIER_LOGIN = "/api/v1/courier/login";

    @Step("Create a courier account")
    public static Response courierCreate(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(URL_COURIER_CREATE);
    }

    @Step("Login with a courier account")
    public static Response courierLogin(CourierLoginRequestBody courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(URL_COURIER_LOGIN);
    }

    @Step("Delete courier")
    public static Response courierDelete(Integer id) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .delete(String.format(URL_COURIER_DELETE, id));
    }

    @Step("Removing previously created courier")
    public static void cleanUpCreatedCourier(CourierLoginRequestBody courier) {
        Response loginResponse = courierLogin(courier);
        if (loginResponse.contentType().equals("application/json")) {
            Integer id = loginResponse.body().path("id");
            if (id != null) {
                courierDelete(id);
            }
        }
    }
}
