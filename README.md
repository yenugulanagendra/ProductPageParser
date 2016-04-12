ProductPageParser Test Readme.

Java Application to parse Product Page.

1. Application has ProductParserMain page interacting with different layers.
2. Service layer
3. Service layer where business logic is performed.

Software Prerequisites
============
1. JDK 1.7 plus
2. maven 3.x
3. Firefox browser driver
4. Git


Maven Path Setting:

Unix:
 export M2_HOME=<Installation_director>
 export PATH=$PATH:$M2_HOME/bin

 Open terminal to test : mvn -version

Windows:

 Open Environment variables
   M2_HOME=<Installation_director>
   PATH =<Add_to_end_of_existing_path>;%M2_HOME%/bin

Open cmd/terminal to test : mvn -version

About Application:


1. Open terminal or cmd.
2. Make sure Java & Maven are in path.
3. mvn clean install (install all the necessary jar files in .m2 directory)
4. mvn compile
5. mvn spring-boot:run -Drun.arguments="http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?msg=&langId=44&categoryId=185749&storeId=10151&krypto=lIMmjlSSD%2FBfugUE5e%2BZ7BQ17Y%2BdwnSH1FDzpuKnH6KQztVoW%2FvROK0LPAj4Sxlbi9mYPVJ7ozApp38iU3JPXmWE2hklcjjajOUUMtE6Y6Lffq79VjeQYlmfpzu3lU5m02sUhwhv4IaQRcxZLyJEEac1W8WH2hcnf65ihjc8qwwBSxNBMlL03tdLev2YNnmZbhAaFYwjqlF7Xh0u%2FXsoeioeMPrkL5MJUYzwrqBMfzBMXEGbTcPwZev3J1AndQnlkPdbDASog7isMYdY7hHgBF%2BFu%2F3jPfPqQOF4j%2BdKRcM%2BtJMxLGgW%2BicSQKNs35C8HbrItPdsIljGTfz7e8JGyA%3D%3D#langId=44&storeId=10151&catalogId=10122&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0"

