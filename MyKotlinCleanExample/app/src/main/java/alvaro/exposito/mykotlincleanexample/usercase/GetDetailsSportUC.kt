package alvaro.exposito.mykotlincleanexample.usercase

/**
 * Created by alvaroexpositoguerra on 15/5/17.
 */

class GetDetailsSportUC {

    /**
     * This method simulates a Server side generating the Sport Detail from de ID
     */
    fun execute(id: Int, listener: ((String) -> Unit)) {

        when (id){
            1 -> listener("Baseball")
            2 -> listener("Windsurfing")
            3 -> listener("Cycling")
            5 -> listener("Motorcycle")
            6 -> listener("Football")
            7 -> listener("Swimming")
            8 -> listener("Karate")
            9 -> listener("Athletism")
            else -> listener("No info")
        }
    }
}