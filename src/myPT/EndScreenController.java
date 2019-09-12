//EndScreenController
//Shernan Javier
//June 18, 2019
//This holds all the fuctionalities for the end screen.

package myPT;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class EndScreenController {
	
	@FXML Text endTargetsHit;
	@FXML Text endTargetsMissed;
	@FXML Text endTotalTargets;
	@FXML Text endAccuracy;
	
	public void getStats(double shotsHit, double shotsMissed, double totalShots, double accuracy) {
		endTargetsHit.setText(String.valueOf((int)shotsHit));
		endTargetsMissed.setText(String.valueOf((int)shotsMissed));
		endTotalTargets.setText(String.valueOf((int)totalShots));
		endAccuracy.setText(String.valueOf(Math.round((accuracy* 100) * 100.0) / 100.0));
	}
	
	public void playAgain() {
		GameController.endStage.close();
		MainMenuController.gameStage.close();
	}
	
	public void quit() {
		GameController.endStage.close();
		MainMenuController.gameStage.close();
		Platform.exit();
	}

}
