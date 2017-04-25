/**
<<<<<<< HEAD
  * Created by viktor on 2017-04-25.
  */
object Main {

  def main(args: Array[String]) = {
    val arr = CostMatrixReader.readMatrix2("data/BLOSUM62.txt")
  }

}
=======
  * Created by Sebastian on 25/04/2017.
  */
object Main {

  def main(args: Array[String]): Unit = {
    CostMatrixReader.readMatrix(CostMatrixReader.costMatrix)
  }
}
>>>>>>> 8881ff03f089e7aaf7941617938eb0e646edd4f3
