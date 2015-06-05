
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************

// Given class (needs further implementation)

public class Student extends Thread
{
  //=================================================================================================================================================

  // Given entities

  private String name ;
  private MusicalChairsGame game;
  private int heldChairID;
  private boolean playing;

  //=================================================================================================================================================

  public Student ( String name , MusicalChairsGame game )
  {
    this.name = name;
    this.game = game;
    this.playing = true;
  }

  //=================================================================================================================================================

  @Override
  public void run ()
  {
    while(!this.game.isGameStarted()){}
    
    while(this.playing)
    {
        System.out.println(name +": Yay!");
        this.game.barrierRoundBegins.await();
     
        this.game.barrierMusicStarts.await();
      
        this.game.barrierMusicStops.await();
        this.heldChairID = this.game.sitDownOnAnAvailableChair();
        System.out.println(name +": ah, uh trying to sit...");
        this.game.barrierPlayersSettle.await();
        if(this.heldChairID == 999)
        {
            System.out.println(name +": uhuhu i couldnt sit down because of those cheaters!");
            this.playing = false;
            
                
        }
        else{
             System.out.println(name +": ahahaha i managed to sit with chair ID" + this.heldChairID);
             this.game.standUpFromChair(heldChairID);
        }
        this.game.barrierReporting.await();
        while(this.game.isGameStarted()){}
     }
    
  }

  //=================================================================================================================================================

    public boolean isPlaying() {
        return playing;
    }
}

//***************************************************************************************************************************************************

