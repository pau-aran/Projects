import java.net.*;

public class EchoServer3 {

   private static int yesCount = 0;
   private static int noCount = 0;
   private static int dontCareCount = 0;

   public static void main(String[] args) {
      int serverPort = 13; // default port

      if (args.length == 1)
         serverPort = Integer.parseInt(args[0]);
      try {
         // instantiates a stream socket for accepting
         // connections
         ServerSocket myConnectionSocket = new ServerSocket(serverPort);
         /**/ System.out.println("Echo server ready.");
         while (true) {
            // forever loop
            // wait to accept a connection
            /**/ System.out.println("Waiting for a connection.");
            MyStreamSocket myDataSocket = new MyStreamSocket(myConnectionSocket.accept());
            /**/ System.out.println("connection accepted");
            // Start a thread to handle this client's sesson
            Thread theThread = new Thread(new EchoServerThread(myDataSocket));
            theThread.start();
            // and go on to the next client
         } // end while forever
      } // end try
      catch (Exception ex) {
         ex.printStackTrace();
      } // end catch
   } // end main

   public static synchronized void incrementYesCount() {
      yesCount++;
      System.out.println("Yes Count: " + yesCount);
      System.out.println("No Count: " + noCount);
      System.out.println("Don't Care Count: " + dontCareCount);
   }

   public static synchronized void incrementNoCount() {
      noCount++;
      System.out.println("Yes Count: " + yesCount);
      System.out.println("No Count: " + noCount);
      System.out.println("Don't Care Count: " + dontCareCount);
   }

   public static synchronized void incrementdontCareCount() {
      dontCareCount++;
      System.out.println("Yes Count: " + yesCount);
      System.out.println("No Count: " + noCount);
      System.out.println("Don't Care Count: " + dontCareCount);
   }

   public static synchronized int getYesCount() {
      return yesCount;
   }

   public static synchronized int getNoCount() {
      return noCount;
   }

   public static synchronized int getdontCareCount() {
      return dontCareCount;
   }

} // end class
