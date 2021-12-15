package kata.tennis;

public class TennisGame1 implements TennisGame
{

	private Player player1;
	private Player player2;

	public TennisGame1(String player1Name, String player2Name)
	{
		this.player1 = new Player(player1Name);
		this.player2 = new Player(player2Name);
	}

	public void wonPoint(String playerName)
	{
		if (playerName == player1.name) {
			player1.points += 1;
		}else {
			player2.points += 1;
		}
	}

	public String getScore()
	{
		String score = "";
		if (player1.points == player2.points) {
			switch (player1.points) {
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
		} else if (player1.points >= 4 || player2.points >= 4) {
			int scoreDifference = player1.points - player2.points;
			if (scoreDifference == 1) score = "Advantage player1";
			else if (scoreDifference == -1) score = "Advantage player2";
			else if (scoreDifference >= 2) score = "Win for player1";
			else score = "Win for player2";
		} else {
			int playerPoints = 0;
			for (int player = 1; player < 3; player++) {
				if (player == 1) playerPoints = player1.points;
				else {
					score += "-";
					playerPoints = player2.points;
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