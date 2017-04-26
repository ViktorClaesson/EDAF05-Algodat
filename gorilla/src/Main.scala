/**
  * Created by viktor on 2017-04-25.
  */
object Main {

  def main(args: Array[String]): Unit = {

    val costMatrix = CostMatrixReader.readMatrix("data/BLOSUM62.txt")
    //costMatrix.foreach(l => println(l.mkString(", ")))

    val str1 = """MVHLTPEEKSAVTALWGKVNVDEVGGEALGRLLVVYPWTQRFFESFGDLSTPDAVMGNPKVKAHGKKVLG
    AFSDGLAHLDNLKGTFATLSELHCDKLHVDPENFRLLGNVLVCVLAHHFGKEFTPPVQAAYQKVVAGVAN
    ALAHKYH"""

    val str2 = """VHLTPVEKSAVTALWGKVNVDEVGGEALGRLLVVYPWTQRFFESFGDLSTPDAVMGNPKVKAHGKKVLGA
    FSDGLAHLDNLKGTFATLSELHCDKLHVDPENFRLLGNVLVCVLAHHFGKEFTPPVQAAYQKVVAGVANA
    LAHKYH"""

    val al = new Alignment(str1, str2)
    val cost = al.alignment(CostMatrixReader.readMatrix("data/BLOSUM62.txt"))
    val aligned = al.buildWords(costMatrix)
    println(cost)
    println(aligned._1)
    println(aligned._2)
  }
}
