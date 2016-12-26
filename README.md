# card-arranger
This is completely related to card game.This code for now only arranges the provided cards in the given following rules. 
Rule 1: 
Name :Thirial  
Alias:Trial 
Rule condition: The provided group of card has 3 identical number . 
Eg:Ace of spade,Ace of club,Ace of Heart 
 
Rule 2: 
Name:Daab run 
Rule condition:The provided group of card has 3 consecutive number from the same card group. 
Eg:Ace of club,Two of club,Three of club 
 
Rule 3: 
Name:Run 
Rule condition:The provided group of card has 3 consecutive number from any of the group. 
Eg:Ace of spade,Two of club,Three of heart 
 
Rule 4: 
Name:Falash 
Rule condition:The provided group of card has any 3 card from the same group of card. 
Eg:Five of club,Eight of club,King of club 
 
 
Rule 5: 
Name:Jute 
Rule condition:The provided group of card has any two identical card number. 
Eg:Four of diamond,Four of heart,Ace of spade 
 
Rule 6: 
Name:Sort 
Rule condition:The provided group of card arranged in descending order. 
Eg:Ace of club,Five of diamond,Two of heart 
 
 
The every group selected from above groups will be arranged according to  descending order .In above rule rule 1 is the highest. 
 
A single group from every rule consist of 3 cards or even more if rule is matched except the rule 'sort' which may or may not have 3 cards. 
 
 
This solution is a single solution from different possibilities that can be formed from the combination of provided cards. 
 
 
 
These six rules can be used to determine list of cards on which group does it fall. 

##
All cards that are won by the user are saved in won cards. 
A card that is odd in a single shuffle is stored in odd cards. 
 
Show class allows to play only one round. 
For multiple round shuffle is created 
The shuffle count is then sent to show class. 
Show class keeps records for the shuffle count and gets the result from player object. Player object can store the shuffle count . Every shuffle creates a new round. 
 
The new count is determined for shuffle count.player object also contains points obj that counts the amount of the card value and saves.every shuffle or the after  new round is created the won cards are saved to the PlayerDict object. 
 
Then getpoint method of the player object loops through the PlayerDict won cards and determine the point of the won cards. 
 
Now all these process repeats until a single user reaches 1000 or greater than the limit number. 
 
----------- 
 
All 52 cards are created by the cardconst class a static method.each card has id,name,number and card group.card group is a enum consisting of all the card groups. 
 
Shuffler is a method that shuffles the given collection of cards . 
Card division is the method that divides the collection of the cards into the number of the player provided .also number of cards to the single player can  be determined. 
 
Then all the six rule can be applied to these collection of single player cards and get the arranged card collection. 
 
---------- 
 
Every player is then looped through their round separated cards.the arranged card is then compared. 
 
In the comparison cases... 
 
Every six rules has different comparing strategy some method maybe common though. 
 
The result is returned after the comparison determining which of the given two collection of the cards is greater or smaller. 
If the two collection of cards have same card with the same number then ,a certain condition is applied .it's 
 
1.select the first of the player who has passed the cards. 
2.select the second of the player who replaces the first player. 
 
 
In comparison cases first it is determined in which rule the arranged group falls .the result is then compared. 
If they fall on same group then the rule compare is used for analyzing same group of cards. 
 
While analyzing same group of cards, 
They are compared to the six rules.in this case,the card that falls on the top of the returned result is considered the greater. 
 
So while comparing the collection of card first they are checked by their rule number.lower the number returned greater the collection of cards. 
Secondly they are checked if the cards are identical that is if the collections of cards have the same numbers to process. 
 
Third,the collection is sent to the six rules comparing techniques and get the result. 
 
All three comparing technique returns same identical value determining the collection of cards which is greater or smaller. 
Greater value is 1 
Smaller value is -1 
Identical value is -2 
Equal value is -0 
