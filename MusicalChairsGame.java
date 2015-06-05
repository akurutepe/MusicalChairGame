
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//***************************************************************************************************************************************************

// Given class (needs further implementation)

public class MusicalChairsGame
{
  //=================================================================================================================================================

  // Given physical entities

  private List<Chair> chairs               = new ArrayList<>();
  private int         numberOfPlayers      ;
  private boolean     musicPlaying         ;
  private boolean     gameStarted          ;  

  // Suggested logical entities

  public Barrier      barrierRoundBegins   = new Barrier(0);
  public Barrier      barrierMusicStarts   = new Barrier(0)   ;
  public Barrier      barrierMusicStops   = new Barrier(0)    ;
  public Barrier      barrierPlayersSettle   = new Barrier(0) ;
  public Barrier      barrierReporting   = new Barrier(0)     ;

  // ...

  //=================================================================================================================================================

  public MusicalChairsGame ( int numberOfPlayers )
  {
    this.numberOfPlayers = numberOfPlayers;
    this.musicPlaying = false;
    for(int i = 0; i<this.numberOfPlayers-1;i++)
    {
        chairs.add(new Chair(i));
    }
    this.barrierRoundBegins.reset(numberOfPlayers);
    this.barrierMusicStarts.reset(numberOfPlayers);
    this.barrierMusicStops.reset(numberOfPlayers);
    this.barrierPlayersSettle.reset(numberOfPlayers);
    this.barrierReporting.reset(numberOfPlayers);
    
    this.gameStarted = false;
  }
  
  public synchronized void reinit ( int numberOfPlayers )
  {
    this.numberOfPlayers = numberOfPlayers;
    this.musicPlaying = false;

    this.barrierRoundBegins.reset(numberOfPlayers);
    this.barrierMusicStarts.reset(numberOfPlayers);
    this.barrierMusicStops.reset(numberOfPlayers);
    this.barrierPlayersSettle.reset(numberOfPlayers);
    this.barrierReporting.reset(numberOfPlayers);
    
    this.gameStarted = false;
  }

  //=================================================================================================================================================

  // Suggested service

  int getNumberOfPlayers ()
  {
    return this.numberOfPlayers;
  }

  //=================================================================================================================================================

  // Suggested service

  synchronized int playMusicForARandomDuration (Teacher teacher)
  {
      if(!this.isMusicPlaying())
      {
          this.musicPlaying = true;
      }
      Random rand = new Random();

      int  sleepTime = rand.nextInt(9000) + 1000;
      
      try{
        teacher.sleep(sleepTime);
      }
      catch(InterruptedException e){
          
      }
      if(this.isMusicPlaying())
      {
          this.musicPlaying = false;
      }
      
      return sleepTime;
      
  }

  //=================================================================================================================================================

  // Suggested service

  synchronized int sitDownOnAnAvailableChair ( )
  {
      for(int i=0; i<chairs.size();i++)
      {
          if(chairs.get(i).isAvailable()){
              chairs.get(i).setAvailable(false);
              return chairs.get(i).getID();
          }  
      }
      
      return 999;
  }

  //=================================================================================================================================================

  // Suggested service

   synchronized void standUpFromChair ( int chairID)
  {
      chairs.get(chairID).setAvailable(true);
  }

  //=================================================================================================================================================

  // Suggested service

  synchronized void removeOneChair ()
  {
      chairs.remove(chairs.size()-1);
  }

  //=================================================================================================================================================

    public   boolean isMusicPlaying() {
        return musicPlaying;
    }

    public    synchronized  boolean isGameStarted() {
        return gameStarted;
    }

    public    synchronized  void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }
  
  
}

//***************************************************************************************************************************************************

