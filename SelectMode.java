package UML_Pac.Mode;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Vector;

import UML_Pac.Object.GroupObject;
import UML_Pac.Object.Shape;
import javafx.event.Event;

public class SelectMode extends Mode{
	protected MouseEvent preEvent;
	protected double StartX , StartY;
	protected double deltaX , deltaY;
	protected boolean DraggingLayout;
	protected boolean isDraggingObject;
	private double MinStartX,MinStartY;
	public SelectMode() {}

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
			//按下滑鼠右鍵
			if(mouseevent.getEventType()==MouseEvent.MOUSE_PRESSED) {		
				DraggingLayout = false;
				StartX = mouseevent.getX();
				StartY = mouseevent.getY();
				//Detect each Object with mouseX and mouseY
				ShapeContainer.forEach((object)->{
					if(object.isonClicked(mouseevent.getX() , mouseevent.getY())) {
					//object.DrawEndpoint();
						selectedObject = object;
						isDraggingObject = true;
						preEvent = mouseevent;
					}
				});
			}
			if(mouseevent.getEventType()==MouseEvent.DRAG_DETECTED) { DraggingLayout=true; }
			if(mouseevent.getEventType()==MouseEvent.MOUSE_DRAGGED) {
				DrawEndpointOnDrag(mouseevent);
				if(isDraggingObject) {
					setDelta(mouseevent);
					DrawWithMovingObject(mouseevent);
					preEvent=mouseevent;
				}
			}
			if(mouseevent.getEventType()==MouseEvent.MOUSE_RELEASED) {
				isDraggingObject = false;
				if(!DraggingLayout)
					DrawEndpointOnClick(mouseevent);
				}
		}
	};
	//被點擊的物件需秀出端點
	private void DrawEndpointOnClick(MouseEvent mouseevent) {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		ShapeContainer.forEach((object)->{
			object.draw();
			object.WriteName();
			if(object.isonClicked(mouseevent.getX(), mouseevent.getY())) {
				selectedObject = object;
				object.DrawEndpoint();
			}
		});
		LineContainer.forEach((line)->{line.Draw();});
	}
	//移動中物體須持續更新畫布且秀出端點
	private void DrawWithMovingObject(MouseEvent mouseevent) {
		selectedObject.ResetPosition(deltaX , deltaY);
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		ShapeContainer.forEach((object)->{
			object.draw();
			object.WriteName();
			if(object.isonClicked(mouseevent.getX(), mouseevent.getY()) && object==selectedObject) {
				object.DrawEndpoint();
			}
		});
		LineContainer.forEach((line)->{line.Draw();});
	}
	//拖曳時需秀出拖曳框及拖曳內物件須秀出端點
	private void DrawEndpointOnDrag(MouseEvent mouseevent) {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setStroke(Color.BLACK);
		double RecWidth = Math.abs(StartX-mouseevent.getX());
		double RecHeight = Math.abs(StartY-mouseevent.getY());
		if(StartX>mouseevent.getX() && StartY>mouseevent.getY()) {gc.strokeRect(StartX-RecWidth, StartY-RecHeight, RecWidth, RecHeight);}//右下啟動
		else if(StartX>mouseevent.getX() && mouseevent.getY()>StartY) {gc.strokeRect(StartX-RecWidth, StartY, RecWidth, RecHeight);}//右上啟動
		else if(mouseevent.getX()>StartX && StartY>mouseevent.getY()) {gc.strokeRect(StartX, StartY-RecHeight, RecWidth, RecHeight);}//左下啟動
		else{gc.strokeRect(StartX, StartY, RecWidth, RecHeight);}//左上啟動
		
		ShapeContainer.forEach((object)->{
			object.draw();
			object.WriteName();
				if(object.isUnderDragged(StartX, StartY, mouseevent.getX(), mouseevent.getY())) {
					object.DrawEndpoint();
				}
		});
		LineContainer.forEach((line)->{line.Draw();});
	}
	private void setDelta(MouseEvent mouseevent) {
		deltaX = mouseevent.getX()-preEvent.getX();
		deltaY = mouseevent.getY()-preEvent.getY();
	}
	 @Override
	 public void ChangeName(String NewName){
		 gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	   selectedObject.setName(NewName);
	   ShapeContainer.forEach((object)->{
			object.draw();
			object.WriteName();
		});
		LineContainer.forEach((line)->{line.Draw();});
	 }
	 @Override
	 public void Group() {
		 Vector <Shape> SelectedObjects= new Vector<Shape>();
		 	ShapeContainer.forEach((object)->{
		 		if(object.isSelected()) {
		 			SelectedObjects.add(object);
		 			
		 		}
		 	});
		 	MinStartX=SelectedObjects.get(0).getX();
		 	MinStartY=SelectedObjects.get(0).getY();
		 	SelectedObjects.forEach((object)->{
		 		ShapeContainer.remove(object);
		 		if(object.getX()<MinStartX) {
					MinStartX =object.getX(); 
				}
		 		if(object.getY()<MinStartY) {
					MinStartY =object.getY(); 
				}
		 		
		 	});
		 	ShapeContainer.add(new GroupObject(gc , SelectedObjects, MinStartX , MinStartY));
		 	//ShapeContainer.forEach((obj)->{System.out.println(obj.getX());});
		
	 }
	@Override
	public void UnGroup() {
		try {
			GroupObject groupobject = (GroupObject) selectedObject;
			ShapeContainer.addAll(groupobject.GetGroupObjects());
			ShapeContainer.remove(selectedObject);
		}
		catch(ClassCastException e) {
			System.out.println("You can't do it");
		}
		
	}
}
