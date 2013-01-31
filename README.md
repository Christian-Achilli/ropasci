ropasci Rock-Paper-Scissors Game
=======
Implementation of the Game.
The application can be built using maven. Import in eclise as Existing maven project. Run maven:install.

The engine of the game is pure java 1.6 and is in the package com.chris.interview.client.ropasci. This package might be 
in a separate jar and used to deliver the game in different ways (console, mobile, desktop, etc)
The approach used for the engine is DDD and some TDD had been used for the development.
The application is delivered through a web browser: the java code is compiled usign GWT 2.5. The delivery part follows
a MVP approach. RopasciGame class is the main class and the view implemenntation. The presenter is GamePresenter class.

It is possible to play against the computer using the best put of 3 matches approach. To realize other type of match, the use case must be implemented in the 
com.chris.interview.client.ropasci.useCases folder or realize another implementation of the IMatch interface. It is likely
that also the web interface should be refactored for the new use case.
It is possible to extend the game rule modifying the ChoiceFactory and the GameEngine classes.




