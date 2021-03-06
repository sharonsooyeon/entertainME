/**
 * MyMoviesCollection.java
 * Written by: Sharon Kim and Eva Stern-Rodriguez
 * Modified date: May 6, 2016
 * CS230 Final Project
 * <p>
 * Implements a class called MyMoviesCollection that represents an ordered collection of 
 * Movie objects ranked by user's preferences/weights.
 * <p>
 * It contains instance data that stores a Vector of Movie objects that contains movies 
 * of only one genre, and an ArrayStack of Movie objects that have been "prioritized," 
 * or ranked, using the mergeSort sorting algorithm extended from the Collections interface.
 * <p>
 * Constructor: because we have decided that the genre is the most important factor when 
 * it comes to choosing a Movie, a MyMovieCollection can only be instantiated if and when 
 * the genre is known.
 * <p>
 * The toString prints the movie collection data in a nice way for testing purposes.
 * <p>
 * Notable methods:
 *   computeRank: given the user's preferences/weights, the computeRank iterates through 
 *                moviesOfGenre and sets the rank for every single Movie object in that 
 *                Vector.
 *   prioritize:  calls the Collection interface's mergeSort algorithm to sort the 
 *                moviesOfGenre and adds each Movie from moviesOfGenre to an ArrayStack 
 *                so that Movies can easily be popped off the ArrayStack as each new 
 *                movie is generated by the user in the GUI by clicking the 
 *                "Get My Movie" button.
 */

import java.util.*;
import javafoundations.*;

public class MyMovieCollection {
  
  // instance variables
  private ArrayStack<Movie> myRankedStack;
  private Vector<Movie> moviesOfGenre;
  
  // constructor
  public MyMovieCollection(String genre) {
    myRankedStack = new ArrayStack<Movie>();
    moviesOfGenre = new MovieLibrary("film.csv").getMoviesOfGenre(genre);
  }
  
  
  /**
   * Computes the ranking of each Movie in moviesOfGenre
   * 
   * @param  time      the length of the movie
   * @param  actor     the name of the actor
   * @param  director  the name of the director
   * @param  wTime     the weight of the length of the movie
   * @param  wActor    the weight of the actor
   * @param  wDirector the weight of the director
   * @return
   */
  public void computeRank(int time, String actor, String director, 
                          int wTime, int wActor, int wDirector) {
    for (int i = 0; i < moviesOfGenre.size(); i++) {
      Movie current = moviesOfGenre.elementAt(i);
      int actorExists = current.actorExists(actor);
      int dirExists = current.directorExists(director);
      int timeExists = current.timeExists(time);
      current.setRank(current.getPopularity() + 
                      (actorExists*wActor) + 
                      (dirExists*wDirector) + 
                      (timeExists*wTime));
    }
  }
  
  
  /**
   * Calls the Collection interface's mergeSort algorithm to sort moviesOfGenre 
   * and adds each Movie from moviesOfGenre to an ArrayStack so each Movie can 
   * easily be popped off the ArrayStack. Returns this ArrayStack.
   * 
   * @param
   * @return      the ordered ArrayStack of Movie objects to be returned
   */
  public ArrayStack<Movie> prioritize() {
    
    Collections.sort(moviesOfGenre);
    
    for (int i = 0; i < moviesOfGenre.size(); i++)
      myRankedStack.push(moviesOfGenre.remove(i));
    
    return myRankedStack;
  }
  
  
  /**
   * Returns a String representation of moviesOfGenre
   * 
   * @param
   * @return      the String representation of moviesOfGenre
   */
  public String toString() {
    
    String s = "";
    for (int i = 0; i < moviesOfGenre.size(); i++)
      s += moviesOfGenre.elementAt(i);
    
    return s;
  }
  
  
  public static void main(String[] args) {
    MyMovieCollection m = new MyMovieCollection("Horror");
    m.prioritize();
    System.out.println(m);
  }
}