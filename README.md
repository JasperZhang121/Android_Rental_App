**The application named "LinkHome" is a booking accommodation application that specialises in rental housing. Our application is focused towards building a strong and diverse culture among group of people by offering them rental property for their vacation mode.

**Application workflow and features:
1. If a user is new to the app, then the user needs to sign up and create an account to add items to wishlist and confirm their booking as required.
2. If the user already has an account on the application, then the user can directly sign in using the credentials.
3. User can still view the properties without logging in the app but cannot wishlist the item.
4. Post login, the user can view a range of beautiful rental properties for their stay along with their description.
5. If a user likes one or more properties, then the user has the feature to save those for future preference to the wishlist page and view it later on.
6. The user can also sort the page basis the value of rent, either from high to low or low to high.
7. The user can also filter the list of records basis their search for example: if a user wants to view all the properties in a particular city with rent less than 300, 8. then it would filter those records.
9. After filtering, the user can also sort the filter records in ascending or descending values of rent.
10. Also, if a user wants to know more information about a particular house, then by clicking on the image would navigate to the detailed page of the property.
11. The user can choose between two different payment options (MasterCard, Paypal) to confirm the booking.
12. Once the user has clicked on confirmed and if the particular property is a scam, an alert box pops up saying it is a scam, would you still like to make the payment? If a user clicks yes, then the payment is successful.

**Grammar Design:

The grammar is designed by taking into account the search criteria in our application.By making tha above mentioned grammar, it helps the user to filter records by city or rent or both. For example, the user can search by adding the below mentioned query in the search bar:
city=Canberra && rent<400
city=Canberra || rent>400
city=Canberra
rent>400
rent>300 && rent <400
rent>300 || rent<400

**Summary of Known Errors and Bugs:
1. Bug 1:
- Sometimes when the user signs up, the app crashes. This can be fixed by clearing the device cache.
- Although the app crashes, the user can still login in the application again using the new credentials.
2. Bug 2:
- If the first item is bookmarked by clicking on the heart icon, then every 6th post after the bookmark post has also a filled heart icon.
- However only the first item chosen is added to the wishlist page.

3. Bug 3:
- On the items page, when the user wants to search something such as city=Canberra then if the user writes anything instead of "city" such as "abcd", the search still happens correctly

4. Bug 4:
- If the user wants to view the app in landscape mode and orientation changes, then the elements on the screen do not resize.

5. Bug 5:
- In the payment page, the application does not check whether the user has filled in the fields or not.
- It still makes the payment and shows payment is successful.
