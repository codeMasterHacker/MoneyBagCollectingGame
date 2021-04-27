import java.util.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.text.*;

public class MoneyBagCollectingGame extends Application
{
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		String briefCaseImageLocation = "file:///Users/Enrique/Desktop/codeFolders/Java/MoneyBagCollectingGame/images/briefcase.png";
		String moneyBagImageLocation = "file:///Users/Enrique/Desktop/codeFolders/Java/MoneyBagCollectingGame/images/moneybag.png";
		
		stage.setTitle("Collect the Money Bags!");

        	Group root = new Group();

        	Canvas canvas = new Canvas(512, 512);
        	root.getChildren().add(canvas);

        	HashSet<String> keys = new HashSet<String>();
        
        	Scene scene = new Scene(root);
        	scene.setOnKeyPressed(new PressedKeyEventHandler(keys));
        	scene.setOnKeyReleased(new ReleasedKeyEventHandler(keys));
        	stage.setScene(scene);

        	GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        	graphicsContext.setFont(Font.font("Andale Mono", FontWeight.BOLD, 24));
        	graphicsContext.setFill(Color.GREEN);
        	graphicsContext.setStroke(Color.BLACK);
        	graphicsContext.setLineWidth(1);
        
        	Sprite briefCase = new Sprite();
        	briefCase.setImage(briefCaseImageLocation);
        	briefCase.setPosition(200, 0);
        
        	ArrayList<Sprite> moneyBagList = new ArrayList<Sprite>();
        	for (int i = 0; i < 15; i++)
        	{
            		Sprite moneyBag = new Sprite();
            		moneyBag.setImage(moneyBagImageLocation);
            		double px = 350 * Math.random() + 50;
            		double py = 350 * Math.random() + 50;          
            		moneyBag.setPosition(px, py);
            		moneyBagList.add(moneyBag);
        	}
        
        	LongValue lastNanoTime = new LongValue(System.nanoTime());
        	IntegerValue score = new IntegerValue(0);
        
        	GameLoop gameLoop = new GameLoop(lastNanoTime, score, briefCase, keys, moneyBagList, graphicsContext);
        	gameLoop.start();

        	stage.show();
	}
}
