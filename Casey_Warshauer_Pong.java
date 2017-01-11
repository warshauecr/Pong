public class PongGame extends Application {
    private Timeline timeline;
    private double x= -1;
    private double y= +1;
    private boolean eventflag;
    private int type= 0;
    private Label scoreText = new Label();
    private Button start = new Button("Serve");
    private Button newGame = new Button("New Game");
    private Group root = new Group();
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
       
        //Add padless
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
               wallPad1();
               wallPad2();
               
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
            x= 1;
            y= 1; 
          ball.setX(250);
          ball.setY(250);
          timeline.play();
        }        
        );
        
 
    }