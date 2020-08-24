package UML_Pac;

import java.util.Optional;
import java.util.Vector;

import UML_Pac.Line.BasicLine;
import UML_Pac.Mode.AssociationLineMode;
import UML_Pac.Mode.CompositionLineMode;
import UML_Pac.Mode.CreateClassMode;
import UML_Pac.Mode.CreateObjectMode;
import UML_Pac.Mode.GeneralizationLineMode;
import UML_Pac.Mode.Mode;
import UML_Pac.Mode.SelectMode;
import UML_Pac.Object.Shape;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;


public class Main extends Application {
	Mode premode;
	Vector <Shape> ShapeContainer = new Vector<Shape>();
	Vector <BasicLine> LineContainer= new Vector<BasicLine>();
	@Override
    public void start(Stage stage) {
    	//Button Setting
    	ToggleButton BtnSelect = new ToggleButton();// Select Object Button
        BtnSelect.setStyle("-fx-base: lightblue;");
        BtnSelect.setMinSize(100,50);
        BtnSelect.setText("Select");
        ToggleButton BtnAssociationLine = new ToggleButton();// Create Association Line Button
        BtnAssociationLine.setStyle("-fx-base: lightblue;");
        BtnAssociationLine.setMinSize(100,50);
        BtnAssociationLine.setText("Association\n\tLine");
        ToggleButton BtnGeneralizationLine = new ToggleButton();// Create Generalization Line Button
        BtnGeneralizationLine.setStyle("-fx-base: lightblue;");
        BtnGeneralizationLine.setMinSize(100,50);
        BtnGeneralizationLine.setText("Generalization\n\tLine");
        ToggleButton BtnCompositionLine = new ToggleButton();// Create Composition Line Button
        BtnCompositionLine.setStyle("-fx-base: lightblue;");
        BtnCompositionLine.setMinSize(100,50);
        BtnCompositionLine.setText("Composition\n\tLine");
        ToggleButton BtnCreateObject = new ToggleButton(); // CreateOval Object Button
        BtnCreateObject.setStyle("-fx-base: lightblue;");
        BtnCreateObject.setMinSize(100,50);
        BtnCreateObject.setText("Object");
        ToggleButton BtnCreateClass = new ToggleButton();// CreateClass Object Button
        BtnCreateClass.setStyle("-fx-base: lightblue;");
        BtnCreateClass.setMinSize(100,50);
        BtnCreateClass.setText("Class");
        
        //Generate Buttons of ToggleGroup
        ToggleGroup ToggleGroup = new ToggleGroup();
        BtnSelect.setToggleGroup(ToggleGroup);
        BtnAssociationLine.setToggleGroup(ToggleGroup);
        BtnGeneralizationLine.setToggleGroup(ToggleGroup);
        BtnCompositionLine.setToggleGroup(ToggleGroup);
        BtnCreateObject.setToggleGroup(ToggleGroup);
        BtnCreateClass.setToggleGroup(ToggleGroup);
        
        //VBox Setting
        VBox vbox = new VBox();
        vbox.getChildren().addAll(BtnSelect,BtnAssociationLine,BtnGeneralizationLine,BtnCompositionLine,BtnCreateClass,BtnCreateObject);
        vbox.setStyle("-fx-border-color: black;");
        vbox.setLayoutX(0);
        vbox.setLayoutY(100);
        
        //Canvas Setting
        Canvas canvas = new Canvas(900,600); // Width is 900px and Height is 600px
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        //MenuBar Setting
        MenuBar menuBar = new MenuBar();
        Menu File = new Menu("File");
        Menu Edit = new Menu("Edit");
        MenuItem Rename = new MenuItem("Rename");
        MenuItem Group = new MenuItem("Group");
        MenuItem UnGroup = new MenuItem("UnGroup");
        Edit.getItems().addAll(Rename , Group , UnGroup);
        menuBar.getMenus().addAll(File,Edit);
        
        //Pane Setting
        BorderPane root = new BorderPane();
        root.setLeft(vbox);//vbox
        root.setCenter(canvas);
        root.setTop(menuBar);
        Scene scene = new Scene(root,800,400);
        
        //Stage Setting
    	stage.setWidth(1000);
    	stage.setHeight(600);
    	stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("UML Editor");
        stage.show();
        
        
        AssociationLineMode associationMode = new AssociationLineMode(); 
        GeneralizationLineMode generalizationMode = new GeneralizationLineMode(); 
        CompositionLineMode compositionMode = new CompositionLineMode();
        SelectMode selectMode = new SelectMode();
        CreateClassMode ClassMode = new CreateClassMode();
        CreateObjectMode ObjectMode = new CreateObjectMode(); 
        
		Rename.setOnAction(BtnClick->rename());
		Group.setOnAction(BtnClick->{
			premode.Group();
		});
		UnGroup.setOnAction(Btn->{premode.UnGroup();});
        BtnAssociationLine.setOnAction(BtnClick->{
        	//System.out.println("click");
        	associationMode.setCanvas(canvas);
        	associationMode.setShapeContainer(ShapeContainer);
        	associationMode.setLineContainer(LineContainer);
        	if(premode!=null)
        		premode.RemoveHandler();
        	associationMode.SetHandler();
        	premode = associationMode;
        });
        BtnGeneralizationLine.setOnAction(BtnClick->{
        	//System.out.println("click");
        	generalizationMode.setCanvas(canvas);
        	generalizationMode.setShapeContainer(ShapeContainer);
        	generalizationMode.setLineContainer(LineContainer);
        	if(premode!=null)
        		premode.RemoveHandler();
        	generalizationMode.SetHandler();
        	premode = generalizationMode;
        });
        BtnCompositionLine.setOnAction(BtnClick->{
        	//System.out.println("click");
        	compositionMode.setCanvas(canvas);
        	compositionMode.setShapeContainer(ShapeContainer);
        	compositionMode.setLineContainer(LineContainer);
        	if(premode!=null)
        		premode.RemoveHandler();
        	compositionMode.SetHandler();
        	premode = compositionMode;
        });
        BtnSelect.setOnAction(BtnClick->{
        	selectMode.setCanvas(canvas);
        	selectMode.setShapeContainer(ShapeContainer);
        	selectMode.setLineContainer(LineContainer);
        	if(premode!=null)
        		premode.RemoveHandler();
        	selectMode.SetHandler();
        	premode = selectMode;
        }); 
        BtnCreateClass.setOnAction(BtnClick->{
        	ClassMode.setCanvas(canvas);
        	ClassMode.setShapeContainer(ShapeContainer);
        	ClassMode.setLineContainer(LineContainer);
        	if(premode!=null)
        		premode.RemoveHandler();
        	ClassMode.SetHandler();
        	premode = ClassMode;
        });
        BtnCreateObject.setOnAction(BtnClick->{
        	ObjectMode.setCanvas(canvas);
        	ObjectMode.setShapeContainer(ShapeContainer);
        	ObjectMode.setLineContainer(LineContainer);
        	if(premode!=null)
        		premode.RemoveHandler();
        	ObjectMode.SetHandler();
        	premode = ObjectMode;
        });

    }
	private void rename() {
		TextInputDialog textInputDialog = new TextInputDialog(); // 實體化TextInputDialog物件，並直接在建構子設定預設的文字內容。由於輸入一定是字串，所以對話框會直接回傳String物件，而不使用泛型
		textInputDialog.setTitle("Rename"); //設定對話框視窗的標題列文字
		textInputDialog.setHeaderText("Type new name"); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
		textInputDialog.setContentText("Name: "); //設定對話框的訊息文字
		Optional<String> opt = textInputDialog.showAndWait();
		if(opt.get()!=null) {
			premode.ChangeName(opt.get());
		}
		
	}
}

