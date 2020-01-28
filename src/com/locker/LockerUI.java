package com.locker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LockerUI  extends Application{

	
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Locker Application");
        
        Label l_password = new Label("State your password");
        TextField l_password_text = new TextField();
        
        Button btn = new Button();
        btn.setText("Login");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        GridPane root = new GridPane();
        
        root.add(l_password,0,0);
        root.add(l_password_text,1,0);
        root.add(btn,1,1);
        root.setMinSize(400, 200); 
        root.setPadding(new Insets(10, 10, 10, 10)); 
        root.setVgap(5); 
        root.setHgap(5);       
        
        //Setting the Grid alignment 
        root.setAlignment(Pos.CENTER); 
        
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
	  
	

}
