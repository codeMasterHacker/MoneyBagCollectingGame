import java.util.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PressedKeyEventHandler implements EventHandler<KeyEvent>
{
	public HashSet<String> keys;
	
	public PressedKeyEventHandler(HashSet<String> set)
	{
		keys = set;
	}
	
	@Override
	public void handle(KeyEvent keyEvent) 
	{
		keys.add(keyEvent.getCode().toString());
	}
}
