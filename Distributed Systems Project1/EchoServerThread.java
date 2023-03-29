import java.io.*;


class EchoServerThread implements Runnable {
   static final String endMessage = ".";
   MyStreamSocket myDataSocket;

   EchoServerThread(MyStreamSocket myDataSocket) {
      this.myDataSocket = myDataSocket;
   }

   public void run() {
      String message;
      try {
         message = myDataSocket.receiveMessage();
         System.out.println("message received: " + message);
         if (message.equals("yes")) {
            EchoServer3.incrementYesCount();
            myDataSocket.sendMessage(message);
         } else if (message.equals("no")) {
            EchoServer3.incrementNoCount();
            myDataSocket.sendMessage(message);
         } else if (message.equals("dontcare")) {
            EchoServer3.incrementdontCareCount();
            myDataSocket.sendMessage(message);
         } else if (message.equals("getCounts")) {
            int yesCount = EchoServer3.getYesCount();
            int noCount = EchoServer3.getNoCount();
            int dontCareCount = EchoServer3.getdontCareCount();
            String countsMessage = "Yes Count: " + yesCount + ", No Count: " + noCount + ", Don't Care Count: " + dontCareCount;
            myDataSocket.sendMessage(countsMessage);
         }
      } // end try
      catch (Exception ex) {
         System.out.println("Exception caught in thread: " + ex);
      } // end catch
   } // end run
} // end class