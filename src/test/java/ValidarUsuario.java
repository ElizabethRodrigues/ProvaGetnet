import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class ValidarUsuario {

    @Test
    public void TamanhoListUsers(){

        RestAssured.given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .log().all()
                .body("data", Matchers.hasSize(6))
                .body("data.first_name", Matchers.hasItems("Michael", "Lindsay", "Tobias" , "Byron", "George", "Rachel"))
        //.body("findAll{it.id >= 1}.first_name", Matchers.is("Lindsay"))

        ;
    }

    @Test
    public void UserNotFound() {

        RestAssured.given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404)
        ;
    }

    @Test
    public void InserirUsuarioValid(){
        //Validar inclusão de usuário válido
        RestAssured.given()
                .log().all()
                .body("{\"name\": \"Elizabeth\",\"job\": \"leader\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.is(Matchers.notNullValue()))
        ;
    }
}
