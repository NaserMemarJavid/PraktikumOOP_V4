package gui.guiFreizeitbaeder;

import business.FreizeitbaederModel;
import ownUtil.*;

import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class FreizeitbaederView implements Observer{
	
	private FreizeitbaederControl freizeitbaederControl;
	private FreizeitbaederModel freizeitbaederModel;
	
	// Anfang Attribute der grafischen Oberflaeche 
		private Pane pane     				= new  Pane();
		
	    private Label lblEingabe    	 	= new Label("Eingabe");
	    private Label lblAnzeige   	 	    = new Label("Anzeige");
	    private Label lblName 				= new Label("Name:");
	    private Label lblGeoeffnetVon   	= new Label("Geöffnet von:");
	    private Label lblGeoeffnetBis  	 	= new Label("Geöffnet bis:");
	    private Label lblBeckenlaenge   	= new Label("Beckenlänge:");
	    private Label lblWassTemperatur  	= new Label("Wassertemperatur:");
	    private TextField txtName 	 		= new TextField();
	    private TextField txtGeoeffnetVon	= new TextField();
	    private TextField txtGeoeffnetBis	= new TextField();
	    private TextField txtBeckenlaenge	= new TextField();
	    private TextField txtWassTemperatur	= new TextField();
	    private TextArea txtAnzeige  		= new TextArea();
	    private Button btnEingabe 		 	= new Button("Eingabe");
	    private Button btnAnzeige 		 	= new Button("Anzeige");
	    private MenuBar mnbrMenuLeiste  	= new MenuBar();
	    private Menu mnDatei             	= new Menu("Datei");
	    private MenuItem mnItmCsvExport 	= new MenuItem("csv-Export");
	    private MenuItem mnItmTxtExport 	= new MenuItem("txt-Export");
	    //-------------------------------------------------------------------
	    
	// Konstruktur
	public FreizeitbaederView (FreizeitbaederControl freizeitbaederControl,
			Stage primaryStage, FreizeitbaederModel frezeitbaederModel){
		
		Scene scene = new Scene(this.pane, 560, 340);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Verwaltung von Freizeitbädern");
		primaryStage.show();

		this.freizeitbaederControl = freizeitbaederControl;
		this.freizeitbaederModel = frezeitbaederModel;
		// Add dieses Object zu  Observer liste
		this.freizeitbaederModel.addObserver(this);
		
		this.initKomponenten();
		this.initListener();
	}

	private void initKomponenten() {
		// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(310);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblName.setLayoutX(20);
    	lblName.setLayoutY(90);
    	lblGeoeffnetVon.setLayoutX(20);
    	lblGeoeffnetVon.setLayoutY(130);
    	lblGeoeffnetBis.setLayoutX(20);
    	lblGeoeffnetBis.setLayoutY(170);
    	lblBeckenlaenge.setLayoutX(20);
    	lblBeckenlaenge.setLayoutY(210);
    	lblWassTemperatur.setLayoutX(20);
    	lblWassTemperatur.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblName, lblGeoeffnetVon, lblGeoeffnetBis,
       		lblBeckenlaenge, lblWassTemperatur);
       	// Textfelder
     	txtName.setLayoutX(130);
    	txtName.setLayoutY(90);
    	txtGeoeffnetVon.setLayoutX(130);
    	txtGeoeffnetVon.setLayoutY(130);
    	txtGeoeffnetBis.setLayoutX(130);
    	txtGeoeffnetBis.setLayoutY(170);
      	txtBeckenlaenge.setLayoutX(130);
    	txtBeckenlaenge.setLayoutY(210);
    	txtWassTemperatur.setLayoutX(130);
    	txtWassTemperatur.setLayoutY(250);
     	pane.getChildren().addAll( 
     		txtName, txtGeoeffnetVon, txtGeoeffnetBis,
     		txtBeckenlaenge, txtWassTemperatur);
     	// Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(310);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(220);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	// Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(310);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        // Menu
   	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvExport);
  	    this.mnDatei.getItems().add(mnItmTxtExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
		
	}	

	private void initListener() {
		btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	
            	nehmeFreizeitbadAuf(
            			txtName.getText(), 
           	            txtGeoeffnetVon.getText(),
           	            txtGeoeffnetBis.getText(),
            		    txtBeckenlaenge.getText(),
            		    txtWassTemperatur.getText()
            	);
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            //txtAnzeige.setText( zeigeFreizeitbadAn() );
	        } 
   	    });
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent e) {
	    		schreibeFreizeitbaederInDatei("csv");
	    	}
	    });
	    mnItmTxtExport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent e) {
	    		schreibeFreizeitbaederInDatei("txt");
	    	}
	    });
		
	}
	
	

	private void nehmeFreizeitbadAuf(String name, String von, String bis, String laenge, String temp) {
		this.freizeitbaederControl.nehmeFreizeitbadAuf(name,von,bis,laenge,temp);
	}
	
	private String zeigeFreizeitbadAn() {
		return this.freizeitbaederControl.zeigeFreizeitbadAn();
	}

	public void zeigeInformationsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.INFORMATION,
	    		"Information", meldung).zeigeMeldungsfensterAn();		
	}

	public void zeigeFehlermeldungsfensterAn(String fehlertyp, String meldung) {
		new MeldungsfensterAnzeiger(AlertType.ERROR,
	        	fehlertyp + "Fehler", meldung).zeigeMeldungsfensterAn();		
	}
	//-----------------------------------------------------

	
	private void schreibeFreizeitbaederInDatei(String typ) {
		this.freizeitbaederControl.schreibeFreizeitbaederInDatei(typ);
	}

	public void zeigeFehlermeldungAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.ERROR,
	    		"!!ACHTUNG!!", meldung).zeigeMeldungsfensterAn();
		
	}

	// Implementation des Interface-Observers
	@Override
	public void update() {
		txtAnzeige.setText( zeigeFreizeitbadAn() );
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

	

