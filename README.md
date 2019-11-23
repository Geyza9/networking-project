# networking-project
Project by Géza Lőrincz, Marcus Hyttel, Martin Sørensen and Thomas Bendix

This mini project is a Chat consisting of two programs, Server and Client. 
Multiple clients can connect to the server, but the server and client(s) both have to be on the same network.

# How to use
1: The project comes in a .zip file, extract the folder and navigate to the Server folder.

2: Run the "server.bat", this should open up a terminal.

3: To start the server type in "start" and hit enter. The terminal should now display you IP address.

4: Now clients should be able to connect. Run "client.jar". And type in the server IP (EXCLUDE ":6666") and desired username.

5: The server will confirm that a new user has connected. The client can now chat with other clients connected to the server.

6: Clients can disconnect at any time by closing down their chat window. 

7: The server can talk to the clients through the console window. Type "say:" followed by the message to be delivered to clients.

8: The Server can stop running temporarily and disconnect all clients type in "stop" and hit enter. To completely stop the server type in "exit".

# Known Issues and Fixes
Issue: Machines running the Client and Server cannot connect over the AAU-1x and AAU-DAY-1 networks (and possibly other networks which operate the same way).
Fix: If the Client and Server are both run on the same Machine, it should work. Otherwise, a different network has to be used.

Issue: The program needs a Java runtime environment installed on the computer, and the path to this needs to be specified in the file "server.bat". If the path is incorrect here, the program will not work.
Fix: In this case, "server.bat" needs to be opened in a text editor, and the path to Java needs to be corrected.

Issue: If the server is using the "say:" command, whenever a client is entering a message while the server is using "say:" the server message will be printed in console too - but not to the clients. This prevents the server from editing their message, however the whole message will still be sent to the clients chatwindow once sent.

Issue: If the client types in the wrong IP address, they will be redirected to the chat screen but not the server. Typing in the chat does not work. 
Fix: The client will have to shut down the window and re-open it. 
