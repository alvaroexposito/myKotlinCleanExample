package alvaro.exposito.mykotlincleanexample.view

import alvaro.exposito.mykotlincleanexample.R
import alvaro.exposito.mykotlincleanexample.utils.SPORT_BUNDLE_EXTRA
import alvaro.exposito.mykotlincleanexample.usercase.GetListSportsUC
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

    /**
     * This method get the Sports List and creates the Adapter to show them in a two columns Table
     */
    fun createAdapter() {
        GetListSportsUC().execute {
            this.recycler.layoutManager = GridLayoutManager(this, 2)
            this.recycler.adapter = SportAdapter(it) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(SPORT_BUNDLE_EXTRA, it)
                startActivity(intent)
            }
            this.recycler.adapter.notifyDataSetChanged()
        }
    }
}
