ProductPageParser Test Readme.

Java Application to parse Product Page.

1. Application has ProductParserMain page interacting with different layers.
2. Facades / Service layer
3. Service layer where business logic is performed.

Software Requirement to run the application:

1. Any Java version 1.7+
2. Maven


Maven Path Setting:

Unix:
 export M2_HOME=<Installation_director>
 export PATH=$PATH:$M2_HOME/bin

 Open terminal to test : mvn -version

Windows:

 Open Environment variables
   M2_HOME=<Installation_director>
   PATH =<Add_to_end_of_existing_path>;%M2_HOME%/bin

 Open cmd to test : mvn -version

About Application:


1. Open terminal or cmd.
2. Make sure Java & Maven are in path.
3. mvn clean install (install all the necessary jar files in .m2 directory)
4. mvn compile
5. mvn exec:java -Dexec.mainClass="uk.co.sainsbury.test.main.ProductParserMain"

