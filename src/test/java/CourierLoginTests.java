import api.methods.CourierMethods;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTests extends BaseCourierTestClass {

    @Test
    @DisplayName("Positive test: Login into a courier account")
    public void loginSuccessTest() {
        CourierMethods.courierCreate(login, password, firstName);
        CourierMethods.courierLogin(login, password)
                .then()
                .statusCode(200)
                .assertThat().body("id", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Negative test: Login with a nonexistent login")
    public void loginNonExistentUserNegativeTest() {
        CourierMethods.courierLogin(login, password)
                .then()
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Negative test: Login with invalid password")
    public void loginInvalidPasswordNegativeTest() {
        CourierMethods.courierCreate(login, password, firstName);
        CourierMethods.courierLogin(login, "invalidPassword")
                .then()
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Negative test: Login without 'login' field")
    public void loginLoginFieldMissingNegativeTest() {
        CourierMethods.courierCreate(login, password, firstName);
        CourierMethods.courierLogin(null, password)
                .then()
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Negative test: Login without 'password' field")
    public void loginPasswordFieldsMissingNegativeTest() {
        CourierMethods.courierCreate(login, password, firstName);
        CourierMethods.courierLogin(login, null)
                .then()
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
}
