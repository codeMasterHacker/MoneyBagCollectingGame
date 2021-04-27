import java.util.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class ReleasedKeyEventHandler implements EventHandler<KeyEvent>
{
public HashSet<String> keys;
	
	public ReleasedKeyEventHandler(HashSet<String> set)
	{
		keys = set;
	}
	
	@Override
	public void handle(KeyEvent keyEvent) 
	{
		keys.remove(keyEvent.getCode().toString());
	}
}
