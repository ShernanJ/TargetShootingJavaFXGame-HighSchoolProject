//GameController for Performance Task
//Shernan Javier
//June 18,2019
//This holds all the code for the game screen, all the mechanics.

package myPT;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameController {
	
	@FXML Button target;
	@FXML Text hitCount;
	@FXML Text missedCount;
	@FXML Text timeCount;
	@FXML Text accuracyCount;
	@FXML Pane gameOverPane;
	@FXML Button gameOverContinue;
	@FXML Text timesUpText;
	
	public int buttonsClicked = 0;
	public int missed = 0;
	public int rngDirectionX;
	public int rngDirectionY;
	public int randX;
	public int randY;
	public long time;
	public boolean gameActive = true;
	public boolean gameFinished = false;
	public boolean movingRight = true;
	public boolean movingLeft = false;
	public boolean movingDown = true;
	public boolean movingUp = false;
	public String difficulty;
	long elapsedTime;
	long elapsedSeconds;
	long secondsDisplay;
	long oldSeconds;
	double accuracy;
	int totalShots = 0;
	long startTime = System.currentTimeMillis();
	public String targetColour;
	public String targetSize;
	
	String miss = "src\\media\\miss.mp3";
	Media missedSound = new Media(new File(miss).toURI().toString());
	String hit = "src\\media\\hit.mp3";
	Media hitSound = new Media(new File(hit).toURI().toString());
	
	static Stage endStage;
	
	public void start() throws InterruptedException {
		
		System.out.println(time);
		setTarget();
		gameOverPane.setVisible(false);
		target.setVisible(true);
		
		new Thread (new Runnable() {
			
			public void run() {
				try {
					for(int i=0; i <= time - 1; i++) {
						moveTarget();
						Thread.sleep(1000);
						calculateTime();
						timeCount.setText(String.valueOf(secondsDisplay));
						if(i == time - 1) {
							gameActive = false;
							gameFinished = true;
							gameOverPane.setVisible(true);
							target.setVisible(false);
							System.out.println("Finished");
						}
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	
	public void calculateTime() {
		//https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java/14323134
		elapsedTime = System.currentTimeMillis() - startTime;
		elapsedSeconds = elapsedTime / 1000;
		secondsDisplay = elapsedSeconds % 60;
	}
	
	public void getOptions(String getDifficulty, String getSize, int getTime, String getColour) {
		difficulty = getDifficulty;
		time = getTime;
		targetSize = getSize;
		targetColour = getColour;
		System.out.println(time);
	}
	
	public void moveTarget() throws InterruptedException {
		
		System.out.println("Left: " + movingLeft);
		System.out.println("Right: "+movingRight);
		switch(difficulty) {
		case "Medium":
			rngDirectionX = (int)(Math.random() * 200)+ 1;
			rngDirectionY = (int)(Math.random() * 200)+ 1;
			System.out.println(rngDirectionX);
			if(rngDirectionX > 100) {
				movingRight = true;
				movingLeft = false;
			}
			if(rngDirectionX < 100) {
				movingLeft = true;
				movingRight = false;
			}
			
			if(movingRight) {
				if(target.getLayoutX() < 960) {
					int x = 40;
					target.setLayoutX(target.getLayoutX() + x);
				}
			}
			if(movingLeft) {
				if(target.getLayoutX() > 120) {
					int x = 40;
					target.setLayoutX(target.getLayoutX() - x);
				}
			}
		break;
		case "Hard":
			randX = (int)(Math.random() * 200)+ 1;
			if(rngDirectionX > 100) {
				movingRight = true;
				movingLeft = false;
			}
			if(rngDirectionX < 100) {
				movingLeft = true;
				movingRight = false;
			}
			if(movingRight) {
				if(target.getLayoutX() < 700) {
					randX = (int)(Math.random() * 150) + 50;
					target.setLayoutX(target.getLayoutX() + randX);
				}
			}
			if(movingLeft) {
				if(target.getLayoutX() > 300) {
					randX = (int)(Math.random() * 150) + 50;
					target.setLayoutX(target.getLayoutX() - randX);
				}
			}
			
			if(movingUp) {
				if(target.getLayoutY() < 400) {
					randY = (int)(Math.random() * 150) + 50;
					target.setLayoutY(target.getLayoutY() + randY);
				}
			}
			if(movingDown) {
				if(target.getLayoutY() > 200) {
					randY = (int)(Math.random() * 150) + 50;
					target.setLayoutY(target.getLayoutY() - randY);
				}
			}
			if(rngDirectionY > 100) {
				movingDown = true;
				movingUp = false;
			}
			if(rngDirectionY < 100) {
				movingUp = true;
				movingDown = false;
			}
		break;
		}
	}
	
	public void setTarget() {
		
		switch(targetSize) {
		case "Small":
			target.setPrefSize(20, 20);
		break;
		case "Medium":
			target.setPrefSize(40, 40);
		break;
		case "Large":
			target.setPrefSize(60, 60);
		break;
		}
		
		switch(targetColour) {
		case "red":
			target.getStyleClass().add("red");
		break;
		case "blue":
			target.getStyleClass().add("blue");
		break;
		case "yellow":
			target.getStyleClass().add("yellow");
		break;
		case "green":
			target.getStyleClass().add("green");
		break;
		case "orange":
			target.getStyleClass().add("orange");
		break;
		}
	}
	
	public void buttonClicked(ActionEvent evt) {
		if(gameActive) {
			playHitSound();
			buttonsClicked++;
			hitCount.setText(String.valueOf(buttonsClicked));
			System.out.println("buttons clicked: " + buttonsClicked);
			rng();
			accuracyCalculate();
			target.setLayoutX(randX);
			target.setLayoutY(randY);
		}
	}
	
	public void missed(ActionEvent evt) {
		if(gameActive) {
			playMissSound();
			missed++;
			missedCount.setText(String.valueOf(missed));
			accuracyCalculate();
			System.out.println("missed: " + missed);
			if(missed == 3) {
				gameActive = false;
				System.out.println("game over");
				openEndScreen();
			}
		}
	}
	
	public void accuracyCalculate() {
		totalShots = buttonsClicked + missed;
		accuracy = (double)buttonsClicked/totalShots;
		System.out.println(totalShots);
		accuracyCount.setText(String.valueOf(Math.round((accuracy* 100) * 100.0) / 100.0));
		
	}
	
	public void rng() {
		randX = (int) (Math.random() * 800) + 50;
		randY = (int) (Math.random() * 500) + 50;
		
	}
	
	public void playMissSound() {
		MediaPlayer missSound = new MediaPlayer(missedSound);
		missSound.play();
	}
	
	public void playHitSound() {
		MediaPlayer hittingSound = new MediaPlayer(hitSound);
		hittingSound.play();
	}
	
	public void openEndScreen() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScreen.fxml"));
			BorderPane endWindow = (BorderPane)loader.load();
			Scene endScene = new Scene(endWindow,400,400);
			endScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			EndScreenController controller = loader.getController();
			controller.getStats(buttonsClicked, missed, totalShots, accuracy);
			
			endStage = new Stage();
			endStage.setScene(endScene);
			endStage.setResizable(false);
			endStage.showAndWait();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 
}
