import api.methods.CourierMethods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.page.object.models.Courier;
import ru.yandex.praktikum.page.object.models.CourierLoginRequestBody;
import util.Consts;


import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTests {
    private String login;
    private String password;
    private String firstName;

    @Before
    public void setup() {
        RestAssured.baseURI = Consts.BASE_URL;

        login = UUID.randomUUID().toString();
        password = UUID.randomUUID().toString();
        firstName = UUID.randomUUID().toString();
    }

    @After
    public void cleanup() {
        CourierMethods.cleanUpCreatedCourier(new CourierLoginRequestBody(login, password));
    }
    @Test
    @DisplayName("Positive test: Create a courier account with first name")
    public void createCourierSuccessTest() {
        CourierMethods.courierCreate(new Courier(login, password, firstName))
            .then()
            .statusCode(HttpStatus.SC_CREATED)
            .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Positive test: Create a courier account without first name")
    public void createCourierMissingFirstnameSuccessTest() {
        CourierMethods.courierCreate(new Courier(login, password, null))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Negative test: Create a courier using an already taken login")
    public void createDuplicateCourierNegativeTest() {
        Courier courier = new Courier(login, password, firstName);
        CourierMethods.courierCreate(courier);
        CourierMethods.courierCreate(courier)
                .then()
                .statusCode(HttpStatus.SC_CONFLICT)
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    @Test
    @DisplayName("Negative test: Create a courier without 'login' field")
    public void createCourierLoginFieldMissingNegativeTest() {
        CourierMethods.courierCreate(new Courier(null, password, firstName))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }


    @Test
    @DisplayName("Negative test: Create a courier without 'password' field")
    public void createCourierPasswordFieldMissingNegativeTest() {
        CourierMethods.courierCreate(new Courier(login, null, firstName))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
