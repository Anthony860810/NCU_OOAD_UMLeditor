package UML_Pac.Line;

import UML_Pac.Object.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GeneralizationLine extends BasicLine{
	public GeneralizationLine(Shape Object1 , int index1 , Shape Object2 , int index2 , GraphicsContext gc) {
		super(Object1 , index1 , Object2 , index2 , gc );
		Draw();
	}
	@Override
	public void Draw() {
		updateEndpoint();
		gc.setLineWidth(2);
		gc.setStroke(Color.VIOLET);
        gc.strokeLine(StartPosition.getX(), StartPosition.getY(), EndPosition.getX(), EndPosition.getY());
        double distance = Math.sqrt(Math.pow(StartPosition.getX()-EndPosition.getX(), 2)+Math.pow(StartPosition.getY()-EndPosition.getY(), 2));
        double len=8;
        double NormaldistanceX = len*(EndPosition.getX()-StartPosition.getX())/distance;
        double NormaldistanceY = len*(EndPosition.getY()-StartPosition.getY())/distance;
        double ExtendPoint1X = EndPosition.getX()-NormaldistanceX - NormaldistanceY;
        double ExtendPoint1Y = EndPosition.getY()-NormaldistanceY+NormaldistanceX;
        double ExtendPoint2X = EndPosition.getX()-NormaldistanceX + NormaldistanceY;
        double ExtendPoint2Y = EndPosition.getY()-NormaldistanceY-NormaldistanceX;
		gc.strokePolygon(new double[] {EndPosition.getX(),ExtendPoint1X,ExtendPoint2X}
		, new double[] {EndPosition.getY(),ExtendPoint1Y,ExtendPoint2Y}, 3);
	}
}
