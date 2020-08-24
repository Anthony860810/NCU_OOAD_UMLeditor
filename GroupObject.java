package UML_Pac.Object;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Vector;
public class GroupObject extends Shape{
	private boolean onclick;
	private double MaxX , MaxY;
	double x;
	private Vector<Shape> SelectedObjects;
	public GroupObject(GraphicsContext gc ,Vector<Shape> SelectedObjects, double x, double y) {// 
		super(x,y,gc);//x,y, 
		this.SelectedObjects = SelectedObjects;	
	}
	@Override
	public void draw() {
		gc.setFill(Color.DARKBLUE);
		SelectedObjects.forEach((object)->{object.draw();});
	}
	@Override
	public void WriteName() {
		SelectedObjects.forEach((object)->{object.WriteName();});
	}
	@Override
	public void ResetPosition(double deltaX , double deltaY) {
		SelectedObjects.forEach((object)->{object.ResetPosition(deltaX, deltaY);});
	}
	@Override
	public void DrawEndpoint() {
		SelectedObjects.forEach((object)->{object.DrawEndpoint();});
	}
	@Override
	public boolean isonClicked(double MouseX , double MouseY) {
		onclick=false;
		SelectedObjects.forEach((object)->{
			if(object.isonClicked(MouseX, MouseY)) {
				onclick=true;
			}
		});
		if(onclick) {
			selected = true;
			return true;
		}
		selected = false;
		return false;
	}
	@Override
	public boolean isConnectable(){return false;}
	@Override
	public boolean CanLink() {return false;}
	public Vector<Shape> GetGroupObjects(){
		return SelectedObjects;
	}
	private void GetMax() {
		MaxX = SelectedObjects.get(0).getX();
		MaxY = SelectedObjects.get(0).getY();
		SelectedObjects.forEach((object)->{
			if(object.getX() > MaxX) {
				MaxX = object.getX();
			}
			if(object.getY() > MaxY) {
				MaxY = object.getY();
			}
		});
	}
}
