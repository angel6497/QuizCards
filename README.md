# QuizCards

The QuizCards repository includes four classes that create a GUI program which can build and play sets of quiz cards.

The QuizCard class simply defines the quiz card object used by the other classes.

The two main classes are QuizCardBuilder and QuizCardPlayer. The first one allows the user to input answers and questions, creating a particular set of quiz cards. When the user is done the class can save the quiz card set into a .txt file that can be read by the player class. The QuizCardPlayer class lets the user load a quiz card set created by the builder class. Once the set is loaded the questions are displayed one by one, while the answers remain hidden. The user can decide to show the answer of to go directly to the next question.

Finally, the QuizCardMenu class has the main method which puts everything together. It just displays the title and two buttons. One calls the builder class and the other calls the player class. Furthermore, closing the builder and player classes will not stop the program from running, for that the menu class has to be closed.
