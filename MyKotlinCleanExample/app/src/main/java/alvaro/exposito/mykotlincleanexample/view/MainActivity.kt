package alvaro.exposito.mykotlincleanexample.view

import alvaro.exposito.mykotlincleanexample.R
import alvaro.exposito.mykotlincleanexample.model.Sport
import alvaro.exposito.mykotlincleanexample.view.components.SportAdapter
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
        items.add(Sport("http://lorempixel.com/400/200/sports/1/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/2/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/3/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/5/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/6/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/7/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/8/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/9/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/1/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/2/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/3/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/5/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/6/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/7/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/8/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/9/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/1/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/2/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/3/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/5/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/6/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/7/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/8/"))
        items.add(Sport("http://lorempixel.com/400/200/sports/9/"))

        this.recycler.layoutManager = GridLayoutManager(this, 2)
        this.recycler.adapter = SportAdapter(items)
        this.recycler.adapter.notifyDataSetChanged()
    }
}
