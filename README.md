# Chess_JAVA

Chess Game using Java Socket Programming README

Welcome to the Chess Game project using Java Socket Programming! This project allows you to play chess with another player over a network. Below you'll find instructions on how to set it up and use it.

### How to Run
Clone the Repository: Start by cloning this repository to your local machine.

    git clone <repository-url>
Navigate to the Directory: Enter into the directory of the cloned repository.

    cd chess-game-java-socket

#### If your IDE is EclipseIDE --> Right click on the project and select Run as Java Application option.

#### If any other IDE --> Simply run the ChessController.java class file.

## How to play
1. Play in one device
       - Run the application as mentioned above.
       - Run it again.
       - It will display two chess board screens.
       - One will be the server so click the listen button on the chess board screen.
       - One will be the client so click on the Connect button on the screen.
       - If the connection is success it will display a dialogue box.
       - Then move the chess pieces to play.
       
3. Play in two devices
       - Setup the same application code in another device with same instructions.
       - In both devices go to the "ChessController.java" class and type the IP address of one device on another in the variable SOCKET_SERVER_ADDR instead of "localhost".
       - for example SOCKET_SERVER_ADDR = "xxx.xx.xx.xx".
       - Then run on one device and then run on another device.
       - After displaying the board click listen button from the server and to, connect button from the client.
       - Then start to move chess pieces to play.

### Technologies used
+ Java: The primary programming language used for implementing the game logic and networking functionality.
+ Java Swing: For the UI purpose.
+ Socket Programming: Used to establish communication between the server and clients over a network.

![Screenshot (34)](https://github.com/Madhunicka/Chess_JAVA/assets/77634975/257fe5e3-1fa6-4b62-af6c-57d047e32c71)

![Screenshot (37)](https://github.com/Madhunicka/Chess_JAVA/assets/77634975/177df61d-6b81-49bf-8dea-eac9611680de)



