/**
  * Created by viktor on 2017-04-25.
  */
object Main {

  def main(args: Array[String]): Unit = {

    val costMatrix = CostMatrixReader.readMatrix("data/BLOSUM62.txt")

    val snark = "KQRIKAAKABK"
    val bandersnatch = "KAK"
    val sphinx = "KQRK"

    val str1 = "MVHLTPEEKSAVTALWGKVNVDEVGGEALGRLLVVYPWTQRFFESFGDLSTPDAVMGNPKVKAHGKKVLGAFSDGLAHLDNLKGTFATLSELHCDKLHVDPENFRLLGNVLVCVLAHHFGKEFTPPVQAAYQKVVAGVANALAHKYH"
    val str2 = "VHLTGEEKAAVTALWGKVNVDEVGGEALGRLLVVYPWTQRFFESFGDLSTPDAVMSNPKVKAHGKKVLGAFSDGLAHLDNLKGTFAQLSELHCDKLHVDPENFRLLGNVLVCVLAHHFGKEFTPQLQAAYQKVVAGVANALAHKYH"

    val al = new Alignment(str1, str2)
    val cost = al.alignment(CostMatrixReader.readMatrix("data/BLOSUM62.txt"))
    val aligned = al.buildWords(costMatrix)
    println(cost)
    println(aligned._1)
    println(aligned._2)
  }
}
