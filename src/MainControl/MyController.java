package MainControl;
import ChessModel.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class MyController implements Initializable{
	public int ChessColor=-1;//黑子
	public Chess [][] ChessStatus=new Chess [13][13];
	@FXML
	private Pane Root;
	@FXML
	private Canvas draw;
	@FXML
	protected void handleCanvasClicked(MouseEvent e) {
		GraphicsContext gc=draw.getGraphicsContext2D();
	
		int point_x=(int)e.getX();
		int point_y=(int)e.getY();
		point_x=(point_x-20);
		point_y=(point_y-20);
		//System.out.println(point_x+" "+point_y);
		int status_x = 0;
		int status_y = 0;
		if(point_x>=0 && point_x<=560 && point_y>=0 && point_y <= 560){
			for(int i=0;i<13;i++) {
				if(point_x>(i+1)*40-20&&point_x<(i+1)*40+20) {
					status_x=i;
				}
				if(point_y>(i+1)*40-20&&point_y<(i+1)*40+20) {
					status_y=i;
				}
			}
		//System.out.println(status_x+" "+status_y);
			if(ChessStatus[status_x][status_y]==null||ChessStatus[status_x][status_y].isPlaced()==false) {
			Chess chess =new Chess (ChessColor,true);
			ChessStatus[status_x][status_y]=chess;
			System.out.println("chess color:"+ChessColor);
			if(ChessColor==-1)
			{
				gc.setFill(Color.BLACK);
				gc.fillArc((status_x+1)*40+5, (status_y+1)*40+5, 30, 30, 0, 360, ArcType.ROUND);
				ChessStatus[status_x][status_y].setPlaced(true);
				ChessColor =0;
				//System.out.println("chess color:"+ChessColor);
			}else {
				gc.setFill(Color.WHITE);
				gc.fillArc((status_x+1)*40+5, (status_y+1)*40+5, 30, 30, 0, 360, ArcType.ROUND);
				ChessStatus[status_x][status_y].setPlaced(true);
				ChessColor = -1;
			}
				if(IsWin(status_x, status_y)){
				System.out.println("WIN!!!!!");
				String winner;
				//如果下一子是白色的，那么此次为黑方
				if(ChessColor == 0)
					winner = "黑方";
				else
					winner = "白方";
				Stage stage=new Stage();
				stage.setScene(new Scene(new Button(winner+"赢"),100,100));
				stage.show();
				}
			}
		}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	//判断输赢
	public boolean IsWin(int xIndex, int yIndex) {
		    int count=0;
			int max = 0;
			int tempXIndex = xIndex;
			int tempYIndex = yIndex;
			boolean flag;
			// 三维数组记录横向，纵向，左斜，右斜的移动
			int[][][] dir = new int[][][] {
					// 横向
					{ { -1, 0 }, { 1, 0 } },
					// 竖着
					{ { 0, -1 }, { 0, 1 } },
					// 左斜
					{ { -1, -1 }, { 1, 1 } },
					// 右斜
					{ { 1, -1 }, { -1, 1 } } };

			for (int i = 0; i < 4; i++) {
				count = 1;
	        //j为0,1分别为棋子的两边方向，比如对于横向的时候，j=0,表示下棋位子的左边，j=1的时候表示右边
				for (int j = 0; j < 2; j++) {
					flag = true;
	        /**
	        while语句中为一直向某一个方向遍历
	                     有相同颜色的棋子的时候，Count++
	                     否则置flag为false，结束该该方向的遍历
	        **/
	        while (flag) {
				tempXIndex = tempXIndex + dir[i][j][0];
				tempYIndex = tempYIndex + dir[i][j][1];
	            if (ChessStatus[tempXIndex][tempYIndex]!=null&&ChessStatus[tempXIndex][tempYIndex].getColor() == ChessStatus[xIndex][yIndex].getColor()) {
					count++;
					System.out.println(count);
				} else
					flag = false;
				}
	                tempXIndex = xIndex;
					tempYIndex = yIndex;
				}

				if (count == 5) {
					max = 1;
					break;
				} else
					max = 0;
			}
	            if (max == 1)
				return true;
			else
				return false;
		}
  
}


