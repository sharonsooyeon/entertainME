/**
 * Movie.java
 * Written by: Eva Stern-Rodriguez and Sharon Kim
 * Modified date: May 13, 2016
 * CS230 Final Project
 * <p>
 * Implements a class called Movie that represents a movie.
 * <p>
 * It contains instance data that store the title, genre, length, actors, director, 
 * popularity, year, and rank of the movie. The rank of the movie depends on user input 
 * and captures the functionality of our program.
 * <p>
 * Constructor: initializes all data.
 * <p>
 * In addition to getters and setters that make sense to have, the toString prints the 
 * Movie data in a nice way for testing purposes.
 * <p>
 * Notable methods:
 *   computeRank: additively computes the ranking of this Movie based on four factors: 
 *                length, popularity, actors, and director
 *   [actor/director/time]Exists: 
 *                because a Movie either contains an actor/director or does not and is 
 *                either within the user's given time contraints or does not, these methods 
 *                check for the presence of the actor/director and determines whether this 
 *                Movie is within the user's time constraints and returns a 1 if the user's 
 *                preferences are matched, and a 0 if they are not.
 *   compareTo:   implements the Comparable interface by comparing the rank of this Movie 
 *                to other Movie for later sorting purposes.
 */

import java.util.Vector;

public class Movie implements Comparable<Movie> {
  
  // instance variables
  private String title;
  private String genre;
  private int length;
  private Vector<String> actors;
  private String director;
  private int popularity;
  private int year;
  private int rank;
  
  // constructor
  public Movie(String title, String genre, int length, Vector<String> actors, 
               String director, int popularity, int year) {
    this.title = title;
    this.genre = genre;
    this.length = length;
    this.actors = actors;
    this.director = director;
    this.popularity = popularity;
    this.year = year;
  }
  
  
  /**
   * Returns this Movie's title
   * 
   * @param
   * @return title this Movie's title
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * Returns this Movie's genre
   * 
   * @param
   * @return genre this Movie's genre
   */
  public String getGenre() {
    return genre;
  }
  
  /**
   * Returns this Movie's length
   * 
   * @param
   * @return length this Movie's length
   */
  public int getLength() {
    return length;
  }
  
  /**
   * Returns this Movie's actors
   * 
   * @param
   * @return actors this Movie's actors
   */
  public Vector<String> getActors() {
    return actors;
  }
  
  /**
   * Returns this Movie's director
   * 
   * @param
   * @return director this Movie's director
   */
  public String getDirector() {
    return director;
  }
  
  /**
   * Returns this Movie's popularity
   * 
   * @param
   * @return popularity this Movie's popularity
   */
  public int getPopularity() {
    return popularity;
  }
  
  /**
   * Returns this Movie's year
   * 
   * @param
   * @return year this Movie's year
   */
  public int getYear() {
    return year;
  }
  
  /**
   * Returns this Movie's rank
   * 
   * @param
   * @return rank this Movie's rank
   */
  public int getRank() {
    return rank;
  }
  
  /**
   * Sets this Movie's rank; necessary for later sorting purposes.
   * 
   * @param  newRank the new rank of this Movie
   * @return
   */
  public void setRank(int newRank) {
    rank = newRank;
  }
  
  /**
   * Returns 1 iff the actor exists in this Movie; 0 otherwise
   * 
   * @param  act  the actor to check if exists in this Movie
   * @return      1 iff the actor exists in this Movie; 0 otherwise
   */
  public int actorExists(String act) {
    return (actors.contains(act) ? 1 : 0);
  }
  
  /**
   * Returns 1 iff the director exists in this Movie; 0 otherwise
   * 
   * @param  dir  the director to check if exists in this Movie
   * @return      1 iff the director exists in this Movie; 0 otherwise
   */
  public int directorExists(String dir) {
    return (director.equals(dir) ? 1 : 0);
  }
  
  /**
   * Returns 1 iff this Movie is within the time limit constraints
   * 
   * @param  time  the time constraint to check for
   * @return       1 iff this Movie is within the time constraints; 0 otherwise
   */
  public int timeExists(int time) {
    return (length <= time ? 1 : 0);
  }
  
  
  @Override
  /**
   * Returns a String representation of this Movie
   * 
   * @param
   * @return      the String representation of this Movie
   */
  public String toString() {
    String s = "";
    s += "Title:\t\t" + title + "\n"; 
    s += "Genre:\t\t" + genre + "\n";
    s += "Length:\t\t" + length + " minutes\n";
    s += "Actors:\t\t" + actors.toString() + "\n";
    s += "Director:\t\t" + director + "\n";
    s += "Popularity:\t" + popularity + "\n";
    s += "Year:\t\t" + year + "\n\n";
    return s;
  }
  
  
  /* Individual user rating is computed by how much the user values 
   * the length, year, popularity, actors, and directors of each Movie 
   * object. The user inputs in weights of these factors in the GUI.
   * 
   * @param  l  the weight of the length     [0, 5]
   * @param  p  the weight of the popularity [0, 5]
   * @param  a  the weight of the actors     [0, 5]
   * @param  d  the weight of the director   [0, 5]
   * @return
   */
  public void computeRank(int l, int p, int a, int d) {
    int r = l + popularity*p + a + d;
    this.setRank(r);
  }
  
  /**
   * Implements the Comparable interface; returns the difference in 
   * rank between this Movie and other Movie
   * 
   * @param  other  the other Movie to which to compare
   * @return        -int iff this rank is less than other rank; +int otherwise; 0 iff same
   */
  public int compareTo(Movie other) {
    return this.rank - other.rank;
  }
}