package UML_Pac.Object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.util.Vector;

public abstract class Shape {
	//draw pen
	GraphicsContext gc;
	//Object Size
	protected double ObjectWidth=120;
	protected double ObjectHeight=90;
	//Endpoint Size
	protected double EndpointSize=10;
	//Object name
	protected String name=null;
	//Coordinate of Mouse
	protected double MouseX;
	protected double MouseY;
	
	//EndPoint of 4 places
	private Vector<Endpoint> EndpointContainer= new Vector<Endpoint>();
	//Select or not
	boolean selected;
	//Depth
		public int Depth=0;
	//EndpointClick
	protected int  EndpointOnClick;
	
	Shape(double MouseX ,double MouseY , GraphicsContext gc){
		this.MouseX = MouseX;
		this.MouseY = MouseY;
		this.gc = gc;
		this.EndpointContainer = setEndpoint();
		selected = false;
	}
	public double getX() {
		return MouseX;
	}
	public double getY() {
		return MouseY;
	}
	public Endpoint getEndpointPos(int index) {
		return EndpointContainer.get(index);
	}
	public void draw(){}
	public boolean isSelected() {return selected;}
	public boolean isConnectable() {return true;}
	public boolean CanLink() {return true;}
	public int getPortOnClick(){return EndpointOnClick;}
	public void setName(String Name) {this.name = Name;}
	public void WriteName() {
		 gc.setFill(Color.RED);
		 gc.setFont(new Font("Times New Roman" , 20));
		 gc.setTextAlign(TextAlignment.CENTER);
		 gc.fillText(name , MouseX+ObjectWidth/2 , MouseY+ObjectWidth/2);
	 }
	//�T�{�ƹ��O�_�I���b����
	public boolean isonClicked(double MouseX , double MouseY) {
		//Check Click place in some object inside
		if( (this.MouseX <= MouseX) && (MouseX <= this.MouseX+ObjectWidth) 
				&& (this.MouseY <= MouseY) && (MouseY <= this.MouseY+ObjectHeight)) {
			double temp = Distance(MouseX , MouseY , EndpointContainer.get(0));
			EndpointOnClick = 0;
			// find closest Endpoint with Mouse click coordinate 
			for(int index=0 ; index<EndpointContainer.size() ; index++) {
			if(temp > Distance(MouseX , MouseY , EndpointContainer.get(index))) {
					temp = Distance(MouseX , MouseY , EndpointContainer.get(index));
					EndpointOnClick = index;
				}
			}
			selected = true;
			return true;
		}
		selected = false;
		return false;
	}
	//�P�w����O�_���b�즲�ؤ�
	public boolean isUnderDragged(double StartX , double StartY , double AfterX, double AfterY) {
		if((this.MouseX >= StartX)&&(AfterX >= this.MouseX+ObjectWidth) //���W��k�U
			&& (this.MouseY >= StartY) && (AfterY >= this.MouseY+ObjectHeight)) {
			selected=true;
			return true;
		}
		else if((StartX >= this.MouseX+ObjectWidth) &&(this.MouseX >= AfterX) && //�k�U�쥪�W
				(StartY >= this.MouseY+ObjectHeight)&& (this.MouseY >= AfterY) ) {
			selected=true;
			return true;
		}
		else if((StartX>=this.MouseX+ObjectWidth) &&(this.MouseX >= AfterX)		//�k�W�쥪�U
				&& (this.MouseY >= StartY) && (AfterY >= this.MouseY+ObjectHeight)) {
			selected=true;
			return true;
		}
		else if((this.MouseX >= StartX)&&(AfterX >= this.MouseX+ObjectWidth) &&//���U��k�W
				(StartY >= this.MouseY+ObjectHeight)&& (this.MouseY >= AfterY)) {
			selected=true;
			return true;
		}
		selected=false;
		return false;
	}
	//Draw endpoint on Clicked Object
	public void DrawEndpoint() {
		gc.setFill(Color.BLACK);
		EndpointContainer.forEach(Obj->{
			gc.fillRect(Obj.getX()-5, Obj.getY()-5, EndpointSize, EndpointSize);
		});
	}
	public void ResetPosition(double deltaX , double deltaY) {
		this.MouseX += deltaX;
		this.MouseY += deltaY;
		this.EndpointContainer = setEndpoint();
	}
	private double Distance(double MouseX , double MouseY , Endpoint point) {
		return Math.sqrt(Math.pow(MouseX-point.getX(), 2)+Math.pow(MouseY-point.getY(), 2));
	}
	private Vector<Endpoint> setEndpoint(){
		Vector<Endpoint> Container= new Vector<Endpoint>();
		Container.add(new Endpoint(	MouseX+ObjectWidth/2 , MouseY));
		Container.add(new Endpoint(	MouseX , MouseY+ObjectHeight/2));
		Container.add(new Endpoint(	MouseX+ObjectWidth , MouseY+ObjectHeight/2));
		Container.add(new Endpoint(	MouseX+ObjectWidth/2 , MouseY+ObjectHeight));
		return Container;
	};
		
}
