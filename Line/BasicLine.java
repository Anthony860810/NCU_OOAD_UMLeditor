package UML_Pac.Line;
import UML_Pac.Object.Endpoint;
import UML_Pac.Object.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Transform;
public abstract class BasicLine {
	protected Endpoint StartPosition , EndPosition;
	protected Shape Object1 , Object2;
	protected int index1 , index2;
	Transform transform;
	//private double length,angle;
	protected GraphicsContext gc;
	BasicLine(Shape Object1 , int index1 , Shape Object2 , int index2 , GraphicsContext gc){
		this.Object1 = Object1;
		this.index1 = index1;
		this.Object2 = Object2;
		this.index2 = index2;
		this.gc = gc;
		Draw();
	}
	public void updateEndpoint() {
		StartPosition = Object1.getEndpointPos(index1);
		EndPosition = Object2.getEndpointPos(index2);
	}
	public void Draw() {}
}
