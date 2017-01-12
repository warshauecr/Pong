import javafx.animation.Animation;
	import javafx.animation.KeyFrame;
	import javafx.animation.Timeline;
	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.stage.Stage;
	import javafx.scene.input.KeyEvent;
	import javafx.scene.layout.Pane;
	import javafx.scene.shape.Rectangle;
	import javafx.util.Duration;
	import javafx.scene.shape.Line;
	

	

	public class PongGame extends Application {
	    
	    private Timeline timeline;
	    private double x= -1;
	    private double y= +1;
	    private boolean eventflag;
	    private int type= 0;
	    private Label scoreText = new Label();
	    private Button start = new Button("Serve");
	    private Button newGame = new Button("New Game");
	    private Pane root = new Pane();
	    private int player1Score = 0;
	    private int player2Score = 0;   
	        
	    private Rectangle player1 = new Rectangle(20, 215, 5, 40);
	    private Rectangle player2 = new Rectangle(430, 215, 5, 40);
	    private Rectangle wall1 = new Rectangle(0, 0, 5, 445);
	    private Rectangle wall2 = new Rectangle(450, 0, 5, 445);
	    private Rectangle wall3 = new Rectangle(5, 440, 445, 5);
	    private Rectangle wall4 = new Rectangle(5, 0, 445, 5);
	    private Line line4 = new Line(225, 0, 225, 444);  
	    private Rectangle ball = new Rectangle(150, 150, 10, 10);
	    
	    @Override
	    public void start(Stage primaryStage) {
	       
	        //Add components to root
	        root.getChildren().addAll(player1, player2, ball, wall1, wall2, wall3, wall4, line4, start, scoreText, newGame);
	        start.setTranslateX(160);
	        start.setTranslateY(460);
	        newGame.setTranslateX(210);
	        newGame.setTranslateY(460);       
	        scoreText.setTranslateX(130);
	        scoreText.setTranslateY(500);
	        line4.getStrokeDashArray().addAll(2d);
	        Scene scene = new Scene(root, 445, 550);
	        primaryStage.setResizable(false);
	        
	        
	        //Build KeyFrame 
	        Duration sec = new Duration(10);
	        KeyFrame keyFrame = new KeyFrame(sec, event ->
	        {
	               Score();
	               Padels();
	               wallMethodPad1();
	               wallMethodPad2();
	               
	               ball.setX(ball.getX() + x);
	               ball.setY(ball.getY()+ y);
	        
	        
	        }
	        );
	        
	        //Build the time line animation.
	        timeline = new Timeline(keyFrame);
	        timeline.setCycleCount(Animation.INDEFINITE);
	         timeline.play();
	      
	       
	        //Set stage
	        primaryStage.setTitle("Pong!");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	        
	        
	        
	        //Add event handler to keyboard
	        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
	            
	            double deltaY = 0 , deltaY2 = 0;
	            
	            switch(e.getCode()) {
	                case UP: 
	                    deltaY = -35 ;
	                    break ;
	                case DOWN:
	                    deltaY = 35 ;
	                    break ;
	                case W:
	                    deltaY2 = -35 ;
	                    break ;
	                case S:
	                    deltaY2 = 35 ;
	                    break ;
	             
	            }
	            player2.setY(player2.getY() + deltaY);
	            player1.setY(player1.getY() + deltaY2);
	           
	        });
	        
	        
	                
	        
	        
	        
	   
	        //add action handler to new game button
	        newGame.setOnAction (
	        event ->
	        {
	          player1Score = 0;
	          player2Score = 0;
	          scoreText.setText("");
	        scoreText.setTranslateX(130);
	        scoreText.setTranslateY(500);
	        }
	        );
	        
	        //add event handler to start button
	        start.setOnAction(
	        event ->
	        {
	            x= 0.8;
	            y= 0.8;
	          ball.setX(250);
	          ball.setY(200);
	          timeline.play();
	        }        
	        );
	        
	 
	    }
	    
	   /*
	    Check for collisions with player’s pads and
	    decides which direction the ball should travel
	    */ 
	   private void Padels()
	   {
	        if(ball.intersects(player1.getBoundsInLocal()))
	                  {
	                        if( player1.getY() > 217)
	                    {
	                       x= +1 + Math.random()*3;
	                       y= +1;
	                       
	                       eventflag = false;
	                    }
	                    else 
	                    {
	                        x= +1 + Math.random()*3;
	                        y = -1;
	                        
	                         eventflag = true;
	                    }
	                
	                       type =0;   
	                  }  
	         else if (ball.intersects(player2.getBoundsInLocal()))
	            {
	                
	                    if(player2.getY() > 217)
	                    {
	                       x= -1 - Math.random()*3; 
	                       y= +1;
	                     
	                       eventflag=false;
	                    }
	                    else
	                    {
	                        x= -1 - Math.random()*3; 
	                        y = -1;
	                        
	                        eventflag=true;
	                    }
	                     type =1;
	            }
	   }
	   
	

	    
	   /* 
	   Check for collisions with players 1 and walls 3 and 4, 
	   and decides which direction the ball should travel
	   */
	    private void wallMethodPad1()
	        {
	          if (ball.intersects(wall3.getBoundsInParent()) && type == 0)
	          {
	             
	                if (eventflag == false)
	                  
	                    {
	                        x=+1;
	                        y=-1;
	                    }
	           
	          }
	          else if (ball.intersects(wall4.getBoundsInParent()) && type == 0)
	          {
	                
	              if (eventflag == true)
	                  
	              {
	                  x= +1;
	                  y= +1;
	              }
	            
	        }
	        }
	    
	    /* 
	   Check for collisions with players 2 and walls 3 and 4, 
	   and decides which direction the ball should travel
	   */
	    private void wallMethodPad2()
	        {
	          if (ball.intersects(wall3.getBoundsInParent()) && type == 1)
	          {
	            
	                if (eventflag == false)
	                   
	                    {
	                        x=-1;
	                        y=-1;
	                    }
	            
	          }
	          else if (ball.intersects(wall4.getBoundsInParent()) && type == 1)
	          {
	              
	              if (eventflag == true)
	              {
	                  x= -1;
	                  y= 1;
	              }
	        }
	        }
	    
	    /*
	    Checks to see if player has scored
	    */
	    private void Score()
	    {
	        if (ball.intersects(wall1.getBoundsInParent()) || ball.intersects(wall2.getBoundsInParent()))
	            {
	                timeline.stop();
	                
	            }
	        
	       if (ball.intersects(wall2.getBoundsInParent()))
	          {
	              
	           player1Score++;
	             
	          }
	          else if (ball.intersects(wall1.getBoundsInParent()))
	          {
	             player2Score++;
	           
	              
	    }
	       scoreText.setText("Player 1 score: " + player1Score + " | Player 2 score: " + player2Score);
	       Winner();
	    }
	    
	  /* Checks to see if either player
	   has won.
	 */    
	    
	private void Winner()
	{
	    if(player1Score == 10)
	    {
	        scoreText.setText("Player 1 wins!");
	        scoreText.setTranslateX(200);
	    }
	    else if(player2Score == 10)
	    {
	        scoreText.setText("Player 2 wins!");
	        scoreText.setTranslateX(200);
	    }
	    
	}
	   
	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	        launch(args);
	    }
	    
	}

