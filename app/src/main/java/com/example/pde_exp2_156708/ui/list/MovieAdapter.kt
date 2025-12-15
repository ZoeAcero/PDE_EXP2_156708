package com.example.pde_exp2_156708.ui.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pde_exp2_156708.R
import com.example.pde_exp2_156708.data.model.Movie
import com.example.pde_exp2_156708.ui.detail.DetailActivity

class MovieAdapter(
    private val context: Context,
    private var movieList: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // Constantes para los tipos de vista
    companion object {
        const val VIEW_TYPE_LINEAR = 1
        const val VIEW_TYPE_GRID = 2
    }

    // El tipo de vista actual, por defecto es LINEAR
    private var currentViewType: Int = VIEW_TYPE_LINEAR

    /**
     * Actualiza el tipo de vista y notifica al adapter si el tipo ha cambiado.
     */
    fun setViewType(newViewType: Int) {
        if (currentViewType != newViewType) {
            currentViewType = newViewType
            notifyDataSetChanged() // Refresca todas las vistas
        }
    }

    /**
     * Define qué layout usar basándose en la preferencia.
     */
    override fun getItemViewType(position: Int): Int {
        return currentViewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutId = when (viewType) {
            VIEW_TYPE_GRID -> R.layout.item_movie_grid
            VIEW_TYPE_LINEAR -> R.layout.item_movie_linear
            else -> R.layout.item_movie_linear
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie, currentViewType)

        // Manejar el clic al detalle
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_MOVIE_TITLE, movie.title)
                putExtra(DetailActivity.EXTRA_MOVIE_DIRECTOR, movie.director)
                putExtra(DetailActivity.EXTRA_MOVIE_SYNOPSIS, movie.synopsis)
                putExtra(DetailActivity.EXTRA_MOVIE_IMAGE_URL, movie.imageUrl)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = movieList.size

    fun updateList(newList: List<Movie>) {
        movieList = newList
        notifyDataSetChanged()
    }

    // ViewHolder para contener las vistas de cada elemento
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView? = itemView.findViewById(R.id.movie_title)
        private val posterImageView: ImageView = itemView.findViewById(R.id.movie_poster)

        fun bind(movie: Movie, viewType: Int) {
            // Cargar la imagen usando Glide
            Glide.with(itemView.context)
                .load(movie.imageUrl)
                .placeholder(R.drawable.ic_launcher_background) // Placeholder de carga
                .into(posterImageView)

            // El título solo existe en el layout LINEAR
            if (viewType == VIEW_TYPE_LINEAR) {
                titleTextView?.text = movie.title
            }
        }
    }
}