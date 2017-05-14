package alvaro.exposito.mykotlincleanexample.view

import alvaro.exposito.mykotlincleanexample.R
import alvaro.exposito.mykotlincleanexample.SPORT_BUNDLE_EXTRA
import alvaro.exposito.mykotlincleanexample.model.Sport
import alvaro.exposito.mykotlincleanexample.view.components.SportAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createAdapter()
    }

    fun createAdapter() {

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

        this.recycler.layoutManager = GridLayoutManager(this, 2)
        this.recycler.adapter = SportAdapter(items) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(SPORT_BUNDLE_EXTRA,items)
            startActivity(intent)
        }
        this.recycler.adapter.notifyDataSetChanged()
    }
}
