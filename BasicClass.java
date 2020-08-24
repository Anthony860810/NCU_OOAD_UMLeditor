package UML_Pac.Object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

public class BasicClass extends Shape{
	public BasicClass(double MouseX , double MouseY , GraphicsContext gc){
		super(MouseX , MouseY , gc);
		draw();
	}
	@Override
	public void draw() {
		gc.setStroke(Color.PURPLE);
		gc.strokeRect(getX(), getY(), ObjectWidth, ObjectHeight/3);
		gc.strokeRect(getX(), getY()+30, ObjectWidth, ObjectHeight/3);
		gc.strokeRect(getX(), getY()+60, ObjectWidth, ObjectHeight/3);
	}
}
