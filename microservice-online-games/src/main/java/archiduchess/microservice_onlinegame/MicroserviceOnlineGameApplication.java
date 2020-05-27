package archiduchess.microservice_onlinegame;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import chesspresso.pgn.PGNSyntaxError;

@SpringBootApplication
public class MicroserviceOnlineGameApplication {

	public static void main(String[] args) throws PGNSyntaxError, IOException {
		SpringApplication.run(MicroserviceOnlineGameApplication.class, args);

//		OnlineGame gameEx = new OnlineGame();
//
//		String pgnEx = "[Event \"Live Chess\"]\n[Site \"Chess.com\"]\n[Date \"2020.05.18\"]\n[Round \"-\"]\n[White \"tiou\"]\n[Black \"Frumpilot\"]\n[Result \"1-0\"]\n[ECO \"C26\"]\n[ECOUrl \"https://www.chess.com/openings/Vienna-Game-Falkbeer-Stanley-Variation\"]\n[CurrentPosition \"2r4k/6pp/p4p2/3B4/1pN1P3/1K2P3/PPP3Q1/R7 b - -\"]\n[Timezone \"UTC\"]\n[UTCDate \"2020.05.18\"]\n[UTCTime \"13:27:41\"]\n[WhiteElo \"1398\"]\n[BlackElo \"1367\"]\n[TimeControl \"180+2\"]\n[Termination \"tiou won by resignation\"]\n[StartTime \"13:27:41\"]\n[EndDate \"2020.05.18\"]\n[EndTime \"13:34:49\"]\n[Link \"https://www.chess.com/live/game/4873212344\"]\n\n1. e4 {[%clk 0:03:01.7]} 1... e5 {[%clk 0:03:01]} 2. Nc3 {[%clk 0:03:00.7]} 2... Nf6 {[%clk 0:03:02.5]} 3. Bc4 {[%clk 0:02:58.4]} 3... Be7 {[%clk 0:03:00.7]} 4. d3 {[%clk 0:02:59.4]} 4... O-O {[%clk 0:03:01]} 5. h3 {[%clk 0:02:58.7]} 5... c6 {[%clk 0:03:02]} 6. Nf3 {[%clk 0:02:59.6]} 6... d5 {[%clk 0:03:03.1]} 7. exd5 {[%clk 0:02:59.8]} 7... cxd5 {[%clk 0:03:04.3]} 8. Bb3 {[%clk 0:03:01.3]} 8... Nc6 {[%clk 0:03:02.6]} 9. O-O {[%clk 0:03:01]} 9... Be6 {[%clk 0:02:58.4]} 10. Bd2 {[%clk 0:02:54.9]} 10... Bd6 {[%clk 0:02:58.5]} 11. Re1 {[%clk 0:02:50.7]} 11... Rc8 {[%clk 0:02:57.6]} 12. Nb5 {[%clk 0:02:46.6]} 12... Bb8 {[%clk 0:02:57.5]} 13. Bc3 {[%clk 0:02:20.3]} 13... a6 {[%clk 0:02:50.3]} 14. Na3 {[%clk 0:02:14.8]} 14... Qd6 {[%clk 0:02:48.2]} 15. Nxe5 {[%clk 0:02:08]} 15... d4 {[%clk 0:02:41.9]} 16. Nxc6 {[%clk 0:01:51.1]} 16... Qh2+ {[%clk 0:02:40.4]} 17. Kf1 {[%clk 0:01:49.8]} 17... Bxh3 {[%clk 0:02:12.8]} 18. Ne7+ {[%clk 0:01:31.9]} 18... Kh8 {[%clk 0:02:10.2]} 19. gxh3 {[%clk 0:01:23]} 19... Qxh3+ {[%clk 0:02:10.5]} 20. Ke2 {[%clk 0:01:23.7]} 20... Rfe8 {[%clk 0:02:07.4]} 21. Bxd4 {[%clk 0:01:15.6]} 21... Rxe7+ {[%clk 0:02:05.8]} 22. Be3 {[%clk 0:01:16.4]} 22... Bf4 {[%clk 0:02:01.9]} 23. Kd2 {[%clk 0:01:11.6]} 23... Bxe3+ {[%clk 0:01:56.4]} 24. Rxe3 {[%clk 0:01:05.6]} 24... Rxe3 {[%clk 0:01:36.8]} 25. fxe3 {[%clk 0:01:05]} 25... Ne4+ {[%clk 0:01:31.7]} 26. dxe4 {[%clk 0:00:54.6]} 26... Rd8+ {[%clk 0:01:31.7]} 27. Bd5 {[%clk 0:00:55.7]} 27... Qg2+ {[%clk 0:01:17.7]} 28. Kc3 {[%clk 0:00:39.9]} 28... Rc8+ {[%clk 0:01:15.4]} 29. Kb3 {[%clk 0:00:35.1]} 29... b5 {[%clk 0:01:10.7]} 30. Qf1 {[%clk 0:00:20.8]} 30... b4 {[%clk 0:01:02.1]} 31. Nc4 {[%clk 0:00:20.7]} 31... f6 {[%clk 0:00:57.3]} 32. Qxg2 {[%clk 0:00:20.5]} 1-0";
//
//		String pgnEx2 = "[Event \"Live Chess\"]\n[Site \"Chess.com\"]\n[Date \"2020.05.18\"]\n[Round \"-\"]\n[White \"tiou\"]\n[Black \"Frumpilot\"]\n[Result \"1-0\"]\n[ECO \"C26\"]\n[ECOUrl \"https://www.chess.com/openings/Vienna-Game-Falkbeer-Stanley-Variation\"]\n[CurrentPosition \"2r4k/6pp/p4p2/3B4/1pN1P3/1K2P3/PPP3Q1/R7 b - -\"]\n[Timezone \"UTC\"]\n[UTCDate \"2020.05.18\"]\n[UTCTime \"13:27:41\"]\n[WhiteElo \"1398\"]\n[BlackElo \"1367\"]\n[TimeControl \"180+2\"]\n[Termination \"tiou won by resignation\"]\n[StartTime \"13:27:41\"]\n[EndDate \"2020.05.18\"]\n[EndTime \"13:34:49\"]\n[Link \"https://www.chess.com/live/game/4873212344\"]\n\n1. e4 1... e5 2. Nc3 {[%clk 0:03:00.7]} 2... Nf6 {[%clk 0:03:02.5]} 3. Bc4 {[%clk 0:02:58.4]} 3... Be7 {[%clk 0:03:00.7]} 4. d3 {[%clk 0:02:59.4]} 4... O-O {[%clk 0:03:01]} 5. h3 {[%clk 0:02:58.7]} 5... c6 {[%clk 0:03:02]} 6. Nf3 {[%clk 0:02:59.6]} 6... d5 {[%clk 0:03:03.1]} 7. exd5 {[%clk 0:02:59.8]} 7... cxd5 {[%clk 0:03:04.3]} 8. Bb3 {[%clk 0:03:01.3]} 8... Nc6 {[%clk 0:03:02.6]} 9. O-O {[%clk 0:03:01]} 9... Be6 {[%clk 0:02:58.4]} 10. Bd2 {[%clk 0:02:54.9]} 10... Bd6 {[%clk 0:02:58.5]} 11. Re1 {[%clk 0:02:50.7]} 11... Rc8 {[%clk 0:02:57.6]} 12. Nb5 {[%clk 0:02:46.6]} 12... Bb8 {[%clk 0:02:57.5]} 13. Bc3 {[%clk 0:02:20.3]} 13... a6 {[%clk 0:02:50.3]} 14. Na3 {[%clk 0:02:14.8]} 14... Qd6 {[%clk 0:02:48.2]} 15. Nxe5 {[%clk 0:02:08]} 15... d4 {[%clk 0:02:41.9]} 16. Nxc6 {[%clk 0:01:51.1]} 16... Qh2+ {[%clk 0:02:40.4]} 17. Kf1 {[%clk 0:01:49.8]} 17... Bxh3 {[%clk 0:02:12.8]} 18. Ne7+ {[%clk 0:01:31.9]} 18... Kh8 {[%clk 0:02:10.2]} 19. gxh3 {[%clk 0:01:23]} 19... Qxh3+ {[%clk 0:02:10.5]} 20. Ke2 {[%clk 0:01:23.7]} 20... Rfe8 {[%clk 0:02:07.4]} 21. Bxd4 {[%clk 0:01:15.6]} 21... Rxe7+ {[%clk 0:02:05.8]} 22. Be3 {[%clk 0:01:16.4]} 22... Bf4 {[%clk 0:02:01.9]} 23. Kd2 {[%clk 0:01:11.6]} 23... Bxe3+ {[%clk 0:01:56.4]} 24. Rxe3 {[%clk 0:01:05.6]} 24... Rxe3 {[%clk 0:01:36.8]} 25. fxe3 {[%clk 0:01:05]} 25... Ne4+ {[%clk 0:01:31.7]} 26. dxe4 {[%clk 0:00:54.6]} 26... Rd8+ {[%clk 0:01:31.7]} 27. Bd5 {[%clk 0:00:55.7]} 27... Qg2+ {[%clk 0:01:17.7]} 28. Kc3 {[%clk 0:00:39.9]} 28... Rc8+ {[%clk 0:01:15.4]} 29. Kb3 {[%clk 0:00:35.1]} 29... b5 {[%clk 0:01:10.7]} 30. Qf1 {[%clk 0:00:20.8]} 30... b4 {[%clk 0:01:02.1]} 31. Nc4 {[%clk 0:00:20.7]} 31... f6 {[%clk 0:00:57.3]} 32. Qxg2 {[%clk 0:00:20.5]} 1-0";
//
//		String pgnEx3 = removeClk(pgnEx, "{[", "]}");
//		
//		System.out.println("***********************");
//		System.out.println(pgnEx3);
//		System.out.println("***********************");
//
//		InputStream is = new ByteArrayInputStream(pgnEx3.getBytes());
//
//		PGNReader pgn = new PGNReader(is, "");
//		
//
//		String fenStr = "rnbq1rk1/pp2bppp/2p2n2/3pp3/2B1P3/2NP1N1P/PPP2PP1/R1BQK2R w KQ d6";
//
//		Game game = pgn.parseGame();
//
//		System.out.println(game);
//		System.out.println(game.getWhite() + " (" + game.getWhiteElo() + ") - " +  game.getBlack() + " (" + game.getBlackElo() + ") : " + game.getResultStr());
//		System.out.println("***********************");
//		
//		System.out.println("nb of moves : "+game.getNumOfMoves());
//		System.out.println("***********************");
//		
//		game.gotoStart();
//		while (game.hasNextMove()) {
//			System.out.println(game.getNextMove());
//			System.out.println(game.getPosition().getFEN());
//			System.out.println(game.getPosition().getFEN().contains(fenStr));
//			
//			game.goForward();
//		}
//		
//		
//		
//
//	}
//
//	public static String removeClk(String str, String start, String end) {
//
//		StringBuilder sb = new StringBuilder(str);
//		System.out.println(sb);
//
//		while (sb.toString().contains(end)) {
//			int endIndex = sb.lastIndexOf(end);
//			int startIndex = sb.lastIndexOf(start);
//
//			sb = sb.delete(startIndex, endIndex+start.length());
//			
//		}
//
//		return sb.toString();
		
	}

}
