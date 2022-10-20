# indexOutOfBounds Report

*Here are some tips to write a good report:*

* *Try to summarise and list the `bullet points` of your project as many as possible rather than give long, tedious paragraphs that mix up everything together.*

* *Try to create `diagrams` instead of text descriptions, which are more straightforward and explanatory.*

* *Try to make your report `well structured`, which is easier for the reviewers to capture the necessary information.*

*We give instructions enclosed in square brackets [...] and examples for each sections to demonstrate what are expected for your project report.*

*Please remove the instructions or examples in `italic` in your final report.*

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Conflict Resolution Protocol](#conflict-resolution-protocol)
4. [Application Description](#application-description)
5. [Application UML](#application-uml)
6. [Application Design and Decisions](#application-design-and-decisions)
7. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
8. [Testing Summary](#testing-summary)
9. [Implemented Features](#implemented-features)
10. [Team Meetings](#team-meetings)

## Team Members and Roles

| UID |      Name      | Role |
| :--- |:--------------:| ---: |
| u7268598 | Devanshi Dhall | GUI, Login Screen, Data Structure, Singleton Design Pattern, Tokenizer and Parser, Testing, Report Writing, Firebase Authentication, Functionality: Filter a list|
| u7399886 | Avani Dhaliwal | GUI, Data Structure, State Design Pattern, Report Writing, Bookmarks and Navigation Menu, Testing, Firebase Authentication, Functionality: Filter a list|
| u7395484 | Nihar Meshram  | GUI, Facade Design Pattern, Firebase Realtime Database, Testing, UML Diagram, Functionality: Payment Process |
| u6523462 |   Hao Zhang    | GUI, Facade Design Pattern, Firebase Realtime Database, Tokenizer and Parser, Testing, UML Diagram, Functionality: Sort the items, Payment Process |

## Summary of Individual Contributions

*[Summarise the contributions made by each member to the project, e.g. code implementation, code design, UI design, report writing, etc.]*

*[Code Implementation. Which features did you implement? Which classes or methods was each member involved in? Provide an approximate proportion in percentage of the contribution of each member to the whole code implementation, e.g. 30%.]*

**u7268598, Devanshi Dhall contributed 25% of the code. Here are the contributions:**

**Code Implementation:**
* All classes in login package
* All classes in tokenizer package
* homePage/posts/Data.class: compareTo()
* stateDesignPattern/User.class: getInstance()
* homePage/payment/mastercard.class: confirm.setOnClickListener()
* homePage/payment/Paypal.class: confirm.setOnClickListener()
* homePage/payment/PaymentSuccessful.class
* homePage/DatabaseFragment.class: onCreateOptionsMenu()
* facade/PaymentMaker.class 
* homePage/bookmarks/BookmarkAdapter.class: checkBox()
* avl/AVLTree.class: Creation and Insertion 
* avl/BinarySearchTree.class 
* avl/EmptyTree.class 
* avl/Tree.class 
* AVLTreeTest.class: immutableTest(), insertInOrderTest()
* ParserTest.class
* MainActivity.class
* Graphical User Interface/ Design:
  * layout/activity_detailed_page.xml
  * layout/activity_login.xml
  * layout/bookmark_fragment.xml
  * layout/activity_payment_successful.xml
  * layout/login_tab_fragment.xml
  * layout/signup_tab_fragment.xml
  * menu/search.xml

**Code Design:** Singleton Design pattern, AVL Tree, Tokenizer and Parser

**Features:** Firebase Login Authentication

**Report:** Team members and Roles, Summary of Individual Contributions, Conflict Resolution Protocol, Application Description, Team meetings (2,3,4)


**u7399886, Avani Dhaliwal contributed 25% of the code. Here are the contributions:**

**Code Implementation:**
* All classes in stateDesignPattern package
* All classes in avl package
* login/LoginAdapter.class 
* login/LoginTabFragment.class 
* homePage/bookmarks/BookmarkFragment.class 
* homePage/posts/DatabaseFragment.class 
* homePage/posts/DetailedPage.class 
* homePage/HomePage.class 
* homePage/bookmarks/BookmarkAdapter.class 
* homePage/posts/DataAdapter.class: addDataToFirebase()
* homePage/posts/DataAdapter.class: deleteDataFromFirebase()
* homePage/posts/Data.class: compareTo()
* AVLTreeTest.class  
* MainActivity.class
* Graphical User Interface/ Design:
  * layout/activity_detailed_page.xml
  * layout/activity_home_page.xml
  * layout/activity_login.xml
  * layout/bookmark_fragment.xml
  * layout/nav_header.xml
  * menu/drawer_menu.xml
  
**Code Design:** State Design Pattern, AVL Tree

**Features:** Firebase Login Authentication

**Report:** Summary of Individual Contributions, Team meeting 1, Application Design and Decisions, Summary of Known Errors and Bugs, Implemented Features

**u6523462, Hao Zhang contributed 25% of the code. Here are the contributions:**

**Code Implementation:**
* All classes in facade package
* homePage/posts/DataAdapter.class 
* homePage/posts/Data.class 
* homePage/Mastercard.class 
* homePage/payment/Paypal.class
* homePage/posts/DatabaseFragment.class: onDataChange()
* homePage/posts/DatabaseFragment.class: onOptionsItemSelected(MenuItem item)
* ParserTest.class
* All classes in tokenizer package 
* Import and create Json File in Firebase
* Graphical User Interface/ Design:
  * activity_database.xml 
  * item.xml 
  * activity_mastercard.xml 
  * activity_paypal.xml 
  * activity_payment_page.xml 
* UML Diagram
  
**Code Design:** Facade Design Pattern, Tokenizer and Parser

**Features:** Firebase Realtime Database (Creating and Importing data from Firebase into the Data class)

**Report:** Summary of Individual Contributions, Application UML

**u7395484, Nihar Meshram contributed 25% of the code. Here are the contributions:**

**Code Implementation:**
* homePage/posts/DataAdapter.class
* homePage/posts/Data.class 
* homePage/payment/Mastercard.class 
* homePage/payment/Paypal.class 
* homePage/posts/DetailedPage: mastercard.setOnClickListener(), paypal.setOnClickListener()
* facade/payment/Mastercard.class
* facade/payment/Payment.interface
* facade/PaymentMaker.class
* facade/Paypal.class
* Import and create Json File in Firebase
* UML diagram
* Graphical User Interface/ Design:
  * activity_mastercard.xml 
  * activity_paypal.xml
  * activity_database.xml
  * item.xml
  * Data generation
  
**Code Design:** Facade Design Pattern, AVL Tree

**Features:** Firebase Realtime Database (Creating and Importing data from Firebase into the Data class)

**Report:** Summary of Individual Contributions, Application Description, Application UML


###*you should ALSO provide links to the specified classes and/or functions*


*[UI Design. Specify what design did the involved member propose? What tools were used for the design?]*

*[Slide preparation. Were you responsible for the slides?]*

*[Miscellaneous contributions. You are welcome to provide anything that you consider as a contribution to the project or team.]*

## Conflict Resolution Protocol

We as a group feel it is vital to follow some guidelines/ rules while working on a group project.
If our group met with conflicts, following are the ways we used:
1. We catered the conflict with group meetings and productive discussions 
2. Every team member would patiently listen to other members speaking
3. Post listening to all the team members, our group would discuss the pros and cons of the idea
4. We would clarify the issue as a team and identify a feasible yet optimised solution to the problem

Following is an example of Conflict Resolution Protocol in our group:

Problem: Our group initially was confused which data structure to choose for our application. 
Solution: The members of the group discussed the strengths and weaknesses of the data structure that could be used to meet the requirements in our application. 
After a healthy discussion, we decided to go ahead with self balancing tree: AVL tree

## Application Description

*[What is your application, what does it do? Include photos or diagrams if necessary]*

Description:

Our application named "LinkHome" is a booking accommodation application that specialises in rental housing. 
Our application is focused towards building a strong and diverse culture among group of people by offering them rental property for their vacation mode or any other requirement.

Application workflow and features:
1. If a user is new to the app, then the user needs to sign up and create an account to add items to wishlist and confirm their booking as required. 
2. If the user already has an account on the application, then the user can directly sign in using the credentials. 
3. User can still view thw properties without logging in the app but cannot wishlist the item.
4. Post login, the user can view a range of rental beautiful properties for their stay along with their description.
5. If a user likes one or more properties, then the user has the feature to save those for future preference to the wishlist page and view it later on.
6. The user can also sort the page basis the value of rent, either from high to low or low to high. 
7. The user can also filter the list of records basis their search for example: if a user wants to view all the properties in a particular city with rent less than 300, then it would filter those records and after filtering, the user can also sort the filter records in ascending or descending values of rent.
8. Also, if a user know more information about a particular house, then clicking on the image would navigate to the detailed page of the property. 
9. The user can choose between two different payment options (MasterCard, Paypal) to confirm the booking. 
10. Once the payment page is loaded, if the particular property is a scam, an alert box pops up saying it is a scam, would you like to still make the payment? If a user clicks yes, then payment is successful.

**Application Use Cases and or Examples**

Here is a use case of our application: LinkHome

If a user plans a vacation to any place with his/ her family members and the user's preference is to stay in a large apartment for few days with all the available facilities, 
then LinkHome helps to meet all these requirements.

*Targets Users: Those who want to find good rental property for their vacation*

* Users can use the app to stay at beautiful houses during their stay.
* Users can build a strong community by meeting people from different cultures at that place.
* Users can use the app to navigate to their wishlist items and decide later on.
* Users can see on the navigation tab if they are logged in.
* Users can filter the list basis their city and rent preference.
* Users have the option to choose which payment method they are comfortable with.

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*

## Application UML

![ClassDiagramExample](./images/ClassDiagramExample.png) <br>
*[Replace the above with a class diagram. You can look at how we have linked an image here as an example of how you can do it too.]*

## Application Design and Decisions

*Please give clear and concise descriptions for each subsections of this part. It would be better to list all the concrete items for each subsection and give no more than `5` concise, crucial reasons of your design. Here is an example for the subsection `Data Structures`:*

*I used the following data structures in my project:*

1. *LinkedList*

   * *Objective: It is used for storing xxxx for xxx feature.*

   * *Locations: line xxx in XXX.java, ..., etc.*

   * *Reasons:*

     * *It is more efficient than Arraylist for insertion with a time complexity O(1)*

     * *We don't need to access the item by index for this feature*

2. ...

3. ...

**Data Structures**

*[What data structures did your team utilise? Where and why?]*
* ** We have used the AVL tree Data Structure ** 
* ** We have used it for search function and for increasing the search efficiency **
* ** We are using the AVl tree in DatabaseFragment **

**Design Patterns**

*[What design patterns did your team utilise? Where and why?]*
* ** We have decided to use the State, Singleton, Facade Design Patterns in out project**
* ** State Design Pattern is used in the Login page **
* ** Singleton Design Pattern is used in User class for making sure that only single user is logged in for each session  **

**Grammar(s)**

Production Rules:
** <exp>    ::= <term> || <exp> | <term> && <exp> |  **
     ** <operator> ::= < < | > | = > **
     * <coefficient> ::= <unsigned integer>*
     * <letter> ::= <alphabets>*

    
    <Non-Terminal> ::= <some output>
    <Non-Terminal> ::= <some output>

*[How do you design the grammar? What are the advantages of your designs?]*

*If there are several grammars, list them all under this section and what they relate to.*

**Tokenizer and Parsers**

*[Where do you use tokenizers and parsers? How are they built? What are the advantages of the designs?]*
**the following enum defines different types of tokens. Example of accessing these: Token.Type.INT
Token.Type.INT {INT, LESS, MORE, EQUAL, AND, OR, TEXT}**

**Surprise Item**

*[If you implement the surprise item, explain how your solution addresses the surprise task. What decisions do your team make in addressing the problem?]*

**Other**

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*

*Here is an example:*

1. *Bug 1:*

- *A space bar (' ') in the sign in email will crash the application.*
- ...

2. *Bug 2:*
3. ...

*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

## Testing Summary

*[What features have you tested? What is your testing coverage?]*
**city=Canberra && rent<400"**
**"rent<300", "rent>300", "city=Sydney", "city=Melbourne"}**

*Here is an example:*

- *Number of test cases: ...*

- *Code coverage: ...*

- *Types of tests created: AVLTree test, Parser test *

*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

## Implemented Features


###Feature Category: Search-related features <br>
####Implemented features: <br>
1. Sort a list of products returned from a search based on price, popularity, rating, availability, etc.
   (easy)
   * The user can sort the posts in descending or ascending order of price
   * Package homePage/posts, Class DatabaseFragment, method onOptionsItemSelected, Lines of code: ...
   * GUI: menu.xml


2. Filter a list of products returned from a search based on their categories (e.g., kids, adults, kitchen,
   bedroom, etc.) (easy)
   * The user can filter the posts based on different cities. 
   * Package homePage/posts, Class DatabaseFragment, method onCreateOptionsMenu, filterByCity, Lines of code: ...
   * GUI: search.xml

###Feature Category: User Interactivity <br>
####Implemented features: <br>
1. The ability to micro-interact with items in your app (e.g. add to watchlist/add to cart/like an
   item/report an item/add reviews (stars)) [stored in-memory]. (medium)
    * The user can save/remove a post to/from their wishlist. 
    * Package homePage/posts, Class DataAdapter, methods: checkBox, addDataToFirebase, removeDataFromFirebase, Lines of code: ... 
    * Package homePage/bookmarks, Class BookmarkAdapter
    * Package homePage/bookmarks, Class BookmarkFragment
    * GUI: menu.xml
    
###Feature Category: Creating Processes <br>
####Implemented features: <br>
1. Payment process (fake process, do not connect to any payment provider). The user must choose
   between at least two different payment methods (e.g., credit card, cash, bitcoin). The payment
   method may add extra costs (e.g., credit card adds 1.5% to the total value). (easy)
    * The user can pay for a house using either Mastercard or PayPal. 
    * Package facade, Classes MasterCard, Payment, PaymentMaker, Paypal
    * Package homePage/payment, Classes MasterCard, PaymentPage, Paypal, PaymentSuccessful
    * Package homePage/posts, Class BookmarkAdapter
    * Package homePage/posts, Class BookmarkFragment
    * GUI: activity_mastercard.xml, activity_payment_page.xml, activity_payment_successful.xml, activity_paypal

###Feature Category: Firebase Integration <br>
####Implemented features: <br>
1. Use Firebase to implement user Authentication/Authorisation. (easy)
   * User can both login and signup through Firebase Authenticati 2on/Authorisation. 
   * Package login, Class LoginTabFragment, method loginUser, Lines of code:...
   * Package login, Class SignUpTabFragment, method createUser, Lines of Code:...
   * GUI: activity_login.xml, login_tab_fragment.xml, signup_tab_fragment.xml


2. Use Firebase to persist all data used in your app. (medium)
   * All information about user posts is stored in Firebase realtime database
   * Package homePage/posts, Class DatabaseFragment, method onCreateView, Lines of Code:...
   * Package homePage/posts, Class DataAdapter, method addDataToFirebase, deleteDataFromFirebase, Lines of Code:... 
   * Package homePage/bookmarks, Class BookmarkFragment, method onCreateView, Lines of Code:...
   * GUI: item.xml, activity_database.xml, bookmark_fragment.xml, 

## Team Meetings

- *[Team Meeting 1](./meeting1.md)*
- *[Team Meeting 2](./meeting2.md)*
- *[Team Meeting 3](./meeting3.md)*
- *[Team Meeting 4](./meeting4.md)*