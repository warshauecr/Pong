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
/**
 *
 * @author Casey Warshauer
 
 Two player pong game
 **Player 1 controls**
 W- Up
 S- Down
 
  **Player 2 controls**
  Up Arrow - Up
  Down Arrow - Down
 
 */
public class PongGame extends Application {
	    
	    private Timeline timeline;
	    private double x;
	    private double y;
            private final double RANDOM;
	    private boolean eventflag;
	    private int type;
	    private Label scoreText;
	    private Button start;
	    private Button newGame;
            private Scene scene; 
	    private Pane root;
	    private int player1Score;
	    private int player2Score;   
	      
	    private Rectangle player1;
	    private Rectangle player2;
	    private Rectangle wall1;
	    private Rectangle wall2;
	    private Rectangle wall3;
	    private Rectangle wall4;
	    private Line line4;  
	    private Rectangle ball;
	    
            /*
             Initializes all variables need for game
            */
            public PongGame()
            {
                x= -1;
                y= +1;
                RANDOM = 3;
                type= 0;
                scoreText = new Label();
                start = new Button("Serve");
                newGame = new Button("New Game");
                root = new Pane();
                player1Score = 0;
                player2Score = 0;
                scene = new Scene(root, 445, 550);
                player1 = new Rectangle(20, 215, 5, 40);
                player2 = new Rectangle(430, 215, 5, 40);
                wall1 = new Rectangle(0, 0, 5, 445);
	        wall2 = new Rectangle(450, 0, 5, 445);
	        wall3 = new Rectangle(5, 440, 445, 5);
	        wall4 = new Rectangle(5, 0, 445, 5);
	        line4 = new Line(225, 0, 225, 444);  
	        ball = new Rectangle(150, 150, 10, 10);
            }
		
	    @Override
	    public void start(Stage primaryStage) 
            {
        
               //Add components
	       createPieces();
             
	       //Build KeyFrame 
	       buildKeyFrame();
               
               //Build stage
               buildStage(primaryStage);
	      
               //Create Event Handlers
               buildEventHandlers();
                         
	    }
            
            /*
            Assembles game.
            */
            private void createPieces()
            {
                root.getChildren().addAll(player1, player2, ball, 
                wall1, wall2, wall3, wall4, line4, start, scoreText, newGame);
	        start.setTranslateX(160);
	        start.setTranslateY(460);
	        newGame.setTranslateX(210);
	        newGame.setTranslateY(460);       
	        scoreText.setTranslateX(130);
	        scoreText.setTranslateY(500);
	        line4.getStrokeDashArray().addAll(2d);
            }
             /*
             Builds stage
            */
            
            private void buildStage(Stage primaryStage)
            {
                //Don't allow application to be resized
                primaryStage.setResizable(false);
                 //Set stage
	        primaryStage.setTitle("Pong!");
	        primaryStage.setScene(scene);
	        primaryStage.show();
            }
            
            /*
            Builds animation timeline
            */
            private void buildKeyFrame()
            {
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
	        
	        //Builds timeline animation.
	        timeline = new Timeline(keyFrame);
	        timeline.setCycleCount(Animation.INDEFINITE);
	        timeline.play();
            }
            
	   /*
	     Create event handlers
	   */
            private void buildEventHandlers()
            {
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
	        
	        
	        //Add action handler to new game button
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
	        
	        //Add event handler to start button
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
	                       x= +1 + Math.random()*RANDOM;
	                       y= +1;
	                       
	                       eventflag = false;
	                    }
	                    else 
	                    {
	                        x= +1 + Math.random()*RANDOM;
	                        y = -1;
	                        
	                         eventflag = true;
	                    }
	                
	                       type =0;   
	                  }  
	         else if (ball.intersects(player2.getBoundsInLocal()))
	            {
	                
	                    if(player2.getY() > 217)
	                    {
	                       x= -1 - Math.random()*RANDOM; 
	                       y= +1;
	                     
	                       eventflag=false;
	                    }
	                    else
	                    {
	                        x= -1 - Math.random()*RANDOM; 
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


