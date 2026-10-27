package alvaro.exposito.mykotlincleanexample.usercase

import alvaro.exposito.mykotlincleanexample.model.Sport

/**
 * Created by alvaroexpositoguerra on 14/5/17.
 */

class GetListSportsUC {

    /**
     * This method simulates a Server side generating the Sports List with the ID and de URL to
     * get the image
     */
    fun execute(listener: (ArrayList<Sport>) -> (Unit)) {

        var items = ArrayList<Sport>()
        items.add(Sport("http://lorempixel.com/400/200/sports/1/", 1))
        items.add(Sport("http://lorempixel.com/400/200/sports/2/", 2))
        items.add(Sport("http://lorempixel.com/400/200/sports/3/", 3))
        items.add(Sport("http://lorempixel.com/400/200/sports/5/", 5))
        items.add(Sport("http://lorempixel.com/400/200/sports/6/", 6))
        items.add(Sport("http://lorempixel.com/400/200/sports/7/", 7))
        items.add(Sport("http://lorempixel.com/400/200/sports/8/", 8))
        items.add(Sport("http://lorempixel.com/400/200/sports/9/", 9))
        items.add(Sport("http://lorempixel.com/400/200/sports/1/", 1))
        items.add(Sport("http://lorempixel.com/400/200/sports/2/", 2))
        items.add(Sport("http://lorempixel.com/400/200/sports/3/", 3))
        items.add(Sport("http://lorempixel.com/400/200/sports/5/", 5))
        items.add(Sport("http://lorempixel.com/400/200/sports/6/", 6))
        items.add(Sport("http://lorempixel.com/400/200/sports/7/", 7))
        items.add(Sport("http://lorempixel.com/400/200/sports/8/", 8))
        items.add(Sport("http://lorempixel.com/400/200/sports/9/", 9))
        items.add(Sport("http://lorempixel.com/400/200/sports/1/", 1))
        items.add(Sport("http://lorempixel.com/400/200/sports/2/", 2))
        items.add(Sport("http://lorempixel.com/400/200/sports/3/", 3))
        items.add(Sport("http://lorempixel.com/400/200/sports/5/", 5))
        items.add(Sport("http://lorempixel.com/400/200/sports/6/", 6))
        items.add(Sport("http://lorempixel.com/400/200/sports/7/", 7))
        items.add(Sport("http://lorempixel.com/400/200/sports/8/", 8))
        items.add(Sport("http://lorempixel.com/400/200/sports/9/", 9))

        listener(items)
    }
}