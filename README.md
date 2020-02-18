# TSP-A_Star
Solving Travelling Salesman Problem using A start algorithm with heuristic as minimum spanning tree.

------------------------------------------------------------------------------------------------------------------------------------------

To Run do JAVA astar

--------------------------------------------

The heuristic used is Minimum Spanning Tree and the code is inspired from the texts which can be found in literature folder. 

The State Space is 
Initial State: Agent in the start city and has not visited any other city

Goal State: Agent has visited all the cities and reached the start city again

Successor Function: Generates all cities that have not yet visited

Edge-cost: distance between the cities represented by the nodes, use this cost to calculate g(n).

h(n): distance to the nearest unvisited city from the current city + estimated distance to travel all the unvisited cities (MST heuristic used here). Note that this is an admissible heuristic function.
