//Main Menu Controller
//Shernan Javier
//June 18, 2019
//This Holds all the functionalities for the main menu.

package myPT;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuController {
	
	static Stage gameStage;
	
	public String difficultyArray[] = new String [3];
	public String timeArray[] = new String [3];
	public String sizeArray[] = new String [3];
	public String color = "red";
	public String size = "Medium";
	public String difficulty = "Easy";
	
	public int difficultyIndex = 0;
	public int sizeIndex = 1;
	public int timeIndex = 1;
	public int time = 15;
	
	@FXML Text difficultyText;
	@FXML Text timeText;
	@FXML Text sizeText;
	@FXML Text colorText;
	
	@FXML Button difficultyLeft;
	@FXML Button difficultyRight;
	@FXML Button timeLeft;
	@FXML Button timeRight;
	@FXML Button sizeLeft;
	@FXML Button sizeRight;
	
	
	public void startGame() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
			BorderPane gameWindow = (BorderPane)loader.load();
			Scene gameScene = new Scene(gameWindow,1000,780);
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			GameController controller = loader.getController();
			controller.getOptions(difficulty, size, time, color);
			controller.moveTarget();
			controller.start();
			
			gameStage = new Stage();
			gameStage.setScene(gameScene);
			gameStage.setResizable(false);
			gameStage.showAndWait();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void difficultySelect(ActionEvent evt) {
		
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText(); 
		difficultyArray[0] = "Easy";
		difficultyArray[1] = "Medium";
		difficultyArray[2] = "Hard";
		
		if(buttonLabel.equals(difficultyLeft.getText()) && difficultyIndex != 0) {
			System.out.println("left");
			difficultyIndex--;
		}
		if(buttonLabel.equals(difficultyRight.getText()) && difficultyIndex != 2) {
			System.out.println("right");
			difficultyIndex++;
		}
		
		difficultyText.setText(difficultyArray[difficultyIndex]);
		difficulty = difficultyArray[difficultyIndex];
	}
	
	public void timeSelect(ActionEvent evt) {
			
			Button clickedButton = (Button) evt.getTarget();
			String buttonLabel = clickedButton.getText(); 
			timeArray[0] = "5";
			timeArray[1] = "15";
			timeArray[2] = "30";
			
			if(buttonLabel.equals(timeLeft.getText()) && timeIndex != 0) {
				timeIndex--;
			}
			if(buttonLabel.equals(timeRight.getText()) && timeIndex != 2) {
				timeIndex++;
			}
			
			time = Integer.parseInt(timeArray[timeIndex]);
			timeText.setText(timeArray[timeIndex]);
		
	}
	
	public void sizeSelect(ActionEvent evt) {
		
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText(); 
		sizeArray[0] = "Small";
		sizeArray[1] = "Medium";
		sizeArray[2] = "Large";
		
		if(buttonLabel.equals(sizeLeft.getText()) && sizeIndex != 0) {
			sizeIndex--;
		}
		if(buttonLabel.equals(sizeRight.getText()) && sizeIndex != 2) {
			sizeIndex++;
		}
		
		sizeText.setText(sizeArray[sizeIndex]);
		size = sizeArray[sizeIndex];
	
	}
	
	public void colorSelect(ActionEvent evt) {
		
		
		Button clickedButton = (Button) evt.getSource();
		String buttonColor = clickedButton.getText();
		
		switch(buttonColor) {
		case "red":
			System.out.println("RED");
			colorText.setText("Red");
			color = "red";
		break;
		case "blue":
			System.out.println("BLUE");
			colorText.setText("Blue");
			color = "blue";
		break;
		case "yellow":
			System.out.println("YELLOW");
			colorText.setText("Yellow");
			color = "yellow";
		break;
		case "orange":
			System.out.println("ORANGE");
			colorText.setText("Orange");
			color = "orange";
		break;
		case "green":
			System.out.println("GREEN");
			colorText.setText("Green");
			color = "green";
		break;
		}
	
	}

}
