package kata.tennis.game1;

import kata.tennis.TennisGame;

public class TennisGame1 implements TennisGame
{

	private int player1Points = 0;
	private int player2Points = 0;
	private String player1Name;
	private String player2Name;

	public TennisGame1(String player1Name, String player2Name)
	{
		this.player1Name = player1Name;
		this.player2Name = player2Name;
	}

	public void wonPoint(String playerName)
	{
		if (playerName == "player1")
			player1Points += 1;
		else
			player2Points += 1;
	}

	public String getScore()
	{
		String score = "";
		if (player1Points == player2Points) {
			switch (player1Points) {
				case 0:
					score = "Love-All";
					break;
				case 1:
					score = "Fifteen-All";
					break;
				case 2:
					score = "Thirty-All";
					break;
				default:
					score = "Deuce";
					break;

			}
		} else if (player1Points >= 4 || player2Points >= 4) {
			int scoreDifference = player1Points - player2Points;
			if (scoreDifference == 1) score = "Advantage player1";
			else if (scoreDifference == -1) score = "Advantage player2";
			else if (scoreDifference >= 2) score = "Win for player1";
			else score = "Win for player2";
		} else {
			int playerPoints = 0;
			for (int player = 1; player < 3; player++) {
				if (player == 1) playerPoints = player1Points;
				else {
					score += "-";
					playerPoints = player2Points;
				}
				switch (playerPoints) {
					case 0:
						score += "Love";
						break;
					case 1:
						score += "Fifteen";
						break;
					case 2:
						score += "Thirty";
						break;
					case 3:
						score += "Forty";
						break;
				}
			}
		}
		return score;
	}
}