package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        // your code here
        movieMap.put(movie.getName(),movie);
    }

    public void saveDirector(Director director){
        // your code here
        directorMap.put(director.getName(),director);
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            // your code here
            List<String> movieList=directorMovieMapping.getOrDefault(director,new ArrayList<String>());
            movieList.add(movie);
            directorMovieMapping.put(director, movieList);

        }
    }

    public Movie findMovie(String movie){
        // your code here
        Movie movieDetails=movieMap.get(movie);
        return movieDetails;
    }

    public Director findDirector(String director){
        // your code here
        Director directorDetails=directorMap.get(director);
        return directorDetails;
    }

    public List<String> findMoviesFromDirector(String director){
        // your code here
        if(!directorMovieMapping.containsKey(director))
            return new ArrayList<>();
//        for(String movie:directorMovieMapping.get(director))
//            System.out.print(movie+" ");
        return directorMovieMapping.get(director);
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        // your code here
//        System.out.println("Director Name:"+ director);
        if(!directorMap.containsKey(director))
            return;

        //Remove all movies by this director from movieDB as well
        for(String movieName:directorMovieMapping.get(director)){
            if(movieMap.containsKey(movieName))
                movieMap.remove(movieName);
        }
        directorMap.remove(director);
        directorMovieMapping.remove(director);
    }

    public void deleteAllDirector(){
        // your code here
        for(Map.Entry<String,List<String>> entry:directorMovieMapping.entrySet()){
            String director=entry.getKey();
            for(String movieName:directorMovieMapping.get(director))
            {
                if(movieMap.containsKey(movieName))
                    movieMap.remove(movieName);
            }
        }
        directorMovieMapping.clear();
        directorMap.clear();
    }
}