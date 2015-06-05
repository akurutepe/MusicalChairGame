//***************************************************************************************************************************************************

// Given class (needs further implementation)

public class Teacher extends Thread
{
  //=================================================================================================================================================
  private MusicalChairsGame game;

  public Teacher ( MusicalChairsGame game )
  {
    this.game = game;
  }

  //=================================================================================================================================================

  @Override
  public void run ()
  {
      double musicPlayTime;
      
      while(this.game.getNumberOfPlayers()>= 2)
      {
        System.out.println("Teacher: I am ready to play music!");
        System.out.println("Teacher: get ready");
        this.game.setGameStarted(true);
        this.game.barrierRoundBegins.await();
        System.out.println("Teacher: If every one is ready game Started! Playing something from 80s");
        this.game.barrierMusicStarts.await();
        musicPlayTime = (double) this.game.playMusicForARandomDuration(this);
        System.out.println("Teacher: music played for " + musicPlayTime/1000.0 + " seconds");
        this.game.barrierMusicStops.await();
        this.game.barrierPlayersSettle.await();
        this.game.barrierReporting.await();
        if(this.game.getNumberOfPlayers()>2)
        {
            System.out.println("Teacher: well done kids.. now next round begins...");
        }
        this.game.removeOneChair();
        this.game.reinit(this.game.getNumberOfPlayers()-1);
      }
      
      System.out.println("Teacher: game is over. lets go back to lesson");
      
          
  }

  //=================================================================================================================================================

  // main method that is going to be used during evaluation
  //
  // (If you plan to have bonus extensions like GUIs which require a differently structured main method, please inform the staff beforehand.)

  public static void main ( String[] args )
  {
    //-----------------------------------------------------------------------------------------------------------------------------------------------

    if ( args.length == 0 )  { args = new String[]{ "Student1" , "Student2" , "Student3" , "Student4" , "Student5" } ; }

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    int               numberOfStudents = args.length                               ;
    MusicalChairsGame game             = new MusicalChairsGame( numberOfStudents ) ;

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    for ( int i = 0 ; i < numberOfStudents ; i++ )  { new Student( args[i] , game ).start() ; }

    new Teacher( game ).start() ;

    //-----------------------------------------------------------------------------------------------------------------------------------------------
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************

