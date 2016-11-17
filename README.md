# Movie Browser

This project is an app that connects to the OMDb public API for movie information. It allows you to star movies for referencing later. There are a fews things in the project that I didn't get time to complete, but wanted to note them so my intent can be understood:

- Adding an activity transition between the movie list item selected and detail page.
- Dynamically update the lists when changes were made to the database
- Animate list item transitions (i.e.: not using notifyDatasetChanged() )
- Adding a few other Material Design touches

I'm more than happy to talk about how I would implement solutions for all the above. There are two practices that I have used for a while that I will explain in further detail; Controllers and Managers.

## Controllers

I’m not a fan of Fragments :) There are several reasons for this, but mainly I’ve found that managing multiple life-cycles brings with it many headaches. I’ve been working with my controller implementation for a while, but am only now attempting to formalize it. Unfortunately, it’s not ready yet, so this project only has a very simple implementation of controllers. Controllers should contain all interactions with any relevant views. If you use Fragments, they will fill a similar space with hopefully less overhead. Also see ViewPagerAdapter for creating a ViewPager with Controllers.

## Managers

Managers allow you to abstract all data querying and CRUD operations out of the rest of the code base. This makes the task of replacing the data source much easier because only the managers need to be changed. All other code in the app is safe and just relies on models being passed.