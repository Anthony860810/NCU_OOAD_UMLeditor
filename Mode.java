package UML_Pac.Mode;

import javafx.scene.canvas.*;
import javafx.scene.canvas.GraphicsContext;
import java.util.Vector;

import UML_Pac.Line.BasicLine;
import UML_Pac.Object.Shape;
public abstract class Mode {
	protected Canvas canvas;
	protected GraphicsContext gc;
	Shape selectedObject;
	Vector <Shape> ShapeContainer= new Vector<Shape>();
	Vector <BasicLine> LineContainer= new Vector<BasicLine>();
	Mode(){}

	public void setCanvas(Canvas canvas) {
			this.canvas = canvas;
			gc = canvas.getGraphicsContext2D();
	}
	public void SetHandler() {}
	public void RemoveHandler() {}
	public void setShapeContainer(Vector <Shape> ShapeContainer) {this.ShapeContainer = ShapeContainer;}
	public void setLineContainer(Vector <BasicLine> LineContainer) {this.LineContainer = LineContainer;}
	public void ChangeName(String Newname){}
	public void Group() {}
	public void UnGroup() {}
}
