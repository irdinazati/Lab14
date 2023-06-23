package serverside;

import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


import serverside.SentenceProcessor;

/**
 * An example of server-Side application using UDP
 * 
 * How to run this application
 * 1. Open terminal
 * 2. Change to directory to /workspace-dad/udpdemo/bin
 * 3. java UDPServerSideApp
 * 4. Initial output: 
 * 	UDPServerSideApp: Demonstration of UDP Server-Side Application.
 * 5. Final output: 
 * 	Message received: Good morning.
 * 
 * 
 * @author irdina izzati
 *
 */

public class UDPServerSideApp {

	public static void main(String[] args) {
		
		System.out.println("UDPServerSideApp: Demonstration of UDP Server-Side "
				+ "Application.");
		
		
		// Permissible port for this application
		int portNo = 8083;
        
        try {
        	
    		// 1. Bind a DatagramSocket's object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);
        	
            // Continually listen for request
        	while (true) {
                
                // 2. Variable to received data from the port
        		// 65535 is the maximum size for UDP packet
                byte[] receivedData = new byte[65535];
          
                // 3. Object represents packet from client
                DatagramPacket receivedPacket = 
                		new DatagramPacket(receivedData, receivedData.length);
                
                // 4. Receive packet
				datagramSocket.receive(receivedPacket);
				
				// 5. Extract data from packet
				receivedData = receivedPacket.getData();
				
				// 6. Further processing
				SentenceProcessor processor = 
						new SentenceProcessor(receivedData);
				String sentence = processor.getSentence();
				System.out.println("\nMessage received: " + sentence + ".\n");
				
				// This is not used because it give a misleading result
	            // int length = sentence.length();
	            
	            // More processing
	            int totalConsonants =  processor.countConsonants();
	            int totalVowels =  processor.countVowels();
	            int totalPunctuations =  processor.countPunctuations();
	            
	            byte[] outData1 = processor.convertToByteArray(totalConsonants);
	            byte[] outData2 = processor.convertToByteArray(totalVowels);
	            byte[] outData3 = processor.convertToByteArray(totalPunctuations);
	            
	            // 7. Get the client information
	            InetAddress clientAddress =  receivedPacket.getAddress();
	            int clientPort = receivedPacket.getPort();
	            int sizeOutData1 = outData1.length;
	            int sizeOutData2 = outData2.length;
	            int sizeOutData3 = outData3.length;
	            
	            // 8. Wrap data into datagram packet
	            DatagramPacket outPacket1 = new DatagramPacket(outData1, 
	            		sizeOutData1, clientAddress, clientPort);
	            
	            DatagramPacket outPacket2 = new DatagramPacket(outData2, 
	            		sizeOutData2, clientAddress, clientPort);
	            
	            DatagramPacket outPacket3 = new DatagramPacket(outData3, 
	            		sizeOutData3, clientAddress, clientPort);
	            
	            // 9. Reply to client
	            datagramSocket.send(outPacket1);
	            System.out.println("Message sent (totalConsonants) : " 
	            		+ totalConsonants + ".\n");
	            
	            datagramSocket.send(outPacket2);
	            System.out.println("Message sent (totalVowels) : " 
	            		+ totalVowels + ".\n");
	            
	            datagramSocket.send(outPacket3);
	            System.out.println("Message sent (totalPunctuations) : " 
	            		+ totalPunctuations + ".\n");
	            
        	}
				
		} catch (IOException e) {
				
			e.printStackTrace();
        }
        
        System.out.println("UDPClientSideApp: End of program.");
    }
}