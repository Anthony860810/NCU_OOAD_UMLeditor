package UML_Pac.Object;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

public class BasicObject extends Shape{
	public BasicObject(double MouseX , double MouseY , GraphicsContext gc){
		super(MouseX , MouseY , gc);
		draw();
	}
	
	@Override
	public void draw() {
		gc.setFill(Color.DARKCYAN); 
		gc.fillOval(getX(), getY(), ObjectWidth, ObjectHeight);
	}
}