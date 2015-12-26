package howmuchtimeleft.utils;

import com.google.gson.Gson;

public class ErrorHandler {
    private Gson gson = null;

    public ErrorHandler(Gson gson) {
        this.gson = gson;
    }

    public void use() {
        spark.Spark.exception(Exception.class, (e, request, response) -> {
            if(e instanceof ValidationException) {
                response.status(422);
                response.body(this.gson.toJson(new ErrorResponse((ValidationException) e)));
            }
            else {
                response.status(400);
                response.body("");
            }
        });
    }
}
