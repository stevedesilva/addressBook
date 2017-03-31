## The task

Your task is to develop a small java application. We need you to build your application in
your own GitHub repository.  Please do not fork our repository to create your project.
Once you are done, send us a link to your repository.

Please allow yourself at least 1 hour of uninterrupted time for this task, but feel free
to spend as much time on the task as you like and make the solution and the code as perfect
as you like.

## The application

Your application needs to read the attached AddressBook file and answer the following questions:

1. How many males are in the address book?
2. Who is the oldest person in the address book?
3. How many days older is Bill than Paul?

## Method Used

After the relevant validation of the input and file has been performed, the file is convert into a records list.
This list is reused for each question. 

This application was built using Java 8, Maven. Tested with Junit and Mockito

IDE -  Intellij 17

## How to run the application

I have used Intellij 17 to create the executable jar file. 
Note, the Address book csv file has been added to the resource folder.

To build the jar file do the following:
1) clone or download address Book App
2) Under Maven Project Lifecycle run 'package'. It will create the jar in the /target folder.
3) Once the jar has been created, from the command line run the following command.

$ java -jar addressBook.jar <path/fileName>



Example
$ java -jar addressBook.jar /Users/stevedesilva/Documents/GumTree/addressBookApp/src/main/resources/AddressBook

E:\Workspace\addressBook\target>java -jar addressBook.jar E:\Workspace\addressBook\src\main\resources\AddressBook
Question (1) How many males are in the address book? - Answer = 3
Question (2) Who is the oldest person in the address book? - Answer = Wes Jackson
Question (3) How many days older is Bill than Paul? - Answer = 2862



==========================================================

