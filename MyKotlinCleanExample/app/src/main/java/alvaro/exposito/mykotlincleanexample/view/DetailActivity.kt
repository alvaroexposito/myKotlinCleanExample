package alvaro.exposito.mykotlincleanexample.view

import alvaro.exposito.mykotlincleanexample.R
import alvaro.exposito.mykotlincleanexample.model.Sport
import alvaro.exposito.mykotlincleanexample.usercase.GetDetailsSportUC
import alvaro.exposito.mykotlincleanexample.utils.SPORT_BUNDLE_EXTRA
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by alvaroexpositoguerra on 14/5/17.
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var sport = intent.getParcelableExtra<Sport>(SPORT_BUNDLE_EXTRA)
        sport?.let { s ->
            initLayout(s)
        }
    }

    /**
     * This method init the Layout loading the URL from de Cache from Picasso and the text From the
     * User Case simulating a Server request
     */
    fun initLayout(sport: Sport) {
        Picasso.with(this).load(sport.imageUrl).into(imageViewDetail)
        GetDetailsSportUC().execute(sport.id) { textViewDetail.text = it }
    }
}