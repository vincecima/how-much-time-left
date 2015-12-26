package howmuchtimeleft.filters;

public class JSONResponseTypeFilter {

    public void use() {
        spark.Spark.before((request, response) -> {
            response.type("application/json");
        });
    }
}
