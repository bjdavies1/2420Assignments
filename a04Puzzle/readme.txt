/******************************************************************************
 *  Name:     Brady Davies
 *  NetID:    No idea
 *  Precept:  No idea
 *
 *  Partner Name:       None
 *  Partner NetID:      
 *  Partner Precept:    
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 4: Slider Puzzle


/******************************************************************************
 *  Explain briefly how you implemented the Board data type.
 *****************************************************************************/
I mainly used the double array with everything, but I feel this may be a slight
mistake, due to me having two double arrays for every call to a new Board...Not sure
how to fix this.


/******************************************************************************
 *  Explain briefly how you represented a search node
 *  (board + number of moves + previous search node).
 *****************************************************************************/
I made a class for it so that we could trace it all the way back with a stack.






/******************************************************************************
 *  Explain briefly how you detected unsolvable puzzles.
 *
 *  What is the order of growth of the running time of your isSolvable()
 *  method as function of the board size n? Recall that with order-of-growth
 *  notation, you should discard leading coefficients and lower-order terms,
 *  e.g., n log n or n^3.

 *****************************************************************************/
If there were an odd number of inversions, plus the blank spaces level if there were an even
number of rows/columns.


Order of growth of running time:
This was mainly noticeable when the arrays went up a dimension, so from 3x3 to 4x4.


/******************************************************************************
 *  For each of the following instances, give the minimum number of moves to
 *  solve the instance (as reported by your program). Also, give the amount
 *  of time your program takes with both the Hamming and Manhattan priority
 *  functions. If your program can't solve the instance in a reasonable
 *  amount of time (say, 5 minutes) or memory, indicate that instead. Note
 *  that your program may be able to solve puzzle[xx].txt even if it can't
 *  solve puzzle[yy].txt even if xx > yy.
 *****************************************************************************/


                 min number          seconds
     instance     of moves     Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt   38		270		2
   puzzle30.txt   44		5+mins		1
   puzzle32.txt   76				25
   puzzle34.txt   64				3
   puzzle36.txt   78				3
   puzzle38.txt   62				3
   puzzle40.txt   106				4
   puzzle42.txt   106				4				

Given how times just kept getting longer, I'm guesing hamming takes longer, but it does seem to find 
the solution in fewer moves, at least with the first one.

/******************************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer: a faster computer (say, 2x as fast), more memory
 *  (say 2x as much), a better priority queue (say, 2x as fast),
 *  or a better priority function (say, one on the order of improvement
 *  from Hamming to Manhattan)? Why?
 *****************************************************************************/
Given how my initial stuff went, I would say more memory for my computer. Just so that
that way It might be able to do more, and keep everything up. That would be my choice from the options listed.




/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
Probably memory. Hamming works, but takes a lot longer compared to Manhattan


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/
I recived some help from Alex in the tutoring center.




/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/
The main problem I encountered was with that Java doesn't pass values, it passes the thing itself... I know that's
a horrible explanation, but I forget wording of things from time to time.


/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/







/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/
I have enjoyed doing it, but it gets frustrating with knowing that I am taking up to much memory, and 
not knowing how to solve things better. Maybe another class that is to build the solution...
It is a frustrating assignment when I didn't know what I was doing, but satisfying when I got it right at the end.