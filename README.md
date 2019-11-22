# networking-project
Project by Géza Lőrincz, Marcus Hyttel, Martin Sørensen and Thomas Bendix

This mini project is a Chat consisting of two programs, Server and Client. 
One or more Clints connect to the server, but the server and client both have to be on the same network.

# How to use
1: The project comes in a .zip file, extract the folder and navigate to the Server folder.

2: Run the "server.bat", this should open up a terminal.

3: To start the server type in "start" and hit enter. The terminal should now display you IP adress.

4: Now clients should be able to connect. Run "client.jar". And type in the server IP (EXCLUDE ":6666") and desired username.

5: The server will confirm that a new user has connected. The client can now chat with other clients connected to the server.

6: Clients can disconnect at any time by closing down their chat window. 

7: The Server can stop running temporarily and disconnect all clients type in "stop" and hit enter. To completly stop the server type in "exit".

# Known Issues and Fixes
Issue: Machines running the Client and Server cannot connect over the AAU-1x and AAU-DAY-1 networks.
Fix: If the Client and Server are both run on the same Machine, it should work. But use another network or a mobile network

Issue: The program needs a Java runtime environment installed on the computer, and the path to this needs to be specified in the file "server.bat". If the path is incorrect here, the program will not work.
Fix: In this case, "server.bat" needs to be opened in a text editor, and the path to Java needs to be corrected.
