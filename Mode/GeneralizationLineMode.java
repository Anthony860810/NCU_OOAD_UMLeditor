package UML_Pac.Mode;

import UML_Pac.Line.GeneralizationLine;
import UML_Pac.Object.Shape;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GeneralizationLineMode extends Mode{
	public GeneralizationLineMode() {}
	private Shape StartObject , EndObject=null;
	private int index1 , index2;
	protected double StartX , StartY;
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
			MouseEvent mouseevent = (MouseEvent) event;
			if(mouseevent.getEventType()==MouseEvent.MOUSE_PRESSED) {
				StartX = mouseevent.getX();
				StartY = mouseevent.getY();
				ShapeContainer.forEach((object)->{
					//촑�_헕을┳쫇かτㅊ
					if(object.isonClicked(mouseevent.getX(), mouseevent.getY())) {
						System.out.println("pressed");
						StartObject = object;
						index1 = object.getPortOnClick();
					}
				});
			}
			if(mouseevent.getEventType()==MouseEvent.MOUSE_DRAGGED) {
				DrawDraggingLine(mouseevent);
				ShapeContainer.forEach((object)->{
					//촑�_헕을┳쫇かτㅊ
					if(object.isonClicked(mouseevent.getX(), mouseevent.getY()) && object!=StartObject) {
						EndObject = object;
						index2 = object.getPortOnClick();
					}
					
				});
			}
			if(mouseevent.getEventType()==MouseEvent.MOUSE_RELEASED) {
				FinalDraw();
				if(EndObject!=null && StartObject.CanLink() && EndObject.CanLink()) {
					LineContainer.add(new GeneralizationLine(StartObject , index1 , EndObject , index2 , gc));
					EndObject=null;
				}
					
			}
		}
		private void DrawDraggingLine(MouseEvent mouseevent) {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			ShapeContainer.forEach((object)->{
				object.draw();
				object.WriteName();
			});
			LineContainer.forEach((line)->{
				line.Draw();
			});
			DrawActiveLine(mouseevent,StartX,StartY);
		}
		private void FinalDraw() {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			ShapeContainer.forEach((object)->{
				object.draw();
				object.WriteName();
			});
			LineContainer.forEach((line)->{
				line.Draw();
			});
		}
		private void DrawActiveLine(MouseEvent mouseevent,double startX, double startY) {
			gc.setStroke(Color.VIOLET);
			gc.strokeLine(startX, startY, mouseevent.getX(), mouseevent.getY());
		}
		
	};
}
