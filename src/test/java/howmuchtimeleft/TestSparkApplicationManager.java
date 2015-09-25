package howmuchtimeleft;


import org.testng.ISuite;
import org.testng.ISuiteListener;
import spark.Spark;

public class TestSparkApplicationManager implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        Application.main(null);
        Spark.awaitInitialization();
    }

    @Override
    public void onFinish(ISuite suite) {
        Spark.stop();
    }
}
