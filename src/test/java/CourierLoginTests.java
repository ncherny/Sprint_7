import api.methods.CourierMethods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.page.object.models.Courier;
import ru.yandex.praktikum.page.object.models.CourierLoginRequestBody;
import util.Consts;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTests {
    private String login;
    private String password;
    private String firstName;

    @Before
    public void setup() {
        RestAssured.baseURI = Consts.BASE_URL;

        login = UUID.randomUUID().toString();
        password = UUID.randomUUID().toString();
        firstName = UUID.randomUUID().toString();

        CourierMethods.courierCreate(new Courier(login, password, firstName));
    }

    @After
    public void cleanup() {
        CourierMethods.cleanUpCreatedCourier(new CourierLoginRequestBody(login, password));
    }

    @Test
    @DisplayName("Positive test: Login into a courier account")
    public void loginSuccessTest() {
        CourierMethods.courierLogin(new CourierLoginRequestBody(login, password))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("id", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Negative test: Login with a nonexistent login")
    public void loginNonExistentUserNegativeTest() {
        CourierMethods.courierLogin(new CourierLoginRequestBody(UUID.randomUUID().toString(), password))
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Negative test: Login with invalid password")
    public void loginInvalidPasswordNegativeTest() {
        CourierMethods.courierLogin(new CourierLoginRequestBody(login, "invalidPassword"))
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Negative test: Login without 'login' field")
    public void loginLoginFieldMissingNegativeTest() {
        CourierMethods.courierLogin(new CourierLoginRequestBody(null, password))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Negative test: Login without 'password' field")
    public void loginPasswordFieldsMissingNegativeTest() {
        CourierMethods.courierLogin(new CourierLoginRequestBody(login, null))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
}
