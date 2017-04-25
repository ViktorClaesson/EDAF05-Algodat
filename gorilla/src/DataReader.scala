import scala.io.Source

/**
  * Created by Sebastian on 25/04/2017.
  */
object DataReader {

  def readToy(): Unit ={
    Source.fromFile("data")
  }

}
