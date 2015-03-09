
import controleur.ControleurMain;
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * 
 * 
 * 
 */
/**
 * TestLangage.java Main
 */
public class TestLangage extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	Application.launch(TestLangage.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
	ControleurMain cm = new ControleurMain(primaryStage);
    }

}
