package UML_Pac.Mode;

import UML_Pac.Object.BasicClass;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class CreateClassMode extends Mode{
	public CreateClassMode() {}
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
				BasicClass Class = new BasicClass(mouseCoordiante.getX() , mouseCoordiante.getY() , gc);
				ShapeContainer.add(Class);
			}
		}
	};
}