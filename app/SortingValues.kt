
import com.google.gson.annotations.SerializedName

data class SortingValues(
    val averageProductPrice: Int,
    val bestMatch: Int,
    val deliveryCosts: Int,
    val distance: Int,
    val minCost: Int,
    val newest: Int,
    val popularity: Int,
    val ratingAverage: Double
)