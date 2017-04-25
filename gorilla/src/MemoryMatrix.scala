import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by Sebastian on 25/04/2017.
  */

object MemoryMatrix {

  def createMatrix(fst: String, snd: String): ArrayBuffer[ArrayBuffer[Int]] ={
    val matrix = ArrayBuffer.fill(fst.length)(ArrayBuffer.fill(snd.length)(0))

    matrix(0) = matrix(0).zipWithIndex.map(t => t._2 * CostMatrixReader.emptyCost)

    for(r <- 0 until matrix.length){
      matrix(r)(0) = r * CostMatrixReader.emptyCost
    }

    matrix
  }

}
