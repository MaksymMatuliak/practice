package connection

import scala.util.Random
import scala.util.Try

class HttpService {
  val random = new Random(System.nanoTime())

  def getConnection(host: String, port: String): Connection = {
    if (random.nextBoolean()) new Connection
    else throw new RuntimeException("Someone took port")
  }

  def renderHTML(page: String): Unit = println(page)
}

object testHttpService extends App {
  val hostname = "localhost"
  val port = "80"
  val httpService = new HttpService
  val possibleConnection = Try(httpService.getConnection(hostname, port))
  val possibleHTML = possibleConnection.flatMap(connection => Try(connection.get("/home")))

  possibleHTML.foreach(httpService.renderHTML)

  val page = for {
    c <- Try(httpService.getConnection(hostname, port))
    page <- Try(c.get("/home"))
  } yield httpService.renderHTML(page)

}
