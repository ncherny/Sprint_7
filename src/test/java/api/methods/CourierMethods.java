package api.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.page.object.models.Courier;

import static io.restassured.RestAssured.given;

public class CourierMethods {

    @Step("Create a courier account")
    public static Response courierCreate(String login, String password, String firstName) {
        Courier courier = new Courier(login, password, firstName);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Login with a courier account")
    public static Response courierLogin(String login, String password) {
        Courier courier = new Courier(login, password);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Delete courier")
    public static Response courierDelete(Integer id) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .delete(String.format("/api/v1/courier/%d", id));
    }

    @Step("Removing previously created courier")
    public static void cleanUpCreatedCourier(String login, String password) {
        Response loginResponse = courierLogin(login, password);
        if (loginResponse.contentType().equals("application/json")) {
            Integer id = loginResponse.body().path("id");
            if (id != null) {
                courierDelete(id);
            }
        }
    }
}
