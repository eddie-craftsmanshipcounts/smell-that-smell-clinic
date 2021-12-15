package kata.tennis;

import kata.tennis.game1.TennisGame1;
import kata.tennis.game2.TennisGame2;
import kata.tennis.game3.TennisGame3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TennisGameTest
{
	@ParameterizedTest
	@MethodSource("allScores")
	public void checkAllScoresForTennisGame1(int player1Score, int player2Score, String expectedScore)
	{
		checkAllScores(
			new TennisGame1("player1", "player2"),
			player1Score, player2Score, expectedScore);
	}

	@Test
	public void game1RequiresPlayer1Name() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> {
			new TennisGame1(null, "player2");
		});
	}

	@Test
	public void game1RequiresPlayer2Name() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> {
			new TennisGame1("player1Name", null);
		});
	}

	@ParameterizedTest
	@MethodSource("allScores")
	public void checkAllScoresForTennisGame2(int player1Score, int player2Score, String expectedScore)
	{
		checkAllScores(
			new TennisGame2("player1", "player2"),
			player1Score, player2Score, expectedScore);
	}

	@ParameterizedTest
	@MethodSource("allScores")
	public void checkAllScoresForTennisGame3(int player1Score, int player2Score, String expectedScore)
	{
		checkAllScores(
			new TennisGame3("player1", "player2"),
			player1Score, player2Score, expectedScore);
	}

	@ParameterizedTest
	@MethodSource("allScores")
	public void checkAllScoresForTennisGame4(int player1Score, int player2Score, String expectedScore)
	{
		checkAllScores(
			new TennisGame3("player1", "player2"),
			player1Score, player2Score, expectedScore);
	}

	private static Stream<Arguments> allScores()
	{
		return Stream.of(
			arguments(0, 0, "Love-All"),
			arguments(1, 1, "Fifteen-All"),
			arguments(2, 2, "Thirty-All"),
			arguments(3, 3, "Deuce"),
			arguments(4, 4, "Deuce"),

			arguments(1, 0, "Fifteen-Love"),
			arguments(0, 1, "Love-Fifteen"),
			arguments(2, 0, "Thirty-Love"),
			arguments(0, 2, "Love-Thirty"),
			arguments(3, 0, "Forty-Love"),
			arguments(0, 3, "Love-Forty"),
			arguments(4, 0, "Win for player1"),
			arguments(0, 4, "Win for player2"),

			arguments(2, 1, "Thirty-Fifteen"),
			arguments(1, 2, "Fifteen-Thirty"),
			arguments(3, 1, "Forty-Fifteen"),
			arguments(1, 3, "Fifteen-Forty"),
			arguments(4, 1, "Win for player1"),
			arguments(1, 4, "Win for player2"),

			arguments(3, 2, "Forty-Thirty"),
			arguments(2, 3, "Thirty-Forty"),
			arguments(4, 2, "Win for player1"),
			arguments(2, 4, "Win for player2"),

			arguments(4, 3, "Advantage player1"),
			arguments(3, 4, "Advantage player2"),
			arguments(5, 4, "Advantage player1"),
			arguments(4, 5, "Advantage player2"),
			arguments(15, 14, "Advantage player1"),
			arguments(14, 15, "Advantage player2"),

			arguments(6, 4, "Win for player1"),
			arguments(4, 6, "Win for player2"),
			arguments(16, 14, "Win for player1"),
			arguments(14, 16, "Win for player2")
		);
	}

	private void checkAllScores(TennisGame game, int player1Score, int player2Score, String expectedScore)
	{
		int highestScore = Math.max(player1Score, player2Score);
		for (int i = 0; i < highestScore; i++) {
			if (i < player1Score)
				game.wonPoint("player1");
			if (i < player2Score)
				game.wonPoint("player2");
		}

		assertEquals(expectedScore, game.getScore());
	}
}
