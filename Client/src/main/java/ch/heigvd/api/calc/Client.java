package ch.heigvd.api.calc;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calculator client implementation
 */
public class Client {

    private static final Logger LOG = Logger.getLogger(Client.class.getName());

    /**
     * Main function to run client
     *
     * @param args no args required
     */
    public static void main(String[] args) {
        // Log output on a single line
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        BufferedReader stdin = null;
        BufferedWriter stdout = null;
        Socket clientSocket = null;

        /* TODO: Implement the client here, according to your specification
         *   The client has to do the following:
         *   - connect to the server
         *   - initialize the dialog with the server according to your specification
         *   - In a loop:
         *     - read the command from the user on stdin (already created)
         *     - send the command to the server
         *     - read the response line from the server (using BufferedReader.readLine)
         */


        try {
            clientSocket = new Socket("www.heig-vd.ch", 2021);
            stdin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            stdout = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            // Reading data using readLine
            String request = reader.readLine();
            stdout.write(request);
            stdout.flush();

            String strAnswer;
            while((strAnswer = stdin.readLine()) != null) {
                LOG.log(Level.INFO, strAnswer);
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            try {
                if (stdout != null) stdout.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }

            try {
                if (stdin != null) stdin.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }

            try {
                if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        stdin = new BufferedReader(new InputStreamReader(System.in));
    }
}
