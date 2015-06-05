//***************************************************************************************************************************************************

// Given class (needs further implementation)

public class Barrier
{
  //=================================================================================================================================================
    
  private Object monitor;
  
  // Given entities

  private int numberOfThreadsCurrentlyWaiting    ;
  private int numberOfThreadsToReachBarrierPoint ;
  private boolean broken;

  //=================================================================================================================================================

  public Barrier ( int numberOfThreadsToReachBarrierPoint )
  {
    this.numberOfThreadsCurrentlyWaiting = 0;
    this.numberOfThreadsToReachBarrierPoint = numberOfThreadsToReachBarrierPoint;
    this.broken = false;
    monitor = new Object();
  }

  //=================================================================================================================================================

  void await ()
  { 
    synchronized(monitor)
    {
        if(this.numberOfThreadsCurrentlyWaiting < this.numberOfThreadsToReachBarrierPoint){
            this.numberOfThreadsCurrentlyWaiting++;
                try{
                    monitor.wait();
                }catch (InterruptedException e) {

                }
        } else {
 
                monitor.notifyAll();
                this.broken = true;
        }
    }
  }
  
  void reset(int numberOfThreadsToReachBarrierPoint)
  {
     this.numberOfThreadsCurrentlyWaiting = 0; 
     this.broken = false;
     this.numberOfThreadsToReachBarrierPoint = numberOfThreadsToReachBarrierPoint;
  }
  
  

  //=================================================================================================================================================

    public boolean isBroken() {
        return broken;
    }
}

//***************************************************************************************************************************************************

