package UML_Pac.Mode;

import javafx.scene.input.MouseEvent;
import UML_Pac.Object.BasicObject;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CreateObjectMode extends Mode{
	public CreateObjectMode() {}
	@Override
	public void SetHandler() {
		canvas.addEventFilter(MouseEvent.ANY, mouseHandler);
	}
	@Override
	public void RemoveHandler() {
		canvas.removeEventFilter(MouseEvent.ANY, mouseHandler);
	}
	EventHandler mouseHandler = new EventHandler() {
		@Override
		public void handle(Event event) {
			MouseEvent mouseCoordiante = (MouseEvent) event;
			if(mouseCoordiante.getEventType()==MouseEvent.MOUSE_CLICKED) {
				BasicObject Object = new BasicObject(mouseCoordiante.getX() , mouseCoordiante.getY() , gc);
				ShapeContainer.add(Object);
			}
		}
	};
}
