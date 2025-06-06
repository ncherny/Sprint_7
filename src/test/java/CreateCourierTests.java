import api.methods.CourierMethods;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTests extends BaseCourierTestClass{

    @Test
    @DisplayName("Positive test: Create a courier account with first name")
    public void createCourierSuccessTest() {
        CourierMethods.courierCreate(login, password, firstName)
            .then()
            .statusCode(201)
            .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Positive test: Create a courier account without first name")
    public void createCourierMissingFirstnameSuccessTest() {
        CourierMethods.courierCreate(login, password, null)
                .then()
                .statusCode(201)
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Negative test: Create a courier using an already taken login")
    public void createDuplicateCourierNegativeTest() {
        CourierMethods.courierCreate(login, password, firstName);
        CourierMethods.courierCreate(login, password, firstName)
                .then()
                .statusCode(409)
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    @Test
    @DisplayName("Negative test: Create a courier without 'login' field")
    public void createCourierLoginFieldMissingNegativeTest() {
        CourierMethods.courierCreate(null, password, firstName)
                .then()
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }


    @Test
    @DisplayName("Negative test: Create a courier without 'password' field")
    public void createCourierPasswordFieldMissingNegativeTest() {
        CourierMethods.courierCreate(login, null, firstName)
                .then()
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
