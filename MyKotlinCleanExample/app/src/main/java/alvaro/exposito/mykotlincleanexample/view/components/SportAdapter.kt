package alvaro.exposito.mykotlincleanexample.view.components

import alvaro.exposito.mykotlincleanexample.R
import alvaro.exposito.mykotlincleanexample.model.Sport
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by alvaroexpositoguerra on 13/5/17.
 */


class SportAdapter(val items: List<Sport>) : RecyclerView.Adapter<SportAdapter.SportViewHolder>() {

    override fun getItemCount(): Int{
        return items.size
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        return SportViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sport_item, parent, false))
    }

    class SportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(sport: Sport) {

            var imageSport = itemView.findViewById(R.id.image_sport) as ImageView
            Picasso.with(itemView.context).load(sport.imageUrl).into(imageSport)
        }
    }
}