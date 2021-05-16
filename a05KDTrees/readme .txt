/******************************************************************************
 *  Name:     Brady Davies
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name:       
 *  Partner NetID:      
 *  Partner Precept:    
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 5: Kd-Trees


/******************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 *****************************************************************************/
I basically did the same thing as BST in algs4, but I have key as a Point2D type.
/******************************************************************************
 *  Describe your method for range search in a kd-tree.
 *****************************************************************************/
The plan is to build other RectHV's and see if they intersect with the one we are given.
Doesn't quite seem to work...

/******************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 *****************************************************************************/
I would see is a point's rectangle was closer to the point in question, then check that spot and
all other points that could be closer, ignoring further ones.

/******************************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Show the math how you used to determine
 *  the operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 *****************************************************************************/

                       calls to nearest() per second
                     brute force               2d-tree
                     ---------------------------------
input100K.txt

input1M.txt

I got it running, but things aren't quite going right
/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
Brute force can go to entirely different screen, homemade tends to stick to one spot

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/
I received help from the tutor center in the Business building.

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/
Still have the same problems of not feeling like I have enough time.

/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/




/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on  how helpful the class meeting was and on how much you learned 
 * from doing the assignment, and whether you enjoyed doing it.
 *****************************************************************************/
Most of the time I enjoy learning, but right now I'm hitting a bit of a roadblock
of where to go.