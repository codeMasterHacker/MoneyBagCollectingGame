import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop extends AnimationTimer
{
	LongValue lastNanoTime;
	IntegerValue points;
	Sprite briefCase;
	HashSet<String> keys;
	ArrayList<Sprite> moneyBagList;
	GraphicsContext graphicsContext;
	
	public GameLoop(LongValue time, IntegerValue score, Sprite sprite, HashSet<String> set, ArrayList<Sprite> list, GraphicsContext gc) 
	{
		lastNanoTime = time;
		points = score;
		briefCase = sprite;
		keys = set;
		moneyBagList = list;
		graphicsContext = gc;
	}

	@Override
	public void handle(long currentNanoTime) 
	{
		// calculate time since last update.
        	double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
        	lastNanoTime.value = currentNanoTime;
        
        	// game logic
        	briefCase.setVelocity(0,0);
        
        	if (keys.contains("LEFT"))
        		briefCase.addVelocity(-50,0);
        	if (keys.contains("RIGHT"))
        		briefCase.addVelocity(50,0);
        	if (keys.contains("UP"))
        		briefCase.addVelocity(0,-50);
        	if (keys.contains("DOWN"))
        		briefCase.addVelocity(0,50);
            
        	briefCase.update(elapsedTime);
        
        	// collision detection
        	Iterator<Sprite> moneyBagIter = moneyBagList.iterator();
        	while (moneyBagIter.hasNext())
        	{
            		Sprite moneyBag = moneyBagIter.next();
            
            		if (briefCase.intersects(moneyBag))
            		{
                		moneyBagIter.remove();
                		points.value++;
            		}
        	}
        
        	// render
        	graphicsContext.clearRect(0, 0, 512, 512);
        	briefCase.render(graphicsContext);
        
        	for (Sprite moneyBag : moneyBagList)
            		moneyBag.render(graphicsContext);

        	String pointsText = "Cash: $" + (100 * points.value);
        	graphicsContext.fillText(pointsText, 360, 36);
        	graphicsContext.strokeText(pointsText, 360, 36);
	}
}
