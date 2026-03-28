package com.cg.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cg.demo.MovieService;
import com.cg.demo.entities.Movies;

@Controller
public class MyController {

    @Autowired
    MovieService mService;

    // 🏠 Welcome Page
    @RequestMapping("/greet")
    public ModelAndView processGreet() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "Welcome to Movie App");
        mv.setViewName("welcome");
        return mv;
    }

    // 📌 Show Movie Page (Add + List)
    @RequestMapping("/movies")
    public ModelAndView showMovies() throws Exception {
        List<Movies> list = mService.searchAllMovies();

        ModelAndView mv = new ModelAndView();
        mv.addObject("movies", list);
        mv.setViewName("movie");

        return mv;
    }

    // ➕ Add Movie
    @RequestMapping("/addMovie")
    public ModelAndView addMovie(@RequestParam String name,
                                @RequestParam double rating,
                                @RequestParam String genre) throws Exception {

        Movies m = new Movies(name, rating, genre);
        mService.addMovie(m);

        return new ModelAndView("redirect:/movies");
    }

    // 🔍 Search by Genre
    @RequestMapping("/searchMovie")
    public ModelAndView searchMovie(@RequestParam String genre) throws Exception {

        List<Movies> list;

        if (genre == null || genre.isEmpty()) {
            list = mService.searchAllMovies();
        } else {
            list = mService.searchByGenre(genre);
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("movies", list);
        mv.setViewName("movie");

        return mv;
    }

    // ❌ Delete Movie (using name for simplicity)
    @RequestMapping("/deleteMovie")
    public ModelAndView deleteMovie(@RequestParam String name) throws Exception {

        mService.deleteMovie(name);

        return new ModelAndView("redirect:/movies");
    }
}