package alvaro.exposito.mykotlincleanexample.usercase

import alvaro.exposito.mykotlincleanexample.model.Sport

/**
 * Created by alvaroexpositoguerra on 14/5/17.
 */

class GetListSportsUC {

    fun execute(listener: (List<Sport>) -> (Unit)) {

        var items = ArrayList<Sport>()
        items.add(Sport("http://lorempixel.com/400/200/sports/1/", 1))
        items.add(Sport("http://lorempixel.com/400/200/sports/2/", 2))
        items.add(Sport("http://lorempixel.com/400/200/sports/3/", 3))
        items.add(Sport("http://lorempixel.com/400/200/sports/5/", 4))
        items.add(Sport("http://lorempixel.com/400/200/sports/6/", 5))
        items.add(Sport("http://lorempixel.com/400/200/sports/7/", 6))
        items.add(Sport("http://lorempixel.com/400/200/sports/8/", 7))
        items.add(Sport("http://lorempixel.com/400/200/sports/9/", 8))
        items.add(Sport("http://lorempixel.com/400/200/sports/1/", 9))
        items.add(Sport("http://lorempixel.com/400/200/sports/2/", 10))
        items.add(Sport("http://lorempixel.com/400/200/sports/3/", 11))
        items.add(Sport("http://lorempixel.com/400/200/sports/5/", 12))
        items.add(Sport("http://lorempixel.com/400/200/sports/6/", 13))
        items.add(Sport("http://lorempixel.com/400/200/sports/7/", 14))
        items.add(Sport("http://lorempixel.com/400/200/sports/8/", 15))
        items.add(Sport("http://lorempixel.com/400/200/sports/9/", 16))
        items.add(Sport("http://lorempixel.com/400/200/sports/1/", 17))
        items.add(Sport("http://lorempixel.com/400/200/sports/2/", 18))
        items.add(Sport("http://lorempixel.com/400/200/sports/3/", 19))
        items.add(Sport("http://lorempixel.com/400/200/sports/5/", 20))
        items.add(Sport("http://lorempixel.com/400/200/sports/6/", 21))
        items.add(Sport("http://lorempixel.com/400/200/sports/7/", 22))
        items.add(Sport("http://lorempixel.com/400/200/sports/8/", 23))
        items.add(Sport("http://lorempixel.com/400/200/sports/9/", 24))

        listener(items)
    }
}